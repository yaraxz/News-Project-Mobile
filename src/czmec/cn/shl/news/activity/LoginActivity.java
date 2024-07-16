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

	//��APPҳ���е��û����������Լ���½��ť��ע�ᰴť����
	private EditText t_login_name,t_login_password;
	private Button login_btn,register_btn;
	private int uid;
	private String uName ,uPass,path,result;
	
	//����һ����ʼ���ķ���
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
		//��ʼ�������е����
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
		uName = t_login_name.getText().toString();//�û�������û���
		uPass = t_login_password.getText().toString();//�û����������
		if(uName.trim().length() == 0 || uPass.trim().length() == 0)
		{
			//��ʾ�û��������벻��Ϊ��
			Toast.makeText(LoginActivity.this, R.string.no_null, Toast.LENGTH_SHORT).show();
		}
		else//��Ϊ
		{
			try {
				uName = URLEncoder.encode(uName,"gbk");//���û������б���ת��
				uPass = URLEncoder.encode(uPass,"gbk");
				path = Config.ServerAddress + "UserLoginAppServlet?userName=" + uName + "&password=" + uPass;
				//����һ���µ��߳�
				new Thread(new Runnable()
				{
					@Override
					public void run() {						
						try {
							//����URL��ȡ������Դ
							//ͨ��URL���е�ַ����
							URL url = new URL(path);
							//����������
							HttpURLConnection conn;
							conn = (HttpURLConnection) url.openConnection();
							//��������ķ�ʽ
							conn.setRequestMethod("GET");
							//��������ʱ�ĳ�ʱ��ʱ��
							conn.setConnectTimeout(5*1000);
							//2.��ȡ�����е�ͼƬ��
							InputStream is = conn.getInputStream();
							//��ͼƬ��ת���ɶ������ֽ���
							byte[] data = StreamTools.streamToBytes(is);
							//������ֽ�ת����string
							String jsonstring = new String (data);
							//��jsonstringת����JSON����
							JSONObject jo = new JSONObject(jsonstring);
							result = jo.getString("msg");
							uid = jo.getInt("userId");
							//����UI�߳�
							runOnUiThread(new Runnable()
							{
								@Override
								public void run() {
									Toast.makeText(LoginActivity.this, result, Toast.LENGTH_SHORT).show();
									if(result.equals("successLogined"))//��½�ɹ�
									{
										Intent intent  = new Intent(LoginActivity.this,MainActivity.class);
										//Intent intent  = new Intent(LoginActivity.this,MainActivity2.class);
										startActivity(intent);
										//���Ӷ���Ч��
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
			}//��
		}
		
	}
}
