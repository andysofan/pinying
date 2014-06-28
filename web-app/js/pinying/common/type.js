V.initTypes = [
    //匹配大小写的等值
    ['equal', function(v, p) {
      return v == p;
    }, function (_v, p) {
      return '输入的值必须与<span class="' + V.config.message_value_class + '">' + p + '相符\(区分大小写\)</span>.';
    }],
    //不匹配大小写的等值
    ['equal_ignore_case', function(v, p) {
      return v.toLowerCase() == p.toLowerCase();
    }, function (_v, p) {
      return '输入的值必须与<span class="' + V.config.message_value_class + '">' + p + '相符\(不区分大小写\)</span>.';
    }],
    //是否为空
    ['required', function(v) {
        var temp = "";
        v = '' + v;
        var splitstring = v.split(" ");
        for(i = 0; i < splitstring.length; i++)
        temp += splitstring[i];
      return !V.empty(temp) && $.trim(temp).length!=0;
    }, '此项不可为空!'],
    //强制选中 
    ['accept', function(v, _p, e) {
      return e.attr("checked")=='checked';
    }, '必须接受!'],
    //
    ['integer', function(v) {
      if (V.empty(v)) return true;
      var f = parseFloat(v);
      return (!isNaN(f) && f.toString() == v && Math.round(f) == f);
    }, '请输入一个正确的整数值.'],
    //数字
    ['number', function(v) {
      return V.empty(v) || (!isNaN(v) && !/^\s+$/.test(v));
    }, '请输入一个正确的数字.'],
    //
    ['digits', function(v) {
      return V.empty(v) || !/[^\d]/.test(v);
    }, '请输入一个非负整数,含0.'],
    //只能输入英文字母
    ['alpha', function (v) {
      return V.empty(v) || /^[a-zA-Z\u00C0-\u00FF\u0100-\u017E\u0391-\u03D6]+$/.test(v);   //% C0 - FF (? - ?); 100 - 17E (? - ?); 391 - 3D6 (? - ?)
    }, '请输入英文字母.'],
    //仅限ASCII编码模式下输入英文字母
    ['asciialpha', function (v) {
      return V.empty(v) || /^[a-zA-Z]+$/.test(v);   //% C0 - FF (? - ?); 100 - 17E (? - ?); 391 - 3D6 (? - ?)
    }, '请在ASCII下输入英文字母.'],
    //英文字母或正数
    ['alphanum', function(v) {
      return V.empty(v) || !/\W/.test(v);
    }, '请输入英文字母或正数.'],
    //邮箱验证
    ['email', function (v) {
      return (V.empty(v) || /\w{1,}[@][\w\-]{1,}([.]([\w\-]{1,})){1,3}$/.test(v));
    }, '邮箱格式不正确,例:abc@163.com'],
    //网址
    ['url', function (v) {
      return V.empty(v) || /^(http|https|ftp):\/\/(([A-Z0-9][A-Z0-9_-]*)(\.[A-Z0-9][A-Z0-9_-]*)+)(:(\d+))?\/?/i.test(v);
    }, '请输入正确的网址,比如:http://www.eventsboom.com'],
    //日期格式
    ['date_au', function(v) {
      if (V.empty(v)) return true;
      var regex = /^(\d{2})\/(\d{2})\/(\d{4})$/;
      if (!regex.test(v)) return false;
      var d = new Date(v.replace(regex, '$2/$1/$3'));
      return ( parseInt(RegExp.$2, 10) == (1 + d.getMonth()) ) && (parseInt(RegExp.$1, 10) == d.getDate()) && (parseInt(RegExp.$3, 10) == d.getFullYear() );
    }, '请输入正确的日期格式,比如:28/05/2010.'],
    //日期格式
    ['date_time', function(v) {
    if (V.empty(v)) return true;
    var regex = /^(\d{4})-(\d{2})-(\d{2})\s(\d{2}):(\d{2})$/;
    if (!regex.test(v)) return false;
    return true;
    }, '请输入正确的日期格式,如:2012-12-22 00:00'], 

    //输入固定长度字符串
    ['length',
      function (v, p) {
        if (p === undefined) {
          return true;
        } else {
          return v.length == parseInt(p);
        }
        ;
      },
      function (_v, p) {
        return '输入字符长度等于<span class="' + V.config.message_value_class + '">' + p + '</span>个.';
      }
    ],
    //
    ['min_length',
      function (v, p) {
        if (p === undefined) {
          return true;
        } else {
          return v.length >= parseInt(p);
        }
        ;
      },
      function (_v, p) {
        return '输入字符长度不低于<span class="' + V.config.message_value_class + '">' + p + '</span>个.';
      }
    ],
    ['max_length',
      function (v, p) {
        if (p === undefined) {
          return true;
        } else {
          return v.length <= parseInt(p);
        }
        ;
      },
      function (_v, p) {
        return '输入字符长度不大于<span class="' + V.config.message_value_class + '">' + p + '</span>个.';
      }
    ],
    //判断密码是相同
    ['same_as',
      function (v, p) {
        if (p === undefined) {
          return true;
        } else {
          var exemplar = document.getElementById(p);
          if (exemplar)
            return v == exemplar.value;
          else
            return false;
        }
        ;
      },
      function (_v, p) {
        var exemplar = document.getElementById(p);
        if (exemplar)
          return '两次密码输入不相同.';
        else
          return '没有可参考值!';
      },
      "",
      function(validation_instance) {
        var exemplar = document.getElementById(validation_instance.param);
        if (exemplar){
          jQuery(exemplar).bind('validate', function(){
            jQuery(validation_instance.element).trigger('validate');
          });
        }
      }
    ],
  //判断新密码与原密码不同相同
    ['differ_with',
      function (v, p) {
        if (p === undefined) {
          return true;
        } else {
          var exemplar = document.getElementById(p);
          if (exemplar)
            return v != exemplar.value;
          else
            return false;
        }
        ;
      },
      function (_v, p) {
        var exemplar = document.getElementById(p);
        if (exemplar)
          return '新密码与原密码一致，请重新输入';
        else
          return '没有可参考值!';
      },
      "",
      function(validation_instance) {
        var exemplar = document.getElementById(validation_instance.param);
        if (exemplar){
          jQuery(exemplar).bind('validate', function(){
            jQuery(validation_instance.element).trigger('validate');
          });
        }
      }
    ],
    //正则匹配,此用法不甚理解
    ['format',
      function(v, p) {
        var params = p.match(/^\/(((\\\/)|[^\/])*)\/(((\\\/)|[^\/])*)$/);        
        if (params.length == 7) {
          var pattern = params[1];
          var attributes = params[4];
          try
          {
            var exp = new RegExp(pattern, attributes);
            return exp.test(v);
          }
          catch(err)
          {
            return false;
          }
        } else {
          return false;
        }
      },
      function (_v, p) {
        var params = p.split('/');
        if (params.length == 3 && params[0] == "") {
          return '输入的值必须与 <span class="' + V.config.message_value_class + '">' + p.toString() + '</span> 相匹配.';
        } else {
          return '提供的值与<span class="' + V.config.message_value_class + '">' + p.toString() + '</span>不匹配.';
        }
      }
    ]
  ];