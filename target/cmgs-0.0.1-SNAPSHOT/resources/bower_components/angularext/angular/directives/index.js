define(function (require) {

	/**
	 * 包含所有可复用的directives的引入
	 * 每个directive可以独立作为一个文件在当前文件夹
	 * 每次新建一个directive需要修改该文件引入
	 * 所有的directive都将在App初始化的时候注入
	 */
	
	require('./input-percent');
	require('./input-per');

	require('./uiselect/select.min.css');
	require('./uiselect/selectize.min.css');
	require('./uiselect/select.min');

	require('./editor/editor');

	require('./menu');

	require('./cmdatepicker');
	
	require('./textReplace');

	angular.module('common.directives', [
		'common.directives.input.percent',
		'common.directives.input.per',
		'common.directives.menu',
		'common.directives.cmdatepicker',
		'common.directives.textreplace',
		'ui.select',
		'summernote'
	]);

});