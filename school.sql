/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 80018
Source Host           : localhost:3306
Source Database       : school

Target Server Type    : MYSQL
Target Server Version : 80018
File Encoding         : 65001

Date: 2023-06-18 15:40:06
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_class
-- ----------------------------
DROP TABLE IF EXISTS `t_class`;
CREATE TABLE `t_class` (
  `clazzid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `clazzname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `clazztname` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_class
-- ----------------------------
INSERT INTO `t_class` VALUES ('2602', '26计算机2班', '周左');
INSERT INTO `t_class` VALUES ('2101', '21计算机1班', '马煜涵');
INSERT INTO `t_class` VALUES ('2201', '22计算机1班', '张世伟');
INSERT INTO `t_class` VALUES ('2203', '22计算机3班', '赵力辉');
INSERT INTO `t_class` VALUES ('2204', '22计算机4班', '孔雨洁');
INSERT INTO `t_class` VALUES ('2001', '20计算机1班', '娄颖');
INSERT INTO `t_class` VALUES ('2304', '23计算机4班', '朱莹榕');

-- ----------------------------
-- Table structure for t_course
-- ----------------------------
DROP TABLE IF EXISTS `t_course`;
CREATE TABLE `t_course` (
  `courseid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `coursename` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `college` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `week` varchar(255) DEFAULT NULL,
  `credit` int(255) DEFAULT NULL,
  `period` int(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_course
-- ----------------------------
INSERT INTO `t_course` VALUES ('3211', '操作系统', '国际商学院', '1~18周', '2', '21');
INSERT INTO `t_course` VALUES ('3827', 'javaweb开发框架实验', '国际商学院', '1~16周', '1', '32');
INSERT INTO `t_course` VALUES ('3814', '操作系统实验', '国际商学院', '1~16周4', '15', '326');
INSERT INTO `t_course` VALUES ('2218', '电子技术与数字电路', '国际商学院', '1~16周', '3', '48');
INSERT INTO `t_course` VALUES ('2813', '电子技术与数字电路实验', '中国语言文化学院', '1~16周', '1', '32');

-- ----------------------------
-- Table structure for t_record
-- ----------------------------
DROP TABLE IF EXISTS `t_record`;
CREATE TABLE `t_record` (
  `rid` int(255) NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `operate` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `oid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `oname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `date` varchar(255) NOT NULL,
  PRIMARY KEY (`rid`)
) ENGINE=InnoDB AUTO_INCREMENT=83 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_record
-- ----------------------------
INSERT INTO `t_record` VALUES ('33', '1', '插入班级', '2801', '28计算机1班', '2023-06-09 13:03:48');
INSERT INTO `t_record` VALUES ('34', '1', '插入班级', '2602', '26计算机2班', '2023-06-09 13:04:25');
INSERT INTO `t_record` VALUES ('35', '1', '插入班级', '2406', '24计算机2班', '2023-06-09 13:08:12');
INSERT INTO `t_record` VALUES ('36', '1', '删除班级', '2406', '24计算机2班', '2023-06-09 13:27:46');
INSERT INTO `t_record` VALUES ('37', '1', '更新班级', '2801', '28计算机1班', '2023-06-09 13:40:12');
INSERT INTO `t_record` VALUES ('38', '1', '增加课程', '1', '2', '2023-06-09 13:51:23');
INSERT INTO `t_record` VALUES ('39', '1', '修改课程', '1', '23', '2023-06-09 13:51:44');
INSERT INTO `t_record` VALUES ('40', '1', '增加课程', '1', '23', '2023-06-09 13:51:46');
INSERT INTO `t_record` VALUES ('41', '1', '增加排课', '0', '20计算机1班', '2023-06-09 14:04:33');
INSERT INTO `t_record` VALUES ('42', '1', '更新排课', '15', '20计算机1班', '2023-06-09 14:04:49');
INSERT INTO `t_record` VALUES ('43', '1', '删除排课', '15', '20计算机1班', '2023-06-09 14:04:54');
INSERT INTO `t_record` VALUES ('44', '1', '增加学生', '1', '2', '2023-06-09 14:35:59');
INSERT INTO `t_record` VALUES ('45', '1', '修改学生', '1', '3', '2023-06-09 14:36:05');
INSERT INTO `t_record` VALUES ('46', '1', '增加学生', '1', '3', '2023-06-09 14:36:06');
INSERT INTO `t_record` VALUES ('47', '1', '修改密码', '1', 'zyr', '2023-06-09 15:43:23');
INSERT INTO `t_record` VALUES ('48', '1', '增加学生', '1', '2', '2023-06-09 22:55:30');
INSERT INTO `t_record` VALUES ('49', '1', '更新排课', '10', '23计算机4班', '2023-06-11 08:55:40');
INSERT INTO `t_record` VALUES ('50', '1', '修改密码', '1', 'zyr', '2023-06-11 09:42:50');
INSERT INTO `t_record` VALUES ('51', '20080501037', '增加学生', '20080501036', 'op', '2023-06-11 14:03:44');
INSERT INTO `t_record` VALUES ('52', '20080501037', '增加学生', '20080501036', 'op', '2023-06-11 14:03:55');
INSERT INTO `t_record` VALUES ('53', '20080501037', '修改学生', '20020702013', '潘伟杰', '2023-06-11 14:04:04');
INSERT INTO `t_record` VALUES ('54', '20080501037', '增加学生', '20080501035', '朱莹榕', '2023-06-11 14:05:18');
INSERT INTO `t_record` VALUES ('55', '1', '修改课程', '3827', 'javaweb开发框架实验', '2023-06-12 21:00:44');
INSERT INTO `t_record` VALUES ('56', '1', '增加学生', '20080501089', 'Amy', '2023-06-12 21:09:37');
INSERT INTO `t_record` VALUES ('57', '1', '增加课程', '3267', '形势与政策', '2023-06-12 21:13:39');
INSERT INTO `t_record` VALUES ('58', '1', '增加课程', '3245', '计算机导论', '2023-06-12 21:14:44');
INSERT INTO `t_record` VALUES ('59', '1', '插入班级', '2301', '23国贸1班', '2023-06-12 21:16:52');
INSERT INTO `t_record` VALUES ('60', '1', '删除班级', '2801', '28计算机1班', '2023-06-12 21:16:56');
INSERT INTO `t_record` VALUES ('61', '1', '增加排课', '0', '23计算机1班', '2023-06-12 21:18:39');
INSERT INTO `t_record` VALUES ('62', '1', '增加学生', '20080501089', 'Amy', '2023-06-12 21:23:14');
INSERT INTO `t_record` VALUES ('63', '1', '删除课程', '3245', '计算机导论', '2023-06-12 21:23:18');
INSERT INTO `t_record` VALUES ('64', '1', '删除班级', '2301', '23国贸1班', '2023-06-12 21:23:23');
INSERT INTO `t_record` VALUES ('65', '1', '删除排课', '26', '23计算机1班', '2023-06-12 21:23:27');
INSERT INTO `t_record` VALUES ('66', '1', '增加学生', '20020702013', '潘伟杰', '2023-06-12 21:23:57');
INSERT INTO `t_record` VALUES ('67', '1', '删除学生', '21080501019', '焦宇涵', '2023-06-12 21:25:44');
INSERT INTO `t_record` VALUES ('68', '1', '删除课程', '3267', '形势与政策', '2023-06-12 21:25:46');
INSERT INTO `t_record` VALUES ('69', '1', '删除班级', '2202', '22计算机2班', '2023-06-12 21:25:49');
INSERT INTO `t_record` VALUES ('70', '1', '删除排课', '6', '21计算机1班', '2023-06-12 21:25:52');
INSERT INTO `t_record` VALUES ('71', '1', '修改学生', '20080501037', '朱莹榕', '2023-06-12 21:27:19');
INSERT INTO `t_record` VALUES ('72', '1', '修改课程', '3211', '操作系统', '2023-06-12 21:28:35');
INSERT INTO `t_record` VALUES ('73', '1', '更新班级', '2602', '26计算机2班', '2023-06-12 21:29:41');
INSERT INTO `t_record` VALUES ('74', '1', '更新排课', '1', '20计算机1班', '2023-06-12 21:30:45');
INSERT INTO `t_record` VALUES ('75', '20080501037', '修改密码', '20080501037', '朱莹榕', '2023-06-13 14:33:15');
INSERT INTO `t_record` VALUES ('76', '20080501037', '修改密码', '20080501037', '朱莹榕', '2023-06-13 14:45:28');
INSERT INTO `t_record` VALUES ('77', '20080501037', '修改密码', '20080501037', '朱莹榕', '2023-06-13 14:45:35');
INSERT INTO `t_record` VALUES ('78', '20080501037', '修改密码', '20080501037', '朱莹榕', '2023-06-13 14:46:58');
INSERT INTO `t_record` VALUES ('79', '20080501037', '修改密码', '20080501037', '朱莹榕', '2023-06-13 14:47:16');
INSERT INTO `t_record` VALUES ('80', '20080501037', '修改密码', '20080501037', '朱莹榕', '2023-06-13 14:50:11');
INSERT INTO `t_record` VALUES ('81', '20080501037', '修改密码', '20080501037', '朱莹榕', '2023-06-13 14:50:34');
INSERT INTO `t_record` VALUES ('82', '20080501037', '修改密码', '20080501037', '朱莹榕', '2023-06-13 14:51:10');

-- ----------------------------
-- Table structure for t_schedule
-- ----------------------------
DROP TABLE IF EXISTS `t_schedule`;
CREATE TABLE `t_schedule` (
  `scheduleid` int(255) NOT NULL AUTO_INCREMENT,
  `coursename` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `clazzname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `tname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `roomname` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`scheduleid`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_schedule
-- ----------------------------
INSERT INTO `t_schedule` VALUES ('1', 'javaweb开发框架', '20计算机1班', '2700359', '涵养楼109');
INSERT INTO `t_schedule` VALUES ('2', 'javaweb开发框架实验', '20计算机1班', '2700359', '涵养楼101');
INSERT INTO `t_schedule` VALUES ('3', '操作系统', '22计算机2班', '2700359', '涵养楼101');
INSERT INTO `t_schedule` VALUES ('4', '操作系统实验', '22计算机4班', '2700358', '涵养楼101');
INSERT INTO `t_schedule` VALUES ('5', '电子技术与数字电路', '21计算机1班', '2700359', '涵养楼101');
INSERT INTO `t_schedule` VALUES ('7', '操作系统', '22计算机3班', '2700359', '涵养楼101');
INSERT INTO `t_schedule` VALUES ('8', '操作系统', '21计算机1班', '2700351', '涵养楼101');
INSERT INTO `t_schedule` VALUES ('9', '操作系统', '22计算机4班', '2700358', '涵养楼101');
INSERT INTO `t_schedule` VALUES ('10', 'javaweb开发框架', '23计算机4班', '2700359', '涵养楼101');
INSERT INTO `t_schedule` VALUES ('11', 'javaweb开发框架', '24计算机4班', '2700359', '涵养楼101');

-- ----------------------------
-- Table structure for t_student
-- ----------------------------
DROP TABLE IF EXISTS `t_student`;
CREATE TABLE `t_student` (
  `stuid` varchar(255) DEFAULT NULL,
  `stuname` varchar(255) DEFAULT NULL,
  `clazzname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `college` varchar(255) DEFAULT NULL,
  `profession` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_student
-- ----------------------------
INSERT INTO `t_student` VALUES ('20080501037', '朱莹榕', '20计算机1班', '英语语言文化学院', '英语（师范）');
INSERT INTO `t_student` VALUES ('22080702024', '王涛', '22计算机2班', '国际商学院', '计算机科学与技术');
INSERT INTO `t_student` VALUES ('22080702002', '陈梦圆', '22计算机2班', '国际商学院', '计算机科学与技术');
INSERT INTO `t_student` VALUES ('22080702001', '王蓉', '22计算机2班', '国际商学院', '计算机科学与技术');
INSERT INTO `t_student` VALUES ('21080501021', '倪婉莹', '21计算机1班', '国际商学院', '计算机科学与技术');
INSERT INTO `t_student` VALUES ('21080501018', '李扬凯', '21计算机1班', '国际商学院', '计算机科学与技术');
INSERT INTO `t_student` VALUES ('21080501012', '周李权', '21计算机1班', '国际商学院', '计算机科学与技术');
INSERT INTO `t_student` VALUES ('21080501011', '许权威', '21计算机1班', '国际商学院', '计算机科学与技术');
INSERT INTO `t_student` VALUES ('21080501001', '毛婉婷', '21计算机1班', '国际商学院', '计算机科学与技术');
INSERT INTO `t_student` VALUES ('20080501007', '泮佳玮', '20计算机1班', '国际商学院', '计算机科学与技术');
INSERT INTO `t_student` VALUES ('20080501006', '洪天成', '20计算机1班', '国际商学院', '计算机科学与技术');
INSERT INTO `t_student` VALUES ('20080501003', '陆昀', '20计算机1班', '国际商学院', '计算机科学与技术');
INSERT INTO `t_student` VALUES ('20080501002', '梁邦帅', '20计算机1班', '国际商学院', '计算机科学与技术');
INSERT INTO `t_student` VALUES ('20080501001', '马丹璐', '20计算机1班', '国际商学院', '计算机科学与技术');
INSERT INTO `t_student` VALUES ('20030801008', '王恒', '21计算机1班', '国际商学院', '计算机科学与技术');

-- ----------------------------
-- Table structure for t_teacher
-- ----------------------------
DROP TABLE IF EXISTS `t_teacher`;
CREATE TABLE `t_teacher` (
  `tid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `tname` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `trank` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_teacher
-- ----------------------------
INSERT INTO `t_teacher` VALUES ('2700321', 'zhang', '男', '副教授');
INSERT INTO `t_teacher` VALUES ('2700351', 'zhong', '男', '讲师');
INSERT INTO `t_teacher` VALUES ('2700358', 'bian', '女', '讲师');
INSERT INTO `t_teacher` VALUES ('2700359', 'liao', '女', '讲师');
INSERT INTO `t_teacher` VALUES ('2700360', 'wan', '女', '副教授');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `userid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `age` int(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'zyr', '1', '21', 'QQ', 'PP', '1');
INSERT INTO `t_user` VALUES ('20080501037', '朱莹榕', '111111', '23', 'XX', '12', '0');
INSERT INTO `t_user` VALUES ('20080501065', '周左', '111111', '24', 'WW', '12', '0');
INSERT INTO `t_user` VALUES ('20080501043', '张扬其', '111111', '35', '65', 'a', '0');
