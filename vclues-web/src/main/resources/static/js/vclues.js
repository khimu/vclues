

function isEmpty(str) {
    return (!str || 0 === str.length);
}


$( document ).ready(function() {
    console.log( "ready!" );

    
    var w = Math.max(document.documentElement.clientWidth, window.innerWidth || 0);
    var h = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);

    console.log(w);
    console.log(h);

    $('body').css({ 'height': h });   
    $('body').css({ 'width': w });  
    
    console.log(parseInt(h));

    if(w /*<![CDATA[*/ > /*]]>*/ 1000) {
    	console.log(h + ' is less than 600');
    	var menu = h * .6;
    	var menumargin = menu * .35;
    
    	$('.home-logo').css({'height': menu + 'px'});
    	$('.h1').css({'margin-top': menumargin + 'px'});
	} else if(h /*<![CDATA[*/< /*]]>*/ 450) {
    	console.log(h + ' is less than 600');
    	var menu = h * .6;
    	var menumargin = menu * .30;
    
    	$('.home-logo').css({'height': menu + 'px'});
    	$('.h1').css({'margin-top': menumargin + 'px'});
	} else if(h /*<![CDATA[*/< /*]]>*/ 700) {
    	console.log(h + ' is less than 600');
    	var menu = h * .25;
    	var menumargin = menu * .15;
    	var closebtn = h * .1;
    
    	$('.home-logo').css({'height': menu + 'px'});
    	$('.h1').css({'margin-top': menumargin + 'px'});
    	$('.btn_close').css({'top' : closebtn + 'px'});
    	
	} else if(h /*<![CDATA[*/< /*]]>*/ 800) {
    	console.log(h + ' is less than 800');
    	var menu = h * .25;
    	var menumargin = menu * .25;
    
    	$('.home-logo').css({'height': menu + 'px'});
    	$('.h1').css({'margin-top': menumargin + 'px'});
	}
    else {
    	console.log(h + ' is NOT less than ' + 800);
    	var menu = h * .35;
    	var menumargin = menu * .30;
    
    	$('.home-logo').css({'height': menu + 'px'});
    	$('.h1').css({'margin-top': menumargin + 'px'});
    }
    
    console.log(menu);

});



function content_type_short()
{
	  console.log('short typed string content_type_short');
	  
  Typed.new(".typed", 
  {
      stringsElement: document.getElementById('describe_site_short_typed_strings'),
      typeSpeed: 30,
      backDelay: 2000,
      loop: false,
      contentType: 'html', // or text
      // defaults to null for infinite loop
      loopCount: null
  });
}
function story_phrase()
{
	  console.log('short typed string story_phrase');
	  
  Typed.new(".typed_phrase", 
  {
      stringsElement: document.getElementById('describe_site_phrase_typed_strings'),
      typeSpeed: 70,
      backDelay: 2000,
      loop: false,
      contentType: 'html', // or text
      // defaults to null for infinite loop
      loopCount: null,
      callback: function(){ foo(); }
      //resetCallback: function() { newTyped(); }
  });
}

function foo()
{
	//console.log('foo is called');
  jQuery("#describe_about_site_startup").css("top",'550%');
  jQuery("#describe_about_site_startup").css("left","-100%");
}

function prev() {
    var prev = parseInt($('#current').val());
    var current = parseInt($('#current').val());

    console.log('before prev ' + prev + ' current ' + current);

    if(prev == 1) {
            $('#current').val(1);
    }
    else {
            prev--;
            console.log(prev);
            $('#current').val(prev);
    }

    console.log('after prev ' + prev + ' current ' + current);

    $('#'+current).hide();
    $('#'+prev).show();
}

function next() {
    var next = parseInt($('#current').val());
    var current = parseInt($('#current').val());
    var listsize = parseInt($('#listsize').val());

    console.log('before next ' + next + ' current ' + current);

    if(next == listsize) {
            $('#current').val(listsize);
    }
    else {
            next++;
            console.log(next);
            $('#current').val(next);
    }

    console.log('after next ' + next + ' current ' + current);

    $('#'+current).hide();
    $('#'+next).show();
}
