package com.easylife.app_wash.wxapi;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.tencent.mm.sdk.constants.ConstantsAPI;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;


/** 微信客户端回调activity示例 */
public class WXEntryActivity extends Activity implements IWXAPIEventHandler {
	// IWXAPI 是第三方app和微信通信的openapi接口
	private IWXAPI api;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		api = WXAPIFactory.createWXAPI(this, "wx1af78a731e6e293d", false);
		api.handleIntent(getIntent(), this);
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onReq(BaseReq req) {
		switch (req.getType()) {
		case ConstantsAPI.COMMAND_GETMESSAGE_FROM_WX:
			// Intent intent = new Intent(this, MainActivity.class);
			// intent.putExtras(getIntent());
			// startActivity(intent);
			finish();
			break;
		// case ConstantsAPI.COMMAND_SHOWMESSAGE_FROM_WX:
		// goToShowMsg((ShowMessageFromWX.Req) req);
		// break;
		default:
			break;
		}
	}

	@Override
	public void onResp(BaseResp resp) {
		Log.d("weixin", "resp.errCode:" + resp.errCode + ",resp.errStr:"
				+ resp.errStr);
		// LogManager.show("weixin", "resp.errCode:" + resp.errCode
		// + ",resp.errStr:" + resp.errStr, 1);
		switch (resp.errCode) {
		case BaseResp.ErrCode.ERR_OK:
			// 分享成功
			break;
		case BaseResp.ErrCode.ERR_USER_CANCEL:
			// 分享取消
			break;
		case BaseResp.ErrCode.ERR_AUTH_DENIED:
			// 分享拒绝
			break;
		}
	}
}
