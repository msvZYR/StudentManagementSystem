let status = false;
$(function () {
    initData();
    $('#search').click(function () {
        let keyword = $("#keyword").val();
        console.log(keyword);
        $.ajax({
            type: "GET",
            url: "http://localhost:8080/getOneUser",
            data: {
                userid: keyword
            },
            success: function (res) {
                console.log(res)
                init(res);
            }
        });
    });
    $('#start').click(function () {
        if (status) {
            alert('选课已开始');
        } else {
            setFlag();
        }
    });
    $('#end').click(function () {
        if (!status) {
            alert('选课已结束');
        } else {
            setFlag();
        }
    });
})

// function initData() {
//     $.ajax({
//         type: "GET",
//         url: "http://localhost:8080/getOneUser",
//         data: {
//             "userid": ADMIN,
//         },
//         success: function (res) {
//             console.log(res);
//             init(res);
//         }
//     })
//     getFlag();
// }
function initData() {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/getAllUser",
        success: function (res) {
            console.log(res);
            init(res);
        }
    })
    getFlag();
}
function init(res) {
    $('#tableBody').empty();
    let str = '';
    for (let i = 0; i < res.length; i++) {
        let row = '<div class="row">';
        let uid = '<div class="col-xs-4 ">' + res[i].userid + '</div>';
        let uname = '<div class="col-xs-4 ">' + res[i].username + '</div>';
        let op = '<div class="col-xs-4"><button class="btn btn-success btn-xs" data-toggle="modal" data-target="#changePwdModel" onclick="resetPwd(' + res[i].userid + ')">重置密码</button></div>';
        row = row + uid + uname + op + '</div>';
        str += row;
    }
    $('#tableBody').append(str);
}
function resetPwd(uid) {
    alert('确定对该用户进行重置密码操作吗？');
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/resetPwdByManager",
        data: {
            userid: uid
        },
        success: function (res) {
            alert('密码重置成功！');
        }
    })
}
function getFlag() {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/getFlag",
        success: function (res) {
            console.log(res);
            status = res;
            if (status) {
                $('#status').text('能');
            } else {
                $('#status').text('否');
            }
        }
    })
}

function setFlag() {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/setFlag",
        success: function (res) {
            console.log(res);
            status = res;
            if (status) {
                $('#status').text('能');
            } else {
                $('#status').text('否');
            }
        }
    })
}