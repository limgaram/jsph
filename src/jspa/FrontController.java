package jspa;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/")
public class FrontController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");

		PrintWriter out = response.getWriter();

		System.out.println("공통 작업 코드");

		// 요청 url 가져오기
		String uri = request.getRequestURI();
		String[] uris = uri.split("/");

		String module = uris[2];
		String dest = "";

		if (module.equals("article")) {
			ArticleController2 controller = new ArticleController2();
			dest = controller.doAction(request, response);

		} else if (module.equals("member")) {
			MemberController2 controller = new MemberController2();
			dest = controller.doAction2(request, response);
		}

		System.out.println(uri);

		if (dest.startsWith("redirect: ")) {

			// redirecting
			String[] bits = dest.split("");
			String url = bits[1];
			response.sendRedirect(url);

		} else {

			// forwarding
			RequestDispatcher rd = request.getRequestDispatcher(dest);
			rd.forward(request, response);
		}
	}

}
