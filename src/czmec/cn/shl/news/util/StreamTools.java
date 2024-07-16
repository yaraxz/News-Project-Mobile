package czmec.cn.shl.news.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/*
 * 讲一个inputstream流里面的内容转换成一个byte[]
 * 工具类
 */
public class StreamTools {

	public static byte[] streamToBytes(InputStream in )
	{
		//定义一个字节数组用来存放缓冲输出流
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		//定义每一次转换的字节数组
		byte[] buffer = new byte[1024];
		//在转换过程中长度；
		int len = 0;
		//进行转换
		try {
			while((len=in.read(buffer))!=-1)
			{
				//将每次转换的字节写入内存
				baos.write(buffer,0,len);
			}
			in.close();
			baos.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return baos.toByteArray();
	}
}
