/*
 * path    : lib/utils/prototype/String
 * create  :  2014-02
 * author  :  swing
 * String原型扩展
 */
String.prototype.trim = function() {
    return this.replace(/(^\s*)|(\s*$)/g, "");
}
String.prototype.isNumber = function() {
    var myReg = /^-?[0-9]+$/;
    if (!myReg.test(this)) return false;
    ActRd = parseInt(this);
    return true;
}
String.prototype.isFloat = function() {
    var myReg = /^-?[0-9.]+$/;
    if (!myReg.test(this)) return false;
    var pos = this.indexOf('.');
    if (pos == -1) return false;
    if (pos != this.lastIndexOf('.')) return false;
    if (pos == 0 || (pos + 1) == this.length) return false;
    ActRd = parseFloat(this);
    return true;
}
String.prototype.len = function() {
    var data = this.toString();
    var len = 0;
    for (var i = 0; i < data.length; i++) {
        var c = data.charCodeAt(i);
        if (c > 0x80) len += 2;
        else len += 1;
    }
    return len;
}
String.prototype.brief = function(length) {
    var len = 0;
    var buf = '';
    for (var i = 0; i < this.length; i++) {
        var c = this.charCodeAt(i);
        if (c > 0x80) len += 2;
        else len += 1;
        if (len <= length) buf += this.charAt(i);
        else return buf;
    }
    return buf;
}
String.prototype.subchar = function(start, stop) {
    var len = 0;
    var buf = '';
    start = start || 0,
    stop = stop || -1;
    for (var i = 0; i < this.length; i++) {
        var last2Len = 0;
        var c = this.charCodeAt(i);
        if (c > 0x80) {
            len += 2;
            last2Len = 1;
        } else len += 1;
        if (len <= (start + last2Len)) continue;
        if (stop == -1 || len <= stop) buf += this.charAt(i);
        else return buf;
    }
    return buf;
}
String.prototype.messageFormat = function(args) {
    var v = this;
    if (args && args instanceof Array) {
        for (var i = 0; i < args.length; i++) {
            var reg = new RegExp("(.*)(\\{" + i + "\\})(.*)", "m");
            if (reg.test(v)) {
                v = v.replace(reg, RegExp.$1 + args[i] + RegExp.$3);
            }
        }
    }
    return v;
}