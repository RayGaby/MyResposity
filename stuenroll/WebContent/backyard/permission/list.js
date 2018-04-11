$(function() {
	if (!checkPermission([ "2_4" ])) {
		return;
	}

	var tabContainer = $(".tab-container");
	if (checkPermission([ "2_1" ])) {
		tabContainer.find(".operation-item[name='add']").show();
	}
	if (checkPermission([ "2_3" ])) {
		tabContainer.find(".operation-item[name='update']").show();
	}
	if (checkPermission([ "2_2" ])) {
		tabContainer.find(".operation-item[name='delete']").show();
	}

	/**
	 * 角色权限管理抽象接口
	 */
	var I_RolePermission = function() {

	}

	I_RolePermission.prototype.searchRolePermission = function(json) {
		throw "抽象方法";
	}

	I_RolePermission.prototype.searchRolePermissionCount = function(json) {
		throw "抽象方法";
	}

	I_RolePermission.prototype.insertRolePermission = function(json) {
		throw "抽象方法";
	}

	I_RolePermission.prototype.deleteRoleById = function() {
		throw "抽象方法";
	}
	I_RolePermission.prototype.SliderButtonChange = function(obj) {
		throw "抽象方法";
	}
	I_RolePermission.prototype.initSliderButton = function(obj) {
		throw "抽象方法";
	}
	I_RolePermission.prototype.refresh = function() {
		throw "抽象方法";
	}

	var RolePermission = function() {

	}

	RolePermission.prototype = new I_RolePermission();
	// 更新页数
	RolePermission.prototype.refresh = function() {
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

	RolePermission.prototype.initSliderButton = function(obj) {
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

	RolePermission.prototype.SliderButtonChange = function(obj) {
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
			"url" : "/stuenroll/rolepermission/updateBlock",
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

	RolePermission.prototype.searchRolePermission = function(json) {

		$.ajax({

			"url" : "/stuenroll/rolepermission/searchRolePermission",
			"type" : "post",
			"dataType" : "json",
			"data" : json,
			"success" : function(json) {
				var data = json.result;
				// alert(data)
				var table = $(".tab-container .data-table");
				// 清空表格数据
				table.find("tr:gt(0)").remove();
				// 获得当前页数
				var currentPage = $(".tab-container #currentPage").text();
				// 当前页数的行号起始数字
				var start = (currentPage - 1) * 35;

				var temp = "";
				for (var i = 0; i < data.length; i++) {

					var one = data[i];
					// console.log(one.name);
					temp += "<tr>";
					temp += "<td><input type='checkbox' name='id' value='" + one.id + "' /></td>"
					temp += "<td>" + (start + i + 1) + "</td>";
					temp += "<td name='roleName'>" + one.name + "</td>";
					temp += "<td>" + one.permissionNum + "</td>";
					temp += "<td name='usernum'>" + one.userNum + "</td>";
					temp += "<td>" + one.activeNum + "</td>";
					temp += "<td>" + one.unActiveNum + "</td>";
					temp += "<td>" + one.adminuser + "</td>";
					temp += "<td>" + one.organizationuser + "</td>";
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
				rolePermission.initSliderButton($(".data-table .state-item*[name='state']"));
				// console.log($(".data-table .state-item*[name='state']"))
				$(".data-table .state-item*[name='state']").click(function() {
					// alert(1)
					rolePermission.SliderButtonChange($(this));
				});
			},
			"error" : function() {
				toastr.error("系统异常");
			}
		});

	}

	RolePermission.prototype.searchRolePermissionCount = function(json) {
		$.ajax({
			"url" : "/stuenroll/rolepermission/searchRolePermissionCount",
			"type" : "post",
			"dataType" : "json",
			"data" : json,
			"async" : false,
			"success" : function(json) {
				var count = json.result; // 总记录数
				var content = $(".tab-container .tab-content");
				content.find("#totalRows").text(count);
				var totalPages = (count % 35 == 0) ? count / 35 : Math.floor(count / 35) + 1;
				content.find("#totalPages").text(totalPages);
			},
			"error" : function() {
				toastr.error("系统异常");
			}
		});
	}

	RolePermission.prototype.insertRolePermission = function(json) {

		var name = $(".tab-container #role-name").val();
		// 获得被选中的记录
		var content = $(".tab-container .permission-item ");
		// alert(content)
		// 被选中的复选框
		var checkbox = content.find("*[name='permission']:checked");
		// alert(checkbox.val())
		// console.log(checkbox)
		var id = [];
		for (var i = 0; i < checkbox.length; i++) {
			id.push($(checkbox[i]).val());
		}
		if (checkbox.length == 0) {
			id = -1;
		}
		$.ajax({
			"url" : "/stuenroll/rolepermission/insertRolePermission",
			"type" : "post",
			"async" : false,
			"traditional" : true,
			"dataType" : "json",
			"data" : {
				"name" : name,
				"id" : id
			},
			"success" : function() {
				toastr.success("添加成功！");
			},
			"error" : function() {
				toastr.error("系统异常");
			}

		});
	}

	RolePermission.prototype.deleteRoleById = function() {
		// 弹出确认对话框
		var bool = confirm("是否删除选中的记录");
		if (bool == false) {
			return;
		}
		// 获得被选中的记录
		var content = $(".tab-container .tab-content");
		// 被选中的复选框
		var checkbox = content.find("*[name='id']:checked");
		// alert(checkbox.val())

		var id = [];
		for (var i = 0; i < checkbox.length; i++) {
			id.push($(checkbox[i]).val());
		}
		if (checkbox.length == 0) {
			toastr.warning("请选中需要删除的记录");
		}
		else if (checkbox.length >= 2) {
			toastr.success("删除了多条记录")
		}
		var usernumber = content.find("*[name='id']:checked").parents("tr").find("td[name='usernum']").text();
		// alert(usernumber)
		if (usernumber != 0) {
			toastr.warning("无法删除关联用户角色");
			return;
		}
		$.ajax({

			"url" : "/stuenroll/rolepermission/deleteRoleById",
			"type" : "post",
			"dataType" : "json",
			"traditional" : true, // 发送数组JSON格式
			"async" : false,
			"data" : {
				"id" : id
			},
			"success" : function(json) {
				toastr.success("删除了" + json.deleteRows + "条记录");
			},
			"error" : function() {
			}
		});

	}

	/**
	 * 添加修改角色权限接口
	 */
	var I_UpdatePermission = function() {

	}
	/**
	 * 显示角色添加面板所有权限选项
	 */
	I_UpdatePermission.prototype.allPermission = function(json) {
		throw "抽象方法";
	}
	/**
	 * 显示角色添加面板抽象方法
	 */
	I_UpdatePermission.prototype.show = function() {
		throw "抽象方法";
	}

	/**
	 * 隐藏角色添加面板抽象方法
	 */
	I_UpdatePermission.prototype.hide = function() {
		throw "抽象方法";
	}
	/**
	 * 清空面板抽象方法
	 */
	I_UpdatePermission.prototype.cleardata = function() {
		throw "抽象方法";
	}

	var UpdatePermission = function() {

	}

	UpdatePermission.prototype = new I_UpdatePermission();

	UpdatePermission.prototype.allPermission = function(json) {
		$.ajax({
			"url" : "/stuenroll/rolepermission/searchSelectPermission",
			"type" : "post",
			"dataType" : "json",
			"async" : false,
			"data" : null,
			"success" : function(json) {
				var data = json.allPermission;
				var table = $(".main-container .permission-content");
				// 清空数据
				table.find("dt:gt(0)").remove();
				var temp = "";
				for (var i = 0; i < data.length; i++) {
					var one = data[i];
					// console.log(one)
					temp += "<dt class='permission-item'>";
					temp += "<input type='checkbox' id='permission' name='permission' value=" + one.id + " /> <label for='permission'>"
							+ one.name + "</label>";
					temp += "</dt>";
				}
				// alert(temp)
				table.append(temp);
			},
			"error" : function() {

			}
		});
	}

	UpdatePermission.prototype.show = function() {
		$(".main-container .update-container").fadeIn();

	}
	UpdatePermission.prototype.hide = function() {
		$(".main-container .update-container").fadeOut();
	}
	UpdatePermission.prototype.cleardata = function() {
		$(".main-container .input").val("");
		// alert(1)
		var content = $(".tab-container .permission-content");
		// 被选中的复选框
		// alert(content)
		var checkbox = content.find("*[name='permission']:checked");
		// console.log(checkbox)
		checkbox.prop("checked", false);
		// $(".main-container .permission-content").find("dt:gt(0)").remove();
	}

	// 修改角色权限抽象接口
	var I_AlterPermission = function() {

	}

	I_AlterPermission.prototype.ShowName = function() {
		throw "抽象方法";
	}
	/**
	 * 更新角色权限方法
	 */
	I_AlterPermission.prototype.updateRolePermissionName = function() {
		throw "抽象方法";
	}
	I_AlterPermission.prototype.updateRolePermissionMore = function(json) {
		throw "抽象方法";
	}
	I_AlterPermission.prototype.updateRolePermissionLess = function(json) {
		throw "抽象方法";
	}
	/**
	 * 显示角色修改面板该角色权限选项
	 */
	I_AlterPermission.prototype.ShowPermission = function(json) {
		throw "抽象方法";
	}
	/**
	 * 显示角色修改面板抽象方法
	 */
	I_AlterPermission.prototype.show = function() {
		throw "抽象方法";
	}
	/**
	 * 隐藏角色修改面板抽象方法
	 */
	I_AlterPermission.prototype.hide = function() {
		throw "抽象方法";
	}
	/**
	 * 清空面板抽象方法
	 */
	I_AlterPermission.prototype.cleardata1 = function() {
		throw "抽象方法";
	}
	var AlterPermission = function() {

	}

	AlterPermission.prototype = new I_AlterPermission();
	/**
	 * 更新角色权限姓名
	 */
	AlterPermission.prototype.updateRolePermissionName = function() {
		var name = $(".tab-container #role-name1").val();
		// 获得被选中的记录
		var content = $(".tab-container .tab-content");
		// 被选中的复选框
		var checkbox = content.find("*[name='id']:checked");
		var id = checkbox.val();
		// alert(id)
		$.ajax({
			"url" : "/stuenroll/rolepermission/updateRolePermissionName",
			"type" : "post",
			"async" : false,
			"traditional" : true,
			"dataType" : "json",
			"data" : {
				"id" : id,
				"name" : name
			},
			"success" : function() {
				toastr.success("权限信息更新中...");
			},
			"error" : function() {
				toastr.error("系统异常");
			}

		});

	}
	AlterPermission.prototype.updateRolePermissionMore = function(json) {
		// 获得被选中的记录
		var content = $(".tab-container .tab-content");
		// 被选中的复选框
		var checkbox = content.find("*[name='id']:checked");
		var id = checkbox.val();
		// alert(id)
		// alert(nowone)
		$.ajax({
			"url" : "/stuenroll/rolepermission/updateRolePermissionMore",
			"type" : "post",
			"async" : false,
			"traditional" : true,
			"dataType" : "json",
			// "data" : json,
			"data" : {
				"id" : id,
				"permission_id" : nowonetemp
			},
			"success" : function() {

			},
			"error" : function() {
				toastr.error("系统异常");
			}

		});
	}
	AlterPermission.prototype.updateRolePermissionLess = function(json) {
		// 获得被选中的记录
		var content = $(".tab-container .tab-content");
		// 被选中的复选框
		var checkbox = content.find("*[name='id']:checked");
		var id = checkbox.val();
		// alert(id)
		// alert(nowone)
		$.ajax({
			"url" : "/stuenroll/rolepermission/updateRolePermissionLess",
			"type" : "post",
			"async" : false,
			"traditional" : true,
			"dataType" : "json",
			"data" : {
				"id" : id,
				"permission_id" : preonetemp
			},
			"success" : function() {

			},
			"error" : function() {
				toastr.error("系统异常");
			}

		});
	}

	/**
	 * 显示修改面板中权限复选框
	 */
	AlterPermission.prototype.ShowPermission = function() {
		// 获得被选中的记录
		var content = $(".tab-container .tab-content");
		// 被选中的复选框
		var id = content.find("*[name='id']:checked").val();
		// alert(id)
		// id.push($(checkbox[i]).val());
		$.ajax({
			"url" : "/stuenroll/rolepermission/searchRoleExistingPermission",
			"type" : "post",
			"dataType" : "json",
			"async" : false,
			"data" : {
				"id" : id
			},
			"success" : function(json) {
				var data = json.existingPermission;
				var content = $(".main-container .permission-content*[name='newselect']");
				// alert(data)
				// console.log(data)
				for (var i = 0; i < data.length; i++) {
					var one = data[i];
					// console.log(one)
					var checked = content.find("*[name='permission']");
					for (var j = 0; j < checked.length; j++) {
						if (checked[j].value == one.id) {
							checked[j].checked = true;
						}
					}
				}
			},
			"error" : function() {

			}
		});
	}

	// 显示修改面板选中项的名字
	AlterPermission.prototype.ShowName = function() {
		var content = $(".tab-container .tab-content");
		var RoleName = content.find("*[name='id']:checked").parents("tr").find("td[name='roleName']").text();
		// alert(RoleName)
		$(".main-container .alter-container #role-name1").val(RoleName);
	}

	AlterPermission.prototype.show = function() {
		$(".main-container .alter-container").fadeIn();
	}
	AlterPermission.prototype.hide = function() {
		$(".main-container .alter-container").fadeOut();
	}
	AlterPermission.prototype.cleardata1 = function() {
		$(".main-container .input").val("");
		var content = $(".tab-container .permission-content*[name='newselect'] ");
		// 被选中的复选框
		// alert(content)
		var checkbox = content.find("*[name='permission']:checked");
		// console.log(checkbox)
		checkbox.prop("checked", false);
		// $(".main-container .permission-content").find("dt:gt(0)").remove();
	}

	function factory(key) {
		if (key == "RolePermission") {
			return new RolePermission();
		}
		else if (key == "UpdatePermission") {
			return new UpdatePermission();
		}
		else if (key == "AlterPermission") {
			return new AlterPermission();
		}
	}

	var rolePermission = factory("RolePermission");
	var updatePermission = factory("UpdatePermission");
	var alterPermission = factory("AlterPermission");

	rolePermission.searchRolePermission();
	rolePermission.searchRolePermissionCount();
	rolePermission.refresh();
	updatePermission.allPermission();

	var flag = 0;
	var flag1 = 0;
	$("#add").click(function() {
		if (flag == 0) {
			updatePermission.show();
			flag = 1;
			alterPermission.hide();
			// // 清空所有的checkbox和input
			$("*[name='permission']").prop("checked", false);
			// $("*[name='id']").prop("checked", false);
			$("*[name='input']").val("");
			$("#role-name").val("");
			flag1 = 0;
		}
		else if (flag == 1) {
			updatePermission.hide();
			// 清空所有的checkbox和input
			$("*[name='permission']").prop("checked", false);
			// $("*[name='id']").prop("checked", false);
			$("*[name='input']").val("");
			$("#role-name").val("");
			flag = 0;
		}
	});

	var prechecked;
	$("#update").click(function() {
		var content = $(".tab-container .tab-content");
		// 被选中的复选框
		var item = content.find("*[name='id']:checked");
		if (item.length == 0) {
			toastr.warning("请选中一个角色");
		}
		else if (item.length >= 2) {
			toastr.warning("每次只能修改一个角色");
		}
		else {
			if (flag1 == 0) {
				alterPermission.show();
				// 获取修改前的名称
				alterPermission.ShowName();
				// 获取修改前的角色权限
				alterPermission.ShowPermission();
				var precontent = $(".main-container .permission-content*[name='newselect']");
				prechecked = precontent.find("*[name='permission']:checked");
				// alert(prechecked)
				// console.log(prechecked)
				flag1 = 1;
				updatePermission.hide();
				flag = 0;
			}
			else if (flag1 == 1) {
				alterPermission.hide();
				// 清空所有的checkbox和input
				$("*[name='permission']").prop("checked", false);
				$("*[name='id']").prop("checked", false);
				$("*[name='input']").val("");
				flag1 = 0;
			}
		}

	});
	$("#clear").click(function() {
		updatePermission.cleardata();
	});
	$("#clear1").click(function() {
		alterPermission.cleardata1();
	});

	$(".main-container .btn[name='save']").click(function() {
		toastr.success("数据更新中，请等待...");
		setTimeout(function() {
			rolePermission.insertRolePermission();
			updatePermission.hide();
			flag = 0;
			rolePermission.searchRolePermission();
			rolePermission.searchRolePermissionCount();
			rolePermission.refresh();
		}, 5000);

	});
	var nowonetemp = [];
	var preonetemp = [];
	var nowone;
	var preone;
	$(".main-container .btn[name='updatesave']").click(function() {
		alterPermission.updateRolePermissionName();
		var nowcontent = $(".main-container .permission-content*[name='newselect']");
		var nowchecked = nowcontent.find("*[name='permission']:checked");
		// console.log(nowchecked)
		nowone = nowchecked;
		preone = prechecked;
		// console.log(nowone)
		// alert(nowone)
		for (var j = 0; j < preone.length; j++) {
			for (var i = 0; i < nowone.length; i++) {
				// console.log(nowone)
				if (nowone[i].defaultValue == preone[j].defaultValue) {
					nowone[i].defaultValue = -1;
					preone[j].defaultValue = -1;
				}
			}
		}
		for (var i = 0; i < nowone.length; i++) {
			// console.log(nowone[i])
			nowonetemp.push($(nowone[i]).val());
			// console.log(nowonetemp)
		}
		for (var j = 0; j < preone.length; j++) {
			preonetemp.push($(preone[j]).val());
		}
		// alert(nowonetemp)
		alterPermission.updateRolePermissionMore();
		// alert(preonetemp)
		alterPermission.updateRolePermissionLess();

		setTimeout(function() {
			// 关闭面板并刷新
			alterPermission.hide();
			flag1 = 0;
			rolePermission.searchRolePermission();
			rolePermission.searchRolePermissionCount();
			toastr.success("数据更新成功！");
		}, 5000)
		rolePermission.searchRolePermission();
		rolePermission.searchRolePermissionCount();
		rolePermission.refresh();

	});

	var element = $(".tab-container .tab-content");
	element.find("*[name='prevBtn']").click(function() {
		var temp = $(this).parents(".page-list").find("#currentPage");
		var currentPage = temp.text();
		currentPage = new Number(currentPage);
		if (currentPage > 1) {
			// 请求Ajax并更新数据
			rolePermission.searchRolePermission({
				"page" : currentPage - 1
			});
			temp.text(currentPage - 1); // 当前页数减1
		}
		rolePermission.refresh();
	});
	element.find("*[name='nextBtn']").click(function() {
		var temp = $(this).parents(".page-list").find("#currentPage");
		var currentPage = temp.text();
		var totalPages = $(this).parents(".page-list").find("#totalPages").text();
		currentPage = new Number(currentPage);
		totalPages = new Number(totalPages);

		if (currentPage < totalPages) {
			// 请求Ajax并更新数据
			rolePermission.searchRolePermission({
				"page" : currentPage + 1
			});
			temp.text(currentPage + 1); // 当前页数加上1页
		}
		rolePermission.refresh();
	});

	element.find("*[name='delete']").click(function() {
		// 先删除
		rolePermission.deleteRoleById();
		// 再查询
		rolePermission.searchRolePermissionCount();
		var totalPages = $(this).parents(".tab-container").find("#totalPages").text();
		var currentPage = $(this).parents(".tab-container").find("#currentPage").text();
		totalPages = new Number(totalPages);
		currentPage = new Number(currentPage);
		if (currentPage > totalPages) {
			currentPage = totalPages;
		}
		$(this).parents(".tab-container").find("#currentPage").text(currentPage); // 更新当前页数
		rolePermission.searchRolePermission({
			"page" : currentPage
		});

	});

});