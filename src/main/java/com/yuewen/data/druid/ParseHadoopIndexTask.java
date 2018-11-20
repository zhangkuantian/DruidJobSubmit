package com.yuewen.data.druid;

import io.druid.jackson.DefaultObjectMapper;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.yuewen.data.druid.task.pojo.HadoopTask;

public class ParseHadoopIndexTask {
	private static final ObjectMapper jsonMapper;

  static {
    jsonMapper = new DefaultObjectMapper();
//    jsonMapper.setInjectableValues(new InjectableValues.Std().addValue(ObjectMapper.class, jsonMapper));
  }
	public static void main(String[] args) {
		Gson gson = new Gson();
		String json = getContent();
		System.out.println(json);
		HadoopTask hadoopTask = gson.fromJson(json, HadoopTask.class);
		
//		ObjectMapper mapper = new ObjectMapper();
//		try {
//			HadoopTask hadoopTask = mapper.readValue(json, HadoopTask.class);
//			System.out.println(hadoopTask);
//			
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		HadoopTask hadoopTask = jsonReadWriteRead(json, HadoopTask.class);
		System.out.println(hadoopTask.getType());
		System.out.println(hadoopTask);
	}
	
	public static String getContent(){
		StringBuilder sb = new StringBuilder();
		try(FileInputStream fis = new FileInputStream("D:\\tmp\\20180605\\hadoop_task.json");
			InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
			BufferedReader br = new BufferedReader(isr, 1024)) {
			String tmp = null;
			while(null != (tmp = br.readLine())){
				sb.append(tmp + "\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
	private static <T>  T jsonReadWriteRead(String s, Class<T> klass)
	  {
	    try {
	    	return jsonMapper.readValue(s, klass);
//	      return jsonMapper.readValue(jsonMapper.writeValueAsBytes(jsonMapper.readValue(s, klass)), klass);
	    }
	    catch (Exception e) {
	    	e.printStackTrace();
//	      throw Throwables.propagate(e);
	    }
	    return null;
	  }

}
