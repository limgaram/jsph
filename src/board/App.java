package board;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import board.article.Article;
import board.article.ArticleDao;
import board.article.Like;
import board.article.Reply;
import board.member.Member;
import board.member.MemberDao;

public class App {

	private ArticleDao articleDao = new ArticleDao();
	private MemberDao memberDao = new MemberDao();
	private Scanner sc = new Scanner(System.in);
	private Member loginedMember = null;
	private String cmd = "";
	private List<Article> currentArticles = null; 
	private Pagination currentPagination = null;
	
	public void start() {
		currentArticles = articleDao.getArticles();
		currentPagination = new Pagination(currentArticles.size());
		currentArticles = articleDao.getArticlesForPaging(currentPagination);

		while (true) {

			inputCommand();

			if (cmd.equals("list")) {
				list();
			} else if (cmd.equals("update")) {
				updateArticle();
			} else if (cmd.equals("delete")) {
				deleteArticle();
			} else if (cmd.equals("add")) {
				if (isLogin()) {
					addArticle();
				}
			} else if (cmd.equals("read")) {
				readArticle();
			} else if (cmd.equals("signup")) {
				signup();
			} else if (cmd.equals("signin")) {
				login();
			} else if (cmd.equals("search")) {
				search();
			} else if (cmd.equals("sort")) {
				sort();
			} else if (cmd.equals("page")) {
				page();
			} else {
				notACommand();
			}
		}
	}

	private void page() {
		while (true) {
			int currentPageNo = currentPagination.getCurrentPageNo();
			int lastPageNo = currentPagination.getLastPageNo();
			System.out.println("페이징 명령어를 입력해주세요 (prev : 이전, next : 다음, go : 선택, back : 뒤로가기) :");
			String pageCmd = sc.nextLine();
			if (pageCmd.equals("prev")) {
				if(currentPageNo < 1) {
					System.out.println("시작페이지입니다.");
					continue;
				}
				currentPagination.setCurrentPageNo(currentPageNo - 1);
			} else if (pageCmd.equals("next")) {
				if(currentPageNo > lastPageNo) {
					System.out.println("마지막페이지입니다.");
					continue;
				}
				currentPagination.setCurrentPageNo(currentPageNo + 1);
			} else if (pageCmd.equals("go")) {
				System.out.print("이동하실 페이지 번호를 입력해주세요 :");
				int selectedPageNo = Integer.parseInt(sc.nextLine()); 
				if(selectedPageNo < 1 || selectedPageNo > lastPageNo) {
					System.out.println("잘못된 페이지입니다.");
					continue;
				}
				currentPagination.setCurrentPageNo(selectedPageNo);
			} else {
				break;
			}
			currentArticles = articleDao.getArticlesForPaging(currentPagination);
			printArticles(currentArticles, currentPagination);
		}
	}

	public void sort() {
		String sortFlag = sc.nextLine();
		String sortType = sc.nextLine();

		currentArticles = articleDao.getSortedArticles(sortFlag, sortType);
		currentPagination = new Pagination(currentArticles.size());
		printArticles(currentArticles, currentPagination);
	}

	public void search() {

		int searchFlag = Integer.parseInt(sc.nextLine());
		String searchKeyword = sc.nextLine();

		currentArticles = articleDao.getSearchedArticles(searchFlag, searchKeyword);
		currentPagination = new Pagination(currentArticles.size());
		printArticles(currentArticles, currentPagination);

	}

	public void login() {
		System.out.print("아이디 : ");
		String id = sc.nextLine();
		System.out.print("비밀번호 : ");
		String pw = sc.nextLine();

		Member target = memberDao.getMemberByLoginIdAndLoginPw(id, pw);

		if (target == null) {
			System.out.println("잘못된 회원정보 입니다.");
		} else {
			System.out.println(target.getNickname() + "님! 반갑습니다!!");
			loginedMember = target;
		}

	}

	public boolean isLogin() {
		if (loginedMember == null) {
			System.out.println("로그인이 필요한 기능입니다.");
			return false;
		}

		return true;
	}

	public void signup() {
		System.out.print("아이디 : ");
		String id = sc.nextLine();
		System.out.print("비밀번호 : ");
		String pw = sc.nextLine();
		System.out.print("닉네임 : ");
		String nm = sc.nextLine();

		memberDao.insertMember(id, pw, nm);
	}

	public void readArticle() {
		System.out.print("상세보기할 게시물 번호 : ");
		int aid = Integer.parseInt(sc.nextLine());

		Article article = articleDao.getArticleById(aid);

		if (article == null) {
			System.out.println("없는 게시물입니다.");
		} else {
			articleDao.increaseHitByAritlceId(article.getId());
			article = articleDao.getArticleById(aid);

			ArrayList<Reply> replies = articleDao.getRepliesByArticleId(article.getId());
			printArticle(article, replies);

			while (true) {
				System.out.print("상세보기 기능을 선택해주세요(1. 댓글 등록, 2. 좋아요, 3. 수정, 4. 삭제, 5. 목록으로) : ");
				int dcmd = Integer.parseInt(sc.nextLine());
				if (dcmd == 1) {
					System.out.print("내용을 입력해주세요 :");
					String body = sc.nextLine();
					articleDao.insertReply(article.getId(), body);
					ArrayList<Reply> replies2 = articleDao.getRepliesByArticleId(article.getId());
					printArticle(article, replies2);
				} else if (dcmd == 2) {

					if (isLogin()) {
						Like like = articleDao.getLike(article.getId(), loginedMember.getId());

						if (like != null) {
							System.out.println("좋아요를 해제했습니다.");
							articleDao.deleteLike(article.getId(), loginedMember.getId());
						} else {
							System.out.println("이 게시물을 좋아합니다.");
							articleDao.insertLike(article.getId(), loginedMember.getId());
						}

						Article article2 = articleDao.getArticleById(article.getId());
						System.out.println("aaa : " + article2.getLikeCnt());
						ArrayList<Reply> replies2 = articleDao.getRepliesByArticleId(article.getId());

						printArticle(article2, replies2);

					}

				} else {
					break;
				}
			}
		}
	}

	public void addArticle() {
		System.out.print("제목 : ");
		String title = sc.nextLine();
		System.out.print("내용 : ");
		String body = sc.nextLine();

		articleDao.insertArticle(title, body, loginedMember.getId());
	}

	public void deleteArticle() {
		System.out.print("삭제할 게시물 번호 : ");
		int aid = Integer.parseInt(sc.nextLine());
		articleDao.deleteArticle(aid);
	}

	public void updateArticle() {
		System.out.print("수정할 게시물 번호 : ");
		int aid = Integer.parseInt(sc.nextLine());

		System.out.print("제목 : ");
		String title = sc.nextLine();
		System.out.print("내용 : ");
		String body = sc.nextLine();
		articleDao.updateArticle(title, body, aid);
	}

	public void list() {
		printArticles(currentArticles, currentPagination);
	}

	public void inputCommand() {
		if (loginedMember == null) {
			System.out.println("명령어를 입력해주세요.");
		} else {

			String loginedUserInfo = String.format("[%s(%s)]", loginedMember.getLoginId(), loginedMember.getNickname());
			System.out.println("명령어를 입력해주세요." + loginedUserInfo);
		}
		cmd = sc.nextLine();
	}

	public void notACommand() {
		System.out.println("올바른 명령어가 아닙니다.");
	}

	public void printArticles(List<Article> articles, Pagination pagination) {
		for (int i = 0; i < articles.size(); i++) {
			Article article = articles.get(i);

			System.out.println("번호 : " + article.getId());
			System.out.println("제목 : " + article.getTitle());
			System.out.println("작성자 : " + article.getNickname());
			System.out.println("등록날짜 : " + article.getRegDate());
			System.out.println("조회수 : " + article.getHit());
			System.out.println("좋아요 : " + article.getLikeCnt());
			System.out.println("=============================");
		}
		for (int i = pagination.getStartPageNoInCurrentBlock(); i <= pagination.getEndPageNoInCurrentBlock(); i++) {
			if (i == pagination.getCurrentPageNo()) {
				System.out.print("[" + i + "] ");
			} else {
				System.out.print(i + " ");
			}
		}
		System.out.println();
	}

	public void printArticle(Article article, ArrayList<Reply> replies) {
		System.out.println("번호 : " + article.getId());
		System.out.println("제목 : " + article.getTitle());
		System.out.println("내용 : " + article.getBody());
		System.out.println("작성자 : " + article.getNickname());
		System.out.println("등록날짜 : " + article.getRegDate());
		System.out.println("조회수 : " + article.getHit());
		System.out.println("좋아요 : " + article.getLikeCnt());
		System.out.println("==== 댓글 ====");
		for (int i = 0; i < replies.size(); i++) {
			System.out.println("내용 : " + replies.get(i).getBody());
			System.out.println("작성자 : " + replies.get(i).getWriter());
			System.out.println("=============================");
		}

	}
}
