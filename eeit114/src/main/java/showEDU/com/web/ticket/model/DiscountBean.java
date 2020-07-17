package showEDU.com.web.ticket.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
// 本類別封裝單筆書籍資料
@Entity
@Table(name="Discount")
public class DiscountBean implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	Integer 	discountId;
	
	@OneToMany(mappedBy = "discountBean")
	 Set<MovieOrderDetailBean> MovieOrderDetail = new HashSet<>();
	
	

	public DiscountBean() {
	}

	public DiscountBean(Integer discountId, Set<MovieOrderDetailBean> movieOrderDetail) {
		super();
		this.discountId = discountId;
		MovieOrderDetail = movieOrderDetail;
	}

	public Integer getDiscountId() {
		return discountId;
	}

	public void setDiscountId(Integer discountId) {
		this.discountId = discountId;
	}

	public Set<MovieOrderDetailBean> getMovieOrderDetail() {
		return MovieOrderDetail;
	}

	public void setMovieOrderDetail(Set<MovieOrderDetailBean> movieOrderDetail) {
		MovieOrderDetail = movieOrderDetail;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
