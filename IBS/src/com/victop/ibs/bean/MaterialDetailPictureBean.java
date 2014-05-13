package com.victop.ibs.bean;

import com.victop.ibs.db.base.BaseBean;
/**
 * 素材详情图片查询
 * 
 * @author vv
 * 
 */
public class MaterialDetailPictureBean extends BaseBean {
	private String modelId_send = "IBS10217";// 素材详情图片查询数据模型编号
	private String datasetId = "4";// 数据集编号
	private String imgurl;//图片url
	private String imgmemo;//图片内容
	private String imgname;//图片名称
	private String imgabbrurl;//图片缩略图url
	
	public String getModelId_send() {
		return modelId_send;
	}
	public void setModelId_send(String modelId_send) {
		this.modelId_send = modelId_send;
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
	public String getImgmemo() {
		return imgmemo;
	}
	public void setImgmemo(String imgmemo) {
		this.imgmemo = imgmemo;
	}
	public String getImgname() {
		return imgname;
	}
	public void setImgname(String imgname) {
		this.imgname = imgname;
	}
	public String getImgabbrurl() {
		return imgabbrurl;
	}
	public void setImgabbrurl(String imgabbrurl) {
		this.imgabbrurl = imgabbrurl;
	}
	
}
