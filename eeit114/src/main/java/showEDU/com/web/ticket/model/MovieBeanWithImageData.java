package showEDU.com.web.ticket.model;

public class MovieBeanWithImageData{
	
		MovieBean mbean;
		String imageData;
		
		public MovieBeanWithImageData() {
		}
		public MovieBeanWithImageData(MovieBean mbean, String imageData) {
			this.mbean = mbean;
			this.imageData = imageData;
		}
		public MovieBean getBean() {
			return mbean;
		}
		public void setBean(MovieBean mbean) {
			this.mbean = mbean;
		}
		public String getImageData() {
			return imageData;
		}
		public void setImageData(String imageData) {
			this.imageData = imageData;
		}
		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("MovieBeanWithImageData [mbean=");
			builder.append(mbean);
			builder.append(", imageData=");
			builder.append(imageData);
			builder.append("]");
			return builder.toString();
		}
		
		
		
}