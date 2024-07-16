package czmec.cn.shl.news.test;

import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.view.Menu;
import android.widget.TextView;

public class ThreadTestMainActivity_1 extends Activity {

	//定义一个对话框
	ProgressDialog dialog = null;
	//创建一个runnable对象
	Runnable runnable = new Runnable(){

		@Override
		public void run() {
			System.out.println("===============one============" + Thread.currentThread());
			//睡眠4秒
			try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//调用UI线程更新界面
			runOnUiThread( new Runnable(){
				@Override
				public void run() {
					System.out.println("=============two============" + Thread.currentThread());
					dialog.dismiss();
					//更新界面
					TextView tv = (TextView) ThreadTestMainActivity_1.this.findViewById(R.id.tv1);
				    tv.setText("数据内容发生了变化.......");
				}				
			}				
			);
		}
		
	};
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_test_main_activity_1);
        dialog = new  ProgressDialog(this);
        dialog.setTitle("提示框");
        dialog.setIcon(R.drawable.ic_launcher);
        dialog.setMessage("正在加载.....");
        dialog.show();
        new Thread(runnable).start();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.thread_test_main_activity_1, menu);
        return true;
    }
    
}
