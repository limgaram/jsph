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
import board.member.Member;
import board.member.MemberDao;

@WebServlet("/Controller")
public class Controller extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");

		ArticleDao dao = new ArticleDao();
		MemberDao Mdao = new MemberDao();
		ArrayList<Article> articles = dao.getArticles();

		String action = request.getParameter("action");

		String dest = "WEB-INF/jsp/list.jsp";

		if (action.equals("list")) {

			// 1. request 객체에 데이터 저장
			request.setAttribute("myData", articles);

			// 2. 위에서 저장한 request 객체를 이용해 새로운 jsp 요청 -> 목적지 jsp 필요
		} else if (action.equals("insert")) {

			String title = request.getParameter("title");
			String body = request.getParameter("body");
			int mid = Integer.parseInt(request.getParameter("mid"));

			dao.insertArticle(title, body, mid);

		} else if (action.equals("update")) {

			String title = request.getParameter("title");
			String body = request.getParameter("body");
			int id = Integer.parseInt(request.getParameter("id"));

			dao.updateArticle(title, body, id);

		} else if (action.equals("delete")) {

			int id = Integer.parseInt(request.getParameter("id"));
			dao.deleteArticle(id);

		} else if (action.equals("detail")) {
			int id = Integer.parseInt(request.getParameter("id"));
			Article article = dao.getArticleById(id);
			request.setAttribute("myData2", article);
			dest = "WEB-INF/jsp/detail.jsp";
		} else if (action.equals("showAdd")) {

			dest = "WEB-INF/jsp/addForm.jsp";

		} else if (action.equals("showUpdate")) {

			int id = Integer.parseInt(request.getParameter("id"));
			Article article = dao.getArticleById(id);
			request.setAttribute("myData3", article);

			dest = "WEB-INF/jsp/updateForm.jsp";

		} else if (action.equals("login")) {

			String id = request.getParameter("id");
			String pw = request.getParameter("pw");
			Mdao.getMemberByLoginIdAndLoginPw(id, pw);
			dest = "WEB-INF/jsp/login.jsp";

		} else if (action.equals("signup")) {
			String id = request.getParameter("id");
			String pw = request.getParameter("pw");
			String name = request.getParameter("name");
			Mdao.insertMember(id, pw, name);
			dest = "WEB-INF/jsp/login.jsp";
		}

		request.setAttribute("myData", dao.getArticles());

		// 3. 요청하기
		RequestDispatcher rd = request.getRequestDispatcher(dest);
		rd.forward(request, response);

	}
}
