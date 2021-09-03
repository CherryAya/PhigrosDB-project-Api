var controllerArray = new Array('Info', 'Overview', 'ChapterList', 'Chapter', 
							'SideStory', 'Single', 'Search', 'Grade', 
							'Difficulty', 'Test');	
var initFlag = true;
var baseUrl = '/api';
var controllerSelect;
var requestUrl, requestMethod, requestParamsTable, apidescription;

function createOption(obj, values) {
	for (var i in values) {
		var opt = new Option(values[i], i);
		obj.options.add(opt);
	}
}

function init() {
	if (initFlag) {
		controllerSelect = document.getElementById('controllerSelect');
		createOption(controllerSelect, controllerArray);
		requestUrl = document.getElementById('requestUrl');
		requestMethod = document.getElementById('requestMethod');
		requestParamsTable = document.getElementById('requestParamsTable');
		apidescription = document.getElementById('apidescription');
		initFlag = false;
	}
}

function OnChangeEventHandler() {
	var selectedIndex = controllerSelect.selectedIndex;
	var selectedOption = controllerArray[selectedIndex];
	apidescription.innerHTML = "";
	switch (selectedOption) {
		case controllerArray[0]: info_func(); break;
		case controllerArray[1]: overview_func(); break;
		case controllerArray[2]: chapterlist_func(); break;
		case controllerArray[3]: chapter_func(); break;
		case controllerArray[4]: sidestory_func(); break;
		case controllerArray[5]: single_func(); break;
		case controllerArray[6]: search_func(); break;
		case controllerArray[7]: grade_func(); break;
		case controllerArray[8]: difficulty_func(); break;
		case controllerArray[9]: test_func(); break;
		default: default_func();
	}
}

function info_func() {
	requestUrl.value = baseUrl + "/info";
	requestMethod.value = "GET";
	requestParamsTable.innerHTML = "";
	apidescription.innerHTML = 'Info 接口 <br><i>> 响应Api/数据库相关信息 </i><br>请求方式: GET<br>参数: 无<br>请求头: 无<br><hr>详见: <a href="https://github.com/CherryAya/PhigrosDB-project-Database/blob/main/docs/Info.md">https://github.com/CherryAya/PhigrosDB-project-Database/blob/main/docs/Info.md</a>';
}

function overview_func() {
	requestUrl.value = baseUrl + "/overview";
	requestMethod.value = "GET";
	requestParamsTable.innerHTML = "";
	apidescription.innerHTML = 'Overview 接口 <br><i>> 响应数据库Overview表 (收录曲总览) </i><br>请求方式: GET<br>参数: 无<br>请求头: 无<br><hr>详见: <a href="https://github.com/CherryAya/PhigrosDB-project-Database/blob/main/docs/Overview.md">https://github.com/CherryAya/PhigrosDB-project-Database/blob/main/docs/Overview.md</a>';
}

function chapterlist_func() {
	requestUrl.value = baseUrl + "/chapterlist";
	requestMethod.value = "GET";
	requestParamsTable.innerHTML = "";
	apidescription.innerHTML = 'ChapterList 接口 <br><i>> 响应数据库ChapterList表 (章节列表) </i><br>请求方式: GET<br>参数: 无<br>请求头: 无<br><hr>详见: <a href="https://github.com/CherryAya/PhigrosDB-project-Database/blob/main/docs/ChapterList.md">https://github.com/CherryAya/PhigrosDB-project-Database/blob/main/docs/ChapterList.md</a>';
}

function chapter_func() {
	requestUrl.value = baseUrl + "/chapter";
	requestMethod.value = "GET";
	requestParamsTable.innerHTML = "PathVariable: ";
	paramstable_addList(requestParamsTable, new Array('/{ChapterID}', '/ex/{ChapterID}'));
	apidescription.innerHTML = 'Chapter 接口 <br><i>> 响应数据库Chapter(Ex)表 (主线章节/额外章节 查询) </i><br>请求方式: GET<br>参数: 路径变量<br><ol><li>/{ChapterID}</li><li>/ex/{ChapterID}</li><p>Chapter(Ex)ID 取表名 Chapter-{ID} | Chapter-Ex-{ID} ( Chapter-One~Three 将重定向于 Chapter-Legacy )</p></ol><br>请求头: 无<br><hr>详见: <ol>Chapter:<li><a href="https://github.com/CherryAya/PhigrosDB-project-Database/blob/main/docs/Chapter-Legacy.md">https://github.com/CherryAya/PhigrosDB-project-Database/blob/main/docs/Chapter-Legacy.md</a></li><li><a href="https://github.com/CherryAya/PhigrosDB-project-Database/blob/main/docs/Chapter-Four.md">https://github.com/CherryAya/PhigrosDB-project-Database/blob/main/docs/Chapter-Four.md</a></li><li><a href="https://github.com/CherryAya/PhigrosDB-project-Database/blob/main/docs/Chapter-Five.md">https://github.com/CherryAya/PhigrosDB-project-Database/blob/main/docs/Chapter-Five.md</a></li><li><a href="https://github.com/CherryAya/PhigrosDB-project-Database/blob/main/docs/Chapter-Six.md">https://github.com/CherryAya/PhigrosDB-project-Database/blob/main/docs/Chapter-Six.md</a></li>ChapterEx:<li><a href="https://github.com/CherryAya/PhigrosDB-project-Database/blob/main/docs/Chapter-Ex-One.md">https://github.com/CherryAya/PhigrosDB-project-Database/blob/main/docs/Chapter-Ex-One.md</a></li><li><a href="https://github.com/CherryAya/PhigrosDB-project-Database/blob/main/docs/Chapter-Ex-Two.md">https://github.com/CherryAya/PhigrosDB-project-Database/blob/main/docs/Chapter-Ex-Two.md</a></li><li><a href="https://github.com/CherryAya/PhigrosDB-project-Database/blob/main/docs/Chapter-Ex-Three.md">https://github.com/CherryAya/PhigrosDB-project-Database/blob/main/docs/Chapter-Ex-Three.md</a></li><li><a href="https://github.com/CherryAya/PhigrosDB-project-Database/blob/main/docs/Chapter-Ex-Four.md">https://github.com/CherryAya/PhigrosDB-project-Database/blob/main/docs/Chapter-Ex-Four.md</a></li><li><a href="https://github.com/CherryAya/PhigrosDB-project-Database/blob/main/docs/Chapter-Ex-Five.md">https://github.com/CherryAya/PhigrosDB-project-Database/blob/main/docs/Chapter-Ex-Five.md</a></li></ol>';
}

function sidestory_func() {
	requestUrl.value = baseUrl + "/sidestory";
	requestMethod.value = "GET";
	requestParamsTable.innerHTML = "PathVariable: ";
	paramstable_addList(requestParamsTable, new Array('/{ChapterID}'));
	apidescription.innerHTML = 'SideStory 接口 <br><i>> 响应数据库SideStory表 (支线章节查询) </i><br>请求方式: GET<br>参数: 路径变量<br><ol><li>/{ChapterID}</li><p>ChapterID 取表名 Side-story-{ID}</p></ol><br>请求头: 无<br><hr>详见: <a href="https://github.com/CherryAya/PhigrosDB-project-Database/blob/main/docs/Side-story-One.md">https://github.com/CherryAya/PhigrosDB-project-Database/blob/main/docs/Side-story-One.md</a>';
}

function single_func() {
	requestUrl.value = baseUrl + "/single";
	requestMethod.value = "GET";
	requestParamsTable.innerHTML = "";
	apidescription.innerHTML = 'Single 接口 <br><i>> 响应数据库Single表 (单曲精选集查询) </i><br>请求方式: GET<br>参数: 无<br>请求头: 无<br><hr>详见: <a href="https://github.com/CherryAya/PhigrosDB-project-Database/blob/main/docs/Single.md">https://github.com/CherryAya/PhigrosDB-project-Database/blob/main/docs/Single.md</a>';
}

function search_func() {
	requestUrl.value = baseUrl + "/search";
	requestMethod.value = "GET";
	requestParamsTable.innerHTML = "Parameter: ";
	paramstable_addList(requestParamsTable, new Array('name = {SongName}'));
	apidescription.innerHTML = 'Search 接口 <br><i>> 收录曲查询接口 </i><br>请求方式: GET<br>参数: 查询参数<br><ol><li>name = {SongName}</li><p>SongName 取收录曲名</p></ol><br>请求头: 无<br><hr>详见: 无';
}

function grade_func() {
	requestUrl.value = baseUrl + "/grade";
	requestMethod.value = "GET";
	requestParamsTable.innerHTML = "";
	apidescription.innerHTML = 'Grade 接口 <br><i>> 响应数据库Grade表 (得分评级信息) </i><br>请求方式: GET<br>参数: 无<br>请求头: 无<br><hr>详见: <a href="https://github.com/CherryAya/PhigrosDB-project-Database/blob/main/docs/Grade.md">https://github.com/CherryAya/PhigrosDB-project-Database/blob/main/docs/Grade.md</a>';
}

function difficulty_func() {
	requestUrl.value = baseUrl + "/difficulty";
	requestMethod.value = "GET";
	requestParamsTable.innerHTML = "";
	apidescription.innerHTML = 'Difficulty 接口 <br><i>> 响应数据库Difficulty表 (难度评级信息) </i><br>请求方式: GET<br>参数: 无<br>请求头: 无<br><hr>详见: <a href="https://github.com/CherryAya/PhigrosDB-project-Database/blob/main/docs/Difficulty.md">https://github.com/CherryAya/PhigrosDB-project-Database/blob/main/docs/Difficulty.md</a>';
}

function test_func() {
	requestUrl.value = baseUrl + "/test";
	requestMethod.value = "GET";
	requestParamsTable.innerHTML = "";
	apidescription.innerHTML = 'Test 接口 <br><i>> 测试用接口 </i><br>请求方式: GET<br>参数: 无<br>请求头: 无<br><hr>详见: 无';
}

function default_func() {
	alert('绝对不是JavaScript的错！绝对不是!');
}

window.onload = function() {
	document.getElementById('controllerSelect').length = 0;
	document.getElementById('requestUrl').value = "";
	document.getElementById('requestMethod').value = "";
}

function paramstable_addList(object, values) {
	var list = document.createElement('ol');
	var list_node, inputElement;
	for	(i=0; i<values.length; i++) {
		list_node = document.createElement('li');
		inputElement = document.createElement('input');
		inputElement.type = 'text';
		inputElement.readOnly = 'readonly';
		inputElement.value = values[i];
		list_node.appendChild(inputElement)
		list.appendChild(list_node);
	}
	object.appendChild(list);
}