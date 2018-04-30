/**
 * 表单序列化
 */
(function ($) {
    $.fn.serializeJsonObj = function () {
        var serializeObj = {};
        var array = this.serializeArray();
        var str = this.serialize();
        $(array).each(function () {
            if (serializeObj[this.name]) {
                if ($.isArray(serializeObj[this.name])) {
                    serializeObj[this.name].push(this.value);
                } else {
                    serializeObj[this.name] = [serializeObj[this.name], this.value];
                }
            } else {
                serializeObj[this.name] = this.value;
            }
        });
        return serializeObj;
    };
})(jQuery);

/**
 * 将form表单数据序列化
 * 参数为表单的id
 * 支持input,radio,checkbox,textarea, select-multiple
 * 对于checkbox ,select-multiple 返回以逗号分割的值的字符串
 * 对于每一个要序列化的input,不管什么类型,都要加transmit='true'标志属性
 * 同一form表单内,不同form元素,name请不要重复
 * 对于value为空的项，也加入序列化，value为空串
 * amazeuiSwitch：判断是不是amazeui封装的开关
 */
var commonSerializeForm = function(formId) {
	var inputAll=$('#'+formId+" :input[transmit='true']");//返回form中的所有表单对象，包括textarea、select、button等
	var  o={};//返回
	$.each(inputAll, function(i, field){
        	if(field.type=='radio' || field.type=='checkbox'){
//        		if(!o[field.name]){
//        			o[field.name]="";
//        		}
				//判断是不是amazeui开关
				if(field.attributes.amazeuiSwitch ){
                    if(field.checked==true){
                        if (!o[field.name]){
                            o[field.name] = 1;
                        } else {
                            o[field.name] = o[field.name] + "," + 1;
                        }
                    }
                    if(field.checked==false){
                        if (!o[field.name]){
                            o[field.name] = 2;
                        } else {
                            o[field.name] = o[field.name] + "," + 2;
                        }
                    }

				}else if(field.checked==true){
        			if (!o[field.name]){
        				o[field.name] = field.value;
        			} else {
        				o[field.name] = o[field.name] + "," + field.value;
        			}
        		}
        	}
        	else if(field.type=="select-multiple"){
        		var selected=field.selectedOptions;
				if(selected.length>0) {
					$.each(selected, function(j, selectedOption){
						   if (o[field.name]){
							   o[field.name] = o[field.name] + "," + selectedOption.value;
			    			} else {
			    			   o[field.name] = selectedOption.value;
			    		    }
					  });
				}
//				else{
//					o[field.name]="";
//				}
        	}
        	else{
        		if(field.value!=""){
        			if (o[field.name]){
        				o[field.name] = o[field.name] + "," + field.value;
        			} else {
        				o[field.name] = field.value;
        			}
        		}
        	}
	});
	return o;
};

/**
 * 获取loading动画
 * @returns {string}
 */
function loadRandomAnimation() {
    var loadingStyle = [
        '<div class="loader loader--audioWave"></div>',
        '<div class="loader loader--snake"></div>',
        '<div class="loader loader--spinningDisc"></div>',
        '<div class="loader loader--glisteningWindow"></div>',
        '<div class="loader loader--circularSquare"></div>'
    ];
    var loadHtml = loadingStyle[Math.floor((Math.random() * loadingStyle.length))];
    return '<div id="loading">' + loadHtml + '<div id="loadingText" style="text-align:center;clear:both;">努力加载中……</div>';
}