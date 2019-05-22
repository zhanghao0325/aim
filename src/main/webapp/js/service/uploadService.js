app.service('uploadService1',function($http){

	this.upload1 = function(){
		// 向后台传递数据:
		var formData = new FormData();
		// 向formData中添加数据:
		var file = document.getElementById("file").files[0];
		formData.append("file",file);

		return $http({
			method:'post',
			url:'../aimjava_war_exploded/upload/uploadFile',
			data:formData,
			headers:{'Content-Type':undefined} ,// Content-Type : text/html  text/plain
			transformRequest: angular.identity
		});
	}
});