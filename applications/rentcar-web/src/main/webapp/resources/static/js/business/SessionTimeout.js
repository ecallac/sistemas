/**
 * Monitor the session timeout cookie from Apache and log the user out when expired
 */
"use strict";

var jQuery = require("jquery").noConflict();
var Cookies = require("js-cookie");


$(document).ready(function(){
	calcOffset();
    checkSession();
});
/**
 * We can't assume the server time and client time are the same
 * so lets calcuate the difference
 */
function calcOffset() {
    var serverTime = Cookies.get('serverTime');
    serverTime = serverTime==null ? null : Math.abs(serverTime);
    var clientTimeOffset = (new Date()).getTime() - serverTime;
    Cookies.set('clientTimeOffset', clientTimeOffset);
}

/**
 * Check the sessionExpiry cookie and see if we should send the user to /
 */
function checkSession() {
    var sessionExpiry = Math.abs(Cookies.get('sessionExpiry'));
    var timeOffset = Math.abs(Cookies.get('clientTimeOffset'));
    var localTime = (new Date()).getTime();
    if(!sessionExpiry){
        window.console.log("Unknown session sessionExpiry");
        return;
    }
    if (localTime - timeOffset > (sessionExpiry+15000)) { // 15 extra seconds to make sure
        window.location = "/login";
        Cookies.remove('sessionExpiry');
    } else {
        setTimeout('checkSession()', 10000);
    }
    window.console.log("Session expires in " + ((sessionExpiry+15000) - localTime - timeOffset) + "ms");
}

window.checkSession = checkSession; //Used for recalling via setTimeout