<!--
var divs = {};
function load(name) {
	document.getElementById(name).style.visibility = "visible";
	var cover = document.getElementById("cover");
	cover.style.height = document.body.scrollHeight;
	cover.style.visibility = "visible";
	divs[name] = name;
	
	//隐藏页面的select，解决div不能覆盖select的问题
	//	var selectarray=document.getElementsByTagName("select");
	//	for(i=0;i<selectarray.length;i++){
	//	  selectarray[i].style.display="none";
	//	}
	
}
function unload(name){
	document.getElementById(name).style.visibility = "hidden";
	document.getElementById("cover").style.visibility = "hidden";
	divs[name] = null;
	//	var selectarray=document.getElementsByTagName("select");
	//	for(i=0;i<selectarray.length;i++){
	//	  selectarray[i].style.display="";
	//	}
	
}

$(window).scroll(function() {
	for (var p in divs) {
		if (null != p) {
			var x = document.getElementById(p);
			//x.style.left = (document.body.clientWidth  - x.clientWidth )/2 + document.documentElement.scrollLeft;
			x.style.top  = (document.body.clientHeight - x.clientHeight) + document.documentElement.scrollTop;
		}
	}	
}); 
//-->

