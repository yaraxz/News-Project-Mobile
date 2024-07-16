package czmec.cn.shl.news.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/*
 * ��һ��inputstream�����������ת����һ��byte[]
 * ������
 */
public class StreamTools {

	public static byte[] streamToBytes(InputStream in )
	{
		//����һ���ֽ�����������Ż��������
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		//����ÿһ��ת�����ֽ�����
		byte[] buffer = new byte[1024];
		//��ת�������г��ȣ�
		int len = 0;
		//����ת��
		try {
			while((len=in.read(buffer))!=-1)
			{
				//��ÿ��ת�����ֽ�д���ڴ�
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
