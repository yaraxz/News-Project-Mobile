package czmec.cn.shl.news.activity;

import czmec.cn.shl.news.test.R;
import czmec.cn.shl.news.test.R.layout;
import czmec.cn.shl.news.test.R.menu;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void getIn(View v)
	{
		Intent intent  = new Intent(MainActivity.this,AllNewsBarActivity.class);
		startActivity(intent);
		//增加动画效果
		MainActivity.this.overridePendingTransition(R.anim.in_translate_left_one, R.anim.in_translate_left_two);
	}
}
