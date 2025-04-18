$.ajaxSetup({
    beforeSend: function(xhr, settings) {
        if (settings.type == 'POST' || settings.type == 'PUT' || settings.type == 'DELETE') {
            function getCookie(name) {
                var cookieValue = null;
                if (document.cookie && (document.cookie != '')) {
                    var cookies = document.cookie.split(';');
                    for (var i = 0; i < cookies.length; i++) {
                        var cookie = jQuery.trim(cookies[i]);
                        // Does this cookie string begin with the name we want?
                        if (cookie.substring(0, name.length + 1) == (name + '=')) {
                            cookieValue = decodeURIComponent(cookie.substring(name.length + 1));
                            break;
                        }
                    }
                }
                return cookieValue;
            }
            if (!(/^http:.*/.test(settings.url) || /^https:.*/.test(settings.url))) {
                // Only send the token to relative URLs i.e. locally.
                xhr.setRequestHeader("X-CSRFToken", getCookie('csrftoken'));
            }
        }
    }
});

function deleteRequest(url, id, callback, errorCallback) {
    $.ajax({
        url: url,
        type: 'DELETE',
        headers: {"id": id},
        success: callback(id) || $.noop,
        error: errorCallback || $.noop
    });
}

function toggleEntity(url, id) {
	deleteRequest(url, id, updateButtonLabel, userUpdateError);
}

function updateButtonLabel(id) {
	if("Disable" == $('#' + id).html()) {
		$('#'+id).html("Enable");
	}
	else {
		$('#'+id).html("Disable");
	}
}

function userUpdateError() {
	console.log('failed');
}