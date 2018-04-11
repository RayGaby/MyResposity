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