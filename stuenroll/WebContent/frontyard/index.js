$(function() {
	/**
	 * 抽象的报名接口
	 */
	var I_RegisterForm = function() {

		}
		/**
		 * 抽象方法
		 */
	I_RegisterForm.prototype.downloadMyReport = function() {
		throw "抽象方法";
	}
	I_RegisterForm.prototype.showMyOrganization = function() {
		throw "抽象方法";
	}
	I_RegisterForm.prototype.showMyProfession = function() {
		throw "抽象方法";
	}

	/**
	 * 报名类
	 */
	var RegisterForm = function() {

	}

	RegisterForm.prototype = new I_RegisterForm(); //继承父类

	RegisterForm.prototype.downloadMyReport = function() {
		//下载报名表
		$downloadModal.find("form").submit(); //提交表单
		$downloadModal.find("*[name='close']").click(); //关闭界面
	}
	RegisterForm.prototype.showMyOrganization = function() {
		$(".organization-list .organization-item").remove();
		$.ajax({
			"url": "/stuenroll/register/searchOrgnizationJoinInYearAtDropDown",
			"type": "post",
			"dataType": "json",
			"data": {
				"year": new Date().getFullYear()
			},
			"success": function(json) {
				var data = json.result;
				var temp = "";
				for (var i = 0; i < data.length; i++) {
					var one = data[i];
					temp += "<dt class='organization-item'>" + one.name + "</dt>";

				}
				$("#showOrganization").css("height", (130 + 37 * i )/ 16 + "rem");
				$(".modal .organization-list").append(temp);
			},
			"error": function() {
				toastr.error("系统异常");
			}
		});
	}
	RegisterForm.prototype.showMyProfession = function() {
		$(".profession-list .profession-item").remove();
		$.ajax({
			"url": "/stuenroll/register/searchProfessionInYearAtDropDown",
			"type": "post",
			"dataType": "json",
			"data": {
				"year": new Date().getFullYear()
			},
			"success": function(json) {
				var data = json.result;
				var temp = "";
				for (var i = 0; i < data.length; i++) {
					var one = data[i];
					temp += "<dt class='profession-item'>" + one.name + "</dt>";

				}
				$("#showProfession").css("height", (130 + 37 * i )/ 16 + "rem");
				$(".modal .profession-list").append(temp);
			},
			"error": function() {
				toastr.error("系统异常");
			}
		});
	}

	/**
	 * 工厂方法
	 * 
	 * @param {Object} key
	 */
	function factory(key) {
		if (key == "RegisterForm") {
			return new RegisterForm();
		}
	}

	var registerform = factory("RegisterForm");
	
	$downloadModal = $("#downloadModal"); //下载我的报名表弹出层
	$showOrganization = $("#showOrganization"); //培训机构列表弹出层
	$showProfession = $("#showProfession"); //培训专业弹出层
	var $myReportLink = $("#myReportLink"); //下载我的报名表连接
	var $pid = $downloadModal.find("#pid"); //身份证文本框
	var $download = $downloadModal.find("*[name='download']"); //下载按钮

	//下载我的报名表点击事件
	$("#myReportLink").click(function() {
		//var $downloadModal = $("#downloadModal");
		modal.show($downloadModal); //显示弹出层
		$pid.val(null); //清空文本框内容
		$pid.focus(); //身份证文本框获得焦点
	});
	//培训机构列表点击事件
	$("#organizationList").click(function() {
		modal.show($showOrganization); //显示弹出层
		registerform.showMyOrganization();
	});
	//培训专业列表点击事件
	$("#professionList").click(function() {
		modal.show($showProfession); //显示弹出层
		registerform.showMyProfession();
	});
	
	//身份证文本框输入事件
	$pid.keyup(function() {
		if (checkPid($pid.val())) {
			//发送Ajax验证后台
			$.ajax({
				"url": "/stuenroll/register/hasRecord",
				"type": "post",
				"dataType": "json",
				"data": {
					"pid": $pid.val()
				},
				"success": function(json) {
					if (json.result) {
						$pid.removeClass("error");
						$download.removeAttr("disabled");
					} else {
						$pid.addClass("error");
					}
				},
				"error": function() {
					$pid.addClass("error");
					$download.attr("disabled", "disabled");
					//TODO toastr   
				}
			});
		} else {
			$pid.addClass("error");
			$download.attr("disabled", "disabled");
		}
	});
	//弹出层下载按钮点击事件        
	$download.click(function() {
		registerform.downloadMyReport();
	});

});