package jspa;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.member.Member;
import board.member.MemberDao;

public class MemberController2 {
	MemberDao Mdao = new MemberDao();

	public String doAction(HttpServletRequest request, HttpServletResponse response) {

		String action = request.getParameter("action");
		String dest = "";

		if (action.equals("showLogin")) {

			dest = "WEB-INF/jsp/loginForm.jsp";

		} else if (action.equals("doLogin")) {

			dest = doLogin(request, response);

		} else if (action.equals("showMember")) {

			dest = "WEB-INF/jsp/memberForm.jsp";

		} else if (action.equals("doInsertMember")) {

			dest = doInsertMember(request, response);

		} else if (action.equals("doLogout")) {

			dest = doLogout(request, response);

		}
		return dest;

	}

	private String doLogout(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
		session.invalidate();

		return "redirect: /board/article?action=list";
	}

	private String doLogin(HttpServletRequest request, HttpServletResponse response) {
		String loginId = request.getParameter("loginId");
		String loginPw = request.getParameter("loginPw");

		Member loginedMember = Mdao.getMemberByLoginIdAndLoginPw(loginId, loginPw);

		if (loginedMember != null) {
			System.out.println("111");
			// session 저장소에 저장하는 법.
			HttpSession session = request.getSession();
			session.setAttribute("loginedMember", loginedMember);

			// request.setAttribute("loginedMember", loginedMember);
			return "redirect: /board/article?action=list";

		} else {
			System.out.println("222");
			return "WEB-INF/jsp/loginFailed.jsp";
		}

	}

	private String doInsertMember(HttpServletRequest request, HttpServletResponse response) {
		String loginId = request.getParameter("loginId");
		String loginPw = request.getParameter("loginPw");
		String nickname = request.getParameter("nickname");

		Mdao.insertMember(loginId, loginPw, nickname);

		return "WEB-INF/jsp/loginForm.jsp";
	}
}
