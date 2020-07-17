package showEDU.com.web.forum.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "DiscussionBoard")
public class DiscussionBoardBean {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer  boardId;
	@OneToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
	@JoinColumn(name = "FK_movieId")
	ForumMovieBean movieBean;
	String viewCount;
	@Transient
	Integer movieId;
	@Transient
	String registerTime;
	@Transient
	Integer replyCounts;
	
	@OneToMany(mappedBy = "discussionBoardBean")
	@JsonIgnore
	Set<ArticleBean> articleBeans = new HashSet<>();
	
	
	public DiscussionBoardBean() {
		super();
	}


	public DiscussionBoardBean(Integer boardId, ForumMovieBean movieBean, String viewCount, Integer movieId,
			String registerTime, Integer replyCounts, Set<ArticleBean> articleBeans) {
		super();
		this.boardId = boardId;
		this.movieBean = movieBean;
		this.viewCount = viewCount;
		this.movieId = movieId;
		this.registerTime = registerTime;
		this.replyCounts = replyCounts;
		this.articleBeans = articleBeans;
	}


	public Integer getBoardId() {
		return boardId;
	}


	public void setBoardId(Integer boardId) {
		this.boardId = boardId;
	}


	public ForumMovieBean getMovieBean() {
		return movieBean;
	}


	public void setMovieBean(ForumMovieBean movieBean) {
		this.movieBean = movieBean;
	}


	public String getViewCount() {
		return viewCount;
	}


	public void setViewCount(String viewCount) {
		this.viewCount = viewCount;
	}


	public Integer getMovieId() {
		return movieId;
	}


	public void setMovieId(Integer movieId) {
		this.movieId = movieId;
	}


	public String getRegisterTime() {
		return registerTime;
	}


	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}


	public Integer getReplyCounts() {
		return replyCounts;
	}


	public void setReplyCounts(Integer replyCounts) {
		this.replyCounts = replyCounts;
	}


	public Set<ArticleBean> getArticleBeans() {
		return articleBeans;
	}


	public void setArticleBeans(Set<ArticleBean> articleBeans) {
		this.articleBeans = articleBeans;
	}


	
	
	
}
