package com.xiaba2.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xiaba2.util.SessionUtil.UserCookie;

public class SendSMSYUNPIAN {
	// 通用发送接口的http地址
	private static String URI_SEND_SMS = "http://yunpian.com/v1/sms/send.json";

	/**
	 * http://www.yunpian.com/admin/main
	 */
	private static String appKey_yunpian = "b50b67ba4c61690b0e1aca60a34a44b1";

	// 编码格式。发送编码格式统一用UTF-8
	private static String ENCODING = "UTF-8";

	public static String sendSMSYUNPIAN(String phone, String content) {

		String rs = "";

		try {
			String json = sendSms(appKey_yunpian, content, phone);

			ObjectMapper mapper = new ObjectMapper();
			Result rsObj = mapper.readValue(json, Result.class);

			rs = rsObj.getCode() + "";

		} catch (IOException e1) {
			e1.printStackTrace();
		}

		return rs;
	}

	/**
	 * 通用接口发短信
	 *
	 * @param apikey
	 *            apikey
	 * @param text
	 *            　短信内容
	 * @param mobile
	 *            　接受的手机号
	 * @return json格式字符串
	 * @throws IOException
	 */
	public static String sendSms(String apikey, String text, String mobile)
			throws IOException {
		Map<String, String> params = new HashMap<String, String>();
		params.put("apikey", apikey);
		params.put("text", text);
		params.put("mobile", mobile);
		return post(URI_SEND_SMS, params);
	}

	/**
	 * 基于HttpClient 4.3的通用POST方法
	 *
	 * @param url
	 *            提交的URL
	 * @param paramsMap
	 *            提交<参数，值>Map
	 * @return 提交响应
	 */
	public static String post(String url, Map<String, String> paramsMap) {
		CloseableHttpClient client = HttpClients.createDefault();
		String responseText = "";
		CloseableHttpResponse response = null;
		try {
			HttpPost method = new HttpPost(url);
			if (paramsMap != null) {
				List<NameValuePair> paramList = new ArrayList<NameValuePair>();
				for (Map.Entry<String, String> param : paramsMap.entrySet()) {
					NameValuePair pair = new BasicNameValuePair(param.getKey(),
							param.getValue());
					paramList.add(pair);
				}
				method.setEntity(new UrlEncodedFormEntity(paramList, ENCODING));
			}
			response = client.execute(method);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				responseText = EntityUtils.toString(entity);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				response.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return responseText;
	}

	public static class Result {
		private String msg;
		private int code;
		private ResultData result;

		public String getMsg() {
			return msg;
		}

		public void setMsg(String msg) {
			this.msg = msg;
		}

		public int getCode() {
			return code;
		}

		public void setCode(int code) {
			this.code = code;
		}

		public ResultData getResult() {
			return result;
		}

		public void setResult(ResultData result) {
			this.result = result;
		}

	}

	public static class ResultData {

		private int count;
		private float fee;
		private long sid;

		public int getCount() {
			return count;
		}

		public void setCount(int count) {
			this.count = count;
		}

		public float getFee() {
			return fee;
		}

		public void setFee(float fee) {
			this.fee = fee;
		}

		public long getSid() {
			return sid;
		}

		public void setSid(long sid) {
			this.sid = sid;
		}

 

	}

}
