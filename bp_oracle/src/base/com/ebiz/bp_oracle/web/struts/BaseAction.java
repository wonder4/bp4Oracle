package com.ebiz.bp_oracle.web.struts;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.upload.FormFile;
import org.springframework.util.Assert;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.ebiz.bp_oracle.domain.BaseData;
import com.ebiz.bp_oracle.domain.BasePopedom;
import com.ebiz.bp_oracle.domain.BaseProvince;
import com.ebiz.bp_oracle.domain.ModPopedom;
import com.ebiz.bp_oracle.domain.RoleUser;
import com.ebiz.bp_oracle.domain.SysModule;
import com.ebiz.bp_oracle.domain.SysSetting;
import com.ebiz.bp_oracle.domain.UserInfo;
import com.ebiz.bp_oracle.service.Facade;
import com.ebiz.bp_oracle.web.Keys;
import com.ebiz.bp_oracle.web.util.EncryptUtilsV2;
import com.ebiz.bp_oracle.web.util.FtpImageUtils;
import com.ebiz.bp_oracle.web.util.FtpUtils;
import com.ebiz.bp_oracle.web.util.StringHelper;
import com.ebiz.ssi.web.struts.BaseSsiAction;
import com.ebiz.ssi.web.struts.bean.UploadFile;

public abstract class BaseAction extends BaseSsiAction {

	public static DecimalFormat dfFormat = new DecimalFormat("0.00");

	public static DecimalFormat dfFormat0 = new DecimalFormat("0");

	public static SimpleDateFormat sdFormat_ymd = new SimpleDateFormat("yyyy-MM-dd");

	public static SimpleDateFormat sdFormatymd = new SimpleDateFormat("yyyyMMdd");

	public static SimpleDateFormat sdFormat_ymdhms = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static SimpleDateFormat sdFormatymdhms = new SimpleDateFormat("yyyyMMddHHmmss");

	private Facade facade;

	/**
	 * the powerful method to return facade with all services(method)
	 * 
	 * @return facade
	 */
	protected Facade getFacade() {
		return this.facade;
	}

	public void setServlet(ActionServlet actionServlet) {
		super.setServlet(actionServlet);
		Assert.notNull(actionServlet, "actionServlet is can not be null");
		ServletContext servletContext = actionServlet.getServletContext();
		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
		this.facade = (Facade) wac.getBean("facade");
	}

	/**
	 * upload files
	 * 
	 * @param form ActionForm
	 * @param uploadDir default is file/upload/
	 * @param isResizeImage default is false
	 * @param iswaterMark 是否添加水印
	 * @param resizeVersions
	 * @return
	 * @throws Exception
	 */
	protected List<UploadFile> uploadFile(ActionForm form, boolean isResizeImage, boolean isWaterMark,
			int... resizeVersions) throws Exception {
		return uploadFile(form, null, isResizeImage, isWaterMark, resizeVersions);
	}

	@SuppressWarnings("unchecked")
	protected List<UploadFile> uploadFile(ActionForm form, String uploadDir, boolean isResizeImage,
			boolean isWaterMark, int... resizeVersions) throws Exception {
		if (StringUtils.isBlank(uploadDir)) {
			uploadDir = StringUtils.join(new String[] { "files", "upload", "" }, File.separator);
		}

		String[] folderPatterns = new String[] { "yyyy", "MM", "dd", "" };
		String autoCreatedDateDir = DateFormatUtils
				.format(new Date(), StringUtils.join(folderPatterns, File.separator));
		String ctxDir = getServlet().getServletContext().getRealPath(File.separator);
		if (!ctxDir.endsWith(File.separator)) {
			ctxDir = ctxDir + File.separator;
		}
		File savePath = new File(ctxDir + uploadDir + autoCreatedDateDir);
		logger.debug("===> save path is: {}", savePath);
		if (!savePath.exists()) {
			savePath.mkdirs();
		}

		List<UploadFile> uploadFileList = new ArrayList<UploadFile>();
		UploadFile uploadFile = null;

		int i = 0;
		Hashtable fileh = form.getMultipartRequestHandler().getFileElements();

		for (Enumeration e = fileh.keys(); e.hasMoreElements();) {
			String key = (String) e.nextElement();

			FormFile formFile = (FormFile) fileh.get(key);
			String fileName = formFile.getFileName().trim();
			if (!"".equals(fileName)) {
				uploadFile = new UploadFile();
				uploadFile.setContentType(formFile.getContentType());
				uploadFile.setFileSize(formFile.getFileSize());
				uploadFile.setFileName(formFile.getFileName().trim());
				uploadFile.setFormName(key);
				String fileSaveName = StringUtils.join(new String[] { UUID.randomUUID().toString(), ".",
						uploadFile.getExtension().toLowerCase() });
				String fileSavePath = uploadDir + autoCreatedDateDir + fileSaveName;
				uploadFile.setFileSaveName(fileSaveName);
				uploadFile.setFileSavePath(StringUtils.replace(fileSavePath, File.separator, "/"));
				logger.debug(uploadFile.toString());
				uploadFileList.add(uploadFile);

				InputStream ins = formFile.getInputStream();
				OutputStream os = new FileOutputStream(ctxDir + fileSavePath);
				int bytesRead = 0;
				byte[] buffer = new byte[8192];
				while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
					os.write(buffer, 0, bytesRead);
				}

				// ftp
				// ftpClientTemplate.storeFile(fileSavePath, ins);
				logger.info("===ctxDir:{}", ctxDir);
				logger.info("===fileSavePath:{}", fileSavePath);

				os.close();
				ins.close();

				// resize image
				if (isResizeImage && formFile.getContentType().indexOf("image/") != -1) {
					// resizeByMaxSize
					for (int v = 0; v < resizeVersions.length; v++) {
						int maxSize = resizeVersions[v];
						String source = ctxDir + fileSavePath;
						FtpImageUtils.resize(source, fileSavePath, maxSize);
					}
				}
				if (isWaterMark && formFile.getContentType().indexOf("image/") != -1) {// 水印
					logger.info("=========aaabbbccc====");
					String watermarkPath = "watermark".concat(File.separator).concat("watermark.png");
					FtpImageUtils.waterMark(ctxDir + fileSavePath, ctxDir + watermarkPath, null);
					FtpUtils.uploadFile(fileSavePath, new File(ctxDir + fileSavePath));
					logger.info("=========aaabbbccc====");
				} else {
					FtpUtils.uploadFile(fileSavePath, new File(ctxDir + fileSavePath));
				}
			}
			i++;
		}
		return uploadFileList;
	}

	/**
	 * 检测用户名是否存在
	 */
	public void checkUserNameIsExist(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String user_name = (String) dynaBean.get("user_name");
		UserInfo userInfo = new UserInfo();
		String isExist = "null";

		if (StringUtils.isNotBlank(user_name)) {
			userInfo.setUser_name(user_name);
			if (null == getFacade().getUserInfoService().getUserInfo(userInfo)) {
				isExist = String.valueOf("0");
			} else {
				isExist = String.valueOf("1");
			}
		}

		super.render(response, isExist, "text/x-json;charset=UTF-8");
	}

	public ActionForward checkPopedomInvalid(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		return isInvalid(request, response, "popedom.check.invalid");
	}

	public ActionForward isInvalid(HttpServletRequest request, HttpServletResponse response, String message)
			throws IOException {

		String msg = super.getMessage(request, message);
		// String cPath = request.getContextPath().concat("/");
		super.renderJavaScript(response, "alert('".concat(msg).concat("');history.back();"));
		return null;
	}

	/**
	 * @desc 显示导航文字
	 */
	protected void setNaviStringToRequestScope(HttpServletRequest request, String oper_name) {
		String mod_id = request.getParameter("mod_id");
		if (StringUtils.isNotBlank(mod_id)) {
			String naviString = " ";
			if (StringUtils.isNotBlank(mod_id)) {
				SysModule sysModule = new SysModule();
				sysModule.setMod_id(new Long(mod_id));
				List<SysModule> sysModuleList = getFacade().getSysModuleService().getSysModuleParentList(sysModule);
				naviString = StringHelper.getNaviStringFromSysModuleList(sysModuleList);
			}
			request.setAttribute("naviString", naviString.concat(oper_name));
		}
	}

	protected void setNaviStringToRequestScope(HttpServletRequest request) {
		String method = request.getParameter("method");
		String oper_name = " ";
		if ("add".equalsIgnoreCase(method)) {
			oper_name = " -&gt; 添加";
		} else if ("edit".equalsIgnoreCase(method)) {
			oper_name = " -&gt; 编辑";
		} else if ("view".equalsIgnoreCase(method)) {
			oper_name = " -&gt; 查看";
		} else if ("audit".equalsIgnoreCase(method)) {
			oper_name = " -&gt; 审核";
		} else {
			oper_name = " -&gt; 列表";
		}

		setNaviStringToRequestScope(request, oper_name);
	}

	protected void getMod_name(Long mod_id, StringBuffer sb) {
		SysModule sysModule = new SysModule();
		sysModule.setMod_id(mod_id);
		// sysModule.setIs_del(0);
		sysModule = this.facade.getSysModuleService().getSysModule(sysModule);
		if (null == sysModule) {
			return;
		}

		if (!"0".equals(sysModule.getPar_id())) {// 不是顶级结点
			getMod_name(sysModule.getPar_id(), sb);
			sb.append(" &gt; ").append(sysModule.getMod_name());
		}

	}

	protected void setNaviStringToRequestScope(HttpServletRequest request, String... mod_names) {
		StringBuffer naviStringSB = new StringBuffer("买卖提物流系统");

		for (String mod_name : mod_names) {
			naviStringSB.append(" &gt; ").append(mod_name);
		}

		request.setAttribute("naviString", naviStringSB.toString());
	}

	public String getSysSetting(String title) throws Exception {
		SysSetting sysSetting = new SysSetting();
		sysSetting.setTitle(title);
		return getFacade().getSysSettingService().getSysSetting(sysSetting).getContent();
	}

	/**
	 * @param encoding 编码，用GBK图形才能正常显示
	 */
	protected void renderXmlWithEncoding(HttpServletResponse response, String text, String encoding) {
		render(response, text, "text/xml;charset=".concat(encoding));
	}

	protected void renderExcelWithEncoding(HttpServletRequest request, HttpServletResponse response, String encoding)
			throws IOException {
		String hiddenHtml = StringUtils.lowerCase(request.getParameter("hiddenHtml"));

		hiddenHtml = StringUtils.replace(hiddenHtml, "border=0", "border=1");
		hiddenHtml = StringUtils.replace(hiddenHtml, "border=\"0\"", "border=\"1\"");

		String fname = EncryptUtilsV2.encodingFileName(request.getParameter("hiddenName"));

		response.setCharacterEncoding(encoding);
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename=" + fname);

		PrintWriter out = response.getWriter();
		out.println(hiddenHtml);

		out.flush();
		out.close();
	}

	public UserInfo getUserInfoFromSession(HttpServletRequest request) {
		return (UserInfo) request.getSession().getAttribute(Keys.SESSION_USERINFO_KEY);
	}

	public String getCtxPath(HttpServletRequest request) {
		StringBuffer ctx = new StringBuffer();
		ctx.append(request.getScheme()).append("://").append(request.getServerName()).append(":").append(
				request.getServerPort());
		ctx.append(request.getContextPath());

		return ctx.toString();
	}

	protected void renderExcel(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String hiddenHtml = StringUtils.lowerCase(request.getParameter("hiddenHtml"));

		hiddenHtml = StringUtils.replace(hiddenHtml, "border=0", "border=1");
		hiddenHtml = StringUtils.replace(hiddenHtml, "border=\"0\"", "border=\"1\"");

		String fname = EncryptUtilsV2.encodingFileName(request.getParameter("hiddenName"));

		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename=" + fname);

		PrintWriter out = response.getWriter();
		out.println(hiddenHtml);

		out.flush();
		out.close();
	}

	/**
	 * @desc 取得分类信息基础数据
	 * @param type : 10,用户类型 20.仓库类型
	 */
	public List<BaseData> getBaseDataList(Integer type, HttpServletRequest request) {
		List<BaseData> baseDataList = new ArrayList<BaseData>();
		BaseData baseData = new BaseData();
		baseData.setType(type);
		baseData.setIs_del(0);
		baseDataList = getFacade().getBaseDataService().getBaseDataList(baseData);
		return baseDataList;
	}

	/**
	 * @desc 将分类信息基础数据List放到session中
	 * @param type : 10,用户类型 20.仓库类型
	 */
	public void setBaseDataListToSession(Integer type, HttpServletRequest request) {
		List<BaseData> baseDataList = new ArrayList<BaseData>();
		BaseData baseData = new BaseData();
		baseData.setType(type);
		baseData.setIs_del(0);
		baseDataList = getFacade().getBaseDataService().getBaseDataList(baseData);
		request.setAttribute("baseData" + type + "List", baseDataList);
	}

	/**
	 * @desc 回显省市县单个的信息
	 */
	public void setprovinceAndcityAndcountryToFrom(DynaBean dynaBean, Long p_index) {
		if (null != p_index) {
			BaseProvince baseProvince = new BaseProvince();
			baseProvince.setP_index(p_index);
			baseProvince = getFacade().getBaseProvinceService().getBaseProvince(baseProvince);
			if (null != baseProvince) {
				if (baseProvince.getPar_index() == 0) {
					dynaBean.set("province", baseProvince.getP_index().toString());
				} else {
					BaseProvince baseP = new BaseProvince();
					baseP.setP_index(baseProvince.getPar_index());
					baseP = getFacade().getBaseProvinceService().getBaseProvince(baseP);
					if (baseP.getPar_index() == 0) {
						dynaBean.set("province", baseP.getP_index().toString());
						dynaBean.set("city", baseProvince.getP_index().toString());
					} else {
						BaseProvince bp = new BaseProvince();
						bp.setP_index(baseP.getPar_index());
						bp = getFacade().getBaseProvinceService().getBaseProvince(bp);
						dynaBean.set("province", bp.getP_index().toString());
						dynaBean.set("city", baseP.getP_index().toString());
						dynaBean.set("country", baseProvince.getP_index().toString());
					}
				}

			}
		}
	}

	protected void setBaseProvinceStringToFrom(ActionForm form, HttpServletRequest request, String p_index) {

		String areaString = "";
		if (StringUtils.isNotBlank(p_index)) {
			BaseProvince baseProvince = new BaseProvince();
			baseProvince.setP_index(new Long(p_index));
			List<BaseProvince> baseProvinceList = getFacade().getBaseProvinceService().getBaseProvinceParentList(
					baseProvince);
			areaString = StringHelper.getAreaStringFromBaseProvince(baseProvinceList);
		}
		request.setAttribute("areaString", areaString);
	}

	/**
	 * @desc用户信息列表
	 */
	public List<UserInfo> getUserInfoList() {

		UserInfo entity = new UserInfo();
		entity.setIs_del(0);
		return getFacade().getUserInfoService().getUserInfoList(entity);// entity
	}

	/**
	 * @desc 回显始发地省市县单个的信息
	 */
	public void setSrcProvinceAndcityAndcountryToForm(DynaBean dynaBean, Long p_index) {
		if (null != p_index) {
			BaseProvince baseProvince = new BaseProvince();
			baseProvince.setP_index(p_index);
			baseProvince = getFacade().getBaseProvinceService().getBaseProvince(baseProvince);
			if (null != baseProvince) {
				if (baseProvince.getPar_index() == 0) {
					dynaBean.set("src_province", baseProvince.getP_index().toString());
				} else {
					BaseProvince baseP = new BaseProvince();
					baseP.setP_index(baseProvince.getPar_index());
					baseP = getFacade().getBaseProvinceService().getBaseProvince(baseP);
					if (baseP.getPar_index() == 0) {
						dynaBean.set("src_province", baseP.getP_index().toString());
						dynaBean.set("src_city", baseProvince.getP_index().toString());
					} else {
						BaseProvince bp = new BaseProvince();
						bp.setP_index(baseP.getPar_index());
						bp = getFacade().getBaseProvinceService().getBaseProvince(bp);
						dynaBean.set("src_province", bp.getP_index().toString());
						dynaBean.set("src_city", baseP.getP_index().toString());
						dynaBean.set("src_country", baseProvince.getP_index().toString());
					}
				}
			}
		}
	}

	/**
	 * @desc 回显目的地省市县单个的信息
	 */
	public void setDestProvinceAndcityAndcountryToForm(DynaBean dynaBean, Long p_index) {
		if (null != p_index) {
			BaseProvince baseProvince = new BaseProvince();
			baseProvince.setP_index(p_index);
			baseProvince = getFacade().getBaseProvinceService().getBaseProvince(baseProvince);
			if (null != baseProvince) {
				if (baseProvince.getPar_index() == 0) {
					dynaBean.set("dest_province", baseProvince.getP_index().toString());
				} else {
					BaseProvince baseP = new BaseProvince();
					baseP.setP_index(baseProvince.getPar_index());
					baseP = getFacade().getBaseProvinceService().getBaseProvince(baseP);
					if (baseP.getPar_index() == 0) {
						dynaBean.set("dest_province", baseP.getP_index().toString());
						dynaBean.set("dest_city", baseProvince.getP_index().toString());
					} else {
						BaseProvince bp = new BaseProvince();
						bp.setP_index(baseP.getPar_index());
						bp = getFacade().getBaseProvinceService().getBaseProvince(bp);
						dynaBean.set("dest_province", bp.getP_index().toString());
						dynaBean.set("dest_city", baseP.getP_index().toString());
						dynaBean.set("dest_country", baseProvince.getP_index().toString());
					}
				}
			}
		}
	}

	/**
	 * 验证是否具有操作权限
	 */
	public Object checkUserModPopeDom(ActionForm form, HttpServletRequest request, String... popedoms) {
		boolean legitimate = false;
		String modPopedom = this.getModPopeDom(form, request);
		if ("+".equals(modPopedom)) {
			return null;
		}

		for (String popedom : popedoms) {
			popedom = popedom.concat("+");
			if (StringUtils.indexOf(modPopedom, popedom) == -1) {
				legitimate = false;
				break;
			}
			legitimate = true;
		}

		if (legitimate) {
			return legitimate;
		}
		return null;
	}

	public String getModPopeDom(ActionForm form, HttpServletRequest request) {
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		StringBuffer popedom = new StringBuffer();

		HttpSession session = request.getSession();
		UserInfo userInfo = (UserInfo) session.getAttribute(Keys.SESSION_USERINFO_KEY);

		RoleUser roleUser = new RoleUser();
		roleUser.setUser_id(userInfo.getId());
		List<RoleUser> roleUserList = this.facade.getRoleUserService().getRoleUserList(roleUser);
		boolean legal = false;
		for (RoleUser temp : roleUserList) {
			if (null == temp) {
				continue;
			}
			legal = true;
			ModPopedom webModPopedom = new ModPopedom();
			webModPopedom.setMod_id(Long.valueOf(mod_id));
			webModPopedom.setRole_id(temp.getRole_id());
			webModPopedom = this.getFacade().getModPopedomService().getModPopedom(webModPopedom);
			if (null != webModPopedom) {
				BasePopedom bp = new BasePopedom();
				bp.setPpdm_code(new Integer(webModPopedom.getPpdm_code()));
				bp = getFacade().getBasePopedomService().getBasePopedom(bp);
				if (null != bp) {
					popedom.append(bp.getPpdm_detail());
				}
			}
		}
		if (legal) {
			popedom.append("+");
		}

		ModPopedom webModPopedom = new ModPopedom();
		webModPopedom.setMod_id(Long.valueOf(mod_id));
		webModPopedom.setUser_id(userInfo.getId());
		webModPopedom = this.getFacade().getModPopedomService().getModPopedom(webModPopedom);
		if (null != webModPopedom) {
			BasePopedom bp = new BasePopedom();
			bp.setPpdm_code(new Integer(webModPopedom.getPpdm_code()));
			bp = getFacade().getBasePopedomService().getBasePopedom(bp);
			if (null != bp) {
				popedom.append(bp.getPpdm_detail());
			}
		}
		popedom.append("+");

		request.setAttribute("popedom", popedom.toString());

		return popedom.toString();
	}

	public String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

}