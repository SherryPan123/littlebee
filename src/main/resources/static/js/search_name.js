var usersTbody = null;
var userTrSample = null;

$(document).ready(function(e) {
    usersTbody = $("#users");
    userTrSample = $("#userTrSample");
    refreshNo();
});

//delay method
var delay = (function(){
    var timer = 0;
    return function(callback, ms){
        clearTimeout (timer);
        timer = setTimeout(callback, ms);
    };
})();

//get user id and set tr
var getUserInJson = function(userIndex,userTr){
    var userNumber = userTr.find("input.userId");
    var username = userTr.find("td.username");
    $.ajax({
        url:"/user/getUserInJson",
        //async:false,
        data:{"userNumber":userIndex},
        success:function(returnData){
            userNumber.val(returnData.userNumber);
            username.html(returnData.username);
        },
        error:function() {
            userNumber.val("");
            username.html("Error:NO User Found!");
        },
        dataType:"json"
    });
};

//bing key up event to input.userIndex
$(document).on("keyup","input.userIndex",
    function(){
        var userIndex = $(this);
        var userTr = userIndex.parent().parent();
        delay(
            function(){
                getUserInJson(userIndex.val(),userTr);
            }, 500
        );
    }
);

// refresh user No
function refreshNo(){
    var userTrs = usersTbody.children();
    $.each(userTrs,function(i,item){
        $(item).find(".NO").html(i+1);
    });
}

//delete user
function deleteUser(img){
    $(img).parent().parent().remove();
    refreshNo();
}

//add user
function addUser(){
    usersTbody.append(userTrSample.clone());
    refreshNo();
}