// 기간 설정
var term = 7;

// 사용자 번호
var userNo = 1;

// 차트 정보 불러오기 ajax
var chartAjax = function(chartText){
	
	// 기간별 방문 횟수 ajax
	$.ajax({
		url:"/getVisitCount",
		type:"GET",
		data:{userNo:userNo, term:term},
		dataType: "json"
	}).done(function(visitTimeData){
		// 기간별 방문 횟수 차트
		var showVisitCount = function(){
			var orgVD = visitTimeData.slice();
			
			var svg = d3.select("#lineChart"),
			 margin = {top: 20, right: 20, bottom: 30, left: 50},
			  width = +svg.attr("width") - margin.left - margin.right,
			 height = +svg.attr("height") - margin.top - margin.bottom,
			      g = svg.append("g").attr("transform", "translate(" + margin.left + "," + margin.top + ")");
			
			var parseTime = d3.timeParse("%d-%b-%y");
			
			var settingDate = new Date();
			
			var x = d3.scaleTime()
								.domain([settingDate.setDate(settingDate.getDate()-term), new Date()])
			    			.range([0, width]);
			
			var y = d3.scaleLinear()
								.domain([0, 10])
			    			.range([height, 0]);
			
			var line = d3.line()
			    				 .x(function(d) { return x(d.roundDate); })
			    				 .y(function(d) { return y(d.population); });
			
			for (var i = 0; i < visitTimeData.length; i++){
			    visitTimeData[i].roundDate = new Date(visitTimeData[i].roundDate);
			    visitTimeData[i].population = +visitTimeData[i].population;
			}
			  
			visitTimeData.unshift(["roundData", "population"]);							

			x.domain(d3.extent(visitTimeData, function(d) { return d.roundDate; }));
			//y.domain(d3.extent(visitTimeData, function(d) { return d.population; }));
			
			g.append("g")
			    .attr("transform", "translate(0," + height + ")")
			    .call(d3.axisBottom(x))
			    .select(".domain")
			    //.remove();
			
			g.append("g")
			    .call(d3.axisLeft(y))
			    .append("text")
			    .attr("fill", "#000")
			    .attr("transform", "rotate(-90)")
			    .attr("y", 6)
			    .attr("dy", "0.71em")
			    .attr("text-anchor", "end")
			    .text("방문자수 (명)");
			
			g.append("path")
			    .datum(orgVD)
			    .attr("fill", "none")
			    .attr("stroke", "#77d662")
			    .attr("stroke-linejoin", "round")
			    .attr("stroke-linecap", "round")
			    .attr("stroke-width", 4)
			    .attr("d", line);
		}
		
		var $lineChartDiv = $("#lineChartDiv");
		
		var visitTimeDataSize = visitTimeData.length;
			switch(visitTimeDataSize){
				case 0 :
					$lineChartDiv.text(chartText +"의 방문자가 존재하지 않습니다.");
					break;
				default :
					$lineChartDiv.html(
							'<h4 class="chart-title">시간별 방문 횟수</h4>'+
							'<svg id="lineChart" width="800" height="400"></svg>');
					showVisitCount();
					break;
		}
	});
	
	
	// 방문자 성별 가져오기 ajax
	$.ajax({
		url:"/getGenderCount",
		type:"GET",
		data:{userNo:userNo, term:term},
		dataType: "json"
	}).done(function(genderData){
		
			var showGender = function(){
			var orgGD = genderData.slice();
			genderData.unshift(["gender", "population"]);
		
			// 성별 방문 비율 차트
			var svg = d3.select("#roundChart"),
			  width = +svg.attr("width"),
			 height = +svg.attr("height"),
			 radius = Math.min(width, height) / 2,
			      g = svg.append("g").attr("transform", "translate(" + width / 2 + "," + height / 2 + ")");
			
			var color = d3.scaleOrdinal(["#f44183", "#4250f4"]);
			
			var pie = d3.pie()
			    				.sort(null)
			    				.value(function(d) { return d.population; });
			
			var path = d3.arc()
			    				 .outerRadius(radius - 10)
			    				 .innerRadius(0);
			
			var label = d3.arc()
			    					.outerRadius(radius - 40)
			    					.innerRadius(radius - 40);
			
			var arc = g.selectAll(".arc")
							   .data(pie(genderData))
							   .enter()
							   .append("g")
							   .attr("class", "arc");
			
			arc.append("path")
			   .attr("d", path)
			   .attr("fill", function(d) { return color(d.data.gender); });
			
			// 텍스트 영역
			var arc2 = g.selectAll(".arc2")
	        				 .data(pie(orgGD))
	        				 .enter()
	        				 .append("g")
	        				 .attr("class", "arc2");
			
			 arc2.append("text")
					 .attr("transform", function(d) { return "translate(" + label.centroid(d) + ")"; })
					 .attr("dy", "0.35em")
					 .text(function(d) { return d.data.gender + "(" + d.data.population + ")" });  
			
			// 범주영역
			var chartCategory = d3.select("#chartCategory");
			var legend = chartCategory
										 .attr("class", "legend")
							       .attr("width", radius * 2)
							       .attr("height", radius / 2 * 3)
							       .selectAll("g")
							       .data(color.domain().slice(1))
							       .enter()
							       .append("g")
							       .attr("transform", function(d, i) { return "translate(0," + i * 20 + ")"; });
			
			legend.append("rect")
			      .attr("width", 18)
			      .attr("height", 18)
			      .style("fill", color);
			
			legend.append("text")
			      .attr("x", 24)
			      .attr("y", 9)
			      .attr("dy", ".35em")
			      .text(function(d) { return d; });
			} 
			
		var genderDataSize = genderData.length;
			var $roundChartDiv = $("#roundChartDiv");
		
			switch(genderDataSize){
				case 0 :
					$roundChartDiv.text(chartText +"의 방문자가 존재하지 않습니다.");
					break;
				case 1 :
					$roundChartDiv.html(
							'<h4 class="chart-title">남녀 방문 비율</h4>'+
							'<svg id="roundChart" width="400" height="200"></svg>'+
							'<svg id="chartCategory" width="400" height="200"></svg>');
					if(genderData[0].gender == "male"){
						genderData[0].gender="남자";
					}else if(genderData[0].gender == "female"){
						genderData[0].gender="여자";
					}
					showGender();
					break;
				case 2 :
					$roundChartDiv.html(
							'<h4 class="chart-title">남녀 방문 비율</h4>'+
							'<svg id="roundChart" width="400" height="200"></svg>'+
							'<svg id="chartCategory" width="400" height="200"></svg>');
					genderData[0].gender="남자";
				genderData[1].gender="여자";
				showGender();
					break;
		}
			
	});
	
	// 방문자 세대 가져오기 ajax
	$.ajax({
		url:"/getGeneration",
		type:"GET",
		data:{userNo:userNo, term:term},
		dataType: "json"
	}).done(function(generationData){
		
		var showGeneration = function(){
			var orgGD = generationData.slice();
			generationData.unshift(["generation","population"]);
		
  	// 연령별 방문 비율 차트
      var svg = d3.select("#roundChart2"),
        width = +svg.attr("width"),
       height = +svg.attr("height"),
       radius = Math.min(width, height) / 2,
            g = svg.append("g").attr("transform", "translate(" + width / 2 + "," + height / 2 + ")");

      var color = d3.scaleOrdinal(["#98abc5", "#8a89a6", "#7b6888", "#6b486b", "#a05d56", "#d0743c", "#ff8c00"]);

      var pie = d3.pie()
          			 	.sort(null)
          				.value(function(d) { return d.population; });

      var path = d3.arc()
         					 .outerRadius(radius - 10)
          				 .innerRadius(0);

      var label = d3.arc()
          					.outerRadius(radius - 40)
          					.innerRadius(radius - 40);
      var arc = g.selectAll(".arc")
        				 .data(pie(generationData))
        				 .enter()
        				 .append("g")
        				 .attr("class", "arc");

      arc.append("path")
         .attr("d", path)
         .attr("fill", function(d) { return color(d.data.generation); });
      
			//텍스트 영역
			var arc2 = g.selectAll(".arc2")
	        				 .data(pie(orgGD))
	        				 .enter()
	        				 .append("g")
	        				 .attr("class", "arc2");
			
			 arc2.append("text")
			.attr("transform", function(d) { return "translate(" + label.centroid(d) + ")"; })
			.attr("dy", "0.35em")
			//.text(function(d) { return d.data.generation + "대" + "(" + d.data.population + ")"; }); 
			//.text(function(d) { return "(" + d.data.population + ")"; }); 
			.text(function(d) { return d.data.population + "명"; }); 
      
			// 범주영역
			var chartCategory2 = d3.select("#chartCategory2");
      var legend = chartCategory2
				             .attr("class", "legend")
				             .attr("width", radius * 2)
				             .attr("height", radius / 2 * 3)
				             .selectAll("g")
				             .data(color.domain().slice(1))
				             .enter()
				             .append("g")
				             .attr("transform", function(d, i) { return "translate(0," + i * 20 + ")"; });

    legend.append("rect")
            .attr("width", 18)
            .attr("height", 18)
            .style("fill", color);

    legend.append("text")
            .attr("x", 24)
            .attr("y", 9)
            .attr("dy", ".35em")
            .text(function(d) { return d + "대"; });
		}
		
		var $roundChartDiv2 = $("#roundChartDiv2");
		
		var generationDataSize = generationData.length;
			switch(generationDataSize){
				case 0 :
					$roundChartDiv2.text(chartText +"의 방문자가 존재하지 않습니다.");
					break;
				default :
					$roundChartDiv2.html(
							'<h4 class="chart-title">연령별 방문 비율</h4>'+
							'<svg id="roundChart2" width="400" height="200"></svg>'+
							'<svg id="chartCategory2" width="400" height="200"></svg>');
					showGeneration();
					break;
		}
		
	});

} // chart ajax function end

$("#div-chart-btn button").click(function(e){
	e.preventDefault();
	// term = $(this).attr("data-term");
	term = $(this).data("term");
	var chartText = $(this).data("text");
	chartAjax(chartText);
});	

$btnDaily = $("#btn-weekly");
$btnDaily.trigger("focus").trigger("click");
