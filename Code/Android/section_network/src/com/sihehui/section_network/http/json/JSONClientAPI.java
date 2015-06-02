package com.sihehui.section_network.http.json;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;
import android.os.DeadObjectException;

import com.sihehui.section_network.http.info.BaseClientAPI;
import com.sihehui.section_network.http.info.IConnectionNetworkAble;
import com.sihehui.section_network.http.info.IFailureListener;
import com.sihehui.section_network.http.info.IServerAPI;
import com.sihehui.section_network.http.info.ProcessException;
import com.sihehui.section_network.util.Contacts;
import com.sihehui.section_vo.vo.ActivityVO;
import com.sihehui.section_vo.vo.DefaultVO;
import com.sihehui.section_vo.vo.FriendVO;
import com.sihehui.section_vo.vo.ListActivityVO;
import com.sihehui.section_vo.vo.ListFriendVO;
import com.sihehui.section_vo.vo.ListNormalVO;
import com.sihehui.section_vo.vo.ListOtherVO;
import com.sihehui.section_vo.vo.ListProductionVO;
import com.sihehui.section_vo.vo.ListQuestionVO;
import com.sihehui.section_vo.vo.ListSquareVO;
import com.sihehui.section_vo.vo.ListUserVO;
import com.sihehui.section_vo.vo.LoginVO;
import com.sihehui.section_vo.vo.NormalDataVO;
import com.sihehui.section_vo.vo.OtherDataVO;
import com.sihehui.section_vo.vo.PersonInfoVO;
import com.sihehui.section_vo.vo.ProductionCommentVO;
import com.sihehui.section_vo.vo.ProductionVO;
import com.sihehui.section_vo.vo.QuestionVO;
import com.sihehui.section_vo.vo.RegistVO;
import com.sihehui.section_vo.vo.SquareVO;
import com.sihehui.section_vo.vo.UserVO;
import com.sihehui.section_vo.vo.VersionVO;

public class JSONClientAPI extends BaseClientAPI implements IServerAPI {

	private final static String TAG = JSONClientAPI.class.getName();

	private static JSONClientAPI clientAPI;

	/**
	 * @param context
	 */
	private JSONClientAPI(Context context) {
		super(context);
	}

	/**
	 * 推荐使用 ApplicationContext
	 * 
	 * @param context
	 * @return
	 */
	public static JSONClientAPI getInstance(Context context) {
		if (clientAPI == null) {
			clientAPI = new JSONClientAPI(context);
		}
		return clientAPI;
	}

	@Override
	public void regist(Map<String, String> input,
			IConnectionNetworkAble<RegistVO> connection) {
		// TODO Auto-generated method stub
		post(Contacts.ServiceURL.RegisterService, input, new CallbackListener<RegistVO>(
				connection) {

			@Override
			RegistVO parseResult(JSONObject result) throws ProcessException,
					Exception {
				RegistVO regVO = new RegistVO();
				regVO.setRetCode(result.isNull("retCode") ? null : result
						.optInt("retCode"));
				regVO.setError(result.isNull("errors") ? null : result
						.optString("errors"));
				JSONObject data = result.optJSONObject("data");
				if (isNotNull(data)) {
					regVO.setUid(data.isNull("uid") ? null : data
							.optString("uid"));
					regVO.setToken(data.isNull("token") ? null : data
							.optString("token"));
				}

				return regVO;
			}

			@Override
			String logTag() {
				return TAG + ".registration";
			}
		}, (IFailureListener) connection);
	}

	@Override
	public void regist_apply(Map<String, String> input,
			IConnectionNetworkAble<DefaultVO> connection) {
		// TODO Auto-generated method stub
		post(Contacts.ServiceURL.REGIST_APPLY, input,
				new CallbackListener<DefaultVO>(connection) {

					@Override
					DefaultVO parseResult(JSONObject result)
							throws ProcessException, Exception {
						DefaultVO vo = new DefaultVO();
						vo.setRetCode(result.isNull("retCode") ? null : result
								.optInt("retCode"));
						vo.setError(result.isNull("errors") ? null : result
								.optString("errors"));
						return vo;
					}

					@Override
					String logTag() {
						return TAG + ".registration";
					}
				}, (IFailureListener) connection);
	}

	@Override
	public void getVersion(Map<String, String> input,
			IConnectionNetworkAble<VersionVO> connection) {
		// TODO Auto-generated method stub
		post(Contacts.ServiceURL.GET_VERSION, input,
				new CallbackListener<VersionVO>(connection) {

					@Override
					VersionVO parseResult(JSONObject result)
							throws ProcessException, Exception {
						VersionVO vo = new VersionVO();
						vo.setRetCode(result.isNull("retCode") ? null : result
								.optString("retCode"));
						vo.setError(result.isNull("errors") ? null : result
								.optString("errors"));
						JSONObject data = result.optJSONObject("data");
						if (isNotNull(data)) {
							vo.setCurversion(data.isNull("version") ? null
									: data.optString("version"));
							vo.setDesc(data.isNull("desc") ? null : data
									.optString("desc"));
							vo.setLink(data.isNull("link") ? null : data
									.optString("link"));
						}
						return vo;
					}

					@Override
					String logTag() {
						return TAG + ".registration";
					}
				}, (IFailureListener) connection);
	}

	@Override
	public void update_image(Map<String, Object> files,
			Map<String, String> input,
			IConnectionNetworkAble<LoginVO> connection) {
		// TODO Auto-generated method stub
		post(Contacts.ServiceURL.UPDATE_IMAGE, files, input,
				new CallbackListener<LoginVO>(connection) {

					@Override
					LoginVO parseResult(JSONObject result)
							throws ProcessException, Exception {
						LoginVO vo = new LoginVO();
						vo.setRetCode(result.isNull("retCode") ? null : result
								.optInt("retCode"));
						vo.setError(result.isNull("errors") ? null : result
								.optString("errors"));
						JSONObject data = result.optJSONObject("data");
						if (isNotNull(data)) {
							// vo.setUid(data.isNull("uid") ? null : data
							// .optString("uid"));
							// vo.setToken(data.isNull("token") ? null : data
							// .optString("token"));
							// vo.setStatus(data.isNull("status") ? null : data
							// .optString("status"));

							// JSONObject user = data.optJSONObject("user");
							// if (isNotNull(user)) {
							vo.setUserId(data.isNull("userId") ? null : data
									.optString("userId"));
							vo.setHeadPic(data.isNull("headPic") ? null : data
									.optString("headPic"));
							vo.setCreateTime(data.isNull("createTime") ? null
									: data.optLong("createTime"));
							// vo.setActivated(data.isNull("activated") ?
							// null :
							// data
							// .optString("activated"));
							vo.setUserAccout(data.isNull("userAccout") ? null
									: data.optString("userAccout"));
							vo.setUserName(data.isNull("userName") ? null
									: data.optString("userName"));
							vo.setPassword(data.isNull("password") ? null
									: data.optString("password"));
							vo.setCityId(data.isNull("cityId") ? null : data
									.optString("cityId"));
							vo.setCityName(data.isNull("cityName") ? null
									: data.optString("cityName"));
							vo.setCompany(data.isNull("company") ? null : data
									.optString("company"));
							vo.setUsertypeId(data.isNull("usertypeId") ? null
									: data.optString("usertypeId"));
							vo.setIndustryId(data.isNull("industryId") ? null
									: data.optString("industryId"));
							vo.setBrandName(data.isNull("brandName") ? null
									: data.optString("brandName"));
							vo.setMallName(data.isNull("mallName") ? null
									: data.optString("mallName"));
							vo.setJobId(data.isNull("jobId") ? null : data
									.optString("jobId"));
							vo.setCompanyDesc(data.isNull("companyDesc") ? null
									: data.optString("companyDesc"));
							vo.setScore(data.isNull("score") ? null : data
									.optString("score"));
							vo.setStatus(data.isNull("status") ? null : data
									.optString("status"));
							// }

						}
						return vo;
					}

					@Override
					String logTag() {
						return TAG + ".registration";
					}
				}, (IFailureListener) connection);
	}

	@Override
	public void login(Map<String, String> input,
			IConnectionNetworkAble<LoginVO> connection) {
		// TODO Auto-generated method stub
		post(Contacts.ServiceURL.LOGIN, input, new CallbackListener<LoginVO>(
				connection) {

			@Override
			LoginVO parseResult(JSONObject result) throws ProcessException,
					Exception {
				LoginVO vo = new LoginVO();
				vo.setRetCode(result.isNull("retCode") ? null : result
						.optInt("retCode"));
				vo.setError(result.isNull("errors") ? null : result
						.optString("errors"));
				JSONObject data = result.optJSONObject("data");
				if (isNotNull(data)) {
					vo.setUid(data.isNull("uid") ? null : data.optString("uid"));
					vo.setToken(data.isNull("token") ? null : data
							.optString("token"));
					// vo.setStatus(data.isNull("status") ? null : data
					// .optString("status"));

					JSONObject user = data.optJSONObject("user");
					if (isNotNull(user)) {
						vo.setUserId(user.isNull("userId") ? null : user
								.optString("userId"));
						vo.setHeadPic(user.isNull("headPic") ? null : user
								.optString("headPic"));
						vo.setCreateTime(user.isNull("createTime") ? null
								: user.optLong("createTime"));
						// vo.setActivated(user.isNull("activated") ? null :
						// user
						// .optString("activated"));
						vo.setUserAccout(user.isNull("userAccout") ? null
								: user.optString("userAccout"));
						vo.setUserName(user.isNull("userName") ? null : user
								.optString("userName"));
						vo.setPassword(user.isNull("password") ? null : user
								.optString("password"));
						vo.setCityId(user.isNull("cityId") ? null : user
								.optString("cityId"));
						vo.setCityName(user.isNull("cityName") ? null : user
								.optString("cityName"));
						vo.setCompany(user.isNull("company") ? null : user
								.optString("company"));
						vo.setUsertypeId(user.isNull("usertypeId") ? null
								: user.optString("usertypeId"));
						vo.setIndustryId(user.isNull("industryId") ? null
								: user.optString("industryId"));
						vo.setBrandName(user.isNull("brandName") ? null : user
								.optString("brandName"));
						vo.setMallName(user.isNull("mallName") ? null : user
								.optString("mallName"));
						vo.setJobId(user.isNull("jobId") ? null : user
								.optString("jobId"));
						vo.setCompanyDesc(user.isNull("companyDesc") ? null
								: user.optString("companyDesc"));
						vo.setScore(user.isNull("score") ? null : user
								.optString("score"));
						vo.setStatus(user.isNull("status") ? null : user
								.optString("status"));
					}

				}
				return vo;
			}

			@Override
			String logTag() {
				return TAG;
			}
		}, (IFailureListener) connection);
	}

	@Override
	public void logout(Map<String, String> input,
			IConnectionNetworkAble<DefaultVO> connection) {
		// TODO Auto-generated method stub
		post(Contacts.ServiceURL.LOGOUT, input,
				new CallbackListener<DefaultVO>(connection) {

					@Override
					DefaultVO parseResult(JSONObject result)
							throws ProcessException, Exception {
						DefaultVO vo = new DefaultVO();
						vo.setRetCode(result.isNull("retCode") ? null : result
								.optInt("retCode"));
						vo.setError(result.isNull("errors") ? null : result
								.optString("errors"));
						return vo;
					}

					@Override
					String logTag() {
						return TAG;
					}
				}, (IFailureListener) connection);
	}

	@Override
	public void findpsd(Map<String, String> input,
			IConnectionNetworkAble<RegistVO> connection) {
		// TODO Auto-generated method stub
		post(Contacts.ServiceURL.FINDPSD, input,
				new CallbackListener<RegistVO>(connection) {

					@Override
					RegistVO parseResult(JSONObject result)
							throws ProcessException, Exception {
						RegistVO vo = new RegistVO();
						vo.setRetCode(result.isNull("retCode") ? null : result
								.optInt("retCode"));
						vo.setError(result.isNull("errors") ? null : result
								.optString("errors"));
						return vo;
					}

					@Override
					String logTag() {
						return TAG;
					}
				}, (IFailureListener) connection);
	}

	@Override
	public void findpsd_apply(Map<String, String> input,
			IConnectionNetworkAble<DefaultVO> connection) {
		// TODO Auto-generated method stub
		post(Contacts.ServiceURL.FINDPSD_APPLY, input,
				new CallbackListener<DefaultVO>(connection) {

					@Override
					DefaultVO parseResult(JSONObject result)
							throws ProcessException, Exception {
						DefaultVO vo = new DefaultVO();
						vo.setRetCode(result.isNull("retCode") ? null : result
								.optInt("retCode"));
						vo.setError(result.isNull("errors") ? null : result
								.optString("errors"));
						return vo;
					}

					@Override
					String logTag() {
						return TAG;
					}
				}, (IFailureListener) connection);
	}

	@Override
	public void user_confirm(Map<String, Object> files,
			Map<String, String> input,
			IConnectionNetworkAble<LoginVO> connection) {
		// TODO Auto-generated method stub
		post(Contacts.ServiceURL.USER_CONFIRM, files, input,
				new CallbackListener<LoginVO>(connection) {

					@Override
					LoginVO parseResult(JSONObject result)
							throws ProcessException, Exception {
						LoginVO vo = new LoginVO();
						vo.setRetCode(result.isNull("retCode") ? null : result
								.optInt("retCode"));
						vo.setError(result.isNull("errors") ? null : result
								.optString("errors"));
						JSONObject data = result.optJSONObject("data");
						if (isNotNull(data)) {
							vo.setUid(data.isNull("uid") ? null : data
									.optString("uid"));
							vo.setToken(data.isNull("token") ? null : data
									.optString("token"));
							// vo.setStatus(data.isNull("status") ? null : data
							// .optString("status"));

							// JSONObject user = data.optJSONObject("user");
							// if (isNotNull(user)) {
							vo.setUserId(data.isNull("userId") ? null : data
									.optString("userId"));
							vo.setHeadPic(data.isNull("headPic") ? null : data
									.optString("headPic"));
							vo.setCreateTime(data.isNull("createTime") ? null
									: data.optLong("createTime"));
							// vo.setActivated(data.isNull("activated") ?
							// null : data
							// .optString("activated"));
							vo.setUserAccout(data.isNull("userAccout") ? null
									: data.optString("userAccout"));
							vo.setUserName(data.isNull("userName") ? null
									: data.optString("userName"));
							vo.setPassword(data.isNull("password") ? null
									: data.optString("password"));
							vo.setCityId(data.isNull("cityId") ? null : data
									.optString("cityId"));
							vo.setCityName(data.isNull("cityName") ? null
									: data.optString("cityName"));
							vo.setCompany(data.isNull("company") ? null : data
									.optString("company"));
							vo.setUsertypeId(data.isNull("usertypeId") ? null
									: data.optString("usertypeId"));
							vo.setIndustryId(data.isNull("industryId") ? null
									: data.optString("industryId"));
							vo.setBrandName(data.isNull("brandName") ? null
									: data.optString("brandName"));
							vo.setMallName(data.isNull("mallName") ? null
									: data.optString("mallName"));
							vo.setJobId(data.isNull("jobId") ? null : data
									.optString("jobId"));
							vo.setCompanyDesc(data.isNull("companyDesc") ? null
									: data.optString("companyDesc"));
							vo.setScore(data.isNull("score") ? null : data
									.optString("score"));
							vo.setStatus(data.isNull("status") ? null : data
									.optString("status"));
							// }

						}
						return vo;
					}

					@Override
					String logTag() {
						return TAG;
					}
				}, (IFailureListener) connection);
	}

	@Override
	public void article_comment(Map<String, String> input,
			IConnectionNetworkAble<DefaultVO> connection) {
		// TODO Auto-generated method stub
		post(Contacts.ServiceURL.ARTICLE_COMMENT, input,
				new CallbackListener<DefaultVO>(connection) {

					@Override
					DefaultVO parseResult(JSONObject result)
							throws ProcessException, Exception {
						DefaultVO vo = new DefaultVO();
						vo.setRetCode(result.isNull("retCode") ? null : result
								.optInt("retCode"));
						vo.setError(result.isNull("errors") ? null : result
								.optString("errors"));
						return vo;
					}

					@Override
					String logTag() {
						return TAG;
					}
				}, (IFailureListener) connection);

	}

	@Override
	public void article_list(Map<String, String> input,
			IConnectionNetworkAble<ListNormalVO> connection) {
		// TODO Auto-generated method stub
		post(Contacts.ServiceURL.ARTICLE_LIST, null, input,
				new CallbackListener<ListNormalVO>(connection) {

					@Override
					ListNormalVO parseResult(JSONObject result)
							throws ProcessException, Exception {
						ListNormalVO listvo = new ListNormalVO();
						if (isNotNull(result)) {
							listvo.setRetCode(result.isNull("retCode") ? null
									: result.optInt("retCode"));
							listvo.setError(result.isNull("errors") ? null
									: result.optString("errors"));
							JSONArray datas = result.optJSONArray("data");
							if (isNotNull(datas)) {
								List<NormalDataVO> list = new ArrayList<NormalDataVO>();
								for (int i = 0; i < datas.length(); i++) {
									JSONObject data = datas.optJSONObject(i);
									if (isNotNull(data)) {
										NormalDataVO vo = new NormalDataVO();
										vo.setArticleFrom(data
												.isNull("articleFrom") ? null
												: data.optString("articleFrom"));
										vo.setArticleId(data
												.isNull("articleId") ? null
												: data.optString("articleId"));
										vo.setSummary(data.isNull("summary") ? null
												: data.optString("summary"));
										vo.setTitle(data.isNull("title") ? null
												: data.optString("title"));
										vo.setTitle(data.isNull("title") ? null
												: data.optString("title"));
										vo.setCreateTime(data
												.isNull("createTime") ? null
												: data.optLong("createTime"));
										vo.setArticleType(data
												.isNull("articleType") ? null
												: data.optString("articleType"));
										vo.setPicUrl(data.isNull("picUrl") ? null
												: data.optString("picUrl"));
										vo.setArticleUrl(data
												.isNull("articleUrl") ? null
												: data.optString("articleUrl"));
										vo.setCommentNum(data
												.isNull("commentNum") ? null
												: data.optInt("commentNum"));
										vo.setCollectionNum(data
												.isNull("collectionNum") ? null
												: data.optInt("collectionNum"));
										vo.setPraiseNum(data
												.isNull("praiseNum") ? null
												: data.optInt("praiseNum"));
										vo.setHasCollection(data
												.isNull("hasCollection") ? false
												: data.optInt("hasCollection") > 0 ? true
														: false);
										vo.setHasPraise(data
												.isNull("hasPraise") ? false
												: data.optInt("hasPraise") > 0 ? true
														: false);
										list.add(vo);
									}
								}
								listvo.setDataList(list);
							}
							JSONObject paginator = result
									.optJSONObject("paginator");
							if (isNotNull(paginator)) {
								listvo.setTotalCount(paginator
										.isNull("totalCount") ? null
										: paginator.optInt("totalCount"));
								listvo.setLimit(paginator.isNull("limit") ? null
										: paginator.optInt("limit"));
								listvo.setPage(paginator.isNull("page") ? null
										: paginator.optInt("page"));
								listvo.setNextPage(paginator.isNull("nextPage") ? null
										: paginator.optInt("nextPage"));
								listvo.setHasNextPage(paginator
										.isNull("hasNextPage") ? false
										: paginator.optBoolean("hasNextPage"));
							}
						}

						return listvo;
					}

					@Override
					String logTag() {
						return TAG;
					}
				}, (IFailureListener) connection);
	}

	@Override
	public void article_praise(Map<String, String> input,
			IConnectionNetworkAble<DefaultVO> connection) {
		// TODO Auto-generated method stub
		post(Contacts.ServiceURL.ARTICLE_PRAISE, null, input,
				new CallbackListener<DefaultVO>(connection) {

					@Override
					DefaultVO parseResult(JSONObject result)
							throws ProcessException, Exception {
						DefaultVO vo = new DefaultVO();
						vo.setRetCode(result.isNull("retCode") ? null : result
								.optInt("retCode"));
						vo.setError(result.isNull("errors") ? null : result
								.optString("errors"));
						return vo;
					}

					@Override
					String logTag() {
						return TAG;
					}
				}, (IFailureListener) connection);
	}

	@Override
	public void article_collection(Map<String, String> input,
			IConnectionNetworkAble<DefaultVO> connection) {
		// TODO Auto-generated method stub
		post(Contacts.ServiceURL.ARTICLE_COLLECTION, input,
				new CallbackListener<DefaultVO>(connection) {

					@Override
					DefaultVO parseResult(JSONObject result)
							throws ProcessException, Exception {
						DefaultVO vo = new DefaultVO();
						vo.setRetCode(result.isNull("retCode") ? null : result
								.optInt("retCode"));
						vo.setError(result.isNull("errors") ? null : result
								.optString("errors"));
						return vo;
					}

					@Override
					String logTag() {
						return TAG;
					}
				}, (IFailureListener) connection);
	}

	@Override
	public void comment_praise(Map<String, String> input,
			IConnectionNetworkAble<DefaultVO> connection) {
		// TODO Auto-generated method stub
		post(Contacts.ServiceURL.COMMENT_PRAISE, input,
				new CallbackListener<DefaultVO>(connection) {

					@Override
					DefaultVO parseResult(JSONObject result)
							throws ProcessException, Exception {
						DefaultVO vo = new DefaultVO();
						vo.setRetCode(result.isNull("retCode") ? null : result
								.optInt("retCode"));
						vo.setError(result.isNull("errors") ? null : result
								.optString("errors"));
						return vo;
					}

					@Override
					String logTag() {
						return TAG;
					}
				}, (IFailureListener) connection);
	}

	@Override
	public void article_share(Map<String, String> input,
			IConnectionNetworkAble<DefaultVO> connection) {
		// TODO Auto-generated method stub
		post(Contacts.ServiceURL.ARTICLE_SHARE, input,
				new CallbackListener<DefaultVO>(connection) {

					@Override
					DefaultVO parseResult(JSONObject result)
							throws ProcessException, Exception {
						DefaultVO vo = new DefaultVO();
						vo.setRetCode(result.isNull("retCode") ? null : result
								.optInt("retCode"));
						vo.setError(result.isNull("errors") ? null : result
								.optString("errors"));
						return vo;
					}

					@Override
					String logTag() {
						return TAG;
					}
				}, (IFailureListener) connection);
	}

	@Override
	public void my_article_comment(Map<String, String> input,
			IConnectionNetworkAble<ListNormalVO> connection) {
		// TODO Auto-generated method stub
		post(Contacts.ServiceURL.MY_ARTICLE_COMMENT, input,
				new CallbackListener<ListNormalVO>(connection) {

					@Override
					ListNormalVO parseResult(JSONObject result)
							throws ProcessException, Exception {
						ListNormalVO listvo = new ListNormalVO();
						if (isNotNull(result)) {
							listvo.setRetCode(result.isNull("retCode") ? null
									: result.optInt("retCode"));
							listvo.setError(result.isNull("errors") ? null
									: result.optString("errors"));
							JSONArray datas = result.optJSONArray("data");
							if (isNotNull(datas)) {
								List<NormalDataVO> list = new ArrayList<NormalDataVO>();
								for (int i = 0; i < datas.length(); i++) {
									JSONObject data = datas.optJSONObject(i);
									if (isNotNull(data)) {
										NormalDataVO vo = new NormalDataVO();
										vo.setArticleFrom(data
												.isNull("articleFrom") ? null
												: data.optString("articleFrom"));
										vo.setArticleUrl(data
												.isNull("articleUrl") ? null
												: data.optString("articleUrl"));
										vo.setUserId(data.isNull("userId") ? null
												: data.optString("userId"));
										vo.setSummary(data.isNull("summary") ? null
												: data.optString("summary"));
										vo.setCommentContent(data
												.isNull("commentContent") ? null
												: data.optString("commentContent"));
										vo.setCommentCreateTime(data
												.isNull("commentCreateTime") ? null
												: data.optLong("commentCreateTime"));
										vo.setHeadPic(data.isNull("headPic") ? null
												: data.optString("headPic"));
										vo.setUserName(data.isNull("userName") ? null
												: data.optString("userName"));
										vo.setStatus(data.isNull("status") ? null
												: data.optString("status"));
										vo.setArticleId(data
												.isNull("articleId") ? null
												: data.optString("articleId"));
										vo.setCommentId(data
												.isNull("commentId") ? null
												: data.optString("commentId"));
										vo.setTitle(data.isNull("title") ? null
												: data.optString("title"));
										vo.setCreateTime(data
												.isNull("createTime") ? null
												: data.optLong("createTime"));
										vo.setPicUrl(data.isNull("picUrl") ? null
												: data.optString("picUrl"));
										vo.setCommentNum(data
												.isNull("commentNum") ? null
												: data.optInt("commentNum"));
										vo.setCollectionNum(data
												.isNull("collectionNum") ? null
												: data.optInt("collectionNum"));
										vo.setPraiseNum(data
												.isNull("praiseNum") ? null
												: data.optInt("praiseNum"));
										vo.setHasCollection(data
												.isNull("hasCollection") ? false
												: data.optInt("hasCollection") > 0 ? true
														: false);
										vo.setHasPraise(data
												.isNull("hasPraise") ? false
												: data.optInt("hasPraise") > 0 ? true
														: false);
										list.add(vo);
									}
								}
								listvo.setDataList(list);
							}
							JSONObject paginator = result
									.optJSONObject("paginator");
							if (isNotNull(paginator)) {
								listvo.setTotalCount(paginator
										.isNull("totalCount") ? null
										: paginator.optInt("totalCount"));
								listvo.setLimit(paginator.isNull("limit") ? null
										: paginator.optInt("limit"));
								listvo.setPage(paginator.isNull("page") ? null
										: paginator.optInt("page"));
								listvo.setNextPage(paginator.isNull("nextPage") ? null
										: paginator.optInt("nextPage"));
								listvo.setHasNextPage(paginator
										.isNull("hasNextPage") ? false
										: paginator.optBoolean("hasNextPage"));
							}
						}

						return listvo;
					}

					@Override
					String logTag() {
						return TAG;
					}
				}, (IFailureListener) connection);
	}

	@Override
	public void my_article_collection(Map<String, String> input,
			IConnectionNetworkAble<ListNormalVO> connection) {
		// TODO Auto-generated method stub
		post(Contacts.ServiceURL.MY_ARTICLE_COLLECTION, input,
				new CallbackListener<ListNormalVO>(connection) {

					@Override
					ListNormalVO parseResult(JSONObject result)
							throws ProcessException, Exception {
						ListNormalVO listvo = new ListNormalVO();
						if (isNotNull(result)) {
							listvo.setRetCode(result.isNull("retCode") ? null
									: result.optInt("retCode"));
							listvo.setError(result.isNull("errors") ? null
									: result.optString("errors"));
							JSONArray datas = result.optJSONArray("data");
							if (isNotNull(datas)) {
								List<NormalDataVO> list = new ArrayList<NormalDataVO>();
								for (int i = 0; i < datas.length(); i++) {
									JSONObject data = datas.optJSONObject(i);
									if (isNotNull(data)) {
										NormalDataVO vo = new NormalDataVO();
										vo.setArticleFrom(data
												.isNull("articleFrom") ? null
												: data.optString("articleFrom"));
										vo.setArticleUrl(data
												.isNull("articleUrl") ? null
												: data.optString("articleUrl"));
										vo.setSummary(data.isNull("summary") ? null
												: data.optString("summary"));
										vo.setUserId(data.isNull("userId") ? null
												: data.optString("userId"));
										vo.setHeadPic(data.isNull("headPic") ? null
												: data.optString("headPic"));
										vo.setUserName(data.isNull("userName") ? null
												: data.optString("userName"));
										vo.setStatus(data.isNull("status") ? null
												: data.optString("status"));
										vo.setArticleId(data
												.isNull("articleId") ? null
												: data.optString("articleId"));
										vo.setCommentId(data
												.isNull("commentId") ? null
												: data.optString("commentId"));
										vo.setTitle(data.isNull("title") ? null
												: data.optString("title"));
										vo.setCreateTime(data
												.isNull("createTime") ? null
												: data.optLong("createTime"));
										vo.setPicUrl(data.isNull("picUrl") ? null
												: data.optString("picUrl"));
										vo.setCommentNum(data
												.isNull("commentNum") ? null
												: data.optInt("commentNum"));
										vo.setCollectionNum(data
												.isNull("collectionNum") ? null
												: data.optInt("collectionNum"));
										vo.setPraiseNum(data
												.isNull("praiseNum") ? null
												: data.optInt("praiseNum"));
										vo.setHasCollection(data
												.isNull("hasCollection") ? false
												: data.optInt("hasCollection") > 0 ? true
														: false);
										vo.setHasPraise(data
												.isNull("hasPraise") ? false
												: data.optInt("hasPraise") > 0 ? true
														: false);
										list.add(vo);
									}
								}
								listvo.setDataList(list);
							}
							JSONObject paginator = result
									.optJSONObject("paginator");
							if (isNotNull(paginator)) {
								listvo.setTotalCount(paginator
										.isNull("totalCount") ? null
										: paginator.optInt("totalCount"));
								listvo.setLimit(paginator.isNull("limit") ? null
										: paginator.optInt("limit"));
								listvo.setPage(paginator.isNull("page") ? null
										: paginator.optInt("page"));
								listvo.setNextPage(paginator.isNull("nextPage") ? null
										: paginator.optInt("nextPage"));
								listvo.setHasNextPage(paginator
										.isNull("hasNextPage") ? false
										: paginator.optBoolean("hasNextPage"));
							}
						}

						return listvo;
					}

					@Override
					String logTag() {
						return TAG;
					}
				}, (IFailureListener) connection);
	}

	@Override
	public void my_article_praise(Map<String, String> input,
			IConnectionNetworkAble<ListNormalVO> connection) {
		// TODO Auto-generated method stub
		post(Contacts.ServiceURL.MY_ARTICLE_PRAISE, input,
				new CallbackListener<ListNormalVO>(connection) {

					@Override
					ListNormalVO parseResult(JSONObject result)
							throws ProcessException, Exception {
						ListNormalVO listvo = new ListNormalVO();
						if (isNotNull(result)) {
							listvo.setRetCode(result.isNull("retCode") ? null
									: result.optInt("retCode"));
							listvo.setError(result.isNull("errors") ? null
									: result.optString("errors"));
							JSONArray datas = result.optJSONArray("data");
							if (isNotNull(datas)) {
								List<NormalDataVO> list = new ArrayList<NormalDataVO>();
								for (int i = 0; i < datas.length(); i++) {
									JSONObject data = datas.optJSONObject(i);
									if (isNotNull(data)) {
										NormalDataVO vo = new NormalDataVO();
										vo.setArticleFrom(data
												.isNull("articleFrom") ? null
												: data.optString("articleFrom"));
										vo.setSummary(data.isNull("summary") ? null
												: data.optString("summary"));
										vo.setArticleId(data
												.isNull("articleId") ? null
												: data.optString("articleId"));
										vo.setTitle(data.isNull("title") ? null
												: data.optString("title"));
										vo.setCreateTime(data
												.isNull("createTime") ? null
												: data.optLong("createTime"));
										vo.setArticleType(data
												.isNull("articleType") ? null
												: data.optString("articleType"));
										vo.setPicUrl(data.isNull("picUrl") ? null
												: data.optString("picUrl"));
										vo.setArticleUrl(data
												.isNull("articleUrl") ? null
												: data.optString("articleUrl"));
										vo.setCommentNum(data
												.isNull("commentNum") ? null
												: data.optInt("commentNum"));
										vo.setCollectionNum(data
												.isNull("collectionNum") ? null
												: data.optInt("collectionNum"));
										vo.setPraiseNum(data
												.isNull("praiseNum") ? null
												: data.optInt("praiseNum"));
										vo.setHasCollection(data
												.isNull("hasCollection") ? false
												: data.optInt("hasCollection") > 0 ? true
														: false);
										vo.setHasPraise(data
												.isNull("hasPraise") ? false
												: data.optInt("hasPraise") > 0 ? true
														: false);
										list.add(vo);
									}
								}
								listvo.setDataList(list);
							}
							JSONObject paginator = result
									.optJSONObject("paginator");
							if (isNotNull(paginator)) {
								listvo.setTotalCount(paginator
										.isNull("totalCount") ? null
										: paginator.optInt("totalCount"));
								listvo.setLimit(paginator.isNull("limit") ? null
										: paginator.optInt("limit"));
								listvo.setPage(paginator.isNull("page") ? null
										: paginator.optInt("page"));
								listvo.setNextPage(paginator.isNull("nextPage") ? null
										: paginator.optInt("nextPage"));
								listvo.setHasNextPage(paginator
										.isNull("hasNextPage") ? false
										: paginator.optBoolean("hasNextPage"));
							}
						}

						return listvo;
					}

					@Override
					String logTag() {
						return TAG;
					}
				}, (IFailureListener) connection);
	}

	@Override
	public void add_friend(Map<String, String> input,
			IConnectionNetworkAble<DefaultVO> connection) {
		// TODO Auto-generated method stub
		post(Contacts.ServiceURL.ADD_FRIEND, input,
				new CallbackListener<DefaultVO>(connection) {

					@Override
					DefaultVO parseResult(JSONObject result)
							throws ProcessException, Exception {
						DefaultVO vo = new DefaultVO();
						vo.setRetCode(result.isNull("retCode") ? null : result
								.optInt("retCode"));
						vo.setError(result.isNull("errors") ? null : result
								.optString("errors"));
						return vo;
					}

					@Override
					String logTag() {
						return TAG;
					}
				}, (IFailureListener) connection);
	}

	@Override
	public void accept_friend(Map<String, String> input,
			IConnectionNetworkAble<DefaultVO> connection) {
		// TODO Auto-generated method stub
		post(Contacts.ServiceURL.ACCEPT_FRIEND, input,
				new CallbackListener<DefaultVO>(connection) {

					@Override
					DefaultVO parseResult(JSONObject result)
							throws ProcessException, Exception {
						DefaultVO vo = new DefaultVO();
						vo.setRetCode(result.isNull("retCode") ? null : result
								.optInt("retCode"));
						vo.setError(result.isNull("errors") ? null : result
								.optString("errors"));
						return vo;
					}

					@Override
					String logTag() {
						return TAG;
					}
				}, (IFailureListener) connection);
	}

	@Override
	public void invite_friend(Map<String, String> input,
			IConnectionNetworkAble<DefaultVO> connection) {
		// TODO Auto-generated method stub
		post(Contacts.ServiceURL.INVITE_FRIEND, input,
				new CallbackListener<DefaultVO>(connection) {

					@Override
					DefaultVO parseResult(JSONObject result)
							throws ProcessException, Exception {
						DefaultVO vo = new DefaultVO();
						vo.setRetCode(result.isNull("retCode") ? null : result
								.optInt("retCode"));
						vo.setError(result.isNull("errors") ? null : result
								.optString("errors"));
						return vo;
					}

					@Override
					String logTag() {
						return TAG;
					}
				}, (IFailureListener) connection);
	}

	@Override
	public void isfriend(Map<String, String> input,
			IConnectionNetworkAble<ListFriendVO> connection) {
		// TODO Auto-generated method stub
		post(Contacts.ServiceURL.IS_FRIEND, input,
				new CallbackListener<ListFriendVO>(connection) {

					@Override
					ListFriendVO parseResult(JSONObject result)
							throws ProcessException, Exception {
						ListFriendVO vo = new ListFriendVO();
						vo.setRetCode(result.isNull("retCode") ? null : result
								.optInt("retCode"));
						vo.setError(result.isNull("errors") ? null : result
								.optString("errors"));
						JSONArray datas = result.optJSONArray("data");
						if (isNotNull(datas)) {
							List<FriendVO> listAct = new ArrayList<FriendVO>();
							for (int index = 0; index < datas.length(); index++) {
								JSONObject data = datas.optJSONObject(index);
								FriendVO fvo = new FriendVO();
								fvo.setPhoneNo(data.isNull("phoneNo") ? null
										: data.optString("phoneNo"));
								fvo.setUserId(data.isNull("userId") ? null
										: data.optString("userId"));
								fvo.setUserName(data.isNull("userName") ? null
										: data.optString("userName"));
								fvo.setHeadPic(data.isNull("headPic") ? null
										: data.optString("headPic"));
								fvo.setStatus(data.isNull("status") ? null
										: data.optString("status"));
								listAct.add(fvo);
							}
							vo.setDataList(listAct);
						}
						JSONObject paginator = result
								.optJSONObject("paginator");
						if (isNotNull(paginator)) {
							vo.setLimit(paginator.isNull("limit") ? null
									: paginator.optInt("limit"));
							vo.setPage(paginator.isNull("page") ? null
									: paginator.optInt("page"));
							vo.setNextPage(paginator.isNull("nextPage") ? null
									: paginator.optInt("nextPage"));
							vo.setHasNextPage(paginator.isNull("hasNextPage") ? false
									: paginator.optBoolean("hasNextPage"));
						}

						return vo;
					}

					@Override
					String logTag() {
						return TAG;
					}
				}, (IFailureListener) connection);
	}

	@Override
	public void my_friend(Map<String, String> input,
			IConnectionNetworkAble<ListFriendVO> connection) {
		// TODO Auto-generated method stub
		post(Contacts.ServiceURL.QUERY_FRIEND, input,
				new CallbackListener<ListFriendVO>(connection) {

					@Override
					ListFriendVO parseResult(JSONObject result)
							throws ProcessException, Exception {
						ListFriendVO vo = new ListFriendVO();
						vo.setRetCode(result.isNull("retCode") ? null : result
								.optInt("retCode"));
						vo.setError(result.isNull("errors") ? null : result
								.optString("errors"));
						JSONArray datas = result.optJSONArray("data");
						if (isNotNull(datas)) {
							List<FriendVO> listAct = new ArrayList<FriendVO>();
							for (int index = 0; index < datas.length(); index++) {
								JSONObject data = datas.optJSONObject(index);
								FriendVO fvo = new FriendVO();
								fvo.setPhoneNo(data.isNull("phoneNo") ? null
										: data.optString("phoneNo"));
								fvo.setUserId(data.isNull("userId") ? null
										: data.optString("userId"));
								fvo.setUserName(data.isNull("userName") ? null
										: data.optString("userName"));
								fvo.setHeadPic(data.isNull("headPic") ? null
										: data.optString("headPic"));
								fvo.setStatus(data.isNull("status") ? null
										: data.optString("status"));
								listAct.add(fvo);
							}
							vo.setDataList(listAct);
						}
						JSONObject paginator = result
								.optJSONObject("paginator");
						if (isNotNull(paginator)) {
							vo.setLimit(paginator.isNull("limit") ? null
									: paginator.optInt("limit"));
							vo.setPage(paginator.isNull("page") ? null
									: paginator.optInt("page"));
							vo.setNextPage(paginator.isNull("nextPage") ? null
									: paginator.optInt("nextPage"));
							vo.setHasNextPage(paginator.isNull("hasNextPage") ? false
									: paginator.optBoolean("hasNextPage"));
						}

						return vo;
					}

					@Override
					String logTag() {
						return TAG;
					}
				}, (IFailureListener) connection);
	}

	@Override
	public void activity_list(Map<String, String> input,
			IConnectionNetworkAble<ListActivityVO> connection) {
		// TODO Auto-generated method stub
		post(Contacts.ServiceURL.ACTIVITY_LIST, input,
				new CallbackListener<ListActivityVO>(connection) {

					@Override
					ListActivityVO parseResult(JSONObject result)
							throws ProcessException, Exception {
						ListActivityVO vo = new ListActivityVO();
						vo.setRetCode(result.isNull("retCode") ? null : result
								.optInt("retCode"));
						vo.setError(result.isNull("errors") ? null : result
								.optString("errors"));
						JSONArray datas = result.optJSONArray("data");
						if (isNotNull(datas)) {
							List<ActivityVO> listAct = new ArrayList<ActivityVO>();
							for (int index = 0; index < datas.length(); index++) {
								JSONObject data = datas.optJSONObject(index);
								ActivityVO actvo = new ActivityVO();
								actvo.setActivityId(data.isNull("activityId") ? null
										: data.optString("activityId"));
								actvo.setTitle(data.isNull("title") ? null
										: data.optString("title"));
								actvo.setActivityUrl(data.isNull("activityUrl") ? null
										: data.optString("activityUrl"));
								actvo.setPicUrl(data.isNull("picUrl") ? null
										: data.optString("picUrl"));
								actvo.setBeginTime(data.isNull("beginTime") ? null
										: data.optLong("beginTime"));
								actvo.setEndTime(data.isNull("endTime") ? null
										: data.optLong("endTime"));
								actvo.setAddress(data.isNull("address") ? null
										: data.optString("address"));
								actvo.setContent(data.isNull("content") ? null
										: data.optString("content"));
								actvo.setIsOfficial(data.isNull("official") ? false
										: "Y".equals(data.optString("official")) ? true
												: false);
								actvo.setPlaceNum(data.isNull("placesNum") ? null
										: data.optInt("placesNum"));
								actvo.setAdviceTel(data.isNull("adviceTel") ? null
										: data.optString("adviceTel"));
								actvo.setApplyBeginTime(data
										.isNull("applyBeginTime") ? null : data
										.optLong("applyBeginTime"));
								actvo.setApplyEndTime(data
										.isNull("applyEndTime") ? null : data
										.optLong("applyEndTime"));
								actvo.setApplyNum(data.isNull("applyNum") ? null
										: data.optInt("applyNum"));
								actvo.setCollectionNum(data
										.isNull("collectionNum") ? null : data
										.optInt("collectionNum"));
								actvo.setStatus(data.isNull("status") ? null
										: data.optString("status"));
								actvo.setHasApply(data.isNull("hasApply") ? false
										: data.optInt("hasApply") > 0 ? true
												: false);
								actvo.setHasCollection(data
										.isNull("hasCollection") ? false : data
										.optInt("hasCollection") > 0 ? true
										: false);
								listAct.add(actvo);
							}
							vo.setDataList(listAct);
						}
						JSONObject paginator = result
								.optJSONObject("paginator");
						if (isNotNull(paginator)) {
							vo.setLimit(paginator.isNull("limit") ? null
									: paginator.optInt("limit"));
							vo.setPage(paginator.isNull("page") ? null
									: paginator.optInt("page"));
							vo.setNextPage(paginator.isNull("nextPage") ? null
									: paginator.optInt("nextPage"));
							vo.setHasNextPage(paginator.isNull("hasNextPage") ? false
									: paginator.optBoolean("hasNextPage"));
						}
						return vo;
					}

					@Override
					String logTag() {
						return TAG;
					}
				}, (IFailureListener) connection);
	}

	@Override
	public void my_activity_apply_list(Map<String, String> input,
			IConnectionNetworkAble<ListActivityVO> connection) {
		// TODO Auto-generated method stub
		post(Contacts.ServiceURL.MY_ACTIVITY_APPLY, input,
				new CallbackListener<ListActivityVO>(connection) {

					@Override
					ListActivityVO parseResult(JSONObject result)
							throws ProcessException, Exception {
						ListActivityVO vo = new ListActivityVO();
						vo.setRetCode(result.isNull("retCode") ? null : result
								.optInt("retCode"));
						vo.setError(result.isNull("errors") ? null : result
								.optString("errors"));
						JSONArray datas = result.optJSONArray("data");
						if (isNotNull(datas)) {
							List<ActivityVO> listAct = new ArrayList<ActivityVO>();
							for (int index = 0; index < datas.length(); index++) {
								JSONObject data = datas.optJSONObject(index);
								ActivityVO actvo = new ActivityVO();
								actvo.setActivityId(data.isNull("activityId") ? null
										: data.optString("activityId"));
								actvo.setActivityUrl(data.isNull("activityUrl") ? null
										: data.optString("activityUrl"));
								actvo.setTitle(data.isNull("title") ? null
										: data.optString("title"));
								actvo.setPicUrl(data.isNull("picUrl") ? null
										: data.optString("picUrl"));
								actvo.setBeginTime(data.isNull("beginTime") ? null
										: data.optLong("beginTime"));
								actvo.setEndTime(data.isNull("endTime") ? null
										: data.optLong("endTime"));
								actvo.setAddress(data.isNull("address") ? null
										: data.optString("address"));
								actvo.setContent(data.isNull("content") ? null
										: data.optString("content"));
								actvo.setIsOfficial(data.isNull("official") ? false
										: data.optBoolean("official"));
								actvo.setPlaceNum(data.isNull("placesNum") ? null
										: data.optInt("placesNum"));
								actvo.setAdviceTel(data.isNull("adviceTel") ? null
										: data.optString("adviceTel"));
								actvo.setApplyBeginTime(data
										.isNull("applyBeginTime") ? null : data
										.optLong("applyBeginTime"));
								actvo.setApplyEndTime(data
										.isNull("applyEndTime") ? null : data
										.optLong("applyEndTime"));
								actvo.setApplyNum(data.isNull("applyNum") ? null
										: data.optInt("applyNum"));
								actvo.setCollectionNum(data
										.isNull("collectionNum") ? null : data
										.optInt("collectionNum"));
								actvo.setStatus(data.isNull("status") ? null
										: data.optString("status"));
								actvo.setHasApply(data.isNull("hasApply") ? false
										: data.optInt("hasApply") > 0 ? true
												: false);
								actvo.setHasCollection(data
										.isNull("hasCollection") ? false : data
										.optInt("hasCollection") > 0 ? true
										: false);
								listAct.add(actvo);
							}
							vo.setDataList(listAct);

						}
						JSONObject paginator = result
								.optJSONObject("paginator");
						if (isNotNull(paginator)) {
							vo.setLimit(paginator.isNull("limit") ? null
									: paginator.optInt("limit"));
							vo.setPage(paginator.isNull("page") ? null
									: paginator.optInt("page"));
							vo.setNextPage(paginator.isNull("nextPage") ? null
									: paginator.optInt("nextPage"));
							vo.setHasNextPage(paginator.isNull("hasNextPage") ? false
									: paginator.optBoolean("hasNextPage"));
						}

						return vo;
					}

					@Override
					String logTag() {
						return TAG;
					}
				}, (IFailureListener) connection);
	}

	@Override
	public void my_activity_collection_list(Map<String, String> input,
			IConnectionNetworkAble<ListActivityVO> connection) {
		// TODO Auto-generated method stub
		post(Contacts.ServiceURL.MY_ACTIVITY_COLLECTION, input,
				new CallbackListener<ListActivityVO>(connection) {

					@Override
					ListActivityVO parseResult(JSONObject result)
							throws ProcessException, Exception {
						ListActivityVO vo = new ListActivityVO();
						vo.setRetCode(result.isNull("retCode") ? null : result
								.optInt("retCode"));
						vo.setError(result.isNull("errors") ? null : result
								.optString("errors"));
						JSONArray datas = result.optJSONArray("data");
						if (isNotNull(datas)) {
							List<ActivityVO> listAct = new ArrayList<ActivityVO>();
							for (int index = 0; index < datas.length(); index++) {
								JSONObject data = datas.optJSONObject(index);
								ActivityVO actvo = new ActivityVO();
								actvo.setActivityId(data.isNull("activityId") ? null
										: data.optString("activityId"));
								actvo.setActivityUrl(data.isNull("activityUrl") ? null
										: data.optString("activityUrl"));
								actvo.setTitle(data.isNull("title") ? null
										: data.optString("title"));
								actvo.setPicUrl(data.isNull("picUrl") ? null
										: data.optString("picUrl"));
								actvo.setBeginTime(data.isNull("beginTime") ? null
										: data.optLong("beginTime"));
								actvo.setEndTime(data.isNull("endTime") ? null
										: data.optLong("endTime"));
								actvo.setAddress(data.isNull("address") ? null
										: data.optString("address"));
								actvo.setContent(data.isNull("content") ? null
										: data.optString("content"));
								actvo.setIsOfficial(data.isNull("official") ? false
										: data.optBoolean("official"));
								actvo.setPlaceNum(data.isNull("placesNum") ? null
										: data.optInt("placesNum"));
								actvo.setAdviceTel(data.isNull("adviceTel") ? null
										: data.optString("adviceTel"));
								actvo.setApplyBeginTime(data
										.isNull("applyBeginTime") ? null : data
										.optLong("applyBeginTime"));
								actvo.setApplyEndTime(data
										.isNull("applyEndTime") ? null : data
										.optLong("applyEndTime"));
								actvo.setApplyNum(data.isNull("applyNum") ? null
										: data.optInt("applyNum"));
								actvo.setCollectionNum(data
										.isNull("collectionNum") ? null : data
										.optInt("collectionNum"));
								actvo.setStatus(data.isNull("status") ? null
										: data.optString("status"));
								actvo.setHasApply(data.isNull("hasApply") ? false
										: data.optInt("hasApply") > 0 ? true
												: false);
								actvo.setHasCollection(data
										.isNull("hasCollection") ? false : data
										.optInt("hasCollection") > 0 ? true
										: false);
								listAct.add(actvo);
							}
							vo.setDataList(listAct);
						}
						JSONObject paginator = result
								.optJSONObject("paginator");
						if (isNotNull(paginator)) {
							vo.setLimit(paginator.isNull("limit") ? null
									: paginator.optInt("limit"));
							vo.setPage(paginator.isNull("page") ? null
									: paginator.optInt("page"));
							vo.setNextPage(paginator.isNull("nextPage") ? null
									: paginator.optInt("nextPage"));
							vo.setHasNextPage(paginator.isNull("hasNextPage") ? false
									: paginator.optBoolean("hasNextPage"));
						}

						return vo;
					}

					@Override
					String logTag() {
						return TAG;
					}
				}, (IFailureListener) connection);
	}

	@Override
	public void activity_apply(Map<String, String> input,
			IConnectionNetworkAble<DefaultVO> connection) {
		// TODO Auto-generated method stub
		post(Contacts.ServiceURL.ACTIVITY_APPLY, input,
				new CallbackListener<DefaultVO>(connection) {

					@Override
					DefaultVO parseResult(JSONObject result)
							throws ProcessException, Exception {
						DefaultVO vo = new DefaultVO();
						vo.setRetCode(result.isNull("retCode") ? null : result
								.optInt("retCode"));
						vo.setError(result.isNull("errors") ? null : result
								.optString("errors"));
						return vo;
					}

					@Override
					String logTag() {
						return TAG;
					}
				}, (IFailureListener) connection);
	}

	@Override
	public void activity_collection(Map<String, String> input,
			IConnectionNetworkAble<DefaultVO> connection) {
		// TODO Auto-generated method stub
		post(Contacts.ServiceURL.ACTIVITY_COLLECTION, input,
				new CallbackListener<DefaultVO>(connection) {

					@Override
					DefaultVO parseResult(JSONObject result)
							throws ProcessException, Exception {
						DefaultVO vo = new DefaultVO();
						vo.setRetCode(result.isNull("retCode") ? null : result
								.optInt("retCode"));
						vo.setError(result.isNull("errors") ? null : result
								.optString("errors"));
						return vo;
					}

					@Override
					String logTag() {
						return TAG;
					}
				}, (IFailureListener) connection);
	}

	@Override
	public void my_detail(Map<String, String> input,
			IConnectionNetworkAble<PersonInfoVO> connection) {
		// TODO Auto-generated method stub
		post(Contacts.ServiceURL.PERSION_INFO, input,
				new CallbackListener<PersonInfoVO>(connection) {

					@Override
					PersonInfoVO parseResult(JSONObject result)
							throws ProcessException, Exception {
						PersonInfoVO vo = new PersonInfoVO();
						vo.setRetCode(result.isNull("retCode") ? null : result
								.optInt("retCode"));
						vo.setError(result.isNull("errors") ? null : result
								.optString("errors"));
						vo.setUserId(result.isNull("userId") ? null : result
								.optString("userId"));
						vo.setUserName(result.isNull("userName") ? null
								: result.optString("userName"));
						vo.setUsertypeName(result.isNull("usertypeName") ? null
								: result.optString("usertypeName"));
						vo.setBrandName(result.isNull("brandName") ? null
								: result.optString("brandName"));
						vo.setMallName(result.isNull("mallName") ? null
								: result.optString("mallName"));
						vo.setCompanyDesc(result.isNull("companyDesc") ? null
								: result.optString("companyDesc"));
						vo.setEmail(result.isNull("email") ? null : result
								.optString("email"));
						vo.setHeadPic(result.isNull("headPic") ? null : result
								.optString("headPic"));
						vo.setCreateTime(result.isNull("createTime") ? null
								: result.optLong("createTime"));
						vo.setScore(result.isNull("score") ? null : result
								.optString("score"));
						vo.setStatus(result.isNull("status") ? null : result
								.optString("status"));
						return vo;
					}

					@Override
					String logTag() {
						return TAG;
					}
				}, (IFailureListener) connection);
	}

	@Override
	public void comment_list(Map<String, String> input,
			IConnectionNetworkAble<ListOtherVO> connection) {
		// TODO Auto-generated method stub
		post(Contacts.ServiceURL.COMMENT_LIST, input,
				new CallbackListener<ListOtherVO>(connection) {

					@Override
					ListOtherVO parseResult(JSONObject result)
							throws ProcessException, Exception {
						ListOtherVO listvo = new ListOtherVO();
						if (isNotNull(result)) {
							listvo.setRetCode(result.isNull("retCode") ? null
									: result.optInt("retCode"));
							listvo.setError(result.isNull("errors") ? null
									: result.optString("errors"));
							JSONArray datas = result.optJSONArray("data");
							if (isNotNull(datas)) {
								List<OtherDataVO> list = new ArrayList<OtherDataVO>();
								for (int i = 0; i < datas.length(); i++) {
									JSONObject data = datas.optJSONObject(i);
									if (isNotNull(data)) {
										OtherDataVO vo = new OtherDataVO();
										vo.setArticleFrom(data
												.isNull("articleFrom") ? null
												: data.optString("articleFrom"));
										vo.setUserId(data.isNull("userId") ? null
												: data.optString("userId"));
										vo.setContent(data.isNull("content") ? null
												: data.optString("content"));
										vo.setHeadPic(data.isNull("headPic") ? null
												: data.optString("headPic"));
										vo.setUserName(data.isNull("userName") ? null
												: data.optString("userName"));
										vo.setStatus(data.isNull("status") ? null
												: data.optString("status"));
										vo.setArticleId(data
												.isNull("articleId") ? null
												: data.optString("articleId"));
										vo.setCommentId(data
												.isNull("commentId") ? null
												: data.optString("commentId"));
										vo.setTitle(data.isNull("title") ? null
												: data.optString("title"));
										vo.setCreateTime(data
												.isNull("createTime") ? null
												: data.optLong("createTime"));
										vo.setPicUrl(data.isNull("picUrl") ? null
												: data.optString("picUrl"));
										vo.setCommentNum(data
												.isNull("commentNum") ? null
												: data.optInt("commentNum"));
										vo.setCollectionNum(data
												.isNull("collectionNum") ? null
												: data.optInt("collectionNum"));
										vo.setPraiseNum(data
												.isNull("praiseNum") ? null
												: data.optInt("praiseNum"));
										vo.setHasCollection(data
												.isNull("hasCollection") ? false
												: data.optInt("hasCollection") > 0 ? true
														: false);
										vo.setHasPraise(data
												.isNull("hasPraise") ? false
												: data.optInt("hasPraise") > 0 ? true
														: false);
										list.add(vo);
									}
								}

								listvo.setDataList(list);
							}
							JSONObject paginator = result
									.optJSONObject("paginator");
							if (isNotNull(paginator)) {
								listvo.setLimit(paginator.isNull("limit") ? null
										: paginator.optInt("limit"));
								listvo.setTotalCount(paginator
										.isNull("totalCount") ? null
										: paginator.optInt("totalCount"));
								listvo.setPage(paginator.isNull("page") ? null
										: paginator.optInt("page"));
								listvo.setNextPage(paginator.isNull("nextPage") ? null
										: paginator.optInt("nextPage"));
								listvo.setHasNextPage(paginator
										.isNull("hasNextPage") ? false
										: paginator.optBoolean("hasNextPage"));
							}
						}

						return listvo;
					}

					@Override
					String logTag() {
						return TAG;
					}
				}, (IFailureListener) connection);
	}

	@Override
	public void square_list(Map<String, String> input,
			IConnectionNetworkAble<ListSquareVO> connection) {
		// TODO Auto-generated method stub
		post(Contacts.ServiceURL.SQUARE_LIST, input,
				new CallbackListener<ListSquareVO>(connection) {

					@Override
					ListSquareVO parseResult(JSONObject result)
							throws ProcessException, Exception {
						ListSquareVO listvo = new ListSquareVO();
						if (isNotNull(result)) {
							listvo.setRetCode(result.isNull("retCode") ? null
									: result.optInt("retCode"));
							listvo.setError(result.isNull("errors") ? null
									: result.optString("errors"));
							JSONArray datas = result.optJSONArray("data");
							if (isNotNull(datas)) {
								List<SquareVO> list = new ArrayList<SquareVO>();
								for (int i = 0; i < datas.length(); i++) {
									JSONObject data = datas.optJSONObject(i);
									if (isNotNull(data)) {
										SquareVO vo = new SquareVO();
										vo.setSpeakId(data.isNull("speakId") ? null
												: data.optString("speakId"));
										vo.setJobName(data.isNull("jobName") ? null
												: data.optString("jobName"));
										vo.setForwardUserName(data
												.isNull("forwardUserName") ? null
												: data.optString("forwardUserName"));
										vo.setUsertypeName(data
												.isNull("usertypeName") ? null
												: data.optString("usertypeName"));
										vo.setCityName(data.isNull("cityName") ? null
												: data.optString("cityName"));
										vo.setBrandName(data
												.isNull("brandName") ? null
												: data.optString("brandName"));
										vo.setMallName(data.isNull("mallName") ? null
												: data.optString("mallName"));
										vo.setIsAnonymous(data
												.isNull("isAnonymous") ? false
												: data.optInt("isAnonymous") > 0 ? true
														: false);
										vo.setIsForward(data
												.isNull("isForward") ? false
												: data.optInt("isForward") > 0 ? true
														: false);
										vo.setForwardSpeakId(data
												.isNull("forwardSpeakId") ? null
												: data.optString("forwardSpeakId"));
										vo.setUserId(data.isNull("userId") ? null
												: data.optString("userId"));
										vo.setContent(data.isNull("content") ? null
												: data.optString("content"));
										vo.setHeadPic(data.isNull("headPic") ? null
												: data.optString("headPic"));
										vo.setUsername(data.isNull("userName") ? null
												: data.optString("userName"));
										vo.setTitle(data.isNull("title") ? null
												: data.optString("title"));
										vo.setCreateTime(data
												.isNull("createTime") ? null
												: data.optLong("createTime"));
										vo.setPicUrl(data.isNull("picUrl") ? null
												: data.optString("picUrl"));
										vo.setCommentNum(data
												.isNull("commentNum") ? null
												: data.optInt("commentNum"));
										vo.setForwardNum(data
												.isNull("forwardNum") ? null
												: data.optInt("forwardNum"));
										vo.setPraiseNum(data
												.isNull("praiseNum") ? null
												: data.optInt("praiseNum"));
										vo.setHasForward(data
												.isNull("hasForward") ? false
												: data.optInt("hasForward") > 0 ? true
														: false);
										vo.setHasPraise(data
												.isNull("hasPraise") ? false
												: data.optInt("hasPraise") > 0 ? true
														: false);
										vo.setHasComment(data
												.isNull("hasComment") ? false
												: data.optInt("hasComment") > 0 ? true
														: false);
										list.add(vo);
									}
								}

								listvo.setDataList(list);
							}
							JSONObject paginator = result
									.optJSONObject("paginator");
							if (isNotNull(paginator)) {
								listvo.setTotalCount(paginator
										.isNull("totalCount") ? null
										: paginator.optInt("totalCount"));
								listvo.setLimit(paginator.isNull("limit") ? null
										: paginator.optInt("limit"));
								listvo.setPage(paginator.isNull("page") ? null
										: paginator.optInt("page"));
								listvo.setNextPage(paginator.isNull("nextPage") ? null
										: paginator.optInt("nextPage"));
								listvo.setHasNextPage(paginator
										.isNull("hasNextPage") ? false
										: paginator.optBoolean("hasNextPage"));
							}
						}

						return listvo;
					}

					@Override
					String logTag() {
						return TAG;
					}
				}, (IFailureListener) connection);
	}

	@Override
	public void question_list(Map<String, String> input,
			IConnectionNetworkAble<ListQuestionVO> connection) {
		// TODO Auto-generated method stub
		post(Contacts.ServiceURL.QUESTION_LIST, input,
				new CallbackListener<ListQuestionVO>(connection) {

					@Override
					ListQuestionVO parseResult(JSONObject result)
							throws ProcessException, Exception {
						ListQuestionVO listvo = new ListQuestionVO();
						if (isNotNull(result)) {
							listvo.setRetCode(result.isNull("retCode") ? null
									: result.optInt("retCode"));
							listvo.setError(result.isNull("errors") ? null
									: result.optString("errors"));
							JSONArray datas = result.optJSONArray("data");
							if (isNotNull(datas)) {
								List<QuestionVO> list = new ArrayList<QuestionVO>();
								for (int i = 0; i < datas.length(); i++) {
									JSONObject data = datas.optJSONObject(i);
									if (isNotNull(data)) {
										QuestionVO vo = new QuestionVO();
										vo.setAskId(data.isNull("askId") ? null
												: data.optString("askId"));
										vo.setIndustryId(data
												.isNull("industryId") ? null
												: data.optString("industryId"));
										vo.setCommentContent(data
												.isNull("commentContent") ? null
												: data.optString("commentContent"));
										vo.setContentUserName(data
												.isNull("contentUserName") ? null
												: data.optString("contentUserName"));

										vo.setCommentCreateTime(data
												.isNull("commentCreateTime") ? null
												: data.optLong("commentCreateTime"));
										vo.setUserId(data.isNull("userId") ? null
												: data.optString("userId"));
										vo.setContent(data.isNull("content") ? null
												: data.optString("content"));
										vo.setHeadPic(data.isNull("headPic") ? null
												: data.optString("headPic"));
										vo.setUsername(data.isNull("userName") ? null
												: data.optString("userName"));
										vo.setTitle(data.isNull("title") ? null
												: data.optString("title"));
										vo.setCreateTime(data
												.isNull("createTime") ? null
												: data.optLong("createTime"));
										vo.setPicUrl(data.isNull("picUrl") ? null
												: data.optString("picUrl"));
										vo.setCommentNum(data
												.isNull("commentNum") ? null
												: data.optInt("commentNum"));
										vo.setCollectionNum(data
												.isNull("collectionNum") ? null
												: data.optInt("collectionNum"));
										vo.setPraiseNum(data
												.isNull("praiseNum") ? null
												: data.optInt("praiseNum"));
										vo.setHasCollection(data
												.isNull("hasCollection") ? false
												: data.optInt("hasCollection") > 0 ? true
														: false);
										vo.setHasPraise(data
												.isNull("hasPraise") ? false
												: data.optInt("hasPraise") > 0 ? true
														: false);
										list.add(vo);
									}
								}

								listvo.setDataList(list);
							}
							JSONObject paginator = result
									.optJSONObject("paginator");
							if (isNotNull(paginator)) {
								listvo.setTotalCount(paginator
										.isNull("totalCount") ? null
										: paginator.optInt("totalCount"));
								listvo.setLimit(paginator.isNull("limit") ? null
										: paginator.optInt("limit"));
								listvo.setPage(paginator.isNull("page") ? null
										: paginator.optInt("page"));
								listvo.setNextPage(paginator.isNull("nextPage") ? null
										: paginator.optInt("nextPage"));
								listvo.setHasNextPage(paginator
										.isNull("hasNextPage") ? false
										: paginator.optBoolean("hasNextPage"));
							}
						}

						return listvo;
					}

					@Override
					String logTag() {
						return TAG;
					}
				}, (IFailureListener) connection);
	}

	@Override
	public void square_speak(Map<String, Object> inputFils,
			Map<String, String> input,
			IConnectionNetworkAble<DefaultVO> connection) {
		// TODO Auto-generated method stub
		post(Contacts.ServiceURL.SQUARE_SPEAK, inputFils, input,
				new CallbackListener<DefaultVO>(connection) {

					@Override
					DefaultVO parseResult(JSONObject result)
							throws ProcessException, Exception {
						DefaultVO vo = new DefaultVO();
						vo.setRetCode(result.isNull("retCode") ? null : result
								.optInt("retCode"));
						vo.setError(result.isNull("errors") ? null : result
								.optString("errors"));
						return vo;
					}

					@Override
					String logTag() {
						return TAG;
					}
				}, (IFailureListener) connection);
	}

	@Override
	public void square_forward(Map<String, String> input,
			IConnectionNetworkAble<DefaultVO> connection) {
		// TODO Auto-generated method stub
		post(Contacts.ServiceURL.SQUARE_FORWARD, input,
				new CallbackListener<DefaultVO>(connection) {

					@Override
					DefaultVO parseResult(JSONObject result)
							throws ProcessException, Exception {
						DefaultVO vo = new DefaultVO();
						vo.setRetCode(result.isNull("retCode") ? null : result
								.optInt("retCode"));
						vo.setError(result.isNull("errors") ? null : result
								.optString("errors"));
						return vo;
					}

					@Override
					String logTag() {
						return TAG;
					}
				}, (IFailureListener) connection);
	}

	@Override
	public void square_praise(Map<String, String> input,
			IConnectionNetworkAble<DefaultVO> connection) {
		// TODO Auto-generated method stub
		post(Contacts.ServiceURL.SQUARE_PRAISE, input,
				new CallbackListener<DefaultVO>(connection) {

					@Override
					DefaultVO parseResult(JSONObject result)
							throws ProcessException, Exception {
						DefaultVO vo = new DefaultVO();
						vo.setRetCode(result.isNull("retCode") ? null : result
								.optInt("retCode"));
						vo.setError(result.isNull("errors") ? null : result
								.optString("errors"));
						return vo;
					}

					@Override
					String logTag() {
						return TAG;
					}
				}, (IFailureListener) connection);
	}

	@Override
	public void square_comment(Map<String, String> input,
			IConnectionNetworkAble<DefaultVO> connection) {
		// TODO Auto-generated method stub
		post(Contacts.ServiceURL.SQUARE_COMMENT, input,
				new CallbackListener<DefaultVO>(connection) {

					@Override
					DefaultVO parseResult(JSONObject result)
							throws ProcessException, Exception {
						DefaultVO vo = new DefaultVO();
						vo.setRetCode(result.isNull("retCode") ? null : result
								.optInt("retCode"));
						vo.setError(result.isNull("errors") ? null : result
								.optString("errors"));
						return vo;
					}

					@Override
					String logTag() {
						return TAG;
					}
				}, (IFailureListener) connection);
	}

	@Override
	public void square_speak_forward(Map<String, String> input,
			IConnectionNetworkAble<ListSquareVO> connection) {
		// TODO Auto-generated method stub
		post(Contacts.ServiceURL.SQUARE_SPEAK_FORWORD, input,
				new CallbackListener<ListSquareVO>(connection) {

					@Override
					ListSquareVO parseResult(JSONObject result)
							throws ProcessException, Exception {
						ListSquareVO listvo = new ListSquareVO();
						if (isNotNull(result)) {
							listvo.setRetCode(result.isNull("retCode") ? null
									: result.optInt("retCode"));
							listvo.setError(result.isNull("errors") ? null
									: result.optString("errors"));
							JSONArray datas = result.optJSONArray("data");
							if (isNotNull(datas)) {
								List<SquareVO> list = new ArrayList<SquareVO>();
								for (int i = 0; i < datas.length(); i++) {
									JSONObject data = datas.optJSONObject(i);
									if (isNotNull(data)) {
										SquareVO vo = new SquareVO();
										vo.setSpeakId(data.isNull("speakId") ? null
												: data.optString("speakId"));
										vo.setJobName(data.isNull("jobName") ? null
												: data.optString("jobName"));
										vo.setIsAnonymous(data
												.isNull("isAnonymous") ? false
												: data.optInt("isAnonymous") > 0 ? true
														: false);
										vo.setIsForward(data
												.isNull("isForward") ? false
												: data.optInt("isForward") > 0 ? true
														: false);
										vo.setForwardSpeakId(data
												.isNull("forwardSpeakId") ? null
												: data.optString("forwardSpeakId"));
										vo.setUserId(data.isNull("userId") ? null
												: data.optString("userId"));
										vo.setCommentId(data
												.isNull("commentId") ? null
												: data.optString("commentId"));
										vo.setContent(data.isNull("content") ? null
												: data.optString("content"));
										vo.setHeadPic(data.isNull("headPic") ? null
												: data.optString("headPic"));
										vo.setUsername(data.isNull("userName") ? null
												: data.optString("userName"));
										vo.setTitle(data.isNull("title") ? null
												: data.optString("title"));
										vo.setCreateTime(data
												.isNull("createTime") ? null
												: data.optLong("createTime"));
										vo.setPicUrl(data.isNull("picUrl") ? null
												: data.optString("picUrl"));
										vo.setCommentNum(data
												.isNull("commentNum") ? null
												: data.optInt("commentNum"));
										vo.setForwardNum(data
												.isNull("forwardNum") ? null
												: data.optInt("forwardNum"));
										vo.setPraiseNum(data
												.isNull("praiseNum") ? null
												: data.optInt("praiseNum"));
										vo.setHasForward(data
												.isNull("hasForward") ? false
												: data.optInt("hasForward") > 0 ? true
														: false);
										vo.setHasPraise(data
												.isNull("hasPraise") ? false
												: data.optInt("hasPraise") > 0 ? true
														: false);
										vo.setHasComment(data
												.isNull("hasComment") ? false
												: data.optInt("hasComment") > 0 ? true
														: false);
										list.add(vo);
									}
								}

								listvo.setDataList(list);
							}
							JSONObject paginator = result
									.optJSONObject("paginator");
							if (isNotNull(paginator)) {
								listvo.setTotalCount(paginator
										.isNull("totalCount") ? null
										: paginator.optInt("totalCount"));
								listvo.setLimit(paginator.isNull("limit") ? null
										: paginator.optInt("limit"));
								listvo.setPage(paginator.isNull("page") ? null
										: paginator.optInt("page"));
								listvo.setNextPage(paginator.isNull("nextPage") ? null
										: paginator.optInt("nextPage"));
								listvo.setHasNextPage(paginator
										.isNull("hasNextPage") ? false
										: paginator.optBoolean("hasNextPage"));
							}
						}

						return listvo;
					}

					@Override
					String logTag() {
						return TAG;
					}
				}, (IFailureListener) connection);

	}

	@Override
	public void square_speak_comment(Map<String, String> input,
			IConnectionNetworkAble<ListSquareVO> connection) {
		// TODO Auto-generated method stub
		post(Contacts.ServiceURL.SQUARE_SPEAK_COMMENT, input,
				new CallbackListener<ListSquareVO>(connection) {

					@Override
					ListSquareVO parseResult(JSONObject result)
							throws ProcessException, Exception {
						ListSquareVO listvo = new ListSquareVO();
						if (isNotNull(result)) {
							listvo.setRetCode(result.isNull("retCode") ? null
									: result.optInt("retCode"));
							listvo.setError(result.isNull("errors") ? null
									: result.optString("errors"));
							JSONArray datas = result.optJSONArray("data");
							if (isNotNull(datas)) {
								List<SquareVO> list = new ArrayList<SquareVO>();
								for (int i = 0; i < datas.length(); i++) {
									JSONObject data = datas.optJSONObject(i);
									if (isNotNull(data)) {
										SquareVO vo = new SquareVO();
										vo.setSpeakId(data.isNull("speakId") ? null
												: data.optString("speakId"));
										vo.setJobName(data.isNull("jobName") ? null
												: data.optString("jobName"));
										vo.setCommentId(data
												.isNull("commentId") ? null
												: data.optString("commentId"));
										vo.setIsAnonymous(data
												.isNull("isAnonymous") ? false
												: data.optInt("isAnonymous") > 0 ? true
														: false);
										vo.setIsForward(data
												.isNull("isForward") ? false
												: data.optInt("isForward") > 0 ? true
														: false);
										vo.setForwardSpeakId(data
												.isNull("forwardSpeakId") ? null
												: data.optString("forwardSpeakId"));
										vo.setUserId(data.isNull("userId") ? null
												: data.optString("userId"));
										vo.setContent(data.isNull("content") ? null
												: data.optString("content"));
										vo.setHeadPic(data.isNull("headPic") ? null
												: data.optString("headPic"));
										vo.setUsername(data.isNull("userName") ? null
												: data.optString("userName"));
										vo.setTitle(data.isNull("title") ? null
												: data.optString("title"));
										vo.setCreateTime(data
												.isNull("createTime") ? null
												: data.optLong("createTime"));
										vo.setPicUrl(data.isNull("picUrl") ? null
												: data.optString("picUrl"));
										vo.setCommentNum(data
												.isNull("commentNum") ? null
												: data.optInt("commentNum"));
										vo.setForwardNum(data
												.isNull("forwardNum") ? null
												: data.optInt("forwardNum"));
										vo.setPraiseNum(data
												.isNull("praiseNum") ? null
												: data.optInt("praiseNum"));
										vo.setHasForward(data
												.isNull("hasForward") ? false
												: data.optInt("hasForward") > 0 ? true
														: false);
										vo.setHasPraise(data
												.isNull("hasPraise") ? false
												: data.optInt("hasPraise") > 0 ? true
														: false);
										vo.setHasComment(data
												.isNull("hasComment") ? false
												: data.optInt("hasComment") > 0 ? true
														: false);
										list.add(vo);
									}
								}

								listvo.setDataList(list);
							}
							JSONObject paginator = result
									.optJSONObject("paginator");
							if (isNotNull(paginator)) {
								listvo.setTotalCount(paginator
										.isNull("totalCount") ? null
										: paginator.optInt("totalCount"));
								listvo.setLimit(paginator.isNull("limit") ? null
										: paginator.optInt("limit"));
								listvo.setPage(paginator.isNull("page") ? null
										: paginator.optInt("page"));
								listvo.setNextPage(paginator.isNull("nextPage") ? null
										: paginator.optInt("nextPage"));
								listvo.setHasNextPage(paginator
										.isNull("hasNextPage") ? false
										: paginator.optBoolean("hasNextPage"));
							}
						}

						return listvo;
					}

					@Override
					String logTag() {
						return TAG;
					}
				}, (IFailureListener) connection);
	}

	@Override
	public void square_speak_praise(Map<String, String> input,
			IConnectionNetworkAble<ListSquareVO> connection) {
		// TODO Auto-generated method stub
		post(Contacts.ServiceURL.SQUARE_SPEAK_PRAISE, input,
				new CallbackListener<ListSquareVO>(connection) {

					@Override
					ListSquareVO parseResult(JSONObject result)
							throws ProcessException, Exception {
						ListSquareVO listvo = new ListSquareVO();
						if (isNotNull(result)) {
							listvo.setRetCode(result.isNull("retCode") ? null
									: result.optInt("retCode"));
							listvo.setError(result.isNull("errors") ? null
									: result.optString("errors"));
							JSONArray datas = result.optJSONArray("data");
							if (isNotNull(datas)) {
								List<SquareVO> list = new ArrayList<SquareVO>();
								for (int i = 0; i < datas.length(); i++) {
									JSONObject data = datas.optJSONObject(i);
									if (isNotNull(data)) {
										SquareVO vo = new SquareVO();
										vo.setSpeakId(data.isNull("speakId") ? null
												: data.optString("speakId"));
										vo.setJobName(data.isNull("jobName") ? null
												: data.optString("jobName"));
										vo.setCommentId(data
												.isNull("commentId") ? null
												: data.optString("commentId"));
										vo.setIsAnonymous(data
												.isNull("isAnonymous") ? false
												: data.optInt("isAnonymous") > 0 ? true
														: false);
										vo.setIsForward(data
												.isNull("isForward") ? false
												: data.optInt("isForward") > 0 ? true
														: false);
										vo.setForwardSpeakId(data
												.isNull("forwardSpeakId") ? null
												: data.optString("forwardSpeakId"));
										vo.setUserId(data.isNull("userId") ? null
												: data.optString("userId"));
										vo.setContent(data.isNull("content") ? null
												: data.optString("content"));
										vo.setHeadPic(data.isNull("headPic") ? null
												: data.optString("headPic"));
										vo.setUsername(data.isNull("userName") ? null
												: data.optString("userName"));
										vo.setTitle(data.isNull("title") ? null
												: data.optString("title"));
										vo.setCreateTime(data
												.isNull("createTime") ? null
												: data.optLong("createTime"));
										vo.setPicUrl(data.isNull("picUrl") ? null
												: data.optString("picUrl"));
										vo.setCommentNum(data
												.isNull("commentNum") ? null
												: data.optInt("commentNum"));
										vo.setForwardNum(data
												.isNull("forwardNum") ? null
												: data.optInt("forwardNum"));
										vo.setPraiseNum(data
												.isNull("praiseNum") ? null
												: data.optInt("praiseNum"));
										vo.setHasForward(data
												.isNull("hasForward") ? false
												: data.optInt("hasForward") > 0 ? true
														: false);
										vo.setHasPraise(data
												.isNull("hasPraise") ? false
												: data.optInt("hasPraise") > 0 ? true
														: false);
										vo.setHasComment(data
												.isNull("hasComment") ? false
												: data.optInt("hasComment") > 0 ? true
														: false);
										list.add(vo);
									}
								}

								listvo.setDataList(list);
							}
							JSONObject paginator = result
									.optJSONObject("paginator");
							if (isNotNull(paginator)) {
								listvo.setTotalCount(paginator
										.isNull("totalCount") ? null
										: paginator.optInt("totalCount"));
								listvo.setLimit(paginator.isNull("limit") ? null
										: paginator.optInt("limit"));
								listvo.setPage(paginator.isNull("page") ? null
										: paginator.optInt("page"));
								listvo.setNextPage(paginator.isNull("nextPage") ? null
										: paginator.optInt("nextPage"));
								listvo.setHasNextPage(paginator
										.isNull("hasNextPage") ? false
										: paginator.optBoolean("hasNextPage"));
							}
						}

						return listvo;
					}

					@Override
					String logTag() {
						return TAG;
					}
				}, (IFailureListener) connection);
	}

	@Override
	public void square_speak_comment_praise(Map<String, String> input,
			IConnectionNetworkAble<DefaultVO> connection) {
		// TODO Auto-generated method stub
		post(Contacts.ServiceURL.SQUARE_SPEAK_COMMENT_PRAISE, input,
				new CallbackListener<DefaultVO>(connection) {

					@Override
					DefaultVO parseResult(JSONObject result)
							throws ProcessException, Exception {
						DefaultVO vo = new DefaultVO();
						vo.setRetCode(result.isNull("retCode") ? null : result
								.optInt("retCode"));
						vo.setError(result.isNull("errors") ? null : result
								.optString("errors"));
						return vo;
					}

					@Override
					String logTag() {
						return TAG;
					}
				}, (IFailureListener) connection);
	}

	@Override
	public void question_ask(Map<String, Object> inputFils,
			Map<String, String> input,
			IConnectionNetworkAble<DefaultVO> connection) {
		// TODO Auto-generated method stub
		post(Contacts.ServiceURL.QUESTION_ASK, input,
				new CallbackListener<DefaultVO>(connection) {

					@Override
					DefaultVO parseResult(JSONObject result)
							throws ProcessException, Exception {
						DefaultVO vo = new DefaultVO();
						vo.setRetCode(result.isNull("retCode") ? null : result
								.optInt("retCode"));
						vo.setError(result.isNull("errors") ? null : result
								.optString("errors"));
						return vo;
					}

					@Override
					String logTag() {
						return TAG;
					}
				}, (IFailureListener) connection);
	}

	@Override
	public void question_answer_list(Map<String, String> input,
			IConnectionNetworkAble<ListQuestionVO> connection) {
		// TODO Auto-generated method stub
		post(Contacts.ServiceURL.QUESTION_ANSWER_LIST, input,
				new CallbackListener<ListQuestionVO>(connection) {

					@Override
					ListQuestionVO parseResult(JSONObject result)
							throws ProcessException, Exception {
						ListQuestionVO listvo = new ListQuestionVO();
						if (isNotNull(result)) {
							listvo.setRetCode(result.isNull("retCode") ? null
									: result.optInt("retCode"));
							listvo.setError(result.isNull("errors") ? null
									: result.optString("errors"));
							JSONArray datas = result.optJSONArray("data");
							if (isNotNull(datas)) {
								List<QuestionVO> list = new ArrayList<QuestionVO>();
								for (int i = 0; i < datas.length(); i++) {
									JSONObject data = datas.optJSONObject(i);
									if (isNotNull(data)) {
										QuestionVO vo = new QuestionVO();
										vo.setAskId(data.isNull("askId") ? null
												: data.optString("askId"));
										vo.setCommentId(data
												.isNull("commentId") ? null
												: data.optString("commentId"));
										vo.setUserId(data.isNull("userId") ? null
												: data.optString("userId"));
										vo.setContent(data.isNull("content") ? null
												: data.optString("content"));
										vo.setHeadPic(data.isNull("headPic") ? null
												: data.optString("headPic"));
										vo.setUsername(data.isNull("userName") ? null
												: data.optString("userName"));
										vo.setTitle(data.isNull("title") ? null
												: data.optString("title"));
										vo.setCreateTime(data
												.isNull("createTime") ? null
												: data.optLong("createTime"));
										vo.setPicUrl(data.isNull("picUrl") ? null
												: data.optString("picUrl"));
										vo.setCommentNum(data
												.isNull("commentNum") ? null
												: data.optInt("commentNum"));
										vo.setCollectionNum(data
												.isNull("collectionNum") ? null
												: data.optInt("collectionNum"));
										vo.setPraiseNum(data
												.isNull("praiseNum") ? null
												: data.optInt("praiseNum"));
										vo.setHasCollection(data
												.isNull("hasCollection") ? false
												: data.optInt("hasCollection") > 0 ? true
														: false);
										vo.setHasPraise(data
												.isNull("hasPraise") ? false
												: data.optInt("hasPraise") > 0 ? true
														: false);
										list.add(vo);
									}
								}

								listvo.setDataList(list);
							}
							JSONObject paginator = result
									.optJSONObject("paginator");
							if (isNotNull(paginator)) {
								listvo.setTotalCount(paginator
										.isNull("totalCount") ? null
										: paginator.optInt("totalCount"));
								listvo.setLimit(paginator.isNull("limit") ? null
										: paginator.optInt("limit"));
								listvo.setPage(paginator.isNull("page") ? null
										: paginator.optInt("page"));
								listvo.setNextPage(paginator.isNull("nextPage") ? null
										: paginator.optInt("nextPage"));
								listvo.setHasNextPage(paginator
										.isNull("hasNextPage") ? false
										: paginator.optBoolean("hasNextPage"));
							}
						}

						return listvo;
					}

					@Override
					String logTag() {
						return TAG;
					}
				}, (IFailureListener) connection);
	}

	@Override
	public void question_answer_praise(Map<String, String> input,
			IConnectionNetworkAble<DefaultVO> connection) {
		post(Contacts.ServiceURL.QUESTION_ANSWER_PRAISE, input,
				new CallbackListener<DefaultVO>(connection) {

					@Override
					DefaultVO parseResult(JSONObject result)
							throws ProcessException, Exception {
						DefaultVO vo = new DefaultVO();
						vo.setRetCode(result.isNull("retCode") ? null : result
								.optInt("retCode"));
						vo.setError(result.isNull("errors") ? null : result
								.optString("errors"));
						return vo;
					}

					@Override
					String logTag() {
						return TAG;
					}
				}, (IFailureListener) connection);
	}

	@Override
	public void question_answer(Map<String, String> input,
			IConnectionNetworkAble<DefaultVO> connection) {
		// TODO Auto-generated method stub
		post(Contacts.ServiceURL.QUESTION_ANSWER, input,
				new CallbackListener<DefaultVO>(connection) {

					@Override
					DefaultVO parseResult(JSONObject result)
							throws ProcessException, Exception {
						DefaultVO vo = new DefaultVO();
						vo.setRetCode(result.isNull("retCode") ? null : result
								.optInt("retCode"));
						vo.setError(result.isNull("errors") ? null : result
								.optString("errors"));
						return vo;
					}

					@Override
					String logTag() {
						return TAG;
					}
				}, (IFailureListener) connection);
	}

	@Override
	public void question_collection(Map<String, String> input,
			IConnectionNetworkAble<DefaultVO> connection) {
		// TODO Auto-generated method stub
		post(Contacts.ServiceURL.QUESTION_COLLECTION, input,
				new CallbackListener<DefaultVO>(connection) {

					@Override
					DefaultVO parseResult(JSONObject result)
							throws ProcessException, Exception {
						DefaultVO vo = new DefaultVO();
						vo.setRetCode(result.isNull("retCode") ? null : result
								.optInt("retCode"));
						vo.setError(result.isNull("errors") ? null : result
								.optString("errors"));
						return vo;
					}

					@Override
					String logTag() {
						return TAG;
					}
				}, (IFailureListener) connection);
	}

	@Override
	public void question_praise(Map<String, String> input,
			IConnectionNetworkAble<DefaultVO> connection) {
		// TODO Auto-generated method stub
		post(Contacts.ServiceURL.QUESTION_PRAISE, input,
				new CallbackListener<DefaultVO>(connection) {

					@Override
					DefaultVO parseResult(JSONObject result)
							throws ProcessException, Exception {
						DefaultVO vo = new DefaultVO();
						vo.setRetCode(result.isNull("retCode") ? null : result
								.optInt("retCode"));
						vo.setError(result.isNull("errors") ? null : result
								.optString("errors"));
						return vo;
					}

					@Override
					String logTag() {
						return TAG;
					}
				}, (IFailureListener) connection);
	}

	@Override
	public void question_expert(Map<String, String> input,
			IConnectionNetworkAble<ListUserVO> connection) {
		// TODO Auto-generated method stub
		post(Contacts.ServiceURL.QUESTION_EXPERTS, input,
				new CallbackListener<ListUserVO>(connection) {

					@Override
					ListUserVO parseResult(JSONObject result)
							throws ProcessException, Exception {
						ListUserVO listvo = new ListUserVO();
						if (isNotNull(result)) {
							listvo.setRetCode(result.isNull("retCode") ? null
									: result.optInt("retCode"));
							listvo.setError(result.isNull("errors") ? null
									: result.optString("errors"));
							JSONArray datas = result.optJSONArray("data");
							if (isNotNull(datas)) {
								List<UserVO> list = new ArrayList<UserVO>();
								for (int i = 0; i < datas.length(); i++) {
									JSONObject data = datas.optJSONObject(i);
									if (isNotNull(data)) {
										UserVO vo = new UserVO();
										vo.setUserId(data.isNull("userId") ? null
												: data.optString("userId"));
										vo.setHeadPic(data.isNull("headPic") ? null
												: data.optString("headPic"));
										vo.setCreateTime(data
												.isNull("createTime") ? null
												: data.optLong("createTime"));
										vo.setActivated(data
												.isNull("activated") ? null
												: data.optString("activated"));
										vo.setUserAccout(data
												.isNull("userAccout") ? null
												: data.optString("userAccout"));
										vo.setUserName(data.isNull("userName") ? null
												: data.optString("userName"));
										vo.setPassword(data.isNull("password") ? null
												: data.optString("password"));
										vo.setCityId(data.isNull("cityId") ? null
												: data.optString("cityId"));
										// vo.setCityName(data.isNull("cityName")
										// ? null : data
										// .optString("cityName"));
										vo.setCompany(data.isNull("company") ? null
												: data.optString("company"));
										vo.setUsertypeId(data
												.isNull("usertypeId") ? null
												: data.optString("usertypeId"));
										vo.setIndustryId(data
												.isNull("industryId") ? null
												: data.optString("industryId"));
										vo.setBrandName(data
												.isNull("brandName") ? null
												: data.optString("brandName"));
										vo.setMallName(data.isNull("mallName") ? null
												: data.optString("mallName"));
										vo.setJobId(data.isNull("jobId") ? null
												: data.optString("jobId"));
										vo.setCompanyDesc(data
												.isNull("companyDesc") ? null
												: data.optString("companyDesc"));
										vo.setScore(data.isNull("score") ? null
												: data.optString("score"));
										vo.setStatus(data.isNull("status") ? null
												: data.optString("status"));
										list.add(vo);
									}
								}

								listvo.setDataList(list);
							}
							JSONObject paginator = result
									.optJSONObject("paginator");
							if (isNotNull(paginator)) {
								listvo.setTotalCount(paginator
										.isNull("totalCount") ? null
										: paginator.optInt("totalCount"));
								listvo.setLimit(paginator.isNull("limit") ? null
										: paginator.optInt("limit"));
								listvo.setPage(paginator.isNull("page") ? null
										: paginator.optInt("page"));
								listvo.setNextPage(paginator.isNull("nextPage") ? null
										: paginator.optInt("nextPage"));
								listvo.setHasNextPage(paginator
										.isNull("hasNextPage") ? false
										: paginator.optBoolean("hasNextPage"));
							}
						}

						return listvo;
					}

					@Override
					String logTag() {
						return TAG;
					}
				}, (IFailureListener) connection);
	}

	@Override
	public void question_at_expert(Map<String, String> input,
			IConnectionNetworkAble<DefaultVO> connection) {
		// TODO Auto-generated method stub
		post(Contacts.ServiceURL.QUESTION_AT_EXPERTS, input,
				new CallbackListener<DefaultVO>(connection) {

					@Override
					DefaultVO parseResult(JSONObject result)
							throws ProcessException, Exception {
						DefaultVO vo = new DefaultVO();
						vo.setRetCode(result.isNull("retCode") ? null : result
								.optInt("retCode"));
						vo.setError(result.isNull("errors") ? null : result
								.optString("errors"));
						return vo;
					}

					@Override
					String logTag() {
						return TAG;
					}
				}, (IFailureListener) connection);
	}

	@Override
	public void my_question_comment(Map<String, String> input,
			IConnectionNetworkAble<ListQuestionVO> connection) {
		// TODO Auto-generated method stub
		post(Contacts.ServiceURL.MY_QUESTION_COMMENT, input,
				new CallbackListener<ListQuestionVO>(connection) {

					@Override
					ListQuestionVO parseResult(JSONObject result)
							throws ProcessException, Exception {
						ListQuestionVO listvo = new ListQuestionVO();
						if (isNotNull(result)) {
							listvo.setRetCode(result.isNull("retCode") ? null
									: result.optInt("retCode"));
							listvo.setError(result.isNull("errors") ? null
									: result.optString("errors"));
							JSONArray datas = result.optJSONArray("data");
							if (isNotNull(datas)) {
								List<QuestionVO> list = new ArrayList<QuestionVO>();
								for (int i = 0; i < datas.length(); i++) {
									JSONObject data = datas.optJSONObject(i);
									if (isNotNull(data)) {
										QuestionVO vo = new QuestionVO();
										vo.setAskId(data.isNull("askId") ? null
												: data.optString("askId"));
										vo.setCommentContent(data
												.isNull("commentContent") ? null
												: data.optString("commentContent"));
										vo.setContentUserName(data
												.isNull("contentUserName") ? null
												: data.optString("contentUserName"));

										vo.setCommentCreateTime(data
												.isNull("commentCreateTime") ? null
												: data.optLong("commentCreateTime"));
										vo.setUserId(data.isNull("userId") ? null
												: data.optString("userId"));
										vo.setContent(data.isNull("content") ? null
												: data.optString("content"));
										vo.setHeadPic(data.isNull("headPic") ? null
												: data.optString("headPic"));
										vo.setUsername(data.isNull("userName") ? null
												: data.optString("userName"));
										vo.setTitle(data.isNull("title") ? null
												: data.optString("title"));
										vo.setCreateTime(data
												.isNull("createTime") ? null
												: data.optLong("createTime"));
										vo.setPicUrl(data.isNull("picUrl") ? null
												: data.optString("picUrl"));
										vo.setCommentNum(data
												.isNull("commentNum") ? null
												: data.optInt("commentNum"));
										vo.setCollectionNum(data
												.isNull("collectionNum") ? null
												: data.optInt("collectionNum"));
										vo.setPraiseNum(data
												.isNull("praiseNum") ? null
												: data.optInt("praiseNum"));
										vo.setHasCollection(data
												.isNull("hasCollection") ? false
												: data.optInt("hasCollection") > 0 ? true
														: false);
										vo.setHasPraise(data
												.isNull("hasPraise") ? false
												: data.optInt("hasPraise") > 0 ? true
														: false);
										list.add(vo);
									}
								}

								listvo.setDataList(list);
							}
							JSONObject paginator = result
									.optJSONObject("paginator");
							if (isNotNull(paginator)) {
								listvo.setTotalCount(paginator
										.isNull("totalCount") ? null
										: paginator.optInt("totalCount"));
								listvo.setLimit(paginator.isNull("limit") ? null
										: paginator.optInt("limit"));
								listvo.setPage(paginator.isNull("page") ? null
										: paginator.optInt("page"));
								listvo.setNextPage(paginator.isNull("nextPage") ? null
										: paginator.optInt("nextPage"));
								listvo.setHasNextPage(paginator
										.isNull("hasNextPage") ? false
										: paginator.optBoolean("hasNextPage"));
							}
						}

						return listvo;
					}

					@Override
					String logTag() {
						return TAG;
					}
				}, (IFailureListener) connection);
	}

	@Override
	public void my_question_at(Map<String, String> input,
			IConnectionNetworkAble<ListQuestionVO> connection) {
		// TODO Auto-generated method stub
		post(Contacts.ServiceURL.MY_QUESTION_AT, input,
				new CallbackListener<ListQuestionVO>(connection) {

					@Override
					ListQuestionVO parseResult(JSONObject result)
							throws ProcessException, Exception {
						ListQuestionVO listvo = new ListQuestionVO();
						if (isNotNull(result)) {
							listvo.setRetCode(result.isNull("retCode") ? null
									: result.optInt("retCode"));
							listvo.setError(result.isNull("errors") ? null
									: result.optString("errors"));
							JSONArray datas = result.optJSONArray("data");
							if (isNotNull(datas)) {
								List<QuestionVO> list = new ArrayList<QuestionVO>();
								for (int i = 0; i < datas.length(); i++) {
									JSONObject data = datas.optJSONObject(i);
									if (isNotNull(data)) {
										QuestionVO vo = new QuestionVO();
										vo.setAskId(data.isNull("askId") ? null
												: data.optString("askId"));
										vo.setUserId(data.isNull("userId") ? null
												: data.optString("userId"));
										vo.setContent(data.isNull("content") ? null
												: data.optString("content"));
										vo.setHeadPic(data.isNull("headPic") ? null
												: data.optString("headPic"));
										vo.setUsername(data.isNull("userName") ? null
												: data.optString("userName"));
										vo.setTitle(data.isNull("title") ? null
												: data.optString("title"));
										vo.setCreateTime(data
												.isNull("createTime") ? null
												: data.optLong("createTime"));
										vo.setPicUrl(data.isNull("picUrl") ? null
												: data.optString("picUrl"));
										vo.setCommentNum(data
												.isNull("commentNum") ? null
												: data.optInt("commentNum"));
										vo.setCollectionNum(data
												.isNull("collectionNum") ? null
												: data.optInt("collectionNum"));
										vo.setPraiseNum(data
												.isNull("praiseNum") ? null
												: data.optInt("praiseNum"));
										vo.setHasCollection(data
												.isNull("hasCollection") ? false
												: data.optInt("hasCollection") > 0 ? true
														: false);
										vo.setHasPraise(data
												.isNull("hasPraise") ? false
												: data.optInt("hasPraise") > 0 ? true
														: false);
										list.add(vo);
									}
								}

								listvo.setDataList(list);
							}
							JSONObject paginator = result
									.optJSONObject("paginator");
							if (isNotNull(paginator)) {
								listvo.setTotalCount(paginator
										.isNull("totalCount") ? null
										: paginator.optInt("totalCount"));
								listvo.setLimit(paginator.isNull("limit") ? null
										: paginator.optInt("limit"));
								listvo.setPage(paginator.isNull("page") ? null
										: paginator.optInt("page"));
								listvo.setNextPage(paginator.isNull("nextPage") ? null
										: paginator.optInt("nextPage"));
								listvo.setHasNextPage(paginator
										.isNull("hasNextPage") ? false
										: paginator.optBoolean("hasNextPage"));
							}
						}

						return listvo;
					}

					@Override
					String logTag() {
						return TAG;
					}
				}, (IFailureListener) connection);
	}

	@Override
	public void my_question_speak(Map<String, String> input,
			IConnectionNetworkAble<ListQuestionVO> connection) {
		// TODO Auto-generated method stub
		post(Contacts.ServiceURL.MY_QUESTION_SPEAK, input,
				new CallbackListener<ListQuestionVO>(connection) {

					@Override
					ListQuestionVO parseResult(JSONObject result)
							throws ProcessException, Exception {
						ListQuestionVO listvo = new ListQuestionVO();
						if (isNotNull(result)) {
							listvo.setRetCode(result.isNull("retCode") ? null
									: result.optInt("retCode"));
							listvo.setError(result.isNull("errors") ? null
									: result.optString("errors"));
							JSONArray datas = result.optJSONArray("data");
							if (isNotNull(datas)) {
								List<QuestionVO> list = new ArrayList<QuestionVO>();
								for (int i = 0; i < datas.length(); i++) {
									JSONObject data = datas.optJSONObject(i);
									if (isNotNull(data)) {
										QuestionVO vo = new QuestionVO();
										vo.setAskId(data.isNull("askId") ? null
												: data.optString("askId"));
										vo.setUserId(data.isNull("userId") ? null
												: data.optString("userId"));
										vo.setContent(data.isNull("content") ? null
												: data.optString("content"));
										vo.setHeadPic(data.isNull("headPic") ? null
												: data.optString("headPic"));
										vo.setUsername(data.isNull("userName") ? null
												: data.optString("userName"));
										vo.setTitle(data.isNull("title") ? null
												: data.optString("title"));
										vo.setCreateTime(data
												.isNull("createTime") ? null
												: data.optLong("createTime"));
										vo.setPicUrl(data.isNull("picUrl") ? null
												: data.optString("picUrl"));
										vo.setCommentNum(data
												.isNull("commentNum") ? null
												: data.optInt("commentNum"));
										vo.setCollectionNum(data
												.isNull("collectionNum") ? null
												: data.optInt("collectionNum"));
										vo.setPraiseNum(data
												.isNull("praiseNum") ? null
												: data.optInt("praiseNum"));
										vo.setHasCollection(data
												.isNull("hasCollection") ? false
												: data.optInt("hasCollection") > 0 ? true
														: false);
										vo.setHasPraise(data
												.isNull("hasPraise") ? false
												: data.optInt("hasPraise") > 0 ? true
														: false);
										list.add(vo);
									}
								}

								listvo.setDataList(list);
							}
							JSONObject paginator = result
									.optJSONObject("paginator");
							if (isNotNull(paginator)) {
								listvo.setTotalCount(paginator
										.isNull("totalCount") ? null
										: paginator.optInt("totalCount"));
								listvo.setLimit(paginator.isNull("limit") ? null
										: paginator.optInt("limit"));
								listvo.setPage(paginator.isNull("page") ? null
										: paginator.optInt("page"));
								listvo.setNextPage(paginator.isNull("nextPage") ? null
										: paginator.optInt("nextPage"));
								listvo.setHasNextPage(paginator
										.isNull("hasNextPage") ? false
										: paginator.optBoolean("hasNextPage"));
							}
						}

						return listvo;
					}

					@Override
					String logTag() {
						return TAG;
					}
				}, (IFailureListener) connection);
	}

	@Override
	public void my_question_praise(Map<String, String> input,
			IConnectionNetworkAble<ListQuestionVO> connection) {
		// TODO Auto-generated method stub
		post(Contacts.ServiceURL.MY_QUESTION_PRAISE, input,
				new CallbackListener<ListQuestionVO>(connection) {

					@Override
					ListQuestionVO parseResult(JSONObject result)
							throws ProcessException, Exception {
						ListQuestionVO listvo = new ListQuestionVO();
						if (isNotNull(result)) {
							listvo.setRetCode(result.isNull("retCode") ? null
									: result.optInt("retCode"));
							listvo.setError(result.isNull("errors") ? null
									: result.optString("errors"));
							JSONArray datas = result.optJSONArray("data");
							if (isNotNull(datas)) {
								List<QuestionVO> list = new ArrayList<QuestionVO>();
								for (int i = 0; i < datas.length(); i++) {
									JSONObject data = datas.optJSONObject(i);
									if (isNotNull(data)) {
										QuestionVO vo = new QuestionVO();
										vo.setAskId(data.isNull("askId") ? null
												: data.optString("askId"));
										vo.setUserId(data.isNull("userId") ? null
												: data.optString("userId"));
										vo.setContent(data.isNull("content") ? null
												: data.optString("content"));
										vo.setHeadPic(data.isNull("headPic") ? null
												: data.optString("headPic"));
										vo.setUsername(data.isNull("userName") ? null
												: data.optString("userName"));
										vo.setTitle(data.isNull("title") ? null
												: data.optString("title"));
										vo.setCreateTime(data
												.isNull("createTime") ? null
												: data.optLong("createTime"));
										vo.setPicUrl(data.isNull("picUrl") ? null
												: data.optString("picUrl"));
										vo.setCommentNum(data
												.isNull("commentNum") ? null
												: data.optInt("commentNum"));
										vo.setCollectionNum(data
												.isNull("collectionNum") ? null
												: data.optInt("collectionNum"));
										vo.setPraiseNum(data
												.isNull("praiseNum") ? null
												: data.optInt("praiseNum"));
										vo.setHasCollection(data
												.isNull("hasCollection") ? false
												: data.optInt("hasCollection") > 0 ? true
														: false);
										vo.setHasPraise(data
												.isNull("hasPraise") ? false
												: data.optInt("hasPraise") > 0 ? true
														: false);
										list.add(vo);
									}
								}
								listvo.setDataList(list);
							}
							JSONObject paginator = result
									.optJSONObject("paginator");
							if (isNotNull(paginator)) {
								listvo.setTotalCount(paginator
										.isNull("totalCount") ? null
										: paginator.optInt("totalCount"));
								listvo.setLimit(paginator.isNull("limit") ? null
										: paginator.optInt("limit"));
								listvo.setPage(paginator.isNull("page") ? null
										: paginator.optInt("page"));
								listvo.setNextPage(paginator.isNull("nextPage") ? null
										: paginator.optInt("nextPage"));
								listvo.setHasNextPage(paginator
										.isNull("hasNextPage") ? false
										: paginator.optBoolean("hasNextPage"));
							}
						}

						return listvo;
					}

					@Override
					String logTag() {
						return TAG;
					}
				}, (IFailureListener) connection);
	}

	@Override
	public void my_question_collection(Map<String, String> input,
			IConnectionNetworkAble<ListQuestionVO> connection) {
		// TODO Auto-generated method stub
		post(Contacts.ServiceURL.MY_QUESTION_COLLECTION, input,
				new CallbackListener<ListQuestionVO>(connection) {

					@Override
					ListQuestionVO parseResult(JSONObject result)
							throws ProcessException, Exception {
						ListQuestionVO listvo = new ListQuestionVO();
						if (isNotNull(result)) {
							listvo.setRetCode(result.isNull("retCode") ? null
									: result.optInt("retCode"));
							listvo.setError(result.isNull("errors") ? null
									: result.optString("errors"));
							JSONArray datas = result.optJSONArray("data");
							if (isNotNull(datas)) {
								List<QuestionVO> list = new ArrayList<QuestionVO>();
								for (int i = 0; i < datas.length(); i++) {
									JSONObject data = datas.optJSONObject(i);
									if (isNotNull(data)) {
										QuestionVO vo = new QuestionVO();
										vo.setAskId(data.isNull("askId") ? null
												: data.optString("askId"));
										vo.setUserId(data.isNull("userId") ? null
												: data.optString("userId"));
										vo.setContent(data.isNull("content") ? null
												: data.optString("content"));
										vo.setHeadPic(data.isNull("headPic") ? null
												: data.optString("headPic"));
										vo.setUsername(data.isNull("userName") ? null
												: data.optString("userName"));
										vo.setTitle(data.isNull("title") ? null
												: data.optString("title"));
										vo.setCreateTime(data
												.isNull("createTime") ? null
												: data.optLong("createTime"));
										vo.setPicUrl(data.isNull("picUrl") ? null
												: data.optString("picUrl"));
										vo.setCommentNum(data
												.isNull("commentNum") ? null
												: data.optInt("commentNum"));
										vo.setCollectionNum(data
												.isNull("collectionNum") ? null
												: data.optInt("collectionNum"));
										vo.setPraiseNum(data
												.isNull("praiseNum") ? null
												: data.optInt("praiseNum"));
										vo.setHasCollection(data
												.isNull("hasCollection") ? false
												: data.optInt("hasCollection") > 0 ? true
														: false);
										vo.setHasPraise(data
												.isNull("hasPraise") ? false
												: data.optInt("hasPraise") > 0 ? true
														: false);
										list.add(vo);
									}
								}

								listvo.setDataList(list);
							}
							JSONObject paginator = result
									.optJSONObject("paginator");
							if (isNotNull(paginator)) {
								listvo.setTotalCount(paginator
										.isNull("totalCount") ? null
										: paginator.optInt("totalCount"));
								listvo.setLimit(paginator.isNull("limit") ? null
										: paginator.optInt("limit"));
								listvo.setPage(paginator.isNull("page") ? null
										: paginator.optInt("page"));
								listvo.setNextPage(paginator.isNull("nextPage") ? null
										: paginator.optInt("nextPage"));
								listvo.setHasNextPage(paginator
										.isNull("hasNextPage") ? false
										: paginator.optBoolean("hasNextPage"));
							}
						}

						return listvo;
					}

					@Override
					String logTag() {
						return TAG;
					}
				}, (IFailureListener) connection);
	}

	@Override
	public void my_square_at(Map<String, String> input,
			IConnectionNetworkAble<ListSquareVO> connection) {
		// TODO Auto-generated method stub
		post(Contacts.ServiceURL.MY_SQUARE_AT, input,
				new CallbackListener<ListSquareVO>(connection) {

					@Override
					ListSquareVO parseResult(JSONObject result)
							throws ProcessException, Exception {
						ListSquareVO listvo = new ListSquareVO();
						if (isNotNull(result)) {
							listvo.setRetCode(result.isNull("retCode") ? null
									: result.optInt("retCode"));
							listvo.setError(result.isNull("errors") ? null
									: result.optString("errors"));
							JSONArray datas = result.optJSONArray("data");
							if (isNotNull(datas)) {
								List<SquareVO> list = new ArrayList<SquareVO>();
								for (int i = 0; i < datas.length(); i++) {
									JSONObject data = datas.optJSONObject(i);
									if (isNotNull(data)) {
										SquareVO vo = new SquareVO();
										vo.setSpeakId(data.isNull("speakId") ? null
												: data.optString("speakId"));
										vo.setJobName(data.isNull("jobName") ? null
												: data.optString("jobName"));
										vo.setIsAnonymous(data
												.isNull("isAnonymous") ? false
												: data.optInt("isAnonymous") > 0 ? true
														: false);
										vo.setIsForward(data
												.isNull("isForward") ? false
												: data.optInt("isForward") > 0 ? true
														: false);
										vo.setForwardSpeakId(data
												.isNull("forwardSpeakId") ? null
												: data.optString("forwardSpeakId"));
										vo.setUserId(data.isNull("userId") ? null
												: data.optString("userId"));
										vo.setContent(data.isNull("content") ? null
												: data.optString("content"));
										vo.setHeadPic(data.isNull("headPic") ? null
												: data.optString("headPic"));
										vo.setUsername(data.isNull("userName") ? null
												: data.optString("userName"));
										vo.setTitle(data.isNull("title") ? null
												: data.optString("title"));
										vo.setCreateTime(data
												.isNull("createTime") ? null
												: data.optLong("createTime"));
										vo.setPicUrl(data.isNull("picUrl") ? null
												: data.optString("picUrl"));
										vo.setCommentNum(data
												.isNull("commentNum") ? null
												: data.optInt("commentNum"));
										vo.setForwardNum(data
												.isNull("forwardNum") ? null
												: data.optInt("forwardNum"));
										vo.setPraiseNum(data
												.isNull("praiseNum") ? null
												: data.optInt("praiseNum"));
										vo.setHasForward(data
												.isNull("hasForward") ? false
												: data.optInt("hasForward") > 0 ? true
														: false);
										vo.setHasPraise(data
												.isNull("hasPraise") ? false
												: data.optInt("hasPraise") > 0 ? true
														: false);
										vo.setHasComment(data
												.isNull("hasComment") ? false
												: data.optInt("hasComment") > 0 ? true
														: false);
										list.add(vo);
									}
								}
								listvo.setDataList(list);
							}
							JSONObject paginator = result
									.optJSONObject("paginator");
							if (isNotNull(paginator)) {
								listvo.setTotalCount(paginator
										.isNull("totalCount") ? null
										: paginator.optInt("totalCount"));
								listvo.setLimit(paginator.isNull("limit") ? null
										: paginator.optInt("limit"));
								listvo.setPage(paginator.isNull("page") ? null
										: paginator.optInt("page"));
								listvo.setNextPage(paginator.isNull("nextPage") ? null
										: paginator.optInt("nextPage"));
								listvo.setHasNextPage(paginator
										.isNull("hasNextPage") ? false
										: paginator.optBoolean("hasNextPage"));
							}
						}

						return listvo;
					}

					@Override
					String logTag() {
						return TAG;
					}
				}, (IFailureListener) connection);

	}

	@Override
	public void my_square_speak(Map<String, String> input,
			IConnectionNetworkAble<ListSquareVO> connection) {
		// TODO Auto-generated method stub
		post(Contacts.ServiceURL.MY_SQUARE_SPEAK, input,
				new CallbackListener<ListSquareVO>(connection) {

					@Override
					ListSquareVO parseResult(JSONObject result)
							throws ProcessException, Exception {
						ListSquareVO listvo = new ListSquareVO();
						if (isNotNull(result)) {
							listvo.setRetCode(result.isNull("retCode") ? null
									: result.optInt("retCode"));
							listvo.setError(result.isNull("errors") ? null
									: result.optString("errors"));
							JSONArray datas = result.optJSONArray("data");
							if (isNotNull(datas)) {
								List<SquareVO> list = new ArrayList<SquareVO>();
								for (int i = 0; i < datas.length(); i++) {
									JSONObject data = datas.optJSONObject(i);
									if (isNotNull(data)) {
										SquareVO vo = new SquareVO();
										vo.setSpeakId(data.isNull("speakId") ? null
												: data.optString("speakId"));
										vo.setJobName(data.isNull("jobName") ? null
												: data.optString("jobName"));
										vo.setIsAnonymous(data
												.isNull("isAnonymous") ? false
												: data.optInt("isAnonymous") > 0 ? true
														: false);
										vo.setIsForward(data
												.isNull("isForward") ? false
												: data.optInt("isForward") > 0 ? true
														: false);
										vo.setForwardSpeakId(data
												.isNull("forwardSpeakId") ? null
												: data.optString("forwardSpeakId"));
										vo.setUserId(data.isNull("userId") ? null
												: data.optString("userId"));
										vo.setContent(data.isNull("content") ? null
												: data.optString("content"));
										vo.setHeadPic(data.isNull("headPic") ? null
												: data.optString("headPic"));
										vo.setUsername(data.isNull("userName") ? null
												: data.optString("userName"));
										vo.setTitle(data.isNull("title") ? null
												: data.optString("title"));
										vo.setCreateTime(data
												.isNull("createTime") ? null
												: data.optLong("createTime"));
										vo.setPicUrl(data.isNull("picUrl") ? null
												: data.optString("picUrl"));
										vo.setCommentNum(data
												.isNull("commentNum") ? null
												: data.optInt("commentNum"));
										vo.setForwardNum(data
												.isNull("forwardNum") ? null
												: data.optInt("forwardNum"));
										vo.setPraiseNum(data
												.isNull("praiseNum") ? null
												: data.optInt("praiseNum"));
										vo.setHasForward(data
												.isNull("hasForward") ? false
												: data.optInt("hasForward") > 0 ? true
														: false);
										vo.setHasPraise(data
												.isNull("hasPraise") ? false
												: data.optInt("hasPraise") > 0 ? true
														: false);
										vo.setHasComment(data
												.isNull("hasComment") ? false
												: data.optInt("hasComment") > 0 ? true
														: false);
										list.add(vo);
									}
								}
								listvo.setDataList(list);
							}
							JSONObject paginator = result
									.optJSONObject("paginator");
							if (isNotNull(paginator)) {
								listvo.setTotalCount(paginator
										.isNull("totalCount") ? null
										: paginator.optInt("totalCount"));
								listvo.setLimit(paginator.isNull("limit") ? null
										: paginator.optInt("limit"));
								listvo.setPage(paginator.isNull("page") ? null
										: paginator.optInt("page"));
								listvo.setNextPage(paginator.isNull("nextPage") ? null
										: paginator.optInt("nextPage"));
								listvo.setHasNextPage(paginator
										.isNull("hasNextPage") ? false
										: paginator.optBoolean("hasNextPage"));
							}
						}

						return listvo;
					}

					@Override
					String logTag() {
						return TAG;
					}
				}, (IFailureListener) connection);
	}

	@Override
	public void my_square_comment(Map<String, String> input,
			IConnectionNetworkAble<ListSquareVO> connection) {
		// TODO Auto-generated method stub
		post(Contacts.ServiceURL.MY_SQUARE_COMMENT, input,
				new CallbackListener<ListSquareVO>(connection) {

					@Override
					ListSquareVO parseResult(JSONObject result)
							throws ProcessException, Exception {
						ListSquareVO listvo = new ListSquareVO();
						if (isNotNull(result)) {
							listvo.setRetCode(result.isNull("retCode") ? null
									: result.optInt("retCode"));
							listvo.setError(result.isNull("errors") ? null
									: result.optString("errors"));
							JSONArray datas = result.optJSONArray("data");
							if (isNotNull(datas)) {
								List<SquareVO> list = new ArrayList<SquareVO>();
								for (int i = 0; i < datas.length(); i++) {
									JSONObject data = datas.optJSONObject(i);
									if (isNotNull(data)) {
										SquareVO vo = new SquareVO();
										vo.setCommentContent(data
												.isNull("commentContent") ? null
												: data.optString("commentContent"));
										vo.setCommentCreateTime(data
												.isNull("commentCreateTime") ? null
												: data.optLong("commentCreateTime"));
										vo.setSpeakId(data.isNull("speakId") ? null
												: data.optString("speakId"));
										vo.setJobName(data.isNull("jobName") ? null
												: data.optString("jobName"));
										vo.setIsAnonymous(data
												.isNull("isAnonymous") ? false
												: data.optInt("isAnonymous") > 0 ? true
														: false);
										vo.setIsForward(data
												.isNull("isForward") ? false
												: data.optInt("isForward") > 0 ? true
														: false);
										vo.setForwardSpeakId(data
												.isNull("forwardSpeakId") ? null
												: data.optString("forwardSpeakId"));
										vo.setUserId(data.isNull("userId") ? null
												: data.optString("userId"));
										vo.setContent(data.isNull("content") ? null
												: data.optString("content"));
										vo.setHeadPic(data.isNull("headPic") ? null
												: data.optString("headPic"));
										vo.setUsername(data.isNull("userName") ? null
												: data.optString("userName"));
										vo.setTitle(data.isNull("title") ? null
												: data.optString("title"));
										vo.setCreateTime(data
												.isNull("createTime") ? null
												: data.optLong("createTime"));
										vo.setPicUrl(data.isNull("picUrl") ? null
												: data.optString("picUrl"));
										vo.setCommentNum(data
												.isNull("commentNum") ? null
												: data.optInt("commentNum"));
										vo.setForwardNum(data
												.isNull("forwardNum") ? null
												: data.optInt("forwardNum"));
										vo.setPraiseNum(data
												.isNull("praiseNum") ? null
												: data.optInt("praiseNum"));
										vo.setHasForward(data
												.isNull("hasForward") ? false
												: data.optInt("hasForward") > 0 ? true
														: false);
										vo.setHasPraise(data
												.isNull("hasPraise") ? false
												: data.optInt("hasPraise") > 0 ? true
														: false);
										vo.setHasComment(data
												.isNull("hasComment") ? false
												: data.optInt("hasComment") > 0 ? true
														: false);
										list.add(vo);
									}
								}

								listvo.setDataList(list);
							}
							JSONObject paginator = result
									.optJSONObject("paginator");
							if (isNotNull(paginator)) {
								listvo.setTotalCount(paginator
										.isNull("totalCount") ? null
										: paginator.optInt("totalCount"));
								listvo.setLimit(paginator.isNull("limit") ? null
										: paginator.optInt("limit"));
								listvo.setPage(paginator.isNull("page") ? null
										: paginator.optInt("page"));
								listvo.setNextPage(paginator.isNull("nextPage") ? null
										: paginator.optInt("nextPage"));
								listvo.setHasNextPage(paginator
										.isNull("hasNextPage") ? false
										: paginator.optBoolean("hasNextPage"));
							}
						}

						return listvo;
					}

					@Override
					String logTag() {
						return TAG;
					}
				}, (IFailureListener) connection);
	}

	@Override
	public void my_square_praise(Map<String, String> input,
			IConnectionNetworkAble<ListSquareVO> connection) {
		// TODO Auto-generated method stub
		post(Contacts.ServiceURL.MY_SQUARE_PRAISE, input,
				new CallbackListener<ListSquareVO>(connection) {

					@Override
					ListSquareVO parseResult(JSONObject result)
							throws ProcessException, Exception {
						ListSquareVO listvo = new ListSquareVO();
						if (isNotNull(result)) {
							listvo.setRetCode(result.isNull("retCode") ? null
									: result.optInt("retCode"));
							listvo.setError(result.isNull("errors") ? null
									: result.optString("errors"));
							JSONArray datas = result.optJSONArray("data");
							if (isNotNull(datas)) {
								List<SquareVO> list = new ArrayList<SquareVO>();
								for (int i = 0; i < datas.length(); i++) {
									JSONObject data = datas.optJSONObject(i);
									if (isNotNull(data)) {
										SquareVO vo = new SquareVO();
										vo.setSpeakId(data.isNull("speakId") ? null
												: data.optString("speakId"));
										vo.setJobName(data.isNull("jobName") ? null
												: data.optString("jobName"));
										vo.setIsAnonymous(data
												.isNull("isAnonymous") ? false
												: data.optInt("isAnonymous") > 0 ? true
														: false);
										vo.setIsForward(data
												.isNull("isForward") ? false
												: data.optInt("isForward") > 0 ? true
														: false);
										vo.setForwardSpeakId(data
												.isNull("forwardSpeakId") ? null
												: data.optString("forwardSpeakId"));
										vo.setUserId(data.isNull("userId") ? null
												: data.optString("userId"));
										vo.setContent(data.isNull("content") ? null
												: data.optString("content"));
										vo.setHeadPic(data.isNull("headPic") ? null
												: data.optString("headPic"));
										vo.setUsername(data.isNull("userName") ? null
												: data.optString("userName"));
										vo.setTitle(data.isNull("title") ? null
												: data.optString("title"));
										vo.setCreateTime(data
												.isNull("createTime") ? null
												: data.optLong("createTime"));
										vo.setPicUrl(data.isNull("picUrl") ? null
												: data.optString("picUrl"));
										vo.setCommentNum(data
												.isNull("commentNum") ? null
												: data.optInt("commentNum"));
										vo.setForwardNum(data
												.isNull("forwardNum") ? null
												: data.optInt("forwardNum"));
										vo.setPraiseNum(data
												.isNull("praiseNum") ? null
												: data.optInt("praiseNum"));
										vo.setHasForward(data
												.isNull("hasForward") ? false
												: data.optInt("hasForward") > 0 ? true
														: false);
										vo.setHasPraise(data
												.isNull("hasPraise") ? false
												: data.optInt("hasPraise") > 0 ? true
														: false);
										vo.setHasComment(data
												.isNull("hasComment") ? false
												: data.optInt("hasComment") > 0 ? true
														: false);
										list.add(vo);
									}
								}

								listvo.setDataList(list);
							}
							JSONObject paginator = result
									.optJSONObject("paginator");
							if (isNotNull(paginator)) {
								listvo.setTotalCount(paginator
										.isNull("totalCount") ? null
										: paginator.optInt("totalCount"));
								listvo.setLimit(paginator.isNull("limit") ? null
										: paginator.optInt("limit"));
								listvo.setPage(paginator.isNull("page") ? null
										: paginator.optInt("page"));
								listvo.setNextPage(paginator.isNull("nextPage") ? null
										: paginator.optInt("nextPage"));
								listvo.setHasNextPage(paginator
										.isNull("hasNextPage") ? false
										: paginator.optBoolean("hasNextPage"));
							}
						}

						return listvo;
					}

					@Override
					String logTag() {
						return TAG;
					}
				}, (IFailureListener) connection);
	}

	@Override
	public void production_list(Map<String, String> input,
			IConnectionNetworkAble<ListProductionVO> connection) {
		// TODO Auto-generated method stub
		post(Contacts.ServiceURL.GOODS_LIST, input,
				new CallbackListener<ListProductionVO>(connection) {

					@Override
					ListProductionVO parseResult(JSONObject result)
							throws ProcessException, Exception {
						ListProductionVO listvo = new ListProductionVO();
						if (isNotNull(result)) {
							listvo.setRetCode(result.isNull("retCode") ? null
									: result.optInt("retCode"));
							listvo.setError(result.isNull("errors") ? null
									: result.optString("errors"));
							JSONArray datas = result.optJSONArray("data");
							if (isNotNull(datas)) {
								List<ProductionVO> list = new ArrayList<ProductionVO>();
								for (int i = 0; i < datas.length(); i++) {
									JSONObject data = datas.optJSONObject(i);
									if (isNotNull(data)) {
										ProductionVO vo = new ProductionVO();
										vo.setGoodsId(data.isNull("goodsId") ? null
												: data.optString("goodsId"));
										vo.setGoodsNum(data.isNull("goodsNum") ? null
												: data.optLong("goodsNum"));
										vo.setGoodsComPicUrl(data
												.isNull("goodsComPicUrl") ? null
												: data.optString("goodsComPicUrl"));
										vo.setShopId(data.isNull("shopId") ? null
												: data.optString("shopId"));
										vo.setShopName(data.isNull("shopName") ? null
												: data.optString("shopName"));
										vo.setShopPic(data.isNull("shopPic") ? null
												: data.optString("shopPic"));
										vo.setGoodsMaterial(data
												.isNull("goodsMaterial") ? null
												: data.optString("goodsMaterial"));
										vo.setGoodsStyle(data
												.isNull("goodsStyle") ? null
												: data.optString("goodsStyle"));
										vo.setGoodsName(data
												.isNull("goodsName") ? null
												: data.optString("goodsName"));
										vo.setGoodsSmPicUrl(data
												.isNull("goodsSmPicUrl") ? null
												: data.optString("goodsSmPicUrl"));
										vo.setGoodsBigPicUrl(data
												.isNull("goodsBigPicUrl") ? null
												: data.optString("goodsBigPicUrl"));
										vo.setGoodsFee(data.isNull("goodsFee") ? "0"
												: data.optString("goodsFee"));
										vo.setGoodsStatus(data
												.isNull("goodsStatus") ? null
												: data.optString("goodsStatus"));
										vo.setGoodsColor(data
												.isNull("goodsColor") ? null
												: data.optString("goodsColor"));
										vo.setGoodsBrand(data
												.isNull("goodsBrand") ? null
												: data.optString("goodsBrand"));
										vo.setGoodsDesc(data
												.isNull("goodsDesc") ? null
												: data.optString("goodsDesc"));
										list.add(vo);
									}
								}

								listvo.setDataList(list);
							}
							JSONObject paginator = result
									.optJSONObject("paginator");
							if (isNotNull(paginator)) {
								listvo.setTotalCount(paginator
										.isNull("totalCount") ? null
										: paginator.optInt("totalCount"));
								listvo.setLimit(paginator.isNull("limit") ? null
										: paginator.optInt("limit"));
								listvo.setPage(paginator.isNull("page") ? null
										: paginator.optInt("page"));
								listvo.setNextPage(paginator.isNull("nextPage") ? null
										: paginator.optInt("nextPage"));
								listvo.setHasNextPage(paginator
										.isNull("hasNextPage") ? false
										: paginator.optBoolean("hasNextPage"));
							}
						}

						return listvo;
					}

					@Override
					String logTag() {
						return TAG;
					}
				}, (IFailureListener) connection);
	}

	@Override
	public void production_detail(Map<String, String> input,
			IConnectionNetworkAble<ProductionVO> connection) {
		// TODO Auto-generated method stub
		post(Contacts.ServiceURL.GOODS_DETAIL, input,
				new CallbackListener<ProductionVO>(connection) {

					@Override
					ProductionVO parseResult(JSONObject result)
							throws ProcessException, Exception {
						ProductionVO vo = new ProductionVO();
						if (isNotNull(result)) {
							// listvo.setRetCode(result.isNull("retCode") ? null
							// : result.optInt("retCode"));
							// listvo.setError(result.isNull("errors") ? null
							// : result.optString("errors"));
							JSONObject data = result.optJSONObject("data");
							if (isNotNull(data)) {
								vo.setGoodsId(data.isNull("goodsId") ? null
										: data.optString("goodsId"));
								vo.setGoodsNum(data.isNull("goodsNum") ? null
										: data.optLong("goodsNum"));
								vo.setGoodsComPicUrl(data
										.isNull("goodsComPicUrl") ? null : data
										.optString("goodsComPicUrl"));
								vo.setShopId(data.isNull("shopId") ? null
										: data.optString("shopId"));
								vo.setShopName(data.isNull("shopName") ? null
										: data.optString("shopName"));
								vo.setShopPic(data.isNull("shopPic") ? null
										: data.optString("shopPic"));
								vo.setGoodsMaterial(data
										.isNull("goodsMaterial") ? null : data
										.optString("goodsMaterial"));
								vo.setGoodsStyle(data.isNull("goodsStyle") ? null
										: data.optString("goodsStyle"));
								vo.setGoodsName(data.isNull("goodsName") ? null
										: data.optString("goodsName"));
								vo.setGoodsSmPicUrl(data
										.isNull("goodsSmPicUrl") ? null : data
										.optString("goodsSmPicUrl"));
								vo.setGoodsBigPicUrl(data
										.isNull("goodsBigPicUrl") ? null : data
										.optString("goodsBigPicUrl"));
								vo.setGoodsFee(data.isNull("goodsFee") ? "0"
										: data.optString("goodsFee"));
								vo.setGoodsStatus(data.isNull("goodsStatus") ? null
										: data.optString("goodsStatus"));
								vo.setGoodsColor(data.isNull("goodsColor") ? null
										: data.optString("goodsColor"));
								vo.setGoodsBrand(data.isNull("goodsBrand") ? null
										: data.optString("goodsBrand"));
								vo.setGoodsDesc(data.isNull("goodsDesc") ? null
										: data.optString("goodsDesc"));
								JSONArray comments = data
										.optJSONArray("commentlist");
								if (isNotNull(comments)) {
									List<ProductionCommentVO> list = new ArrayList<ProductionCommentVO>();
									for (int i = 0; i < comments.length(); i++) {
										JSONObject comment = comments
												.optJSONObject(i);
										if (isNotNull(comment)) {
											ProductionCommentVO commentvo = new ProductionCommentVO();
											commentvo.setCommentContent(comment
													.isNull("goodsComment") ? null
													: comment
															.optString("goodsComment"));
											commentvo.setUserName(comment
													.isNull("userName") ? null
													: comment
															.optString("userName"));
											commentvo.setHeadPic(comment
													.isNull("headPic") ? null
													: comment
															.optString("headPic"));
											commentvo.setTime(comment
													.isNull("time") ? 0
													: comment.optLong("time"));
											list.add(commentvo);
										}
									}
									vo.setListComment(list);
								}

							}
						}

						return vo;
					}

					@Override
					String logTag() {
						return TAG;
					}
				}, (IFailureListener) connection);
	}

	@Override
	public void store_production_list(Map<String, String> input,
			IConnectionNetworkAble<ListProductionVO> connection) {
		// TODO Auto-generated method stub
		post(Contacts.ServiceURL.STORE_DETAIL, input,
				new CallbackListener<ListProductionVO>(connection) {

					@Override
					ListProductionVO parseResult(JSONObject result)
							throws ProcessException, Exception {
						ListProductionVO listvo = new ListProductionVO();
						if (isNotNull(result)) {
							listvo.setRetCode(result.isNull("retCode") ? null
									: result.optInt("retCode"));
							listvo.setError(result.isNull("errors") ? null
									: result.optString("errors"));
							JSONArray datas = result.optJSONArray("data");
							if (isNotNull(datas)) {
								List<ProductionVO> list = new ArrayList<ProductionVO>();
								for (int i = 0; i < datas.length(); i++) {
									JSONObject data = datas.optJSONObject(i);
									if (isNotNull(data)) {
										ProductionVO vo = new ProductionVO();
										vo.setGoodsId(data.isNull("goodsId") ? null
												: data.optString("goodsId"));
										vo.setGoodsNum(data.isNull("goodsNum") ? null
												: data.optLong("goodsNum"));
										vo.setGoodsComPicUrl(data
												.isNull("goodsComPicUrl") ? null
												: data.optString("goodsComPicUrl"));
										vo.setShopId(data.isNull("shopId") ? null
												: data.optString("shopId"));
										vo.setShopName(data.isNull("shopName") ? null
												: data.optString("shopName"));
										vo.setShopPic(data.isNull("shopPic") ? null
												: data.optString("shopPic"));
										vo.setGoodsMaterial(data
												.isNull("goodsMaterial") ? null
												: data.optString("goodsMaterial"));
										vo.setGoodsStyle(data
												.isNull("goodsStyle") ? null
												: data.optString("goodsStyle"));
										vo.setGoodsName(data
												.isNull("goodsName") ? null
												: data.optString("goodsName"));
										vo.setGoodsSmPicUrl(data
												.isNull("goodsSmPicUrl") ? null
												: data.optString("goodsSmPicUrl"));
										vo.setGoodsBigPicUrl(data
												.isNull("goodsBigPicUrl") ? null
												: data.optString("goodsBigPicUrl"));
										vo.setGoodsFee(data.isNull("goodsFee") ? "0"
												: data.optString("goodsFee"));
										vo.setGoodsStatus(data
												.isNull("goodsStatus") ? null
												: data.optString("goodsStatus"));
										vo.setGoodsColor(data
												.isNull("goodsColor") ? null
												: data.optString("goodsColor"));
										vo.setGoodsBrand(data
												.isNull("goodsBrand") ? null
												: data.optString("goodsBrand"));
										vo.setGoodsDesc(data
												.isNull("goodsDesc") ? null
												: data.optString("goodsDesc"));
										list.add(vo);
									}
								}

								listvo.setDataList(list);
							}
							JSONObject paginator = result
									.optJSONObject("paginator");
							if (isNotNull(paginator)) {
								listvo.setTotalCount(paginator
										.isNull("totalCount") ? null
										: paginator.optInt("totalCount"));
								listvo.setLimit(paginator.isNull("limit") ? null
										: paginator.optInt("limit"));
								listvo.setPage(paginator.isNull("page") ? null
										: paginator.optInt("page"));
								listvo.setNextPage(paginator.isNull("nextPage") ? null
										: paginator.optInt("nextPage"));
								listvo.setHasNextPage(paginator
										.isNull("hasNextPage") ? false
										: paginator.optBoolean("hasNextPage"));
							}
						}

						return listvo;
					}

					@Override
					String logTag() {
						return TAG;
					}
				}, (IFailureListener) connection);
	}

	@Override
	public void production_order(Map<String, String> input,
			IConnectionNetworkAble<DefaultVO> connection) {
		// TODO Auto-generated method stub
		post(Contacts.ServiceURL.GOODS_ORDER, input,
				new CallbackListener<DefaultVO>(connection) {

					@Override
					DefaultVO parseResult(JSONObject result)
							throws ProcessException, Exception {
						DefaultVO vo = new DefaultVO();
						vo.setRetCode(result.isNull("retCode") ? null : result
								.optInt("retCode"));
						vo.setError(result.isNull("errors") ? null : result
								.optString("errors"));
						vo.setData(result.isNull("data") ? null : result
								.optString("data"));
						return vo;
					}

					@Override
					String logTag() {
						return TAG + ".registration";
					}
				}, (IFailureListener) connection);
	}

	@Override
	public void my_order(Map<String, String> input,
			IConnectionNetworkAble<ListProductionVO> connection) {
		// TODO Auto-generated method stub
		post(Contacts.ServiceURL.MY_ORDER, input,
				new CallbackListener<ListProductionVO>(connection) {

					@Override
					ListProductionVO parseResult(JSONObject result)
							throws ProcessException, Exception {
						ListProductionVO listvo = new ListProductionVO();
						if (isNotNull(result)) {
							listvo.setRetCode(result.isNull("retCode") ? null
									: result.optInt("retCode"));
							listvo.setError(result.isNull("errors") ? null
									: result.optString("errors"));
							JSONArray datas = result.optJSONArray("data");
							if (isNotNull(datas)) {
								List<ProductionVO> list = new ArrayList<ProductionVO>();
								for (int i = 0; i < datas.length(); i++) {
									JSONObject data = datas.optJSONObject(i);
									if (isNotNull(data)) {
										ProductionVO vo = new ProductionVO();
										vo.setOrderId(data.isNull("orderId") ? null
												: data.optString("orderId"));
										vo.setGoodsId(data.isNull("goodsId") ? null
												: data.optString("goodsId"));
										vo.setGoodsNum(data.isNull("goodsNum") ? null
												: data.optLong("goodsNum"));
										vo.setGoodsComPicUrl(data
												.isNull("goodsComPicUrl") ? null
												: data.optString("goodsComPicUrl"));
										vo.setShopId(data.isNull("shopId") ? null
												: data.optString("shopId"));
										vo.setShopName(data.isNull("shopName") ? null
												: data.optString("shopName"));
										vo.setShopPic(data.isNull("shopPic") ? null
												: data.optString("shopPic"));
										vo.setGoodsMaterial(data
												.isNull("goodsMaterial") ? null
												: data.optString("goodsMaterial"));
										vo.setGoodsStyle(data
												.isNull("goodsStyle") ? null
												: data.optString("goodsStyle"));
										vo.setGoodsName(data
												.isNull("goodsName") ? null
												: data.optString("goodsName"));
										vo.setGoodsSmPicUrl(data
												.isNull("goodsSmPicUrl") ? null
												: data.optString("goodsSmPicUrl"));
										vo.setGoodsBigPicUrl(data
												.isNull("goodsBigPicUrl") ? null
												: data.optString("goodsBigPicUrl"));
										vo.setGoodsFee(data.isNull("goodsFee") ? "0"
												: data.optString("goodsFee"));
										vo.setGoodsTotalFee(data
												.isNull("goodsTotalFee") ? "0"
												: data.optString("goodsTotalFee"));
										vo.setGoodsStatus(data
												.isNull("goodsStatus") ? null
												: data.optString("goodsStatus"));
										vo.setGoodsColor(data
												.isNull("goodsColor") ? null
												: data.optString("goodsColor"));
										vo.setGoodsBrand(data
												.isNull("goodsBrand") ? null
												: data.optString("goodsBrand"));
										vo.setGoodsDesc(data
												.isNull("goodsDesc") ? null
												: data.optString("goodsDesc"));
										vo.setCommentType(data
												.isNull("isComment") ? 0 : data
												.optInt("isComment"));
										vo.setPaymentType(data
												.isNull("isPayment") ? 0 : data
												.optInt("isPayment"));
										list.add(vo);
									}
								}

								listvo.setDataList(list);
							}
							JSONObject userInfo = result
									.optJSONObject("userInfo");
							if (isNotNull(userInfo)) {
								listvo.setReceiverAddress(userInfo
										.isNull("receiverAddress") ? null
										: userInfo.optString("receiverAddress"));
								listvo.setReceiverName(userInfo
										.isNull("receiverName") ? null
										: userInfo.optString("receiverName"));
								listvo.setReceiverPhone(userInfo
										.isNull("receiverPhone") ? null
										: userInfo.optString("receiverPhone"));
								listvo.setReceiverPostcode(userInfo
										.isNull("receiverPostcode") ? null
										: userInfo
												.optString("receiverPostcode"));
							}

							JSONObject paginator = result
									.optJSONObject("paginator");
							if (isNotNull(paginator)) {
								listvo.setTotalCount(paginator
										.isNull("totalCount") ? null
										: paginator.optInt("totalCount"));
								listvo.setLimit(paginator.isNull("limit") ? null
										: paginator.optInt("limit"));
								listvo.setPage(paginator.isNull("page") ? null
										: paginator.optInt("page"));
								listvo.setNextPage(paginator.isNull("nextPage") ? null
										: paginator.optInt("nextPage"));
								listvo.setHasNextPage(paginator
										.isNull("hasNextPage") ? false
										: paginator.optBoolean("hasNextPage"));
							}
						}

						return listvo;
					}

					@Override
					String logTag() {
						return TAG;
					}
				}, (IFailureListener) connection);
	}

	@Override
	public void add_cart(Map<String, String> input,
			IConnectionNetworkAble<DefaultVO> connection) {
		// TODO Auto-generated method stub
		post(Contacts.ServiceURL.ADD_CART, input,
				new CallbackListener<DefaultVO>(connection) {

					@Override
					DefaultVO parseResult(JSONObject result)
							throws ProcessException, Exception {
						DefaultVO vo = new DefaultVO();
						vo.setRetCode(result.isNull("retCode") ? null : result
								.optInt("retCode"));
						vo.setError(result.isNull("errors") ? null : result
								.optString("errors"));
						return vo;
					}

					@Override
					String logTag() {
						return TAG + ".registration";
					}
				}, (IFailureListener) connection);
	}

	@Override
	public void my_cart(Map<String, String> input,
			IConnectionNetworkAble<ListProductionVO> connection) {
		// TODO Auto-generated method stub
		post(Contacts.ServiceURL.MY_CART, input,
				new CallbackListener<ListProductionVO>(connection) {

					@Override
					ListProductionVO parseResult(JSONObject result)
							throws ProcessException, Exception {
						ListProductionVO listvo = new ListProductionVO();
						if (isNotNull(result)) {
							listvo.setRetCode(result.isNull("retCode") ? null
									: result.optInt("retCode"));
							listvo.setError(result.isNull("errors") ? null
									: result.optString("errors"));
							JSONArray datas = result.optJSONArray("data");
							if (isNotNull(datas)) {
								List<ProductionVO> list = new ArrayList<ProductionVO>();
								for (int i = 0; i < datas.length(); i++) {
									JSONObject data = datas.optJSONObject(i);
									if (isNotNull(data)) {
										ProductionVO vo = new ProductionVO();
										vo.setCartId(data.isNull("cartId") ? null
												: data.optString("cartId"));
										vo.setGoodsId(data.isNull("goodsId") ? null
												: data.optString("goodsId"));
										vo.setGoodsNum(data.isNull("goodsNum") ? null
												: data.optLong("goodsNum"));
										vo.setGoodsComPicUrl(data
												.isNull("goodsComPicUrl") ? null
												: data.optString("goodsComPicUrl"));
										vo.setShopId(data.isNull("shopId") ? null
												: data.optString("shopId"));
										vo.setShopName(data.isNull("shopName") ? null
												: data.optString("shopName"));
										vo.setShopPic(data.isNull("shopPic") ? null
												: data.optString("shopPic"));
										vo.setGoodsMaterial(data
												.isNull("goodsMaterial") ? null
												: data.optString("goodsMaterial"));
										vo.setGoodsStyle(data
												.isNull("goodsStyle") ? null
												: data.optString("goodsStyle"));
										vo.setGoodsName(data
												.isNull("goodsName") ? null
												: data.optString("goodsName"));
										vo.setGoodsSmPicUrl(data
												.isNull("goodsSmPicUrl") ? null
												: data.optString("goodsSmPicUrl"));
										vo.setGoodsBigPicUrl(data
												.isNull("goodsBigPicUrl") ? null
												: data.optString("goodsBigPicUrl"));
										vo.setGoodsFee(data.isNull("goodsFee") ? "0"
												: data.optString("goodsFee"));
										vo.setGoodsTotalFee(data
												.isNull("goodsTotalFee") ? "0"
												: data.optString("goodsTotalFee"));
										vo.setGoodsStatus(data
												.isNull("goodsStatus") ? null
												: data.optString("goodsStatus"));
										vo.setGoodsColor(data
												.isNull("goodsColor") ? null
												: data.optString("goodsColor"));
										vo.setGoodsBrand(data
												.isNull("goodsBrand") ? null
												: data.optString("goodsBrand"));
										vo.setGoodsDesc(data
												.isNull("goodsDesc") ? null
												: data.optString("goodsDesc"));
										vo.setCommentType(data
												.isNull("isComment") ? 0 : data
												.optInt("isComment"));
										vo.setPaymentType(data
												.isNull("isPayment") ? 0 : data
												.optInt("isPayment"));
										list.add(vo);
									}
								}

								listvo.setDataList(list);
							}
							JSONObject paginator = result
									.optJSONObject("paginator");
							if (isNotNull(paginator)) {
								listvo.setTotalCount(paginator
										.isNull("totalCount") ? null
										: paginator.optInt("totalCount"));
								listvo.setLimit(paginator.isNull("limit") ? null
										: paginator.optInt("limit"));
								listvo.setPage(paginator.isNull("page") ? null
										: paginator.optInt("page"));
								listvo.setNextPage(paginator.isNull("nextPage") ? null
										: paginator.optInt("nextPage"));
								listvo.setHasNextPage(paginator
										.isNull("hasNextPage") ? false
										: paginator.optBoolean("hasNextPage"));
							}
						}

						return listvo;
					}

					@Override
					String logTag() {
						return TAG;
					}
				}, (IFailureListener) connection);
	}

	@Override
	public void production_num(Map<String, String> input,
			IConnectionNetworkAble<ProductionVO> connection) {
		// TODO Auto-generated method stub
		post(Contacts.ServiceURL.GOODS_NUM, input,
				new CallbackListener<ProductionVO>(connection) {

					@Override
					ProductionVO parseResult(JSONObject result)
							throws ProcessException, Exception {
						ProductionVO vo = new ProductionVO();
						if (isNotNull(result)) {
							vo.setRetCode(result.isNull("retCode") ? null
									: result.optInt("retCode"));
							// listvo.setError(result.isNull("errors") ? null
							// : result.optString("errors"));
							JSONObject data = result.optJSONObject("data");
							if (isNotNull(data)) {
								vo.setGoodsNum(data.isNull("goodsNum") ? null
										: data.optLong("goodsNum"));
							}
						}

						return vo;
					}

					@Override
					String logTag() {
						return TAG;
					}
				}, (IFailureListener) connection);
	}

	@Override
	public void collection_goods(Map<String, String> input,
			IConnectionNetworkAble<DefaultVO> connection) {
		// TODO Auto-generated method stub
		post(Contacts.ServiceURL.GOODS_COLLECTION, input,
				new CallbackListener<DefaultVO>(connection) {

					@Override
					DefaultVO parseResult(JSONObject result)
							throws ProcessException, Exception {
						DefaultVO vo = new DefaultVO();
						vo.setRetCode(result.isNull("retCode") ? null : result
								.optInt("retCode"));
						vo.setError(result.isNull("errors") ? null : result
								.optString("errors"));
						return vo;
					}

					@Override
					String logTag() {
						return TAG + ".registration";
					}
				}, (IFailureListener) connection);
	}

	@Override
	public void collection_store(Map<String, String> input,
			IConnectionNetworkAble<DefaultVO> connection) {
		// TODO Auto-generated method stub
		post(Contacts.ServiceURL.STORE_COLLECTION, input,
				new CallbackListener<DefaultVO>(connection) {

					@Override
					DefaultVO parseResult(JSONObject result)
							throws ProcessException, Exception {
						DefaultVO vo = new DefaultVO();
						vo.setRetCode(result.isNull("retCode") ? null : result
								.optInt("retCode"));
						vo.setError(result.isNull("errors") ? null : result
								.optString("errors"));
						return vo;
					}

					@Override
					String logTag() {
						return TAG + ".registration";
					}
				}, (IFailureListener) connection);
	}

	@Override
	public void my_goods_collection(Map<String, String> input,
			IConnectionNetworkAble<ListProductionVO> connection) {
		// TODO Auto-generated method stub
		post(Contacts.ServiceURL.MY_GOODS_COLLECTION, input,
				new CallbackListener<ListProductionVO>(connection) {

					@Override
					ListProductionVO parseResult(JSONObject result)
							throws ProcessException, Exception {
						ListProductionVO listvo = new ListProductionVO();
						if (isNotNull(result)) {
							listvo.setRetCode(result.isNull("retCode") ? null
									: result.optInt("retCode"));
							listvo.setError(result.isNull("errors") ? null
									: result.optString("errors"));
							JSONArray datas = result.optJSONArray("data");
							if (isNotNull(datas)) {
								List<ProductionVO> list = new ArrayList<ProductionVO>();
								for (int i = 0; i < datas.length(); i++) {
									JSONObject data = datas.optJSONObject(i);
									if (isNotNull(data)) {
										ProductionVO vo = new ProductionVO();
										vo.setGoodsId(data.isNull("goodsId") ? null
												: data.optString("goodsId"));
										vo.setGoodsNum(data.isNull("goodsNum") ? null
												: data.optLong("goodsNum"));
										vo.setGoodsComPicUrl(data
												.isNull("goodsComPicUrl") ? null
												: data.optString("goodsComPicUrl"));
										vo.setShopId(data.isNull("shopId") ? null
												: data.optString("shopId"));
										vo.setShopName(data.isNull("shopName") ? null
												: data.optString("shopName"));
										vo.setShopPic(data.isNull("shopPic") ? null
												: data.optString("shopPic"));
										vo.setGoodsMaterial(data
												.isNull("goodsMaterial") ? null
												: data.optString("goodsMaterial"));
										vo.setGoodsStyle(data
												.isNull("goodsStyle") ? null
												: data.optString("goodsStyle"));
										vo.setGoodsName(data
												.isNull("goodsName") ? null
												: data.optString("goodsName"));
										vo.setGoodsSmPicUrl(data
												.isNull("goodsSmPicUrl") ? null
												: data.optString("goodsSmPicUrl"));
										vo.setGoodsBigPicUrl(data
												.isNull("goodsBigPicUrl") ? null
												: data.optString("goodsBigPicUrl"));
										vo.setGoodsFee(data.isNull("goodsFee") ? "0"
												: data.optString("goodsFee"));
										vo.setGoodsStatus(data
												.isNull("goodsStatus") ? null
												: data.optString("goodsStatus"));
										vo.setGoodsColor(data
												.isNull("goodsColor") ? null
												: data.optString("goodsColor"));
										vo.setGoodsBrand(data
												.isNull("goodsBrand") ? null
												: data.optString("goodsBrand"));
										vo.setGoodsDesc(data
												.isNull("goodsDesc") ? null
												: data.optString("goodsDesc"));
										list.add(vo);
									}
								}

								listvo.setDataList(list);
							}
							JSONObject paginator = result
									.optJSONObject("paginator");
							if (isNotNull(paginator)) {
								listvo.setTotalCount(paginator
										.isNull("totalCount") ? null
										: paginator.optInt("totalCount"));
								listvo.setLimit(paginator.isNull("limit") ? null
										: paginator.optInt("limit"));
								listvo.setPage(paginator.isNull("page") ? null
										: paginator.optInt("page"));
								listvo.setNextPage(paginator.isNull("nextPage") ? null
										: paginator.optInt("nextPage"));
								listvo.setHasNextPage(paginator
										.isNull("hasNextPage") ? false
										: paginator.optBoolean("hasNextPage"));
							}
						}

						return listvo;
					}

					@Override
					String logTag() {
						return TAG;
					}
				}, (IFailureListener) connection);
	}

	@Override
	public void my_store_collection(Map<String, String> input,
			IConnectionNetworkAble<ListProductionVO> connection) {
		// TODO Auto-generated method stub
		post(Contacts.ServiceURL.MY_STORE_COLLECTION, input,
				new CallbackListener<ListProductionVO>(connection) {

					@Override
					ListProductionVO parseResult(JSONObject result)
							throws ProcessException, Exception {
						ListProductionVO listvo = new ListProductionVO();
						if (isNotNull(result)) {
							listvo.setRetCode(result.isNull("retCode") ? null
									: result.optInt("retCode"));
							listvo.setError(result.isNull("errors") ? null
									: result.optString("errors"));
							JSONArray datas = result.optJSONArray("data");
							if (isNotNull(datas)) {
								List<ProductionVO> list = new ArrayList<ProductionVO>();
								for (int i = 0; i < datas.length(); i++) {
									JSONObject data = datas.optJSONObject(i);
									if (isNotNull(data)) {
										ProductionVO vo = new ProductionVO();
										vo.setGoodsId(data.isNull("goodsId") ? null
												: data.optString("goodsId"));
										vo.setGoodsNum(data.isNull("goodsNum") ? null
												: data.optLong("goodsNum"));
										vo.setGoodsComPicUrl(data
												.isNull("goodsComPicUrl") ? null
												: data.optString("goodsComPicUrl"));
										vo.setShopId(data.isNull("shopId") ? null
												: data.optString("shopId"));
										vo.setShopName(data.isNull("shopName") ? null
												: data.optString("shopName"));
										vo.setShopPic(data.isNull("shopPic") ? null
												: data.optString("shopPic"));
										vo.setGoodsMaterial(data
												.isNull("goodsMaterial") ? null
												: data.optString("goodsMaterial"));
										vo.setGoodsStyle(data
												.isNull("goodsStyle") ? null
												: data.optString("goodsStyle"));
										vo.setGoodsName(data
												.isNull("goodsName") ? null
												: data.optString("goodsName"));
										vo.setGoodsSmPicUrl(data
												.isNull("goodsSmPicUrl") ? null
												: data.optString("goodsSmPicUrl"));
										vo.setGoodsBigPicUrl(data
												.isNull("goodsBigPicUrl") ? null
												: data.optString("goodsBigPicUrl"));
										vo.setGoodsFee(data.isNull("goodsFee") ? "0"
												: data.optString("goodsFee"));
										vo.setGoodsStatus(data
												.isNull("goodsStatus") ? null
												: data.optString("goodsStatus"));
										vo.setGoodsColor(data
												.isNull("goodsColor") ? null
												: data.optString("goodsColor"));
										vo.setGoodsBrand(data
												.isNull("goodsBrand") ? null
												: data.optString("goodsBrand"));
										vo.setGoodsDesc(data
												.isNull("goodsDesc") ? null
												: data.optString("goodsDesc"));
										list.add(vo);
									}
								}

								listvo.setDataList(list);
							}
							JSONObject paginator = result
									.optJSONObject("paginator");
							if (isNotNull(paginator)) {
								listvo.setTotalCount(paginator
										.isNull("totalCount") ? null
										: paginator.optInt("totalCount"));
								listvo.setLimit(paginator.isNull("limit") ? null
										: paginator.optInt("limit"));
								listvo.setPage(paginator.isNull("page") ? null
										: paginator.optInt("page"));
								listvo.setNextPage(paginator.isNull("nextPage") ? null
										: paginator.optInt("nextPage"));
								listvo.setHasNextPage(paginator
										.isNull("hasNextPage") ? false
										: paginator.optBoolean("hasNextPage"));
							}
						}

						return listvo;
					}

					@Override
					String logTag() {
						return TAG;
					}
				}, (IFailureListener) connection);
	}

	@Override
	public void order_comment(Map<String, String> input,
			IConnectionNetworkAble<DefaultVO> connection) {
		// TODO Auto-generated method stub
		post(Contacts.ServiceURL.ORDER_COMMENT, input,
				new CallbackListener<DefaultVO>(connection) {

					@Override
					DefaultVO parseResult(JSONObject result)
							throws ProcessException, Exception {
						DefaultVO vo = new DefaultVO();
						vo.setRetCode(result.isNull("retCode") ? null : result
								.optInt("retCode"));
						vo.setError(result.isNull("errors") ? null : result
								.optString("errors"));
						return vo;
					}

					@Override
					String logTag() {
						return TAG + ".registration";
					}
				}, (IFailureListener) connection);
	}

	@Override
	public void order_confirm(Map<String, String> input,
			IConnectionNetworkAble<DefaultVO> connection) {
		// TODO Auto-generated method stub
		post(Contacts.ServiceURL.ORDER_CONFIRM, input,
				new CallbackListener<DefaultVO>(connection) {

					@Override
					DefaultVO parseResult(JSONObject result)
							throws ProcessException, Exception {
						DefaultVO vo = new DefaultVO();
						vo.setRetCode(result.isNull("retCode") ? null : result
								.optInt("retCode"));
						vo.setError(result.isNull("errors") ? null : result
								.optString("errors"));
						return vo;
					}

					@Override
					String logTag() {
						return TAG + ".registration";
					}
				}, (IFailureListener) connection);
	}

}
