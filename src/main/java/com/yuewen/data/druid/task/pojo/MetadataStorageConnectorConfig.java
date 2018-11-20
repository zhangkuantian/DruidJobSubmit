package com.yuewen.data.druid.task.pojo;

import com.yuewen.data.druid.task.pojo.itf.PasswordProvider;

public class MetadataStorageConnectorConfig {
	private boolean createTables = true;
	private String host = "localhost";
	private int port = 1527;
	private String connectURI;
	private String user = null;
	private PasswordProvider passwordProvider;
	public MetadataStorageConnectorConfig() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MetadataStorageConnectorConfig(boolean createTables, String host,
			int port, String connectURI, String user,
			PasswordProvider passwordProvider) {
		super();
		this.createTables = createTables;
		this.host = host;
		this.port = port;
		this.connectURI = connectURI;
		this.user = user;
		this.passwordProvider = passwordProvider;
	}
	public boolean isCreateTables() {
		return createTables;
	}
	public void setCreateTables(boolean createTables) {
		this.createTables = createTables;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getConnectURI() {
		return connectURI;
	}
	public void setConnectURI(String connectURI) {
		this.connectURI = connectURI;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword(){
	    return passwordProvider == null ? null : passwordProvider.getPassword();
	}
	public void setPasswordProvider(PasswordProvider passwordProvider) {
		this.passwordProvider = passwordProvider;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((connectURI == null) ? 0 : connectURI.hashCode());
		result = prime * result + (createTables ? 1231 : 1237);
		result = prime * result + ((host == null) ? 0 : host.hashCode());
		result = prime
				* result
				+ ((passwordProvider == null) ? 0 : passwordProvider.hashCode());
		result = prime * result + port;
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MetadataStorageConnectorConfig other = (MetadataStorageConnectorConfig) obj;
		if (connectURI == null) {
			if (other.connectURI != null)
				return false;
		} else if (!connectURI.equals(other.connectURI))
			return false;
		if (createTables != other.createTables)
			return false;
		if (host == null) {
			if (other.host != null)
				return false;
		} else if (!host.equals(other.host))
			return false;
		if (passwordProvider == null) {
			if (other.passwordProvider != null)
				return false;
		} else if (!passwordProvider.equals(other.passwordProvider))
			return false;
		if (port != other.port)
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "MetadataStorageConnectorConfig [createTables=" + createTables
				+ ", host=" + host + ", port=" + port + ", connectURI="
				+ connectURI + ", user=" + user + ", passwordProvider="
				+ passwordProvider + "]";
	}
}