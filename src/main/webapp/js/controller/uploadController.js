app.controller('uploadController',function($scope,$location,uploadService1){
    $scope.upload1 = function(){
        // 调用uploadService的方法完成文件的上传
        uploadService1.upload1().success(function(response){
            if(response.flag){
                location.href="../../aimjava_war_exploded/search.html";
            }else{
                alert(response.message);
            }
        });
    }
});