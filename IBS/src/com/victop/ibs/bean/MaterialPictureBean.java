package com.victop.ibs.bean;

import com.victop.ibs.db.base.BaseBean;
/**
 * 素材图片关系
 * 
 * @author vv
 * 
 */
public class MaterialPictureBean extends BaseBean {
	private String modelId = "IBS11112";// 数据模型编号
	private String datasetId = "16";// 数据集编号
	private String imgurl;//图片url
	private String imgname;//图片名称
	private String imgmemo;//图片描述
	private String imgabbrurl;//图片缩略图url
	private String materialid;//素材id
	private String matimgid;//素材图片id
	public String getModelId() {
		return modelId;
	}
	public void setModelId(String modelId) {
		this.modelId = modelId;
	}
	public String getDatasetId() {
		return datasetId;
	}
	public void setDatasetId(String datasetId) {
		this.datasetId = datasetId;
	}
	public String getImgurl() {
		return imgurl;
	}
	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
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
	public String getImgabbrurl() {
		return imgabbrurl;
	}
	public void setImgabbrurl(String imgabbrurl) {
		this.imgabbrurl = imgabbrurl;
	}
	public String getMaterialid() {
		return materialid;
	}
	public void setMaterialid(String materialid) {
		this.materialid = materialid;
	}
	public String getMatimgid() {
		return matimgid;
	}
	public void setMatimgid(String matimgid) {
		this.matimgid = matimgid;
	}
	
}
