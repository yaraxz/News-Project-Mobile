package czmec.cn.shl.news.test;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import czmec.cn.shl.news.util.StreamTools;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

public class GetNetPicActivity_2 extends Activity {

	Runnable runnable = new Runnable(){

		@Override
		public void run() {
			//1.����һ�����͵ĵ�ַ
			//String path = "http://y1.ifengimg.com/mappa/2015/06/09/f7aa4e03313848f083509c14910470d1.jpg";
			String path = "http://172.19.16.237:8080/News_PC/photos/pic/Winter.jpg";
			try {
				//ͨ��URL���е�ַ����
				URL url = new URL(path);
				//����������
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				//��������ķ�ʽ
				conn.setRequestMethod("GET");
				//��������ʱ�ĳ�ʱ��ʱ��
				conn.setConnectTimeout(5*1000);
				//2.��ȡ�����е�ͼƬ��
				InputStream is = conn.getInputStream();
				//��ͼƬ��ת���ɶ������ֽ���
				byte[] data = StreamTools.streamToBytes(is);
				//�½�һ��λͼ
				final Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
				
				//3.����android���棬�����߳��е���UI�߳�
				runOnUiThread(new Runnable()
				{
					@Override
					public void run() {
						ImageView image = (ImageView) GetNetPicActivity_2.this.findViewById(R.id.netpic);
						image.setImageBitmap(bitmap);
						
					}
					
				}
						);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_get_net_pic_activity_2);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.get_net_pic_activity_2, menu);
		return true;
	}

	public void getPic(View v)
	{
		new Thread(runnable).start();
	}
}
