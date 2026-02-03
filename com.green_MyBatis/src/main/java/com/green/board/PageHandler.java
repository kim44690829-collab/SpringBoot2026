package com.green.board;

// 페이징을 하기 위한 계산식을 가지고 있는 클래스 
public class PageHandler {
	
	// 1. 기본 변수
	private int totalCnt; // 전체 게시글 개수
	private int pageNum; // 현재 페이지 번호
	private int pageSize; // 한 페이지에 보여줄 게시글 개수
	private int pageBlock = 3; // 한 화면에 페이지 묶음 (1~3페이지)
	
	// 2. DB 조회 변수
	// Limit 1(startRow), 5(pageSize) => 1부터 시작해서 5개만 출력
	private int startRow; // DB의 시작 위치
	private int endRow; // 가져올 게시글 개수 = pageSize
	
	// 3. pageBlock => [1], [2], [3] / [4], [5], [6]
	private int totalPage; // 전체 페이지 수
	private int startPage; // 한 블록의 시작번호 
	private int endPage; // 한 블록의 마지막 번호
	
	private boolean prev; // ◀ 이전 버튼
	private boolean next; // ▶ 다음 버튼
	
	public PageHandler(int totalCnt, int pageNum, int pageSize) {
		this.totalCnt = totalCnt;
		this.pageNum = pageNum;
		this.pageSize = pageSize;
		
		// 계산 함수 불러올 예정
		calcPaging();
	}
	
	// 페이지 계산 메서드
	public void calcPaging() {
		// 전체 페이지 수 계산 => totalPage
		// 게시글의 개수는 증가 혹은 감소 => 바뀔때 페이지가 늘어날수도 줄어들수도 있음
		// 11/5 => 2.x => int -> 2 소수점을 올림해서 페이지수를 하나 늘려줘야함
		// Math.ceil() => 소수점을 무조건 올림하여 정수를 출력하는 메서드
		totalPage = (int)Math.ceil(totalCnt / (double)pageSize);
		
		// DB에서 조회하는 범위 - 첫번째 
		// 1페이지 -> 0부터 5개
		// 2페이지 -> 5부터 5개
		// 3페이지 -> 10부터 5개
		
		// pageNum = 1 => 현재 보고있는 페이지 번호
		// 페이지별 시작 번호 / 0, 5, 10
		startRow = (pageNum - 1) * pageSize;
		endRow = pageSize;
		
		// pageBlock의 startPage, endPage
		// pageNum = 1, pageBlock = 3 => 
		// (1-1) / 3 = 0 * 3 = 0 + 1 = 1
		// (2-1) / 3 = 0 * 3 = 0 + 1 = 1
		// (3-1) / 3 = 0 * 3 = 0 + 1 = 1
		// (4-1) / 3 = 1 * 3 = 3 + 1 = 4
		
		startPage = ((pageNum - 1) / pageBlock) * pageBlock + 1;
		// 현재 페이지 블럭 = 3으로 넣어놨으니까 startPage + 2를 해도 상관없지만 페이지 블럭을 바꿨을때는 로직에 와서 바꿔줘야함
		// startPage + (pageBlock - 1) 이렇게 했을때 pageBlock - 1을 해줘야 pageBlock이 자동으로 바뀔수있음
		endPage = startPage + (pageBlock - 1);
		
		// 위의 계산식으로는 게시글이 몇개건 무조건 123, 456이 나오게됨 => 게시글이 5개여도 3페이지까지 나옴
		// 이런 경우 마지막 페이지를 강제로 endPage에 totalPage를 담아야함
		if(endPage > totalPage) {
			endPage = totalPage;
		}
		
		// 이전 버튼 , 다음 버튼 여부
		prev = startPage > 1;
     
		next = endPage < totalPage;
	}

	public int getTotalCnt() {
		return totalCnt;
	}

	public void setTotalCnt(int totalCnt) {
		this.totalCnt = totalCnt;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
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
