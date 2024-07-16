package czmec.cn.shl.news.adapter;

import java.util.List;

import czmec.cn.shl.news.entity.NewsType;
import czmec.cn.shl.news.test.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


public class NewsBarlistAdapter extends BaseAdapter {
	private List<NewsType> newsbarList;
	private LayoutInflater inflater;
	public NewsBarlistAdapter(Context context, List<NewsType> newsbarList) 
	{
		inflater = LayoutInflater.from(context);
		this.newsbarList = newsbarList;		
	}
	@Override
	public int getCount() {		
		return newsbarList.size();
	}
	@Override
	public Object getItem(int position) {	
		return position;
	}
	@Override
	public long getItemId(int position) {		
		return position;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {		
		if (convertView == null) 
		{
			convertView = inflater.inflate(R.layout.newsbar_list_item, null);
		}		
		TextView tv = (TextView) convertView.findViewById(R.id.newsbar_item_title);
		tv.setText(newsbarList.get(position).getTitleName());		
		return convertView;
	}


}
