$(function () {
    $('#login').click(function () {
        let uid = $("#uid").val();
        let pwd = $("#pwd").val();
        if (uid == '') {
            alert('请输入账号');
            return;
        }
        if (pwd == '') {
            alert('请输入密码');
            return;
        }
        $.ajax({
            type: "get",
            url: "http://localhost:8080/login",
            data: {
                "userid": uid,
                "password": pwd
            },

            success: function (res) {
                console.log("myres" + res);
                if (res === 1) {
                    alert('该账号为管理员账号');
                    window.location.replace('/mStudent?uid=' + uid);
                } else if (res === 2) {
                    alert('该账号为学生账号');
                    window.location.replace('/nStudent?uid=' + uid);
                } else if (res === 3) {
                    alert('账号不存在');
                } else if (res === 0) {
                    alert('密码错误');
                }
            }
        })
    });
})