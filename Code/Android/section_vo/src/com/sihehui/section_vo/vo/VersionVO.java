package com.sihehui.section_vo.vo;

public class VersionVO {
	private String curversion;
	private String desc;
	private String link;
	private String retCode;
	private String error;

	public String getRetCode() {
		return retCode;
	}

	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getCurversion() {
		return curversion;
	}

	public void setCurversion(String curversion) {
		this.curversion = curversion;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

}
