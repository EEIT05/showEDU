package showEDU.com.web.application.model;



import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ActClass")
public class ActClassBean implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	Integer actClassId;
	String name;
	
	public ActClassBean() {
		super();
	}

	public Integer getActClassId() {
		return actClassId;
	}

	public void setActClassId(Integer actClassId) {
		this.actClassId = actClassId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
