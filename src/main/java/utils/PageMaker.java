package utils;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class PageMaker {
	private Criteria criteria;				//���� ������ ����
	private int totalCount;				// ���̺� ����� �� �Խñ��� ��
	private int startPage;					// ȭ�鿡 ǥ���� ���� ������ ��ȣ
	private int endPage;					// ȭ�鿡 ǥ���� �� ������ ��ȣ
	private boolean prev;				// ���� ������ ��ư ����
	private boolean next;				// ���� ������ ��ư ����
	private int cntPageNum = 10;	// �� ȭ�鿡 ǥ���� ������ ��ư�� ��
	private int realEndPage;			// ���� ��
	
	public Criteria getCriteria() {
		return criteria;
	}
	public void setCriteria(Criteria criteria) {
		this.criteria = criteria;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		
		// ������ ��� ���� �ʱ�ȭ
		fieldInti();
	}
	
	private void fieldInti() {
		// ǥ���� �� ������ ��ȣ ���
		//endPage = (int)(Math.ceil(criteria.getPageNum() / (double)cntPageNum) * cntPageNum); 
		// �ٸ� ���
		endPage = (criteria.getPageNum() / cntPageNum +1) * cntPageNum;
		
		// (2) ���� ������ ��ȣ ���
		startPage =  endPage -  cntPageNum + 1;
		
		// ���� �� ������ ���
		// �ø�(�� �Խñ��� �� / �������� �Խñ��� ��)
		realEndPage = (int)(Math.ceil(totalCount / (double)criteria.getRowsPerPage())); 
		
		
		// (4)���� �� ������ ��ȣ �� ����
		if(endPage > realEndPage) {
			endPage= realEndPage;
		} 
		
		// (5) ����, ���� ������ ��ư ǥ�� ����
		//		���������� ��ȣ�� 1�� ��� '����' ��ư�� �ʿ� ����
		//		�� ������ ��ȣ * ������ �� �Խñ��� �� < �� �Խñ��� ��(totalCount) => ���� ��ư �ʿ�
		prev = (startPage ==1) ? false : true;
		next =  endPage * criteria.getRowsPerPage() < totalCount ? true : false ;
	}
	 // ������ ��ȣ�� Ŭ������ �� ������ Query String�� �������ִ� �޼ҵ�
	// �Է��Ķ���� : Ŭ���� ������ ��ȣ
	// ��: Ŭ���� ������ ��ȣ = 3
	// ?pageNum3&rowsPerPage=10
	public String makeQuery(int page) {
		UriComponents uri = UriComponentsBuilder.newInstance()
				.queryParam("pageNum", page) //pageNum=3
				.queryParam("rowsPerpage", criteria.getRowsPerPage()) //rowsPerPage =10
				.build();
		
		return uri.toString();
		
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
	public int getCntPageNum() {
		return cntPageNum;
	}
	public void setCntPageNum(int cntPageNum) {
		this.cntPageNum = cntPageNum;
	}
	public int getRealEndPage() {
		return realEndPage;
	}
	public void setRealEndPage(int realEndPage) {
		this.realEndPage = realEndPage;
	}
	@Override
	public String toString() {
		return "PageMaker [criteria=" + criteria + ", totalCount=" + totalCount + ", startPage=" + startPage
				+ ", endPage=" + endPage + ", prev=" + prev + ", next=" + next + ", cntPageNum=" + cntPageNum
				+ ", realEndPage=" + realEndPage + "]";
	}
}
