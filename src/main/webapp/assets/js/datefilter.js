$(function(){
	$('#datepicker').datepicker().on("changeDate", function(event) {
		var hiddenInputSelector = "#" + $(event.target).attr("id") + "-hidden";
		$(hiddenInputSelector).val(event.date.valueOf());
	});
});
