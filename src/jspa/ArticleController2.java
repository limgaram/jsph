package jspa;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.Pagination;
import board.article.Article;
import board.article.ArticleDao;
import board.article.Like;
import board.article.Reply;
import board.member.Member;

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

		} else if (action.equals("doSearch")) {
			dest = doSearch(request, response);
		}
//		reply
		else if (action.equals("doInsertReply")) {

			dest = insertReply(request, response);

		} else if (action.equals("showReplyUpdate")) {

			dest = showReplyUpdate(request, response);

		} else if (action.equals("doDeleteReply")) {

			dest = deleteReply(request, response);

		} else if (action.equals("doUpdateReply")) {

			dest = updateReply(request, response);

		} else if (action.equals("doLikeCheck")) {

			dest = doLikeCheck(request, response);

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
		String flag = request.getParameter("flag");

		Article article = dao.getArticleById(id);
		ArrayList<Reply> replies = dao.getRepliesByArticleId(id);

		if (flag != null) {
			int rid = Integer.parseInt(request.getParameter("rid"));

			request.setAttribute("flag", flag);
			request.setAttribute("rid", rid);
		}

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
		Pagination pagination = new Pagination(articles.size());
		int pageNo = Integer.parseInt(request.getParameter("pageNo"));
		pagination.setCurrentPageNo(pageNo);
		pagination.setCurrentPageBlockNo(pageNo);

		ArrayList<Article> articlesPerPage = dao.getArticlesForPaging(pagination);

		request.setAttribute("myData", articlesPerPage);
		request.setAttribute("pagination", pagination);

		return "WEB-INF/jsp/list.jsp";
	}

//	reply
	private String updateReply(HttpServletRequest request, HttpServletResponse response) {
		int aid = Integer.parseInt(request.getParameter("aid"));
		int rid = Integer.parseInt(request.getParameter("rid"));
		String body = request.getParameter("rbody");

		dao.updateReply(body, rid);

		return "redirect: /board/article?action=detail&id=" + aid;
	}

	private String deleteReply(HttpServletRequest request, HttpServletResponse response) {

		int id = Integer.parseInt(request.getParameter("id"));
		int aid = Integer.parseInt(request.getParameter("aid"));
		dao.deleteReplyById(id);

		return "redirect: /board/article?action=detail&id=" + aid;
	}

	private String showReplyUpdate(HttpServletRequest request, HttpServletResponse response) {

		int aid = Integer.parseInt(request.getParameter("aid"));
		int id = Integer.parseInt(request.getParameter("id"));

		return "redirect: /board/article?action=detail&id=" + aid + "&flag=u&rid=" + id;
	}

	private String insertReply(HttpServletRequest request, HttpServletResponse response) {

		int aid = Integer.parseInt(request.getParameter("aid"));
		int mid = Integer.parseInt(request.getParameter("mid"));
		String body = request.getParameter("rbody");

		dao.insertReply(aid, body, mid);

		return "redirect: /board/article?action=detail&id=" + aid;
	}

	private String doSearch(HttpServletRequest request, HttpServletResponse response) {

		String dateInterval = request.getParameter("dateInterval");
		String sTarget = request.getParameter("sTarget");
		String keyword = request.getParameter("keyword");

		ArrayList<Article> searchedArticles = dao.searchArticle(dateInterval, sTarget, keyword);
		request.setAttribute("myData", searchedArticles);

		return "WEB-INF/jsp/list.jsp";
	}

	private String doLikeCheck(HttpServletRequest request, HttpServletResponse response) {

		int aid = Integer.parseInt(request.getParameter("id"));
		HttpSession session = request.getSession();
		int mid = ((Member) session.getAttribute("loginedMember")).getId();

		Like like = dao.getLike(aid, mid);

		if (like == null) {
			dao.insertLike(aid, mid);
		} else {
			dao.deleteLike(aid, mid);
		}

		return "redirect: /board/article?action=detail&id=" + aid;
	}

}
