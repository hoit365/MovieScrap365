
/* ux.js */

//초기실행
$(document).ready(function(){
	$('input, textarea').placeholder();
	fncSelect($(".select_area"));
	//리스트 슬라이드
	leftRightSlide(0);
	topDownSlide($(".top_notice"));
	setDatePicker();
	tabProduct($(".movie_content"), 0);
});

//datepicker 설정
function setDatePicker(){
	$( ".datepicker" ).datepicker({
		dateFormat : 'yy-mm-dd',
		showOn: "button",
		buttonImage: "/movie_srcap/public/images/common/icon_calendal.png",
		buttonImageOnly: true,
		buttonText: "Select date",
		changeMonth: true,
		changeYear: true,
		monthNamesShort : [ "1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월" ],
		dayNamesShort: [ "일", "월", "화", "수", "목", "금", "토" ],
		gotoCurrent : true,
		showButtonPanel: true,
		defaultDate: "-20y"
	});
}


//체크박스 모두 체크
function fncCheckAll(chk , name){
	var chk_list = $("input[name='"+name+"']").parent();
	
	if( chk.checked ){
		chk_list.each(function(){
			$(this).removeClass('on');
			$(this).children('input').removeAttr('checked');
		})
	}else{
		//모두체크
		chk_list.each(function(){
			$(this).addClass('on');
			$(this).children('input').attr('checked', 'checked');
		})
	}
}

//체크박스 디자인
function fncCheckBox(el){
	$(el).each(function(){
		var label = $(this).children('label');
		if(label.children('input').is(":checked")){
			label.addClass('on');
		}
		label.on("click", function(e){
			e.preventDefault();
			if(!$(this).hasClass('on')){
				$(this).addClass('on');
				$(this).children('input').attr('checked','checked');
			}else{
				$(this).removeClass('on');
				$(this).children('input').removeAttr('checked');
			}
		});
	});
}

//셀렉션디자인
function fncSelect(el){
	$(el).each(function(){
		var width = $(this).css('width');
		$(this).wrap('<span class="select_area" style="width:'+width+'"></span>');

		var select = $(this);
		var select_area = $(this).parent(".select_area");
		var selected = select.children('option:selected').text();
		select_area.append(setSelectList(select, selected));
		
		var select_list = getSelectList(select_area);
		select_list.children('a.select_on').click(function(){
			var btn = $(this);
			var list = $(this).next('ul');
			if( list.css('display') == 'none'){
				$(this).next('ul').slideDown(200, function(){
					$(this).children('li').children('a').off().click(function(e){
						e.preventDefault();
						var str_select = $(this).text();
						var str_val = $(this).attr('data-value');
						list.children('li').removeClass('on');
						$(this).parent().addClass('on');
						list.hide(10, function(){
							setSelected(select, str_val);
							btn.text(str_select);
						});
					});
				});
			}else{
				$(this).next('ul').slideUp(200); 
			}
		});
	});
	function setSelected(el, str_val){
		$(el).children('option').each(function(){
			$(this).removeAttr('selected');
			if( $(this).val() == str_val ){
				$(this).attr('selected', 'selected');
			}
		});
	}
	function setSelectList(el , str_select){
		$(el).hide();
		var output = "<div class='select_list'>";
		var str_select = str_select != false ? str_select : $(el).children('option').eq(0).text();

		output +="<a href='javascript:;' class='select_on'>"+str_select+"</a>";
		output += "<ul style='display:none;list-style:none;margin:0;padding:0'>";
		$(el).children('option').each(function(){
			output +="<li><a href='#' data-value='"+$(this).attr('value')+"'>"+$(this).text()+"</a></li>";
		});
		output +="</ul>";
		output +="</div>";

		return output;
	}

	function getSelectList(el){
		return $(el).find('.select_list');
	}
}

//모바일체크
function isCheckMobile(){
	var mobileInfo = new Array('Android', 'iPhone', 'iPod', 'BlackBerry', 'Windows CE', 'SAMSUNG', 'LG', 'MOT', 'SonyEricsson');
	for (var info in mobileInfo){
		if (navigator.userAgent.match(mobileInfo[info]) != null){
			// 모바일 수행
			return true;
		}else{
			return false;
		}
	}
}

/* 레이어팝업 열기 */
function openLayerPop(id){
	var blind = $(".blind");
	var id_con = $("#"+id);

	function adjustLayer(data){
		blind.fadeIn(10 , function(){
			id_con.parent().css({
				display:'block',
				top : getScrollTop()
			});
			id_con.show();
			id_con.append(data);
		});
	}
}

//레이어 팝업 닫기
function closeLayerPop(id){
	var blind = $(".blind");
	var id_con = $("#"+id);
	blind.hide();
	id_con.parent().css('display','none');
	id_con.empty();
}

//스크롤 위치
function getScrollTop(){
	return $(window).scrollTop();
}

//콤마찍기
function comma(str) {
	str = String(str);
	return str.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
}


//리스트 슬라이드
function leftRightSlide(start_num){
	var t_clinet;
	var current_num = start_num;
	var slider = $(".slider_list");
	var slider_nav = $(".slider_nav");
	var viewer = slider.find('.viewer');
	var v_width = viewer.width();
	var ul = viewer.children('ul');
	var li = ul.children('li');
	var li_width = li.eq(0).width();
	var li_size = li.size();
	var total_width = li_size * li_width;
	var total_num = Math.floor(total_width/v_width);

	var init = function(){
		if( total_width > v_width ){
		ul.css('width' , total_width);
		ul.children('li').eq(-1).prependTo(ul);
		ul.css('margin-left', '-'+li_width+'px');
			slider_nav.append( 
				'<a href="javascript:;" class="btn_next">다음</a>'+
				'<a href="javascript:;" class="btn_prev">이전</a>'
			);
		}
	}
	init();

	list_start();

	function to_next(){
		clearInterval(t_client);
		ul.stop().animate({left:"-"+li_width}, 2000, function(){
			ul.children('li').eq(0).appendTo(ul);
			ul.css('left', 0);
			list_start();
		});
	}

	function to_prev(){
		clearInterval(t_client);
		ul.stop().animate({left:li_width}, 2000, function(){
			ul.children('li').eq(-1).prependTo(ul);
			ul.css('left', 0);
			list_start();
		});
	}

	function list_start(){
		t_client = setInterval(function(){
			to_next();
		}, 4000);
	}

	$(".btn_next").click(function(){
		clearInterval(t_clinet);
		to_next();
	});

	$(".btn_prev").click(function(){
		clearInterval(t_clinet);
		to_prev();
	});

}


//위 아래 슬라이드
function topDownSlide(el){
	var slider = $(el);
	var viewer = $(el).find('.list_view');
	var ul = viewer.children('ul');
	var list = ul.children('li');
	var list_size = list.size();

	var btn_prev = slider.find('.btn_prev');
	var btn_next = slider.find('.btn_next');
	var timer;
	var init = function(){
		viewer.css({
			position:"relative",
			zIndex:10,
			overflow:'hidden'
		});
		ul.css({
			position:"absolute",
			zIndex:20,
			marginTop : -list.height()
		});
		ul.children('li').eq(-1).prependTo(ul);
	}
	if(list_size > 1){
		init();
		timer = setInterval(toNext,6000);
	}
	btn_prev.on("click",function(e){
		toPrev();
		e.preventDefault();
	});
	btn_next.on("click",function(e){
		toNext();
		e.preventDefault();
	});
	function toPrev(){
		clearInterval(timer);
		ul.not(":animated").animate({
			top:-list.height()
		},300,function(){
			$(this).children('li').eq(0).appendTo(ul);
			$(this).css('top',0);
			timer = setInterval(toNext,6000);
		});
	}
	function toNext(){
		clearInterval(timer);
		ul.not(":animated").animate({
			top:list.height()
		},300,function(){
			$(this).children('li').eq(-1).prependTo(ul);
			$(this).css('top',0);
			timer = setInterval(toNext,6000);
		});
	}
}

//탭
function tabProduct(el, init_num){
	var tab = $(el).find(".tab");
	var tab_list = tab.children('ul').children('li');
	var tab_con = $(el).find('.tab_content');

	var init = function(){
		tab_list.children('a').removeClass('on');
		tab_list.eq(init_num).children('a').addClass('on');
		tab_con.hide();
		tab_con.eq(init_num).show();
	}
	init();
	
	tab_list.each(function(){
		$(this).children('a').click(function(e){
			var href = $(this).attr('href');
			tab_list.find('a').removeClass('on');
			$(this).addClass('on');
			tab_con.hide();
			$(href).show();
			e.preventDefault();
		});
	});
}

//ie8이하 이미지 opacity적용
function ieOpacityImage(){
	if(getInternetExplorerVersion()==8 || getInternetExplorerVersion()==7){
		$('.ie_png').each(function(){
			var src = String($(this).attr('src'));
				if (src.indexOf('.png') > -1 ) {
					$(this).attr('style',"filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled='true',sizingMethod='crop',src='" + src + "')");
				}
		});
	}
}

//ie 버전체크
function getInternetExplorerVersion() {
	var rv = -1; // Return value assumes failure.    
	if (navigator.appName == 'Microsoft Internet Explorer') {
		var ua = navigator.userAgent;
		var re = new RegExp("MSIE ([0-9]{1,}[\.0-9]{0,})");
		if (re.exec(ua) != null)            
		rv = parseFloat(RegExp.$1);    
	}
	return rv; 
}

//아코디언
function fncArcodian(className, start_num){
	var obj = $('.'+className).children('dl');
	
	if(start_num>=0){
		obj.find('dd').not(obj.find('dd').eq(start_num)).css('display','none'); //오픈
		addOn(start_num); //오픈
	}else{
		obj.find('dd').css('display','none');
	}

	obj.children('dt').each(function(index){
		var index = index;
		$(this).children('a').click(function(){
			if($(this).parent().next('dd').css('display') == 'block'){
				$(this).parent().next('dd').slideUp();
				removeOn();
			}else{
				obj.find('dd').not($(this).parent().next('dd')).slideUp();
				$(this).parent().next('dd').slideDown();
				removeOn();
				addOn(index);
			}
		});
	});
	function addOn(index){
		obj.find('dt').eq(index).addClass('on');
		obj.find('dd').eq(index).addClass('on');
	}
	function removeOn(){
		obj.find('dd').removeClass("on");
		obj.find('dt').removeClass("on");
	}
}

//팝업오픈 openPopup(경로, 넓이, 높이, top, left)
function openPopup(path, width, height, top, left){
	window.open(path, "","width="+width+",height="+height+",top="+top+",left="+left+",noresizable,toolbar=no,status=no,scrollbars=yes,directory=no");
}

//갤러리 정렬 리스트
function fnArrayList(el){
	var gallery = $(el);
	var ul = gallery.children('ul');
	var ul_width = ul.width();
	var list = ul.children('li');
	var list_width = list.width();
	
	//열갯수
	var getCol = function(){
		ul_width = ul.width();
		list = ul.children('li');
		return parseInt(ul_width/list_width);
	}

	var init = function(){
		gallery.addClass('opcacity0');
		ul.css({
			position: 'relative',
			height : maxHeight()+"px",
			'z-index' : 40
		});
		list.css({
			position: "absolute",
			'z-index':45
		});
	}
	if( list.size() > 0){
	var resizeTimer;
	$(window).resize(function(){
		clearTimeout(resizeTimer);
		resizeTimer = setTimeout(
		function(){
			list_width = list.width();
			setListLocation();
		}, 200);
	});

	init();

	setTimeout(function(){
		setListLocation();
	},400);
	}
	function maxHeight(){
		var h_list = new Array();
		for(var i =1; i<=getCol();i++){
			try{
			h_list.push(list.eq(-i).position().top+list.eq(-i).height());
			}catch(err){}
		}
		return Math.max.apply(null, h_list);
		
	}

	//위치셋팅
	function setListLocation(){
		var h_list = setListHeight();
		var col_len = getCol();

		list.each(function(index){
			var i = index;
			var x = index%col_len;
			$(this).animate({
				"left": x*list_width,
				"top":getListHeight(index)
			},50, function(){
				ul.css({
					height : maxHeight()+"px"
				});
				gallery.removeClass('opcacity0');
			});
		});
	}

	//리스트 높이 배열로 얻기
	function setListHeight(){
		var h_list = new Array();
		list.each(function(){
			h_list.push($(this).height()+parseInt($(this).css('margin-bottom')));
		});

		return h_list;
	}

	function getListHeight(index){
		var h_list = setListHeight();
		var sum = 0;
		var col_len = getCol();
		for(var i=0;i<h_list.length ; i++){
			if(index >i && index%col_len == i%col_len){
				sum+=h_list[i]
			}
		}
		return sum;
	}
}

//placeholder 플러그인
/*! http://mths.be/placeholder v2.0.8 by @mathias */
;(function(window, document, $) {

	// Opera Mini v7 doesn?셳 support placeholder although its DOM seems to indicate so
	var isOperaMini = Object.prototype.toString.call(window.operamini) == '[object OperaMini]';
	var isInputSupported = 'placeholder' in document.createElement('input') && !isOperaMini;
	var isTextareaSupported = 'placeholder' in document.createElement('textarea') && !isOperaMini;
	var prototype = $.fn;
	var valHooks = $.valHooks;
	var propHooks = $.propHooks;
	var hooks;
	var placeholder;

	if (isInputSupported && isTextareaSupported) {

		placeholder = prototype.placeholder = function() {
			return this;
		};

		placeholder.input = placeholder.textarea = true;

	} else {

		placeholder = prototype.placeholder = function() {
			var $this = this;
			$this
				.filter((isInputSupported ? 'textarea' : ':input') + '[placeholder]')
				.not('.placeholder')
				.bind({
					'focus.placeholder': clearPlaceholder,
					'blur.placeholder': setPlaceholder
				})
				.data('placeholder-enabled', true)
				.trigger('blur.placeholder');
			return $this;
		};

		placeholder.input = isInputSupported;
		placeholder.textarea = isTextareaSupported;

		hooks = {
			'get': function(element) {
				var $element = $(element);

				var $passwordInput = $element.data('placeholder-password');
				if ($passwordInput) {
					return $passwordInput[0].value;
				}
				return $element.data('placeholder-enabled') && $element.hasClass('placeholder') ? '' : element.value;
			},
			'set': function(element, value) {
				var $element = $(element);

				var $passwordInput = $element.data('placeholder-password');
				if ($passwordInput) {
					return $passwordInput[0].value = value;
				}

				if (!$element.data('placeholder-enabled')) {
					return element.value = value;
				}
				if (value == '') {
					element.value = value;
					// Issue #56: Setting the placeholder causes problems if the element continues to have focus.
					if (element != safeActiveElement()) {
						// We can't use `triggerHandler` here because of dummy text/password inputs :(
						setPlaceholder.call(element);
					}
				} else if ($element.hasClass('placeholder')) {
					clearPlaceholder.call(element, true, value) || (element.value = value);
				} else {
					element.value = value;
				}
				// `set` can not return `undefined`; see http://jsapi.info/jquery/1.7.1/val#L2363
				return $element;
			}
		};

		if (!isInputSupported) {
			valHooks.input = hooks;
			propHooks.value = hooks;
		}
		if (!isTextareaSupported) {
			valHooks.textarea = hooks;
			propHooks.value = hooks;
		}

		$(function() {
			// Look for forms
			$(document).delegate('form', 'submit.placeholder', function() {
				// Clear the placeholder values so they don't get submitted
				var $inputs = $('.placeholder', this).each(clearPlaceholder);
				setTimeout(function() {
					$inputs.each(setPlaceholder);
				}, 10);
			});
		});

		// Clear placeholder values upon page reload
		$(window).bind('beforeunload.placeholder', function() {
			$('.placeholder').each(function() {
				this.value = '';
			});
		});

	}

	function args(elem) {
		// Return an object of element attributes
		var newAttrs = {};
		var rinlinejQuery = /^jQuery\d+$/;
		$.each(elem.attributes, function(i, attr) {
			if (attr.specified && !rinlinejQuery.test(attr.name)) {
				newAttrs[attr.name] = attr.value;
			}
		});
		return newAttrs;
	}

	function clearPlaceholder(event, value) {
		var input = this;
		var $input = $(input);
		if (input.value == $input.attr('placeholder') && $input.hasClass('placeholder')) {
			if ($input.data('placeholder-password')) {
				$input = $input.hide().next().show().attr('id', $input.removeAttr('id').data('placeholder-id'));
				// If `clearPlaceholder` was called from `$.valHooks.input.set`
				if (event === true) {
					return $input[0].value = value;
				}
				$input.focus();
			} else {
				input.value = '';
				$input.removeClass('placeholder');
				input == safeActiveElement() && input.select();
			}
		}
	}

	function setPlaceholder() {
		var $replacement;
		var input = this;
		var $input = $(input);
		var id = this.id;
		if (input.value == '') {
			if (input.type == 'password') {
				if (!$input.data('placeholder-textinput')) {
					try {
						$replacement = $input.clone().attr({ 'type': 'text' });
					} catch(e) {
						$replacement = $('<input>').attr($.extend(args(this), { 'type': 'text' }));
					}
					$replacement
						.removeAttr('name')
						.data({
							'placeholder-password': $input,
							'placeholder-id': id
						})
						.bind('focus.placeholder', clearPlaceholder);
					$input
						.data({
							'placeholder-textinput': $replacement,
							'placeholder-id': id
						})
						.before($replacement);
				}
				$input = $input.removeAttr('id').hide().prev().attr('id', id).show();
				// Note: `$input[0] != input` now!
			}
			$input.addClass('placeholder');
			$input[0].value = $input.attr('placeholder');
		} else {
			$input.removeClass('placeholder');
		}
	}

	function safeActiveElement() {
		// Avoid IE9 `document.activeElement` of death
		// https://github.com/mathiasbynens/jquery-placeholder/pull/99
		try {
			return document.activeElement;
		} catch (exception) {}
	}

}(this, document, jQuery));

