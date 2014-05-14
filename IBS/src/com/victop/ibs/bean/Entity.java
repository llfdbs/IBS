package com.victop.ibs.bean;

import java.io.Serializable;

public class Entity implements Serializable {

	String id;// url

	String URL;//

	String Text;

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
