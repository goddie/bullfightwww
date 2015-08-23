package com.xiaba2.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class SendSMS {
	private static String Url = "http://121.199.16.178/webservice/sms.php?method=Submit";

	
	
	/**
	 * http://www.yunpian.com/api/sms.html
	 */
	private static String Url_yunpian = "http://yunpian.com/v1/sms/send.json";
	
	/**
	 * http://www.yunpian.com/admin/main
	 */
	private static String appKey_yunpian = "b50b67ba4c61690b0e1aca60a34a44b1";

	 

	public static String sendSMS(String username, String password,
			String phone, String content) {

		String rs = "";

		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {

			List<BasicNameValuePair> formparams = new ArrayList<BasicNameValuePair>();
			formparams.add(new BasicNameValuePair("account", username));
			formparams.add(new BasicNameValuePair("password", password));
			formparams.add(new BasicNameValuePair("mobile", phone));
			formparams.add(new BasicNameValuePair("content", content));
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams,
					Consts.UTF_8);

			HttpPost httppost = new HttpPost(Url);

			httppost.setHeader("ContentType",
					"application/x-www-form-urlencoded;charset=UTF-8");

			httppost.setEntity(entity);

			// Create a custom response handler
			ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

				@Override
				public String handleResponse(final HttpResponse response)
						throws ClientProtocolException, IOException {
					int status = response.getStatusLine().getStatusCode();
					if (status >= 200 && status < 300) {
						HttpEntity entity = response.getEntity();
						return entity != null ? EntityUtils.toString(entity)
								: null;
					} else {
						throw new ClientProtocolException(
								"Unexpected response status: " + status);
					}
				}

			};

			String responseBody = "";

			responseBody = httpclient.execute(httppost, responseHandler);

			// System.out.println("----------------------------------------");
			// System.out.println(responseBody);

			Document doc = DocumentHelper.parseText(responseBody);
			Element root = doc.getRootElement();

			String code = root.elementText("code");
			String msg = root.elementText("msg");
			String smsid = root.elementText("smsid");

			System.out.println(code);
			System.out.println(msg);
			System.out.println(smsid);

			rs = code;

			if (code == "2") {

				System.out.println("短信提交成功");
			}

		} catch (Exception e) {

			Logger.getGlobal().log(Level.INFO, e.getMessage());

		} finally {

			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		return rs;

	}

	public static int getRand() {
		int max = 9;
		int min = 0;
		Random random = new Random();
		int s = random.nextInt(max) % (max - min + 1) + min;
		return s;
	}

}
