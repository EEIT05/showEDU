package showEDU.com.web.forum.model;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import showEDU.com.web.member.model.MemberBean;

@Entity
@Table(name = "CommentSec")
public class CommentSecBean {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer commentSecId;
	String content;
	Timestamp registerTime;
	@ManyToOne
	@JoinColumn(name = "FK_commentId", nullable=false)
	CommentBean commentBean;
	@OneToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
	@JoinColumn(name = "FK_memberId")
	MemberBean memberBean;
	@Transient
	String time;
	@Transient
	Integer likeCount = 0;
	@Transient
	Integer dislikeCount = 0;
	public CommentSecBean() {
		super();
	}
	public CommentSecBean(Integer commentSecId, String content, Timestamp registerTime, CommentBean commentBean,
			MemberBean memberBean, String time, Integer likeCount, Integer dislikeCount) {
		super();
		this.commentSecId = commentSecId;
		this.content = content;
		this.registerTime = registerTime;
		this.commentBean = commentBean;
		this.memberBean = memberBean;
		this.time = time;
		this.likeCount = likeCount;
		this.dislikeCount = dislikeCount;
	}
	public Integer getCommentSecId() {
		return commentSecId;
	}
	public void setCommentSecId(Integer commentSecId) {
		this.commentSecId = commentSecId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getRegisterTime() {
		return registerTime;
	}
	public void setRegisterTime(Timestamp registerTime) {
		this.registerTime = registerTime;
	}
	public CommentBean getCommentBean() {
		return commentBean;
	}
	public void setCommentBean(CommentBean commentBean) {
		this.commentBean = commentBean;
	}
	public MemberBean getMemberBean() {
		return memberBean;
	}
	public void setMemberBean(MemberBean memberBean) {
		this.memberBean = memberBean;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Integer getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(Integer likeCount) {
		this.likeCount = likeCount;
	}
	public Integer getDislikeCount() {
		return dislikeCount;
	}
	public void setDislikeCount(Integer dislikeCount) {
		this.dislikeCount = dislikeCount;
	}
	
	
	
}
