$(function(){
	if (!checkPermission(["7_4"])){
		return;
	}
	
	var tabContainer=$(".tab-container");
	if(checkPermission(["7_1"])){
		tabContainer.find(".operation-item[name='addOrganization']").show();
	}
	if(checkPermission(["7_2"])){
		tabContainer.find(".operation-item[name='deleteOrganization']").show();
		tabContainer.find(".operation-item[name='deleteJoin']").show();
	}
	if(checkPermission(["7_3"])){
		tabContainer.find(".operation-item[name='updateOrganization']").show();
		tabContainer.find(".operation-item[name='updateJoin']").show();
	}
	if(checkPermission(["7_4"])){
		tabContainer.find(".operation-item[name='searchJoin']").show();
	}
	if(checkPermission(["7_23"])){
		tabContainer.find(".operation-item[name='addJoin']").show();
	}
	
	
	var I_Organization = function(){
		
	}
	
	I_Organization.prototype.showOrganization = function(json){
		throw"抽象方法";
	}
	I_Organization.prototype.showOrganizationCount = function(json){
		throw"抽象方法";
	}
	I_Organization.prototype.deleteById = function(){
		throw"抽象方法";
	}
	I_Organization.prototype.addOrganization = function(json){
		throw"抽象方法";
	}
	I_Organization.prototype.updateOrganizationById = function(json){
		throw"抽象方法";
	}
	I_Organization.prototype.addJoin = function(json){
		throw"抽象方法";
	}
	I_Organization.prototype.searchJoin = function(json){
		throw"抽象方法";
	}
	I_Organization.prototype.searchJoinCount = function(json){
		throw"抽象方法";
	}
	I_Organization.prototype.updateJoinById = function(json){
		throw"抽象方法";
	}
	I_Organization.prototype.deleteJoinById = function(json){
		throw"抽象方法";
	}
	I_Organization.prototype.refreshPage = function(json){
		throw"抽象方法";
	}
	I_Organization.prototype.openJoinById = function(json){
		throw"抽象方法";
	}
	I_Organization.prototype.closeJoinById = function(json){
		throw"抽象方法";
	}
	
	var Organization = function(){
		
	}	
	Organization.prototype = new I_Organization();
	
	Organization.prototype.showOrganization = function(json){
		$.ajax({
			"url" : "/stuenroll/organization/showOrganization",
			"type" : "post",
			"datatype" : "json",
			"data" : json,
			"async" : false,
			"success" : function(json){
				var data = json.result;
				var table = $(".tab-container .tab-content[data-index='机构列表'] .data-table");
				table.find("tr:gt(0)").remove();
				var temp = "";
				for(var i = 0; i < data.length; i++){
					var one = data[i];
					temp += "<tr>";
					temp += "<td><input type = 'checkbox' name='id' value='" + one.id + "' /></td>";
					temp += "<td>" + (i + 1) + "</td>";
					temp += "<td name='name'>" + one.name + "</td>";
					temp += "<td name='abbreviation'>" + one.abbreviation + "</td>";
					temp += "<td name='address'>" + one.address + "</td>";
					temp += "<td name='tel'>" + one.tel + "</td>";
					temp += "<td name='liaison'>" + one.liaison + "</td>";
					temp += "<td name='pcount'>" + one.pcount + "</td>";
					temp += "<td name='ccount'>" + one.ccount + "</td>";
					temp += "<td name='scount'>" + one.scount + "</td>";
					temp += "</tr>";
				}
				table.append(temp);
			},
			"error" : function(){
				toastr.error("系统异常");
			}
		});
	}
	
	Organization.prototype.showOrganizationCount = function(json){
		$.ajax({
			"url" : "/stuenroll/organization/showOrganizationCount",
			"type" : "post",
			"dataType" : "json",
			"data" : json,
			"async":false,
			"success" : function(json) {
				var count = json.result;
				var content = $(".tab-container .tab-content[data-index='机构列表']");
				content.find("#totalRows").text(count);
			},
			"error" : function() {
				toastr.error("系统异常");
			}
		});
	}
	
	Organization.prototype.deleteById = function() {
		// 弹出确认对话框
		var bool = confirm("是否删除选中的记录？");
		if (bool == false) {
			return;
		}
		// 获得被选中的记录
		var content = $(".tab-container .tab-content[data-index='机构列表']");
		var checkbox = content.find("*[name='id']:checked"); // 被选中的复选框

		var id = [];
		for (var i = 0; i < checkbox.length; i++) {
			var tempid = $(checkbox[i]).parents('tr').find("*[name='id']").val();
			var name = $(checkbox[i]).parents('tr').find("*[name='name']").text();
			$.ajax({
				"url" : "/stuenroll/organization/searchInOJById",
				"type" : "post",
				"dataType" : "json",
				"traditional" : true,	//发送数组JSON格式
				"async":false,
				"data" : {
					"id" : tempid
				},
				"success" : function(json) {
					if(json.result == 0){
						id.push($(checkbox[i]).val());
					}
					else if (json.result == 1){
						alert(name+"已经参与转换，无法删除");
					}
				},
				"error" : function() {
					toastr.error("操作失败");
				}
			});
		}

		$.ajax({
			"url" : "/stuenroll/organization/deleteById",
			"type" : "post",
			"dataType" : "json",
			"traditional" : true,	//发送数组JSON格式
			"async":false,
			"data" : {
				"id" : id
			},
			"success" : function(json) {
				toastr.success("删除了" + json.deleteRows + "条记录");
			},
			"error" : function() {
				toastr.error("操作失败");
			}
		});
	}
	
	Organization.prototype.addOrganization = function(json){
		var checkTable = $(".tab-container .tab-content[data-index='机构列表'] .check-table");
		var name = checkTable.find("*[name='name']").val();
		var abbreviation = checkTable.find("*[name='abbreviation']").val();
		var address = checkTable.find("*[name='address']").val();
		var liaison = checkTable.find("*[name='liaison']").val();
		var tel = checkTable.find("*[name='tel']").val();
		$.ajax({
			"url" : "/stuenroll/organization/addOrganization",
			"type" : "post",
			"datatype" : "json",
			"async" : false,
			"data" : {
				"name" : name,
				"abbreviation" : abbreviation,
				"address" : address,
				"liaison" : liaison,
				"tel" : tel
			},
			"success" : function(json){
				toastr.success("添加"+json.result);
			},
			"error" : function(){
				toastr.error("添加失败，请确认填入正确信息");
			}
		});
	}
	
	Organization.prototype.updateOrganizationById = function(json){
		var checkTable = $(".tab-container .tab-content[data-index='机构列表'] .check-table");
		var name = checkTable.find("*[name='name']").val();
		var abbreviation = checkTable.find("*[name='abbreviation']").val();
		var address = checkTable.find("*[name='address']").val();
		var liaison = checkTable.find("*[name='liaison']").val();
		var tel = checkTable.find("*[name='tel']").val();
		var checkbox = organizationElement.find("*[name='id']:checked");
		var id = $(checkbox[0]).val();
		$.ajax({
			"url" : "/stuenroll/organization/updateOrganizationById",
			"type" : "post",
			"datatype" : "json",
			"async" : false,
			"data" : {
				"name" : name,
				"abbreviation" : abbreviation,
				"address" : address,
				"liaison" : liaison,
				"tel" : tel,
				"id" : id
			},
			"success" : function(json){
				toastr.success("修改"+json.result);
			},
			"error" : function(){
				toastr.error("系统异常");
			}
		});
	}
	Organization.prototype.addJoin = function(json){
		var addJoinTable = $(".tab-container .tab-content[data-index='机构列表'] .addJoin-table");
		var checkbox = organizationElement.find("*[name='id']:checked");
		var id = [];
		for (var i = 0; i < checkbox.length; i++) {
			id.push($(checkbox[i]).val());
		}
		for (var i = 0; i < checkbox.length; i++) {
			console.log(id[i]);
		}
		var year = addJoinTable.find("*[name='year']").val();
		var select = addJoinTable.find("*[name='select']").val();
		if(select == "开放"){
			var block = 0;
		}
		else if(select == "不开放"){
			var block = 1;
		}
		$.ajax({
			"url" : "/stuenroll/organization/addJoin",
			"type" : "post",
			"datatype" : "json",
			"traditional" : true,
			"async" : false,
			"data" : {
				"year" : year,
				"block" : block,
				"id" : id,
			},
			"success" : function(json){
				toastr.success("成功转换"+json.addRows+"条数据");
			},
			"error" : function(){
				toastr.error("系统异常");
			}
		});
	}
	
	Organization.prototype.searchJoin = function(json){
		var checkTable = $(".tab-container .tab-content[data-index='参与转换'] .check-table");
		var name = checkTable.find("*[name='name']").val();
		var abbreviation = checkTable.find("*[name='abbreviation']").val();
		var year = checkTable.find("*[name='year']").val();
		$.ajax({
			"url" : "/stuenroll/organization/searchJoin",
			"type" : "post",
			"dataType" : "json",
			"async" : false,
			"data" : {
				"name" : name,
				"abbreviation" : abbreviation,
				"year" : year,
			},
			"success" : function(json) {
				var data = json.result;
				var table = $(".tab-container .tab-content[data-index='参与转换'] .data-table");
				// 清空表格数据
				table.find("tr:gt(0)").remove();

				// 获得当前页数
				var currentPage = $(".tab-container .tab-content[data-index='参与转换'] #currentPage").text();
				// 转化成数字类型
				currentPage = new Number(currentPage);
				// 当前页数的行号起始数字
				var start = (currentPage - 1) * 35;

				var temp = "";//先设一个为空的字符串，后面再往里填数据
				for (var i = 0; i < data.length; i++) {
					var one = data[i];
					temp += "<tr>";
					temp += "<td><input type='checkbox' name='id' value='" + one.id + "' /></td>";
					temp += "<td>" + (start + i + 1) + "</td>";
					temp += "<td name='name'>" + one.name + "</td>";
					temp += "<td name='abbreviation'>" + one.abbreviation + "</td>";
					temp += "<td name='year'>" + one.year + "</td>";
					temp += "<td name='pcount'>" + one.pcount + "</td>";
					temp += "<td name='ccount'>" + one.ccount + "</td>";
					temp += "<td name='scount'>" + one.scount + "</td>";
					temp += "<td name='qcount'>" + one.qcount + "</td>";
					temp += "<td name='wcount'>" + one.wcount + "</td>";
					temp += "<td><label><input type='checkbox' class='ios-switch green' name='openJoin' "
						if(one.block==0){
							temp += "checked";
						}
					temp +=	"/><div><div></div></div></label></td>"
					temp += "</tr>";
				}
				table.append(temp);
				
				table.find("*[name='openJoin']").change(function(){
					var id = $(this).parents("tr").find("*[name='id']").val();
					if($(this).is(':checked')){
						organization.openJoinById({"id": id});
					}
					else{
						organization.closeJoinById({"id": id});
					}
				});
			},
			"error" : function() {
				toastr.error("系统异常");
			}
		});
	}
	
	Organization.prototype.searchJoinCount = function(json){
		var checkTable = $(".tab-container .tab-content[data-index='参与转换'] .check-table");
		var name = checkTable.find("*[name='name']").val();
		var abbreviation = checkTable.find("*[name='abbreviation']").val();
		var year = checkTable.find("*[name='year']").val();
		$.ajax({
			"url" : "/stuenroll/organization/searchJoinCount",
			"type" : "post",
			"dataType" : "json",
			"async" : false,
			"data" : {
				"name" : name,
				"abbreviation" : abbreviation,
				"year" : year,
			},
			"async":false,
			"success" : function(json) {
				var count = json.result; // 总记录数
				var content = $(".tab-container .tab-content[data-index='参与转换']");
				content.find("#totalRows").text(count);
				var totalPages = (count % 35 == 0) ? count / 35 : Math.floor(count / 35) + 1;
				content.find("#totalPages").text(totalPages);
			},
			"error" : function() {
				toastr.error("系统异常");
			}
		});
	}
	
	Organization.prototype.updateJoinById = function(json){
		var checkTable = $(".tab-container .tab-content[data-index='参与转换'] .check-table");
		var year = checkTable.find("*[name='year']").val();
		var checkbox = joinElement.find("*[name='id']:checked");
		var id = $(checkbox[0]).val();
		$.ajax({
			"url" : "/stuenroll/organization/updateJoinById",
			"type" : "post",
			"datatype" : "json",
			"async" : false,
			"data" : {
				"year" : year,
				"id" : id
			},
			"success" : function(json){
				toastr.success("修改"+json.result);
			},
			"error" : function(){
				toastr.error("系统异常");
			}
		});
	}
	
	Organization.prototype.refreshPage = function(){
		var table = $(".tab-container .tab-content[data-index='参与转换'] .page-list");
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
			$(pagetest[currentNumber-1]).removeClass("page-disable");
			$(pagetest[currentNumber-1]).addClass("page-active");
		}
	}
	
	Organization.prototype.deleteJoinById = function(json){
		var bool = confirm("是否删除选中的记录？");
		if (bool == false) {
			return;
		}
		// 获得被选中的记录
		var content = $(".tab-container .tab-content[data-index='参与转换']");
		var checkbox = content.find("*[name='id']:checked"); // 被选中的复选框

		var id = [];
		for (var i = 0; i < checkbox.length; i++) {
			var ccount = $(checkbox[i]).parents('tr').find("*[name='ccount']").text();
			var scount = $(checkbox[i]).parents('tr').find("*[name='scount']").text();
			if( ccount == 0 && scount == 0){
				id.push($(checkbox[i]).val());
			}
			else{
				var name = $(checkbox[i]).parents('tr').find("*[name='name']").text();
				alert(name+"已经有班级或学生，无法删除");
			}
		}
		
		$.ajax({
			"url" : "/stuenroll/organization/deleteJoinById",
			"type" : "post",
			"dataType" : "json",
			"traditional" : true,	//发送数组JSON格式
			"async":false,
			"data" : {
				"id" : id
			},
			"success" : function(json) {
				toastr.success("删除了" + json.deleteRows + "条记录");
			},
			"error" : function() {
				toastr.error("操作失败");
			}
		});
	}
	
	Organization.prototype.openJoinById = function(json){
		$.ajax({
			"url" : "/stuenroll/organization/openJoinById",
			"type" : "post",
			"datatype" : "json",
			"async" : false,
			"data" : json,
			"success" : function(json){
				toastr.success("开放报名"+json.result);
			},
			"error" : function(){
				toastr.error("系统异常");
			}
		});
	}
	
	Organization.prototype.closeJoinById = function(json){
		$.ajax({
			"url" : "/stuenroll/organization/closeJoinById",
			"type" : "post",
			"datatype" : "json",
			"async" : false,
			"data" : json,
			"success" : function(json){
				toastr.success("关闭报名"+json.result);
			},
			"error" : function(){
				toastr.error("系统异常");
			}
		});
	}
	
	function factory(key) {
		if(key == "Tab"){
			return new Tab();
		}
		else if (key == "Organization"){
			return new Organization();
		}
	}
	
	var tab = factory("Tab");
	var organization = factory("Organization");
	
	$(".tab-list .tab-item").click(function(){
		var temp = $(this).data("index");
		tab.showTab(temp);
		
		if(temp == "机构列表"){
			organization.showOrganization();
			organization.showOrganizationCount();
		}
		if(temp == "参与转换"){
			organization.searchJoin();
			organization.searchJoinCount();
			organization.refreshPage();
		}
	});
	
	organization.showOrganization();
	organization.showOrganizationCount();
	organization.refreshPage();
	
	
	var organizationElement = $(".tab-container .tab-content[data-index='机构列表']");
	
	organizationElement.find("*[name='addOrganization']").click(function(){
		$(this).parents(".tab-content").find(".check-table").css('display','block');	//显示该模块
		$(this).parents(".tab-content").find("*[name='addInfo']").css('display','inline');
		$(this).parents(".tab-content").find("*[name='updateInfo']").css('display','none');
		$(this).parents(".tab-content").find("*[name='saveBtn']").css('display','inline');	
		$(this).parents(".tab-content").find("*[name='updateBtn']").css('display','none');	//不显示修改
		$(this).parents(".tab-content").find(".addJoin-table").css('display','none');
	});
	
	organizationElement.find("*[name='saveBtn']").click(function(){
		organization.addOrganization();
		$(this).parents(".tab-content").find(".check-table").css('display','none');	//下拉框消失
		organization.showOrganization();
		organization.showOrganizationCount();
	});
	
	organizationElement.find("*[name='quitBtn']").click(function(){
		$(this).parents(".tab-content").find(".check-table").css('display','none');
	});
	
	organizationElement.find("*[name='updateOrganization']").click(function(){
		var checkbox = organizationElement.find("*[name='id']:checked"); // 被选中的复选框
		if(checkbox.length != 1){
			toastr.warning("请选择一条记录进行修改");
			return
		}
		var id = $(checkbox[0]).val();
		var checkitem = organizationElement.find(".check-table .check-item");
		var name = $(checkbox[0]).parents('tr').find("*[name='name']").text();
		var abbreviation = $(checkbox[0]).parents('tr').find("*[name='abbreviation']").text();
		var address = $(checkbox[0]).parents('tr').find("*[name='address']").text();
		var liaison = $(checkbox[0]).parents('tr').find("*[name='liaison']").text();
		var tel = $(checkbox[0]).parents('tr').find("*[name='tel']").text();
		checkitem.find("#name").val(name);
		checkitem.find("#abbreviation").val(abbreviation);
		checkitem.find("#address").val(address);
		checkitem.find("#liaison").val(liaison);
		checkitem.find("#tel").val(tel);
		$(this).parents(".tab-content").find(".check-table").css('display','block');	//显示该模块
		$(this).parents(".tab-content").find("*[name='addInfo']").css('display','none');
		$(this).parents(".tab-content").find("*[name='updateInfo']").css('display','inline');
		$(this).parents(".tab-content").find("*[name='saveBtn']").css('display','none');	//不显示添加
		$(this).parents(".tab-content").find("*[name='updateBtn']").css('display','inline');	
		$(this).parents(".tab-content").find(".addJoin-table").css('display','none');
	});
	
	organizationElement.find("*[name='updateBtn']").click(function(){
		organization.updateOrganizationById();
		$(this).parents(".tab-content").find(".check-table").css('display','none');
		organization.showOrganizationCount();
		organization.showOrganization();
	});
	
	organizationElement.find("*[name='clearBtn']").click(function(){
		var checkTable = $(".tab-container .tab-content[data-index='机构列表'] .check-table");
		checkTable.find("*[name='name']").val("");
		checkTable.find("*[name='abbreviation']").val("");
		checkTable.find("*[name='address']").val("");
		checkTable.find("*[name='liaison']").val("");
		checkTable.find("*[name='tel']").val("");
	});
	
	organizationElement.find("*[name='deleteOrganization']").click(function(){
		var checkbox = organizationElement.find("*[name='id']:checked"); // 被选中的复选框
		if(checkbox.length == 0){
			toastr.warning("请选择至少一条记录进行删除");
			return
		}
		organization.deleteById();
		organization.showOrganizationCount();
		organization.showOrganization();
	});
	
	organizationElement.find("*[name='addJoin']").click(function(){
		$(this).parents(".tab-content").find(".check-table").css('display','none');
		$(this).parents(".tab-content").find(".addJoin-table").css('display','block');
		var checkbox = organizationElement.find("*[name='id']:checked"); // 被选中的复选框
		var number = checkbox.length;
		var addJoinitem = organizationElement.find(".addJoin-table .addJoin-item");
		addJoinitem.find("#number").val(number);
	});
	
	organizationElement.find("*[name='addJoinBtn']").click(function(){
		var checkbox = organizationElement.find("*[name='id']:checked"); // 被选中的复选框
		if(checkbox.length == 0){
			toastr.warning("请选择至少一条记录进行转换");
			return
		}
		organization.addJoin();
		$(this).parents(".tab-content").find(".addJoin-table").css('display','none');
	});
	
	organizationElement.find("*[name='quitJoinBtn']").click(function(){
		$(this).parents(".tab-content").find(".addJoin-table").css('display','none');
	});
	
	var joinElement = $(".tab-container .tab-content[data-index='参与转换']");

	joinElement.find("*[name='searchJoin']").click(function(){
		var checkitem = joinElement.find(".check-table .check-item");
		checkitem.find("#name").removeAttr('readonly','readonly');
		checkitem.find("#abbreviation").removeAttr('readonly','readonly');
		checkitem.find("*[name='name']").val("");
		checkitem.find("*[name='abbreviation']").val("");
		checkitem.find("*[name='year']").val("");
		$(this).parents(".tab-content").find(".check-table").css('display','block');	//显示该模块
		$(this).parents(".tab-content").find("*[name='searchInfo']").css('display','inline');
		$(this).parents(".tab-content").find("*[name='updateInfo']").css('display','none');
		$(this).parents(".tab-content").find("*[name='searchBtn']").css('display','inline');	
		$(this).parents(".tab-content").find("*[name='updateBtn']").css('display','none');	//不显示修改
	});

	joinElement.find("*[name='searchBtn']").click(function(){
		organization.searchJoin();
		organization.searchJoinCount();
		$(this).parents(".tab-content").find(".check-table").css('display','none');	//下拉框消失
	});

	joinElement.find("*[name='quitBtn']").click(function(){
		$(this).parents(".tab-content").find(".check-table").css('display','none');
	});

	joinElement.find("*[name='updateJoin']").click(function(){
		var checkbox = joinElement.find("*[name='id']:checked"); // 被选中的复选框
		if(checkbox.length != 1){
			toastr.warning("请选择一条记录进行修改");
			return
		}
		var id = $(checkbox[0]).val();
		var checkitem = joinElement.find(".check-table .check-item");
		var name = $(checkbox[0]).parents('tr').find("*[name='name']").text();
		var abbreviation = $(checkbox[0]).parents('tr').find("*[name='abbreviation']").text();
		var year = $(checkbox[0]).parents('tr').find("*[name='year']").text();
		checkitem.find("#name").val(name);
		checkitem.find("#name").attr('readonly','readonly');
		checkitem.find("#abbreviation").val(abbreviation);
		checkitem.find("#abbreviation").attr('readonly','readonly');
		checkitem.find("#year").val(year);
		$(this).parents(".tab-content").find(".check-table").css('display','block');	//显示该模块
		$(this).parents(".tab-content").find("*[name='searchInfo']").css('display','none');
		$(this).parents(".tab-content").find("*[name='updateInfo']").css('display','inline');
		$(this).parents(".tab-content").find("*[name='searchBtn']").css('display','none');	//不显示添加
		$(this).parents(".tab-content").find("*[name='updateBtn']").css('display','inline');	
	});

	joinElement.find("*[name='updateBtn']").click(function(){
		organization.updateJoinById();
		var checkTable = $(".tab-container .tab-content[data-index='参与转换'] .check-table");
		checkTable.find("*[name='name']").val("");
		checkTable.find("*[name='abbreviation']").val("");
		checkTable.find("*[name='year']").val("");
		$(this).parents(".tab-content").find(".check-table").css('display','none');
		organization.searchJoinCount();
		organization.searchJoin();
	});

	joinElement.find("*[name='clearBtn']").click(function(){
		var checkTable = $(".tab-container .tab-content[data-index='参与转换'] .check-table");
		checkTable.find("*[name='name']").val("");
		checkTable.find("*[name='abbreviation']").val("");
		checkTable.find("*[name='year']").val("");
	});
	
	joinElement.find("*[name='deleteJoin']").click(function(){
		var checkbox = joinElement.find("*[name='id']:checked"); // 被选中的复选框
		if(checkbox.length == 0){
			toastr.warning("请选择至少一条记录进行删除");
			return
		}
		organization.deleteJoinById();
		var currentPage = $(this).parents(".tab-container").find("#currentPage").text();
		organization.searchJoin({
			"page" : currentPage
		});
		organization.searchJoinCount();
	});
	
	joinElement.find("*[name='prevBtn']").click(function(){
		var temp = $(this).parents(".page-list").find("#currentPage");
		var currentPage = temp.text();
		currentPage = new Number(currentPage);
		if(currentPage > 1){
			//根据隐藏的查询面板的设置条件查询数据
			var name = archiveElement.find(".check-table *[name='name']").val();
			var abbreviation = archiveElement.find(".check-table *[name='abbreviation']").val();
			var year = archiveElement.find(".check-table *[name='year']").val();
			//请求Ajax并更新数据
			organization.searchJoin({
				"name" : name,
				"abbreviation" : abbreviation,
				"year" : year,
				"page" : currentPage - 1
			});
			temp.text(currentPage - 1 );//当前页数减去1页
		}
		archive.refreshArchive();
	});
	
	joinElement.find("*[name='nextBtn']").click(function(){
		var temp = $(this).parents(".page-list").find("#currentPage");
		var currentPage = temp.text();
		currentPage = new Number(currentPage);
		var totalPages = $(this).parents(".page-list").find("#totalPages").text();
		totalPages = new Number(totalPages);
		if(currentPage < totalPages){
			// 根据隐藏的查询面板的设置条件查询数据
			var name = archiveElement.find(".check-table *[name='name']").val();
			var abbreviation = archiveElement.find(".check-table *[name='abbreviation']").val();
			var year = archiveElement.find(".check-table *[name='year']").val();
			//请求Ajax并更新数据
			archive.searchArchive({
				"name" : name,
				"abbreviation" : abbreviation,
				"year" : year,
				"page" : currentPage + 1
			});
			temp.text(currentPage + 1 );//当前页数加上1页
		}
		archive.refreshArchive();
	});
});
