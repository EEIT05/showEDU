package showEDU.com.web.forum.model;

import java.sql.Blob;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

import showEDU.com.web.member.model.MemberBean;

@Entity
@Table(name = "Article")
public class ArticleBean {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer artId;
	String title;
	@Column(length = 1000 )
	String content;
	@Transient
	String registerTime;
	@Transient
	Integer replyCount;
	String viewCount;
	
	@ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
	@JoinColumn(name = "FK_artType")
	ArtTypeBean artTypeBean;
	
	@OneToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
	@JoinColumn(name = "FK_memberId")
	MemberBean memberBean;
	
	@ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
	@JoinColumn(name = "FK_boardId")
	DiscussionBoardBean discussionBoardBean;
	@Column(nullable = true)
	@JsonIgnore
	Blob articleImage;
	@Column(nullable = true)
	String fileName;
	@Transient
	Integer typeId;
	@Transient
	MultipartFile image;
	
	public ArticleBean() {
		super();
	}

	public ArticleBean(Integer artId, String title, String content, String registerTime, Integer replyCount,
			String viewCount, ArtTypeBean artTypeBean, MemberBean memberBean, DiscussionBoardBean discussionBoardBean,
			Blob articleImage, String fileName, Integer typeId, MultipartFile image) {
		super();
		this.artId = artId;
		this.title = title;
		this.content = content;
		this.registerTime = registerTime;
		this.replyCount = replyCount;
		this.viewCount = viewCount;
		this.artTypeBean = artTypeBean;
		this.memberBean = memberBean;
		this.discussionBoardBean = discussionBoardBean;
		this.articleImage = articleImage;
		this.fileName = fileName;
		this.typeId = typeId;
		this.image = image;
	}

	public Integer getArtId() {
		return artId;
	}

	public void setArtId(Integer artId) {
		this.artId = artId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}

	public Integer getReplyCount() {
		return replyCount;
	}

	public void setReplyCount(Integer replyCount) {
		this.replyCount = replyCount;
	}

	public String getViewCount() {
		return viewCount;
	}

	public void setViewCount(String viewCount) {
		this.viewCount = viewCount;
	}

	public ArtTypeBean getArtTypeBean() {
		return artTypeBean;
	}

	public void setArtTypeBean(ArtTypeBean artTypeBean) {
		this.artTypeBean = artTypeBean;
	}

	public MemberBean getMemberBean() {
		return memberBean;
	}

	public void setMemberBean(MemberBean memberBean) {
		this.memberBean = memberBean;
	}

	public DiscussionBoardBean getDiscussionBoardBean() {
		return discussionBoardBean;
	}

	public void setDiscussionBoardBean(DiscussionBoardBean discussionBoardBean) {
		this.discussionBoardBean = discussionBoardBean;
	}

	public Blob getArticleImage() {
		return articleImage;
	}

	public void setArticleImage(Blob articleImage) {
		this.articleImage = articleImage;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public MultipartFile getImage() {
		return image;
	}

	public void setImage(MultipartFile image) {
		this.image = image;
	}

	
	
	
}
