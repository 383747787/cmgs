
/*
 * path    : lib/common/utils/BrowserUtils
 * author  : swing
 * 通用工具类，主要方法如下：
 * copyToBoard：拷贝文本到粘贴板
 */
define(function(require,exports,module){
	
	module.exports = {
		/*
		 * 拷贝文本到粘贴板
		 * @return 
		 * true - 表示拷贝成功；
		 * false - 目前只有FireFox可能出现错误
		 * 被浏览器拒绝！\n请在浏览器地址栏输入'about:config'并回车\n然后将'signed.applets.codebase_principal_support'设置为'true'
		 */
		copyToBoard:function(txt){
			if(window.clipboardData){
				window.clipboardData.clearData();
				window.clipboardData.setData("text",txt);
			}else if(navigator.userAgent.indexOf("Opera")!=-1){
				window.location=txt;
			}else if(window.netscape){
				try{
					netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect");
				}catch(e){
					return false;
				}
				var clip=Components.classes['@mozilla.org/widget/clipboard;1'].createInstance(Components.interfaces.nsIClipboard);
				if(!clip)return;
				var trans=Components.classes['@mozilla.org/widget/transferable;1'].createInstance(Components.interfaces.nsITransferable);
				if(!trans)return;
				trans.addDataFlavor('text/unicode');
				var str=new Object();
				var len=new Object();
				var str=Components.classes["@mozilla.org/supports-string;1"].createInstance(Components.interfaces.nsISupportsString);
				var copytext=txt;str.data=copytext;
				trans.setTransferData("text/unicode",str,copytext.length*2);
				var clipid=Components.interfaces.nsIClipboard;
				if(!clip)return;
				clip.setData(trans,null,clipid.kGlobalClipboard);
			}
			return true;
		},
		/*
		 * 获取Flash版本
		 */
		getFlashVersion:function(){
			var v;
			if(navigator.plugins&&navigator.mimeTypes.length){
				var x=navigator.plugins["Shockwave Flash"];
				if(x&&x.description){
					v=x.description.replace(/([a-z]|[A-Z]|\s)+/,"").replace(/(\s+r|\s+b[0-9]+)/,".").split(".");
				}
			}else{
				try{
					var axo=new ActiveXObject("ShockwaveFlash.ShockwaveFlash");
					v=axo.GetVariable("$version").split(" ")[1].split(",");
				}catch(e){
					try{
						for(var i=5;axo!=null;i++){
							axo=new ActiveXObject("ShockwaveFlash.ShockwaveFlash."+i);
							v=[i,0,0];
						}
					}catch(e){}
				}
			}
			var r=new Object();
			r.major=v? parseInt(v[0])||0:0;
			r.minor=v? parseInt(v[1])||0:0;
			r.rev=v? parseInt(v[2])||0:0;
			return r;
		},
		/**
		 * [getUrlParam 获取浏览器路径search参数]
		 * @param  {[string]} key
		 * @return {[string]} value
		 */
		getUrlParam:function(key){
			var reg = new RegExp("(^|&)"+ key +"=([^&]*)(&|$)");
			var r = window.location.search.substr(1).match(reg);
			if (r!=null) return unescape(r[2]); return null;
		},
		/**
		 * 渐进跳转到顶部
		 * @param pos 跳转到的位置 默认0
		 * @param time 延迟的时间 默认600
		 */
		scrollTop:function(pos,time){
			pos = pos || 0;
			time = time || 600;
			if ($(document.body).scrollTop()) {
				$(document.body).animate({
					scrollTop: pos
				}, time);
			} else {
				$(document.documentElement).animate({
					scrollTop: pos
				}, time);
			}
		},
		getFormatedDate:function(date){
			if(typeof date == "string"){
                return date;
            }else{
                return date instanceof Date ? date.getFullYear() + '-' + ((date.getMonth() + 1)<=9?'0'+(date.getMonth() + 1):(date.getMonth() + 1)) + '-' + (date.getDate()<=9?'0'+date.getDate():date.getDate()) : null;
            }
		}
	}
});

