package com.sihehui.section_network.http.json;

import java.util.Map;

public interface MultiPartRequest {

//	public void addFileUpload(String param, Object file);
//
//	public void addStringUpload(String param, String content);

	public Map<String, Object> getFileUploads();

	public Map<String, String> getStringUploads();
}
