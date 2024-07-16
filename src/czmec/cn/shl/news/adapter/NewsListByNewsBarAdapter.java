package czmec.cn.shl.news.adapter;

import java.util.List;

import czmec.cn.shl.news.config.Config;
import czmec.cn.shl.news.entity.NewsContent;
import czmec.cn.shl.news.entity.NewsType;
import czmec.cn.shl.news.test.R;
import czmec.cn.shl.news.util.ImageTask;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class NewsListByNewsBarAdapter extends BaseAdapter {

	private List<NewsContent> newsList;
	private LayoutInflater inflater;
	public NewsListByNewsBarAdapter(Context context, List<NewsContent> newsList) 
	{
		inflater = LayoutInflater.from(context);
		this.newsList = newsList;		
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return newsList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if (convertView == null) 
		{
			convertView = inflater.inflate(R.layout.news_list_item, null);
		}		
		TextView tvnewstitle = (TextView) convertView.findViewById(R.id.newstitle);
		tvnewstitle.setText(newsList.get(position).getTitleName());	
		TextView tvnewsdate = (TextView) convertView.findViewById(R.id.newsdate);
		tvnewsdate.setText(newsList.get(position).getAddDate());
		ImageView c_img = (ImageView) convertView.findViewById(R.id.newspic);
		String pic = newsList.get(position).getPic();
		if(pic!="" && pic !=null)
		{
			new ImageTask().execute(Config.ImageAddress
					+ newsList.get(position).getPic(), c_img);// “Ï≤Ωº”‘ÿ
		}
		
		return convertView;
	}

}
