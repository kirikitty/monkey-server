package com.kiri.monkey;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NewsController {

	private static Logger logger = LoggerFactory
			.getLogger(NewsController.class);

	@RequestMapping("/test")
	public String test() {
		return "Hello";
	}

	@RequestMapping("/news")
	public String getNews() {
		CloseableHttpClient http = HttpClientBuilder.create().build();
		HttpGet httpGet = new HttpGet(
				"https://cre.dp.sina.cn/api/v3/get?cateid=1o&cre=tianyi&mod=wnews&merge=3&statics=1&length=20");
		try {
			CloseableHttpResponse resp = http.execute(httpGet);
			String json = null;
			try (InputStream is = resp.getEntity().getContent()) {
				ByteArrayOutputStream bos = new ByteArrayOutputStream();
				byte[] buffer = new byte[4096];
				int len = 0;
				while((len = is.read(buffer)) > 0) {
					bos.write(buffer, 0, len);
				}
				
				json = new String(bos.toByteArray());
			}
			logger.info("s = {}", json);
			return json;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping("/news/163")
	public String get163News() {
		CloseableHttpClient http = HttpClientBuilder.create().build();
		HttpGet httpGet = new HttpGet(
				"https://3g.163.com/touch/nc/api/user/recommend/GuessLike/1-10-10-10.do?offset=0&size=20");
		try {
			CloseableHttpResponse resp = http.execute(httpGet);
			String json = null;
			try (InputStream is = resp.getEntity().getContent()) {
				ByteArrayOutputStream bos = new ByteArrayOutputStream();
				byte[] buffer = new byte[4096];
				int len = 0;
				while((len = is.read(buffer)) > 0) {
					bos.write(buffer, 0, len);
				}
				
				json = new String(bos.toByteArray());
			}
			logger.info("s = {}", json);
			return json;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
