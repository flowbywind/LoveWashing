package com.sihehui.section_vo.vo;

public class AccountMoneyVO {
	private double balance; //	账户总额
	private double balanceAvaliable;//	可用余额
	private String collectCorpusAndInterest;	//待收本息
	private double freezeAmount	;//冻结金额
	private double totalInvestAmount;//	累计投资总额
	private String collectProfit;//	预计总收益
	private String receivedCorpus; //	已收本金
	private String receviedInterest;//	已收利息
	private double collectCorpus;//	待收本金
	private double collectInterest; //	待收利息
	/**
	 * @return 账户总额
	 */
	public double getBalance() {
		return balance;
	}
	/**
	 * @param 账户总额
	 */
	public void setBalance(double balance) {
		this.balance = balance;
	}
	/**
	 * @return 可用余额
	 */
	public double getBalanceAvaliable() {
		return balanceAvaliable;
	}
	/**
	 * @param 可用余额
	 */
	public void setBalanceAvaliable(double balanceAvaliable) {
		this.balanceAvaliable = balanceAvaliable;
	}
	/**
	 * @return 待收本息
	 */
	public String getCollectCorpusAndInterest() {
		return collectCorpusAndInterest;
	}
	/**
	 * @param set 待收本息
	 */
	public void setCollectCorpusAndInterest(String collectCorpusAndInterest) {
		this.collectCorpusAndInterest = collectCorpusAndInterest;
	}
	/**
	 * @return 冻结金额
	 */
	public double getFreezeAmount() {
		return freezeAmount;
	}
	/**
	 * @param 冻结金额
	 */
	public void setFreezeAmount(double freezeAmount) {
		this.freezeAmount = freezeAmount;
	}
	/**
	 * @return 累计投资总额
	 */
	public double getTotalInvestAmount() {
		return totalInvestAmount;
	}
	/**
	 * @param 累计投资总额
	 */
	public void setTotalInvestAmount(double totalInvestAmount) {
		this.totalInvestAmount = totalInvestAmount;
	}
	/**
	 * @return 预计总收益
	 */
	public String getCollectProfit() {
		return collectProfit;
	}
	/**
	 * @param 预计总收益
	 */
	public void setCollectProfit(String collectProfit) {
		this.collectProfit = collectProfit;
	}
	/**
	 * @return 已收本金
	 */
	public String getReceivedCorpus() {
		return receivedCorpus;
	}
	/**
	 * @param 已收本金
	 */
	public void setReceivedCorpus(String receivedCorpus) {
		this.receivedCorpus = receivedCorpus;
	}
	/**
	 * @return 已收利息
	 */
	public String getReceviedInterest() {
		return receviedInterest;
	}
	/**
	 * @param 已收利息
	 */
	public void setReceviedInterest(String receviedInterest) {
		this.receviedInterest = receviedInterest;
	}
	/**
	 * @return 待收本金
	 */
	public double getCollectCorpus() {
		return collectCorpus;
	}
	/**
	 * @param 待收本金
	 */
	public void setCollectCorpus(double collectCorpus) {
		this.collectCorpus = collectCorpus;
	}
	/**
	 * @return 待收利息
	 */
	public double getCollectInterest() {
		return collectInterest;
	}
	/**
	 * @param 待收利息
	 */
	public void setCollectInterest(double collectInterest) {
		this.collectInterest = collectInterest;
	}

}
