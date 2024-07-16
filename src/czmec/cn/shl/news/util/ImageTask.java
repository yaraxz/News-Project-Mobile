package czmec.cn.shl.news.util;

import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;


public class ImageTask extends AsyncTask<Object, Object, Bitmap> {

	private ImageView imageView = null;

	@Override
	protected Bitmap doInBackground(Object... params) {
		// TODO Auto-generated method stub
		Bitmap bmp = null;
		imageView = (ImageView) params[1];
		try {
			bmp = BitmapFactory.decodeStream(new URL((String) params[0])
					.openStream());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bmp;
	}

	protected void onPostExecute(Bitmap result) {
		imageView.setImageBitmap(result);
	}

}
