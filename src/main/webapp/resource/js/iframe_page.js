(function(){
	
	
	$("input").resize(function() {
		var obj = this;
		setTimeout(function(){
			obj.scrollIntoView(false);
			window.parent.resetIframePage1();
		},200)
	})
	$("input").focus(function() {
		var obj = this;
		setTimeout(function(){
			obj.scrollIntoView(false);
			window.parent.resetIframePage1();
		},200)
	})
	$("input").blur(function() {
		var obj = this;
		setTimeout(function(){
			obj.scrollIntoView(false);
			window.parent.resetIframePage1();
		},200)
	})
	$("textarea").resize(function() {
		var obj = this;
		setTimeout(function(){
			obj.scrollIntoView(false);
			window.parent.resetIframePage1();
		},200)
	})
	$("textarea").focus(function() {
		var obj = this;
		setTimeout(function(){
			obj.scrollIntoView(false);
			window.parent.resetIframePage1();
		},200)
	})
	$("textarea").blur(function() {
		var obj = this;
		setTimeout(function(){
			obj.scrollIntoView(false);
			window.parent.resetIframePage1();
		},200)
	})
}).call(this)


