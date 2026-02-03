package com.green.board;

public class PageHandler {
	// 1. 기본 변수
	private int totalCount; // 전체 게시글 수
	private int pageNum; // 현재 페이지 번호
	private int pageSize; // 한 페이지에 보여줄 게시글 수
	private int pageBlock = 3; // 페이지 번호 블럭
	
	// 2. DB 조회용 값
	private int startRow; // DB에서 시작 위치
	private int endRow; // 가져올 게시글 개수
	
	// 3. 페이지 네비게이션용 값
	private int totalPage; // 전체 페이지 수
	private int startPage; // 현재 화면 페이지 시작 번호
	private int endPage; // 현재 화면 페이지 끝 번호
	
	private boolean prev; // 이전 버튼
	private boolean next; // 다음 버튼
	
	public PageHandler(int totalCount, int pageNum, int pageSize) {
		this.totalCount = totalCount;
		this.pageNum = pageNum;
		this.pageSize = pageSize;
		
		calcPaging();
	}
	
	public void calcPaging() {
		// 1. 전체 페이지 수 계산 메서드
		// 전체 페이지 = 전체 게시글 수 / 페이지당 게시글 수 => 22개 / 5개 => 4페이지와 2개의 게시글
		// 올림 Math.ceil() 을 하지 않으면 2개의 게시글을 넣을 방법이 없음
		// 결국 올림을 해야하는데 int의 데이터 타입으로는 올림을 할 수가 없음 정수형이기 때문에
		// double로 명시적 형변환을 해준 후 올림을 하고 int형으로 형변환을 해주지 않으면 5.0 이런 형식으로 나오기때문에 int 형으로 형변환을 해줌
		totalPage = (int)Math.ceil(totalCount / (double)pageSize);
		
		// 2. DB 조회 범위
		// startRow => 
		startRow = (pageNum - 1) * pageSize;
		endRow = pageSize;
		
		// 3. 페이지 네비게이션 시작 / 끝
		// startPage는 현재 화면 페이지의 시작번호 [1][2][3] 이면 [1]을 의미
		// pageNum - 1 을 한 후 pageBlock으로 나누면 pageNum - 1이 pageBlock과 같아져야 1이 됨
		// 그 전까지는 계속 0.xx => int 형이기때문에 0 * pageBlock은 0 => +1 은 1 결국 [1], [2], [3] 전부의 페이지 시작번호는 1
		// 4페이지라고 했을때는 (4-1) / 3 => 1 이고 1 * 3 은 3이고 + 1은 4가 되서 [4][5][6]페이지의 시작 번호는 4
		startPage = ((pageNum - 1) / pageBlock) * pageBlock + 1;
		// 현 화면의 마지막 페이지 번호는 시작페이지의 + 2 => 블럭이 3이기때문에
		// 블럭이 3일때는 명시적으로 +2를 넣어둬도 상관없지만 블럭이 늘어나거나 줄어들면 문제가 있음.
		// endPage = 시작 페이지 + (현재 블럭 - 1) 을 하면 블럭이 늘어나도 상관없음
		endPage = startPage + (pageBlock - 1);
		
		// 현재 상황에서는 endPage는 무조건 2가 더해지기때문에 총 페이지가 8이 나오더라도 9까지 만들어지는 상황이 발생
		// 결국 전체페이지보다 endPage가 커지는 상황에서는 명시적으로 endPage에 전체페이지 숫자를 대입
		if(endPage > totalPage) {
			endPage = totalPage;
		}
		
		// 4. 이전 / 다음 버튼
		// startPage가 1보다 크면 즉 4페이지, 7페이지 등으로 다음 버튼을 눌러서 가면 이전 버튼 생성
		// 차후 HTML에서 th:if를 사용해서 만들면 false면 th:if가 있는 태그 전체가 사라져서 상관없음
		prev = startPage > 1; 
		// 다음 버튼은 예를들어 1,2,3 페이지 이후에 4페이지에도 게시글이 있으면 다음 버튼이 활성화되어야함
		// endPage는 현재 화면에서의 마지막 번호이니 마지막 번호보다 총 페이지가 더 크면 게시글이 더 남았다는 것
		// 만약 endPage와 totalPage가 같을때도 false이므로 버튼은 없어짐
		next = endPage < totalPage;
	}
	
	
	
	
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int curPageNum) {
		this.pageNum = curPageNum;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageBlock() {
		return pageBlock;
	}
	public void setPageBlock(int pageBlock) {
		this.pageBlock = pageBlock;
	}
	public int getStartRow() {
		return startRow;
	}
	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}
	public int getEndRow() {
		return endRow;
	}
	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public boolean isPrev() {
		return prev;
	}
	public void setPrev(boolean prev) {
		this.prev = prev;
	}
	public boolean isNext() {
		return next;
	}
	public void setNext(boolean next) {
		this.next = next;
	}
	
	
}
