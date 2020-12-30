package jspa;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.article.Article;
import board.article.ArticleDao;
import board.member.Member;
import board.member.MemberDao;

@WebServlet("/article")
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

			request.setAttribute("myData", articles);

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
			request.setAttribute("detailData", article);
			
			
			dest = "WEB-INF/jsp/detail.jsp";

		} else if (action.equals("showAdd")) {
			/*
			 * int mid = Integer.parseInt(request.getParameter("mid")); 
			 * Member loginedMember = Mdao.getMemberById(mid);
			 */

			/*
			 * HttpSession session = request.getSession();
			 * Member loginedMember = (Member)session.getAttribute("loginedMember");
			 * request.setAttribute("loginedMember", loginedMember);
			 */

			dest = "WEB-INF/jsp/addForm.jsp";

		} else if (action.equals("showUpdate")) {

			int id = Integer.parseInt(request.getParameter("id"));
			Article article = dao.getArticleById(id);
			request.setAttribute("updateData", article);

			dest = "WEB-INF/jsp/updateForm.jsp";

		} else if (action.equals("showLogin")) {

			dest = "WEB-INF/jsp/loginForm.jsp";

		} else if (action.equals("doLogin")) {

			String loginId = request.getParameter("loginId");
			String loginPw = request.getParameter("loginPw");

			Member loginedMember = Mdao.getMemberByLoginIdAndLoginPw(loginId, loginPw);

			if (loginedMember != null) {
				// session 저장소에 저장하는 법.
				HttpSession session = request.getSession();
				session.setAttribute("loginedMember", loginedMember);
				// request.setAttribute("loginedMember", loginedMember);

				dest = "WEB-INF/jsp/list.jsp";

			} else {
				dest = "WEB-INF/jsp/loginFailed.jsp";
			}

		} else if (action.equals("showMember")) {
			dest = "WEB-INF/jsp/memberForm.jsp";

		} else if (action.equals("doInsertMember")) {

			String loginId = request.getParameter("loginId");
			String loginPw = request.getParameter("loginPw");
			String nickname = request.getParameter("nickname");

			Mdao.insertMember(loginId, loginPw, nickname);

			dest = "WEB-INF/jsp/loginForm.jsp";
		}

		request.setAttribute("myData", dao.getArticles());

		// 3. 요청하기
		RequestDispatcher rd = request.getRequestDispatcher(dest);
		rd.forward(request, response);

	}

}
