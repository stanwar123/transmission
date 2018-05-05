<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	
	<head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pojo to Json Serialization using Jersey with Jackson for Java REST Services</title>
        
        <script src="<%=request.getContextPath() %>/js/jquery-1.11.2.min.js"></script>
        <script>
            var ctxPath = "<%=request.getContextPath() %>";
            $(function(){                
                $("#postDispatchLevel").on("click", function(){
                	var theName = $.trim($("#txt1").val());
                    $.ajax({
                        url: ctxPath+"/dispatch/DispatchLevelService/post",
                        type: "POST",
                        data: '{"inputMessage": "'+theName+'"}',
                        contentType: "application/json",
                        cache: false,
                        dataType: "json",
                        success:function(response) {
                        	//alert(data[0].value);
                        	    if(response) {   // DO SOMETHING     
                        	        $('#picoutput').html(response.outputMessage);
                        	    } else {  
                        	    	$('#picoutput').html("This is error");
                        	  }
                            }
                    });
                });                
            });
        </script>
                
   	</head>
	<body>
	<h3><div id="picoutput"></div><h3>
	   <ul>
	   		<input type="text" name="txt1" id="txt1" /><br>
	   		<input type="text" name="txt2" id="txt2" /><br>
           <li><button id="postDispatchLevel">Get DispatchLevel</button></li>
	   </ul>
           
    </body>
    
</html>