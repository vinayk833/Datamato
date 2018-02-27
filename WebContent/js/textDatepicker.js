 $(function() {
      $( "#datepicker" ).datepicker({ minDate: -100, maxDate: "+0D" });
      $("#datepicker").datepicker("setDate",new Date());
      $( "#datepicker" ).datepicker( "option", "dateFormat", "dd/mm/yy");
    });
 
 
 