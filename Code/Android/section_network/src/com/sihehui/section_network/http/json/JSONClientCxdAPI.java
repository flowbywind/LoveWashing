package com.sihehui.section_network.http.json;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.sihehui.section_network.http.info.BaseClientAPI;
import com.sihehui.section_network.http.info.IConnectionNetworkAble;
import com.sihehui.section_network.http.info.IFailureListener;
import com.sihehui.section_network.http.info.IServerCxdAPI;
import com.sihehui.section_network.http.info.ProcessException;
import com.sihehui.section_network.util.Contacts;
import com.sihehui.section_vo.vo.AuthVO;
import com.sihehui.section_vo.vo.BannerVO;
import com.sihehui.section_vo.vo.DefaultVO;
import com.sihehui.section_vo.vo.LoanVO;
import com.sihehui.section_vo.vo.UserLoginVO;

public class JSONClientCxdAPI extends BaseClientAPI implements IServerCxdAPI {
	private final static String TAG = JSONClientCxdAPI.class.getName();

	private static JSONClientCxdAPI clientAPI;

	public JSONClientCxdAPI(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public static JSONClientCxdAPI getInstance(Context context) {
		if (clientAPI == null) {
			clientAPI = new JSONClientCxdAPI(context);
		}
		return clientAPI;
	}

	@Override
	public <T> void getArrayData(Map<String, Object> input, String serviceName,
			IConnectionNetworkAble<List<T>> connection, final Class<T> objClass) {
		// TODO Auto-generated method stub
		post(input, serviceName, new CallbackListener<List<T>>(connection) {

			@Override
			List<T> parseResult(JSONObject result) throws ProcessException,
					Exception {
				// TODO Auto-generated method stub
				String data = result.optString("result");
				System.out.println("getArrayData:" + data);
				if (data == null || "".equals(data)) {
					return null;
				}
				List<T> t = JSON.parseArray(data, objClass);
				return t;
			}

			@Override
			String logTag() {
				// TODO Auto-generated method stub
				return null;
			}

		}, (IFailureListener) connection);
	}

	@Override
	public <T> void getObjectData(Map<String, Object> input,
			String serviceName, IConnectionNetworkAble<T> connection,
			final Class<T> objClass) {
		// TODO Auto-generated method stub
		post(input, serviceName, new CallbackListener<T>(connection) {

			@Override
			T parseResult(JSONObject result) throws ProcessException, Exception {
				// TODO Auto-generated method stub
				String data = result.optString("result");
				System.out.println("getObjectData:" + data);
				if (data == null || "".equals(data)) {
					return null;
				}
				T t = JSON.parseObject(data, objClass);
				return t;
			}

			@Override
			String logTag() {
				// TODO Auto-generated method stub
				return null;
			}

		}, (IFailureListener) connection);
	}

	@Override
	public void getAuthCode(Map<String, Object> input,
			IConnectionNetworkAble<DefaultVO> connection) {
		// TODO Auto-generated method stub
		post(input, Contacts.ServiceURL.CXD_AuthCode,
				new CallbackListener<DefaultVO>(connection) {

					@Override
					DefaultVO parseResult(JSONObject result)
							throws ProcessException, Exception {
						DefaultVO regVO = new DefaultVO();
						// regVO.setRetCode(result.isNull("retCode") ? null :
						// result
						// .optInt("retCode"));
						// regVO.setError(result.isNull("errors") ? null :
						// result
						// .optString("errors"));
						JSONObject data = result.optJSONObject("result");

						return regVO;
					}

					@Override
					String logTag() {
						return TAG + ".registration";
					}
				}, (IFailureListener) connection);
	}

	@Override
	public void registerService(Map<String, Object> input,
			IConnectionNetworkAble<DefaultVO> connection) {
		// TODO Auto-generated method stub
		post(input, Contacts.ServiceURL.RegisterService,
				new CallbackListener<DefaultVO>(connection) {

					@Override
					DefaultVO parseResult(JSONObject result)
							throws ProcessException, Exception {
						// TODO Auto-generated method stub
						System.out.println(result);
						DefaultVO model = new DefaultVO();
						String data = result.optString("result");
						if (data == null || "".equals(data)) {
							String resultmsg = result
									.optString("resultMessage");
							model.setError(resultmsg == null ? "注册失败"
									: resultmsg.toString());
						}
						System.out.println("data" + data);
						model.setData(data == null ? "" : data.toString());
						return model;
					}

					@Override
					String logTag() {
						// TODO Auto-generated method stub
						return null;
					}

				}, (IFailureListener) connection);
	}

	@Override
	public void login(Map<String, Object> input,
			IConnectionNetworkAble<UserLoginVO> connection) {
		// TODO Auto-generated method stub
		post(input, Contacts.ServiceURL.LoginService,
				new CallbackListener<UserLoginVO>(connection) {

					@Override
					UserLoginVO parseResult(JSONObject result)
							throws ProcessException, Exception {
						// TODO Auto-generated method stub
						String data = result.optString("result");
						UserLoginVO vo = new UserLoginVO();
						vo = JSON.parseObject(data, UserLoginVO.class);
						System.out.println("login vo:" + vo.getUsername());
						return vo;
					}

					@Override
					String logTag() {
						// TODO Auto-generated method stub
						return null;
					}

				}, (IFailureListener) connection);
	}

	// 重新设置密码
	@Override
	public void resetPsw(Map<String, Object> input,
			IConnectionNetworkAble<DefaultVO> connection) {
		// TODO Auto-generated method stub
		post(input, Contacts.ServiceURL.ResetPswService,
				new CallbackListener<DefaultVO>(connection) {
					@Override
					DefaultVO parseResult(JSONObject result)
							throws ProcessException, Exception {
						// TODO Auto-generated method stub
						System.out.println(result);
						DefaultVO model = new DefaultVO();
						JSONObject data = result.optJSONObject("result");
						if (data != null) {
							String msg = data.optString("successMsg");
							model.setData(msg);
						}
						return model;
					}

					@Override
					String logTag() {
						// TODO Auto-generated method stub
						return null;
					}

				}, (IFailureListener) connection);
	}

	@Override
	public void getBannerImage(Map<String, Object> input,
			IConnectionNetworkAble<List<BannerVO>> connection) {
		// TODO Auto-generated method stub
		post(input, Contacts.ServiceURL.BannerService,
				new CallbackListener<List<BannerVO>>(connection) {

					@Override
					List<BannerVO> parseResult(JSONObject result)
							throws ProcessException, Exception {
						List<BannerVO> list = null;
						if (isNotNull(result)) {
							String data = result.optString("result");
							System.out.println("banner:" + data);
							if (data != null && !"".equals(data)) {
								list = JSON.parseArray(data, BannerVO.class);
								System.out.println("banner list size:"
										+ list.size());
							}
						}
						return list;
					}

					@Override
					String logTag() {
						return null;
					}
				}, (IFailureListener) connection);
	}

	@Override
	public void getLoanService(Map<String, Object> input,
			IConnectionNetworkAble<List<LoanVO>> connection) {
		// TODO Auto-generated method stub
		post(input, Contacts.ServiceURL.LoansAppService,
				new CallbackListener<List<LoanVO>>(connection) {

					@Override
					List<LoanVO> parseResult(JSONObject result)
							throws ProcessException, Exception {
						List<LoanVO> list = null;
						if (isNotNull(result)) {
							String data = result.optString("result");
							System.out.println("banner:" + data);
							if (data != null && !"".equals(data)) {
								list = JSON.parseArray(data, LoanVO.class);
								System.out.println("banner list size:"
										+ list.size());
							}
						}
						return list;
					}

					@Override
					String logTag() {
						return null;
					}
				}, (IFailureListener) connection);
	}

	@Override
	public void auth(Map<String, Object> input,String serviceName,
			IConnectionNetworkAble<AuthVO> connection) {
		// TODO Auto-generated method stub
		post(input,serviceName, 
				new CallbackListener<AuthVO>(connection) {

					@Override
					AuthVO parseResult(JSONObject result)
							throws ProcessException, Exception {
						AuthVO vo=new AuthVO();
						if (isNotNull(result)) {
							String data = result.optString("result");
							System.out.println("banner:" + data);
							vo.setResult(data);
						}
						return vo;
					}

					@Override
					String logTag() {
						return null;
					}
				}, (IFailureListener) connection);
	}

}
