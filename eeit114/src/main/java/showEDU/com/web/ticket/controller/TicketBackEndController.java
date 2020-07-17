package showEDU.com.web.ticket.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Date;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialBlob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import showEDU.com.web.ticket.model.MovieBean;
import showEDU.com.web.ticket.model.MovieBeanWithImageData;
import showEDU.com.web.ticket.model.MovieLevelBean;
import showEDU.com.web.ticket.model.MovieStatusBean;
import showEDU.com.web.ticket.service.TicketBackEndService;
@Controller
public class TicketBackEndController {

	@Autowired
	TicketBackEndService ticketBackEndService;
	@Autowired
	ServletContext ctx;

	@Autowired
	ServletContext context;
	

	//新增電影-傳送空白表單
	@GetMapping("/ticketBackEnd")
	public String getAddNewProductForm(Model model) {
		MovieBean Movie = new MovieBean();
		model.addAttribute("movieBean", Movie);
		return "ticket/addMovie";
	}

	//新增電影-接收回傳表單
	@PostMapping("/ticketBackEnd")
	public String processAddNewProductForm(@ModelAttribute("movieBean") MovieBean mb, BindingResult result) {
		System.out.println("=============================start==========================");
		String[] suppressedFields = result.getSuppressedFields();
		if (suppressedFields.length > 0) {
			throw new RuntimeException("嘗試傳入不允許的欄位: " + StringUtils.arrayToCommaDelimitedString(suppressedFields));
		}
		
		MultipartFile movieImage = mb.getMovieImage();  //取得使用者上傳的圖檔
		String sss = mb.getActors();
		Date d = mb.getOffDate();
		System.out.println("========================"+d+"=======================================");
		System.out.println("========================"+sss+"=======================================");
//		String originalFilename = movieImage.getOriginalFilename();
		
		String movie = "movie";   //自定義檔案名稱 開頭為movie
		//自定義檔案名稱 結尾為該次新增的movieId
		String originalFilename = movie + ticketBackEndService.getMovieIdForImageName() + ".jpg";
		mb.setImageName(originalFilename);
		//  建立Blob物件，交由 Hibernate 寫入資料庫
		if (movieImage != null && !movieImage.isEmpty() ) {  //如果使用者有上傳檔案
			try {
				byte[] b = movieImage.getBytes();
				Blob blob = new SerialBlob(b);
				mb.setImage(blob);
			} catch(Exception e) {
				e.printStackTrace();
				throw new RuntimeException("檔案上傳發生異常: " + e.getMessage());
			}
		}
		ticketBackEndService.addMovie(mb);
		
		String ext = "movie";
		String rootDirectory = context.getRealPath("/");
		try {
			File photoFolder = new File(rootDirectory, "images");
			if (!photoFolder.exists()) photoFolder.mkdirs();
			File file = new File(photoFolder, mb.getMovieId() + ext);
			movieImage.transferTo(file);
		} catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException("檔案上傳發生異常: " + e.getMessage());
		}

		return "redirect:ticket/addMovie";  //在此重新回傳的是邏輯字串，不是jsp名稱
//		return "redirect:/movieList";  //在此重新回傳的是邏輯字串，不是jsp名稱
	}
	
	//連結-電影列表頁
	@GetMapping("/movieList")  
	public String movielist(Model model, HttpServletRequest req) {
		List<MovieBean> moviebeans = ticketBackEndService.getAllMovies();
		model.addAttribute("movies", moviebeans);
		return "ticket/movieList";
	}

	//連結-電影介紹頁
	@GetMapping("/movieDetail")
	public String getMovieDetailById(@RequestParam("movieId") Integer movieId, Model model) {
		MovieBean mb = ticketBackEndService.getMovieDetailById(movieId);
		model.addAttribute("movie", mb);
		return "ticket/movieDetail";
	}
	
	//方法-取得電影圖片
	@GetMapping("/getMovieImg/{movieId}")
	public ResponseEntity<byte[]> getMovieImg(HttpServletResponse resp, @PathVariable Integer movieId) {
		String filePath = "/resources/images/NoImage.jpg";

		byte[] media = null;
		HttpHeaders headers = new HttpHeaders();
		String filename = "";
		int len = 0;
		MovieBean bean = ticketBackEndService.getMovieById(movieId);
		if (bean != null) {  //如果該Bean找不到movieID代表沒資料
			Blob blob = bean.getImage();
			filename = bean.getImageName();
			if (blob != null) {  //如果存圖片的Blob欄位不是空的就取出
				try {
					len = (int) blob.length();
					media = blob.getBytes(1, len);
				} catch (SQLException e) {
					throw new RuntimeException("TicketBackEndController的getPicture()發生SQLException: " + e.getMessage());
				}
			} else {  //如果是空的就用空白圖片
				media = toByteArray(filePath);
				filename = filePath;
			}
		} else {
			media = toByteArray(filePath);
			filename = filePath;
		}
		headers.setCacheControl(CacheControl.noCache().getHeaderValue());
		String mimeType = context.getMimeType(filename);
		MediaType mediaType = MediaType.valueOf(mimeType);
		System.out.println("mediaType =" + mediaType);
		headers.setContentType(mediaType);
		ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(media, headers, HttpStatus.OK);
		return responseEntity;
	}
	
	//方法-取得電影級別圖片
	@GetMapping("/getMovieLevelImg/{movieLevelId}")
	public ResponseEntity<byte[]> getMovieLevelImg(HttpServletResponse resp, @PathVariable Integer movieLevelId) {
		String filePath = "/resources/images/NoImage.jpg";
		
		byte[] media = null;
		HttpHeaders headers = new HttpHeaders();
		String filename = "";
		int len = 0;
		MovieLevelBean mlb = ticketBackEndService.getMovieLevelById(movieLevelId);
		if (mlb != null) {  //如果該Bean找不到movieID代表沒資料
			Blob blob = mlb.getLevelPhoto();
			filename = mlb.getPhotoName();
			if (blob != null) {  //如果存圖片的Blob欄位不是空的就取出
				try {
					len = (int) blob.length();
					media = blob.getBytes(1, len);
				} catch (SQLException e) {
					throw new RuntimeException("TicketBackEndController的getPicture()發生SQLException: " + e.getMessage());
				}
			} else {  //如果是空的就用空白圖片
				media = toByteArray(filePath);
				filename = filePath;
			}
		} else {
			media = toByteArray(filePath);
			filename = filePath;
		}
		headers.setCacheControl(CacheControl.noCache().getHeaderValue());
		String mimeType = context.getMimeType(filename);
		MediaType mediaType = MediaType.valueOf(mimeType);
		System.out.println("mediaType =" + mediaType);
		headers.setContentType(mediaType);
		ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(media, headers, HttpStatus.OK);
		return responseEntity;
	}
	
	//Auto-取得電影級別列表
	@ModelAttribute("movieLevelList")
	public Map<Integer, String> getMovieLevelList() {
		Map<Integer, String> movieLevelMap = new LinkedHashMap<>();
		List<MovieLevelBean> list = ticketBackEndService.getMovieLevelList();
		for (MovieLevelBean mlb : list) {
			movieLevelMap.put(mlb.getMovieLevelId(), mlb.getLevel());
		}
		return movieLevelMap;
	}
	//Auto-取得電影狀態列表
	@ModelAttribute("movieStatusList")
	public Map<Integer, String> getMovieStatusList() {
		Map<Integer, String> movieStatusMap = new HashMap<>();
		List<MovieStatusBean> list = ticketBackEndService.getMovieStatusList();
		for (MovieStatusBean msb : list) {
			movieStatusMap.put(msb.getMovieStatusId(), msb.getStatus());
		}
		return movieStatusMap;
	}
	
	//Auto-取得電影列表
	@ModelAttribute("movieList")
	public Map<Integer, String> getMovieList() {
		Map<Integer, String> movieMap = new HashMap<>();
		List<MovieBean> list = ticketBackEndService.getMovieList();
		for (MovieBean msb : list) {
			movieMap.put(msb.getMovieId(), msb.getChName());
		}
		return movieMap;
	}
	
	private byte[] toByteArray(String filepath) {
		byte[] b = null;
		String realPath = context.getRealPath(filepath);
		try {
			File file = new File(realPath);
			long size = file.length();
			b = new byte[(int) size];
			InputStream fis = context.getResourceAsStream(filepath);
			fis.read(b);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return b;
	}
	
//	========================Ajax & Restful=======================
	@GetMapping("/singleMovie")
	public MovieBean  singlemovie(
			@RequestParam(value="movieId", defaultValue = "0") Integer movieId)  {
		MovieBean bean = ticketBackEndService.getMovieTextOnly(movieId);
		return bean;
	}
	
	@GetMapping("/allMovies")
	public ResponseEntity<List<MovieBeanWithImageData>>  allMoviesWithImageData()  {
		List<MovieBeanWithImageData> list = ticketBackEndService.getAllMoviesWithImageData();
		ResponseEntity<List<MovieBeanWithImageData>> re = new ResponseEntity<>(list, HttpStatus.OK);
		return re;
	}
	
	@GetMapping("/getmovieImage")
	public ResponseEntity<byte[]>  getImage(
			@RequestParam(value="id", defaultValue = "0") Integer movieId)  {
		byte[] b = null;
		System.out.println("movieId=" + movieId);
		MovieBean bean = ticketBackEndService.getMovieById(movieId);
		Blob blob = bean.getImage();
		try {
			b = blob.getBytes(1, (int)blob.length());
		} catch(Exception e) {
			e.printStackTrace();
		}	
		
		ResponseEntity<byte[]> re = new ResponseEntity<>(b, HttpStatus.OK );
		
		return re;
	}
	
	
	
	
	
}
