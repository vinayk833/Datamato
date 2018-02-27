
$(document).ready(function() {

	$( "#startdate,#enddate" ).datepicker({
		 maxDate : "0",
		 maxDate:"0",
	changeMonth: true,
	changeYear: true,
	firstDay: 1,
	dateFormat: 'dd/mm/yy',
	})

	$( "#startdate" ).datepicker({ dateFormat: 'dd/mm/yy' });
	$( "#enddate" ).datepicker({ dateFormat: 'dd/mm/yy' });

	$('#enddate').change(function() {
	var start = $('#startdate').datepicker('getDate');
	var end   = $('#enddate').datepicker('getDate');

	if(start==null){
		alert("Please Enter the Start Date")
		$('#startdate').val("");
		$('#enddate').val("");
		$('#days').val("");}
		
	   else if (start<end) {
	 
		var days   = (end - start)/1000/60/60/24;
		$('#days').val(days)
		
	}

	 else {
		
	alert ("End Date must be latter than Start Date!");
	$('#startdate').val("");
	$('#enddate').val("");
	$('#days').val("");
	}
	}
	); //end change function
	}); //end ready
	   