function loadData(){
	loadYear();
	loadMonth();
	loadDay();
}

// 加载年份
function loadYear(){
	// 获取指定的表单中的下拉列表
	var year = document.forms["form1"].year;
	// 依次加载数据
	for(var i=1901;i<=new Date().getFullYear();i++){
		// 创建新的节点并设置相关的属性，挂载节点到指定的位置
		var op = document.createElement("option");
		op.value = i;
		op.textContent = i;
		year.appendChild(op);
	}
}

// 加载月份
function loadMonth(){
	// 获取指定的表单中的下拉列表
	var month = document.forms["form1"].month;
	// 依次加载数据
	for(var i=2;i<=12;i++){
		// 创建新的节点并设置相关的属性，挂载节点到指定的位置
		var op = document.createElement("option");
		// 调整月份格式
		if(i<10){
			op.value = "0"+ i;
			op.textContent = "0" + i;
		}else{
			op.value = i;
			op.textContent = i;
		}
		month.appendChild(op);
	}
}

// 加载日期
function loadDay(){
	// 获取指定的表单中的下拉列表
	var day = document.forms["form1"].day;
	// 依次加载数据
	for(var i=1;i<=31;i++){
		// 创建新的节点并设置相关的属性，挂载节点到指定的位置
		var op = document.createElement("option");
		// 调整月份格式
		if(i<10){
			op.value = "0"+ i;
			op.textContent = "0" + i;
		}else{
			op.value = i;
			op.textContent = i;
		}
		day.appendChild(op);
	}
}

// 设置二级联动，当选择了相应的年份、月份获取相应的天数
function reloadDay(){
	// 获取当前选择的年份月份
	var selectYear = document.forms["form1"].year.value;
	var selectMonth = document.forms["form1"].month.value;
	// 获取某年某月有几天
	var month = parseInt(selectMonth,10) + 1;
    var date = new Date(selectYear, selectMonth, 0);
    var count =  date.getDate();
    // 获取day，重新加载天数信息
    var day = document.forms["form1"].day;
    day.length=0;
   	// 依次加载数据
	for(var i=1;i<=count;i++){
		// 创建新的节点并设置相关的属性，挂载节点到指定的位置
		var op = document.createElement("option");
		// 调整月份格式
		if(i<10){
			op.value = "0"+ i;
			op.textContent = "0" + i;
		}else{
			op.value = i;
			op.textContent = i;
		}
		day.appendChild(op);
	}
}
