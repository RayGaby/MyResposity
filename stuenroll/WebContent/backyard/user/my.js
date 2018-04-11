$(function() {

	require.config({
		paths : {
			echarts : '../../js/echarts'
		}
	});

	var Slider = function() {

	}

	Slider.prototype.timer = null;
	Slider.prototype.auto = function() {
		throw "抽象方法";
	}
	Slider.prototype.next = function() {
		throw "抽象方法";
	}
	Slider.prototype.prev = function() {
		throw "抽象方法";
	}
	var SliderImpl = function() {

	}

	SliderImpl.prototype = new Slider(); // 继承父类

	SliderImpl.prototype.auto = function() {
		this.timer = setInterval(this.next, 3000);
	}
	SliderImpl.prototype.next = function() {
		$(".slider").animate({
			"margin-left" : -434 / 16 + "rem"
		}, 1000, function() {
			var first = $(".slider .item:first");
			var last = $(".slider .item:last");
			last.after(first);
			$(".slider").css("margin-left", "0");
		});

	}
	SliderImpl.prototype.prev = function() {
		var first = $(".slider .item:first");
		var last = $(".slider .item:last");
		first.before(last);
		$(".slider").css("margin-left", -434 / 16 + "rem");
		$(".slider").animate({
			"margin-left" : 0
		}, 1000);
	}

	/**
	 * 抽象的列表接口
	 */
	var I_List = function() {

	}

	/**
	 * 抽象方法
	 */
	I_List.prototype.loadOrganizationCloud = function() {
		throw "抽象方法";
	}
	I_List.prototype.loadProfessionRanking = function() {
		throw "抽象方法";
	}
	I_List.prototype.loadPersonalInfo = function() {
		throw "抽象方法";
	}
	I_List.prototype.searchFriend = function(json) {
		throw "抽象方法";
	}
	I_List.prototype.addFriend = function(json) {
		throw "抽象方法";
	}
	I_List.prototype.searchFriendCount = function() {
		throw "抽象方法";
	}
	I_List.prototype.deleteById = function(id) {
		throw "抽象方法";
	}
	I_List.prototype.friendTop = function(id) {
		throw "抽象方法";
	}
	I_List.prototype.searchFriendFromUser = function(json) {
		throw "抽象方法";
	}
	I_List.prototype.init = function(obj) {
		throw "抽象方法";
	}
	I_List.prototype.initadd = function(obj) {
		throw "抽象方法";
	}
	I_List.prototype.userOnLine = function() {
		throw "抽象方法";
	}
	I_List.prototype.userOnLineadd = function() {
		throw "抽象方法";
	}
	I_List.prototype.initPhoto = function() {
		throw "抽象方法";
	}

	/**
	 * 列表类
	 */
	var List = function() {

	}
	// 全局变量
	var search_switch = true;
	var timer_switch = 1;

	List.prototype = new I_List(); // 继承父类

	List.prototype.loadOrganizationCloud = function() {
		$.ajax({
			"url" : "/stuenroll/friend/searchKeyWords",
			"type" : "post",
			"dataType" : "json",
			"data" : null,
			"success" : function(json) {
				var Key = json.result.Key;
				var Numbers = json.result.Numbers;

				require([ 'echarts', 'echarts/chart/wordCloud' ], function(ec) {
					var myChart = ec.init($(".content3_2")[0], 'macarons');

					function createRandomItemStyle() {
						return {
							normal : {
								color : 'rgb('
										+ [ Math.round(Math.random() * 160), Math.round(Math.random() * 160),
												Math.round(Math.random() * 160) ].join(',') + ')'
							}
						};
					}

					var option = {
						title : {
							text : '关键词',
						// link: 'http://www.google.com/trends/hottrends'
						},
						tooltip : {
							show : true
						},
						series : [ {
							name : 'Key Words',
							type : 'wordCloud',
							size : [ '80%', '80%' ],
							textRotation : [ 0, 45, 90, -45 ],
							textPadding : 0,
							autoSize : {
								enable : true,
								minSize : 14
							},
							data : [ {
								name : "就业网",
								value : 2000,
								itemStyle : {
									normal : {
										color : 'black'
									}
								}
							} ]
						} ]
					};

					for (var i = 0; i < json.result.Numbers.length; i++) {

						option.series[0].data[i] = {
							"value" : eval("json.result.Numbers[" + i + "]"),
							"itemStyle" : createRandomItemStyle()
						};
					}

					for (var i = 0; i < json.result.Key.length; i++) {

						option.series[0].data[i].name = eval("json.result.Key[" + i + "]");
					}
					// 为echarts对象加载数据
					myChart.setOption(option);

				});
			},
			"error" : function() {
				toastr.error("系统异常");
			}
		});

	}
	List.prototype.loadProfessionRanking = function() {
		$.ajax({
			"url" : "/stuenroll/friend/searchProfessionWithOrganization",
			"type" : "post",
			"dataType" : "json",
			"data" : null,
			"success" : function(json) {
				var profession = json.result.专业;
				var count = json.result.人数;

				require([ 'echarts', 'echarts/chart/pie' ], function(ec) {
					var myChart = ec.init($(".statistics1")[0], 'macarons');

					var option = {
						title : {
							text : '',
							x : 'center'
						},
						tooltip : {
							trigger : 'item',
							formatter : "{a} <br/>{b} : {c} ({d}%)"
						},
						legend : {
							x : 'center',
							y : 'bottom',
							data : []
						},
						toolbox : {
							show : true,
							feature : {
								saveAsImage : {
									show : true
								}
							}
						},
						// calculable: true,
						series : [ {
							name : '半径模式',
							type : 'pie',
							radius : [ 20, 110 ],
							center : [ '25%', 200 ],
							roseType : 'radius',
							width : '40%', // for funnel
							max : 40, // for funnel
							itemStyle : {
								normal : {
									label : {
										show : false
									},
									labelLine : {
										show : false
									}
								},
								emphasis : {
									label : {
										show : true
									},
									labelLine : {
										show : true
									}
								}
							},
							data : [ {

							} ]
						}, {
							name : '面积模式',
							type : 'pie',
							radius : [ 30, 110 ],
							center : [ '75%', 200 ],
							roseType : 'area',
							x : '50%', // for funnel
							max : 40, // for funnel
							sort : 'ascending', // for funnel
							data : [ {

							} ]
						} ]
					};
					if (sessionStorage.organization == "辽宁省就业网") {
						option.title.text = "培训机构专业分布图";
					}
					else {
						option.title.text = sessionStorage.organization + "报名专业分布图";
					}
					for (var i = 0; i < json.result.人数.length; i++) {
						option.series[0].data[i] = {
							"value" : eval("json.result.人数[" + i + "]")
						};
						option.series[1].data[i] = {
							"value" : eval("json.result.人数[" + i + "]")
						};
					}
					for (var i = 0; i < json.result.专业.length; i++) {
						option.series[0].data[i].name = eval("json.result.专业[" + i + "]");
						option.series[1].data[i].name = eval("json.result.专业[" + i + "]");
						option.legend.data[i] = eval("json.result.专业[" + i + "]");
					}
					// 为echarts对象加载数据
					myChart.setOption(option);
				});
			},
			"error" : function() {
				toastr.error("系统异常");
			}
		});
	}
	List.prototype.loadPersonalInfo = function() {
		$.ajax({
			"url" : "/stuenroll/user/searchUserInfoAtMyHomepage",
			"type" : "post",
			"dataType" : "json",
			"data" : null,
			"success" : function(json) {
				$("#personal-name").text(sessionStorage.name);
				$("#personal-sex").text(json.result.sex);
				$("#personal-role").text(sessionStorage.role);
				$("#personal-organization").text(sessionStorage.organization);
				$("#personal-tel").text(json.result.tel);
				$("#personal-wechat").text(json.result.wechat);
				$("#personal-pid").text(json.result.pid);
				$("#personal-email").text(json.result.email);
			},
			"error" : function() {
				toastr.error("系统异常");
			}
		});
	}

	List.prototype.searchFriendFromUser = function(json) {
		$.ajax({
			"url" : "/stuenroll/friend/searchFriendFromUser",
			"type" : "post",
			"dataType" : "json",
			"data" : json,
			"async" : false,
			"success" : function(json) {
				var data = json.result;
				if (data == null) {
					alert("没有找到该好友!");
					timer_switch = 0;
					return;
				}
				var item = $(".myfriends-container .list-container");
				// 清空好友信息
				item.find(".list-item").remove();
				item.find(".add-list-item").remove();

				var temp = "";

				temp += "<dt class='add-list-item' >";
				temp += "<div class='add-photo-container'>";
				temp += "<img src='" + "data:image;base64," + data.photo_base64 + "' class='add-user-photo'/>";
				temp += "<div class='add-small-circle add-circle-red'></div>";
				temp += "</div>";
				temp += "<div class='add-personal-info'>";
				temp += "<dl class='add-info-container'>";
				temp += "<dt class='add-myfriends-name'>" + data.username + "</dt>";
				temp += "<dt class='add-myfriends-role'>" + data.role + "</dt>";
				temp += "<dt class='add-myfriends-organization'>" + data.organization + "</dt>";
				temp += "</dl>";
				temp += "</div>";
				temp += "<div class='add-action'>";
				temp += "<input type='button' name='add' id='add' value='添加' class='add' />";
				temp += "</div>";
				temp += "</dt>";
				item.append(temp);
				list.initadd(item);
			},
			"error" : function() {
				toastr.error("系统异常");
			}
		});
	}
	List.prototype.searchFriend = function(json) {
		// 显示好友列表
		$.ajax({
			"url" : "/stuenroll/friend/searchFriend",
			"type" : "post",
			"dataType" : "json",
			"data" : json,
			"async" : false,
			"success" : function(json) {
				var data = json.result;
				if (data.length == 0) {
					search_switch = false;
					return;
				}
				var item = $(".myfriends-container .list-container");
				// 清空好友信息
				item.find(".list-item").remove();
				item.find(".add-list-item").remove();

				var temp = "";
				for (var i = 0; i < data.length; i++) {
					var one = data[i];

					temp += "<dt class='list-item' >";
					temp += "<div class='photo-container'>";
					temp += "<img src='" + "data:image;base64," + one.photo_base64 + "' class='user-photo' />";
					temp += "<div class='small-circle circle-red'></div>";
					temp += "</div>";
					temp += "<div class='personal-info'>";
					temp += "<dl class='info-container'>";
					temp += "<dt class='myfriends-name'>" + one.friendname + "</dt>";
					temp += "<dt class='myfriends-role'>" + one.role + "</dt>";
					temp += "<dt class='myfriends-organization'>" + one.organization + "</dt>";
					temp += "</dl>";
					temp += "</div>";
					temp += "<div class='action action-hidden'>";
					temp += "<img src='../../img/star.png' class='star'/>";
					temp += "<img src='../../img/furcation_fff.png' class='furcation'/>";
					temp += "<a class='friend-id'>" + one.id + "</a>";
					temp += "</div>";
					temp += "</dt>";

				}
				item.append(temp);
				list.init(item);
			},
			"error" : function() {
				toastr.error("系统异常");
			}
		});
	}
	List.prototype.addFriend = function() {
		// 弹出确认对话框
		var bool = confirm("是否要添加该好友？");
		if (bool == false) {
			return;
		}
		var friendname = $(".add-list-item").find(".add-myfriends-name").text();
		var friendrole = $(".add-list-item").find(".add-myfriends-role").text();
		var friendorganization = $(".add-list-item").find(".add-myfriends-organization").text();

		$.ajax({
			"url" : "/stuenroll/friend/addFriend",
			"type" : "post",
			"dataType" : "json",
			"data" : {
				"friendname" : friendname,
				"role" : friendrole,
				"organization" : friendorganization
			},
			"success" : function(json) {
				if (json.result) {
					alert("添加好友成功");
					toastr.success("添加好友成功");
				}
			},
			"error" : function() {
				toastr.error("系统异常");
			}
		});
	}

	List.prototype.searchFriendCount = function() {
		$.ajax({
			"url" : "/stuenroll/friend/searchFriendCount",
			"type" : "post",
			"dataType" : "json",
			"data" : null,
			"async" : false,
			"success" : function(json) {
				var count = json.result; // 总记录数
				var totalPages = (count % 10 == 0) ? count / 10 : Math.floor(count / 10) + 1;
				// 记录总页数
				$("#totalPages").text(totalPages);
			},
			"error" : function() {
				toastr.error("系统异常");
			}
		});
	}
	List.prototype.deleteById = function(id) {
		// 弹出确认对话框
		var bool = confirm("确定要删除该好友？");
		if (bool == false) {
			return;
		}
		$.ajax({
			"url" : "/stuenroll/friend/deleteById",
			"type" : "post",
			"dataType" : "json",
			// "traditional" : true, //发送数组JSON格式
			"async" : false,
			"data" : {
				"id" : id
			},
			"success" : function(json) {
				alert("该好友已删除！");
				toastr.success("该好友已删除！");
			},
			"error" : function() {
				toastr.error("系统异常");
			}
		});
	}
	List.prototype.friendTop = function(id) {
		$.ajax({
			"url" : "/stuenroll/friend/friendTop",
			"type" : "post",
			"dataType" : "json",
			"async" : false,
			"data" : {
				"id" : id
			},
			"success" : function(json) {
				list.searchFriend();
				list.searchFriendCount();
				list.userOnLine();
			},
			"error" : function() {
				toastr.error("系统异常");
			}
		});
	}
	List.prototype.initadd = function(obj) {
		var $item = obj.find(".add-list-item");
		// 鼠标选中列表项
		// 添加好友点击事件
		$item.find(".add-action").unbind("click");
		$item.find(".add-action").click(function(event) {
			list.addFriend();
			event.stopPropagation();
		});
	}

	List.prototype.init = function(obj) {
		var $item = obj.find(".list-item");
		// 鼠标选中列表项
		$item.unbind("click");
		$item.click(function(event) {

			$(".myfriends-container .list-item").removeClass("bgc-active");
			$(this).addClass("bgc-active");
			$(".myfriends-container .action").addClass("action-hidden");
			var action = $(this).find(".action");
			$(action).removeClass("action-hidden");
			event.stopPropagation();
		});
		// 删除好友点击事件
		$item.find(".furcation").unbind("click");
		$item.find(".furcation").click(function(event) {

			var id = $(this).parents(".action").find(".friend-id").text();
			// 先删除
			list.deleteById(id);
			// 再查询
			list.searchFriendCount();
			var totalPages = $(this).parents(".myfriends-container").find("#totalPages").text();

			var currentPage = $(this).parents(".myfriends-container").find("#currentPage").text();

			totalPages = new Number(totalPages);
			currentPage = new Number(currentPage);

			if (currentPage > totalPages) {
				currentPage = totalPages;

			}

			$(this).parents(".myfriends-container").find("#currentPage").text(currentPage); // 更新当前页数

			list.searchFriend({
				"page" : currentPage
			});
			list.userOnLine();

			event.stopPropagation();
		});
		// 好友列表中置顶点击事件
		$item.find(".star").unbind("click");
		$item.find(".star").click(function(event) {
			var id = $(this).parents(".action").find(".friend-id").text();
			list.friendTop(id);

			var item = $(".myfriends-container .list-item");
			$(".myfriends-container .list-item").removeClass("bgc-active");
			$(item[0]).addClass("bgc-active");
			$(".myfriends-container .action").addClass("action-hidden");
			$(item[0]).find(".action").removeClass("action-hidden");

			$(".myfriends-container").find("#currentPage").text(1);

			event.stopPropagation();
		});
	}
	List.prototype.userOnLine = function() {
		// 判断用户是否在线
		var $item = $(".myfriends-list").find(".list-item");

		for (var i = 0; i < $item.length; i++) {
			var username = $($item[i]).find(".myfriends-name").text();
			$.ajax({
				"url" : "/stuenroll/friend/userOnLine",
				"type" : "post",
				"dataType" : "json",
				"async" : false,
				"data" : {
					"username" : username
				},
				"success" : function(json) {
					if (json.result) {
						$($item[i]).find(".small-circle").removeClass("circle-red");
						$($item[i]).find(".small-circle").addClass("circle-green");
					}
					else {
						$($item[i]).find(".small-circle").removeClass("circle-green");
						$($item[i]).find(".small-circle").addClass("circle-red");
					}

				},
				"error" : function() {
					toastr.error("系统异常");
				}
			});
		}
	}

	List.prototype.initPhoto = function() {

		$.ajax({
			"url" : "/stuenroll/friend/initPersonalPhoto",
			"type" : "post",
			"dataType" : "json",
			"data" : {
				"name" : sessionStorage.username
			},
			"success" : function(json) {
				// 加载用户头像
				var item = $(".personal-infomation .photo-container");
				// 清空头像
				item.find(".personal-photo").remove();

				var temp = "<img src='" + "data:image;base64," + json.result + "' class='personal-photo' />";
				item.append(temp);

			},
			"error" : function() {
				toastr.error("系统异常");
			}
		});

	}

	List.prototype.userOnLineadd = function() {
		// 判断用户是否在线
		var $item = $(".myfriends-list").find(".add-list-item");
		var username = $item.find(".add-myfriends-name").text();
		$.ajax({
			"url" : "/stuenroll/friend/userOnLine",
			"type" : "post",
			"dataType" : "json",
			"async" : false,
			"data" : {
				"username" : username
			},
			"success" : function(json) {
				if (json.result) {
					$item.find(".add-small-circle").removeClass("add-circle-red");
					$item.find(".add-small-circle").addClass("add-circle-green");
				}
				else {
					$item.find(".add-small-circle").removeClass("add-circle-green");
					$item.find(".add-small-circle").addClass("add-circle-red");
				}
			},
			"error" : function() {
				toastr.error("系统异常");
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
		if (key == "List") {
			return new List();
		}
		else if (key == "SliderImpl") {
			return new SliderImpl();
		}
	}
	var list = factory("List");

	var slider = factory("SliderImpl");

	slider.auto();

	list.searchFriend();

	list.searchFriendCount();

	list.loadPersonalInfo();

	list.loadProfessionRanking();

	list.loadOrganizationCloud();

	list.userOnLine();

	list.initPhoto();
	
	// 每15秒更新一次用户在线状态
	var timer1 = setInterval(function() {
		list.searchFriend();
		list.searchFriendCount();
		list.userOnLine();
	}, 15000);

	// 点击上一页
	$(".myfriends-bottom").find("*[name='prevBtn']").click(function() {
		var temp = $(this).parents(".page-list").find("#currentPage");
		var currentPage = temp.text();
		currentPage = new Number(currentPage);
		if (currentPage > 1) {
			list.searchFriend({
				"page" : currentPage - 1
			});
			list.userOnLine();
			temp.text(currentPage - 1); // 当前页数减去1页
		}
	});
	// 点击下一页
	$(".myfriends-bottom").find("*[name='nextBtn']").click(function() {
		var temp = $(this).parents(".page-list").find("#currentPage");
		var currentPage = temp.text();

		var totalPages = $(this).parents(".page-list").find("#totalPages").text();

		currentPage = new Number(currentPage);
		totalPages = new Number(totalPages);

		if (currentPage < totalPages) {
			list.searchFriend({
				"page" : currentPage + 1
			});

			list.userOnLine();
			temp.text(currentPage + 1); // 当前页数加上1页
		}
	});
	// 点击查询好友
	var searchFlag = true;
	$(".myfriends-container .title-mark").click(function() {
		if (searchFlag == true) {
			// 显示查询好友面板
			$("#search").val("");
			$(".myfriends-list .myfriends-search").css("top", 0 + "rem");

			var item = $(".myfriends-container .list-item");

			searchFlag = false;
		}
		else {
			// 收起查询好友面板
			$(".myfriends-list .myfriends-search").css("top", -100 + "rem");

			searchFlag = true;
		}

	});

	// 查找好友删除按钮
	$(".myfriends-search .search-furcation").click(function() {
		$("#search").val("");
	});

	// 查找好友查找按钮
	$(".myfriends-search .magnifier").click(function() {
		var friendname = $("#search").val();
		$(".myfriends-list .myfriends-search").css("top", -100 + "rem");
		searchFlag = true;
		// 首先在好友列表中查询
		list.searchFriend({
			"friendname" : friendname
		});
		list.userOnLine();

		if (search_switch == false) {
			// 好友列表中不存在然后在user表里查询

			list.searchFriendFromUser({
				"username" : friendname
			});
			list.userOnLineadd();

			if (timer_switch == 0) {
				// user表中也不存在，没有找到该好友
				list.searchFriend();
				list.searchFriendCount();
				list.userOnLine();
				$(".page-list").find("#currentPage").text(1);
			}
			else if (timer_switch == 1) {
				// 在user表中找到
				$(".page-list").find("#totalPages").text(1);
				$(".page-list").find("#currentPage").text(1);
				var timer = setInterval(function() {
					var i = 0;
					if (i == 0) {
						list.searchFriend();
						list.searchFriendCount();
						list.userOnLine();
						clearInterval(timer);
					}
				}, 9000);
			}

		}
		else {
			// 在好友列表中找到
			$(".page-list").find("#totalPages").text(1);
			$(".page-list").find("#currentPage").text(1);
			var timer = setInterval(function() {
				var i = 0;
				if (i == 0) {
					list.searchFriend();
					list.searchFriendCount();
					list.userOnLine();
					clearInterval(timer);
				}
			}, 9000);

		}
		timer_switch = 1;
		search_switch = true;

	});

	$(".next-top").click(function() {
		clearInterval(slider.timer);
		slider.next();
		slider.auto();
	});

	$(".prev-top").click(function() {
		clearInterval(slider.timer);
		slider.prev();
		slider.auto();
	});
	$(".chart-container").click(function() {

		$(".myfriends-container .list-item").removeClass("bgc-active");
		$(".myfriends-container .action").addClass("action-hidden");
	});

});