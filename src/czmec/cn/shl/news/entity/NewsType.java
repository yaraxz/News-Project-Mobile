package czmec.cn.shl.news.entity;

/*
 * 新闻栏目类
 */
public class NewsType {

	private String newsTypeID;
	private String titleName;
	private String creator;
	private String creatDate;
	private  String cteatorID;
	private String userRealName;	
	private String yxx;
	public String getUserRealName() {
		return userRealName;
	}
	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}
	public String getYxx() {
		return yxx;
	}
	public void setYxx(String yxx) {
		this.yxx = yxx;
	}
	public String getNewsTypeID() {
		return newsTypeID;
	}
	public void setNewsTypeID(String newsTypeID) {
		this.newsTypeID = newsTypeID;
	}
	public String getTitleName() {
		return titleName;
	}
	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getCreatDate() {
		return creatDate;
	}
	public void setCreatDate(String creatDate) {
		this.creatDate = creatDate;
	}
	public String getCteatorID() {
		return cteatorID;
	}
	public void setCteatorID(String cteatorID) {
		this.cteatorID = cteatorID;
	}
	
	
}
