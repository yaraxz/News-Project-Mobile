package czmec.cn.shl.news.test;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class AnimActivity extends Activity {

	private ImageView imageview;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_anim);
		imageview  = (ImageView) this.findViewById(R.id.imageView1);
		//������
		//1.͸������
		//Animation anim = AnimationUtils.loadAnimation(this, R.anim.my_alpha);
		//2.��λ����
		//Animation anim = AnimationUtils.loadAnimation(this, R.anim.my_translate);
		//3.��ת����
	    //Animation anim = AnimationUtils.loadAnimation(this, R.anim.my_rotate);
		//4.��������
		Animation anim = AnimationUtils.loadAnimation(this, R.anim.my_scale);
		imageview.setAnimation(anim);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.anim, menu);
		return true;
	}

}
