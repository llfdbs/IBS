package com.victop.ibs.bean;

import com.victop.ibs.db.base.BaseBean;

/**
 * 素材删除记录
 * 
 * @author vv
 * 
 */
public class MaterialDeleteBean extends BaseBean {
	
	private String deletename;// 删除人
	private String deletemanid;// 删除人
	private String deletedate;// 删除时间
	private String deleteid;// 删除记录id
	private String materialid;// 素材id

	public String getDeletename() {
		return deletename;
	}

	public void setDeletename(String deletename) {
		this.deletename = deletename;
	}

	public String getDeletemanid() {
		return deletemanid;
	}

	public void setDeletemanid(String deletemanid) {
		this.deletemanid = deletemanid;
	}

	public String getDeletedate() {
		return deletedate;
	}

	public void setDeletedate(String deletedate) {
		this.deletedate = deletedate;
	}

	public String getDeleteid() {
		return deleteid;
	}

	public void setDeleteid(String deleteid) {
		this.deleteid = deleteid;
	}

	public String getMaterialid() {
		return materialid;
	}

	public void setMaterialid(String materialid) {
		this.materialid = materialid;
	}

}
