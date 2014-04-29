package com.victop.ibs.db.model;

public class Person {
	private long mID;
	private String mNameHashCode;
	private String mAgeHashCode;
	private String mHeightHashCode;

	public long getmID() {
		return mID;
	}

	public void setmID(long pID) {
		mID = pID;
	}

	public String getmNameHashCode() {
		return mNameHashCode;
	}

	public void setmNameHashCode(String mNameHashCode) {
		this.mNameHashCode = mNameHashCode;
	}

	public String getmAgeHashCode() {
		return mAgeHashCode;
	}

	public void setmAgeHashCode(String mAgeHashCode) {
		this.mAgeHashCode = mAgeHashCode;
	}

	public String getmHeightHashCode() {
		return mHeightHashCode;
	}

	public void setmHeightHashCode(String mHeightHashCode) {
		this.mHeightHashCode = mHeightHashCode;
	}

	public long getID() {
		return mID;
	}

	public void setID(long pID) {
		mID = pID;
	}

	public String getNameHashCode() {
		return mNameHashCode;
	}

	public void setNameHashCode(String pNameHashCode) {
		mNameHashCode = pNameHashCode;
	}

	public String getAgeHashCode() {
		return mAgeHashCode;
	}

	public void setAgeHashCode(String pAgeHashCode) {
		mAgeHashCode = pAgeHashCode;
	}

	public String getHeightHashCode() {
		return mHeightHashCode;
	}

	public void setHeightHashCode(String pHeightHashCode) {
		mHeightHashCode = pHeightHashCode;
	}

	@Override
	public String toString() {
		return "Person [mID=" + mID + ", mNameHashCode=" + mNameHashCode
				+ ", mAgeHashCode=" + mAgeHashCode + ", mHeightHashCode="
				+ mHeightHashCode + "]";
	}

}
