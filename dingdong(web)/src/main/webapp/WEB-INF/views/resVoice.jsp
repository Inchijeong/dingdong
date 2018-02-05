<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>  
<!DOCTYPE html>
<!--[if lt IE 7]><html class="no-js lt-ie9 lt-ie8 lt-ie7" lang="en"> <![endif]-->
<!--[if (IE 7)&!(IEMobile)]><html class="no-js lt-ie9 lt-ie8" lang="en"><![endif]-->
<!--[if (IE 8)&!(IEMobile)]><html class="no-js lt-ie9" lang="en"><![endif]-->
<!--[if (IE 9)]><html class="no-js ie9" lang="en"><![endif]-->
<!--[if gt IE 8]><!--> <html> <!--<![endif]-->
	<head>
	
	<!-- Meta Tags -->
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	
	<title>DingDong</title>   
	
	<meta name="description" content="Insert Your Site Description" /> 
	
	<!-- Mobile Specifics -->
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="HandheldFriendly" content="true"/>
	<meta name="MobileOptimized" content="320"/>   
	
	<!-- Mobile Internet Explorer ClearType Technology -->
	<!--[if IEMobile]>  <meta http-equiv="cleartype" content="on">  <![endif]-->
	
	<!-- Bootstrap -->
	<link href="/resources/_include/css/bootstrap.min.css" rel="stylesheet">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
	
	<!-- Main Style -->
	<link href="/resources/_include/css/main.css" rel="stylesheet">
	
	<!-- Supersized -->
	<link href="/resources/_include/css/supersized.css" rel="stylesheet">
	<link href="/resources/_include/css/supersized.shutter.css" rel="stylesheet">
	
	<!-- FancyBox -->
	<link href="/resources/_include/css/fancybox/jquery.fancybox.css" rel="stylesheet">
	
	<!-- Font Icons -->
	<link href="/resources/_include/css/fonts.css" rel="stylesheet">
	
	<!-- Shortcodes -->
	<link href="/resources/_include/css/shortcodes.css" rel="stylesheet">
	
	<!-- Responsive -->
	<link href="/resources/_include/css/bootstrap-responsive.min.css" rel="stylesheet">
	<link href="/resources/_include/css/responsive.css" rel="stylesheet">
	
	<!-- Supersized -->
	<link href="/resources/_include/css/supersized.css" rel="stylesheet">
	<link href="/resources/_include/css/supersized.shutter.css" rel="stylesheet">
	
	<!-- Google Font -->
	<link href='https://fonts.googleapis.com/css?family=Titillium+Web:400,200,200italic,300,300italic,400italic,600,600italic,700,700italic,900' rel='stylesheet' type='text/css'>
	
  <!-- sweetalert2 -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.11.4/sweetalert2.min.css">
  <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
     
	<!-- Fav Icon -->
	<link rel="shortcut icon" href="#">
	
	<link rel="apple-touch-icon" href="#">
	<link rel="apple-touch-icon" sizes="114x114" href="#">
	<link rel="apple-touch-icon" sizes="72x72" href="#">
	<link rel="apple-touch-icon" sizes="144x144" href="#">
	
	
	<!-- Modernizr -->
	<script src="/resources/_include/js/modernizr.js"></script>
	
	<!-- Analytics -->
	<script type="text/javascript">
	
 	  var _gaq = _gaq || [];
	  _gaq.push(['_setAccount', 'Insert Your Code']);
	  _gaq.push(['_trackPageview']);
	
	  (function() {
	    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
	    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
	    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
	  })(); 
	
	</script>
	<!-- End Analytics -->
	
	</head>

	<body>
		<sec:authentication var="user" property="principal" />
	
		<%@include file="/WEB-INF/views/common/header.jsp" %>		
		
		<!-- Our Work Section -->
 		<div id="resVoice" class="page">
			<div class="container text-center">
				<!-- Title Page -->
		    <div class="row">
		        <div class="col-xs-12">
		            <div class="title-page">
		                <h2 class="title">응대 메시지</h2>
		                <h3 class="title-description"><span class="emphasis">방문자</span>에게 발송할 <span class="emphasis">응대 메시지</span>를 관리하세요.</h3>
		            </div>
		        </div>
		    </div>
		    <!-- End Title Page -->
			    
				<!-- Response Voice Page -->
				<div class="row">
		    	<div class="col-xs-12 rv-row">
			    	<input type="text" class="rvinput" id="resFileName">
				    <a class="startBtn button button-small">Start</a>
				    <a class="stopBtn button button-small">Stop</a>
				    <a class="saveBtn button button-small">Save</a>
				    <a class="clearBtn button button-small">Clear</a>
				    <audio class="draftFilePlayer"></audio>
		    	</div>
		    </div>
		    
		    <div class="row">
		    	<div class="col-xs-12">
				    <div>
			        <audio class="saveFilePlayer"></audio>
			       	<table class="list-table table-fixed">
			       	 <thead>
				       	<tr><th colspan="3"><span>My Voice List</span></th></tr>
			       	</thead> 
			        <tbody class="resVoiceList">
			        </tbody>
			        </table>
				    </div>
			    </div>
				</div>
		  	<!--End Response Voice Page-->
 			</div>
		</div>
		<!-- End resVoice Section -->
		

		<!-- Footer -->
		<footer>
			<p class="credits">&copy;2017. WIPY all right reserved. </p>
		</footer>
		<!-- End Footer -->
		
		<!-- Back To Top -->
		<a id="back-to-top" href="#">
			<i class="font-icon-arrow-simple-up"></i>
		</a>
		<!-- End Back to Top -->
	
		<!-- Js -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script> <!-- jQuery Core -->
 		<script src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.11.4/sweetalert2.min.js"></script> <!-- sweetalert2 -->
		<script src="/resources/_include/js/bootstrap.min.js"></script> <!-- Bootstrap -->
		<script src="/resources/_include/js/supersized.3.2.7.min.js"></script> <!-- Slider -->
		<script src="/resources/_include/js/waypoints.js"></script> <!-- WayPoints -->
		<script src="/resources/_include/js/waypoints-sticky.js"></script> <!-- Waypoints for Header -->
		<script src="/resources/_include/js/jquery.isotope.js"></script> <!-- Isotope Filter -->
		<script src="/resources/_include/js/jquery.fancybox.pack.js"></script> <!-- Fancybox -->
		<script src="/resources/_include/js/jquery.fancybox-media.js"></script> <!-- Fancybox for Media -->
		<script src="/resources/_include/js/plugins.js"></script> <!-- Contains: jPreloader, jQuery Easing, jQuery ScrollTo, jQuery One Page Navi -->
		<script src="/resources/_include/js/main.js"></script> <!-- Default JS -->
		<script src="/resources/_include/js/common.js"></script>
		
		<!-- End Js -->
	  <script>
      var shouldStop = false;
      var stopped = false;
      var recordedChunks = [];

      var handleSuccess = function(stream) {
          var mediaRecorder = new MediaRecorder(stream, {mimeType: 'video/webm; codecs=vp9'});
          mediaRecorder.addEventListener('dataavailable', function(e) {
              if (e.data.size > 0) {
                  recordedChunks.push(e.data);
              }

              if(shouldStop === true && stopped === false) {
                  mediaRecorder.stop();
                  stopped = true;
              }
          });
          mediaRecorder.addEventListener('start', function() {
              mediaRecorder.requestData();
          });

          $(".startBtn").on('click', function() {
              stopped = false;
              shouldStop = false;
              mediaRecorder.start();
              
              $(".rv-row a").removeClass("active");
              $(this).addClass("active");
          });

          $(".stopBtn").on('click', function() {
              shouldStop = true;
              mediaRecorder.requestData();
              
              $(".rv-row a").removeClass("active");
              $(this).addClass("active");
          });
      };

      navigator.mediaDevices.getUserMedia({ audio: true, video: false }).then(handleSuccess);
          

      $(".saveBtn").on("click", function(e){
          if(recordedChunks.length <= 0 ){
              showAlert("메시지 녹음 후 저장하세요","info");
              return false;
          }
					
          var fd = new FormData();
          fd.append("file", new Blob(recordedChunks,{type:"audio/mpeg"}),$("#resFileName").val() + ".mp3");
          fd.append("userNo", ${user.userNo});
          $.ajax({
              url : "/resfile",
              type : "post",
              data : fd,
              dataType : "text",
              processData : false,
              contentType:false
          }).done(function(result){
              console.log("saveBtn > done > " + result);
              $(".rv-row a").removeClass("active");
              $(this).addClass("active");
              
              if(result == "success"){
                  showAlert("저장되었습니다.");
                  recordedChunks = [];
                  $("#resFileName").val("");
                  getResVoiceList();
              }else{
                  showAlert("저장 중 오류가 발생하였습니다.","error");
              }
          });
      });

      $(".clearBtn").on("click", function(e){
          recordedChunks = [];
          $(".rv-row a").removeClass("active");
          $(this).addClass("active");
      });

      $(".resVoiceList").on("click","span.delVoiceBtn", function(e){
          var $li = $(this).closest("tr");
          var resFileNo = $li.data("no");
          console.log($li);
          console.log("fileno : " + resFileNo);
          $.ajax({
              url: "/resfile/" + resFileNo,
              type: "delete",
			  			data:{'${_csrf.parameterName}' : '${_csrf.token}'},
              dataType : "text"
          }).done(function(result){
              console.log("delVoiceBtn > done > " + result);
              if(result == "success"){
                  showAlert("선택한 메시지가 삭제되었습니다.");
                  getResVoiceList();
              }else{
                  showAlert("삭제 중 오류가 발생하였습니다.","error");
              }
          });
      })

      $(".resVoiceList").on("click","span.playBtn", function(e){console.log("click");
          $(".saveFilePlayer").attr({"src":"/displayFile/"+$(this).data("fpath")+"?name="+$(this).data("fname"), "autoplay":true}).trigger("load");
      });

      function getResVoiceList(){
          $.ajax({
              url : "/resfile",
              data : {userNo:${user.userNo}},
              dataType : "json"
          }).done(function(list){
              console.log(list);
              var listStr = "";
              if(list.length > 0){
	              for(var i in list){
	            	  listStr += '<tr data-no="'+list[i].resFileNo+'">' +
	                  '<td class="col-xs-10">'+ list[i].resFileTitle +'</td>' +
	                  '<td class="col-xs-1"><span class="playBtn list-button font-icon-volume-up play" data-fpath="'+list[i].resFilePath+'" data-fname="'+list[i].resFileName+'"></span></td>'+
	                  '<td class="col-xs-1"><span class="delVoiceBtn material-icons list-button delete">delete</span></td>'+
	                  '</tr>';
	              }
              }else{
            	  listStr += '<tr><td colspan="3">등록된 응대 메시지가 없습니다.</td></tr>'
              }
             
              $(".resVoiceList").html(listStr);
          });
      }
      getResVoiceList();
    </script>
	</body>
</html>