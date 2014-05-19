package com.victop.ibs.db.base;

public class BaseBean {
	
	/**
	 * 1-查询；
	   2-删除；
	   4-新增；
	   8-修改；
	   
	   数据状态，表示该数据的当前状态，是查询来的，还是需要修改的
	 */
	String RowState = "1"; 

	public String getRowState() {
		return RowState;
	}

	public void setRowState(String rowState) {
		RowState = rowState;
	}
}