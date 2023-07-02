const ADMIN = QueryString('uid');
const COLLEGE = ['英语语言文化学院', '东方语言文化学院', '西方语言文化学院', '文化与旅游学院', '国际商学院', '中国语言文化学院', '教育学院', '艺术学院'];
const PROFESSION = [
    ['英语（师范）', '英语', '商务英语', '翻译'],
    ['日语', '阿拉伯语'],
    ['法语', '西班牙语'],
    ['旅游管理（全英教学）', '会展经济与管理'],
    ['计算机科学与技术', '跨境电子商务'],
    ['汉语言文学（师范）', '网络与新媒体'],
    ['小学教育（师范）', '科学教育（师范）'],
    ['音乐学（师范）', '艺术与科技']
];
$(function () {
    if (ADMIN) {
        $('#mStudent').click(function () {
            location.href = './mStudent?uid=' + ADMIN;
        });
        $('#mCourse').click(function () {
            location.href = './mCourse?uid=' + ADMIN;
        });
        $('#mType').click(function () {
            location.href = './mType?uid=' + ADMIN;
        });
        $('#mSelect').click(function () {
            location.href = './mSelect?uid=' + ADMIN;
        });
        $('#mRecord').click(function () {
            location.href = './mRecord?uid=' + ADMIN;
        });
        $('#mCenter').click(function () {
            location.href = './mCenter?uid=' + ADMIN;
        });

        $('#nStudent').click(function () {
            location.href = './nStudent?uid=' + ADMIN;
        });
        $('#nCourse').click(function () {
            location.href = './nCourse?uid=' + ADMIN;
        });
        $('#nType').click(function () {
            location.href = './nType?uid=' + ADMIN;
        });
        $('#nSelect').click(function () {
            location.href = './nSelect?uid=' + ADMIN;
        });
        $('#nCenter').click(function () {
            location.href = './nCenter?uid=' + ADMIN;
        });
    } else {
        location.replace('./index.html');
    }
    $('#userName').text('欢迎您！管理员' + ADMIN);
    $('#nuserName').text('欢迎您！' + ADMIN);
    $('#exit').click(function () {
        location.replace('./index.html');
    });
});

function QueryString(item) {
    var sValue = location.search.match(new RegExp("[\?\&]" + item + "=([^\&]*)(\&?)", "i"));
    return sValue ? sValue[1] : sValue;
}