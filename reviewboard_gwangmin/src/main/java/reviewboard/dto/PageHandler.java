package reviewboard.dto;

public class PageHandler {
	
	// 1. 일반 변수
	private int totalCount; // 총 게시글 수
	private int pageNum; // 현재 페이지 번호
	private int pageSize; // 현재 페이지의 게시글 개수
	private int pageBlock; // 페이지 블럭 (1~3) 
	
	// 2. DB 조회 변수
	private int startRow; // DB 시작 위치
	private int endRow; // 가져올 개수 -> endRow(확장성을 위해 기입)

	// db시작 페이지
	private int totalPage; // 총 페이지 수
	private int startPage; // 한 블럭의 시작 번호
	private int endPage; // 한 블럭의 마지막 번호
	
	private boolean prev; // 이전 버튼
	private boolean next; // 다음 버튼
	
	public PageHandler(int totalCount, int pageNum, int pageSize) {
		this.totalCount = totalCount;
		this.pageNum = pageNum;
		this.pageSize = pageSize;
		
		calcPaging();
	}
	
	public void calcPaging() {
		// 전체 페이지 수 계산
		// 전체 게시글 수를 현재 페이지의 게시글 개수를 나누었을때 몫이 페이지 수
		// 근데 여기서 나머지는 다음페이지의 게시글이 되어야하는데 INT형이기 때문에 버려짐
		// 그래서 double로 명시적 형변환을해야 소수점까지 계산 할 수 있음.
		// 근데 소수점을 반올림 혹은 버림을 하면 게시글이 안나옴
		// 그래서 올림을 해서 페이지를 늘려주고 totalPage는 int형이므로 다시 int형으로 명시적 형변환을 함
		totalPage = (int)Math.ceil(totalCount / (double)pageSize);
		
		// DB에서 LIMIT를 통해 가져올 데이터의 시작 위치를 구해야 한다.
		// 5개씩 게시글을 나눠야 함 -> 1페이지 : 0,1,2,3,4
							//	2페이지 : 5,6,7,8,9
							//  3페이지 : 10,11,12,13,14
		// 결국 5의 배수가 되어야하는데 페이지가 늘어날때마다 시작 위치가 늘어나야하니까
		// pageNum 에 5를 곱해야함. 근데 5를 곱하니까 5, 10, 15로 시작 위치가 하나 더 앞임
		// 그래서 -1을 해줘서 0부터 시작하도록 함.
		startRow = (pageNum - 1) * pageSize;
		endRow = pageSize;
		
		startPage = ((pageNum - 1) / pageBlock) * pageBlock + 1;
		// endPage는 startPage에서 2를 더하면 되지만 그건 블럭이 3일때 이야기임
		// 그러니까 현재 몇일지 모르는 pageBlock을 startPage의 페이지 1개를 제외한 값을 startPage에 더해야 블럭의 마지막 페이지가 나옴
		// 즉 block 을 5라고 생각하면 결국 1,2,3,4,5페이지가 되어야함. 근데 startPage는 1이니까 (1 + (pageBlcok(5)-1)) 을 해야 5가 나온다는 소리임.
		endPage = startPage + (pageBlock - 1);
		
		// 근데 만약에 게시글이 8개여서 2페이지만 필요하다면?
		// endPage는 startPage에서 pageBlock - 1이니까 3페이지가 다 만들어지는데
		// 이렇게 되면 값이 비어있는 페이지가 생성됨.
		// 그래서 if문을 통해 만약에 endPage보다 totalPage가 더 작으면 endPage에 totalPage를 강제로 넣어라 라는 코드가 필요함
		if(endPage > totalPage) {
			endPage = totalPage;
		}
		
		// 이전 버튼은 왼쪽으로 갈 수 있는 상황에서만 활성화 됨
		// 갈 수 있다? -> 현재 블럭의 시작번호가 1보다 크다.
		// 근데 startPage는 1 다음에 4임 그러니까 1보다 크다는 뜻은 다음버튼을 눌러서 이미 블럭을 하나 옮겼다는 뜻
		prev = startPage > 1;
		
		// 다음 버튼은 오른쪽으로 갈수 없으면 없어져야함
		// endPage가 totalPage보다 작다는 뜻은 다음 페이지가 있다는 뜻임.
		// 만약에 endPage가 2페이지면 결국 위의 if문으로 totalPage도 같아져서 아래 코드가 false가 되어 버튼이 사라짐
		// endPage가 3이 되었는데 totalPage가 4라면 아래 코드는 true로 버튼은 아직 활성화 됨.
		// endPage가 3이 되었는데 totalPage도 3이라면 아래 코드는 false로 버튼이 사라짐.
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
