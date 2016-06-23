
/*
 * path    : lib/utils/StringUtils
 * author  : swing
 * 通用工具类，主要方法如下：
 * getParamFromUrl：从当前URL中获取参数
 * getParamsFromUrl：从当前URL键值对转换成对象
 * parseParamFromSplit：针对双重分隔符字符串中根据key取得value值
 * parseParamsFromSplit：针对双重分隔符字符串的键值对转换成对象
 */
define(function(require,exports,module){
	
	module.exports = {
		/**
		 * 从当前URL中获取参数
		 * 如：从 ?a=11&b=22 URL参数中取得 a 或 b 变量
		 */
		getParamFromUrl:function(param){
			return getParamsFromUrl()[param];
		},
		/**
		 * 从当前URL键值对转换成对象
		 */
		getParamsFromUrl:function(){
			var search = location.search;
			if(search.length > 0){
				return parseParamsFromSplit(search.substring(1), '&', '=');
			}
			return {};
		},
		/**
		 * 针对双重分隔符字符串中根据key取得value值
		 * 如：a=11&b=22 ，依key取出value，如果是key为a，则值为11
		 * iSplit: 项分隔符，如 &
		 * vSplit: 值分隔符，如 =
		 */
		parseParamFromSplit:function(str, key, iSplit, vSplit){
			var params = str.split(iSplit);
			for(var i=0; i<params.length; i++){
				if(params[i].indexOf(key) == 0){
					return decodeURIComponent(params[i].replace(key+vSplit, ''));
				}
			}
			return parseParamsFromSplit(search.substring(1), iSplit, vSplit)[key];
		},
		/**
		 * 针对双重分隔符字符串的键值对转换成对象
		 */
		parseParamsFromSplit:function(str, iSplit, vSplit){
			var rs = {};
			var params = str.split(iSplit);
			for(var i=0; i<params.length; i++){
				var kv = params[i].split(vSplit);
				if(kv.length = 2){
					rs[kv[0]] = decodeURIComponent(kv[1]);
				}
			}
			return rs;
		}
	}
});

