package jspa;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReplyController {
	ReplyDao RDao = new ReplyDao();

	String doAction3(HttpServletRequest request, HttpServletResponse response) {
		String action = request.getParameter("action");
		String dest = "";

		if (action.equals("replyInsert")) {
			dest = replyInsert(request, response);
		} else if (action.equals("replyUpdate")) {
			dest = replyUpdate(request, response);

		} else if (action.equals("replyDelete")) {

		}
	}

	private String replyUpdate(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	private String replyInsert(HttpServletRequest request, HttpServletResponse response) {

		return replyInsert(request, response);
	}

}
