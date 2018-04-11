/**
 * 弹出层抽象类
 */
var Modal = function() {

}

/**
 * 显示弹出层抽象方法
 * @param {jQuery} $obj 弹出层JQuery对象
 * @param {HTMLString} content 弹出层内容
 */
Modal.prototype.show = function($obj) {
	throw "抽象方法";
}

/**
 * 弹出层实现类
 */
var ModalImpl = function() {

}

ModalImpl.prototype = new Modal();

/**
 * 显示弹出层方法
 * @param {jQuery} $obj 弹出层JQuery对象
 * @param {HTMLString} content 弹出层内容
 */
ModalImpl.prototype.show = function($obj) {
	    $obj.before("<div class='shade no-select'></div>");
        var $shade = $(".shade");
        $shade.fadeIn();
        $obj.fadeIn();
        $shade.click(function (e) {
            $obj.fadeOut();
            $shade.fadeOut(function () {
            $shade.remove();
            });
        });
        $obj.find(".operation *[name='close']").click(function () {
            $shade.click();
        });
}

/**
 * 弹出层工厂方法
 * @return {Modal} 弹出层对象
 */
function getModal() {
	return new ModalImpl();
}

var modal=getModal();
