/*
 * path    : lib/utils/prototype/Function
 * create  :  2014-02
 * author  :  swing
 * 开发规则扩展及核心函数扩展
 */
//绑定方法，用于this指针绑定作用
if(!Function.prototype.bind){
	Function.prototype.bind = function(obj) {
		var m = this;
		return function() {
			return m.apply(obj, arguments);
		}
	}
}
////属性扩展，从source拷贝到dest中
//Object.extend = function(dest, source) {
//	for (property in source) {
//		dest[property] = source[property];
//	}
//	return dest;
//}
//判断整个对象是否相等
Object.equals = function(dest, source) {
	for (property in source) {
		if(dest[property]!=source[property])
			return false;
	}
	return true;
}

