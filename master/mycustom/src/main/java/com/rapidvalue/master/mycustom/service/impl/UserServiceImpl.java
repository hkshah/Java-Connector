package com.rapidvalue.master.mycustom.service.impl;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rapidvalue.master.common.exception.MasterException;
import com.rapidvalue.master.dao.ProcedureDao;
import com.rapidvalue.master.dao.exception.DaoException;
import com.rapidvalue.master.mycustom.service.UserService;
import com.rapidvalue.master.mycustom.util.Constant;
import com.rapidvalue.master.mycustom.util.UserProperty;
import com.rapidvalue.master.mycustom.vo.NotificationRespVO;
import com.rapidvalue.master.mycustom.vo.NotificationVO;
import com.rapidvalue.master.mycustom.vo.PushFetchResponseVO;
import com.rapidvalue.master.mycustom.vo.PushFetchVO;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private ProcedureDao procedureDao;

	@Autowired
	UserProperty userProperty;

	private static final Logger LOG = Logger.getLogger(UserServiceImpl.class);
	public String tester = Constant.ENABLE;
	public String tester2;

	@Scheduled(fixedDelay = 60000)
	@Async
	@Override
	public void sendPushNotification() {
		tester2 = Constant.ENABLE;
		if ((Constant.ENABLE).equals(userProperty.getNotificationEnable())) {

			LOG.info("Scheduler started");

			final MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
			headers.add(Constant.ORACLE_MOBILE_BACKEND_ID, userProperty.getOracleMobBackendId());
			headers.add(Constant.AUTHORIZATION, Constant.BASIC + userProperty.getAuthorizationBasic());
			headers.add(Constant.CONTENT_TYPE, userProperty.getContentType());
			PushFetchResponseVO pushNotification = null;
			String result = null;
			try {
				pushNotification = getPushNotificationDetail();
				RestTemplate restTemplate = null;
				NotificationVO notificationVO = null;
				List<String> userList = null;
				HttpEntity<NotificationVO> request = null;

				List<PushFetchVO> pushFetchList = pushNotification.getPushNotFetchDtls();
				if (pushFetchList != null && !pushFetchList.isEmpty()) {
					for (final PushFetchVO pushFetchVO : pushFetchList) {
						restTemplate = new RestTemplate();
						restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
						notificationVO = new NotificationVO();
						notificationVO.setMessage(pushFetchVO.getSubject());
						userList = new ArrayList<>();
						userList.add(pushFetchVO.getToRole());
						notificationVO.setUsers(userList);
						// notificationVO.setTitle("iExpense");
						notificationVO.setState(pushFetchVO.getNavigationPath());

						LOG.info("Message : : " + pushFetchVO.getSubject());
						LOG.info("Role : : " + pushFetchVO.getToRole());
						LOG.info("State : : " + pushFetchVO.getNavigationPath());

						request = new HttpEntity<NotificationVO>(notificationVO, headers);

						LOG.info("RequestBody : : " + request.getBody().toString());
						// ResponseEntity<String> res =
						// getCustomRestTemplate().exchange(userProperty.getMcsBaseURL()
						// + Constant.PUSH_NOT_RELATIVE_URL, HttpMethod.POST,
						// request, String.class);

						ResponseEntity<NotificationRespVO> res = getCustomRestTemplate().exchange(
								new URI(userProperty.getMcsBaseURL() + Constant.PUSH_NOT_RELATIVE_URL), HttpMethod.POST,
								request, NotificationRespVO.class);
						if (res != null) {

							LOG.info("Statuscode:" + res.getStatusCode());

							LOG.info("Response : " + res.getBody() != null ? res.getBody().toString()
									: "Response is empty");
						}
						/*
						 * result = getCustomRestTemplate().postForObject(
						 * userProperty.getMcsBaseURL() +
						 * Constant.PUSH_NOT_RELATIVE_URL, request,
						 * String.class);
						 */

						// LOG.info("Result " + result);
					}
				}

			} catch (MasterException e) {
				LOG.info("error" + e);
			} catch (Exception e) {
				LOG.info("error" + e);
				LOG.info("result" + result);
			}

			LOG.info("Scheduler End");
		}

	}

	public static RestTemplate getCustomRestTemplate() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();
		messageConverter.setObjectMapper(mapper);
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
		messageConverters.add(messageConverter);
		return new RestTemplate(messageConverters);
	}

	@Override
	public PushFetchResponseVO getPushNotificationDetail() throws MasterException {
		final PushFetchResponseVO responseVO = new PushFetchResponseVO();
		try {
			procedureDao.execute(Constant.PROCEDURE_PUSH_NOT_FETCH, responseVO);
		} catch (DaoException e) {
			LOG.error("Get push notification detail Procedure execution Failed::" + e);
			throw new MasterException(e.getCode(), e.getMessage(), e);
		}
		return responseVO;
	}
}