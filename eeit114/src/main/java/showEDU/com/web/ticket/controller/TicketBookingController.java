package showEDU.com.web.ticket.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import showEDU.com.web.ticket.model.BookingCart;
//import showEDU.com.web.ticket.model.BookingCart;
import showEDU.com.web.ticket.model.MovieBean;
import showEDU.com.web.ticket.model.MovieLevelBean;
import showEDU.com.web.ticket.model.MovieShowtimeBean;
import showEDU.com.web.ticket.model.MovieStatusBean;
import showEDU.com.web.ticket.model.MovieTicketBean;
import showEDU.com.web.ticket.service.TicketBackEndService;

@Controller
@SessionAttributes({ "LoginOK", "BookingCart" })
public class TicketBookingController {
	@Autowired
	TicketBackEndService ticketBackEndService;
	@Autowired
	ServletContext ctx;
	@Autowired
	ServletContext context;

	// 連結取資料-FromDetail取電影時刻BEAN
	@GetMapping("/movieShowTime")
	public String getMovieShowTimeByID(@RequestParam("movieId") Integer movieId, Model model) {
		MovieBean mb = ticketBackEndService.getMovieDetailById(movieId);
		model.addAttribute("movie", mb);
		List<MovieShowtimeBean> mstb = ticketBackEndService.getMovieShowTimeById(movieId);
		model.addAttribute("movieShowTime", mstb);
		// 呼叫日期列表存至model
		Map<Integer, Date> date = getMovieShowTimeDateByMovieID(movieId);
		model.addAttribute("DateList", date);
		// 呼叫時間列表存至model
		Map<Integer, String> time = getMovieShowTimeTimeByMovieID(movieId);
		model.addAttribute("TimeList", time);

		return "ticket/booking";
	}

	// =========================座位圖▼=================================

	// 連結取資料-選完電影、日期、時間、票券依照條件產生座位圖
	@GetMapping("/seatmap")
	public String getSeatMap(
						@RequestParam("movieId") String movieId, 
						@RequestParam("date") String date,
						@RequestParam("time") String time, 
						@RequestParam("bookingTicket") List<String> bookingTicket, 
						Model model) {
		// ===============處理Integer===============
		Integer mm = Integer.parseInt(movieId);
		// 使用者選取的總票數
		List<Integer> ticketCount = new ArrayList<Integer>();
		Integer totalTicket = 0;
		for (int i = 0; i < bookingTicket.size(); i++) {
			Integer num = (Integer.parseInt(bookingTicket.get(i)));
			ticketCount.add(num);
			totalTicket = totalTicket + num;
		}
		
		
		// ===============處理日期==================
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date d = null;
		try {
			d = sdf.parse(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		java.sql.Date premierDate = new java.sql.Date(d.getTime());
		Date dd = premierDate;

		// 依照使用者選取的資訊取得seatsBean存至model
		List<Integer> sb = ticketBackEndService.getSeatsByOrderDetail(mm, dd, time);
		if (sb == null) { // 判斷陣列是否為空
			for (int i = 0; i < 200; i++) { // 空的就放入0代表未被購買
				sb.add(0);
			}
		} else {
			for (int i = 0; i < 200; i++) { // 不是空的就依照陣列放入
				try {
					if (sb.get(i) == null || sb.get(i) == 0) {
						sb.add(0);
					}
				} catch (IndexOutOfBoundsException e) {
					sb.add(0);
				}
			}
		}

//		// 取出存放在session物件內的BookingCart物件
//		BookingCart cart = (BookingCart) model.getAttribute("BookingCart");
//		// 如果找不到BookingCart物件
//		if (cart == null) {
//			// 就新建BookingCart物件
//			cart = new BookingCart();
//			// 並將此新建BookingCart的物件放到session物件內，成為它的屬性物件
//			System.out.println("新建BookingCart...");
//			model.addAttribute("BookingCart", cart);
//		}
		
		
		

		model.addAttribute("seats", sb);
		// 依照使用者選取的資訊取得seatsline存至model
		List<String> line = ticketBackEndService.getSeatsBeanlineLetters();
		model.addAttribute("line", line);
		// 依照使用者選取的資訊取得seatsrow存至model
		List<Integer> row = ticketBackEndService.getSeatsBeanrowNumber();
		model.addAttribute("row", row);
		// 依照使用者選取的資訊取得剩餘座位數存至model
		Integer reSeat = ticketBackEndService.getRemainingSeatsByUserSelected(mm, dd, time);
		model.addAttribute("reSeat", reSeat);
		// 依照使用者選取的資訊取得剩餘座位數存至model
		model.addAttribute("totalTicket", totalTicket);
		System.out.println("=============================");
		System.out.println(totalTicket);
		return "ticket/seats";
	}

	// =========================座位圖▲=================================

	// 方法-依照電影ID取得電影時刻"日期"列表
	public Map<Integer, Date> getMovieShowTimeDateByMovieID(Integer movieId) {
		Map<Integer, Date> mstdmap = new HashMap<>();
		List<Date> list = ticketBackEndService.getMovieShowTimeDateByMovieID(movieId);
		Integer count = 100;
		Integer listsize = list.size();

		for (Date msb : list) {
			mstdmap.put(count, msb);
			count++;
		}
		return mstdmap;
	}

	// 方法-依照電影ID取得電影時刻"時間"列表
	public Map<Integer, String> getMovieShowTimeTimeByMovieID(Integer movieId) {
		Map<Integer, String> mstdmap = new HashMap<>();
		List<String> list = ticketBackEndService.getMovieShowTimeTimeByMovieID(movieId);
		Integer count = 100;
		Integer listsize = list.size();

		for (String msb : list) {
			mstdmap.put(count, msb);
			count++;
		}
		return mstdmap;
	}

	// 方法-取得票券圖片
	@GetMapping("/getTicketImg/{movieTicketId}")
	public ResponseEntity<byte[]> getTicketImg(HttpServletResponse resp, @PathVariable Integer movieTicketId) {
		String filePath = "/resources/images/NoImage.jpg";

		byte[] media = null;
		HttpHeaders headers = new HttpHeaders();
		String filename = "";
		int len = 0;
		MovieTicketBean ticketbean = ticketBackEndService.getTicketById(movieTicketId);
		if (ticketbean != null) { // 如果該Bean找不到movieID代表沒資料
			Blob blob = ticketbean.getImage();
			filename = ticketbean.getImageName();
			if (blob != null) { // 如果存圖片的Blob欄位不是空的就取出
				try {
					len = (int) blob.length();
					media = blob.getBytes(1, len);
				} catch (SQLException e) {
					throw new RuntimeException("TicketBackEndController的getPicture()發生SQLException: " + e.getMessage());
				}
			} else { // 如果是空的就用空白圖片
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

	// 依照電影ID、日期、時間、影廳、訂票總數，取得座位table
	@GetMapping("/getSeats/{movieTicketId}")
	public List<Integer> getSeatsByOrderDetail(Model model, Integer movieId, Date date, String time,
			Integer totalticket) {
		List<Integer> seatlist = ticketBackEndService.getSeatsByOrderDetail(movieId, date, time);
		return seatlist;
	}

//	@SuppressWarnings("unchecked")
//	@PostMapping("/BookingTicket.do")
//	protected String buyBook(Model model, HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		MemberBean memberBean = (MemberBean) model.getAttribute("LoginOK");
//		if (memberBean == null) {
//			return "redirect: " + context.getContextPath() + "/_02_login/login";
//		}
//
//		HttpSession session = request.getSession(false);
//		if (session == null) {
//			return "redirect: " + context.getContextPath() + "/_02_login/login";
//		}
//
//		// 取出存放在session物件內的BookingCart物件
//		BookingCart cart = (BookingCart) model.getAttribute("BookingCart");
//		// 如果找不到BookingCart物件
//		if (cart == null) {
//			// 就新建BookingCart物件
//			cart = new BookingCart();
//			// 並將此新建BookingCart的物件放到session物件內，成為它的屬性物件
//			System.out.println("新建BookingCart...");
//			model.addAttribute("BookingCart", cart);
//		}
//		String bookIdStr = request.getParameter("bookId");
//		int bookId = Integer.parseInt(bookIdStr.trim());
//
//		String qtyStr = request.getParameter("qty");
//		Integer qty = 0;
//
//		Map<Integer, MovieOrderDetailBean> bookMap = (Map<Integer, MovieOrderDetailBean>) session
//				.getAttribute("products_DPP");
//		MovieOrderDetailBean bean = bookMap.get(bookId);
//		String pageNo = request.getParameter("pageNo");
//		if (pageNo == null || pageNo.trim().length() == 0) {
//			pageNo = (String) model.getAttribute("pageNo");
//			if (pageNo == null) {
//				pageNo = "1";
//			}
//		}
//
//		try {
//			// 進行資料型態的轉換
//			qty = Integer.parseInt(qtyStr.trim());
//		} catch (NumberFormatException e) {
//			throw new ServletException(e);
//		}
//		// 將訂單資料(價格，數量，折扣與BookBean)封裝到OrderItemBean物件內
////			MovieOrderDetailBean oib = new  MovieOrderDetailBean(null, null, bookId, bean.getDescription(), 
////					qty, bean.getListPrice(), bean.getDiscount(), bean.getTitle(), bean.getAuthor(), bean.getCompanyBean().getName());
////			// 將OrderItem物件內加入BookingCart的物件內
////			cart.addToCart(bookId, oib);
//
//		return "_03_listBooks/listBooks";
//	}

	// Auto-取得票價列表
	@ModelAttribute("tickets")
	public List<MovieTicketBean> getMovieTicketPrice() {
		List<MovieTicketBean> ticketlist = ticketBackEndService.getMovieTicketPrice();
		return ticketlist;
	}

	// Auto-取得電影級別列表
	@ModelAttribute("bookingmovieLevelList")
	public Map<Integer, String> getMovieLevelList() {
		Map<Integer, String> movieLevelMap = new LinkedHashMap<>();
		List<MovieLevelBean> list = ticketBackEndService.getMovieLevelList();
		for (MovieLevelBean mlb : list) {
			movieLevelMap.put(mlb.getMovieLevelId(), mlb.getLevel());
		}
		return movieLevelMap;
	}

	// Auto-取得電影狀態列表
	@ModelAttribute("bookingmovieStatusList")
	public Map<Integer, String> getMovieStatusList() {
		Map<Integer, String> movieStatusMap = new HashMap<>();
		List<MovieStatusBean> list = ticketBackEndService.getMovieStatusList();
		for (MovieStatusBean msb : list) {
			movieStatusMap.put(msb.getMovieStatusId(), msb.getStatus());
		}
		return movieStatusMap;
	}

	// Auto-取得電影列表
	@ModelAttribute("bookingmovieList")
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

}
