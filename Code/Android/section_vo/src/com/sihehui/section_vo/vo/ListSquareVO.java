package com.sihehui.section_vo.vo;

import java.util.ArrayList;
import java.util.List;

public class ListSquareVO {
	private Integer retCode;
	private List<SquareVO> dataList = new ArrayList<SquareVO>();
	private String error;
	private Integer limit;
	private Integer page;
	private Integer nextPage;
	private Boolean hasNextPage;
	private Integer totalCount;

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Boolean getHasNextPage() {
		return hasNextPage;
	}

	public void setHasNextPage(Boolean hasNextPage) {
		this.hasNextPage = hasNextPage;
	}

	public Integer getRetCode() {
		return retCode;
	}

	public void setRetCode(Integer retCode) {
		this.retCode = retCode;
	}

	public List<SquareVO> getDataList() {
		return dataList;
	}

	public void setDataList(List<SquareVO> dataList) {
		this.dataList = dataList;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public Integer getNextPage() {
		return nextPage;
	}

	public void setNextPage(Integer nextPage) {
		this.nextPage = nextPage;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

}
