/**
 * 
 */

// ----------------------------------------------------------------------
//
// POLYFILLS
//
// ----------------------------------------------------------------------
if (!Array.prototype.forEach) {
	Array.prototype.forEach = function(fun /* , thisArg */) {
		"use strict";

		if (this === void 0 || this === null)
			throw new TypeError();

		var t = Object(this);
		var len = t.length >>> 0;
		if (typeof fun !== "function")
			throw new TypeError();

		var thisArg = arguments.length >= 2 ? arguments[1] : void 0;
		for (var i = 0; i < len; i++) {
			if (i in t)
				fun.call(thisArg, t[i], i, t);
		}
	};
}

if (!Array.prototype.indexOf) {
	Array.prototype.indexOf = function(searchElement, fromIndex) {
		if (this === undefined || this === null) {
			throw new TypeError('"this" is null or not defined');
		}

		var length = this.length >>> 0; // Hack to convert object.length to a
		// UInt32

		fromIndex = +fromIndex || 0;

		if (Math.abs(fromIndex) === Infinity) {
			fromIndex = 0;
		}

		if (fromIndex < 0) {
			fromIndex += length;
			if (fromIndex < 0) {
				fromIndex = 0;
			}
		}

		for (; fromIndex < length; fromIndex++) {
			if (this[fromIndex] === searchElement) {
				return fromIndex;
			}
		}

		return -1;
	};
}

if (!Array.prototype.find) {
	Array.prototype.find = function(predicate) {
		if (this == null) {
			throw new TypeError('Array.prototype.find called on null or undefined');
		}
		if (typeof predicate !== 'function') {
			throw new TypeError('predicate must be a function');
		}
		var list = Object(this);
		var length = list.length >>> 0;
		var thisArg = arguments[1];
		var value;

		for (var i = 0; i < length; i++) {
			value = list[i];
			if (predicate.call(thisArg, value, i, list)) {
				return value;
			}
		}
		return undefined;
	};
}

//
// Selectors API Level 1 (http://www.w3.org/TR/selectors-api/)
// http://ajaxian.com/archives/creating-a-queryselector-for-ie-that-runs-at-native-speed
//
if (!document.querySelectorAll) {
	document.querySelectorAll = function(selectors) {
		var style = document.createElement('style'), elements = [], element;
		document.documentElement.firstChild.appendChild(style);
		document._qsa = [];

		style.styleSheet.cssText = selectors + '{x-qsa:expression(document._qsa && document._qsa.push(this))}';
		window.scrollBy(0, 0);
		style.parentNode.removeChild(style);

		while (document._qsa.length) {
			element = document._qsa.shift();
			element.style.removeAttribute('x-qsa');
			elements.push(element);
		}
		document._qsa = null;
		return elements;
	};
}

if (!document.querySelector) {
	document.querySelector = function(selectors) {
		var elements = document.querySelectorAll(selectors);
		return (elements.length) ? elements[0] : null;
	};
}

(function($) {

	$.fn.formFromObject = function(object) {

		var elements = [],
			iterator = function(path, value) {
				var elements = [];
	
				if (value === null || value === void 0) {
					return [];
				}
	
				if ($.isArray(value)) {
					for (var i = 0, len = value.length; i < len; i++) {
						elements = elements.concat(iterator(path + '[' + i + ']', value[i]));
					}
				} else if (Object.prototype.toString.call(value) === '[object Object]') {
					for ( var prop in value) {
						// TODO check with hasOwnProperty?!
						var p = path !== '' ? path + '.' + prop : prop;
						elements = elements.concat(iterator(p, value[prop]));
					}
				} else {
					elements.push('<input type="hidden" name="' + path + '" value="' + value + '" />');
				}
	
				return elements;
			};

		if (Object.prototype.toString.call(object) !== '[object Object]') {
			return;
		}

		elements = iterator('', object);

		this.append(elements.join(''));
		
		return this;
	};
	
	$(document).ready(function() {
		
		function fire(obj, name, data) {
	    	var event = $.Event(name);
	    	obj.trigger(event, data);
	    	return event.result !== false;
	    }
		
		function allowAction($element) {
			var message = $element.data('confirm');
			
			if (!message) {
				return true;
			}
			
			return window.confirm(message);
		}
		
		$(document).on('click', 'a[data-remote]', function(event) {
			var $link = $(this), method = $link.data('method').toUpperCase(), data = $link.data('params');
			
			if (!$link.data('remote') || !allowAction($link)) {
				return false;
			}
			
			if ((!method || method === 'GET') && !data) {
				return true;
			}
			
			if (fire($link, 'ajax:before')) {
				$.ajax({
					context: this,
					url: $link.attr('href'),
					type: method || 'GET',
					data: data
				});
			}
			
			return false;
		});
		
	});

})(jQuery);