package showEDU.com.web.member.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;



@Entity
@Table(name="SHOW114_CreditCard")
public class CreditCardBean {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer cardId;
	
	private String cardNumber;
	private String exYear;
	private String exMounth;
	private String sctCode;
	
	@Transient
	private Integer memberId;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "FK_memberBean_Id")
	private MemberBean memberBean;
	
	public Integer getCardId() {
		return cardId;
	}
	public void setCardId(Integer cardId) {
		this.cardId = cardId;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getExYear() {
		return exYear;
	}
	public void setExYear(String exYear) {
		this.exYear = exYear;
	}
	public String getExMounth() {
		return exMounth;
	}
	public void setExMounth(String exMounth) {
		this.exMounth = exMounth;
	}
	public String getSctCode() {
		return sctCode;
	}
	public void setSctCode(String sctCode) {
		this.sctCode = sctCode;
	}
	public Integer getMemberId() {
		return memberId;
	}
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	public CreditCardBean(Integer cardId, String cardNumber, String exYear, String exMounth, String sctCode,
			Integer memberId) {
		super();
		this.cardId = cardId;
		this.cardNumber = cardNumber;
		this.exYear = exYear;
		this.exMounth = exMounth;
		this.sctCode = sctCode;
		this.memberId = memberId;
	}
	public CreditCardBean() {
		super();
	}
	
	
}
