package com.rapidvalue.master.mycustom.service;

import com.rapidvalue.master.common.exception.MasterException;
import com.rapidvalue.master.mycustom.vo.PushFetchResponseVO;

public interface UserService {

	public void sendPushNotification() throws MasterException;

	public PushFetchResponseVO getPushNotificationDetail() throws MasterException;

}