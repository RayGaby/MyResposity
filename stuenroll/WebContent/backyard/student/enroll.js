$(function() {
	if (!checkPermission([ "3_4" ])) {
		return;
	}
	
	var tabContainer=$(".tab-container");
	if(checkPermission(["3_4"])){
		tabContainer.find(".operation-item[name='search']").show();
	}
	if(checkPermission(["0"])){
		tabContainer.find(".operation-item[name='append']").show();
	}
	if(checkPermission(["3_3"])){
		tabContainer.find(".operation-item[name='update']").show();
	}
	if(checkPermission(["3_2"])){
		tabContainer.find(".operation-item[name='delete']").show();
	}
	if(checkPermission(["3_19"])){
		tabContainer.find(".operation-item[name='divide']").show();
	}
	if(checkPermission(["3_20"])){
		tabContainer.find(".operation-item[name='undivided']").show();
	}
	if(checkPermission(["5_1"])){
		tabContainer.find(".operation-item[name='quit']").show();
	}
	if(checkPermission(["5_2"])){
		tabContainer.find(".operation-item[name='unquit']").show();
	}
	/**
	 * 报名管理抽象接口
	 */
	var I_Enroll = function() {

	}

	I_Enroll.prototype.searchEnroll = function(json) {
		throw "抽象方法";
	}
	I_Enroll.prototype.searchEnrollCount = function(json) {
		throw "抽象方法";
	}
	I_Enroll.prototype.deleteById = function() {
		throw "抽象方法";
	}
	I_Enroll.prototype.getinfo = function() {
		throw "抽象方法";
	}
	I_Enroll.prototype.update = function(){
		throw "抽象方法";
	}
	I_Enroll.prototype.divide = function(){
		throw "抽象方法";
	}
	I_Enroll.prototype.undivided = function(){
		throw "抽象方法";
	}
	I_Enroll.prototype.quit = function(){
		throw "抽象方法";
	}
	I_Enroll.prototype.unquit = function(){
		throw "抽象方法";
	}
	
	var Enroll = function() {

	}
	Enroll.prototype = new I_Enroll();
	Enroll.prototype.searchEnroll = function(json) {
		var index = json.index;
		$.ajax({
			"url" : "/stuenroll/enroll/searchEnroll",
			"type" : "post",
			"dataType" : "json",
			"data" : json,
			"async":false,
			"success" : function(json) {
				var data = json.result;
				var table;
				var currentPage;
				if(index=="全部学员"){
					// 获得当前选中的表格
					table = $(".tab-container .tab-content[data-index='全部学员'] .data-table");
					// 获得当前页数
					currentPage = $(".tab-container .tab-content[data-index='全部学员'] .currentPage").text();
				}else if(index=="已分班"){
					// 获得当前选中的表格
					table = $(".tab-container .tab-content[data-index='已分班'] .data-table");
					// 获得当前页数
					currentPage = $(".tab-container .tab-content[data-index='已分班'] .currentPage").text();
				}else if(index=="未分班"){
					// 获得当前选中的表格
					table = $(".tab-container .tab-content[data-index='未分班'] .data-table");
					// 获得当前页数
					currentPage = $(".tab-container .tab-content[data-index='未分班'] .currentPage").text();
				}else if(index=="中退学员"){
					// 获得当前选中的表格
					table = $(".tab-container .tab-content[data-index='中退学员'] .data-table");
					// 获得当前页数
					currentPage = $(".tab-container .tab-content[data-index='中退学员'] .currentPage").text();
				}
				
				// 清空表格数据
				table.find("tr:gt(0)").remove();
				// 转化成数字类型
				currentPage = new Number(currentPage);
				// 当前页数的行号起始数字
				var start = (currentPage - 1) * 35;
				var temp = "";
				for (var i = 0; i < data.length; i++) {
					var one = data[i];
					console.log(one.name);
					temp += "<tr>";
					temp += "<td><input type='checkbox' name='id' value='" + one.id + "' /></td>"
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
			"error" : function() {
				toast.error("系统异常");
			}
		});
	}
	Enroll.prototype.searchEnrollCount = function(json) {
		var index = json.index;
		$.ajax({
			"url" : "/stuenroll/enroll/searchEnrollCount",
			"type" : "post",
			"dataType" : "json",
			"data" : json,
			"async":false,
			"success" : function(json) {
				var count = json.result; // 总记录数
				var content
				if(index=="全部学员"){
					content = $(".tab-container .tab-content[data-index='全部学员']");
				}else if(index=="已分班"){
					content = $(".tab-container .tab-content[data-index='已分班']");
				}else if(index=="未分班"){
					content = $(".tab-container .tab-content[data-index='未分班']");
				}else if(index=="中退学员"){
					content = $(".tab-container .tab-content[data-index='中退学员']");
				}
				content.find(".totalRows").text(count);
				var totalPages = (count % 35 == 0) ? count / 35 : Math.floor(count / 35) + 1;
				content.find(".totalPages").text(totalPages);
			},
			"error" : function() {
				toast.error("系统异常");
			}
		});
	}
	Enroll.prototype.deleteById = function() {
		// 获得被选中的记录
		var content ;
		if(index=="全部学员"){
			content = $(".tab-container .tab-content[data-index='全部学员']");
		}else if(index=="已分班"){
			content = $(".tab-container .tab-content[data-index='已分班']");
		}else if(index=="未分班"){
			content = $(".tab-container .tab-content[data-index='未分班']");
		}else if(index=="中退学员"){
			content = $(".tab-container .tab-content[data-index='中退学员']");
		}
		var checkbox = $(content).find("*[name='id']:checked"); // 被选中的复选框
		if(checkbox.length==0){
			toastr.warning("没有选中任何数据");
			return;
		}
		// 弹出确认对话框
		var bool = confirm("是否删除选中的记录？");
		if (bool == false) {
			return;
		}
		var id = [];
		for (var i = 0; i < checkbox.length; i++) {
			id.push($(checkbox[i]).val());
		}

		$.ajax({
			"url" : "/stuenroll/enroll/deleteById",
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
				toastr.error("系统异常");
			}
		});
	}
	Enroll.prototype.getinfo = function(){
		// 获得被选中的记录
		var content ;
		if(index=="全部学员"){
			content = $(".tab-container .tab-content[data-index='全部学员']");
		}else if(index=="已分班"){
			content = $(".tab-container .tab-content[data-index='已分班']");
		}else if(index=="未分班"){
			content = $(".tab-container .tab-content[data-index='未分班']");
		}else if(index=="中退学员"){
			content = $(".tab-container .tab-content[data-index='中退学员']");
		}
		var checkbox = $(content).find("*[name='id']:checked"); // 被选中的复选框
		var id = $(checkbox[0]).val();
		var box = content.find(".updateMenu");
		$.ajax({
			"url" : "/stuenroll/enroll/getinfo",
			"type" : "post",
			"dataType" : "json",
			"traditional" : true,	//发送数组JSON格式
			"async":false,
			"data" : {
				"id" : id
			},
			"success" : function(json) {
				$(box).find(".input").removeClass("error");
				var data = json.result;
				$(box).find(".input[name='name']").val(data.name);
				$(box).find(".select-box[name='sex']").val(data.sex);
				$(box).find(".select-box[name='nation']").val(data.nation);
				$(box).find(".select-box[name='graduate_year']").val(data.graduate_year);
				$(box).find(".select-box[name='major']").val(data.major);
				$(box).find(".select-box[name='healthy']").val(data.healthy);
				$(box).find(".select-box[name='major']").val(data.major);
				$(box).find(".select-box[name='politics']").val(data.politics);
				$(box).find(".input[name='graduate_school']").val(data.graduate_school);
				$(box).find(".input[name='graduate_date']").val(data.graduate_date);
				$(box).find(".input[name='birthday']").val(data.birthday);
				$(box).find(".input[name='resident_address']").val(data.resident_address);
				$(box).find(".input[name='permanent_address']").val(data.permanent_address);
				$(box).find(".input[name='home_address']").val(data.home_address);
				$(box).find(".input[name='tel']").val(data.tel);
				$(box).find(".input[name='home_tel']").val(data.home_tel);
				$(box).find(".input[name='email']").val(data.email);
				$(box).find(".input[name='className']").val(data.classinfo_id);
				$(box).find(".input[name='pid']").val(data.pid);
				$(box).find(".select-box[name='organization']").val(data.organization_id);
				var profession = $(box).find(".select-box[name='profession']");
				var date = new Date();
				var year = date.getFullYear();
				$.ajax({
					"url" : "/stuenroll/enroll/getProfession",
					"type" : "post",
					"dataType" : "json",
					"traditional" : true,	//发送数组JSON格式
					"async":false,
					"data" : {
						"organizationId" : data.organization_id,
						"year":year,
					},
					"success" : function(json) {
						var data = json.result;
						$(profession).find("option").remove();
						for (var i = 0; i < data.length; i++) {
							var one = data[i];
							console.log(one.professionName);
							profession.append("<option value ="+one.professionId+">"+one.professionName+"</option>");
						}
					},
					"error" : function() {
						toastr.error("系统异常");
					}
				});
				$(box).find(".select-box[name='profession']").val(data.profession_id);
				$(box).find(".select-box[name='place']").val(data.place);
			},
			"error" : function() {
				toastr.error("系统异常");
			}
		});
	}
	Enroll.prototype.update = function(method){
		// 获得被选中的记录
		var content ;
		if(index=="全部学员"){
			content = $(".tab-container .tab-content[data-index='全部学员']");
		}else if(index=="已分班"){
			content = $(".tab-container .tab-content[data-index='已分班']");
		}else if(index=="未分班"){
			content = $(".tab-container .tab-content[data-index='未分班']");
		}else if(index=="中退学员"){
			content = $(".tab-container .tab-content[data-index='中退学员']");
		}
		var menu;
		if(method=="添加")
			menu = $(content).find(".appendMenu");
		else
			menu = $(content).find(".updateMenu");
		var date = new Date();
		var year = date.getFullYear();
		var content ;
		if(index=="全部学员"){
			content = $(".tab-container .tab-content[data-index='全部学员']");
		}else if(index=="已分班"){
			content = $(".tab-container .tab-content[data-index='已分班']");
		}else if(index=="未分班"){
			content = $(".tab-container .tab-content[data-index='未分班']");
		}else if(index=="中退学员"){
			content = $(".tab-container .tab-content[data-index='中退学员']");
		}
		var checkbox = $(content).find("*[name='id']:checked"); // 被选中的复选框
		var id = $(checkbox[0]).val();
		$.ajax({
			"url" : "/stuenroll/enroll/update",
			"type" : "post",
			"dataType" : "json",
			"traditional" : true,	//发送数组JSON格式
			"async":false,
			"data" : {
				"method":method,
				"name":$(menu).find(".input[name='name']").val(),
				"sex":$(menu).find(".select-box[name='sex']").val(),
				"nation":$(menu).find(".select-box[name='nation']").val(),
				"graduate_year":$(menu).find(".select-box[name='graduate_year']").val(),
				"major":$(menu).find(".select-box[name='major']").val(),
				"healthy":$(menu).find(".select-box[name='healthy']").val(),
				"politics":$(menu).find(".select-box[name='politics']").val(),
				"graduate_school":$(menu).find(".input[name='graduate_school']").val(),
				"graduate_date":$(menu).find(".input[name='graduate_date']").val(),
				"birthday":$(menu).find(".input[name='birthday']").val(),
				"resident_address":$(menu).find(".input[name='resident_address']").val(),
				"permanent_address":$(menu).find(".input[name='permanent_address']").val(),
				"home_address":$(menu).find(".input[name='home_address']").val(),
				"tel":$(menu).find(".input[name='tel']").val(),
				"home_tel":$(menu).find(".input[name='home_tel']").val(),
				"email":$(menu).find(".input[name='email']").val(),
				"classinfo_id":$(menu).find(".input[name='className']").val(),
				"pid":$(menu).find(".input[name='pid']").val(),
				"organization_id":$(menu).find(".select-box[name='organization']").val(),
				"professionId":$(menu).find(".select-box[name='profession']").val(),
				"year":year,
				"place":$(menu).find(".select-box[name='place']").val(),
				"id":id
			},
			"success" : function(json) {
				if(json.updateRows!=-1){
					toastr.success("成功处理了" + json.updateRows + "条记录");
				}else{
					toastr.warning("添加数据已存在,操作失败");
				}
			},
			"error" : function() {
				toastr.error("操作失败,系统异常");
			}
		});
	}
	Enroll.prototype.divide = function() {
		// 获得被选中的记录
		var content ;
		if(index=="全部学员"){
			content = $(".tab-container .tab-content[data-index='全部学员']");
		}else if(index=="未分班"){
			content = $(".tab-container .tab-content[data-index='未分班']");
		}
		var bool = true;
		var select = content.find(".divideMenu .select-box");
		select.each(function(i, one) {
			if ($(one).val()=="")
				bool = false;
			console.log($(one).val());
		});
		if (!bool) {
			toastr.error("内容错误，请正确选择内容");
			return;
		}
		var organizationId = content.find(".divideMenu .select-box[name='organization']").val();
		var professionId = content.find(".divideMenu .select-box[name='profession']").val();
		var classId = content.find(".divideMenu .select-box[name='class_name']").val();
		var place = content.find(".divideMenu .select-box[name='place']").val();
		var checkbox = $(content).find("*[name='id']:checked"); // 被选中的复选框
		var id = [];
		for (var i = 0; i < checkbox.length; i++) {
			id.push($(checkbox[i]).val());
		}
		$.ajax({
			"url" : "/stuenroll/enroll/divide",
			"type" : "post",
			"dataType" : "json",
			"traditional" : true,	//发送数组JSON格式
			"async":false,
			"data" : {
				"id" : id,
				"organizationId":organizationId,
				"professionId":professionId,
				"classId":classId,
				"place":place
			},
			"success" : function(json) {
				toastr.success("成功处理了" + json.divideRows + "条记录");
			},
			"error" : function() {
				toastr.error("系统异常");
			}
		});
	}
	Enroll.prototype.undivided = function() {
		// 获得被选中的记录
		var content ;
		if(index=="全部学员"){
			content = $(".tab-container .tab-content[data-index='全部学员']");
		}else if(index=="已分班"){
			content = $(".tab-container .tab-content[data-index='已分班']");
		}
		var checkbox = $(content).find("*[name='id']:checked"); // 被选中的复选框
		if(checkbox.length==0){
			toastr.warning("没有选中任何数据");
			return;
		}
		// 弹出确认对话框
		var bool = confirm("是否取消分班？");
		if (bool == false) {
			return;
		}
		var id = [];
		for (var i = 0; i < checkbox.length; i++) {
			id.push($(checkbox[i]).val());
		}
		$.ajax({
			"url" : "/stuenroll/enroll/undivided",
			"type" : "post",
			"dataType" : "json",
			"traditional" : true,	//发送数组JSON格式
			"async":false,
			"data" : {
				"id" : id
			},
			"success" : function(json) {
				if(json.undividedRows>0){
					toastr.success("处理了" + json.undividedRows + "条记录");
				}else{
					toastr.warning("处理了" + json.undividedRows + "条记录,但其中含有未分班成员");
					
				}
				
			},
			"error" : function() {
				toastr.error("系统异常");
			}
		});
	}
	Enroll.prototype.quit = function() {
		// 获得被选中的记录
		var content = $(".tab-container .tab-content[data-index='全部学员']");
		var checkbox = $(content).find("*[name='id']:checked"); // 被选中的复选框
		var id = $(checkbox[0]).val();
		var menu = $(".tab-container .tab-content[data-index='全部学员'] .quitMenu");
		var input = menu.find(".input");
		input.each(function(i, one) {
			bool = bool && one.checkValidity();
		});
		if (!bool) {
			toastr.error("内容错误，请核实填写的内容");
			return;
		}
		var message = $(menu).find(".quit-reason").val();
		var date = $(menu).find(".quit_date").val();
		$.ajax({
			"url" : "/stuenroll/enroll/quit",
			"type" : "post",
			"dataType" : "json",
			"traditional" : true,	//发送数组JSON格式
			"async":false,
			"data" : {
				"id" : id,
				"message":message,
				"date":date
			},
			"success" : function(json) {
				toastr.success("处理了" + json.quitRows + "条记录");
			},
			"error" : function() {
				toastr.error("系统异常");
			}
		});
	}
	Enroll.prototype.unquit = function(){
		// 获得被选中的记录
		var content ;
		if(index=="全部学员"){
			content = $(".tab-container .tab-content[data-index='全部学员']");
		}else if(index=="中退学员"){
			content = $(".tab-container .tab-content[data-index='中退学员']");
		}
		var checkbox = $(content).find("*[name='id']:checked"); // 被选中的复选框
		if(checkbox.length==0){
			toastr.warning("没有选中任何数据");
			return;
		}
		// 弹出确认对话框
		var bool = confirm("是否取消中退？");
		if (bool == false) {
			return;
		}
		var id = [];
		for (var i = 0; i < checkbox.length; i++) {
			id.push($(checkbox[i]).val());
		}
		$.ajax({
			"url" : "/stuenroll/enroll/unquit",
			"type" : "post",
			"dataType" : "json",
			"traditional" : true,	//发送数组JSON格式
			"async":false,
			"data" : {
				"id" : id
			},
			"success" : function(json) {
				if(json.undividedRows>0){
					toastr.success("处理了" + json.unquitRows + "条记录");
				}else{
					toastr.warning("处理了" + json.unquitRows + "条记录,但其中含有未分班成员");
					
				}
			},
			"error" : function() {
				toastr.error("系统异常");
			}
		});
		
	}
	function factory(key) {
		if (key == "Tab") {
			return new Tab();
		}
		else if (key == "Enroll") {
			return new Enroll();
		}
	}

	var index = "全部学员";
	var tab = factory("Tab");
	var enroll = factory("Enroll");

	
	$(".tab-list .tab-item").click(function() {
		up_flag=0;se_flag = 0;
		var flush = $(".main-container .input");
		flush.each(function(i, one) {
			$(one).val("");
		});
		var flu = $(".main-container .select-box");
		flu.each(function(i, one) {
			$(one).val("");
		});
		var temp = $(this).data("index");
		tab.showTab(temp);
		// 切换选项卡，重新查询数据
		if (temp == "全部学员") {
			index = "全部学员";
			$(".tab-container .tab-content[data-index='全部学员'] .currentPage").text(1);
			enroll.searchEnroll({"index":index});
			enroll.searchEnrollCount({"index":index});
		}else if (temp == "已分班") {
			index = "已分班";
			$(".tab-container .tab-content[data-index='已分班'] .currentPage").text(1);
			enroll.searchEnroll({"index":index});
			enroll.searchEnrollCount({"index":index});
		}else if (temp == "未分班") {
			index = "未分班";
			$(".tab-container .tab-content[data-index='未分班'] .currentPage").text(1);
			enroll.searchEnroll({"index":index});
			enroll.searchEnrollCount({"index":index});
		}else if (temp == "中退学员") {
			index = "中退学员";
			$(".tab-container .tab-content[data-index='中退学员'] .currentPage").text(1);
			enroll.searchEnroll({"index":index});
			enroll.searchEnrollCount({"index":index});
		}
		var init_date = new Date();
		var init_year = init_date.getFullYear();
		orgInit(init_year);
		refresh(index);
	});
	
	enroll.searchEnroll({"index":index});
	enroll.searchEnrollCount({"index":index});

	var element = $(".tab-container .tab-content");
	//点击查询时初始化专业信息,并对年届的输入进行监测
	var initProfession = function(index,year){
		var content ;
		if(index=="全部学员"){
			content = $(".tab-container .tab-content[data-index='全部学员']");
		}else if(index=="已分班"){
			content = $(".tab-container .tab-content[data-index='已分班']");
		}else if(index=="未分班"){
			content = $(".tab-container .tab-content[data-index='未分班']");
		}else if(index=="中退学员"){
			content = $(".tab-container .tab-content[data-index='中退学员']");
		}
		var profession = $(content).find(".searchMenu .select-box[name='profession']");
		$(profession).find("option").remove();
		$.ajax({
			"url" : "/stuenroll/enroll/getProfession",
			"type" : "post",
			"dataType" : "json",
			"traditional" : true,	//发送数组JSON格式
			"async":false,
			"data" : {
				"year":year,
			},
			"success" : function(json) {
				var data = json.result;
				profession.append("<option value ="+""+">请选择专业</option>");
				for (var i = 0; i < data.length; i++) {
					var one = data[i];
					console.log(one.professionName);
					profession.append("<option value ="+one.professionId+">"+one.professionName+"</option>");
				}
			},
			"error" : function() {
				toastr.error("系统异常");
			}
		});
	} ;
	//初始化除了search外的公共的机构初始化信息
	var orgInit = function(YEAR){
		var year=YEAR;
		var box = $(".menu");
		var time = $(box).find(".select-box[name='graduate_year']");
		for(var i=0;i<5;i++){
			var t = year-i;
			time.append("<option value ="+t+">"+t+"</option>")
		}
		$.ajax({
			"url" : "/stuenroll/enroll/getOrganization",
			"type" : "post",
			"dataType" : "json",
			"traditional" : true,	//发送数组JSON格式
			"async":false,
			"data" : {
				"year" : year
			},
			"success" : function(json) {
				var data = json.result;
				var organization = $(box).find(".select-box[name='organization']");
				$(organization).find("option").remove();
				organization.append("<option value ="+""+">"+"请选择机构"+"</option>");
				for (var i = 0; i < data.length; i++) {
					var one = data[i];console.log(one.name);
					organization.append("<option value ="+one.organization_id+">"+one.name+"</option>");
				}
				initProfession(index,year);
			},
			"error" : function() {
				toastr.error("系统异常");
			}
		});
	} 
	var init_date = new Date();
	var init_year = init_date.getFullYear();
	orgInit(init_year);
	//监控选择信息,添加专业和地址
	var org = element.find(".menu .select-box[name='organization']");
	$(org).bind("change",function(){
		var flag = "";
		var check_flag = $(this).parents(".operation-item").find(".bref");
		if($(check_flag).text() == "条件查询:"){
			flag = $(this).parents(".operation-item").find(".input[name='year']").val();
		}
		var box = $(this).parents(".menu");
		var profession = $(box).find(".select-box[name='profession']");
		$(profession).find("option").remove();
		var place = $(box).find(".select-box[name='place']");
		$(place).find("option").remove();
		if($(this).val()==""){
			return;
		}
		else{
			var organizationId = $(this).val();
			var date = new Date();
			var year = date.getFullYear();
			if(flag != "" ){
				year = flag;
			}
			$.ajax({
				"url" : "/stuenroll/enroll/getProfession",
				"type" : "post",
				"dataType" : "json",
				"async":false,
				"traditional" : true,	//发送数组JSON格式
				"data" : {
					"organizationId" : organizationId,
					"year":year,
				},
				"success" : function(json) {
					var data = json.result;
					profession.append("<option value ="+""+">请选择专业</option>");
					for (var i = 0; i < data.length; i++) {
						var one = data[i];
						console.log(one.professionName);
						profession.append("<option value ="+one.professionId+">"+one.professionName+"</option>");
					}
				},
				"error" : function() {
					toastr.error("系统异常");
				}
			});
			$.ajax({
				"url" : "/stuenroll/enroll/getPlace",
				"type" : "post",
				"dataType" : "json",
				"traditional" : true,	//发送数组JSON格式
				"data" : {
					"organizationId" : organizationId
				},
				"success" : function(json) {
					var data = json.result;
					for (var i = 0; i < data.length; i++) {
						var one = data[i];
						console.log(one.professionName);
						place.append("<option value ="+one.place+">"+one.place+"</option>");
					}
				},
				"error" : function() {
					toastr.error("系统异常");
				}
			});
		}
	});
	//监控选项,添加班级
	var org1 = element.find(".menu .select-box[name='profession']");
	$(org1).bind("change",function(){
		var box = $(this).parents(".menu");
		var class_name = $(box).find(".select-box[name='class_name']");
		$(class_name).find("option").remove();
		var professionId = $(this).val();
		var organizationId = $(box).find(".select-box[name='organization']").val();
		var date = new Date();
		var year = date.getFullYear();
		$.ajax({
			"url" : "/stuenroll/enroll/getProfessionClass",
			"type" : "post",
			"dataType" : "json",
			"traditional" : true,	//发送数组JSON格式
			"data" : {
				"organizationId" : organizationId,
				"professionId":professionId,
				"year":year
			},
			"success" : function(json) {
				var data = json.result;
				if(data.length>0){
					for (var i = 0; i < data.length; i++) {
						var one = data[i];
						class_name.append("<option value ="+one.id+">"+one.name+"</option>");
					}
					
				}else{
					class_name.append("<option value ="+""+">"+"该专业暂无班级"+"</option>");
				}
			},
			"error" : function() {
				toastr.error("系统异常");
			}
		});
	});
	
	//清除
	element.find(".menu-btn[name='clear-btn']").click(function(e){
		var flush = $(this).parents(".menu").find(".input");
		flush.each(function(i, one) {
			$(one).val("");
		});
		var flu = $(this).parents(".menu").find(".select-box");
		flu.each(function(i, one) {
			$(one).val("");
		});
		se_flag = 0;
		orgInit(init_year);
		e.stopPropagation();
	});
	//关闭
	element.find(".menu-btn[name='close-btn']").click(function(e){
		up_flag = 0;
		var close = $(".operation-item .menu");
		close.removeClass("come");
		e.stopPropagation();
	});
	
	//查询
	var se_flag = 0;
	element.find(".operation-item[name='search']").click(function(){
		var parent = $(this).parents(".tab-content");
		index = $(parent).data("index");
		var other = $(".operation-item .menu");
		other.removeClass("come");
		if(se_flag==0){
			var flush = $(this).find(".searchMenu .input");
			flush.each(function(i, one) {
				$(one).val("");
			});
			var flu = $(this).find(".searchMenu .select-box");
			flu.each(function(i, one) {
				$(one).val("");
			});
			initProfession(index,init_year);
			se_flag=1;
		}
		$(this).find(".searchMenu").addClass("come");
	});
	element.find(".searchMenu .menu-btn[name='search-btn']").click(function(e){
		var parent = $(this).parents(".tab-content");
		var menu = $(parent).find(".searchMenu");
		var bool = true;
		var input = menu.find(".input");
		input.each(function(i, one) {
			if (one.id == "pid") {
				bool = bool && checkPid($(one).val());
			}
			else {
				bool = bool && one.checkValidity();
			}
		});
		if (!bool) {
			toastr.error("内容错误，请核实填写的内容");
			return;
		}
		var name = menu.find(".input[name='name']").val();
		var pid = menu.find(".input[name='pid']").val();
		var year = menu.find(".input[name='year']").val();
		var sex = menu.find(".select-box[name='sex'] option:selected").val();
		var education = menu.find(".select-box[name='education'] option:selected").val();
		var organization = menu.find(".select-box[name='organization'] option:selected").val();
		var profession = menu.find(".select-box[name='profession'] option:selected").val();
		var className = menu.find(".input[name='className']").val();
		var state_id = menu.find(".select-box[name='state_id'] option:selected").val();
		enroll.searchEnroll({"profession":profession,"className":className,"index":index,"name":name,"pid":pid,"year":year,"sex":sex,"education":education,"stateId":state_id,"organization":organization,});
		enroll.searchEnrollCount({"profession":profession,"className":className,"index":index,"name":name,"pid":pid,"year":year,"sex":sex,"education":education,"stateId":state_id,"organization":organization,});
		$(".searchMenu").removeClass("come");
		se_flag = 0;
		var temp = $(this).parents(".tab-content").find(".page-list .currentPage");
		temp.text(1);
		refresh(index);
		e.stopPropagation();
	});
	//添加
	element.find(".operation-item[name='append']").click(function(){
		var date = new Date();
		var year = date.getFullYear();
		if(init_year!=year){
			inti_year = year;
			orgInit(year);
		}
		var parent = $(this).parents(".tab-content");
		index = $(parent).data("index");
		var other = $(".operation-item .menu");
		other.removeClass("come");
		$(this).find(".appendMenu").addClass("come");
	});
	element.find(".appendMenu .menu-btn[name='append-btn']").click(function(e){
		$(".appendMenu").removeClass("come");
		enroll.update("添加");
		var totalPages = $(this).parents(".tab-content").find(".totalPages").text();
		var currentPage = $(this).parents(".tab-content").find(".currentPage").text();
		totalPages = new Number(totalPages);
		currentPage = new Number(currentPage);
		if (currentPage > totalPages) {
			currentPage = totalPages;
		}
		$(this).parents(".tab-content").find(".currentPage").text(currentPage); // 更新当前页数
		var menu = $(parent).find(".searchMenu");
		var bool = true;
		var input = menu.find(".input");
		input.each(function(i, one) {
			if (one.id == "pid") {
				bool = bool && checkPid($(one).val());
			}
			else {
				bool = bool && one.checkValidity();
			}
		});
		if (!bool) {
			toastr.error("内容错误，请核实填写的内容");
			return;
		}
		var name = menu.find(".input[name='name']").val();
		var pid = menu.find(".input[name='pid']").val();
		var year = menu.find(".input[name='year']").val();
		var sex = menu.find(".select-box[name='sex'] option:selected").val();
		var education = menu.find(".select-box[name='education'] option:selected").val();
		var organization = menu.find(".select-box[name='organization'] option:selected").val();
		var profession = menu.find(".select-box[name='profession'] option:selected").val();
		var className = menu.find(".input[name='className'] option:selected").val();
		var state_id = menu.find(".select-box[name='state_id'] option:selected").val();
		var place = menu.find(".select-box[name='place_t'] option:selected").val();
		enroll.searchEnroll({"profession":profession,"page":currentPage,"className":className,"index":index,"name":name,"pid":pid,"year":year,"sex":sex,"education":education,"stateId":state_id,"palce":place,"organization":organization,});
		enroll.searchEnrollCount({"profession":profession,"className":className,"index":index,"name":name,"pid":pid,"year":year,"sex":sex,"education":education,"stateId":state_id,"palce":place,"organization":organization,});
		refresh(index);
		e.stopPropagation();
	});
	//修改
	var up_flag = 0;
	element.find(".operation-item[name='update']").click(function(){
		var date = new Date();
		var year = date.getFullYear();
		if(init_year!=year){
			inti_year = year;
			orgInit(year);
		}
		var parent = $(this).parents(".tab-content");
		index = $(parent).data("index");
		var content ;
		if(index=="全部学员"){
			content = $(".tab-container .tab-content[data-index='全部学员']");
		}else if(index=="已分班"){
			content = $(".tab-container .tab-content[data-index='已分班']");
		}else if(index=="未分班"){
			content = $(".tab-container .tab-content[data-index='未分班']");
		}else if(index=="中退学员"){
			content = $(".tab-container .tab-content[data-index='中退学员']");
		}
		var checkbox = $(content).find("*[name='id']:checked"); // 被选中的复选框
		if(checkbox.length==0){
			toastr.warning("没有选中任何数据");
			return;
		}
		if(checkbox.length>1){
			toastr.warning("仅支持单条修改");
			return;
		}
		if(up_flag==0){
			enroll.getinfo();
			up_flag=1;
		}
		var other = $(".operation-item .menu");
		other.removeClass("come");
		$(this).find(".updateMenu").addClass("come");
	});
	element.find(".updateMenu .menu-btn[name='append-btn']").click(function(e){
		up_flag = 0;
		$(".updateMenu").removeClass("come");
		enroll.update("修改");
		var totalPages = $(this).parents(".tab-content").find(".totalPages").text();
		var currentPage = $(this).parents(".tab-content").find(".currentPage").text();
		totalPages = new Number(totalPages);
		currentPage = new Number(currentPage);
		if (currentPage > totalPages) {
			currentPage = totalPages;
		}
		$(this).parents(".tab-content").find(".currentPage").text(currentPage); // 更新当前页数
		var menu = $(parent).find(".searchMenu");
		var bool = true;
		var input = menu.find(".input");
		input.each(function(i, one) {
			if (one.id == "pid") {
				bool = bool && checkPid($(one).val());
			}
			else {
				bool = bool && one.checkValidity();
			}
		});
		if (!bool) {
			toastr.error("内容错误，请核实填写的内容");
			return;
		}
		var name = menu.find(".input[name='name']").val();
		var pid = menu.find(".input[name='pid']").val();
		var year = menu.find(".input[name='year']").val();
		var sex = menu.find(".select-box[name='sex'] option:selected").val();
		var education = menu.find(".select-box[name='education'] option:selected").val();
		var organization = menu.find(".select-box[name='organization'] option:selected").val();
		var profession = menu.find(".select-box[name='profession'] option:selected").val();
		var className = menu.find(".input[name='className'] option:selected").val();
		var state_id = menu.find(".select-box[name='state_id'] option:selected").val();
		enroll.searchEnroll({"profession":profession,"page":currentPage,"className":className,"index":index,"name":name,"pid":pid,"year":year,"sex":sex,"education":education,"stateId":state_id,"organization":organization,});
		enroll.searchEnrollCount({"profession":profession,"className":className,"index":index,"name":name,"pid":pid,"year":year,"sex":sex,"education":education,"stateId":state_id,"organization":organization,});
		refresh(index);
		e.stopPropagation();
	});
	//删除
	element.find("*[name='delete']").click(function() {
		var parent = $(this).parents(".tab-content");
		index = $(parent).data("index");
		// 先删除
		enroll.deleteById();
		// 再查询
		var totalPages = $(this).parents(".tab-content").find(".totalPages").text();
		var currentPage = $(this).parents(".tab-content").find(".currentPage").text();
		totalPages = new Number(totalPages);
		currentPage = new Number(currentPage);
		if (currentPage > totalPages) {
			currentPage = totalPages;
		}
		$(this).parents(".tab-content").find(".currentPage").text(currentPage); // 更新当前页数
		var menu = $(parent).find(".searchMenu");
		var bool = true;
		var input = menu.find(".input");
		input.each(function(i, one) {
			if (one.id == "pid") {
				bool = bool && checkPid($(one).val());
			}
			else {
				bool = bool && one.checkValidity();
			}
		});
		if (!bool) {
			toastr.error("内容错误，请核实填写的内容");
			return;
		}
		var name = menu.find(".input[name='name']").val();
		var pid = menu.find(".input[name='pid']").val();
		var year = menu.find(".input[name='year']").val();
		var sex = menu.find(".select-box[name='sex'] option:selected").val();
		var education = menu.find(".select-box[name='education'] option:selected").val();
		var organization = menu.find(".select-box[name='organization'] option:selected").val();
		var profession = menu.find(".select-box[name='profession'] option:selected").val();
		var className = menu.find(".input[name='className'] option:selected").val();
		var state_id = menu.find(".select-box[name='state_id'] option:selected").val();
		enroll.searchEnroll({"profession":profession,"page":currentPage,"className":className,"index":index,"name":name,"pid":pid,"year":year,"sex":sex,"education":education,"stateId":state_id,"organization":organization,});
		enroll.searchEnrollCount({"profession":profession,"className":className,"index":index,"name":name,"pid":pid,"year":year,"sex":sex,"education":education,"stateId":state_id,"organization":organization,});
		refresh(index);
	});
	//学员分班
	element.find(".operation-item[name='divide']").click(function(){
		var date = new Date();
		var year = date.getFullYear();
		if(init_year!=year){
			inti_year = year;
			orgInit(year);
		}
		var parent = $(this).parents(".tab-content");
		index = $(parent).data("index");
		var content ;
		if(index=="全部学员"){
			content = $(".tab-container .tab-content[data-index='全部学员']");
		}else if(index=="未分班"){
			content = $(".tab-container .tab-content[data-index='未分班']");
		}
		var checkbox = $(content).find("*[name='id']:checked"); // 被选中的复选框
		if(checkbox.length==0){
			toastr.warning("没有选中任何数据");
			return;
		}
		var other = $(".operation-item .menu");
		other.removeClass("come");
		$(this).find(".divideMenu").addClass("come");
	});
	
	//保存分班
	element.find(".divideMenu .menu-btn[name='save-btn']").click(function(e){
		enroll.divide();
		$(".divideMenu").removeClass("come");
		// 再查询
		var totalPages = $(this).parents(".tab-content").find(".totalPages").text();
		var currentPage = $(this).parents(".tab-content").find(".currentPage").text();
		totalPages = new Number(totalPages);
		currentPage = new Number(currentPage);
		if (currentPage > totalPages) {
			currentPage = totalPages;
		}
		$(this).parents(".tab-content").find(".currentPage").text(currentPage); // 更新当前页数
		var menu = $(parent).find(".searchMenu");
		var bool = true;
		var input = menu.find(".input");
		input.each(function(i, one) {
			if (one.id == "pid") {
				bool = bool && checkPid($(one).val());
			}
			else {
				bool = bool && one.checkValidity();
			}
		});
		if (!bool) {
			toastr.error("内容错误，请核实填写的内容");
			return;
		}
		var name = menu.find(".input[name='name']").val();
		var pid = menu.find(".input[name='pid']").val();
		var year = menu.find(".input[name='year']").val();
		var sex = menu.find(".select-box[name='sex'] option:selected").val();
		var education = menu.find(".select-box[name='education'] option:selected").val();
		var organization = menu.find(".select-box[name='organization'] option:selected").val();
		var profession = menu.find(".select-box[name='profession'] option:selected").val();
		var className = menu.find(".input[name='className'] option:selected").val();
		var state_id = menu.find(".select-box[name='state_id'] option:selected").val();
		enroll.searchEnroll({"profession":profession,"page":currentPage,"className":className,"index":index,"name":name,"pid":pid,"year":year,"sex":sex,"education":education,"stateId":state_id,"organization":organization,});
		enroll.searchEnrollCount({"profession":profession,"className":className,"index":index,"name":name,"pid":pid,"year":year,"sex":sex,"education":education,"stateId":state_id,"organization":organization,});
		//查完更新页数
		refresh(index);
		e.stopPropagation();
	});
	//取消分班
	element.find(".operation-item[name='undivided']").click(function(){
		enroll.undivided();
		// 再查询
		var totalPages = $(this).parents(".tab-content").find(".totalPages").text();
		var currentPage = $(this).parents(".tab-content").find(".currentPage").text();
		totalPages = new Number(totalPages);
		currentPage = new Number(currentPage);
		if (currentPage > totalPages) {
			currentPage = totalPages;
		}
		$(this).parents(".tab-content").find(".currentPage").text(currentPage); // 更新当前页数
		var menu = $(parent).find(".searchMenu");
		var bool = true;
		var input = menu.find(".input");
		input.each(function(i, one) {
			if (one.id == "pid") {
				bool = bool && checkPid($(one).val());
			}
			else {
				bool = bool && one.checkValidity();
			}
		});
		if (!bool) {
			toastr.error("内容错误，请核实填写的内容");
			return;
		}
		var name = menu.find(".input[name='name']").val();
		var pid = menu.find(".input[name='pid']").val();
		var year = menu.find(".input[name='year']").val();
		var sex = menu.find(".select-box[name='sex'] option:selected").val();
		var education = menu.find(".select-box[name='education'] option:selected").val();
		var organization = menu.find(".select-box[name='organization'] option:selected").val();
		var profession = menu.find(".select-box[name='profession'] option:selected").val();
		var className = menu.find(".input[name='className'] option:selected").val();
		var state_id = menu.find(".select-box[name='state_id'] option:selected").val();
		enroll.searchEnroll({"profession":profession,"page":currentPage,"className":className,"index":index,"name":name,"pid":pid,"year":year,"sex":sex,"education":education,"stateId":state_id,"organization":organization,});
		enroll.searchEnrollCount({"profession":profession,"className":className,"index":index,"name":name,"pid":pid,"year":year,"sex":sex,"education":education,"stateId":state_id,"organization":organization,});
		//查完更新页数
		refresh(index);
	});
	
	//学员中退
	element.find(".operation-item[name='quit']").click(function(){
		var parent = $(this).parents(".tab-content");
		index = $(parent).data("index");
		var content = $(".tab-container .tab-content[data-index='全部学员']");
		var checkbox = $(content).find("*[name='id']:checked"); // 被选中的复选框
		if(checkbox.length==0){
			toastr.warning("没有选中任何数据");
			return;
		}
		if(checkbox.length>1){
			toastr.warning("仅支持单条修改");
			return;
		}
		var other = $(".operation-item .menu");
		other.removeClass("come");
		$(this).find(".quitMenu").addClass("come");
	});
	element.find(".quitMenu .menu-btn[name='save-btn']").click(function(e){
		var parent = $(this).parents(".tab-content");
		var menu = $(parent).find(".searchMenu");
		var bool = true;
		var input = menu.find(".input");
		input.each(function(i, one) {
			if (one.id == "pid") {
				bool = bool && checkPid($(one).val());
			}
			else {
				bool = bool && one.checkValidity();
			}
		});
		if (!bool) {
			toastr.error("内容错误，请核实填写的内容");
			return;
		}
		var name = menu.find(".input[name='name']").val();
		var pid = menu.find(".input[name='pid']").val();
		var year = menu.find(".input[name='year']").val();
		var sex = menu.find(".select-box[name='sex'] option:selected").val();
		var education = menu.find(".select-box[name='education'] option:selected").val();
		var organization = menu.find(".select-box[name='organization'] option:selected").val();
		var profession = menu.find(".select-box[name='profession'] option:selected").val();
		var className = menu.find(".input[name='className'] option:selected").val();
		var state_id = menu.find(".select-box[name='state_id'] option:selected").val();
		enroll.quit();
		enroll.searchEnroll({"profession":profession,"className":className,"index":index,"name":name,"pid":pid,"year":year,"sex":sex,"education":education,"stateId":state_id,"organization":organization,});
		enroll.searchEnrollCount({"profession":profession,"className":className,"index":index,"name":name,"pid":pid,"year":year,"sex":sex,"education":education,"stateId":state_id,"organization":organization,});
		refresh(index);
		$(".quitMenu").removeClass("come");
		e.stopPropagation();
	});
	//取消中退
	element.find(".operation-item[name='unquit']").click(function(){
		var parent = $(this).parents(".tab-content");
		index = $(parent).data("index");
		var content ;
		if(index=="全部学员"){
			content = $(".tab-container .tab-content[data-index='全部学员']");
		}else if(index=="中退学员"){
			content = $(".tab-container .tab-content[data-index='中退学员']");
		}
		var checkbox = $(content).find("*[name='id']:checked"); // 被选中的复选框
		if(checkbox.length==0){
			toastr.warning("没有选中任何数据");
			return;
		}
		//取消中退
		enroll.unquit();
		// 再查询
		var totalPages = $(this).parents(".tab-content").find(".totalPages").text();
		var currentPage = $(this).parents(".tab-content").find(".currentPage").text();
		totalPages = new Number(totalPages);
		currentPage = new Number(currentPage);
		if (currentPage > totalPages) {
			currentPage = totalPages;
		}
		$(this).parents(".tab-content").find(".currentPage").text(currentPage); // 更新当前页数
		var menu = $(parent).find(".searchMenu");
		var bool = true;
		var input = menu.find(".input");
		input.each(function(i, one) {
			if (one.id == "pid") {
				bool = bool && checkPid($(one).val());
			}
			else {
				bool = bool && one.checkValidity();
			}
		});
		if (!bool) {
			toastr.error("内容错误，请核实填写的内容");
			return;
		}
		var name = menu.find(".input[name='name']").val();
		var pid = menu.find(".input[name='pid']").val();
		var year = menu.find(".input[name='year']").val();
		var sex = menu.find(".select-box[name='sex'] option:selected").val();
		var education = menu.find(".select-box[name='education'] option:selected").val();
		var organization = menu.find(".select-box[name='organization'] option:selected").val();
		var profession = menu.find(".select-box[name='profession'] option:selected").val();
		var className = menu.find(".input[name='className'] option:selected").val();
		var state_id = menu.find(".select-box[name='state_id'] option:selected").val();
		enroll.searchEnroll({"profession":profession,"page":currentPage,"className":className,"index":index,"name":name,"pid":pid,"year":year,"sex":sex,"education":education,"stateId":state_id,"organization":organization,});
		enroll.searchEnrollCount({"profession":profession,"className":className,"index":index,"name":name,"pid":pid,"year":year,"sex":sex,"education":education,"stateId":state_id,"organization":organization,});
		//查完更新页数
		refresh(index);
	});
	//上一页
	element.find("*[name='prevBtn']").click(function() {
		var parent = $(this).parents(".tab-content");
		index = $(parent).data("index");
		var temp = $(this).parents(".page-list").find(".currentPage");
		var currentPage = temp.text();
		currentPage = new Number(currentPage);
		if (currentPage > 1) {
			// TODO 根据隐藏的查询面板的设置条件查询数据
			// 请求Ajax并更新数据
			var totalPages = $(this).parents(".tab-content").find(".totalPages").text();
			var currentPage = $(this).parents(".tab-content").find(".currentPage").text();
			totalPages = new Number(totalPages);
			currentPage = new Number(currentPage);
			if (currentPage > totalPages) {
				currentPage = totalPages;
			}
			$(this).parents(".tab-content").find(".currentPage").text(currentPage); // 更新当前页数
			var menu = $(parent).find(".searchMenu");
			var bool = true;
			var input = menu.find(".input");
			input.each(function(i, one) {
				if (one.id == "pid") {
					bool = bool && checkPid($(one).val());
				}
				else {
					bool = bool && one.checkValidity();
				}
			});
			if (!bool) {
				toastr.error("内容错误，请核实填写的内容");
				return;
			}
			var name = menu.find(".input[name='name']").val();
			var pid = menu.find(".input[name='pid']").val();
			var year = menu.find(".input[name='year']").val();
			var sex = menu.find(".select-box[name='sex'] option:selected").val();
			var education = menu.find(".select-box[name='education'] option:selected").val();
			var organization = menu.find(".select-box[name='organization'] option:selected").val();
			var profession = menu.find(".select-box[name='profession'] option:selected").val();
			var className = menu.find(".input[name='className'] option:selected").val();
			var state_id = menu.find(".select-box[name='state_id'] option:selected").val();
			enroll.searchEnroll({"profession":profession,"page":currentPage-1,"className":className,"index":index,"name":name,"pid":pid,"year":year,"sex":sex,"education":education,"stateId":state_id,"organization":organization,});
			temp.text(currentPage - 1); // 当前页数减去1页
			refresh(index);
		}

	});

	//下一页
	element.find("*[name='nextBtn']").click(function() {
		var parent = $(this).parents(".tab-content");
		index = $(parent).data("index");
		var temp = $(this).parents(".page-list").find(".currentPage");
		var currentPage = temp.text();
		var totalPages = $(this).parents(".page-list").find(".totalPages").text();
		currentPage = new Number(currentPage);
		totalPages = new Number(totalPages);
		if (currentPage < totalPages) {
			// TODO 根据隐藏的查询面板的设置条件查询数据
			// 请求Ajax并更新数据
			var totalPages = $(this).parents(".tab-content").find(".totalPages").text();
			var currentPage = $(this).parents(".tab-content").find(".currentPage").text();
			totalPages = new Number(totalPages);
			currentPage = new Number(currentPage);
			if (currentPage > totalPages) {
				currentPage = totalPages;
			}
			$(this).parents(".tab-content").find(".currentPage").text(currentPage); // 更新当前页数
			var menu = $(parent).find(".searchMenu");
			var bool = true;
			var input = menu.find(".input");
			input.each(function(i, one) {
				if (one.id == "pid") {
					bool = bool && checkPid($(one).val());
				}
				else {
					bool = bool && one.checkValidity();
				}
			});
			if (!bool) {
				toastr.error("内容错误，请核实填写的内容");
				return;
			}
			var name = menu.find(".input[name='name']").val();
			var pid = menu.find(".input[name='pid']").val();
			var year = menu.find(".input[name='year']").val();
			var sex = menu.find(".select-box[name='sex'] option:selected").val();
			var education = menu.find(".select-box[name='education'] option:selected").val();
			var organization = menu.find(".select-box[name='organization'] option:selected").val();
			var profession = menu.find(".select-box[name='profession'] option:selected").val();
			var className = menu.find(".input[name='className'] option:selected").val();
			var state_id = menu.find(".select-box[name='state_id'] option:selected").val();
			enroll.searchEnroll({"profession":profession,"page":currentPage+1,"className":className,"index":index,"name":name,"pid":pid,"year":year,"sex":sex,"education":education,"stateId":state_id,"organization":organization,});
			temp.text(currentPage + 1); // 当前页数加上1页
			refresh(index);
		}
	});
	//更新页数
	var refresh = function(index){
		var table;
		if(index=="全部学员"){
			table = $(".tab-container .tab-content[data-index='全部学员'] .page-list");
		}else if(index=="已分班"){
			table = $(".tab-container .tab-content[data-index='已分班'] .page-list");
		}else if(index=="未分班"){
			table = $(".tab-container .tab-content[data-index='未分班'] .page-list");
		}else if(index=="中退学员"){
			table = $(".tab-container .tab-content[data-index='中退学员'] .page-list");
		}
		var pagetest = $(table).find(".page-test");
		var currentNumber = $(table).find(".currentPage").text();
		var pageNumber = $(table).find(".currentPage").text();
		var maxNumber = $(table).find(".totalPages").text();
		var i = 0 ;
		i = new Number(i);
		currentNumber = new Number(currentNumber);
		pageNumber = new Number(pageNumber);
		maxNumber = new Number(maxNumber);
		if(maxNumber>5){
			if(currentNumber>=3&&currentNumber<maxNumber-1)
				pageNumber = 3;
			else if(currentNumber==maxNumber-1)
				pageNumber=4;
			else if(currentNumber==maxNumber)
				pageNumber = 5;
		}
		pagetest.removeClass("page-active");
		pagetest.removeClass("page-disable");
		if(maxNumber<=5){
			for(;i<5;i++){
				$(pagetest[i]).text(i+1);
				if(i+1>maxNumber)
					$(pagetest[i]).addClass("page-disable");
			}
		}else{
			for(;i<5;i++)
				$(pagetest[i]).text(currentNumber+i+1-pageNumber);
		}
		$(pagetest[pageNumber-1]).addClass("page-active");
	}
	//校验输入是否正确
	$(".tab-container .input").keyup(function() {
		if (this.name == "pid") {
			if (checkPid($(this).val())) {
				$(this).removeClass("error");
			}
			else {
				$(this).addClass("error");
			}
		}else{
			if (this.checkValidity()) {
				$(this).removeClass("error");
			}
			else {
				$(this).addClass("error");
			}
		}
	});
	
	//实时更新查询年份后生成的机构列表
	$(".tab-container .searchMenu .input[name='year']").keyup(function() {
		var date = new Date();
		var now = date.getFullYear();
		if($(this).val()>2000&&$(this).val()<=now){
			orgInit($(this).val());
		}
	});

});