package czmec.cn.shl.news.activity;

import java.util.List;

import czmec.cn.shl.news.adapter.NewsBarlistAdapter;
import czmec.cn.shl.news.config.Config;
import czmec.cn.shl.news.engine.ParseJson;
import czmec.cn.shl.news.entity.NewsType;
import czmec.cn.shl.news.test.R;
import czmec.cn.shl.news.test.R.layout;
import czmec.cn.shl.news.test.R.menu;
import czmec.cn.shl.news.util.SynsHttp;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class AllNewsBarActivity extends Activity {
	private ListView listview;
	private List<NewsType> newsBarList;
	private NewsBarlistAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_all_newsbar);
		listview = (ListView) this.findViewById(R.id.newsbar_listview);	
		TextView tv = (TextView) this.findViewById(R.id.title_name);
		tv.setText("新闻分类");
		new Thread(new Runnable() {			
			@Override
			public void run() {				
				getNewsAllBar();
			}
		}).start();				
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {	
		getMenuInflater().inflate(R.menu.classified_news, menu);
		return true;
	}
	public void back(View v) 
	{
		Intent intent = new Intent(AllNewsBarActivity.this, MainActivity.class);
		startActivity(intent);
		overridePendingTransition(R.anim.back_translate_right_one,R.anim.back_translate_right_two);
	}	
	public void getNewsAllBar()
	{
		//String url = Config.ServerAddress+"GetAllNewsBarAppServlet";
		String url = Config.ServerAddress+"GetALLBarListAppServlet";
		byte[] data;
		try {
			data = SynsHttp.readParse(url);
			String jsonStr = new String(data);
			newsBarList = ParseJson.getNewsBarList(jsonStr);
			for(NewsType newsbar:newsBarList)
			{
				System.out.println("打印：" + newsbar.getTitleName());
			}			
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
		runOnUiThread(new Runnable() {			
			@Override
			public void run() {		
				adapter = new NewsBarlistAdapter(AllNewsBarActivity.this, newsBarList);//创建一个自定义的数据适配器
				listview.setAdapter(adapter);				
				//dialog.dismiss();
				listview.setOnItemClickListener(new OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
							long arg3) {
						// TODO Auto-generated method stub
						System.out.println("-----被点击的Item是---->>" + arg2);
						Bundle bu = new Bundle();
						bu.putString("barID", newsBarList.get(arg2).getNewsTypeID());
						Toast.makeText(AllNewsBarActivity.this, "你点击的栏目是：id:"+newsBarList.get(arg2).getNewsTypeID() +",栏目名称是为" +newsBarList.get(arg2).getTitleName(), 4000).show();
						bu.putString("barName", newsBarList.get(arg2).getTitleName());
						Intent intent = new Intent(AllNewsBarActivity.this,
								NewsByNewsBarActivity.class);
						intent.putExtras(bu);
						startActivity(intent);
						//增加动画效果
						AllNewsBarActivity.this.overridePendingTransition(R.anim.in_translate_left_one, R.anim.in_translate_left_two);					
					}					
				});
			}
		});
	}
}
