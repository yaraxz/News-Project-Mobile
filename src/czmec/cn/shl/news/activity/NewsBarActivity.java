package czmec.cn.shl.news.activity;

import czmec.cn.shl.news.test.R;
import czmec.cn.shl.news.test.R.layout;
import czmec.cn.shl.news.test.R.menu;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class NewsBarActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news_bar);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.news_bar, menu);
		return true;
	}

}
