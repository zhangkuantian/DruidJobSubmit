package com.yuewen.data.druid;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import com.yuewen.data.druid.util.FileUtils;

public class SubmitJob {

	public static void main(String[] args) {
		String result = FileUtils.readStringFromFile("d:/tmp/t_ed_qqbook_order_book_olap.json", "/qidian/offline_olap/t_ed_qqbook_order_book_olap/ds=20181029", "2018-10-29T00:00:00/2018-10-31T00:00:00");
		System.out.println(result);
	}
	
	public static String submit(String jsonMsg, String hostPath){
		String result = null;
		CloseableHttpClient httpclient = HttpClientBuilder.create().build();
		RequestConfig.Builder builder = RequestConfig.custom().setConnectTimeout(2000).setSocketTimeout(2000);
		RequestConfig config = builder.build();
		HttpPost post = new HttpPost("");
		post.setHeader("Content-type", "application/json; charset=utf-8");
		
		
		return result;
	}
	
}
