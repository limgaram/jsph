package board;

public class Pagination {
	private int currentPageNo; // 현재 페이지 번호
	private int totalCountOfItems; // 모든 아이템의 수
	private int itemCountPerPage; // 한 페이지당 출력될 게시물의 수
	private int pageCountPerPageBlock; // 한 블럭당 출력될 페이지의 수
	
	public Pagination() {
		
	}
	
	public Pagination(int totalCountOfItems) {
		this.currentPageNo = 1;
		this.totalCountOfItems = totalCountOfItems;
		this.itemCountPerPage = 3;
		this.pageCountPerPageBlock = 5;
	}
	
	public int getCurrentPageNo() {
		return currentPageNo;
	}
	public void setCurrentPageNo(int currentPageNo) {
		this.currentPageNo = currentPageNo;
	}
	public int getTotalCountOfItems() {
		return totalCountOfItems;
	}
	public void setTotalCountOfItems(int totalCountOfItems) {
		this.totalCountOfItems = totalCountOfItems;
	}
	public int getItemCountPerPage() {
		return itemCountPerPage;
	}
	public void setItemCountPerPage(int itemCountPerPage) {
		this.itemCountPerPage = itemCountPerPage;
	}
	public int getPageCountPerPageBlock() {
		return pageCountPerPageBlock;
	}
	public void setPageCountPerPageBlock(int pageCountPerPageBlock) {
		this.pageCountPerPageBlock = pageCountPerPageBlock;
	}
	
	// 현재 페이지 번호를 이용해 페이지 블럭 계산 
	public int getCurrentPageBlock() {
		return (int)Math.ceil((double)currentPageNo / pageCountPerPageBlock);
	}
	
	// 현재 페이지 블럭의 시작 번호를 계산
	public int getStartPageNoInCurrentBlock() {
		return pageCountPerPageBlock * (getCurrentPageBlock() - 1) + 1;  
	}
	
	// 현재 페이지 블럭의 끝 번호를 계산
	public int getEndPageNoInCurrentBlock() {
		int endPageNo = getStartPageNoInCurrentBlock() + pageCountPerPageBlock - 1;
		int lastPageNo = getLastPageNo();
		
		if(endPageNo > lastPageNo) {
			endPageNo = lastPageNo;
		}
		
		return endPageNo;
	}
	
	// 마지막 페이지 번호 계산
	public int getLastPageNo() {
		return (int)Math.ceil((double)totalCountOfItems / itemCountPerPage);
	}
	
}
