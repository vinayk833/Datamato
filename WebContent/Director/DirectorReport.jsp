<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>

    <%@ page import="com.login.util.DBConnection" %>
<%ResultSet resultset =null;%>
<%ResultSet resultset1 =null;%>
<%ResultSet resultset2 =null;%>
<%ResultSet resultset3 =null;%>
<%ResultSet resultset4 =null;%>

<html>
<head>

<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/AdminDashboard.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/DatePicker.css">

<!--  for Datepicker -->
<script type="text/javascript" src='${pageContext.request.contextPath }/js/jquery-1.8.3.js'></script>
<script type="text/javascript" src='${pageContext.request.contextPath }/js/jquery-ui-1.10.2.custom.js'></script>
<link type="text/css" href='${pageContext.request.contextPath}/css/jquery-ui-1.10.2.custom.css' rel='stylesheet' />
<script>
  $(function() {
    $( "#startdate" ).datepicker();
    $( "#enddate" ).datepicker();

  });
  </script>

<style type="text/css">
#startdate
        {
           background:  url(https://i.imgur.com/u6upaAs.png) right no-repeat;
             background-repeat: no-repeat;
             padding-right: 10px;
            }
 #enddate
        {
            background:  url(https://i.imgur.com/u6upaAs.png) right no-repeat;
             background-repeat: no-repeat;
             padding-right: 10px;
            }
            </style>



 <script>
 
 

 if("undefined"==typeof jQuery)throw new Error("Bootstrap requires jQuery");+function(a){"use strict";function b(){var a=document.createElement("bootstrap"),b={WebkitTransition:"webkitTransitionEnd",MozTransition:"transitionend",OTransition:"oTransitionEnd otransitionend",transition:"transitionend"};for(var c in b)if(void 0!==a.style[c])return{end:b[c]}}a.fn.emulateTransitionEnd=function(b){var c=!1,d=this;a(this).one(a.support.transition.end,function(){c=!0});var e=function(){c||a(d).trigger(a.support.transition.end)};return setTimeout(e,b),this},a(function(){a.support.transition=b()})}(jQuery),+function(a){"use strict";var b='[data-dismiss="alert"]',c=function(c){a(c).on("click",b,this.close)};c.prototype.close=function(b){function c(){f.trigger("closed.bs.alert").remove()}var d=a(this),e=d.attr("data-target");e||(e=d.attr("href"),e=e&&e.replace(/.*(?=#[^\s]*$)/,""));var f=a(e);b&&b.preventDefault(),f.length||(f=d.hasClass("alert")?d:d.parent()),f.trigger(b=a.Event("close.bs.alert")),b.isDefaultPrevented()||(f.removeClass("in"),a.support.transition&&f.hasClass("fade")?f.one(a.support.transition.end,c).emulateTransitionEnd(150):c())};var d=a.fn.alert;a.fn.alert=function(b){return this.each(function(){var d=a(this),e=d.data("bs.alert");e||d.data("bs.alert",e=new c(this)),"string"==typeof b&&e[b].call(d)})},a.fn.alert.Constructor=c,a.fn.alert.noConflict=function(){return a.fn.alert=d,this},a(document).on("click.bs.alert.data-api",b,c.prototype.close)}(jQuery),+function(a){"use strict";var b=function(c,d){this.$element=a(c),this.options=a.extend({},b.DEFAULTS,d)};b.DEFAULTS={loadingText:"loading..."},b.prototype.setState=function(a){var b="disabled",c=this.$element,d=c.is("input")?"val":"html",e=c.data();a+="Text",e.resetText||c.data("resetText",c[d]()),c[d](e[a]||this.options[a]),setTimeout(function(){"loadingText"==a?c.addClass(b).attr(b,b):c.removeClass(b).removeAttr(b)},0)},b.prototype.toggle=function(){var a=this.$element.closest('[data-toggle="buttons"]'),b=!0;if(a.length){var c=this.$element.find("input");"radio"===c.prop("type")&&(c.prop("checked")&&this.$element.hasClass("active")?b=!1:a.find(".active").removeClass("active")),b&&c.prop("checked",!this.$element.hasClass("active")).trigger("change")}b&&this.$element.toggleClass("active")};var c=a.fn.button;a.fn.button=function(c){return this.each(function(){var d=a(this),e=d.data("bs.button"),f="object"==typeof c&&c;e||d.data("bs.button",e=new b(this,f)),"toggle"==c?e.toggle():c&&e.setState(c)})},a.fn.button.Constructor=b,a.fn.button.noConflict=function(){return a.fn.button=c,this},a(document).on("click.bs.button.data-api","[data-toggle^=button]",function(b){var c=a(b.target);c.hasClass("btn")||(c=c.closest(".btn")),c.button("toggle"),b.preventDefault()})}(jQuery),+function(a){"use strict";var b=function(b,c){this.$element=a(b),this.$indicators=this.$element.find(".carousel-indicators"),this.options=c,this.paused=this.sliding=this.interval=this.$active=this.$items=null,"hover"==this.options.pause&&this.$element.on("mouseenter",a.proxy(this.pause,this)).on("mouseleave",a.proxy(this.cycle,this))};b.DEFAULTS={interval:5e3,pause:"hover",wrap:!0},b.prototype.cycle=function(b){return b||(this.paused=!1),this.interval&&clearInterval(this.interval),this.options.interval&&!this.paused&&(this.interval=setInterval(a.proxy(this.next,this),this.options.interval)),this},b.prototype.getActiveIndex=function(){return this.$active=this.$element.find(".item.active"),this.$items=this.$active.parent().children(),this.$items.index(this.$active)},b.prototype.to=function(b){var c=this,d=this.getActiveIndex();return b>this.$items.length-1||0>b?void 0:this.sliding?this.$element.one("slid.bs.carousel",function(){c.to(b)}):d==b?this.pause().cycle():this.slide(b>d?"next":"prev",a(this.$items[b]))},b.prototype.pause=function(b){return b||(this.paused=!0),this.$element.find(".next, .prev").length&&a.support.transition.end&&(this.$element.trigger(a.support.transition.end),this.cycle(!0)),this.interval=clearInterval(this.interval),this},b.prototype.next=function(){return this.sliding?void 0:this.slide("next")},b.prototype.prev=function(){return this.sliding?void 0:this.slide("prev")},b.prototype.slide=function(b,c){var d=this.$element.find(".item.active"),e=c||d[b](),f=this.interval,g="next"==b?"left":"right",h="next"==b?"first":"last",i=this;if(!e.length){if(!this.options.wrap)return;e=this.$element.find(".item")[h]()}this.sliding=!0,f&&this.pause();var j=a.Event("slide.bs.carousel",{relatedTarget:e[0],direction:g});if(!e.hasClass("active")){if(this.$indicators.length&&(this.$indicators.find(".active").removeClass("active"),this.$element.one("slid.bs.carousel",function(){var b=a(i.$indicators.children()[i.getActiveIndex()]);b&&b.addClass("active")})),a.support.transition&&this.$element.hasClass("slide")){if(this.$element.trigger(j),j.isDefaultPrevented())return;e.addClass(b),e[0].offsetWidth,d.addClass(g),e.addClass(g),d.one(a.support.transition.end,function(){e.removeClass([b,g].join(" ")).addClass("active"),d.removeClass(["active",g].join(" ")),i.sliding=!1,setTimeout(function(){i.$element.trigger("slid.bs.carousel")},0)}).emulateTransitionEnd(600)}else{if(this.$element.trigger(j),j.isDefaultPrevented())return;d.removeClass("active"),e.addClass("active"),this.sliding=!1,this.$element.trigger("slid.bs.carousel")}return f&&this.cycle(),this}};var c=a.fn.carousel;a.fn.carousel=function(c){return this.each(function(){var d=a(this),e=d.data("bs.carousel"),f=a.extend({},b.DEFAULTS,d.data(),"object"==typeof c&&c),g="string"==typeof c?c:f.slide;e||d.data("bs.carousel",e=new b(this,f)),"number"==typeof c?e.to(c):g?e[g]():f.interval&&e.pause().cycle()})},a.fn.carousel.Constructor=b,a.fn.carousel.noConflict=function(){return a.fn.carousel=c,this},a(document).on("click.bs.carousel.data-api","[data-slide], [data-slide-to]",function(b){var c,d=a(this),e=a(d.attr("data-target")||(c=d.attr("href"))&&c.replace(/.*(?=#[^\s]+$)/,"")),f=a.extend({},e.data(),d.data()),g=d.attr("data-slide-to");g&&(f.interval=!1),e.carousel(f),(g=d.attr("data-slide-to"))&&e.data("bs.carousel").to(g),b.preventDefault()}),a(window).on("load",function(){a('[data-ride="carousel"]').each(function(){var b=a(this);b.carousel(b.data())})})}(jQuery),+function(a){"use strict";var b=function(c,d){this.$element=a(c),this.options=a.extend({},b.DEFAULTS,d),this.transitioning=null,this.options.parent&&(this.$parent=a(this.options.parent)),this.options.toggle&&this.toggle()};b.DEFAULTS={toggle:!0},b.prototype.dimension=function(){var a=this.$element.hasClass("width");return a?"width":"height"},b.prototype.show=function(){if(!this.transitioning&&!this.$element.hasClass("in")){var b=a.Event("show.bs.collapse");if(this.$element.trigger(b),!b.isDefaultPrevented()){var c=this.$parent&&this.$parent.find("> .panel > .in");if(c&&c.length){var d=c.data("bs.collapse");if(d&&d.transitioning)return;c.collapse("hide"),d||c.data("bs.collapse",null)}var e=this.dimension();this.$element.removeClass("collapse").addClass("collapsing")[e](0),this.transitioning=1;var f=function(){this.$element.removeClass("collapsing").addClass("in")[e]("auto"),this.transitioning=0,this.$element.trigger("shown.bs.collapse")};if(!a.support.transition)return f.call(this);var g=a.camelCase(["scroll",e].join("-"));this.$element.one(a.support.transition.end,a.proxy(f,this)).emulateTransitionEnd(350)[e](this.$element[0][g])}}},b.prototype.hide=function(){if(!this.transitioning&&this.$element.hasClass("in")){var b=a.Event("hide.bs.collapse");if(this.$element.trigger(b),!b.isDefaultPrevented()){var c=this.dimension();this.$element[c](this.$element[c]())[0].offsetHeight,this.$element.addClass("collapsing").removeClass("collapse").removeClass("in"),this.transitioning=1;var d=function(){this.transitioning=0,this.$element.trigger("hidden.bs.collapse").removeClass("collapsing").addClass("collapse")};return a.support.transition?(this.$element[c](0).one(a.support.transition.end,a.proxy(d,this)).emulateTransitionEnd(350),void 0):d.call(this)}}},b.prototype.toggle=function(){this[this.$element.hasClass("in")?"hide":"show"]()};var c=a.fn.collapse;a.fn.collapse=function(c){return this.each(function(){var d=a(this),e=d.data("bs.collapse"),f=a.extend({},b.DEFAULTS,d.data(),"object"==typeof c&&c);e||d.data("bs.collapse",e=new b(this,f)),"string"==typeof c&&e[c]()})},a.fn.collapse.Constructor=b,a.fn.collapse.noConflict=function(){return a.fn.collapse=c,this},a(document).on("click.bs.collapse.data-api","[data-toggle=collapse]",function(b){var c,d=a(this),e=d.attr("data-target")||b.preventDefault()||(c=d.attr("href"))&&c.replace(/.*(?=#[^\s]+$)/,""),f=a(e),g=f.data("bs.collapse"),h=g?"toggle":d.data(),i=d.attr("data-parent"),j=i&&a(i);g&&g.transitioning||(j&&j.find('[data-toggle=collapse][data-parent="'+i+'"]').not(d).addClass("collapsed"),d[f.hasClass("in")?"addClass":"removeClass"]("collapsed")),f.collapse(h)})}(jQuery),+function(a){"use strict";function b(){a(d).remove(),a(e).each(function(b){var d=c(a(this));d.hasClass("open")&&(d.trigger(b=a.Event("hide.bs.dropdown")),b.isDefaultPrevented()||d.removeClass("open").trigger("hidden.bs.dropdown"))})}function c(b){var c=b.attr("data-target");c||(c=b.attr("href"),c=c&&/#/.test(c)&&c.replace(/.*(?=#[^\s]*$)/,""));var d=c&&a(c);return d&&d.length?d:b.parent()}var d=".dropdown-backdrop",e="[data-toggle=dropdown]",f=function(b){a(b).on("click.bs.dropdown",this.toggle)};f.prototype.toggle=function(d){var e=a(this);if(!e.is(".disabled, :disabled")){var f=c(e),g=f.hasClass("open");if(b(),!g){if("ontouchstart"in document.documentElement&&!f.closest(".navbar-nav").length&&a('<div class="dropdown-backdrop"/>').insertAfter(a(this)).on("click",b),f.trigger(d=a.Event("show.bs.dropdown")),d.isDefaultPrevented())return;f.toggleClass("open").trigger("shown.bs.dropdown"),e.focus()}return!1}},f.prototype.keydown=function(b){if(/(38|40|27)/.test(b.keyCode)){var d=a(this);if(b.preventDefault(),b.stopPropagation(),!d.is(".disabled, :disabled")){var f=c(d),g=f.hasClass("open");if(!g||g&&27==b.keyCode)return 27==b.which&&f.find(e).focus(),d.click();var h=a("[role=menu] li:not(.divider):visible a",f);if(h.length){var i=h.index(h.filter(":focus"));38==b.keyCode&&i>0&&i--,40==b.keyCode&&i<h.length-1&&i++,~i||(i=0),h.eq(i).focus()}}}};var g=a.fn.dropdown;a.fn.dropdown=function(b){return this.each(function(){var c=a(this),d=c.data("bs.dropdown");d||c.data("bs.dropdown",d=new f(this)),"string"==typeof b&&d[b].call(c)})},a.fn.dropdown.Constructor=f,a.fn.dropdown.noConflict=function(){return a.fn.dropdown=g,this},a(document).on("click.bs.dropdown.data-api",b).on("click.bs.dropdown.data-api",".dropdown form",function(a){a.stopPropagation()}).on("click.bs.dropdown.data-api",e,f.prototype.toggle).on("keydown.bs.dropdown.data-api",e+", [role=menu]",f.prototype.keydown)}(jQuery),+function(a){"use strict";var b=function(b,c){this.options=c,this.$element=a(b),this.$backdrop=this.isShown=null,this.options.remote&&this.$element.load(this.options.remote)};b.DEFAULTS={backdrop:!0,keyboard:!0,show:!0},b.prototype.toggle=function(a){return this[this.isShown?"hide":"show"](a)},b.prototype.show=function(b){var c=this,d=a.Event("show.bs.modal",{relatedTarget:b});this.$element.trigger(d),this.isShown||d.isDefaultPrevented()||(this.isShown=!0,this.escape(),this.$element.on("click.dismiss.modal",'[data-dismiss="modal"]',a.proxy(this.hide,this)),this.backdrop(function(){var d=a.support.transition&&c.$element.hasClass("fade");c.$element.parent().length||c.$element.appendTo(document.body),c.$element.show(),d&&c.$element[0].offsetWidth,c.$element.addClass("in").attr("aria-hidden",!1),c.enforceFocus();var e=a.Event("shown.bs.modal",{relatedTarget:b});d?c.$element.find(".modal-dialog").one(a.support.transition.end,function(){c.$element.focus().trigger(e)}).emulateTransitionEnd(300):c.$element.focus().trigger(e)}))},b.prototype.hide=function(b){b&&b.preventDefault(),b=a.Event("hide.bs.modal"),this.$element.trigger(b),this.isShown&&!b.isDefaultPrevented()&&(this.isShown=!1,this.escape(),a(document).off("focusin.bs.modal"),this.$element.removeClass("in").attr("aria-hidden",!0).off("click.dismiss.modal"),a.support.transition&&this.$element.hasClass("fade")?this.$element.one(a.support.transition.end,a.proxy(this.hideModal,this)).emulateTransitionEnd(300):this.hideModal())},b.prototype.enforceFocus=function(){a(document).off("focusin.bs.modal").on("focusin.bs.modal",a.proxy(function(a){this.$element[0]===a.target||this.$element.has(a.target).length||this.$element.focus()},this))},b.prototype.escape=function(){this.isShown&&this.options.keyboard?this.$element.on("keyup.dismiss.bs.modal",a.proxy(function(a){27==a.which&&this.hide()},this)):this.isShown||this.$element.off("keyup.dismiss.bs.modal")},b.prototype.hideModal=function(){var a=this;this.$element.hide(),this.backdrop(function(){a.removeBackdrop(),a.$element.trigger("hidden.bs.modal")})},b.prototype.removeBackdrop=function(){this.$backdrop&&this.$backdrop.remove(),this.$backdrop=null},b.prototype.backdrop=function(b){var c=this.$element.hasClass("fade")?"fade":"";if(this.isShown&&this.options.backdrop){var d=a.support.transition&&c;if(this.$backdrop=a('<div class="modal-backdrop '+c+'" />').appendTo(document.body),this.$element.on("click.dismiss.modal",a.proxy(function(a){a.target===a.currentTarget&&("static"==this.options.backdrop?this.$element[0].focus.call(this.$element[0]):this.hide.call(this))},this)),d&&this.$backdrop[0].offsetWidth,this.$backdrop.addClass("in"),!b)return;d?this.$backdrop.one(a.support.transition.end,b).emulateTransitionEnd(150):b()}else!this.isShown&&this.$backdrop?(this.$backdrop.removeClass("in"),a.support.transition&&this.$element.hasClass("fade")?this.$backdrop.one(a.support.transition.end,b).emulateTransitionEnd(150):b()):b&&b()};var c=a.fn.modal;a.fn.modal=function(c,d){return this.each(function(){var e=a(this),f=e.data("bs.modal"),g=a.extend({},b.DEFAULTS,e.data(),"object"==typeof c&&c);f||e.data("bs.modal",f=new b(this,g)),"string"==typeof c?f[c](d):g.show&&f.show(d)})},a.fn.modal.Constructor=b,a.fn.modal.noConflict=function(){return a.fn.modal=c,this},a(document).on("click.bs.modal.data-api",'[data-toggle="modal"]',function(b){var c=a(this),d=c.attr("href"),e=a(c.attr("data-target")||d&&d.replace(/.*(?=#[^\s]+$)/,"")),f=e.data("modal")?"toggle":a.extend({remote:!/#/.test(d)&&d},e.data(),c.data());b.preventDefault(),e.modal(f,this).one("hide",function(){c.is(":visible")&&c.focus()})}),a(document).on("show.bs.modal",".modal",function(){a(document.body).addClass("modal-open")}).on("hidden.bs.modal",".modal",function(){a(document.body).removeClass("modal-open")})}(jQuery),+function(a){"use strict";var b=function(a,b){this.type=this.options=this.enabled=this.timeout=this.hoverState=this.$element=null,this.init("tooltip",a,b)};b.DEFAULTS={animation:!0,placement:"top",selector:!1,template:'<div class="tooltip"><div class="tooltip-arrow"></div><div class="tooltip-inner"></div></div>',trigger:"hover focus",title:"",delay:0,html:!1,container:!1},b.prototype.init=function(b,c,d){this.enabled=!0,this.type=b,this.$element=a(c),this.options=this.getOptions(d);for(var e=this.options.trigger.split(" "),f=e.length;f--;){var g=e[f];if("click"==g)this.$element.on("click."+this.type,this.options.selector,a.proxy(this.toggle,this));else if("manual"!=g){var h="hover"==g?"mouseenter":"focus",i="hover"==g?"mouseleave":"blur";this.$element.on(h+"."+this.type,this.options.selector,a.proxy(this.enter,this)),this.$element.on(i+"."+this.type,this.options.selector,a.proxy(this.leave,this))}}this.options.selector?this._options=a.extend({},this.options,{trigger:"manual",selector:""}):this.fixTitle()},b.prototype.getDefaults=function(){return b.DEFAULTS},b.prototype.getOptions=function(b){return b=a.extend({},this.getDefaults(),this.$element.data(),b),b.delay&&"number"==typeof b.delay&&(b.delay={show:b.delay,hide:b.delay}),b},b.prototype.getDelegateOptions=function(){var b={},c=this.getDefaults();return this._options&&a.each(this._options,function(a,d){c[a]!=d&&(b[a]=d)}),b},b.prototype.enter=function(b){var c=b instanceof this.constructor?b:a(b.currentTarget)[this.type](this.getDelegateOptions()).data("bs."+this.type);return clearTimeout(c.timeout),c.hoverState="in",c.options.delay&&c.options.delay.show?(c.timeout=setTimeout(function(){"in"==c.hoverState&&c.show()},c.options.delay.show),void 0):c.show()},b.prototype.leave=function(b){var c=b instanceof this.constructor?b:a(b.currentTarget)[this.type](this.getDelegateOptions()).data("bs."+this.type);return clearTimeout(c.timeout),c.hoverState="out",c.options.delay&&c.options.delay.hide?(c.timeout=setTimeout(function(){"out"==c.hoverState&&c.hide()},c.options.delay.hide),void 0):c.hide()},b.prototype.show=function(){var b=a.Event("show.bs."+this.type);if(this.hasContent()&&this.enabled){if(this.$element.trigger(b),b.isDefaultPrevented())return;var c=this.tip();this.setContent(),this.options.animation&&c.addClass("fade");var d="function"==typeof this.options.placement?this.options.placement.call(this,c[0],this.$element[0]):this.options.placement,e=/\s?auto?\s?/i,f=e.test(d);f&&(d=d.replace(e,"")||"top"),c.detach().css({top:0,left:0,display:"block"}).addClass(d),this.options.container?c.appendTo(this.options.container):c.insertAfter(this.$element);var g=this.getPosition(),h=c[0].offsetWidth,i=c[0].offsetHeight;if(f){var j=this.$element.parent(),k=d,l=document.documentElement.scrollTop||document.body.scrollTop,m="body"==this.options.container?window.innerWidth:j.outerWidth(),n="body"==this.options.container?window.innerHeight:j.outerHeight(),o="body"==this.options.container?0:j.offset().left;d="bottom"==d&&g.top+g.height+i-l>n?"top":"top"==d&&g.top-l-i<0?"bottom":"right"==d&&g.right+h>m?"left":"left"==d&&g.left-h<o?"right":d,c.removeClass(k).addClass(d)}var p=this.getCalculatedOffset(d,g,h,i);this.applyPlacement(p,d),this.$element.trigger("shown.bs."+this.type)}},b.prototype.applyPlacement=function(a,b){var c,d=this.tip(),e=d[0].offsetWidth,f=d[0].offsetHeight,g=parseInt(d.css("margin-top"),10),h=parseInt(d.css("margin-left"),10);isNaN(g)&&(g=0),isNaN(h)&&(h=0),a.top=a.top+g,a.left=a.left+h,d.offset(a).addClass("in");var i=d[0].offsetWidth,j=d[0].offsetHeight;if("top"==b&&j!=f&&(c=!0,a.top=a.top+f-j),/bottom|top/.test(b)){var k=0;a.left<0&&(k=-2*a.left,a.left=0,d.offset(a),i=d[0].offsetWidth,j=d[0].offsetHeight),this.replaceArrow(k-e+i,i,"left")}else this.replaceArrow(j-f,j,"top");c&&d.offset(a)},b.prototype.replaceArrow=function(a,b,c){this.arrow().css(c,a?50*(1-a/b)+"%":"")},b.prototype.setContent=function(){var a=this.tip(),b=this.getTitle();a.find(".tooltip-inner")[this.options.html?"html":"text"](b),a.removeClass("fade in top bottom left right")},b.prototype.hide=function(){function b(){"in"!=c.hoverState&&d.detach()}var c=this,d=this.tip(),e=a.Event("hide.bs."+this.type);return this.$element.trigger(e),e.isDefaultPrevented()?void 0:(d.removeClass("in"),a.support.transition&&this.$tip.hasClass("fade")?d.one(a.support.transition.end,b).emulateTransitionEnd(150):b(),this.$element.trigger("hidden.bs."+this.type),this)},b.prototype.fixTitle=function(){var a=this.$element;(a.attr("title")||"string"!=typeof a.attr("data-original-title"))&&a.attr("data-original-title",a.attr("title")||"").attr("title","")},b.prototype.hasContent=function(){return this.getTitle()},b.prototype.getPosition=function(){var b=this.$element[0];return a.extend({},"function"==typeof b.getBoundingClientRect?b.getBoundingClientRect():{width:b.offsetWidth,height:b.offsetHeight},this.$element.offset())},b.prototype.getCalculatedOffset=function(a,b,c,d){return"bottom"==a?{top:b.top+b.height,left:b.left+b.width/2-c/2}:"top"==a?{top:b.top-d,left:b.left+b.width/2-c/2}:"left"==a?{top:b.top+b.height/2-d/2,left:b.left-c}:{top:b.top+b.height/2-d/2,left:b.left+b.width}},b.prototype.getTitle=function(){var a,b=this.$element,c=this.options;return a=b.attr("data-original-title")||("function"==typeof c.title?c.title.call(b[0]):c.title)},b.prototype.tip=function(){return this.$tip=this.$tip||a(this.options.template)},b.prototype.arrow=function(){return this.$arrow=this.$arrow||this.tip().find(".tooltip-arrow")},b.prototype.validate=function(){this.$element[0].parentNode||(this.hide(),this.$element=null,this.options=null)},b.prototype.enable=function(){this.enabled=!0},b.prototype.disable=function(){this.enabled=!1},b.prototype.toggleEnabled=function(){this.enabled=!this.enabled},b.prototype.toggle=function(b){var c=b?a(b.currentTarget)[this.type](this.getDelegateOptions()).data("bs."+this.type):this;c.tip().hasClass("in")?c.leave(c):c.enter(c)},b.prototype.destroy=function(){this.hide().$element.off("."+this.type).removeData("bs."+this.type)};var c=a.fn.tooltip;a.fn.tooltip=function(c){return this.each(function(){var d=a(this),e=d.data("bs.tooltip"),f="object"==typeof c&&c;e||d.data("bs.tooltip",e=new b(this,f)),"string"==typeof c&&e[c]()})},a.fn.tooltip.Constructor=b,a.fn.tooltip.noConflict=function(){return a.fn.tooltip=c,this}}(jQuery),+function(a){"use strict";var b=function(a,b){this.init("popover",a,b)};if(!a.fn.tooltip)throw new Error("Popover requires tooltip.js");b.DEFAULTS=a.extend({},a.fn.tooltip.Constructor.DEFAULTS,{placement:"right",trigger:"click",content:"",template:'<div class="popover"><div class="arrow"></div><h3 class="popover-title"></h3><div class="popover-content"></div></div>'}),b.prototype=a.extend({},a.fn.tooltip.Constructor.prototype),b.prototype.constructor=b,b.prototype.getDefaults=function(){return b.DEFAULTS},b.prototype.setContent=function(){var a=this.tip(),b=this.getTitle(),c=this.getContent();a.find(".popover-title")[this.options.html?"html":"text"](b),a.find(".popover-content")[this.options.html?"html":"text"](c),a.removeClass("fade top bottom left right in"),a.find(".popover-title").html()||a.find(".popover-title").hide()},b.prototype.hasContent=function(){return this.getTitle()||this.getContent()},b.prototype.getContent=function(){var a=this.$element,b=this.options;return a.attr("data-content")||("function"==typeof b.content?b.content.call(a[0]):b.content)},b.prototype.arrow=function(){return this.$arrow=this.$arrow||this.tip().find(".arrow")},b.prototype.tip=function(){return this.$tip||(this.$tip=a(this.options.template)),this.$tip};var c=a.fn.popover;a.fn.popover=function(c){return this.each(function(){var d=a(this),e=d.data("bs.popover"),f="object"==typeof c&&c;e||d.data("bs.popover",e=new b(this,f)),"string"==typeof c&&e[c]()})},a.fn.popover.Constructor=b,a.fn.popover.noConflict=function(){return a.fn.popover=c,this}}(jQuery),+function(a){"use strict";function b(c,d){var e,f=a.proxy(this.process,this);this.$element=a(c).is("body")?a(window):a(c),this.$body=a("body"),this.$scrollElement=this.$element.on("scroll.bs.scroll-spy.data-api",f),this.options=a.extend({},b.DEFAULTS,d),this.selector=(this.options.target||(e=a(c).attr("href"))&&e.replace(/.*(?=#[^\s]+$)/,"")||"")+" .nav li > a",this.offsets=a([]),this.targets=a([]),this.activeTarget=null,this.refresh(),this.process()}b.DEFAULTS={offset:10},b.prototype.refresh=function(){var b=this.$element[0]==window?"offset":"position";this.offsets=a([]),this.targets=a([]);var c=this;this.$body.find(this.selector).map(function(){var d=a(this),e=d.data("target")||d.attr("href"),f=/^#\w/.test(e)&&a(e);return f&&f.length&&[[f[b]().top+(!a.isWindow(c.$scrollElement.get(0))&&c.$scrollElement.scrollTop()),e]]||null}).sort(function(a,b){return a[0]-b[0]}).each(function(){c.offsets.push(this[0]),c.targets.push(this[1])})},b.prototype.process=function(){var a,b=this.$scrollElement.scrollTop()+this.options.offset,c=this.$scrollElement[0].scrollHeight||this.$body[0].scrollHeight,d=c-this.$scrollElement.height(),e=this.offsets,f=this.targets,g=this.activeTarget;if(b>=d)return g!=(a=f.last()[0])&&this.activate(a);for(a=e.length;a--;)g!=f[a]&&b>=e[a]&&(!e[a+1]||b<=e[a+1])&&this.activate(f[a])},b.prototype.activate=function(b){this.activeTarget=b,a(this.selector).parents(".active").removeClass("active");var c=this.selector+'[data-target="'+b+'"],'+this.selector+'[href="'+b+'"]',d=a(c).parents("li").addClass("active");d.parent(".dropdown-menu").length&&(d=d.closest("li.dropdown").addClass("active")),d.trigger("activate.bs.scrollspy")};var c=a.fn.scrollspy;a.fn.scrollspy=function(c){return this.each(function(){var d=a(this),e=d.data("bs.scrollspy"),f="object"==typeof c&&c;e||d.data("bs.scrollspy",e=new b(this,f)),"string"==typeof c&&e[c]()})},a.fn.scrollspy.Constructor=b,a.fn.scrollspy.noConflict=function(){return a.fn.scrollspy=c,this},a(window).on("load",function(){a('[data-spy="scroll"]').each(function(){var b=a(this);b.scrollspy(b.data())})})}(jQuery),+function(a){"use strict";var b=function(b){this.element=a(b)};b.prototype.show=function(){var b=this.element,c=b.closest("ul:not(.dropdown-menu)"),d=b.data("target");if(d||(d=b.attr("href"),d=d&&d.replace(/.*(?=#[^\s]*$)/,"")),!b.parent("li").hasClass("active")){var e=c.find(".active:last a")[0],f=a.Event("show.bs.tab",{relatedTarget:e});if(b.trigger(f),!f.isDefaultPrevented()){var g=a(d);this.activate(b.parent("li"),c),this.activate(g,g.parent(),function(){b.trigger({type:"shown.bs.tab",relatedTarget:e})})}}},b.prototype.activate=function(b,c,d){function e(){f.removeClass("active").find("> .dropdown-menu > .active").removeClass("active"),b.addClass("active"),g?(b[0].offsetWidth,b.addClass("in")):b.removeClass("fade"),b.parent(".dropdown-menu")&&b.closest("li.dropdown").addClass("active"),d&&d()}var f=c.find("> .active"),g=d&&a.support.transition&&f.hasClass("fade");g?f.one(a.support.transition.end,e).emulateTransitionEnd(150):e(),f.removeClass("in")};var c=a.fn.tab;a.fn.tab=function(c){return this.each(function(){var d=a(this),e=d.data("bs.tab");e||d.data("bs.tab",e=new b(this)),"string"==typeof c&&e[c]()})},a.fn.tab.Constructor=b,a.fn.tab.noConflict=function(){return a.fn.tab=c,this},a(document).on("click.bs.tab.data-api",'[data-toggle="tab"], [data-toggle="pill"]',function(b){b.preventDefault(),a(this).tab("show")})}(jQuery),+function(a){"use strict";var b=function(c,d){this.options=a.extend({},b.DEFAULTS,d),this.$window=a(window).on("scroll.bs.affix.data-api",a.proxy(this.checkPosition,this)).on("click.bs.affix.data-api",a.proxy(this.checkPositionWithEventLoop,this)),this.$element=a(c),this.affixed=this.unpin=null,this.checkPosition()};b.RESET="affix affix-top affix-bottom",b.DEFAULTS={offset:0},b.prototype.checkPositionWithEventLoop=function(){setTimeout(a.proxy(this.checkPosition,this),1)},b.prototype.checkPosition=function(){if(this.$element.is(":visible")){var c=a(document).height(),d=this.$window.scrollTop(),e=this.$element.offset(),f=this.options.offset,g=f.top,h=f.bottom;"object"!=typeof f&&(h=g=f),"function"==typeof g&&(g=f.top()),"function"==typeof h&&(h=f.bottom());var i=null!=this.unpin&&d+this.unpin<=e.top?!1:null!=h&&e.top+this.$element.height()>=c-h?"bottom":null!=g&&g>=d?"top":!1;this.affixed!==i&&(this.unpin&&this.$element.css("top",""),this.affixed=i,this.unpin="bottom"==i?e.top-d:null,this.$element.removeClass(b.RESET).addClass("affix"+(i?"-"+i:"")),"bottom"==i&&this.$element.offset({top:document.body.offsetHeight-h-this.$element.height()}))}};var c=a.fn.affix;a.fn.affix=function(c){return this.each(function(){var d=a(this),e=d.data("bs.affix"),f="object"==typeof c&&c;e||d.data("bs.affix",e=new b(this,f)),"string"==typeof c&&e[c]()})},a.fn.affix.Constructor=b,a.fn.affix.noConflict=function(){return a.fn.affix=c,this},a(window).on("load",function(){a('[data-spy="affix"]').each(function(){var b=a(this),c=b.data();c.offset=c.offset||{},c.offsetBottom&&(c.offset.bottom=c.offsetBottom),c.offsetTop&&(c.offset.top=c.offsetTop),b.affix(c)})})}(jQuery);
 
 
 </script>


<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/multiselect.css">
  <script>
  
  
   var cus = document.getElementById('opts');
  (function (root, factory) {
      // check to see if 'knockout' AMD module is specified if using requirejs
      if (typeof define === 'function' && define.amd &&
          typeof require === 'function' && typeof require.specified === 'function' && require.specified('knockout')) {

          // AMD. Register as an anonymous module.
          define(['jquery', 'knockout'], factory);
      } else {
          // Browser globals
          factory(root.jQuery, root.ko);
      }
  })(this, function ($, ko) {
      "use strict";// jshint ;_;

      if (typeof ko !== 'undefined' && ko.bindingHandlers && !ko.bindingHandlers.multiselect) {
          ko.bindingHandlers.multiselect = {
              after: ['options', 'value', 'selectedOptions', 'enable', 'disable'],

              init: function(element, valueAccessor, allBindings, viewModel, bindingContext) {
                  var $element = $(element);
                  var config = ko.toJS(valueAccessor());

                  $element.multiselect(config);

                  if (allBindings.has('options')) {
                      var options = allBindings.get('options');
                      if (ko.isObservable(options)) {
                          ko.computed({
                              read: function() {
                                  options();
                                  setTimeout(function() {
                                      var ms = $element.data('multiselect');
                                      if (ms)
                                          ms.updateOriginalOptions();//Not sure how beneficial this is.
                                      $element.multiselect('rebuild');
                                  }, 1);
                              },
                              disposeWhenNodeIsRemoved: element
                          });
                      }
                  }

                  //value and selectedOptions are two-way, so these will be triggered even by our own actions.
                  //It needs some way to tell if they are triggered because of us or because of outside change.
                  //It doesn't loop but it's a waste of processing.
                  if (allBindings.has('value')) {
                      var value = allBindings.get('value');
                      if (ko.isObservable(value)) {
                          ko.computed({
                              read: function() {
                                  value();
                                  setTimeout(function() {
                                      $element.multiselect('refresh');
                                  }, 1);
                              },
                              disposeWhenNodeIsRemoved: element
                          }).extend({ rateLimit: 100, notifyWhenChangesStop: true });
                      }
                  }

                  //Switched from arrayChange subscription to general subscription using 'refresh'.
                  //Not sure performance is any better using 'select' and 'deselect'.
                  if (allBindings.has('selectedOptions')) {
                      var selectedOptions = allBindings.get('selectedOptions');
                      if (ko.isObservable(selectedOptions)) {
                          ko.computed({
                              read: function() {
                                  selectedOptions();
                                  setTimeout(function() {
                                      $element.multiselect('refresh');
                                  }, 1);
                              },
                              disposeWhenNodeIsRemoved: element
                          }).extend({ rateLimit: 100, notifyWhenChangesStop: true });
                      }
                  }

                  var setEnabled = function (enable) {
                      setTimeout(function () {
                          if (enable)
                              $element.multiselect('enable');
                          else
                              $element.multiselect('disable');
                      });
                  };

                  if (allBindings.has('enable')) {
                      var enable = allBindings.get('enable');
                      if (ko.isObservable(enable)) {
                          ko.computed({
                              read: function () {
                                  setEnabled(enable());
                              },
                              disposeWhenNodeIsRemoved: element
                          }).extend({ rateLimit: 100, notifyWhenChangesStop: true });
                      } else {
                          setEnabled(enable);
                      }
                  }

                  if (allBindings.has('disable')) {
                      var disable = allBindings.get('disable');
                      if (ko.isObservable(disable)) {
                          ko.computed({
                              read: function () {
                                  setEnabled(!disable());
                              },
                              disposeWhenNodeIsRemoved: element
                          }).extend({ rateLimit: 100, notifyWhenChangesStop: true });
                      } else {
                          setEnabled(!disable);
                      }
                  }

                  ko.utils.domNodeDisposal.addDisposeCallback(element, function() {
                      $element.multiselect('destroy');
                  });
              },

              update: function(element, valueAccessor, allBindings, viewModel, bindingContext) {
                  var $element = $(element);
                  var config = ko.toJS(valueAccessor());

                  $element.multiselect('setOptions', config);
                  $element.multiselect('rebuild');
              }
          };
      }

      function forEach(array, callback) {
          for (var index = 0; index < array.length; ++index) {
              callback(array[index], index);
          }
      }

      /**
       * Constructor to create a new multiselect using the given select.
       *
       * @param {jQuery} select
       * @param {Object} options
       * @returns {Multiselect}
       */
      function Multiselect(select, options) {

          this.$select = $(select);
          this.options = this.mergeOptions($.extend({}, options, this.$select.data()));

          // Placeholder via data attributes
          if (this.$select.attr("data-placeholder")) {
              this.options.nonSelectedText = this.$select.data("placeholder");
          }

          // Initialization.
          // We have to clone to create a new reference.
          this.originalOptions = this.$select.clone()[0].options;
          this.query = '';
          this.searchTimeout = null;
          this.lastToggledInput = null;

          this.options.multiple = this.$select.attr('multiple') === "multiple";
          this.options.onChange = $.proxy(this.options.onChange, this);
          this.options.onSelectAll = $.proxy(this.options.onSelectAll, this);
          this.options.onDeselectAll = $.proxy(this.options.onDeselectAll, this);
          this.options.onDropdownShow = $.proxy(this.options.onDropdownShow, this);
          this.options.onDropdownHide = $.proxy(this.options.onDropdownHide, this);
          this.options.onDropdownShown = $.proxy(this.options.onDropdownShown, this);
          this.options.onDropdownHidden = $.proxy(this.options.onDropdownHidden, this);
          this.options.onInitialized = $.proxy(this.options.onInitialized, this);
          this.options.onFiltering = $.proxy(this.options.onFiltering, this);

          // Build select all if enabled.
          this.buildContainer();
          this.buildButton();
          this.buildDropdown();
          this.buildSelectAll();
          this.buildDropdownOptions();
          this.buildFilter();

          this.updateButtonText();
          this.updateSelectAll(true);

          if (this.options.enableClickableOptGroups && this.options.multiple) {
              this.updateOptGroups();
          }

          this.options.wasDisabled = this.$select.prop('disabled');
          if (this.options.disableIfEmpty && $('option', this.$select).length <= 0) {
              this.disable();
          }

          this.$select.wrap('<span class="multiselect-native-select" />').after(this.$container);
          this.options.onInitialized(this.$select, this.$container);
      }

      Multiselect.prototype = {

          defaults: {
              /**
               * Default text function will either print 'None selected' in case no
               * option is selected or a list of the selected options up to a length
               * of 3 selected options.
               *
               * @param {jQuery} options
               * @param {jQuery} select
               * @returns {String}
               */
              buttonText: function(options, select) {
                  if (this.disabledText.length > 0
                          && (select.prop('disabled') || (options.length == 0 && this.disableIfEmpty)))  {

                      return this.disabledText;
                  }
                  else if (options.length === 0) {
                      return this.nonSelectedText;
                  }
                  else if (this.allSelectedText
                          && options.length === $('option', $(select)).length
                          && $('option', $(select)).length !== 1
                          && this.multiple) {

                      if (this.selectAllNumber) {
                          return this.allSelectedText + ' (' + options.length + ')';
                      }
                      else {
                          return this.allSelectedText;
                      }
                  }
                  else if (this.numberDisplayed != 0 && options.length > this.numberDisplayed) {
                      return options.length + ' ' + this.nSelectedText;
                  }
                  else {
                      var selected = '';
                      var delimiter = this.delimiterText;

                      options.each(function() {
                          var label = ($(this).attr('label') !== undefined) ? $(this).attr('label') : $(this).text();
                          selected += label + delimiter;
                      });

                      return selected.substr(0, selected.length - this.delimiterText.length);
                  }
              },
              /**
               * Updates the title of the button similar to the buttonText function.
               *
               * @param {jQuery} options
               * @param {jQuery} select
               * @returns {@exp;selected@call;substr}
               */
              buttonTitle: function(options, select) {
                  if (options.length === 0) {
                      return this.nonSelectedText;
                  }
                  else {
                      var selected = '';
                      var delimiter = this.delimiterText;

                      options.each(function () {
                          var label = ($(this).attr('label') !== undefined) ? $(this).attr('label') : $(this).text();
                          selected += label + delimiter;
                      });
                      return selected.substr(0, selected.length - this.delimiterText.length);
                  }
              },
              checkboxName: function(option) {
                  return false; // no checkbox name
              },
              /**
               * Create a label.
               *
               * @param {jQuery} element
               * @returns {String}
               */
              optionLabel: function(element){
                  return $(element).attr('label') || $(element).text();
              },
              /**
               * Create a class.
               *
               * @param {jQuery} element
               * @returns {String}
               */
              optionClass: function(element) {
                  return $(element).attr('class') || '';
              },
              /**
               * Triggered on change of the multiselect.
               *
               * Not triggered when selecting/deselecting options manually.
               *
               * @param {jQuery} option
               * @param {Boolean} checked
               */
              onChange : function(option, checked) {

              },
              /**
               * Triggered when the dropdown is shown.
               *
               * @param {jQuery} event
               */
              onDropdownShow: function(event) {

              },
              /**
               * Triggered when the dropdown is hidden.
               *
               * @param {jQuery} event
               */
              onDropdownHide: function(event) {

              },
              /**
               * Triggered after the dropdown is shown.
               *
               * @param {jQuery} event
               */
              onDropdownShown: function(event) {

              },
              /**
               * Triggered after the dropdown is hidden.
               *
               * @param {jQuery} event
               */
              onDropdownHidden: function(event) {

              },
              /**
               * Triggered on select all.
               */
              onSelectAll: function() {

              },
              /**
               * Triggered on deselect all.
               */
              onDeselectAll: function() {

              },
              /**
               * Triggered after initializing.
               *
               * @param {jQuery} $select
               * @param {jQuery} $container
               */
              onInitialized: function($select, $container) {

              },
              /**
               * Triggered on filtering.
               *
               * @param {jQuery} $filter
               */
              onFiltering: function($filter) {

              },
            
              enableHTML: false,
              buttonClass: 'btn btn-default',
              inheritClass: false,
              buttonWidth: 'auto',
              buttonContainer: '<div class="btn-group" />',
              dropRight: false,
              dropUp: false,
              selectedClass: 'active',
              // Maximum height of the dropdown menu.
              // If maximum height is exceeded a scrollbar will be displayed.
              maxHeight: false,
              includeSelectAllOption: false,
              includeSelectAllIfMoreThan: 0,
              selectAllText: ' Select all',
              selectAllValue: 'multiselect-all',
              selectAllName: false,
              selectAllNumber: true,
              selectAllJustVisible: true,
              enableFiltering: false,
              enableCaseInsensitiveFiltering: false,
              enableFullValueFiltering: false,
              enableClickableOptGroups: false,
              enableCollapsibleOptGroups: false,
              filterPlaceholder: 'Search',
              // possible options: 'text', 'value', 'both'
              filterBehavior: 'text',
              includeFilterClearBtn: true,
              preventInputChangeEvent: false,
              nonSelectedText: 'None selected',
              nSelectedText: 'selected',
              allSelectedText: 'All selected',
              numberDisplayed: 1,
              disableIfEmpty: false,
              disabledText: '',
              delimiterText: ', ',
              templates: {
                  button: '<button type="button" class="multiselect dropdown-toggle" data-toggle="dropdown"><span class="multiselect-selected-text"></span> <b class="caret"></b></button>',
                  ul: '<ul class="multiselect-container dropdown-menu"></ul>',
                  filter: '<li class="multiselect-item multiselect-filter"><div class="input-group"><span class="input-group-addon"><i class="glyphicon glyphicon-search"></i></span><input class="form-control multiselect-search" type="text" /></div></li>',
                  filterClearBtn: '<span class="input-group-btn"><button class="btn btn-default multiselect-clear-filter" type="button"><i class="glyphicon glyphicon-remove-circle"></i></button></span>',
                  li: '<li><a tabindex="0"><label></label></a></li>',
                  divider: '<li class="multiselect-item divider"></li>',
                  liGroup: '<li class="multiselect-item multiselect-group"><label></label></li>'
              }
          },

          constructor: Multiselect,

          /**
           * Builds the container of the multiselect.
           */
          buildContainer: function() {
              this.$container = $(this.options.buttonContainer);
              this.$container.on('show.bs.dropdown', this.options.onDropdownShow);
              this.$container.on('hide.bs.dropdown', this.options.onDropdownHide);
              this.$container.on('shown.bs.dropdown', this.options.onDropdownShown);
              this.$container.on('hidden.bs.dropdown', this.options.onDropdownHidden);
          },

          /**
           * Builds the button of the multiselect.
           */
          buildButton: function() {
              this.$button = $(this.options.templates.button).addClass(this.options.buttonClass);
              if (this.$select.attr('class') && this.options.inheritClass) {
                  this.$button.addClass(this.$select.attr('class'));
              }
              // Adopt active state.
              if (this.$select.prop('disabled')) {
                  this.disable();
              }
              else {
                  this.enable();
              }

              // Manually add button width if set.
              if (this.options.buttonWidth && this.options.buttonWidth !== 'auto') {
                  this.$button.css({
                      'width' : '100%', //this.options.buttonWidth,
                      'overflow' : 'hidden',
                      'text-overflow' : 'ellipsis'
                  });
                  this.$container.css({
                      'width': this.options.buttonWidth
                  });
              }

              // Keep the tab index from the select.
              var tabindex = this.$select.attr('tabindex');
              if (tabindex) {
                  this.$button.attr('tabindex', tabindex);
              }

              this.$container.prepend(this.$button);
          },

          /**
           * Builds the ul representing the dropdown menu.
           */
          buildDropdown: function() {

              // Build ul.
              this.$ul = $(this.options.templates.ul);

              if (this.options.dropRight) {
                  this.$ul.addClass('pull-right');
              }

              // Set max height of dropdown menu to activate auto scrollbar.
              if (this.options.maxHeight) {
                  // TODO: Add a class for this option to move the css declarations.
                  this.$ul.css({
                      'max-height': this.options.maxHeight + 'px',
                      'overflow-y': 'auto',
                      'overflow-x': 'hidden'
                  });
              }

              if (this.options.dropUp) {

                  var height = Math.min(this.options.maxHeight, $('option[data-role!="divider"]', this.$select).length*26 + $('option[data-role="divider"]', this.$select).length*19 + (this.options.includeSelectAllOption ? 26 : 0) + (this.options.enableFiltering || this.options.enableCaseInsensitiveFiltering ? 44 : 0));
                  var moveCalc = height + 34;

                  this.$ul.css({
                      'max-height': height + 'px',
                      'overflow-y': 'auto',
                      'overflow-x': 'hidden',
                      'margin-top': "-" + moveCalc + 'px'
                  });
              }

              this.$container.append(this.$ul);
          },

          /**
           * Build the dropdown options and binds all necessary events.
           *
           * Uses createDivider and createOptionValue to create the necessary options.
           */
          buildDropdownOptions: function() {

              this.$select.children().each($.proxy(function(index, element) {

                  var $element = $(element);
                  // Support optgroups and options without a group simultaneously.
                  var tag = $element.prop('tagName')
                      .toLowerCase();

                  if ($element.prop('value') === this.options.selectAllValue) {
                      return;
                  }

                  if (tag === 'optgroup') {
                      this.createOptgroup(element);
                  }
                  else if (tag === 'option') {

                      if ($element.data('role') === 'divider') {
                          this.createDivider();
                      }
                      else {
                          this.createOptionValue(element);
                      }

                  }

                  // Other illegal tags will be ignored.
              }, this));

              // Bind the change event on the dropdown elements.
              $(this.$ul).off('change', 'li:not(.multiselect-group) input[type="checkbox"], li:not(.multiselect-group) input[type="radio"]');
              $(this.$ul).on('change', 'li:not(.multiselect-group) input[type="checkbox"], li:not(.multiselect-group) input[type="radio"]', $.proxy(function(event) {
                  var $target = $(event.target);

                  var checked = $target.prop('checked') || false;
                  var isSelectAllOption = $target.val() === this.options.selectAllValue;

                  // Apply or unapply the configured selected class.
                  if (this.options.selectedClass) {
                      if (checked) {
                          $target.closest('li')
                              .addClass(this.options.selectedClass);
                      }
                      else {
                          $target.closest('li')
                              .removeClass(this.options.selectedClass);
                      }
                  }

                  // Get the corresponding option.
                  var value = $target.val();
                  var $option = this.getOptionByValue(value);

                  var $optionsNotThis = $('option', this.$select).not($option);
                  var $checkboxesNotThis = $('input', this.$container).not($target);

                  if (isSelectAllOption) {

                      if (checked) {
                          this.selectAll(this.options.selectAllJustVisible, true);
                      }
                      else {
                          this.deselectAll(this.options.selectAllJustVisible, true);
                      }
                  }
                  else {
                      if (checked) {
                          $option.prop('selected', true);

                          if (this.options.multiple) {
                              // Simply select additional option.
                              $option.prop('selected', true);
                          }
                          else {
                              // Unselect all other options and corresponding checkboxes.
                              if (this.options.selectedClass) {
                                  $($checkboxesNotThis).closest('li').removeClass(this.options.selectedClass);
                              }

                              $($checkboxesNotThis).prop('checked', false);
                              $optionsNotThis.prop('selected', false);

                              // It's a single selection, so close.
                              this.$button.click();
                          }

                          if (this.options.selectedClass === "active") {
                              $optionsNotThis.closest("a").css("outline", "");
                          }
                      }
                      else {
                          // Unselect option.
                          $option.prop('selected', false);
                      }

                      // To prevent select all from firing onChange: #575
                      this.options.onChange($option, checked);

                      // Do not update select all or optgroups on select all change!
                      this.updateSelectAll();

                      if (this.options.enableClickableOptGroups && this.options.multiple) {
                          this.updateOptGroups();
                      }
                  }

                  this.$select.change();
                  this.updateButtonText();

                  if(this.options.preventInputChangeEvent) {
                      return false;
                  }
              }, this));

              $('li a', this.$ul).on('mousedown', function(e) {
                  if (e.shiftKey) {
                      // Prevent selecting text by Shift+click
                      return false;
                  }
              });

              $(this.$ul).on('touchstart click', 'li a', $.proxy(function(event) {
                  event.stopPropagation();

                  var $target = $(event.target);

                  if (event.shiftKey && this.options.multiple) {
                      if($target.is("label")){ // Handles checkbox selection manually (see https://github.com/davidstutz/bootstrap-multiselect/issues/431)
                          event.preventDefault();
                          $target = $target.find("input");
                          $target.prop("checked", !$target.prop("checked"));
                      }
                      var checked = $target.prop('checked') || false;

                      if (this.lastToggledInput !== null && this.lastToggledInput !== $target) { // Make sure we actually have a range
                          var from = this.$ul.find("li:visible").index($target.parents("li"));
                          var to = this.$ul.find("li:visible").index(this.lastToggledInput.parents("li"));

                          if (from > to) { // Swap the indices
                              var tmp = to;
                              to = from;
                              from = tmp;
                          }

                          // Make sure we grab all elements since slice excludes the last index
                          ++to;

                          // Change the checkboxes and underlying options
                          var range = this.$ul.find("li").not(".multiselect-filter-hidden").slice(from, to).find("input");

                          range.prop('checked', checked);

                          if (this.options.selectedClass) {
                              range.closest('li')
                                  .toggleClass(this.options.selectedClass, checked);
                          }

                          for (var i = 0, j = range.length; i < j; i++) {
                              var $checkbox = $(range[i]);

                              var $option = this.getOptionByValue($checkbox.val());

                              $option.prop('selected', checked);
                          }
                      }

                      // Trigger the select "change" event
                      $target.trigger("change");
                  }

                  // Remembers last clicked option
                  if($target.is("input") && !$target.closest("li").is(".multiselect-item")){
                      this.lastToggledInput = $target;
                  }

                  $target.blur();
              }, this));

              // Keyboard support.
              this.$container.off('keydown.multiselect').on('keydown.multiselect', $.proxy(function(event) {
                  if ($('input[type="text"]', this.$container).is(':focus')) {
                      return;
                  }

                  if (event.keyCode === 9 && this.$container.hasClass('open')) {
                      this.$button.click();
                  }
                  else {
                      var $items = $(this.$container).find("li:not(.divider):not(.disabled) a").filter(":visible");

                      if (!$items.length) {
                          return;
                      }

                      var index = $items.index($items.filter(':focus'));
                      
                      // Navigation up.
                      if (event.keyCode === 38 && index > 0) {
                          index--;
                      }
                      // Navigate down.
                      else if (event.keyCode === 40 && index < $items.length - 1) {
                          index++;
                      }
                      else if (!~index) {
                          index = 0;
                      }

                      var $current = $items.eq(index);
                      $current.focus();

                      if (event.keyCode === 32 || event.keyCode === 13) {
                          var $checkbox = $current.find('input');

                          $checkbox.prop("checked", !$checkbox.prop("checked"));
                          $checkbox.change();
                      }

                      event.stopPropagation();
                      event.preventDefault();
                  }
              }, this));

              if (this.options.enableClickableOptGroups && this.options.multiple) {
                  $("li.multiselect-group input", this.$ul).on("change", $.proxy(function(event) {
                      event.stopPropagation();

                      var $target = $(event.target);
                      var checked = $target.prop('checked') || false;

                      var $li = $(event.target).closest('li');
                      var $group = $li.nextUntil("li.multiselect-group")
                          .not('.multiselect-filter-hidden')
                          .not('.disabled');

                      var $inputs = $group.find("input");

                      var values = [];
                      var $options = [];

                      if (this.options.selectedClass) {
                          if (checked) {
                              $li.addClass(this.options.selectedClass);
                          }
                          else {
                              $li.removeClass(this.options.selectedClass);
                          }
                      }

                      $.each($inputs, $.proxy(function(index, input) {
                          var value = $(input).val();
                          var $option = this.getOptionByValue(value);

                          if (checked) {
                              $(input).prop('checked', true);
                              $(input).closest('li')
                                  .addClass(this.options.selectedClass);

                              $option.prop('selected', true);
                          }
                          else {
                              $(input).prop('checked', false);
                              $(input).closest('li')
                                  .removeClass(this.options.selectedClass);

                              $option.prop('selected', false);
                          }

                          $options.push(this.getOptionByValue(value));
                      }, this))

                      // Cannot use select or deselect here because it would call updateOptGroups again.

                      this.options.onChange($options, checked);

                      this.$select.change();
                      this.updateButtonText();
                      this.updateSelectAll();
                  }, this));
              }

              if (this.options.enableCollapsibleOptGroups && this.options.multiple) {
                  $("li.multiselect-group .caret-container", this.$ul).on("click", $.proxy(function(event) {
                      var $li = $(event.target).closest('li');
                      var $inputs = $li.nextUntil("li.multiselect-group")
                              .not('.multiselect-filter-hidden');

                      var visible = true;
                      $inputs.each(function() {
                          visible = visible && $(this).is(':visible');
                      });

                      if (visible) {
                          $inputs.hide()
                              .addClass('multiselect-collapsible-hidden');
                      }
                      else {
                          $inputs.show()
                              .removeClass('multiselect-collapsible-hidden');
                      }
                  }, this));

                  $("li.multiselect-all", this.$ul).css('background', '#f3f3f3').css('border-bottom', '1px solid #eaeaea');
                  $("li.multiselect-all > a > label.checkbox", this.$ul).css('padding', '3px 20px 3px 35px');
                  $("li.multiselect-group > a > input", this.$ul).css('margin', '4px 0px 5px -20px');
              }
          },

          /**
           * Create an option using the given select option.
           *
           * @param {jQuery} element
           */
          createOptionValue: function(element) {
              var $element = $(element);
              if ($element.is(':selected')) {
                  $element.prop('selected', true);
              }

              // Support the label attribute on options.
              var label = this.options.optionLabel(element);
              var classes = this.options.optionClass(element);
              var value = $element.val();
              var inputType = this.options.multiple ? "checkbox" : "radio";

              var $li = $(this.options.templates.li);
              var $label = $('label', $li);
              $label.addClass(inputType);
              $li.addClass(classes);

              if (this.options.enableHTML) {
                  $label.html(" " + label);
              }
              else {
                  $label.text(" " + label);
              }

              var $checkbox = $('<input/>').attr('type', inputType);

              var name = this.options.checkboxName($element);
              if (name) {
                  $checkbox.attr('name', name);
              }

              $label.prepend($checkbox);

              var selected = $element.prop('selected') || false;
              $checkbox.val(value);

              if (value === this.options.selectAllValue) {
                  $li.addClass("multiselect-item multiselect-all");
                  $checkbox.parent().parent()
                      .addClass('multiselect-all');
              }

              $label.attr('title', $element.attr('title'));

              this.$ul.append($li);

              if ($element.is(':disabled')) {
                  $checkbox.attr('disabled', 'disabled')
                      .prop('disabled', true)
                      .closest('a')
                      .attr("tabindex", "-1")
                      .closest('li')
                      .addClass('disabled');
              }

              $checkbox.prop('checked', selected);

              if (selected && this.options.selectedClass) {
                  $checkbox.closest('li')
                      .addClass(this.options.selectedClass);
              }
          },

          /**
           * Creates a divider using the given select option.
           *
           * @param {jQuery} element
           */
          createDivider: function(element) {
              var $divider = $(this.options.templates.divider);
              this.$ul.append($divider);
          },

          /**
           * Creates an optgroup.
           *
           * @param {jQuery} group
           */
          createOptgroup: function(group) {
              var label = $(group).attr("label");
              var value = $(group).attr("value");
              var $li = $('<li class="multiselect-item multiselect-group"><a href="javascript:void(0);"><label><b></b></label></a></li>');

              var classes = this.options.optionClass(group);
              $li.addClass(classes);

              if (this.options.enableHTML) {
                  $('label b', $li).html(" " + label);
              }
              else {
                  $('label b', $li).text(" " + label);
              }

              if (this.options.enableCollapsibleOptGroups && this.options.multiple) {
                  $('a', $li).append('<span class="caret-container"><b class="caret"></b></span>');
              }

              if (this.options.enableClickableOptGroups && this.options.multiple) {
                  $('a label', $li).prepend('<input type="checkbox" value="' + value + '"/>');
              }

              if ($(group).is(':disabled')) {
                  $li.addClass('disabled');
              }

              this.$ul.append($li);

              $("option", group).each($.proxy(function($, group) {
                  this.createOptionValue(group);
              }, this))
          },

          /**
           * Build the select all.
           *
           * Checks if a select all has already been created.
           */
          buildSelectAll: function() {
              if (typeof this.options.selectAllValue === 'number') {
                  this.options.selectAllValue = this.options.selectAllValue.toString();
              }

              var alreadyHasSelectAll = this.hasSelectAll();

              if (!alreadyHasSelectAll && this.options.includeSelectAllOption && this.options.multiple
                      && $('option', this.$select).length > this.options.includeSelectAllIfMoreThan) {

                  // Check whether to add a divider after the select all.
                  if (this.options.includeSelectAllDivider) {
                      this.$ul.prepend($(this.options.templates.divider));
                  }

                  var $li = $(this.options.templates.li);
                  $('label', $li).addClass("checkbox");

                  if (this.options.enableHTML) {
                      $('label', $li).html(" " + this.options.selectAllText);
                  }
                  else {
                      $('label', $li).text(" " + this.options.selectAllText);
                  }

                  if (this.options.selectAllName) {
                      $('label', $li).prepend('<input type="checkbox" name="' + this.options.selectAllName + '" />');
                  }
                  else {
                      $('label', $li).prepend('<input type="checkbox" />');
                  }

                  var $checkbox = $('input', $li);
                  $checkbox.val(this.options.selectAllValue);

                  $li.addClass("multiselect-item multiselect-all");
                  $checkbox.parent().parent()
                      .addClass('multiselect-all');

                  this.$ul.prepend($li);

                  $checkbox.prop('checked', false);
              }
          },

          /**
           * Builds the filter.
           */
          buildFilter: function() {

              // Build filter if filtering OR case insensitive filtering is enabled and the number of options exceeds (or equals) enableFilterLength.
              if (this.options.enableFiltering || this.options.enableCaseInsensitiveFiltering) {
                  var enableFilterLength = Math.max(this.options.enableFiltering, this.options.enableCaseInsensitiveFiltering);

                  if (this.$select.find('option').length >= enableFilterLength) {

                      this.$filter = $(this.options.templates.filter);
                      $('input', this.$filter).attr('placeholder', this.options.filterPlaceholder);

                      // Adds optional filter clear button
                      if(this.options.includeFilterClearBtn) {
                          var clearBtn = $(this.options.templates.filterClearBtn);
                          clearBtn.on('click', $.proxy(function(event){
                              clearTimeout(this.searchTimeout);

                              this.query = '';
                              this.$filter.find('.multiselect-search').val('');
                              $('li', this.$ul).show().removeClass('multiselect-filter-hidden');

                              this.updateSelectAll();

                              if (this.options.enableClickableOptGroups && this.options.multiple) {
                                  this.updateOptGroups();
                              }

                          }, this));
                          this.$filter.find('.input-group').append(clearBtn);
                      }

                      this.$ul.prepend(this.$filter);

                      this.$filter.val(this.query).on('click', function(event) {
                          event.stopPropagation();
                      }).on('input keydown', $.proxy(function(event) {
                          // Cancel enter key default behaviour
                          if (event.which === 13) {
                            event.preventDefault();
                        }

                          // This is useful to catch "keydown" events after the browser has updated the control.
                          clearTimeout(this.searchTimeout);

                          this.searchTimeout = this.asyncFunction($.proxy(function() {

                              if (this.query !== event.target.value) {
                                  this.query = event.target.value;

                                  var currentGroup, currentGroupVisible;
                                  $.each($('li', this.$ul), $.proxy(function(index, element) {
                                      var value = $('input', element).length > 0 ? $('input', element).val() : "";
                                      var text = $('label', element).text();

                                      var filterCandidate = '';
                                      if ((this.options.filterBehavior === 'text')) {
                                          filterCandidate = text;
                                      }
                                      else if ((this.options.filterBehavior === 'value')) {
                                          filterCandidate = value;
                                      }
                                      else if (this.options.filterBehavior === 'both') {
                                          filterCandidate = text + '\n' + value;
                                      }

                                      if (value !== this.options.selectAllValue && text) {

                                          // By default lets assume that element is not
                                          // interesting for this search.
                                          var showElement = false;

                                          if (this.options.enableCaseInsensitiveFiltering) {
                                              filterCandidate = filterCandidate.toLowerCase();
                                              this.query = this.query.toLowerCase();
                                          }

                                          if (this.options.enableFullValueFiltering && this.options.filterBehavior !== 'both') {
                                              var valueToMatch = filterCandidate.trim().substring(0, this.query.length);
                                              if (this.query.indexOf(valueToMatch) > -1) {
                                                  showElement = true;
                                              }
                                          }
                                          else if (filterCandidate.indexOf(this.query) > -1) {
                                              showElement = true;
                                          }

                                          // Toggle current element (group or group item) according to showElement boolean.
                                          if(!showElement){
                                            $(element).css('display', 'none');
                                            $(element).addClass('multiselect-filter-hidden');
                                          }
                                          if(showElement){
                                            $(element).css('display', 'block');
                                            $(element).removeClass('multiselect-filter-hidden');
                                          }

                                          // Differentiate groups and group items.
                                          if ($(element).hasClass('multiselect-group')) {
                                              // Remember group status.
                                              currentGroup = element;
                                              currentGroupVisible = showElement;
                                          }
                                          else {
                                              // Show group name when at least one of its items is visible.
                                              if (showElement) {
                                                  $(currentGroup).show()
                                                      .removeClass('multiselect-filter-hidden');
                                              }

                                              // Show all group items when group name satisfies filter.
                                              if (!showElement && currentGroupVisible) {
                                                  $(element).show()
                                                      .removeClass('multiselect-filter-hidden');
                                              }
                                          }
                                      }
                                  }, this));
                              }

                              this.updateSelectAll();

                              if (this.options.enableClickableOptGroups && this.options.multiple) {
                                  this.updateOptGroups();
                              }

                              this.options.onFiltering(event.target);

                          }, this), 300, this);
                      }, this));
                  }
              }
          },

          /**
           * Unbinds the whole plugin.
           */
          destroy: function() {
              this.$container.remove();
              this.$select.show();

              // reset original state
              this.$select.prop('disabled', this.options.wasDisabled);

              this.$select.data('multiselect', null);
          },

          /**
           * Refreshs the multiselect based on the selected options of the select.
           */
          refresh: function () {
              var inputs = {};
              $('li input', this.$ul).each(function() {
                inputs[$(this).val()] = $(this);
              });

              $('option', this.$select).each($.proxy(function (index, element) {
                  var $elem = $(element);
                  var $input = inputs[$(element).val()];

                  if ($elem.is(':selected')) {
                      $input.prop('checked', true);

                      if (this.options.selectedClass) {
                          $input.closest('li')
                              .addClass(this.options.selectedClass);
                      }
                  }
                  else {
                      $input.prop('checked', false);

                      if (this.options.selectedClass) {
                          $input.closest('li')
                              .removeClass(this.options.selectedClass);
                      }
                  }

                  if ($elem.is(":disabled")) {
                      $input.attr('disabled', 'disabled')
                          .prop('disabled', true)
                          .closest('li')
                          .addClass('disabled');
                  }
                  else {
                      $input.prop('disabled', false)
                          .closest('li')
                          .removeClass('disabled');
                  }
              }, this));

              this.updateButtonText();
              this.updateSelectAll();

              if (this.options.enableClickableOptGroups && this.options.multiple) {
                  this.updateOptGroups();
              }
          },

          /**
           * Select all options of the given values.
           *
           * If triggerOnChange is set to true, the on change event is triggered if
           * and only if one value is passed.
           *
           * @param {Array} selectValues
           * @param {Boolean} triggerOnChange
           */
          select: function(selectValues, triggerOnChange) {
              if(!$.isArray(selectValues)) {
                  selectValues = [selectValues];
              }

              for (var i = 0; i < selectValues.length; i++) {
                  var value = selectValues[i];

                  if (value === null || value === undefined) {
                      continue;
                  }

                  var $option = this.getOptionByValue(value);
                  var $checkbox = this.getInputByValue(value);

                  if($option === undefined || $checkbox === undefined) {
                      continue;
                  }

                  if (!this.options.multiple) {
                      this.deselectAll(false);
                  }

                  if (this.options.selectedClass) {
                      $checkbox.closest('li')
                          .addClass(this.options.selectedClass);
                  }

                  $checkbox.prop('checked', true);
                  $option.prop('selected', true);

                  if (triggerOnChange) {
                      this.options.onChange($option, true);
                  }
              }

              this.updateButtonText();
              this.updateSelectAll();

              if (this.options.enableClickableOptGroups && this.options.multiple) {
                  this.updateOptGroups();
              }
          },

          /**
           * Clears all selected items.
           */
          clearSelection: function () {
              this.deselectAll(false);
              this.updateButtonText();
              this.updateSelectAll();

              if (this.options.enableClickableOptGroups && this.options.multiple) {
                  this.updateOptGroups();
              }
          },

          /**
           * Deselects all options of the given values.
           *
           * If triggerOnChange is set to true, the on change event is triggered, if
           * and only if one value is passed.
           *
           * @param {Array} deselectValues
           * @param {Boolean} triggerOnChange
           */
          deselect: function(deselectValues, triggerOnChange) {
              if(!$.isArray(deselectValues)) {
                  deselectValues = [deselectValues];
              }

              for (var i = 0; i < deselectValues.length; i++) {
                  var value = deselectValues[i];

                  if (value === null || value === undefined) {
                      continue;
                  }

                  var $option = this.getOptionByValue(value);
                  var $checkbox = this.getInputByValue(value);

                  if($option === undefined || $checkbox === undefined) {
                      continue;
                  }

                  if (this.options.selectedClass) {
                      $checkbox.closest('li')
                          .removeClass(this.options.selectedClass);
                  }

                  $checkbox.prop('checked', false);
                  $option.prop('selected', false);

                  if (triggerOnChange) {
                      this.options.onChange($option, false);
                  }
              }

              this.updateButtonText();
              this.updateSelectAll();

              if (this.options.enableClickableOptGroups && this.options.multiple) {
                  this.updateOptGroups();
              }
          },

          /**
           * Selects all enabled & visible options.
           *
           * If justVisible is true or not specified, only visible options are selected.
           *
           * @param {Boolean} justVisible
           * @param {Boolean} triggerOnSelectAll
           */
          selectAll: function (justVisible, triggerOnSelectAll) {

              var justVisible = typeof justVisible === 'undefined' ? true : justVisible;
              var allLis = $("li:not(.divider):not(.disabled):not(.multiselect-group)", this.$ul);
              var visibleLis = $("li:not(.divider):not(.disabled):not(.multiselect-group):not(.multiselect-filter-hidden):not(.multiselect-collapisble-hidden)", this.$ul).filter(':visible');

              if(justVisible) {
                  $('input:enabled' , visibleLis).prop('checked', true);
                  visibleLis.addClass(this.options.selectedClass);

                  $('input:enabled' , visibleLis).each($.proxy(function(index, element) {
                      var value = $(element).val();
                      var option = this.getOptionByValue(value);
                      $(option).prop('selected', true);
                  }, this));
              }
              else {
                  $('input:enabled' , allLis).prop('checked', true);
                  allLis.addClass(this.options.selectedClass);

                  $('input:enabled' , allLis).each($.proxy(function(index, element) {
                      var value = $(element).val();
                      var option = this.getOptionByValue(value);
                      $(option).prop('selected', true);
                  }, this));
              }

              $('li input[value="' + this.options.selectAllValue + '"]', this.$ul).prop('checked', true);

              if (this.options.enableClickableOptGroups && this.options.multiple) {
                  this.updateOptGroups();
              }

              if (triggerOnSelectAll) {
                  this.options.onSelectAll();
              }
          },

          /**
           * Deselects all options.
           *
           * If justVisible is true or not specified, only visible options are deselected.
           *
           * @param {Boolean} justVisible
           */
          deselectAll: function (justVisible, triggerOnDeselectAll) {

              var justVisible = typeof justVisible === 'undefined' ? true : justVisible;
              var allLis = $("li:not(.divider):not(.disabled):not(.multiselect-group)", this.$ul);
              var visibleLis = $("li:not(.divider):not(.disabled):not(.multiselect-group):not(.multiselect-filter-hidden):not(.multiselect-collapisble-hidden)", this.$ul).filter(':visible');

              if(justVisible) {
                  $('input[type="checkbox"]:enabled' , visibleLis).prop('checked', false);
                  visibleLis.removeClass(this.options.selectedClass);

                  $('input[type="checkbox"]:enabled' , visibleLis).each($.proxy(function(index, element) {
                      var value = $(element).val();
                      var option = this.getOptionByValue(value);
                      $(option).prop('selected', false);
                  }, this));
              }
              else {
                  $('input[type="checkbox"]:enabled' , allLis).prop('checked', false);
                  allLis.removeClass(this.options.selectedClass);

                  $('input[type="checkbox"]:enabled' , allLis).each($.proxy(function(index, element) {
                      var value = $(element).val();
                      var option = this.getOptionByValue(value);
                      $(option).prop('selected', false);
                  }, this));
              }

              $('li input[value="' + this.options.selectAllValue + '"]', this.$ul).prop('checked', false);

              if (this.options.enableClickableOptGroups && this.options.multiple) {
                  this.updateOptGroups();
              }

              if (triggerOnDeselectAll) {
                  this.options.onDeselectAll();
              }
          },

          /**
           * Rebuild the plugin.
           *
           * Rebuilds the dropdown, the filter and the select all option.
           */
          rebuild: function() {
              this.$ul.html('');

              // Important to distinguish between radios and checkboxes.
              this.options.multiple = this.$select.attr('multiple') === "multiple";

              this.buildSelectAll();
              this.buildDropdownOptions();
              this.buildFilter();

              this.updateButtonText();
              this.updateSelectAll(true);

              if (this.options.enableClickableOptGroups && this.options.multiple) {
                  this.updateOptGroups();
              }

              if (this.options.disableIfEmpty && $('option', this.$select).length <= 0) {
                  this.disable();
              }
              else {
                  this.enable();
              }

              if (this.options.dropRight) {
                  this.$ul.addClass('pull-right');
              }
          },

          /**
           * The provided data will be used to build the dropdown.
           */
          dataprovider: function(dataprovider) {

              var groupCounter = 0;
              var $select = this.$select.empty();

              $.each(dataprovider, function (index, option) {
                  var $tag;

                  if ($.isArray(option.children)) { // create optiongroup tag
                      groupCounter++;

                      $tag = $('<optgroup/>').attr({
                          label: option.label || 'Group ' + groupCounter,
                          disabled: !!option.disabled
                      });

                      forEach(option.children, function(subOption) { // add children option tags
                          var attributes = {
                              value: subOption.value,
                              label: subOption.label || subOption.value,
                              title: subOption.title,
                              selected: !!subOption.selected,
                              disabled: !!subOption.disabled
                          };

                          //Loop through attributes object and add key-value for each attribute
                         for (var key in subOption.attributes) {
                              attributes['data-' + key] = subOption.attributes[key];
                         }
                           //Append original attributes + new data attributes to option
                          $tag.append($('<option/>').attr(attributes));
                      });
                  }
                  else {

                      var attributes = {
                          'value': option.value,
                          'label': option.label || option.value,
                          'title': option.title,
                          'class': option['class'],
                          'selected': !!option['selected'],
                          'disabled': !!option['disabled']
                      };
                      //Loop through attributes object and add key-value for each attribute
                      for (var key in option.attributes) {
                        attributes['data-' + key] = option.attributes[key];
                      }
                      //Append original attributes + new data attributes to option
                      $tag = $('<option/>').attr(attributes);

                      $tag.text(option.label || option.value);
                  }

                  $select.append($tag);
              });

              this.rebuild();
          },

          /**
           * Enable the multiselect.
           */
          enable: function() {
              this.$select.prop('disabled', false);
              this.$button.prop('disabled', false)
                  .removeClass('disabled');
          },

          /**
           * Disable the multiselect.
           */
          disable: function() {
              this.$select.prop('disabled', true);
              this.$button.prop('disabled', true)
                  .addClass('disabled');
          },

          /**
           * Set the options.
           *
           * @param {Array} options
           */
          setOptions: function(options) {
              this.options = this.mergeOptions(options);
          },

          /**
           * Merges the given options with the default options.
           *
           * @param {Array} options
           * @returns {Array}
           */
          mergeOptions: function(options) {
              return $.extend(true, {}, this.defaults, this.options, options);
          },

          /**
           * Checks whether a select all checkbox is present.
           *
           * @returns {Boolean}
           */
          hasSelectAll: function() {
              return $('li.multiselect-all', this.$ul).length > 0;
          },

          /**
           * Update opt groups.
           */
          updateOptGroups: function() {
              var $groups = $('li.multiselect-group', this.$ul)
              var selectedClass = this.options.selectedClass;

              $groups.each(function() {
                  var $options = $(this).nextUntil('li.multiselect-group')
                      .not('.multiselect-filter-hidden')
                      .not('.disabled');

                  var checked = true;
                  $options.each(function() {
                      var $input = $('input', this);

                      if (!$input.prop('checked')) {
                          checked = false;
                      }
                  });

                  if (selectedClass) {
                      if (checked) {
                          $(this).addClass(selectedClass);
                      }
                      else {
                          $(this).removeClass(selectedClass);
                      }
                  }

                  $('input', this).prop('checked', checked);
              });
          },

          /**
           * Updates the select all checkbox based on the currently displayed and selected checkboxes.
           */
          updateSelectAll: function(notTriggerOnSelectAll) {
              if (this.hasSelectAll()) {
                  var allBoxes = $("li:not(.multiselect-item):not(.multiselect-filter-hidden):not(.multiselect-group):not(.disabled) input:enabled", this.$ul);
                  var allBoxesLength = allBoxes.length;
                  var checkedBoxesLength = allBoxes.filter(":checked").length;
                  var selectAllLi  = $("li.multiselect-all", this.$ul);
                  var selectAllInput = selectAllLi.find("input");

                  if (checkedBoxesLength > 0 && checkedBoxesLength === allBoxesLength) {
                      selectAllInput.prop("checked", true);
                      selectAllLi.addClass(this.options.selectedClass);
                  }
                  else {
                      selectAllInput.prop("checked", false);
                      selectAllLi.removeClass(this.options.selectedClass);
                  }
              }
          },

          /**
           * Update the button text and its title based on the currently selected options.
           */
          updateButtonText: function() {
              var options = this.getSelected();

              // First update the displayed button text.
              if (this.options.enableHTML) {
                  $('.multiselect .multiselect-selected-text', this.$container).html(this.options.buttonText(options, this.$select));
              }
              else {
                  $('.multiselect .multiselect-selected-text', this.$container).text(this.options.buttonText(options, this.$select));
              }

              // Now update the title attribute of the button.
              $('.multiselect', this.$container).attr('title', this.options.buttonTitle(options, this.$select));
          },

          /**
           * Get all selected options.
           *
           * @returns {jQUery}
           */
          getSelected: function() {
              return $('option', this.$select).filter(":selected");
          },

          /**
           * Gets a select option by its value.
           *
           * @param {String} value
           * @returns {jQuery}
           */
          getOptionByValue: function (value) {

              var options = $('option', this.$select);
              var valueToCompare = value.toString();

              for (var i = 0; i < options.length; i = i + 1) {
                  var option = options[i];
                  if (option.value === valueToCompare) {
                      return $(option);
                  }
              }
          },

          /**
           * Get the input (radio/checkbox) by its value.
           *
           * @param {String} value
           * @returns {jQuery}
           */
          getInputByValue: function (value) {

              var checkboxes = $('li input:not(.multiselect-search)', this.$ul);
              var valueToCompare = value.toString();

              for (var i = 0; i < checkboxes.length; i = i + 1) {
                  var checkbox = checkboxes[i];
                  if (checkbox.value === valueToCompare) {
                      return $(checkbox);
                  }
              }
          },

          /**
           * Used for knockout integration.
           */
          updateOriginalOptions: function() {
              this.originalOptions = this.$select.clone()[0].options;
          },

          asyncFunction: function(callback, timeout, self) {
              var args = Array.prototype.slice.call(arguments, 3);
              return setTimeout(function() {
                  callback.apply(self || window, args);
              }, timeout);
          },

          setAllSelectedText: function(allSelectedText) {
              this.options.allSelectedText = allSelectedText;
              this.updateButtonText();
          }
      };

      $.fn.multiselect = function(option, parameter, extraOptions) {
          return this.each(function() {
              var data = $(this).data('multiselect');
              var options = typeof option === 'object' && option;

              // Initialize the multiselect.
              if (!data) {
                  data = new Multiselect(this, options);
                  $(this).data('multiselect', data);
              }

              // Call multiselect method.
              if (typeof option === 'string') {
                  data[option](parameter, extraOptions);

                  if (option === 'destroy') {
                      $(this).data('multiselect', false);
                  }
              }
          });
      };

      $.fn.multiselect.Constructor = Multiselect;

      $(function() {
          $("select[data-role=multiselect]").multiselect();
      });

  });
   </script>


<script type="text/javascript">
    $(function () {
        $('#lst').multiselect({
            includeSelectAllOption: true
        });
       
        $('#lst1').multiselect({
            includeSelectAllOption: true
            
        });
        $('#lst2').multiselect({
            includeSelectAllOption: true
        });
    });
   
    function showForm() {
        var selopt = document.getElementById("opts").value;
        var f1=document.getElementById("f1");
          var f2=document.getElementById("f2");
          var f3=document.getElementById("f3");
        if (selopt == 1) {
             
            f1.style.display = "block"
            f2.style.display = "none";
            f3.style.display = "none";
          
        }
        if (selopt == 2) {
            f2.style.display = "block";
            f1.style.display = "none";
            f3.style.display = "none";
        }
       
        if (selopt == 3) {
            f3.style.display = "block";
            f2.style.display = "none";
            f1.style.display = "none";
        }
       
        if (selopt == 0){
            f1.style.display = "none";
            f2.style.display = "none";
            f3.style.display = "none";
        }
       
        var val = document.getElementById("opts").value;
        var e = document.getElementById("txtName");
        e.value = val;
    }
    
    $(function(){
    	$('#image').click(function(){
    		$('#lst').prop('selected',false);
    	});  	
    }); 
    
   
</script>

</head>
<body>
<div class="container">
<header><img src="${pageContext.request.contextPath}/images/logo.png" alt="Avatar" class="avatar">
<tm style="font-family:calibri">Timesheet Management System</tm>
  <user><%
		if (session != null) {
			if (session.getAttribute("Director") != null) {
				String name = (String) session.getAttribute("Director");
				session.setAttribute("Director",name);

				out.print("Welcome " + name+"   Director" );
			} else {
				response.sendRedirect("/TimeSheet/"); 
			}
		}
	%></user>
  </header>
  <div class="HorizontalNav">
<ul><li>
<a class="active" href="${pageContext.request.contextPath}/DirectorDashboard">Home</a></li>
  <li class="dropdown">
    <a href="${pageContext.request.contextPath}/Director/DirectorTask.jsp" class="dropbtn">Task</a>
    <div class="dropdown-content">
      <a href="${pageContext.request.contextPath}/Director/DirectorTask.jsp">Create Task</a>
      <a href="${pageContext.request.contextPath}/Director/ViewDirTask.jsp">Display Task </a>
      </div>
  </li>
   <li><a href="${pageContext.request.contextPath}/DisplayDirectorApproval">Approval</a></li>
    <li><a  href="${pageContext.request.contextPath}/Director/DirectorReport.jsp">Reports</a></li>
     <li style="float:right"><a href="<%=request.getContextPath()%>/LogoutServlet">Logout</a></li>
</ul>
</div>
<%
    try{
    	 Connection con = null;
        
     con = DBConnection.createConnection();
        Statement statement1 = con.createStatement() ;
        Statement statement2 = con.createStatement() ;
        Statement statement3 = con.createStatement() ;
        Statement statement4 = con.createStatement() ;
        
        resultset1 =statement1.executeQuery("select * from myproject") ;  
        resultset2 =statement2.executeQuery("select * from myproject") ; 
        resultset3 =statement3.executeQuery("select * from users") ; 
        
           
        
        %>
        
        
        <div style="align:center;height:50%;">
<center>
<article>
    <form method="post" name="frm" >
    <h1 style="color:#106E9B;font-family: Calibri;">Export Report</h1><br><br>
    <table border="1" bordercolor="#C0C0C0" cellspacing="3" cellpadding="3" width="40%" align="center" >
    <tr><td><select id="opts" name="opts" onchange="showForm()" style="width:200;height:35" >
	<option value="0" name="Select Report">Select Report</option>
      <option value="1" name="project name">project name</option>
      <option value="2" name="customer name">customer name</option>
      <option value="3" name="employee name">employee name</option>
      <option value="4" name="My Report">My Report</option>
   </select></td>
   
   
<td >

<div id="ff"  style="display: none">
<select id="f" name="f0" multiple="multiple">
</select>
</div>

<div id="f1"  style="display: none">
<select id="lst" name="projectReport" multiple="multiple">
<% while(resultset1.next()){ %>
            <option><%= resultset1.getString("ProjName")%></option>
            <% }%>
</select>
</div>
 
<div id="f2"  style="display: none">
<select id="lst1" name="customerreport" multiple="multiple">
<% while(resultset2.next()){ %>
            <option><%= resultset2.getString("CustomerName")%></option>
            <% }%>
</select>
</div>
 
<div id="f3" style="display: none">
<select id="lst2" name="empreport" multiple="multiple">
<% while(resultset3.next()){ %>
            <option><%= resultset3.getString("EmployeeName")%></option>
            <% }%>
</select>
</div>
 
</td>
</tr>
    </table>
    <br><br><br>
    <table border="1" bordercolor="#C0C0C0" cellspacing="2" cellpadding="2" width="55%" align="center" >
      <tr><td ><b>Start Date:</b></td><td><input type="text" id="startdate" placeholder="mm/dd/yy" style="width:200px" required name="title"/>
 <td ><b>End Date:</b></td><td><input type="text" id="enddate"  placeholder="mm/dd/yy" style="width:200px" required name="title"/></td>
  
    <td colspan=1 align="center">
 
  <INPUT TYPE="image" id="image" SRC="${pageContext.request.contextPath}/images/icon11.jpg" name="show" class="button1" onclick="form.action='<%=request.getContextPath()%>/DirectorReport';clear();" style="height:40px;width:40px">
    </td></tr>
    
    </table>
    
    </form>
</article></center>

 </div>

<%
//**Should I input the codes here?**
        }
        catch(Exception e)
        {
             out.println("wrong entry"+e);
        }
%>
</body>
</html>