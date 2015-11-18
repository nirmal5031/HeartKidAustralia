(function($) {

    var refreshKeyPressed = false;
    var modifierPressed = false;
    var f5key = 116;
    var rkey = 82;
    var modkey = [17, 224, 91, 93];

    // Check for refresh keys
    $(document).bind('keydown',function(evt) {
        // Check for refresh
        if (evt.which == f5key || window.modifierPressed && evt.which == rkey) {
            refreshKeyPressed = true;
        }

        // Check for modifier
        if (modkey.indexOf(evt.which) >= 0) {
            modifierPressed = true;
        }
    });

    // Check for refresh keys
    $(document).bind('keyup', function(evt) {
        // Check undo keys
        if (evt.which == f5key || evt.which == rkey) {
            refreshKeyPressed = false;
        }

        // Check for modifier
        if (modkey.indexOf(evt.which) >= 0) {
            modifierPressed = false;
        }
    });

    $(window).bind('beforeunload', function(event) {
        var message = "not refreshed";

        if (refreshKeyPressed) {
            message = "refreshed";
        }

        event.returnValue = message;
        return message;
    });

}(jQuery));
$(window).bind('beforeunload', function(e) {

    return "Please complete the survey before exiting. You may just need 10-15 minutes of time. Thank You "
});

/**
 * Created by 535222 on 11/17/2015.
 */
