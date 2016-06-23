define(function (require) {

	/**
	 * 包含所有可复用的service的引入
	 * 每个service可以独立作为一个文件在当前文件夹
	 * 每次新建一个service需要修改该文件引入
	 * 所有的service都将在App初始化的时候注入
	 * @author 叶阳
	 */
	
	require('./local/angular-locale_zh-cn');

	angular.module('common.services', []);
});