package showEDU._00_init;

/*  
    程式說明：建立表格與設定初始測試資料。
    表格包括：Book, BookCompany, Member, Orders, OrderItems
 
*/
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Blob;
import java.sql.Date;
import java.text.SimpleDateFormat;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import showEDU._00_init.util.HibernateUtils;
import showEDU._00_init.util.SystemUtils2018;
import showEDU.com.web.ticket.model.MovieBean;
import showEDU.com.web.ticket.model.MovieLevelBean;
import showEDU.com.web.ticket.model.MovieShowtimeBean;
import showEDU.com.web.ticket.model.MovieStatusBean;
import showEDU.com.web.ticket.model.SeatsBean;
import showEDU.com.web.ticket.model.TheaterBean;

public class TicketTable {
	public static final String UTF8_BOM = "\uFEFF"; // 定義 UTF-8的BOM字元

	public static void main(String args[]) {

		String line = "";

		int count = 0;
		SessionFactory factory = HibernateUtils.getSessionFactory();
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();

			// 1. 由"data/ticket/moviestatus.dat"逐筆讀入moviestatus表格內的初始資料，
			// 然後依序新增到moviestatus表格中
			try (FileReader fr = new FileReader("data/ticket/moviestatus.dat");
					BufferedReader br = new BufferedReader(fr);) {
				while ((line = br.readLine()) != null) {
					if (line.startsWith(UTF8_BOM)) {
						line = line.substring(1);
					}
					String[] token = line.split("\\|");
					MovieStatusBean msb = new MovieStatusBean();
					msb.setStatus(token[0]);
					session.save(msb);
				}
			} catch (IOException e) {
				System.err.println("新建MovieStatusBean表格時發生IO例外: " + e.getMessage());
			}
			session.flush();
			System.out.println("MovieStatus 資料新增成功");

			// 2. 由"data/ticket/movielevel.dat"逐筆讀入movielevel表格內的初始資料，然後依序新增
			// 到movielevel表格中
			File file = new File("data/ticket/movielevel.dat");
			try (FileInputStream fis = new FileInputStream(file);
					InputStreamReader isr = new InputStreamReader(fis, "UTF8");
					BufferedReader br = new BufferedReader(isr);) {
				while ((line = br.readLine()) != null) {
					System.out.println("line=" + line);
					// 去除 UTF8_BOM: \uFEFF
					if (line.startsWith(UTF8_BOM)) {
						line = line.substring(1);
					}
					String[] token = line.split("\\|");
					MovieLevelBean movielevel = new MovieLevelBean();
					movielevel.setLevel(token[0]);
					// --------------處理Blob(圖片)欄位----------------
					Blob sb = SystemUtils2018.fileToBlob(token[1]);
					movielevel.setLevelPhoto(sb);
					String imageFileName = token[1].substring(token[1].lastIndexOf("\\") + 1);
					movielevel.setPhotoName(imageFileName);
					session.save(movielevel);
					System.out.println("新增一筆movielevel紀錄成功");
				}
				// 印出資料新增成功的訊息
				session.flush();
				System.out.println("movielevel資料新增成功");
			}

			// 3. 由"data/ticket/theater.dat"逐筆讀入theater表格內的初始資料，然後依序新增
			// 到theater表格中
			try (FileInputStream fis = new FileInputStream("data/ticket/theater.dat");
					InputStreamReader isr = new InputStreamReader(fis, "UTF8");
					BufferedReader br = new BufferedReader(isr);) {
				while ((line = br.readLine()) != null) {
					System.out.println("line=" + line);
					// 去除 UTF8_BOM: \uFEFF
					if (line.startsWith(UTF8_BOM)) {
						line = line.substring(1);
					}
					String[] token = line.split("\\|");
					TheaterBean theater = new TheaterBean();
					theater.setName(token[0]);
					theater.setTotalSeats(Integer.parseInt(token[1].trim())); // 將字串強轉為整數
					session.save(theater);
					System.out.println("新增一筆theater紀錄成功");
				}
				// 印出資料新增成功的訊息
				session.flush();
				System.out.println("theater資料新增成功");
			}

			// 4. 由"data/ticket/movie.dat"逐筆讀入movie表格內的初始資料，然後依序新增
			// 到movie表格中
			try (FileInputStream fis = new FileInputStream("data/ticket/movie.dat");
					InputStreamReader isr = new InputStreamReader(fis, "UTF8");
					BufferedReader br = new BufferedReader(isr);) {
				while ((line = br.readLine()) != null) {
					System.out.println("line=" + line);
					// 去除 UTF8_BOM: \uFEFF
					if (line.startsWith(UTF8_BOM)) {
						line = line.substring(1);
					}
					String[] token = line.split("\\|");
					MovieBean movie = new MovieBean();
					movie.setChName(token[0]);
					movie.setEnName(token[1]);
					// --------------處理Blob(圖片)欄位----------------
					Blob sb = SystemUtils2018.fileToBlob(token[2]);
					movie.setImage(sb);
					String imageFileName = token[2].substring(token[2].lastIndexOf("\\") + 1);
					movie.setImageName(imageFileName);
					// --------------處理ManytoOne欄位----------------
					Integer movieLevelId = Integer.parseInt(token[4].trim());
					MovieLevelBean mlb = session.get(MovieLevelBean.class, movieLevelId);
					movie.setMovieLevelBean(mlb);
//					movie.setMovieLevelId(movieLevelId);
					movie.setLength(Integer.parseInt(token[5].trim()));
					movie.setDirector(token[6]);
					movie.setActors(token[7]);
					movie.setTrailer(token[8]);
					movie.setSynopsis(token[9]);
					System.out.println(token[10]);
					// --------------處理Date欄位----------------
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

					java.util.Date d = null;
					try {
						d = sdf.parse(token[10]);
					} catch (Exception e) {
						e.printStackTrace();
					}
					java.sql.Date premierDate = new java.sql.Date(d.getTime());
					movie.setPremierDate(premierDate);
					SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy/MM/dd");
					java.util.Date d1 = null;
					try {
						d1 = sdf1.parse(token[11]);
					} catch (Exception e) {
						e.printStackTrace();
					}
					java.sql.Date offDate = new java.sql.Date(d1.getTime());
					movie.setOffDate(offDate);

					// --------------處理ManytoOne欄位----------------
					Integer movieStatusId = Integer.parseInt(token[12].trim());
					MovieStatusBean msb = session.get(MovieStatusBean.class, movieStatusId);
					movie.setMovieStatusBean(msb);
//					movie.setMovieStatusId(movieStatusId);
					session.save(movie);
					System.out.println("新增一筆movie紀錄成功");
				}
				// 印出資料新增成功的訊息
				session.flush();
				System.out.println("movie資料新增成功");
			}

			// 5. 由"data/ticket/seats.dat"逐筆讀入seats表格內的初始資料，然後依序新增
			// 到seats表格中
			try (FileInputStream fis = new FileInputStream("data/ticket/seats.dat");
					InputStreamReader isr = new InputStreamReader(fis, "UTF8");
					BufferedReader br = new BufferedReader(isr);) {
				while ((line = br.readLine()) != null) {
					System.out.println("line=" + line);
					// 去除 UTF8_BOM: \uFEFF
					if (line.startsWith(UTF8_BOM)) {
						line = line.substring(1);
					}
					String[] token = line.split("\\|");
					SeatsBean sb = new SeatsBean();
					// --------------處理ManytoOne欄位----------------
					Integer theaterId = Integer.parseInt(token[0].trim());
					TheaterBean msb = session.get(TheaterBean.class, theaterId);
					sb.setTheaterBean(msb);
					sb.setLineLetters(token[1]);
					sb.setRowNumber(Integer.parseInt(token[2].trim()));

					session.save(sb);
					System.out.println("新增一筆movie紀錄成功");
				}
				// 印出資料新增成功的訊息
				session.flush();
				System.out.println("movie資料新增成功");
			}

			// 6. 由"data/ticket/movieshowtime.dat"逐筆讀入movieshowtime表格內的初始資料，
			// 然後依序新增到movieshowtime表格中
			try (FileReader fr = new FileReader("data/ticket/movieshowtime.dat");
					BufferedReader br = new BufferedReader(fr);) {
				while ((line = br.readLine()) != null) {
					if (line.startsWith(UTF8_BOM)) {
						line = line.substring(1);
					}
					String[] token = line.split("\\|");
					MovieShowtimeBean mstb = new MovieShowtimeBean();
					// --------------處理ManytoOne欄位----------------
					Integer movieId = Integer.parseInt(token[0].trim());
					MovieBean mb = session.get(MovieBean.class, movieId);
					mstb.setMovieBean(mb);
					Integer theaterId = Integer.parseInt(token[1].trim());
					TheaterBean tb = session.get(TheaterBean.class, theaterId);
					mstb.setTheaterBean(tb);
					mstb.setTheaterBean(tb);
					// --------------處理Date欄位----------------
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

					java.util.Date d = null;
					try {
						d = sdf.parse(token[2]);
					} catch (Exception e) {
						e.printStackTrace();
					}
					java.sql.Date date = new java.sql.Date(d.getTime());
					mstb.setDate(date);

					mstb.setTime(token[3]);
					mstb.setSeatsStatus(token[4]);
					// --------------處理Integer欄位----------------
					mstb.setRemainingSeats(Integer.parseInt(token[5].trim()));
					session.save(mstb);
				}
			} catch (IOException e) {
				System.err.println("新建MovieStatusBean表格時發生IO例外: " + e.getMessage());
			}
			session.flush();
			System.out.println("MovieStatus 資料新增成功");

			// =======================送出commit 關閉sesstion factory=================
			tx.commit();
		} catch (Exception e) {
			System.err.println("新建表格時發生例外: " + e.getMessage());
			e.printStackTrace();
			tx.rollback();
		}
		factory.close();
	} // main 結束

	private static Date sdf(String string) {
		// TODO Auto-generated method stub
		return null;
	}

}