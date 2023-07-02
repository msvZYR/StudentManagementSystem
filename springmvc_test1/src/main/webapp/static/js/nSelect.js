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

    $('#exportSchedule').click(function () {
        window.location.href = 'http://localhost:8080/exportSchedule';
    });

})

function initData() {
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
        row = row + sdate + uid + uname + cid + cname  + '</div>';
        str += row;
    }
    $('#tableBody').append(str);
}
function init(res) {

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
        row = row + sdate + uid + uname + cid + cname  + '</div>';
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