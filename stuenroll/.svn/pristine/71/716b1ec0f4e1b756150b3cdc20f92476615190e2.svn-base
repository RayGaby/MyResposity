$(function(){
	
	if (!checkPermission([ "8_5" ])) {
		return;
	}
	
	
	var I_Detail = function(){
		
	}
	
	I_Detail.prototype.showMenu = function($scope,event){
		throw "抽象方法";
	}
	I_Detail.prototype.hideMenu = function(){
		throw "抽象方法";
	}
	I_Detail.prototype.init = function(){
		throw "抽象方法";
	}
	I_Detail.prototype.searchName = function(json){
		throw "抽象方法";
	}
	I_Detail.prototype.queryVisitList = function(json){
		throw "抽象方法";
	}
	I_Detail.prototype.queryVisitListCount = function(json){
		throw "抽象方法";
	}
	
	I_Detail.prototype.deleteById = function(json){
		throw "抽象方法";
	}
	
	
	var Detail = function(){
		
	}
	
	Detail.prototype = new I_Detail();
	
	Detail.prototype.showMenu = function($scope, event){
		$obj = $(event.target).find(".menu");
		$(".menu").fadeOut();
		$obj.parent().siblings().removeClass("item-active");
		$obj.parent().addClass("item-active");
		$obj.fadeIn();
		event.stopPropagation();
	}
	Detail.prototype.hideMenu = function(){
		var obj = $(".menu");
		obj.fadeOut();
		obj.parent().removeClass("item-active");
		DropDown.closeDropDown();
	}
	Detail.prototype.init = function(){
		//Menu之外任意位置点击鼠标，menu消失
		var obj = this;
		$(document).click(function(){
			obj.hideMenu();
		});
		
		//menu内部不消失
		$(".menu").click(function(event){
			event.stopPropagation();
		});
		
	}
	
	Detail.prototype.searchName = function(json){
		$.ajax({
			"url" : "/stuenroll/visit/searchOraganizationAndClass",
			"type" : "post",
			"async" :false,
			"dataType" : "json",
			"data" : json,
			"success" : function(json){
				var data = json.result;
				$("#organization").text(data.organization);
				$("#classinfo").text(data.classinfo);
			},
			"error" : function(){
				toastr.error("系统异常");
			}
 		});
	}
	
	Detail.prototype.queryVisitList = function(json){
		$.ajax({
			"url" : "/stuenroll/visit/queryVisitList",
			"type" : "post",
			"dataType" : "json",
			"async" :false,
			"data" : json,
			"success" : function(json){
				var data = json.result;
				
				var table = $(".tab .tab-content .fixed-table");
				//清空表格数据
				table.find("tr:gt(0)").remove();
				
				//获得当前页数
				var currentPage = $("#currentPage").text();
				//转化为数字类型

				currentPage = new Number(currentPage);
				//当前页数的行号起始数字
				var start = (currentPage -1 ) * 3;
				var temp = "";
				for(var i = 0; i < data.length; i++){
					var one = data[i];
					
					temp += "<tr>";
					temp += "<td><input type='checkbox' name='id' value='"+one.id+"' /></td>";
					temp += "<td>" + (start + i + 1) + "</td>";
					temp += "<td>" + one.name + "</td>";
					temp += "<td>" + one.datetime + "</td>";
					temp += "<td name='time'>" + one.time +"</td>";
					temp += "<td>" + one.user + "</td>";
					temp += "<td>" + one.satisfy_degree + "</td>";
					temp += "<td>" + one.change + "</td>";
					temp += "<td class='click'>[ 详细记录 ]</td>";
					
					temp += "</tr>";
				}
				table.append(temp);
				click();
			},
			"error" : function(){
				toastr.error("系统异常");
			}
		});
	}
	
	Detail.prototype.queryVisitListCount = function(json){
		$.ajax({
			"url" : "/stuenroll/visit/queryVisitListCount",
			"type" : "post",
			"dataType" : "json",
			"async" :false,
			"data" : json,
			"success" : function(json){
				var data = json.result;
				var count = data.count;
				$("#totalRows").text(count);
				
				
				
				var totalPages = (count % 3 == 0) ? count / 3 : Math.floor(count/3) + 1;
				$("#totalPages").text(totalPages);
			},
			"error" : function(){
				toastr.error("系统异常");
			}
		});
	}
	
	Detail.prototype.deleteById = function(){
		//获取被选中的记录
		var content = $(".tab .tab-content .fixed-table");
		var checkbox = content.find("*[name='id']:checked");//被选中的复选框
		
		if(checkbox.length == 0){
			toastr.warning("请选中需要删除的回访记录");
		}
		else{
			
			var id = [];//定义数组
			
			var bool = confirm("是否删除选中的记录？");
			if(bool == false){
				return;
			}
			
			for(var i = 0; i < checkbox.length; i++){
				id.push($(checkbox[i]).val());
			}
			$.ajax({
				"url" : "/stuenroll/visit/deleteVisitById",
				"type" : "post",
				"dataType" : "json",
				"traditional" : true,
				"async" : false,
				"data" : {
					"id" : id
				},
				"success" : function(json){
					toastr.success("删除了" + json.deleteRows + "条记录");
				},
				"error" : function(){
					toastr.error("系统异常");
				}
			});
		}

	}
	
	function upload(fileName){
		$.ajaxFileUpload({
 			url : '/stuenroll/visit/importFile?classinfoId='+classinfoId,   //提交的路径
 			secureuri : false, // 是否启用安全提交，默认为false
 			fileElementId : 'file', // file控件id
 			async : false,
 			dataType : 'json',
 			data : {
 				fileName : fileName   //传递参数，用于解析出文件名
 			}, // 键:值，传递文件名
 			success : function(json) {
 				var count = json.result
 				if (count == -1) {
 					toastr.warning("只有就业网用户可以导入文件");
 					detail.hideMenu();
 		 			var currentPage = $(this).parents(".page-list").find("#currentPage").text();
 					var stuName = $("#searchMenu .menu-btn[value='查询']").data("stuName");
 					var userName = $("#searchMenu .menu-btn[value='查询']").data("userName");
 					var startDate = $("#searchMenu .menu-btn[value='查询']").data("start");
 					var endDate = $("#searchMenu .menu-btn[value='查询']").data("end");
 					var satisfy= $("#searchMenu .menu-btn[value='查询']").data("satisfy");
 					var result= $("#searchMenu .menu-btn[value='查询']").data("result");
 					
 					detail.queryVisitList({
 						"classinfo" : classinfoId,
 						"stuName" : stuName,
 						"userName" : userName,
 						"startDate" : startDate,
 						"endDate" : endDate,
 						"satisfy" : satisfy,
 						"result" : result,
 						"page" : currentPage
 					});
 					detail.queryVisitListCount({
 						"classinfo" : classinfoId,
 						"stuName" : stuName,
 						"userName" : userName,
 						"startDate" : startDate,
 						"endDate" : endDate,
 						"satisfy" : satisfy,
 						"result" : result
 					});
 				}
 				else if(count == -2){
 					toastr.warning("导入失败");
 					detail.hideMenu();
 		 			var currentPage = $(this).parents(".page-list").find("#currentPage").text();
 					var stuName = $("#searchMenu .menu-btn[value='查询']").data("stuName");
 					var userName = $("#searchMenu .menu-btn[value='查询']").data("userName");
 					var startDate = $("#searchMenu .menu-btn[value='查询']").data("start");
 					var endDate = $("#searchMenu .menu-btn[value='查询']").data("end");
 					var satisfy= $("#searchMenu .menu-btn[value='查询']").data("satisfy");
 					var result= $("#searchMenu .menu-btn[value='查询']").data("result");
 					
 					detail.queryVisitList({
 						"classinfo" : classinfoId,
 						"stuName" : stuName,
 						"userName" : userName,
 						"startDate" : startDate,
 						"endDate" : endDate,
 						"satisfy" : satisfy,
 						"result" : result,
 						"page" : currentPage
 					});
 					detail.queryVisitListCount({
 						"classinfo" : classinfoId,
 						"stuName" : stuName,
 						"userName" : userName,
 						"startDate" : startDate,
 						"endDate" : endDate,
 						"satisfy" : satisfy,
 						"result" : result
 					});
 				}
 				else if(count == 0){
 					toastr.warning("你导入的文件内容不是该班级的学员或者所有的学员都已经被回访");
 					detail.hideMenu();
 		 			var currentPage = $(this).parents(".page-list").find("#currentPage").text();
 					var stuName = $("#searchMenu .menu-btn[value='查询']").data("stuName");
 					var userName = $("#searchMenu .menu-btn[value='查询']").data("userName");
 					var startDate = $("#searchMenu .menu-btn[value='查询']").data("start");
 					var endDate = $("#searchMenu .menu-btn[value='查询']").data("end");
 					var satisfy= $("#searchMenu .menu-btn[value='查询']").data("satisfy");
 					var result= $("#searchMenu .menu-btn[value='查询']").data("result");
 					
 					detail.queryVisitList({
 						"classinfo" : classinfoId,
 						"stuName" : stuName,
 						"userName" : userName,
 						"startDate" : startDate,
 						"endDate" : endDate,
 						"satisfy" : satisfy,
 						"result" : result,
 						"page" : currentPage
 					});
 					detail.queryVisitListCount({
 						"classinfo" : classinfoId,
 						"stuName" : stuName,
 						"userName" : userName,
 						"startDate" : startDate,
 						"endDate" : endDate,
 						"satisfy" : satisfy,
 						"result" : result
 					});
 				}
 				else{
 					toastr.success("导入成功");
 					detail.hideMenu();
 		 			var currentPage = $(this).parents(".page-list").find("#currentPage").text();
 					var stuName = $("#searchMenu .menu-btn[value='查询']").data("stuName");
 					var userName = $("#searchMenu .menu-btn[value='查询']").data("userName");
 					var startDate = $("#searchMenu .menu-btn[value='查询']").data("start");
 					var endDate = $("#searchMenu .menu-btn[value='查询']").data("end");
 					var satisfy= $("#searchMenu .menu-btn[value='查询']").data("satisfy");
 					var result= $("#searchMenu .menu-btn[value='查询']").data("result");
 					
 					detail.queryVisitList({
 						"classinfo" : classinfoId,
 						"stuName" : stuName,
 						"userName" : userName,
 						"startDate" : startDate,
 						"endDate" : endDate,
 						"satisfy" : satisfy,
 						"result" : result,
 						"page" : currentPage
 					});
 					detail.queryVisitListCount({
 						"classinfo" : classinfoId,
 						"stuName" : stuName,
 						"userName" : userName,
 						"startDate" : startDate,
 						"endDate" : endDate,
 						"satisfy" : satisfy,
 						"result" : result
 					});
 				}
 					
 				
 			},
 			error : function(json) {
 				toastr.error("系统异常");
 			}
 		});
	}
	
	function factory(key){
		if(key == "Detail"){
			return new Detail();
		}
	}
	
	var detail = factory("Detail");
	
	//回到顶部
	$(window.parent.document).find("body").animate({scrollTop:0},5);
	var paramMap = getIframeParams("page");
	var classinfoId = paramMap.classinfoId;
	var from = paramMap.from;
	
	detail.init();
	DropDown.initAll();
	detail.searchName({
		"classinfo" : classinfoId
	});
	//判断网页从哪里跳转过来
	//从tel_visit跳转
	if(from == 0){
		var detailPage = paramMap.detailPage;
		var stuName = localStorage.getItem("stuName");
		var userName = localStorage.getItem("userName");
		var start = localStorage.getItem("start");
		var end = localStorage.getItem("end");
		var satisfy = localStorage.getItem("satisfy");
		var result = localStorage.getItem("result");
		//存在data-中
		$("#searchMenu .menu-btn[value='查询']").data("stuName",stuName);
		$("#searchMenu .menu-btn[value='查询']").data("userName",userName);
		$("#searchMenu .menu-btn[value='查询']").data("start",start);
		$("#searchMenu .menu-btn[value='查询']").data("end",end);
		$("#searchMenu .menu-btn[value='查询']").data("satisfy",satisfy);
		$("#searchMenu .menu-btn[value='查询']").data("result",result);
		
		detail.queryVisitList({
			"classinfo" : classinfoId,
			"stuName" : stuName,
			"userName" : userName,
			"startDate" : start,
			"endDate" : end,
			"satisfy" : satisfy,
			"result" : result,
			"page" : detailPage
		});
		detail.queryVisitListCount({
			"classinfo" : classinfoId,
			"stuName" : stuName,
			"userName" : userName,
			"startDate" : start,
			"endDate" : end,
			"satisfy" : satisfy,
			"result" : result
		});
	}
	else{
		var page = paramMap.page;
		var classInfo = paramMap.classInfo;
		var year = paramMap.year;
		var organization = paramMap.organization;
		var profession = paramMap.profession;
		
		//将传入的参数存入LocalStorage中
		localStorage.setItem("classInfo",classInfo);
		localStorage.setItem("year",year);
		localStorage.setItem("organization",organization);
		localStorage.setItem("profession",profession);
		localStorage.setItem("page",page);
		
		detail.queryVisitList({
			"classinfo" : classinfoId
		});
		detail.queryVisitListCount({
			"classinfo" : classinfoId
		});
	}

	nextPage();
	choosePage();
	
	$(".tab").find("*[name='search']").click(function(){
		$(".keyword").val("");
		$(".value").text("-选择-");
		DropDown.clearAll();
		var $scope = $("#searchMenu");
		detail.showMenu($scope,event);
	});
	
	$(".tab").find("*[name='import']").click(function(){
		var $scope = $("#importMenu");
		detail.showMenu($scope,event);
	});
	
	$(".tab").find("*[name='export']").click(function(){
		var $scope = $("#exportMenu");
		detail.showMenu($scope,event);
	});
	
	$(".tab .operation-list").find(".return").click(function(){
		var classInfo = localStorage.getItem("classInfo");
		var year = localStorage.getItem("year");
		var organization = localStorage.getItem("organization");
		var profession = localStorage.getItem("profession");
		var page = localStorage.getItem("page");
		//删除localStorage
		localStorage.removeItem("classInfo");
		localStorage.removeItem("year");
		localStorage.removeItem("organization");
		localStorage.removeItem("profession");
		localStorage.removeItem("page");
		localStorage.removeItem("stuName");
		localStorage.removeItem("userName");
		localStorage.removeItem("start");
		localStorage.removeItem("end");
		localStorage.removeItem("satisfy");
		localStorage.removeItem("result");
		$(window.parent.document).find("iframe").attr("src","classinfo/classinfo.html?index=4&operation=0&page="+page+"&classInfo="+classInfo+"&year="+year+"&organization="+organization+"&profession="+profession);
	});
	
	function click(){
		$(".fixed-table .click").unbind("click");
		$(".fixed-table .click").click(function(){
			var stuId = $(this).parents("tr").find("*[name='id']").val();
			var detailPage = $("#currentPage").text();
			var stuName = $("#searchMenu .menu-btn[value='查询']").data("stuname");
			var userName = $("#searchMenu .menu-btn[value='查询']").data("username");
			var startDate = $("#searchMenu .menu-btn[value='查询']").data("start");
			var endDate = $("#searchMenu .menu-btn[value='查询']").data("end");
			var satisfy= $("#searchMenu .menu-btn[value='查询']").data("satisfy");
			var result= $("#searchMenu .menu-btn[value='查询']").data("result");
			
			//存入localStorage中，等到返回时获，退出时删除
			//将传入的参数存入LocalStorage中
			localStorage.setItem("stuName",stuName);
			localStorage.setItem("userName",userName);
			localStorage.setItem("start",startDate);
			localStorage.setItem("end",endDate);
			localStorage.setItem("satisfy",satisfy);
			localStorage.setItem("result",result);
			
			var time = $(this).parents("tr").find("*[name='time']").text();
			
			if(time == ""){
				toastr.warning("该学生未被成功回访，没有详细信息");
			}
			else{
				$(window.parent.document).find("iframe").attr("src","classinfo/tel_visit.html?from=0&classinfoId="+classinfoId+"&stuId="+stuId+"&detailPage="+detailPage);
			}
			
		});
	}
	
	function nextPage(){
		var pageTab = $(".tab .tab-content .page-list");
		var pageNum = $(pageTab).find(".page-num");
		var currentPage = pageTab.find("#currentPage").text();
		var totalPage = pageTab.find("#totalPages").text();
		currentPage = new Number(currentPage);
		totalPage = new Number(totalPage);
		var page = new Number(currentPage);
		
		if(totalPage > 5){
				if(currentPage>=3&&currentPage<totalPage-1)
					page = 3;
				else if(currentPage==totalPage-1)
					page=4;
				else if(currentPage == 1)
					page = 1;
				else if(currentPage == 2)
					page = 2;
				else
					page = 5;

		}
		pageNum.removeClass("page-active");
		pageNum.removeClass("page-disable");
		var i=0;
		i = new Number(i);
		if(totalPage<=5){
			for(;i<5;i++){
				$(pageNum[i]).text(i+1);
				if(i+1>totalPage)
					$(pageNum[i]).addClass("page-disable");
			}
		}else{
			for(;i<5;i++)
				$(pageNum[i]).text(currentPage+i+1-page);
		}
		var pagetest = $(pageNum[page-1])
		pagetest.addClass("page-active");
	}
	
	function choosePage(){
		$(".page-list .page-num").click(function(){
			var unclick = $(this).parents(".page-list").find(".page-disable").eq(0).text();
			var page = $(this).text();
			if(page < unclick || unclick == ""){
				$(".tab .page-list").find("#currentPage").text(page);
				$(".page-num").removeClass("page-active");
				$(this).addClass("page-active");
				
				var stuName = $("#searchMenu .menu-btn[value='查询']").data("stuName");
				var userName = $("#searchMenu .menu-btn[value='查询']").data("userName");
				var startDate = $("#searchMenu .menu-btn[value='查询']").data("start");
				var endDate = $("#searchMenu .menu-btn[value='查询']").data("end");
				var satisfy= $("#searchMenu .menu-btn[value='查询']").data("satisfy");
				var result= $("#searchMenu .menu-btn[value='查询']").data("result");
				
				detail.queryVisitList({
					"classinfo" : classinfoId,
					"page" : page,
					"stuName" : stuName,
					"userName" : userName,
					"startDate" : start,
					"endDate" : end,
					"satisfy" : satisfy,
					"result" : result
				});
			}
			
		});
	}
	
	function checkAction(value){
		var stuName = $("#searchMenu .menu-btn[value='查询']").data("stuname");
		var userName = $("#searchMenu .menu-btn[value='查询']").data("username");
		var startDate = $("#searchMenu .menu-btn[value='查询']").data("start");
		var endDate = $("#searchMenu .menu-btn[value='查询']").data("end");
		var satisfy= $("#searchMenu .menu-btn[value='查询']").data("satisfy");
		var result= $("#searchMenu .menu-btn[value='查询']").data("result");
		
		if(value==0){
			document.exportForm.action="/stuenroll/visit/exportExcel?classinfo="+classinfoId+"&stuName="+stuName+"&userName="+userName+"&startDate="+startDate+"&endDate="+endDate+"&satisfy="+satisfy+"&result="+result;
		}else if(value==1){
			document.exportForm.action="/stuenroll/visit/exportXml?classinfo="+classinfoId+"&stuName="+stuName+"&userName="+userName+"&startDate="+startDate+"&endDate="+endDate+"&satisfy="+satisfy+"&result="+result;
		}else{
			document.exportForm.action="/stuenroll/visit/exportCvs?classinfo="+classinfoId+"&stuName="+stuName+"&userName="+userName+"&startDate="+startDate+"&endDate="+endDate+"&satisfy="+satisfy+"&result="+result;
		}
		document.exportForm.submit();
	}
	
	var ele = $(".tab .page-list .page-item");
	ele.find("*[name='prevBtn']").unbind("click");
	ele.find("*[name='prevBtn']").click(function(){
		var temp = $(this).parents(".page-list").find("#currentPage");
		var currentPage = temp.text();
		
		currentPage = new Number(currentPage);
		if(currentPage > 1){
			var temp = $(this).parents(".page-list").find("#currentPage");
			temp.text(currentPage - 1);
			var stuName = $("#searchMenu .menu-btn[value='查询']").data("stuName");
			var userName = $("#searchMenu .menu-btn[value='查询']").data("userName");
			var startDate = $("#searchMenu .menu-btn[value='查询']").data("start");
			var endDate = $("#searchMenu .menu-btn[value='查询']").data("end");
			var satisfy= $("#searchMenu .menu-btn[value='查询']").data("satisfy");
			var result= $("#searchMenu .menu-btn[value='查询']").data("result");
			
			detail.queryVisitList({
				"classinfo" : classinfoId,
				"stuName" : stuName,
				"userName" : userName,
				"startDate" : startDate,
				"endDate" : endDate,
				"satisfy" : satisfy,
				"result" : result,
				"page" : currentPage - 1
			});
			detail.queryVisitListCount({
				"classinfo" : classinfoId,
				"stuName" : stuName,
				"userName" : userName,
				"startDate" : startDate,
				"endDate" : endDate,
				"satisfy" : satisfy,
				"result" : result
			});
			nextPage();
		}
	});
	ele.find("*[name='nextBtn']").unbind("click");
	ele.find("*[name='nextBtn']").click(function(){
		var temp = $(this).parents(".page-list").find("#currentPage");
		var currentPage = temp.text();

		var totalPages = $(this).parents(".page-list").find("#totalPages").text();
		
		currentPage = new Number(currentPage);
		totalPages = new Number(totalPages);
		if (currentPage < totalPages) {
			
			// 请求Ajax并更新数据
			var temp = $(this).parents(".page-list").find("#currentPage");
			temp.text(currentPage + 1); // 当前页数加上1页
			var stuName = $("#searchMenu .menu-btn[value='查询']").data("stuName");
			var userName = $("#searchMenu .menu-btn[value='查询']").data("userName");
			var startDate = $("#searchMenu .menu-btn[value='查询']").data("start");
			var endDate = $("#searchMenu .menu-btn[value='查询']").data("end");
			var satisfy= $("#searchMenu .menu-btn[value='查询']").data("satisfy");
			var result= $("#searchMenu .menu-btn[value='查询']").data("result");
			
			detail.queryVisitList({
				"classinfo" : classinfoId,
				"stuName" : stuName,
				"userName" : userName,
				"startDate" : startDate,
				"endDate" : endDate,
				"satisfy" : satisfy,
				"result" : result,
				"page" : currentPage + 1
			});
			detail.queryVisitListCount({
				"classinfo" : classinfoId,
				"stuName" : stuName,
				"userName" : userName,
				"startDate" : startDate,
				"endDate" : endDate,
				"satisfy" : satisfy,
				"result" : result
			});
			nextPage();
		}
	});
	
	//
	$("#searchMenu .keyword").keyup(function(){
		if (this.checkValidity()) {
			$(this).removeClass("error");
		}
		else {
			$(this).addClass("error");
		}
	});
	
	$("#searchMenu .menu-btn[value='清除']").click(function(){
		$(".keyword").val("");
		$(".value").text("-选择-");
		DropDown.clearAll();
		
	});
	
	$("#searchMenu .menu-btn[value='查询']").click(function(){
		//每次点击查询按钮时，先将上一次的记录清除
		$(this).data("stuName","");
		$(this).data("start","");
		$(this).data("end","");
		$(this).data("userName","");
		$(this).data("satisfy","");
		$(this).data("result","");
		
		
		var stuName = $("#searchMenu *[name='name'] ").val();
		var userName = $("#searchMenu *[name='executor'] ").val();
		var startDate = $("#searchMenu *[name='start-date'] ").val();
		var endDate = $("#searchMenu *[name='end-date'] ").val();
		var satisfy= $("#searchMenu").find("*[name='satisfaction']").find(".dropdown-item-active").data("result");
		var result= $("#searchMenu").find("*[name='result']").find(".dropdown-item-active").data("id");
		
		if(startDate != "" && endDate != ""){
			if(startDate > endDate){
				toastr.warning("输入起始日期大于结束日期");
				$("#searchMenu *[name='start-date'] ").val("");
				$("#searchMenu *[name='end-date'] ").val("");
				return;
			}
		}
		
		var error = checkError($("#searchMenu"));
		if(stuName != "" || userName != ""){
			if(!error){
				toastr.warning("请正确输入");
				return;
			}
		}
		
		
		$(this).data("stuName",stuName);
		$(this).data("start",startDate);
		$(this).data("end",endDate);
		$(this).data("userName",userName);
		$(this).data("satisfy",satisfy);
		$(this).data("result",result);
		
		var temp = $(".tab .tab-content ").find("#currentPage");
	    temp.text(1);
		
		detail.queryVisitList({
			"classinfo" : classinfoId,
			"stuName" : stuName,
			"userName" : userName,
			"startDate" : startDate,
			"endDate" : endDate,
			"satisfy" : satisfy,
			"result" : result
		});
		detail.queryVisitListCount({
			"classinfo" : classinfoId,
			"stuName" : stuName,
			"userName" : userName,
			"startDate" : startDate,
			"endDate" : endDate,
			"satisfy" : satisfy,
			"result" : result
		});
		detail.hideMenu();
		nextPage();
		
	});
	
	$(".tab .tab-content *[name='delete']").click(function(){
		detail.deleteById();
		
		detail.queryVisitListCount({
			"classinfo" : classinfoId
		});
		
		var totalPages = $("#totalPages").text();
		var currentPage = $("#currentPage").text();
		
		totalPages = new Number(totalPages);
		currentPage = new Number(currentPage);
		//alert(currentPage)
		if(currentPage > totalPages && totalPages != 0){
			currentPage = totalPages;
		}
		
		$(this).parents(".tab").find("#currentPage").text(currentPage);
		detail.queryVisitList({
			"classinfo" : classinfoId,
			"page" : currentPage
		});
	});
	
	$("#import").click(function(){
		var currentPage = $(".page-list").find("#currentPage").text();
		var file = $(".file").val()
 		if( file !=""){
 			upload(file);   //函数参数为上传的文件的本机地址
 			$(".file").val("");
 			
 		}else{
 			alert("请选择要导入的文件");
 		}
	});
	
	$("#excelBtn").click(function(){
		var count = $(this).data("index");
		checkAction(count);
		detail.hideMenu();
	});
	
	$("#xmlBtn").click(function(){
		var count = $(this).data("index");
		checkAction(count);
		detail.hideMenu();
	});
	
	$("#csvBtn").click(function(){
		var count = $(this).data("index");
		checkAction(count);
		detail.hideMenu();
	});
	
	
	
});
