$(function(){
	function createDP (selector) {
		var $input = $(selector),
			$hidden = $(selector + "-hidden");
		$input.datepicker()
			.on("changeDate", function(event) {
				$hidden.val(event.date.valueOf());
			});
		var date = new Date(parseInt($hidden.val(), 10));
		if (!isNaN(date)) {
			$input.datepicker("update", date);
		}
	}
	createDP("#date-start");
	createDP("#date-end");
});
