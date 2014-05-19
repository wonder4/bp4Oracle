package com.ebiz.bp_oracle.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.ebiz.ssi.domain.BaseDomain;

public class NewsInfo extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;

	private String uuid;

	private String mod_id;

	private String title;

	private String title_color;

	private Integer title_is_strong;

	private String title_short;

	private String title_sub;

	private Integer is_use_direct_uri;

	private String direct_uri;

	private String keyword;

	private String image_path;

	private String image_desc;

	private String summary;

	private String author;

	private String info_source;

	private Integer is_use_invalid_date;

	private Date invalid_date;

	private Date pub_time;

	private Long view_count;

	private Date view_datetime;

	private Integer info_state;

	private Long add_uid;

	private Date add_time;

	private Long update_uid;

	private Date update_time;

	private Long del_uid;

	private Date del_time;

	private Integer order_value;

	private Integer is_del;

	private String locale_name;

	private String video_desc;

	private String video_path;

	private String content;

	private String upload_image_files;

	private List<NewsAttachment> newsAttachmentList;

	public NewsInfo() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getMod_id() {
		return mod_id;
	}

	public void setMod_id(String mod_id) {
		this.mod_id = mod_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle_color() {
		return title_color;
	}

	public void setTitle_color(String title_color) {
		this.title_color = title_color;
	}

	public Integer getTitle_is_strong() {
		return title_is_strong;
	}

	public void setTitle_is_strong(Integer title_is_strong) {
		this.title_is_strong = title_is_strong;
	}

	public String getTitle_short() {
		return title_short;
	}

	public void setTitle_short(String title_short) {
		this.title_short = title_short;
	}

	public String getTitle_sub() {
		return title_sub;
	}

	public void setTitle_sub(String title_sub) {
		this.title_sub = title_sub;
	}

	public Integer getIs_use_direct_uri() {
		return is_use_direct_uri;
	}

	public void setIs_use_direct_uri(Integer is_use_direct_uri) {
		this.is_use_direct_uri = is_use_direct_uri;
	}

	public String getDirect_uri() {
		return direct_uri;
	}

	public void setDirect_uri(String direct_uri) {
		this.direct_uri = direct_uri;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getImage_path() {
		return image_path;
	}

	public void setImage_path(String image_path) {
		this.image_path = image_path;
	}

	public String getImage_desc() {
		return image_desc;
	}

	public void setImage_desc(String image_desc) {
		this.image_desc = image_desc;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getInfo_source() {
		return info_source;
	}

	public void setInfo_source(String info_source) {
		this.info_source = info_source;
	}

	public Integer getIs_use_invalid_date() {
		return is_use_invalid_date;
	}

	public void setIs_use_invalid_date(Integer is_use_invalid_date) {
		this.is_use_invalid_date = is_use_invalid_date;
	}

	public Date getInvalid_date() {
		return invalid_date;
	}

	public void setInvalid_date(Date invalid_date) {
		this.invalid_date = invalid_date;
	}

	public Date getPub_time() {
		return pub_time;
	}

	public void setPub_time(Date pub_time) {
		this.pub_time = pub_time;
	}

	public Long getView_count() {
		return view_count;
	}

	public void setView_count(Long view_count) {
		this.view_count = view_count;
	}

	public Date getView_datetime() {
		return view_datetime;
	}

	public void setView_datetime(Date view_datetime) {
		this.view_datetime = view_datetime;
	}

	public Integer getInfo_state() {
		return info_state;
	}

	public void setInfo_state(Integer info_state) {
		this.info_state = info_state;
	}

	public Long getAdd_uid() {
		return add_uid;
	}

	public void setAdd_uid(Long add_uid) {
		this.add_uid = add_uid;
	}

	public Date getAdd_time() {
		return add_time;
	}

	public void setAdd_time(Date add_time) {
		this.add_time = add_time;
	}

	public Long getUpdate_uid() {
		return update_uid;
	}

	public void setUpdate_uid(Long update_uid) {
		this.update_uid = update_uid;
	}

	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}

	public Long getDel_uid() {
		return del_uid;
	}

	public void setDel_uid(Long del_uid) {
		this.del_uid = del_uid;
	}

	public Date getDel_time() {
		return del_time;
	}

	public void setDel_time(Date del_time) {
		this.del_time = del_time;
	}

	public Integer getOrder_value() {
		return order_value;
	}

	public void setOrder_value(Integer order_value) {
		this.order_value = order_value;
	}

	public Integer getIs_del() {
		return is_del;
	}

	public void setIs_del(Integer is_del) {
		this.is_del = is_del;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUpload_image_files() {
		return upload_image_files;
	}

	public void setUpload_image_files(String uploadImageFiles) {
		upload_image_files = uploadImageFiles;
	}

	public List<NewsAttachment> getNewsAttachmentList() {
		return newsAttachmentList;
	}

	public void setNewsAttachmentList(List<NewsAttachment> newsAttachmentList) {
		this.newsAttachmentList = newsAttachmentList;
	}

	public String getLocale_name() {
		return locale_name;
	}

	public void setLocale_name(String localeName) {
		locale_name = localeName;
	}

	public String getVideo_desc() {
		return video_desc;
	}

	public void setVideo_desc(String videoDesc) {
		video_desc = videoDesc;
	}

	public String getVideo_path() {
		return video_path;
	}

	public void setVideo_path(String videoPath) {
		video_path = videoPath;
	}
}