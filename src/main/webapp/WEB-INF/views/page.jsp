<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@page pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>my SpringTestApp</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style>
			table {
				border: 2px solid black;
				border-collapse: collapse;
			}
			
			#lettersHolder {
				width: 980px;
			}
			
			#formRow {
				border-bottom: 2px solid black;
			}
			
			button {
				height: 22px;
				width: 70px;
				margin: -3px -30px;
				position: relative;
				top: 50%;
				left: 50%;
			}
		</style>
    </head>
        <body>
    	<section id="springtestapp">
    		<div align="right">
    			[<a href="?lang=en">EN</a>] [<a href="?lang=ru">RU</a>]
    		</div>
	        <h3 align="center"><spring:message code="label.letters"/>: </h3>
	        <div align="center">
	        	<form id="insertForm">
			        <table id="lettersHolder" border="1">
			        	<tr>
				        	<th><spring:message code="label.number"/></th>
				        	<th><spring:message code="label.date"/></th>
				        	<th><spring:message code="label.subject"/></th>
				        	<th><spring:message code="label.published"/></th>
				        	<th><spring:message code="label.file"/></th>
				        	<th><spring:message code="label.comment"/></th>
				        	<th> </th>
			        	</tr>
			        	<tr id="formRow">
			        		<td><input name="number" type="text"></td>
				        	<td><input name="date" type="text"></td>
				        	<td><input name="subject" type="text"></td>
				        	<td><input name="published" type="checkbox"></td>
				        	<td>
				        		<input name="file[id]" id="file_id" type="hidden">
				        		<input name="file[name]" id="file_name" type="hidden">
				        		<input name="file[contentType]" id="file_contentType" type="hidden">
				        		<div id="filenameHolder">
				        			<a href="#" id="uploadLink"><spring:message code="label.upload"/></a>
				        		</div>
				        	</td>
				        	<td><input name="description" type="text"></td>
				        	<td><input type="submit" value="<spring:message code="label.save"/>"></td>
			        	</tr>
			        </table>
		        </form>
				<input type="file" name="fileUpload" id="fileUpload" style="opacity: 0">
		</div>
        </section>
        <script type="text/template" id="letter-template">
			<td><@=number@></td>
            <td><@=date@></td>
            <td><@=subject@></td>
            <td><input type="checkbox" class="published" <@=(published ? "checked" : "")@> <@=(published ? "disabled" : "")@>></td>
            <td><a href="/springtestapp/upload/<@=file.id@>"><@=file.name@></a></td>
            <td><@=description@></td>
			<td><button class="removeLink" href="#"><spring:message code="label.delete"/></button></td>
        </script>
        <!-- Scripts -->
        <script src="static/js/libs/jquery.js"></script>
        <script src="static/js/libs/underscore.js"></script>
        <script src="static/js/libs/backbone.js"></script>
        <script src="static/js/libs/backbone.syphon.js"></script>

        <script src="static/js/app.js"></script>
        <script src="static/js/models/letter.js"></script>
        <script src="static/js/views/letter.js"></script>
        <script src="static/js/collections/letters.js"></script>
        <script src="static/js/views/letters.js"></script>

        <script>
	        $("#uploadLink").on("click", function(){
	        	$("#fileUpload").click();
       		})
       		$("#fileUpload").on("change", function() {
       			
       			if ($("#fileUpload")[0].files.length == 0) {
	        		return;
	        	}
       			
       			var data = new FormData();
       			data.append("fileUpload", $("#fileUpload")[0].files[0]);
       			
       			$.ajax({
       			    url: '/springtestapp/upload',
       			    data: data,
       			    cache: false,
       			    contentType: false,
       			    processData: false,
       			    type: 'POST',
       			    success: function(data){
       			        $("#file_id")[0].value = data.id;
       			        $("#file_name")[0].value = data.name;
       			        $("#file_contentType")[0].value = data.contentType;
       			        $("#filenameHolder").html(data.name);
       			    }
       			});
       			
       		});


        </script>

    </body>
</html>
