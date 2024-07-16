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
			//1.定义一个发送的地址
			//String path = "http://y1.ifengimg.com/mappa/2015/06/09/f7aa4e03313848f083509c14910470d1.jpg";
			String path = "http://172.19.16.237:8080/News_PC/photos/pic/Winter.jpg";
			try {
				//通过URL进行地址解析
				URL url = new URL(path);
				//打开网络连接
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				//设置请求的方式
				conn.setRequestMethod("GET");
				//设置请求时的超时的时间
				conn.setConnectTimeout(5*1000);
				//2.获取网络中的图片流
				InputStream is = conn.getInputStream();
				//将图片流转换成二进制字节码
				byte[] data = StreamTools.streamToBytes(is);
				//新建一个位图
				final Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
				
				//3.更新android界面，在子线程中调用UI线程
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
