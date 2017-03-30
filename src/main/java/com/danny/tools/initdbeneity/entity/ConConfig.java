package com.danny.tools.initdbeneity.entity;

public class ConConfig {
	private String url;
	private String user;
	private String pwd;
	private String dbName;

	public ConConfig(String url, String user, String pwd) {
		super();
		this.url = url;
		this.user = user;
		this.pwd = pwd;
	}

	public ConConfig(String url, String user, String pwd, String dbName) {
		super();
		this.url = url;
		this.user = user;
		this.pwd = pwd;
		this.dbName = dbName;
	}

	public String getUrl() {
		if (dbName != null) {
			return url + ";databaseName=" + dbName;
		}
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}
}
