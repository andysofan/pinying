/***
 * jquery blockUI Dialog plugin 
 * v1.1 
 * @createDate -- 2012-1-4
 * @author hoojo
 * @email hoojo_@126.com
 * @requires jQuery v1.2.3 or later, jquery.blockUI-2.3.8
 * Copyright (c) 2012 M. hoo
 * Dual licensed under the MIT and GPL licenses:
 * http://hoojo.cnblogs.com
 * http://blog.csdn.net/IBM_hoojo
 * http://www.cnblogs.com/best/archive/2011/10/01/2197842.html
 **/
 
;(function ($) {
    
    var _dialog = $.blockUI.dialog = {};
    
    // dialog 默认配置
    var defaultOptions = {
        css: { 
            padding: '8px', 
            opacity: .7, 
            color: '#ffffff', 
            border: 'none', 
            'border-radius': '10px', 
            backgroundColor: '#000000' 
        },
        // 默认回调函数 取消或隐藏 dialog
        emptyHandler: function () {
            //$.unblockUI();
        },
        
        // 用户回调函数
        callbackHandler: function (fn) {
            return function () {
                fn();
                defaultOptions.emptyHandler();
            };
        },
        
        // confirm 提示 html元素
        confirmElement: function (settings) {
            settings = settings || {};
            var message = settings.message || "default confirm message!";
            var okText = settings.okText || "ok";
            var cancelText = settings.cancelText || "cancel";
            
            if (typeof settings.onOk !== "function") {
                settings.onOk = this.emptyHandler;
            }
            if (typeof settings.onCancel !== "function") {
                settings.onCancel = this.emptyHandler;
            }
            var okCallback = this.callbackHandler(settings.onOk)|| this.emptyHandler;
            var cancelCallback = this.callbackHandler(settings.onCancel) || this.emptyHandler;
            
            var html = [
                '<div class="dialog confirm">',
                '<p>',
                message,
                '</p>',
                '<input type="button" value="',
                okText,
                '" class="btn ok-btn"/>',
                '<input type="button" value="',
                cancelText,
                '" class="btn cancel-btn"/>'
            ].join("");
            
            var $el = $(html);
            $el.find(":button").get(0).onclick =  okCallback;
            $el.find(":button:last").get(0).onclick = cancelCallback;
            return $el;
        },
        
        // alert 提示html元素
        alertElement: function (settings) {
            settings = settings || {};
            var message = settings.message || "default alert message!";
            var okText = settings.okText || "ok";
            
            if (typeof settings.onOk !== "function") {
                settings.onOk = this.emptyHandler;
            }
            
            var okCallback = this.callbackHandler(settings.onOk) || this.emptyHandler;
            
            var html = [
                '<div class="dialog alert">',
                '<p>',
                message,
                '</p>',
                '<input type="button" value="',
                okText,
                '" class="btn ok-btn"/>'
            ].join("");
            
            var $el = $(html);
            
            $el.find(":button:first").get(0).onclick =  okCallback;
            return $el;
        }
    };
    
    var _options = defaultOptions;
    
    // 对外公开的dialog提示html元素，提供配置、覆盖
    $.blockUI.dialog.confirmElement = function () {
        return _options.confirmElement(arguments[0]);
    };
    
    $.blockUI.dialog.alertElement = function () {
        return _options.alertElement(arguments[0]);
    };
    
    // 提供jquery 插件方法
    $.extend({
        confirm: function (opts) {
            var i = (typeof opts === "object") ? 1 : 0;
            var defaults = {
                message: arguments[i++] || "default confirm message!",
                onOk: arguments[i++] || _options.emptyHandler(),
                onCancel: arguments[i++] || _options.emptyHandler(),
                okText: arguments[i++] || "ok",
                cancelText: arguments[i] || "cancel"
            };
            opts = opts || {};
            opts.css = $.extend({}, _options.css, opts.css);
            
            // 覆盖默认配置
            var settings = $.extend({}, _options, defaults, opts);
            settings = $.extend(settings, { message: _dialog.confirmElement(settings) });
            settings = $.extend({}, $.blockUI.defaults, settings);
            $.blockUI(settings);
        },
        alert: function (opts) {
            var i = (typeof opts === "object") ? 1 : 0;
            
            var defaults = {
                message: arguments[i++] || "default alert message!",
                onOk: arguments[i++] || _options.emptyHandler(),
                okText: arguments[i] || "ok"
            };
            
            opts = opts || {};
            opts.css = $.extend({}, _options.css, opts.css);
            
            var settings = $.extend({}, _options, defaults, opts);
            settings = $.extend(settings, { message: _dialog.alertElement(settings) });
            settings = $.extend({}, $.blockUI.defaults, settings);
            $.blockUI(settings);
        },
        
        // dialog提示
        dialog: function (opts) {
            var settings = $.extend({}, $.blockUI.defaults, _options, opts || {});
            $.blockUI(settings);
        },
        // 移除dialog提示框
        removeDialog: function () {
            _options.emptyHandler();
        }
    });
})(jQuery);