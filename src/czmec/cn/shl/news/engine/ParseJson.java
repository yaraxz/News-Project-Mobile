package czmec.cn.shl.news.engine;


import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import czmec.cn.shl.news.entity.NewsContent;
import czmec.cn.shl.news.entity.NewsType;

public class ParseJson {	
	//解析新闻栏目
	public static List<NewsType> getNewsBarList(String str) {
		List<NewsType> list = new ArrayList<NewsType>();
		try {
			JSONObject jsonobject = new JSONObject(str);
			JSONArray dislist = jsonobject.getJSONArray("barlist");
			NewsType newsbar;
			for (int i = 0; i < dislist.length(); i++) {
				JSONObject jo = (JSONObject) dislist.opt(i);
				newsbar = new NewsType();
				newsbar.setNewsTypeID(jo.getString("barID"));				
				try {
					System.out.println("jo::::::::;" + jo.getString("barName"));					
					newsbar.setTitleName(jo.getString("barName"));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}		
				list.add(newsbar);
			}
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
		return list;
	}
	
	//解析新闻列表
		public static List<NewsContent> getNewsList(String str) {
			List<NewsContent> list = new ArrayList<NewsContent>();
			try {
				JSONObject jsonobject = new JSONObject(str);
				JSONArray dislist = jsonobject.getJSONArray("newslist");
				NewsContent news;
				for (int i = 0; i < dislist.length(); i++) {
					JSONObject jo = (JSONObject) dislist.opt(i);
					news = new NewsContent();
					news.setNewsID(jo.getString("newsID"));				
					try {
						System.out.println("jo::::::::;" + jo.getString("newsID"));					
						news.setTitleName(jo.getString("titleName"));
						news.setAddDate(jo.getString("addDate"));
						news.setPic(jo.getString("picpath"));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}		
					list.add(news);
				}
			} catch (JSONException e) {
				e.printStackTrace();
				return null;
			}
			return list;
		}
}
