package showEDU.com.web.forum.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import showEDU.com.web.member.model.MemberBean;

@Entity
@Table(name = "Comment")
public class CommentBean {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer commentId;
	String content;
	Timestamp registerTime;
	@ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
	@JoinColumn(name = "FK_artId")
	ArticleBean articleBean;
	@OneToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
	@JoinColumn(name = "FK_memberId")
	MemberBean memberBean;
	@OneToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
	@JoinColumn(name = "FK_boardId")
	DiscussionBoardBean boardBean;
	@OneToMany(mappedBy = "commentBean", cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
	@JsonIgnore
	Set<CommentSecBean> commentSecBeans = new HashSet<>();
	@Transient
	String time;
	@Transient
	Integer likeCount = 0;
	@Transient
	Integer dislikeCount = 0;
	@Transient
	Boolean status = false;
	
	public CommentBean() {
		super();
	}

	public CommentBean(Integer commentId, String content, Timestamp registerTime, ArticleBean articleBean,
			MemberBean memberBean, DiscussionBoardBean boardBean, Set<CommentSecBean> commentSecBeans, String time,
			Integer likeCount, Integer dislikeCount, Boolean status) {
		super();
		this.commentId = commentId;
		this.content = content;
		this.registerTime = registerTime;
		this.articleBean = articleBean;
		this.memberBean = memberBean;
		this.boardBean = boardBean;
		this.commentSecBeans = commentSecBeans;
		this.time = time;
		this.likeCount = likeCount;
		this.dislikeCount = dislikeCount;
		this.status = status;
	}

	public Integer getCommentId() {
		return commentId;
	}

	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
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

	public ArticleBean getArticleBean() {
		return articleBean;
	}

	public void setArticleBean(ArticleBean articleBean) {
		this.articleBean = articleBean;
	}

	public MemberBean getMemberBean() {
		return memberBean;
	}

	public void setMemberBean(MemberBean memberBean) {
		this.memberBean = memberBean;
	}

	public DiscussionBoardBean getBoardBean() {
		return boardBean;
	}

	public void setBoardBean(DiscussionBoardBean boardBean) {
		this.boardBean = boardBean;
	}

	public Set<CommentSecBean> getCommentSecBeans() {
		return commentSecBeans;
	}

	public void setCommentSecBeans(Set<CommentSecBean> commentSecBeans) {
		this.commentSecBeans = commentSecBeans;
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

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	
	
	

}
