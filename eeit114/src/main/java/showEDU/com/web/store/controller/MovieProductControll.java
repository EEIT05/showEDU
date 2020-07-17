 package showEDU.com.web.store.controller;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialBlob;

//import org.apache.jasper.tagplugins.jstl.core.If;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import showEDU.com.web.store.model.CategoryBean;
import showEDU.com.web.store.model.PeripheralProductBean;
import showEDU.com.web.store.model.ProductCategoryBean;
import showEDU.com.web.store.model.ProductImageBean;
import showEDU.com.web.store.service.PeripheralService;
import showEDU.com.web.store.validator.ProductValidator;



@Controller
public class MovieProductControll {
	
	@Autowired
	PeripheralService peripheralService;
	@Autowired
	ServletContext ctx;
	
	//前台抓取所有商品
	@GetMapping("/showproduct")
	public String list(Model model, HttpServletRequest req) {
		List<PeripheralProductBean> beans = peripheralService.getAllProducts();
		model.addAttribute("products", beans);
		return "store/PeripheralProduct2";
	}
	
	//前台抓取所有商品Ajax
	@GetMapping("/Peripheralproduct2")
	public ResponseEntity<List<PeripheralProductBean>>ajaxGetAllProduct(HttpServletRequest req){
		List<PeripheralProductBean> beans = peripheralService.getAllProducts();
		ResponseEntity<List<PeripheralProductBean>> re = new ResponseEntity<List<PeripheralProductBean>>(beans,HttpStatus.OK);
		return re;
	}
	
	
	
//	@SuppressWarnings("unchecked")
//	@GetMapping("/Product")
//	public ResponseEntity<List<PeripheralProductBean>>AjaxgetProductById(@RequestParam("id") Integer productId){
//		List<PeripheralProductBean> beans = (List<PeripheralProductBean>) peripheralService.getProductById(productId);
//		ResponseEntity<List<PeripheralProductBean>> re = new ResponseEntity<List<PeripheralProductBean>>(beans,HttpStatus.OK);
//		return re;
//		
//	}
	//沒用到
//	@GetMapping("/Peripheralproduct")
//	public String getCategoryList(Model model, HttpServletRequest req) {
//		List<ProductCategoryBean> list = peripheralService.getAllCategories();
//		//System.out.println(list.get(0).getProductCategoryId()+list.get(0).getItems());
//		model.addAttribute("PeripheralcategoryList", list);
//		return "PeripheralProduct2";
//	}
	
	
	
	// 以類別抓取所有類別商品
//	@GetMapping("/Peripheralproducts/{id}")
//	public String getProductsByCategoryList(@PathVariable Integer id, Model model) {
//		List<PeripheralProductBean> beans = peripheralService.getProductsByCategory(id);
//		model.addAttribute("Peripheralcategory", beans);
//		return "PeripheralProduct2";
//	}
	
	//以類別抓取所有類別商品Ajax
	@GetMapping("/Peripheralproducts")
	public ResponseEntity<List<PeripheralProductBean>>ajaxGetProductByCategory(@RequestParam(value = "CategoryId",defaultValue = "0") Integer CategoryId){
		System.out.println(CategoryId);
		List<PeripheralProductBean> beans = peripheralService.getProductsByCategory(CategoryId);
		ResponseEntity<List<PeripheralProductBean>> re = new ResponseEntity<List<PeripheralProductBean>>(beans,HttpStatus.OK);
		return re;
	}
	
	
	//抓取單一商品
	@SuppressWarnings("unchecked")
	@GetMapping("/Product")
	public String getProductById(@RequestParam("id") Integer productId, Model model) {
		PeripheralProductBean beans =  peripheralService.getProductById(productId);
		model.addAttribute("product", beans);
	//	List list = peripheralService.getProductImage(productId);
		//model.addAttribute("list", list);
		return "store/ProductItem";
	}
	
//	//Ajax版抓取單一商品
//	@SuppressWarnings("unchecked")
//	@GetMapping("/Product")
//	public ResponseEntity<List<PeripheralProductBean>>AjaxgetProductById(@RequestParam("id") Integer productId){
//		List<PeripheralProductBean> beans = (List<PeripheralProductBean>) peripheralService.getProductById(productId);
//		ResponseEntity<List<PeripheralProductBean>> re = new ResponseEntity<List<PeripheralProductBean>>(beans,HttpStatus.OK);
//		return re;
//		
//	}
	
	@GetMapping("/getProductPicture/{productId}")
	public ResponseEntity<byte[]> getProductPicture(HttpServletResponse req,
			@PathVariable Integer productId){
		String noImagePath="/webapp/WEB-INF/views/MovieImages/NoImage.jpg";
		ResponseEntity<byte[]> re = null;
		//List<PeripheralProductBean> bean = peripheralService.getProductsByCategory(productId);
		PeripheralProductBean bean = peripheralService.getProductById(productId);
		//ProductImageBean iBean = bean.get(0);
		
		
		ByteArrayOutputStream baos = null;
		InputStream is = null;
		try {
			Blob blob = bean.getImages();
			
			if(blob !=null) {
				is = blob.getBinaryStream();					
			}else {
				;
			}
			if(is == null) {
				is=ctx.getResourceAsStream(noImagePath);
			}
			baos = new ByteArrayOutputStream();
			int len = 0;
			byte[] b = new byte[819200];
			while((len = is.read(b))!=-1) {
				baos.write(b,0,len);
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		byte[]b0 = baos.toByteArray();
		
		String filename = bean.getFileName();
		MediaType mediaType =null;
		if(filename!=null) {
			String mimeType = ctx.getMimeType(filename);
			mediaType = MediaType.valueOf(mimeType);
		}else {
			String mimeType = ctx.getMimeType(noImagePath);
			mediaType = MediaType.valueOf(mimeType);
		}
		HttpHeaders headers=new HttpHeaders();
		headers.setContentType(mediaType);
		headers.setCacheControl(CacheControl.noCache().getHeaderValue());
		re = new ResponseEntity<>(b0,headers,HttpStatus.OK);
		
		return re;
	}
	
//	@SuppressWarnings("unchecked")
//	@GetMapping("/getPictures/{productId}")
//	public ResponseEntity<byte[]> getPictures(HttpServletResponse req,
//			@PathVariable Integer productId ,Model model){
//		String noImagePath="/webapp/WEB-INF/views/MovieImages/NoImage.jpg";
//		ResponseEntity<byte[]> re = null;
//			
//		 ProductImageBean iBean = peripheralService.getItemImg(productId);
//			
//			
//			ByteArrayOutputStream baos = null;
//			InputStream is = null;
//			try {
//				Blob blob = iBean.getImages();
//				
//				if(blob !=null) {
//					is = blob.getBinaryStream();					
//				}else {
//					;
//				}
//				if(is == null) {
//					is=ctx.getResourceAsStream(noImagePath);
//				}
//				baos = new ByteArrayOutputStream();
//				int len = 0;
//				byte[] b = new byte[819200];
//				while((len = is.read(b))!=-1) {
//					baos.write(b,0,len);
//				}
//			} catch (Exception e) {
//				
//				e.printStackTrace();
//			}
//			byte[]b0 = baos.toByteArray();
//			
//			String filename = iBean.getFileName();
//			MediaType mediaType =null;
//			if(filename!=null) {
//				String mimeType = ctx.getMimeType(filename);
//				mediaType = MediaType.valueOf(mimeType);
//			}else {
//				String mimeType = ctx.getMimeType(noImagePath);
//				mediaType = MediaType.valueOf(mimeType);
//			}
//			HttpHeaders headers=new HttpHeaders();
//			headers.setContentType(mediaType);
//			headers.setCacheControl(CacheControl.noCache().getHeaderValue());
//			re = new ResponseEntity<>(b0,headers,HttpStatus.OK);
//			//imgMap.put(ImageBean, re);
//		
			
		//}
		//model.addAttribute("imgs", imgMap);
//		return re;
//		for (int i = 1; i <= bean.size(); i++) {
//			
//			ProductImageBean iBean = bean.get(i);
//		
//		
//		ByteArrayOutputStream baos = null;
//		InputStream is = null;
//		try {
//			Blob blob = iBean.getImages();
//			
//			if(blob !=null) {
//				is = blob.getBinaryStream();					
//			}else {
//				;
//			}
//			if(is == null) {
//				is=ctx.getResourceAsStream(noImagePath);
//			}
//			baos = new ByteArrayOutputStream();
//			int len = 0;
//			byte[] b = new byte[819200];
//			while((len = is.read(b))!=-1) {
//				baos.write(b,0,len);
//			}
//		} catch (Exception e) {
//			
//			e.printStackTrace();
//		}
//		byte[]b0 = baos.toByteArray();
//		
//		String filename = iBean.getFileName();
//		MediaType mediaType =null;
//		if(filename!=null) {
//			String mimeType = ctx.getMimeType(filename);
//			mediaType = MediaType.valueOf(mimeType);
//		}else {
//			String mimeType = ctx.getMimeType(noImagePath);
//			mediaType = MediaType.valueOf(mimeType);
//		}
//		HttpHeaders headers=new HttpHeaders();
//		headers.setContentType(mediaType);
//		headers.setCacheControl(CacheControl.noCache().getHeaderValue());
//		re = new ResponseEntity<>(b0,headers,HttpStatus.OK);
//		imgMap.put(i, re);
//		System.out.println("==============================================bbbbbbbbbbbb");
//		System.out.println(imgMap+"======================aaaaaaaaa");
//		//return re;
//		}
//		System.out.println(re+"=============");
//		model.addAttribute("imgMap", imgMap);
		
		
		
//	}
	
	//新增商品空白表單
	@GetMapping(value = "/product/add")
	public String getAddNewProduct(Model model) {
		PeripheralProductBean pp = new PeripheralProductBean();
		model.addAttribute("productBean", pp);
		//return 	"selectProduct2";
		return 	"store/addProduct2";
	}
	
	//送出新增商品
	@PostMapping(value = "/product/add")
	public String processAddNewProduct(@ModelAttribute("productBean") PeripheralProductBean pp ,BindingResult result) { 
		//ProductImageBean ppImg =  new ProductImageBean();
		ProductValidator validator = new ProductValidator();
		validator.validate(pp, result);
		if(result.hasErrors()) {
			return "store/addProduct2";
		}
		
		MultipartFile productImage = pp.getProductImage();
		String originalFilename = productImage.getOriginalFilename();
		pp.setFileName(originalFilename);
		//  建立Blob物件，交由 Hibernate 寫入資料庫
		if (productImage != null && !productImage.isEmpty() ) {
			try {
				byte[] b = productImage.getBytes();
				Blob blob = new SerialBlob(b);
				pp.setImages(blob);
			} catch(Exception e) {
				e.printStackTrace();
				throw new RuntimeException("檔案上傳發生異常: " + e.getMessage());
			}
		}
		Date now = new Date();
		pp.setCreatedate(now);
	    peripheralService.addproduct(pp);
	   
	    return "redirect:/product/backSelect";
	}
	
	//新增商品獲取類別列表
	@ModelAttribute("categoryList")
	public Map<Integer, String> getCategoryList() {
	    Map<Integer, String> categoryMap = new HashMap<>();
	    List<CategoryBean> list = peripheralService.getCategoryList();
	    for (CategoryBean cb : list) {
	        categoryMap.put(cb.getCategoryId(), cb.getCategory());
	    }
	    return categoryMap;
	}
	//新增商品獲取商品項目列表
	@ModelAttribute("ProductCategoryList")
	public Map<Integer, String> getProductCategoryList() {
	    Map<Integer, String> productCategoryMap = new HashMap<>();
	    List<ProductCategoryBean> list = peripheralService.geProducttCategoryList();
	    for (ProductCategoryBean pcb : list) {
	    	productCategoryMap.put(pcb.getProductCategoryId(), pcb.getItems());
	    }
	    return productCategoryMap;
	}
	
	//後臺查詢所有商品
	@GetMapping(value = "/product/backSelect")
	public String getAllProductSelect(Model model) {
		List<PeripheralProductBean>getAllProductSelect = peripheralService.getAllProductSelect();
		model.addAttribute("getAllProductSelect", getAllProductSelect);

		return 	"store/selectProduct2";
	}
	//A
	@GetMapping("/product/backSelect2")
	public ResponseEntity<List<PeripheralProductBean>>  getAllProductSelect()  {
		List<PeripheralProductBean> getAllProductSelect = peripheralService.getAllProductSelect();
		ResponseEntity<List<PeripheralProductBean>> re = new ResponseEntity<>(getAllProductSelect, HttpStatus.OK);
		return re;
	}
	
	//後臺修改取得修改商品
	@GetMapping(value = "/product/backUpdate/{id}")
	public String showDataForm(@PathVariable Integer id, Model model) {
		PeripheralProductBean product = peripheralService.getProductById(id);
//		String category=product.setCategoryBean(product.getCategoryBean().getCategory());
		model.addAttribute("updateProduct",product);
		return "store/updateProduct";

	}
	
	@PostMapping(value = "/product/backUpdate/{id}")
	public String modify(@ModelAttribute("updateProduct") PeripheralProductBean pp,BindingResult result, @PathVariable Integer id
			,HttpServletRequest request) {
		pp.setProductId(id); 
		ProductValidator validator = new ProductValidator();
		validator.validate(pp, result);
		if(result.hasErrors()) {
			return "store/updateProduct";
		}
		MultipartFile productImage = pp.getProductImage();
		String originalFilename = productImage.getOriginalFilename();
		pp.setFileName(originalFilename);
		//  建立Blob物件，交由 Hibernate 寫入資料庫
		if (productImage != null && !productImage.isEmpty() ) {
			try {
				byte[] b = productImage.getBytes();
				Blob blob = new SerialBlob(b);
				pp.setImages(blob);
			} catch(Exception e) {
				e.printStackTrace();
				throw new RuntimeException("檔案上傳發生異常: " + e.getMessage());
			}
		}
		Date now = new Date();
		pp.setCreatedate(now);
	    peripheralService.updateproduct(pp);
	    return 	"redirect:/product/backSelect";
	}
	
	@GetMapping("/product/backUpdate/delete/{id}")
	public String delete(@PathVariable Integer id) {
		
		peripheralService.delete(id);
		return "redirect:/product/backSelect";
	}
	
	@ModelAttribute
	public void getProduct(@PathVariable(value="id",required = false)Integer productId,Model model) {
		if (productId != null) {
			PeripheralProductBean product = peripheralService.getProductById(productId);
			model.addAttribute("product",product);
			}else {
				PeripheralProductBean product = new PeripheralProductBean();
				model.addAttribute("product",product); 
			}
	}
	
}
