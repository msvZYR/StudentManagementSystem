let KID = 0;
let pageNum = 1;
let pageTotal = 0;
$(function () {
    initData();
    $('#search').click(function () {
        let keyword = $("#keyword").val();
        console.log(keyword);
        $.ajax({
            type: "GET",
            url: "http://localhost:8080/selectClassByName",
            data: {
                clazzname: keyword
            },
            success: function (res) {
                init1(res);
            }
        });
    });
    $('#addType').click(function () {
        let pname = $("#pname").val();
        let cnum = $("#cnum").val();
        let type = $("#type").val();
        if (pname === '') {
            alert('班级id不能为空');
            return;
        }
        if (cnum === '') {
            alert('班级名不能为空');
            return;
        }
        if (type === '') {
            alert('班主任姓名不能为空');
            return;
        }
        $.ajax({
            type: "POST",
            url: "http://localhost:8080/insertClass",
            data: {
                admin:ADMIN,
                clazzname: pname,
                clazzid: cnum,
                clazztname: type,
            },
            success: function (res) {
                console.log(res);
                if (res === 0) {
                    alert('该班级已存在');
                    return;
                } else {
                    window.location.reload();
                }
            }
        });
    });
    $('#updateType').click(function () {
        let pname = $("#pname2").val();
        let cnum = $("#cnum2").val();
        let type = $("#type2").val();
        if (pname === '') {
            alert('专业名不能为空');
            return;
        }
        if (cnum === '') {
            alert('课程号不能为空');
            return;
        }
        if (type === '') {
            alert('课程类型不能为空');
            return;
        }
        $.ajax({
            type: "POST",
            url: "http://localhost:8080/updateClass",
            data: {
                // kid: KID,
                admin:ADMIN,
                clazzname: pname,
                clazzid: cnum,
                clazztname: type,
            },
            success: function (res) {
                console.log(res);
                if (res === 0) {
                    alert('该班级不存在');
                    return;
                } else {
                    window.location.reload();
                }
            }
        });
    });
    $('#exportClazz').click(function () {
        window.location.href = 'http://localhost:8080/exportClazz';
    });
    $('#importClazz').click(function () {
        let formData = new FormData();
        //上传文件的名称
        let name = $("#xlsfile").val();
        //数据
        formData.append("xlsfile", $("#xlsfile")[0].files[0]);
        formData.append("name", name);
        $.ajax({
            type: "post",
            url: "http://localhost:8080/importClazz",
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
    //     type: "get",
    //     url: "http://localhost:8080/getAllClass",
    //     success: function (res) {
    //         console.log(res);
    //         init(res);
    //     }
    // })
    $.ajax({
        type: "get",
        url: "http://localhost:8080/getAllClass/"+pageNum.toString(),
        success: function (res) {
            // console.log("getStudentPage",res)
            init(res);
        },
        error:function (error){
            console.log("mStudent_initData:error==="+error)
        }

    });
}
function init1(res) {
    $('#tableBody').empty();
    $('#tableNav').empty();
    let str = '';
    for (let i = 0; i < res.length; i++) {
        let row = '<div class="row">';
        let cnum = '<div class="col-xs-3 ">' + res[i].clazzid + '</div>';
        let pname = '<div class="col-xs-3 ">' + res[i].clazzname + '</div>';
        let type = '<div class="col-xs-3 ">' + res[i].clazztname + '</div>';
        let op = '<div class="col-xs-3"><button class="btn btn-success btn-xs" data-toggle="modal" data-target="#updateTypeModel" onclick="update(' + res[i].clazzid + ')">修改</button><button class="btn btn-danger btn-xs" onclick="deleteType(' + res[i].clazzid + ')">删除</button></div>';
        row = row + cnum + pname + type + op + '</div>';
        str += row;
    }
    $('#tableBody').append(str);
}
function init(res) {
    // $('#tableBody').empty();
    // let str = '';
    // for (let i = 0; i < res.length; i++) {
    //     let row = '<div class="row">';
    //     let cnum = '<div class="col-xs-3 ">' + res[i].clazzid + '</div>';
    //     let pname = '<div class="col-xs-3 ">' + res[i].clazzname + '</div>';
    //     let type = '<div class="col-xs-3 ">' + res[i].clazztname + '</div>';
    //     let op = '<div class="col-xs-3"><button class="btn btn-success btn-xs" data-toggle="modal" data-target="#updateTypeModel" onclick="update(' + res[i].clazzid + ')">修改</button><button class="btn btn-danger btn-xs" onclick="deleteType(' + res[i].clazzid + ')">删除</button></div>';
    //     row = row + cnum + pname + type + op + '</div>';
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
        let cnum = '<div class="col-xs-3 ">' + page[i].clazzid + '</div>';
        let pname = '<div class="col-xs-3 ">' + page[i].clazzname + '</div>';
        let type = '<div class="col-xs-3 ">' + page[i].clazztname + '</div>';
        let op = '<div class="col-xs-3"><button class="btn btn-success btn-xs" data-toggle="modal" data-target="#updateTypeModel" onclick="update(' + page[i].clazzid + ')">修改</button><button class="btn btn-danger btn-xs" onclick="deleteType(' + page[i].clazzid + ')">删除</button></div>';
        row = row + cnum + pname + type + op + '</div>';
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
function update(kid) {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/selectClassById",
        data: {
            clazzid: kid,
        },
        success: function (res) {
            // KID = res.kid;
            $("#pname2").val(res[0].clazzname);
            $("#cnum2").val(res[0].clazzid);
            $("#type2").val(res[0].clazztname);
        }
    });
}

function deleteType(kid) {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/deleteClassById",
        data: {
            admin:ADMIN,
            clazzid: kid,
        },
        success: function (res) {
            if (res === 0) {
                alert('删除班级失败');
            } else {
                initData();
            }
        }
    });
}