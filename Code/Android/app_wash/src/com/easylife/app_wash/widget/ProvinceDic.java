package com.easylife.app_wash.widget;

import java.util.HashMap;
import java.util.Map;

public class ProvinceDic {
	static Map<String, String> all = new HashMap<String, String>();
	static {
		all.put("110000", "北京市");
		all.put("120000", "天津市");
		all.put("130000", "河北省");
		all.put("140000", "山西省");
		all.put("150000", "内蒙古");
		all.put("210000", "辽宁省");
		all.put("220000", "吉林省");
		all.put("230000", "黑龙江省");
		all.put("310000", "上海市");
		all.put("320000", "江苏省");
		all.put("330000", "浙江省");
		all.put("340000", "安徽省");
		all.put("350000", "福建省");
		all.put("360000", "江西省");
		all.put("370000", "山东省");
		all.put("410000", "河南省");
		all.put("420000", "湖北省");
		all.put("430000", "湖南省");
		all.put("440000", "广东省");
		all.put("450000", "广西壮族自治区");
		all.put("460000", "海南省");
		all.put("500000", "重庆市");
		all.put("510000", "四川省");
		all.put("520000", "贵州省");
		all.put("530000", "云南省");
		all.put("540000", "西藏自治区");
		all.put("610000", "陕西省");
		all.put("620000", "甘肃省");
		all.put("630000", "青海省");
		all.put("640000", "宁夏回族自治区");
		all.put("650000", "新疆维吾尔自治区");
		all.put("710000", "台湾省");
		all.put("810000", "香港特别行政区");
		all.put("820000", "澳门特别行政区");
	}

	public static String codeToName(String code) {
		return all.get(code);

	}
}
