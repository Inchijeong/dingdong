<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.11.4/sweetalert2.min.css">
  	
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.11.4/sweetalert2.min.js"></script>
		<script src="/resources/_include/js/common.js"></script> <!-- Default JS -->
		<title>FCM</title>
	</head>
	<body>
		<div class="container">
			<sec:authentication var="user" property="principal" />
			<button type="button" class="resVoiceListBtn btn btn-info" data-toggle="modal" data-target="#rvModal">응대 메시지 선택</button><br />
			
			<!-- 방문자 이미지/영상/음성 표시 DIV -->
			<div class="dspVisitorFiles"></div>
			
			<!-- modal -->
			<div class="modal fade" id="rvModal" role="dialog">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
							<h4 class="modal-title">응답 메시지 목록</h4>
						</div>
						
						<div class="modal-body">
							<div>
								<ul class="resVoiceList"></ul>
							</div>
						</div>
						
						<div class="modal-footer">
							<button type="button" class="sendBtn btn btn-default">전송</button>
							<button type="button" class="closeBtn btn btn-default" data-dismiss="modal">닫기</button>
						</div>
					</div>
				</div>
			</div>
			<!-- end modal -->
		</div>
		
		<script>
			$(".sendBtn").on("click", function(e){
				$.ajax({
					url:"/sendVoice",
					type:"post",
					data:{
						userNo:1,
						resFileNo:$(":radio[name='resFileNo']:checked").val(),
						'${_csrf.parameterName}' : '${_csrf.token}'
					},
					dataType:"text"
				}).done(function(result){
					console.log("전송");
					console.log(result);
					if(result == "success"){
						showAlert("선택하신 응답 메시지를 전송하였습니다.");
						$(".closeBtn").click();
					}else{
						showAlert("응답 메시지 전송에 실패하였습니다.","error");
					}
				});
			});
			
			$(".resVoiceListBtn").on("click", function(e){
			 	getResVoiceList();
			});
		
			function getResVoiceList(){
        $.ajax({
	        url : "/resfile",
	        data : {userNo:1},
	        dataType : "json"
        }).done(function(list){
	        console.log(list);
	        var listStr = "";
	        for(var i in list){
	        	var resFileNo = list[i].resFileNo;
	          listStr += '<li>' +
	          					 '	<input type="radio" id="resFile'+ resFileNo +'" name="resFileNo" value="'+ resFileNo +'">' +
					             '	<label for="resFile'+ resFileNo +'">'+ list[i].resFileTitle +'</label>' +
					             '	<span class="playBtn" data-fpath="'+ list[i].resFilePath +'" data-fname="'+ list[i].resFileName +'">재생</span>' +
					             '</li>';
	        }
	        $(".resVoiceList").html(listStr);
        });
	    }
	    
	    function getVisitorFiles(){
	    	console.log("fn : "+ getParameters("fn"));
	    	$.ajax({
	    		url : "/getFiles",
	    		type : "post",
	    		data : {
	    			userNo : 1,
	    			visitFileNo : getParameters("fn"),
	    			'${_csrf.parameterName}' : '${_csrf.token}'
	    		},
	    		dataType : "json"
	    	}).done(function(data){
	    		console.log("get");
	    		console.log(data);
	    		var htmlStr = "";
	    		if(data.visitVoiceName){
	    			htmlStr	+= '<audio controls>'+
	    			  				 '	<source src="/displayFile/audio?name='+data.visitVoiceName+'" type="audio/mpeg">'+
	    								 '</audio><br />';
	    		}
	    		if(data.visitFileType != 'a'){
		    		if(data.visitFileType == 'i'){
		    			htmlStr += '<img src="/displayFile/image?name='+data.visitFileName+'" alt="visitor image" width="450"><br />';
		    		}else{
		    			htmlStr += '<video width="450" preload="metadata" controls>'+
		    				  			 '	<source src="/displayFile/video?name='+data.visitFileName+'" type="video/mp4">'+
		    								 '</video><br />';
		    		}
	    		}
	    		$(".dspVisitorFiles").html(htmlStr);
	    	});
	    }
	    getVisitorFiles();
		</script>
	</body>
</html>