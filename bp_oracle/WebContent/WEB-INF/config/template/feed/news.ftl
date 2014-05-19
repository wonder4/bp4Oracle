<?xml version="1.0" encoding="utf-8"?>
<rss version="2.0">
<channel>
<title>易商数码内网办公平台-新闻列表</title>
<description>易商数码内网办公平台新闻列表</description>
<link><![CDATA[http://${server}]]></link>
<language>zh-cn</language>
<docs>易商数码内网办公平台</docs>
<generator>安徽易商数码科技有限公司</generator>
<image>
	<title>易商数码内网办公平台</title>
	<url><![CDATA[http://oa.easy-biz.com.cn/favicon.ico]]></url>
	<link><![CDATA[http://oa.easy-biz.com.cn/]]></link>
</image>

<#list newsList as cur>
<item>
	<title><![CDATA[${cur.title}]]></title>
	<description><![CDATA[${cur.summary!""}]]></description>
	<link><![CDATA[http://${server}/oa/web/manager/N${cur.id?string("0")}.shtml]]></link>
	<author><![CDATA[${cur.author!""}]]></author>
	<pubDate><![CDATA[${(cur.add_datetime?string("yyyy-MM-dd HH:mm:ss"))!""}]]></pubDate>
</item>
</#list>
</channel>
</rss>