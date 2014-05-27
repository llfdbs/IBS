package com.victop.ibs.bean;

import com.victop.ibs.db.base.BaseBean;
/**
 * 任务相关素材实体类
 * 
 * @author vv
 * 
 */
public class TaskMaterialsBean extends BaseBean {
	private String modelId = "IBS11118";// 任务素材查询数据模型编号
	public static final String datasetId = "2";// 任务素材查询数据集编号
	private String addman;//提交人
	private String adddate;//添加时间
	private String materialid;//素材ID
	private String materialmemo;//素材内容
	private String summaterialid;//素材总数
	private String imgname;// 图片名称
	private String imgmemo;// 图片描述
	private String imgwidth;// 缩略图宽度
	private String imgrule;// 缩略图规则
	private String imghigh;// 缩略图高度
	private String materialstatus;//素材状态
	public String getMaterialstatus() {
		return materialstatus;
	}
	public void setMaterialstatus(String materialstatus) {
		this.materialstatus = materialstatus;
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
	
	public String getAddman() {
		return addman;
	}
	public void setAddman(String addman) {
		this.addman = addman;
	}
	public String getAdddate() {
		return adddate;
	}
	public void setAdddate(String adddate) {
		this.adddate = adddate;
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
	public String getSummaterialid() {
		return summaterialid;
	}
	public void setSummaterialid(String summaterialid) {
		this.summaterialid = summaterialid;
	}
	
	
}
