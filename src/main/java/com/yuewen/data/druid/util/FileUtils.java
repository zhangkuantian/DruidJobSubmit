package com.yuewen.data.druid.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class FileUtils {
	
	public static String readStringFromFile(String path){
		StringBuilder sb = new StringBuilder();
		try (FileInputStream fis = new FileInputStream(path);
			 InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
			 BufferedReader br = new BufferedReader(isr, 1024)){
			String tmp = null;
			while(null != (tmp = br.readLine())){
				sb.append(tmp);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	public static String readStringFromFile(String path, String paths, String intervals){
		StringBuilder sb = new StringBuilder();
		try (FileInputStream fis = new FileInputStream(path);
			 InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
			 BufferedReader br = new BufferedReader(isr, 1024)){
			String tmp = null;
			while(null != (tmp = br.readLine())){
				System.out.println("---" + tmp);
				if(tmp.trim().startsWith("\"paths")){
					sb.append("\"paths\" :\"" + paths + "\"");
				} else if(tmp.trim().startsWith("\"intervals\"")){
					sb.append("\"intervals\" : [\"" + intervals + "\"]");
				} else{
					sb.append(tmp);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	public static void writeString2File(String str, String path){
		
	}
	
}
