package czmec.cn.shl.news.entity;

/*
 * 新闻内容封装类
 */
public class NewsContent {

	private String newsID;
	private String titleName;
	private String keyWord;
	private String contentAbatract;
	private String content;
	private String addDate;
	private String writerID;
	private String writerName;
	private String titlebarID;
	private String titlebarName;
	private String pic;
	
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getNewsID() {
		return newsID;
	}
	public void setNewsID(String newsID) {
		this.newsID = newsID;
	}
	public String getTitleName() {
		return titleName;
	}
	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}
	public String getKeyWord() {
		return keyWord;
	}
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
	public String getContentAbatract() {
		return contentAbatract;
	}
	public void setContentAbatract(String contentAbatract) {
		this.contentAbatract = contentAbatract;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAddDate() {
		return addDate;
	}
	public void setAddDate(String addDate) {
		this.addDate = addDate;
	}
	public String getWriterID() {
		return writerID;
	}
	public void setWriterID(String writerID) {
		this.writerID = writerID;
	}
	public String getWriterName() {
		return writerName;
	}
	public void setWriterName(String writerName) {
		this.writerName = writerName;
	}
	public String getTitlebarID() {
		return titlebarID;
	}
	public void setTitlebarID(String titlebarID) {
		this.titlebarID = titlebarID;
	}
	public String getTitlebarName() {
		return titlebarName;
	}
	public void setTitlebarName(String titlebarName) {
		this.titlebarName = titlebarName;
	}
	
}
