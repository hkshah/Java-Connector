package com.rapidvalue.master.mycustom.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UserProperty {

	@Autowired
	private @Value("${pushnotification.enable}") String notificationEnable;
	@Autowired
	private @Value("${oracle.mobile.backend.id}") String oracleMobBackendId;
	@Autowired
	private @Value("${authorization.basic}") String authorizationBasic;
	@Autowired
	private @Value("${content.type}") String contentType;
	@Autowired
	private @Value("${mcs.baseurl}") String mcsBaseURL;

	public String getNotificationEnable() {
		return notificationEnable;
	}

	public void setNotificationEnable(String notificationEnable) {
		this.notificationEnable = notificationEnable;
	}

	public String getOracleMobBackendId() {
		return oracleMobBackendId;
	}

	public void setOracleMobBackendId(String oracleMobBackendId) {
		this.oracleMobBackendId = oracleMobBackendId;
	}

	public String getAuthorizationBasic() {
		return authorizationBasic;
	}

	public void setAuthorizationBasic(String authorizationBasic) {
		this.authorizationBasic = authorizationBasic;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getMcsBaseURL() {
		return mcsBaseURL;
	}

	public void setMcsBaseURL(String mcsBaseURL) {
		this.mcsBaseURL = mcsBaseURL;
	}
}