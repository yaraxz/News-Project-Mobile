package czmec.cn.shl.news.activity;

import java.util.List;

import czmec.cn.shl.news.adapter.NewsBarlistAdapter;
import czmec.cn.shl.news.adapter.NewsListByNewsBarAdapter;
import czmec.cn.shl.news.config.Config;
import czmec.cn.shl.news.engine.ParseJson;
import czmec.cn.shl.news.entity.NewsContent;
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
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class NewsByNewsBarActivity extends Activity {
	private ListView listview;
	private List<NewsContent> newsList;
	private NewsListByNewsBarAdapter adapter;
	private String newsBarID,newsBarName;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news_by_news_bar);
		Intent intent = getIntent();
		newsBarID = intent.getStringExtra("barID");
		newsBarName = intent.getStringExtra("barName");
		
		listview = (ListView) this.findViewById(R.id.news_listview);	
		TextView tv = (TextView) this.findViewById(R.id.title_name);
		tv.setText(newsBarName);
		new Thread(new Runnable() {			
			@Override
			public void run() {				
				getNewsByNewsBar();
			}
		}).start();				
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {	
		getMenuInflater().inflate(R.menu.news_by_news_bar, menu);
		return true;
	}
	public void back(View v) 
	{
		Intent intent = new Intent(NewsByNewsBarActivity.this, AllNewsBarActivity.class);
		startActivity(intent);
		overridePendingTransition(R.anim.back_translate_right_one,R.anim.back_translate_right_two);
	}	
	public void getNewsByNewsBar()
	{
		
		
		String url = Config.ServerAddress+"GetNewsByNewsIdAppServlet?barID=" + newsBarID;
		byte[] data;
		try {
			data = SynsHttp.readParse(url);
			String jsonStr = new String(data);
			newsList = ParseJson.getNewsList(jsonStr);
			for(NewsContent newsbar:newsList)
			{
				System.out.println("打印：" + newsbar.getTitleName());
			}			
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
		runOnUiThread(new Runnable() {			
			@Override
			public void run() {		
				adapter = new NewsListByNewsBarAdapter(NewsByNewsBarActivity.this, newsList);
				listview.setAdapter(adapter);				
				//dialog.dismiss();
				listview.setOnItemClickListener(new OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
							long arg3) {
						// TODO Auto-generated method stub
						System.out.println("-----被点击的Item是---->>" + arg2);
						Bundle bu = new Bundle();
						bu.putString("newsId", newsList.get(arg2).getNewsID());
						Toast.makeText(NewsByNewsBarActivity.this, "你点击的新闻是：id:"+newsList.get(arg2).getNewsID() +",新闻名称是为" +newsList.get(arg2).getTitleName(), 4000).show();
						//bu.putString("fname", array.get(arg2).getFname());
						Intent intent = new Intent(NewsByNewsBarActivity.this,NewsDetailActivity.class);
						intent.putExtras(bu);
						startActivity(intent);
						//增加动画效果
						//AllNewsBarActivity.this.overridePendingTransition(R.anim.in_translate_left_one, R.anim.in_translate_left_two);					
					}					
				});
			}
		});
	}
}
