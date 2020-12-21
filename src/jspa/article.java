package jspa;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.article.Article;
import board.article.ArticleDao;

@WebServlet("/article")
public class article extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");

		ArticleDao articleDao = new ArticleDao();
		ArrayList<Article> articles = articleDao.getArticles();

		String action = request.getParameter("action");

		request.setAttribute("data", articles);

		String dest = "";

		if (action.equals("list")) {
			dest = "./article.jsp";
		} else if (action.equals("insert")) {
			String title = request.getParameter("title");
			String body = request.getParameter("body");
			String mid = request.getParameter("mid");
			int midM = Integer.parseInt(mid);

			articleDao.insertArticle(title, body, midM);
			dest = "./article.jsp";
		} else if (action.equals("delete")) {
			String aid = request.getParameter("aid");
			int aidA = Integer.parseInt(aid);

			articleDao.deleteArticle(aidA);
			dest = "./article.jsp";
		} else if (action.equals("update")) {
			String aid = request.getParameter("aid");
			int idI = Integer.parseInt(aid);
			String title = request.getParameter("title");
			String body = request.getParameter("body");
			
			articleDao.updateArticle(title, body, idI);
			dest = "./article.jsp";
		}

		RequestDispatcher rd = request.getRequestDispatcher(dest);
		rd.forward(request, response);
	}

}
