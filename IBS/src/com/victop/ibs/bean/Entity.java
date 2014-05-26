package com.victop.ibs.bean;

import java.io.Serializable;

public class Entity implements Serializable {

	String id;// url

	String URL;//

	String Text;
	String hieght;

	public String getHieght() {
		return hieght;
	}

	public void setHieght(String hieght) {
		this.hieght = hieght;
	}

	
	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	public String getText() {
		return Text;
	}

	public void setText(String text) {
		Text = text;
	}

	public String getId() {

		return id;

	}

	public void setId(String id) {

		this.id = id;

	}

}
