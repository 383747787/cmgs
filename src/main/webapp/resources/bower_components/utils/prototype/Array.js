/*
 * path    : lib/utils/prototype/Array
 * create  :  2014-02
 * author  :  swing
 * Array原型扩展
 */
Array.prototype.clear = function() {
	this.length = 0;
	return this;
}
Array.prototype.first = function() {
    return this[0];
}
Array.prototype.last = function() {
    return this[this.length - 1];
}
Array.prototype.contains = function(item) {
	for ( var i = 0 ; i < this.length ; i++ ) {
		if ( this[i] == item ) 
			return true;
	}
	return false;
};

Array.prototype.remove = function(dx) {
    if( isNaN(dx) || dx > this.length )
        return false;
	for( var i=0,n=0; i<this.length; i++ ){
		if( i != dx )
			this[n++]=this[i];
	}
	this.length-=1;
};
Array.prototype.removeItem = function(item) {
    for ( var i = 0 ; i < this.length ; i++ ) {
        if ( this[i] == item ) {
            this.remove(i);
			break;
    	}
    }
};
Array.prototype.removeAll = function() {
	for( var i=this.length-1; i>=0; i-- ){
		this[i]=null;
	}
	this.length=0;
};