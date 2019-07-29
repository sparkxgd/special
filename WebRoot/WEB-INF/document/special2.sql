/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50508
Source Host           : localhost:3306
Source Database       : special

Target Server Type    : MYSQL
Target Server Version : 50508
File Encoding         : 65001

Date: 2016-10-19 16:58:50
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `tb_admin_x`
-- ----------------------------
DROP TABLE IF EXISTS `tb_admin_x`;
CREATE TABLE `tb_admin_x` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL DEFAULT '',
  `password` varchar(255) NOT NULL DEFAULT '',
  `type` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_admin_x
-- ----------------------------
INSERT INTO `tb_admin_x` VALUES ('11', 'admin', 'e10adc3949ba59abbe56e057f20f883e', '1');
INSERT INTO `tb_admin_x` VALUES ('12', 'xiao', 'c4ca4238a0b923820dcc509a6f75849b', '0');

-- ----------------------------
-- Table structure for `tb_audition`
-- ----------------------------
DROP TABLE IF EXISTS `tb_audition`;
CREATE TABLE `tb_audition` (
  `id` varchar(100) NOT NULL DEFAULT '',
  `subject` int(11) DEFAULT NULL,
  `student` int(11) DEFAULT NULL,
  `grade` int(11) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  `classroom` int(11) DEFAULT NULL,
  `consultDetailId` varchar(255) DEFAULT NULL,
  `teacher` int(11) DEFAULT NULL,
  `result` tinyint(4) DEFAULT '1' COMMENT '0：成功，1：待定，2：失败',
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `subject` (`subject`),
  KEY `student` (`student`),
  KEY `grade` (`grade`),
  KEY `classroom` (`classroom`),
  KEY `consultDetailId` (`consultDetailId`),
  KEY `teacher` (`teacher`),
  CONSTRAINT `tb_audition_ibfk_1` FOREIGN KEY (`subject`) REFERENCES `tb_subject` (`id`),
  CONSTRAINT `tb_audition_ibfk_2` FOREIGN KEY (`student`) REFERENCES `tb_student` (`id`),
  CONSTRAINT `tb_audition_ibfk_3` FOREIGN KEY (`grade`) REFERENCES `tb_grade` (`id`),
  CONSTRAINT `tb_audition_ibfk_4` FOREIGN KEY (`classroom`) REFERENCES `tb_classroom` (`id`),
  CONSTRAINT `tb_audition_ibfk_5` FOREIGN KEY (`consultDetailId`) REFERENCES `tb_consult_detail` (`id`),
  CONSTRAINT `tb_audition_ibfk_6` FOREIGN KEY (`teacher`) REFERENCES `tb_employee` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_audition
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_campus`
-- ----------------------------
DROP TABLE IF EXISTS `tb_campus`;
CREATE TABLE `tb_campus` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `addr` varchar(255) DEFAULT NULL,
  `employeeId` int(11) DEFAULT NULL,
  `state` int(11) DEFAULT '1' COMMENT '0使用，1未用',
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_campus
-- ----------------------------
INSERT INTO `tb_campus` VALUES ('1', '万博校区', '凯里市', '1', '0', '2016-09-25 23:36:19');
INSERT INTO `tb_campus` VALUES ('2', '中博校区', '凯里市中博步行街A栋7单元614', '1', '0', '2016-09-25 23:36:41');

-- ----------------------------
-- Table structure for `tb_classinfo`
-- ----------------------------
DROP TABLE IF EXISTS `tb_classinfo`;
CREATE TABLE `tb_classinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `employeeId` int(11) DEFAULT NULL,
  `subjectId` int(11) DEFAULT NULL,
  `startTime` datetime DEFAULT NULL,
  `endTime` datetime DEFAULT NULL,
  `gradeId` int(11) DEFAULT NULL,
  `classTypeId` int(11) DEFAULT NULL,
  `timeSlot` int(11) DEFAULT NULL,
  `state` tinyint(4) DEFAULT '0' COMMENT '0：正常，1结束',
  PRIMARY KEY (`id`),
  KEY `subjectId` (`subjectId`),
  KEY `empid` (`employeeId`),
  KEY `class_gradeId` (`gradeId`),
  KEY `classinfo_classTypeId` (`classTypeId`),
  KEY `ci_timeSlot` (`timeSlot`),
  CONSTRAINT `ci_timeSlot` FOREIGN KEY (`timeSlot`) REFERENCES `tb_timeslot` (`id`),
  CONSTRAINT `classinfo_classTypeId` FOREIGN KEY (`classTypeId`) REFERENCES `tb_classtype` (`id`),
  CONSTRAINT `class_gradeId` FOREIGN KEY (`gradeId`) REFERENCES `tb_grade` (`id`),
  CONSTRAINT `empid` FOREIGN KEY (`employeeId`) REFERENCES `tb_employee` (`id`),
  CONSTRAINT `subjectId` FOREIGN KEY (`subjectId`) REFERENCES `tb_subject` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=90 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_classinfo
-- ----------------------------
INSERT INTO `tb_classinfo` VALUES ('58', '初三物理1对1王宇嫣', '2', '4', '2016-10-18 15:22:50', null, '9', '3', '1', '0');
INSERT INTO `tb_classinfo` VALUES ('59', '初三物理1对1文瀚楼', '2', '4', '2016-10-18 15:24:34', null, '9', '3', '6', '0');
INSERT INTO `tb_classinfo` VALUES ('60', '初三物理A', '2', '4', '2016-10-18 15:31:06', null, '9', '1', '4', '0');
INSERT INTO `tb_classinfo` VALUES ('61', '初三物理B', '2', '4', '2016-10-18 16:36:07', null, '9', '1', '6', '0');
INSERT INTO `tb_classinfo` VALUES ('62', '初三物理C', '2', '4', '2016-10-18 16:51:31', null, '9', '1', '6', '0');
INSERT INTO `tb_classinfo` VALUES ('63', '初三英语A', '3', '3', '2016-10-18 16:58:19', null, '9', '1', '2', '0');
INSERT INTO `tb_classinfo` VALUES ('64', '初三英语B', '3', '3', '2016-10-18 17:48:36', null, '9', '1', '6', '0');
INSERT INTO `tb_classinfo` VALUES ('65', '初三英语C', '3', '3', '2016-10-18 18:12:01', null, '9', '1', '3', '0');
INSERT INTO `tb_classinfo` VALUES ('66', '初二英语', '3', '3', '2016-10-18 18:16:26', null, '8', '1', '1', '0');
INSERT INTO `tb_classinfo` VALUES ('67', '初三英语1对1文瀚楼', '3', '3', '2016-10-18 18:23:46', null, '9', '3', '7', '0');
INSERT INTO `tb_classinfo` VALUES ('68', '高二数学A', '14', '1', '2016-10-18 20:16:11', null, '11', '1', '6', '0');
INSERT INTO `tb_classinfo` VALUES ('69', '高三数学', '14', '1', '2016-10-18 20:37:29', null, '12', '1', '3', '0');
INSERT INTO `tb_classinfo` VALUES ('70', '高二英语1对1', '10', '3', '2016-10-18 21:01:53', null, '11', '3', '2', '0');
INSERT INTO `tb_classinfo` VALUES ('71', '高三英语1对1', '10', '3', '2016-10-18 21:09:22', null, '12', '3', '2', '0');
INSERT INTO `tb_classinfo` VALUES ('72', '高一数学', '8', '1', '2016-10-18 21:16:25', '2016-10-18 21:18:23', '10', '1', '1', '1');
INSERT INTO `tb_classinfo` VALUES ('73', '高二数学B', '8', '1', '2016-10-18 21:19:52', null, '11', '1', '1', '0');
INSERT INTO `tb_classinfo` VALUES ('74', '高一数学A', '8', '1', '2016-10-18 21:21:33', null, '10', '1', '2', '0');
INSERT INTO `tb_classinfo` VALUES ('75', '初三化学', '4', '5', '2016-10-18 21:44:41', null, '9', '1', '3', '0');
INSERT INTO `tb_classinfo` VALUES ('76', '高三化学1 对1', '4', '5', '2016-10-18 21:49:36', null, '12', '3', '7', '0');
INSERT INTO `tb_classinfo` VALUES ('77', '初三化学1对1', '4', '5', '2016-10-18 21:52:31', null, '9', '3', '5', '0');
INSERT INTO `tb_classinfo` VALUES ('78', '初三物理1对1孙婷', '2', '4', '2016-10-18 21:56:40', null, '9', '3', '7', '0');
INSERT INTO `tb_classinfo` VALUES ('79', '五年级数学1对1', '7', '1', '2016-10-18 22:15:33', null, '5', '3', '1', '0');
INSERT INTO `tb_classinfo` VALUES ('80', '六年级数学1对1', '7', '1', '2016-10-18 22:18:09', null, '6', '3', '3', '0');
INSERT INTO `tb_classinfo` VALUES ('81', '五年级语文1对1 ', '7', '2', '2016-10-18 22:21:35', null, '5', '3', '5', '0');
INSERT INTO `tb_classinfo` VALUES ('82', '三年级语数外', '7', '1', '2016-10-18 22:23:52', null, '3', '3', '6', '0');
INSERT INTO `tb_classinfo` VALUES ('83', '一年级语文1对1', '7', '2', '2016-10-18 22:26:44', null, '1', '3', '4', '0');
INSERT INTO `tb_classinfo` VALUES ('84', '初三数学1对1文瀚楼', '1', '1', '2016-10-19 10:03:41', null, '9', '3', '7', '0');
INSERT INTO `tb_classinfo` VALUES ('85', '初二数学1对1肖俊康', '1', '1', '2016-10-19 10:10:15', null, '8', '3', '2', '0');
INSERT INTO `tb_classinfo` VALUES ('86', '初三数学A', '1', '1', '2016-10-19 10:13:36', null, '9', '1', '4', '0');
INSERT INTO `tb_classinfo` VALUES ('87', '初三数学B', '1', '1', '2016-10-19 10:31:40', null, '9', '1', '5', '0');
INSERT INTO `tb_classinfo` VALUES ('88', '初三数学C', '1', '1', '2016-10-19 11:02:31', null, '9', '1', '6', '0');
INSERT INTO `tb_classinfo` VALUES ('89', '初三数学1对2', '1', '1', '2016-10-19 11:10:57', null, '9', '4', '6', '0');

-- ----------------------------
-- Table structure for `tb_classrcord`
-- ----------------------------
DROP TABLE IF EXISTS `tb_classrcord`;
CREATE TABLE `tb_classrcord` (
  `id` varchar(100) NOT NULL,
  `classInfoId` int(11) DEFAULT NULL,
  `gotime` datetime DEFAULT NULL,
  `classroomId` int(11) DEFAULT NULL,
  `employeeId` int(11) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `overTime` datetime DEFAULT NULL,
  `no` tinyint(11) DEFAULT NULL,
  `type` tinyint(4) DEFAULT '1' COMMENT '0：补课1：正常上课',
  PRIMARY KEY (`id`),
  KEY `sgc_classInfoId` (`classInfoId`),
  KEY `sgc_classroomId` (`classroomId`),
  KEY `sgc_employeeId` (`employeeId`),
  CONSTRAINT `tb_classrcord_ibfk_1` FOREIGN KEY (`classInfoId`) REFERENCES `tb_classinfo` (`id`),
  CONSTRAINT `tb_classrcord_ibfk_2` FOREIGN KEY (`classroomId`) REFERENCES `tb_classroom` (`id`),
  CONSTRAINT `tb_classrcord_ibfk_3` FOREIGN KEY (`employeeId`) REFERENCES `tb_employee` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_classrcord
-- ----------------------------
INSERT INTO `tb_classrcord` VALUES ('2016101816261292178715', '59', '2016-10-08 16:25:15', '1', '2', null, '2016-10-08 18:25:19', '1', '1');
INSERT INTO `tb_classrcord` VALUES ('2016101816264284398207', '59', '2016-10-09 16:25:41', '1', '2', null, '2016-10-09 18:25:46', '2', '1');
INSERT INTO `tb_classrcord` VALUES ('201610181627329214764', '59', '2016-10-12 16:26:25', '1', '2', null, '2016-10-12 18:26:31', '4', '1');
INSERT INTO `tb_classrcord` VALUES ('201610181627731211763', '59', '2016-10-10 10:26:03', '1', '2', null, '2016-10-10 12:26:09', '3', '1');
INSERT INTO `tb_classrcord` VALUES ('2016101816283921814193', '59', '2016-10-15 16:27:29', '4', '2', null, '2016-10-15 18:27:33', '6', '1');
INSERT INTO `tb_classrcord` VALUES ('2016101816285995369870', '59', '2016-10-16 16:27:49', '4', '2', null, '2016-10-16 18:27:53', '7', '1');
INSERT INTO `tb_classrcord` VALUES ('201610181628843741761', '59', '2016-10-13 16:26:54', '1', '2', null, '2016-10-13 18:27:01', '5', '1');
INSERT INTO `tb_classrcord` VALUES ('2016101816291934359509', '59', '2016-10-17 16:28:05', '1', '2', null, '2016-10-17 18:28:09', '8', '1');
INSERT INTO `tb_classrcord` VALUES ('201610181632495936262', '60', '2016-10-06 16:28:31', '2', '2', null, '2016-10-06 18:28:35', '1', '1');
INSERT INTO `tb_classrcord` VALUES ('2016101816333154690314', '60', '2016-10-12 16:31:48', '4', '2', null, '2016-10-12 18:31:52', '2', '1');
INSERT INTO `tb_classrcord` VALUES ('2016101816341110935212', '60', '2016-10-13 16:32:25', '3', '2', null, '2016-10-13 18:32:29', '3', '1');
INSERT INTO `tb_classrcord` VALUES ('2016101816345332865496', '60', '2016-10-14 16:33:08', '6', '2', null, '2016-10-14 18:33:11', '4', '1');
INSERT INTO `tb_classrcord` VALUES ('2016101816351670337945', '60', '2016-10-16 16:33:32', '6', '2', null, '2016-10-16 18:33:38', '5', '1');
INSERT INTO `tb_classrcord` VALUES ('2016101816451039086534', '61', '2016-10-11 16:41:23', '2', '2', null, '2016-10-11 18:41:27', '1', '1');
INSERT INTO `tb_classrcord` VALUES ('2016101816455243746045', '61', '2016-10-13 16:42:59', '2', '2', null, '2016-10-13 18:43:03', '2', '1');
INSERT INTO `tb_classrcord` VALUES ('2016101816462998497346', '61', '2016-10-14 16:43:39', '7', '2', null, '2016-10-14 18:43:44', '3', '1');
INSERT INTO `tb_classrcord` VALUES ('201610181647195381231', '61', '2016-10-15 16:44:14', '3', '2', null, '2016-10-15 18:44:20', '4', '1');
INSERT INTO `tb_classrcord` VALUES ('2016101816473176596085', '61', '2016-10-16 16:44:40', '2', '2', null, '2016-10-16 18:44:45', '5', '1');
INSERT INTO `tb_classrcord` VALUES ('2016101816474985984471', '61', '2016-10-17 16:45:08', '3', '2', null, '2016-10-17 18:45:11', '6', '1');
INSERT INTO `tb_classrcord` VALUES ('2016101816545185995058', '62', '2016-10-16 16:54:31', '3', '2', null, '2016-10-16 18:54:36', '1', '1');
INSERT INTO `tb_classrcord` VALUES ('2016101817321460961532', '63', '2016-10-07 17:30:58', '5', '3', null, '2016-10-07 19:31:03', '1', '1');
INSERT INTO `tb_classrcord` VALUES ('2016101817324731278698', '63', '2016-10-08 17:31:29', '5', '3', null, '2016-10-08 19:31:32', '2', '1');
INSERT INTO `tb_classrcord` VALUES ('2016101817331443717380', '63', '2016-10-09 17:32:07', '5', '3', null, '2016-10-09 19:32:11', '3', '1');
INSERT INTO `tb_classrcord` VALUES ('20161018173355153867', '63', '2016-10-11 17:32:48', '3', '3', null, '2016-10-11 19:32:51', '4', '1');
INSERT INTO `tb_classrcord` VALUES ('2016101817341370317590', '63', '2016-10-13 17:33:07', '2', '3', null, '2016-10-13 19:33:11', '5', '1');
INSERT INTO `tb_classrcord` VALUES ('201610181735389029185', '63', '2016-10-14 17:33:56', '7', '3', null, '2016-10-14 19:33:58', '6', '1');
INSERT INTO `tb_classrcord` VALUES ('2016101817472689098496', '63', '2016-10-17 17:46:17', '5', '3', null, '2016-10-17 19:46:20', '7', '1');
INSERT INTO `tb_classrcord` VALUES ('2016101818152634331566', '65', '2016-10-15 18:14:17', '2', '3', null, '2016-10-15 20:14:21', '1', '1');
INSERT INTO `tb_classrcord` VALUES ('2016101818205096850917', '66', '2016-10-14 18:19:29', '2', '3', null, '2016-10-14 20:19:35', '1', '1');
INSERT INTO `tb_classrcord` VALUES ('2016101818211796860018', '66', '2016-10-15 18:20:05', '4', '3', null, '2016-10-15 20:20:08', '2', '1');
INSERT INTO `tb_classrcord` VALUES ('2016101818214168782322', '66', '2016-10-16 18:20:29', '2', '3', null, '2016-10-16 20:20:34', '3', '1');
INSERT INTO `tb_classrcord` VALUES ('20161018182239010328', '66', '2016-10-17 18:21:16', '2', '3', null, '2016-10-17 20:21:21', '4', '1');
INSERT INTO `tb_classrcord` VALUES ('20161018182329362044', '66', '2016-10-18 18:21:56', '4', '3', null, '2016-10-18 20:21:59', '5', '1');
INSERT INTO `tb_classrcord` VALUES ('2016101818244431298814', '67', '2016-10-15 18:23:35', '1', '3', null, '2016-10-15 20:23:39', '1', '1');
INSERT INTO `tb_classrcord` VALUES ('201610181825187582254', '67', '2016-10-16 18:23:56', '1', '3', null, '2016-10-16 20:23:59', '2', '1');
INSERT INTO `tb_classrcord` VALUES ('2016101818252064037673', '67', '2016-10-17 18:24:13', '4', '3', null, '2016-10-17 20:24:15', '3', '1');
INSERT INTO `tb_classrcord` VALUES ('2016101818394489021820', '65', '2016-10-05 18:38:38', '2', '3', null, '2016-10-05 20:38:41', '2', '1');
INSERT INTO `tb_classrcord` VALUES ('2016101818401845319640', '65', '2016-10-06 18:38:59', '3', '3', null, '2016-10-06 20:39:02', '3', '1');
INSERT INTO `tb_classrcord` VALUES ('20161018184509316800', '64', '2016-10-07 18:03:38', '2', '3', null, '2016-10-07 20:03:41', '1', '1');
INSERT INTO `tb_classrcord` VALUES ('201610181871014063608', '64', '2016-10-15 18:05:23', '7', '3', null, '2016-10-15 20:05:28', '2', '1');
INSERT INTO `tb_classrcord` VALUES ('2016101818832967688', '64', '2016-10-16 18:06:47', '2', '3', null, '2016-10-16 21:06:51', '3', '1');
INSERT INTO `tb_classrcord` VALUES ('2016101820413132856249', '69', '2016-10-04 20:40:22', '2', '14', null, '2016-10-04 22:40:26', '1', '1');
INSERT INTO `tb_classrcord` VALUES ('20161018204208433826', '69', '2016-10-06 10:40:52', '2', '14', null, '2016-10-06 12:40:59', '2', '1');
INSERT INTO `tb_classrcord` VALUES ('201610182046382896946', '69', '2016-10-07 20:44:58', '4', '14', null, '2016-10-07 22:45:01', '3', '1');
INSERT INTO `tb_classrcord` VALUES ('2016101820473921879533', '69', '2016-10-09 20:46:32', '5', '14', null, '2016-10-09 22:46:37', '4', '1');
INSERT INTO `tb_classrcord` VALUES ('2016101820494014082981', '69', '2016-10-14 20:48:33', '5', '14', null, '2016-10-14 21:48:37', '5', '1');
INSERT INTO `tb_classrcord` VALUES ('201610182050271811561', '69', '2016-10-16 20:48:55', '6', '14', null, '2016-10-16 22:48:58', '6', '1');
INSERT INTO `tb_classrcord` VALUES ('201610182051147885275', '69', '2016-10-13 20:50:04', '6', '14', null, '2016-10-13 22:50:08', '7', '1');
INSERT INTO `tb_classrcord` VALUES ('2016101820523362584747', '69', '2016-10-11 20:51:22', '7', '14', null, '2016-10-11 21:51:28', '8', '1');
INSERT INTO `tb_classrcord` VALUES ('201610182105265661680', '62', '2016-10-18 19:59:39', '2', '2', null, '2016-10-18 20:59:47', '2', '1');
INSERT INTO `tb_classrcord` VALUES ('2016101821274884339735', '74', '2016-10-09 21:26:39', '2', '8', null, '2016-10-09 22:26:42', '1', '1');
INSERT INTO `tb_classrcord` VALUES ('2016101821281589068354', '74', '2016-10-11 18:26:58', '4', '8', null, '2016-10-11 20:27:06', '2', '1');
INSERT INTO `tb_classrcord` VALUES ('201610182128486716363', '74', '2016-10-13 19:27:29', '2', '8', null, '2016-10-13 21:27:37', '3', '1');
INSERT INTO `tb_classrcord` VALUES ('2016101821291143751065', '74', '2016-10-14 19:27:57', '4', '8', null, '2016-10-14 21:28:06', '4', '1');
INSERT INTO `tb_classrcord` VALUES ('201610182129349215457', '74', '2016-10-15 19:28:22', '2', '8', null, '2016-10-15 21:28:28', '5', '1');
INSERT INTO `tb_classrcord` VALUES ('201610182130328124314', '74', '2016-10-16 19:28:43', '3', '8', null, '2016-10-16 21:28:50', '6', '1');
INSERT INTO `tb_classrcord` VALUES ('2016101821514843784684', '76', '2016-10-14 19:00:00', '1', '4', null, '2016-10-14 21:00:00', '1', '1');
INSERT INTO `tb_classrcord` VALUES ('2016101822311776570797', '83', '2016-10-15 14:30:45', '3', '7', null, '2016-10-15 16:30:58', '1', '1');
INSERT INTO `tb_classrcord` VALUES ('2016101822314584334515', '83', '2016-10-16 14:31:16', '1', '7', null, '2016-10-16 16:31:26', '2', '1');
INSERT INTO `tb_classrcord` VALUES ('2016101910162228151981', '86', '2016-10-04 10:15:18', '2', '1', null, '2016-10-04 12:15:23', '1', '1');
INSERT INTO `tb_classrcord` VALUES ('2016101910164271817978', '86', '2016-10-05 10:15:38', '3', '1', null, '2016-10-05 12:15:43', '2', '1');
INSERT INTO `tb_classrcord` VALUES ('201610191023351253094', '86', '2016-10-10 10:23:04', '3', '1', null, '2016-10-10 12:23:09', '3', '1');
INSERT INTO `tb_classrcord` VALUES ('201610191025557894350', '86', '2016-10-13 10:24:22', '6', '1', null, '2016-10-13 12:24:27', '4', '1');
INSERT INTO `tb_classrcord` VALUES ('201610191029598441599', '86', '2016-10-15 10:27:51', '2', '1', null, '2016-10-15 12:27:59', '5', '1');
INSERT INTO `tb_classrcord` VALUES ('2016101910305329690569', '86', '2016-10-16 10:29:32', '6', '1', null, '2016-10-16 12:29:36', '6', '1');
INSERT INTO `tb_classrcord` VALUES ('201610191065078126987', '84', '2016-10-08 10:05:52', '2', '1', null, '2016-10-08 12:05:56', '1', '1');
INSERT INTO `tb_classrcord` VALUES ('201610191071759391488', '84', '2016-10-15 10:06:20', '1', '1', null, '2016-10-15 12:06:26', '2', '1');
INSERT INTO `tb_classrcord` VALUES ('201610191073867115796', '84', '2016-10-16 10:06:42', '4', '1', null, '2016-10-16 12:06:48', '3', '1');
INSERT INTO `tb_classrcord` VALUES ('20161019108048464918', '84', '2016-10-17 10:07:05', '1', '1', null, '2016-10-17 12:07:09', '4', '1');
INSERT INTO `tb_classrcord` VALUES ('201610191105093763291', '87', '2016-10-15 10:58:24', '2', '1', null, '2016-10-15 12:58:36', '1', '1');
INSERT INTO `tb_classrcord` VALUES ('201610191111370391943', '87', '2016-10-16 10:59:11', '3', '1', null, '2016-10-16 12:59:15', '2', '1');
INSERT INTO `tb_classrcord` VALUES ('2016101911125476540401', '89', '2016-10-18 19:10:47', '3', '1', null, '2016-10-18 21:10:55', '1', '1');
INSERT INTO `tb_classrcord` VALUES ('201610191182146868788', '88', '2016-10-16 11:06:16', '3', '1', null, '2016-10-16 13:06:22', '1', '1');

-- ----------------------------
-- Table structure for `tb_classroom`
-- ----------------------------
DROP TABLE IF EXISTS `tb_classroom`;
CREATE TABLE `tb_classroom` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `campus` varchar(50) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `seats` int(11) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `state` tinyint(4) DEFAULT '0' COMMENT '0可用，1弃用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_classroom
-- ----------------------------
INSERT INTO `tb_classroom` VALUES ('1', '中博', '一号教室', '2', '2016-09-18 21:26:00', '0');
INSERT INTO `tb_classroom` VALUES ('2', '中博', '二号教室', '8', '2016-09-18 21:26:00', '0');
INSERT INTO `tb_classroom` VALUES ('3', '中博', '三号教室', '12', '2016-09-18 21:26:00', '0');
INSERT INTO `tb_classroom` VALUES ('4', '中博', '四号教室', '2', '2016-09-18 21:26:00', '0');
INSERT INTO `tb_classroom` VALUES ('5', '中博', '五号教室', '8', '2016-09-18 21:26:00', '0');
INSERT INTO `tb_classroom` VALUES ('6', '中博', '六号教室', '16', '2016-09-18 21:26:00', '0');
INSERT INTO `tb_classroom` VALUES ('7', '中博', '七号教室', '4', '2016-09-18 21:26:00', '0');
INSERT INTO `tb_classroom` VALUES ('8', '中博', '八号教室', '20', '2016-09-18 21:26:00', '0');

-- ----------------------------
-- Table structure for `tb_classtype`
-- ----------------------------
DROP TABLE IF EXISTS `tb_classtype`;
CREATE TABLE `tb_classtype` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_classtype
-- ----------------------------
INSERT INTO `tb_classtype` VALUES ('1', '普通班');
INSERT INTO `tb_classtype` VALUES ('2', '全日制');
INSERT INTO `tb_classtype` VALUES ('3', 'VIP一对一');
INSERT INTO `tb_classtype` VALUES ('4', 'VIP一对二');
INSERT INTO `tb_classtype` VALUES ('5', 'VIP一对三');

-- ----------------------------
-- Table structure for `tb_consult`
-- ----------------------------
DROP TABLE IF EXISTS `tb_consult`;
CREATE TABLE `tb_consult` (
  `id` varchar(255) NOT NULL DEFAULT '',
  `time` datetime DEFAULT NULL,
  `campus` int(11) DEFAULT NULL,
  `advocate` varchar(255) DEFAULT NULL,
  `tel` varchar(255) DEFAULT NULL,
  `receiverType` tinyint(4) DEFAULT '1' COMMENT '0学生1家长2朋友',
  `receiver` int(11) DEFAULT NULL,
  `channel` tinyint(4) DEFAULT '2' COMMENT '0网络1传单2朋友介绍3招生队',
  `responsible` int(11) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `campus` (`campus`),
  KEY `receiver` (`receiver`),
  KEY `responsible` (`responsible`),
  CONSTRAINT `tb_consult_ibfk_1` FOREIGN KEY (`campus`) REFERENCES `tb_campus` (`id`),
  CONSTRAINT `tb_consult_ibfk_2` FOREIGN KEY (`receiver`) REFERENCES `tb_employee` (`id`),
  CONSTRAINT `tb_consult_ibfk_3` FOREIGN KEY (`responsible`) REFERENCES `tb_employee` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_consult
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_consult_detail`
-- ----------------------------
DROP TABLE IF EXISTS `tb_consult_detail`;
CREATE TABLE `tb_consult_detail` (
  `id` varchar(255) NOT NULL DEFAULT '',
  `subject` int(11) DEFAULT NULL,
  `grade` int(11) DEFAULT NULL,
  `classType` int(11) DEFAULT NULL,
  `score` varchar(255) DEFAULT NULL,
  `school` int(11) DEFAULT NULL,
  `consultId` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `state` tinyint(4) DEFAULT '0' COMMENT '0：未处理1已处理',
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `subject` (`subject`),
  KEY `grade` (`grade`),
  KEY `classType` (`classType`),
  KEY `school` (`school`),
  KEY `consultId` (`consultId`),
  CONSTRAINT `tb_consult_detail_ibfk_1` FOREIGN KEY (`subject`) REFERENCES `tb_subject` (`id`),
  CONSTRAINT `tb_consult_detail_ibfk_2` FOREIGN KEY (`grade`) REFERENCES `tb_grade` (`id`),
  CONSTRAINT `tb_consult_detail_ibfk_3` FOREIGN KEY (`classType`) REFERENCES `tb_classtype` (`id`),
  CONSTRAINT `tb_consult_detail_ibfk_4` FOREIGN KEY (`school`) REFERENCES `tb_school` (`id`),
  CONSTRAINT `tb_consult_detail_ibfk_5` FOREIGN KEY (`consultId`) REFERENCES `tb_consult` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_consult_detail
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_employee`
-- ----------------------------
DROP TABLE IF EXISTS `tb_employee`;
CREATE TABLE `tb_employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `sex` tinyint(4) DEFAULT '1' COMMENT '1男 2女',
  `borthday` date DEFAULT NULL,
  `creatTime` datetime DEFAULT NULL,
  `identification` varchar(100) DEFAULT NULL,
  `nativePlace` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_employee
-- ----------------------------
INSERT INTO `tb_employee` VALUES ('1', '肖光鼎', '1', '2016-09-18', '2016-09-18 00:00:00', '522631198601038512', '贵州黎平');
INSERT INTO `tb_employee` VALUES ('2', '吴启平', '0', '2016-09-18', '2016-09-18 21:50:08', null, null);
INSERT INTO `tb_employee` VALUES ('3', '潘万毫', '1', '2016-09-18', '2016-09-18 21:50:45', '522631198601038510', '贵州黎平');
INSERT INTO `tb_employee` VALUES ('4', '孟祥立', '1', '2016-09-18', '2016-09-18 21:50:45', '522631198601038513', '贵州黎平');
INSERT INTO `tb_employee` VALUES ('5', '杨强', '1', '2016-09-18', '2016-09-18 21:52:12', '522631198601038514', '贵州黎平');
INSERT INTO `tb_employee` VALUES ('6', '杨杰', '1', '2016-09-18', '2016-09-18 21:52:35', '522631198601038515', '贵州黎平');
INSERT INTO `tb_employee` VALUES ('7', '章萍', '2', '2016-09-18', '2016-09-18 21:53:08', '522631198601038516', '贵州剑河');
INSERT INTO `tb_employee` VALUES ('8', '欧桂明', '1', '2016-09-18', '2016-09-18 21:54:23', '522631198601038517', '贵州台江');
INSERT INTO `tb_employee` VALUES ('9', '杨秀娟', '0', null, '2016-10-09 11:24:59', null, null);
INSERT INTO `tb_employee` VALUES ('10', '朱以礼', '0', '2016-10-12', '2016-10-12 09:27:09', '522631199001038512', '贵州凯里');
INSERT INTO `tb_employee` VALUES ('11', '杨群', '0', null, null, null, null);
INSERT INTO `tb_employee` VALUES ('12', '张先元', '1', null, null, null, null);
INSERT INTO `tb_employee` VALUES ('13', '吴方英', '0', null, null, null, null);
INSERT INTO `tb_employee` VALUES ('14', '张贤梅', '0', null, null, null, null);
INSERT INTO `tb_employee` VALUES ('15', '李春平', '0', null, null, null, null);
INSERT INTO `tb_employee` VALUES ('16', '欧国林', '1', null, null, null, null);
INSERT INTO `tb_employee` VALUES ('17', '王忠智', '1', null, null, null, null);
INSERT INTO `tb_employee` VALUES ('18', '陆成虎', '1', null, null, null, null);
INSERT INTO `tb_employee` VALUES ('19', '周美花', '0', null, null, null, null);

-- ----------------------------
-- Table structure for `tb_employeeinfo`
-- ----------------------------
DROP TABLE IF EXISTS `tb_employeeinfo`;
CREATE TABLE `tb_employeeinfo` (
  `id` int(11) NOT NULL DEFAULT '0',
  `tel` varchar(20) DEFAULT NULL,
  `qq` varchar(50) DEFAULT NULL,
  `weixin` varchar(50) DEFAULT NULL,
  `addr` varchar(100) DEFAULT NULL,
  `homeAddr` varchar(100) DEFAULT NULL,
  `isSingle` tinyint(4) DEFAULT '0' COMMENT '0单身，1已婚',
  `headImg` varchar(100) DEFAULT NULL,
  `skill` varchar(100) DEFAULT NULL,
  `major` varchar(100) DEFAULT NULL,
  `type` tinyint(4) DEFAULT '1' COMMENT '0兼职，1全职',
  `inTime` date DEFAULT NULL,
  `outTime` date DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `positionId` int(11) DEFAULT '0',
  `state` tinyint(4) DEFAULT '0' COMMENT '0在职，1离职',
  PRIMARY KEY (`id`),
  KEY `positionId` (`positionId`),
  CONSTRAINT `employeeId` FOREIGN KEY (`id`) REFERENCES `tb_employee` (`id`),
  CONSTRAINT `positionId` FOREIGN KEY (`positionId`) REFERENCES `tb_position` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_employeeinfo
-- ----------------------------
INSERT INTO `tb_employeeinfo` VALUES ('1', '15286349520', '3204524784', 'special_xgd', '凯里永乐路', '贵州省黎平县德化乡高洋村', '1', null, '忽悠人', '计算机科学与技术', '1', '2016-03-12', null, '2016-09-18 21:59:13', '3', '0');
INSERT INTO `tb_employeeinfo` VALUES ('2', '15286349520', null, null, null, null, '0', null, null, null, '1', null, null, null, '3', '0');
INSERT INTO `tb_employeeinfo` VALUES ('3', null, null, null, null, null, '0', null, null, null, '1', null, null, null, '3', '0');
INSERT INTO `tb_employeeinfo` VALUES ('4', null, null, null, null, null, '0', null, null, null, '1', null, null, null, '3', '0');
INSERT INTO `tb_employeeinfo` VALUES ('5', null, null, null, null, null, '0', null, null, null, '1', null, null, null, '3', '0');
INSERT INTO `tb_employeeinfo` VALUES ('6', null, null, null, null, null, '0', null, null, null, '1', null, null, null, '3', '0');
INSERT INTO `tb_employeeinfo` VALUES ('7', null, null, null, null, null, '0', null, null, null, '1', null, null, null, '3', '0');
INSERT INTO `tb_employeeinfo` VALUES ('8', null, null, null, null, null, '0', null, null, null, '1', null, null, null, '3', '0');
INSERT INTO `tb_employeeinfo` VALUES ('9', null, null, null, null, null, '0', null, null, null, '1', null, null, null, '3', '0');
INSERT INTO `tb_employeeinfo` VALUES ('10', null, null, null, null, null, '0', null, null, null, '1', null, null, null, '3', '0');
INSERT INTO `tb_employeeinfo` VALUES ('11', null, null, null, null, null, '0', null, null, null, '1', null, null, null, '3', '0');
INSERT INTO `tb_employeeinfo` VALUES ('12', null, null, null, null, null, '0', null, null, null, '1', null, null, null, '3', '0');
INSERT INTO `tb_employeeinfo` VALUES ('13', null, null, null, null, null, '0', null, null, null, '1', null, null, null, '3', '0');
INSERT INTO `tb_employeeinfo` VALUES ('14', null, null, null, null, null, '0', null, null, null, '1', null, null, null, '3', '0');
INSERT INTO `tb_employeeinfo` VALUES ('15', null, null, null, null, null, '0', null, null, null, '1', null, null, null, '3', '0');
INSERT INTO `tb_employeeinfo` VALUES ('16', null, null, null, null, null, '0', null, null, null, '1', null, null, null, '3', '0');
INSERT INTO `tb_employeeinfo` VALUES ('17', null, null, null, null, null, '0', null, null, null, '1', null, null, null, '3', '0');
INSERT INTO `tb_employeeinfo` VALUES ('18', null, null, null, null, null, '0', null, null, null, '1', null, null, null, '3', '0');
INSERT INTO `tb_employeeinfo` VALUES ('19', null, null, null, null, null, '0', null, null, null, '1', null, null, null, '3', '0');

-- ----------------------------
-- Table structure for `tb_grade`
-- ----------------------------
DROP TABLE IF EXISTS `tb_grade`;
CREATE TABLE `tb_grade` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_grade
-- ----------------------------
INSERT INTO `tb_grade` VALUES ('1', '一年级');
INSERT INTO `tb_grade` VALUES ('2', '二年级');
INSERT INTO `tb_grade` VALUES ('3', '三年级');
INSERT INTO `tb_grade` VALUES ('4', '四年级');
INSERT INTO `tb_grade` VALUES ('5', '五年级');
INSERT INTO `tb_grade` VALUES ('6', '六年级');
INSERT INTO `tb_grade` VALUES ('7', '七年级');
INSERT INTO `tb_grade` VALUES ('8', '八年级');
INSERT INTO `tb_grade` VALUES ('9', '九年级');
INSERT INTO `tb_grade` VALUES ('10', '高一');
INSERT INTO `tb_grade` VALUES ('11', '高二');
INSERT INTO `tb_grade` VALUES ('12', '高三');

-- ----------------------------
-- Table structure for `tb_position`
-- ----------------------------
DROP TABLE IF EXISTS `tb_position`;
CREATE TABLE `tb_position` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_position
-- ----------------------------
INSERT INTO `tb_position` VALUES ('1', '校长');
INSERT INTO `tb_position` VALUES ('2', '副校长');
INSERT INTO `tb_position` VALUES ('3', '教师');
INSERT INTO `tb_position` VALUES ('4', '招生');
INSERT INTO `tb_position` VALUES ('5', '前台');
INSERT INTO `tb_position` VALUES ('6', '教务主任');
INSERT INTO `tb_position` VALUES ('7', '网络总管');
INSERT INTO `tb_position` VALUES ('8', '其他');

-- ----------------------------
-- Table structure for `tb_registrationfee`
-- ----------------------------
DROP TABLE IF EXISTS `tb_registrationfee`;
CREATE TABLE `tb_registrationfee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `classTypeId` int(11) DEFAULT NULL,
  `gradeId` int(11) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT '0.00',
  `remark` varchar(255) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `classTypeId` (`classTypeId`),
  KEY `gradeId` (`gradeId`),
  CONSTRAINT `classTypeId` FOREIGN KEY (`classTypeId`) REFERENCES `tb_classtype` (`id`),
  CONSTRAINT `gradeId` FOREIGN KEY (`gradeId`) REFERENCES `tb_grade` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_registrationfee
-- ----------------------------
INSERT INTO `tb_registrationfee` VALUES ('1', '1', '1', '25.00', '每课时', '2016-09-18 21:36:09');
INSERT INTO `tb_registrationfee` VALUES ('2', '1', '2', '25.00', '每课时', '2016-09-18 21:36:09');
INSERT INTO `tb_registrationfee` VALUES ('3', '1', '3', '25.00', '每课时', '2016-09-18 21:36:09');
INSERT INTO `tb_registrationfee` VALUES ('4', '1', '4', '25.00', '每课时', '2016-09-18 21:36:09');
INSERT INTO `tb_registrationfee` VALUES ('5', '1', '5', '25.00', '每课时', '2016-09-18 21:36:09');
INSERT INTO `tb_registrationfee` VALUES ('6', '1', '6', '25.00', '每课时', '2016-09-18 21:36:09');
INSERT INTO `tb_registrationfee` VALUES ('7', '1', '7', '37.50', '每课时', '2016-09-18 21:36:09');
INSERT INTO `tb_registrationfee` VALUES ('8', '1', '8', '37.50', '每课时', '2016-09-18 21:36:09');
INSERT INTO `tb_registrationfee` VALUES ('9', '1', '9', '37.50', '每课时', '2016-09-18 21:36:09');
INSERT INTO `tb_registrationfee` VALUES ('10', '1', '10', '50.00', '每课时', '2016-09-18 21:36:09');
INSERT INTO `tb_registrationfee` VALUES ('11', '1', '11', '50.00', '每课时', '2016-09-18 21:36:09');
INSERT INTO `tb_registrationfee` VALUES ('12', '1', '12', '50.00', '每课时', '2016-09-18 21:36:09');
INSERT INTO `tb_registrationfee` VALUES ('13', '3', '1', '90.00', '每课时', '2016-09-18 21:36:09');
INSERT INTO `tb_registrationfee` VALUES ('14', '3', '2', '90.00', '每课时', '2016-09-18 21:36:09');
INSERT INTO `tb_registrationfee` VALUES ('15', '3', '3', '90.00', '每课时', '2016-09-18 21:36:09');
INSERT INTO `tb_registrationfee` VALUES ('16', '3', '4', '90.00', '每课时', '2016-09-18 21:36:09');
INSERT INTO `tb_registrationfee` VALUES ('17', '3', '5', '90.00', '每课时', '2016-09-18 21:36:09');
INSERT INTO `tb_registrationfee` VALUES ('18', '3', '6', '90.00', '每课时', '2016-09-18 21:36:09');
INSERT INTO `tb_registrationfee` VALUES ('19', '3', '7', '160.00', '每课时', '2016-09-18 21:36:09');
INSERT INTO `tb_registrationfee` VALUES ('20', '3', '8', '160.00', '每课时', '2016-09-18 21:36:09');
INSERT INTO `tb_registrationfee` VALUES ('21', '3', '9', '160.00', '每课时', '2016-09-18 21:36:09');
INSERT INTO `tb_registrationfee` VALUES ('22', '3', '10', '180.00', '每课时', '2016-09-18 21:36:09');
INSERT INTO `tb_registrationfee` VALUES ('23', '3', '11', '180.00', '每课时', '2016-09-18 21:36:09');
INSERT INTO `tb_registrationfee` VALUES ('24', '3', '12', '180.00', '每课时', '2016-09-18 21:36:09');
INSERT INTO `tb_registrationfee` VALUES ('25', '4', '1', '70.00', '每课时', '2016-10-11 17:33:51');
INSERT INTO `tb_registrationfee` VALUES ('26', '4', '2', '70.00', '每课时', '2016-10-11 17:35:40');
INSERT INTO `tb_registrationfee` VALUES ('27', '4', '3', '70.00', '每课时', '2016-10-11 17:35:54');
INSERT INTO `tb_registrationfee` VALUES ('28', '4', '4', '70.00', '每课时', '2016-10-11 17:36:06');
INSERT INTO `tb_registrationfee` VALUES ('29', '4', '5', '70.00', '每课时', '2016-10-11 17:36:19');
INSERT INTO `tb_registrationfee` VALUES ('30', '4', '6', '70.00', '每课时', '2016-10-11 17:36:32');
INSERT INTO `tb_registrationfee` VALUES ('31', '4', '7', '140.00', '每课时', '2016-10-11 17:50:58');
INSERT INTO `tb_registrationfee` VALUES ('32', '4', '8', '140.00', '每课时', '2016-10-11 17:51:07');
INSERT INTO `tb_registrationfee` VALUES ('33', '4', '9', '140.00', '每课时', '2016-10-11 17:49:49');
INSERT INTO `tb_registrationfee` VALUES ('34', '4', '10', '160.00', '每课时', '2016-10-11 17:52:35');
INSERT INTO `tb_registrationfee` VALUES ('35', '4', '11', '160.00', '每课时', '2016-10-11 17:51:50');
INSERT INTO `tb_registrationfee` VALUES ('36', '4', '12', '160.00', '每课时', '2016-10-11 17:52:09');
INSERT INTO `tb_registrationfee` VALUES ('37', '5', '1', '60.00', '每课时', '2016-10-11 17:53:22');
INSERT INTO `tb_registrationfee` VALUES ('38', '5', '2', '60.00', '每课时', '2016-10-11 17:53:36');
INSERT INTO `tb_registrationfee` VALUES ('39', '5', '3', '60.00', '每课时', '2016-10-11 17:54:35');
INSERT INTO `tb_registrationfee` VALUES ('40', '5', '4', '60.00', '每课时', '2016-10-11 17:54:51');
INSERT INTO `tb_registrationfee` VALUES ('41', '5', '5', '60.00', '每课时', '2016-10-11 17:55:10');
INSERT INTO `tb_registrationfee` VALUES ('42', '5', '6', '60.00', '每课时', '2016-10-11 17:55:24');
INSERT INTO `tb_registrationfee` VALUES ('43', '5', '7', '120.00', '每课时', '2016-10-11 17:55:46');
INSERT INTO `tb_registrationfee` VALUES ('44', '5', '8', '120.00', '每课时', '2016-10-11 17:56:04');
INSERT INTO `tb_registrationfee` VALUES ('45', '5', '9', '120.00', '每课时', '2016-10-11 17:56:23');
INSERT INTO `tb_registrationfee` VALUES ('46', '5', '10', '140.00', '每课时', '2016-10-11 17:56:43');
INSERT INTO `tb_registrationfee` VALUES ('47', '5', '11', '140.00', '每课时', '2016-10-11 17:57:00');
INSERT INTO `tb_registrationfee` VALUES ('48', '5', '12', '140.00', '每课时', '2016-10-11 17:57:15');

-- ----------------------------
-- Table structure for `tb_regorder`
-- ----------------------------
DROP TABLE IF EXISTS `tb_regorder`;
CREATE TABLE `tb_regorder` (
  `id` varchar(100) NOT NULL,
  `studentRegId` varchar(100) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `type` tinyint(4) DEFAULT '0' COMMENT '订单类型	0新增，1续报，2新生',
  `regNumber` int(11) DEFAULT '0',
  `total` decimal(10,2) DEFAULT '0.00',
  `mustCost` decimal(10,2) DEFAULT '0.00',
  `actualCost` decimal(10,2) DEFAULT '0.00',
  `discountCost` decimal(10,2) DEFAULT '0.00',
  `uncollected` decimal(10,2) DEFAULT '0.00',
  `createTime` datetime DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `state` tinyint(4) DEFAULT '0' COMMENT '状态	0有效，1无效',
  PRIMARY KEY (`id`),
  KEY `stureg_studentRegId` (`studentRegId`),
  CONSTRAINT `stureg_studentRegId` FOREIGN KEY (`studentRegId`) REFERENCES `tb_studentregistration` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_regorder
-- ----------------------------
INSERT INTO `tb_regorder` VALUES ('2016101815232298481168', '2016101815232290651392', '2016-10-18 15:23:22', '0', '2', '320.00', '320.00', '320.00', '0.00', '0.00', '2016-10-18 15:23:22', '', '0');
INSERT INTO `tb_regorder` VALUES ('2016101815252734321441', '2016101815252734340902', '2016-10-18 15:25:27', '0', '10', '1600.00', '1600.00', '0.00', '0.00', '0.00', '2016-10-18 15:25:27', '', '0');
INSERT INTO `tb_regorder` VALUES ('2016101815322326546715', '201610181532232652680', '2016-10-18 15:38:24', '0', '8', '300.00', '300.00', '300.00', '0.00', '0.00', '2016-10-18 15:32:23', '|', '0');
INSERT INTO `tb_regorder` VALUES ('2016101815372198444513', '2016101815372198489190', '2016-10-18 15:37:21', '0', '8', '300.00', '300.00', '0.00', '0.00', '0.00', '2016-10-18 15:37:21', '', '0');
INSERT INTO `tb_regorder` VALUES ('2016101815393381256761', '2016101815393379695035', '2016-10-18 15:39:33', '0', '8', '300.00', '300.00', '0.00', '0.00', '0.00', '2016-10-18 15:39:33', '', '0');
INSERT INTO `tb_regorder` VALUES ('2016101815395981271810', '2016101815395981294011', '2016-10-18 15:39:59', '0', '8', '300.00', '300.00', '0.00', '0.00', '0.00', '2016-10-18 15:39:59', '', '0');
INSERT INTO `tb_regorder` VALUES ('20161018154027234435', '2016101815402723457413', '2016-10-18 15:40:27', '0', '8', '300.00', '300.00', '0.00', '0.00', '0.00', '2016-10-18 15:40:27', '', '0');
INSERT INTO `tb_regorder` VALUES ('2016101815413459358624', '201610181541345937044', '2016-10-18 15:41:34', '0', '27', '1012.50', '1012.50', '1012.50', '0.00', '0.00', '2016-10-18 15:41:34', '', '0');
INSERT INTO `tb_regorder` VALUES ('20161018154348933821', '201610181543489312314', '2016-10-18 15:43:48', '0', '10', '375.00', '375.00', '375.00', '0.00', '0.00', '2016-10-18 15:43:48', '', '0');
INSERT INTO `tb_regorder` VALUES ('201610181545895394493', '201610181545893715521', '2016-10-18 15:45:08', '0', '27', '1012.50', '1012.50', '1012.50', '0.00', '0.00', '2016-10-18 15:45:08', '', '0');
INSERT INTO `tb_regorder` VALUES ('201610181546409390529', '201610181546409390364', '2016-10-19 09:51:43', '0', '7', '262.50', '225.00', '225.00', '37.50', '0.00', '2016-10-18 15:46:40', '|', '0');
INSERT INTO `tb_regorder` VALUES ('2016101815501929650816', '2016101815501929660695', '2016-10-18 15:50:19', '0', '15', '562.50', '562.50', '562.50', '0.00', '0.00', '2016-10-18 15:50:19', '', '0');
INSERT INTO `tb_regorder` VALUES ('2016101815513150091218', '2016101815513150070285', '2016-10-18 15:51:31', '0', '8', '300.00', '300.00', '0.00', '0.00', '0.00', '2016-10-18 15:51:31', '', '0');
INSERT INTO `tb_regorder` VALUES ('201610181552623420942', '201610181552623474652', '2016-10-18 15:52:06', '0', '8', '300.00', '300.00', '0.00', '0.00', '0.00', '2016-10-18 15:52:06', '', '0');
INSERT INTO `tb_regorder` VALUES ('201610181637365695304', '201610181637365670755', '2016-10-18 16:37:03', '0', '8', '300.00', '300.00', '0.00', '0.00', '0.00', '2016-10-18 16:37:03', '', '0');
INSERT INTO `tb_regorder` VALUES ('2016101816393693795152', '2016101816393693745848', '2016-10-18 16:39:36', '0', '8', '300.00', '300.00', '300.00', '0.00', '0.00', '2016-10-18 16:39:36', '', '0');
INSERT INTO `tb_regorder` VALUES ('2016101816404914014401', '2016101816404914085190', '2016-10-18 16:40:49', '0', '8', '300.00', '300.00', '0.00', '0.00', '0.00', '2016-10-18 16:40:49', '', '0');
INSERT INTO `tb_regorder` VALUES ('2016101816423275018915', '2016101816423273498808', '2016-10-18 16:42:32', '0', '8', '300.00', '300.00', '0.00', '0.00', '0.00', '2016-10-18 16:42:32', '', '0');
INSERT INTO `tb_regorder` VALUES ('20161018165114649691', '20161018165114636008', '2016-10-19 14:40:07', '0', '32', '1200.00', '1200.00', '1200.00', '0.00', '0.00', '2016-10-18 16:51:01', '|', '0');
INSERT INTO `tb_regorder` VALUES ('201610181653254643102', '201610181653254643140', '2016-10-18 16:54:20', '0', '25', '937.50', '937.50', '937.50', '0.00', '0.00', '2016-10-18 16:53:25', '|', '0');
INSERT INTO `tb_regorder` VALUES ('2016101816533453199887', '201610181653345318167', '2016-10-18 16:53:34', '0', '8', '300.00', '300.00', '300.00', '0.00', '0.00', '2016-10-18 16:53:34', '', '0');
INSERT INTO `tb_regorder` VALUES ('2016101817301634398688', '2016101817301634388170', '2016-10-18 17:30:16', '0', '8', '300.00', '300.00', '0.00', '0.00', '0.00', '2016-10-18 17:30:16', '', '0');
INSERT INTO `tb_regorder` VALUES ('201610181731232882900', '201610181731231218763', '2016-10-18 17:31:02', '0', '8', '300.00', '300.00', '300.00', '0.00', '0.00', '2016-10-18 17:31:02', '', '0');
INSERT INTO `tb_regorder` VALUES ('2016101817355764073289', '2016101817355764082886', '2016-10-18 17:35:57', '0', '8', '300.00', '300.00', '0.00', '0.00', '0.00', '2016-10-18 17:35:57', '', '0');
INSERT INTO `tb_regorder` VALUES ('201610181736583122994', '2016101817365831265385', '2016-10-18 17:36:58', '0', '25', '937.50', '937.50', '937.50', '0.00', '0.00', '2016-10-18 17:36:58', '', '0');
INSERT INTO `tb_regorder` VALUES ('201610181741893760726', '201610181741893741615', '2016-10-18 17:41:08', '0', '25', '937.50', '937.50', '937.50', '0.00', '0.00', '2016-10-18 17:41:08', '', '0');
INSERT INTO `tb_regorder` VALUES ('2016101817423390634065', '2016101817423390611233', '2016-10-18 17:42:33', '0', '26', '975.00', '975.00', '975.00', '0.00', '0.00', '2016-10-18 17:42:33', '', '0');
INSERT INTO `tb_regorder` VALUES ('2016101817443475078735', '2016101817443475071412', '2016-10-18 17:44:34', '0', '8', '300.00', '300.00', '0.00', '0.00', '0.00', '2016-10-18 17:44:34', '', '0');
INSERT INTO `tb_regorder` VALUES ('201610181745267183663', '2016101817452671893057', '2016-10-18 17:45:26', '0', '8', '300.00', '300.00', '300.00', '0.00', '0.00', '2016-10-18 17:45:26', '', '0');
INSERT INTO `tb_regorder` VALUES ('2016101817462943715523', '2016101817462943744424', '2016-10-18 17:46:29', '0', '8', '300.00', '300.00', '300.00', '0.00', '0.00', '2016-10-18 17:46:29', '', '0');
INSERT INTO `tb_regorder` VALUES ('201610181758302187895', '2016101817583021837811', '2016-10-18 17:58:30', '0', '8', '300.00', '300.00', '0.00', '0.00', '0.00', '2016-10-18 17:58:30', '', '0');
INSERT INTO `tb_regorder` VALUES ('2016101817591578187018', '2016101817591578119324', '2016-10-18 17:59:15', '0', '8', '300.00', '300.00', '300.00', '0.00', '0.00', '2016-10-18 17:59:15', '', '0');
INSERT INTO `tb_regorder` VALUES ('201610181803593764485', '201610181803593783823', '2016-10-18 18:00:35', '0', '8', '300.00', '300.00', '300.00', '0.00', '0.00', '2016-10-18 18:00:35', '', '0');
INSERT INTO `tb_regorder` VALUES ('2016101818111731226554', '2016101818111731291544', '2016-10-18 18:11:17', '0', '14', '525.00', '525.00', '525.00', '0.00', '0.00', '2016-10-18 18:11:17', '', '0');
INSERT INTO `tb_regorder` VALUES ('2016101818122893737547', '201610181812289371082', '2016-10-18 18:12:28', '0', '8', '300.00', '300.00', '0.00', '0.00', '0.00', '2016-10-18 18:12:28', '', '0');
INSERT INTO `tb_regorder` VALUES ('2016101818135565614757', '2016101818135565673588', '2016-10-18 18:13:55', '0', '6', '225.00', '225.00', '225.00', '0.00', '0.00', '2016-10-18 18:13:55', '', '0');
INSERT INTO `tb_regorder` VALUES ('201610181813945392692', '20161018181394536254', '2016-10-18 18:01:39', '0', '8', '300.00', '300.00', '0.00', '0.00', '0.00', '2016-10-18 18:01:39', '', '0');
INSERT INTO `tb_regorder` VALUES ('2016101818145557814016', '2016101818145557898090', '2016-10-18 18:14:55', '0', '8', '300.00', '300.00', '0.00', '0.00', '0.00', '2016-10-18 18:14:55', '', '0');
INSERT INTO `tb_regorder` VALUES ('201610181817375627056', '2016101818173756293329', '2016-10-18 18:18:21', '0', '8', '300.00', '250.00', '300.00', '50.00', '0.00', '2016-10-18 18:17:37', '|', '0');
INSERT INTO `tb_regorder` VALUES ('20161018181848515648', '2016101818184851532609', '2016-10-18 18:18:48', '0', '8', '300.00', '300.00', '0.00', '0.00', '0.00', '2016-10-18 18:18:48', '', '0');
INSERT INTO `tb_regorder` VALUES ('2016101818194262546999', '2016101818194262592564', '2016-10-18 18:19:42', '0', '8', '300.00', '300.00', '0.00', '0.00', '0.00', '2016-10-18 18:19:42', '', '0');
INSERT INTO `tb_regorder` VALUES ('201610181821714057492', '201610181821714077082', '2016-10-18 18:02:17', '0', '8', '300.00', '300.00', '300.00', '0.00', '0.00', '2016-10-18 18:02:17', '', '0');
INSERT INTO `tb_regorder` VALUES ('2016101818241675030009', '2016101818241675037680', '2016-10-18 18:24:16', '0', '10', '1600.00', '1600.00', '0.00', '0.00', '0.00', '2016-10-18 18:24:16', '', '0');
INSERT INTO `tb_regorder` VALUES ('201610181831040636159', '201610181831040627519', '2016-10-18 18:03:10', '0', '8', '300.00', '300.00', '0.00', '0.00', '0.00', '2016-10-18 18:03:10', '', '0');
INSERT INTO `tb_regorder` VALUES ('201610181834542192238', '201610181834542147209', '2016-10-18 18:03:45', '0', '8', '300.00', '300.00', '0.00', '0.00', '0.00', '2016-10-18 18:03:45', '', '0');
INSERT INTO `tb_regorder` VALUES ('201610182019345340425', '201610182019345397698', '2016-10-18 20:19:03', '0', '22', '1100.00', '1100.00', '1100.00', '0.00', '0.00', '2016-10-18 20:19:03', '', '0');
INSERT INTO `tb_regorder` VALUES ('2016101820202868725926', '2016101820202868724544', '2016-10-18 20:20:28', '0', '2', '100.00', '100.00', '100.00', '0.00', '0.00', '2016-10-18 20:20:28', '', '0');
INSERT INTO `tb_regorder` VALUES ('201610182021207031407', '2016101820212070341134', '2016-10-18 20:21:20', '0', '22', '1100.00', '1100.00', '1100.00', '0.00', '0.00', '2016-10-18 20:21:20', '', '0');
INSERT INTO `tb_regorder` VALUES ('2016101820405818754910', '20161018204058187319', '2016-10-18 20:40:58', '0', '8', '400.00', '400.00', '0.00', '0.00', '0.00', '2016-10-18 20:40:58', '', '0');
INSERT INTO `tb_regorder` VALUES ('2016101820423565641593', '2016101820423565694458', '2016-10-18 20:42:35', '0', '16', '800.00', '800.00', '800.00', '0.00', '0.00', '2016-10-18 20:42:35', '', '0');
INSERT INTO `tb_regorder` VALUES ('2016101820452657863900', '2016101820452657812092', '2016-10-18 20:45:26', '0', '8', '400.00', '400.00', '0.00', '0.00', '0.00', '2016-10-18 20:45:26', '', '0');
INSERT INTO `tb_regorder` VALUES ('2016101820471023459353', '2016101820471023463736', '2016-10-18 20:47:10', '0', '8', '400.00', '400.00', '0.00', '0.00', '0.00', '2016-10-18 20:47:10', '', '0');
INSERT INTO `tb_regorder` VALUES ('2016101820484731294807', '2016101820484731252023', '2016-10-18 20:48:47', '0', '8', '400.00', '400.00', '0.00', '0.00', '0.00', '2016-10-18 20:48:47', '', '0');
INSERT INTO `tb_regorder` VALUES ('201610182049121540956', '201610182049121567624', '2016-10-18 20:49:12', '0', '8', '400.00', '400.00', '400.00', '0.00', '0.00', '2016-10-18 20:49:12', '', '0');
INSERT INTO `tb_regorder` VALUES ('201610182054297893475', '201610182054297879525', '2016-10-18 20:54:29', '0', '8', '400.00', '400.00', '0.00', '0.00', '0.00', '2016-10-18 20:54:29', '', '0');
INSERT INTO `tb_regorder` VALUES ('201610182117121820727', '201610182117121879153', '2016-10-18 21:20:04', '0', '1', '50.00', '50.00', '50.00', '0.00', '0.00', '2016-10-18 21:17:01', '', '1');
INSERT INTO `tb_regorder` VALUES ('2016101821202181219672', '201610182120218128967', '2016-10-18 21:20:21', '0', '1', '50.00', '50.00', '50.00', '0.00', '0.00', '2016-10-18 21:20:21', '', '0');
INSERT INTO `tb_regorder` VALUES ('2016101821245110986584', '2016101821245110996772', '2016-10-18 21:24:51', '0', '8', '400.00', '400.00', '0.00', '0.00', '0.00', '2016-10-18 21:24:51', '', '0');
INSERT INTO `tb_regorder` VALUES ('201610182126123444878', '201610182126121885582', '2016-10-18 21:26:41', '0', '32', '1600.00', '1400.00', '1400.00', '200.00', '0.00', '2016-10-18 21:26:01', '|', '0');
INSERT INTO `tb_regorder` VALUES ('201610182127198453165', '201610182127198443660', '2016-10-18 21:27:01', '0', '7', '350.00', '350.00', '350.00', '0.00', '0.00', '2016-10-18 21:27:01', '', '0');
INSERT INTO `tb_regorder` VALUES ('201610182132232843863', '201610182132232899885', '2016-10-18 21:05:19', '0', '5', '900.00', '850.00', '900.00', '50.00', '0.00', '2016-10-18 21:03:22', '|', '1');
INSERT INTO `tb_regorder` VALUES ('201610182146734347290', '201610182146734355308', '2016-10-18 21:46:07', '0', '22', '825.00', '825.00', '825.00', '0.00', '0.00', '2016-10-18 21:46:07', '', '0');
INSERT INTO `tb_regorder` VALUES ('201610182147529613904', '201610182147528199322', '2016-10-18 21:47:05', '0', '9', '337.50', '337.50', '337.50', '0.00', '0.00', '2016-10-18 21:47:05', '', '0');
INSERT INTO `tb_regorder` VALUES ('201610182148026563100', '201610182148026563856', '2016-10-18 21:48:00', '0', '6', '225.00', '225.00', '225.00', '0.00', '0.00', '2016-10-18 21:48:00', '', '0');
INSERT INTO `tb_regorder` VALUES ('201610182151340639819', '201610182151340680009', '2016-10-18 21:51:03', '0', '10', '1800.00', '1800.00', '0.00', '0.00', '0.00', '2016-10-18 21:51:03', '', '0');
INSERT INTO `tb_regorder` VALUES ('201610182153451584734', '201610182153451518694', '2016-10-18 21:55:07', '0', '8', '1280.00', '1040.00', '1040.00', '240.00', '0.00', '2016-10-18 21:53:04', '||', '0');
INSERT INTO `tb_regorder` VALUES ('201610182154395368400', '201610182154395359390', '2016-10-18 21:06:12', '0', '8', '1440.00', '1440.00', '0.00', '0.00', '0.00', '2016-10-18 21:05:43', '', '1');
INSERT INTO `tb_regorder` VALUES ('201610182157865640786', '201610182157865676523', '2016-10-18 21:58:05', '0', '9', '1440.00', '1170.00', '1170.00', '270.00', '0.00', '2016-10-18 21:57:08', '|', '0');
INSERT INTO `tb_regorder` VALUES ('20161018216459335267', '20161018216459310615', '2016-10-18 21:07:30', '0', '5', '900.00', '850.00', '850.00', '50.00', '0.00', '2016-10-18 21:06:45', '|', '0');
INSERT INTO `tb_regorder` VALUES ('201610182194057877813', '201610182194057879953', '2016-10-18 21:09:40', '0', '8', '1440.00', '1440.00', '1440.00', '0.00', '0.00', '2016-10-18 21:09:40', '', '0');
INSERT INTO `tb_regorder` VALUES ('201610182216740624506', '201610182216740621589', '2016-10-18 22:16:07', '0', '9', '810.00', '810.00', '810.00', '0.00', '0.00', '2016-10-18 22:16:07', '', '0');
INSERT INTO `tb_regorder` VALUES ('2016101822183448439284', '2016101822183448447503', '2016-10-18 22:18:34', '0', '9', '810.00', '810.00', '810.00', '0.00', '0.00', '2016-10-18 22:18:34', '', '0');
INSERT INTO `tb_regorder` VALUES ('20161018222158932639', '201610182221589394896', '2016-10-18 22:21:58', '0', '8', '720.00', '720.00', '720.00', '0.00', '0.00', '2016-10-18 22:21:58', '', '0');
INSERT INTO `tb_regorder` VALUES ('2016101822242525053589', '2016101822242525039322', '2016-10-18 22:24:25', '0', '18', '1620.00', '1620.00', '620.00', '0.00', '0.00', '2016-10-18 22:24:25', '', '0');
INSERT INTO `tb_regorder` VALUES ('20161018222929536673', '201610182229295324204', '2016-10-18 22:30:17', '0', '8', '720.00', '700.00', '700.00', '20.00', '0.00', '2016-10-18 22:29:02', '700包拼音|', '0');
INSERT INTO `tb_regorder` VALUES ('2016101910111575089227', '2016101910111573428515', '2016-10-19 10:12:13', '0', '11', '1760.00', '1100.00', '1100.00', '660.00', '0.00', '2016-10-19 10:11:15', '|', '0');
INSERT INTO `tb_regorder` VALUES ('201610191015494668101', '201610191015494681327', '2016-10-19 10:15:49', '0', '8', '300.00', '300.00', '0.00', '0.00', '0.00', '2016-10-19 10:15:49', '', '0');
INSERT INTO `tb_regorder` VALUES ('201610191018183144907', '201610191018183115596', '2016-10-19 10:18:18', '0', '8', '300.00', '300.00', '300.00', '0.00', '0.00', '2016-10-19 10:18:18', '', '0');
INSERT INTO `tb_regorder` VALUES ('2016101910183770343329', '2016101910183770354099', '2016-10-19 10:19:51', '0', '6', '225.00', '187.50', '187.50', '37.50', '0.00', '2016-10-19 10:18:37', '|', '0');
INSERT INTO `tb_regorder` VALUES ('20161019102018624320', '201610191020186230536', '2016-10-19 10:20:18', '0', '8', '300.00', '300.00', '0.00', '0.00', '0.00', '2016-10-19 10:20:18', '', '0');
INSERT INTO `tb_regorder` VALUES ('2016101910222015618306', '2016101910222015656144', '2016-10-19 10:22:20', '0', '29', '1087.50', '1087.50', '1087.50', '0.00', '0.00', '2016-10-19 10:22:20', '', '0');
INSERT INTO `tb_regorder` VALUES ('201610191024166289168', '201610191024166298030', '2016-10-19 10:24:16', '0', '8', '300.00', '300.00', '0.00', '0.00', '0.00', '2016-10-19 10:24:16', '', '0');
INSERT INTO `tb_regorder` VALUES ('2016101910255390654151', '2016101910255390685388', '2016-10-19 10:25:53', '0', '8', '300.00', '300.00', '0.00', '0.00', '0.00', '2016-10-19 10:25:53', '', '0');
INSERT INTO `tb_regorder` VALUES ('2016101910264332889671', '2016101910264332872031', '2016-10-19 10:26:43', '0', '32', '1200.00', '1200.00', '1200.00', '0.00', '0.00', '2016-10-19 10:26:43', '', '0');
INSERT INTO `tb_regorder` VALUES ('2016101910275123430083', '2016101910275123429592', '2016-10-19 10:27:51', '0', '8', '300.00', '300.00', '300.00', '0.00', '0.00', '2016-10-19 10:27:51', '', '0');
INSERT INTO `tb_regorder` VALUES ('2016101910295032884475', '2016101910295032881522', '2016-10-19 10:29:50', '0', '8', '300.00', '300.00', '0.00', '0.00', '0.00', '2016-10-19 10:29:50', '', '0');
INSERT INTO `tb_regorder` VALUES ('201610191042126526747', '201610191042126556567', '2016-10-19 10:04:21', '0', '10', '1600.00', '1600.00', '0.00', '0.00', '0.00', '2016-10-19 10:04:21', '', '0');
INSERT INTO `tb_regorder` VALUES ('201610191055241558687', '20161019105524091609', '2016-10-19 10:55:24', '0', '8', '300.00', '300.00', '0.00', '0.00', '0.00', '2016-10-19 10:55:24', '', '0');
INSERT INTO `tb_regorder` VALUES ('201610191058182858219', '201610191058182812811', '2016-10-19 10:58:01', '0', '8', '300.00', '300.00', '0.00', '0.00', '0.00', '2016-10-19 10:58:01', '', '0');
INSERT INTO `tb_regorder` VALUES ('2016101910583378120255', '201610191058337811690', '2016-10-19 10:58:33', '0', '8', '300.00', '300.00', '300.00', '0.00', '0.00', '2016-10-19 10:58:33', '', '0');
INSERT INTO `tb_regorder` VALUES ('2016101910592931267912', '2016101910592931293151', '2016-10-19 10:59:29', '0', '8', '300.00', '300.00', '300.00', '0.00', '0.00', '2016-10-19 10:59:29', '', '0');
INSERT INTO `tb_regorder` VALUES ('20161019111144090588', '2016101911114398416820', '2016-10-19 11:11:44', '0', '10', '1400.00', '1400.00', '0.00', '0.00', '0.00', '2016-10-19 11:11:44', '', '0');
INSERT INTO `tb_regorder` VALUES ('201610191112196292484', '201610191112196270305', '2016-10-19 11:12:19', '0', '10', '1400.00', '1400.00', '0.00', '0.00', '0.00', '2016-10-19 11:12:19', '', '0');
INSERT INTO `tb_regorder` VALUES ('20161019113217346695', '201610191132171849955', '2016-10-19 11:03:21', '0', '8', '300.00', '300.00', '300.00', '0.00', '0.00', '2016-10-19 11:03:21', '', '0');
INSERT INTO `tb_regorder` VALUES ('201610191141159380138', '201610191141159373752', '2016-10-19 11:04:11', '0', '8', '300.00', '300.00', '300.00', '0.00', '0.00', '2016-10-19 11:04:11', '', '0');
INSERT INTO `tb_regorder` VALUES ('201610191151545341799', '201610191151545360729', '2016-10-19 11:05:15', '0', '8', '300.00', '300.00', '300.00', '0.00', '0.00', '2016-10-19 11:05:15', '', '0');
INSERT INTO `tb_regorder` VALUES ('201610191174371816707', '201610191174371886196', '2016-10-19 11:07:43', '0', '8', '300.00', '300.00', '0.00', '0.00', '0.00', '2016-10-19 11:07:43', '', '0');

-- ----------------------------
-- Table structure for `tb_regorderdetail`
-- ----------------------------
DROP TABLE IF EXISTS `tb_regorderdetail`;
CREATE TABLE `tb_regorderdetail` (
  `id` varchar(255) NOT NULL DEFAULT '',
  `regorderId` varchar(255) DEFAULT NULL,
  `payId` varchar(255) DEFAULT NULL,
  `total` decimal(10,2) DEFAULT '0.00',
  `createTime` datetime DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `regord_regorderId` (`regorderId`),
  KEY `regord_payId` (`payId`),
  CONSTRAINT `regord_payId` FOREIGN KEY (`payId`) REFERENCES `tb_studentpay` (`id`),
  CONSTRAINT `regord_regorderId` FOREIGN KEY (`regorderId`) REFERENCES `tb_regorder` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_regorderdetail
-- ----------------------------
INSERT INTO `tb_regorderdetail` VALUES ('201610181524753135763', '2016101815232298481168', '201610181524751536862', '320.00', '2016-08-10 12:00:00', '');
INSERT INTO `tb_regorderdetail` VALUES ('2016101815331367195025', '2016101815322326546715', '2016101815331367128564', '112.50', '2016-10-12 15:30:04', '');
INSERT INTO `tb_regorderdetail` VALUES ('201610181538566212159', '2016101815322326546715', '201610181538566241112', '187.50', '2016-10-15 15:35:15', '');
INSERT INTO `tb_regorderdetail` VALUES ('2016101815422767158103', '2016101815413459358624', '2016101815422767144803', '1012.50', '2016-10-16 15:38:26', '');
INSERT INTO `tb_regorderdetail` VALUES ('2016101815442889040730', '20161018154348933821', '2016101815442889099967', '375.00', '2016-09-07 00:00:00', '');
INSERT INTO `tb_regorderdetail` VALUES ('2016101815454043779284', '201610181545895394493', '2016101815454043741462', '1012.50', '2016-10-13 15:41:26', '');
INSERT INTO `tb_regorderdetail` VALUES ('201610181547971820025', '201610181546409390529', '20161018154797182367', '225.00', '2016-10-16 15:42:50', '');
INSERT INTO `tb_regorderdetail` VALUES ('2016101815505518798608', '2016101815501929650816', '2016101815505518740652', '562.50', '2016-10-13 15:46:19', '');
INSERT INTO `tb_regorderdetail` VALUES ('201610181640102969985', '2016101816393693795152', '2016101816401029695179', '300.00', '2016-10-13 16:38:12', '');
INSERT INTO `tb_regorderdetail` VALUES ('2016101816535865671088', '201610181653254643102', '2016101816535865699764', '900.00', '2016-10-15 16:51:04', '');
INSERT INTO `tb_regorderdetail` VALUES ('2016101816541750093979', '2016101816533453199887', '2016101816541750049226', '300.00', '2016-10-10 16:54:05', '');
INSERT INTO `tb_regorderdetail` VALUES ('2016101816544971858933', '201610181653254643102', '2016101816544971836581', '37.50', '2016-10-14 16:51:50', '');
INSERT INTO `tb_regorderdetail` VALUES ('20161018173132152325', '201610181731232882900', '201610181731321556963', '300.00', '2016-10-14 17:30:30', '');
INSERT INTO `tb_regorderdetail` VALUES ('2016101817385695339499', '201610181736583122994', '2016101817385695399224', '937.50', '2016-10-09 17:37:54', '');
INSERT INTO `tb_regorderdetail` VALUES ('2016101817415190624850', '201610181741893760726', '2016101817415190632822', '937.50', '2016-10-15 17:40:50', '');
INSERT INTO `tb_regorderdetail` VALUES ('201610181743253591413', '2016101817423390634065', '2016101817432535945257', '975.00', '2016-10-17 17:42:24', '');
INSERT INTO `tb_regorderdetail` VALUES ('2016101817455042160594', '201610181745267183663', '2016101817455042156519', '300.00', '2016-10-14 17:44:50', '');
INSERT INTO `tb_regorderdetail` VALUES ('2016101817465695348562', '2016101817462943715523', '2016101817465695387593', '300.00', '2016-10-12 17:45:56', '');
INSERT INTO `tb_regorderdetail` VALUES ('201610181801154638118', '2016101817591578187018', '201610181801154693292', '300.00', '2016-10-07 17:58:59', '');
INSERT INTO `tb_regorderdetail` VALUES ('20161018181240645070', '201610181803593764485', '2016101818124069761', '300.00', '2016-10-05 18:00:01', '');
INSERT INTO `tb_regorderdetail` VALUES ('2016101818131071837060', '2016101818111731226554', '2016101818131071887942', '525.00', '2016-10-07 18:12:07', '');
INSERT INTO `tb_regorderdetail` VALUES ('2016101818143053122780', '2016101818135565614757', '2016101818143053128905', '225.00', '2016-10-15 18:13:24', '');
INSERT INTO `tb_regorderdetail` VALUES ('2016101818181168720674', '201610181817375627056', '2016101818181168761139', '300.00', '2016-10-10 18:17:09', '');
INSERT INTO `tb_regorderdetail` VALUES ('201610181824817149617', '201610181821714057492', '20161018182481718900', '300.00', '2016-10-10 18:01:47', '');
INSERT INTO `tb_regorderdetail` VALUES ('2016101820193139066647', '201610182019345340425', '2016101820193139048054', '1100.00', '2016-10-12 20:18:31', '');
INSERT INTO `tb_regorderdetail` VALUES ('2016101820205568795895', '2016101820202868725926', '2016101820205568785683', '100.00', '2016-10-15 20:19:51', '');
INSERT INTO `tb_regorderdetail` VALUES ('20161018202143098778', '201610182021207031407', '20161018202143029321', '1100.00', '2016-10-13 20:20:43', '');
INSERT INTO `tb_regorderdetail` VALUES ('2016101820431215654563', '2016101820423565641593', '2016101820431215660573', '800.00', '2016-10-12 20:42:13', '');
INSERT INTO `tb_regorderdetail` VALUES ('2016101820514990618352', '201610182049121540956', '2016101820514990685162', '400.00', '2016-10-20 20:50:49', '');
INSERT INTO `tb_regorderdetail` VALUES ('2016101821101295363254', '201610182194057877813', '2016101821101295361843', '1440.00', '2016-10-12 21:09:10', '');
INSERT INTO `tb_regorderdetail` VALUES ('2016101821172923434346', '201610182117121820727', '2016101821172923489048', '50.00', '2016-10-12 21:16:29', '');
INSERT INTO `tb_regorderdetail` VALUES ('20161018212053068315', '2016101821202181219672', '2016101821205304115', '50.00', '2016-10-08 21:19:50', '');
INSERT INTO `tb_regorderdetail` VALUES ('2016101821263237599582', '201610182126123444878', '2016101821263237589093', '1400.00', '2016-10-13 21:25:28', '');
INSERT INTO `tb_regorderdetail` VALUES ('2016101821272653111238', '201610182127198453165', '2016101821272653111381', '350.00', '2016-10-08 21:26:24', '');
INSERT INTO `tb_regorderdetail` VALUES ('201610182135839088283', '201610182132232843863', '201610182135839040161', '900.00', '2016-09-10 00:00:00', '');
INSERT INTO `tb_regorderdetail` VALUES ('2016101821463637530920', '201610182146734347290', '2016101821463637519378', '825.00', '2016-10-13 21:45:24', '');
INSERT INTO `tb_regorderdetail` VALUES ('201610182147379321490', '201610182147529613904', '201610182147379334795', '337.50', '2016-10-06 21:46:26', '');
INSERT INTO `tb_regorderdetail` VALUES ('2016101821482835976436', '201610182148026563100', '2016101821482835914920', '225.00', '2016-10-08 21:47:16', '');
INSERT INTO `tb_regorderdetail` VALUES ('2016101821545435956055', '201610182153451584734', '2016101821545435985771', '1040.00', '2016-10-13 21:54:46', '');
INSERT INTO `tb_regorderdetail` VALUES ('2016101821574732899812', '201610182157865640786', '2016101821574732814845', '1170.00', '2016-10-15 21:57:43', '');
INSERT INTO `tb_regorderdetail` VALUES ('201610182172065661387', '20161018216459335267', '201610182172065658100', '850.00', '2016-09-09 00:00:00', '');
INSERT INTO `tb_regorderdetail` VALUES ('2016101822163390653992', '201610182216740624506', '2016101822163390665051', '810.00', '2016-10-14 22:16:24', '');
INSERT INTO `tb_regorderdetail` VALUES ('201610182218583190745', '2016101822183448439284', '201610182218583153987', '810.00', '2016-10-03 22:18:48', '');
INSERT INTO `tb_regorderdetail` VALUES ('2016101822222514093675', '20161018222158932639', '2016101822222514049433', '720.00', '2016-10-04 22:22:14', '');
INSERT INTO `tb_regorderdetail` VALUES ('201610182225968770790', '2016101822242525053589', '201610182225968713872', '620.00', '2016-09-10 00:00:00', '');
INSERT INTO `tb_regorderdetail` VALUES ('201610182230928174695', '20161018222929536673', '201610182230928144255', '700.00', '2016-10-15 10:29:22', '');
INSERT INTO `tb_regorderdetail` VALUES ('2016101910115470331857', '2016101910111575089227', '2016101910115470357916', '1100.00', '2016-10-07 10:11:06', '');
INSERT INTO `tb_regorderdetail` VALUES ('201610191019229621754', '201610191018183144907', '201610191019229655459', '300.00', '2016-09-17 00:00:00', '');
INSERT INTO `tb_regorderdetail` VALUES ('2016101910193775078271', '2016101910183770343329', '2016101910193775057144', '187.50', '2016-10-14 10:19:31', '');
INSERT INTO `tb_regorderdetail` VALUES ('201610191023146822262', '2016101910222015618306', '201610191023146816983', '1087.50', '2016-10-09 10:22:42', '');
INSERT INTO `tb_regorderdetail` VALUES ('2016101910271493751771', '2016101910264332889671', '2016101910271493720497', '1200.00', '2016-10-14 10:26:25', '');
INSERT INTO `tb_regorderdetail` VALUES ('2016101910282857812019', '2016101910275123430083', '2016101910282857855162', '300.00', '2016-10-15 10:27:24', '');
INSERT INTO `tb_regorderdetail` VALUES ('201610191059845363357', '2016101910583378120255', '201610191059845376035', '300.00', '2016-10-08 10:57:13', '');
INSERT INTO `tb_regorderdetail` VALUES ('2016101911001576155', '2016101910592931267912', '2016101911001517626', '300.00', '2016-10-15 10:58:07', '');
INSERT INTO `tb_regorderdetail` VALUES ('201610191135320329185', '20161019113217346695', '20161019113532038022', '300.00', '2016-10-17 11:02:00', '');
INSERT INTO `tb_regorderdetail` VALUES ('201610191144696829466', '201610191141159380138', '201610191144696849761', '300.00', '2016-10-10 11:02:52', '');
INSERT INTO `tb_regorderdetail` VALUES ('201610191155381232511', '201610191151545341799', '201610191155381228498', '300.00', '2016-10-08 11:04:01', '');
INSERT INTO `tb_regorderdetail` VALUES ('20161019144123192053', '20161018165114649691', '20161019144123116217', '1200.00', '2016-10-07 14:39:49', '');

-- ----------------------------
-- Table structure for `tb_school`
-- ----------------------------
DROP TABLE IF EXISTS `tb_school`;
CREATE TABLE `tb_school` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_school
-- ----------------------------
INSERT INTO `tb_school` VALUES ('1', '凯里老一中');
INSERT INTO `tb_school` VALUES ('2', '凯里二中');
INSERT INTO `tb_school` VALUES ('3', '凯里三中');
INSERT INTO `tb_school` VALUES ('4', '凯里四中');
INSERT INTO `tb_school` VALUES ('5', '凯里五中');
INSERT INTO `tb_school` VALUES ('6', '凯里六中');
INSERT INTO `tb_school` VALUES ('7', '凯里七中');
INSERT INTO `tb_school` VALUES ('8', '凯里八中');
INSERT INTO `tb_school` VALUES ('9', '凯里九中');
INSERT INTO `tb_school` VALUES ('10', '凯里十中');
INSERT INTO `tb_school` VALUES ('11', '凯里附中');
INSERT INTO `tb_school` VALUES ('12', '鸭塘中学');
INSERT INTO `tb_school` VALUES ('13', '开发区中学');
INSERT INTO `tb_school` VALUES ('14', '凯里博南中学');
INSERT INTO `tb_school` VALUES ('15', '其他');
INSERT INTO `tb_school` VALUES ('16', '凯里新一中');
INSERT INTO `tb_school` VALUES ('17', '振华中学');
INSERT INTO `tb_school` VALUES ('18', '凯里十五中');

-- ----------------------------
-- Table structure for `tb_student`
-- ----------------------------
DROP TABLE IF EXISTS `tb_student`;
CREATE TABLE `tb_student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `sex` tinyint(4) DEFAULT '1' COMMENT '1男，2女',
  `creatTime` datetime DEFAULT NULL,
  `borthday` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=178 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_student
-- ----------------------------
INSERT INTO `tb_student` VALUES ('1', '肖小武', '1', '2016-10-09 09:48:43', null);
INSERT INTO `tb_student` VALUES ('2', '吴佑德', '1', '2016-10-09 11:17:04', null);
INSERT INTO `tb_student` VALUES ('3', '潘梅', '2', '2016-10-09 11:41:58', null);
INSERT INTO `tb_student` VALUES ('4', '杨永怡', '2', '2016-10-09 11:48:20', null);
INSERT INTO `tb_student` VALUES ('5', '余汉春', '2', '2016-10-09 15:28:09', null);
INSERT INTO `tb_student` VALUES ('6', '王靖雯', '2', '2016-10-09 16:43:09', null);
INSERT INTO `tb_student` VALUES ('7', '梁冰冰', '2', '2016-10-09 16:44:14', null);
INSERT INTO `tb_student` VALUES ('8', '刘诗然', '2', '2016-10-09 16:45:00', null);
INSERT INTO `tb_student` VALUES ('9', '陈兴甜', '2', '2016-10-09 16:46:00', null);
INSERT INTO `tb_student` VALUES ('10', '王秋秋', '2', '2016-10-09 16:46:39', null);
INSERT INTO `tb_student` VALUES ('11', '王镜', '1', '2016-10-09 16:48:18', null);
INSERT INTO `tb_student` VALUES ('12', '付继开', '1', '2016-10-09 16:50:49', null);
INSERT INTO `tb_student` VALUES ('13', '文小燕', '2', '2016-10-09 16:51:28', null);
INSERT INTO `tb_student` VALUES ('14', '张开焱', '1', '2016-10-09 16:51:45', null);
INSERT INTO `tb_student` VALUES ('15', '张丽洁', '2', '2016-10-09 16:52:47', null);
INSERT INTO `tb_student` VALUES ('16', '杨名芳', '2', '2016-10-09 16:53:00', null);
INSERT INTO `tb_student` VALUES ('17', '宋佳妮', '2', '2016-10-09 16:53:39', null);
INSERT INTO `tb_student` VALUES ('18', '顾珍莲', '2', '2016-10-09 16:53:59', null);
INSERT INTO `tb_student` VALUES ('19', '唐昭华', '1', '2016-10-09 16:55:01', null);
INSERT INTO `tb_student` VALUES ('20', '吴义焓', '1', '2016-10-09 16:56:20', null);
INSERT INTO `tb_student` VALUES ('21', '李微微', '1', '2016-10-09 16:57:51', null);
INSERT INTO `tb_student` VALUES ('22', '石秋霞', '1', '2016-10-09 16:58:38', null);
INSERT INTO `tb_student` VALUES ('23', '罗江城', '1', '2016-10-09 16:59:58', null);
INSERT INTO `tb_student` VALUES ('24', '熊晓燕', '2', '2016-10-09 17:01:09', null);
INSERT INTO `tb_student` VALUES ('25', '吴洋', '2', '2016-10-09 17:02:04', null);
INSERT INTO `tb_student` VALUES ('26', '杨芳芳', '2', '2016-10-09 17:02:55', null);
INSERT INTO `tb_student` VALUES ('27', '杨小凤', '2', '2016-10-09 17:03:41', null);
INSERT INTO `tb_student` VALUES ('28', '唐正龙', '1', '2016-10-09 17:04:32', null);
INSERT INTO `tb_student` VALUES ('29', '张轩华', '1', '2016-10-09 17:05:34', null);
INSERT INTO `tb_student` VALUES ('30', '张殊荣', '1', '2016-10-09 17:07:02', null);
INSERT INTO `tb_student` VALUES ('31', '杨芳芳1', '2', '2016-10-09 17:09:44', null);
INSERT INTO `tb_student` VALUES ('32', '顾婕', '2', '2016-10-09 17:19:58', null);
INSERT INTO `tb_student` VALUES ('33', '周书阳', '1', '2016-10-09 17:20:09', null);
INSERT INTO `tb_student` VALUES ('34', '台庆菊', '2', '2016-10-09 17:20:58', null);
INSERT INTO `tb_student` VALUES ('35', '杨静媛', '2', '2016-10-09 17:21:44', null);
INSERT INTO `tb_student` VALUES ('36', '张欣然', '1', '2016-10-09 17:22:45', null);
INSERT INTO `tb_student` VALUES ('37', '吴金凤', '2', '2016-10-09 17:25:31', null);
INSERT INTO `tb_student` VALUES ('38', '杨双然', '1', '2016-10-09 17:26:03', null);
INSERT INTO `tb_student` VALUES ('39', '张振楠', '1', '2016-10-09 17:27:08', null);
INSERT INTO `tb_student` VALUES ('40', '时小义', '2', '2016-10-09 17:27:31', null);
INSERT INTO `tb_student` VALUES ('41', '杨德重', '1', '2016-10-09 17:27:49', null);
INSERT INTO `tb_student` VALUES ('42', '黄雅诗', '2', '2016-10-09 17:28:59', null);
INSERT INTO `tb_student` VALUES ('43', '潘雨萱', '2', '2016-10-09 17:29:53', null);
INSERT INTO `tb_student` VALUES ('44', '姚元斌', '1', '2016-10-09 17:31:39', null);
INSERT INTO `tb_student` VALUES ('45', '陶绍宇', '1', '2016-10-09 17:34:10', null);
INSERT INTO `tb_student` VALUES ('46', '王一帆', '1', '2016-10-09 17:40:33', null);
INSERT INTO `tb_student` VALUES ('47', '吴寿菊 初二', '1', '2016-10-09 17:41:17', null);
INSERT INTO `tb_student` VALUES ('48', '吴万里', '1', '2016-10-09 17:42:09', null);
INSERT INTO `tb_student` VALUES ('49', '王秋秋', '1', '2016-10-09 17:44:28', null);
INSERT INTO `tb_student` VALUES ('50', '文秀吉', '1', '2016-10-09 17:45:21', null);
INSERT INTO `tb_student` VALUES ('51', '欧阳蕊琦', '1', '2016-10-09 17:46:53', null);
INSERT INTO `tb_student` VALUES ('52', '文翰楼', '1', '2016-10-11 16:33:08', null);
INSERT INTO `tb_student` VALUES ('53', '万珊', '2', '2016-10-11 17:09:20', null);
INSERT INTO `tb_student` VALUES ('54', '胡金凯', '1', '2016-10-12 09:54:41', null);
INSERT INTO `tb_student` VALUES ('55', '杨千慧', '2', '2016-10-12 10:00:54', null);
INSERT INTO `tb_student` VALUES ('56', '肖俊康', '1', '2016-10-12 10:56:34', null);
INSERT INTO `tb_student` VALUES ('57', '刘隆兴', '1', '2016-10-12 11:21:29', null);
INSERT INTO `tb_student` VALUES ('58', '廖明琳', '2', '2016-10-12 11:23:24', null);
INSERT INTO `tb_student` VALUES ('59', '谢闽东', '1', '2016-10-12 11:23:47', null);
INSERT INTO `tb_student` VALUES ('60', '杨欣荣', '2', '2016-10-12 11:27:25', null);
INSERT INTO `tb_student` VALUES ('61', '马梁骏晖', '1', '2016-10-12 11:28:02', null);
INSERT INTO `tb_student` VALUES ('62', '杨欣星', '2', '2016-10-12 11:28:33', null);
INSERT INTO `tb_student` VALUES ('63', '段焕涛', '1', '2016-10-12 11:41:49', null);
INSERT INTO `tb_student` VALUES ('64', '潘光智', '1', '2016-10-12 11:46:44', null);
INSERT INTO `tb_student` VALUES ('65', '陈鑫', '1', '2016-10-12 13:53:55', null);
INSERT INTO `tb_student` VALUES ('66', '明心越', '2', '2016-10-12 13:56:14', null);
INSERT INTO `tb_student` VALUES ('67', '姜英学', '2', '2016-10-12 13:56:44', null);
INSERT INTO `tb_student` VALUES ('68', '王于荆', '2', '2016-10-12 13:57:17', null);
INSERT INTO `tb_student` VALUES ('69', '万乾香', '2', '2016-10-12 13:57:40', null);
INSERT INTO `tb_student` VALUES ('70', '刘小燕', '2', '2016-10-12 13:58:06', null);
INSERT INTO `tb_student` VALUES ('71', '雷佳丽', '2', '2016-10-12 14:12:41', null);
INSERT INTO `tb_student` VALUES ('72', '周子安', '1', '2016-10-12 14:13:37', null);
INSERT INTO `tb_student` VALUES ('73', '李紫渝', '2', '2016-10-12 14:25:30', null);
INSERT INTO `tb_student` VALUES ('74', '袁娟娟', '2', '2016-10-12 14:56:03', null);
INSERT INTO `tb_student` VALUES ('75', '陆真洋', '1', '2016-10-12 15:03:03', null);
INSERT INTO `tb_student` VALUES ('76', '杨谷', '2', '2016-10-12 15:22:32', null);
INSERT INTO `tb_student` VALUES ('77', '邰琳琳', '2', '2016-10-12 15:26:00', null);
INSERT INTO `tb_student` VALUES ('78', '黄尧洁', '2', '2016-10-12 16:29:38', null);
INSERT INTO `tb_student` VALUES ('79', '张婷', '2', '2016-10-12 16:38:52', null);
INSERT INTO `tb_student` VALUES ('80', '向贤梁', '1', '2016-10-12 16:42:45', null);
INSERT INTO `tb_student` VALUES ('81', '龙铧', '1', '2016-10-12 16:44:26', null);
INSERT INTO `tb_student` VALUES ('82', '龙旻', '1', '2016-10-12 16:46:14', null);
INSERT INTO `tb_student` VALUES ('83', '彭超', '1', '2016-10-12 16:55:17', null);
INSERT INTO `tb_student` VALUES ('84', '陈星光', '1', '2016-10-12 16:58:21', null);
INSERT INTO `tb_student` VALUES ('85', '王俊鸿', '1', '2016-10-12 17:02:13', null);
INSERT INTO `tb_student` VALUES ('86', '吴寿菊 高三', '2', '2016-10-12 17:06:08', null);
INSERT INTO `tb_student` VALUES ('87', '李新梅', '2', '2016-10-12 17:10:21', null);
INSERT INTO `tb_student` VALUES ('88', '石秋霞', '2', '2016-10-12 17:12:33', null);
INSERT INTO `tb_student` VALUES ('89', '丁小露', '2', '2016-10-13 11:32:18', null);
INSERT INTO `tb_student` VALUES ('90', '罗森文', '1', '2016-10-13 11:44:43', null);
INSERT INTO `tb_student` VALUES ('91', '杨菊香', '2', '2016-10-14 08:59:29', null);
INSERT INTO `tb_student` VALUES ('92', '佘文瑶', '2', '2016-10-14 09:00:29', null);
INSERT INTO `tb_student` VALUES ('93', '周杨', '2', '2016-10-14 16:01:56', null);
INSERT INTO `tb_student` VALUES ('94', '庄倩', '2', '2016-10-15 12:57:43', null);
INSERT INTO `tb_student` VALUES ('95', '潘杨婉晶', '2', '2016-10-18 09:26:04', null);
INSERT INTO `tb_student` VALUES ('96', '杨小石', '1', '2016-10-18 09:26:35', null);
INSERT INTO `tb_student` VALUES ('97', '王语嫣', '2', '2016-10-18 10:07:08', null);
INSERT INTO `tb_student` VALUES ('98', '宋佳馨', '2', '2016-10-18 10:31:02', null);
INSERT INTO `tb_student` VALUES ('99', '蒋雨婷', '2', '2016-10-18 10:41:01', null);
INSERT INTO `tb_student` VALUES ('100', '冯超娅玲', '2', '2016-10-18 11:07:48', null);
INSERT INTO `tb_student` VALUES ('101', '张来薏', '2', '2016-10-18 16:42:13', null);
INSERT INTO `tb_student` VALUES ('102', '彭品学', '2', '2016-10-18 16:53:14', null);
INSERT INTO `tb_student` VALUES ('103', '余晗银', '1', '2016-10-18 17:29:53', null);
INSERT INTO `tb_student` VALUES ('104', '杨颖松', '2', '2016-10-18 17:40:40', null);
INSERT INTO `tb_student` VALUES ('105', '吉庆菊', '2', '2016-10-18 17:44:08', null);
INSERT INTO `tb_student` VALUES ('106', '余婷', '2', '2016-10-18 17:45:08', null);
INSERT INTO `tb_student` VALUES ('107', '杨俊勇', '1', '2016-10-18 17:53:00', null);
INSERT INTO `tb_student` VALUES ('108', '杨通雪', '2', '2016-10-18 17:54:04', null);
INSERT INTO `tb_student` VALUES ('109', '王志宇', '1', '2016-10-18 17:54:47', null);
INSERT INTO `tb_student` VALUES ('110', '蒋安艳', '2', '2016-10-18 17:55:32', null);
INSERT INTO `tb_student` VALUES ('111', '龙安康', '1', '2016-10-18 17:56:20', null);
INSERT INTO `tb_student` VALUES ('112', '王亚昕', '2', '2016-10-18 18:19:25', null);
INSERT INTO `tb_student` VALUES ('113', '杨明燚', '2', '2016-10-18 20:40:23', null);
INSERT INTO `tb_student` VALUES ('114', '肖墨凌', '1', '2016-10-18 20:46:50', null);
INSERT INTO `tb_student` VALUES ('115', '吴青梅', '2', '2016-10-18 20:48:08', null);
INSERT INTO `tb_student` VALUES ('116', '罗雯', '2', '2016-10-18 20:48:29', null);
INSERT INTO `tb_student` VALUES ('117', '杨金柱', '1', '2016-10-18 21:50:39', null);
INSERT INTO `tb_student` VALUES ('118', '孙婷', '2', '2016-10-18 21:51:55', null);
INSERT INTO `tb_student` VALUES ('119', '杨广', '1', '2016-10-18 22:14:01', null);
INSERT INTO `tb_student` VALUES ('120', '杨琦琳', '2', '2016-10-18 22:17:45', null);
INSERT INTO `tb_student` VALUES ('121', '刘妍', '2', '2016-10-18 22:19:54', null);
INSERT INTO `tb_student` VALUES ('122', '柳长春', '1', '2016-10-18 22:27:10', null);
INSERT INTO `tb_student` VALUES ('123', '胡曼', '2', '2016-10-19 09:18:30', null);
INSERT INTO `tb_student` VALUES ('124', '熊筱立', '2', '2016-10-19 09:21:09', null);
INSERT INTO `tb_student` VALUES ('125', '李乐', '2', '2016-10-19 09:25:20', null);
INSERT INTO `tb_student` VALUES ('126', '邰通旭', '1', '2016-10-19 09:26:30', null);
INSERT INTO `tb_student` VALUES ('127', '田文雯', '2', '2016-10-19 09:30:36', null);
INSERT INTO `tb_student` VALUES ('128', '魏思航', '1', '2016-10-19 09:31:46', null);
INSERT INTO `tb_student` VALUES ('129', '蒙奕丹', '2', '2016-10-19 09:33:50', null);
INSERT INTO `tb_student` VALUES ('130', '杨凯丽', '2', '2016-10-19 09:39:35', null);
INSERT INTO `tb_student` VALUES ('131', '文吉秀', '2', '2016-10-19 09:41:21', null);
INSERT INTO `tb_student` VALUES ('132', '李朝霞', '2', '2016-10-19 09:52:24', null);
INSERT INTO `tb_student` VALUES ('133', '周佛林', '1', '2016-10-19 09:53:18', null);
INSERT INTO `tb_student` VALUES ('134', '杨勉', '1', '2016-10-19 09:53:46', null);
INSERT INTO `tb_student` VALUES ('135', '赖俊儒', '1', '2016-10-19 09:54:51', null);
INSERT INTO `tb_student` VALUES ('136', '龙福景', '1', '2016-10-19 09:57:51', null);
INSERT INTO `tb_student` VALUES ('137', '张瑾玥', '2', '2016-10-19 10:17:20', null);
INSERT INTO `tb_student` VALUES ('138', '李鑫钰', '2', '2016-10-19 10:19:05', null);
INSERT INTO `tb_student` VALUES ('139', '赵凯生', '1', '2016-10-19 10:20:26', null);
INSERT INTO `tb_student` VALUES ('140', '覃毅', '1', '2016-10-19 10:53:56', null);
INSERT INTO `tb_student` VALUES ('141', '杨明燚', '1', '2016-10-19 10:56:14', null);
INSERT INTO `tb_student` VALUES ('142', '肖墨凌', '2', '2016-10-19 11:05:55', null);
INSERT INTO `tb_student` VALUES ('143', '龙百花', '2', '2016-10-19 11:16:13', null);
INSERT INTO `tb_student` VALUES ('144', '蒋世蓉', '2', '2016-10-19 11:18:00', null);
INSERT INTO `tb_student` VALUES ('145', '李欢', '2', '2016-10-19 11:19:11', null);
INSERT INTO `tb_student` VALUES ('146', '潘荣林', '1', '2016-10-19 11:20:45', null);
INSERT INTO `tb_student` VALUES ('147', '陈佳莉', '2', '2016-10-19 11:22:42', null);
INSERT INTO `tb_student` VALUES ('148', '侯万娟', '2', '2016-10-19 11:29:00', null);
INSERT INTO `tb_student` VALUES ('149', '朱婷', '2', '2016-10-19 11:29:46', null);
INSERT INTO `tb_student` VALUES ('150', '李蕾', '2', '2016-10-19 11:31:19', null);
INSERT INTO `tb_student` VALUES ('151', '杨羿婵', '2', '2016-10-19 11:32:12', null);
INSERT INTO `tb_student` VALUES ('152', '张发燕', '2', '2016-10-19 11:36:49', null);
INSERT INTO `tb_student` VALUES ('153', '钟巧晗', '2', '2016-10-19 11:37:23', null);
INSERT INTO `tb_student` VALUES ('154', '杨馨燕', '2', '2016-10-19 11:38:31', null);
INSERT INTO `tb_student` VALUES ('155', '吴珊珊', '2', '2016-10-19 11:40:36', null);
INSERT INTO `tb_student` VALUES ('156', '杨丽娜', '2', '2016-10-19 15:06:35', null);
INSERT INTO `tb_student` VALUES ('157', '秦茂华', '2', '2016-10-19 15:07:28', null);
INSERT INTO `tb_student` VALUES ('158', '廖婷钰', '2', '2016-10-19 15:18:21', null);
INSERT INTO `tb_student` VALUES ('159', '张淇', '2', '2016-10-19 15:38:21', null);
INSERT INTO `tb_student` VALUES ('160', '石宇晨', '1', '2016-10-19 15:39:14', null);
INSERT INTO `tb_student` VALUES ('161', '张文静', '2', '2016-10-19 15:40:34', null);
INSERT INTO `tb_student` VALUES ('162', '张阐尹', '1', '2016-10-19 15:41:32', null);
INSERT INTO `tb_student` VALUES ('163', '杨亦灿', '1', '2016-10-19 15:42:19', null);
INSERT INTO `tb_student` VALUES ('164', '杨双然', '2', '2016-10-19 15:43:32', null);
INSERT INTO `tb_student` VALUES ('165', '杨文浩', '1', '2016-10-19 15:44:20', null);
INSERT INTO `tb_student` VALUES ('166', '余成瑶', '2', '2016-10-19 15:46:58', null);
INSERT INTO `tb_student` VALUES ('167', '彭倩', '2', '2016-10-19 15:48:10', null);
INSERT INTO `tb_student` VALUES ('168', '王汉峰', '1', '2016-10-19 15:48:55', null);
INSERT INTO `tb_student` VALUES ('169', '贾子欣', '2', '2016-10-19 15:50:02', null);
INSERT INTO `tb_student` VALUES ('170', '郑婷钰', '2', '2016-10-19 15:50:47', null);
INSERT INTO `tb_student` VALUES ('171', '曾静', '2', '2016-10-19 15:54:10', null);
INSERT INTO `tb_student` VALUES ('172', '何其畔', '2', '2016-10-19 15:58:07', null);
INSERT INTO `tb_student` VALUES ('173', '顾珍莲', '2', '2016-10-19 15:59:17', null);
INSERT INTO `tb_student` VALUES ('174', '邹菲儿', '2', '2016-10-19 16:00:14', null);
INSERT INTO `tb_student` VALUES ('175', '刘尧浩', '1', '2016-10-19 16:03:00', null);
INSERT INTO `tb_student` VALUES ('176', '吴莉', '2', '2016-10-19 16:03:47', null);
INSERT INTO `tb_student` VALUES ('177', '李薇薇', '2', '2016-10-19 16:04:32', null);

-- ----------------------------
-- Table structure for `tb_studentclass`
-- ----------------------------
DROP TABLE IF EXISTS `tb_studentclass`;
CREATE TABLE `tb_studentclass` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `studentId` int(11) DEFAULT NULL,
  `studentRegId` varchar(255) DEFAULT NULL,
  `classinfoId` int(11) DEFAULT NULL,
  `intTime` datetime DEFAULT NULL,
  `outTime` datetime DEFAULT NULL,
  `state` tinyint(4) DEFAULT '0' COMMENT '0：在读，1离开',
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `sc_studentId` (`studentId`),
  KEY `sc_classinfoId` (`classinfoId`),
  KEY `sc_studentRegId` (`studentRegId`),
  CONSTRAINT `sc_classinfoId` FOREIGN KEY (`classinfoId`) REFERENCES `tb_classinfo` (`id`),
  CONSTRAINT `sc_studentId` FOREIGN KEY (`studentId`) REFERENCES `tb_student` (`id`),
  CONSTRAINT `sc_studentRegId` FOREIGN KEY (`studentRegId`) REFERENCES `tb_studentregistration` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=204 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_studentclass
-- ----------------------------
INSERT INTO `tb_studentclass` VALUES ('108', '97', '2016101815232290651392', '58', '2016-10-18 15:23:31', null, '0', null);
INSERT INTO `tb_studentclass` VALUES ('109', '52', '2016101815252734340902', '59', '2016-10-18 15:25:38', null, '0', null);
INSERT INTO `tb_studentclass` VALUES ('110', '6', '201610181532232652680', '60', '2016-10-18 15:32:31', null, '0', null);
INSERT INTO `tb_studentclass` VALUES ('111', '7', '2016101815372198489190', '60', '2016-10-18 15:37:52', null, '0', null);
INSERT INTO `tb_studentclass` VALUES ('112', '8', '2016101815393379695035', '60', '2016-10-18 15:39:39', null, '0', null);
INSERT INTO `tb_studentclass` VALUES ('113', '9', '2016101815395981294011', '60', '2016-10-18 15:40:07', null, '0', null);
INSERT INTO `tb_studentclass` VALUES ('114', '3', '201610181541345937044', '60', '2016-10-18 15:41:42', null, '0', null);
INSERT INTO `tb_studentclass` VALUES ('115', '10', '2016101815402723457413', '60', '2016-10-18 15:41:51', null, '0', null);
INSERT INTO `tb_studentclass` VALUES ('116', '12', '201610181543489312314', '60', '2016-10-18 15:43:55', null, '0', null);
INSERT INTO `tb_studentclass` VALUES ('117', '14', '201610181545893715521', '60', '2016-10-18 15:45:15', null, '0', null);
INSERT INTO `tb_studentclass` VALUES ('118', '15', '201610181546409390364', '60', '2016-10-18 15:46:48', null, '0', null);
INSERT INTO `tb_studentclass` VALUES ('119', '17', '2016101815501929660695', '60', '2016-10-18 15:50:27', null, '0', null);
INSERT INTO `tb_studentclass` VALUES ('120', '98', '2016101815513150070285', '60', '2016-10-18 15:51:46', null, '0', null);
INSERT INTO `tb_studentclass` VALUES ('121', '99', '201610181552623474652', '60', '2016-10-18 15:52:13', null, '0', null);
INSERT INTO `tb_studentclass` VALUES ('122', '100', '201610181637365670755', '61', '2016-10-18 16:37:11', null, '0', null);
INSERT INTO `tb_studentclass` VALUES ('123', '37', '2016101816393693745848', '61', '2016-10-18 16:39:45', null, '0', null);
INSERT INTO `tb_studentclass` VALUES ('124', '90', '2016101816404914085190', '61', '2016-10-18 16:40:57', null, '0', null);
INSERT INTO `tb_studentclass` VALUES ('125', '101', '2016101816423273498808', '61', '2016-10-18 16:43:20', null, '0', null);
INSERT INTO `tb_studentclass` VALUES ('126', '43', '20161018165114636008', '62', '2016-10-18 16:51:40', null, '0', null);
INSERT INTO `tb_studentclass` VALUES ('127', '89', '201610181653254643140', '62', '2016-10-18 16:53:33', null, '0', null);
INSERT INTO `tb_studentclass` VALUES ('128', '102', '201610181653345318167', '62', '2016-10-18 16:53:46', null, '0', null);
INSERT INTO `tb_studentclass` VALUES ('129', '5', '2016101817301634388170', '63', '2016-10-18 17:30:30', null, '0', null);
INSERT INTO `tb_studentclass` VALUES ('130', '103', '201610181731231218763', '63', '2016-10-18 17:31:09', null, '0', null);
INSERT INTO `tb_studentclass` VALUES ('131', '12', '2016101817355764082886', '63', '2016-10-18 17:36:05', null, '0', null);
INSERT INTO `tb_studentclass` VALUES ('132', '64', '2016101817365831265385', '63', '2016-10-18 17:37:06', null, '0', null);
INSERT INTO `tb_studentclass` VALUES ('133', '104', '201610181741893741615', '63', '2016-10-18 17:41:16', null, '0', null);
INSERT INTO `tb_studentclass` VALUES ('134', '14', '2016101817423390611233', '63', '2016-10-18 17:43:00', null, '0', null);
INSERT INTO `tb_studentclass` VALUES ('135', '105', '2016101817443475071412', '63', '2016-10-18 17:44:42', null, '0', null);
INSERT INTO `tb_studentclass` VALUES ('136', '106', '2016101817452671893057', '63', '2016-10-18 17:45:34', null, '0', null);
INSERT INTO `tb_studentclass` VALUES ('137', '6', '2016101817462943744424', '63', '2016-10-18 17:46:38', null, '0', null);
INSERT INTO `tb_studentclass` VALUES ('138', '107', '2016101817583021837811', '64', '2016-10-18 17:58:37', null, '0', null);
INSERT INTO `tb_studentclass` VALUES ('139', '108', '2016101817591578119324', '64', '2016-10-18 17:59:29', null, '0', null);
INSERT INTO `tb_studentclass` VALUES ('140', '109', '201610181803593783823', '64', '2016-10-18 18:00:41', null, '0', null);
INSERT INTO `tb_studentclass` VALUES ('141', '110', '20161018181394536254', '64', '2016-10-18 18:01:46', null, '0', null);
INSERT INTO `tb_studentclass` VALUES ('142', '111', '201610181821714077082', '64', '2016-10-18 18:02:25', null, '0', null);
INSERT INTO `tb_studentclass` VALUES ('143', '101', '201610181831040627519', '64', '2016-10-18 18:03:20', null, '0', null);
INSERT INTO `tb_studentclass` VALUES ('144', '91', '201610181834542147209', '64', '2016-10-18 18:06:08', null, '0', null);
INSERT INTO `tb_studentclass` VALUES ('145', '17', '2016101818111731291544', '65', '2016-10-18 18:12:39', null, '0', null);
INSERT INTO `tb_studentclass` VALUES ('146', '63', '201610181812289371082', '65', '2016-10-18 18:12:45', null, '0', null);
INSERT INTO `tb_studentclass` VALUES ('147', '16', '2016101818135565673588', '65', '2016-10-18 18:14:03', null, '0', null);
INSERT INTO `tb_studentclass` VALUES ('148', '98', '2016101818145557898090', '65', '2016-10-18 18:15:01', null, '0', null);
INSERT INTO `tb_studentclass` VALUES ('149', '54', '2016101818173756293329', '66', '2016-10-18 18:17:45', null, '0', null);
INSERT INTO `tb_studentclass` VALUES ('150', '55', '2016101818184851532609', '66', '2016-10-18 18:18:54', null, '0', null);
INSERT INTO `tb_studentclass` VALUES ('151', '112', '2016101818194262592564', '66', '2016-10-18 18:19:50', null, '0', null);
INSERT INTO `tb_studentclass` VALUES ('152', '52', '2016101818241675037680', '67', '2016-10-18 18:24:22', null, '0', null);
INSERT INTO `tb_studentclass` VALUES ('153', '78', '201610182019345397698', '68', '2016-10-18 20:19:10', null, '0', null);
INSERT INTO `tb_studentclass` VALUES ('154', '26', '2016101820202868724544', '68', '2016-10-18 20:20:34', null, '0', null);
INSERT INTO `tb_studentclass` VALUES ('155', '79', '2016101820212070341134', '68', '2016-10-18 20:21:26', null, '0', null);
INSERT INTO `tb_studentclass` VALUES ('156', '113', '20161018204058187319', '69', '2016-10-18 20:41:05', null, '0', null);
INSERT INTO `tb_studentclass` VALUES ('157', '87', '2016101820423565694458', '69', '2016-10-18 20:42:42', null, '0', null);
INSERT INTO `tb_studentclass` VALUES ('158', '88', '2016101820452657812092', '69', '2016-10-18 20:45:32', null, '0', null);
INSERT INTO `tb_studentclass` VALUES ('159', '114', '2016101820471023463736', '69', '2016-10-18 20:47:17', null, '0', null);
INSERT INTO `tb_studentclass` VALUES ('160', '116', '2016101820484731252023', '69', '2016-10-18 20:48:53', null, '0', null);
INSERT INTO `tb_studentclass` VALUES ('161', '115', '201610182049121567624', '69', '2016-10-18 20:50:19', null, '0', null);
INSERT INTO `tb_studentclass` VALUES ('162', '86', '201610182054297879525', '69', '2016-10-18 20:54:35', null, '0', null);
INSERT INTO `tb_studentclass` VALUES ('163', '48', '201610182132232899885', '70', '2016-10-18 21:03:29', '2016-10-18 21:06:19', '1', null);
INSERT INTO `tb_studentclass` VALUES ('164', '48', '20161018216459310615', '70', '2016-10-18 21:06:52', null, '0', null);
INSERT INTO `tb_studentclass` VALUES ('165', '28', '201610182194057879953', '71', '2016-10-18 21:09:46', null, '0', null);
INSERT INTO `tb_studentclass` VALUES ('166', '74', '201610182117121879153', '72', '2016-10-18 21:17:06', '2016-10-18 21:18:23', '1', null);
INSERT INTO `tb_studentclass` VALUES ('167', '74', '201610182120218128967', '73', '2016-10-18 21:20:29', null, '0', null);
INSERT INTO `tb_studentclass` VALUES ('168', '77', '2016101821245110996772', '74', '2016-10-18 21:24:59', null, '0', null);
INSERT INTO `tb_studentclass` VALUES ('169', '76', '201610182126121885582', '74', '2016-10-18 21:26:07', null, '0', null);
INSERT INTO `tb_studentclass` VALUES ('170', '41', '201610182127198443660', '74', '2016-10-18 21:27:08', null, '0', null);
INSERT INTO `tb_studentclass` VALUES ('171', '3', '201610182146734355308', '75', '2016-10-18 21:46:13', null, '0', null);
INSERT INTO `tb_studentclass` VALUES ('172', '4', '201610182147528199322', '75', '2016-10-18 21:47:16', null, '0', null);
INSERT INTO `tb_studentclass` VALUES ('173', '102', '201610182148026563856', '75', '2016-10-18 21:48:06', null, '0', null);
INSERT INTO `tb_studentclass` VALUES ('174', '117', '201610182151340680009', '76', '2016-10-18 21:51:10', null, '0', null);
INSERT INTO `tb_studentclass` VALUES ('175', '118', '201610182153451518694', '77', '2016-10-18 21:53:12', null, '0', null);
INSERT INTO `tb_studentclass` VALUES ('176', '118', '201610182157865676523', '78', '2016-10-18 21:57:15', null, '0', null);
INSERT INTO `tb_studentclass` VALUES ('177', '119', '201610182216740621589', '79', '2016-10-18 22:16:12', null, '0', null);
INSERT INTO `tb_studentclass` VALUES ('178', '120', '2016101822183448447503', '80', '2016-10-18 22:18:39', null, '0', null);
INSERT INTO `tb_studentclass` VALUES ('179', '33', '201610182221589394896', '81', '2016-10-18 22:22:04', null, '0', null);
INSERT INTO `tb_studentclass` VALUES ('180', '29', '2016101822242525039322', '82', '2016-10-18 22:24:30', null, '0', null);
INSERT INTO `tb_studentclass` VALUES ('181', '122', '201610182229295324204', '83', '2016-10-18 22:29:08', null, '0', null);
INSERT INTO `tb_studentclass` VALUES ('182', '52', '201610191042126556567', '84', '2016-10-19 10:04:28', null, '0', null);
INSERT INTO `tb_studentclass` VALUES ('183', '56', '2016101910111573428515', '85', '2016-10-19 10:11:25', null, '0', null);
INSERT INTO `tb_studentclass` VALUES ('184', '5', '201610191015494681327', '86', '2016-10-19 10:15:57', null, '0', null);
INSERT INTO `tb_studentclass` VALUES ('185', '60', '201610191018183115596', '86', '2016-10-19 10:18:34', null, '0', null);
INSERT INTO `tb_studentclass` VALUES ('186', '4', '2016101910183770354099', '86', '2016-10-19 10:18:44', null, '0', null);
INSERT INTO `tb_studentclass` VALUES ('187', '66', '201610191020186230536', '86', '2016-10-19 10:20:24', null, '0', null);
INSERT INTO `tb_studentclass` VALUES ('188', '51', '2016101910222015656144', '86', '2016-10-19 10:22:30', null, '0', null);
INSERT INTO `tb_studentclass` VALUES ('189', '70', '201610191024166298030', '86', '2016-10-19 10:24:23', null, '0', null);
INSERT INTO `tb_studentclass` VALUES ('190', '67', '2016101910255390685388', '86', '2016-10-19 10:26:04', null, '0', null);
INSERT INTO `tb_studentclass` VALUES ('191', '68', '2016101910264332872031', '86', '2016-10-19 10:26:49', null, '0', null);
INSERT INTO `tb_studentclass` VALUES ('192', '93', '2016101910275123429592', '86', '2016-10-19 10:27:58', null, '0', null);
INSERT INTO `tb_studentclass` VALUES ('193', '69', '2016101910295032881522', '86', '2016-10-19 10:29:58', null, '0', null);
INSERT INTO `tb_studentclass` VALUES ('194', '65', '20161019105524091609', '87', '2016-10-19 10:55:38', null, '0', null);
INSERT INTO `tb_studentclass` VALUES ('195', '42', '201610191058182812811', '87', '2016-10-19 10:58:10', null, '0', null);
INSERT INTO `tb_studentclass` VALUES ('196', '15', '201610191058337811690', '87', '2016-10-19 10:58:41', null, '0', null);
INSERT INTO `tb_studentclass` VALUES ('197', '71', '2016101910592931293151', '87', '2016-10-19 10:59:37', null, '0', null);
INSERT INTO `tb_studentclass` VALUES ('198', '102', '201610191132171849955', '88', '2016-10-19 11:03:28', null, '0', null);
INSERT INTO `tb_studentclass` VALUES ('199', '106', '201610191141159373752', '88', '2016-10-19 11:04:18', null, '0', null);
INSERT INTO `tb_studentclass` VALUES ('200', '104', '201610191151545360729', '88', '2016-10-19 11:05:22', null, '0', null);
INSERT INTO `tb_studentclass` VALUES ('201', '34', '201610191174371886196', '88', '2016-10-19 11:07:51', null, '0', null);
INSERT INTO `tb_studentclass` VALUES ('202', '91', '2016101911114398416820', '89', '2016-10-19 11:11:51', null, '0', null);
INSERT INTO `tb_studentclass` VALUES ('203', '92', '201610191112196270305', '89', '2016-10-19 11:12:25', null, '0', null);

-- ----------------------------
-- Table structure for `tb_studentgoclass`
-- ----------------------------
DROP TABLE IF EXISTS `tb_studentgoclass`;
CREATE TABLE `tb_studentgoclass` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `studentId` int(11) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `login` tinyint(4) DEFAULT '0' COMMENT '1：已签到 0：未签到',
  `availd` tinyint(4) DEFAULT '0' COMMENT '0：无效1：有效',
  `createTime` datetime DEFAULT NULL,
  `classRcordId` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `sgc_studentId` (`studentId`),
  KEY `sgc_classRcordId` (`classRcordId`),
  CONSTRAINT `sgc_classRcordId` FOREIGN KEY (`classRcordId`) REFERENCES `tb_classrcord` (`id`),
  CONSTRAINT `sgc_studentId` FOREIGN KEY (`studentId`) REFERENCES `tb_student` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=677 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_studentgoclass
-- ----------------------------
INSERT INTO `tb_studentgoclass` VALUES ('406', '52', '', '1', '1', '2016-10-18 16:26:12', '2016101816261292178715');
INSERT INTO `tb_studentgoclass` VALUES ('407', '52', '', '1', '1', '2016-10-18 16:26:42', '2016101816264284398207');
INSERT INTO `tb_studentgoclass` VALUES ('408', '52', '', '1', '1', '2016-10-18 16:27:07', '201610181627731211763');
INSERT INTO `tb_studentgoclass` VALUES ('409', '52', '', '1', '1', '2016-10-18 16:27:32', '201610181627329214764');
INSERT INTO `tb_studentgoclass` VALUES ('410', '52', '', '1', '1', '2016-10-18 16:28:08', '201610181628843741761');
INSERT INTO `tb_studentgoclass` VALUES ('411', '52', '', '1', '1', '2016-10-18 16:28:39', '2016101816283921814193');
INSERT INTO `tb_studentgoclass` VALUES ('412', '52', '', '1', '1', '2016-10-18 16:28:59', '2016101816285995369870');
INSERT INTO `tb_studentgoclass` VALUES ('413', '52', '', '1', '1', '2016-10-18 16:29:19', '2016101816291934359509');
INSERT INTO `tb_studentgoclass` VALUES ('414', '99', '', '0', '0', '2016-10-18 16:32:49', '201610181632495936262');
INSERT INTO `tb_studentgoclass` VALUES ('415', '98', '', '0', '0', '2016-10-18 16:32:49', '201610181632495936262');
INSERT INTO `tb_studentgoclass` VALUES ('416', '17', '', '1', '1', '2016-10-18 16:32:49', '201610181632495936262');
INSERT INTO `tb_studentgoclass` VALUES ('417', '15', '', '1', '1', '2016-10-18 16:32:49', '201610181632495936262');
INSERT INTO `tb_studentgoclass` VALUES ('418', '14', '', '1', '1', '2016-10-18 16:32:49', '201610181632495936262');
INSERT INTO `tb_studentgoclass` VALUES ('419', '12', '', '1', '1', '2016-10-18 16:32:49', '201610181632495936262');
INSERT INTO `tb_studentgoclass` VALUES ('420', '10', '', '1', '1', '2016-10-18 16:32:49', '201610181632495936262');
INSERT INTO `tb_studentgoclass` VALUES ('421', '3', '', '1', '1', '2016-10-18 16:32:49', '201610181632495936262');
INSERT INTO `tb_studentgoclass` VALUES ('422', '9', '', '1', '1', '2016-10-18 16:32:49', '201610181632495936262');
INSERT INTO `tb_studentgoclass` VALUES ('423', '8', '', '1', '1', '2016-10-18 16:32:49', '201610181632495936262');
INSERT INTO `tb_studentgoclass` VALUES ('424', '7', '', '1', '1', '2016-10-18 16:32:49', '201610181632495936262');
INSERT INTO `tb_studentgoclass` VALUES ('425', '6', '', '1', '1', '2016-10-18 16:32:49', '201610181632495936262');
INSERT INTO `tb_studentgoclass` VALUES ('426', '99', '', '0', '0', '2016-10-18 16:33:31', '2016101816333154690314');
INSERT INTO `tb_studentgoclass` VALUES ('427', '98', '', '0', '0', '2016-10-18 16:33:31', '2016101816333154690314');
INSERT INTO `tb_studentgoclass` VALUES ('428', '17', '', '1', '1', '2016-10-18 16:33:31', '2016101816333154690314');
INSERT INTO `tb_studentgoclass` VALUES ('429', '15', '', '1', '1', '2016-10-18 16:33:31', '2016101816333154690314');
INSERT INTO `tb_studentgoclass` VALUES ('430', '14', '', '1', '1', '2016-10-18 16:33:31', '2016101816333154690314');
INSERT INTO `tb_studentgoclass` VALUES ('431', '12', '', '1', '1', '2016-10-18 16:33:31', '2016101816333154690314');
INSERT INTO `tb_studentgoclass` VALUES ('432', '10', '', '1', '1', '2016-10-18 16:33:31', '2016101816333154690314');
INSERT INTO `tb_studentgoclass` VALUES ('433', '3', '', '1', '1', '2016-10-18 16:33:31', '2016101816333154690314');
INSERT INTO `tb_studentgoclass` VALUES ('434', '9', '', '1', '1', '2016-10-18 16:33:31', '2016101816333154690314');
INSERT INTO `tb_studentgoclass` VALUES ('435', '8', '', '1', '1', '2016-10-18 16:33:31', '2016101816333154690314');
INSERT INTO `tb_studentgoclass` VALUES ('436', '7', '', '1', '1', '2016-10-18 16:33:31', '2016101816333154690314');
INSERT INTO `tb_studentgoclass` VALUES ('437', '6', '', '1', '1', '2016-10-18 16:33:31', '2016101816333154690314');
INSERT INTO `tb_studentgoclass` VALUES ('438', '99', '', '0', '0', '2016-10-18 16:34:11', '2016101816341110935212');
INSERT INTO `tb_studentgoclass` VALUES ('439', '98', '', '1', '1', '2016-10-18 16:34:11', '2016101816341110935212');
INSERT INTO `tb_studentgoclass` VALUES ('440', '17', '', '1', '1', '2016-10-18 16:34:11', '2016101816341110935212');
INSERT INTO `tb_studentgoclass` VALUES ('441', '15', '', '1', '1', '2016-10-18 16:34:11', '2016101816341110935212');
INSERT INTO `tb_studentgoclass` VALUES ('442', '14', '', '1', '1', '2016-10-18 16:34:11', '2016101816341110935212');
INSERT INTO `tb_studentgoclass` VALUES ('443', '12', '', '1', '1', '2016-10-18 16:34:11', '2016101816341110935212');
INSERT INTO `tb_studentgoclass` VALUES ('444', '10', '', '1', '1', '2016-10-18 16:34:11', '2016101816341110935212');
INSERT INTO `tb_studentgoclass` VALUES ('445', '3', '', '1', '1', '2016-10-18 16:34:11', '2016101816341110935212');
INSERT INTO `tb_studentgoclass` VALUES ('446', '9', '', '1', '1', '2016-10-18 16:34:11', '2016101816341110935212');
INSERT INTO `tb_studentgoclass` VALUES ('447', '8', '', '1', '1', '2016-10-18 16:34:11', '2016101816341110935212');
INSERT INTO `tb_studentgoclass` VALUES ('448', '7', '', '1', '1', '2016-10-18 16:34:11', '2016101816341110935212');
INSERT INTO `tb_studentgoclass` VALUES ('449', '6', '', '1', '1', '2016-10-18 16:34:11', '2016101816341110935212');
INSERT INTO `tb_studentgoclass` VALUES ('450', '99', '', '1', '1', '2016-10-18 16:34:53', '2016101816345332865496');
INSERT INTO `tb_studentgoclass` VALUES ('451', '98', '', '1', '1', '2016-10-18 16:34:53', '2016101816345332865496');
INSERT INTO `tb_studentgoclass` VALUES ('452', '17', '', '1', '1', '2016-10-18 16:34:53', '2016101816345332865496');
INSERT INTO `tb_studentgoclass` VALUES ('453', '15', '', '1', '1', '2016-10-18 16:34:53', '2016101816345332865496');
INSERT INTO `tb_studentgoclass` VALUES ('454', '14', '', '1', '1', '2016-10-18 16:34:53', '2016101816345332865496');
INSERT INTO `tb_studentgoclass` VALUES ('455', '12', '', '1', '1', '2016-10-18 16:34:53', '2016101816345332865496');
INSERT INTO `tb_studentgoclass` VALUES ('456', '10', '', '1', '1', '2016-10-18 16:34:53', '2016101816345332865496');
INSERT INTO `tb_studentgoclass` VALUES ('457', '3', '', '1', '1', '2016-10-18 16:34:53', '2016101816345332865496');
INSERT INTO `tb_studentgoclass` VALUES ('458', '9', '', '1', '1', '2016-10-18 16:34:53', '2016101816345332865496');
INSERT INTO `tb_studentgoclass` VALUES ('459', '8', '', '1', '1', '2016-10-18 16:34:53', '2016101816345332865496');
INSERT INTO `tb_studentgoclass` VALUES ('460', '7', '', '1', '1', '2016-10-18 16:34:53', '2016101816345332865496');
INSERT INTO `tb_studentgoclass` VALUES ('461', '6', '', '1', '1', '2016-10-18 16:34:53', '2016101816345332865496');
INSERT INTO `tb_studentgoclass` VALUES ('462', '99', '', '1', '1', '2016-10-18 16:35:16', '2016101816351670337945');
INSERT INTO `tb_studentgoclass` VALUES ('463', '98', '', '1', '1', '2016-10-18 16:35:16', '2016101816351670337945');
INSERT INTO `tb_studentgoclass` VALUES ('464', '17', '', '1', '1', '2016-10-18 16:35:16', '2016101816351670337945');
INSERT INTO `tb_studentgoclass` VALUES ('465', '15', '', '1', '1', '2016-10-18 16:35:16', '2016101816351670337945');
INSERT INTO `tb_studentgoclass` VALUES ('466', '14', '', '1', '1', '2016-10-18 16:35:16', '2016101816351670337945');
INSERT INTO `tb_studentgoclass` VALUES ('467', '12', '', '1', '1', '2016-10-18 16:35:16', '2016101816351670337945');
INSERT INTO `tb_studentgoclass` VALUES ('468', '10', '', '1', '1', '2016-10-18 16:35:16', '2016101816351670337945');
INSERT INTO `tb_studentgoclass` VALUES ('469', '3', '', '1', '1', '2016-10-18 16:35:16', '2016101816351670337945');
INSERT INTO `tb_studentgoclass` VALUES ('470', '9', '', '1', '1', '2016-10-18 16:35:16', '2016101816351670337945');
INSERT INTO `tb_studentgoclass` VALUES ('471', '8', '', '1', '1', '2016-10-18 16:35:16', '2016101816351670337945');
INSERT INTO `tb_studentgoclass` VALUES ('472', '7', '', '1', '1', '2016-10-18 16:35:16', '2016101816351670337945');
INSERT INTO `tb_studentgoclass` VALUES ('473', '6', '', '1', '1', '2016-10-18 16:35:16', '2016101816351670337945');
INSERT INTO `tb_studentgoclass` VALUES ('474', '101', '', '0', '0', '2016-10-18 16:45:10', '2016101816451039086534');
INSERT INTO `tb_studentgoclass` VALUES ('475', '90', '', '0', '0', '2016-10-18 16:45:10', '2016101816451039086534');
INSERT INTO `tb_studentgoclass` VALUES ('476', '37', '', '1', '1', '2016-10-18 16:45:10', '2016101816451039086534');
INSERT INTO `tb_studentgoclass` VALUES ('477', '100', '', '1', '1', '2016-10-18 16:45:10', '2016101816451039086534');
INSERT INTO `tb_studentgoclass` VALUES ('478', '101', '', '0', '0', '2016-10-18 16:45:52', '2016101816455243746045');
INSERT INTO `tb_studentgoclass` VALUES ('479', '90', '', '0', '0', '2016-10-18 16:45:52', '2016101816455243746045');
INSERT INTO `tb_studentgoclass` VALUES ('480', '37', '', '1', '1', '2016-10-18 16:45:52', '2016101816455243746045');
INSERT INTO `tb_studentgoclass` VALUES ('481', '100', '', '1', '1', '2016-10-18 16:45:52', '2016101816455243746045');
INSERT INTO `tb_studentgoclass` VALUES ('482', '101', '', '0', '0', '2016-10-18 16:46:29', '2016101816462998497346');
INSERT INTO `tb_studentgoclass` VALUES ('483', '90', '', '0', '0', '2016-10-18 16:46:29', '2016101816462998497346');
INSERT INTO `tb_studentgoclass` VALUES ('484', '37', '', '1', '1', '2016-10-18 16:46:29', '2016101816462998497346');
INSERT INTO `tb_studentgoclass` VALUES ('485', '100', '', '1', '1', '2016-10-18 16:46:29', '2016101816462998497346');
INSERT INTO `tb_studentgoclass` VALUES ('486', '101', '', '0', '0', '2016-10-18 16:47:01', '201610181647195381231');
INSERT INTO `tb_studentgoclass` VALUES ('487', '90', '', '0', '0', '2016-10-18 16:47:01', '201610181647195381231');
INSERT INTO `tb_studentgoclass` VALUES ('488', '37', '', '1', '1', '2016-10-18 16:47:01', '201610181647195381231');
INSERT INTO `tb_studentgoclass` VALUES ('489', '100', '', '1', '1', '2016-10-18 16:47:01', '201610181647195381231');
INSERT INTO `tb_studentgoclass` VALUES ('490', '101', '', '0', '0', '2016-10-18 16:47:31', '2016101816473176596085');
INSERT INTO `tb_studentgoclass` VALUES ('491', '90', '', '1', '1', '2016-10-18 16:47:31', '2016101816473176596085');
INSERT INTO `tb_studentgoclass` VALUES ('492', '37', '', '1', '1', '2016-10-18 16:47:31', '2016101816473176596085');
INSERT INTO `tb_studentgoclass` VALUES ('493', '100', '', '1', '1', '2016-10-18 16:47:31', '2016101816473176596085');
INSERT INTO `tb_studentgoclass` VALUES ('494', '101', '', '1', '1', '2016-10-18 16:47:49', '2016101816474985984471');
INSERT INTO `tb_studentgoclass` VALUES ('495', '90', '', '1', '1', '2016-10-18 16:47:49', '2016101816474985984471');
INSERT INTO `tb_studentgoclass` VALUES ('496', '37', '', '1', '1', '2016-10-18 16:47:49', '2016101816474985984471');
INSERT INTO `tb_studentgoclass` VALUES ('497', '100', '', '1', '1', '2016-10-18 16:47:49', '2016101816474985984471');
INSERT INTO `tb_studentgoclass` VALUES ('498', '102', '', '1', '1', '2016-10-18 16:54:51', '2016101816545185995058');
INSERT INTO `tb_studentgoclass` VALUES ('499', '89', '', '1', '1', '2016-10-18 16:54:51', '2016101816545185995058');
INSERT INTO `tb_studentgoclass` VALUES ('500', '43', '', '1', '1', '2016-10-18 16:54:51', '2016101816545185995058');
INSERT INTO `tb_studentgoclass` VALUES ('501', '103', '', '1', '1', '2016-10-18 17:32:14', '2016101817321460961532');
INSERT INTO `tb_studentgoclass` VALUES ('502', '5', '', '1', '1', '2016-10-18 17:32:14', '2016101817321460961532');
INSERT INTO `tb_studentgoclass` VALUES ('503', '103', '', '1', '1', '2016-10-18 17:32:47', '2016101817324731278698');
INSERT INTO `tb_studentgoclass` VALUES ('504', '5', '', '1', '1', '2016-10-18 17:32:47', '2016101817324731278698');
INSERT INTO `tb_studentgoclass` VALUES ('505', '103', '', '1', '1', '2016-10-18 17:33:14', '2016101817331443717380');
INSERT INTO `tb_studentgoclass` VALUES ('506', '5', '', '1', '1', '2016-10-18 17:33:14', '2016101817331443717380');
INSERT INTO `tb_studentgoclass` VALUES ('507', '103', '', '1', '1', '2016-10-18 17:33:55', '20161018173355153867');
INSERT INTO `tb_studentgoclass` VALUES ('508', '5', '', '1', '1', '2016-10-18 17:33:55', '20161018173355153867');
INSERT INTO `tb_studentgoclass` VALUES ('509', '103', '', '1', '1', '2016-10-18 17:34:13', '2016101817341370317590');
INSERT INTO `tb_studentgoclass` VALUES ('510', '5', '', '1', '1', '2016-10-18 17:34:13', '2016101817341370317590');
INSERT INTO `tb_studentgoclass` VALUES ('511', '103', '', '1', '1', '2016-10-18 17:35:03', '201610181735389029185');
INSERT INTO `tb_studentgoclass` VALUES ('512', '5', '', '1', '1', '2016-10-18 17:35:03', '201610181735389029185');
INSERT INTO `tb_studentgoclass` VALUES ('513', '6', '', '1', '1', '2016-10-18 17:47:26', '2016101817472689098496');
INSERT INTO `tb_studentgoclass` VALUES ('514', '106', '', '1', '1', '2016-10-18 17:47:26', '2016101817472689098496');
INSERT INTO `tb_studentgoclass` VALUES ('515', '105', '', '1', '1', '2016-10-18 17:47:26', '2016101817472689098496');
INSERT INTO `tb_studentgoclass` VALUES ('516', '14', '', '1', '1', '2016-10-18 17:47:26', '2016101817472689098496');
INSERT INTO `tb_studentgoclass` VALUES ('517', '104', '', '1', '1', '2016-10-18 17:47:26', '2016101817472689098496');
INSERT INTO `tb_studentgoclass` VALUES ('518', '64', '', '1', '1', '2016-10-18 17:47:26', '2016101817472689098496');
INSERT INTO `tb_studentgoclass` VALUES ('519', '12', '', '1', '1', '2016-10-18 17:47:26', '2016101817472689098496');
INSERT INTO `tb_studentgoclass` VALUES ('520', '103', '', '1', '1', '2016-10-18 17:47:26', '2016101817472689098496');
INSERT INTO `tb_studentgoclass` VALUES ('521', '5', '', '1', '1', '2016-10-18 17:47:26', '2016101817472689098496');
INSERT INTO `tb_studentgoclass` VALUES ('522', '101', '', '0', '0', '2016-10-18 18:04:50', '20161018184509316800');
INSERT INTO `tb_studentgoclass` VALUES ('523', '111', '', '1', '1', '2016-10-18 18:04:50', '20161018184509316800');
INSERT INTO `tb_studentgoclass` VALUES ('524', '110', '', '0', '0', '2016-10-18 18:04:50', '20161018184509316800');
INSERT INTO `tb_studentgoclass` VALUES ('525', '109', '', '0', '0', '2016-10-18 18:04:50', '20161018184509316800');
INSERT INTO `tb_studentgoclass` VALUES ('526', '108', '', '0', '0', '2016-10-18 18:04:50', '20161018184509316800');
INSERT INTO `tb_studentgoclass` VALUES ('527', '107', '', '0', '0', '2016-10-18 18:04:50', '20161018184509316800');
INSERT INTO `tb_studentgoclass` VALUES ('528', '91', '', '0', '0', '2016-10-18 18:07:10', '201610181871014063608');
INSERT INTO `tb_studentgoclass` VALUES ('529', '101', '', '0', '0', '2016-10-18 18:07:10', '201610181871014063608');
INSERT INTO `tb_studentgoclass` VALUES ('530', '111', '', '1', '1', '2016-10-18 18:07:10', '201610181871014063608');
INSERT INTO `tb_studentgoclass` VALUES ('531', '110', '', '0', '0', '2016-10-18 18:07:10', '201610181871014063608');
INSERT INTO `tb_studentgoclass` VALUES ('532', '109', '', '1', '1', '2016-10-18 18:07:10', '201610181871014063608');
INSERT INTO `tb_studentgoclass` VALUES ('533', '108', '', '1', '1', '2016-10-18 18:07:10', '201610181871014063608');
INSERT INTO `tb_studentgoclass` VALUES ('534', '107', '', '1', '1', '2016-10-18 18:07:10', '201610181871014063608');
INSERT INTO `tb_studentgoclass` VALUES ('535', '91', '', '0', '0', '2016-10-18 18:08:03', '2016101818832967688');
INSERT INTO `tb_studentgoclass` VALUES ('536', '101', '', '1', '1', '2016-10-18 18:08:03', '2016101818832967688');
INSERT INTO `tb_studentgoclass` VALUES ('537', '111', '', '1', '1', '2016-10-18 18:08:03', '2016101818832967688');
INSERT INTO `tb_studentgoclass` VALUES ('538', '110', '', '1', '1', '2016-10-18 18:08:03', '2016101818832967688');
INSERT INTO `tb_studentgoclass` VALUES ('539', '109', '', '1', '1', '2016-10-18 18:08:03', '2016101818832967688');
INSERT INTO `tb_studentgoclass` VALUES ('540', '108', '', '1', '1', '2016-10-18 18:08:03', '2016101818832967688');
INSERT INTO `tb_studentgoclass` VALUES ('541', '107', '', '1', '1', '2016-10-18 18:08:03', '2016101818832967688');
INSERT INTO `tb_studentgoclass` VALUES ('542', '98', '', '1', '1', '2016-10-18 18:15:26', '2016101818152634331566');
INSERT INTO `tb_studentgoclass` VALUES ('543', '16', '', '1', '1', '2016-10-18 18:15:26', '2016101818152634331566');
INSERT INTO `tb_studentgoclass` VALUES ('544', '63', '', '1', '1', '2016-10-18 18:15:26', '2016101818152634331566');
INSERT INTO `tb_studentgoclass` VALUES ('545', '17', '', '1', '1', '2016-10-18 18:15:26', '2016101818152634331566');
INSERT INTO `tb_studentgoclass` VALUES ('546', '112', '', '0', '0', '2016-10-18 18:20:50', '2016101818205096850917');
INSERT INTO `tb_studentgoclass` VALUES ('547', '55', '', '1', '1', '2016-10-18 18:20:50', '2016101818205096850917');
INSERT INTO `tb_studentgoclass` VALUES ('548', '54', '', '0', '0', '2016-10-18 18:20:50', '2016101818205096850917');
INSERT INTO `tb_studentgoclass` VALUES ('549', '112', '', '0', '0', '2016-10-18 18:21:17', '2016101818211796860018');
INSERT INTO `tb_studentgoclass` VALUES ('550', '55', '', '1', '1', '2016-10-18 18:21:17', '2016101818211796860018');
INSERT INTO `tb_studentgoclass` VALUES ('551', '54', '', '0', '0', '2016-10-18 18:21:17', '2016101818211796860018');
INSERT INTO `tb_studentgoclass` VALUES ('552', '112', '', '0', '0', '2016-10-18 18:21:41', '2016101818214168782322');
INSERT INTO `tb_studentgoclass` VALUES ('553', '55', '', '1', '1', '2016-10-18 18:21:41', '2016101818214168782322');
INSERT INTO `tb_studentgoclass` VALUES ('554', '54', '', '1', '1', '2016-10-18 18:21:41', '2016101818214168782322');
INSERT INTO `tb_studentgoclass` VALUES ('555', '112', '', '0', '0', '2016-10-18 18:22:39', '20161018182239010328');
INSERT INTO `tb_studentgoclass` VALUES ('556', '55', '', '1', '1', '2016-10-18 18:22:39', '20161018182239010328');
INSERT INTO `tb_studentgoclass` VALUES ('557', '54', '', '1', '1', '2016-10-18 18:22:39', '20161018182239010328');
INSERT INTO `tb_studentgoclass` VALUES ('558', '112', '', '1', '1', '2016-10-18 18:23:02', '20161018182329362044');
INSERT INTO `tb_studentgoclass` VALUES ('559', '55', '', '1', '1', '2016-10-18 18:23:02', '20161018182329362044');
INSERT INTO `tb_studentgoclass` VALUES ('560', '54', '', '1', '1', '2016-10-18 18:23:02', '20161018182329362044');
INSERT INTO `tb_studentgoclass` VALUES ('561', '52', '', '1', '1', '2016-10-18 18:24:44', '2016101818244431298814');
INSERT INTO `tb_studentgoclass` VALUES ('562', '52', '', '1', '1', '2016-10-18 18:25:01', '201610181825187582254');
INSERT INTO `tb_studentgoclass` VALUES ('563', '52', '', '1', '1', '2016-10-18 18:25:20', '2016101818252064037673');
INSERT INTO `tb_studentgoclass` VALUES ('564', '98', '', '0', '0', '2016-10-18 18:39:44', '2016101818394489021820');
INSERT INTO `tb_studentgoclass` VALUES ('565', '16', '', '0', '0', '2016-10-18 18:39:44', '2016101818394489021820');
INSERT INTO `tb_studentgoclass` VALUES ('566', '63', '', '1', '1', '2016-10-18 18:39:44', '2016101818394489021820');
INSERT INTO `tb_studentgoclass` VALUES ('567', '17', '', '0', '0', '2016-10-18 18:39:44', '2016101818394489021820');
INSERT INTO `tb_studentgoclass` VALUES ('568', '98', '', '0', '0', '2016-10-18 18:40:18', '2016101818401845319640');
INSERT INTO `tb_studentgoclass` VALUES ('569', '16', '', '0', '0', '2016-10-18 18:40:18', '2016101818401845319640');
INSERT INTO `tb_studentgoclass` VALUES ('570', '63', '', '1', '1', '2016-10-18 18:40:18', '2016101818401845319640');
INSERT INTO `tb_studentgoclass` VALUES ('571', '17', '', '0', '0', '2016-10-18 18:40:18', '2016101818401845319640');
INSERT INTO `tb_studentgoclass` VALUES ('572', '113', '', '1', '1', '2016-10-18 20:41:31', '2016101820413132856249');
INSERT INTO `tb_studentgoclass` VALUES ('573', '113', '', '1', '1', '2016-10-18 20:42:00', '20161018204208433826');
INSERT INTO `tb_studentgoclass` VALUES ('574', '88', '', '1', '1', '2016-10-18 20:46:03', '201610182046382896946');
INSERT INTO `tb_studentgoclass` VALUES ('575', '87', '', '1', '1', '2016-10-18 20:46:03', '201610182046382896946');
INSERT INTO `tb_studentgoclass` VALUES ('576', '113', '', '1', '1', '2016-10-18 20:46:03', '201610182046382896946');
INSERT INTO `tb_studentgoclass` VALUES ('577', '114', '', '1', '1', '2016-10-18 20:47:39', '2016101820473921879533');
INSERT INTO `tb_studentgoclass` VALUES ('578', '88', '', '1', '1', '2016-10-18 20:47:39', '2016101820473921879533');
INSERT INTO `tb_studentgoclass` VALUES ('579', '87', '', '1', '1', '2016-10-18 20:47:39', '2016101820473921879533');
INSERT INTO `tb_studentgoclass` VALUES ('580', '113', '', '1', '1', '2016-10-18 20:47:39', '2016101820473921879533');
INSERT INTO `tb_studentgoclass` VALUES ('581', '116', '', '1', '1', '2016-10-18 20:49:40', '2016101820494014082981');
INSERT INTO `tb_studentgoclass` VALUES ('582', '114', '', '1', '1', '2016-10-18 20:49:40', '2016101820494014082981');
INSERT INTO `tb_studentgoclass` VALUES ('583', '88', '', '1', '1', '2016-10-18 20:49:40', '2016101820494014082981');
INSERT INTO `tb_studentgoclass` VALUES ('584', '87', '', '1', '1', '2016-10-18 20:49:40', '2016101820494014082981');
INSERT INTO `tb_studentgoclass` VALUES ('585', '113', '', '1', '1', '2016-10-18 20:49:40', '2016101820494014082981');
INSERT INTO `tb_studentgoclass` VALUES ('586', '116', '', '1', '1', '2016-10-18 20:50:02', '201610182050271811561');
INSERT INTO `tb_studentgoclass` VALUES ('587', '114', '', '1', '1', '2016-10-18 20:50:02', '201610182050271811561');
INSERT INTO `tb_studentgoclass` VALUES ('588', '88', '', '1', '1', '2016-10-18 20:50:02', '201610182050271811561');
INSERT INTO `tb_studentgoclass` VALUES ('589', '87', '', '1', '1', '2016-10-18 20:50:02', '201610182050271811561');
INSERT INTO `tb_studentgoclass` VALUES ('590', '113', '', '1', '1', '2016-10-18 20:50:02', '201610182050271811561');
INSERT INTO `tb_studentgoclass` VALUES ('591', '115', '', '1', '1', '2016-10-18 20:51:14', '201610182051147885275');
INSERT INTO `tb_studentgoclass` VALUES ('592', '116', '', '0', '0', '2016-10-18 20:51:14', '201610182051147885275');
INSERT INTO `tb_studentgoclass` VALUES ('593', '114', '', '1', '1', '2016-10-18 20:51:14', '201610182051147885275');
INSERT INTO `tb_studentgoclass` VALUES ('594', '88', '', '1', '1', '2016-10-18 20:51:14', '201610182051147885275');
INSERT INTO `tb_studentgoclass` VALUES ('595', '87', '', '1', '1', '2016-10-18 20:51:14', '201610182051147885275');
INSERT INTO `tb_studentgoclass` VALUES ('596', '113', '', '1', '1', '2016-10-18 20:51:14', '201610182051147885275');
INSERT INTO `tb_studentgoclass` VALUES ('597', '115', '', '1', '1', '2016-10-18 20:52:33', '2016101820523362584747');
INSERT INTO `tb_studentgoclass` VALUES ('598', '116', '', '0', '0', '2016-10-18 20:52:33', '2016101820523362584747');
INSERT INTO `tb_studentgoclass` VALUES ('599', '114', '', '0', '0', '2016-10-18 20:52:33', '2016101820523362584747');
INSERT INTO `tb_studentgoclass` VALUES ('600', '88', '', '0', '0', '2016-10-18 20:52:33', '2016101820523362584747');
INSERT INTO `tb_studentgoclass` VALUES ('601', '87', '', '0', '0', '2016-10-18 20:52:33', '2016101820523362584747');
INSERT INTO `tb_studentgoclass` VALUES ('602', '113', '', '0', '0', '2016-10-18 20:52:33', '2016101820523362584747');
INSERT INTO `tb_studentgoclass` VALUES ('603', '102', '', '1', '1', '2016-10-18 21:00:52', '201610182105265661680');
INSERT INTO `tb_studentgoclass` VALUES ('604', '89', '', '1', '1', '2016-10-18 21:00:52', '201610182105265661680');
INSERT INTO `tb_studentgoclass` VALUES ('605', '43', '', '1', '1', '2016-10-18 21:00:52', '201610182105265661680');
INSERT INTO `tb_studentgoclass` VALUES ('606', '41', '', '1', '1', '2016-10-18 21:27:48', '2016101821274884339735');
INSERT INTO `tb_studentgoclass` VALUES ('607', '76', '', '1', '1', '2016-10-18 21:27:48', '2016101821274884339735');
INSERT INTO `tb_studentgoclass` VALUES ('608', '77', '', '1', '1', '2016-10-18 21:27:48', '2016101821274884339735');
INSERT INTO `tb_studentgoclass` VALUES ('609', '41', '', '1', '1', '2016-10-18 21:28:15', '2016101821281589068354');
INSERT INTO `tb_studentgoclass` VALUES ('610', '76', '', '1', '1', '2016-10-18 21:28:15', '2016101821281589068354');
INSERT INTO `tb_studentgoclass` VALUES ('611', '77', '', '1', '1', '2016-10-18 21:28:15', '2016101821281589068354');
INSERT INTO `tb_studentgoclass` VALUES ('612', '41', '', '1', '1', '2016-10-18 21:28:48', '201610182128486716363');
INSERT INTO `tb_studentgoclass` VALUES ('613', '76', '', '1', '1', '2016-10-18 21:28:48', '201610182128486716363');
INSERT INTO `tb_studentgoclass` VALUES ('614', '77', '', '1', '1', '2016-10-18 21:28:48', '201610182128486716363');
INSERT INTO `tb_studentgoclass` VALUES ('615', '41', '', '1', '1', '2016-10-18 21:29:11', '2016101821291143751065');
INSERT INTO `tb_studentgoclass` VALUES ('616', '76', '', '1', '1', '2016-10-18 21:29:11', '2016101821291143751065');
INSERT INTO `tb_studentgoclass` VALUES ('617', '77', '', '1', '1', '2016-10-18 21:29:11', '2016101821291143751065');
INSERT INTO `tb_studentgoclass` VALUES ('618', '41', '', '1', '1', '2016-10-18 21:29:34', '201610182129349215457');
INSERT INTO `tb_studentgoclass` VALUES ('619', '76', '', '1', '1', '2016-10-18 21:29:34', '201610182129349215457');
INSERT INTO `tb_studentgoclass` VALUES ('620', '77', '', '1', '1', '2016-10-18 21:29:34', '201610182129349215457');
INSERT INTO `tb_studentgoclass` VALUES ('621', '41', '', '1', '1', '2016-10-18 21:30:03', '201610182130328124314');
INSERT INTO `tb_studentgoclass` VALUES ('622', '76', '', '0', '0', '2016-10-18 21:30:03', '201610182130328124314');
INSERT INTO `tb_studentgoclass` VALUES ('623', '77', '', '1', '1', '2016-10-18 21:30:03', '201610182130328124314');
INSERT INTO `tb_studentgoclass` VALUES ('624', '117', '', '1', '1', '2016-10-18 21:51:48', '2016101821514843784684');
INSERT INTO `tb_studentgoclass` VALUES ('625', '122', '', '1', '1', '2016-10-18 22:31:17', '2016101822311776570797');
INSERT INTO `tb_studentgoclass` VALUES ('626', '122', '', '1', '1', '2016-10-18 22:31:45', '2016101822314584334515');
INSERT INTO `tb_studentgoclass` VALUES ('627', '52', '', '1', '1', '2016-10-19 10:06:50', '201610191065078126987');
INSERT INTO `tb_studentgoclass` VALUES ('628', '52', '', '1', '1', '2016-10-19 10:07:17', '201610191071759391488');
INSERT INTO `tb_studentgoclass` VALUES ('629', '52', '', '1', '1', '2016-10-19 10:07:38', '201610191073867115796');
INSERT INTO `tb_studentgoclass` VALUES ('630', '52', '', '1', '1', '2016-10-19 10:08:00', '20161019108048464918');
INSERT INTO `tb_studentgoclass` VALUES ('631', '5', '', '1', '1', '2016-10-19 10:16:22', '2016101910162228151981');
INSERT INTO `tb_studentgoclass` VALUES ('632', '5', '', '1', '1', '2016-10-19 10:16:42', '2016101910164271817978');
INSERT INTO `tb_studentgoclass` VALUES ('633', '51', '', '1', '1', '2016-10-19 10:23:35', '201610191023351253094');
INSERT INTO `tb_studentgoclass` VALUES ('634', '66', '', '1', '1', '2016-10-19 10:23:35', '201610191023351253094');
INSERT INTO `tb_studentgoclass` VALUES ('635', '4', '', '1', '1', '2016-10-19 10:23:35', '201610191023351253094');
INSERT INTO `tb_studentgoclass` VALUES ('636', '60', '', '1', '1', '2016-10-19 10:23:35', '201610191023351253094');
INSERT INTO `tb_studentgoclass` VALUES ('637', '5', '', '1', '1', '2016-10-19 10:23:35', '201610191023351253094');
INSERT INTO `tb_studentgoclass` VALUES ('638', '70', '', '1', '1', '2016-10-19 10:25:05', '201610191025557894350');
INSERT INTO `tb_studentgoclass` VALUES ('639', '51', '', '1', '1', '2016-10-19 10:25:05', '201610191025557894350');
INSERT INTO `tb_studentgoclass` VALUES ('640', '66', '', '1', '1', '2016-10-19 10:25:05', '201610191025557894350');
INSERT INTO `tb_studentgoclass` VALUES ('641', '4', '', '1', '1', '2016-10-19 10:25:05', '201610191025557894350');
INSERT INTO `tb_studentgoclass` VALUES ('642', '60', '', '1', '1', '2016-10-19 10:25:05', '201610191025557894350');
INSERT INTO `tb_studentgoclass` VALUES ('643', '5', '', '1', '1', '2016-10-19 10:25:05', '201610191025557894350');
INSERT INTO `tb_studentgoclass` VALUES ('644', '93', '', '1', '1', '2016-10-19 10:29:05', '201610191029598441599');
INSERT INTO `tb_studentgoclass` VALUES ('645', '68', '', '1', '1', '2016-10-19 10:29:05', '201610191029598441599');
INSERT INTO `tb_studentgoclass` VALUES ('646', '67', '', '1', '1', '2016-10-19 10:29:05', '201610191029598441599');
INSERT INTO `tb_studentgoclass` VALUES ('647', '70', '', '1', '1', '2016-10-19 10:29:05', '201610191029598441599');
INSERT INTO `tb_studentgoclass` VALUES ('648', '51', '', '1', '1', '2016-10-19 10:29:05', '201610191029598441599');
INSERT INTO `tb_studentgoclass` VALUES ('649', '66', '', '1', '1', '2016-10-19 10:29:05', '201610191029598441599');
INSERT INTO `tb_studentgoclass` VALUES ('650', '4', '', '1', '1', '2016-10-19 10:29:05', '201610191029598441599');
INSERT INTO `tb_studentgoclass` VALUES ('651', '60', '', '1', '1', '2016-10-19 10:29:05', '201610191029598441599');
INSERT INTO `tb_studentgoclass` VALUES ('652', '5', '', '1', '1', '2016-10-19 10:29:05', '201610191029598441599');
INSERT INTO `tb_studentgoclass` VALUES ('653', '69', '', '1', '1', '2016-10-19 10:30:53', '2016101910305329690569');
INSERT INTO `tb_studentgoclass` VALUES ('654', '93', '', '1', '1', '2016-10-19 10:30:53', '2016101910305329690569');
INSERT INTO `tb_studentgoclass` VALUES ('655', '68', '', '1', '1', '2016-10-19 10:30:53', '2016101910305329690569');
INSERT INTO `tb_studentgoclass` VALUES ('656', '67', '', '1', '1', '2016-10-19 10:30:53', '2016101910305329690569');
INSERT INTO `tb_studentgoclass` VALUES ('657', '70', '', '1', '1', '2016-10-19 10:30:53', '2016101910305329690569');
INSERT INTO `tb_studentgoclass` VALUES ('658', '51', '', '1', '1', '2016-10-19 10:30:53', '2016101910305329690569');
INSERT INTO `tb_studentgoclass` VALUES ('659', '66', '', '1', '1', '2016-10-19 10:30:53', '2016101910305329690569');
INSERT INTO `tb_studentgoclass` VALUES ('660', '4', '', '1', '1', '2016-10-19 10:30:53', '2016101910305329690569');
INSERT INTO `tb_studentgoclass` VALUES ('661', '60', '', '1', '1', '2016-10-19 10:30:53', '2016101910305329690569');
INSERT INTO `tb_studentgoclass` VALUES ('662', '5', '', '1', '1', '2016-10-19 10:30:53', '2016101910305329690569');
INSERT INTO `tb_studentgoclass` VALUES ('663', '71', '', '0', '0', '2016-10-19 11:00:50', '201610191105093763291');
INSERT INTO `tb_studentgoclass` VALUES ('664', '15', '', '1', '1', '2016-10-19 11:00:50', '201610191105093763291');
INSERT INTO `tb_studentgoclass` VALUES ('665', '42', '', '1', '1', '2016-10-19 11:00:50', '201610191105093763291');
INSERT INTO `tb_studentgoclass` VALUES ('666', '65', '', '0', '0', '2016-10-19 11:00:50', '201610191105093763291');
INSERT INTO `tb_studentgoclass` VALUES ('667', '71', '', '1', '1', '2016-10-19 11:01:13', '201610191111370391943');
INSERT INTO `tb_studentgoclass` VALUES ('668', '15', '', '1', '1', '2016-10-19 11:01:13', '201610191111370391943');
INSERT INTO `tb_studentgoclass` VALUES ('669', '42', '', '1', '1', '2016-10-19 11:01:13', '201610191111370391943');
INSERT INTO `tb_studentgoclass` VALUES ('670', '65', '', '0', '0', '2016-10-19 11:01:13', '201610191111370391943');
INSERT INTO `tb_studentgoclass` VALUES ('671', '34', '', '1', '1', '2016-10-19 11:08:21', '201610191182146868788');
INSERT INTO `tb_studentgoclass` VALUES ('672', '104', '', '1', '1', '2016-10-19 11:08:21', '201610191182146868788');
INSERT INTO `tb_studentgoclass` VALUES ('673', '106', '', '1', '1', '2016-10-19 11:08:21', '201610191182146868788');
INSERT INTO `tb_studentgoclass` VALUES ('674', '102', '', '1', '1', '2016-10-19 11:08:21', '201610191182146868788');
INSERT INTO `tb_studentgoclass` VALUES ('675', '92', '', '1', '1', '2016-10-19 11:12:54', '2016101911125476540401');
INSERT INTO `tb_studentgoclass` VALUES ('676', '91', '', '1', '1', '2016-10-19 11:12:54', '2016101911125476540401');

-- ----------------------------
-- Table structure for `tb_studentinfo`
-- ----------------------------
DROP TABLE IF EXISTS `tb_studentinfo`;
CREATE TABLE `tb_studentinfo` (
  `id` int(11) NOT NULL DEFAULT '0',
  `tel` varchar(11) DEFAULT NULL,
  `parentTel` varchar(11) DEFAULT NULL,
  `addr` varchar(100) DEFAULT NULL,
  `school` int(11) DEFAULT NULL,
  `grade` int(11) DEFAULT NULL,
  `class` varchar(50) DEFAULT NULL,
  `qq` varchar(50) DEFAULT NULL,
  `weixin` varchar(50) DEFAULT NULL,
  `state` tinyint(4) DEFAULT '0' COMMENT '0离校，1在校',
  `updateTime` datetime DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `id` FOREIGN KEY (`id`) REFERENCES `tb_student` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_studentinfo
-- ----------------------------
INSERT INTO `tb_studentinfo` VALUES ('1', '1878874906', '', null, '15', '9', null, null, null, '2', '2016-10-09 09:48:43', '湖南怀化的学生');
INSERT INTO `tb_studentinfo` VALUES ('2', '18585568471', '18585565725', null, '17', '11', null, null, null, '1', '2016-10-09 17:36:15', '1对1 ');
INSERT INTO `tb_studentinfo` VALUES ('3', '15186817739', '15186896238', null, '2', '9', null, null, null, '1', '2016-10-09 16:58:37', '');
INSERT INTO `tb_studentinfo` VALUES ('4', '18285576757', '', null, '15', '9', null, null, null, '1', '2016-10-09 16:44:41', '');
INSERT INTO `tb_studentinfo` VALUES ('5', '12331223333', '', null, '5', '9', null, null, null, '1', '2016-10-09 15:28:09', '');
INSERT INTO `tb_studentinfo` VALUES ('6', '18212269772', '', null, '2', '9', null, null, null, '1', '2016-10-09 16:43:09', '');
INSERT INTO `tb_studentinfo` VALUES ('7', '18585515815', '', null, '2', '9', null, null, null, '1', '2016-10-09 16:44:14', '');
INSERT INTO `tb_studentinfo` VALUES ('8', '13329654989', '', null, '2', '9', null, null, null, '1', '2016-10-09 16:45:00', '');
INSERT INTO `tb_studentinfo` VALUES ('9', '13310458543', '', null, '2', '9', null, null, null, '1', '2016-10-09 16:46:00', '');
INSERT INTO `tb_studentinfo` VALUES ('10', '18386647818', '', null, '2', '9', null, null, null, '1', '2016-10-09 16:46:39', '');
INSERT INTO `tb_studentinfo` VALUES ('11', '18385813035', '', null, '17', '9', null, null, null, '1', '2016-10-09 16:48:18', '');
INSERT INTO `tb_studentinfo` VALUES ('12', '13765575557', '', null, '2', '9', null, null, null, '1', '2016-10-09 16:50:49', '');
INSERT INTO `tb_studentinfo` VALUES ('13', '15185733668', '', null, '18', '10', null, null, null, '1', '2016-10-09 16:51:28', '');
INSERT INTO `tb_studentinfo` VALUES ('14', '15085699383', '15085226082', null, '2', '9', null, null, null, '1', '2016-10-09 17:12:06', '');
INSERT INTO `tb_studentinfo` VALUES ('15', '18184563952', '', null, '2', '9', null, null, null, '1', '2016-10-09 16:52:47', '');
INSERT INTO `tb_studentinfo` VALUES ('16', '18212337998', '', null, '18', '8', null, null, null, '1', '2016-10-09 16:53:00', '');
INSERT INTO `tb_studentinfo` VALUES ('17', '15185617725', '', null, '2', '9', null, null, null, '1', '2016-10-09 16:53:39', '');
INSERT INTO `tb_studentinfo` VALUES ('18', '15121477466', '', null, '16', '8', null, null, null, '1', '2016-10-09 16:53:59', '');
INSERT INTO `tb_studentinfo` VALUES ('19', '11111111111', '13985275405', null, '16', '8', null, null, null, '1', '2016-10-09 16:55:37', '');
INSERT INTO `tb_studentinfo` VALUES ('20', '15286635201', '13985294908', null, '16', '8', null, null, null, '1', '2016-10-09 16:56:59', '');
INSERT INTO `tb_studentinfo` VALUES ('21', '18585556572', '', null, '1', '9', null, null, null, '1', '2016-10-09 16:57:51', '');
INSERT INTO `tb_studentinfo` VALUES ('22', '18212432725', '', null, '11', '12', null, null, null, '1', '2016-10-09 16:59:13', '');
INSERT INTO `tb_studentinfo` VALUES ('23', '13595546629', '', null, '4', '8', null, null, null, '1', '2016-10-09 16:59:58', '');
INSERT INTO `tb_studentinfo` VALUES ('24', '15286350346', '', null, '17', '11', null, null, null, '1', '2016-10-09 17:01:09', '');
INSERT INTO `tb_studentinfo` VALUES ('25', '11111111111', '13885540334', null, '15', '9', null, null, null, '1', '2016-10-09 17:02:04', '');
INSERT INTO `tb_studentinfo` VALUES ('26', '15185725867', '', null, '3', '10', null, null, null, '1', '2016-10-09 17:02:55', '');
INSERT INTO `tb_studentinfo` VALUES ('27', '18230748789', '', null, '3', '10', null, null, null, '2', '2016-10-09 17:03:41', '');
INSERT INTO `tb_studentinfo` VALUES ('28', '15286359318', '13885530171', null, '17', '12', null, null, null, '1', '2016-10-12 20:36:26', '');
INSERT INTO `tb_studentinfo` VALUES ('29', '11111111111', '18608535222', null, '15', '3', null, null, null, '1', '2016-10-09 17:05:34', '');
INSERT INTO `tb_studentinfo` VALUES ('30', '11111111111', '18608535222', null, '5', '8', null, null, null, '1', '2016-10-09 17:07:02', '');
INSERT INTO `tb_studentinfo` VALUES ('31', '15285266185', '', null, '17', '12', null, null, null, '1', '2016-10-09 17:09:44', '');
INSERT INTO `tb_studentinfo` VALUES ('32', '18984608733', '', null, '1', '12', null, null, null, '1', '2016-10-09 17:19:58', '');
INSERT INTO `tb_studentinfo` VALUES ('33', '11111111111', '13765503555', null, '15', '5', null, null, null, '1', '2016-10-09 17:20:09', '');
INSERT INTO `tb_studentinfo` VALUES ('34', '18375286890', '', null, '11', '9', null, null, null, '0', '2016-10-09 17:20:58', '');
INSERT INTO `tb_studentinfo` VALUES ('35', '18286500660', '', null, '11', '9', null, null, null, '0', '2016-10-09 17:21:44', '');
INSERT INTO `tb_studentinfo` VALUES ('36', '13595529924', '13595512217', null, '16', '12', null, null, null, '1', '2016-10-09 17:22:45', '');
INSERT INTO `tb_studentinfo` VALUES ('37', '18798579961', '', null, '15', '9', null, null, null, '1', '2016-10-18 16:39:02', '');
INSERT INTO `tb_studentinfo` VALUES ('38', '18998570409', '', null, '15', '12', null, null, null, '1', '2016-10-09 17:26:03', '');
INSERT INTO `tb_studentinfo` VALUES ('39', '13765556284', '13985297045', null, '17', '9', null, null, null, '1', '2016-10-09 17:27:08', '');
INSERT INTO `tb_studentinfo` VALUES ('40', '18184580512', '13885524853', null, '1', '12', null, null, null, '1', '2016-10-09 17:27:31', '');
INSERT INTO `tb_studentinfo` VALUES ('41', '11111111111', '15121418068', null, '3', '10', null, null, null, '1', '2016-10-09 17:27:49', '');
INSERT INTO `tb_studentinfo` VALUES ('42', '18375286890', '15185581371', null, '4', '9', null, null, null, '1', '2016-10-12 14:12:01', '');
INSERT INTO `tb_studentinfo` VALUES ('43', '15585359114', '', null, '12', '9', null, null, null, '1', '2016-10-09 17:29:53', '');
INSERT INTO `tb_studentinfo` VALUES ('44', '11111111111', '15185745833', null, '17', '11', null, null, null, '1', '2016-10-09 17:31:39', '');
INSERT INTO `tb_studentinfo` VALUES ('45', '11111111111', '15185682058', null, '15', '6', null, null, null, '1', '2016-10-09 17:38:29', '');
INSERT INTO `tb_studentinfo` VALUES ('46', '11111111111', '15185682058', null, '15', '6', null, null, null, '1', '2016-10-09 17:40:33', '');
INSERT INTO `tb_studentinfo` VALUES ('47', '15870223059', '', null, '11', '8', null, null, null, '1', '2016-10-18 20:54:12', '');
INSERT INTO `tb_studentinfo` VALUES ('48', '11111111111', '15186918036', null, '17', '11', null, null, null, '1', '2016-10-12 14:16:46', '');
INSERT INTO `tb_studentinfo` VALUES ('49', '18386647818', '', null, '2', '9', null, null, null, '1', '2016-10-09 17:44:28', '');
INSERT INTO `tb_studentinfo` VALUES ('50', '15186792181', '', null, '16', '11', null, null, null, '1', '2016-10-11 15:54:59', '');
INSERT INTO `tb_studentinfo` VALUES ('51', '18798507982', '18685515055', null, '5', '9', null, null, null, '1', '2016-10-09 17:46:53', '');
INSERT INTO `tb_studentinfo` VALUES ('52', '13398555315', '13368553805', null, '4', '9', null, null, null, '1', '2016-10-11 16:33:08', '');
INSERT INTO `tb_studentinfo` VALUES ('53', '', '', null, '1', '12', null, null, null, '1', '2016-10-11 17:09:20', '');
INSERT INTO `tb_studentinfo` VALUES ('54', '15185730719', '15121450802', null, '15', '8', null, null, null, '1', '2016-10-12 09:54:41', '');
INSERT INTO `tb_studentinfo` VALUES ('55', '15185667518', '', null, '15', '8', null, null, null, '1', '2016-10-12 10:01:04', '');
INSERT INTO `tb_studentinfo` VALUES ('56', '', '', null, '10', '8', null, null, null, '1', '2016-10-12 10:56:34', '');
INSERT INTO `tb_studentinfo` VALUES ('57', '', '', null, '10', '8', null, null, null, '1', '2016-10-12 11:21:29', '');
INSERT INTO `tb_studentinfo` VALUES ('58', '', '', null, '4', '8', null, null, null, '1', '2016-10-12 11:23:24', '');
INSERT INTO `tb_studentinfo` VALUES ('59', '15186797272', '', null, '13', '8', null, null, null, '1', '2016-10-12 11:25:29', '');
INSERT INTO `tb_studentinfo` VALUES ('60', '18386751015', '', null, '2', '9', null, null, null, '1', '2016-10-12 11:33:33', '');
INSERT INTO `tb_studentinfo` VALUES ('61', '', '', null, '10', '8', null, null, null, '1', '2016-10-12 11:28:02', '');
INSERT INTO `tb_studentinfo` VALUES ('62', '111111111', '', null, '4', '8', null, null, null, '1', '2016-10-12 14:01:45', '');
INSERT INTO `tb_studentinfo` VALUES ('63', '13885559201', '13628552818', null, '15', '9', null, null, null, '1', '2016-10-12 11:41:49', '');
INSERT INTO `tb_studentinfo` VALUES ('64', '13765551658', '15185726964', null, '15', '9', null, null, null, '1', '2016-10-12 11:46:44', '');
INSERT INTO `tb_studentinfo` VALUES ('65', '', '', null, '4', '9', null, null, null, '1', '2016-10-12 13:53:55', '');
INSERT INTO `tb_studentinfo` VALUES ('66', '', '', null, '15', '9', null, null, null, '1', '2016-10-12 13:56:14', '');
INSERT INTO `tb_studentinfo` VALUES ('67', '', '', null, '2', '9', null, null, null, '1', '2016-10-12 13:56:44', '');
INSERT INTO `tb_studentinfo` VALUES ('68', '1111111111', '', null, '6', '9', null, null, null, '1', '2016-10-12 14:01:24', '');
INSERT INTO `tb_studentinfo` VALUES ('69', '', '', null, '6', '9', null, null, null, '1', '2016-10-12 13:57:40', '');
INSERT INTO `tb_studentinfo` VALUES ('70', '15185733668', '', null, '15', '9', null, null, null, '1', '2016-10-12 14:01:02', '');
INSERT INTO `tb_studentinfo` VALUES ('71', '', '', null, '15', '9', null, null, null, '1', '2016-10-12 14:12:41', '');
INSERT INTO `tb_studentinfo` VALUES ('72', '15329987760', '18085551658', null, '11', '8', null, null, null, '1', '2016-10-12 14:13:37', '');
INSERT INTO `tb_studentinfo` VALUES ('73', '18285590186', '18285599379', null, '15', '8', null, null, null, '1', '2016-10-12 14:25:30', '');
INSERT INTO `tb_studentinfo` VALUES ('74', '15902554402', '', null, '17', '11', null, null, null, '1', '2016-10-12 14:56:03', '');
INSERT INTO `tb_studentinfo` VALUES ('75', '15186880355', '', null, '15', '11', null, null, null, '1', '2016-10-12 15:03:03', '');
INSERT INTO `tb_studentinfo` VALUES ('76', '15286312188', '13668554921', null, '15', '10', null, null, null, '1', '2016-10-12 15:22:32', '');
INSERT INTO `tb_studentinfo` VALUES ('77', '', '15186816266', null, '15', '10', null, null, null, '1', '2016-10-12 15:26:00', '');
INSERT INTO `tb_studentinfo` VALUES ('78', '13086956547', '', null, '15', '11', null, null, null, '1', '2016-10-12 16:29:38', '');
INSERT INTO `tb_studentinfo` VALUES ('79', '13508559429', '', null, '16', '11', null, null, null, '1', '2016-10-12 16:38:52', '');
INSERT INTO `tb_studentinfo` VALUES ('80', '13638559235', '', null, '15', '11', null, null, null, '1', '2016-10-12 16:42:45', '');
INSERT INTO `tb_studentinfo` VALUES ('81', '13765594337', '', null, '15', '11', null, null, null, '1', '2016-10-12 16:51:56', '');
INSERT INTO `tb_studentinfo` VALUES ('82', '13765532492', '', null, '15', '11', null, null, null, '1', '2016-10-12 16:46:14', '');
INSERT INTO `tb_studentinfo` VALUES ('83', '18908559979', '', null, '15', '12', null, null, null, '1', '2016-10-12 16:55:17', '');
INSERT INTO `tb_studentinfo` VALUES ('84', '18585552905', '', null, '15', '12', null, null, null, '1', '2016-10-12 16:58:21', '');
INSERT INTO `tb_studentinfo` VALUES ('85', '18386609769', '', null, '15', '12', null, null, null, '1', '2016-10-12 17:02:13', '');
INSERT INTO `tb_studentinfo` VALUES ('86', '15870223059', '', null, '15', '12', null, null, null, '1', '2016-10-18 20:53:38', '');
INSERT INTO `tb_studentinfo` VALUES ('87', '15285288606', '', null, '15', '12', null, null, null, '1', '2016-10-12 17:10:21', '');
INSERT INTO `tb_studentinfo` VALUES ('88', '18212432725', '', null, '11', '12', null, null, null, '1', '2016-10-12 17:12:33', '');
INSERT INTO `tb_studentinfo` VALUES ('89', '15285271581', '', null, '12', '9', null, null, null, '1', '2016-10-13 11:32:18', '');
INSERT INTO `tb_studentinfo` VALUES ('90', '13885503889', '', null, '2', '9', null, null, null, '1', '2016-10-13 11:44:43', '');
INSERT INTO `tb_studentinfo` VALUES ('91', '15186876252', '', null, '9', '9', null, null, null, '2', '2016-10-14 08:59:29', '家庭有点困难');
INSERT INTO `tb_studentinfo` VALUES ('92', '15339552621', '', null, '9', '9', null, null, null, '2', '2016-10-14 09:00:29', '跟着杨菊香一起过来咨询的');
INSERT INTO `tb_studentinfo` VALUES ('93', '15985531692', '17605651828', null, '9', '9', null, null, null, '1', '2016-10-15 12:59:11', '老生');
INSERT INTO `tb_studentinfo` VALUES ('94', '18385823939', '', null, '6', '8', null, null, null, '1', '2016-10-15 12:57:43', '');
INSERT INTO `tb_studentinfo` VALUES ('95', '15085257128', '13765507908', null, '15', '8', null, null, null, '1', '2016-10-18 09:26:04', '');
INSERT INTO `tb_studentinfo` VALUES ('96', '', '15186895215', null, '15', '8', null, null, null, '1', '2016-10-18 09:26:35', '');
INSERT INTO `tb_studentinfo` VALUES ('97', '13765513832', '15885826812', null, '15', '9', null, null, null, '1', '2016-10-18 10:07:08', '');
INSERT INTO `tb_studentinfo` VALUES ('98', '18385792654', '', null, '15', '9', null, null, null, '1', '2016-10-18 10:31:02', '');
INSERT INTO `tb_studentinfo` VALUES ('99', '', '', null, '15', '9', null, null, null, '1', '2016-10-18 10:41:01', '');
INSERT INTO `tb_studentinfo` VALUES ('100', '18084544451', '15186787894', null, '15', '9', null, null, null, '1', '2016-10-18 11:07:48', '');
INSERT INTO `tb_studentinfo` VALUES ('101', '13595592962', '18786415077', null, '15', '9', null, null, null, '1', '2016-10-18 16:42:13', '');
INSERT INTO `tb_studentinfo` VALUES ('102', '', '', null, '9', '9', null, null, null, '1', '2016-10-18 16:53:14', '');
INSERT INTO `tb_studentinfo` VALUES ('103', '', '15985517770', null, '15', '9', null, null, null, '1', '2016-10-18 17:29:53', '');
INSERT INTO `tb_studentinfo` VALUES ('104', '15186900286', '13595506908', null, '15', '9', null, null, null, '1', '2016-10-18 17:40:40', '');
INSERT INTO `tb_studentinfo` VALUES ('105', '', '', null, '15', '9', null, null, null, '1', '2016-10-18 17:44:08', '');
INSERT INTO `tb_studentinfo` VALUES ('106', '', '', null, '15', '9', null, null, null, '1', '2016-10-18 17:45:08', '');
INSERT INTO `tb_studentinfo` VALUES ('107', '', '18385707184', null, '15', '9', null, null, null, '1', '2016-10-18 17:53:00', '');
INSERT INTO `tb_studentinfo` VALUES ('108', '', '15885827133', null, '15', '9', null, null, null, '1', '2016-10-18 17:54:04', '');
INSERT INTO `tb_studentinfo` VALUES ('109', '', '13368651008', null, '15', '8', null, null, null, '1', '2016-10-18 17:54:47', '');
INSERT INTO `tb_studentinfo` VALUES ('110', '', '', null, '15', '9', null, null, null, '1', '2016-10-18 17:55:32', '');
INSERT INTO `tb_studentinfo` VALUES ('111', '13595579594', '15985567401', null, '15', '9', null, null, null, '1', '2016-10-18 17:56:20', '');
INSERT INTO `tb_studentinfo` VALUES ('112', '', '', null, '15', '8', null, null, null, '1', '2016-10-18 18:19:25', '');
INSERT INTO `tb_studentinfo` VALUES ('113', '', '', null, '15', '12', null, null, null, '1', '2016-10-18 20:40:23', '');
INSERT INTO `tb_studentinfo` VALUES ('114', '', '', null, '15', '12', null, null, null, '1', '2016-10-18 20:46:50', '');
INSERT INTO `tb_studentinfo` VALUES ('115', '', '', null, '15', '12', null, null, null, '1', '2016-10-18 20:48:08', '');
INSERT INTO `tb_studentinfo` VALUES ('116', '', '', null, '15', '12', null, null, null, '1', '2016-10-18 20:48:29', '');
INSERT INTO `tb_studentinfo` VALUES ('117', '18608557669', '', null, '15', '12', null, null, null, '1', '2016-10-18 21:50:39', '');
INSERT INTO `tb_studentinfo` VALUES ('118', '', '', null, '15', '9', null, null, null, '1', '2016-10-18 21:51:55', '');
INSERT INTO `tb_studentinfo` VALUES ('119', '', '18385832969', null, '15', '5', null, null, null, '1', '2016-10-18 22:14:01', '');
INSERT INTO `tb_studentinfo` VALUES ('120', '', '13885500086', null, '15', '6', null, null, null, '1', '2016-10-18 22:17:45', '');
INSERT INTO `tb_studentinfo` VALUES ('121', '', '13885588976', null, '15', '5', null, null, null, '1', '2016-10-18 22:19:54', '');
INSERT INTO `tb_studentinfo` VALUES ('122', '', '', null, '15', '1', null, null, null, '1', '2016-10-18 22:27:10', '');
INSERT INTO `tb_studentinfo` VALUES ('123', '18285527233', '', null, '15', '11', null, null, null, '1', '2016-10-19 09:18:30', '');
INSERT INTO `tb_studentinfo` VALUES ('124', '15186771860', '', null, '15', '11', null, null, null, '1', '2016-10-19 11:01:28', '');
INSERT INTO `tb_studentinfo` VALUES ('125', '18385800294', '', null, '15', '11', null, null, null, '1', '2016-10-19 11:01:14', '');
INSERT INTO `tb_studentinfo` VALUES ('126', '15186780822', '', null, '15', '11', null, null, null, '1', '2016-10-19 11:00:28', '');
INSERT INTO `tb_studentinfo` VALUES ('127', '18085507218', '18908555533', null, '15', '11', null, null, null, '1', '2016-10-19 11:00:07', '');
INSERT INTO `tb_studentinfo` VALUES ('128', '15186834263', '', null, '15', '11', null, null, null, '1', '2016-10-19 11:00:16', '');
INSERT INTO `tb_studentinfo` VALUES ('129', '15121401977', '', null, '15', '11', null, null, null, '1', '2016-10-19 10:59:29', '');
INSERT INTO `tb_studentinfo` VALUES ('130', '15286356290', '', null, '15', '11', null, null, null, '1', '2016-10-19 10:59:19', '');
INSERT INTO `tb_studentinfo` VALUES ('131', '15186792181', '', null, '15', '11', null, null, null, '1', '2016-10-19 10:59:41', '');
INSERT INTO `tb_studentinfo` VALUES ('132', '13765560918', '', null, '15', '12', null, null, null, '1', '2016-10-19 10:59:57', '');
INSERT INTO `tb_studentinfo` VALUES ('133', '13985284559', '', null, '15', '12', null, null, null, '1', '2016-10-19 10:59:07', '');
INSERT INTO `tb_studentinfo` VALUES ('134', '15286637324', '', null, '15', '12', null, null, null, '1', '2016-10-19 10:58:32', '');
INSERT INTO `tb_studentinfo` VALUES ('135', '11111111111', '', null, '15', '12', null, null, null, '1', '2016-10-19 10:58:18', '');
INSERT INTO `tb_studentinfo` VALUES ('136', '11111111111', '', null, '15', '12', null, null, null, '1', '2016-10-19 10:58:45', '');
INSERT INTO `tb_studentinfo` VALUES ('137', '18212335101', '', null, '15', '12', null, null, null, '1', '2016-10-19 15:03:05', '');
INSERT INTO `tb_studentinfo` VALUES ('138', '13379638657', '', null, '15', '12', null, null, null, '1', '2016-10-19 10:56:57', '');
INSERT INTO `tb_studentinfo` VALUES ('139', '15185627125', '', null, '15', '12', null, null, null, '1', '2016-10-19 10:56:49', '');
INSERT INTO `tb_studentinfo` VALUES ('140', '13595594303', '', null, '15', '12', null, null, null, '1', '2016-10-19 10:56:41', '');
INSERT INTO `tb_studentinfo` VALUES ('141', '13595528926', '18386702281', null, '15', '12', null, null, null, '1', '2016-10-19 10:56:31', '');
INSERT INTO `tb_studentinfo` VALUES ('142', '18285500009', '15186796066', null, '15', '12', null, null, null, '1', '2016-10-19 11:05:55', '');
INSERT INTO `tb_studentinfo` VALUES ('143', '15808551510', '', null, '15', '12', null, null, null, '1', '2016-10-19 11:16:13', '');
INSERT INTO `tb_studentinfo` VALUES ('144', '13985284559', '', null, '15', '12', null, null, null, '1', '2016-10-19 11:18:00', '');
INSERT INTO `tb_studentinfo` VALUES ('145', '15085269023', '', null, '15', '12', null, null, null, '1', '2016-10-19 11:19:11', '');
INSERT INTO `tb_studentinfo` VALUES ('146', '13885586961', '', null, '15', '12', null, null, null, '1', '2016-10-19 11:20:45', '');
INSERT INTO `tb_studentinfo` VALUES ('147', '18285534763', '', null, '15', '12', null, null, null, '1', '2016-10-19 11:22:42', '');
INSERT INTO `tb_studentinfo` VALUES ('148', '18286535648', '', null, '15', '12', null, null, null, '1', '2016-10-19 11:29:00', '');
INSERT INTO `tb_studentinfo` VALUES ('149', '13339656480', '', null, '15', '12', null, null, null, '1', '2016-10-19 11:29:46', '');
INSERT INTO `tb_studentinfo` VALUES ('150', '', '15286375789', null, '15', '12', null, null, null, '1', '2016-10-19 11:31:19', '');
INSERT INTO `tb_studentinfo` VALUES ('151', '13595526495', '', null, '15', '12', null, null, null, '1', '2016-10-19 11:32:12', '');
INSERT INTO `tb_studentinfo` VALUES ('152', '18085530599', '', null, '15', '12', null, null, null, '1', '2016-10-19 11:36:49', '');
INSERT INTO `tb_studentinfo` VALUES ('153', '18744837131', '', null, '15', '12', null, null, null, '1', '2016-10-19 11:37:23', '');
INSERT INTO `tb_studentinfo` VALUES ('154', '13595579204', '', null, '15', '12', null, null, null, '1', '2016-10-19 11:38:31', '');
INSERT INTO `tb_studentinfo` VALUES ('155', '15185667923', '', null, '15', '12', null, null, null, '1', '2016-10-19 11:40:36', '');
INSERT INTO `tb_studentinfo` VALUES ('156', '', '', null, '15', '12', null, null, null, '1', '2016-10-19 15:06:35', '');
INSERT INTO `tb_studentinfo` VALUES ('157', '', '', null, '15', '12', null, null, null, '1', '2016-10-19 15:07:28', '');
INSERT INTO `tb_studentinfo` VALUES ('158', '', '', null, '15', '12', null, null, null, '2', '2016-10-19 15:18:21', '');
INSERT INTO `tb_studentinfo` VALUES ('159', '15185557583', '', null, '15', '12', null, null, null, '1', '2016-10-19 15:38:21', '');
INSERT INTO `tb_studentinfo` VALUES ('160', '15329150345', '', null, '15', '12', null, null, null, '1', '2016-10-19 15:39:14', '');
INSERT INTO `tb_studentinfo` VALUES ('161', '15286600704', '', null, '15', '12', null, null, null, '1', '2016-10-19 15:40:34', '');
INSERT INTO `tb_studentinfo` VALUES ('162', '15870282076', '', null, '15', '12', null, null, null, '1', '2016-10-19 15:41:32', '');
INSERT INTO `tb_studentinfo` VALUES ('163', '13985282062', '', null, '15', '12', null, null, null, '1', '2016-10-19 15:42:19', '');
INSERT INTO `tb_studentinfo` VALUES ('164', '18798570409', '', null, '15', '12', null, null, null, '1', '2016-10-19 15:43:32', '');
INSERT INTO `tb_studentinfo` VALUES ('165', '', '', null, '15', '12', null, null, null, '1', '2016-10-19 15:44:20', '');
INSERT INTO `tb_studentinfo` VALUES ('166', '15121423482', '', null, '15', '12', null, null, null, '1', '2016-10-19 15:46:58', '');
INSERT INTO `tb_studentinfo` VALUES ('167', '13885592311', '', null, '15', '12', null, null, null, '1', '2016-10-19 15:48:10', '');
INSERT INTO `tb_studentinfo` VALUES ('168', '18798568984', '', null, '15', '12', null, null, null, '1', '2016-10-19 15:48:55', '');
INSERT INTO `tb_studentinfo` VALUES ('169', '15870249167', '', null, '15', '12', null, null, null, '1', '2016-10-19 15:50:02', '');
INSERT INTO `tb_studentinfo` VALUES ('170', '15286622198', '', null, '15', '12', null, null, null, '1', '2016-10-19 15:50:47', '');
INSERT INTO `tb_studentinfo` VALUES ('171', '18685579204', '', null, '15', '12', null, null, null, '1', '2016-10-19 15:54:10', '');
INSERT INTO `tb_studentinfo` VALUES ('172', '18085512295', '', null, '16', '12', null, null, null, '1', '2016-10-19 15:58:07', '');
INSERT INTO `tb_studentinfo` VALUES ('173', '15121477466', '', null, '16', '12', null, null, null, '0', '2016-10-19 15:59:27', '');
INSERT INTO `tb_studentinfo` VALUES ('174', '', '', null, '15', '12', null, null, null, '1', '2016-10-19 16:00:14', '');
INSERT INTO `tb_studentinfo` VALUES ('175', '18798592040', '', null, '15', '12', null, null, null, '1', '2016-10-19 16:03:00', '');
INSERT INTO `tb_studentinfo` VALUES ('176', '15885133218', '', null, '15', '12', null, null, null, '1', '2016-10-19 16:03:47', '');
INSERT INTO `tb_studentinfo` VALUES ('177', '18585556572', '', null, '15', '12', null, null, null, '1', '2016-10-19 16:04:32', '');

-- ----------------------------
-- Table structure for `tb_studentpay`
-- ----------------------------
DROP TABLE IF EXISTS `tb_studentpay`;
CREATE TABLE `tb_studentpay` (
  `id` varchar(255) NOT NULL,
  `studentId` int(11) DEFAULT NULL,
  `total` decimal(10,2) DEFAULT '0.00',
  `payTime` datetime DEFAULT NULL,
  `model` tinyint(4) DEFAULT '1' COMMENT '缴费方式：1现金，2刷卡，3微信，4支付宝',
  `operator` int(11) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `payerTel` varchar(100) DEFAULT NULL,
  `payer` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `operator` (`operator`),
  CONSTRAINT `operator` FOREIGN KEY (`operator`) REFERENCES `tb_employee` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_studentpay
-- ----------------------------
INSERT INTO `tb_studentpay` VALUES ('201610181524751536862', null, '320.00', '2016-08-10 12:00:00', '1', '15', '', '', '学生本人');
INSERT INTO `tb_studentpay` VALUES ('2016101815331367128564', null, '112.50', '2016-10-12 15:30:04', '1', '9', '', '', '学生本人');
INSERT INTO `tb_studentpay` VALUES ('201610181538566241112', null, '187.50', '2016-10-15 15:35:15', '1', '9', '', '', '学生本人');
INSERT INTO `tb_studentpay` VALUES ('2016101815422767144803', null, '1012.50', '2016-10-16 15:38:26', '1', '9', '', '', '学生本人');
INSERT INTO `tb_studentpay` VALUES ('2016101815442889099967', null, '375.00', '2016-09-07 00:00:00', '1', '15', '', '', '学生本人');
INSERT INTO `tb_studentpay` VALUES ('2016101815454043741462', null, '1012.50', '2016-10-13 15:41:26', '1', '9', '', '', '学生本人');
INSERT INTO `tb_studentpay` VALUES ('20161018154797182367', null, '375.00', '2016-10-16 15:42:50', '1', '9', '', '', '学生本人');
INSERT INTO `tb_studentpay` VALUES ('2016101815505518740652', null, '562.50', '2016-10-13 15:46:19', '1', '9', '', '', '学生本人');
INSERT INTO `tb_studentpay` VALUES ('2016101816401029695179', null, '300.00', '2016-10-13 16:38:12', '1', '9', '', '', '学生本人');
INSERT INTO `tb_studentpay` VALUES ('2016101816535865699764', null, '900.00', '2016-10-15 16:51:04', '1', '9', '', '', '学生本人');
INSERT INTO `tb_studentpay` VALUES ('2016101816541750049226', null, '300.00', '2016-10-10 16:54:05', '1', '9', '', '', '学生家长');
INSERT INTO `tb_studentpay` VALUES ('2016101816544971836581', null, '37.50', '2016-10-14 16:51:50', '1', '9', '', '', '学生本人');
INSERT INTO `tb_studentpay` VALUES ('201610181731321556963', null, '300.00', '2016-10-14 17:30:30', '1', '9', '', '', '学生本人');
INSERT INTO `tb_studentpay` VALUES ('2016101817385695399224', null, '937.50', '2016-10-09 17:37:54', '1', '9', '', '', '学生本人');
INSERT INTO `tb_studentpay` VALUES ('2016101817415190632822', null, '937.50', '2016-10-15 17:40:50', '1', '9', '', '', '学生本人');
INSERT INTO `tb_studentpay` VALUES ('2016101817432535945257', null, '975.00', '2016-10-17 17:42:24', '1', '9', '', '', '学生本人');
INSERT INTO `tb_studentpay` VALUES ('2016101817455042156519', null, '300.00', '2016-10-14 17:44:50', '1', '9', '', '', '学生本人');
INSERT INTO `tb_studentpay` VALUES ('2016101817465695387593', null, '300.00', '2016-10-12 17:45:56', '1', '9', '', '', '学生本人');
INSERT INTO `tb_studentpay` VALUES ('201610181801154693292', null, '300.00', '2016-10-07 17:58:59', '1', '9', '', '', '学生本人');
INSERT INTO `tb_studentpay` VALUES ('2016101818124069761', null, '300.00', '2016-10-05 18:00:01', '1', '9', '', '', '学生本人');
INSERT INTO `tb_studentpay` VALUES ('2016101818131071887942', null, '525.00', '2016-10-07 18:12:07', '1', '9', '', '', '学生本人');
INSERT INTO `tb_studentpay` VALUES ('2016101818143053128905', null, '225.00', '2016-10-15 18:13:24', '1', '9', '', '', '学生本人');
INSERT INTO `tb_studentpay` VALUES ('2016101818181168761139', null, '300.00', '2016-10-10 18:17:09', '1', '9', '', '', '学生本人');
INSERT INTO `tb_studentpay` VALUES ('20161018182481718900', null, '300.00', '2016-10-10 18:01:47', '1', '9', '', '', '学生本人');
INSERT INTO `tb_studentpay` VALUES ('2016101820193139048054', null, '1100.00', '2016-10-12 20:18:31', '1', '9', '', '', '学生本人');
INSERT INTO `tb_studentpay` VALUES ('2016101820205568785683', null, '100.00', '2016-10-15 20:19:51', '1', '9', '', '', '学生本人');
INSERT INTO `tb_studentpay` VALUES ('20161018202143029321', null, '1100.00', '2016-10-13 20:20:43', '1', '9', '', '', '学生本人');
INSERT INTO `tb_studentpay` VALUES ('2016101820431215660573', null, '800.00', '2016-10-12 20:42:13', '1', '9', '', '', '学生本人');
INSERT INTO `tb_studentpay` VALUES ('2016101820514990685162', null, '400.00', '2016-10-20 20:50:49', '1', '9', '', '', '学生本人');
INSERT INTO `tb_studentpay` VALUES ('2016101821101295361843', null, '1440.00', '2016-10-12 21:09:10', '1', '15', '', '', '学生本人');
INSERT INTO `tb_studentpay` VALUES ('2016101821172923489048', null, '50.00', '2016-10-12 21:16:29', '1', '15', '', '', '学生本人');
INSERT INTO `tb_studentpay` VALUES ('2016101821205304115', null, '50.00', '2016-10-08 21:19:50', '1', '9', '', '', '学生本人');
INSERT INTO `tb_studentpay` VALUES ('2016101821263237589093', null, '1400.00', '2016-10-13 21:25:28', '1', '15', '', '', '学生本人');
INSERT INTO `tb_studentpay` VALUES ('2016101821272653111381', null, '350.00', '2016-10-08 21:26:24', '1', '9', '', '', '学生本人');
INSERT INTO `tb_studentpay` VALUES ('201610182135839040161', null, '900.00', '2016-09-10 00:00:00', '1', '15', '', '', '学生本人');
INSERT INTO `tb_studentpay` VALUES ('2016101821463637519378', null, '825.00', '2016-10-13 21:45:24', '1', '9', '', '', '学生本人');
INSERT INTO `tb_studentpay` VALUES ('201610182147379334795', null, '337.50', '2016-10-06 21:46:26', '1', '9', '', '', '学生本人');
INSERT INTO `tb_studentpay` VALUES ('2016101821482835914920', null, '225.00', '2016-10-08 21:47:16', '1', '9', '', '', '学生家长');
INSERT INTO `tb_studentpay` VALUES ('2016101821545435985771', null, '1040.00', '2016-10-13 21:54:46', '1', '9', '', '', '学生本人');
INSERT INTO `tb_studentpay` VALUES ('2016101821574732814845', null, '1170.00', '2016-10-15 21:57:43', '1', '9', '', '', '学生本人');
INSERT INTO `tb_studentpay` VALUES ('201610182172065658100', null, '850.00', '2016-09-09 00:00:00', '1', '15', '', '', '学生本人');
INSERT INTO `tb_studentpay` VALUES ('2016101822163390665051', null, '810.00', '2016-10-14 22:16:24', '1', '15', '', '', '家长');
INSERT INTO `tb_studentpay` VALUES ('201610182218583153987', null, '810.00', '2016-10-03 22:18:48', '1', '9', '', '', '家长');
INSERT INTO `tb_studentpay` VALUES ('2016101822222514049433', null, '720.00', '2016-10-04 22:22:14', '1', '9', '', '', '家长');
INSERT INTO `tb_studentpay` VALUES ('201610182225968713872', null, '620.00', '2016-09-10 00:00:00', '1', '15', '', '', '学生家长');
INSERT INTO `tb_studentpay` VALUES ('201610182230928144255', null, '700.00', '2016-10-15 10:29:22', '1', '5', '', '', '学生家长');
INSERT INTO `tb_studentpay` VALUES ('2016101910115470357916', null, '1100.00', '2016-10-07 10:11:06', '1', '15', '', '', '学生家长');
INSERT INTO `tb_studentpay` VALUES ('201610191019229655459', null, '300.00', '2016-09-17 00:00:00', '1', '15', '', '', '学生本人');
INSERT INTO `tb_studentpay` VALUES ('2016101910193775057144', null, '187.50', '2016-10-14 10:19:31', '1', '9', '', '', '学生本人');
INSERT INTO `tb_studentpay` VALUES ('201610191023146816983', null, '1087.50', '2016-10-09 10:22:42', '1', '9', '', '', '学生本人');
INSERT INTO `tb_studentpay` VALUES ('2016101910271493720497', null, '1200.00', '2016-10-14 10:26:25', '1', '9', '', '', '学生本人');
INSERT INTO `tb_studentpay` VALUES ('2016101910282857855162', null, '300.00', '2016-10-15 10:27:24', '1', '9', '', '', '学生本人');
INSERT INTO `tb_studentpay` VALUES ('201610191059845376035', null, '300.00', '2016-10-08 10:57:13', '1', '9', '', '', '学生本人');
INSERT INTO `tb_studentpay` VALUES ('2016101911001517626', null, '300.00', '2016-10-15 10:58:07', '1', '9', '', '', '学生本人');
INSERT INTO `tb_studentpay` VALUES ('20161019113532038022', null, '300.00', '2016-10-17 11:02:00', '1', '9', '', '', '学生本人');
INSERT INTO `tb_studentpay` VALUES ('201610191144696849761', null, '300.00', '2016-10-10 11:02:52', '1', '9', '', '', '学生本人');
INSERT INTO `tb_studentpay` VALUES ('201610191155381228498', null, '300.00', '2016-10-08 11:04:01', '1', '9', '', '', '学生本人');
INSERT INTO `tb_studentpay` VALUES ('20161019144123116217', null, '1200.00', '2016-10-07 14:39:49', '1', '15', '', '', '学生本人');

-- ----------------------------
-- Table structure for `tb_studentregistration`
-- ----------------------------
DROP TABLE IF EXISTS `tb_studentregistration`;
CREATE TABLE `tb_studentregistration` (
  `id` varchar(100) NOT NULL,
  `studentId` int(11) DEFAULT NULL,
  `regTime` datetime DEFAULT NULL,
  `subjectId` int(11) DEFAULT NULL,
  `classTypeId` int(11) DEFAULT NULL,
  `availd` tinyint(4) DEFAULT '1' COMMENT '1有效，0无效',
  `finishNumber` int(11) DEFAULT '0',
  `youxiaoNumber` int(11) DEFAULT '0',
  `gradeId` int(11) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `sr_studentId` (`studentId`),
  KEY `sr_subjectId` (`subjectId`),
  KEY `sr_classTypeId` (`classTypeId`),
  KEY `sr_gradeId` (`gradeId`),
  CONSTRAINT `sr_classTypeId` FOREIGN KEY (`classTypeId`) REFERENCES `tb_classtype` (`id`),
  CONSTRAINT `sr_gradeId` FOREIGN KEY (`gradeId`) REFERENCES `tb_grade` (`id`),
  CONSTRAINT `sr_studentId` FOREIGN KEY (`studentId`) REFERENCES `tb_student` (`id`),
  CONSTRAINT `sr_subjectId` FOREIGN KEY (`subjectId`) REFERENCES `tb_subject` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_studentregistration
-- ----------------------------
INSERT INTO `tb_studentregistration` VALUES ('2016101815232290651392', '97', '2016-10-18 15:23:22', '4', '3', '1', '0', '2', '9', '');
INSERT INTO `tb_studentregistration` VALUES ('2016101815252734340902', '52', '2016-10-18 15:25:27', '4', '3', '1', '8', '0', '9', '');
INSERT INTO `tb_studentregistration` VALUES ('201610181532232652680', '6', '2016-10-18 15:32:23', '4', '1', '1', '5', '8', '9', '');
INSERT INTO `tb_studentregistration` VALUES ('2016101815372198489190', '7', '2016-10-18 15:37:21', '4', '1', '1', '5', '0', '9', '');
INSERT INTO `tb_studentregistration` VALUES ('2016101815393379695035', '8', '2016-10-18 15:39:33', '4', '1', '1', '5', '0', '9', '');
INSERT INTO `tb_studentregistration` VALUES ('2016101815395981294011', '9', '2016-10-18 15:39:59', '4', '1', '1', '5', '0', '9', '');
INSERT INTO `tb_studentregistration` VALUES ('2016101815402723457413', '10', '2016-10-18 15:40:27', '4', '1', '1', '5', '0', '9', '');
INSERT INTO `tb_studentregistration` VALUES ('201610181541345937044', '3', '2016-10-18 15:41:34', '4', '1', '1', '5', '27', '9', '');
INSERT INTO `tb_studentregistration` VALUES ('201610181543489312314', '12', '2016-10-18 15:43:48', '4', '1', '1', '5', '10', '9', '');
INSERT INTO `tb_studentregistration` VALUES ('201610181545893715521', '14', '2016-10-18 15:45:08', '4', '1', '1', '5', '27', '9', '');
INSERT INTO `tb_studentregistration` VALUES ('201610181546409390364', '15', '2016-10-18 15:46:40', '4', '1', '1', '5', '7', '9', '');
INSERT INTO `tb_studentregistration` VALUES ('2016101815501929660695', '17', '2016-10-18 15:50:19', '4', '1', '1', '5', '15', '9', '');
INSERT INTO `tb_studentregistration` VALUES ('2016101815513150070285', '98', '2016-10-18 15:51:31', '4', '1', '1', '3', '0', '9', '');
INSERT INTO `tb_studentregistration` VALUES ('201610181552623474652', '99', '2016-10-18 15:52:06', '4', '1', '1', '2', '0', '9', '');
INSERT INTO `tb_studentregistration` VALUES ('201610181637365670755', '100', '2016-10-18 16:37:03', '4', '1', '1', '6', '0', '9', '');
INSERT INTO `tb_studentregistration` VALUES ('2016101816393693745848', '37', '2016-10-18 16:39:36', '4', '1', '1', '6', '8', '9', '');
INSERT INTO `tb_studentregistration` VALUES ('2016101816404914085190', '90', '2016-10-18 16:40:49', '4', '1', '1', '2', '0', '9', '');
INSERT INTO `tb_studentregistration` VALUES ('2016101816423273498808', '101', '2016-10-18 16:42:32', '4', '1', '1', '1', '0', '9', '');
INSERT INTO `tb_studentregistration` VALUES ('20161018165114636008', '43', '2016-10-18 16:51:01', '4', '1', '1', '2', '32', '9', '');
INSERT INTO `tb_studentregistration` VALUES ('201610181653254643140', '89', '2016-10-18 16:53:25', '4', '1', '1', '2', '25', '9', '');
INSERT INTO `tb_studentregistration` VALUES ('201610181653345318167', '102', '2016-10-18 16:53:34', '4', '1', '1', '2', '8', '9', '');
INSERT INTO `tb_studentregistration` VALUES ('2016101817301634388170', '5', '2016-10-18 17:30:16', '3', '1', '1', '7', '0', '9', '');
INSERT INTO `tb_studentregistration` VALUES ('201610181731231218763', '103', '2016-10-18 17:31:02', '3', '1', '1', '7', '8', '9', '');
INSERT INTO `tb_studentregistration` VALUES ('2016101817355764082886', '12', '2016-10-18 17:35:57', '3', '1', '1', '1', '0', '9', '');
INSERT INTO `tb_studentregistration` VALUES ('2016101817365831265385', '64', '2016-10-18 17:36:58', '3', '1', '1', '1', '25', '9', '');
INSERT INTO `tb_studentregistration` VALUES ('201610181741893741615', '104', '2016-10-18 17:41:08', '3', '1', '1', '1', '25', '9', '');
INSERT INTO `tb_studentregistration` VALUES ('2016101817423390611233', '14', '2016-10-18 17:42:33', '3', '1', '1', '1', '26', '9', '');
INSERT INTO `tb_studentregistration` VALUES ('2016101817443475071412', '105', '2016-10-18 17:44:34', '3', '1', '1', '1', '0', '9', '');
INSERT INTO `tb_studentregistration` VALUES ('2016101817452671893057', '106', '2016-10-18 17:45:26', '3', '1', '1', '1', '8', '9', '');
INSERT INTO `tb_studentregistration` VALUES ('2016101817462943744424', '6', '2016-10-18 17:46:29', '3', '1', '1', '1', '8', '9', '');
INSERT INTO `tb_studentregistration` VALUES ('2016101817583021837811', '107', '2016-10-18 17:58:30', '3', '1', '1', '2', '0', '9', '');
INSERT INTO `tb_studentregistration` VALUES ('2016101817591578119324', '108', '2016-10-18 17:59:15', '3', '1', '1', '2', '8', '9', '');
INSERT INTO `tb_studentregistration` VALUES ('201610181803593783823', '109', '2016-10-18 18:00:35', '3', '1', '1', '2', '8', '9', '');
INSERT INTO `tb_studentregistration` VALUES ('2016101818111731291544', '17', '2016-10-18 18:11:17', '3', '1', '1', '1', '14', '9', '');
INSERT INTO `tb_studentregistration` VALUES ('201610181812289371082', '63', '2016-10-18 18:12:28', '3', '1', '1', '3', '0', '9', '');
INSERT INTO `tb_studentregistration` VALUES ('2016101818135565673588', '16', '2016-10-18 18:13:55', '3', '1', '1', '1', '6', '9', '');
INSERT INTO `tb_studentregistration` VALUES ('20161018181394536254', '110', '2016-10-18 18:01:39', '3', '1', '1', '1', '0', '9', '');
INSERT INTO `tb_studentregistration` VALUES ('2016101818145557898090', '98', '2016-10-18 18:14:55', '3', '1', '1', '1', '0', '9', '');
INSERT INTO `tb_studentregistration` VALUES ('2016101818173756293329', '54', '2016-10-18 18:17:37', '3', '1', '1', '3', '9', '8', '');
INSERT INTO `tb_studentregistration` VALUES ('2016101818184851532609', '55', '2016-10-18 18:18:48', '3', '1', '1', '5', '0', '8', '');
INSERT INTO `tb_studentregistration` VALUES ('2016101818194262592564', '112', '2016-10-18 18:19:42', '3', '1', '1', '1', '0', '8', '');
INSERT INTO `tb_studentregistration` VALUES ('201610181821714077082', '111', '2016-10-18 18:02:17', '3', '1', '1', '3', '8', '9', '');
INSERT INTO `tb_studentregistration` VALUES ('2016101818241675037680', '52', '2016-10-18 18:24:16', '3', '3', '1', '3', '0', '9', '');
INSERT INTO `tb_studentregistration` VALUES ('201610181831040627519', '101', '2016-10-18 18:03:10', '3', '1', '1', '1', '0', '9', '');
INSERT INTO `tb_studentregistration` VALUES ('201610181834542147209', '91', '2016-10-18 18:03:45', '3', '1', '1', '0', '0', '9', '');
INSERT INTO `tb_studentregistration` VALUES ('201610182019345397698', '78', '2016-10-18 20:19:03', '1', '1', '1', '0', '22', '11', '');
INSERT INTO `tb_studentregistration` VALUES ('2016101820202868724544', '26', '2016-10-18 20:20:28', '1', '1', '1', '0', '2', '11', '');
INSERT INTO `tb_studentregistration` VALUES ('2016101820212070341134', '79', '2016-10-18 20:21:20', '1', '1', '1', '0', '22', '11', '');
INSERT INTO `tb_studentregistration` VALUES ('20161018204058187319', '113', '2016-10-18 20:40:58', '1', '1', '1', '7', '0', '12', '');
INSERT INTO `tb_studentregistration` VALUES ('2016101820423565694458', '87', '2016-10-18 20:42:35', '1', '1', '1', '5', '16', '12', '');
INSERT INTO `tb_studentregistration` VALUES ('2016101820452657812092', '88', '2016-10-18 20:45:26', '1', '1', '1', '5', '0', '12', '');
INSERT INTO `tb_studentregistration` VALUES ('2016101820471023463736', '114', '2016-10-18 20:47:10', '1', '1', '1', '4', '0', '12', '');
INSERT INTO `tb_studentregistration` VALUES ('2016101820484731252023', '116', '2016-10-18 20:48:47', '1', '1', '1', '2', '0', '12', '');
INSERT INTO `tb_studentregistration` VALUES ('201610182049121567624', '115', '2016-10-18 20:49:12', '1', '1', '1', '2', '8', '12', '');
INSERT INTO `tb_studentregistration` VALUES ('201610182054297879525', '86', '2016-10-18 20:54:29', '1', '1', '1', '0', '0', '12', '');
INSERT INTO `tb_studentregistration` VALUES ('201610182117121879153', '74', '2016-10-18 21:17:01', '1', '1', '0', '0', '1', '10', '');
INSERT INTO `tb_studentregistration` VALUES ('201610182120218128967', '74', '2016-10-18 21:20:21', '1', '1', '1', '0', '1', '11', '');
INSERT INTO `tb_studentregistration` VALUES ('2016101821245110996772', '77', '2016-10-18 21:24:51', '1', '1', '1', '6', '0', '10', '');
INSERT INTO `tb_studentregistration` VALUES ('201610182126121885582', '76', '2016-10-18 21:26:01', '1', '1', '1', '5', '32', '10', '');
INSERT INTO `tb_studentregistration` VALUES ('201610182127198443660', '41', '2016-10-18 21:27:01', '1', '1', '1', '6', '7', '10', '');
INSERT INTO `tb_studentregistration` VALUES ('201610182132232899885', '48', '2016-10-18 21:03:22', '3', '3', '0', '0', '5', '11', '');
INSERT INTO `tb_studentregistration` VALUES ('201610182146734355308', '3', '2016-10-18 21:46:07', '5', '1', '1', '0', '22', '9', '');
INSERT INTO `tb_studentregistration` VALUES ('201610182147528199322', '4', '2016-10-18 21:47:05', '5', '1', '1', '0', '9', '9', '');
INSERT INTO `tb_studentregistration` VALUES ('201610182148026563856', '102', '2016-10-18 21:48:00', '5', '1', '1', '0', '6', '9', '');
INSERT INTO `tb_studentregistration` VALUES ('201610182151340680009', '117', '2016-10-18 21:51:03', '5', '3', '1', '1', '0', '12', '');
INSERT INTO `tb_studentregistration` VALUES ('201610182153451518694', '118', '2016-10-18 21:53:04', '5', '3', '1', '0', '8', '9', '');
INSERT INTO `tb_studentregistration` VALUES ('201610182154395359390', '48', '2016-10-18 21:05:43', '3', '3', '0', '0', '0', '11', '');
INSERT INTO `tb_studentregistration` VALUES ('201610182157865676523', '118', '2016-10-18 21:57:08', '4', '3', '1', '0', '9', '9', '');
INSERT INTO `tb_studentregistration` VALUES ('20161018216459310615', '48', '2016-10-18 21:06:45', '3', '3', '1', '0', '5', '11', '');
INSERT INTO `tb_studentregistration` VALUES ('201610182194057879953', '28', '2016-10-18 21:09:40', '3', '3', '1', '0', '8', '12', '');
INSERT INTO `tb_studentregistration` VALUES ('201610182216740621589', '119', '2016-10-18 22:16:07', '1', '3', '1', '0', '9', '5', '');
INSERT INTO `tb_studentregistration` VALUES ('2016101822183448447503', '120', '2016-10-18 22:18:34', '1', '3', '1', '0', '9', '6', '');
INSERT INTO `tb_studentregistration` VALUES ('201610182221589394896', '33', '2016-10-18 22:21:58', '2', '3', '1', '0', '8', '5', '');
INSERT INTO `tb_studentregistration` VALUES ('2016101822242525039322', '29', '2016-10-18 22:24:25', '1', '3', '1', '0', '6', '3', '');
INSERT INTO `tb_studentregistration` VALUES ('201610182229295324204', '122', '2016-10-18 22:29:02', '2', '3', '1', '2', '8', '1', '700包拼音');
INSERT INTO `tb_studentregistration` VALUES ('2016101910111573428515', '56', '2016-10-19 10:11:15', '1', '3', '1', '0', '11', '8', '');
INSERT INTO `tb_studentregistration` VALUES ('201610191015494681327', '5', '2016-10-19 10:15:49', '1', '1', '1', '6', '0', '9', '');
INSERT INTO `tb_studentregistration` VALUES ('201610191018183115596', '60', '2016-10-19 10:18:18', '1', '1', '1', '4', '8', '9', '');
INSERT INTO `tb_studentregistration` VALUES ('2016101910183770354099', '4', '2016-10-19 10:18:37', '1', '1', '1', '4', '6', '9', '');
INSERT INTO `tb_studentregistration` VALUES ('201610191020186230536', '66', '2016-10-19 10:20:18', '1', '1', '1', '4', '0', '9', '');
INSERT INTO `tb_studentregistration` VALUES ('2016101910222015656144', '51', '2016-10-19 10:22:20', '1', '1', '1', '4', '29', '9', '');
INSERT INTO `tb_studentregistration` VALUES ('201610191024166298030', '70', '2016-10-19 10:24:16', '1', '1', '1', '3', '0', '9', '');
INSERT INTO `tb_studentregistration` VALUES ('2016101910255390685388', '67', '2016-10-19 10:25:53', '1', '1', '1', '2', '0', '9', '');
INSERT INTO `tb_studentregistration` VALUES ('2016101910264332872031', '68', '2016-10-19 10:26:43', '1', '1', '1', '2', '32', '9', '');
INSERT INTO `tb_studentregistration` VALUES ('2016101910275123429592', '93', '2016-10-19 10:27:51', '1', '1', '1', '2', '8', '9', '');
INSERT INTO `tb_studentregistration` VALUES ('2016101910295032881522', '69', '2016-10-19 10:29:50', '1', '1', '1', '1', '0', '9', '');
INSERT INTO `tb_studentregistration` VALUES ('201610191042126556567', '52', '2016-10-19 10:04:21', '1', '3', '1', '4', '0', '9', '');
INSERT INTO `tb_studentregistration` VALUES ('20161019105524091609', '65', '2016-10-19 10:55:24', '1', '1', '1', '0', '0', '9', '');
INSERT INTO `tb_studentregistration` VALUES ('201610191058182812811', '42', '2016-10-19 10:58:01', '1', '1', '1', '2', '0', '9', '');
INSERT INTO `tb_studentregistration` VALUES ('201610191058337811690', '15', '2016-10-19 10:58:33', '1', '1', '1', '2', '8', '9', '');
INSERT INTO `tb_studentregistration` VALUES ('2016101910592931293151', '71', '2016-10-19 10:59:29', '1', '1', '1', '1', '8', '9', '');
INSERT INTO `tb_studentregistration` VALUES ('2016101911114398416820', '91', '2016-10-19 11:11:43', '1', '4', '1', '1', '0', '9', '');
INSERT INTO `tb_studentregistration` VALUES ('201610191112196270305', '92', '2016-10-19 11:12:19', '1', '4', '1', '1', '0', '9', '');
INSERT INTO `tb_studentregistration` VALUES ('201610191132171849955', '102', '2016-10-19 11:03:21', '1', '1', '1', '1', '8', '9', '');
INSERT INTO `tb_studentregistration` VALUES ('201610191141159373752', '106', '2016-10-19 11:04:11', '1', '1', '1', '1', '8', '9', '');
INSERT INTO `tb_studentregistration` VALUES ('201610191151545360729', '104', '2016-10-19 11:05:15', '1', '1', '1', '1', '8', '9', '');
INSERT INTO `tb_studentregistration` VALUES ('201610191174371886196', '34', '2016-10-19 11:07:43', '1', '1', '1', '1', '0', '9', '');

-- ----------------------------
-- Table structure for `tb_subject`
-- ----------------------------
DROP TABLE IF EXISTS `tb_subject`;
CREATE TABLE `tb_subject` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_subject
-- ----------------------------
INSERT INTO `tb_subject` VALUES ('1', '数学');
INSERT INTO `tb_subject` VALUES ('2', '语文');
INSERT INTO `tb_subject` VALUES ('3', '英语');
INSERT INTO `tb_subject` VALUES ('4', '物理');
INSERT INTO `tb_subject` VALUES ('5', '化学');
INSERT INTO `tb_subject` VALUES ('6', '生物');
INSERT INTO `tb_subject` VALUES ('7', '地理');
INSERT INTO `tb_subject` VALUES ('8', '日语');
INSERT INTO `tb_subject` VALUES ('9', '政治');
INSERT INTO `tb_subject` VALUES ('10', '历史');

-- ----------------------------
-- Table structure for `tb_teacher`
-- ----------------------------
DROP TABLE IF EXISTS `tb_teacher`;
CREATE TABLE `tb_teacher` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `employeeId` int(11) DEFAULT NULL,
  `subjectId` int(11) DEFAULT NULL,
  `gradeId` int(11) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `remark` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `tc_employeeId` (`employeeId`),
  KEY `tc_subjectId` (`subjectId`),
  KEY `tc_gradeId` (`gradeId`),
  CONSTRAINT `tc_employeeId` FOREIGN KEY (`employeeId`) REFERENCES `tb_employee` (`id`),
  CONSTRAINT `tc_gradeId` FOREIGN KEY (`gradeId`) REFERENCES `tb_grade` (`id`),
  CONSTRAINT `tc_subjectId` FOREIGN KEY (`subjectId`) REFERENCES `tb_subject` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_teacher
-- ----------------------------
INSERT INTO `tb_teacher` VALUES ('1', '1', '1', '9', '2016-09-20 18:08:29', null);
INSERT INTO `tb_teacher` VALUES ('2', '2', '4', '9', '2016-09-20 18:08:52', null);

-- ----------------------------
-- Table structure for `tb_timeslot`
-- ----------------------------
DROP TABLE IF EXISTS `tb_timeslot`;
CREATE TABLE `tb_timeslot` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT '8:00-10:00',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_timeslot
-- ----------------------------
INSERT INTO `tb_timeslot` VALUES ('1', '8:00-10:00');
INSERT INTO `tb_timeslot` VALUES ('2', '10:00-12:00');
INSERT INTO `tb_timeslot` VALUES ('3', '12:00-14:00');
INSERT INTO `tb_timeslot` VALUES ('4', '14:00-16:00');
INSERT INTO `tb_timeslot` VALUES ('5', '16:00-18:00');
INSERT INTO `tb_timeslot` VALUES ('6', '18:00-20:00');
INSERT INTO `tb_timeslot` VALUES ('7', '20:00-22:00');

-- ----------------------------
-- View structure for `tv_pay_detail`
-- ----------------------------
DROP VIEW IF EXISTS `tv_pay_detail`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `tv_pay_detail` AS select `a`.`id` AS `id`,`a`.`regorderId` AS `regorderId`,`a`.`payId` AS `payId`,`a`.`total` AS `total`,`a`.`createTime` AS `createTime`,`a`.`remark` AS `remark`,`i`.`name` AS `operator`,`g`.`name` AS `classtype`,`f`.`name` AS `subject`,`e`.`name` AS `grade`,`d`.`name` AS `student`,`h`.`model` AS `model`,`h`.`payerTel` AS `payerTel`,`h`.`payer` AS `payer` from ((((((((`tb_regorderdetail` `a` left join `tb_regorder` `b` on((`a`.`regorderId` = `b`.`id`))) left join `tb_studentregistration` `c` on((`b`.`studentRegId` = `c`.`id`))) left join `tb_student` `d` on((`c`.`studentId` = `d`.`id`))) left join `tb_grade` `e` on((`c`.`gradeId` = `e`.`id`))) left join `tb_subject` `f` on((`c`.`subjectId` = `f`.`id`))) left join `tb_classtype` `g` on((`c`.`classTypeId` = `g`.`id`))) left join `tb_studentpay` `h` on((`a`.`payId` = `h`.`id`))) left join `tb_employee` `i` on((`h`.`operator` = `i`.`id`))) ;

-- ----------------------------
-- View structure for `tv_students_number`
-- ----------------------------
DROP VIEW IF EXISTS `tv_students_number`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `tv_students_number` AS select year(`a`.`gotime`) AS `y`,month(`a`.`gotime`) AS `m`,count(0) AS `val`,`b`.`studentId` AS `studentId` from (`tb_classrcord` `a` left join `tb_studentgoclass` `b` on((`a`.`id` = `b`.`classRcordId`))) group by year(`a`.`gotime`),month(`a`.`gotime`),`b`.`studentId` ;
DROP TRIGGER IF EXISTS `add_vaild_period`;
DELIMITER ;;
CREATE TRIGGER `add_vaild_period` AFTER INSERT ON `tb_regorderdetail` FOR EACH ROW BEGIN

set @new_regorderId=new.regorderId;
set @studentRegId=(SELECT studentRegId FROM tb_regorder where id=@new_regorderId);

set @total=new.total;
set @old_actualCost=(select  actualCost from tb_regorder where id=@new_regorderId );
set @tol=(@total+@old_actualCost);
UPDATE tb_regorder  set actualCost= @tol where id=@new_regorderId;

set @vaild_period=(select SUM(floor((actualCost*regNumber)/mustCost))  from tb_regorder where studentRegId=@studentRegId );
UPDATE tb_studentregistration set youxiaoNumber=@vaild_period where id=@studentRegId;


END
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `update_vaild_period`;
DELIMITER ;;
CREATE TRIGGER `update_vaild_period` AFTER UPDATE ON `tb_regorderdetail` FOR EACH ROW BEGIN

set @new_regorderId=old.regorderId;
set @studentRegId=(SELECT studentRegId FROM tb_regorder where id=@new_regorderId);

set @total=new.total;
set @old_total=old.total;
set @old_actualCost=(select  actualCost from tb_regorder where id=@new_regorderId );
set @tol=(@old_actualCost-@old_total+@total);
UPDATE tb_regorder  set actualCost= @tol where id=@new_regorderId;

set @vaild_period=(select SUM(floor((actualCost*regNumber)/mustCost))  from tb_regorder where studentRegId=@studentRegId );
UPDATE tb_studentregistration set youxiaoNumber=@vaild_period where id=@studentRegId;


END
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `add_goclass_number`;
DELIMITER ;;
CREATE TRIGGER `add_goclass_number` AFTER INSERT ON `tb_studentgoclass` FOR EACH ROW BEGIN

set @studentId=new.studentId;
set @avild=new.availd;
set @rcId=new.classRcordId;
if @avild=1 then
set @classInfoId=(select classInfoId from tb_classRcord where id=@rcId);
set @studentRegId=(select studentRegId from tb_studentclass where state=0 and studentId=@studentId and classinfoId=@classInfoId);
set @finishNumber=(SELECT finishNumber FROM tb_studentregistration where id=@studentRegId );
set @totalNumber=@finishNumber+1;
UPDATE tb_studentregistration set finishNumber=@totalNumber where id=@studentRegId;
end if;

END
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `up_goclass_number`;
DELIMITER ;;
CREATE TRIGGER `up_goclass_number` AFTER UPDATE ON `tb_studentgoclass` FOR EACH ROW BEGIN

set @studentId=old.studentId;
set @rcId=old.classRcordId;
set @avild=old.availd;
set @new_avild=new.availd;
if @avild=1 then
if @new_avild=0 then
set @classInfoId=(select classInfoId from tb_classRcord where id=@rcId);
set @studentRegId=(select studentRegId from tb_studentclass where state=0 and studentId=@studentId and classinfoId=@classInfoId);
set @finishNumber=(SELECT finishNumber FROM tb_studentregistration where id=@studentRegId );
set @totalNumber=@finishNumber-1;
UPDATE tb_studentregistration set finishNumber=@totalNumber where id=@studentRegId;
end if;
end if;

if @avild=0 then
if @new_avild=1 then
set @classInfoId=(select classInfoId from tb_classRcord where id=@rcId);
set @studentRegId=(select studentRegId from tb_studentclass where state=0 and studentId=@studentId and classinfoId=@classInfoId);
set @finishNumber=(SELECT finishNumber FROM tb_studentregistration where id=@studentRegId );
set @totalNumber=@finishNumber+1;
UPDATE tb_studentregistration set finishNumber=@totalNumber where id=@studentRegId;
end if;
end if;
END
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `del_goclass_number`;
DELIMITER ;;
CREATE TRIGGER `del_goclass_number` AFTER DELETE ON `tb_studentgoclass` FOR EACH ROW BEGIN

set @studentId=old.studentId;
set @rcId=old.classRcordId;
set @avild=old.availd;
if @avild=1 then
set @classInfoId=(select classInfoId from tb_classRcord where id=@rcId);
set @studentRegId=(select studentRegId from tb_studentclass where state=0 and studentId=@studentId and classinfoId=@classInfoId);
set @finishNumber=(SELECT finishNumber FROM tb_studentregistration where id=@studentRegId );
set @totalNumber=@finishNumber-1;
UPDATE tb_studentregistration set finishNumber=@totalNumber where id=@studentRegId;
end if;
END
;;
DELIMITER ;
