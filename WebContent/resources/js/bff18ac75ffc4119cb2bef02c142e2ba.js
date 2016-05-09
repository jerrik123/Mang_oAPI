var wiki={
"bindNavEvent":function(pathname){
	var path = pathname.split("/").slice(2),nav=[],curNav,getId = function(s){return "n-"+encodeURIComponent(s).replace(/%/g,".");};
	$("#mw-panel").find(".portal").css("margin-top","-1px");//.find(".body").hide();//@raphealguo
	$("#mw-panel").find("h5").click(function(){
		var t = $(this),b = t.next(".body"),p = t.parent();
		if (b.is(":visible")){
			b.slideUp("fast");
			p.removeClass("active");
		}else{
			b.find("li").find("em").addClass("none");
			b.slideDown("fast",function(){
				b.find("li").find("em").removeClass("none");
			});
			p.addClass("active");
		}
		$(this).parent().siblings(".portal").removeClass("active").find(".body").slideUp("fast");
	});
	
	$("#mw-panel  li .del").click(function(){
		alert("删除该项");
	});
}
};


