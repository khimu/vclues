var appId = '475131239512420';

var fbId;
var userEmail;
var accessToken;

var d = new Date();
var todaycache = d.getMonth() + "/" + d.getDay() + "/" + d.getFullYear();


function setCookie(name)
{
        var exdate = new Date();
        exdate.setDate(exdate.getDate() + 365);
        document.cookie = name + "=1";
}

function getCookie(name)
{
        var i,x,y,ARRcookies=document.cookie.split(";");
        for (i = 0;i<ARRcookies.length;i++)
        {
          x = ARRcookies[i].substr(0, ARRcookies[i].indexOf("="));
          y = ARRcookies[i].substr(ARRcookies[i].indexOf("=") + 1);
          x = x.replace(/^\s+|\s+$/g, "");
          if (x == name)
          {
            return unescape(y);
          }
         }
}

// add Social elements to the pages where required with correct sharing URLs
function renderButtons() {
    var linkUrl = window.location.href;

    if ($('.googlePlus').length > 0) {
        $('.googlePlus').html('<g:plusone href="' + linkUrl + '" size="medium" style="float:right"></g:plusone>');
    };
    if ($('.fbLike').length > 0) {
        $('.fbLike').html('<fb:like send="false" href="' + linkUrl + '" layout="button_count" width="80" show_faces="false" font="arial"></fb:like>');
    };
    if ($('.fbSend').length > 0) {
        $('.fbSend').html('<fb:like send="false" href="' + linkUrl + '" layout="button_count" width="80" show_faces="false" font="arial"></fb:like>');
    };
    if ($('.tweet').length > 0) {
        $('.tweet').html('<a href="http://twitter.com/share" data-url="' + linkUrl + '" class="twitter-share-button" data-count="none" data-via="test">Tweet</a></span>')
    };
    if ($('.fbComments').length > 0) {
        $('.fbComments').html('<fb:comments href="' + linkUrl + '" num_posts="3" width="640"></fb:comments>');
    };
    if ($('.fbRecommend').length > 0) {
        $('.fbRecommend').html('<img style="border: none" alt="" src="/assets/images/like_us_home.png"><fb:activity recommendations="true" border_color="#000000" font="arial" colorscheme="dark" header="false" height="400" width="220" site="www.test.com"><span>');
    };

};


function facebookReady(){
	
    FB.init({
      appId  : appId,
      status : true,
      cookie : true,
      xfbml  : true
    });
    
    /* does not work in sandbox mode */
    FB.getLoginStatus(function(response) {
 	   
    	console.log('login');
 	   
        updateButton(response);
             
        if (response.status != 'connected') {
        	console.log('response.status ' + response.status + ' is NOT connected');
        }else{           
          accessToken = response.authResponse.accessToken;
          FB.api('/me?fields=name,id,email', function(response) {
                 fbId = response.id;
                 userEmail = response.email;
                 console.log('response.status ' + response.status + ' is connected');

                 // Login to VegaClues
                 autoLogin(response.id, accessToken, response.email);
                 
                 $(document).trigger("facebook:ready");
          });       
        }
      });
} // end facebookReady();
    
$(document).ready(function(){
	(function () {
	    $.getScript(document.location.protocol + '//apis.google.com/js/plusone.js'); // Load Google Plus files
	    $.getScript(document.location.protocol + '//platform.twitter.com/widgets.js'); // load Twitter files
	    $.getScript(document.location.protocol + '//connect.facebook.net/en_US/all.js');
	    //$.getScript('/scripts/datepicker/js/datepicker.js');

	    renderButtons(); // render the buttons in the page
	} ());	

	  if(window.FB) {
	    facebookReady();
	  } else {
	    window.fbAsyncInit = facebookReady;
	  }
		  
}); // end document().ready


var logoutClick = (function(response) {
	  console.log('logoutClick anonymous function called');
	  
		var copyResponse = response;
		
		function facebookLogout() {
		    FB.logout(function(copyResponse) {
		        $('.profileFBpic img').attr('src', staticUrl + '/assets/images/fb_login.png');
		               //$('#fbname').html('Login Here');
		    });	
		}
	});


function updateButton(response) {
    console.log('updateButton');
    //var button = document.getElementById('fb-auth');
    
    logoutClick(response);
        
    if (response.authResponse) {
    	console.log('already logged in');
    	

      FB.api('/me?fields=name,id', function(response) {
    	  fbId = response.id;
    	  console.log(fbId);
          $('.profileFBpic img').attr('src', 'https://graph.facebook.com/' + response.id + '/picture');
          
          //$('#fbname').html(response.name);
          //$('#fbname-title').html(response.name);
          
          //var aToken = response.authResponse.accessToken;
          //var email = response.email
          //autoLogin(fbId, accessToken, email);
      });

      //button.onclick = function() {                         
      //  FB.logout(function(response) {
      //   $('.profileFBpic img').attr('src', staticUrl + '/assets/images/fb_login.png');
                //$('#fbname').html('Login Here');
    	//});
      //};
      
      

      
    } else {                        
      button.onclick = function() {     
      // window.setTimeout(function() { window.location.href = ctx + "/fas/load.htm"; }, 6000); 
      FB.login(function(response) {
         var aToken = response.authResponse.accessToken;
         if (response.authResponse) {
            FB.api('/me', function(response) {
                fbId = response.id;
                console.log('After facebook login button click');
                //window.location.href = ctx + "/fas/load.htm";
                // NOTE: not every page has the html below and therefore we cannot support code below.  To allow this code to listen
                // to click on 'fb-auth' from any page, we must handle this even generically which is to redirect to the load page
                
                //$('#logged-in').show();
                //$('#logged-out').hide();                                                
                //alert('logged in' + $('.profileFBpic img').attr('src'));
                
                //$('.profileFBpic img').attr('src', 'https://graph.facebook.com/' + response.id + '/picture');
                //$('#fbname').html(response.name);
                //$('#fbname-title').html(response.name);
                
               autoLogin(response.id, aToken, response.email);
        });    
          } else {
            //user cancelled login or did not grant authorization
          }
        }, {scope:'email', perms:'publish_stream'});                                  }
    }
  }



//stream publish method
function post(postMsg){
 //storeBoxy.hide(); 
     FB.login(function(response) {
            if (response.authResponse) {
                    accessToken = response.authResponse.accessToken; 
        FB.api('/me?fields=name,id,email', function(response) {
             autoLogin(response.id, accessToken,response.email);
             fbId = response.id;
             fbPost(postMsg); 
         });    
            }
      // handle the response
     }, {scope: 'publish_stream,user_about_me,email'});
}

function fbPost(postMsg){
    FB.api('/me/feed', 'post', { message: postMsg }, function(response) {
      if(response && response.id ){                  
    	  alert('success');
      }else {
    	  alert('failed');
      } 
    });
}

var csrfParam = '';
var csrfToken = '';

function autoLogin(fbId, accessToken, email){

    if (email == undefined || email == null){
            FB.api('/me', function(response) {
            	email = response.email;
            });
    }
            
    $.ajax({
        type: "POST",
        url: "/fblogin",
        data: {fbId: fbId, accessToken: accessToken, email: email, csrfParam: csrfToken}
      }).done(function ( data ) {
          console.log('login success');
      }).fail(function(jqXHR, textStatus){ //ERROR
            console.log('FAILURE: ' + textStatus);
            console.log(jqXHR);
        });
}