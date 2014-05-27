package com.victop.ibs.db.model;

import com.victop.ibs.bean.MaterialDetailHistoryBean;
import com.victop.ibs.bean.MaterialDetailMessageBean;
import com.victop.ibs.bean.MaterialDetailPictureBean;
import com.victop.ibs.bean.MaterialDetailSortBean;
import com.victop.ibs.bean.MaterialDetailTagBean;

/**
 * 素材详情model
 * 
 * @author vv
 * 
 */
public class MaterialDetailModel {
	public final static String modelId="IBS10217";
	public final static String formId="10217";
	public MaterialDetailMessageBean materialDetailMessageBean;
	public MaterialDetailTagBean materialDetailTagBean;
	public MaterialDetailSortBean materialDetailSortBean;
	public MaterialDetailPictureBean materialDetailPictureBean;
	public MaterialDetailHistoryBean materialDetailHistoryBean;
	public MaterialDetailMessageBean getMaterialDetailMessageBean() {
		return materialDetailMessageBean;
	}
	public void setMaterialDetailMessageBean(
			MaterialDetailMessageBean materialDetailMessageBean) {
		this.materialDetailMessageBean = materialDetailMessageBean;
	}
	public MaterialDetailTagBean getMaterialDetailTagBean() {
		return materialDetailTagBean;
	}
	public void setMaterialDetailTagBean(MaterialDetailTagBean materialDetailTagBean) {
		this.materialDetailTagBean = materialDetailTagBean;
	}
	public MaterialDetailSortBean getMaterialDetailSortBean() {
		return materialDetailSortBean;
	}
	public void setMaterialDetailSortBean(
			MaterialDetailSortBean materialDetailSortBean) {
		this.materialDetailSortBean = materialDetailSortBean;
	}
	public MaterialDetailPictureBean getMaterialDetailPictureBean() {
		return materialDetailPictureBean;
	}
	public void setMaterialDetailPictureBean(
			MaterialDetailPictureBean materialDetailPictureBean) {
		this.materialDetailPictureBean = materialDetailPictureBean;
	}
	public MaterialDetailHistoryBean getMaterialDetailHistoryBean() {
		return materialDetailHistoryBean;
	}
	public void setMaterialDetailHistoryBean(
			MaterialDetailHistoryBean materialDetailHistoryBean) {
		this.materialDetailHistoryBean = materialDetailHistoryBean;
	}
	
}
