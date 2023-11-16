package utils;

/*
 * ���� �������� ���õ� ���� ���� Ŭ����
 * - ���� ������ ��ȣ
 * - �������� �Խñ��� ��
 */
public class Criteria {
	private int pageNum; // ���� ������ ��ȣ
	private int rowsPerPage; // �������� �Խñ��� ��

	public Criteria() {
		this(1, 10); // �⺻ ������ ȣ��� : ������ ��ȣ = 1, �������� �Խñ��� �� = 10
	}

	public Criteria(int pageNum, int rowsPerPage) {
		this.pageNum = pageNum;
		this.rowsPerPage = rowsPerPage;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		if (pageNum <= 0)
			this.pageNum = 1;
		else
			this.pageNum = pageNum;
	}

	public int getRowsPerPage() {
		return rowsPerPage;
	}

	/*
	 * �������� �Խñ��� �� ���� : 1-20
	 */
	public void setRowsPerPage(int rowsPerPage) {
		if (rowsPerPage <= 0 || rowsPerPage > 20)
			this.rowsPerPage = 20;
		else
			this.rowsPerPage = rowsPerPage;
	}

	@Override
	public String toString() {
		return "Criteria [pageNum=" + pageNum + ", rowsPerPage=" + rowsPerPage + "]";
	}

}
