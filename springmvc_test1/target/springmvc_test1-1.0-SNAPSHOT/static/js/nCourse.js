let pageNum = 1;
let pageTotal = 0;
$(function () {
    initData();
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

    $('#exportCourse').click(function () {
        window.location.href = 'http://localhost:8080/exportCourse';
    });

})

function initData() {
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
        row = row + cnum + cname + teacher + ctime + credit + capacity + '</div>';
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
        let cnum = '<div class="col-xs-1 " style="width: 10%">' + page[i].courseid + '</div>';
        let cname = '<div class="col-xs-1 " style="width: 20%">' + page[i].coursename + '</div>';
        let teacher = '<div class="col-xs-1 " style="width: 20%">' + page[i].college + '</div>';
        let ctime = '<div class="col-xs-6 " style="width: 12%">' + page[i].week + '</div>';
        let credit = '<div class="col-xs-1 " style="width: 7%">' + page[i].credit + '</div>';
        let capacity = '<div class="col-xs-1 " style="width: 10%">' + page[i].period + '</div>';
        row = row + cnum + cname + teacher + ctime + credit + capacity  + '</div>';
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
