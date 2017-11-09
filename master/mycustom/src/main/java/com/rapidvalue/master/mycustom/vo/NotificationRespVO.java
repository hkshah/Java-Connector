
package com.rapidvalue.master.mycustom.vo;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NotificationRespVO {
	@JsonProperty("id")
	private String id;
	@JsonProperty("template")
	private TemplateVO template;
	@JsonProperty("users")
	private List users;
	@JsonProperty("roles")
	private List roles;
	@JsonProperty("notificationTokens")
	private List notificationTokens;
	@JsonProperty("status")
	private String status;
	@JsonProperty("createdOn")
	private Date createdOn;
	@JsonProperty("links")
	private List links;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public List getUsers() {
		return users;
	}

	public void setUsers(List users) {
		this.users = users;
	}

	public List getRoles() {
		return roles;
	}

	public void setRoles(List roles) {
		this.roles = roles;
	}

	public List getNotificationTokens() {
		return notificationTokens;
	}

	public void setNotificationTokens(List notificationTokens) {
		this.notificationTokens = notificationTokens;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public List getLinks() {
		return links;
	}

	public void setLinks(List links) {
		this.links = links;
	}

	public TemplateVO getTemplate() {
		return template;
	}

	public void setTemplate(TemplateVO template) {
		this.template = template;
	}

	@Override
	public String toString() {
		return "Response [id=" + id + ", status=" + status + ", createdOn=" + createdOn + "]";
	}

}