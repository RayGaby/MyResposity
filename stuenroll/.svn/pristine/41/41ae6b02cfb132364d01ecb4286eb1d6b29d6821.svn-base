//解析src地址

function getIframeParams(iframe){
		var regexpParam = /\??([\w\d%]+)=([\w\d%]*)&?/g;//分离参数的正则表达式
		var targetEle = window.parent.document.getElementById(iframe);
		
		var paramMap = null;
		
		if(!!targetEle){
			var url = targetEle.src;
			
			var ret;
			paramMap = {};//初始化结果集
			
			while((ret = regexpParam.exec(url)) != null){
				paramMap[ret[1]] = ret[2];
			}
		}
		return paramMap;
	}