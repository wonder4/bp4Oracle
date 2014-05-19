package com.ebiz.bp_oracle.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GetHtmlTools {
	// private static final Logger logger = LoggerFactory.getLogger(GetHtmlTools.class);

	/**
	 * 网页抓取方法
	 * 
	 * @param urlString 要抓取的url地址,普通方法
	 * @param charset 网页编码方式
	 * @param timeout 超时时间
	 * @return 抓取的网页内容
	 * @throws IOException 抓取异常
	 */
	public static String GetWebContent(String urlString, final String charset, int timeout) throws IOException {
		if (urlString == null || urlString.length() == 0) {
			return null;
		}
		urlString = (urlString.startsWith("http://") || urlString.startsWith("https://")) ? urlString
				: ("http://" + urlString).intern();
		URL url = new URL(urlString);

		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn
				.setRequestProperty("User-Agent",
						"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.2; Trident/4.0; .NET CLR 1.1.4322; .NET CLR 2.0.50727)");// 增加报头，模拟浏览器，防止屏蔽
		conn.setRequestProperty("Accept", "text/html");// 只接受text/html类型，当然也可以接受图片,pdf,*/*任意，就是tomcat/conf/web里面定义那些

		conn.setConnectTimeout(timeout);
		try {
			if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
				return null;
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		InputStream input = conn.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(input, charset));
		String line = null;
		StringBuffer sb = new StringBuffer();
		while ((line = reader.readLine()) != null) {
			sb.append(line).append("\r\n");
		}
		if (reader != null) {
			reader.close();
		}
		if (conn != null) {
			conn.disconnect();
		}
		return sb.toString();

	}

	/**
	 * @desc 通过区域判断：省级、 市级、 县级用户
	 * @return 1：省级, 2 :市级, 3:县级
	 */
	public static Integer judgePindex(String p_index) {
		Integer flag = 0;
		if (StringUtils.isBlank(p_index)) {
			return null;
		}
		if (!StringUtils.substring(p_index, 4, 6).equals("00")) {
			flag = 3;// 县
		} else if (!StringUtils.substring(p_index, 2, 4).equals("00")) {
			flag = 2;// 市
		} else {
			flag = 1;// 省
		}

		return flag;
	}

	/**
	 * @desc 从网页抓取最新省市县数据
	 * @param url 抓取网页的url地址
	 * @return 插入数据库的语句
	 */
	public static void GetP_INDEX(String url) {
		Document doc;
		if (StringUtils.isBlank(url)) {
			url = "http://www.stats.gov.cn/tjbz/xzqhdm/t20130118_402867249.htm";
		}
		try {
			doc = Jsoup.connect(url).get();

			// File input = new File("E:/p/province.htm");
			// Document doc = Jsoup.parse(input, "GBK");

			// System.out.println(doc1.toString());
			// Elements ems = doc.getElementsBy("em");
			Elements ems = doc.select(".MsoNormalTable").select("tr");
			// System.out.println(ems.html());
			StringBuffer sb = new StringBuffer();
			for (Element em : ems) {
				Elements tds = em.select("TD");
				// for (Element td : tds) {
				String p_index = StringUtils.trimToEmpty(tds.get(0).text());
				String p_name = StringUtils.trimToEmpty(tds.get(1).text());
				String root_code = StringUtils.substring(p_index, 0, 2).concat("0000");
				String par_index = "0";
				int level = judgePindex(p_index);
				if (level == 3) {
					par_index = StringUtils.substring(p_index, 0, 4).concat("00");
				}
				if (level == 2) {
					par_index = StringUtils.substring(p_index, 0, 2).concat("0000");
				}
				// }

				String sql = "insert into base_province1 (P_INDEX,PAR_INDEX,ROOT_CODE, P_NAME) values ("
						.concat(p_index).concat(",").concat(par_index).concat(",").concat(root_code).concat(", trim('")
						.concat(p_name).concat("'));\n");
				sb.append(sql);
				System.out.println(sql);

			}
			FileUtils.writeStringToFile(new File("E://sql.sql"), sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

		// String xx = "ASUS华硕  ";
		// System.out.println(StringUtils.trim(xx));
		// System.out.println(StringUtils.trimToEmpty(xx));
		// System.out.println(StringUtils.replace(xx, "  ", ""));
	}

	/**
	 * @desc 从京东网页抓取产品类别,并保打印
	 * @param url 抓取网页的url地址
	 */
	public static void get360BuyClsInfo(String url) {
		url = "http://www.nmgzfcg.gov.cn";
		String JingdongCls;
		try {
			JingdongCls = GetWebContent(url, "gbk", 5000);
			int begin = StringUtils.indexOf(JingdongCls, "(");
			int end = StringUtils.lastIndexOf(JingdongCls, ")");
			String JingdongClsString = StringUtils.substring(JingdongCls, begin + 1, end);

			System.out.println("JingdongClsString:" + JingdongClsString);
			JSONObject JingdongClsJson = new JSONObject(JingdongClsString);
			JSONArray jsonArray = JingdongClsJson.getJSONArray("data");
			// System.out.println(jsonArray.length());
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject json_name_level1 = new JSONObject(jsonArray.getString(i));
				String name1 = Jsoup.parse(json_name_level1.getString("n")).text();
				System.out.println("name:[" + i + "]" + name1);

				JSONArray json_array_level2 = json_name_level1.getJSONArray("i");
				for (int i2 = 0; i2 < json_array_level2.length(); i2++) {
					JSONObject json_name_level2 = new JSONObject(json_array_level2.getString(i2));
					String name2 = Jsoup.parse(json_name_level2.getString("n")).text();
					System.out.println("name2:[" + i + "][" + i2 + "]" + name2);

					JSONArray json_array_level3 = json_name_level2.getJSONArray("i");
					for (int i3 = 0; i3 < json_array_level3.length(); i3++) {
						String name3 = StringUtils.split(json_array_level3.getString(i3), "|")[1];
						// String name3 = json_array_level3.getString(i3);
						System.out.println("name3:[" + i + "][" + i2 + "][" + i3 + "]" + name3);
					}

				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}

	}

	/**
	 * @desc 从京东网页抓取属性,并保存到商网的数据库中
	 * @param url 抓取网页的url地址
	 * @param 该属性绑定的类别ID
	 */
	// public static void get360BuyAttrInfoInsertIntoSw(Facade facade, String url, String bindClsId) {
	// Document doc;
	// try {
	// doc = Jsoup.connect(url).timeout(5000).get();
	// Element ems = doc.getElementById("select");
	// // StringBuffer sb = new StringBuffer();
	// // System.out.println(ems.toString());
	// Elements dls = ems.select("dl");
	// for (Element em : dls) {
	// // String linkHref = link.attr("href");
	// String praAttrName = em.select("dt").text();
	// praAttrName = StringUtils.replace(praAttrName, "�", "");
	// praAttrName = StringUtils.replace(praAttrName, "：", "");
	// if (!StringUtils.contains(praAttrName, "品牌")) {
	// BasePdAttribute basePdAttribute = new BasePdAttribute();
	// basePdAttribute.setAttr_name(praAttrName);
	// basePdAttribute.setAttr_show_name(praAttrName);
	// basePdAttribute.setIs_del(0);
	// Long attr_id = facade.getBasePdAttributeService().createBasePdAttribute(basePdAttribute);
	// logger.info("==[praAttrName]:{} [attr_id]:{}", praAttrName, attr_id);
	// Elements sonElements = em.select("dd").get(0).select("a");
	// for (Element emSon : sonElements) {
	// String sonAttrName = emSon.text();
	// sonAttrName = StringUtils.replace(sonAttrName, "�", "");
	// if (!StringUtils.contains(sonAttrName, "不限")) {
	// BasePdAttributeSon basePdAttributeSon = new BasePdAttributeSon();
	// basePdAttributeSon.setAttr_id(attr_id);
	// basePdAttributeSon.setAttr_name(sonAttrName);
	// basePdAttributeSon.setAttr_show_name(sonAttrName);
	// facade.getBasePdAttributeSonService().createBasePdAttributeSon(basePdAttributeSon);
	// logger.info("==[sonAttrName]:{} [attr_id]:{}", sonAttrName, attr_id);
	// }
	// }
	// if (StringUtils.isNotBlank(bindClsId) && null != attr_id) {
	// BasePdClassLinkAttribute basePdClassLinkAttribute = new BasePdClassLinkAttribute();
	// basePdClassLinkAttribute.setCls_id(Long.valueOf(bindClsId));
	// basePdClassLinkAttribute.setAttr_id(Long.valueOf(attr_id));
	// facade.getBasePdClassLinkAttributeService().createBasePdClassLinkAttribute(
	// basePdClassLinkAttribute);
	// }
	// }
	// }
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }

	/**
	 * @desc 从阿里巴巴网站页抓取属性,并保存到商网的数据库中
	 * @param url 抓取网页的url地址
	 * @param 该属性绑定的类别ID
	 */
	// public static void getAlibabaAttrInfoInsertIntoSw(Facade facade, String url, String bindClsId) {
	// Document doc;
	// try {
	// doc = Jsoup.connect(url).timeout(5000).get();
	// Element ems = doc.getElementById("selloffer-category");
	// // StringBuffer sb = new StringBuffer();
	// // System.out.println(ems.toString());
	// Elements dls = ems.select("dl");
	// for (Element em : dls) {
	// // String linkHref = link.attr("href");
	// String praAttrName = em.select("dt").text();
	// praAttrName = StringUtils.replace(praAttrName, "�", "");
	// praAttrName = StringUtils.replace(praAttrName, "：", "");
	// if (!StringUtils.contains(praAttrName, "品牌")) {
	// BasePdAttribute basePdAttribute = new BasePdAttribute();
	// basePdAttribute.setAttr_name(praAttrName);
	// basePdAttribute.setAttr_show_name(praAttrName);
	// basePdAttribute.setIs_required(1);
	// basePdAttribute.setIs_show(0);
	// basePdAttribute.setIs_del(0);
	// Long attr_id = facade.getBasePdAttributeService().createBasePdAttribute(basePdAttribute);
	// logger.info("==[praAttrName]:{} [attr_id]:{}", praAttrName, attr_id);
	// Elements sonElements = em.select("dd").get(0).select("a");
	// for (Element emSon : sonElements) {
	// String sonAttrName = emSon.text();
	// sonAttrName = StringUtils.replace(sonAttrName, "�", "");
	// if (!StringUtils.contains(sonAttrName, "不限")) {
	// BasePdAttributeSon basePdAttributeSon = new BasePdAttributeSon();
	// basePdAttributeSon.setAttr_id(attr_id);
	// basePdAttributeSon.setAttr_name(sonAttrName);
	// basePdAttributeSon.setAttr_show_name(sonAttrName);
	// facade.getBasePdAttributeSonService().createBasePdAttributeSon(basePdAttributeSon);
	// logger.info("==[sonAttrName]:{} [attr_id]:{}", sonAttrName, attr_id);
	// }
	// }
	// if (StringUtils.isNotBlank(bindClsId) && null != attr_id) {
	// BasePdClassLinkAttribute basePdClassLinkAttribute = new BasePdClassLinkAttribute();
	// basePdClassLinkAttribute.setCls_id(Long.valueOf(bindClsId));
	// basePdClassLinkAttribute.setAttr_id(Long.valueOf(attr_id));
	// facade.getBasePdClassLinkAttributeService().createBasePdClassLinkAttribute(
	// basePdClassLinkAttribute);
	// }
	// }
	// }
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }

	// public static void run(Facade facade) {
	// GetHtmlTools ght = new GetHtmlTools();
	// ght.get360BuyAttrInfo(facade, "http://www.360buy.com/products/737-794-870-0-0-0-0-0-0-0-1-1-1-1-72-33.html",
	// null);
	// }
	public static void main(String args[]) throws IOException {
		// String url = "http://www.nmgzfcg.gov.cn";
		// Document doc;
		// if (StringUtils.isBlank(url)) {
		// url = "http://www.newegg.com.cn/BrandList.htm";
		// }
		// try {
		// doc = Jsoup.connect(url).get();
		// System.out.print(doc.toString());
		// } catch (Exception e) {
		// // TODO: handle exception
		// }
		GetHtmlTools.GetP_INDEX("");
		// GetHtmlTools ght = new GetHtmlTools();
		// ght.get360BuyAttrInfo("http://www.360buy.com/products/737-794-870-0-0-0-0-0-0-0-1-1-1-1-72-33.html", null);
	}

}
