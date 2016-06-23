/*
 * path    : lib/utils/Cookie
 * author  : winner
 * Cookie的操作类
 */
define(function(require, exports, module) {
	//类定义
	var C = Cookie = function() { //构造方法
	}
	/** 设置，expires有效期单位为小时 */
	C.setCookie = function(name, value, expires, path, domain, secure) {
		var cookie = name + "=" + escape(value);
		expires = expires || 0;
		if(expires > 0){
			var date = new Date();
			date.setTime(date.getTime() + expires * 3600 * 1000); 
			cookie += "; expires=" + date.toGMTString();
		}
		cookie += ((path) ? "; path=" + path: "") + ((domain) ? "; domain=" + domain: "") + ((secure) ? "; secure": "");
		document.cookie = cookie;
	}
	/** 读取 */
	C.getCookie = function(name, noUnescape) {
		var prefix = name + "="
		var start = document.cookie.indexOf(prefix);
		if (start == -1) {
			return null;
		}
		var end = document.cookie.indexOf(";", start + prefix.length);
		if (end == -1) {
			end = document.cookie.length;
		}
		var value = document.cookie.substring(start + prefix.length, end);
		return noUnescape ? value: unescape(value);
	}
	/** 删除 */
	C.deleteCookie = function(name, path, domain) {
		if (Cookie.getCookie(name)) {
			document.cookie = name + "=" + ((path) ? "; path=" + path: "") + 
				((domain) ? "; domain=" + domain: "") + "; expires=Thu, 1 Jan 1970 00:00:00 UTC"; //Thu, 01-Jan-70 00:00:01 GMT";
		}
	}
	/** 判断是否支持Cookie */
	C.cookiesDisabled = function() {
		var result = true;
		// some browser versions support this - use it if possible
		if (navigator.cookiesEnabled) return false;
		// else try to set and read a cookie
		document.cookie = "testcookie=yes;";
		var cookieSet = document.cookie;
		if (cookieSet.indexOf("testcookie=yes") > -1) {
			result = false;
		}
		document.cookie = "testcookie=;expires=;";
		return result;
	}

	//导出类
	module.exports = Cookie;
});