define(function (require) {

	var http = require('bower_components/angularext/angularExt').http;
			   require('bower_components/utils/DateUtils');

	angular.module('App').
	factory('CommonList', ['$http', function ($http) {

		var CommonList = function(){
			this.config = {};
			this.dateFormat = 'yyyy-MM-dd';
			this.startopened = this.endopened = false;

			this.fetching = false;

			this.data = {
				totalSize: 0
			};

			this.filter = {
				page: 1,
				pageSize: 20
			};
		};

		/**
		 * [getFormatedDate 时间格式化]
		 * @param  {[type]} date [description]
		 * @return {[type]}      [description]
		 */
		function getFormatedDate(date) {
			if(typeof date == "string"){
				return date;
			}
			return date instanceof Date ? date.format("yyyy-MM-dd") : null;
		}

		CommonList.prototype.getFilterData=function(filter) {
			var f = angular.copy(filter);
			if(typeof f.search == "undefined"){
				return f;
			}
			for(k in f.search){
				if(f.search[k] instanceof Date){
					f.search[k] = getFormatedDate(f.search[k]);
				}
			}

			return f;
		}

		CommonList.prototype.setConfig = function(url,filter){
			this.url = url;
			if(filter){
				this.filter = angular.extend(this.filter,filter);
			}
		}


		CommonList.prototype.fetch = function () {
			var self = this;
			self.fetching = true;

			$http.post(angular.path + this.url, this.getFilterData(self.filter))
				.success(function (resp) {
					self.data = resp;
					self.fetching = false;

				})
				.error(function () {
					self.fetching = false;
				});
		};

		CommonList.prototype.search = function () {
			this.filter.page = 1;
			this.filter.pageNumber=1;
			this.fetch();
		};

		CommonList.prototype.openCal = function (event, type) {
			event.preventDefault();
			event.stopPropagation();

			this[type + 'opened'] = true;
		};

		// return this;
		return CommonList;
	}]);

});
