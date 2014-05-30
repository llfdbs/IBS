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
	private String imgname;// 图片名称
	private String imgmemo;// 图片描述
	private String materialguid;// GUID
	private String matimgid;// 素材图片ID
	private String imgwidth;// 缩略图宽度
	private String imgrule;// 缩略图规则
	private String imghigh;// 缩略图高度

	public MaterialPictureBean() {
		setRowState("4");
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

	public String getMatimgid() {
		return matimgid;
	}

	public void setMatimgid(String matimgid) {
		this.matimgid = matimgid;
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
