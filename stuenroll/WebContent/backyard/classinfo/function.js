  	/**
    	 * 翻页
    	 */
function factory(key) {
		if (key == "Tab") {
			return new Tab();
		}
		if(key == "Classinfo"){
			return new Classinfo();
		}
	}


	var tab = factory("Tab");
	var classinfo = factory("Classinfo");

	function page(){
		var ele = $(".tab-container .content-active");
		ele.find("*[name='prevBtn']").unbind("click");
    	ele.find("*[name='prevBtn']").click(function(){
    		var temp = $(this).parents(".page-list").find("*[name='currentPage']");
    		var currentPage = temp.text();
    		
    		currentPage = new Number(currentPage);
    		if(currentPage > 1){
    			var temp = $(this).parents(".page-list").find("*[name='currentPage']");
    			temp.text(currentPage - 1);
    			var classInfo =$(".content-active .menu-btn[value='查询']").data("name");
    			var year = $(".content-active .menu-btn[value='查询']").data("year");
    			var organization= $(".content-active .menu-btn[value='查询']").data("organization");
    			var profession= $(".content-active .menu-btn[value='查询']").data("profession");
    			nextPage();
    			//classinfo.hideMenu();
    			var word = $(".tab-list .tab-active").data("index");
    			if(word == "班级列表"){
    				classinfo.searchClassinfo({
    					"page" : currentPage - 1,
    					"profession" : profession,
    					"organization" : organization,
    					"classinfo" : classInfo,
    					"year" : year
    				});
    				classinfo.searchClassinfoCount({
    					"profession" : profession,
    					"organization" : organization,
    					"classinfo" : classInfo,
    					"year" : year
    				});
    			}
    			else if(word == "电话回访"){
    				classinfo.searchTelVisit({
    					"page" : currentPage - 1,
    					"profession" : profession,
    					"organization" : organization,
    					"classinfo" : classInfo,
    					"year" : year
    				});
    				classinfo.searchClassinfoCount({
    					"profession" : profession,
    					"organization" : organization,
    					"classinfo" : classInfo,
    					"year" : year
    				});
    			}
    			else{
    				classinfo.searchClassinfo({
    					"state" : word,
    					"page" : currentPage - 1,
    					"profession" : profession,
    					"organization" : organization,
    					"classinfo" : classInfo,
    					"year" : year
    				});
    				classinfo.searchClassinfoCount({
    					"state" : word,
    					"page" : currentPage - 1,
    					"profession" : profession,
    					"organization" : organization,
    					"classinfo" : classInfo,
    					"year" : year
    				});
    			}
    			//temp.text(currentPage - 1); // 当前页数加上1页
    			
    		}
    	});
    	ele.find("*[name='nextBtn']").unbind("click");
    	ele.find("*[name='nextBtn']").click(function(){
    		
    		var temp = $(this).parents(".page-list").find("*[name='currentPage']");
    		var currentPage = temp.text();

    		var totalPages = $(this).parents(".page-list").find("*[name='totalPages']").text();
    		
    		currentPage = new Number(currentPage);
    		totalPages = new Number(totalPages);
    		if (currentPage < totalPages) {
    			
    			// 请求Ajax并更新数据
    			var temp = $(this).parents(".page-list").find("*[name='currentPage']");
    			temp.text(currentPage + 1); // 当前页数加上1页
    			var classInfo = $(".content-active .menu-btn[value='查询']").data("name");
    			var year = $(".content-active .menu-btn[value='查询']").data("year");
    			var organization= $(".content-active .menu-btn[value='查询']").data("organization");
    			var profession= $(".content-active .menu-btn[value='查询']").data("profession");
    			nextPage();
    			//classinfo.hideMenu();
    			var word = $(".tab-list .tab-active").data("index");
    			if(word == "班级列表"){
    				classinfo.searchClassinfo({
    					"page" : currentPage + 1,
    					"profession" : profession,
    					"organization" : organization,
    					"classinfo" : classInfo,
    					"year" : year
    				});
    				classinfo.searchClassinfoCount({
    					"profession" : profession,
    					"organization" : organization,
    					"classinfo" : classInfo,
    					"year" : year
    				});
    			}
    			else if(word == "电话回访"){
    				classinfo.searchTelVisit({
    					"page" : currentPage + 1,
    					"profession" : profession,
    					"organization" : organization,
    					"classinfo" : classInfo,
    					"year" : year
    				});
    				classinfo.searchClassinfoCount({
    					"profession" : profession,
    					"organization" : organization,
    					"classinfo" : classInfo,
    					"year" : year
    				});
    			
    			}
    			else{
    				classinfo.searchClassinfo({
    					"state" : word,
    					"page" : currentPage + 1,
    					"profession" : profession,
    					"organization" : organization,
    					"classinfo" : classInfo,
    					"year" : year
    				});
    				classinfo.searchClassinfoCount({
    					"state" : word,
    					"page" : currentPage + 1,
    					"profession" : profession,
    					"organization" : organization,
    					"classinfo" : classInfo,
    					"year" : year
    				});
    			}
    			//temp.text(currentPage + 1); // 当前页数加上1页
    		}
    	});
	}
	
	//点击查询
	
	function search() {
		$(".tab-container .content-active").find("*[name='search']").unbind("click");
		$(".tab-container .content-active").find("*[name='search']").click(function() {
			$(".content-active").find(".stu-menu").css("display","none");
			$(".content-active #searchMenu").find(".value").text("-选择-");
			$(".content-active #searchMenu .dropdown-list").empty();
			DropDown.clearAll();
			//$("#searchMenu").unbind("click");
			var $scope = $(".content-active #searchMenu");
			
			var e = window.event||event;
				
			classinfo.showMenu($scope, e);
			});
	}
	
	//点击班级归档
	function archive(){
		$(".tab-container .content-active *[name='archive']").click(function(){
			classinfo.archiveClassinfo();
			
			var currentPage = $(this).parents(".tab-container .content-active").find("*[name='currentPage']").text();
			
			currentPage = new Number(currentPage);

			$(this).parents(".tab-container").find("span[name='currentPage']").text(currentPage);
			var classInfo =$(".content-active .menu-btn[value='查询']").data("name");
			var year = $(".content-active .menu-btn[value='查询']").data("year");
			var organization= $(".content-active .menu-btn[value='查询']").data("organization");
			var profession= $(".content-active .menu-btn[value='查询']").data("profession");
			
			var word = $(".tab-list .tab-active").data("index");
			
			setTimeout(function() {
				if(word == "班级列表"){
					classinfo.searchClassinfo({
						"profession" : profession,
						"organization" : organization,
						"classinfo" : classInfo,
						"year" : year,
						"page" : currentPage
					});
					classinfo.searchClassinfoCount({
						"profession" : profession,
						"organization" : organization,
						"classinfo" : classInfo,
						"year" : year
					});
				}
				else if(word == "未归档"){
					classinfo.searchClassinfo({
						"profession" : profession,
						"organization" : organization,
						"classinfo" : classInfo,
						"year" : year,
						"state" : word,
						"page" : currentPage
					});
					classinfo.searchClassinfoCount({
						"state" : word,
						"profession" : profession,
						"organization" : organization,
						"classinfo" : classInfo,
						"year" : year
					});
				}
			}, 1000);
			
			
			
		});
	}
	
	/**
	 * 查询下拉
	 */
	function dropdown(){
		$(".content-active #searchMenu *[name='class-id']").click(function(){
			//获取已选择的机构，专业，年届
			//alert(1)
			$(this).find(".dropdown-list").empty();
			var profession = $("#searchMenu").find("*[name='profession']").find(".dropdown-item-active").data("id");
			var year = $("#searchMenu").find("*[name='year']").find(".dropdown-item-active").data("id");
			var organization= $("#searchMenu").find("*[name='organization']").find(".dropdown-item-active").data("id");
			var word = $(".tab-list .tab-active").data("index");
			if(word == "班级列表"){
				classinfo.dropDownClassId({
					"organization" : organization,
					"profession" : profession,
					"year" : year
				});
			}
			else if(word == "电话回访"){
				classinfo.dropDownClassId({
					"organization" : organization,
					"profession" : profession,
					"year" : year
				});
			}
			else{
				classinfo.dropDownClassId({
					"state" : word,
					"organization" : organization,
					"profession" : profession,
					"year" : year
				});
			}
		
		});
	
		$(".content-active #searchMenu").find("*[name='year']").click(function(){
			//获取已选择的机构，专业，班级
			//alert(2)
			$(this).find(".dropdown-list").empty();
			var profession = $("#searchMenu").find("*[name='profession']").find(".dropdown-item-active").data("id");
			var classInfo = $("#searchMenu").find("*[name='class-id']").find(".dropdown-item-active").data("id");
			var organization= $("#searchMenu").find("*[name='organization']").find(".dropdown-item-active").data("id");
		
			var word = $(".tab-list .tab-active").data("index");
			if(word == "班级列表"){
				classinfo.dropDownClassYear({
					"organization" : organization,
					"profession" : profession,
					"classinfo" : classInfo
				});
			}
			else if(word == "电话回访"){
				classinfo.dropDownClassYear({
					"organization" : organization,
					"profession" : profession,
					"classinfo" : classInfo
				});
			}
			else{
				classinfo.dropDownClassYear({
					"state" : word,
					"organization" : organization,
					"profession" : profession,
					"classinfo" : classInfo
				});
			}
		
		});
	
		$(".content-active #searchMenu").find("*[name='organization']").click(function(){
			//获取已选择的班级，专业，年届
			//alert(3)
			$(this).find(".dropdown-list").empty();
			var profession = $("#searchMenu").find("*[name='profession']").find(".dropdown-item-active").data("id");
			
			var year = $("#searchMenu").find("*[name='year']").find(".dropdown-item-active").data("id");
			var classInfo= $("#searchMenu").find("*[name='class-id']").find(".dropdown-item-active").data("id");
			
			var word = $(".tab-list .tab-active").data("index");
			if(word == "班级列表"){
				classinfo.dropDownOrganization({
					"year" : year,
					"profession" : profession,
					"classinfo" : classInfo
				});
			}
			else if(word == "电话回访"){
				classinfo.dropDownOrganization({
					"year" : year,
					"profession" : profession,
					"classinfo" : classInfo
				});
			}
			else{
				classinfo.dropDownOrganization({
					"state" : word,
					"year" : year,
					"profession" : profession,
					"classinfo" : classInfo
				});
			}
			
		});
	
		$(".content-active #searchMenu").find("*[name='profession']").click(function(){
			//获取已选择的机构，班级，年届
			$(this).find(".dropdown-list").empty();
			var classInfo = $("#searchMenu").find("*[name='class-id']").find(".dropdown-item-active").data("id");
			var year = $("#searchMenu").find("*[name='year']").find(".dropdown-item-active").data("id");
			var organization= $("#searchMenu").find("*[name='organization']").find(".dropdown-item-active").data("id");
	
			var word = $(".tab-list .tab-active").data("index");
			if(word == "班级列表"){
				classinfo.dropDownProfession({
					"organization" : organization,
					"classinfo" : classInfo,
					"year" : year
				});
			}
			else if(word == "电话回访"){
				classinfo.dropDownProfession({
					"organization" : organization,
					"classinfo" : classInfo,
					"year" : year
				});
			}
			else{
				classinfo.dropDownProfession({
					"state" : word,
					"organization" : organization,
					"classinfo" : classInfo,
					"year" : year
				});
			}
			
		});
	}
	//查询面板的查询
	function menuSearch(){
		$(".content-active .menu-btn[value='查询']").unbind("click");
		$(".content-active .menu-btn[value='查询']").click(function(){
			$(this).data("name","");
		    $(this).data("year","");
		    $(this).data("organization","");
		    $(this).data("profession","");
	
			var classInfo = $(".content-active #searchMenu").find("*[name='class-id']").find(".dropdown-item-active").data("id");
			var year = $(".content-active #searchMenu").find("*[name='year']").find(".dropdown-item-active").data("id");
			var organization= $(".content-active #searchMenu").find("*[name='organization']").find(".dropdown-item-active").data("id");
			var profession= $(".content-active #searchMenu").find("*[name='profession']").find(".dropdown-item-active").data("id");
			classinfo.hideMenu();
			
			var temp = $(".tab-container .content-active ").find("*[name='currentPage']");
		    temp.text(1);
		    $(this).data("name",classInfo);
		    $(this).data("year",year);
		    $(this).data("organization",organization);
		    $(this).data("profession",profession);
		    
		    var word = $(".tab-list .tab-active").data("index");
			if(word == "班级列表"){
				classinfo.searchClassinfo({
					"profession" : profession,
					"organization" : organization,
					"classinfo" : classInfo,
					"year" : year
				});
				classinfo.searchClassinfoCount({
					"profession" : profession,
					"organization" : organization,
					"classinfo" : classInfo,
					"year" : year
				});
			}
			else if(word == "电话回访"){
				classinfo.searchTelVisit({
					"profession" : profession,
					"organization" : organization,
					"classinfo" : classInfo,
					"year" : year
				});
				classinfo.searchClassinfoCount({
					"profession" : profession,
					"organization" : organization,
					"classinfo" : classInfo,
					"year" : year
				});
			}
			else{
				classinfo.searchClassinfo({
					"state" : word,
					"profession" : profession,
					"organization" : organization,
					"classinfo" : classInfo,
					"year" : year
				});
				classinfo.searchClassinfoCount({
					"state" : word,
					"profession" : profession,
					"organization" : organization,
					"classinfo" : classInfo,
					"year" : year
				});
			}
			
			nextPage();
		});
	}
	
	/**
	 * 点击班级人数，获得班级名单
	 */
	function stuCount(){
		$(".content-active .data-table tr td[name='stu_count']").unbind("click");
		$(".content-active .data-table tr td[name='stu_count']").click(function(){
			var classinfoId = $(this).parents("tr").find("*[name='id']").val();
			var state = $(this).parents("tr").find("*[name='state']").text();
			classinfo.searchStudent({
				"classinfo" : classinfoId,
				"state" : state
			});
			$(this).parents(".content-active").find(".stu-menu").css("display","block");
			$(".content-active .stu-menu .stu-page-list *[name='stu-currentPage']").text(1);
			var count = $(this).text();
			$(".content-active .stu-menu .stu-page-list *[name='stu-totalRows']").text(count);
			classinfo.searchStudentCount(count);
			$(this).addClass("stu");
		});
		
	}
	
	//关闭班级名单的关闭按钮
	function close(){
		$(".tab-container .content-active .stu-menu .stu-menu-btn").click(function(){
			$(this).parents(".content-active").find(".stu-menu").css("display","none");
			$(".content-active .data-table tr td[name='stu_count']").removeClass("stu");
		});
	}
	
	 	/**
		 * 班级成员翻页
		 */
	function stuPage(){
		var ele = $(".tab-container .content-active");
		ele.find("*[name='stu-prevBtn']").unbind("click");
		ele.find("*[name='stu-prevBtn']").click(function(){
			var temp = $(this).parents(".stu-page-list").find("*[name='stu-currentPage']");
			var currentPage = temp.text();
			
			currentPage = new Number(currentPage);
			if(currentPage > 1){
				var temp = $(this).parents(".stu-page-list").find("*[name='stu-currentPage']");
				temp.text(currentPage - 1);
				var classinfoId = $(".content-active .data-table tr .stu").parents("tr").find("*[name='id']").val();
				var state = $(".content-active .data-table tr .stu").parents("tr").find("*[name='state']").text();
				classinfo.searchStudent({
					"page" : currentPage - 1,
					"classinfo" : classinfoId,
					"state" : state
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
				var classinfoId = $(".content-active .data-table tr .stu").parents("tr").find("*[name='id']").val();
				var state = $(".content-active .data-table tr .stu").parents("tr").find("*[name='state']").text();
				classinfo.searchStudent({
					"page" : currentPage + 1,
					"classinfo" : classinfoId,
					"state" : state
				});
				//temp.text(currentPage + 1); // 当前页数加上1页
			}
		});
	}
	
	function tel(){
		$(".tab-content[data-index='电话回访'] .data-table .detail").unbind("click");
		$(".tab-content[data-index='电话回访'] .data-table .detail").click(function(){
			var classinfoId = $(this).parents("tr").find("td[name='id']").data("id");
			//var count = $(this).parent("tr").find("td[name='count']").text();
			var classInfo =$(".content-active .menu-btn[value='查询']").data("name");
			var year = $(".content-active .menu-btn[value='查询']").data("year");
			var organization= $(".content-active .menu-btn[value='查询']").data("organization");
			var profession= $(".content-active .menu-btn[value='查询']").data("profession");
			
			var page = $(".tab-container .content-active").find("*[name=currentPage]").text();
			$(window.parent.document).find("iframe").attr("src","classinfo/detail.html?from=1&classinfoId="+classinfoId+"&page="+page+"&classInfo="+classInfo+"&year="+year+"&organization="+organization+"&profession="+profession);
		
			
		});
		$(".tab-content[data-index='电话回访'] .data-table .operation").unbind("click");
		$(".tab-content[data-index='电话回访'] .data-table .operation").click(function(){
			var classinfoId = $(this).parents("tr").find("td[name='id']").data("id");
			var rate = $(this).parent("tr").find("td[name='visit']").text();
			
			classinfo.queryClassinfoCount(classinfoId,rate);
			
		});
	}
	
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
	
	function nextPage(){
		var pageTab = $(".tab-container .content-active .page-list");
		var pageNum = $(pageTab).find(".page-num");
		var currentPage = pageTab.find("*[name='currentPage']").text();
		var totalPage = pageTab.find("*[name='totalPages']").text();
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
		$(".content-active .page-list .page-num").click(function(){
			var unclick = $(this).parents(".page-list").find(".page-disable").eq(0).text();
			var page = $(this).text();
			if(page < unclick || unclick == ""){
				$(".tab-container .content-active .page-list").find("*[name='currentPage']").text(page);
				$(".page-num").removeClass("page-active");
				$(this).addClass("page-active");
				
				var name =$(".content-active .menu-btn[value='查询']").data("name");
				var searchYear = $(".content-active .menu-btn[value='查询']").data("year");
				var searchOrganization= $(".content-active .menu-btn[value='查询']").data("organization");
				var searchProfession= $(".content-active .menu-btn[value='查询']").data("profession");
				
				var word = $(".tab-list .tab-active").data("index");
    			if(word == "班级列表"){
    				classinfo.searchClassinfo({
    					"page" : page,
    					"profession" : searchProfession,
    					"organization" : searchOrganization,
    					"classinfo" : name,
    					"year" : searchYear
    				});
    			}
    			else if(word == "电话回访"){
    				classinfo.searchTelVisit({
    					"page" : page,
    					"profession" : searchProfession,
    					"organization" : searchOrganization,
    					"classinfo" : name,
    					"year" : searchYear
    				});
    			}
    			else{
    				classinfo.searchClassinfo({
    					"state" : word,
    					"page" : page,
    					"profession" : searchProfession,
    					"organization" : searchOrganization,
    					"classinfo" : name,
    					"year" : searchYear
    				});
    				
    			}
			}
			
		});
	}
	