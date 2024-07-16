package czmec.cn.shl.news.activity;

import java.util.List;

import czmec.cn.shl.news.config.Config;
import czmec.cn.shl.news.engine.ParseJson;
import czmec.cn.shl.news.entity.NewsContent;
import czmec.cn.shl.news.test.R;
import czmec.cn.shl.news.test.R.layout;
import czmec.cn.shl.news.test.R.menu;
import czmec.cn.shl.news.util.ImageTask;
import czmec.cn.shl.news.util.SynsHttp;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

public class NewsDetailActivity extends Activity {
	TextView tv1;
	TextView tv2;
	ImageView iv1;
	String title,content,pic;
	List<NewsContent> newsList;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news_detail);
		tv1 = (TextView) this.findViewById(R.id.tv_newstitle);
		tv2 = (TextView) this.findViewById(R.id.tv_newscontent);
		iv1 = (ImageView) this.findViewById(R.id.iv_newspic);
		Intent intent = getIntent();
		final String newsID = intent.getStringExtra("newsId");
		new Thread(new Runnable(){
			@Override
			public void run(){
				getNewsData(newsID);
			}
		}).start();
	}

	protected void getNewsData(String id) {
		// TODO Auto-generated method stub
		String url = Config.ServerAddress+"GetNewsByNewsIdAppServlet?newsID="+ id;
		byte[]data;
		try{
			data = SynsHttp.readParse(url);
			String jsonStr = new String(data);
			newsList = ParseJson.getNewsList(jsonStr);
			title = newsList.get(0).getTitleName().toString().trim();
			content = newsList.get(0).getContent().toString().trim();
			pic = newsList.get(0).getPic().toString().trim();
			String a = Config.ImageAddress +pic;
		}catch (Exception e){
			e.printStackTrace();
		}
		runOnUiThread(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				tv1.setText(title);
				tv2.setText(content);
				if(pic!=""&&pic !=null){
					new ImageTask().execute(Config.ImageAddress +pic, iv1);
				}
			}
		});
		
	}

	private void start() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.news_detail, menu);
		return true;
	}
	

}
