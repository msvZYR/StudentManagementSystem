let status = false;
$(function () {
    initData();
    $('#changePwd').click(function () {
        let pwd1 = $("#newPwd").val();
        let pwd2 = $("#newPwd2").val();
        if (pwd1 === '') {
            alert('新密码不能为空');
            return;
        }
        if (pwd2 === '') {
            alert('新密码不能为空');
            return;
        }
        if (pwd1 !== pwd2) {
            alert('两次输入密码不同');
            return;
        }
        $.ajax({
            type: "POST",
            url: "http://localhost:8080/resetPwd",
            data: {
                userid: ADMIN,
                password: pwd1,
            },
            success: function (res) {
                window.location.replace('./');
            }
        });
    });

})

function initData() {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/getOneUser",
        data: {
            "userid": ADMIN,
        },
        success: function (res) {
            console.log(res);
            init(res);
        }
    })
}

function init(res) {
    $('#tableBody').empty();
    let str = '';
    let row = '<div class="row">';
    let uid = '<div class="col-xs-4 ">' + res[0].userid + '</div>';
    let uname = '<div class="col-xs-4 ">' + res[0].username + '</div>';
    let op = '<div class="col-xs-4"><button class="btn btn-success btn-xs" data-toggle="modal" data-target="#changePwdModel">修改密码</button></div>';
    row = row + uid + uname + op + '</div>';
    str += row;

    $('#tableBody').append(str);
}