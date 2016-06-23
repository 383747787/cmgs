define(function (require) {

	/**
	 * 包含所有可复用的filters的引入
	 * 每个filter可以独立作为一个文件在当前文件夹
	 * 每次新建一个filter需要修改该文件引入
	 * 所有的filter都将在App初始化的时候注入
	 * @author Joker
	 */
	
	require('./number-format-filter');

	angular.module('common.filters', [
		'common.filters.number.format'
	]);

});