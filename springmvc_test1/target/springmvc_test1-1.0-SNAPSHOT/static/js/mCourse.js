let CID = 0;
let global_college = '';
let college_index = 0;
let global_college2 = '';
let college_index2 = 0;
let pageNum = 1;
let pageTotal = 0;
$(function () {
    initData();
    addCollege(COLLEGE);
    addCollege2(COLLEGE);
    function addCollege(cList) {
        let str = '<option value="">请选择分院</option>';
        for (let i = 0; i < cList.length; i++) {
            str += '<option value=' + i + '>' + cList[i] + '</option>';
        }
        $("#sCollege").html(str);
    }
    function addCollege2(cList) {
        let str = '<option value="">请选择分院</option>';
        for (let i = 0; i < cList.length; i++) {
            str += '<option value=' + i + '>' + cList[i] + '</option>';
        }
        $("#sCollege2").html(str);
    }
    $('#sCollege').change(function () {
        college_index = $('#sCollege').val();
        if (college_index == '') {
            global_college = '';
            addPro(getPro(college_index));
        } else {
            global_college = COLLEGE[college_index];
            addPro(getPro(college_index));
        }
    });
    $('#sCollege2').change(function () {
        college_index2 = $('#sCollege2').val();
        if (college_index2 == '') {
            global_college2 = '';
            addPro2(getPro(college_index2));
        } else {
            global_college2 = COLLEGE[college_index2];
            addPro2(getPro(college_index2));
        }
    });
    $('#search').click(function () {
        let keyword = $("#keyword").val();
        console.log(keyword);
        $.ajax({
            type: "GET",
            url: "http://localhost:8080/selectCourseByName",
            data: {
                coursename: keyword
            },
            success: function (res) {
                init1(res);
            }
        });
    });
    $('#addCourse').click(function () {
        let cnum = $("#cnum").val();
        let cname = $("#cname").val();
        let capacity = $("#capacity").val();
        let college = global_college;
        let credit = $("#credit").val();
        let ctime = $("#ctime").val();
        if (cnum === '') {
            alert('课程号不能为空');
            return;
        }
        if (cname === '') {
            alert('课程名不能为空');
            return;
        }
        if (capacity === '') {
            alert('课程周次不能为空');
            return;
        }
        if (college === '') {
            alert('分院不能为空');
            return;
        }
        if (credit === '') {
            alert('学分不能为空');
            return;
        }
        if (ctime === '') {
            alert('上课时间不能为空');
            return;
        }
        $.ajax({
            type: "POST",
            url: "http://localhost:8080/insertCourse",
            data: {
                admin: ADMIN,
                courseid: cnum,
                coursename: cname,
                college: college,
                week: capacity,
                credit: credit,
                period: ctime,
            },
            success: function (res) {
                console.log(res);
                if (res === 0) {
                    alert('该排课已存在');
                    return;
                } else {
                    window.location.reload();
                }
            }
        });
    });
    $('#updateCourse').click(function () {
        let cnum = $("#cnum2").val();
        let cname = $("#cname2").val();
        let college = global_college2;
        let capacity = $("#capacity2").val();
        let credit = $("#credit2").val();
        let ctime = $("#ctime2").val();
        if (cnum == '') {
            alert('课程号不能为空');
            return;
        }
        if (cname == '') {
            alert('课程名不能为空');
            return;
        }

        if (college === '') {
            alert('开设学院不能为空');
            return;
        }
        if (capacity == '') {
            alert('上课周次不能为空');
            return;
        }
        if (credit == '') {
            alert('学分不能为空');
            return;
        }
        if (ctime == '') {
            alert('学时不能为空');
            return;
        }
        console.log(cnum," ",cname," ",college," ",capacity," ",credit," ",ctime," ");
        $.ajax({
            type: "POST",
            url: "http://localhost:8080/updateCourse",
            data: {
                admin: ADMIN,
                // cid: CID,
                courseid: cnum,
                coursename: cname,
                college: college,
                week: capacity,
                credit: credit,
                period: ctime,
            },
            success: function (res) {
                window.location.reload();
            }
        });
    });
    $('#exportCourse').click(function () {
        window.location.href = 'http://localhost:8080/exportCourse';
    });
    $('#importCourse').click(function () {
        let formData = new FormData();
        //上传文件的名称
        let name = $("#xlsfile").val();
        //数据
        formData.append("xlsfile", $("#xlsfile")[0].files[0]);
        formData.append("name", name);
        $.ajax({
            type: "post",
            url: "http://localhost:8080/importCourse",
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
    //     url: "http://localhost:8080/getAllCourse",
    //     success: function (res) {
    //         console.log(res);
    //         init(res);
    //     }
    // })
    $.ajax({
        type: "get",
        url: "http://localhost:8080/getAllCourse/"+pageNum.toString(),
        success: function (res) {
            console.log("getAllCourse:",res)
            init(res);
        }

    });
}
function init1(res) {
    $('#tableBody').empty();
    $('#tableNav').empty();
    let str = '';
    for (let i = 0; i < res.length; i++) {
        // let objTime = JSON.parse(res[i].ctime);
        // let strTime = '';
        // for (let j = 0; j < objTime.length; j++) {
        //     strTime += objTime[j].classroom + ':';
        //     strTime += '星期' + objTime[j].week + ',';
        //     strTime += '第' + objTime[j].cstart + '节课,';
        //     strTime += objTime[j].time + '课时 ';
        // }
        let row = '<div class="row">';
        let cnum = '<div class="col-xs-1 " style="width: 10%">' + res[i].courseid + '</div>';
        let cname = '<div class="col-xs-1 " style="width: 20%">' + res[i].coursename + '</div>';
        let teacher = '<div class="col-xs-1 " style="width: 20%">' + res[i].college + '</div>';
        let ctime = '<div class="col-xs-6 " style="width: 12%">' + res[i].week + '</div>';
        let credit = '<div class="col-xs-1 " style="width: 7%">' + res[i].credit + '</div>';
        let capacity = '<div class="col-xs-1 " style="width: 10%">' + res[i].period + '</div>';
        let op = '<div class="col-xs-1" style="width: 14%"><button class="btn btn-success btn-xs" data-toggle="modal" data-target="#updateCourseModel" onclick="update(' + res[i].courseid + ')">修改</button><button class="btn btn-danger btn-xs" onclick="deleteCourse(' + res[i].courseid + ')">删除</button></div>';
        row = row + cnum + cname + teacher + ctime + credit + capacity + op + '</div>';
        str += row;
    }
    $('#tableBody').append(str);
}
function init(res) {
    // $('#tableBody').empty();
    // let str = '';
    // for (let i = 0; i < res.length; i++) {
    //     // let objTime = JSON.parse(res[i].ctime);
    //     // let strTime = '';
    //     // for (let j = 0; j < objTime.length; j++) {
    //     //     strTime += objTime[j].classroom + ':';
    //     //     strTime += '星期' + objTime[j].week + ',';
    //     //     strTime += '第' + objTime[j].cstart + '节课,';
    //     //     strTime += objTime[j].time + '课时 ';
    //     // }
    //     let row = '<div class="row">';
    //     let cnum = '<div class="col-xs-1 " style="width: 10%">' + res[i].courseid + '</div>';
    //     let cname = '<div class="col-xs-1 " style="width: 20%">' + res[i].coursename + '</div>';
    //     let teacher = '<div class="col-xs-1 " style="width: 20%">' + res[i].college + '</div>';
    //     let ctime = '<div class="col-xs-6 " style="width: 12%">' + res[i].week + '</div>';
    //     let credit = '<div class="col-xs-1 " style="width: 7%">' + res[i].credit + '</div>';
    //     let capacity = '<div class="col-xs-1 " style="width: 10%">' + res[i].period + '</div>';
    //     let op = '<div class="col-xs-1" style="width: 14%"><button class="btn btn-success btn-xs" data-toggle="modal" data-target="#updateCourseModel" onclick="update(' + res[i].courseid + ')">修改</button><button class="btn btn-danger btn-xs" onclick="deleteCourse(' + res[i].courseid + ')">删除</button></div>';
    //     row = row + cnum + cname + teacher + ctime + credit + capacity + op + '</div>';
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
        let cnum = '<div class="col-xs-1 " style="width: 10%">' + page[i].courseid + '</div>';
        let cname = '<div class="col-xs-1 " style="width: 20%">' + page[i].coursename + '</div>';
        let teacher = '<div class="col-xs-1 " style="width: 20%">' + page[i].college + '</div>';
        let ctime = '<div class="col-xs-6 " style="width: 12%">' + page[i].week + '</div>';
        let credit = '<div class="col-xs-1 " style="width: 7%">' + page[i].credit + '</div>';
        let capacity = '<div class="col-xs-1 " style="width: 10%">' + page[i].period + '</div>';
        let op = '<div class="col-xs-1" style="width: 14%"><button class="btn btn-success btn-xs" data-toggle="modal" data-target="#updateCourseModel" onclick="update(' + page[i].courseid + ')">修改</button><button class="btn btn-danger btn-xs" onclick="deleteCourse(' + page[i].courseid + ')">删除</button></div>';
        row = row + cnum + cname + teacher + ctime + credit + capacity + op + '</div>';
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
function update(cid) {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/selectCourseById",
        data: {
            courseid: cid,
        },
        success: function (res) {
            // CID = res.cid;
            $("#cnum2").val(res[0].courseid);
            $("#cname2").val(res[0].coursename);
            $("#capacity2").val(res[0].week);
            global_college2 = res[0].college;
            $("#credit2").val(res[0].credit);
            $("#ctime2").val(res[0].period);
        }
    });
}

function deleteCourse(cid) {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/deleteCourseById",
        data: {
            admin: ADMIN,
            courseid: cid,
        },
        success: function (res) {
            if (res === 0) {
                alert('删除课程失败');
            } else {
                initData();
            }
        }
    });
}

function QueryString(item) {
    var sValue = location.search.match(new RegExp("[\?\&]" + item + "=([^\&]*)(\&?)", "i"));
    return sValue ? sValue[1] : sValue;
}