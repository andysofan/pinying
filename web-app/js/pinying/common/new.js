var V={
    isInit:false,
    validators:{},
    TEXTAREA: 1,
    TEXT: 2,
    PASSWORD: 3,
    CHECKBOX: 4,
    SELECT: 5,
    FILE: 6,
    remoteParams:null    //远程公共参数，远程提交验证信息共享的信息
};
V.config={
    valid_class: 'success',//验证正确时表单样式
    invalid_class: 'error',//验证失败时该表单样式
    success_class: 'success',//验证成功时文字信息的样式
    advice_class: 'error',//验证失败时文字信息的样式
    prefix: ':',
    separator: ';',
    reset_defer_timeout: 700,
    pass2unchanged:true    //当输入框信息没有变化时，失去焦点是否不进行任何验证处理
};
V.elementDecorate=function(result){
    V.elementRest(result.element);
    if(result.success){
        $('<label></label>').addClass(V.config.success_class).insertAfter(result.element);
    }else{
        $('<label>'+result.message+'</label>').addClass(V.config.advice_class).insertAfter(result.element);
    }
};
V.elementRest=function(element){
    var n = element.parent().find('label');
    n.remove();
};
V.empty=function(v){return  ((v == null) || (v.length == 0));};
V.getErrorMessage=function(success,em,v,p,m){
    if(success)return '';
    if(!em)return 'error';
    if(typeof(em)=='function'){
        return em(v,p,m);
    }else{
        return em;
    }
};
V.init=function(){
    if(!V.isInit){
        //初始化验证器
        var ts=V.initTypes,va;
        for(var i in ts){
        	console.log(i);
            va={
                validate:ts[i][1],
                errorMessage:ts[i][2]
            };
            if(ts[i][3]){
                var config = ts[i][3];
                config;//如果需要其他特殊参数，使用config进行配置
            };
            V.validators[ts[i][0]] = va;
        };
        //远程验证处理
        if(V.initRemoteTypes){
            ts=V.initRemoteTypes;
            for(var i in ts){
                va={
                    asyn:true,
                    url:ts[i][1],
                    errorMessage:ts[i][2]
                };
                V.validators[ts[i][0]] = va;
            }
        }
        //某些元素将在表单提交时，重新验证，不论之前是否已经验证通过
        var fl='validate.submit';
        if(V.submitRevalidateList){
            $.each(V.submitRevalidateList,function(){
                this.data(fl,true);
            });
        }
        V.checkOnSubmitValidateId=function(el){
            return el.data(fl);
        };
        V.isInit=true;
    }
};
V.isAsyn=function(n){
	if(V.validators[n] == undefined){
		return false;
	}else{
		if(V.validators[n].asyn){
        	return true;
    	}else{
        	return false;
    	}
	}
};
V.test=function(n,v,p,el){
    var validator=V.validators[n],rs=validator.validate(v,p,el);
    return {success:rs,message:V.getErrorMessage(rs,validator.errorMessage,v,p),element:el};
};
V.testRemote=function(n,v,p,el,handler){
    var validator=V.validators[n];
    //复制公共参数
    if(V.remoteParams){
        if(!p)p={};
        for(i in V.remoteParams){
            p[i]=V.remoteParams[i];
        };
    };
    $.getJSON(validator.url+'/'+v,p).success(function(result){
        result.message = V.getErrorMessage(result.success,validator.errorMessage,v,p,result.message);
        if(handler){
            result.element = el;//返回元素包含所在表单元素
            handler(result);
        }
    }).error(function(){
        //alert('Remote execute error');
    });
};
V.handleResult=function(result){
    var el=result.element,r,a;
    if(!result.success){
        r=V.config.valid_class;
        a=V.config.invalid_class;
    }else{
        r=V.config.invalid_class;
        a=V.config.valid_class;
    }
    el.removeClass(r);
    el.addClass(a);
    //执行装饰代码
    V.elementDecorate(result);
};
V.parse_class_name = function(class_name) {
    if (class_name.indexOf(V.config.prefix) == 0) {
        var v_params = class_name.substr(V.config.prefix.length).split(V.config.separator);
        for (var key in v_params) {
            if (v_params[key] == "") {
                v_params[key] = undefined;
            };
        }
        return v_params;
    } else {
        return null;
    };
};
V.getElementType=function(el) {
    switch (true) {
      case (el.nodeName.toUpperCase() == 'TEXTAREA'):
        return V.TEXTAREA;
      case (el.nodeName.toUpperCase() == 'INPUT' && el.type.toUpperCase() == 'TEXT'):
        return V.TEXT;
      case (el.nodeName.toUpperCase() == 'INPUT' && el.type.toUpperCase() == 'PASSWORD'):
        return V.PASSWORD;
      case (el.nodeName.toUpperCase() == 'INPUT' && el.type.toUpperCase() == 'CHECKBOX'):
        return V.CHECKBOX;
      case (el.nodeName.toUpperCase() == 'INPUT' && el.type.toUpperCase() == 'FILE'):
        return V.FILE;
      case (el.nodeName.toUpperCase() == 'SELECT'):
        return V.SELECT;
      case (el.nodeName.toUpperCase() == 'INPUT'):
        throw new Error('Vanadium::getElementType - Cannot use Vanadium on an ' + el.type + ' input!');
      default:
        throw new Error('Vanadium::getElementType - Element must be an input, select, or textarea!');
  }
};
V.initForm=function(f){
    var elements = f.find(':text,:checkbox,:file,:password,select,textarea');
    var formValidators = [],formValid=true,remoteCount=0,remoteValidCount=0,remoteInvalidCount=0,isSubmit=false;
    elements.each(function(){
            var el = $(this);
            var classes = el.attr("class");
            //add by wxx start 文哥你要是修改的话告诉我一下
            var notsubmit=el.attr("notsubmit");
            //add by wxx end
            if(!classes){return;}
            var class_names = classes.split(' ');
            var validateOnSubmit = V.checkOnSubmitValidateId(el);
            var validators=[],isAsyn=false,isValid=false,unChanged=false;//isAsyn:是否含有远程验证 isValid:是否已经验证通过 unChanged:是否修改过内容
            $.each(class_names,function(){
                    var params = V.parse_class_name(this);
                    if(params){
                        validators.push({
                            name:params[0],
                            param:params[1]
                        });
                        if(!isAsyn && V.isAsyn(params[0])){
                            isAsyn=true;remoteCount++;
                        };
                    }
            });
            var validate=function(isSubmit){
                if(V.config.pass2unchanged){    //执行验证函数，将unChanged设置为true
                    el.data('unChanged',true);
                }
                if((!isSubmit||!validateOnSubmit) && isValid){ //某些特殊元素在表单提交是需要重新验证 validateOnSubmit表示是否需要验证
                    if(isAsyn)remoteValidCount++;
                    return;
                }
                var val = el.val();
                for(var i in validators){
                    if(isAsyn && V.isAsyn(validators[i].name)){
                        V.testRemote(validators[i].name,val,validators[i].param,el,function(result){
                            V.handleResult(result);
                            isValid=result.success;
                            if(result.success){
                                remoteValidCount++;
                            }else{
                                remoteInvalidCount++;//同一个输入框只支持一个远程验证
                            }
                            if(isSubmit==true && (remoteValidCount+remoteInvalidCount)==remoteCount){
                                if(formValid && remoteInvalidCount==0){
                                    f.unbind('submit');
                                    //add by wxx start 文哥你要是修改的话告诉我一下
                                    if(notsubmit!="yes"){
                                    //add by wxx end
                                    f.submit();
                                    }
                                }
                                isSubmit = false;
                            }
                        });
                    }else{
                        var result = V.test(validators[i].name,val,validators[i].param,el);
                        V.handleResult(result);
                        if(!result.success){
                            formValid = false;
                            return;
                        }
                    };
                };
                if(!isAsyn){
                    isValid=true;
                }
            };
            el.bind('validate',validate);//绑定验证函数
            var deferValidate = function(){
                //if(unChanged)return; //
                unChanged = true;    //执行修改函数  设置unchanged为true 同时防止重复处罚修改事件
                if (el.data('timeout')) clearTimeout(el.data('timeout'));
                var timeout = setTimeout(function() {
                    V.elementRest(el);
                    el.trigger('validate');
                }, V.config.reset_defer_timeout);
                el.data('timeout',timeout);
            };
            switch (V.getElementType(el[0])) {
              case V.CHECKBOX:
                el.click(validate);
                break;
              case V.SELECT:
              case V.FILE:
                el.change(validate);
                break;
              default:
                el.keydown(function(e) {
                    if (e.keyCode != 9) {
                        V.elementRest(el);//输入过程清除原有信息
                    }
                });
                el.keyup(function(e) {
                    if (e.keyCode != 9) {
                        isValid = false;
                        unChanged = false;
                        deferValidate();
                    }
                });
                el.blur(function(){
                    /*if(V.config.pass2unchanged && unChanged){
                        return;
                    } */
                    isValid = false;
                    unChanged = false;
                    deferValidate();
                });
            };
        formValidators.push(validate);
    });
    //提交表单
    f.submit(function(){
        remoteValidCount=0,remoteInvalidCount=0,isSubmit=true,formValid=true;
        $.each(formValidators,function(){
            this(true);    
        });
        if((remoteCount==0||(remoteInvalidCount+remoteValidCount)==remoteCount) && formValid){
            return true;
        }
        return false;
    });
    //验证是否表单是否通过 暂时不支持异步
    f.bind('validateForm',function(event,success,error){
        remoteValidCount=0,remoteInvalidCount=0,isSubmit=true,formValid=true;
        $.each(formValidators,function(){
            this(true);    
        });
        if(formValid){
            success();
        }else{
            error();
        }
    });
};
V.validate=function(fs){
    V.init();
    if(fs){
        V.initForm(fs);
    }
};