package showEDU.com.web.ticket.exception;

public class MovieNotFoundException extends RuntimeException {
	Integer movieId;
	public MovieNotFoundException() { }
	public MovieNotFoundException(Integer movieId) {
		this.movieId = movieId;
	}
	public Integer getMovieId() {
		return movieId;
	}
	public void setMovieId(Integer movieId) {
		this.movieId = movieId;
	}
	public MovieNotFoundException(String message) {
		super(message);
	}
	public MovieNotFoundException(Throwable cause) {
		super(cause);
	}
	public MovieNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
	public MovieNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
