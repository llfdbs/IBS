package com.victop.ibs.bean;

import com.victop.ibs.db.base.BaseBean;
/**
 * 素材详情详细内容
 * 
 * @author vv
 * 
 */
public class MaterialDetailMessageBean extends BaseBean {
	private String modelId = "IBS10217";// 素材详情详细查询数据模型编号
	public static final String datasetId = "1";// 数据集编号
	private String taskcode;//任务单号
	private String imgname;//图片名称
	private String imgmemo;//图片描述
	private String versioncode;//版本号
	private String materialguid;//素材GUID
	private String materialid;//素材id
	private String materialmemo;//素材内容
	private String imgwidth;//缩略图宽度
	private String imgrule;//缩略图规则
	private String imghigh;//缩略图高度
	
	public String getTaskcode() {
		return taskcode;
	}
	public void setTaskcode(String taskcode) {
		this.taskcode = taskcode;
	}
	public String getVersioncode() {
		return versioncode;
	}
	public void setVersioncode(String versioncode) {
		this.versioncode = versioncode;
	}
	public String getMaterialid() {
		return materialid;
	}
	public void setMaterialid(String materialid) {
		this.materialid = materialid;
	}
	public String getMaterialmemo() {
		return materialmemo;
	}
	public void setMaterialmemo(String materialmemo) {
		this.materialmemo = materialmemo;
	}
	public String getImgname() {
		return imgname;
	}
	public void setImgname(String imgname) {
		this.imgname = imgname;
	}
	public String getImgmemo() {
		return imgmemo;
	}
	public void setImgmemo(String imgmemo) {
		this.imgmemo = imgmemo;
	}
	public String getMaterialguid() {
		return materialguid;
	}
	public void setMaterialguid(String materialguid) {
		this.materialguid = materialguid;
	}
	public String getImgwidth() {
		return imgwidth;
	}
	public void setImgwidth(String imgwidth) {
		this.imgwidth = imgwidth;
	}
	public String getImgrule() {
		return imgrule;
	}
	public void setImgrule(String imgrule) {
		this.imgrule = imgrule;
	}
	public String getImghigh() {
		return imghigh;
	}
	public void setImghigh(String imghigh) {
		this.imghigh = imghigh;
	}
	
	
}
