package showEDU._00_init;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Blob;
import java.sql.Timestamp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import showEDU._00_init.util.GlobalService;
import showEDU._00_init.util.HibernateUtils;
import showEDU.com.web.forum.model.ArtTypeBean;
import showEDU.com.web.forum.model.ArticleBean;
import showEDU.com.web.forum.model.CommentBean;
import showEDU.com.web.forum.model.CommentSecBean;
import showEDU.com.web.forum.model.DiscussionBoardBean;
import showEDU.com.web.forum.model.ForumMovieBean;
import showEDU.com.web.forum.model.ThumbsUpBean;
import showEDU.com.web.member.model.MemberBean;

public class ForumTableResetHibernate {

	public static final String UTF8_BOM = "\uFEFF";

	public static void main(String[] args) {
		String line = "";
		int count = 0;
//		String path = "src\\main\\webapp\\WEB-INF\\applicationContext.xml";
//		ApplicationContext ctx = new FileSystemXmlApplicationContext(path);
		SessionFactory factory = HibernateUtils.getSessionFactory();
		Session session = factory.getCurrentSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();

			// 1.建立Movie表格
			try (FileInputStream fis = new FileInputStream("data/article/_for_movie.txt");
					InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
					BufferedReader br = new BufferedReader(isr);) {
				while ((line = br.readLine()) != null) {
					String[] sa = line.split("\\|");
					ForumMovieBean bean = new ForumMovieBean();

					bean.setName(sa[0]);
					Blob sb = GlobalService.fileToBlob(sa[1]);
					bean.setImage(sb);
					session.save(bean);
					bean.setFilename(sa[2]);
					count++;
					System.out.println("新增" + count + "筆記錄:" + sa[1]);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			// 2. 建立Board表格
			try (FileInputStream fis = new FileInputStream("data/article/_for_board.txt");
					InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
					BufferedReader br = new BufferedReader(isr);) {
				while ((line = br.readLine()) != null) {
					String[] sa = line.split("\\|");
					DiscussionBoardBean bean = new DiscussionBoardBean();
					int movieId = Integer.parseInt(sa[0]);
					ForumMovieBean mb = session.get(ForumMovieBean.class, movieId);
					bean.setMovieBean(mb);
					bean.setViewCount(sa[1]);
					session.save(bean);
					count++;
					System.out.println("新增" + count + "筆電影編號:" + sa[0]);
				}
				System.out.println("新增Board成功");
			} catch (IOException e) {
				System.out.println("新建Board表格時發生IO例外:" + e.getMessage());
			}

			// 3. 建立Member表格
			try (FileInputStream fis = new FileInputStream("data/article/_for_member.txt");
					InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
					BufferedReader br = new BufferedReader(isr);) {
				while ((line = br.readLine()) != null) {
					String[] sa = line.split("\\|");
					MemberBean bean = new MemberBean();
					bean.setName(sa[0]);
					bean.setPhone(sa[1]);
					bean.setAddress(sa[2]);
					bean.setAccount(sa[3]);
					bean.setPswd(sa[4]);
					bean.setFileName(sa[5]);
					Blob sb = GlobalService.fileToBlob(sa[6]);
					bean.setImage(sb);
					bean.setUserType(sa[7]);
					session.save(bean);
					count++;
					System.out.println("新增" + count + "筆會員資料:" + sa[0]);
				}
				System.out.println("新增Member成功");
			} catch (IOException e) {
				System.out.println("新建Member表格時發生IO例外:" + e.getMessage());
			}

			// 4. 建立artType表格
			try (FileInputStream fis = new FileInputStream("data/article/_for_artType.txt");
					InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
					BufferedReader br = new BufferedReader(isr);) {
				while ((line = br.readLine()) != null) {
					String[] sa = line.split("\\|");
					ArtTypeBean bean = new ArtTypeBean();
					bean.setType(sa[0]);
					Blob sb = GlobalService.fileToBlob(sa[1]);
					bean.setImage(sb);
					bean.setFilename(sa[2]);
					session.save(bean);
				}
				System.out.println("新增artType成功");
			} catch (IOException e) {
				System.out.println("新建artType表格時發生IO例外:" + e.getMessage());
			}

			// 5. 建立article表格
			try (FileInputStream fis = new FileInputStream("data/article/_for_article.txt");
					InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
					BufferedReader br = new BufferedReader(isr);) {
				while ((line = br.readLine()) != null) {
					String[] sa = line.split("\\|");
					ArticleBean bean = new ArticleBean();
					bean.setTitle(sa[0]);
					bean.setContent(sa[1]);
					bean.setViewCount(sa[2]);
					int artTypeId = Integer.parseInt(sa[3]);
					ArtTypeBean at = session.get(ArtTypeBean.class, artTypeId);
					bean.setArtTypeBean(at);
					int memberId = Integer.parseInt(sa[4]);
					MemberBean mb = session.get(MemberBean.class, memberId);
					bean.setMemberBean(mb);
					int boardId = Integer.parseInt(sa[5]);
					DiscussionBoardBean dcb = session.get(DiscussionBoardBean.class, boardId);
					bean.setDiscussionBoardBean(dcb);
					session.save(bean);
				}
				System.out.println("新增article成功");
			} catch (IOException e) {
				System.out.println("新建article表格時發生IO例外:" + e.getMessage());
			}

			// 6. 建立comment表格
			try (FileInputStream fis = new FileInputStream("data/article/_for_comment.txt");
					InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
					BufferedReader br = new BufferedReader(isr);) {
				while ((line = br.readLine()) != null) {
					String[] sa = line.split("\\|");
					CommentBean bean = new CommentBean();
					bean.setContent(sa[0]);
					String time = sa[1];
					Timestamp timestamp = Timestamp.valueOf(time);
					bean.setRegisterTime(timestamp);
					int artId = Integer.parseInt(sa[2]);
					ArticleBean art = session.get(ArticleBean.class, artId);
					bean.setArticleBean(art);
					int memberId = Integer.parseInt(sa[3]);
					MemberBean mb = session.get(MemberBean.class, memberId);
					bean.setMemberBean(mb);
					int boardId = Integer.parseInt(sa[4]);
					DiscussionBoardBean db = session.get(DiscussionBoardBean.class, boardId);
					bean.setBoardBean(db);
					session.save(bean);
				}
				System.out.println("新增comment成功");
			} catch (IOException e) {
				System.out.println("新建comment表格時發生IO例外:" + e.getMessage());
			}
			// 7. 建立commentSec表格
			try (FileInputStream fis = new FileInputStream("data/article/_for_commentSec.txt");
					InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
					BufferedReader br = new BufferedReader(isr);) {
				while ((line = br.readLine()) != null) {
					String[] sa = line.split("\\|");
					CommentSecBean bean = new CommentSecBean();
					bean.setContent(sa[0]);
					String time = sa[1];
					Timestamp timestamp = Timestamp.valueOf(time);
					bean.setRegisterTime(timestamp);
					int commentId = Integer.parseInt(sa[2]);
					CommentBean commentBean = session.get(CommentBean.class, commentId);
					bean.setCommentBean(commentBean);
					int memberId = Integer.parseInt(sa[3]);
					MemberBean mb = session.get(MemberBean.class, memberId);
					bean.setMemberBean(mb);
					session.save(bean);
				}
				System.out.println("新增commentSec成功");
			} catch (IOException e) {
				System.out.println("新建commentSec表格時發生IO例外:" + e.getMessage());
			}
			// 8. 建立ThumbsUp表格
			try (FileInputStream fis = new FileInputStream("data/article/_for_thumb.txt");
					InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
					BufferedReader br = new BufferedReader(isr);) {
				while ((line = br.readLine()) != null) {
					String[] sa = line.split("\\|");
					ThumbsUpBean bean = new ThumbsUpBean();
					int status = Integer.parseInt(sa[0]);
					bean.setStatus(status);
					if(sa[1].length() > 0) {
						int commentId = Integer.parseInt(sa[1]);
						CommentBean commentBean = session.get(CommentBean.class, commentId);
						bean.setCommentBean(commentBean);
					}
					if(sa[2].length() > 0) {
						int commentSecId = Integer.parseInt(sa[2]);
						CommentSecBean commentSecBean = session.get(CommentSecBean.class, commentSecId);
						bean.setCommentSecBean(commentSecBean);
					}
					int memberId = Integer.parseInt(sa[3]);
					MemberBean mb = session.get(MemberBean.class, memberId);
					bean.setMemberBean(mb);
					session.save(bean);
				}
				System.out.println("新增ThumbsUp表格成功");
			} catch (IOException e) {
				System.out.println("新建ThumbsUpBean表格時發生IO例外:" + e.getMessage());
			}
			tx.commit();

		} catch (Exception e) {
			System.err.println("新建表格錯誤");
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
		}
	}

}
