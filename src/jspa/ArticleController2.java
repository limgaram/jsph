package jspa;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.article.Article;
import board.article.ArticleDao;
import board.article.Reply;

public class ArticleController2 {
	ArticleDao dao = new ArticleDao();

	String doAction(HttpServletRequest request, HttpServletResponse response) {

		String action = request.getParameter("action");
		String dest = "";

		if (action.equals("list")) {
			dest = list(request, response);

		} else if (action.equals("insert")) {

			dest = insert(request, response);

		} else if (action.equals("update")) {

			dest = update(request, response);

		} else if (action.equals("delete")) {

			dest = delete(request, response);

		} else if (action.equals("detail")) {

			dest = detail(request, response);

		} else if (action.equals("showAdd")) {

			dest = "WEB-INF/jsp/addForm.jsp";

		} else if (action.equals("showUpdate")) {

			dest = showUpdate(request, response);

		}
//		reply
		else if (action.equals("replyInsert")) {

			dest = replyInsert(request, response);

		} else if (action.equals("replyUpdate")) {

			dest = replyUpdate(request, response);

		} else if (action.equals("replyDelete")) {

		}
		return dest;
	}

	private String showUpdate(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));
		Article article = dao.getArticleById(id);
		request.setAttribute("updateData", article);

		return "WEB-INF/jsp/updateForm.jsp";

	}

	private String detail(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));
		Article article = dao.getArticleById(id);
		ArrayList<Reply> replies = dao.getRepliesByArticleId(id);
		
		
		request.setAttribute("detailData", article);
		request.setAttribute("replyData", replies);
		
		return "WEB-INF/jsp/detail.jsp";
	}

	private String delete(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));
		dao.deleteArticle(id);

		return list(request, response);
	}

	private String update(HttpServletRequest request, HttpServletResponse response) {
		String title = request.getParameter("title");
		String body = request.getParameter("body");
		int id = Integer.parseInt(request.getParameter("id"));

		dao.updateArticle(title, body, id);

		return detail(request, response);
	}

	private String insert(HttpServletRequest request, HttpServletResponse response) {
		String title = request.getParameter("title");
		String body = request.getParameter("body");
		int mid = Integer.parseInt(request.getParameter("mid"));

		dao.insertArticle(title, body, mid);

		return list(request, response);
	}

	public String list(HttpServletRequest request, HttpServletResponse response) {
		ArrayList<Article> articles = dao.getArticles();
		request.setAttribute("myData", articles);

		return "WEB-INF/jsp/list.jsp";
	}

//	reply

	private String replyUpdate(HttpServletRequest request, HttpServletResponse response) {

		return null;
	}

	private String replyInsert(HttpServletRequest request, HttpServletResponse response) {
		int aid = Integer.parseInt(request.getParameter("aid"));
		String body = request.getParameter("body");
		dao.insertReply(aid, body);

		return "WEB-INF/jsp/detail.jsp";
	}
}
