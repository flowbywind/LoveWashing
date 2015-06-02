package com.sihehui.section_network.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.telephony.TelephonyManager;

import com.sihehui.section_network.R;

public class Contacts {

	public static final class ServiceURL {

		public static final String BannerService = "bannerAppService";// 首页轮播图片
		public static final String CXD_AuthCode = "authCodeAppService";// 注册时获取验证码接口
		public static final String RegisterService = "registerAppService";// 用户注册
		public static final String LoginService = "loginAppService"; // 登录
		public static final String ResetPswService = "resetPswAppService";// 重新设置密码
		public static final String UserInfoAppAppService = "userInfoAppService";
		public static final String LoansAppService = "loansAppService";// 5.
																		// 投资项目
		public static final String TransactionRecordsAppService = "transactionRecordsAppService";// 7.
																									// 交易记录
		public static final String AccountBalanceAppService = "accountBalanceAppService";// 8.
																							// 账户金额
		public static final String InvestRecordsAppService = "investRecordsAppService";// 9.
																						// 项目投资记录
		public static final String UserInvestsAppService = "userInvestsAppService";// 10.
																					// 用户投资记录
		public static final String IsRealnameAuthAppService = "isRealnameAuthAppService";// 12.
																							// 实名认证与否
		public static final String RealnameAuthAppService = "realnameAuthAppService";// 13.
																						// 实名认证
		public static final String RechargeAppService = "rechargeAppService";// 14.
																				// 充值
		public static final String InvestAppService = "investAppService";// 15.
																			// 投资
		public static final String WithdrawAppService = "withdrawAppService";// 16.
																				// 提现
		public static final String RepayPlanAppService = "repayPlanAppService";// 投资还款计划
		public static final String BindCardQueryAppService="bindCardQueryAppService";//我的银行卡
		public static final String ArticeAppService="articeAppService";//文章查询
		public static final String ReferrerAppService="referrerAppService";//推荐人管理
		public static final String ActivityAppService="activityAppService";//活动

		public static final String GET_VERSION = "/outside/version/getVersion.json?";
		public static final String REGIST_APPLY = "/outside/register/apply.json";// 注册时获取验证码接口

		public static final String LOGIN = "/outside/user/login.json";// 登录
		public static final String LOGOUT = "/do/user/logout.json";// 退出
		public static final String FINDPSD_APPLY = "/outside/findpasswd/apply.json";// 找回密码时获取短信验证码
		public static final String FINDPSD = "/outside/findpasswd/confirm.json";// 找回密码
		public static final String USER_CONFIRM = "/do/user/confirm.json";// 身份确认信息
		public static final String PERSION_INFO = "/my/user/personInfo.json";// 查询用户信息
		public static final String UPDATE_IMAGE = "/do/user/updateAvatar.json";// 更改用户头像

		public static final String ADD_FRIEND = "/do/friend/addFriend.json";// 添加好友
		public static final String ACCEPT_FRIEND = "/do/friend/acceptFriend.json";// 接受好友邀请
		public static final String INVITE_FRIEND = "/do/friend/invitFriend.json";// 邀请好友
		public static final String IS_FRIEND = "/my/friend/isMyFriends.json";// 查询是否是好友，发言中@时使用
		public static final String QUERY_FRIEND = "/my/friend/queryMyFriends.json";// 获取好友列表

		public static final String ARTICLE_LIST = "/query/article/getList.json";// 文章列表
		public static final String ACTIVITY_LIST = "/query/activity/getList.json";// 活动列表
		public static final String COMMENT_LIST = "query/article/getCommentList.json";// 评论列表
		public static final String ARTICLE_COMMENT = "/do/article/commentArticle.json";// 文章评论
		public static final String ARTICLE_PRAISE = "/do/article/praiseArticle.json";// 文章点赞
		public static final String ARTICLE_COLLECTION = "/do/article/collectionArticle.json";// 文章收藏
		public static final String COMMENT_PRAISE = "/do/article/commentPraise.json";// 评论点赞
		public static final String ARTICLE_SHARE = "/do/article/shareArticle.json";// 文章分享
		public static final String MY_ARTICLE_COMMENT = "/my/article/myArticleComment.json";// 我的文章评论列表
		public static final String MY_ARTICLE_COLLECTION = "/my/article/myCollectionArticle.json";// 我的文章收藏列表
		public static final String MY_ARTICLE_PRAISE = "/my/article/myPraiseArticle.json";// 我的文章点赞列表

		public static final String ACTIVITY_APPLY = "/do/activity/applyActivity.json";// 活动报名
		public static final String ACTIVITY_COLLECTION = "/do/activity/collectionActivity.json";// 活动收藏
		public static final String MY_ACTIVITY_APPLY = "/my/activity/myApplyActivity.json";// 我的活动报名列表
		public static final String MY_ACTIVITY_COLLECTION = "/my/activity/myCollectionActivity.json";// 我的活动收藏列表

		public static final String SQUARE_LIST = "/query/speak/speakList.json";// 广场列表
		public static final String SQUARE_SPEAK = "/do/speak/saveSpeak.json";// 广场发言
		public static final String SQUARE_FORWARD = "/do/speak/saveForwardSpeak.json";// 广场转发
		public static final String SQUARE_PRAISE = "/do/speak/savePraise.json";// 广场点赞
		public static final String SQUARE_COMMENT = "/do/speak/saveComment.json";// 广场评论
		public static final String SQUARE_SPEAK_FORWORD = "/query/speak/speakForward.json";// 发言中转发列表
		public static final String SQUARE_SPEAK_COMMENT = "/query/speak/speakComment.json";// 发言中评论列表
		public static final String SQUARE_SPEAK_COMMENT_PRAISE = "/do/speak/saveCommentPraise.json";// 发言中评论列表中点赞功能
		public static final String SQUARE_SPEAK_PRAISE = "/query/speak/speakPraise.json";// 发言中点赞列表
		public static final String MY_SQUARE_SPEAK = "/my/speak/mySpeak.json";// 我的广场发言
		public static final String MY_SQUARE_COMMENT = "/my/speak/myCommentSpeak.json";// 我的广场评论
		public static final String MY_SQUARE_PRAISE = "/my/speak/myPraiseSpeak.json";// 我的广场点赞
		public static final String MY_SQUARE_AT = "/my/speak/atMeSpeak.json";// @我的发言

		public static final String QUESTION_LIST = "/query/ask/getList.json";// 一问列表
		public static final String QUESTION_ASK = "/do/ask/saveAsk.json";// 一问提问
		public static final String QUESTION_ANSWER_LIST = "/query/ask/getCommentList.json";// 一问回答列表
		public static final String QUESTION_ANSWER_PRAISE = "/do/ask/commentPraise.json";// 一问回答中的点赞
		public static final String QUESTION_ANSWER = "/do/ask/commentAsk.json";// 一问列表中回答
		public static final String QUESTION_COLLECTION = "/do/ask/collectionAsk.json";// 一问列表中收藏
		public static final String QUESTION_PRAISE = "/do/ask/praiseAsk.json";// 一问列表中收藏
		public static final String QUESTION_EXPERTS = "/do/ask/getExpert.json";// 一问列表中专家列表
		public static final String QUESTION_AT_EXPERTS = "/do/ask/atExpert.json";// 一问列表邀专家
		public static final String MY_QUESTION_COMMENT = "/my/ask/myCommentAsk.json";// 我的一问评论
		public static final String MY_QUESTION_COLLECTION = "/my/ask/myCollectionAsk.json";// 我的一问收藏
		public static final String MY_QUESTION_PRAISE = "/my/ask/myPraiseAsk.json";// 我的一问点赞
		public static final String MY_QUESTION_SPEAK = "/my/ask/myAsk.json";// 我的一问提问
		public static final String MY_QUESTION_AT = "/my/ask/atMeAsk.json";// @我的问题

		/**
		 * 以下为云纸新增接口
		 */
		public static final String GOODS_LIST = "/query/goods/getList.json";// 产品列表
		public static final String GOODS_DETAIL = "/query/goods/querygoods.json";// 产品详情
		public static final String STORE_DETAIL = "/query/goods/querygoodsdetail.json";// 店铺详情
		public static final String GOODS_NUM = "/query/goods/querygoodsnum.json";// 点击立即购买后数量选择
		public static final String GOODS_ORDER = "/do/order/saveorder.json";// 订单
		public static final String MY_ORDER = "/query/order/showorders.json";// 我的订单
		public static final String ADD_CART = "/do/order/saveordercart.json";// 加入购物车
		public static final String MY_CART = "/query/order/queryordercart.json";// 我的购物车
		public static final String GOODS_COLLECTION = "/do/goods/savegoodsCollection.json";// 产品收藏
		public static final String MY_GOODS_COLLECTION = "/query/goods/showgoodsCollection.json";// 我的产品收藏
		public static final String STORE_COLLECTION = "/do/goods/saveshopCollection.json";// 店铺收藏
		public static final String MY_STORE_COLLECTION = "/query/goods/showshopCollection.json";// 我的店铺收藏
		public static final String ORDER_COMMENT = "/do/goods/addgoodsComment.json";// 订单评价
		public static final String ORDER_CONFIRM = "/do/order/confirmorders.json";// 确认收货

	}

	public static final class WebURL {

	}

	public static final class StaticURL {
	}

	public static final String SIGN_NAME = "111111";// 签名

	// public String CLIENT_SOURCE;
	// public String WEB_SOURCE;
	public String IMEI = "UNKNOWN";
	public String VERSION = "UNKNOWN";
	public String CHANNEL = "1711";
	public String PACKAGE = "UNKNOWN";

	public String SERVER_DOMAIN;
	public String SERVER;
	public String SERVER_RESOURCE;
	static Contacts contacts;
	Context context;

	public static Contacts getInstance(Context context) {
		if (contacts == null) {
			contacts = new Contacts(context);
		}
		return contacts;
	}

	Contacts(Context context) {
		this.context = context;
		// 获取imei、包名、版本号等
		TelephonyManager tm = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		IMEI = tm.getDeviceId();
		PackageManager packageManager = context.getPackageManager();
		try {
			PackageInfo packageInfo = packageManager.getPackageInfo(
					context.getPackageName(), 0);
			PACKAGE = packageInfo.packageName;
			VERSION = packageInfo.versionName;
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// String CHANNEL_KEY = "CHANNEL_KEY";
		// String channel;
		// SharedPreferences preferences =
		// context.getSharedPreferences("CHANNEL",
		// Activity.MODE_PRIVATE);
		// if (preferences.contains(CHANNEL_KEY)) {
		// channel = preferences.getString(CHANNEL_KEY,
		// context.getString(R.string.channel));
		// } else {
		// channel = context.getString(R.string.channel);
		// Editor editor = preferences.edit();
		// editor.putString(CHANNEL_KEY, channel);
		// editor.commit();
		// }
		// if (channel != null) {
		// CHANNEL = channel;
		// }

		SERVER_DOMAIN = context.getString(R.string.host);
		SERVER = "http://" + SERVER_DOMAIN;
		SERVER_RESOURCE = "http://" + context.getString(R.string.host_resource);
		// CLIENT_SOURCE = context.getString(R.string.client_source);
		// WEB_SOURCE = context.getString(R.string.web_source) + VERSION;

	}

}
