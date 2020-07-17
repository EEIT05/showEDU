package showEDU.com.web.store.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Entity
@Table(name = "ProductOrdersItems")
public class ProductOrdersItemsBean implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer productOrdersItemsId;
	@ManyToOne
	@JoinColumn(name = "FK_ProductOrders_formNumber" )
	ProductOrdersBean productOrdersBean;
	String name;
	String phone;
	String address;
	@ManyToOne
	@JoinColumn(name="FK_PeripheralProductBean_Id")
	PeripheralProductBean peripheralProductBean;
	Integer productId;
	Integer buyCount;
	String useDiscount;
//	@ManyToOne
//	@JoinColumn
	//折扣資料表
	Integer discountId;
	String payStatus;
	String sendStatus;
	


	public ProductOrdersItemsBean(Integer productOrdersItemsId, ProductOrdersBean productOrdersBean, String name,
			String phone, String address, PeripheralProductBean peripheralProductBean, Integer productId,
			Integer buyCount, String useDiscount, Integer discountId, String payStatus, String sendStatus) {
		super();
		this.productOrdersItemsId = productOrdersItemsId;
		this.productOrdersBean = productOrdersBean;
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.peripheralProductBean = peripheralProductBean;
		this.productId = productId;
		this.buyCount = buyCount;
		this.useDiscount = useDiscount;
		this.discountId = discountId;
		this.payStatus = payStatus;
		this.sendStatus = sendStatus;
	}

	public ProductOrdersItemsBean() {
		// TODO Auto-generated constructor stub
	}

	public Integer getProductOrdersItemsId() {
		return productOrdersItemsId;
	}

	public void setProductOrdersItemsId(Integer productOrdersItemsId) {
		this.productOrdersItemsId = productOrdersItemsId;
	}

	public ProductOrdersBean getProductOrdersBean() {
		return productOrdersBean;
	}

	public void setProductOrdersBean(ProductOrdersBean productOrdersBean) {
		this.productOrdersBean = productOrdersBean;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public PeripheralProductBean getPeripheralProductBean() {
		return peripheralProductBean;
	}

	public void setPeripheralProductBean(PeripheralProductBean peripheralProductBean) {
		this.peripheralProductBean = peripheralProductBean;
	}

	public Integer getBuyCount() {
		return buyCount;
	}

	public void setBuyCount(Integer buyCount) {
		this.buyCount = buyCount;
	}

	public String getUseDiscount() {
		return useDiscount;
	}

	public void setUseDiscount(String useDiscount) {
		this.useDiscount = useDiscount;
	}

	public Integer getDiscountId() {
		return discountId;
	}

	public void setDiscountId(Integer discountId) {
		this.discountId = discountId;
	}

	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	public String getSendStatus() {
		return sendStatus;
	}

	public void setSendStatus(String sendStatus) {
		this.sendStatus = sendStatus;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
