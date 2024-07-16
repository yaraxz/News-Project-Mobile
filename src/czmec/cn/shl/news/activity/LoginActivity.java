package czmec.cn.shl.news.activity;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.json.JSONObject;

import czmec.cn.shl.news.config.Config;
import czmec.cn.shl.news.test.MainActivity2;
import czmec.cn.shl.news.test.R;
import czmec.cn.shl.news.test.R.layout;
import czmec.cn.shl.news.test.R.menu;
import czmec.cn.shl.news.util.StreamTools;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {

	//将APP页面中的用户名、密码以及登陆按钮、注册按钮定义
	private EditText t_login_name,t_login_password;
	private Button login_btn,register_btn;
	private int uid;
	private String uName ,uPass,path,result;
	
	//定义一个初始化的方法
	private void initData()
	{
		t_login_name = (EditText) this.findViewById(R.id.login_name);
		t_login_password = (EditText) this.findViewById(R.id.login_password);
		login_btn = (Button) this.findViewById(R.id.login_btn);
		register_btn = (Button) this.findViewById(R.id.register_btn);
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		//初始化界面中的组件
		initData();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	public void loginoperate(View v)
	{
		uName = t_login_name.getText().toString();//用户输入的用户名
		uPass = t_login_password.getText().toString();//用户输入的密码
		if(uName.trim().length() == 0 || uPass.trim().length() == 0)
		{
			//提示用户名和密码不能为空
			Toast.makeText(LoginActivity.this, R.string.no_null, Toast.LENGTH_SHORT).show();
		}
		else//不为
		{
			try {
				uName = URLEncoder.encode(uName,"gbk");//将用户名进行编码转换
				uPass = URLEncoder.encode(uPass,"gbk");
				path = Config.ServerAddress + "UserLoginAppServlet?userName=" + uName + "&password=" + uPass;
				//开启一个新的线程
				new Thread(new Runnable()
				{
					@Override
					public void run() {						
						try {
							//根据URL获取网络资源
							//通过URL进行地址解析
							URL url = new URL(path);
							//打开网络连接
							HttpURLConnection conn;
							conn = (HttpURLConnection) url.openConnection();
							//设置请求的方式
							conn.setRequestMethod("GET");
							//设置请求时的超时的时间
							conn.setConnectTimeout(5*1000);
							//2.获取网络中的图片流
							InputStream is = conn.getInputStream();
							//将图片流转换成二进制字节码
							byte[] data = StreamTools.streamToBytes(is);
							//将这个字节转换成string
							String jsonstring = new String (data);
							//将jsonstring转换成JSON对象
							JSONObject jo = new JSONObject(jsonstring);
							result = jo.getString("msg");
							uid = jo.getInt("userId");
							//开启UI线程
							runOnUiThread(new Runnable()
							{
								@Override
								public void run() {
									Toast.makeText(LoginActivity.this, result, Toast.LENGTH_SHORT).show();
									if(result.equals("successLogined"))//登陆成功
									{
										Intent intent  = new Intent(LoginActivity.this,MainActivity.class);
										//Intent intent  = new Intent(LoginActivity.this,MainActivity2.class);
										startActivity(intent);
										//增加动画效果
										LoginActivity.this.overridePendingTransition(R.anim.in_translate_left_one, R.anim.in_translate_left_two);
									}
									
								}
							
							});
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						
					}
					
				}
						).start();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}//将
		}
		
	}
}
