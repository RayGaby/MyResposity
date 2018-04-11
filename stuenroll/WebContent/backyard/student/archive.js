$(function(){
	if (!checkPermission(["4_4"])){
		return;
	}
	
	var tabContainer=$(".tab-container");
	if(checkPermission(["4_4"])){
		tabContainer.find(".operation-item[name='searchArchive']").show();
	}
	if(checkPermission(["0"])){
		tabContainer.find(".operation-item[name='insertArchive']").show();
	}
	if(checkPermission(["4_2"])){
		tabContainer.find(".operation-item[name='deleteArchive']").show();
	}
	if(checkPermission(["4_3"])){
		tabContainer.find(".operation-item[name='updateArchive']").show();
	}
	if(!checkPermission(["5_4"])){
		$(".main-container").find(".tab-item[data-index='中退学员']").hide();
	}
	if(checkPermission(["5_1"])){
		tabContainer.find(".operation-item[name='insertQuit']").show();
	}
	if(checkPermission(["5_2"])){
		tabContainer.find(".operation-item[name='deleteQuit']").show();
	}
	if(checkPermission(["5_3"])){
		tabContainer.find(".operation-item[name='updateQuit']").show();
	}
	if(checkPermission(["5_4"])){
		tabContainer.find(".operation-item[name='searchQuit']").show();
	}
	
	/*
	 * 归档管理抽象接口
	 */
	
	var I_Archive=function(){
		
	}
	
	I_Archive.prototype.searchArchive = function(json){
		throw"抽象方法";
	}
	I_Archive.prototype.searchArchiveCount = function(json){
		throw"抽象方法";
	}
	I_Archive.prototype.insertArchive = function(json){
		throw"抽象方法";
	}
	I_Archive.prototype.updateArchive = function(json){
		throw"抽象方法";
	}
	I_Archive.prototype.searchArchiveById = function(json){
		throw"抽象方法";
	}
	I_Archive.prototype.deleteById = function(){
		throw"抽象方法";
	}
	I_Archive.prototype.searchOrganization = function(){
		throw"抽象方法";
	}
	I_Archive.prototype.searchProfession = function(){
		throw"抽象方法";
	}
	I_Archive.prototype.searchQuit = function(json){
		throw"抽象方法";
	}
	I_Archive.prototype.searchQuitCount = function(json){
		throw"抽象方法";
	}
	I_Archive.prototype.insertQuit = function(json){
		throw"抽象方法";
	}
	I_Archive.prototype.searchQuitById = function(json){
		throw"抽象方法";
	}
	I_Archive.prototype.updateQuit = function(json){
		throw"抽象方法";
	}
	I_Archive.prototype.deleteQuitById = function(){
		throw"抽象方法";
	}
	I_Archive.prototype.refreshArchive = function(){	//刷新页数
		throw"抽象方法";
	}
	I_Archive.prototype.refreshQuit = function(){
		throw"抽象方法";
	}
	
	var Archive = function(){
		
	}
	
	Archive.prototype = new I_Archive();
	Archive.prototype.searchArchive = function(json){
		$.ajax({
			"url" : "/stuenroll/archive/searchArchive",
			"type" : "post",
			"datatype" : "json",
			"data" : json,
			"success" : function(json){
				var data = json.result;
				var table = $(".tab-container .tab-content[data-index='全部学员'] .data-table");
				// 清空表格数据
				table.find("tr:gt(0)").remove();
				// 获得当前页数
				var currentPage = $(".tab-container .tab-content[data-index='全部学员'] #currentPage").text();
				// 转化成数字类型
				currentPage = new Number(currentPage);
				// 当前页数的行号起始数字
				var start = (currentPage - 1) * 35;
				var temp = "";
				for (var i = 0; i < data.length; i++) {
					var one = data[i];
					temp += "<tr>";
					temp += "<td><input type='checkbox' name='id' value='" + one.id + "' /></td>";
					temp += "<td>" + (start + i + 1) + "</td>";
					temp += "<td>" + one.name + "</td>";
					temp += "<td>" + one.pid + "</td>";
					temp += "<td>" + one.tel + "</td>";
					temp += "<td>" + one.organization + "</td>";
					temp += "<td>" + one.profession + "</td>";
					temp += "<td>" + one.classinfo + "</td>";
					temp += "<td>" + one.year + "</td>";
					temp += "<td>" + one.state + "</td>";
					temp += "</tr>";
				}
				table.append(temp);
			},
			"error" : function(){
				toastr.error("系统异常");
			}
		});
	}
	Archive.prototype.searchArchiveCount = function(json){
		$.ajax({
			"url" : "/stuenroll/archive/searchArchiveCount",
			"type" : "post",
			"datatype" : "json",
			"data" : json,
			"async" : false,
			"success" : function(json){
				var count = json.result; //总记录数
				var content = $(".tab-container .tab-content[data-index='全部学员']");
				content.find("#totalRows").text(count);
				var totalPages = (count % 35 == 0) ? count / 35 : Math.floor(count / 35) + 1;
				content.find("#totalPages").text(totalPages);
			},
			"error" : function(){
				toastr.error("系统异常");
			}
		});
	}
	Archive.prototype.insertArchive = function(json){
		$.ajax({
			"url" : "/stuenroll/archive/insertArchive",
			"type" : "post",
			"datatype" : "json",
			"data" : json,
			"async" : false,
			"success" : function(json){
				toastr.success("添加"+json.result);
			},
			"error" : function(){
				toastr.error("系统异常");
			}
		});
	}
	Archive.prototype.updateArchive = function(json){
		$.ajax({
			"url" : "/stuenroll/archive/updateArchive",
			"type" : "post",
			"datatype" : "json",
			"data" : json,
			"async" : false,
			"success" : function(json){
				toastr.success("修改"+json.result);
			},
			"error" : function(){
				toastr.error("系统异常");
			}
		});
	}
	
	Archive.prototype.searchArchiveById = function(json){
		$.ajax({
			"url" : "/stuenroll/archive/searchArchiveById",
			"type" : "post",
			"datatype" : "json",
			"data" : json,
			"async" : false,
			"success" : function(json){
				var data = json.result;
				var one = data[0];
				
				var archiveElement =  $(".tab-container .tab-content[data-index='全部学员']");
				archiveElement.find(".add-table *[name='name']").val(one.name);
				archiveElement.find(".add-table *[name='sex'] .value").text(one.sex);
				archiveElement.find(".add-table *[name='nation'] .value").text(one.nation);
				archiveElement.find(".add-table *[name='pid']").val(one.pid);
				archiveElement.find(".add-table *[name='graSchool']").val(one.graduate_school);
				archiveElement.find(".add-table *[name='graduateYear'] .value").text(one.graduate_year);
				var graduateDate=new Date(one.graduate_date);
				var graduateDateYear=graduateDate.getFullYear();
				var graduateDateMonth=graduateDate.getMonth();
				if(graduateDateMonth<10)
					graduateDateMonth="0"+graduateDateMonth;
				var graduateDateDay=graduateDate.getDate();
				if(graduateDateDay<10)
					graduateDateDay = "0"+graduateDateDay;
				var graduateDateTemp=graduateDateYear+"-"+graduateDateMonth+"-"+graduateDateDay;
				
				archiveElement.find(".add-table *[name='graDate']").val(graduateDateTemp);
				archiveElement.find(".add-table *[name='major'] .value").text(one.major);
				archiveElement.find(".add-table *[name='healthy'] .value").text(one.healthy);
				archiveElement.find(".add-table *[name='politics'] .value").text(one.politics);

				var birthday=new Date(one.birthday);
				var birthdayYear=birthday.getFullYear();
				var birthdayMonth=birthday.getMonth();
				if(birthdayMonth<10)
					birthdayMonth="0"+birthdayMonth;
				var birthdayDay=birthday.getDate();
				if(birthdayDay <10 )
					birthdayDay ="0"+birthdayDay;
				var birthdayTemp=birthdayYear+"-"+birthdayMonth+"-"+birthdayDay;
				archiveElement.find(".add-table *[name='birthday']").val(birthdayTemp);
				
				archiveElement.find(".add-table *[name='resident_address']").val(one.resident_address);
				archiveElement.find(".add-table *[name='permanent_address']").val(one.permanent_address);
				archiveElement.find(".add-table *[name='home_address']").val(one.home_address);
				archiveElement.find(".add-table *[name='tel']").val(one.tel);
				archiveElement.find(".add-table *[name='home_tel']").val(one.home_tel);
				archiveElement.find(".add-table *[name='email']").val(one.email);
				archiveElement.find(".add-table *[name='organization'] .value").text(one.organization);
				archiveElement.find(".add-table *[name='profession'] .value").text(one.profession);
				archiveElement.find(".add-table *[name='place'] .value").text(one.place);
				archiveElement.find(".add-table *[name='education'] .value").text(one.education);
			},
			"error" : function(){
				toastr.error("系统异常");
			}
		});
	}
	
	Archive.prototype.deleteById = function(){
		//弹出确认对话框
		var bool =confirm("是否删除选中的记录？");
		if(bool == false){
			return;
		}
		//获得被选中的记录
		var content = $(".tab-container .tab-content[data-index='全部学员']");
		var checkbox = content.find("*[name='id']:checked");	//被选中的复选框
		
		var id = [];
		for (var i = 0; i < checkbox.length; i++) {
			id.push($(checkbox[i]).val());
		}
		
		$.ajax({
			"url" : "/stuenroll/archive/deleteById",
			"type" : "post",
			"dataType" : "json",
			"traditional" : true, 	//发送数组JSON格式
			"async" : false,
			"data" : {
				"id" : id
			},
			"success" : function(json){
				toastr.success("删除了" +json.deleteRows +"条记录");
			},
			"error" : function(){
				toastr.error("系统异常");
			}
		});
	}
	
	Archive.prototype.searchOrganization = function(json){
		$.ajax({
			"url" : "/stuenroll/archive/searchOrganization",
			"type" : "post",
			"dataType" :"json",
			"data" : json,
			"success" : function(json){
				var data = json.result;
				var orgDropDownItem = $(".main-container .tab-content *[name='organization'] .dropdown-list");
				orgDropDownItem.empty();
				var temp = "";
				if(data.length==1){
					one = data[0];
					console.log(one.id);
					orgDropDownItem.append("<li class='dropdown-item' data-id='"+one.id+"'>"+one.abbreviation+"</li>");
				}
				else{
					for(var i = 1;i<data.length;i++){
						one = data[i];
						temp += "<li class='dropdown-item' data-id='"+one.id+"'>"+one.abbreviation+"</li>";
					}
				}
				orgDropDownItem.append(temp);
				DropDown.initAll();
			},
			"error" : function(){
				toastr.error("系统异常");
			}
		});
	}
	
	Archive.prototype.searchProfession = function(json){
		$.ajax({
			"url" : "/stuenroll/archive/searchProfession",
			"type" : "post",
			"dataType" : "json",
			"data" : json,
			"success" : function(json){
				var data = json.result;
				var proDropDownItem = $(".main-container .tab-content *[name='profession'] .dropdown-list");
				proDropDownItem.empty();
				var temp = "";
				if(data.length == 0){
					toastr.warning("该机构下无培训专业");
				}
				for(var i = 0;i<data.length;i++){
					one = data[i];
					temp += "<li class='dropdown-item' data-id='"+one.id+"'>"+one.name+"</li>";
				}
				proDropDownItem.append(temp);
				DropDown.initAll();
			},
			"error" : function(json){
				toastr.error("请先选择机构");
			}
		});
	}
	
	Archive.prototype.searchQuit = function(json){
		$.ajax({
			"url" : "/stuenroll/archive/searchQuit",
			"type" : "post",
			"dataType" : "json",
			"data" : json,
			"success" : function(json){
				var data = json.result;
				var table = $(".tab-container .tab-content[data-index='中退学员'] .data-table");
				//清空表格数据
				table.find("tr:gt(0)").remove();
				//获得当前页数
				var currentPage = $(".tab-container .tab-content[data-index='中退学员'] #currentPage").text(); 
				//转换成数字类型
				currentPage = new Number(currentPage);
				var start = (currentPage - 1) * 35;
				var temp = "";
				for(var i = 0; i < data.length; i++){
					var one = data[i];
					temp += "<tr>";
					temp += "<td><input type='checkbox' name='id' value='" + one.id + "' /></td>";
					temp += "<td>" + (start + i + 1) + "</td>";
					temp += "<td>" + one.name + "</td>";
					temp += "<td>" + one.pid + "</td>";
					temp += "<td>" + one.tel + "</td>";
					temp += "<td>" + one.organization + "</td>";
					temp += "<td>" + one.profession + "</td>";
					temp += "<td>" + one.classinfo + "</td>";
					temp += "<td>" + one.year + "</td>";
					temp += "<td>" + one.state + "</td>";
					temp += "</tr>";
				}
				table.append(temp);
			},
			"error" : function(){
				toastr.error("系统异常");
			}
		});
	}
	Archive.prototype.searchQuitCount = function(json){
		$.ajax({
			"url" : "/stuenroll/archive/searchQuitCount",
			"type" : "post",
			"dataType" : "json",
			"data" : json,
			"async" : false,
			"success" : function(json){
				var count = json.result;	//总记录数
				var content = $(".tab-container .tab-content[data-index='中退学员']");
				content.find("#totalRows").text(count);
				var totalPages = (count % 35 ==0) ? count / 35 : Math.floor(count / 35) + 1;
				content.find("#totalPages").text(totalPages);
			},
			"error" : function(){
				toastr.error("系统异常");
			}
		});
	}
	
	Archive.prototype.insertQuit = function(json){
		$.ajax({
			"url" : "/stuenroll/archive/insertQuit",
			"type" : "post",
			"datatype" : "json",
			"data" : json,
			"async" : false,
			"success" : function(json){
				toastr.success("添加"+json.result);
			},
			"error" : function(){
				toastr.error("系统异常");
			}
		});
	}
	
	Archive.prototype.searchQuitById = function(json){
		$.ajax({
			"url" : "/stuenroll/archive/searchQuitById",
			"type" : "post",
			"datatype" : "json",
			"data" : json,
			"async" : false,
			"success" : function(json){
				var data = json.result;
				var one = data[0];
				
				var quitElement =  $(".tab-container .tab-content[data-index='中退学员']");
				quitElement.find(".add-table *[name='name']").val(one.name);
				quitElement.find(".add-table *[name='sex'] .value").text(one.sex);
				quitElement.find(".add-table *[name='nation'] .value").text(one.nation);
				quitElement.find(".add-table *[name='pid']").val(one.pid);
				quitElement.find(".add-table *[name='graSchool']").val(one.graduate_school);
				quitElement.find(".add-table *[name='graduateYear'] .value").text(one.graduate_year);
//				var graduateDate=new Date(one.graduate_date);
//				var graduateDateYear=graduateDate.getFullYear();
//				var graduateDateMonth=graduateDate.getMonth();
//				if(graduateDateMonth<10)
//					graduateDateMonth="0"+graduateDateMonth;
//				var graduateDateDay=graduateDate.getDate();
//				if(graduateDateDay<10)
//					graduateDateDay = "0"+graduateDate;
//				var graduateDateTemp=graduateDateYear+"-"+graduateDateMonth+"-"+graduateDateDay;
//				quitElement.find(".add-table *[name='graDate']").val(graduateDateTemp);
				quitElement.find(".add-table *[name='graDate']").val(one.graduate_date);
				quitElement.find(".add-table *[name='major'] .value").text(one.major);
				quitElement.find(".add-table *[name='healthy'] .value").text(one.healthy);
				quitElement.find(".add-table *[name='politics'] .value").text(one.politics);

				var birthday=new Date(one.birthday);
				var birthdayYear=birthday.getFullYear();
				var birthdayMonth=birthday.getMonth();
				if(birthdayMonth<10)
					birthdayMonth="0"+birthdayMonth;
				var birthdayDay=birthday.getDate();
				if(birthdayDay <10 )
					birthdayDay ="0"+birthdayDay;
				var birthdayTemp=birthdayYear+"-"+birthdayMonth+"-"+birthdayDay;
				quitElement.find(".add-table *[name='birthday']").val(birthdayTemp);
				
				quitElement.find(".add-table *[name='resident_address']").val(one.resident_address);
				quitElement.find(".add-table *[name='permanent_address']").val(one.permanent_address);
				quitElement.find(".add-table *[name='home_address']").val(one.home_address);
				quitElement.find(".add-table *[name='tel']").val(one.tel);
				quitElement.find(".add-table *[name='home_tel']").val(one.home_tel);
				quitElement.find(".add-table *[name='email']").val(one.email);
				quitElement.find(".add-table *[name='organization'] .value").text(one.organization);
				quitElement.find(".add-table *[name='profession'] .value").text(one.profession);
				quitElement.find(".add-table *[name='place'] .value").text(one.place);
				quitElement.find(".add-table *[name='education'] .value").text(one.education);
			},
			"error" : function(){
				toastr.error("系统异常");
			}
		});
	}
	
	Archive.prototype.updateQuit = function(json){
		$.ajax({
			"url" : "/stuenroll/archive/updateQuit",
			"type" : "post",
			"datatype" : "json",
			"data" : json,
			"async" : false,
			"success" : function(json){
				toastr.success("修改"+json.result);
			},
			"error" : function(){
				toastr.error("系统异常");
			}
		});
	}
	
	Archive.prototype.deleteQuitById = function(){
		var bool = confirm("是否删除选中的记录？");
		if(bool == false){
			return;
		}
		var content = $(".tab-container .tab-content[data-index='中退学员']");
		var checkbox = content.find("*[name='id']:checked");
		
		var id = [];
		for(var i = 0; i < checkbox.length; i++){
			id.push($(checkbox[i]).val());
		}
		
		$.ajax({
			"url" : "/stuenroll/archive/deleteQuitById",
			"type" : "post",
			"dataType" : "json",
			"traditional" : true,	//发送数组JSON格式
			"async" : false,
			"data" : {
				"id" :id
			},
			"success" : function(json) {
				toastr.success("删除了" + json.deleteRows + "条记录");
			},
			"error" : function() {
				toastr.error("系统异常");
			}
		});
	}
	
	Archive.prototype.refreshArchive = function(){
		var table = $(".tab-container .tab-content[data-index='全部学员'] .page-list");
		var pagetest = $(table).find(".page-test");
		var currentNumber = $(table).find("#currentPage").text();
		var pageNumber = $(table).find("#currentPage").text();
		var maxNumber = $(table).find("#totalPages").text();
		currentNumber = new Number(currentNumber);
		pageNumber = new Number(pageNumber);
		if(maxNumber > 5){
			if(currentNumber>=3&&currentNumber<maxNumber-1)
				pageNumber = 3;
			else if(currentNumber==maxNumber-1)
				pageNumber=4;
			else if(currentNumber==maxNumber)
				pageNumber = 5;
			for(var i=0;i<5;i++)
				$(pagetest[i]).text(currentNumber+i+1-pageNumber);
			pagetest.removeClass("page-active");
			$(pagetest[pageNumber-1]).addClass("page-active");
		}
		else if(maxNumber <=5 ){
			for(var i=0;i<5;i++){
				$(pagetest[i]).text(i+1);
				if(i>maxNumber-1)
					$(pagetest[i]).addClass("page-disable");
			}
			pagetest.removeClass("page-active");
			$(pagetest[currentNumber-1]).addClass("page-active");
		}

	}
	
	Archive.prototype.refreshQuit = function(){
		var table = $(".tab-container .tab-content[data-index='中退学员'] .page-list");
		var pagetest = $(table).find(".page-test");
		var currentNumber = $(table).find("#currentPage").text();
		var pageNumber = $(table).find("#currentPage").text();
		var maxNumber = $(table).find("#totalPages").text();
		currentNumber = new Number(currentNumber);
		pageNumber = new Number(pageNumber);
		if(maxNumber > 5){
			if(currentNumber>=3&&currentNumber<maxNumber-1)
				pageNumber = 3;
			else if(currentNumber==maxNumber-1)
				pageNumber=4;
			else if(currentNumber==maxNumber)
				pageNumber = 5;
			for(var i=0;i<5;i++)
				$(pagetest[i]).text(currentNumber+i+1-pageNumber);
			pagetest.removeClass("page-active");
			$(pagetest[pageNumber-1]).addClass("page-active");
		}
		else if(maxNumber <=5 ){
			for(var i=0;i<5;i++){
				$(pagetest[i]).text(i+1);
				if(i>maxNumber-1)
					$(pagetest[i]).addClass("page-disable");
			}
			pagetest.removeClass("page-active");
			$(pagetest[currentNumber-1]).addClass("page-active");
		}
	}
	
	function factory(key){
		if(key=="Tab"){
			return new Tab();
		}
		else if(key=="Archive"){
			return new Archive();
		}
	}
	
	var tab = factory("Tab");
	var archive = factory("Archive");
	
	$(".tab-list .tab-item").click(function(){
		var temp = $(this).data("index");
		tab.showTab(temp);
		//切换选项卡重新显示数据;
		if(temp == "全部学员"){
			$(".tab-container .tab-content[data-index='全部学员'] #currentPage").text(1);
			archive.searchArchive();
			archive.searchArchiveCount();
			archive.refreshArchive();
		}
		if(temp == "中退学员"){
			$(".tab-container .tab-content[data-index='中退学员'] #currentPage").text(1);
			archive.searchQuit();
			archive.searchQuitCount();
			archive.refreshQuit();
		}
	});
	
	archive.searchArchive();
	archive.searchArchiveCount();
	DropDown.initAll();
	archive.refreshArchive();
	
	var archiveElement = $(".tab-container .tab-content[data-index='全部学员']");
	
	archiveElement.find("*[name='prevBtn']").click(function(){
		var temp = $(this).parents(".page-list").find("#currentPage");
		var currentPage = temp.text();
		currentPage = new Number(currentPage);
		if(currentPage > 1){
			// 根据隐藏的查询面板的设置条件查询数据、
			//请求Ajax并更新数据
			//得到name,pid,year
			var name = archiveElement.find(".check-table *[name='name']").val();
			var pid = archiveElement.find(".check-table *[name='pid']").val();
			var year = archiveElement.find(".check-table *[name='year']").val();
			
			//得到sex,education,organizationId
			if(archiveElement.find(".check-table *[name='sex'] .dropdown-item-active").text() == "男" || archiveElement.find(".check-table *[name='sex'] .dropdown-item-active").text() == "女"){
				var sex = archiveElement.find(".check-table *[name='sex'] .dropdown-item-active").text();
			}else{
				sex = null;
			}
			if(archiveElement.find(".check-table *[name='education'] .value").text()!= "- 选择 -"){
				var education = archiveElement.find(".check-table *[name='education'] .value").text();
			}else{
				education = null;
			}
			if(archiveElement.find(".check-table *[name='organization'] .value").text()!= "- 选择 -"){
				var organizationId = $(this).parents(".tab-content").find(".dropdown[name='organization'] .dropdown-item-active").data("id"); 
			}else{
				organizationId = null;
			}
			
			//得到profession,classInfo,enrollState
			if(archiveElement.find(".check-table *[name='profession'] .value").text()!="- 选择 -"){
				var profession =  $(this).parents(".tab-content").find(".dropdown[name='profession'] .dropdown-item-active").data("id");
			}else{
				profession = null;
			}
			var classInfo = archiveElement.find("*[name='classInfo']").val();
			if(archiveElement.find(".check-table *[name='enrollState'] .value")!="- 选择 -"){
				var enrollState = $(this).parents(".tab-content").find(".dropdown[name='enrollState'] .dropdown-item-active").data("id");	
			}else{
				enrollState = null;
			}
			archive.searchArchive({
				"name" : name,
				"sex" : sex,
				"pid" : pid,
				"year" : year,
				"education" : education,
				"stateId" : enrollState,
				"organizationId" : organizationId,
				"professionId" : profession,
				"classinfoId" : classInfo,
				"page" : currentPage - 1
			});
			temp.text(currentPage - 1 );//当前页数减去1页
		}
		archive.refreshArchive();
	});
	
	archiveElement.find("*[name='nextBtn']").click(function(){
		var temp = $(this).parents(".page-list").find("#currentPage");
		var currentPage = temp.text();
		currentPage = new Number(currentPage);
		var totalPages = $(this).parents(".page-list").find("#totalPages").text();
		totalPages = new Number(totalPages);
		if(currentPage < totalPages){
			// 根据隐藏的查询面板的设置条件查询数据、
			//请求Ajax并更新数据
			//得到name,pid,year
			var name = archiveElement.find(".check-table *[name='name']").val();
			var pid = archiveElement.find(".check-table *[name='pid']").val();
			var year = archiveElement.find(".check-table *[name='year']").val();
			
			//得到sex,education,organizationId
			if(archiveElement.find(".check-table *[name='sex'] .dropdown-item-active").text() == "男" || archiveElement.find(".check-table *[name='sex'] .dropdown-item-active").text() == "女"){
				var sex = archiveElement.find(".check-table *[name='sex'] .dropdown-item-active").text();
			}else{
				sex = null;
			}
			if(archiveElement.find(".check-table *[name='education'] .value").text()!= "- 选择 -"){
				var education = archiveElement.find(".check-table *[name='education'] .value").text();
			}else{
				education = null;
			}
			if(archiveElement.find(".check-table *[name='organization'] .value").text()!= "- 选择 -"){
				var organizationId = $(this).parents(".tab-content").find(".dropdown[name='organization'] .dropdown-item-active").data("id"); 
			}else{
				organizationId = null;
			}
			
			//得到profession,classInfo,enrollState
			if(archiveElement.find(".check-table *[name='profession'] .value").text()!="- 选择 -"){
				var profession =  $(this).parents(".tab-content").find(".dropdown[name='profession'] .dropdown-item-active").data("id");
			}else{
				profession = null;
			}
			var classInfo = archiveElement.find("*[name='classInfo']").val();
			if(archiveElement.find(".check-table *[name='enrollState'] .value")!="- 选择 -"){
				var enrollState = $(this).parents(".tab-content").find(".dropdown[name='enrollState'] .dropdown-item-active").data("id");	
			}else{
				enrollState = null;
			}
			archive.searchArchive({
				"name" : name,
				"sex" : sex,
				"pid" : pid,
				"year" : year,
				"education" : education,
				"stateId" : enrollState,
				"organizationId" : organizationId,
				"professionId" : profession,
				"classinfoId" : classInfo,
				"page" : currentPage + 1
			});
			temp.text(currentPage + 1 );//当前页数加上1页
		}
		archive.refreshArchive();
	});
	
	archiveElement.find("*[name='searchArchive']").click(function(){
		//进入查询面板时先清空
		$(this).parents(".tab-content").find(".input").val("");
		$(this).parents(".tab-content").find(".value").text("- 选择 -");
		archive.searchOrganization();
		$(this).parents(".tab-content").find(".dropdown-item").removeClass("dropdown-item-active");
		$(this).parents(".tab-content").find(".check-table").css('display','block');
		$(this).parents(".tab-content").find(".add-table").css('display','none');
	});
	archiveElement.find("*[name='profession']").click(function(){
		var organization = $(this).parents(".tab-content").find(".dropdown[name='organization'] .dropdown-item-active").data("id");
		organization = new String(organization);
		archive.searchProfession({
			"organizationId" : organization
		})
	});
	archiveElement.find("*[name='searchArchiveBtn']").click(function(){
		//得到name,pid,year
		var name = archiveElement.find("*[name='name']").val();
		var pid = archiveElement.find("*[name='pid']").val();
		var year = archiveElement.find("*[name='year']").val();
		
		//得到sex,education,organizationId
		if(archiveElement.find("*[name='sex'] .dropdown-item-active").text() == "男" || archiveElement.find("*[name='sex'] .dropdown-item-active").text() == "女"){
			var sex = archiveElement.find("*[name='sex'] .dropdown-item-active").text();
		}else{
			sex = null;
		}
		if(archiveElement.find("*[name='education'] .value").text()!= "- 选择 -"){
			var education = archiveElement.find(".check-table *[name='education'] .dropdown-item-active").text();
		}else{
			education = null;
		}
		if(archiveElement.find("*[name='organization'] .value").text()!= "- 选择 -"){
			var organizationId = $(this).parents(".tab-content").find(".dropdown[name='organization'] .dropdown-item-active").data("id"); 
		}else{
			organizationId = null;
		}
		
		//得到profession,classInfo,enrollState
		if(archiveElement.find("*[name='profession'] .value").text()!="- 选择 -"){
			var profession =  $(this).parents(".tab-content").find(".dropdown[name='profession'] .dropdown-item-active").data("id");
		}else{
			profession = null;
		}
		var classInfo = archiveElement.find("*[name='classInfo']").val();
		if(archiveElement.find("*[name='enrollState'] .value")!="- 选择 -"){
			var enrollState = $(this).parents(".tab-content").find(".dropdown[name='enrollState'] .dropdown-item-active").data("id");	
		}else{
			enrollState = null;
		}
		$(this).parents(".tab-container").find("#currentPage").text(1);
		archive.searchArchive({
			"name" : name,
			"sex" : sex,
			"pid" : pid,
			"year" : year,
			"education" : education,
			"stateId" : enrollState,
			"organizationId" : organizationId,
			"professionId" : profession,
			"classinfoId" : classInfo,
			"page":1
		});
		archive.searchArchiveCount({
			"name" : name,
			"sex" : sex,
			"pid" : pid,
			"year" : year,
			"education" : education,
			"stateId" : enrollState,
			"organizationId" : organizationId,
			"professionId" : profession,
			"classinfoId" : classInfo,
			"page":1
		});
		archive.refreshArchive();
		//发送完ajax显示之后 将查询面板清空
		$(this).parents(".tab-content").find(".check-table").css('display','none');
	});
	archiveElement.find("*[name='clearSearchArchiveBtn']").click(function(){
		$(this).parents(".tab-content").find(".input").val("");
		$(this).parents(".tab-content").find(".value").text("- 选择 -");
	});
	archiveElement.find("*[name='cancelSearchArchive']").click(function(){
		$(this).parents(".tab-content").find(".check-table").css('display','none');
	});
	archiveElement.find("*[name='insertArchive']").click(function(){
		$(this).parents(".tab-content").find(".input").val("");
		$(this).parents(".tab-content").find(".value").text("- 选择 -");
		archive.searchOrganization();
		$(this).parents(".tab-content").find("*[name='addArchive']").css('display','Inline');
		$(this).parents(".tab-content").find("*[name='updArchive']").css('display','none');
		$(this).parents(".tab-content").find("*[name='insertArchiveBtn']").css('display','Inline');
		$(this).parents(".tab-content").find("*[name='updateArchiveBtn']").css('display','none');
		$(this).parents(".tab-content").find(".check-table").css('display','none');
		$(this).parents(".tab-content").find(".add-table").css('display','block');
	});
	archiveElement.find("*[name='updateArchive']").click(function(){
		var checkbox = archiveElement.find("*[name='id']:checked"); // 被选中的复选框
		if(checkbox.length != 1){
			toastr.warning("请选择一条记录进行修改");
			return
		}
		var id = $(checkbox[0]).val();
		archive.searchArchiveById({
			"id" : id
		});
		
		$(this).parents(".tab-content").find("*[name='updArchive']").css('display','Inline');
		$(this).parents(".tab-content").find("*[name='addArchive']").css('display','none');
		$(this).parents(".tab-content").find("*[name='updateArchiveBtn']").css('display','Inline');
		$(this).parents(".tab-content").find("*[name='insertArchiveBtn']").css('display','none');
		$(this).parents(".tab-content").find(".check-table").css('display','none');
		$(this).parents(".tab-content").find(".add-table").css('display','block');
	});
	archiveElement.find("*[name='insertArchiveBtn']").click(function(){
		var name = archiveElement.find(".add-table *[name='name']").val();
		if(name == ""){
			toastr.warning("姓名必填");
			return;
		}
		if(archiveElement.find(".add-table *[name='sex'] .dropdown-item-active").text() == "男" || archiveElement.find(".add-table *[name='sex'] .dropdown-item-active").text() == "女"){
			var sex = archiveElement.find(".add-table *[name='sex'] .dropdown-item-active").text();
		}else{
			toastr.warning("性别选项必填");
			return;
		}
		if(archiveElement.find(".add-table *[name='nation'] .value").text()!="- 选择 -"){
			var nation = archiveElement.find(".add-table *[name='nation'] .dropdown-item-active").text();
		}else{
			toastr.warning("民族选项必填");
			return;
		}
		var pid = archiveElement.find(".add-table *[name='pid']").val();
		if(pid == ""){
			toastr.warning("身份证号必填");
			return;
		}
		var graSchool = archiveElement.find(".add-table *[name='graSchool']").val();
		if(graSchool == ""){
			toastr.warning("毕业学校必填");
			return;
		}
		if(archiveElement.find(".add-table *[name='graduateYear'] .value").text()!= "- 选择 -"){
			var graYear = archiveElement.find(".add-table *[name='graduateYear'] .dropdown-item-active").text();
		}else{
			toastr.warning("毕业年份选项必填");
			return;
		}
		var graDate=archiveElement.find(".add-table *[name='graDate']").val();
		if(graDate == ""){
			toastr.warning("毕业日期必填");
			return;
		}
		if(archiveElement.find(".add-table *[name='major'] .value").text()!= "- 选择 -"){
			var major = archiveElement.find(".add-table *[name='major'] .dropdown-item-active").text();
		}else{
			toastr.warning("所学专业选项必填");
		}
		if(archiveElement.find(".add-table *[name='healthy'] .value").text()!="- 选择 -"){
			var healthy = archiveElement.find(".add-table *[name='healthy'] .dropdown-item-active").text();
		}else{
			toastr.warning("健康状况选项必填");
		}
		if(archiveElement.find(".add-table *[name='politics'] .value").text()!="- 选择 -"){
			var politics = archiveElement.find(".add-table *[name='politics'] .dropdown-item-active").text();
		}else{
			toastr.warning("政治面貌选项必填");
		}
		
		var birthday = archiveElement.find(".add-table *[name='birthday']").val();
		if(birthday == ""){
			toastr.warning("出生日期必填");
			return;
		}
		var resAddress = archiveElement.find(".add-table *[name='resident_address']").val();
		if(resAddress == ""){
			toastr.warning("现居住地必填");
			return;
		}
		var perAddress = archiveElement.find(".add-table *[name='permanent_address']").val();
		if(perAddress == ""){
			toastr.warning("家庭地址必填");
			return;
		}
		var homAddress = archiveElement.find(".add-table *[name='home_address']").val();
		if(homAddress == ""){
			toastr.warning("户籍地址必填");
			return;
		}
		var tel = archiveElement.find(".add-table *[name='tel']").val();
		if(tel == ""){
			toastr.warning("移动电话必填");
			return;
		}
		var homTel = archiveElement.find(".add-table *[name='home_tel']").val();
		if(homTel == ""){
			toastr.warning("家庭电话必填");
			return;
		}
		var email = archiveElement.find(".add-table *[name='email']").val();
		if(email == ""){
			toastr.warning("电子信箱必填");
			return;
		}
		
		if(archiveElement.find(".add-table *[name='organization'] .value").text()!= "- 选择 -"){
			var organizationId =  $(this).parents(".tab-content").find(".dropdown[name='organization'] .dropdown-item-active").data("id");
		}else{
			toastr.warning("培训机构选项必填");
			return;
		}
		if(archiveElement.find(".add-table *[name='profession'] .value").text()!= "- 选择 -"){
			var professionId =  $(this).parents(".tab-content").find(".dropdown[name='profession'] .dropdown-item-active").data("id");
		}else{
			toastr.warning("培训专业选项必填");
			return;
		}
		if(archiveElement.find(".add-table *[name='place'] .value").text()!= "- 选择 -"){
			var place = archiveElement.find(".add-table *[name='place'] .value").text();
		}else{
			toastr.warning("培训地点选项必填");
			return;
		}
		if(archiveElement.find(".add-table *[name='education'] .value").text()!="- 选择 -"){
			var education = archiveElement.find(".add-table *[name='education'] .value").text();
		}else{
			toastr.warning("学历选项必填");
			return;
		}
		archive.insertArchive({
			"name":name,
			"sex":sex,
			"nation":nation,
			"pid":pid,
			"graduateSchool":graSchool,
			"graduateYear":graYear,
			"graduateDate":graDate,
			"education":education,
			"major":major,
			"healthy":healthy,
			"politics":politics,
			"birthday":birthday,
			"residentAddress":resAddress,
			"homeAddress":homAddress,
			"permanentAddress":perAddress,
			"tel":tel,
			"homeTel":homTel,
			"email":email,
			"organizationId":organizationId,
			"professionId":professionId,
			"place":place
		});
		$(this).parents(".tab-content").find(".add-table").css('display','none');
		archive.searchArchiveCount();
		archive.searchArchive();
		archive.refreshArchive();

	});
	archiveElement.find("*[name='updateArchiveBtn']").click(function(){
		var name = archiveElement.find(".add-table *[name='name']").val();
		if(name == ""){
			toastr.warning("姓名必填");
			return;
		}
		if(archiveElement.find(".add-table *[name='sex'] .value").text() == "男" || archiveElement.find(".add-table *[name='sex'] .value").text() == "女"){
			var sex = archiveElement.find(".add-table *[name='sex'] .value").text();
		}else{
			toastr.warning("性别选项必填");
			return;
		}
		if(archiveElement.find(".add-table *[name='nation'] .value").text()!="- 选择 -"){
			var nation = archiveElement.find(".add-table *[name='nation'] .value").text();
		}else{
			toastr.warning("民族选项必填");
			return;
		}
		var pid = archiveElement.find(".add-table *[name='pid']").val();
		if(pid == ""){
			toastr.warning("身份证号必填");
			return;
		}
		var graSchool = archiveElement.find(".add-table *[name='graSchool']").val();
		if(graSchool == ""){
			toastr.warning("毕业学校必填");
			return;
		}
		if(archiveElement.find(".add-table *[name='graduateYear'] .value").text()!= "- 选择 -"){
			var graYear = archiveElement.find(".add-table *[name='graduateYear'] .value").text();
		}else{
			toastr.warning("毕业年份选项必填");
			return;
		}
		var graDate=archiveElement.find(".add-table *[name='graDate']").val();
		if(graDate == ""){
			toastr.warning("毕业日期必填");
			return;
		}
		if(archiveElement.find(".add-table *[name='major'] .value").text()!= "- 选择 -"){
			var major = archiveElement.find(".add-table *[name='major'] .value").text();
		}else{
			toastr.warning("所学专业选项必填");
		}
		if(archiveElement.find(".add-table *[name='healthy'] .value").text()!="- 选择 -"){
			var healthy = archiveElement.find(".add-table *[name='healthy'] .value").text();
		}else{
			toastr.warning("健康状况选项必填");
		}
		if(archiveElement.find(".add-table *[name='politics'] .value").text()!="- 选择 -"){
			var politics = archiveElement.find(".add-table *[name='politics'] .value").text();
		}else{
			toastr.warning("政治面貌选项必填");
		}
		
		var birthday = archiveElement.find(".add-table *[name='birthday']").val();
		if(birthday == ""){
			toastr.warning("出生日期必填");
			return;
		}
		var resAddress = archiveElement.find(".add-table *[name='resident_address']").val();
		if(resAddress == ""){
			toastr.warning("现居住地必填");
			return;
		}
		var perAddress = archiveElement.find(".add-table *[name='permanent_address']").val();
		if(perAddress == ""){
			toastr.warning("家庭地址必填");
			return;
		}
		var homAddress = archiveElement.find(".add-table *[name='home_address']").val();
		if(homAddress == ""){
			toastr.warning("户籍地址必填");
			return;
		}
		var tel = archiveElement.find(".add-table *[name='tel']").val();
		if(tel == ""){
			toastr.warning("移动电话必填");
			return;
		}
		var homTel = archiveElement.find(".add-table *[name='home_tel']").val();
		if(homTel == ""){
			toastr.warning("家庭电话必填");
			return;
		}
		var email = archiveElement.find(".add-table *[name='email']").val();
		if(email == ""){
			toastr.warning("电子信箱必填");
			return;
		}
		
		if(archiveElement.find(".add-table *[name='organization'] .value").text()!= "- 选择 -"){
			var organization = archiveElement.find(".add-table *[name='organization'] .value").text();
		}else{
			toastr.warning("培训机构选项必填");
		}
		if(archiveElement.find(".add-table *[name='profession'] .value").text()!= "- 选择 -"){
			var profession =  archiveElement.find(".add-table *[name='profession'] .value").text();
		}else{
			toastr.warning("培训专业选项必填")
		}
		if(archiveElement.find(".add-table *[name='place'] .value").text()!= "- 选择 -"){
			var place = archiveElement.find(".add-table *[name='place'] .value").text();
		}else{
			toastr.warning("培训地点选项必填")
		}
		if(archiveElement.find(".add-table *[name='education'] .value").text()!="- 选择 -"){
			var education = archiveElement.find(".add-table *[name='education'] .value").text();
		}else{
			toastr.warning("学历选项必填");
		}
		
		var checkbox = archiveElement.find("*[name='id']:checked");
		var id = $(checkbox[0]).val();
		
		archive.updateArchive({
			"name":name,
			"sex":sex,
			"nation":nation,
			"pid":pid,
			"graduateSchool":graSchool,
			"graduateYear":graYear,
			"graduateDate":graDate,
			"education":education,
			"major":major,
			"healthy":healthy,
			"politics":politics,
			"birthday":birthday,
			"residentAddress":resAddress,
			"homeAddress":homAddress,
			"permanentAddress":perAddress,
			"tel":tel,
			"homeTel":homTel,
			"email":email,
			"organization":organization,
			"profession":profession,
			"place":place,
			"id":id
		});
		$(this).parents(".tab-content").find(".add-table").css('display','none');
		archive.searchArchiveCount();
		var currentPage = $(this).parents(".tab-container").find("#currentPage").text();
		archive.searchArchive({
			"page" : currentPage
		});
		archive.refreshArchive();
	});
	archiveElement.find("*[name='clearArvhiceBtn']").click(function(){
		$(this).parents(".tab-content").find(".input").val("");
		$(this).parents(".tab-content").find(".value").text("- 选择 -");
	});
	archiveElement.find("*[name='cancelAddArchive']").click(function(){
		$(this).parents(".tab-content").find(".add-table").css('display','none');
	});
	
	archiveElement.find("*[name='deleteArchive']").click(function(){
		var checkbox = archiveElement.find("*[name='id']:checked"); // 被选中的复选框
		if(checkbox.length == 0){
			toastr.warning("请选择至少一条记录进行删除");
			return
		}
		
		//执行删除功能
		archive.deleteById();
		//重新查询
		archive.searchArchiveCount();
		var totalPages = $(this).parents(".tab-container").find("#totalPages").text();
		var currentPage = $(this).parents(".tab-container").find("#currentPage").text();
		totalPages = new Number(totalPages);
		currentPage = new Number(currentPage);
		if(currentPage > totalPages) {
			currentPage = totalPages;
		}
		
		$(this).parents(".tab-container").find("#currentPage").text(currentPage);	//更新当前页数
		archive.searchArchive({
			"page" :currentPage
		});
		archive.refreshArchive();
	});
	
	var quitElement = $(".tab-container .tab-content[data-index='中退学员']");
	
	quitElement.find("*[name='prevBtn']").click(function(){
		var temp = $(this).parents(".page-list").find("#currentPage");
		var currentPage = temp.text();
		currentPage = new Number(currentPage);
		if(currentPage > 1){
			//根据隐藏的查询面板的设置条件查询数据
			//得到name,pid,year
			var name = quitElement.find(".check-table *[name='name']").val();
			var pid = quitElement.find(".check-table *[name='pid']").val();
			var year = quitElement.find(".check-table *[name='year']").val();
			
			//得到sex,education,organizationId
			if(quitElement.find(".check-table *[name='sex'] .dropdown-item-active").text() == "男" || quitElement.find(".check-table *[name='sex'] .dropdown-item-active").text() == "女"){
				var sex = quitElement.find(".check-table *[name='sex'] .dropdown-item-active").text();
			}else{
				sex = null;
			}
			if(quitElement.find(".check-table *[name='education'] .value").text()!= "- 选择 -"){
				var education = quitElement.find(".check-table *[name='education'] .value").text();
			}else{
				education = null;
			}
			if(quitElement.find(".check-table *[name='organization'] .value").text()!= "- 选择 -"){
				var organizationId = $(this).parents(".tab-content").find(".dropdown[name='organization'] .dropdown-item-active").data("id"); 
			}else{
				organizationId = null;
			}
			
			//得到profession,classInfo,enrollState
			if(quitElement.find(".check-table *[name='profession'] .value").text()!="- 选择 -"){
				var profession =  $(this).parents(".tab-content").find(".dropdown[name='profession'] .dropdown-item-active").data("id");
			}else{
				profession = null;
			}
			var classInfo = quitElement.find("*[name='classInfo']").val();
			if(quitElement.find(".check-table *[name='enrollState'] .value")!="- 选择 -"){
				var enrollState = $(this).parents(".tab-content").find(".dropdown[name='enrollState'] .dropdown-item-active").data("id");	
			}else{
				enrollState = null;
			}
			//请求Ajax并更新数据
			archive.searchQuit({
				"name" : name,
				"sex" : sex,
				"pid" : pid,
				"year" : year,
				"education" : education,
				"stateId" : enrollState,
				"organizationId" : organizationId,
				"professionId" : profession,
				"classinfoId" : classInfo,
				"page" : currentPage - 1
			});
			temp.text(currentPage - 1 );//当前页数减去1页
		}
		archive.refreshQuit();
	});
	
	quitElement.find("*[name='nextBtn']").click(function(){
		var temp = $(this).parents(".page-list").find("#currentPage");
		var currentPage = temp.text();
		currentPage = new Number(currentPage);
		var totalPages = $(this).parents(".page-list").find("#totalPages").text();
		totalPages = new Number(totalPages);
		if(currentPage < totalPages){
			//根据隐藏的查询面板的设置条件查询数据
			//得到name,pid,year
			var name = quitElement.find(".check-table *[name='name']").val();
			var pid = quitElement.find(".check-table *[name='pid']").val();
			var year = quitElement.find(".check-table *[name='year']").val();
			
			//得到sex,education,organizationId
			if(quitElement.find(".check-table *[name='sex'] .dropdown-item-active").text() == "男" || quitElement.find(".check-table *[name='sex'] .dropdown-item-active").text() == "女"){
				var sex = quitElement.find(".check-table *[name='sex'] .dropdown-item-active").text();
			}else{
				sex = null;
			}
			if(quitElement.find(".check-table *[name='education'] .value").text()!= "- 选择 -"){
				var education = quitElement.find(".check-table *[name='education'] .value").text();
			}else{
				education = null;
			}
			if(quitElement.find(".check-table *[name='organization'] .value").text()!= "- 选择 -"){
				var organizationId = $(this).parents(".tab-content").find(".dropdown[name='organization'] .dropdown-item-active").data("id"); 
			}else{
				organizationId = null;
			}
			
			//得到profession,classInfo,enrollState
			if(quitElement.find(".check-table *[name='profession'] .value").text()!="- 选择 -"){
				var profession =  $(this).parents(".tab-content").find(".dropdown[name='profession'] .dropdown-item-active").data("id");
			}else{
				profession = null;
			}
			var classInfo = quitElement.find("*[name='classInfo']").val();
			if(quitElement.find(".check-table *[name='enrollState'] .value")!="- 选择 -"){
				var enrollState = $(this).parents(".tab-content").find(".dropdown[name='enrollState'] .dropdown-item-active").data("id");	
			}else{
				enrollState = null;
			}
			//请求Ajax并更新数据
			archive.searchQuit({
				"name" : name,
				"sex" : sex,
				"pid" : pid,
				"year" : year,
				"education" : education,
				"stateId" : enrollState,
				"organizationId" : organizationId,
				"professionId" : profession,
				"classinfoId" : classInfo,
				"page" : currentPage + 1
			});
			temp.text(currentPage + 1 );//当前页数加上1页
		}
		archive.refreshQuit();
	});
	
	quitElement.find("*[name='searchQuit']").click(function(){
		$(this).parents(".tab-content").find(".input").val("");
		$(this).parents(".tab-content").find(".value").text("- 选择 -");
		archive.searchOrganization();
		$(this).parents(".tab-content").find(".dropdown-item").removeClass("dropdown-item-active");
		$(this).parents(".tab-content").find(".check-table").css('display','block')
		$(this).parents(".tab-content").find(".add-table").css('display','none');
	});
	quitElement.find("*[name='profession']").click(function(){
		var organization = $(this).parents(".tab-content").find(".dropdown[name='organization'] .dropdown-item-active").data("id");
		organization = new String(organization);
		archive.searchProfession({
			"organizationId" : organization
		})
	});
	quitElement.find("*[name='searchQuitBtn']").click(function(){
		//得到name,pid,year
		var name = quitElement.find("*[name='name']").val();
		var pid = quitElement.find("*[name='pid']").val();
		var year = quitElement.find("*[name='year']").val();
		
		//得到sex,education,organizationId
		if(quitElement.find("*[name='sex'] .dropdown-item-active").text() == "男" || quitElement.find("*[name='sex'] .dropdown-item-active").text() == "女"){
			var sex = quitElement.find("*[name='sex'] .dropdown-item-active").text();
		}else{
			sex = null;
		}
		if(quitElement.find("*[name='education'] .value").text()!= "- 选择 -"){
			var education = quitElement.find(".check-table *[name='education'] .dropdown-item-active").text();
		}else{
			education = null;
		}
		if(quitElement.find("*[name='organization'] .value").text()!= "- 选择 -"){
			var organizationId = $(this).parents(".tab-content").find(".dropdown[name='organization'] .dropdown-item-active").data("id"); 
		}else{
			organizationId = null;
		}
		
		//得到profession,classInfo,enrollState
		if(quitElement.find("*[name='profession'] .value").text()!="- 选择 -"){
			var profession =  $(this).parents(".tab-content").find(".dropdown[name='profession'] .dropdown-item-active").data("id");
		}else{
			profession = null;
		}
		var classInfo = quitElement.find("*[name='classInfo']").val();
		if(quitElement.find("*[name='enrollState'] .value")!="- 选择 -"){
			var enrollState = $(this).parents(".tab-content").find(".dropdown[name='enrollState'] .dropdown-item-active").data("id");	
		}else{
			enrollState = null;
		}
		$(this).parents(".tab-container").find("#currentPage").text(1);
		archive.searchQuit({
			"name" : name,
			"sex" : sex,
			"pid" : pid,
			"year" : year,
			"education" : education,
			"organizationId" : organizationId,
			"professionId" : profession,
			"classinfoId" : classInfo,
			"page":1
		});
		archive.searchQuitCount({
			"name" : name,
			"sex" : sex,
			"pid" : pid,
			"year" : year,
			"education" : education,
			"organizationId" : organizationId,
			"professionId" : profession,
			"classinfoId" : classInfo,
			"page":1
		});
		archive.refreshQuit();
		//发送完ajax显示之后 将查询面板清空
		$(this).parents(".tab-content").find(".check-table").css('display','none');
	});
	quitElement.find("*[name='clearSearchQuitBtn']").click(function(){
		$(this).parents(".tab-content").find(".input").val("");
		$(this).parents(".tab-content").find(".value").text("- 选择 -");
	});
	quitElement.find("*[name='cancelSearchQuit']").click(function(){
		$(this).parents(".tab-content").find(".check-table").css('display','none');
	});
	quitElement.find("*[name='insertQuit']").click(function(){
		$(this).parents(".tab-content").find(".input").val("");
		$(this).parents(".tab-content").find(".value").text("- 选择 -");
		archive.searchOrganization();
		$(this).parents(".tab-content").find("*[name='addArchive']").css('display','Inline');
		$(this).parents(".tab-content").find("*[name='updArchive']").css('display','none');
		$(this).parents(".tab-content").find("*[name='insertArchiveBtn']").css('display','Inline');
		$(this).parents(".tab-content").find("*[name='updateArchiveBtn']").css('display','none');
		$(this).parents(".tab-content").find(".check-table").css('display','none');
		$(this).parents(".tab-content").find(".add-table").css('display','block');
	});
	quitElement.find("*[name='updateQuit']").click(function(){
		var checkbox = quitElement.find("*[name='id']:checked"); // 被选中的复选框
		if(checkbox.length != 1){
			toastr.warning("请选择一条记录进行修改");
			return
		}
		var id = $(checkbox[0]).val();
		archive.searchQuitById({
			"id" : id
		});

		$(this).parents(".tab-content").find("*[name='updQuit']").css('display','Inline');
		$(this).parents(".tab-content").find("*[name='addQuit']").css('display','none');
		$(this).parents(".tab-content").find("*[name='updateQuitBtn']").css('display','Inline');
		$(this).parents(".tab-content").find("*[name='insertQuitBtn']").css('display','none');
		$(this).parents(".tab-content").find(".check-table").css('display','none');
		$(this).parents(".tab-content").find(".add-table").css('display','block');
	});
	quitElement.find("*[name='insertQuitBtn']").click(function(){
		var name = quitElement.find(".add-table *[name='name']").val();
		if(name == ""){
			toastr.warning("姓名必填");
			return;
		}
		if(quitElement.find(".add-table *[name='sex'] .dropdown-item-active").text() == "男" || quitElement.find(".add-table *[name='sex'] .dropdown-item-active").text() == "女"){
			var sex = quitElement.find(".add-table *[name='sex'] .dropdown-item-active").text();
		}else{
			toastr.warning("性别选项必填");
			return;
		}
		if(quitElement.find(".add-table *[name='nation'] .value").text()!="- 选择 -"){
			var nation = quitElement.find(".add-table *[name='nation'] .dropdown-item-active").text();
		}else{
			toastr.warning("民族选项必填");
			return;
		}
		var pid = quitElement.find(".add-table *[name='pid']").val();
		if(pid == ""){
			toastr.warning("身份证号必填");
			return;
		}
		var graSchool = quitElement.find(".add-table *[name='graSchool']").val();
		if(graSchool == ""){
			toastr.warning("毕业学校必填");
			return;
		}
		if(quitElement.find(".add-table *[name='graduateYear'] .value").text()!= "- 选择 -"){
			var graYear = quitElement.find(".add-table *[name='graduateYear'] .dropdown-item-active").text();
		}else{
			toastr.warning("毕业年份选项必填");
			return;
		}
		var graDate=quitElement.find(".add-table *[name='graDate']").val();
		if(graDate == ""){
			toastr.warning("毕业日期必填");
			return;
		}
		if(quitElement.find(".add-table *[name='major'] .value").text()!= "- 选择 -"){
			var major = quitElement.find(".add-table *[name='major'] .dropdown-item-active").text();
		}else{
			toastr.warning("所学专业选项必填");
		}
		if(quitElement.find(".add-table *[name='healthy'] .value").text()!="- 选择 -"){
			var healthy = quitElement.find(".add-table *[name='healthy'] .dropdown-item-active").text();
		}else{
			toastr.warning("健康状况选项必填");
		}
		if(quitElement.find(".add-table *[name='politics'] .value").text()!="- 选择 -"){
			var politics = quitElement.find(".add-table *[name='politics'] .dropdown-item-active").text();
		}else{
			toastr.warning("政治面貌选项必填");
		}
		
		var birthday = quitElement.find(".add-table *[name='birthday']").val();
		if(birthday == ""){
			toastr.warning("出生日期必填");
			return;
		}
		var resAddress = quitElement.find(".add-table *[name='resident_address']").val();
		if(resAddress == ""){
			toastr.warning("现居住地必填");
			return;
		}
		var perAddress = quitElement.find(".add-table *[name='permanent_address']").val();
		if(perAddress == ""){
			toastr.warning("家庭地址必填");
			return;
		}
		var homAddress = quitElement.find(".add-table *[name='home_address']").val();
		if(homAddress == ""){
			toastr.warning("户籍地址必填");
			return;
		}
		var tel = quitElement.find(".add-table *[name='tel']").val();
		if(tel == ""){
			toastr.warning("移动电话必填");
			return;
		}
		var homTel = quitElement.find(".add-table *[name='home_tel']").val();
		if(homTel == ""){
			toastr.warning("家庭电话必填");
			return;
		}
		var email = quitElement.find(".add-table *[name='email']").val();
		if(email == ""){
			toastr.warning("电子信箱必填");
			return;
		}
		
		if(quitElement.find(".add-table *[name='organization'] .value").text()!= "- 选择 -"){
			var organizationId =  $(this).parents(".tab-content").find(".dropdown[name='organization'] .dropdown-item-active").data("id");
		}else{
			toastr.warning("培训机构选项必填");
			return;
		}
		if(quitElement.find(".add-table *[name='profession'] .value").text()!= "- 选择 -"){
			var professionId =  $(this).parents(".tab-content").find(".dropdown[name='profession'] .dropdown-item-active").data("id");
		}else{
			toastr.warning("培训专业选项必填");
			return;
		}
		if(quitElement.find(".add-table *[name='place'] .value").text()!= "- 选择 -"){
			var place = quitElement.find(".add-table *[name='place'] .value").text();
		}else{
			toastr.warning("培训地点选项必填");
			return;
		}
		if(quitElement.find(".add-table *[name='education'] .value").text()!="- 选择 -"){
			var education = quitElement.find(".add-table *[name='education'] .value").text();
		}else{
			toastr.warning("学历选项必填");
			return;
		}
		archive.insertQuit({
			"name":name,
			"sex":sex,
			"nation":nation,
			"pid":pid,
			"graduateSchool":graSchool,
			"graduateYear":graYear,
			"graduateDate":graDate,
			"education":education,
			"major":major,
			"healthy":healthy,
			"politics":politics,
			"birthday":birthday,
			"residentAddress":resAddress,
			"homeAddress":homAddress,
			"permanentAddress":perAddress,
			"tel":tel,
			"homeTel":homTel,
			"email":email,
			"organizationId":organizationId,
			"professionId":professionId,
			"place":place
		});
		$(this).parents(".tab-content").find(".add-table").css('display','none');
		archive.searchQuitCount();
		archive.searchQuit();
		archive.refreshQuit();
	});
	quitElement.find("*[name='insertQuit']").click(function(){
		$(this).parents(".tab-content").find("*[name='addQuit']").css('display','Inline');
		$(this).parents(".tab-content").find("*[name='updQuit']").css('display','none');
		$(this).parents(".tab-content").find("*[name='insertQuitBtn']").css('display','Inline');
		$(this).parents(".tab-content").find("*[name='updateQuitBtn']").css('display','none');
		$(this).parents(".tab-content").find(".check-table").css('display','none');
		$(this).parents(".tab-content").find(".add-table").css('display','block');
	});
	
	quitElement.find("*[name='updateQuitBtn']").click(function(){
		var name = quitElement.find(".add-table *[name='name']").val();
		if(name == ""){
			toastr.warning("姓名必填");
			return;
		}
		if(quitElement.find(".add-table *[name='sex'] .value").text() == "男" || quitElement.find(".add-table *[name='sex'] .value").text() == "女"){
			var sex = quitElement.find(".add-table *[name='sex'] .value").text();
		}else{
			toastr.warning("性别选项必填");
			return;
		}
		if(quitElement.find(".add-table *[name='nation'] .value").text()!="- 选择 -"){
			var nation = quitElement.find(".add-table *[name='nation'] .value").text();
		}else{
			toastr.warning("民族选项必填");
			return;
		}
		var pid = quitElement.find(".add-table *[name='pid']").val();
		if(pid == ""){
			toastr.warning("身份证号必填");
			return;
		}
		var graSchool = quitElement.find(".add-table *[name='graSchool']").val();
		if(graSchool == ""){
			toastr.warning("毕业学校必填");
			return;
		}
		if(quitElement.find(".add-table *[name='graduateYear'] .value").text()!= "- 选择 -"){
			var graYear = quitElement.find(".add-table *[name='graduateYear'] .value").text();
		}else{
			toastr.warning("毕业年份选项必填");
			return;
		}
		var graDate=quitElement.find(".add-table *[name='graDate']").val();
		if(graDate == ""){
			toastr.warning("毕业日期必填");
			return;
		}
		if(quitElement.find(".add-table *[name='major'] .value").text()!= "- 选择 -"){
			var major = quitElement.find(".add-table *[name='major'] .value").text();
		}else{
			toastr.warning("所学专业选项必填");
		}
		if(quitElement.find(".add-table *[name='healthy'] .value").text()!="- 选择 -"){
			var healthy = quitElement.find(".add-table *[name='healthy'] .value").text();
		}else{
			toastr.warning("健康状况选项必填");
		}
		if(quitElement.find(".add-table *[name='politics'] .value").text()!="- 选择 -"){
			var politics = quitElement.find(".add-table *[name='politics'] .value").text();
		}else{
			toastr.warning("政治面貌选项必填");
		}
		
		var birthday = quitElement.find(".add-table *[name='birthday']").val();
		if(birthday == ""){
			toastr.warning("出生日期必填");
			return;
		}
		var resAddress = quitElement.find(".add-table *[name='resident_address']").val();
		if(resAddress == ""){
			toastr.warning("现居住地必填");
			return;
		}
		var perAddress = quitElement.find(".add-table *[name='permanent_address']").val();
		if(perAddress == ""){
			toastr.warning("家庭地址必填");
			return;
		}
		var homAddress = quitElement.find(".add-table *[name='home_address']").val();
		if(homAddress == ""){
			toastr.warning("户籍地址必填");
			return;
		}
		var tel = quitElement.find(".add-table *[name='tel']").val();
		if(tel == ""){
			toastr.warning("移动电话必填");
			return;
		}
		var homTel = quitElement.find(".add-table *[name='home_tel']").val();
		if(homTel == ""){
			toastr.warning("家庭电话必填");
			return;
		}
		var email = quitElement.find(".add-table *[name='email']").val();
		if(email == ""){
			toastr.warning("电子信箱必填");
			return;
		}
		
		if(quitElement.find(".add-table *[name='organization'] .value").text()!= "- 选择 -"){
			var organization = quitElement.find(".add-table *[name='organization'] .value").text();
		}else{
			toastr.warning("培训机构选项必填");
		}
		if(quitElement.find(".add-table *[name='profession'] .value").text()!= "- 选择 -"){
			var profession =  quitElement.find(".add-table *[name='profession'] .value").text();
		}else{
			toastr.warning("培训专业选项必填")
		}
		if(quitElement.find(".add-table *[name='place'] .value").text()!= "- 选择 -"){
			var place = quitElement.find(".add-table *[name='place'] .value").text();
		}else{
			toastr.warning("培训地点选项必填")
		}
		if(quitElement.find(".add-table *[name='education'] .value").text()!="- 选择 -"){
			var education = quitElement.find(".add-table *[name='education'] .value").text();
		}else{
			toastr.warning("学历选项必填");
		}
		
		var checkbox = quitElement.find("*[name='id']:checked");
		var id = $(checkbox[0]).val();
		
		archive.updateQuit({
			"name":name,
			"sex":sex,
			"nation":nation,
			"pid":pid,
			"graduateSchool":graSchool,
			"graduateYear":graYear,
			"graduateDate":graDate,
			"education":education,
			"major":major,
			"healthy":healthy,
			"politics":politics,
			"birthday":birthday,
			"residentAddress":resAddress,
			"homeAddress":homAddress,
			"permanentAddress":perAddress,
			"tel":tel,
			"homeTel":homTel,
			"email":email,
			"organization":organization,
			"profession":profession,
			"place":place,
			"id":id
		});
		$(this).parents(".tab-content").find(".add-table").css('display','none');
		archive.searchQuitCount();
		var currentPage = $(this).parents(".tab-container").find("#currentPage").text();
		archive.searchQuit({
			"page" : currentPage
		});
		archive.refreshQuit();
	});
	quitElement.find("*[name='clearQuit']").click(function(){
		$(this).parents(".tab-content").find(".input").val("");
		$(this).parents(".tab-content").find(".value").text("- 选择 -");
	});
	quitElement.find("*[name='cancelAddQuit']").click(function(){
		$(this).parents(".tab-content").find(".add-table").css('display','none');
		$(this).parents(".tab-content").find(".check-table").css('display','none');
	});
	
	quitElement.find("*[name='deleteQuit']").click(function(){
		var checkbox = quitElement.find("*[name='id']:checked"); // 被选中的复选框
		if(checkbox.length == 0){
			toastr.warning("请选择至少一条记录进行删除");
			return
		}
		
		//执行删除功能
		archive.deleteQuitById();
		//重新查询
		archive.searchQuitCount();
		var totalPages = $(this).parents(".tab-container").find("#totalPages").text();
		var currentPage = $(this).parents(".tab-container").find("#currentPage").text();
		totalPages = new Number(totalPages);
		currentPage = new Number(currentPage);
		if(currentPage > totalPages) {
			currentPage = totalPages;
		}
		
		$(this).parents(".tab-container").find("#currentPage").text(currentPage);	//更新当前页数
		archive.searchQuit({
			"page" :currentPage
		});
		archive.refreshQuit();
	});
});
