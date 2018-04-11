$(function() {
	/**
	 * 抽象的报名接口
	 */
	var I_Register = function() {

	}
	/**
	 * 抽象方法
	 */
	// 加载学历、专业、健康状况、培训地点下拉列表数据
	I_Register.prototype.searchSelectableEducation = function() {
		throw "抽象方法";
	}
	I_Register.prototype.searchSelectableMajor = function() {
		throw "抽象方法";
	}
	I_Register.prototype.searchSelectableHealthy = function() {
		throw "抽象方法";
	}
	I_Register.prototype.searchSelectablePolitics = function() {
		throw "抽象方法";
	}
	I_Register.prototype.searchSelectablePlace = function() {
		throw "抽象方法";
	}

	// 加载民族、毕业年份下拉列表数据
	I_Register.prototype.init = function() {
			throw "抽象方法";
		}
	// 加载申报专业下拉列表数据
	I_Register.prototype.loadProfessionDropDown = function() {
			throw "抽象方法";
		}
		// 加载培训机构下拉列表数据
	I_Register.prototype.loadOrganizationDropWithProfessionDown = function() {
			throw "抽象方法";
		}
	I_Register.prototype.register = function() {
			throw "抽象方法";
		}
	/**
	 * 报名类
	 */
	var Register = function() {

	}

	Register.prototype = new I_Register(); // 继承父类
	
	Register.prototype.searchSelectableEducation = function() {
		$.ajax({
			"url": "/stuenroll/register/searchSelectableEducation",
			"type": "post",
			"dataType": "json",
// "data": json,
			// <理解错误>若不加，将导致不可控的赋值错误(我太懒233333，变量名起的一样QAQ，so你懂得_(:з」∠)_。。@404)
			// 若不加，将导致不可控的json接收错误(_(:з」∠)_。。@404)
			"async": false,
			"success": function(json) {
				var data = json.result;
				var options = JSON.stringify(data);
				// alert(options);
				var arr = options.split("('");
				var selectableOptions = arr[1];
				arr = selectableOptions.split("')");
				var offers = arr[0].split("','");
				var education = $("#education");
				var temp = "";
				// alert(offers[1]);
				// alert(offers.length);
				for (var i = 0; i < offers.length; i++) {
					// temp += "<option value='" + offers[i] + "'>" + offers[i]
					// + "</option>";
					temp += "<li class='dropdown-item'>" + offers[i] + "</li>";
					// alert(temp);
				}
				education.find(".dropdown-list").append(temp);
				DropDown.init(education); // 初始化
				// 学历列表选项点击事件
				$("#education").find(".dropdown-item").click(function() {
					// 拿出该列表选项的值
					var educationVal=$(this).parents(".dropdown").find(".value").text();
				});
			},
			"error": function() {
				toastr.error("系统异常2");
			}
		});
	}
	Register.prototype.searchSelectableMajor = function() {
		$.ajax({
			"url": "/stuenroll/register/searchSelectableMajor",
			"type": "post",
			"dataType": "json",
// "data": json,
			"async": false,
			"success": function(json) {
				var data = json.result;
				var options = JSON.stringify(data);
				var arr = options.split("('");
				var selectableOptions = arr[1];
				arr = selectableOptions.split("')");
				var offers = arr[0].split("','");
				var major = $("#major");
				var temp = "";
				for (var i = 0; i < offers.length; i++) {
					temp += "<li class='dropdown-item'>" + offers[i] + "</li>";
				}
				major.find(".dropdown-list").append(temp);
				DropDown.init(major); // 初始化
				// 所学专业列表选项点击事件
				$("#major").find(".dropdown-item").click(function() {
					// 拿出该列表选项的值
					var majorVal=$(this).parents(".dropdown").find(".value").text();
				});
				
			},
			"error": function() {
				toastr.error("系统异常3");
			}
		});
	}
	Register.prototype.searchSelectableHealthy = function() {
		$.ajax({
			"url": "/stuenroll/register/searchSelectableHealthy",
			"type": "post",
			"dataType": "json",
// "data": json,
			"async": false,
			"success": function(json) {
				var data = json.result;
				var options = JSON.stringify(data);
				var arr = options.split("('");
				var selectableOptions = arr[1];
				arr = selectableOptions.split("')");
				var offers = arr[0].split("','");
				var healthy = $("#healthy");
				var temp = "";
				for (var i = 0; i < offers.length; i++) {
					temp += "<li class='dropdown-item'>" + offers[i] + "</li>";
				}
				healthy.find(".dropdown-list").append(temp);
				DropDown.init(healthy); // 初始化
				// 健康状况列表选项点击事件
				$("#healthy").find(".dropdown-item").click(function() {
					// 拿出该列表选项的值
					var healthyVal=$(this).parents(".dropdown").find(".value").text();
				});
			},
			"error": function() {
				toastr.error("系统异常4");
			}
		});
	}
	Register.prototype.searchSelectablePolitics = function() {
		$.ajax({
			"url": "/stuenroll/register/searchSelectablePolitics",
			"type": "post",
			"dataType": "json",
// "data": json,
			"async": false,
			"success": function(json) {
				var data = json.result;
				var options = JSON.stringify(data);
				var arr = options.split("('");
				var selectableOptions = arr[1];
				arr = selectableOptions.split("')");
				var offers = arr[0].split("','");
				var politics = $("#politics");
				var temp = "";
				for (var i = 0; i < offers.length; i++) {
					temp += "<li class='dropdown-item'>" + offers[i] + "</li>";
				}
				politics.find(".dropdown-list").append(temp);
				DropDown.init(politics); // 初始化
				// 政治面貌列表选项点击事件
				$("#politics").find(".dropdown-item").click(function() {
					// 拿出该列表选项的值
					var politicsVal=$(this).parents(".dropdown").find(".value").text();
				});
			},
			"error": function() {
				toastr.error("系统异常5");
			}
		});
	}
	Register.prototype.searchSelectablePlace = function() {
		$.ajax({
			"url": "/stuenroll/register/searchSelectablePlace",
			"type": "post",
			"dataType": "json",
// "data": json,
			"async": false,
			"success": function(json) {
				var data = json.result;
				var options = JSON.stringify(data);
				var arr = options.split("('");
				var selectableOptions = arr[1];
				arr = selectableOptions.split("')");
				var offers = arr[0].split("','");
				var place = $("#place");
				var temp = "";
				for (var i = 0; i < offers.length; i++) {
					temp += "<li class='dropdown-item'>" + offers[i] + "</li>";
				}
				place.find(".dropdown-list").append(temp);
				DropDown.init(place); // 初始化
				// 培训地点列表选项点击事件
				$("#place").find(".dropdown-item").click(function() {
					// 拿出该列表选项的值
					var placeVal=$(this).parents(".dropdown").find(".value").text();
				});
			},
			"error": function() {
				toastr.error("系统异常6");
			}
		});
	}
	Register.prototype.init = function() {
// //初始化政治面貌列表选项
// var $politics = $("#politics");
// var nation=["中共党员","中共预备党员","共青团员","民革党员","民盟盟员","民建会员","民进会员",
// "农工党党员","致公党党员","九三学社社员","台盟盟员","无党派民主人士","群众(现称普通公民)"];
// var temp = "";
// for (var i = 0; i < nation.length; i++) {
// temp += "<li class='dropdown-item'>" + nation[i] + "</li>";
// }
// $politics.find(".dropdown-list").append(temp);
// DropDown.init($politics); //初始化政治面貌列表
//		
// //初始化所学专业列表选项
// var $major = $("#major");
// var major=["哲学","经济学","法学","教育学","文学","历史学","理学",
// "农学","医学","管理学","工学","军事学"];
// var temp = "";
// for (var i = 0; i < major.length; i++) {
// temp += "<li class='dropdown-item'>" + major[i] + "</li>";
// }
// $major.find(".dropdown-list").append(temp);
// DropDown.init($major); //初始化所学专业列表
		
		// 初始化毕业年份列表选项
		var $graduateYear = $("#graduateYear");		
		var graduateYear=["2005","2006","2007","2008","2009","2010","2011","2012","2013","2014","2015","2016"];			
		var temp = "";
		for (var i = 0; i < graduateYear.length; i++) {
			temp += "<li class='dropdown-item'>" + graduateYear[i] + "</li>";
		}
		$graduateYear.find(".dropdown-list").append(temp);
		DropDown.init($graduateYear); // 初始化毕业年份列表
		// 毕业年份列表选项点击事件
		$("#graduateYear").find(".dropdown-item").click(function() {
			// 拿出该列表选项的值
			var graduateYearVal=$(this).parents(".dropdown").find(".value").text();
		});
		
		// 初始化民族列表选项
		var $nation = $("#nation");		
		var nation=["汉族","壮族","藏族","裕固族","彝族","瑶族","锡伯族","乌孜别克族"
		            ,"维吾尔族","佤族","土家族","土族","塔塔尔族","塔吉克族","水族",
		            "畲族","撒拉族","羌族","普米族","怒族","纳西族","仫佬族","苗族",
		            "蒙古族","门巴族","毛南族","满族","珞巴族","僳僳族","黎族","拉祜族",
		            "柯尔克孜族","景颇族","京族","基诺族","回族","赫哲族","朝鲜族","哈萨克族",
		            "哈尼族","仡佬族","高山族","鄂温克族","俄罗斯族","鄂伦春族","独龙族","东乡族",
		            "侗族","德昂族","傣族","达斡尔族","布依族","布朗族","保安族","白族","阿昌族"];			

		var temp = "";
		for (var i = 0; i < nation.length; i++) {
			temp += "<li class='dropdown-item'>" + nation[i] + "</li>";
		}
		$nation.find(".dropdown-list").append(temp);
		DropDown.init($nation); // 初始化民族列表
		// 民族列表选项点击事件
		$("#nation").find(".dropdown-item").click(function() {
			// 拿出该列表选项的值
			var nationVal=$(this).parents(".dropdown").find(".value").text();
		});
		
		// 性别列表选项点击事件
		$("#sex").find(".dropdown-item").click(function() {
			// 拿出该列表选项的值
			var sexVal=$(this).parents(".dropdown").find(".value").text();
		});

		
	}
	Register.prototype.loadProfessionDropDown = function() {
		var $profession = $("#profession");
		var $organization = $("#organization");
		// 删除专业列表和机构列表中的选项
		$profession.find(".dropdown-item").remove();
		$organization.find(".dropdown-item").remove();
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
					
					temp += "<li class='dropdown-item' data-id='" + one.id + "'>" + one.name + "</li>";

				}
				$profession.find(".dropdown-list").append(temp);
				DropDown.init($profession); // 初始化专业列表
				// 专业列表点击之后清空机构列表
				$profession.click(function() {
					$organization.find(".value").text("- 选择 -");
				});
				// 专业列表选项点击事件
				$profession.find(".dropdown-item").click(function() {
					register.loadOrganizationDropWithProfessionDown();
					// 拿出该列表选项的值
					var professionVal=$(this).parents(".dropdown").find(".value").text();
				});
			},
			"error": function() {
				toastr.error("系统异常7");
			}
		});
	}
	Register.prototype.loadOrganizationDropWithProfessionDown = function() {

			var $organization = $("#organization");
			// 清空列表中的全部内容
			$organization.find(".dropdown-item").remove();
			$.ajax({
				"url": "/stuenroll/register/searchOrgnizationJoinInYearWithProfessionAtDropDown",
				"type": "post",
				"dataType": "json",
				"data": {
					"year": new Date().getFullYear(),
					"professionId": $("#profession .dropdown-item-active").data("id")
				},
				"success": function(json) {

					var data = json.result;
					var temp = "";
					for (var i = 0; i < data.length; i++) {
						var one = data[i];
						temp += "<li class='dropdown-item' data-id='" + one.id + "'>" + one.name + "</li>";
					}
					$("#organization .dropdown-list").append(temp);
					DropDown.init($organization); // 初始化机构列表
					// 机构列表选项点击事件
					$organization.find(".dropdown-item").click(function() {
						// 拿出该列表选项的值
						var organizationVal=$(this).parents(".dropdown").find(".value").text();
					});
				},
				"error": function() {
					toastr.error("系统异常8");
				}
			});
	}
	Register.prototype.register = function() {
		// 检查每个input标签里的值满足正则表达式且下拉列表都不为空
		var bool = true;
		var input = $(".register-list .input");
		
		input.each(function(i, one) {
			if (one.id == "pid") {
				bool = bool && checkPid($(one).val());
			}
			else {
				bool = bool && one.checkValidity();
			}
		});
		
		var temp=$(".dropdown").find(".value");
		for (var i = 0; i < temp.length; i++) {
			bool = bool && ($(temp[i]).text()!="- 选择 -");
		}
		if (!bool) {
			toastr.warning("内容错误或填写不完整，请核实填写的内容");
			return;
		}
		// TODO注册学生报名信息
		$.ajax({
			"url": "/stuenroll/register/registerRedis",
			"type": "post",
			"dataType": "json",
			"data": {
				"name": $("#name").val(),
				"sex": $("#sex").find(".value").text(),
				"nation": $("#nation").find(".value").text(),
				"pid": $("#pid").val(),
				"graduateSchool": $("#graduate_school").val(),
				"graduateYear": $("#graduateYear").find(".value").text(),
				"graduateDate": $("#graduateDate").val(),
				"education": $("#education").find(".value").text(),
				"major": $("#major").find(".value").text(),
				"healthy": $("#healthy").find(".value").text(),
				"politics": $("#politics").find(".value").text(),
				"birthday": $("#birthday").val(),
				"residentAddress": $("#residentAddress").val(),
				"permanentAddress": $("#permanentAddress").val(),
				"homeAddress": $("#homeAddress").val(),
				"tel": $("#tel").val(),
				"homeTel": $("#homeTel").val(),
				"email": $("#email").val(),
				"wechat": $("#wechat").val(),
				"profession": $("#profession").find(".value").text(),
				"organization": $("#organization").find(".value").text(),
				"place" : $("#place").find(".value").text(),
				"remark" : $("#remark").val(),
			},
			"success": function(json){
				if(json.result){
					toastr.success("注册成功");
					//清空列表
					 $("#name").val("");
					 $("#sex").find(".value").text("- 选择 -");
					 $("#nation").find(".value").text("- 选择 -");
					 $("#pid").val("");
					 $("#graduate_school").val("");
					 $("#graduateYear").find(".value").text("- 选择 -");
					 $("#graduateDate").val("");
					 $("#education").find(".value").text("- 选择 -");
					 $("#major").find(".value").text("- 选择 -");
					 $("#healthy").find(".value").text("- 选择 -");
					 $("#politics").find(".value").text("- 选择 -");
					 $("#birthday").val("");
				     $("#residentAddress").val("");
					 $("#permanentAddress").val("");
					 $("#homeAddress").val("");
					 $("#tel").val("");
					 $("#homeTel").val("");
					 $("#email").val("");
				     $("#wechat").val("");
				     $("#profession").find(".value").text("- 选择 -");
			         $("#organization").find(".value").text("- 选择 -");
				     $("#place").find(".value").text("- 选择 -");
				     $("#remark").val("");
					//location.href = "/stuenroll/backyard/login.html";
				}
				else{
					toastr.warning("注册失败");
				}
			},
			"error": function() {
				toastr.error("系统异常9");
			}
		});
	}
		/**
		 * 工厂方法
		 * 
		 * @param {Object}
		 *            key
		 */
	function factory(key) {
		if (key == "Register") {
			return new Register();
		}
	}

	var register = factory("Register");
	
	// 初始化性别下拉列表
	DropDown.initAll();
	
	register.init();
	var timer=setInterval(function() {
		var i=0;
		if(i==0){
			register.loadProfessionDropDown();
			clearInterval(timer);
		}
	}, 2000)
	
	register.searchSelectableEducation();	
	register.searchSelectableMajor();	
	register.searchSelectableHealthy();	
	register.searchSelectablePolitics();	
	register.searchSelectablePlace();	
	
	
	// 输入项键盘弹起事件
	$(".register .input").keyup(function() {
		if (this.id != "pid" && this.checkValidity()) {
			$(this).removeClass("error");
		}
		else {
			$(this).addClass("error");
		}

		if (this.id == "pid") {
			if (!checkPid($(this).val())) {
				$(this).addClass("error");
			}
			else {
				$(this).removeClass("error");
			}
		}
	});
	
	//判断该pid是否已注册输入时的键盘弹起事件
	$("#pid").keyup(function(){
		
		$.ajax({
			"url": "/stuenroll/register/hasRecord",
			"type": "post",
			"dataType": "json",
			"data": {
				"pid": $("#pid").val()
			},
			"success": function(json) {
				//alert(json.result)
				if (json.result) {
					toastr.warning("该身份证号已注册！");
					$("#pid").addClass("error");
					$("#register-btn").attr("disabled", "disabled");
				}
				else{
					$("#pid").removeClass("error");
					$("#register-btn").removeAttr("disabled", "disabled");
				}
			},
			"error": function() {
				//toastr.error("系统异常1"); 
			}
		});
	});
	// 注册按钮点击事件
	$("#register-btn").click(function(){
		
		register.register();

	});
	//取消按钮点击事件
	$("#cancel-btn").click(function(){
		 $("#name").val("");
		 $("#sex").find(".value").text("- 选择 -");
		 $("#nation").find(".value").text("- 选择 -");
		 $("#pid").val("");
		 $("#graduate_school").val("");
		 $("#graduateYear").find(".value").text("- 选择 -");
		 $("#graduateDate").val("");
		 $("#education").find(".value").text("- 选择 -");
		 $("#major").find(".value").text("- 选择 -");
		 $("#healthy").find(".value").text("- 选择 -");
		 $("#politics").find(".value").text("- 选择 -");
		 $("#birthday").val("");
	     $("#residentAddress").val("");
		 $("#permanentAddress").val("");
		 $("#homeAddress").val("");
		 $("#tel").val("");
		 $("#homeTel").val("");
		 $("#email").val("");
	     $("#wechat").val("");
	     $("#profession").find(".value").text("- 选择 -");
         $("#organization").find(".value").text("- 选择 -");
	     $("#place").find(".value").text("- 选择 -");
	     $("#remark").val("");
	});
});
