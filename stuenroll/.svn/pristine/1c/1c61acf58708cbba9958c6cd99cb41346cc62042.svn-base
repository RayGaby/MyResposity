$(function() {
	if (!checkPermission([ "1_4" ])) {
		return;
	}
	var tabContainer = $(".tab-container");
	if (checkPermission([ "1_1" ])) {
		tabContainer.find(".operation-item[name='add']").show();
	}
	if (checkPermission([ "1_3" ])) {
		tabContainer.find(".operation-item[name='update']").show();
	}
	if (checkPermission([ "1_2" ])) {
		tabContainer.find(".operation-item[name='delete']").show();
	}
	tabContainer.find(".operation-item[name='infile']").show();
	
	tabContainer.find(".operation-item[name='outfile']").show();

	/**
	 * 用户信息管理抽象接口
	 */
	var I_UserInfo = function() {

	}

	I_UserInfo.prototype.searchUserInfo = function(json) {
		throw "抽象方法";
	}
	I_UserInfo.prototype.searchUserInfoCount = function(json) {
		throw "抽象方法";
	}
	I_UserInfo.prototype.deleteUserInfo = function() {
		throw "抽象方法";
	}
	I_UserInfo.prototype.updateconfirm = function(json) {
		throw "抽象方法";
	}
	I_UserInfo.prototype.SliderButtonChange = function(obj) {
		throw "抽象方法";
	}
	I_UserInfo.prototype.initSliderButton = function(obj) {
		throw "抽象方法";
	}
	I_UserInfo.prototype.refresh = function() {
		throw "抽象方法";
	}
	I_UserInfo.prototype.import = function() {
		throw "抽象方法";
	}

	var UserInfo = function() {

	}

	UserInfo.prototype = new I_UserInfo();

	// 更新页数
	UserInfo.prototype.refresh = function() {
		var table = $(".tab-container .tab-content .page-list");
		var pagetest = $(table).find(".page-test");
		var currentNumber = $(table).find("#currentPage").text();
		var pageNumber = $(table).find("#currentPage").text();
		var maxNumber = $(table).find("#totalPages").text();
		var i = 0;
		i = new Number(i);
		currentNumber = new Number(currentNumber);
		pageNumber = new Number(pageNumber);
		maxNumber = new Number(maxNumber);

		if (maxNumber > 5) {
			if (currentNumber >= 3 && currentNumber < maxNumber - 1)
				pageNumber = 3;
			else if (currentNumber == maxNumber - 1)
				pageNumber = 4;
			else if (currentNumber == maxNumber)
				pageNumber = 5;
		}
		pagetest.removeClass("page-active");
		pagetest.removeClass("page-disable");
		if (maxNumber <= 5) {
			for (; i < 5; i++) {
				$(pagetest[i]).text(i + 1);
				if (i + 1 > maxNumber)
					$(pagetest[i]).addClass("page-disable");
			}
		}
		else {
			for (; i < 5; i++)
				$(pagetest[i]).text(currentNumber + i + 1 - pageNumber);
		}
		$(pagetest[pageNumber - 1]).addClass("page-active");
	}

	UserInfo.prototype.initSliderButton = function(obj) {
		// 读取页面中滑动按钮的初始化状态，然后执行初始化工作
		// console.info(obj)
		obj.each(function(i, one) {
			var btn = $(one).find(".btn");
			// alert(btn)
			var index = btn.data("index");
			if (index == "left") {
				btn.css("left", 0);
				$(one).css("background-color", "#00CC99");
				var open = $(one).find(".open");
				open.css("display", "block");

			}
			else {
				btn.css("right", 0);
				$(one).css("background-color", "#FC6432");
				var close = $(one).find(".close");
				close.css("display", "block");
			}
			// $(one).css("visibility", "visible");
		});
	}

	UserInfo.prototype.SliderButtonChange = function(obj) {
		// console.log(obj)
		var id = $(obj).parents("tr").find("td").find("[name='id']").val();
		// console.log($(obj).parents("tr"))
		// alert(id)
		var block;
		var btn = obj.find(".btn");
		var index = btn.data("index");
		if (index == "left") {
			// btn.removeAttr("style");
			btn.css({
				"margin-left" : 40,
				"margin-right" : 0
			});
			btn.css("right", 0);
			obj.css("background-color", "#FC6432");
			var close = obj.find(".close");
			close.css("display", "block");
			var open = obj.find(".open");
			open.css("display", "block");
			btn.data("index", "right");

			block = 1;
		}
		else {
			// btn.removeAttr("style");
			btn.css({
				"margin-right" : 40,
				"margin-left" : 0
			});
			btn.css("left", 0);
			obj.css("background-color", "#00CC99");
			var open = obj.find(".open");
			open.css("display", "block");
			var close = obj.find(".close");
			close.css("display", "none");
			btn.data("index", "left");

			block = 0;
		}
		$.ajax({
			"url" : "/stuenroll/userinfo/updateUserBlock",
			"type" : "post",
			"dataType" : "json",
			"data" : {
				"id" : id,
				"block" : block
			},
			"async" : false,
			"success" : function(json) {
				toastr.success("状态更改成功");
			},
			"error" : function() {
				toastr.warning("更改无效！请检查有无更改权限");
			}
		});
	}

	UserInfo.prototype.searchUserInfo = function(json) {
		// alert(1)
		$.ajax({
			"url" : "/stuenroll/userinfo/searchUserInfo",
			"type" : "post",
			"dataType" : "json",
			"async" : "false",
			"data" : json,
			"success" : function(json) {
				// alert(123)
				var data = json.result;
				// alert(data)
				// console.log(data)
				var table = $(".tab-container .data-table");

				// 清空表格数据
				table.find("tr:gt(0)").remove();
				// 获得当前页数
				var currentPage = $(".tab-container .page-list #currentPage").text();
				// 转化成数字类型
				currentPage = new Number(currentPage);
				// 当前页数的行号起始数字
				var start = (currentPage - 1) * 35;

				var temp = "";
				for (var i = 0; i < data.length; i++) {
					var one = data[i];
					// console.log(one.name);
					temp += "<tr>";
					temp += "<td><input type='checkbox' name='id' value='" + one.id + "' /></td>"
					temp += "<td>" + (start + i + 1) + "</td>";
					temp += "<td>" + one.username + "</td>";
					temp += "<td>" + one.name + "</td>";
					temp += "<td>" + one.organization + "</td>";
					temp += "<td>" + one.role + "</td>";
					temp += "<td>" + one.tel + "</td>";
					temp += "<td>" + one.email + "</td>";
					temp += "<td>";
					if (one.block == false) { // 根据block状态来初始化角色状态
						// temp += "<div class='state-container' name='state'>";
						temp += "<div class='state-item' name='state'>";
						temp += "<div class='close'>关</div>";
						temp += "<div class='btn' data-index='left'></div>";
						temp += "<div class='open'>开</div>";
						temp += "</div>";
					}
					else if (one.block == true) {
						// temp += "<div class='state-container' name='state'>";
						temp += "<div class='state-item' name='state'>";
						temp += "<div class='close'>关</div>";
						temp += "<div class='btn' data-index='right'></div>";
						temp += "<div class='open'>开</div>";
						temp += "</div>";
					}
					temp += "</td>";
					temp += "</tr>";
				}
				table.append(temp);
				userInfo.initSliderButton($(".data-table .state-item*[name='state']"));
				// console.log($(".data-table .state-item*[name='state']"))
				$(".data-table .state-item*[name='state']").click(function() {
					// alert(1)
					userInfo.SliderButtonChange($(this));
				});
				// ShowMember.init(obj);

			},
			"error" : function() {
				toastr.error("系统异常");
			}

		});
	}

	UserInfo.prototype.searchUserInfoCount = function(json) {
		$.ajax({
			"url" : "/stuenroll/userinfo/searchUserInfoCount",
			"type" : "post",
			"dataType" : "json",
			"data" : json,
			"async" : false,
			"success" : function(json) {
				var count = json.result; // 总记录数
				var content = $(".tab-container .page-list");
				content.find("#totalRows").text(count);
				var totalPages = (count % 35 == 0) ? count / 35 : Math.floor(count / 35) + 1;
				content.find("#totalPages").text(totalPages);
			},
			"error" : function() {
				toastr.error("系统异常");
			}
		});
	}

	UserInfo.prototype.deleteUserInfo = function() {
		// 弹出确认对话框
		var bool = confirm("是否删除选中的记录");
		if (bool == false) {
			return;
		}
		// 获得被选中的记录
		var content = $(".tab-container .tab-content");
		// 被选中的复选框
		var checkbox = content.find("*[name='id']:checked");

		var id = [];
		for (var i = 0; i < checkbox.length; i++) {
			id.push($(checkbox[i]).val());
		}
		$.ajax({
			"url" : "/stuenroll/userinfo/deleteUserInfo",
			"type" : "post",
			"dataType" : "json",
			"traditional" : true,
			"async" : false,
			"data" : {
				"id" : id
			},
			"success" : function(json) {
				toastr.success("删除了" + json.deleteRows + "条记录");
			},
			"error" : function() {
				toastr.warning("无效操作");
			}
		});
	}
	// 确认修改按钮方法
	UserInfo.prototype.updateconfirm = function(json) {
		// 弹出确认对话框
		var bool = confirm("是否修改选中的记录");
		if (bool == false) {
			return;
		}
		// 获得被选中的记录
		var content = $(".tab-container .tab-content");
		// 被选中的复选框
		var checkbox = content.find("*[name='id']:checked");
		if (checkbox.length != 1) {
			toastr.warning("请选择一个复选框！");
			return;

		}
		var id = checkbox.val();
		// id.push($(checkbox).val());
		// alert(id)
		location.href = "update.html?id=" + id;

	}

	UserInfo.prototype.import = function(fileName) {
		$.ajaxFileUpload({
			url : '/stuenroll/userinfo/importFile', // 提交的路径
			secureuri : false, // 是否启用安全提交，默认为false
			fileElementId : 'file', // file控件id
			dataType : 'json',
			data : {
				fileName : fileName
			// 传递参数，用于解析出文件名
			}, // 键:值，传递文件名
			success : function(json) {
				var count = json.result;
				if (count == -1) {
					toastr.warning("只有就业网用户可以导入专业");

				}
				else if (count == -2) {
					toastr.warning("导入失败");

				}
				else if (count == 0) {
					toastr.success("导入的用户名在记录中已经存在");
					inFile.hide();
					flag1 = 0;
					userInfo.searchUserInfo();
					userInfo.searchUserInfoCount();
					userInfo.refresh();

				}
				else {
					toastr.success("成功导入" + count + "条记录");
					inFile.hide();
					flag1 = 0;
					userInfo.searchUserInfo();
					userInfo.searchUserInfoCount();
					userInfo.refresh();

				}
				
			},
			error : function(json) {
				toastr.error("系统异常");

			}
		});

	}

	/**
	 * 导入导出面板接口
	 */
	var I_HandleFile = function() {

	}
	/**
	 * 显示面板抽象方法
	 */
	I_HandleFile.prototype.show = function() {
		throw "抽象方法";
	}

	/**
	 * 隐藏面板抽象方法
	 */
	I_HandleFile.prototype.hide = function() {
		throw "抽象方法";
	}

	var InFile = function() {

	}
	InFile.prototype = new I_HandleFile();

	InFile.prototype.show = function() {
		$(".main-container .Infile-container").fadeIn();
	}
	InFile.prototype.hide = function() {
		$(".main-container .Infile-container").fadeOut();
	}

	var OutFile = function() {

	}
	OutFile.prototype = new I_HandleFile();

	OutFile.prototype.show = function() {
		$(".main-container .Outfile-container").fadeIn();
	}
	OutFile.prototype.hide = function() {
		$(".main-container .Outfile-container").fadeOut();
	}

	
	
	
	
	/**
	 * 显示已归档信息接口
	 */
	var I_ShowMember = function() {

	}
	/**
	 * 显示已归档信息面板抽象方法
	 */
	I_ShowMember.prototype.show = function() {
		throw "抽象方法";
	}
	/**
	 * 隐藏已归档信息面板抽象方法
	 */
	I_ShowMember.prototype.hide = function() {
		throw "抽象方法";
	}
	/**
	 * 显示已归档成员信息
	 */
	I_ShowMember.prototype.searchOrganizationArchiveMember = function(json) {
		throw "抽象方法";
	}
	/**
	 * 显示已归档成员总数
	 */
	I_ShowMember.prototype.searchMemberCount = function() {
		throw "抽象方法";
	}

	var ShowMember = function() {

	}
	ShowMember.prototype = new I_ShowMember();

	ShowMember.prototype.show = function() {
		$(".showMember-container").fadeIn();
	}
	ShowMember.prototype.hide = function() {
		$(".showMember-container").fadeOut();
	}

	ShowMember.prototype.searchOrganizationArchiveMember = function(json) {
		//alert(123)
		$.ajax({
			"url" : "/stuenroll/userinfo/searchOrganizationArchiveMember",
			"type" : "post",
			"dataType" : "json",
			"async" : "false",
			"data" : json,
			"success" : function(json) {
				// alert(1)
				var data = json.result;
				var table = $(".tab-container .member-table");
				// 清空表格数据
				table.find("tr:gt(0)").remove();
				// 获得当前页数
				var currentPage = $(".tab-container .memberpage-list #currentPage").text();
				// 转化成数字类型
				currentPage = new Number(currentPage);
				// 当前页数的行号起始数字
				var start = (currentPage - 1) * 10;
				var temp = "";
				for (var i = 0; i < data.length; i++) {
					var one = data[i];
					temp += "<tr>";
					temp += "<td>" + (start + i + 1) + "</td>";
					temp += "<td>" + one.name + "</td>";
					temp += "<td>" + one.sex + "</td>";
					temp += "<td>" + one.tel + "</td>";
					temp += "<td>" + one.pid + "</td>";
					temp += "<td>" + one.state + "</td>";
					temp += "</tr>";
				}
				table.append(temp);
			},
			"error" : function() {
			}
		});
	}

	ShowMember.prototype.searchMemberCount = function(json) {
		//alert(1)
		$.ajax({
			"url" : "/stuenroll/userinfo/searchMemberCount",
			"type" : "post",
			"dataType" : "json",
			"data" : json,
			"async" : false,
			"success" : function(json) {
				var count = json.result; // 总记录数
				var content = $(".tab-container .memberpage-list");
				content.find("#totalRows").text(count);
				var totalPages = (count % 10 == 0) ? count / 10 : Math.floor(count / 10) + 1;
				content.find("#totalPages").text(totalPages);
			},
			"error" : function() {

			}
		});
	}

	function factory(key) {
		if (key == "ShowMember") {
			return new ShowMember();
		}
		else if (key == "UserInfo") {
			return new UserInfo();
		}
		else if (key == "OutFile") {
			return new OutFile();
		}
		else if (key == "InFile") {
			return new InFile();
		}
	}

	var showMember = factory("ShowMember");
	var userInfo = factory("UserInfo");
	var outFile = factory("OutFile");
	var inFile = factory("InFile");

	userInfo.searchUserInfo();
	userInfo.searchUserInfoCount();
	userInfo.refresh();

	showMember.searchOrganizationArchiveMember();
	showMember.searchMemberCount();

	var flag1 = 0;
	var flag = 0;
	/**
	 * 点击导入
	 */
	$("#infile").click(function() {
		if (flag == 1) {
			outFile.hide();
			flag = 0;
		}
		if (flag1 == 0 && flag == 0) {
			inFile.show();
			flag1 = 1
		}
		else if (flag1 == 1) {
			inFile.hide();
			flag1 = 0;
		}
		$("#fileImport").click(function() {
			if ($("#file").val() != "") {
				userInfo.import($('#file').val()); // 函数参数为上传的文件的本机地址
				$("#file").val("");
			}
			else {
				toastr.warning("请选择要导入的文件");
			}
		});
		$(".Infile-container").find(".file").val("");

	});

	/**
	 * 点击导出
	 */
	$("#outfile").click(function() {
		if (flag1 == 1) {
			inFile.hide();
			flag1 = 0;
		}
		if (flag == 0 && flag1 == 0) {
			outFile.show();
			flag = 1
		}
		else if (flag == 1) {
			outFile.hide();
			flag = 0;
		}
		// 导出文件
		$("#Excel").click(function() {
			document.exportForm.action = "/stuenroll/userinfo/exportExcel";
			document.exportForm.submit();
		});
		$("#XML").click(function() {
			document.exportForm.action = "/stuenroll/userinfo/exportXml";
			document.exportForm.submit();
		});
		$("#CSV").click(function() {
			document.exportForm.action = "/stuenroll/userinfo/exportCsv";
			document.exportForm.submit();
		});

	});

	$("#archive-item").click(function() {
		showMember.show();
	});

	$(".main-container .operation-item[name='update']").click(function() {
		userInfo.updateconfirm();
	});
	$(".main-container .btn-item").click(function() {
		showMember.hide();

	});

	// 用户的上下页设置
	var element = $(".tab-container .tab-content");
	element.find("*[name='prevBtn']").click(function() {
		var temp = $(this).parents(".page-list").find("#currentPage");
		var currentPage = temp.text();
		currentPage = new Number(currentPage);
		if (currentPage > 1) {
			// 请求Ajax并更新数据
			userInfo.searchUserInfo({
				"page" : currentPage - 1
			});
			temp.text(currentPage - 1); // 当前页数减1
		}
		userInfo.refresh();
	});
	element.find("*[name='nextBtn']").click(function() {
		var temp = $(this).parents(".page-list").find("#currentPage");
		var currentPage = temp.text();
		var totalPages = $(this).parents(".page-list").find("#totalPages").text();
		currentPage = new Number(currentPage);
		totalPages = new Number(totalPages);

		if (currentPage < totalPages) {
			// 请求Ajax并更新数据
			userInfo.searchUserInfo({
				"page" : currentPage + 1
			});
			temp.text(currentPage + 1); // 当前页数加上1页
		}
		userInfo.refresh();
	});
	element.find("*[name='delete']").click(function() {
		// alert(1)
		// 先删除
		userInfo.deleteUserInfo();
		// 再查询
		userInfo.searchUserInfoCount();
		var totalPages = $(this).parents(".tab-container").find("#totalPages").text();
		var currentPage = $(this).parents(".tab-container").find("#currentPage").text();
		totalPages = new Number(totalPages);
		currentPage = new Number(currentPage);
		if (currentPage > totalPages) {
			currentPage = totalPages;
		}
		$(this).parents(".tab-container").find("#currentPage").text(currentPage); // 更新当前页数
		userInfo.searchUserInfo();
		userInfo.refresh();

	});

	// 机构已归档成员的上下页设置
	var element1 = $(".showMember-container .showtab-content");
	element1.find("*[name='prevBtn']").click(function() {
		var temp = $(this).parents(".memberpage-list").find("#currentPage");
		var currentPage = temp.text();
		currentPage = new Number(currentPage);
		if (currentPage > 1) {
			// 请求Ajax并更新数据
			showMember.searchOrganizationArchiveMember({
				"page" : currentPage - 1
			});
			temp.text(currentPage - 1); // 当前页数减1
		}
	});
	element1.find("*[name='nextBtn']").click(function() {
		var temp = $(this).parents(".memberpage-list").find("#currentPage");
		var currentPage = temp.text();
		var totalPages = $(this).parents(".memberpage-list").find("#totalPages").text();
		currentPage = new Number(currentPage);
		totalPages = new Number(totalPages);

		if (currentPage < totalPages) {
			// 请求Ajax并更新数据
			showMember.searchOrganizationArchiveMember({
				"page" : currentPage + 1
			});
			temp.text(currentPage + 1); // 当前页数加上1页
		}
	});

});