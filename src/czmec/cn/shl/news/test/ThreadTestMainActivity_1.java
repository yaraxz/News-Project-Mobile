package czmec.cn.shl.news.test;

import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.view.Menu;
import android.widget.TextView;

public class ThreadTestMainActivity_1 extends Activity {

	//����һ���Ի���
	ProgressDialog dialog = null;
	//����һ��runnable����
	Runnable runnable = new Runnable(){

		@Override
		public void run() {
			System.out.println("===============one============" + Thread.currentThread());
			//˯��4��
			try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//����UI�̸߳��½���
			runOnUiThread( new Runnable(){
				@Override
				public void run() {
					System.out.println("=============two============" + Thread.currentThread());
					dialog.dismiss();
					//���½���
					TextView tv = (TextView) ThreadTestMainActivity_1.this.findViewById(R.id.tv1);
				    tv.setText("�������ݷ����˱仯.......");
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
        dialog.setTitle("��ʾ��");
        dialog.setIcon(R.drawable.ic_launcher);
        dialog.setMessage("���ڼ���.....");
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
