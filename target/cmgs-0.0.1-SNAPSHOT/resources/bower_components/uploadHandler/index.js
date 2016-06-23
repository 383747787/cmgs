/**
 * @author : 叶阳
 * @created : 2015/08/05
 * @version : v1.0
 * @desc : 文件上传模块
 *
 * 使用方法
 * html部分
 * <div class="button" ng-file-select multiple ng-accept="'image/*'|validate($file)" ng-file-change="upload($files)">上传</div>
 *
 * js部分
 * var uploadHandler = new UploadHandler($upload,folder);
 * 参数说明
 *     $upload 上传组件服务
 *     folder 后台约定的又拍云上的目录文件夹
 *
 * 上传方法
 *     uploadHandler.upload(files,opts);
 * 参数说明
 *     files 选择的文件对象
 *     opts
 *         {
 *             success:function(resp,config){},//resp 又拍云返回对象，config 文件信息
 *             progress:function(i,evt){} //i图片索引（多张图片一起上传是所用），evt 上传进度信息
 *         }
 */
define(function (require) {
    angular.module('App').factory("uploadHandler",["$http","$q","$timeout",function($http,$q,$timeout){
        /**
         * 获取远程数据
         */
        var data = {
            policy:'',
            signature:'',
            bucket:"",
            host:""
        };

        function UploadHandler($upload,folder){
            this.$upload = $upload;
            this.urls = [];
            this.folder = folder;
        }

        UploadHandler.YP_UPLOAD_URL = "http://v0.api.upyun.com/";
        UploadHandler.YP_GET_YPIFNO=angular.path+"/upyun/getConfig";

        UploadHandler.prototype.getUrls = function(){
            return this.urls.join(',');
        };

        /**
         * [upload 上传]
         * @param  {[type]} files    [文件数组]
         * @param  {[type]} opts [配置]
         * opts
         * {
         *    fields://额外的参数
         *    progress:function //进度条回调
         *    success:function //成功后回调
         * }
         * @return {[type]}          [description]
         */
        UploadHandler.prototype.upload = function(files,opts){
            var self = this;
            if (files && files.length>0) {
                var promise = self.getYpInfo(opts);
                //获取信息之后执行
                promise.then(function(extOpts){
                    for (var i = 0; i < files.length; i++) {
                        var sendData = {file:files[i]};
                        sendData = angular.extend(sendData,extOpts.data);
                        //上传文件
                        self.$upload.upload({
                            url: UploadHandler.YP_UPLOAD_URL+opts.bucket,
                            data: sendData
                        }).then(function(resp){//success
                            if(resp!=null){
                                if(resp.data&&resp.data.message && resp.data.message=="ok"){
                                    resp.data.url = opts.host+"/"+resp.data.url;
                                    self.urls.push(resp.data.url);
                                    if(typeof extOpts.success == "function"){
                                        extOpts.success(resp.data,resp.config);
                                    }
                                }else{
                                    console.log(resp);
                                }
                            }
                        },function(resp){//error

                        },function (evt) {//progress
                            var progressPercentage = parseInt(100.0 * evt.loaded / evt.total);
                            console.log('progress: ' + progressPercentage + '% ' + evt.config.data.file.name);
                            //var progressPercentage = parseInt(100.0 * evt.loaded / evt.total);
                            //console.log(progressPercentage);
                            if(typeof extOpts.progress == "function"){
                                extOpts.progress(i,evt);
                            }
                        });
                    }
                });
            }
        }

        UploadHandler.prototype.getYpInfo = function(opts){
            var self = this;
            var deferred = $q.defer();

            /*$timeout(function(){
             opts.data = data;
             deferred.resolve(opts);
             },200);*/
            $http.jsonp(UploadHandler.YP_GET_YPIFNO+"?type="+self.folder+"&callback=JSON_CALLBACK").success(function(rs){
                for ( var key in data) {
                    data[key] = rs[key];
                }
                opts.data = {policy:data['policy'],signature:data['signature']};
                angular.extend(opts,data);
                deferred.resolve(opts);
            });

            return deferred.promise;
        };

        return UploadHandler;
    }]);
});