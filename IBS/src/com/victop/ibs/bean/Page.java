package com.victop.ibs.bean;
/**
 * 分页实体类
 * 
 * @author vv
 * 
 */
public class Page {

	public int ispage;//是否分页（1分页  0不分页）
	public int pageno;//页码   
	public int pagesize;//每页显示的条目
	
	public int getIspage() {
		return ispage;
	}
	public void setIspage(int ispage) {
		this.ispage = ispage;
	}
	public int getPageno() {
		return pageno;
	}
	public void setPageno(int pageno) {
		this.pageno = pageno;
	}
	public int getPagesize() {
		return pagesize;
	}
	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}
	
	
	
}
