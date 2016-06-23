package com.zstu.bysj.cmgs.util;

/**
 * 分页工具类
 */
import java.io.Serializable;
import java.util.List;

/**
 * <pre>
 * 分页数据对象，创建该对象最基本的参数必须 包括：page 或 pageSize
 * pageSize为空时，默认采用每页 10 条作为页面显示条数。
 * 例如，
 * 1. 分页对象的创建：PageList<Xxx> pageList = PageList.build(1, 10);
 * 2. 分页对象作为Controller的Body参数输入，客户端直接JSON对象POST请求：
 *    POST: {"page":2,"pageSize":4,"totalSize":10}
 * ntroller: public ResponseEntity<String> getXxx(@RequestBody PageList<Xxx> userPageList) { }
 */
public class PageList<T> implements Serializable {
	// 默认页面显示条数
	private static final int PAGE_SIZE = 10;
	private static final long serialVersionUID = -6127342801976137954L;
	// 当前页数
	private int page = 1;
	// 页面大小，每页显示条数
	private int pageSize = PAGE_SIZE;
	// // 总页数，计算出来，不允许用户设置
	// private int totalPage;
	// // 开始偏移量，计算出来，不允许用户设置
	// private int start;
	// 总条数，即总记录数
	private int totalSize = -1;
	// 返回的列表数据
	private List<T> data;
	// 条件和实体对象
	private T condition;

	public boolean isFirstPage() {
		return page <= 1;
	}

	public boolean isLastPage() {
		return page >= this.getTotalPage();
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int currPage) {
		this.page = currPage;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	/**
	 * <pre>
	 * 获取开始偏移量
	 * @return
	 */
	public int getStart() {
		return (page - 1) * pageSize;
	}

	/**
	 * <pre>
	 * 获取当前页结束偏移量
	 * @return
	 */
	public int getLast() {
		return Math.min(page * pageSize, totalSize) - 1;
	}

	/**
	 * <pre>
	 * 获取总页数
	 * @return
	 */
	public int getTotalPage() {
		if (totalSize < 0) {
			return 0;
		}
		if (totalSize % pageSize == 0) {
			return totalSize / pageSize;
		} else {
			return totalSize / pageSize + 1;
		}
	}

	public T getCondition() {
		return condition;
	}

	public void setCondition(T condition) {
		this.condition = condition;
	}

	/**
	 * 
	 * @param page
	 *            当前页面
	 * @param pageSize
	 *            每页显示数
	 * @return
	 */
	public static <E> PageList<E> build(Integer page, Integer pageSize) {
		return build(page == null ? 1 : page.intValue(), pageSize == null ? PAGE_SIZE : pageSize.intValue());
	}

	public static <E> PageList<E> build(int page, int pageSize) {
		PageList<E> pageList = new PageList<E>();
		pageList.setPage(page);
		pageList.setPageSize(pageSize);
		return pageList;
	}

}
