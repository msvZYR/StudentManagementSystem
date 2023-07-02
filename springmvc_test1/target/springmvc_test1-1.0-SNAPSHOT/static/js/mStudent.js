let global_college = '';
let college_index = 0;
let global_profession = '';
let global_college2 = '';
let college_index2 = 0;
let global_profession2 = '';
let pageNum = 1;
let pageTotal = 0;
$(function () {
    initData();
    addCollege(COLLEGE);
    addCollege2(COLLEGE);
    $('#search').click(function () {
        let keyword = $("#keyword").val();
        console.log(keyword);
        $.ajax({
            type: "GET",
            url: "http://localhost:8080/selectStudentByName",
            data: {
                stuname: keyword
            },
            success: function (res) {
                console.log(res)
                init1(res);
            }
        });
    });
    $('#search1').click(function () {
        let keyword1 = $("#keyword1").val();
        console.log(keyword1);
        $.ajax({
            type: "GET",
            url: "http://localhost:8080/selectStudentByClazz",
            data: {
                clazzname: keyword1
            },
            success: function (res) {
                console.log(res)
                init1(res);
            }
        });
    });
    $('#sCollege').change(function () {
        college_index = $('#sCollege').val();
        if (college_index == '') {
            global_college = '';
            global_profession = '';
            addPro(getPro(college_index));
        } else {
            global_college = COLLEGE[college_index];
            addPro(getPro(college_index));
        }
        $('#sPro').selectpicker('refresh');
    });
    $('#sPro').change(function () {
        let index = $('#sPro').val();
        if (index == '') {
            global_profession = '';
        } else {
            global_profession = PROFESSION[college_index][index];
        }
    });
    $('#sCollege2').change(function () {
        college_index2 = $('#sCollege2').val();
        if (college_index2 == '') {
            global_college2 = '';
            global_profession2 = '';
            addPro2(getPro(college_index2));
        } else {
            global_college2 = COLLEGE[college_index2];
            addPro2(getPro(college_index2));
        }
        $('#sPro2').selectpicker('refresh');
    });
    $('#sPro2').change(function () {
        let index = $('#sPro2').val();
        if (index == '') {
            global_profession2 = '';
        } else {
            global_profession2 = PROFESSION[college_index2][index];
        }
    });
    $('#addStudent').click(function () {
        let userId = $("#uid").val();
        let userName = $("#uname").val();
        let college = global_college;
        let profession = global_profession;
        let stuClass = $("#stuClass").val();
        if (userId === '') {
            alert('学生学号不能为空');
            return;
        }
        if (userName === '') {
            alert('学生姓名不能为空');
            return;
        }
        if (college === '') {
            alert('学生分院不能为空');
            return;
        }
        if (profession === '') {
            alert('学生专业不能为空');
            return;
        }
        if (stuClass === '') {
            alert('学生班级不能为空');
            return;
        }
        console.log(userId, userName, college, profession, stuClass);
        $.ajax({
            type: "post",
            url: "http://localhost:8080/insertStudent",
            data: {
                admin: ADMIN,
                stuid: userId,
                stuname: userName,
                college: college,
                profession: profession,
                clazzname: stuClass,
            },
            success: function (res) {
                console.log(res);
                if (res === 0) {
                    alert('该学生已存在');
                    return;
                } else {
                    console.log(userId," ",userName," ",college," ",profession," ", stuClass," ");
                    window.location.reload();

                }
            }
        });
    });
    $('#updateStudent').click(function () {
        let userId = $("#uid2").val();
        let userName = $("#uname2").val();
        let college = global_college2;
        let profession = global_profession2;
        let stuClass = $("#stuClass2").val();
        console.log(college, profession);
        if (userId === '') {
            alert('学生学号不能为空');
            return;
        }
        if (userName === '') {
            alert('学生姓名不能为空');
            return;
        }
        if (college === '') {
            alert('学生分院不能为空');
            return;
        }
        if (profession === '') {
            alert('学生专业不能为空');
            return;
        }
        if (stuClass === '') {
            alert('学生班级不能为空');
            return;
        }
        console.log(userId, userName, college, profession, stuClass);
        $.ajax({
            type: "post",
            url: "http://localhost:8080/updateStudent",
            data: {
                admin: ADMIN,
                stuid: userId,
                stuname: userName,
                college: college,
                profession: profession,
                clazzname: stuClass,
            },
            success: function (res) {
                console.log("updateStudent===",res)
                window.location.reload();
            }
        });
    });
    $('#close').click(function () {
        window.location.reload();
    });
    $('#close2').click(function () {
        window.location.reload();
    });
    $('#exportStu').click(function () {
        window.location.href = 'http://localhost:8080/exportStu';
    });
    $('#importStu').click(function () {
        let formData = new FormData();
        //上传文件的名称
        let name = $("#xlsfile").val();
        //数据
        formData.append("xlsfile", $("#xlsfile")[0].files[0]);
        formData.append("name", name);
        $.ajax({
            type: "post",
            url: "http://localhost:8080/importStu",
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

});

function initData() {
    // $.ajax({
    //     type: "get",
    //     url: "http://localhost:8080/getAllStudent",
    //
    //     success: function (res) {
    //         console.log(res)
    //         init(res);
    //     },
    //     error:function (error){
    //         console.log("mStudent_initData:error==="+error)
    //     }
    //
    // });
    $.ajax({
        type: "get",
        url: "http://localhost:8080/getAllStudent/"+pageNum.toString(),
        success: function (res) {
            console.log("getStudentPage",res)
            init(res);
        },
        error:function (error){
            console.log("mStudent_initData:error==="+error)
        }

    });
}

function addCollege(cList) {
    let str = '<option value="">请选择分院</option>';
    for (let i = 0; i < cList.length; i++) {
        str += '<option value=' + i + '>' + cList[i] + '</option>';
    }
    $("#sCollege").html(str);
}

function addPro(pList) {
    let str = '<option value="">请选择专业</option>';
    if (college_index == '') {
        $("#sPro").html(str);
    } else {
        for (let i = 0; i < pList.length; i++) {
            str += '<option value="' + i + '">' + pList[i] + '</option>';
        }
        $("#sPro").html(str);
    }
}

function addCollege2(cList) {
    let str = '<option value="">请选择分院</option>';
    for (let i = 0; i < cList.length; i++) {
        str += '<option value=' + i + '>' + cList[i] + '</option>';
    }
    $("#sCollege2").html(str);
}

function addPro2(pList) {
    let str = '<option value="">请选择专业</option>';
    if (college_index2 == '') {
        $("#sPro2").html(str);
    } else {
        for (let i = 0; i < pList.length; i++) {
            str += '<option value="' + i + '">' + pList[i] + '</option>';
        }
        $("#sPro2").html(str);
    }
}

function getPro(index) {
    return PROFESSION[index];
}
function init1(res) {
    $('#tableBody').empty();
    $('#tableNav').empty();
    console.log("init===",res)
    let str = '';
    for (let i = 0; i < res.length; i++) {
        let row = '<div class="row">';
        let uid = '<div class="col-xs-2 ">' + res[i].stuid + '</div>';
        let uname = '<div class="col-xs-2 ">' + res[i].stuname + '</div>';
        let college = '<div class="col-xs-2 ">' + res[i].college + '</div>';
        let profession = '<div class="col-xs-2 ">' + res[i].profession + '</div>';
        let stuClass = '<div class="col-xs-2 ">' + res[i].clazzname + '</div>';
        let op = '<div class="col-xs-2"><button class="btn btn-success btn-xs" data-toggle="modal" data-target="#updateUserModel" onclick="updateUser(' + res[i].stuid + ')">修改</button><button class="btn btn-danger btn-xs" onclick="deleteUser(' + res[i].stuid + ')">删除</button></div>';
        row = row + uid + uname + college + profession + stuClass + op + '</div>';
        str += row;
    }
    $('#tableBody').append(str);
}
function init(res) {
    // $('#tableBody').empty();
    // console.log("init===",res)
    // let str = '';
    // for (let i = 0; i < res.length; i++) {
    //     let row = '<div class="row">';
    //     let uid = '<div class="col-xs-2 ">' + res[i].stuid + '</div>';
    //     let uname = '<div class="col-xs-2 ">' + res[i].stuname + '</div>';
    //     let college = '<div class="col-xs-2 ">' + res[i].college + '</div>';
    //     let profession = '<div class="col-xs-2 ">' + res[i].profession + '</div>';
    //     let stuClass = '<div class="col-xs-2 ">' + res[i].clazzname + '</div>';
    //     let op = '<div class="col-xs-2"><button class="btn btn-success btn-xs" data-toggle="modal" data-target="#updateUserModel" onclick="updateUser(' + res[i].stuid + ')">修改</button><button class="btn btn-danger btn-xs" onclick="deleteUser(' + res[i].stuid + ')">删除</button></div>';
    //     row = row + uid + uname + college + profession + stuClass + op + '</div>';
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
        let uid = '<div class="col-xs-2 ">' + page[i].stuid + '</div>';
        let uname = '<div class="col-xs-2 ">' + page[i].stuname + '</div>';
        let college = '<div class="col-xs-2 ">' + page[i].college + '</div>';
        let profession = '<div class="col-xs-2 ">' + page[i].profession + '</div>';
        let stuClass = '<div class="col-xs-2 ">' + page[i].clazzname + '</div>';
        let op = '<div class="col-xs-2"><button class="btn btn-success btn-xs" data-toggle="modal" data-target="#updateUserModel" onclick="updateUser(' + page[i].stuid + ')">修改</button><button class="btn btn-danger btn-xs" onclick="deleteUser(' + page[i].stuid + ')">删除</button></div>';
        row = row + uid + uname + college + profession + stuClass + op + '</div>';
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
function updateUser(stuid) {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/selectStudentById",
        data: {
            stuid: stuid,
        },
        success: function (res) {
            console.log("selectStudentById",res);
            $("#uid2").val(res[0].stuid);
            $("#uname2").val(res[0].stuname);
            global_college2 = res[0].college;
            global_profession2 = res[0].profession;
            $("#stuClass2").val(res[0].clazzname);
        }
    });
}

function deleteUser(stuid) {
    $.ajax({
        type: "get",
        url: "http://localhost:8080/deleteStudentById",
        data: {
            admin: ADMIN,
            stuid: stuid,
        },
        success: function (res) {
            if (res === 0) {
                alert('删除学生失败');
            } else {
                initData();
            }
        }
    });
}