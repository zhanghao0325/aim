String.prototype.Trim = function() {    
    return this.replace(/(^\s*)|(\s*$)/g, "");    
}