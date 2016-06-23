/**
 * 浮层提示信息
 * @auth   宇尘
 * @date   2015-06-10
 * @demo  
 *  new Fixtip({
 * 		msg: '格式错误',
 * 		timer: 3000, 			选填 显示时间 默认3000毫秒
 * 		bottom: '100px', 		选填 距离底部的位置 默认为10%
 * 		callback:function(){} 	选填
 * 	})
 * @desc 
 * @return {Object} Fixtip构造函数
 */
define(function(require, exports, module) {
	require('../zepto/zepto.min');
	require('./fixtip.css');

	function Fixtip(options) {
		this.options = options;

		this._init();
		this._render();
	}

	Fixtip.prototype._init = function() {
		var defaults = {
			timer: 3000,
			bottom: '10%'
		}

		this._config = $.extend({}, defaults, this.options);
	}

	Fixtip.prototype._render = function() {
		var config = this._config;
		var $fixtip = null;

		if ($('.ui-fixtip').length == 0) {
			$fixtip = $('<div class="ui-fixtip"><span>'+
				config.msg+'</span></div>').appendTo('body');

			$fixtip.css({bottom: config.bottom});

			setTimeout(function() {
				$fixtip.remove();
				if(typeof config.callback == "function"){
					config.callback();
				}
			}, config.timer);
		}
		
	}

	module.exports = Fixtip;

});