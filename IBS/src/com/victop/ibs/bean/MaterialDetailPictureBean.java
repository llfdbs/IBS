package com.victop.ibs.bean;

import java.io.Serializable;

import com.victop.ibs.db.base.BaseBean;
/**
 * 素材详情图片查询
 * 
 * @author vv
 * 
 */
public class MaterialDetailPictureBean extends BaseBean implements Serializable {
	private static final long serialVersionUID = -7260210544600464481L;
    public static final String modelId = "IBS10217";// 素材详情图片查询数据模型编号
    public static final String datasetId = "4";// 数据集编号
	private String imgmemo;//图片内容
	private String imgname;//图片名称
	private String imgwidth;//缩略图宽度
	private String imgrule;//缩略图规则
	private String imghigh;//缩略图高度
	
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
