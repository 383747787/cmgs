/**
 * path    : lib/ext/angular/angularExt
 * create  :  2015/02
 * author  :  Winner
 * 
 */

define(function(require,exports,module) {
	
	var C = function(){};
	
	var p = C.prototype;
	/**
	 * 将数据转换成 x-www-form-urlencoded 格式。
	 * 解决原生 $http 的 post 请求时无法模拟真正表单请求的头及数据格式的问题。
	 */
	C.http = function($http){
		return {
			post: function(url, param){
				return $http({method: 'post', url: url, data: formSeriealize(param),
					headers:{'Content-Type': 'application/x-www-form-urlencoded'}
				});
			}
		}
	}
	function formSeriealize(data) {
		var param = function(obj) {
			var query = '';
			var name, value, fullSubName, subName, subValue, innerObj, i;
	
			for (name in obj) {
				value = obj[name];
	
				if (value instanceof Array) {
					for (i = 0; i < value.length; ++i) {
						subValue = value[i];
						fullSubName = name + '[' + i + ']';
						innerObj = {};
						innerObj[fullSubName] = subValue;
						query += param(innerObj) + '&';
					}
				} else if (value instanceof Object) {
					for (subName in value) {
						subValue = value[subName];
						fullSubName = name + '[' + subName + ']';
						innerObj = {};
						innerObj[fullSubName] = subValue;
						query += param(innerObj) + '&';
					}
				} else if (value !== undefined && value !== null) {
					query += encodeURIComponent(name) + '=' + encodeURIComponent(value) + '&';
				}
			}
	
			return query.length ? query.substr(0, query.length - 1) : query;
		};
		return angular.isObject(data) && String(data) !== '[object File]' ? param(data) : data;
	}
	module.exports = C;
});


