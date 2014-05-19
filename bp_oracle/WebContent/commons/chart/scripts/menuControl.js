	//用户信息数组，1维用户类型名称，2维用户登录名，3维用户菜单编码,4维系统用户类型（1企业，2管理部门），5维用户类型编码
	var UserArray = new Array();
	UserArray[0] = new Array('生产资料流通行业','0001','1,2,12,3,63,64,65,69','1','1');
	UserArray[1] = new Array('零售业','0002','1,2,12,4,5,6,7,63,64,65,66,69','1','2');
	UserArray[2] = new Array('餐饮业','0003','1,2,12,8,63,64,65,69','1','3');
	UserArray[3] = new Array('洗浴服务业','0004','1,2,12,9,63,64,65,69','1','4');
	UserArray[4] = new Array('理发及美容保健服务','0005','1,2,12,10,63,64,65,69','1','5');
	UserArray[5] = new Array('会展服务业','0006','1,2,12,11,63,64,65,69','1','6');
	
	UserArray[6] = new Array('系统用户','admin','1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,67,68,69','2','7');
	UserArray[7] = new Array('商务部用户','swb','1,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,54,55,56,69','3','8');
	UserArray[8] = new Array('商务主管用户','anhui','39,42,1,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,57,58,59,67,68,69','2','9');
	UserArray[9] = new Array('中国物流信息中心','wlxxzx','39,42,1,13,14,15,16,17,18,19,29,60,61,62,20,30,67,69','2','10');
	UserArray[10] = new Array('中国连锁经营协会','lsxh','39,42,1,13,14,15,16,17,18,19,29,60,61,62,21,31,67,69','2','11');
	UserArray[11] = new Array('中国商业联合会','sylhh','39,42,1,13,14,15,16,17,18,19,29,60,61,62,22,23,32,33,67,69','2','12');
	UserArray[12] = new Array('全国酒家酒店办公室','jjjd','39,42,1,13,14,15,16,17,18,19,29,60,61,62,25,35,67,69','2','13');
	UserArray[13] = new Array('中国商业联合会沐浴专业委员会','myxh','39,42,1,13,14,15,16,17,18,19,29,60,61,62,26,36,67,69','2','14');
	UserArray[14] = new Array('中国美发美容协会','mfmrxh','39,42,1,13,14,15,16,17,18,19,29,60,61,62,27,37,67,69','2','15');
	UserArray[15] = new Array('全国城市工业品贸易中心联合会','mylhh','39,42,1,13,14,15,16,17,18,19,29,60,61,62,28,38,67,69','2','16');
	UserArray[16] = new Array('中国百货商业协会','bhsyxh','39,42,1,13,14,15,16,17,18,19,29,60,61,62,21,31,67,69','2','11');


	//将用户登录信息写入到浏览器cookie中
	function SetCookie(cookieValue, cookieHours)
	{
		//系统用户类型（1企业，2管理部门）
		var sysUserType;		
		//标识当前用户是否存在
		var blnHas=false;
		//用户菜单编码
		var userMenuCode;
		//用户类型编码
		var userTypeCode;

		$.each
		( 
			UserArray, 
			function(i, n)
			{
				if(n[1]==cookieValue)
				{
					//alert( "用户类型名称："+n[0]+"，用户登录名：" + n[1] + "，用户菜单编码： " + n[2] +"系统用户类型（0企业，1管理部门）:"+n[3]+"用户类型编码"+n[4]);
					userMenuCode=n[2];
					sysUserType=n[3];
					userTypeCode=n[4];
					blnHas=true;
					return false;
				}
			}
		);
		
		if(blnHas)
		{
			//用户名
			writeCookie("UserCode", cookieValue, cookieHours);
			//操作菜单编码
			writeCookie("UserMenuCode", userMenuCode, cookieHours);
			//用户类型编码
			writeCookie("userTypeCode", userTypeCode, cookieHours);
			
			//alert(sysUserType);
			if(sysUserType=="1")
			{
				window.location.href="system/edit_reportforms.html";
			}
			else if(sysUserType=="2")
			{
				window.location.href="system/manage_uncheck.html";
			}
			else if(sysUserType=="3")
			{
				window.location.href="system/search_sended.html";
			}
		}
		else
		{
			alert("您输入的用户名不存在，请重新输入！");
		}
		
	}
	
	//读取指定的cookie
	function GetCookie(cookieName)
	{
		return readCookie(cookieName);
	}
		

	//$("div[id]");
	//页面加裁时处理用户能操作的菜单
	$(document).ready(function()
	 {
		//用户名
		var UserCode=GetCookie("UserCode");
		//用户类型编码
		var userTypeCode=GetCookie("userTypeCode");
		//获取用户操作菜单编码
		var UserMenuCode=GetCookie("UserMenuCode");
		//用户操作菜单数组
		var arr=UserMenuCode.split(",");
		
		//alert("登录后的用户名："+UserCode+"\n登录后的用户菜单编码："+UserMenuCode);
	
		$("#column > h3").hide();
		$("#column > ul > li").hide();
		
		//用户菜单分配操作
		$.each
		( 
			arr, 
			function(i, n)
			{
				$("#column > h3[sysMenu="+n+"]").show();
				$("#column > ul > li[sysMenu="+n+"]").show();
			}
		);
		
		//不同用户操作 报表修改 报表审核 时的显示问题
		$("#showModRept tr").hide();
		$("#showModRept tr:first").show();
		var tdID=1;
		$.each
		(
		 	$("#showModRept tr"),
			function(i,n)
			{
				//alert($(n).attr("sysShow")+":"+userTypeCode);
				if($(n).attr("sysShow")==userTypeCode)
				{
					//alert($(n).find("td").length);	//此行中的单元格数量
					$(n).show();
					$(n).find("td:eq(0)").html(tdID);
					//表格隔行换色
					if(tdID % 2==0)
					{
						$(n).addClass("repeat");
					}
					tdID++;
				}
			}
		 )
		
		//表格隔行换色，鼠标划过及移除时颜色切换
		$("#showModRept tr")
		.mouseover(function()
			{  
				//鼠标划过时背景色
				$(this).addClass("over");
			}
		)
		.mouseout(function()
			{
				//鼠标移除时去除背景色
				$(this).removeClass("over");
			}
		)
		//表格隔行换色
		//$("#showModRept tr:odd").addClass("repeat");
		
	 });
	 
	var showDate;
	var now=new Date();
	showDate=now.getYear()+"年"+(now.getMonth()+1)+"月"+now.getDate()+"日";
	//document.write("现在是"+now.getYear()+"年"+(now.getMonth()+1)+"月"+now.getDate()+"日"+now.getHours()+"时"+now.getMinutes()+"分"+now.getSeconds()+"秒") 