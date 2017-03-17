/**
 * 
 */
var remainingTime = null;
function initTimer(time) {
	remainingTime = time;
	setInterval(() => {
		remainingTime = remainingTime - 1000;
		$('#timer').html(msToTime(remainingTime));
	}, 1000);
	
	setTimeout(() => {
		
		$.post(window.location.origin + "/assessment/question", $('form').serialize(), function () {
			window.location.href = window.location.origin + "/assessment/terminate/" + $('#examId').html();
		});
	}, time);
}

$(document).ready(function() {
	$.get(window.location.origin + "/exam/timer/" + $('#examId').html())
		.done(function(data) {
			initTimer(data);
		})
		.fail(function(data) {
			console.log(data);
		});
});

function msToTime(s) {
	  var ms = s % 1000;
	  s = (s - ms) / 1000;
	  var secs = s % 60;
	  s = (s - secs) / 60;
	  var mins = s % 60;
	  var hrs = (s - mins) / 60;
	
	  return format(hrs) + ':' + format(mins) + ':' + format(secs);
}

function format(number) {
	if (number < 10) {
		return "0" + number;
	}
	
	return number;
}