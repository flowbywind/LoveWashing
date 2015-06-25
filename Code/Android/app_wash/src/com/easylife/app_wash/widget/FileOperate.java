package com.easylife.app_wash.widget;

import java.io.File;
import java.io.IOException;

import android.os.Environment;

public class FileOperate extends File {

	private static final long serialVersionUID = 6359476869554251131L;
	private final static String CACHE = "/cxdimagecache";

	public FileOperate(File dir, String name) {
		super(dir, name);
	}

	public static File isExistsFile() {
		File sdDir = null;
		File cachefile = null;
		boolean sdCardExist = Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED);// 判断sdcard是否存在
		if (sdCardExist) {
			sdDir = Environment.getExternalStorageDirectory();// 得到sdcard路径
			cachefile = new File(sdDir, CACHE);
			if (!cachefile.exists()) {
				cachefile.mkdirs();
			}
		} else {
			cachefile = new File("/data/data/com.easylife.app_wash/files", CACHE);
			if (!cachefile.exists()) {
				cachefile.mkdirs();
			}
		}

		return cachefile;
	}

	// 删除指定的文件，主要用于更改头像
	public static void deleteFile(String fileName) {
		String strfilename = MD5.getMD5(fileName)
				+ fileName.substring(fileName.lastIndexOf("."));
		File cache = isExistsFile();
		File file = new File(cache, strfilename);
		if (file.exists()) {
			file.delete();
		}

	}

	public static File createFile(File path, String strfilename) {
		File file = new File(path, strfilename);
		try {
			file.createNewFile();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return file;
	}
}
