package showEDU.com.web.forum.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import showEDU.com.web.member.model.MemberBean;

@Entity
@Table(name = "ThumbsUp")
public class ThumbsUpBean {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer thumbsId;
	
	@OneToOne
	@JoinColumn(name = "FK_commentId", nullable = true)
	CommentBean commentBean;
	
	@OneToOne
	@JoinColumn(name = "FK_commentSecId", nullable = true)
	CommentSecBean commentSecBean;
	
	@OneToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
	@JoinColumn(name = "FK_memberId", nullable = false)
	MemberBean memberBean;
	
	Integer status;
	

	public ThumbsUpBean() {
		super();
	}

	public ThumbsUpBean(Integer thumbsId, CommentBean commentBean, CommentSecBean commentSecBean, MemberBean memberBean,
			Integer status) {
		super();
		this.thumbsId = thumbsId;
		this.commentBean = commentBean;
		this.commentSecBean = commentSecBean;
		this.memberBean = memberBean;
		this.status = status;
	}

	public Integer getThumbsId() {
		return thumbsId;
	}

	public void setThumbsId(Integer thumbsId) {
		this.thumbsId = thumbsId;
	}

	public CommentBean getCommentBean() {
		return commentBean;
	}

	public void setCommentBean(CommentBean commentBean) {
		this.commentBean = commentBean;
	}

	public CommentSecBean getCommentSecBean() {
		return commentSecBean;
	}

	public void setCommentSecBean(CommentSecBean commentSecBean) {
		this.commentSecBean = commentSecBean;
	}

	public MemberBean getMemberBean() {
		return memberBean;
	}

	public void setMemberBean(MemberBean memberBean) {
		this.memberBean = memberBean;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	
	
	
}
