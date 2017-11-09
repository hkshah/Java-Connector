package com.rapidvalue.master.mycustom.vo;

public class PushFetchVO {
	private Integer slId;
	private String messageName;
	private String subject;
	private String fromRole;
	private String toRole;
	private String status;
	private String moreInfoRole;
	private String itemKey;
	private Integer notificationId;
	private String navigationPath;

	public Integer getNotificationId() {
		return notificationId;
	}

	public void setNotificationId(Integer notificationId) {
		this.notificationId = notificationId;
	}

	public Integer getSlId() {
		return slId;
	}

	public void setSlId(Integer slId) {
		this.slId = slId;
	}

	public String getMessageName() {
		return messageName;
	}

	public void setMessageName(String messageName) {
		this.messageName = messageName;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getFromRole() {
		return fromRole;
	}

	public void setFromRole(String fromRole) {
		this.fromRole = fromRole;
	}

	public String getToRole() {
		return toRole;
	}

	public void setToRole(String toRole) {
		this.toRole = toRole;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMoreInfoRole() {
		return moreInfoRole;
	}

	public void setMoreInfoRole(String moreInfoRole) {
		this.moreInfoRole = moreInfoRole;
	}

	public String getItemKey() {
		return itemKey;
	}

	public void setItemKey(String itemKey) {
		this.itemKey = itemKey;
	}

	public String getNavigationPath() {
		return navigationPath;
	}

	public void setNavigationPath(String navigationPath) {
		this.navigationPath = navigationPath;
	}

}
