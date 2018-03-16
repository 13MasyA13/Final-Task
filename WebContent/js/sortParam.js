function setAttr(prmName,val){
    var res = '';
	var d = location.href.split("#")[0].split("?");
	var base = d[0];
	var query = d[1];
	if(query) {
		var params = query.split("&");
		for(var i = 0; i < params.length; i++) {
			var keyval = params[i].split("=");
			if(keyval[0] != prmName) {
				res += params[i] + '&';
			}
		}
	}
	res += prmName + '=' + val;
	window.history.pushState(null, null, base + '?' + res);
	return false;
}
