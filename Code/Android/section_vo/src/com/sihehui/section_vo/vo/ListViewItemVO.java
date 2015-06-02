package com.sihehui.section_vo.vo;

public class ListViewItemVO {
	private String Title;
	private Integer ImageId;

	public ListViewItemVO()
	{
	}
	public ListViewItemVO(String title)
	{
		this.Title=title;
	}
	public ListViewItemVO(String title,Integer imageId)
	{
		this.Title=title;
		this.ImageId=imageId;
	}
	public void setTitle(String title) {
		this.Title = title;
	}

	public String getTitle() {
		return this.Title;
	}

	public void setImageId(Integer imageId) {
		this.ImageId = imageId;
	}

	public Integer getImageId() {
		return this.ImageId;
	}
}
