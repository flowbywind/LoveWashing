package com.sihehui.section_vo.vo;

import java.io.Serializable;

public class LoanVO implements Serializable{
	private String id, userId, description, loanPurpose, money, loanMoney,
			cardinalNumber, rate, minInvestMoney, repayType, progress,
			remainTime, deadline,name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLoanPurpose() {
		return loanPurpose;
	}

	public void setLoanPurpose(String loanPurpose) {
		this.loanPurpose = loanPurpose;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getLoanMoney() {
		return loanMoney;
	}

	public void setLoanMoney(String loanMoney) {
		this.loanMoney = loanMoney;
	}

	public String getCardinalNumber() {
		return cardinalNumber;
	}

	public void setCardinalNumber(String cardinalNumber) {
		this.cardinalNumber = cardinalNumber;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getMinInvestMoney() {
		return minInvestMoney;
	}

	public void setMinInvestMoney(String minInvestMoney) {
		this.minInvestMoney = minInvestMoney;
	}

	public String getRepayType() {
		return repayType;
	}

	public void setRepayType(String repayType) {
		this.repayType = repayType;
	}

	public String getProgress() {
		return progress;
	}

	public void setProgress(String progress) {
		this.progress = progress;
	}

	public String getRemainTime() {
		return remainTime;
	}

	public void setRemainTime(String remainTime) {
		this.remainTime = remainTime;
	}

	public String getDeadline() {
		return deadline;
	}

	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
