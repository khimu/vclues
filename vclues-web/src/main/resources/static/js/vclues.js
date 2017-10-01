

function isEmpty(str) {
    return (!str || 0 === str.length);
}


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
      // resetCallback: function() { newTyped(); }
  });
}

function foo()
{
	// console.log('foo is called');
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

function addannouncementview() {
      console.log( "ready!" );
    
    var w = Math.max(document.documentElement.clientWidth, window.innerWidth || 0);
    var h = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);

    console.log(w);
    console.log(h);

    $('body').css({ 'height': h });   
    $('body').css({ 'width': w });  
    $('.curtain_plate').css({ 'height': (h * .25) + "px" });
    $('.character').css({ 'height': (h * .23) + "px" });
    
    $('.dagger').css({ 'height': (h * .45) + "px" }); 
    $('.dagger').css({ 'width': (w * .67) + "px" });
    $('.dagger').css({ 'left': "33%" });
    // $('.dagger').css({ 'bottom': "10%" });
    
    console.log(parseInt(h));

  console.log(h + ' default ');
  var menu = h * .35;
  var menumargin = menu * .30;  
  var buttons = h * .2;

 if(h /* <![CDATA[ */ > /* ]]> */ 1300) {
      console.log(h + ' is ipad pro portrait');
      var menu = h * .13;
      var menumargin = menu * .6;
      var buttons = h * .02;
      $('.curtain_plate').css({ 'padding-top': "130px" });
  }else if(w /* <![CDATA[ */ > /* ]]> */ 1300) {
      console.log(h + ' is ipad pro landscape');
      var menu = h * .15;
      var menumargin = menu * .7;
      var buttons = h * -.05;
      $('.curtain_plate').css({ 'padding-top': "120px" });
  } else if(h /* <![CDATA[ */ > /* ]]> */ 1000) {
      console.log(h + ' is ipad portrait');
      var menu = h * .14;
      var menumargin = menu * .25;
      var buttons = h * .02;
      $('.character').css({ 'height': (h * .35) + "px" });
      $('.curtain_plate').css({ 'height': (h * .2) + "px" });
      $('.character').css({ 'width': "auto" });
  }else if(w /* <![CDATA[ */ > /* ]]> */ 1000) {
      console.log(h + ' is ipad landscape');
      var menu = h * .15;
      var menumargin = menu * .25;
      var buttons = h * -.05;  
      $('.character').css({ 'height': (h * .27) + "px" });
      $('.dagger').css({ 'left': "28%" });
  } else if(h /* <![CDATA[ */< /* ]]> */ 350) {
      console.log(h + ' is iphone5 landscape');
      var menu = h * .22;
      var menumargin = menu * .04;
      var buttons = h * -.1; 
      $('.character').css({ 'height': (h * .6) + "px" });
      $('.curtain_plate').css({ 'height': (h * .35) + "px" });
      $('.curtain_plate').css({ 'padding-top': "20px" });   
      
      $('.dagger').css({ 'height': (h * .9) + "px" }); 
      $('.dagger').css({ 'width': (w * .67) + "px" });
      $('.dagger').css({ 'left': "38.6%" });
      $('.dagger').css({ 'bottom': "-18%" });
  } else if(h /* <![CDATA[ */< /* ]]> */ 450) {
      console.log(h + ' is iphone6 landscape');
      var menu = h * .2;
      var menumargin = menu * .04;
      var buttons = h * -.1; 
      $('.character').css({ 'height': (h * .45) + "px" });
      $('.curtain_plate').css({ 'height': (h * .3) + "px" });
      $('.curtain_plate').css({ 'padding-top': "20px" }); 
      
      $('.dagger').css({ 'height': (h * .75) + "px" }); 
      $('.dagger').css({ 'width': (w * .67) + "px" });
      $('.dagger').css({ 'left': "35%" });
      $('.dagger').css({ 'bottom': "-35%" });      
  } else if(h /* <![CDATA[ */< /* ]]> */ 700) {
      console.log(h + ' is iphone 6');
      var menu = h * .12;
      var menumargin = menu * .1;
      var buttons = h * -.05;
      $('.character').css({ 'height': (h * .32) + "px" });
      $('.curtain_plate').css({ 'height': (h * .17) + "px" });
      $('.curtain_plate').css({ 'padding-top': "30px" }); 
      $('.dagger').css({ 'left': "51%" });
      $('.dagger').css({ 'bottom': "-18%" });
  } else if(h /* <![CDATA[ */< /* ]]> */ 800) {
      console.log(h + ' is iphone 6+');
      var menu = h * .14;
      var menumargin = menu * .3;
      var buttons = h * -.05;
      $('.character').css({ 'height': (h * .35) + "px" });
      // $('.character').css({ 'padding-bottom': "20px" });
      $('.curtain_plate').css({ 'height': (h * .17) + "px" });
      $('.curtain_plate').css({ 'padding-top': "30px" }); 
      $('.dagger').css({ 'left': "47%" });
      $('.dagger').css({ 'bottom': "-10%" });
  }

    
  $('.home-logo').css({'height': menu + 'px'});
  $('.h1').css({'margin-top': menumargin + 'px'});   
  $('.buttons').css({'margin-top': buttons + 'px'});  

    console.log(menu);
}

function addgameview() {
      console.log( "ready!" );
    
    var w = Math.max(document.documentElement.clientWidth, window.innerWidth || 0);
    var h = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);

    console.log(w);
    console.log(h);

    $('body').css({ 'height': h });   
    $('body').css({ 'width': w });  
    $('.curtain_plate').css({ 'height': (h * .3) + "px" });

    console.log(parseInt(h));

    console.log(h + ' default ');
    var menu = h * .35;
    var menumargin = menu * .30;  
    var buttons = h * .2;

  if(h /* <![CDATA[ */ > /* ]]> */ 1300) {
      console.log(h + ' is ipad pro portrait');
      var menu = h * .23;
      var menumargin = menu * .6;
      var buttons = h * .02;
      $('.curtain_plate').css({ 'padding-top': "150px" });
      $('.pre-scrollable').css({ 'height': (h * .5) + "px" });
  }else if(w /* <![CDATA[ */ > /* ]]> */ 1300) {
      console.log(h + ' is ipad pro landscape');
      var menu = h * .15;
      var menumargin = menu * .7;
      var buttons = h * -.05;
      $('.curtain_plate').css({ 'padding-top': "120px" });
      $('.pre-scrollable').css({ 'height': (h * .3) + "px" });
  } else if(h /* <![CDATA[ */ > /* ]]> */ 1000) {
      console.log(h + ' is ipad portrait');
      var menu = h * .1;
      var menumargin = menu * .35;
      var buttons = h * .02;
      $('.curtain_plate').css({ 'padding-top': "100px" });  
  }else if(w /* <![CDATA[ */ > /* ]]> */ 1000) {
      console.log(h + ' is ipad landscape');
      var menu = h * .15;
      var menumargin = menu * .25;
      var buttons = h * -.05;  
  } else if(h /* <![CDATA[ */< /* ]]> */ 350) {
      console.log(h + ' is iphone5 landscape');
      var menu = h * .22;
      var menumargin = menu * .04;
      var buttons = h * -.1; 
      $('.curtain_plate').css({ 'height': (h * .35) + "px" });
      $('.curtain_plate').css({ 'padding-top': "20px" });  
  } else if(h /* <![CDATA[ */< /* ]]> */ 450) {
      console.log(h + ' is iphone6 landscape');
      var menu = h * .2;
      var menumargin = menu * .04;
      var buttons = h * -.1; 
      $('.curtain_plate').css({ 'height': (h * .3) + "px" });
      $('.curtain_plate').css({ 'padding-top': "20px" });     
  } else if(h /* <![CDATA[ */< /* ]]> */ 700) {
      console.log(h + ' is iphone 6');
      var menu = h * .12;
      var menumargin = menu * .1;
      var buttons = h * -.05;
      $('.curtain_plate').css({ 'height': (h * .17) + "px" });
      $('.curtain_plate').css({ 'padding-top': "30px" });
  } else if(h /* <![CDATA[ */< /* ]]> */ 800) {
      console.log(h + ' is iphone 6+');
      var menu = h * .09;
      var menumargin = menu * .4;
      var buttons = h * -.05;
      // $('.curtain_plate').css({ 'height': (h * .17) + "px" });
      $('.curtain_plate').css({ 'padding-top': "80px" }); 
  }

    
  $('.home-logo').css({'height': menu + 'px'});
  $('.h1').css({'margin-top': menumargin + 'px'});   
  $('.buttons').css({'margin-top': buttons + 'px'});  

    console.log(menu);
}

function announcementview() {
      console.log( "ready!" );
    
    var w = Math.max(document.documentElement.clientWidth, window.innerWidth || 0);
    var h = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);

    console.log(w);
    console.log(h);

    $('body').css({ 'height': h });   
    $('body').css({ 'width': w });  
    $('.curtain_plate').css({ 'height': (h * .25) + "px" });
    
    console.log(parseInt(h));

  console.log(h + ' default ');
  var menu = h * .35;
  var menumargin = menu * .30;  
  var buttons = h * .2;

 if(h /* <![CDATA[ */ > /* ]]> */ 1300) {
      console.log(h + ' is ipad pro portrait');
      var menu = h * .13;
      var menumargin = menu * .6;
      var buttons = h * .02;
      $('.curtain_plate').css({ 'padding-top': "130px" });
  }else if(w /* <![CDATA[ */ > /* ]]> */ 1300) {
      console.log(h + ' is ipad pro landscape');
      var menu = h * .15;
      var menumargin = menu * .7;
      var buttons = h * -.05;
      $('.curtain_plate').css({ 'padding-top': "120px" });
  } else if(h /* <![CDATA[ */ > /* ]]> */ 1000) {
      console.log(h + ' is ipad portrait');
      var menu = h * .1;
      var menumargin = menu * .25;
      var buttons = h * .02;
      $('.curtain_plate').css({ 'height': (h * .3) + "px" });
  }else if(w /* <![CDATA[ */ > /* ]]> */ 1000) {
      console.log(h + ' is ipad landscape');
      var menu = h * .1;
      var menumargin = menu * .25;
      var buttons = h * -.05;  
      $('.curtain_plate').css({ 'height': (h * .4) + "px" });
  } else if(h /* <![CDATA[ */< /* ]]> */ 350) {
      console.log(h + ' is iphone5 landscape');
      var menu = h * .22;
      var menumargin = menu * .04;
      var buttons = h * -.1; 
      $('.curtain_plate').css({ 'height': (h * .35) + "px" });
      $('.curtain_plate').css({ 'padding-top': "20px" }); 
  } else if(h /* <![CDATA[ */< /* ]]> */ 450) {
      console.log(h + ' is iphone6 landscape');
      var menu = h * .2;
      var menumargin = menu * .04;
      var buttons = h * -.1; 
      $('.curtain_plate').css({ 'height': (h * .29) + "px" });
      $('.curtain_plate').css({ 'padding-top': "30px" }); 
        
  } else if(h /* <![CDATA[ */< /* ]]> */ 700) {
      console.log(h + ' is iphone 6');
      var menu = h * .12;
      var menumargin = menu * .1;
      var buttons = h * -.05;
      $('.curtain_plate').css({ 'height': (h * .17) + "px" });
      $('.curtain_plate').css({ 'padding-top': "30px" }); 
  } else if(h /* <![CDATA[ */< /* ]]> */ 800) {
      console.log(h + ' is iphone 6+');
      var menu = h * .14;
      var menumargin = menu * .3;
      var buttons = h * -.05;
      // $('.character').css({ 'padding-bottom': "20px" });
      $('.curtain_plate').css({ 'height': (h * .17) + "px" });
      $('.curtain_plate').css({ 'padding-top': "30px" }); 
  }

    
  $('.home-logo').css({'height': menu + 'px'});
  $('.h1').css({'margin-top': menumargin + 'px'});   
  $('.buttons').css({'margin-top': buttons + 'px'});  

    console.log(menu);
    
    console.log($('#listsize').val());
}

function castview() {
      console.log( "ready!" );
    
    var w = Math.max(document.documentElement.clientWidth, window.innerWidth || 0);
    var h = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);

    console.log(w);
    console.log(h);

    $('body').css({ 'height': h });   
    $('body').css({ 'width': w });  
    $('.curtain_plate').css({ 'height': (h * .25) + "px" });
    $('.character').css({ 'height': (h * .23) + "px" });
    
    console.log(parseInt(h));

  console.log(h + ' default ');
  var menu = h * .35;
  var menumargin = menu * .30;  
  var buttons = h * .2;

  if(h /* <![CDATA[ */ > /* ]]> */ 1300) {
      console.log(h + ' is ipad pro portrait');
      var menu = h * .13;
      var menumargin = menu * .6;
      var buttons = h * .02;
      $('.curtain_plate').css({ 'padding-top': "130px" });
      $('.pre-scrollable').css({ 'height': (h * .5) + "px" });
  }else if(w /* <![CDATA[ */ > /* ]]> */ 1300) {
      console.log(h + ' is ipad pro landscape');
      var menu = h * .15;
      var menumargin = menu * .7;
      var buttons = h * -.05;
      $('.curtain_plate').css({ 'padding-top': "120px" });
      $('.pre-scrollable').css({ 'height': (h * .3) + "px" });
  } else if(h /* <![CDATA[ */ > /* ]]> */ 1000) {
      console.log(h + ' is ipad portrait');
      var menu = h * .14;
      var menumargin = menu * .25;
      var buttons = h * .02;
      $('.character').css({ 'height': (h * .35) + "px" });
      $('.curtain_plate').css({ 'height': (h * .2) + "px" });
      $('.character').css({ 'width': "auto" });
      // $('.character').css({ 'padding-bottom':"20px" });
  }else if(w /* <![CDATA[ */ > /* ]]> */ 1000) {
      console.log(h + ' is ipad landscape');
      var menu = h * .15;
      var menumargin = menu * .25;
      var buttons = h * -.05;  
      $('.character').css({ 'height': (h * .27) + "px" });
      // $('.character').css({ 'padding-bottom': "20px" });
      // $('.curtain_plate').css({ 'height': (h * .17) + "px" });
      // $('.curtain_plate').css({ 'padding-top': "30px" });
  } else if(h /* <![CDATA[ */< /* ]]> */ 350) {
      console.log(h + ' is iphone5 landscape');
      var menu = h * .22;
      var menumargin = menu * .04;
      var buttons = h * -.1; 
      $('.character').css({ 'height': (h * .6) + "px" });
      $('.curtain_plate').css({ 'height': (h * .35) + "px" });
      $('.curtain_plate').css({ 'padding-top': "20px" });      
  } else if(h /* <![CDATA[ */< /* ]]> */ 450) {
      console.log(h + ' is iphone6 landscape');
      var menu = h * .2;
      var menumargin = menu * .04;
      var buttons = h * -.1; 
      $('.character').css({ 'height': (h * .45) + "px" });
      $('.curtain_plate').css({ 'height': (h * .3) + "px" });
      $('.curtain_plate').css({ 'padding-top': "20px" }); 
  } else if(h /* <![CDATA[ */< /* ]]> */ 700) {
      console.log(h + ' is iphone 6');
      var menu = h * .12;
      var menumargin = menu * .1;
      var buttons = h * -.05;
      $('.character').css({ 'height': (h * .32) + "px" });
      $('.curtain_plate').css({ 'height': (h * .17) + "px" });
      $('.curtain_plate').css({ 'padding-top': "30px" }); 
  } else if(h /* <![CDATA[ */< /* ]]> */ 800) {
      console.log(h + ' is iphone 6+');
      var menu = h * .14;
      var menumargin = menu * .3;
      var buttons = h * -.05;
      $('.character').css({ 'height': (h * .35) + "px" });
      // $('.character').css({ 'padding-bottom': "20px" });
      $('.curtain_plate').css({ 'height': (h * .17) + "px" });
      $('.curtain_plate').css({ 'padding-top': "30px" }); 
  }

    
  $('.home-logo').css({'height': menu + 'px'});
  $('.h1').css({'margin-top': menumargin + 'px'});   
  $('.buttons').css({'margin-top': buttons + 'px'});  

    console.log(menu);
}

function castonlyview() {
      console.log( "ready!" );
    
    var w = Math.max(document.documentElement.clientWidth, window.innerWidth || 0);
    var h = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);

    console.log(w);
    console.log(h);

    $('body').css({ 'height': h });   
    $('body').css({ 'width': w });  
    $('.curtain_plate').css({ 'height': (h * .25) + "px" });
    $('.character').css({ 'height': (h * .23) + "px" });
    
    $('.dagger').css({ 'height': (h * .45) + "px" }); 
    $('.dagger').css({ 'width': (w * .67) + "px" });
    $('.dagger').css({ 'left': "33%" });
    // $('.dagger').css({ 'bottom': "10%" });
    
    console.log(parseInt(h));

  console.log(h + ' default ');
  var menu = h * .35;
  var menumargin = menu * .30;  
  var buttons = h * .2;

  if(h /* <![CDATA[ */ > /* ]]> */ 1300) {
      console.log(h + ' is ipad pro portrait');
      var menu = h * .13;
      var menumargin = menu * .6;
      var buttons = h * .02;
      $('.curtain_plate').css({ 'padding-top': "130px" });
  }else if(w /* <![CDATA[ */ > /* ]]> */ 1300) {
      console.log(h + ' is ipad pro landscape');
      var menu = h * .15;
      var menumargin = menu * .7;
      var buttons = h * -.05;
      $('.curtain_plate').css({ 'padding-top': "120px" });
  } else if(h /* <![CDATA[ */ > /* ]]> */ 1000) {
      console.log(h + ' is ipad portrait');
      var menu = h * .14;
      var menumargin = menu * .25;
      var buttons = h * .02;
      $('.character').css({ 'height': (h * .35) + "px" });
      $('.curtain_plate').css({ 'height': (h * .2) + "px" });
      $('.character').css({ 'width': "auto" });
  }else if(w /* <![CDATA[ */ > /* ]]> */ 1000) {
      console.log(h + ' is ipad landscape');
      var menu = h * .15;
      var menumargin = menu * .25;
      var buttons = h * -.05;  
      $('.character').css({ 'height': (h * .27) + "px" });
      $('.dagger').css({ 'left': "28%" });
  } else if(h /* <![CDATA[ */< /* ]]> */ 350) {
      console.log(h + ' is iphone5 landscape');
      var menu = h * .22;
      var menumargin = menu * .04;
      var buttons = h * -.1; 
      $('.character').css({ 'height': (h * .6) + "px" });
      $('.curtain_plate').css({ 'height': (h * .35) + "px" });
      $('.curtain_plate').css({ 'padding-top': "20px" });   
      
      $('.dagger').css({ 'height': (h * .9) + "px" }); 
      $('.dagger').css({ 'width': (w * .67) + "px" });
      $('.dagger').css({ 'left': "38.6%" });
      $('.dagger').css({ 'bottom': "-18%" });
  } else if(h /* <![CDATA[ */< /* ]]> */ 450) {
      console.log(h + ' is iphone6 landscape');
      var menu = h * .2;
      var menumargin = menu * .04;
      var buttons = h * -.1; 
      $('.character').css({ 'height': (h * .45) + "px" });
      $('.curtain_plate').css({ 'height': (h * .3) + "px" });
      $('.curtain_plate').css({ 'padding-top': "20px" }); 
      
      $('.dagger').css({ 'height': (h * .75) + "px" }); 
      $('.dagger').css({ 'width': (w * .67) + "px" });
      $('.dagger').css({ 'left': "35%" });
      $('.dagger').css({ 'bottom': "-35%" });      
  } else if(h /* <![CDATA[ */< /* ]]> */ 700) {
      console.log(h + ' is iphone 6');
      var menu = h * .12;
      var menumargin = menu * .1;
      var buttons = h * -.05;
      $('.character').css({ 'height': (h * .32) + "px" });
      $('.curtain_plate').css({ 'height': (h * .17) + "px" });
      $('.curtain_plate').css({ 'padding-top': "30px" }); 
      $('.dagger').css({ 'left': "51%" });
      $('.dagger').css({ 'bottom': "-18%" });
  } else if(h /* <![CDATA[ */< /* ]]> */ 800) {
      console.log(h + ' is iphone 6+');
      var menu = h * .14;
      var menumargin = menu * .3;
      var buttons = h * -.05;
      $('.character').css({ 'height': (h * .35) + "px" });
      // $('.character').css({ 'padding-bottom': "20px" });
      $('.curtain_plate').css({ 'height': (h * .17) + "px" });
      $('.curtain_plate').css({ 'padding-top': "30px" }); 
      $('.dagger').css({ 'left': "47%" });
      $('.dagger').css({ 'bottom': "-10%" });
  }

    
  $('.home-logo').css({'height': menu + 'px'});
  $('.h1').css({'margin-top': menumargin + 'px'});   
  $('.buttons').css({'margin-top': buttons + 'px'});  

    console.log(menu);
}

function cluesview() {
      console.log( "ready!" );
    
    var w = Math.max(document.documentElement.clientWidth, window.innerWidth || 0);
    var h = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);

    console.log(w);
    console.log(h);

    $('body').css({ 'height': h });   
    $('body').css({ 'width': w });  
    
    console.log(parseInt(h));

  console.log(h + ' default ');
  var menu = h * .35;
  var menumargin = menu * .30;  
  var buttons = h * .2;

 if(h /* <![CDATA[ */ > /* ]]> */ 1300) {
      console.log(h + ' is ipad pro portrait');
      var menu = h * .13;
      var menumargin = menu * .6;
      var buttons = h * .02;
      $('.curtain_plate').css({ 'padding-top': "130px" });
  }else if(w /* <![CDATA[ */ > /* ]]> */ 1300) {
      console.log(h + ' is ipad pro landscape');
      var menu = h * .15;
      var menumargin = menu * .7;
      var buttons = h * -.05;
      $('.curtain_plate').css({ 'padding-top': "120px" });
  } else if(h /* <![CDATA[ */ > /* ]]> */ 1000) {
      console.log(h + ' is ipad portrait');
      var menu = h * .30;
      var menumargin = menu * .15;
      var buttons = h * .01;
  }else if(w /* <![CDATA[ */ > /* ]]> */ 1000) {
      console.log(h + ' is ipad landscape');
      var menu = h * .25;
      var menumargin = menu * .14;
      var buttons = h * -.05;  
  } else if(h /* <![CDATA[ */< /* ]]> */ 450) {
      console.log(h + ' is device landscape');
      var menu = h * .24;
      var menumargin = menu * .04;
      var buttons = h * -.08; 
  } else if(h /* <![CDATA[ */< /* ]]> */ 700) {
      console.log(h + ' is iphone 6');
      var menu = h * .2;
      var menumargin = menu * .15;
      var buttons = h * -.07; 
  } else if(h /* <![CDATA[ */< /* ]]> */ 800) {
      console.log(h + ' is iphone 6+');
      var menu = h * .25;
      var menumargin = menu * .15;
      var buttons = h * -.05;
  }

    
  $('.home-logo').css({'height': menu + 'px'});
  $('.h1').css({'margin-top': menumargin + 'px'});   
  $('.buttons').css({'margin-top': buttons + 'px'});  

    console.log(menu);
}

function errorview() {
    console.log( "ready!" );

    
    var w = Math.max(document.documentElement.clientWidth, window.innerWidth || 0);
    var h = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);

    console.log(w);
    console.log(h);

    $('body').css({ 'height': h });   
    $('body').css({ 'width': w });  
    
    console.log(parseInt(h));
    
    /* Default Values */
    console.log(h + ' is NOT less than ' + 800);
    
    var menu = h * .35;
    var menumargin = menu * .30;
    var closebtn = h * .1;    

 if(h /* <![CDATA[ */ > /* ]]> */ 1300) {
      console.log(h + ' is ipad pro portrait');
      var menu = h * .13;
      var menumargin = menu * .6;
      var buttons = h * .02;
      $('.curtain_plate').css({ 'padding-top': "130px" });
  }else if(w /* <![CDATA[ */ > /* ]]> */ 1300) {
      console.log(h + ' is ipad pro landscape');
      var menu = h * .15;
      var menumargin = menu * .7;
      var buttons = h * -.05;
      $('.curtain_plate').css({ 'padding-top': "120px" });
  } else if(w /* <![CDATA[ */ > /* ]]> */ 1000) {
        console.log(h + ' is less than 600');
        var menu = h * .25;
        var menumargin = menu * .25;
        var closebtn = h * .12;
    } else if(h /* <![CDATA[ */< /* ]]> */ 450) {
        console.log(h + ' is less than 600');
        var menu = h * .30;
        var menumargin = menu * .2;
        var closebtn = h * .2;
    } else if(h /* <![CDATA[ */< /* ]]> */ 700) {
        console.log(h + ' is less than 600');
        var menu = h * .18;
        var menumargin = menu * .24;
        var closebtn = h * .1;      
    } else if(h /* <![CDATA[ */< /* ]]> */ 800) {
        console.log(h + ' is less than 800');
        var menu = h * .25;
        var menumargin = menu * .25;
        var closebtn = h * .1;
    }

    $('.home-logo').css({'height': menu + 'px'});
    $('.h1').css({'margin-top': menumargin + 'px'});
    $('.btn_close').css({'top' : closebtn + 'px'});
      
    console.log(menu);
}

function gamesview() {
      console.log( "ready!" );
    
    var w = Math.max(document.documentElement.clientWidth, window.innerWidth || 0);
    var h = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);

    console.log(w);
    console.log(h);

    $('body').css({ 'height': h });   
    $('body').css({ 'width': w });  
    $('.curtain_plate').css({ 'height': (h * .25) + "px" });
    $('.pre-scrollable').css({ 'height': (h * .23) + "px" });
    $('.pre-scrollable').css({ 'width': (w * .7) + "px" });
    
    console.log(parseInt(h));

  console.log(h + ' default ');
  var menu = h * .35;
  var menumargin = menu * .30;  
  var buttons = h * .2;

  if(h /* <![CDATA[ */ > /* ]]> */ 1300) {
      console.log(h + ' is ipad pro portrait');
      var menu = h * .13;
      var menumargin = menu * .6;
      var buttons = h * .02;
      $('.curtain_plate').css({ 'padding-top': "130px" });
      $('.pre-scrollable').css({ 'height': (h * .5) + "px" });
  }else if(w /* <![CDATA[ */ > /* ]]> */ 1300) {
      console.log(h + ' is ipad pro landscape');
      var menu = h * .15;
      var menumargin = menu * .7;
      var buttons = h * -.05;
      $('.curtain_plate').css({ 'padding-top': "120px" });
      $('.pre-scrollable').css({ 'height': (h * .3) + "px" });
  } else if(h /* <![CDATA[ */ > /* ]]> */ 1000) {
      console.log(h + ' is ipad portrait');
      var menu = h * .12;
      var menumargin = menu * .22;
      var buttons = h * .02;
      $('.pre-scrollable').css({ 'height': (h * .25) + "px" });
  }else if(w /* <![CDATA[ */ > /* ]]> */ 1000) {
      console.log(h + ' is ipad landscape');
      var menu = h * .15;
      var menumargin = menu * .2;
      var buttons = h * -.05;  
      $('.pre-scrollable').css({ 'height': (h * .2) + "px" });
  } else if(h /* <![CDATA[ */< /* ]]> */ 350) {
      console.log(h + ' is iphone5 landscape');
      var menu = h * .21;
      var menumargin = menu * .04;
      var buttons = h * -.1; 
      $('.pre-scrollable').css({ 'height': (h * .25) + "px" });
      $('.curtain_plate').css({ 'height': (h * .4) + "px" });
      $('.curtain_plate').css({ 'padding-top': "30px" });      
  } else if(h /* <![CDATA[ */< /* ]]> */ 380) {
      console.log(h + ' is device landscape');
      var menu = h * .16;
      var menumargin = menu * .04;
      var buttons = h * -.1; 
      $('.pre-scrollable').css({ 'height': (h * .3) + "px" });
      $('.curtain_plate').css({ 'height': (h * .4) + "px" });
      $('.curtain_plate').css({ 'padding-top': "40px" }); 
  } else if(h /* <![CDATA[ */< /* ]]> */ 450) {
      console.log(h + ' is device landscape');
      var menu = h * .14;
      var menumargin = menu * .13;
      var buttons = h * -.1; 
      $('.pre-scrollable').css({ 'height': (h * .25) + "px" });
      $('.curtain_plate').css({ 'height': (h * .4) + "px" });
      $('.curtain_plate').css({ 'padding-top': "50px" });       
  } else if(h /* <![CDATA[ */< /* ]]> */ 700) {
      console.log(h + ' is iphone 6');
      var menu = h * .12;
      var menumargin = menu * .15;
      var buttons = h * -.05;
      $('.pre-scrollable').css({ 'height': (h * .23) + "px" });
      $('.curtain_plate').css({ 'height': (h * .18) + "px" });
      $('.curtain_plate').css({ 'padding-top': "30px" }); 
  } else if(h /* <![CDATA[ */< /* ]]> */ 800) {
      console.log(h + ' is iphone 6+');
      var menu = h * .1;
      var menumargin = menu * .4;
      var buttons = h * -.05;
  }

    
  $('.home-logo').css({'height': menu + 'px'});
  $('.h1').css({'margin-top': menumargin + 'px'});   
  $('.buttons').css({'margin-top': buttons + 'px'});  

    console.log(menu);
}

function guessesview() {
      console.log( "ready!" );
    
    var w = Math.max(document.documentElement.clientWidth, window.innerWidth || 0);
    var h = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);

    console.log(w);
    console.log(h);

    $('body').css({ 'height': h });   
    $('body').css({ 'width': w });  
    $('.curtain_plate').css({ 'height': (h * .25) + "px" });
    $('.character').css({ 'height': (h * .23) + "px" });
    
    $('.dagger').css({ 'height': (h * .45) + "px" }); 
    $('.dagger').css({ 'width': (w * .67) + "px" });
    $('.dagger').css({ 'left': "33%" });
    // $('.dagger').css({ 'bottom': "10%" });
    
    console.log(parseInt(h));

  console.log(h + ' default ');
  var menu = h * .35;
  var menumargin = menu * .30;  
  var buttons = h * .2;

 if(h /* <![CDATA[ */ > /* ]]> */ 1300) {
      console.log(h + ' is ipad pro portrait');
      var menu = h * .13;
      var menumargin = menu * .6;
      var buttons = h * .02;
      $('.curtain_plate').css({ 'padding-top': "130px" });
  }else if(w /* <![CDATA[ */ > /* ]]> */ 1300) {
      console.log(h + ' is ipad pro landscape');
      var menu = h * .15;
      var menumargin = menu * .7;
      var buttons = h * -.05;
      $('.curtain_plate').css({ 'padding-top': "120px" });
  } else if(h /* <![CDATA[ */ > /* ]]> */ 1000) {
      console.log(h + ' is ipad portrait');
      var menu = h * .14;
      var menumargin = menu * .25;
      var buttons = h * .02;
      $('.character').css({ 'height': (h * .35) + "px" });
      $('.curtain_plate').css({ 'height': (h * .2) + "px" });
      $('.character').css({ 'width': "auto" });
      $('.dagger').css({ 'height': (h * .4) + "px" }); 
      $('.dagger').css({ 'width': (w * .67) + "px" });
      $('.dagger').css({ 'left': "33%" });
      $('.dagger').css({ 'top': "-35%" });
  }else if(w /* <![CDATA[ */ > /* ]]> */ 1000) {
      console.log(h + ' is ipad landscape');
      var menu = h * .15;
      var menumargin = menu * .25;
      var buttons = h * -.05;  
      $('.character').css({ 'height': (h * .27) + "px" });
      $('.dagger').css({ 'height': (h * .45) + "px" }); 
      $('.dagger').css({ 'width': (w * .67) + "px" });
      $('.dagger').css({ 'left': "28.5%" });
      $('.dagger').css({ 'top': "-75%" });
  } else if(h /* <![CDATA[ */< /* ]]> */ 350) {
      console.log(h + ' is iphone5 landscape');
      var menu = h * .22;
      var menumargin = menu * .04;
      var buttons = h * -.1; 
      $('.character').css({ 'height': (h * .6) + "px" });
      $('.curtain_plate').css({ 'height': (h * .35) + "px" });
      $('.curtain_plate').css({ 'padding-top': "20px" });   
      
      $('.dagger').css({ 'height': (h * .9) + "px" }); 
      $('.dagger').css({ 'width': (w * .67) + "px" });
      $('.dagger').css({ 'left': "38.6%" });
      $('.dagger').css({ 'bottom': "-18%" });
  } else if(h /* <![CDATA[ */< /* ]]> */ 380) {
      console.log(h + ' is iphone6 landscape');
      var menu = h * .2;
      var menumargin = menu * .04;
      var buttons = h * -.1; 
      $('.character').css({ 'height': (h * .45) + "px" });
      $('.curtain_plate').css({ 'height': (h * .3) + "px" });
      $('.curtain_plate').css({ 'padding-top': "20px" }); 
      
      $('.dagger').css({ 'height': (h * .75) + "px" }); 
      $('.dagger').css({ 'width': (w * .67) + "px" });
      $('.dagger').css({ 'left': "35%" });
      $('.dagger').css({ 'top': "-35%" });    
  } else if(h /* <![CDATA[ */< /* ]]> */ 450) {
      console.log(h + ' is iphone6 landscape');
      var menu = h * .2;
      var menumargin = menu * .04;
      var buttons = h * -.1; 
      $('.character').css({ 'height': (h * .45) + "px" });
      $('.curtain_plate').css({ 'height': (h * .3) + "px" });
      $('.curtain_plate').css({ 'padding-top': "20px" }); 
      
      $('.dagger').css({ 'height': (h * .75) + "px" }); 
      $('.dagger').css({ 'width': (w * .67) + "px" });
      $('.dagger').css({ 'left': "33.5%" });
      $('.dagger').css({ 'top': "-65%" });         
  } else if(h /* <![CDATA[ */< /* ]]> */ 700) {
      console.log(h + ' is iphone 6');
      var menu = h * .12;
      var menumargin = menu * .1;
      var buttons = h * -.05;
      $('.character').css({ 'height': (h * .32) + "px" });
      $('.curtain_plate').css({ 'height': (h * .17) + "px" });
      $('.curtain_plate').css({ 'padding-top': "30px" }); 
      $('.dagger').css({ 'left': "50%" });
      $('.dagger').css({ 'top': "-22%" });
  } else if(h /* <![CDATA[ */< /* ]]> */ 800) {
      console.log(h + ' is iphone 6+');
      var menu = h * .14;
      var menumargin = menu * .3;
      var buttons = h * -.05;
      $('.character').css({ 'height': (h * .35) + "px" });
      // $('.character').css({ 'padding-bottom': "20px" });
      $('.curtain_plate').css({ 'height': (h * .17) + "px" });
      $('.curtain_plate').css({ 'padding-top': "30px" }); 
      $('.dagger').css({ 'left': "47%" });
      $('.dagger').css({ 'top': "-22%" });
  }

    
  $('.home-logo').css({'height': menu + 'px'});
  $('.h1').css({'margin-top': menumargin + 'px'});   
  $('.buttons').css({'margin-top': buttons + 'px'});  

    console.log(menu);
}

function loginview() {
            // console.log('document ready');

          
          var w = Math.max(document.documentElement.clientWidth, window.innerWidth || 0);
          var h = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);
      
          console.log(w);
          console.log(h);
      
          $('body').css({ 'height': h });    
          $('body').css({ 'width': w });              

            console.log('login stuff');
            
 if(h /* <![CDATA[ */ > /* ]]> */ 1300) {
      console.log(h + ' is ipad pro portrait');
      var menu = h * .13;
      var menumargin = menu * .6;
      var buttons = h * .02;
      $('.curtain_plate').css({ 'padding-top': "130px" });
  }else if(w /* <![CDATA[ */ > /* ]]> */ 1300) {
      console.log(h + ' is ipad pro landscape');
      var menu = h * .15;
      var menumargin = menu * .7;
      var buttons = h * -.05;
      $('.curtain_plate').css({ 'padding-top': "120px" });
  } else  if(w /* <![CDATA[ */ > /* ]]> */ 1000) {
              console.log(h + ' is ipad landscape');
              var menu = h * .45;
            
              $('.login-logo').css({'height': menu + 'px'})
            }else if(h /* <![CDATA[ */ > /* ]]> */ 1000) {
              console.log(h + ' is ipad portrait');
              var menu = h * .5;
            
              $('.login-logo').css({'height': menu + 'px'})
          } else if(h /* <![CDATA[ */< /* ]]> */ 450) {
              console.log(h + ' is device landscape');
              var menu = h * .3;
            
              $('.login-logo').css({'height': menu + 'px'})
          } else if(h /* <![CDATA[ */< /* ]]> */ 700) {
              console.log(h + ' is less than 600');
              var menu = h * .40;
              var menumargin = menu * .15;
            
              $('.login-logo').css({'height': menu + 'px'})
          } else if(h /* <![CDATA[ */< /* ]]> */ 800) {
              console.log(h + ' is less than 800');
              var menu = h * .45;
              var menumargin = menu * .25;
            
              $('.login-logo').css({'height': menu + 'px'})
              $('.h1').css({'margin-top': menumargin + 'px'})
          }
            else {
              console.log(h + ' is NOT less than ' + 800);
              var menu = h * .35;
              var menumargin = menu * .30;
            
              $('.login-logo').css({'height': menu + 'px'})
              $('.h1').css({'margin-top': menumargin + 'px'})     
            }
            
}

function menuview() {
      console.log( "ready!" );

    
    var w = Math.max(document.documentElement.clientWidth, window.innerWidth || 0);
    var h = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);

    console.log(w);
    console.log(h);

    $('body').css({ 'height': h });   
    $('body').css({ 'width': w });  
    
    console.log(parseInt(h));

  console.log(h + ' default ');
  var menu = h * .35;
  var menumargin = menu * .30;    
    
  if(h /* <![CDATA[ */ > /* ]]> */ 1300) {
      console.log(h + ' is ipad pro portrait');
      var menu = h * .35;
      var menumargin = menu * .4;
      var buttons = h * .02;
  }else if(w /* <![CDATA[ */ > /* ]]> */ 1300) {
      console.log(h + ' is ipad pro landscape');
      var menu = h * .45;
      var menumargin = menu * .4;
      var buttons = h * -.05;
  } else if(h /* <![CDATA[ */ > /* ]]> */ 1000) {
      console.log(h + ' is ipad portrait');
      var menu = h * .35;
      var menumargin = menu * .3;
  } else if(h /* <![CDATA[ */< /* ]]> */ 450) {
      console.log(h + ' is less than 600');
      var menu = h * .6;
      var menumargin = menu * .30;
  } else if(h /* <![CDATA[ */< /* ]]> */ 700) {
      console.log(h + ' is less than 600');
      var menu = h * .25;
      var menumargin = menu * .15;    
  } else if(h /* <![CDATA[ */< /* ]]> */ 800) {
      console.log(h + ' is less than 800');
      var menu = h * .25;
      var menumargin = menu * .25;
  }

    
  $('.home-logo').css({'height': menu + 'px'});
  $('.h1').css({'margin-top': menumargin + 'px'});    
    
    console.log(menu);
}

function murdererview() {
      console.log( "ready!" );
    
    var w = Math.max(document.documentElement.clientWidth, window.innerWidth || 0);
    var h = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);

    console.log(w);
    console.log(h);

    $('body').css({ 'height': h });   
    $('body').css({ 'width': w });  
    $('.curtain_plate').css({ 'height': (h * .25) + "px" });
    $('.character').css({ 'height': (h * .23) + "px" });
    
    $('.dagger').css({ 'height': (h * .45) + "px" }); 
    $('.dagger').css({ 'width': (w * .67) + "px" });
    $('.dagger').css({ 'left': "33%" });
    // $('.dagger').css({ 'bottom': "10%" });
    
    console.log(parseInt(h));

  console.log(h + ' default ');
  var menu = h * .35;
  var menumargin = menu * .30;  
  var buttons = h * .2;

  if(h /* <![CDATA[ */ > /* ]]> */ 1300) {
      console.log(h + ' is ipad pro portrait');
      var menu = h * .13;
      var menumargin = menu * .6;
      var buttons = h * .02;
      $('.curtain_plate').css({ 'padding-top': "130px" });
  }else if(w /* <![CDATA[ */ > /* ]]> */ 1300) {
      console.log(h + ' is ipad pro landscape');
      var menu = h * .15;
      var menumargin = menu * .7;
      var buttons = h * -.05;
      $('.curtain_plate').css({ 'padding-top': "120px" });
  }else if(h /* <![CDATA[ */ > /* ]]> */ 1000) {
          console.log(h + ' is ipad portrait');
          var menu = h * .14;
          var menumargin = menu * .25;
          var buttons = h * .02;
          $('.character').css({ 'height': (h * .35) + "px" });
          $('.curtain_plate').css({ 'height': (h * .2) + "px" });
          $('.character').css({ 'width': "auto" });
  }else if(w /* <![CDATA[ */ > /* ]]> */ 1000) {
      console.log(h + ' is ipad landscape');
      var menu = h * .15;
      var menumargin = menu * .25;
      var buttons = h * -.05;  
      $('.character').css({ 'height': (h * .27) + "px" });
      $('.dagger').css({ 'left': "28%" });
  } else if(h /* <![CDATA[ */< /* ]]> */ 350) {
      console.log(h + ' is iphone5 landscape');
      var menu = h * .22;
      var menumargin = menu * .04;
      var buttons = h * -.1; 
      $('.character').css({ 'height': (h * .6) + "px" });
      $('.curtain_plate').css({ 'height': (h * .35) + "px" });
      $('.curtain_plate').css({ 'padding-top': "20px" });   
      
      $('.dagger').css({ 'height': (h * .9) + "px" }); 
      $('.dagger').css({ 'width': (w * .67) + "px" });
      $('.dagger').css({ 'left': "38.6%" });
      $('.dagger').css({ 'bottom': "-18%" });
  } else if(h /* <![CDATA[ */< /* ]]> */ 450) {
      console.log(h + ' is iphone6 landscape');
      var menu = h * .2;
      var menumargin = menu * .04;
      var buttons = h * -.1; 
      $('.character').css({ 'height': (h * .45) + "px" });
      $('.curtain_plate').css({ 'height': (h * .3) + "px" });
      $('.curtain_plate').css({ 'padding-top': "20px" }); 
      
      $('.dagger').css({ 'height': (h * .75) + "px" }); 
      $('.dagger').css({ 'width': (w * .67) + "px" });
      $('.dagger').css({ 'left': "35%" });
      $('.dagger').css({ 'bottom': "-35%" });      
  } else if(h /* <![CDATA[ */< /* ]]> */ 700) {
      console.log(h + ' is iphone 6');
      var menu = h * .12;
      var menumargin = menu * .1;
      var buttons = h * -.05;
      $('.character').css({ 'height': (h * .32) + "px" });
      $('.curtain_plate').css({ 'height': (h * .17) + "px" });
      $('.curtain_plate').css({ 'padding-top': "30px" }); 
      $('.dagger').css({ 'left': "51%" });
      $('.dagger').css({ 'bottom': "-18%" });
  } else if(h /* <![CDATA[ */< /* ]]> */ 800) {
      console.log(h + ' is iphone 6+');
      var menu = h * .14;
      var menumargin = menu * .3;
      var buttons = h * -.05;
      $('.character').css({ 'height': (h * .35) + "px" });
      // $('.character').css({ 'padding-bottom': "20px" });
      $('.curtain_plate').css({ 'height': (h * .17) + "px" });
      $('.curtain_plate').css({ 'padding-top': "30px" }); 
      $('.dagger').css({ 'left': "47%" });
      $('.dagger').css({ 'bottom': "-10%" });
  }

    
  $('.home-logo').css({'height': menu + 'px'});
  $('.h1').css({'margin-top': menumargin + 'px'});   
  $('.buttons').css({'margin-top': buttons + 'px'});  

    console.log(menu);
}

function scriptview() {
    console.log( "ready!" );

    var w = Math.max(document.documentElement.clientWidth, window.innerWidth || 0);
    var h = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);

    console.log(w);
    console.log(h);

    $('body').css({ 'height': h });   
    $('body').css({ 'width': w });  
    // $('.curtain_plate').css({ 'width': (w * .45) + "px"});
    $('.curtain_plate').css({ 'height': (h * .25) + "px" });
    $('.pre-scrollable').css({ 'height': (h * .3) + "px" });
    
    console.log(parseInt(h));
    
    /* Default Values */
  console.log(h + ' default ');
    
  var menu = h * .35;
  var menumargin = menu * .30;
  var closebtn = h * .1;   

 if(h /* <![CDATA[ */ > /* ]]> */ 1300) {
      console.log(h + ' is ipad pro portrait');
      var menu = h * .13;
      var menumargin = menu * .6;
      var buttons = h * .02;
      $('.curtain_plate').css({ 'padding-top': "130px" });
  }else if(w /* <![CDATA[ */ > /* ]]> */ 1300) {
      console.log(h + ' is ipad pro landscape');
      var menu = h * .15;
      var menumargin = menu * .7;
      var buttons = h * -.05;
      $('.curtain_plate').css({ 'padding-top': "120px" });
  } else if(w /* <![CDATA[ */ > /* ]]> */ 1000) {
      console.log(h + ' ipad landscape');
      var menu = h * .17;
      var menumargin = menu * .29;
      var closebtn = h * .12;
      $('.pre-scrollable').css({ 'height': (h * .28) + "px" });
    }else if(h /* <![CDATA[ */ > /* ]]> */ 1000) {
          console.log(h + ' ipad portrait');
          var menu = h * .19;
          var menumargin = menu * .55;
          var closebtn = h * .12;     
          $('.pre-scrollable').css({ 'height': (h * .6) + "px" });
  } else if(h /* <![CDATA[ */< /* ]]> */ 325) {
      console.log(h + ' iphone 5 landscape');
      var menu = h * .19;
      var menumargin = menu * .30;
      var closebtn = h * .2;
       $('.pre-scrollable').css({ 'height': (h * .5) + "px" });
    } else if(h /* <![CDATA[ */< /* ]]> */ 380) {
      console.log(h + ' iphone 6 landscape');
      var menu = h * .15;
      var menumargin = menu * .30;
      var closebtn = h * .2;
       $('.pre-scrollable').css({ 'height': (h * .45) + "px" });
    } else if(h /* <![CDATA[ */< /* ]]> */ 450) {
      console.log(h + ' iphone 6+ landscape');
      var menu = h * .14;
      var menumargin = menu * .33;
      var closebtn = h * .2;
       $('.pre-scrollable').css({ 'height': (h * .4) + "px" });
    } else if(h /* <![CDATA[ */< /* ]]> */ 700) {
      console.log(h + ' iphone 6');
      var menu = h * .10;
      var menumargin = menu * .45;
      var closebtn = h * .1;      
       $('.pre-scrollable').css({ 'height': (h * .2) + "px" });
  } else if(h /* <![CDATA[ */< /* ]]> */ 800) {
      console.log(h + ' iphone 6+');
      var menu = h * .12;
      var menumargin = menu * .50;
      var closebtn = h * .1;
  }

  $('.home-logo').css({'height': menu + 'px'});
  $('.h1').css({'margin-top': menumargin + 'px'});
  $('.btn_close').css({'top' : closebtn + 'px'});
      
    console.log(menu);

}

function submenuview() {
    console.log( "ready!" );
    
    var w = Math.max(document.documentElement.clientWidth, window.innerWidth || 0);
    var h = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);

    console.log(w);
    console.log(h);

    $('body').css({ 'height': h });   
    $('body').css({ 'width': w });  
    
    console.log(parseInt(h));

  console.log(h + ' default ');
  var menu = h * .35;
  var menumargin = menu * .30;  
  var buttons = h * .2;
    
 if(h /* <![CDATA[ */ > /* ]]> */ 1300) {
      console.log(h + ' is ipad pro portrait');
      var menu = h * .13;
      var menumargin = menu * .6;
      var buttons = h * .02;
      $('.curtain_plate').css({ 'padding-top': "130px" });
  }else if(w /* <![CDATA[ */ > /* ]]> */ 1300) {
      console.log(h + ' is ipad pro landscape');
      var menu = h * .15;
      var menumargin = menu * .7;
      var buttons = h * -.05;
      $('.curtain_plate').css({ 'padding-top': "120px" });
  } else if(h /* <![CDATA[ */ > /* ]]> */ 1000) {
      console.log(h + ' is ipad portrait');
      var menu = h * .25;
      var menumargin = menu * .15;
      var buttons = h * .01;
  }else if(w /* <![CDATA[ */ > /* ]]> */ 1000) {
      console.log(h + ' is ipad landscape');
      var menu = h * .18;
      var menumargin = menu * .2;
      var buttons = h * -.05;  
  } else if(h /* <![CDATA[ */< /* ]]> */ 450) {
      console.log(h + ' is device landscape');
      var menu = h * .24;
      var menumargin = menu * .04;
      var buttons = h * -.08; 
  } else if(h /* <![CDATA[ */< /* ]]> */ 700) {
      console.log(h + ' is iphone 6');
      var menu = h * .2;
      var menumargin = menu * .15;
      var buttons = h * -.07; 
  } else if(h /* <![CDATA[ */< /* ]]> */ 800) {
      console.log(h + ' is iphone 6+');
      var menu = h * .25;
      var menumargin = menu * .15;
      var buttons = h * -.05;
  }

    
  $('.home-logo').css({'height': menu + 'px'});
  $('.h1').css({'margin-top': menumargin + 'px'});   
  $('.buttons').css({'margin-top': buttons + 'px'});   
    
    console.log(menu);
}

function storyview() {
    console.log( "ready!" );
    
    var w = Math.max(document.documentElement.clientWidth, window.innerWidth || 0);
    var h = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);

    console.log(w);
    console.log(h);

    $('body').css({ 'height': h });   
    $('body').css({ 'width': w });  
    $('.curtain_plate').css({ 'height': (h * .25) + "px" });
    $('.pre-scrollable').css({ 'height': (h * .35) + "px" });
    $('.pre-scrollable').css({ 'width': (w * .8) + "px" });
    
    console.log(parseInt(h));

  console.log(h + ' default ');
  var menu = h * .35;
  var menumargin = menu * .30;  
  var buttons = h * .2;

  if(h /* <![CDATA[ */ > /* ]]> */ 1300) {
      console.log(h + ' is ipad pro portrait');
      var menu = h * .13;
      var menumargin = menu * .6;
      var buttons = h * .02;
      $('.curtain_plate').css({ 'padding-top': "150px" });
      $('.pre-scrollable').css({ 'height': (h * .5) + "px" });
  }else if(w /* <![CDATA[ */ > /* ]]> */ 1300) {
      console.log(h + ' is ipad pro landscape');
      var menu = h * .15;
      var menumargin = menu * .7;
      var buttons = h * -.05;
      $('.curtain_plate').css({ 'padding-top': "120px" });
      $('.pre-scrollable').css({ 'height': (h * .3) + "px" });
  } else if(h /* <![CDATA[ */ > /* ]]> */ 1000) {
      console.log(h + ' is ipad portrait');
      var menu = h * .12;
      var menumargin = menu * .3;
      var buttons = h * .02;
      $('.pre-scrollable').css({ 'height': (h * .5) + "px" });
  }else if(w /* <![CDATA[ */ > /* ]]> */ 1000) {
      console.log(h + ' is ipad landscape');
      var menu = h * .15;
      var menumargin = menu * .2;
      var buttons = h * -.05;  
      $('.pre-scrollable').css({ 'height': (h * .3) + "px" });
  } else if(h /* <![CDATA[ */< /* ]]> */ 450) {
      console.log(h + ' is device landscape');
      var menu = h * .1;
      var menumargin = menu * .04;
      var buttons = h * -.1; 
      $('.pre-scrollable').css({ 'height': (h * .49) + "px" });
  } else if(h /* <![CDATA[ */< /* ]]> */ 700) {
      console.log(h + ' is iphone 6');
      var menu = h * .07;
      var menumargin = menu * .35;
      var buttons = h * -.05;
      $('.pre-scrollable').css({ 'height': (h * .33) + "px" });
  } else if(h /* <![CDATA[ */< /* ]]> */ 800) {
      console.log(h + ' is iphone 6+');
      var menu = h * .09;
      var menumargin = menu * .4;
      var buttons = h * -.05;
  }

    
  $('.home-logo').css({'height': menu + 'px'});
  $('.h1').css({'margin-top': menumargin + 'px'});   
  $('.buttons').css({'margin-top': buttons + 'px'});  

    console.log(menu);
}
