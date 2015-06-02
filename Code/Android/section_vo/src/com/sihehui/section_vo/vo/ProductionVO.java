package com.sihehui.section_vo.vo;

import java.io.Serializable;
import java.util.List;

public class ProductionVO implements Serializable {
	/**
	 * 
	 */
	private static long serialVersionUID = 1L;
	private String goodsMaterial;
	private String goodsStyle;
	private String goodsName;
	private Long goodsNum;
	private String goodsSmPicUrl;
	private String goodsFee;
	private String goodsTotalFee;
	private String goodsStatus;
	private String goodsId;
	private String goodsColor;
	private String goodsBrand;
	private String goodsBigPicUrl;
	private String goodsDesc;
	private String shopId;
	private String shopName;
	private String shopPic;
	private String goodsComPicUrl;
	private String orderId;
	private String cartId;
	private Integer retCode;
	private Integer commentType;
	private Integer paymentType;
	private List<ProductionCommentVO> listComment;

	// private String receiverName, receiverPhone, receiverAddress,
	// receiverPostcode;
	//
	// public String getReceiverName() {
	// return receiverName;
	// }
	//
	// public void setReceiverName(String receiverName) {
	// this.receiverName = receiverName;
	// }
	//
	// public String getReceiverPhone() {
	// return receiverPhone;
	// }
	//
	// public void setReceiverPhone(String receiverPhone) {
	// this.receiverPhone = receiverPhone;
	// }
	//
	// public String getReceiverAddress() {
	// return receiverAddress;
	// }
	//
	// public void setReceiverAddress(String receiverAddress) {
	// this.receiverAddress = receiverAddress;
	// }
	//
	// public String getReceiverPostcode() {
	// return receiverPostcode;
	// }
	//
	// public void setReceiverPostcode(String receiverPostcode) {
	// this.receiverPostcode = receiverPostcode;
	// }

	public List<ProductionCommentVO> getListComment() {
		return listComment;
	}

	public void setListComment(List<ProductionCommentVO> listComment) {
		this.listComment = listComment;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public static void setSerialVersionUID(long serialVersionUID) {
		ProductionVO.serialVersionUID = serialVersionUID;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static void setSerialversionuid(long serialversionuid) {
		serialVersionUID = serialversionuid;
	}

	public String getGoodsTotalFee() {
		return goodsTotalFee;
	}

	public void setGoodsTotalFee(String goodsTotalFee) {
		this.goodsTotalFee = goodsTotalFee;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getCartId() {
		return cartId;
	}

	public void setCartId(String cartId) {
		this.cartId = cartId;
	}

	public Long getGoodsNum() {
		return goodsNum;
	}

	public void setGoodsNum(Long goodsNum) {
		this.goodsNum = goodsNum;
	}

	public String getGoodsColor() {
		return goodsColor;
	}

	public void setGoodsColor(String goodsColor) {
		this.goodsColor = goodsColor;
	}

	public String getGoodsBrand() {
		return goodsBrand;
	}

	public void setGoodsBrand(String goodsBrand) {
		this.goodsBrand = goodsBrand;
	}

	public String getGoodsBigPicUrl() {
		return goodsBigPicUrl;
	}

	public void setGoodsBigPicUrl(String goodsBigPicUrl) {
		this.goodsBigPicUrl = goodsBigPicUrl;
	}

	public String getGoodsDesc() {
		return goodsDesc;
	}

	public void setGoodsDesc(String goodsDesc) {
		this.goodsDesc = goodsDesc;
	}

	public String getGoodsMaterial() {
		return goodsMaterial;
	}

	public void setGoodsMaterial(String goodsMaterial) {
		this.goodsMaterial = goodsMaterial;
	}

	public String getGoodsStyle() {
		return goodsStyle;
	}

	public void setGoodsStyle(String goodsStyle) {
		this.goodsStyle = goodsStyle;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getGoodsSmPicUrl() {
		return goodsSmPicUrl;
	}

	public void setGoodsSmPicUrl(String goodsSmPicUrl) {
		this.goodsSmPicUrl = goodsSmPicUrl;
	}

	public String getGoodsFee() {
		return goodsFee;
	}

	public void setGoodsFee(String goodsFee) {
		this.goodsFee = goodsFee;
	}

	public String getGoodsStatus() {
		return goodsStatus;
	}

	public void setGoodsStatus(String goodsStatus) {
		this.goodsStatus = goodsStatus;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public String getGoodsComPicUrl() {
		return goodsComPicUrl;
	}

	public void setGoodsComPicUrl(String goodsComPicUrl) {
		this.goodsComPicUrl = goodsComPicUrl;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getShopPic() {
		return shopPic;
	}

	public void setShopPic(String shopPic) {
		this.shopPic = shopPic;
	}

	public Integer getRetCode() {
		return retCode;
	}

	public void setRetCode(Integer retCode) {
		this.retCode = retCode;
	}

	public Integer getCommentType() {
		return commentType;
	}

	public void setCommentType(Integer commentType) {
		this.commentType = commentType;
	}

	public Integer getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(Integer paymentType) {
		this.paymentType = paymentType;
	}

}
