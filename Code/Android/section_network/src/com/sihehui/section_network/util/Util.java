package com.sihehui.section_network.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Set;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Util {
	
	public static String getShowTime(Long time) {
		if (time == null) {
			return null;
		}
		long offset = System.currentTimeMillis() - time;
		Calendar mCalendar = Calendar.getInstance();
		mCalendar.set(Calendar.HOUR_OF_DAY, 0);
		mCalendar.set(Calendar.MINUTE, 0);
		mCalendar.set(Calendar.SECOND, 0);
		mCalendar.setTimeInMillis(mCalendar.getTimeInMillis() + offset);

		Date d = mCalendar.getTime();
		// Date d = new Date(timeLeft); 得到格林尼治时间
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String format = sdf.format(d);
		// android.util.Log.d("system", "time=" + time);
		String hour, minute, second;
		if (format.contains(":")) {
			int index = format.indexOf(":");
			int indexa = format.lastIndexOf(":");
			hour = format.substring(0, index);
			if (hour.startsWith("0")) {
				hour = hour.substring(1);
			}
			minute = format.substring(index + 1, indexa);
			if (minute.startsWith("0")) {
				minute = minute.substring(1);
			}
			second = format.substring(indexa + 1);
		} else {
			hour = "";
			minute = "--";
			second = "--";
		}
		if (offset > 0 && offset < 1000 * 60 * 60 * 24) {
			// mCalendar.setTimeInMillis(offset);
			// int value = 0;
			if (offset < 1000 * 60) {
				return "刚刚";
			} else if (offset >= 1000 * 60 && offset < 3600000) {
				// value = minute;
				return (minute + "分钟前");
			} else if (offset >= 3600000) {
				// value = mCalendar.get(Calendar.HOUR);
				return (hour + "小时前");
			}
		}
		mCalendar.setTimeInMillis(time);
		return ((mCalendar.get(Calendar.MONTH) + 1) + "-" + mCalendar
				.get(Calendar.DAY_OF_MONTH));

	}

	public static File bitmap2File(Context context, String fileName,
			Bitmap bitmap) {
		// create a file to write bitmap data
		File f = new File(context.getCacheDir(), fileName);
		try {
			f.createNewFile();
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			bitmap.compress(CompressFormat.JPEG, 50 /* ignored for PNG */, bos);
			byte[] bitmapdata = bos.toByteArray();

			// write the bytes in file
			FileOutputStream fos = new FileOutputStream(f);
			fos.write(bitmapdata);
			bos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return f;

	}

	public static String map2Json(Map<String, Object> map) {
		if (map==null||map.isEmpty())
			return "{}";
		StringBuilder sb = new StringBuilder(map.size() << 4);
		sb.append('{');
		Set<String> keys = map.keySet();
		for (String key : keys) {
			Object value = map.get(key);
			sb.append('\"');
			sb.append(key);
			sb.append('\"');
			sb.append(':');
			sb.append(toJson(value));
			sb.append(',');
		}
		//
		// 将最后的
		// ','
		// 变为
		// '}':
		sb.setCharAt(sb.length() - 1, '}');
		return sb.toString();
	}

	static String string2Json(String s) {
		StringBuilder sb = new StringBuilder(s.length() + 20);
		sb.append('\"');
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			switch (c) {
			case '\"':
				sb.append("\\\"");
				break;
			case '\\':
				sb.append("\\\\");
				break;
			case '/':
				sb.append("\\/");
				break;
			case '\b':
				sb.append("\\b");
				break;
			case '\f':
				sb.append("\\f");
				break;
			case '\n':
				sb.append("\\n");
				break;
			case '\r':
				sb.append("\\r");
				break;
			case '\t':
				sb.append("\\t");
				break;
			default:
				sb.append(c);
			}
		}
		sb.append('\"');
		return sb.toString();
	}

	static String number2Json(Number number) {
		return number.toString();
	}

	static String boolean2Json(Boolean bool) {
		return bool.toString();
	}

	static String array2Json(Object[] array) {
		if (array.length == 0)
			return "[]";
		StringBuilder sb = new StringBuilder(array.length << 4);
		sb.append('[');
		for (Object o : array) {
			sb.append(toJson(o));
			sb.append(',');
		}
		//
		// 将最后添加的
		// ','
		// 变为
		// ']':
		sb.setCharAt(sb.length() - 1, ']');
		return sb.toString();
	}

	public static String toJson(Object o) {
		if (o == null)
			return "null";
		if (o instanceof String)
			return string2Json((String) o);
		if (o instanceof Boolean)
			return boolean2Json((Boolean) o);
		if (o instanceof Number)
			return number2Json((Number) o);
		if (o instanceof Map)
			return map2Json((Map<String, Object>) o);
		if (o instanceof Object[])
			return array2Json((Object[]) o);
		throw new RuntimeException("Unsupported type: "
				+ o.getClass().getName());
	}

	// 监测网络环境
	public static boolean checkNetwork(Context con) {
		if (con != null) {
			ConnectivityManager conn = (ConnectivityManager) con
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo net = conn.getActiveNetworkInfo();
			if (net != null && net.isAvailable() && net.isConnected()) {
				return true;
			}
		}
		return false;
	}
}
