app.controller('uploadController',function($scope,$location,uploadService1){
    $scope.upload = function(){
        // 调用uploadService的方法完成文件的上传
        uploadService1.upload().success(function(response){
            if(response.flag){
                location.href="../../search.html";
            }else{
                alert(response.message);
            }
        });
    }
}