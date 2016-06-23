/**
 * @author : 叶阳
 * @created : 2015/05/04
 * @version : v1.0
 * @desc : 为模板工具添加额外的方法
 *
 * 模板工具地址
 * https://github.com/aui/artTemplate
 *
 * 对外提供的方法
 * compile(source, options)
 * render(source, options)
 * helper(name, callback)
 * config(name, value)
 */

define(function(require){
	var artTemplate = require("./artTemplate.min");

	/** 
	 * 对日期进行格式化， 
	 * @param date 要格式化的日期 
	 * @param format 进行格式化的模式字符串
	 *     支持的模式字母有： 
	 *     y:年, 
	 *     M:年中的月份(1-12), 
	 *     d:月份中的天(1-31), 
	 *     h:小时(0-23), 
	 *     m:分(0-59), 
	 *     s:秒(0-59), 
	 *     S:毫秒(0-999),
	 *     q:季度(1-4)
	 * @return String
	 */
	artTemplate.helper('dateFormat', function (date, format) {

	    date = new Date(date);

	    var map = {
	        "M": date.getMonth() + 1, //月份 
	        "d": date.getDate(), //日 
	        "h": date.getHours(), //小时 
	        "m": date.getMinutes(), //分 
	        "s": date.getSeconds(), //秒 
	        "q": Math.floor((date.getMonth() + 3) / 3), //季度 
	        "S": date.getMilliseconds() //毫秒 
	    };
	    format = format.replace(/([yMdhmsqS])+/g, function(all, t){
	        var v = map[t];
	        if(v !== undefined){
	            if(all.length > 1){
	                v = '0' + v;
	                v = v.substr(v.length-2);
	            }
	            return v;
	        }
	        else if(t === 'y'){
	            return (date.getFullYear() + '').substr(4 - all.length);
	        }
	        return all;
	    });
	    return format;
	});

	// 除以100，用以计算分到元
	artTemplate.helper('divide100', function (val) {
		var format = (parseFloat(val) / 100).toFixed(2);
    return isNaN(format) ? (val || '') : format;
  });

	return artTemplate;
});