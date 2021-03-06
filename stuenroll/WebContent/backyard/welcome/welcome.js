$(function() {

	require.config({
		paths : {
			echarts : '../../js/echarts'
		}
	});

	/**
	 * 年度统计抽象接口
	 */
	var I_AnnualStatistics = function() {

	}

	I_AnnualStatistics.prototype.init = function() {
		throw "抽象方法";
	}

	var I_Statistics_4 =function(){
		
	}
	I_Statistics_4.prototype.init0 = function(){
		throw "error";
	}
	I_Statistics_4.prototype.init1 = function(){
		throw "error";
	}
	I_Statistics_4.prototype.init2 = function(){
		throw "error";
	}
	I_Statistics_4.prototype.init3 = function(){
		throw "error";
	}
	I_Statistics_4.prototype.init = function(){
		throw "error";
	}
	var I_Statistics_2 = function(){
		
	}
	I_Statistics_2.prototype.init = function(){
		throw "error";
	}
	
	var I_Info = function(){}
	I_Info.prototype.init = function(){
		throw "error";
	}
	
	var Info = function(){}
	Info.prototype = new I_Info();
	
	Info.prototype.init = function(){
		if (!checkPermission(["3_4","4_4"])) {
			return;
		}
		var date = new Date();
		var year = date.getFullYear();
		var month = date.getMonth()+1;
		var day = date.getDate();
		var desc = year+"/"+month+"/"+day;
		var analysis_desc = $(".info-container");
		analysis_desc.find(".analysis-desc").text(desc);
		var Tlabel = $(".header");
		Tlabel.find("[name='date-year']").text(year);
		Tlabel.find("[name='date-month']").text(month);
		$.ajax({
			"url":"/stuenroll/welcome/getApplyInfo",
			"type" : "post",
			"dataType" : "json",
			"data" : {
				"year" : year,
				"month":month
			},
			"success":function(json){
				var learnNumber = json.applyInfo.learnNumber;
				var organization = json.applyInfo.organization;
				var actualNumber = json.applyInfo.actualNumber;
				var applyNumber = json.applyInfo.applyNumber;
				var passNumber = json.applyInfo.passNumber;
				var ckeckNumber = json.applyInfo.ckeckNumber;
				var archiveNumber = json.applyInfo.archiveNumber;
				var quitNumber = json.applyInfo.quitNumber;
				var data = $(".info-container");
				data.find("[name='passNumber']").text(passNumber);
				data.find("[name='organization_name']").text(organization);
				data.find("[name='actualNumber']").text(actualNumber);
				data.find("[name='applyNumber']").text(applyNumber);
				data.find("[name='ckeckNumber']").text(ckeckNumber);
				data.find("[name='archiveNumber']").text(archiveNumber);
				if(quitNumber>0){
					data.find("[name='quitNumber']").text(quitNumber);
				}
				
				require([ 'echarts', 'echarts/chart/bar' ], function(ec) {
					var myChart = ec.init($(".analysis-table")[0], 'macarons');
					var option = {
							legend: {
						        data:['报名人数', '审查人数','归档人数','中退人数'],
						        x : 'center',
						        y : 'bottom'
						    },
						    xAxis : [
						        {
						            type : 'value',
						         	axisLine : {    // 轴线
						                show: false,
						            },
						          	axisTick : {    // 轴标记
						                show:false,
						            },
						         	axisLabel : {
						                show:false,
						            }
						        }
						    ], 
						    yAxis : [
						        {
						            type : 'category',
						            data : ['number'],
						          	axisLine : {    // 轴线
						                show: false,
						            },
						          	axisTick : {    // 轴标记
						                show:false,
						            },
						         	axisLabel : {
						                show:false,
						            }
						        }
						    ],  
						    grid:{
						    	x:'6%',
						        y:'20%',
						        width:'90%',
						    },
						    series : [
						        {
						            name:'报名人数',
						            type:'bar',
						            stack: '总量',
						            itemStyle : { normal: {label : {show: false, position: 'insideRight'}}},
						            data:[applyNumber]
						        },
						        {
						            name:'审查人数',
						            type:'bar',
						            stack: '总量',
						            itemStyle : { normal: {label : {show: false, position: 'insideRight'}}},
						            data:[ckeckNumber]
						        },
						        {
						            name:'归档人数',
						            type:'bar',
						            stack: '总量',
						            itemStyle : { normal: {label : {show: false, position: 'insideRight'}}},
						            data:[archiveNumber]
						        },
						        {
						            name:'中退人数',
						            type:'bar',
						            stack: '总量',
						            itemStyle : { normal: {label : {show: false, position: 'insideRight'}}},
						            data:[quitNumber]
						        }
						    ]
					};

					// 为echarts对象加载数据
					myChart.setOption(option);
				});
			},
			"error":function(){
			}
		});
	}
	function fn(str){
		var num;
		str>=10?num=str:num="0"+str;
		return num;
	}
	
	function creatTable(year,month){
		var year = year;
		var month = month;
		$.ajax({
			"url":"/stuenroll/welcome/getApplyInfo",
			"type" : "post",
			"dataType" : "json",
			"data" : {
				"year" : year,
				"month":month
			},
			"success":function(json){
				var learnNumber = json.applyInfo.learnNumber;
				//var organization = json.applyInfo.organization;
				var actualNumber = json.applyInfo.actualNumber;
				var applyNumber = json.applyInfo.applyNumber;
				var ckeckNumber = json.applyInfo.ckeckNumber;
				var archiveNumber = json.applyInfo.archiveNumber;
				var passNumber = json.applyInfo.passNumber;
				var quitNumber = json.applyInfo.quitNumber;
				var data = $(".info-container");
				data.find("[name='passNumber']").text(passNumber);
				//data.find("[name='organization_name']").text(organization);
				data.find("[name='actualNumber']").text(actualNumber);
				data.find("[name='applyNumber']").text(applyNumber);
				data.find("[name='ckeckNumber']").text(ckeckNumber);
				data.find("[name='archiveNumber']").text(archiveNumber);
				data.find("[name='quitNumber']").text(quitNumber);
				require([ 'echarts', 'echarts/chart/bar' ], function(ec) {
					var myChart = ec.init($(".analysis-table")[0], 'macarons');
					var option = {
							legend: {
						        data:['报名人数', '审查人数','归档人数','中退人数'],
						        x : 'center',
						        y : 'bottom'
						    },
						    xAxis : [
						        {
						            type : 'value',
						         	axisLine : {    // 轴线
						                show: false,
						            },
						          	axisTick : {    // 轴标记
						                show:false,
						            },
						         	axisLabel : {
						                show:false,
						            }
						        }
						    ], 
						    yAxis : [
						        {
						            type : 'category',
						            data : ['number'],
						          	axisLine : {    // 轴线
						                show: false,
						            },
						          	axisTick : {    // 轴标记
						                show:false,
						            },
						         	axisLabel : {
						                show:false,
						            }
						        }
						    ],  
						    grid:{
						    	x:'6%',
						        y:'20%',
						        width:'90%',
						    },
						    series : [
						        {
						            name:'报名人数',
						            type:'bar',
						            stack: '总量',
						            itemStyle : { normal: {label : {show: false, position: 'insideRight'}}},
						            data:[applyNumber]
						        },
						        {
						            name:'审查人数',
						            type:'bar',
						            stack: '总量',
						            itemStyle : { normal: {label : {show: false, position: 'insideRight'}}},
						            data:[ckeckNumber]
						        },
						        {
						            name:'归档人数',
						            type:'bar',
						            stack: '总量',
						            itemStyle : { normal: {label : {show: false, position: 'insideRight'}}},
						            data:[archiveNumber]
						        },
						        {
						            name:'中退人数',
						            type:'bar',
						            stack: '总量',
						            itemStyle : { normal: {label : {show: false, position: 'insideRight'}}},
						            data:[quitNumber]
						        }
						    ]
					};

					// 为echarts对象加载数据
					myChart.setOption(option);
				});
				
			},
			"error":function(){
			}
		});
	}

	function getTime(){
		var  date = new Date();
		var year = date.getFullYear();
		var month = date.getMonth()+1;
		var day = date.getDate();
		var weekday = date.getDay();
		var hour = date.getHours();
		var minute = date.getMinutes();
		var second = date.getSeconds();
		if(weekday==1){
			weekday = "星期一";
		}else if(weekday==2){
			weekday = "星期二";
		}else if(weekday==3){
			weekday = "星期三";
		}else if(weekday==4){
			weekday = "星期四";
		}else if(weekday==5){
			weekday = "星期五";
		}else if(weekday==6){
			weekday = "星期六";
		}else if(weekday==7){
			weekday = "星期日";
		}
		var dateTime = year+"年"+month+"月"+day+"日"+" ("+weekday+")";
		var DATE = $(".clock");
		DATE.find(".date").text(dateTime);
		DATE.find(".hour").text(fn(hour));
		DATE.find(".minute").text(fn(minute));
		DATE.find(".second").text(fn(second));
	} 
	
	var AnnualStatistics = function() {

	}

	AnnualStatistics.prototype = new I_AnnualStatistics();

	AnnualStatistics.prototype.init = function() {
		// 如果不具备相应权限，下列程序将不会执行
		if (!checkPermission(["3_4","4_4"])) {
			return;
		}

		var date = new Date();
		var year = date.getFullYear();
		var statistics = $(".annual-statistics");
		statistics.find(".year").text(year);

		// 更新进度
		var month = date.getMonth() + 1;
		var items = statistics.find(".step-list .step-item");
		items.removeClass("step-active");
		if (month >= 1 && month < 3) {
			$(items[0]).addClass("step-active");
		}
		else if (month >= 3 && month < 7) {
			$(items[1]).addClass("step-active");
		}
		else if (month >= 7 && month < 9) {
			$(items[2]).addClass("step-active");
		}
		else if (month >= 9 && month < 11) {
			$(items[3]).addClass("step-active");
		}
		else if (month >= 11) {
			$(items[4]).addClass("step-active");
		}

		// Ajax查询年度数据
		$.ajax({
			"url" : "/stuenroll/welcome/statisticsInYear",
			"type" : "post",
			"dataType" : "json",
			"data" : {
				"year" : year
			},
			"success" : function(json) {
				var list_1 = json.statistics.报名数据;
				var list_2 = json.statistics.审查数据;
				var list_3 = json.statistics.学习数据;
				var list_4 = json.statistics.中退数据;
				var list_5 = json.statistics.就业数据;

				require([ 'echarts', 'echarts/chart/line' ], function(ec) {
					var myChart = ec.init($(".statistics")[0], 'macarons');

					var option = {
						tooltip : {
							trigger : 'axis'
						},
						legend : {
							data : [ '报名人数', '审查人数', '学习人数', '中退人数', '就业人数' ]
						},
						toolbox : {
							show : true,
							feature : {
								saveAsImage : {
									show : true
								}
							}
						},
						calculable : false,
						xAxis : [ {
							type : 'category',
							boundaryGap : false,
							data : [ '1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月' ]
						} ],
						yAxis : [ {
							type : 'value'
						} ],
						series : [ {
							name : '报名人数',
							type : 'line',
							stack : '总量',
							itemStyle : {
								normal : {
									areaStyle : {
										type : 'default'
									}
								}
							},
							data : list_1
						}, {
							name : '审查人数',
							type : 'line',
							stack : '总量',
							itemStyle : {
								normal : {
									areaStyle : {
										type : 'default'
									}
								}
							},
							data : list_2
						}, {
							name : '学习人数',
							type : 'line',
							stack : '总量',
							itemStyle : {
								normal : {
									areaStyle : {
										type : 'default'
									}
								}
							},
							data : list_3
						}, {
							name : '中退人数',
							type : 'line',
							stack : '总量',
							itemStyle : {
								normal : {
									areaStyle : {
										type : 'default'
									}
								}
							},
							data : list_4
						}, {
							name : '就业人数',
							type : 'line',
							stack : '总量',
							itemStyle : {
								normal : {
									areaStyle : {
										type : 'default'
									}
								}
							},
							data : list_5
						} ]
					};

					// 为echarts对象加载数据
					myChart.setOption(option);
				});
			},
			"error" : function() {
				toastr.error("系统异常");
			}
		});
	}

	var Statistics_4 = function(){
		
	}
	Statistics_4.prototype = new I_Statistics_4();
	Statistics_4.prototype.init0 = function(){
		var date = new Date();
		var year = date.getFullYear();
		$.ajax({
			"url" : "/stuenroll/welcome/professionCount",
			"type" : "post",
			"dataType" : "json",
			//"async":false,
			"data" : {
				"year" : year
			},
			"success" : function(json) {
				var count_1_1 = json.statistics_4_1.pc_1;
				var count_1_2 = json.statistics_4_1.pc_2;
				var count_1_3 = json.statistics_4_1.pc_3;
				var count_1_4 = json.statistics_4_1.pc_4;
				var count_1_5 = json.statistics_4_1.pc_5;
				var name_1_1 = json.statistics_4_1.pn_1;
				var name_1_2 = json.statistics_4_1.pn_2;
				var name_1_3 = json.statistics_4_1.pn_3;
				var name_1_4 = json.statistics_4_1.pn_4;
				var name_1_5 = json.statistics_4_1.pn_5;
				require([ 'echarts', 'echarts/chart/pie' ], function(ec) {
					var myChart = ec.init($(".statistics-4-item")[0], 'macarons');
					var option = {
		                	title : {
		                		x:'15',
			        			y:'15',
				        		text: '报名专业数据分析',
				        		textStyle:{
				        			color:'#666666',
				        			fontSize:18,
				        			
				        		}
				   			},tooltip: {
								trigger: 'item',
								formatter: "{a} <br/>{b}: {d}%"
							},legend: {
				   		        x: 'left',
								y: 'bottom',
								bottom: 20,
				   		        data:[name_1_1,name_1_2,name_1_3,name_1_4,name_1_5]
				   		    },
				   		    calculable : false,
				   		    series : [
				   		        {
				   		            name:'专业',
				   		            type:'pie',
				   		            radius : ['50%', '70%'],
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
				   		                        show : true,
				   		                        position : 'center',
				   		                        textStyle : {
				   		                            fontSize : '30',
				   		                            fontWeight : 'bold'
				   		                        }
				   		                    }
				   		                }
				   		            },
				   		            data:[
				   		                {value:count_1_1, name:name_1_1},
				   		                {value:count_1_2, name:name_1_2},
				   		                {value:count_1_3, name:name_1_3},
				   		                {value:count_1_4, name:name_1_4},
				   		                {value:count_1_5, name:name_1_5}
				   		            ]
				   		        }
				   		    ]

		                };
					// 为echarts对象加载数据
					myChart.setOption(option);
				});
			},
			"error" : function() {
				toastr.error("系统异常");
			}
		});
	}
	Statistics_4.prototype.init1 = function(){
		var date = new Date();
		var year = date.getFullYear();
		$.ajax({
			"url" : "/stuenroll/welcome/PlaceCount",
			"type" : "post",
			"dataType" : "json",
			//"async":false,
			"data" : {
				"year" : year
			},
			"success" : function(json) {
				var count_2_1 = json.statistics_4_2.大连;
				var count_2_2 = json.statistics_4_2.沈阳;
				var count_2_3 = json.statistics_4_2.鞍山;
				require([ 'echarts', 'echarts/chart/pie' ], function(ec) {
					var myChart = ec.init($(".statistics-4-item")[1], 'macarons');
					var option = {
							tooltip: {
								trigger: 'item',
								formatter: "{a} <br/>{b}: {d}%"
							},
		                	title : {
		                		x:'15',
			        			y:'15',
				        		text: '培训地点数据分析',
				        		textStyle:{
				        			color:'#666666',
				        			fontSize:18,
				        			
				        		}
				   			},legend: {
				   		        x: 'left',
								y: 'bottom',
								bottom: 20,
				   		        data:['大连','沈阳','鞍山']
				   		    },
				   		    calculable : false,
				   		    series : [
				   		        {
				   		            name:'地区',
				   		            type:'pie',
				   		            radius : ['50%', '70%'],
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
				   		                        show : true,
				   		                        position : 'center',
				   		                        textStyle : {
				   		                            fontSize : '30',
				   		                            fontWeight : 'bold'
				   		                        }
				   		                    }
				   		                }
				   		            },
				   		            data:[
				   		                {value:count_2_1, name:'大连'},
				   		                {value:count_2_2, name:'沈阳'},
				   		                {value:count_2_3, name:'鞍山'}
				   		            ]
				   		        }
				   		    ]

		                };
					// 为echarts对象加载数据
					myChart.setOption(option);
				});
			},
			"error" : function() {
				toastr.error("系统异常");
			}
		});
	}
	Statistics_4.prototype.init2 = function(){
		var date = new Date();
		var year = date.getFullYear();
		$.ajax({
			"url" : "/stuenroll/welcome/education",
			"type" : "post",
			"dataType" : "json",
			//"async":false,
			"data" : {
				"year" : year
			},
			"success":function(json){
				var count_3_1 = json.statistics_4_3.专科;
				var count_3_2 = json.statistics_4_3.本科;
				var count_3_3 = json.statistics_4_3.硕士;
				var count_3_4 = json.statistics_4_3.博士;
				require([ 'echarts', 'echarts/chart/pie' ], function(ec) {
					var myChart = ec.init($(".statistics-4-item")[2], 'macarons');
					var option = {
							tooltip: {
								trigger: 'item',
								formatter: "{a} <br/>{b}: {d}%"
							},
		                	title : {
		                		x:'15',
			        			y:'15',
				        		text: '学员学历数据分析',
				        		textStyle:{
				        			color:'#666666',
				        			fontSize:18,
				        			
				        		}
				   			},legend: {
				   		        x: 'left',
								y: 'bottom',
								bottom: 20,
				   		        data:['专科','本科','硕士','博士']
				   		    },
				   		    calculable : false,
				   		    series : [
				   		        {
				   		            name:'学历',
				   		            type:'pie',
				   		            radius : ['50%', '70%'],
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
				   		                        show : true,
				   		                        position : 'center',
				   		                        textStyle : {
				   		                            fontSize : '30',
				   		                            fontWeight : 'bold'
				   		                        }
				   		                    }
				   		                }
				   		            },
				   		            data:[
				   		                {value:count_3_1, name:'专科'},
				   		                {value:count_3_2, name:'本科'},
				   		                {value:count_3_3, name:'硕士'},
				   		                {value:count_3_4, name:'博士'}
				   		            ]
				   		        }
				   		    ]

		                };
					// 为echarts对象加载数据
					myChart.setOption(option);
				});
			},
			"error":function(){
				toastr.error("系统异常");
			}
		});
	}
	Statistics_4.prototype.init3 = function(){
		var date = new Date();
		var year = date.getFullYear();
		$.ajax({
			"url" : "/stuenroll/welcome/professionClassCount",
			"type" : "post",
			"dataType" : "json",
			//"async":false,
			"data" : {
				"year" : year
			},
			"success" : function(json) {
				var count_1_1 = json.statistics_4_4.pcn_1;
				var count_1_2 = json.statistics_4_4.pcn_2;
				var count_1_3 = json.statistics_4_4.pcn_3;
				var count_1_4 = json.statistics_4_4.pcn_4;
				var count_1_5 = json.statistics_4_4.pcn_5;
				var name_1_1 = json.statistics_4_4.pn_1;
				var name_1_2 = json.statistics_4_4.pn_2;
				var name_1_3 = json.statistics_4_4.pn_3;
				var name_1_4 = json.statistics_4_4.pn_4;
				var name_1_5 = json.statistics_4_4.pn_5;
				require([ 'echarts', 'echarts/chart/bar' ], function(ec) {
					var myChart = ec.init($(".statistics-4-item")[3], 'macarons');
					var option = {
							tooltip: {
								trigger: 'axis',
								axisPointer: {
									type: 'shadow'
								}
							},
		                	title : {
		                		x:'15',
			        			y:'15',
				        		text: '班级数量',
				        		textStyle:{
				        			color:'#666666',
				        			fontSize:18,
				        			
				        		}
				   			},
				   			grid: {
								width:'60%',
								height:'70%'
							},
				   		    xAxis : [
				   		        {
				   		            type : 'value',
				   		            boundaryGap : [0, 0.01]
				   		        }
				   		    ],
				   		    yAxis : [
				   		        {
				   		            type : 'category',
				   		            data : [name_1_1,name_1_2,name_1_3,name_1_4,name_1_5]
				   		        }
				   		    ],
				   		    series : [
				   		        {
				   		            name:'班级',
				   		            type:'bar',
				   		            data:[
				   		                {value:count_1_1},
				   		                {value:count_1_2},
				   		                {value:count_1_3},
				   		                {value:count_1_4},
				   		                {value:count_1_5}
				   		            ]
				   		        },
				   		    ]

		                };
					// 为echarts对象加载数据
					myChart.setOption(option);
				});
			},
			"error" : function() {
				toastr.error("系统异常");
			}
		});
	}
	Statistics_4.prototype.init = function(){
		if (!checkPermission(["3_4","4_4"])) {
			return;
		}
		this.init0();
		this.init1();
		this.init2();
		this.init3();
	}
	var Statistics_2 = function(){
		
	}
	
	Statistics_2.prototype = new I_Statistics_2();
	Statistics_2.prototype.init = function(){
		if (!checkPermission(["3_4","4_4"])) {
			return;
		}
		require([ 'echarts', 'echarts/chart/line' ], function(ec) {
			var myChart = ec.init($(".statistics-2-item")[0], 'macarons');
			option = {
					title : {
                		x:'15',
	        			y:'15',
		        		text: '数据流量',
		        		textStyle:{
		        			color:'#666666',
		        			fontSize:18,
		        		}
		   			},
					tooltip: {
						trigger: 'axis'
					},
					legend: {
						data: ['浏览器', '移动端'],
						y:'45',
					},
					grid: {
						y:'25%',
						width:'80%',
						height:'60%',
					},
					xAxis: {
						type: 'category',
						boundaryGap: false,
						data: ['周一', '周二', '周三', '周四', '周五', '周六', '周日']
					},
					yAxis: {
						type: 'value',
						axisLabel: {
							formatter: '{value}'
						}
					},
					series: [{
						name: '浏览器',
						type: 'line',
						data: [11, 11, 15, 13, 12, 13, 10],
						markPoint: {
							data: [{
								type: 'max',
								name: '最大值'
							}, {
								type: 'min',
								name: '最小值'
							}]
						}
					}, {
						name: '移动端',
						type: 'line',
						data: [1, -2, 2, 5, 3, 2, 0],
						markPoint: {
							data: [{
								name: '周最低',
								value: -2,
								xAxis: 1,
								yAxis: -1.5
							}]
						}
					}]

                };
			// 为echarts对象加载数据
			myChart.setOption(option);
			
		});
		var date = new Date();
		var year = date.getFullYear();
		
		$.ajax({
			"url" : "/stuenroll/welcome/getJob",
			"type" : "post",
			"dataType" : "json",
			"data" : {
				"year" : year
			},
			"success":function(json){
				var count_1_1 = json.statistics_2.pe_1;
				var count_1_2 = json.statistics_2.pe_2;
				var count_1_3 = json.statistics_2.pe_3;
				var count_1_4 = json.statistics_2.pe_4;
				var count_1_5 = json.statistics_2.pe_5;
				var name_1_1 = json.statistics_2.pn_1;
				var name_1_2 = json.statistics_2.pn_2;
				var name_1_3 = json.statistics_2.pn_3;
				var name_1_4 = json.statistics_2.pn_4;
				var name_1_5 = json.statistics_2.pn_5;
				require([ 'echarts', 'echarts/chart/radar' ], function(ec) {
					var myChart = ec.init($(".statistics-2-item")[1], 'macarons');
					var option = {
		                	title : {
		                		x:'15',
			        			y:'15',
				        		text: '就业率',
				        		textStyle:{
				        			color:'#666666',
				        			fontSize:18,
				        			
				        		}
				   			},
				   		    tooltip : {
				   		        trigger: 'axis',
				   		        formatter: "{d}专业{b}:{c}%",
				   		    },
				   		    polar : [
				   		        {
				   		            indicator : [
				   		                {text : name_1_1, max  : 100 },
				   		                {text : name_1_2, max  : 100},
				   		                {text : name_1_3, max  : 100},
				   		                {text : name_1_4, max  : 100},
				   		                {text : name_1_5, max  : 100}
				   		            ],
				   		            radius : '60%'
				   		        }
				   		    ],
				   		    series : [
				   		        {
				   		            name: '就业率图表',
				   		            type: 'radar',
				   		            itemStyle: {
				   		                normal: {
				   		                    areaStyle: {
				   		                        type: 'default'
				   		                    }
				   		                }
				   		            },
				   		            data:[
				   		                  {
				   		                	  value:[count_1_1,count_1_2,count_1_3,count_1_4,count_1_5],
				   		                	  name:['就业率']
				   		                  
				   		                  }
				   		               ]
				   		        }
				   		    ]

		                };
					// 为echarts对象加载数据
					myChart.setOption(option);
				});
			},
			"error":function(){
				toastr.error("系统异常");
			}
		});
	}
	
	function factory(key) {
		if (key == "AnnualStatistics") {
			return new AnnualStatistics();
		}
		else if(key=="Statistics_4"){
			return new Statistics_4();
		}else if(key=="Statistics_2"){
			return new Statistics_2();
		}else if(key=="Info"){
			return new Info();
		}
	}

	var annualStatistics = factory("AnnualStatistics");
	annualStatistics.init();
	var statistics_4 = factory("Statistics_4");
	statistics_4.init();
	var statistics_2 = factory("Statistics_2");
	statistics_2.init();
	var info = factory("Info");
	$(".info-container *[name='prevBtn']").click(function(){
		var Tlabel = $(".header");
		var year = Tlabel.find("[name='date-year']").text();
		var month = Tlabel.find("[name='date-month']").text();
		year = new Number(year);
		month = new Number(month);
		month=month-1;
		if(month==0){
			month=12;year=year-1;	
		}
		if(year<2013)
			return;
		Tlabel.find("[name='date-year']").text(year);
		Tlabel.find("[name='date-month']").text(month);
		creatTable(year,month);
	});
	$(".info-container *[name='nextBtn']").click(function(){
		var date = new Date();
		var Tlabel = $(".header");
		var year = Tlabel.find("[name='date-year']").text();
		var month = Tlabel.find("[name='date-month']").text();
		var Nyear = date.getFullYear();
		var Nmonth = date.getMonth()+1;
		if(year==Nyear){
			if(month==Nmonth){
				return;
			}
		}
		var mon = parseInt(month,10);
		var max_mon = 12;
		year = new Number(year);
		mon = new Number(mon);
		mon = mon +1;
		max_mon = new Number(max_mon);
		if(mon>max_mon){
			mon=1;year=year+1;	
		}
		Tlabel.find("[name='date-year']").text(year);
		Tlabel.find("[name='date-month']").text(mon);
		creatTable(year,mon);
	});
	info.init();
	
	window.onload = function(){
		setInterval(function(){
			getTime();}
			,1000);
	};
});