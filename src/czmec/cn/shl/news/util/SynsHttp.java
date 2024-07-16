package czmec.cn.shl.news.util;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class SynsHttp {

	public static byte[] readParse(String urlPath) throws Exception {

		ByteArrayOutputStream outStream = new ByteArrayOutputStream();

		byte[] data = new byte[1024];

		int len = 0;
		// get方式获取数据
		URL url = new URL(urlPath);

		HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		conn.setRequestMethod("GET");

		conn.setConnectTimeout(5000);
		

		InputStream inStream = conn.getInputStream();

		while ((len = inStream.read(data)) != -1) {

			outStream.write(data, 0, len);

		}

		inStream.close();

		return outStream.toByteArray();

	}
}
