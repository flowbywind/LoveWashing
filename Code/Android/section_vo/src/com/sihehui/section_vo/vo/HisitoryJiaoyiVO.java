package com.sihehui.section_vo.vo;

public class HisitoryJiaoyiVO {
	// 交易记录id
	private String id;
	// 交易时间
	private String time;
	// 交易金额
	private String money;
	// 交易详情
	private String detail;
	// 交易类型信息
	private String typeInfo;

	/**
	 * @return 交易记录id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param 交易记录id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return 交易时间
	 */
	public String getTime() {
		return time;
	}

	/**
	 * @param 交易时间
	 */
	public void setTime(String time) {
		this.time = time;
	}

	/**
	 * @return 交易金额
	 */
	public String getMoney() {
		return money;
	}

	/**
	 * @param 交易金额
	 */
	public void setMoney(String money) {
		this.money = money;
	}

	/**
	 * @return 交易详情
	 */
	public String getDetail() {
		return detail;
	}

	/**
	 * @param 交易详情
	 */
	public void setDetail(String detail) {
		this.detail = detail;
	}

	/**
	 * @return 交易类型信息
	 */
	public String getTypeInfo() {
		return typeInfo;
	}

	/**
	 * @param 交易类型信息
	 */
	public void setTypeInfo(String typeInfo) {
		this.typeInfo = typeInfo;
	}
}
