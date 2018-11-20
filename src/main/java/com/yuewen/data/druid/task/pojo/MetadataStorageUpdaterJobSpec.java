package com.yuewen.data.druid.task.pojo;

import com.yuewen.data.druid.task.pojo.itf.PasswordProvider;
import com.yuewen.data.druid.task.pojo.itf.Supplier;

public class MetadataStorageUpdaterJobSpec implements
		Supplier<MetadataStorageConnectorConfig> {
	public String type;
	public String connectURI;
	public String user;
	private PasswordProvider passwordProvider;
	public String segmentTable;
	
	@Override
	public MetadataStorageConnectorConfig get() {
		
		return new MetadataStorageConnectorConfig(){
	      @Override
	      public String getConnectURI(){
	        return connectURI;
	      }
	      @Override
	      public String getUser(){
	        return user;
	      }
	      @Override
	      public String getPassword(){
	        return passwordProvider == null ? null : passwordProvider.getPassword();
	      }
	    };
	}
	
//	public MetadataStorageTablesConfig getMetadataStorageTablesConfig(){
//	    return new MetadataStorageTablesConfig(
//	        null,
//	        null,
//	        segmentTable,
//	        null,
//	        null,
//	        null,
//	        null,
//	        null,
//	        null,
//	        null,
//	        null
//	    );
//	  }
}
