/*
 * package jspa;
 * 
 * import java.io.IOException; import java.util.ArrayList;
 * 
 * import javax.servlet.RequestDispatcher; import
 * javax.servlet.ServletException; import javax.servlet.annotation.WebServlet;
 * import javax.servlet.http.HttpServlet; import
 * javax.servlet.http.HttpServletRequest; import
 * javax.servlet.http.HttpServletResponse; import
 * javax.servlet.http.HttpSession;
 * 
 * import board.article.Article; import board.article.ArticleDao; import
 * board.member.Member; import board.member.MemberDao;
 * 
 * @WebServlet("/article") public class ArticleController extends HttpServlet {
 * 
 * ArticleDao dao = new ArticleDao();
 * 
 * protected void doGet(HttpServletRequest request, HttpServletResponse
 * response) throws ServletException, IOException {
 * 
 * String action = request.getParameter("action"); String dest = "";
 * 
 * if (action.equals("list")) { dest = list(request, response);
 * 
 * } else if (action.equals("insert")) {
 * 
 * dest = insert(request, response);
 * 
 * } else if (action.equals("update")) {
 * 
 * dest = update(request, response);
 * 
 * } else if (action.equals("delete")) {
 * 
 * dest = delete(request, response);
 * 
 * } else if (action.equals("detail")) {
 * 
 * dest = detail(request, response);
 * 
 * } else if (action.equals("showAdd")) {
 * 
 * dest = "WEB-INF/jsp/addForm.jsp";
 * 
 * } else if (action.equals("showUpdate")) {
 * 
 * dest = showUpdate(request, response);
 * 
 * }
 * 
 * request.setAttribute("myData", dao.getArticles());
 * 
 * // forwarding RequestDispatcher rd = request.getRequestDispatcher(dest);
 * rd.forward(request, response);
 * 
 * // redirecting
 * 
 * }
 * 
 * private String showUpdate(HttpServletRequest request, HttpServletResponse
 * response) { String title = request.getParameter("title"); String body =
 * request.getParameter("body"); int id = Integer.parseInt("id");
 * 
 * return detail(request, response); }
 * 
 * private String detail(HttpServletRequest request, HttpServletResponse
 * response) { int id = Integer.parseInt(request.getParameter("id")); Article
 * article = dao.getArticleById(id); request.setAttribute("detailData",
 * article);
 * 
 * return "WEB-INF/jsp/detail.jsp"; }
 * 
 * private String delete(HttpServletRequest request, HttpServletResponse
 * response) { int id = Integer.parseInt(request.getParameter("id"));
 * dao.deleteArticle(id);
 * 
 * return list(request, response); }
 * 
 * private String update(HttpServletRequest request, HttpServletResponse
 * response) { String title = request.getParameter("title"); String body =
 * request.getParameter("body"); int id =
 * Integer.parseInt(request.getParameter("id"));
 * 
 * dao.updateArticle(title, body, id);
 * 
 * return "WEB-INF/jsp/detail.jsp"; }
 * 
 * private String insert(HttpServletRequest request, HttpServletResponse
 * response) { String title = request.getParameter("title"); String body =
 * request.getParameter("body"); int mid =
 * Integer.parseInt(request.getParameter("mid"));
 * 
 * dao.insertArticle(title, body, mid);
 * 
 * return list(request, response); }
 * 
 * public String list(HttpServletRequest request, HttpServletResponse response)
 * { ArrayList<Article> articles = dao.getArticles();
 * request.setAttribute("myData", articles);
 * 
 * return "WEB-INF/jsp/list.jsp"; }
 * 
 * }
 */