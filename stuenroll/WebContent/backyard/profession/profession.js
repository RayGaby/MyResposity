$(function(){
	if(!checkPermission(["6_4"])){
		return;
	}
	
	var tabContainer=$(".tab-container");
	tabContainer.find(".operation-item[name='classMember']").show();
	if(checkPermission(["6_1"])){
		tabContainer.find(".operation-item[name='add']").show();
	}
	if(checkPermission(["6_2"])){
		tabContainer.find(".operation-item[name='delete']").show();
	}
	if(checkPermission(["6_3"])){
		tabContainer.find(".operation-item[name='modify']").show();
	}
	if(checkPermission(["6_1"])){
		tabContainer.find(".operation-item[name='import']").show();
	}
	if(checkPermission(["6_4"])){
		tabContainer.find(".operation-item[name='export']").show();
	}
	
	/**
	 * profession接口
	 */
	var I_Profession = function(){
		
	}
	
	I_Profession.prototype.searchProfession = function(){
		throw "抽象方法";
	}
	I_Profession.prototype.searchProfessionCount = function(){
		throw "抽象方法";
	}
	I_Profession.prototype.addProfession = function(json){
		throw "抽象方法";
	}
	I_Profession.prototype.modifyProfession = function(json){
		throw "抽象方法";
	}
	I_Profession.prototype.deleteProfession = function(){
		throw "抽象方法";
	}
	
	I_Profession.prototype.showMenu = function($scope,event){
		throw "抽象方法";
	}
	I_Profession.prototype.hideMenu = function(){
		throw "抽象方法";
	}
	I_Profession.prototype.init = function(){
		throw "抽象方法";
	}
	I_Profession.prototype.searchStudent = function(json){
		throw "抽象方法";
	}
	I_Profession.prototype.searchStudentCount = function(count){
		throw "抽象方法";
	}
	I_Profession.prototype.import = function(fileName){
		throw "抽象方法";
	}
	
	/**
	 * 实现类
	 */
	var Profession = function(){
		
	}
	
	Profession.prototype = new I_Profession();
	Profession.prototype.searchProfession = function(json){
		$.ajax({
			"url":"/stuenroll/profession/queryProfession",
			"type":"post",
			"dataType":"json",
			"data":json,
			"async":false, //同步
			"success":function(json){
				
				var data = json.result;
				var table = $(".tab-container .data-table");
				// 清空表格数据
				table.find("tr:gt(0)").remove();
				
				// 获得当前页数
				var currentPage = $(".tab-container  #currentPage").text();
				// 转化成数字类型
				currentPage = new Number(currentPage);
				// 当前页数的行号起始数字
				var start = (currentPage - 1) * 3;
				
				var temp = "";
				for (var i = 0; i < data.length; i++) {
					var one = data[i];
					//console.log(one.profession_id);
					temp += "<tr>";
					temp += "<td><input type='checkbox' name='id' value='" + one.profession_id + "' /></td>"
					temp += "<td>" + (start + i + 1) + "</td>";
					temp += "<td name='profession_name'>" + one.professionName + "</td>";
					temp += "<td name='organization'>" + one.organization + "</td>";
					temp += "<td>" + one.year + "</td>";
					temp += "<td>" + one.class_count + "</td>";
					temp += "<td name='stu_count' class='click'>" + one.student_count + "</td>";
					employed_count = new Number(one.employed_count);
					student_count = new Number(one.student_count);
					if(student_count == 0){
						temp += "<td>" + 0 + "</td>";
					}else{
						var rate = employed_count/student_count;
						//console.log(rate);
						temp += "<td>" + (Math.round(rate*10000)/100).toFixed(0)+'%' + "</td>";
					}
					temp += "</tr>";
					
				}
				table.append(temp);
				stuMember();
				close();
				stuPage();
				
				
			},
			"error":function(){
				toastr.error("系统异常");
			}
		});
	}
	Profession.prototype.searchProfessionCount = function(){
		$.ajax({
			"url" : "/stuenroll/profession/queryProfessionCount",
			"type" : "post",
			"dataType" : "json",
			"async" :false,
			"data" : null,
			"success" : function(json){
				var count = json.result.count; // 总记录数
				var content = $(".tab-container");
				content.find("#totalRows").text(count);
				var totalPages = (count % 3 == 0) ? count /3 : Math.floor(count / 3) + 1;
				content.find("#totalPages").text(totalPages);
			}
		
		});
	}
	Profession.prototype.addProfession = function(json){
		$.ajax({
 			"url" : "/stuenroll/profession/addProfession",
			"type" : "post",
			"dataType" : "json",
			"data" : json,
			"async":false, //同步
			"success" : function(json) {
				if(json.result == -1){
					toastr.warning("只有就业网用户可以添加专业");
					profession.hideMenu();
				}
				else if(json.result == 0){
					toastr.warning("添加的专业的名称已经存在");
					$("#add").find(".keyword").val("");
				}else{
					toastr.success("添加专业操作成功，添加了"+json.result+"条记录");
					profession.hideMenu();
				}
			},
			"error" : function() {
				toastr.error("系统异常");
			}
 		});
	}
	Profession.prototype.modifyProfession = function(json){
		$.ajax({
 			"url" : "/stuenroll/profession/modifyProfession",
			"type" : "post",
			"dataType" : "json",
			"data" : json,
			"async":false, //同步
			"success" : function(json) {
				var content = $(".tab-container .data-table");
				var checkbox = content.find("*[name='id']:checked");//被选中的复选框
				if(json.result == -1){
					toastr.warning("只有就业网用户可以修改专业");
					profession.hideMenu();
					checkbox.attr("checked",false);
					var temp = $(".tab-container #currentPage");
					var currentPage = temp.text();
					
					profession.searchProfession({
						"page" : currentPage
					});
				}
				else if(json.result == 0){
					toastr.warning("修改的专业的名称已经存在");
					$("#modify").find(".keyword").val("");
				}else{
					toastr.success("修改专业操作成功");
					profession.hideMenu();
					checkbox.attr("checked",false);
					var temp = $(".tab-container #currentPage");
					var currentPage = temp.text();
					setTimeout(function() {
						profession.searchProfession({
							"page" : currentPage
						});
					}, 500);
					
				}
			},
			"error" : function() {
				toastr.error("系统异常");
			}
 		});
	}
	Profession.prototype.deleteProfession = function(){
		//获取被选中的记录
		var content = $(".tab-container .data-table");
		var checkbox = content.find("*[name='id']:checked");//被选中的复选框
		
		if(checkbox.length == 0){
			toastr.warning("请选中需要删除的专业");
		}
		else{
			
			var id = [];//定义数组
			for(var i = 0; i < checkbox.length; i++){
				var organization = checkbox.parents("tr").find("*[name='organization']").text();
				var stu_count = checkbox.parents("tr").find("*[name='stu_count']").text();
				if(stu_count != 0){
					toastr.warning("记录关联学生人数，不能删除");
					return;
				}
				else if(organization != 0){
					toastr.warning("记录关联机构，不能删除");
					return;
				}
			}
			var bool = confirm("是否删除选中的记录？");
			if(bool == false){
				return;
			}
			
			for(var i = 0; i < checkbox.length; i++){
				id.push($(checkbox[i]).val());
			}
			$.ajax({
				"url" : "/stuenroll/profession/deleteProfession",
				"type" : "post",
				"dataType" : "json",
				"traditional" : true,
				"async" : false,
				"data" : {
					"id" : id
				},
				"success" : function(json){
					if(json.deleteRows == -1){
						toastr.warning("只有就业网用户可以删除专业");
						classinfo.hideMenu();
					}
					else{
						toastr.success("删除了" + json.deleteRows + "条记录");
					}
					
				},
				"error" : function(){
					toastr.error("系统异常");
				}
			});
		}
	}

	Profession.prototype.init = function() {
		//Menu之外任意位置点击鼠标，Menu消失
		var obj = this;
		$(document).click(function() {
			obj.hideMenu();
		});

		//Menu内部点击鼠标阻止Menu消失
		$(".menu").click(function(event) {
			event.stopPropagation();
		});
	}
	Profession.prototype.showMenu = function($scope, event) {
		var $obj = $(event.target).find(".menu");
		$(".menu").fadeOut(); //隐藏所有菜单
		$obj.parent().siblings().removeClass("item-active"); //取消所有选项
		$obj.parent().addClass("item-active"); //选项被选中
		$obj.fadeIn(); //菜单出现
		event.stopPropagation(); //阻止点击事件传播
	}
	Profession.prototype.hideMenu = function() {
		var $obj = $(".menu");
		$obj.fadeOut();
		$obj.parent().removeClass("item-active");
		DropDown.closeDropDown();

	}
	
	//查看班级中的学生名单
	Profession.prototype.searchStudent = function(json){
		$.ajax({
			"url" : "/stuenroll/profession/queryProfessionMember",
			"type" : "post",
			"dataType" : "json",
			"data" : json,
			"success" : function(json){
				var data = json.result;
				
				var table = $(".tab-container .stu-table");
				
				table.find("tr:gt(0)").remove();
				
				//获得当前页数
				var currentPage = $(".tab-container span[name='stu-currentPage']").text();
				//转化为数字类型

				currentPage = new Number(currentPage);
				//当前页数的行号起始数字
				var start = (currentPage -1 ) * 10;
				var temp = "";
				
				for(var i = 0; i < data.length; i++){
					var one = data[i];

					temp += "<tr>";
					temp += "<td>" + (start + i + 1) + "</td>";
					temp += "<td>" + one.name + "</td>";
					temp += "<td>" + one.sex + "</td>";
					temp += "<td>" + one.tel +"</td>";
					temp += "<td>" + one.pid + "</td>";
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
	
	Profession.prototype.searchStudentCount = function(count){
		var content = $(".stu-menu .stu-page-list")
	
		var totalPages = (count % 10 == 0) ? count / 10 : Math.floor(count/10) + 1;
		content.find("*[name='stu-totalPages']").text(totalPages);
	}
	
	Profession.prototype.import = function(fileName) {
		$.ajaxFileUpload({
 			url : '/stuenroll/profession/importFile',   //提交的路径
 			secureuri : false, // 是否启用安全提交，默认为false
 			fileElementId : 'file', // file控件id
 			dataType : 'json',
 			data : {
 				fileName : fileName   //传递参数，用于解析出文件名
 			}, // 键:值，传递文件名
 			success : function(json) {
 				var count = json.result;
 				if (count == -1) {
 					toastr.warning("只有就业网用户可以导入专业");
 					profession.hideMenu();
 				} 
 				else if(count == -2){
 					toastr.warning("导入失败");
 					profession.hideMenu();
 				}
 				else if(count == 0){
 					toastr.success("导入的专业名字在记录中已经存在");
 					profession.hideMenu();
 				}
 				else{
 					toastr.success("成功导入"+count+"条记录");
 					profession.hideMenu();
 				}
 			},
 			error : function(json) {
 				toastr.error("系统异常");
 				profession.hideMenu();
 			}
 		});

	}
	
	function factory(key){
		if(key == "Profession"){
			return new Profession();
		}
	}
	
	/**
	 * 判断输入的内容是否符合要求
	 * @param {Object} $obj
	 */
	function checkError($obj) {
		// 查询不合法的文本框输入
		var element = $obj.find(".keyword");
		var count = 0;
		for (var i = 0; i < element.length; i++) {
			if (element.eq(i).hasClass("error")) {
				count++;
			}
		}
		if (count > 0)
			return false;
		else
			return true;
	}
	
	/**
	 * 当前页数的变化
	 */
	function nextPage(){
		var pageTab = $(".tab-container .page-list");
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
	
	function stuMember(){
		$(".data-table tr td[name='stu_count']").unbind("click");
		$(".data-table *[name='stu_count']").click(function(){
			var id = $(this).parents("tr").find("*[name='id']").val();
			profession.searchStudent({
				"id" : id
			});
			$(".stu-menu").css("display","block");
			$(".stu-menu .stu-page-list *[name='stu-currentPage']").text(1);
			var count = $(this).text();
			$(".stu-menu .stu-page-list *[name='stu-totalRows']").text(count);
			profession.searchStudentCount(count);
			$(this).addClass("stu");//添加类选择器，等到翻页的时候查找专业id
		});
	}
	
	
	//关闭班级名单的关闭按钮
	function close(){
		$(".tab-container .stu-menu .stu-menu-btn").click(function(){
			$(this).parents(".stu-menu").css("display","none");
			$(".data-table tr td[name='stu_count']").removeClass("stu");
		});
	}
	
 	/**
	 * 班级成员翻页
	 */
	function stuPage(){
		var ele = $(".tab-container");
		ele.find("*[name='stu-prevBtn']").unbind("click");
		ele.find("*[name='stu-prevBtn']").click(function(){
			var temp = $(this).parents(".stu-page-list").find("*[name='stu-currentPage']");
			var currentPage = temp.text();
			
			currentPage = new Number(currentPage);
			if(currentPage > 1){
				var temp = $(this).parents(".stu-page-list").find("*[name='stu-currentPage']");
				temp.text(currentPage - 1);
				var id = $(".data-table tr .stu").parents("tr").find("*[name='id']").val();
				
				profession.searchStudent({
					"page" : currentPage - 1,
					"id" : id
				});
			}
		});
		ele.find("*[name='stu-nextBtn']").unbind("click");
		ele.find("*[name='stu-nextBtn']").click(function(){
			var temp = $(this).parents(".stu-page-list").find("*[name='stu-currentPage']");
			var currentPage = temp.text();
	
			var totalPages = $(this).parents(".stu-page-list").find("*[name='stu-totalPages']").text();
			
			currentPage = new Number(currentPage);
			totalPages = new Number(totalPages);
			if (currentPage < totalPages) {
				var temp = $(this).parents(".stu-page-list").find("*[name='stu-currentPage']");
				temp.text(currentPage + 1); // 当前页数加上1页
				var id = $(".data-table tr .stu").parents("tr").find("*[name='id']").val();
				
				profession.searchStudent({
					"page" : currentPage + 1,
					"id" : id
				});
				//temp.text(currentPage + 1); // 当前页数加上1页
			}
		});
	}
	
	/**
	 * 判断导出什么文件
	 */
	function checkAction(value){
		if(value==0){
			document.exportForm.action="/stuenroll/profession/exportExcel";
		}else if(value==1){
			document.exportForm.action="/stuenroll/profession/exportXml";
		}else{
			document.exportForm.action="/stuenroll/profession/exportCsv";
		}
		document.exportForm.submit();
	}
	
	var profession = factory("Profession");
	
	profession.init();
	profession.searchProfession();
	profession.searchProfessionCount();
	nextPage();
	
	var element = $(".tab-container");
	/**
	 * 翻页
	 */
	element.find("*[name='prevBtn']").unbind("click");
	element.find("*[name='prevBtn']").click(function() {
		var temp = $(this).parents(".page-list").find("#currentPage");
		var currentPage = temp.text();
		currentPage = new Number(currentPage);
		if (currentPage > 1) {
			temp.text(currentPage - 1); // 当前页数减去1页
			profession.searchProfession({
				"page" : currentPage - 1
			});
			nextPage();
		}

	});
	element.find("*[name='nextBtn']").unbind("click");
	element.find("*[name='nextBtn']").click(function() {
		var temp = $(this).parents(".page-list").find("#currentPage");
		var currentPage = temp.text();

		var totalPages = $(this).parents(".page-list").find("#totalPages").text();

		currentPage = new Number(currentPage);
		totalPages = new Number(totalPages);

		if (currentPage < totalPages) {
			temp.text(currentPage + 1); // 当前页数加上1页
			profession.searchProfession({
				"page" : currentPage + 1
			});
			nextPage();
		}
	});
	/**
	 * 翻页结束
	 */
	
	/**
	 * 添加操作
	 */
	element.find("*[name='add']").click(function() {
		$(".stu-menu").css("display","none");
		$("#add").find(".keyword").val("");
		var $scope = $("#add");
		profession.showMenu($scope, event);
	});
	
	/**
	 * 修改操作
	 */
	element.find("*[name='modify']").click(function() {
		//获取被选中的记录
		$(".stu-menu").css("display","none");
		var content = $(".tab-container .data-table");
		var checkbox = content.find("*[name='id']:checked");//被选中的复选框
		
		if(checkbox.length  > 1){
			toastr.warning("只可以选中一条记录，请重新选择");
			checkbox.attr('checked', false);
		}
		else if(checkbox.length == 0){
			toastr.warning("请选择一条记录");
		}
		else{
			
			var organization = checkbox.parents("tr").find("*[name='organization']").text();
			var stu_count = checkbox.parents("tr").find("*[name='stu_count']").text();
			if(stu_count != 0){
				toastr.warning("记录关联学生人数，不能修改");
				checkbox.attr('checked', false);
				return;
			}
			if(organization != 0){
				toastr.warning("记录关联机构，不能修改");
				checkbox.attr('checked', false);
				return;
			}
			
			var $scope = $("#modify");
			profession.showMenu($scope, event);
			var id = checkbox.val();
			var profession_name = checkbox.parents("tr").find("[name='profession_name']").text();
			$("#modify").find(".keyword").val(profession_name);
			
		}
	});
	
	/**
	 * 点击导入
	 */
	element.find("*[name='import']").click(function() {
		$(".stu-menu").css("display","none");
		$("#import").find(".file").val("");
		var $scope = $("#import");
		profession.showMenu($scope, event);
	});
	
	/**
	 * 点击导出
	 */
	element.find("*[name='export']").click(function() {
		$(".stu-menu").css("display","none");
		var $scope = $("#export");
		profession.showMenu($scope, event);
	});
	
	//键盘弹起事件
	$(".menu .keyword").keyup(function(){
		if (this.checkValidity()) {
			$(this).removeClass("error");
		}
		else {
			$(this).addClass("error");
		}
	})
	
	/**
	 * 删除操作
	 */
	element.find("*[name='delete']").click(function() {
		$(".stu-menu").css("display","none");
		// 先删除
		profession.deleteProfession();
		// 再查询
		setTimeout(function() {
			profession.searchProfessionCount();
			var totalPages = $("#totalPages").text();
			var currentPage = $("#currentPage").text();
			totalPages = new Number(totalPages);
			currentPage = new Number(currentPage);
			if (currentPage > totalPages) {
				currentPage = totalPages;
			}

			$("#currentPage").text(currentPage); // 更新当前页数

			profession.searchProfession({
				"page" : currentPage
			});
			nextPage();
		}, 500);
	});
	
	/**
	 * 添加保存
	 */
	$("#add").find(".save").click(function(){
		var error = checkError($("#add"));
		if(!error){
			toastr.warning("请正确输入");
		}
		else{
			var name= $("#add").find("*[name='name']").val();
			
			if(name != ""){
				profession.addProfession({
					"name" : name
				});
			}
			else{
				toastr.warning("请填写完整信息");
			}
			setTimeout(function() {
				var temp = $("#currentPage");
				var currentPage = temp.text();
				
				profession.searchProfession({
					"page" : currentPage
				});
				profession.searchProfessionCount();
				nextPage();
			}, 500);
			
		}
	});
	
	/**
	 * 修改保存
	 */
	$("#modify").find(".save").unbind("click");
	$("#modify").find(".save").click(function(){
		var error = checkError($("#modify"));
		if(!error){
			toastr.warning("请正确输入");
		}
		else{
			var name = $("#modify").find(".keyword").val();

			var content = $(".tab-container .data-table");
			var checkbox = content.find("*[name='id']:checked");//被选中的复选框
			var id = checkbox.val();
			
			//判断名称是否修改
			var oldName = checkbox.parents("tr").find("*[name='profession_name']").text();
			if(oldName == name){
				profession.hideMenu();
				checkbox.attr("checked",false);
				return;
			}
			if(name != ""){
				profession.modifyProfession({
					"name" : name,
					"id" : id
				});
			}
			else{
				toastr.warning("请正确填写专业名称");
			}
			
		}
	});
	/**
	 * 点击导入
	 */
	$("#import-file").click(function(){
		var currentPage = $(".page-list").find("#currentPage").text();
 		if($("#file").val()!=""){
 			profession.import($('#file').val());   //函数参数为上传的文件的本机地址
 			$("#file").val("");
 			setTimeout(function() {
 				profession.hideMenu();
 				profession.searchProfession({
					"page":currentPage
				});
				profession.searchProfessionCount();
				nextPage();
 			}, 6000);
 			
 		}else{
 			alert("请选择要导入的文件");
 		}
	})
	/**
	 * 点击导出文件的按钮
	 */
	$("#export .save").click(function(){
		var value = $(this).data("index");
		checkAction(value);
		profession.hideMenu();
	});
	
	$(".page-list .page-num").click(function(){
		var unclick = $(this).parents(".page-list").find(".page-disable").eq(0).text();
		var page = $(this).text();
		if(page < unclick || unclick == ""){
			$("#currentPage").text(page);
			$(".page-num").removeClass("page-active");
			$(this).addClass("page-active");
			profession.searchProfession({
				"page" : page
			});
		}
		
	});
	
	
});
