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

@WebServlet("/Controller")
public class Controller extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");

		ArticleDao articleDao = new ArticleDao();
		ArrayList<Article> articles = articleDao.getArticles();

		String action = request.getParameter("action");

		request.setAttribute("data", articles);

		String dest = "";
		if (action.equals("board")) {
			dest = "./board.jsp";
		} else {
			dest = "./board2.jsp";
		}

		RequestDispatcher rd = request.getRequestDispatcher(dest);
		rd.forward(request, response);
	}

}
