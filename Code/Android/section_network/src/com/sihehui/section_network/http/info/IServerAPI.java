package com.sihehui.section_network.http.info;

import java.util.Map;

import com.sihehui.section_vo.vo.DefaultVO;
import com.sihehui.section_vo.vo.ListActivityVO;
import com.sihehui.section_vo.vo.ListFriendVO;
import com.sihehui.section_vo.vo.ListNormalVO;
import com.sihehui.section_vo.vo.ListOtherVO;
import com.sihehui.section_vo.vo.ListProductionVO;
import com.sihehui.section_vo.vo.ListQuestionVO;
import com.sihehui.section_vo.vo.ListSquareVO;
import com.sihehui.section_vo.vo.ListUserVO;
import com.sihehui.section_vo.vo.LoginVO;
import com.sihehui.section_vo.vo.PersonInfoVO;
import com.sihehui.section_vo.vo.ProductionVO;
import com.sihehui.section_vo.vo.RegistVO;
import com.sihehui.section_vo.vo.VersionVO;

/**
 * 通用参数 
 * imei imei号 
 * version 软件版本 
 * channel 渠道号码 
 * package 软件包名
 * 
 * */
/**
 * @author Administrator
 * 
 */
public interface IServerAPI {

	/**
	 * 版本更新
	 */
	void getVersion(Map<String, String> input,
			IConnectionNetworkAble<VersionVO> connection);

	/**
	 * 注册
	 * 
	 */
	void regist(Map<String, String> input,
			IConnectionNetworkAble<RegistVO> connection);

	/**
	 * 注册时验证码
	 */
	void regist_apply(Map<String, String> input,
			IConnectionNetworkAble<DefaultVO> connection);

	/**
	 * 登录
	 */
	void login(Map<String, String> input,
			IConnectionNetworkAble<LoginVO> connection);

	/**
	 * 退出
	 */
	void logout(Map<String, String> input,
			IConnectionNetworkAble<DefaultVO> connection);

	/**
	 * 更改用户头像
	 */
	void update_image(Map<String, Object> files, Map<String, String> input,
			IConnectionNetworkAble<LoginVO> connection);

	/**
	 * 找回密码
	 * 
	 */
	void findpsd(Map<String, String> input,
			IConnectionNetworkAble<RegistVO> connection);

	/**
	 * 找回密码证码
	 */
	void findpsd_apply(Map<String, String> input,
			IConnectionNetworkAble<DefaultVO> connection);

	/**
	 * 身份验证信息
	 */
	void user_confirm(Map<String, Object> files, Map<String, String> input,
			IConnectionNetworkAble<LoginVO> connection);

	/**
	 * 文章评论
	 */
	void article_comment(Map<String, String> input,
			IConnectionNetworkAble<DefaultVO> connection);

	/**
	 * 文章列表
	 */
	void article_list(Map<String, String> input,
			IConnectionNetworkAble<ListNormalVO> connection);

	/**
	 * 文章点赞
	 */
	void article_praise(Map<String, String> input,
			IConnectionNetworkAble<DefaultVO> connection);

	/**
	 * 文章收藏
	 */
	void article_collection(Map<String, String> input,
			IConnectionNetworkAble<DefaultVO> connection);

	/**
	 * 评论点赞
	 */
	void comment_praise(Map<String, String> input,
			IConnectionNetworkAble<DefaultVO> connection);

	/**
	 * 文章评论列表
	 * 
	 * @param input
	 * @param connection
	 */
	void comment_list(Map<String, String> input,
			IConnectionNetworkAble<ListOtherVO> connection);

	/**
	 * 文章分享
	 */
	void article_share(Map<String, String> input,
			IConnectionNetworkAble<DefaultVO> connection);

	/**
	 * 我的文章评论列表
	 */
	void my_article_comment(Map<String, String> input,
			IConnectionNetworkAble<ListNormalVO> connection);

	/**
	 * 我的文章评论收集
	 */
	void my_article_collection(Map<String, String> input,
			IConnectionNetworkAble<ListNormalVO> connection);

	/**
	 * 我的文章评论点赞
	 */
	void my_article_praise(Map<String, String> input,
			IConnectionNetworkAble<ListNormalVO> connection);

	/**
	 * 添加好友
	 */
	void add_friend(Map<String, String> input,
			IConnectionNetworkAble<DefaultVO> connection);

	/**
	 * 接受好友
	 */
	void accept_friend(Map<String, String> input,
			IConnectionNetworkAble<DefaultVO> connection);

	/**
	 * 邀请好友
	 */
	void invite_friend(Map<String, String> input,
			IConnectionNetworkAble<DefaultVO> connection);

	/**
	 * 查询是否好友
	 */
	void isfriend(Map<String, String> input,
			IConnectionNetworkAble<ListFriendVO> connection);

	/**
	 * 我的好友列表
	 */
	void my_friend(Map<String, String> input,
			IConnectionNetworkAble<ListFriendVO> connection);

	/**
	 * 活动列表
	 */
	void activity_list(Map<String, String> input,
			IConnectionNetworkAble<ListActivityVO> connection);

	/**
	 * 我的报名活动列表
	 */
	void my_activity_apply_list(Map<String, String> input,
			IConnectionNetworkAble<ListActivityVO> connection);

	/**
	 * 我的收藏活动列表
	 */
	void my_activity_collection_list(Map<String, String> input,
			IConnectionNetworkAble<ListActivityVO> connection);

	/**
	 * 报名活动
	 */
	void activity_apply(Map<String, String> input,
			IConnectionNetworkAble<DefaultVO> connection);

	/**
	 * 收藏活动
	 */
	void activity_collection(Map<String, String> input,
			IConnectionNetworkAble<DefaultVO> connection);

	/**
	 * 个人身份信息
	 */
	void my_detail(Map<String, String> input,
			IConnectionNetworkAble<PersonInfoVO> connection);

	/**
	 * 广场列表
	 */
	void square_list(Map<String, String> input,
			IConnectionNetworkAble<ListSquareVO> connection);

	/**
	 * 我的广场发言
	 */
	void my_square_speak(Map<String, String> input,
			IConnectionNetworkAble<ListSquareVO> connection);

	/**
	 * 我的广场评论
	 */
	void my_square_comment(Map<String, String> input,
			IConnectionNetworkAble<ListSquareVO> connection);

	/**
	 * 我的广场点赞
	 */
	void my_square_praise(Map<String, String> input,
			IConnectionNetworkAble<ListSquareVO> connection);

	/**
	 * 广场发言,转发公用一个方法
	 */
	void square_speak(Map<String, Object> inputFils, Map<String, String> input,
			IConnectionNetworkAble<DefaultVO> connection);

	/**
	 * 广场点赞
	 */
	void square_praise(Map<String, String> input,
			IConnectionNetworkAble<DefaultVO> connection);

	/**
	 * 广场评论
	 */
	void square_comment(Map<String, String> input,
			IConnectionNetworkAble<DefaultVO> connection);

	/**
	 * 广场转发
	 */
	void square_forward(Map<String, String> input,
			IConnectionNetworkAble<DefaultVO> connection);

	/**
	 * 广场发言中转发列表
	 */
	void square_speak_forward(Map<String, String> input,
			IConnectionNetworkAble<ListSquareVO> connection);

	/**
	 * 广场发言中评论列表
	 */
	void square_speak_comment(Map<String, String> input,
			IConnectionNetworkAble<ListSquareVO> connection);

	/**
	 * 广场发言中评论点赞
	 */
	void square_speak_comment_praise(Map<String, String> input,
			IConnectionNetworkAble<DefaultVO> connection);

	/**
	 * 广场发言中点赞列表
	 */
	void square_speak_praise(Map<String, String> input,
			IConnectionNetworkAble<ListSquareVO> connection);

	/**
	 * @广场发言发言
	 */
	void my_square_at(Map<String, String> input,
			IConnectionNetworkAble<ListSquareVO> connection);

	/**
	 * 一问列表
	 */
	void question_list(Map<String, String> input,
			IConnectionNetworkAble<ListQuestionVO> connection);

	/**
	 * 提问
	 */
	void question_ask(Map<String, Object> inputFils, Map<String, String> input,
			IConnectionNetworkAble<DefaultVO> connection);

	/**
	 * 问题回答列表
	 */
	void question_answer_list(Map<String, String> input,
			IConnectionNetworkAble<ListQuestionVO> connection);

	/**
	 * 回答列表点赞
	 */
	void question_answer_praise(Map<String, String> input,
			IConnectionNetworkAble<DefaultVO> connection);

	/**
	 * 一问列表回答
	 */
	void question_answer(Map<String, String> input,
			IConnectionNetworkAble<DefaultVO> connection);

	/**
	 * 一问列表收藏
	 */
	void question_collection(Map<String, String> input,
			IConnectionNetworkAble<DefaultVO> connection);

	/**
	 * 一问列表点赞
	 */
	void question_praise(Map<String, String> input,
			IConnectionNetworkAble<DefaultVO> connection);

	/**
	 * 一问列表专家列表
	 */
	void question_expert(Map<String, String> input,
			IConnectionNetworkAble<ListUserVO> connection);

	/**
	 * 一问列表邀专家
	 */
	void question_at_expert(Map<String, String> input,
			IConnectionNetworkAble<DefaultVO> connection);

	/**
	 * 我的回答
	 */
	void my_question_comment(Map<String, String> input,
			IConnectionNetworkAble<ListQuestionVO> connection);

	/**
	 * 我的提问
	 */
	void my_question_speak(Map<String, String> input,
			IConnectionNetworkAble<ListQuestionVO> connection);

	/**
	 * 我的问题点赞
	 */
	void my_question_praise(Map<String, String> input,
			IConnectionNetworkAble<ListQuestionVO> connection);

	/**
	 * 我的问题收藏
	 */
	void my_question_collection(Map<String, String> input,
			IConnectionNetworkAble<ListQuestionVO> connection);

	/**
	 * 我的问题收藏
	 */
	void my_question_at(Map<String, String> input,
			IConnectionNetworkAble<ListQuestionVO> connection);

	/**
	 * 产品列表
	 */
	void production_list(Map<String, String> input,
			IConnectionNetworkAble<ListProductionVO> connection);

	/**
	 * 产品详情
	 */
	void production_detail(Map<String, String> input,
			IConnectionNetworkAble<ProductionVO> connection);

	/**
	 * 店铺产品列表
	 */
	void store_production_list(Map<String, String> input,
			IConnectionNetworkAble<ListProductionVO> connection);

	/**
	 * 获取产品数量
	 */
	void production_num(Map<String, String> input,
			IConnectionNetworkAble<ProductionVO> connection);

	/**
	 * 产品订单
	 * 
	 * @param input
	 * @param connection
	 */
	void production_order(Map<String, String> input,
			IConnectionNetworkAble<DefaultVO> connection);

	/**
	 * 我的订单
	 * 
	 * @param input
	 * @param connection
	 */
	void my_order(Map<String, String> input,
			IConnectionNetworkAble<ListProductionVO> connection);

	/**
	 * 加入购物车
	 * 
	 * @param input
	 * @param connection
	 */
	void add_cart(Map<String, String> input,
			IConnectionNetworkAble<DefaultVO> connection);

	/**
	 * 我的购物车
	 * 
	 * @param input
	 * @param connection
	 */
	void my_cart(Map<String, String> input,
			IConnectionNetworkAble<ListProductionVO> connection);

	/**
	 * 产品收藏
	 * 
	 * @param input
	 * @param connection
	 */
	void collection_goods(Map<String, String> input,
			IConnectionNetworkAble<DefaultVO> connection);

	/**
	 * 店铺收藏
	 * 
	 * @param input
	 * @param connection
	 */
	void collection_store(Map<String, String> input,
			IConnectionNetworkAble<DefaultVO> connection);

	/**
	 * 我的产品收藏
	 * 
	 * @param input
	 * @param connection
	 */
	void my_goods_collection(Map<String, String> input,
			IConnectionNetworkAble<ListProductionVO> connection);

	/**
	 * 我的店铺收藏
	 * 
	 * @param input
	 * @param connection
	 */
	void my_store_collection(Map<String, String> input,
			IConnectionNetworkAble<ListProductionVO> connection);

	/**
	 * 订单评价
	 * 
	 * @param input
	 * @param connection
	 */
	void order_comment(Map<String, String> input,
			IConnectionNetworkAble<DefaultVO> connection);

	/**
	 * 确认收货
	 * 
	 * @param input
	 * @param connection
	 */
	void order_confirm(Map<String, String> input,
			IConnectionNetworkAble<DefaultVO> connection);
}
