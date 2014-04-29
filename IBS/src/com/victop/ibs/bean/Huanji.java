package com.victop.ibs.bean;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Huanji implements Serializable {
/**
 * 换机的bean数据
 */
	private static final long serialVersionUID = 1L;
	String description; // 软件描述
	String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSoftwareName() {
		return softwareName;
	}

	public void setSoftwareName(String softwareName) {
		this.softwareName = softwareName;
	}

	public String getImagesName() {
		return imagesName;
	}

	public void setImagesName(String imagesName) {
		this.imagesName = imagesName;
	}

	public String getOnlineTime() {
		return onlineTime;
	}

	public void setOnlineTime(String onlineTime) {
		this.onlineTime = onlineTime;
	}

	public String getDownloadLink() {
		return downloadLink;
	}

	public void setDownloadLink(String downloadLink) {
		this.downloadLink = downloadLink;
	}

	String softwareName; // 名称
	String imagesName; // 图片
	String onlineTime;// 上线时间
	String downloadLink; // 下载地址

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static List listFromJson(String jsonData) {
		Type listType = new TypeToken<LinkedList<Huanji>>() {
		}.getType();
		Gson gson = new Gson();
		LinkedList<Huanji> list = gson.fromJson(jsonData, listType);
		return list;
	}

}
