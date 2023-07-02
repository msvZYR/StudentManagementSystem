let SID = 0;
let pageNum = 1;
let pageTotal = 0;
$(function () {
    initData();
    $('#search').click(function () {
        let keyword = $("#keyword").val();
        if (keyword == '') {
            window.location.reload();
        }
        $.ajax({
            type: "GET",
            url: "http://localhost:8080/selectScheduleByClazzName",
            data: {
                clazzname: keyword
            },
            success: function (res) {
                init1(res);
            }
        });
    });
    $('#addSelect').click(function () {
        let course = $("#course").val();
        let clazz = $("#clazz").val();
        let teacher = $("#teacher").val();
        let room = $("#room").val();

        if (course === '') {
            alert('课程名称不能为空');
            return;
        }
        if (clazz === '') {
            alert('班级名称不能为空');
            return;
        }
        if (teacher === '') {
            alert('教师姓名不能为空');
            return;
        }
        if (room === '') {
            alert('教室不能为空');
            return;
        }
        console.log(course," ",clazz," ",teacher," ",room," ",)
        $.ajax({
            type: "POST",
            url: "http://localhost:8080/insertSchedule",
            data: {
                admin:ADMIN,
                coursename: course,
                clazzname: clazz,
                tname: teacher,
                roomname: room,
            },
            success: function (res) {
                console.log(res);
                if (res === 0) {
                    alert('该课程已存在');
                    return;
                } else {
                    window.location.reload();
                }
            }
        });
    });
    $('#updateSelect').click(function () {
        let schedule = $("#scheduleid2").val();
        let course = $("#course2").val();
        let clazz = $("#clazz2").val();
        let teacher = $("#teacher2").val();
        let room = $("#room2").val();
        if (course === '') {
            alert('课程名称不能为空');
            return;
        }
        if (clazz === '') {
            alert('班级名称不能为空');
            return;
        }
        if (teacher === '') {
            alert('教师姓名不能为空');
            return;
        }
        if (room === '') {
            alert('教室不能为空');
            return;
        }
        $.ajax({
            type: "POST",
            url: "http://localhost:8080/updateSchedule",
            data: {
                // sid: SID,
                admin:ADMIN,
                scheduleid:schedule,
                coursename: course,
                clazzname: clazz,
                tname: teacher,
                roomname: room,
            },
            success: function (res) {
                window.location.reload();
            }
        });
    });
    $('#exportSchedule').click(function () {
        window.location.href = 'http://localhost:8080/exportSchedule';
    });
    $('#importSchedule').click(function () {
        let formData = new FormData();
        //上传文件的名称
        let name = $("#xlsfile").val();
        //数据
        formData.append("xlsfile", $("#xlsfile")[0].files[0]);
        formData.append("name", name);
        $.ajax({
            type: "post",
            url: "http://localhost:8080/importSchedule",
            data:formData,
            async: false,
            cache: false,
            contentType: false,
            processData: false,
            success: function (res) {
                console.log(res);
                window.location.reload();
            },
            fail: function (e) {
                console.log(e);
                window.location.reload();
            }
        });
    });
})

function initData() {
    // $.ajax({
    //     type: "GET",
    //     url: "http://localhost:8080/getAllSchedule",
    //     success: function (res) {
    //         console.log(res);
    //         init(res);
    //     }
    // })
    $.ajax({
        type: "get",
        url: "http://localhost:8080/getAllSchedule/"+pageNum.toString(),
        success: function (res) {
            init(res);
        }
    });
}
function init1(res) {
    $('#tableBody').empty();
    $('#tableNav').empty();
    let str = '';
    for (let i = 0; i < res.length; i++) {
        let row = '<div class="row">';
        let sdate = '<div class="col-xs-2 " style="width: 10%">' + res[i].scheduleid + '</div>';
        let uid = '<div class="col-xs-2 "style="width: 20%">' + res[i].coursename + '</div>';
        let uname = '<div class="col-xs-2 ">' + res[i].clazzname + '</div>';
        let cid = '<div class="col-xs-2 ">' + res[i].tname + '</div>';
        let cname = '<div class="col-xs-2 ">' + res[i].roomname + '</div>';
        let op = '<div class="col-xs-2"><button class="btn btn-success btn-xs" data-toggle="modal" data-target="#updateSelectModel" onclick="update(' + res[i].scheduleid + ')">修改</button><button class="btn btn-danger btn-xs" onclick="deleteSelect(' + res[i].scheduleid + ')">删除</button></div>';
        row = row + sdate + uid + uname + cid + cname + op + '</div>';
        str += row;
    }
    $('#tableBody').append(str);
}
function init(res) {
    // $('#tableBody').empty();
    // let str = '';
    // for (let i = 0; i < res.length; i++) {
    //     let row = '<div class="row">';
    //     let sdate = '<div class="col-xs-2 " style="width: 10%">' + res[i].scheduleid + '</div>';
    //     let uid = '<div class="col-xs-2 "style="width: 20%">' + res[i].coursename + '</div>';
    //     let uname = '<div class="col-xs-2 ">' + res[i].clazzname + '</div>';
    //     let cid = '<div class="col-xs-2 ">' + res[i].tname + '</div>';
    //     let cname = '<div class="col-xs-2 ">' + res[i].roomname + '</div>';
    //     let op = '<div class="col-xs-2"><button class="btn btn-success btn-xs" data-toggle="modal" data-target="#updateSelectModel" onclick="update(' + res[i].scheduleid + ')">修改</button><button class="btn btn-danger btn-xs" onclick="deleteSelect(' + res[i].scheduleid + ')">删除</button></div>';
    //     row = row + sdate + uid + uname + cid + cname + op + '</div>';
    //     str += row;
    // }
    // $('#tableBody').append(str);
    pageTotal=res.pages;
    $('#tableBody').empty();
    let page=res.list;
    console.log("page",page);
    let str = '';
    for (let i = 0; i < page.length; i++) {
        let row = '<div class="row">';
        let sdate = '<div class="col-xs-2 " style="width: 10%">' + page[i].scheduleid + '</div>';
        let uid = '<div class="col-xs-2 "style="width: 20%">' + page[i].coursename + '</div>';
        let uname = '<div class="col-xs-2 ">' + page[i].clazzname + '</div>';
        let cid = '<div class="col-xs-2 ">' + page[i].tname + '</div>';
        let cname = '<div class="col-xs-2 ">' + page[i].roomname + '</div>';
        let op = '<div class="col-xs-2"><button class="btn btn-success btn-xs" data-toggle="modal" data-target="#updateSelectModel" onclick="update(' + page[i].scheduleid + ')">修改</button><button class="btn btn-danger btn-xs" onclick="deleteSelect(' + page[i].scheduleid + ')">删除</button></div>';
        row = row + sdate + uid + uname + cid + cname + op + '</div>';
        str += row;
    }
    $('#tableBody').append(str);

    $('#tableNav').empty();
    let str1 = '';
    let el5 = '';
    let el1='<a onclick="toFirst()" style="padding-right: 10px;cursor:pointer; text-decoration: none;">首页</a>';
    let el2='<a onclick="toPre()" style="padding-right: 20px;cursor:pointer; text-decoration: none">上一页</a>';
    for (let i=0;i<res.navigatepageNums.length;i++){
        el5+='<a onclick="toPage('  +res.navigatepageNums[i]+  ')" style="display:inline-block;width: 15px;cursor:pointer; text-decoration: none">'+res.navigatepageNums[i]+'</a>'
    }
    let el3='<a onclick="toNext()" style="padding-left: 20px;cursor:pointer; text-decoration: none">下一页</a>';
    let el4='<a onclick="toLast()" style="padding-left: 10px;cursor:pointer; text-decoration: none">末页</a>';
    str1=el1+el2+el5+el3+el4;
    $('#tableNav').append(str1);
}
function toFirst(data){
    console.log("data",data)
    pageNum=1;
    initData();
}
function toPre(){
    if(pageNum>1){
        pageNum--;
        initData();
    }
}
function toNext(){
    if(pageNum<pageTotal){
        pageNum++;
        initData();
    }
}
function toLast(){
    pageNum=pageTotal;
    initData();
}
function toPage(data){
    console.log("data:",data)
    pageNum=data;
    initData();
}

function update(sid) {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/selectScheduleByScheduleId",
        data: {
            scheduleid: sid,
        },
        success: function (res) {
            // CID = res.sid;
            $("#scheduleid2").val(res[0].scheduleid);
            $("#course2").val(res[0].coursename);
            $("#clazz2").val(res[0].clazzname);
            $("#teacher2").val(res[0].tname);
            $("#room2").val(res[0].roomname);
        }
    });
}

function deleteSelect(sid) {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/deleteScheduleById",
        data: {
            admin:ADMIN,
            scheduleid: sid,
        },
        success: function (res) {
            if (res === 1) {
                initData();
            } else {
                alert('删除课程失败');
            }
        }
    });
}