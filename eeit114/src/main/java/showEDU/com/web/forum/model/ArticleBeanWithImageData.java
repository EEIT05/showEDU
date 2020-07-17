package showEDU.com.web.forum.model;

public class ArticleBeanWithImageData {
	ArticleBean articleBean;
	String imageData;
	public ArticleBeanWithImageData() {
		super();
	}
	public ArticleBeanWithImageData(ArticleBean articleBean, String imageData) {
		super();
		this.articleBean = articleBean;
		this.imageData = imageData;
	}
	public ArticleBean getArticleBean() {
		return articleBean;
	}
	public void setArticleBean(ArticleBean articleBean) {
		this.articleBean = articleBean;
	}
	public String getImageData() {
		return imageData;
	}
	public void setImageData(String imageData) {
		this.imageData = imageData;
	}
	
	
}
