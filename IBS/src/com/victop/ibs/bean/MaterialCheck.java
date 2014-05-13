package com.victop.ibs.bean;

/**
 * 素材审核实体类
 * 
 * @author vv
 * 
 */
public class MaterialCheck {
	private String modelId = "IBS11115";// 数据模型编号
	private String datasetId = "1";// 数据集编号
	private String checkman;// 审核人
	private String checkmanid;// 审核人ID
	private String checkdate;// 审核时间
	private String checkreason;// 理由
	private String materialid;// 素材ID
	private String matcheckid;// 素材审核ID

	public String getCheckman() {
		return checkman;
	}

	public void setCheckman(String checkman) {
		this.checkman = checkman;
	}

	public String getCheckmanid() {
		return checkmanid;
	}

	public void setCheckmanid(String checkmanid) {
		this.checkmanid = checkmanid;
	}

	public String getCheckdate() {
		return checkdate;
	}

	public void setCheckdate(String checkdate) {
		this.checkdate = checkdate;
	}

	public String getCheckreason() {
		return checkreason;
	}

	public void setCheckreason(String checkreason) {
		this.checkreason = checkreason;
	}

	public String getMaterialid() {
		return materialid;
	}

	public void setMaterialid(String materialid) {
		this.materialid = materialid;
	}

	public String getMatcheckid() {
		return matcheckid;
	}

	public void setMatcheckid(String matcheckid) {
		this.matcheckid = matcheckid;
	}

}
