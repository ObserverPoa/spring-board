package kw.proj2.vo;

import java.util.Date;

public class boardVO // board Value Object(VO) class
{
	// used Integer wrapper class as integer to allow null.
	private Integer anum; // article number
	private String title; // article title
	private String content; // article content
	private String name; // article writer
	private Date wrdate; // article written date
	private Integer views; // article views
	private Integer mainord; // article main order
	private Integer subord; // article sub order
	private Integer depth; // article depth
	
	//getter and setters of each members.
	public Integer getAnum() {
		return anum;
	}
	public void setAnum(Integer anum) {
		this.anum = anum;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getWrdate() {
		return wrdate;
	}
	public void setWrdate(Date wrdate) {
		this.wrdate = wrdate;
	}
	public Integer getViews() {
		return views;
	}
	public void setViews(Integer views) {
		this.views = views;
	}
	public Integer getMainord() {
		return mainord;
	}
	public void setMainord(Integer mainord) {
		this.mainord = mainord;
	}
	public Integer getSubord() {
		return subord;
	}
	public void setSubord(Integer subord) {
		this.subord = subord;
	}
	public Integer getDepth() {
		return depth;
	}
	public void setDepth(Integer depth) {
		this.depth = depth;
	}
	
}
