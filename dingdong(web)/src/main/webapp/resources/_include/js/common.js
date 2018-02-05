var getParameters = function (paramName) {
    // 리턴값을 위한 변수 선언
    var returnValue;

    // 현재 URL 가져오기
    var url = location.href;

    // get 파라미터 값을 가져올 수 있는 ? 를 기점으로 slice 한 후 split 으로 나눔
    var parameters = (url.slice(url.indexOf('?') + 1, url.length)).split('&');

    // 나누어진 값의 비교를 통해 paramName 으로 요청된 데이터의 값만 return
    for (var i = 0; i < parameters.length; i++) {
        var varName = parameters[i].split('=')[0];
        if (varName.toUpperCase() == paramName.toUpperCase()) {
            returnValue = parameters[i].split('=')[1];
            return decodeURIComponent(returnValue);
        }
    }
};

function showAlert(msg, type, isButton, time){
	type = type ? type : "success";
	isButton = isButton ? isButton : false;
	time = time ? time : 1000;
	
	swal({
		title:msg,
		type:type,
		showConfirmButton: isButton,
		timer: time
	}).catch(function() {});
}

function showConfirm(msg, callback, param){
	swal({
		  title: msg,
		  type: 'warning',
		  showCancelButton: true,
		  confirmButtonColor: '#3085d6',
		  cancelButtonColor: '#d33',
		  confirmButtonText: '예',
		  cancelButtonText: '아니오'
		}).then(function () {
			callback(param);
		}, function(){});
}

function getDateFormat(date,sep){
	var d = new Date(date);
	var month = d.getMonth()+1;
	var date = d.getDate();
	
	return d.getFullYear()+sep+(month < 10 ? "0"+month:month)+sep+(date < 10 ? "0"+date:date);
}

function getDateTime(date){
	var d = new Date(date);
	var month = d.getMonth()+1;
	var date = (d.getDate() < 10 ? "0":"") + d.getDate();
	var hours = (d.getHours() < 10 ? "0":"") + d.getHours();
	var minutes = (d.getMinutes() < 10 ? "0":"") + d.getMinutes();
	var seconds = (d.getSeconds() < 10 ? "0":"") + d.getSeconds();
	
	return d.getFullYear()+"-"+(month < 10 ? "0"+month:month)+"-"+ date +
			" "+ hours +":"+ minutes +":"+ seconds;
}