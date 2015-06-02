package com.sihehui.section_vo.vo;

public class QuestionVO {
	private String askId;
	private String title;
	private String userId;
	private String username;
	private String headPic;
	private String content;
	private Long createTime;
	private String picUrl;
	private Integer commentNum;
	private Integer praiseNum;
	private Integer collectionNum;
	private String industryId;
	private Boolean hasCollection;
	private Boolean hasPraise;
	private String status;
	private String commentId;
	private String commentContent;
	private Long commentCreateTime;
	private String contentUserName;

	public String getContentUserName() {
		return contentUserName;
	}

	public void setContentUserName(String contentUserName) {
		this.contentUserName = contentUserName;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public Long getCommentCreateTime() {
		return commentCreateTime;
	}

	public void setCommentCreateTime(Long commentCreateTime) {
		this.commentCreateTime = commentCreateTime;
	}

	public String getAskId() {
		return askId;
	}

	public void setAskId(String askId) {
		this.askId = askId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getHeadPic() {
		return headPic;
	}

	public void setHeadPic(String headPic) {
		this.headPic = headPic;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public Integer getCommentNum() {
		return commentNum;
	}

	public void setCommentNum(Integer commentNum) {
		this.commentNum = commentNum;
	}

	public Integer getPraiseNum() {
		return praiseNum;
	}

	public void setPraiseNum(Integer praiseNum) {
		this.praiseNum = praiseNum;
	}

	public Integer getCollectionNum() {
		return collectionNum;
	}

	public void setCollectionNum(Integer collectionNum) {
		this.collectionNum = collectionNum;
	}

	public String getIndustryId() {
		return industryId;
	}

	public void setIndustryId(String industryId) {
		this.industryId = industryId;
	}

	public Boolean getHasPraise() {
		return hasPraise;
	}

	public void setHasPraise(Boolean hasPraise) {
		this.hasPraise = hasPraise;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Boolean getHasCollection() {
		return hasCollection;
	}

	public void setHasCollection(Boolean hasCollection) {
		this.hasCollection = hasCollection;
	}

	public String getCommentId() {
		return commentId;
	}

	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}

}
