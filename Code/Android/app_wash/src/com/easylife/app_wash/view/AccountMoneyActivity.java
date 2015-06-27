package com.easylife.app_wash.view;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.easylife.app_wash.R;
import com.easylife.app_wash.widget.BaseActivity;
import com.easylife.app_wash.widget.ConnectionNetworkAbleUIBase;
import com.sihehui.section_network.http.info.ProcessException;
import com.sihehui.section_network.util.Contacts;
import com.sihehui.section_vo.vo.AccountMoneyVO;

public class AccountMoneyActivity extends BaseActivity {
	TextView mBalance, mBalanceAvaliable, mCollectCorpusAndInterest,
			mFreezeAmount, mTotalInvestAmount, mCollectProfit, mReceivedCorpus,
			mReceviedInterest, mCollectCorpus, mCollectInterest;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		mBalance = (TextView) findViewById(R.id.txt_balance);
		mBalanceAvaliable = (TextView) findViewById(R.id.txt_balanceAvaliable);
		mCollectCorpusAndInterest = (TextView) findViewById(R.id.txt_collectCorpusAndInterest);
		mFreezeAmount = (TextView) findViewById(R.id.txt_freezeAmount);
		mTotalInvestAmount = (TextView) findViewById(R.id.txt_totalInvestAmount);
		mCollectProfit = (TextView) findViewById(R.id.txt_collectProfit);
		mReceivedCorpus = (TextView) findViewById(R.id.txt_receivedCorpus);
		mReceviedInterest = (TextView) findViewById(R.id.txt_receviedInterest);
		mCollectCorpus = (TextView) findViewById(R.id.txt_collectCorpus);
		mCollectInterest = (TextView) findViewById(R.id.txt_collectInterest);
        loadData();
	}

	private void loadData() {
		AccountMoneyListener listener = new AccountMoneyListener(
				AccountMoneyActivity.this);
		Map<String, Object> input = new HashMap<String, Object>();
		input.put("name", mUser.getUserName());
		mServerCxdAPI.getObjectData(input,
				Contacts.ServiceURL.AccountBalanceAppService, listener,
				AccountMoneyVO.class);
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();

	}

	@Override
	protected void setContentView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.accountmoney);
	}

	@Override
	protected String getHeaderTitle() {
		// TODO Auto-generated method stub
		return "账户余额";
	}

	private class AccountMoneyListener extends
			ConnectionNetworkAbleUIBase<AccountMoneyVO> {

		public AccountMoneyListener(Context context) {
			super(context);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onSuccessed(AccountMoneyVO result) throws ProcessException {
			// TODO Auto-generated method stub
			if (result == null) {
				result=new AccountMoneyVO();
			}
			mBalance.setText("¥"+String.valueOf(result.getBalance()));
			mBalanceAvaliable.setText("¥"+String.valueOf(result.getBalanceAvaliable()));
			mCollectCorpusAndInterest.setText("¥"+String.valueOf(result.getCollectCorpusAndInterest()==null?"0.0":result.getCollectCorpus()));
			mFreezeAmount.setText("¥"+String.valueOf(result.getFreezeAmount()));
			mTotalInvestAmount.setText("¥"+String.valueOf(result.getTotalInvestAmount()));
			
			mCollectProfit.setText("¥"+String.valueOf(result.getCollectProfit()==null?"0.0":result.getCollectProfit()));
			mReceivedCorpus.setText("¥"+String.valueOf(result.getReceivedCorpus()==null?"0.0":result.getReceivedCorpus()));
			mReceviedInterest.setText("¥"+String.valueOf(result.getReceviedInterest()==null?"0.0":result.getReceviedInterest()));
			mCollectCorpus.setText("¥"+String.valueOf(result.getCollectCorpus()));
			mCollectInterest.setText("¥"+String.valueOf(result.getCollectInterest()));
		}

	}

}
