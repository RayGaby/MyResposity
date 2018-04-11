$(function(){
	if (!checkPermission([ "8_4" ])) {
		return;
	}
	
	var tabContainer=$(".tab-container");
	if(checkPermission(["8_4"])){
		tabContainer.find(".operation-item[name='search']").show();
	}
	if(checkPermission(["8_2"])){
		tabContainer.find(".operation-item[name='delete']").show();
	}
	if(checkPermission(["8_1"])){
		tabContainer.find(".operation-item[name='add']").show();
	}
	if(checkPermission(["8_3"])){
		tabContainer.find(".operation-item[name='modify']").show();
	}
	if(checkPermission(["8_10"])){
		tabContainer.find(".operation-item[name='archive']").show();
	}

	classinfo.organization();
	var paramMap = getIframeParams("page");
	var index = paramMap.index;
	if(index == 1){
		tab.showTab("班级列表");
		classinfo.searchClassinfo();
		classinfo.searchClassinfoCount();
	}
	else{
		tab.showTab("电话回访");
		var currentPage = paramMap.page;
		var classInfo = paramMap.classInfo;
		var year = paramMap.year;
		var organization = paramMap.organization;
		var profession = paramMap.profession;
		var operation = paramMap.operation;
		
		if(operation == 1){
			var stuId = paramMap.stuId;
			var classinfoId = paramMap.classinfoId;
			classinfo.searchStuName({
				"stuId" : stuId,
				"classinfoId" : classinfoId
			});
		}
		
		//将查询条件添加到查询按钮的data中
		$(".tab-content[data-index='电话回访'] .menu-btn[value='查询']").data("name",classInfo);
	    $(".tab-content[data-index='电话回访'] .menu-btn[value='查询']").data("year",year);
	    $(".tab-content[data-index='电话回访'] .menu-btn[value='查询']").data("organization",organization);
	    $(".tab-content[data-index='电话回访'] .menu-btn[value='查询']").data("profession",profession);
		
	    var classi = $(".tab-content[data-index='电话回访'] .menu-btn[value='查询']").data("name");
	    var yt =  $(".tab-content[data-index='电话回访'] .menu-btn[value='查询']").data("year");
	    var prof = $(".tab-content[data-index='电话回访'] .menu-btn[value='查询']").data("organization");
	    var ort = $(".tab-content[data-index='电话回访'] .menu-btn[value='查询']").data("profession");
	    
	    console.log(classi);
	    console.log(yt);
	    console.log(prof);
	    console.log(ort);
	    		
		$(".tab-container .content-active").find("*[name=currentPage]").text(currentPage);
		
		classinfo.searchTelVisit({
			"page" : currentPage,
			"classinfo" : classInfo,
			"year" : year,
			"organization" : organization,
			"profession" : profession
		});
		classinfo.searchClassinfoCount({
			"classinfo" : classInfo,
			"year" : year,
			"organization" : organization,
			"profession" : profession
		});
		
	}
	DropDown.initAll()
	classinfo.init();
	nextPage();
	page();
	search();
	dropdown();
	menuSearch();
	archive();
	choosePage();
	//回到顶部
	$(window.parent.document).find("body").animate({scrollTop:0},5);
	
	$(".tab-list .tab-item").click(function() {
		var temp = $(this).data("index");
		tab.showTab(temp);
		page();
		search();
		dropdown();
		menuSearch();
		archive();
		choosePage();
		$(".content-active").find(".stu-menu").css("display","none");
		
		$(".content-active .menu-btn[value='查询']").data("name","");
		$(".content-active .menu-btn[value='查询']").data("year","");
		$(".content-active .menu-btn[value='查询']").data("organization","");
		$(".content-active .menu-btn[value='查询']").data("profession","");
		// 切换选项卡，重新查询数据
		if(temp == "班级列表"){
			var currentPage = $(".tab-content[data-index='班级列表']").find("*[name='currentPage']");
			currentPage.text(1);
			
			classinfo.searchClassinfo({
				"page" : 1
			});
			classinfo.searchClassinfoCount({
				"page" : 1
			});
			
		}
		else if(temp == "电话回访"){
			var currentPage = $(".tab-content[data-index='电话回访']").find("*[name='currentPage']");
			currentPage.text(1);

			
			classinfo.searchTelVisit({
				"page" : 1
			});
			classinfo.searchClassinfoCount({
				"page" : 1
			});
			tel();
		}
		else{
			var currentPage = $(".tab-content[data-index='"+temp+"']").find("*[name='currentPage']");
			currentPage.text(1);
			classinfo.searchClassinfo({
				"state" : temp,
				"page" : 1
			});
			classinfo.searchClassinfoCount({
				"state" : temp,
				"page" : 1
			});
		}
		
		nextPage();

	});
   
  
	//点击添加
	$(".tab-container").find("*[name='add']").click(function() {
		$(".content-active").find(".stu-menu").css("display","none");
		$(this).find(".keyword").val("");
		$(this).find(".value").text("-选择-");
		DropDown.clearAll();
		var $scope = $("#addMenu");
		classinfo.showMenu($scope, event);
	});
	
	//点击修改
	$(".tab-container").find("*[name='modify']").click(function() {
		$(".content-active").find(".stu-menu").css("display","none");
		//获取被选中的记录
		var content = $(".tab-container .tab-content[data-index='班级列表']");
		var checkbox = content.find("*[name='id']:checked");//被选中的复选框
		
		if(checkbox.length  > 1){
			toastr.warning("只可以选中一条记录，请重新选择");
			checkbox.attr('checked', false);
		}
		else if(checkbox.length == 0){
			toastr.warning("请选择一条记录");
		}
		else{
			
			var stu_count = checkbox.parents("tr").find("*[name=stu_count]").text();
			if(stu_count != 0){
				toastr.warning("记录关联学生人数，不能修改");
				checkbox.attr('checked', false);
				return;
			}
			
			var $scope = $("#modifyMenu");
			classinfo.showMenu($scope, event);
			var id = checkbox.val();
			var class_name = checkbox.parents("tr").find("[name='name']").text();
			content.find(".condition *[name='class-id']").val(class_name);
			
			var year = checkbox.parents("tr").find("[name='year']").text();
			content.find(".condition *[name='year']").val(year);
			
			classinfo.dropDownOranizationAboutModify({
				"classinfo" : id
			});
			classinfo.dropDownProfessionAboutModify({
				"classinfo" : id
			});
			
			
		}
		
		
	});
	
	
	
	//点击删除
	$(".tab-container *[name='delete']").click(function(){
		classinfo.deleteById();
		
		var classInfo =$(".content-active .menu-btn[value='查询']").data("name");
		var year = $(".content-active .menu-btn[value='查询']").data("year");
		var organization= $(".content-active .menu-btn[value='查询']").data("organization");
		var profession= $(".content-active .menu-btn[value='查询']").data("profession");
		
		classinfo.searchClassinfoCount({
			"classinfo" : classInfo,
			"year" : year,
			"organization" : organization,
			"profession" : profession,
		});
		
		var totalPages = $(this).parents(".tab-container .content-active").find("*[name='totalPages']").text();
		var currentPage = $(this).parents(".tab-container .content-active").find("*[name='currentPage']").text();
		
		totalPages = new Number(totalPages);
		currentPage = new Number(currentPage);
		
		if(currentPage > totalPages){
			currentPage = totalPages;
		}
		
		$(this).parents(".tab-container").find("*[name='currentPage']").text(currentPage);
		classinfo.searchClassinfo({
			"classinfo" : classInfo,
			"year" : year,
			"organization" : organization,
			"profession" : profession,
			"page" : currentPage
		});
		nextPage();
	});

	//输入年份键盘弹起事件
	$(".tab-content[data-index='班级列表'] .operation-item .keyword").keyup(function(){
		if (this.checkValidity()) {
			$(this).removeClass("error");
		}
		else {
			$(this).addClass("error");
		}
	});
	
	//对年届的变化
	$("*[name='year']").keyup(function(){
		if (this.checkValidity()) {
			$(this).removeClass("error");
			$(".value").text("-选择-");
			DropDown.clearAll();
		}
		else {
			$(this).addClass("error");
		}
	});
	//清空填写的内容
	$(".tab-content .menu-btn[value='清除']").click(function(){
		$(".keyword").val("");
		$(".value").text("-选择-");
		DropDown.clearAll();
		
	});
	
	/**
	 * 查询结束
	 */
	/**
	 * 添加
	 */
	$("#addMenu .menu-btn[value='保存']").click(function(){
		var error = checkError($("#addMenu"));
		if(!error){
			toastr.warning("请正确输入");
		}
		else{
			var classInfo = $("#addMenu").find("*[name='class-id']").val();
			var year = $("#addMenu").find("*[name='year']").val();
			var organization= $("#addMenu").find("*[name='organization']").find(".dropdown-item-active").data("id");
			var profession= $("#addMenu").find("*[name='profession']").find(".dropdown-item-active").data("id");
			if(classInfo != "" && year != "" && organization != null && profession != null){
				classinfo.addClassinfo({
					"profession" : profession,
					"organization" : organization,
					"classinfo" : classInfo,
					"year" : year
				});
				
			}
			else{
				toastr.warning("请填写完整信息");
			}
			var temp = $(".tab-container .tab-content[data-index='班级列表'] *[name='prevBtn']").parents(".page-list").find("*[name='currentPage']");
			var currentPage = temp.text();
			
			var name =$(".content-active .menu-btn[value='查询']").data("name");
			var searchYear = $(".content-active .menu-btn[value='查询']").data("year");
			var searchOrganization= $(".content-active .menu-btn[value='查询']").data("organization");
			var searchProfession= $(".content-active .menu-btn[value='查询']").data("profession");
			setTimeout(function() {
				classinfo.searchClassinfo({
					"profession" : searchProfession,
					"organization" : searchOrganization,
					"classinfo" : name,
					"year" : searchYear,
					"page" : currentPage
				});
				classinfo.searchClassinfoCount({
					"profession" : searchProfession,
					"organization" : searchOrganization,
					"classinfo" : name,
					"year" : searchYear
				});
				nextPage();
			}, 500);
			
		}
		
	});
	
	$("#addMenu").find("*[name='organization']").click(function(){
		//获取已选择的班级，专业，年届
		$(this).find(".dropdown-list").empty();
		var classInfo = $("#addMenu").find("*[name='class-id']").val();
		//alert(classInfo)
		var year = $("#addMenu").find("*[name='year']").val();
		//alert(year)
		//console.log(classInfo)
		if(year != "" && classInfo != ""){
			var profession= $("#addMenu").find("*[name='profession']").find(".dropdown-item-active").data("id");
			classinfo.dropDownOrganizationAboutAdd({
				"year" : year,
				"profession" : profession
			});
		}
		else{
			toastr.warning("请填写班级编号和年届");
		}
		
	});
	
	$("#addMenu").find("*[name='profession']").click(function(){
		//获取已选择的机构，班级，年届
		$(this).find(".dropdown-list").empty();
		var classInfo = $("#addMenu").find("*[name='class-id']").val();
		var year = $("#addMenu").find("*[name='year']").val();
		
		if(year != "" && classInfo != ""){
			var organization= $("#addMenu").find("*[name='organization']").find(".dropdown-item-active").data("id");
			classinfo.dropDownProfessionAboutAdd({
				"year" : year,
				"organization" : organization
			});
		}
		else{
			toastr.warning("请填写班级编号和年届");
		}
		
	});
	
	/**
	 * 添加结束
	 */
	/**
	 * 修改
	 */
	$("#modifyMenu").find("*[name='organization']").click(function(){
		//获取已选择的班级，专业，年届
		$(this).find(".dropdown-list").empty();
		//$("#modifyMenu *[name='profession']").find(".dropdown-list").empty();
		var classInfo = $("#modifyMenu").find("*[name='class-id']").val();
		//alert(classInfo)
		var year = $("#modifyMenu").find("*[name='year']").val();
		//alert(year)
		//console.log(classInfo)
		if(year != "" && classInfo != ""){
			var profession= $("#modifyMenu").find("*[name='profession']").find(".dropdown-item-active").data("id");
			classinfo.dropDownOrganizationAboutAdd({
				"year" : year,
				"profession" : profession
			});
		}
		else{
			toastr.warning("请填写班级编号和年届");
		}
		
	});
	
	$("#modifyMenu").find("*[name='profession']").unbind("click");
	$("#modifyMenu").find("*[name='profession']").click(function(){
		alert(1)
		//获取已选择的班级，专业，年届
		$(this).find(".dropdown-list").empty();
		var classInfo = $("#modifyMenu").find("*[name='class-id']").val();
		//alert(classInfo)
		var year = $("#modifyMenu").find("*[name='year']").val();
		//alert(year)
		//console.log(classInfo)
		if(year != "" && classInfo != ""){
			var organization= $("#modifyMenu").find("*[name='organization']").find(".dropdown-item-active").data("id");
			classinfo.dropDownProfessionAboutAdd({
				"year" : year,
				"organization" : organization
			});
		}
		else{
			toastr.warning("请填写班级编号和年届");
		}
		
	});
	
	/**
	 * 修改保存
	 */
	$("#modifyMenu .menu-btn[value='保存']").click(function(){
		var error = checkError($("#addMenu"));
		if(!error){
			toastr.warning("请正确输入");
		}
		else{
			var classInfo = $("#modifyMenu").find("*[name='class-id']").val();
			
			var year = $("#modifyMenu").find("*[name='year']").val();
			
			var organization= $("#modifyMenu").find("*[name='organization']").find(".dropdown-item-active").data("id");
			var profession= $("#modifyMenu").find("*[name='profession']").find(".dropdown-item-active").data("id");
			//
			var content = $(".tab-container .tab-content[data-index='班级列表']");
			var checkbox = content.find("*[name='id']:checked");//被选中的复选框
			var id = checkbox.val();
			
			//判断是否正确
			var ifEqual = "";
			var oldName = checkbox.parents("tr").find("*[name='name']").text();
			if(oldName == classInfo){
				ifEqual = true;
			}
			else{
				ifEqual = false;
			}
			
			if(classInfo != "" && year != "" && organization != null && profession != null){
				//alert(124)
				classinfo.modifyClassinfo({
					"id" : id,
					"profession" : profession,
					"organization" : organization,
					"classinfo" : classInfo,
					"year" : year,
					"ifEqual" : ifEqual
				});
				checkbox.attr("checked",false);
			}
			else{
				toastr.warning("请填写完整信息");
			}
			var temp = $(".tab-container .tab-content[data-index='班级列表'] *[name='prevBtn']").parents(".page-list").find("*[name='currentPage']");
			var currentPage = temp.text();
			
			var name =$(".content-active .menu-btn[value='查询']").data("name");
			var searchYear = $(".content-active .menu-btn[value='查询']").data("year");
			var searchOrganization= $(".content-active .menu-btn[value='查询']").data("organization");
			var searchProfession= $(".content-active .menu-btn[value='查询']").data("profession");
			
			classinfo.searchClassinfo({
				"profession" : searchProfession,
				"organization" : searchOrganization,
				"classinfo" : name,
				"year" : searchYear,
				"page" : currentPage
			});

			
			
		}
	});
	
	
	
	
	
});