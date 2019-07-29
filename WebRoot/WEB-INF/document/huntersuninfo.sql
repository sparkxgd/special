/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50515
Source Host           : localhost:3306
Source Database       : huntersuninfo

Target Server Type    : MYSQL
Target Server Version : 50515
File Encoding         : 65001

Date: 2016-01-14 10:01:51
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
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_admin_x
-- ----------------------------
INSERT INTO `tb_admin_x` VALUES ('9', 'zhixingbus', 'e10adc3949ba59abbe56e057f20f883e', '1');
INSERT INTO `tb_admin_x` VALUES ('11', 'admin', 'e10adc3949ba59abbe56e057f20f883e', '1');
INSERT INTO `tb_admin_x` VALUES ('12', 'xiao', 'c4ca4238a0b923820dcc509a6f75849b', '0');

-- ----------------------------
-- Table structure for `tb_article_x`
-- ----------------------------
DROP TABLE IF EXISTS `tb_article_x`;
CREATE TABLE `tb_article_x` (
  `id` varchar(60) NOT NULL,
  `title` varchar(100) DEFAULT NULL COMMENT '文章标题',
  `summary` text COMMENT '摘要',
  `content` text COMMENT '内容',
  `author` varchar(100) DEFAULT NULL COMMENT '作者（ip和地区名）',
  `createTime` datetime DEFAULT NULL COMMENT '编写时间',
  `commentsNun` int(11) DEFAULT NULL COMMENT '评论数',
  `readNum` int(11) DEFAULT NULL COMMENT '阅读数',
  `type` tinyint(4) DEFAULT NULL COMMENT '文章类型(1：公交资讯，2：智行公交更新日志)',
  `level` tinyint(4) DEFAULT NULL COMMENT '级别，数字越大，级别越高',
  `publishTime` datetime DEFAULT NULL COMMENT '发表时间',
  `state` tinyint(4) DEFAULT NULL COMMENT '状态0：未发表。1：已发表。2：已经下架',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `isComment` tinyint(4) DEFAULT '0' COMMENT '是否可以评论，0：不可以，1：可以',
  `headImg` varchar(255) DEFAULT NULL COMMENT '图片',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_article_x
-- ----------------------------
INSERT INTO `tb_article_x` VALUES ('298aa3a8751148e68859ee04d7400b3c', '公司简介', '贵州中科汉天下信息技术有限公司是中科汉天下集团的子公司（以下简称公司），于2014年9月成立于贵阳高新区，公司是一家由来自国内顶级学府清华大学、北京大学、中科院、美国海归等具有丰富互联网从事经验的团队组建。公司在数据获取与数据分析方面具有深厚的技术积累与敏锐的市场理解，初期将积极开拓贵州地方市场，成为区域性的大数据应用产品的行业龙头，并逐步发展，力争成为全国数据处理分析领域的领先企业，共享大数据快速发展的行业盛宴', '				<p style=\"margin: 0px; padding: 0px; color: rgb(102, 102, 102); font-family: Simsun; font-size: 12px; font-style: normal; font-variant: normal; font-weight: normal; letter-spacing: normal; line-height: normal; orphans: auto; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: auto; word-spacing: 0px; -webkit-text-stroke-width: 0px; background-color: rgb(255, 255, 255);\"><span style=\"font-size: 15px; font-family: 宋体, SimSun; line-height: 24px;\"><span style=\"font-family:宋体, SimSun;font-size:18px;line-height: 24px;\"><span style=\"font-family:宋体, SimSun;font-size:18px;color:#000000;line-height: 24px;\"><span style=\"font-family:微软雅黑, \'Microsoft YaHei\';font-size:16px;color:#000000;line-height: 24px;\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 贵州中科汉天下信息技术有限公司是中科汉天下集团的子公司（以下简称公司），于2014年9月成立于贵阳高新区，公司是一家由来自国内顶级学府清华大学、北京大学、中科院、美国海归等具有丰富互联网从事经验的团队组建。公司在数据获取与数据分析方面具有深厚的技术积累与敏锐的市场理解，初期将积极开拓贵州地方市场，成为区域性的大数据应用产品的行业龙头，并逐步发展，力争成为全国数据处理分析领域的领先企业，共享大数据快速发展的行业盛宴。</span></span></span></span></p><p style=\"margin: 0px; padding: 0px; color: rgb(102, 102, 102); font-family: Simsun; font-size: 12px; font-style: normal; font-variant: normal; font-weight: normal; letter-spacing: normal; line-height: normal; orphans: auto; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: auto; word-spacing: 0px; -webkit-text-stroke-width: 0px; background-color: rgb(255, 255, 255);\"><span style=\"font-family:宋体, SimSun;font-size:18px;color:#000000;line-height: 24px;\"></span></p><p style=\"margin: 0px; padding: 0px; color: rgb(102, 102, 102); font-family: Simsun; font-size: 12px; font-style: normal; font-variant: normal; font-weight: normal; letter-spacing: normal; line-height: normal; orphans: auto; text-indent: 0px; text-transform: none; white-space: normal; widows: auto; word-spacing: 0px; -webkit-text-stroke-width: 0px; background-color: rgb(255, 255, 255); text-align: center;\"><img src=\"http://www.huntersun-info.com/ueditor/jsp/upload/image/20150922/1442923871589063731.png\" style=\"margin-right: auto; margin-left: auto; border: none; height: 232px; width: 343px;\" alt=\"\" height=\"232\" width=\"343\" /></p><p style=\"margin: 0px; padding: 0px; color: rgb(102, 102, 102); font-family: Simsun; font-size: 12px; font-style: normal; font-variant: normal; font-weight: normal; letter-spacing: normal; line-height: normal; orphans: auto; text-indent: 0px; text-transform: none; white-space: normal; widows: auto; word-spacing: 0px; -webkit-text-stroke-width: 0px; background-color: rgb(255, 255, 255); text-align: center;\"><span style=\"font-family:宋体, SimSun;font-size:12px;color:#000000;line-height: 30px;\">贵州中科汉天下电子有限公司各分公司分布情况</span></p><p style=\"margin: 0px; padding: 0px; color: rgb(102, 102, 102); font-family: Simsun; font-size: 12px; font-style: normal; font-variant: normal; font-weight: normal; letter-spacing: normal; line-height: normal; orphans: auto; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: auto; word-spacing: 0px; -webkit-text-stroke-width: 0px; background-color: rgb(255, 255, 255);\"><span style=\"font-family:宋体, SimSun;font-size:18px;color:#000000;line-height: 24px;\">&nbsp;&nbsp;&nbsp;</span></p><p style=\"margin: 0px; padding: 0px; color: rgb(102, 102, 102); font-family: Simsun; font-size: 12px; font-style: normal; font-variant: normal; font-weight: normal; letter-spacing: normal; line-height: normal; orphans: auto; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: auto; word-spacing: 0px; -webkit-text-stroke-width: 0px; background-color: rgb(255, 255, 255);\"><span style=\"font-family:宋体, SimSun;font-size:18px;color:#000000;\">&nbsp;&nbsp;&nbsp;<span>&nbsp;</span><span style=\"font-family:微软雅黑, \'Microsoft YaHei\';font-size:16px;color:#000000;\">拥有北京、上海、贵州三个研发中心，上海和深圳两个销售与技术支持中心，以及香港商务和销售子公司。目前在香港设立销售子公司，在上海设立集研发、销售和技术支持为一体的分公司、深圳分公司负责市场、营销及技术支持，贵州中科汉天下的负责公司的运营、生产、销售、仓储、财务等相关工作，并按照贵州凯里物联网科教产业园“一园六区”规划实施园区工程建设。</span></span></p><p style=\"margin: 0px; padding: 0px; color: rgb(102, 102, 102); font-family: Simsun; font-size: 12px; font-style: normal; font-variant: normal; font-weight: normal; letter-spacing: normal; line-height: normal; orphans: auto; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: auto; word-spacing: 0px; -webkit-text-stroke-width: 0px; background-color: rgb(255, 255, 255);\"><span style=\"font-family:微软雅黑, \'Microsoft YaHei\';font-size:16px;color:#000000;\"><br /></span></p><p style=\"margin: 0px; padding: 0px; color: rgb(102, 102, 102); font-family: Simsun; font-size: 12px; font-style: normal; font-variant: normal; font-weight: normal; letter-spacing: normal; line-height: normal; orphans: auto; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: auto; word-spacing: 0px; -webkit-text-stroke-width: 0px; background-color: rgb(255, 255, 255);\"><span style=\"font-family:微软雅黑, \'Microsoft YaHei\';font-size:16px;\"><span style=\"font-family:微软雅黑, \'Microsoft YaHei\';color:#000000;\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 中科汉天下秉承“臻善吾芯，心济天下”的宗旨，“积极务实、开拓创新”的理念，以及“开放、共享”的精神，努力实现与合作伙伴互惠双赢，共同发展，力争四年内打造贵州省第一家半导体/集成电路高科技上市企业，五年内打造销售额5亿美元以上的高科技公司。</span></span></p>', 'xiao', '2016-01-13 16:08:36', '0', '31', '6', '0', '2016-01-13 16:19:27', '1', 'xiao发布|', '0', '/resources/images/www/news/298aa3a8751148e68859ee04d7400b3c/thumbnail/298aa3a8751148e68859ee04d7400b3c_1442923871589063731.png');
INSERT INTO `tb_article_x` VALUES ('7ae6b44e8d994df1ad05aa4c51d3797f', '企业文化', '企业文化', '<span style=\"font-size:18px;\"><span style=\"font-family:微软雅黑,Microsoft YaHei;font-size:16px;color:#000000;\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 智行公交是一款服务于全国的智慧公交手机APP&nbsp;，用户可以在手机电子地图上对实时公交进行动态定位、对公交车内的拥挤情况进行查询，用手机支付的方式乘坐公交，并提供基于公交特性的移动互联网社交、基于移动互联网的免费公交、本地化网上商城及智慧城市微应用等领域提供智慧化的服务，最终达到免费公交和智慧城市的目的。</span></span><p><span style=\"font-family:微软雅黑,Microsoft YaHei;font-size:16px;color:#000000;\">&nbsp;&nbsp;&nbsp; <br /></span></p><p><span style=\"font-family:微软雅黑,Microsoft YaHei;font-size:16px;color:#000000;\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 我们坚信中国进入智慧公交甚至免费公交的时代即将来临。我希望更多的和我们一样坚信智慧公交甚至免费公交必将实现的朋友们加入我们团队，在技术上、在商务上、在资本上给予我们支持和帮助，让我们一起来实现这个足以惊叹甚至颠覆多少年来一成不变的城市公共交通运行服务模式。<br />&nbsp;&nbsp;&nbsp; <br /></span></p><p><span style=\"font-family:微软雅黑,Microsoft YaHei;font-size:16px;color:#000000;\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 我们通过建立一个可以让用户在短时间内创造价值的平台，让用户在轻松娱乐的工程中，挣到自己的公交车票。主要是通过我们的数据分享引擎平台来实现。现在用户在微信微博QQ空间等网络平台上分享自己的想法心得文章等信息是没有收益的。是用户创造了内容，创造了整个网络世界，但是却没有任何的收益，这不合理也这不公平。我们必须承认的一个事实是，历史是人民创造的，互联网的世界是用户创造的，那么网络世界的创造者理所当然的要成为网络世界的主人，有权分享网络世界的收益。即，用户发布、分享、转发信息等等行为，带动了网络流量的增长，让流量变现成为了可能，所以用户必须要获得劳动报酬分享收益，而不是提供网络平台的运营方独占收益。我们的数据分享引擎平台就是要让用户的数据分享行为给用户带来收益。相对便宜的公交出行费用和数据分享引擎平台的收益分享让免费公交的实现成为了可能。</span></p><p><span style=\"font-family:微软雅黑,Microsoft YaHei;font-size:16px;color:#000000;\">&nbsp;&nbsp;&nbsp; <br /></span></p><p><span style=\"font-family:微软雅黑,Microsoft YaHei;font-size:16px;color:#000000;\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 我们团队核心成员有多年的互联网和移动互联网等从业经验，团队核心成员曾在百度、IBM、新浪、清华同方、乐视网等行业重点企业担任过高级管理人员和高级研发主管。</span></p><p><span style=\"font-family:微软雅黑,Microsoft YaHei;font-size:16px;color:#000000;\">&nbsp;&nbsp;&nbsp; <br /></span></p><p><span style=\"font-family:微软雅黑,Microsoft YaHei;font-size:16px;color:#000000;\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 创业不仅仅是为了改变自己，更是为了改变这个时代。类似这样的理念同样也是无数的创业者人内心深处的人生使命和愿景。我们知道改变甚至巅峰现有的城市公共交通运行模式是一个艰巨的过程，免费公交也不是一蹴而就的事情，而是一个长期探索和实践的过程，但我们坚信任何未来必定实现的事情，总会在探索者的勇敢尝试中实现。自古远山路难行，事事难成事事成。我们愿意作为第一批探索免费公交的勇士，去创造一个全新城市公共交通时代，创新改变城市，尝试凝聚成功，我们必定会创造出这样一个全新的时代。</span></p>&nbsp;				', 'admin', '2016-01-13 16:18:59', '0', '13', '10', '0', '2016-01-13 16:19:11', '1', 'admin发布|', '0', '/resources/images/www/news/7ae6b44e8d994df1ad05aa4c51d3797f/thumbnail/7ae6b44e8d994df1ad05aa4c51d3797f_u=3254622067,1823939326&fm=21&gp=0.jpg');
INSERT INTO `tb_article_x` VALUES ('b7f4e59a817140558dd14eb951f1aa10', '联系我们', '联系我们', '								<div class=\"contact-info\" align=\"center\"><span style=\"font-size:24px;padding：10px;\">贵州中科汉天下信息技术有限公司<br /><br /></span></div><div align=\"center\"><span style=\"font-size:24px;padding：10px;\">        </span></div><div class=\"contact-info\" align=\"center\"><span style=\"font-size:24px;padding：10px;\">贵州省.贵阳市.高新区<br /><br /></span></div><div align=\"center\"><span style=\"font-size:24px;padding：10px;\">        </span></div><div class=\"contact-info\" align=\"center\"><span style=\"font-size:24px;padding：10px;\">Tell: 0851-84393936<br /><br /></span></div><div align=\"center\"><span style=\"font-size:24px;padding：10px;\">        </span></div><div class=\"contact-info\" align=\"center\"><span style=\"font-size:24px;padding：10px;\">Email: products@zhixingbus.com</span></div>', 'admin', '2016-01-13 17:19:03', '0', '31', '13', '0', '2016-01-13 17:56:05', '1', 'admin发布|', '0', '/resources/images/www/news/b7f4e59a817140558dd14eb951f1aa10/thumbnail/b7f4e59a817140558dd14eb951f1aa10_contact_phone.png');

-- ----------------------------
-- Table structure for `tb_comments`
-- ----------------------------
DROP TABLE IF EXISTS `tb_comments`;
CREATE TABLE `tb_comments` (
  `id` varchar(60) NOT NULL,
  `articleId` varchar(60) DEFAULT NULL COMMENT '摘要',
  `content` text COMMENT '内容',
  `cityName` varchar(50) DEFAULT NULL,
  `ip` varchar(50) DEFAULT NULL COMMENT '作者（ip和地区名）',
  `createTime` datetime DEFAULT NULL COMMENT '编写时间',
  `agreeNum` int(11) DEFAULT NULL COMMENT '赞同数',
  `disapprovalNum` int(11) DEFAULT NULL COMMENT '不赞同',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_comments
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_config`
-- ----------------------------
DROP TABLE IF EXISTS `tb_config`;
CREATE TABLE `tb_config` (
  `id` varchar(255) NOT NULL DEFAULT '',
  `key` varchar(255) DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `remark` varchar(255) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_config
-- ----------------------------
INSERT INTO `tb_config` VALUES ('1', 'tuwen', '298aa3a8751148e68859ee04d7400b3c', '图文展示', '展示id为的文章', '2016-01-13 16:17:57');
INSERT INTO `tb_config` VALUES ('10', 'wrapper', 'ae2a263d98a643d69844b577b8f88201', '图片轮换展示', 'id为文章id', '2016-01-13 10:36:58');
INSERT INTO `tb_config` VALUES ('11', 'wrapper', '714cadc6a1e14b7d9de9b94bb6d5f3c3', '图片轮换展示', 'id为文章id', '2016-01-13 10:36:56');
INSERT INTO `tb_config` VALUES ('12', 'wrapper', '6e0ef6e0a3214f3caa7a04246747f089', '图片轮换展示', 'id为文章id', '2016-01-13 10:36:54');
INSERT INTO `tb_config` VALUES ('2', 'list', '7', '列表展示', '类型id为5的文章', '2016-01-13 10:31:50');
INSERT INTO `tb_config` VALUES ('3', 'wrapper', '0affb13def974090ad5049be0aad77fb', '图片轮换展示', 'id为文章id', '2016-01-13 10:33:00');
INSERT INTO `tb_config` VALUES ('4', 'wrapper', '6481e56c44974942aa0baf7d931dcd2e', '图片轮换展示', 'id为文章id', '2016-01-13 10:37:13');
INSERT INTO `tb_config` VALUES ('5', 'wrapper', 'c985e2eb116b4f0aa0590d87f691b2ca', '图片轮换展示', 'id为文章id', '2016-01-13 10:37:11');
INSERT INTO `tb_config` VALUES ('6', 'wrapper', 'c19904b82c5248428761f59ad308defa', '图片轮换展示', 'id为文章id', '2016-01-13 10:37:08');
INSERT INTO `tb_config` VALUES ('7', 'wrapper', '7fbfdcbb06144f11baaca4193f0baa84', '图片轮换展示', 'id为文章id', '2016-01-13 10:37:05');
INSERT INTO `tb_config` VALUES ('8', 'wrapper', '8087642394e74a2dbd684eaeb0fc8e64', '图片轮换展示', 'id为文章id', '2016-01-13 10:37:03');
INSERT INTO `tb_config` VALUES ('9', 'wrapper', 'c985e2eb116b4f0aa0590d87f691b2ca', '图片轮换展示', 'id为文章id', '2016-01-13 10:37:01');

-- ----------------------------
-- Table structure for `tb_cooperation`
-- ----------------------------
DROP TABLE IF EXISTS `tb_cooperation`;
CREATE TABLE `tb_cooperation` (
  `id` varchar(255) NOT NULL DEFAULT '',
  `name` varchar(255) DEFAULT NULL,
  `website` varchar(255) DEFAULT NULL COMMENT '网站',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_cooperation
-- ----------------------------
INSERT INTO `tb_cooperation` VALUES ('0d9be9520de046b699ceeb1e63467bcc', '智行公交5', 'http://www.zhixingbus.com');
INSERT INTO `tb_cooperation` VALUES ('352d91b2936c49078cfe5949e4bf2eb5', '智行公交3', 'http://www.zhixingbus.com');
INSERT INTO `tb_cooperation` VALUES ('8bd3f881ea014b96b3edddf18af3b310', '智行公交2', 'http://www.zhixingbus.com');
INSERT INTO `tb_cooperation` VALUES ('a8c40107c0294760ad9bd55260777572', '智行公交', 'http://www.zhixingbus.com');
INSERT INTO `tb_cooperation` VALUES ('d38c809614a94db38c17fbb6c8c40f15', '智行公交4', 'http://www.zhixingbus.com');

-- ----------------------------
-- Table structure for `tb_menu`
-- ----------------------------
DROP TABLE IF EXISTS `tb_menu`;
CREATE TABLE `tb_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `treeId` int(11) DEFAULT NULL COMMENT '子菜单id',
  `name` varchar(100) DEFAULT NULL COMMENT '菜单名称',
  `page` varchar(255) DEFAULT NULL COMMENT '跳转页面或者事件',
  `position` tinyint(4) DEFAULT NULL COMMENT '显示位置',
  `enName` varchar(100) DEFAULT NULL COMMENT '英文名称',
  `isShow` tinyint(4) DEFAULT NULL COMMENT '是否显示，0：显示，1，不显示',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_menu
-- ----------------------------
INSERT INTO `tb_menu` VALUES ('4', '-1', '首页', '/', '1', 'index', '0');
INSERT INTO `tb_menu` VALUES ('5', '-1', '关于我们', '/detail', '2', 'about', '0');
INSERT INTO `tb_menu` VALUES ('6', '5', '公司简介', '/detail', '1', 'jianjie', '0');
INSERT INTO `tb_menu` VALUES ('7', '-1', '新闻资讯', '/detailList', '3', 'news', '0');
INSERT INTO `tb_menu` VALUES ('8', '7', '行业新闻', '/detailList', '1', 'hangye', '0');
INSERT INTO `tb_menu` VALUES ('9', '7', '公司快讯', '/detailList', '1', 'kuai', '0');
INSERT INTO `tb_menu` VALUES ('10', '5', '企业文化', '/detail', '1', 'culture', '0');
INSERT INTO `tb_menu` VALUES ('11', '-1', '产品中心', '/detail', '4', 'GROUP INDUSTRY', '0');
INSERT INTO `tb_menu` VALUES ('12', '-1', '加入我们', '/detail', '5', 'RECRUITMENT', '0');
INSERT INTO `tb_menu` VALUES ('13', '-1', '联系我们', '/detail', '6', 'CONTACT US', '0');

-- ----------------------------
-- Table structure for `tb_suggest`
-- ----------------------------
DROP TABLE IF EXISTS `tb_suggest`;
CREATE TABLE `tb_suggest` (
  `id` varchar(60) NOT NULL,
  `name` varchar(60) DEFAULT NULL COMMENT '名字',
  `email` varchar(60) DEFAULT NULL COMMENT '邮箱',
  `title` varchar(100) DEFAULT NULL COMMENT '标题',
  `content` text COMMENT '内容',
  `ip` varchar(50) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL COMMENT '地区',
  `createTime` datetime DEFAULT NULL COMMENT '时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_suggest
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_visitor`
-- ----------------------------
DROP TABLE IF EXISTS `tb_visitor`;
CREATE TABLE `tb_visitor` (
  `id` varchar(100) NOT NULL DEFAULT '',
  `remoteIp` varchar(50) DEFAULT NULL COMMENT '外网ip',
  `ip` varchar(50) DEFAULT NULL COMMENT '内网ip',
  `addr` varchar(100) DEFAULT NULL COMMENT '地区',
  `browser` text COMMENT '浏览器信息',
  `visitTime` datetime DEFAULT NULL COMMENT '访问时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_visitor
-- ----------------------------
INSERT INTO `tb_visitor` VALUES ('08fd38e585024a5dbe9c8cdb3e73481a', '222.85.139.103', '192.168.1.47', '贵州省贵阳市', 'Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.3; WOW64; Trident/7.0; .NET4.0E; .NET4.0C; .NET CLR 3.5.30729; .NET CLR 2.0.50727; .NET CLR 3.0.30729; Tablet PC 2.0; InfoPath.3)', '2016-01-14 09:44:07');
INSERT INTO `tb_visitor` VALUES ('130eaa2fbd754078819c9c1e0e0d9f47', '222.85.139.103', '192.168.1.97', '贵州省贵阳市', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36 Edge/12.10240', '2016-01-13 16:43:58');
INSERT INTO `tb_visitor` VALUES ('14cc651c91a340fbb21c2a0312b2c342', '222.85.139.103', '192.168.1.17', '贵州省贵阳市', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:43.0) Gecko/20100101 Firefox/43.0', '2016-01-13 16:19:46');
INSERT INTO `tb_visitor` VALUES ('16888880bfc04cb083991859e5ac95fc', '222.85.139.103', '192.168.1.97', '贵州省贵阳市', 'Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 10.0; WOW64; Trident/8.0; .NET4.0C; .NET4.0E; .NET CLR 2.0.50727; .NET CLR 3.0.30729; .NET CLR 3.5.30729; InfoPath.3; MALCJS)', '2016-01-13 17:07:17');
INSERT INTO `tb_visitor` VALUES ('1a7e647976d3413ca42f0fd0f4d9f895', '222.85.139.103', '192.168.1.26', '贵州省贵阳市', 'Mozilla/5.0 (Windows NT 10.0; WOW64; rv:43.0) Gecko/20100101 Firefox/43.0', '2016-01-13 16:10:16');
INSERT INTO `tb_visitor` VALUES ('1a9a7ad3168e42e7a5b6a8dc963977af', '222.85.139.103', '0:0:0:0:0:0:0:1', '贵州省贵阳市', 'Mozilla/5.0 (Windows NT 10.0; WOW64; rv:43.0) Gecko/20100101 Firefox/43.0', '2016-01-13 17:42:47');
INSERT INTO `tb_visitor` VALUES ('1df18c5b8fb243f28cbad3ef6ff81151', '222.85.139.103', '192.168.1.97', '贵州省贵阳市', 'Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 10.0; WOW64; Trident/8.0; .NET4.0C; .NET4.0E; .NET CLR 2.0.50727; .NET CLR 3.0.30729; .NET CLR 3.5.30729; InfoPath.3; MALCJS)', '2016-01-13 17:04:05');
INSERT INTO `tb_visitor` VALUES ('1e1a3f3f11fb4aa3bcf23f2de1ced994', '222.85.139.103', '0:0:0:0:0:0:0:1', '贵州省贵阳市', 'Mozilla/5.0 (Windows NT 10.0; WOW64; rv:43.0) Gecko/20100101 Firefox/43.0', '2016-01-13 17:10:06');
INSERT INTO `tb_visitor` VALUES ('23bcee02f7744f548b3aa3b5730caa8b', '222.85.139.103', '192.168.1.97', '贵州省贵阳市', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36 Edge/12.10240', '2016-01-13 16:02:09');
INSERT INTO `tb_visitor` VALUES ('254f85ba7ab84badb7e64bfaa584c81f', '222.85.139.103', '192.168.1.47', '贵州省贵阳市', 'Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.3; WOW64; Trident/7.0; .NET4.0E; .NET4.0C; .NET CLR 3.5.30729; .NET CLR 2.0.50727; .NET CLR 3.0.30729; Tablet PC 2.0; InfoPath.3)', '2016-01-13 17:54:38');
INSERT INTO `tb_visitor` VALUES ('2661bf65791a4c91a6366cf0e8ed1cb2', '222.85.139.103', '192.168.1.97', '贵州省贵阳市', 'Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 10.0; WOW64; Trident/8.0; .NET4.0C; .NET4.0E; .NET CLR 2.0.50727; .NET CLR 3.0.30729; .NET CLR 3.5.30729; InfoPath.3; MALCJS)', '2016-01-13 17:07:33');
INSERT INTO `tb_visitor` VALUES ('30a6a5c3da8643cc9402adf449a0bd1e', '222.85.139.103', '192.168.1.47', '贵州省贵阳市', 'Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.3; WOW64; Trident/7.0; .NET4.0E; .NET4.0C; .NET CLR 3.5.30729; .NET CLR 2.0.50727; .NET CLR 3.0.30729; Tablet PC 2.0; InfoPath.3)', '2016-01-13 17:55:32');
INSERT INTO `tb_visitor` VALUES ('30ef298edc1046f3a034ce645c016127', '222.85.139.103', '192.168.1.17', '贵州省贵阳市', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:43.0) Gecko/20100101 Firefox/43.0', '2016-01-13 17:23:16');
INSERT INTO `tb_visitor` VALUES ('317afdddecba41b5a5c45906111ef78f', '222.85.139.103', '192.168.1.17', '贵州省贵阳市', 'Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.1; WOW64; Trident/6.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; .NET4.0C; .NET4.0E)', '2016-01-13 16:05:37');
INSERT INTO `tb_visitor` VALUES ('31a9bd1091634073b27840d37fb93dd5', '222.85.139.103', '192.168.1.17', '贵州省贵阳市', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:43.0) Gecko/20100101 Firefox/43.0', '2016-01-13 16:19:55');
INSERT INTO `tb_visitor` VALUES ('36a6cd217770452cb488364dd804b887', '222.85.139.103', '0:0:0:0:0:0:0:1', '贵州省贵阳市', 'Mozilla/5.0 (Windows NT 10.0; WOW64; rv:43.0) Gecko/20100101 Firefox/43.0', '2016-01-13 15:48:03');
INSERT INTO `tb_visitor` VALUES ('38841c3410a8491481534bae7814f73e', '222.85.139.103', '192.168.1.17', '贵州省贵阳市', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:43.0) Gecko/20100101 Firefox/43.0', '2016-01-13 17:10:45');
INSERT INTO `tb_visitor` VALUES ('3d66939bfced4b778ed87764db7a6e6a', '222.85.139.103', '192.168.1.17', '贵州省贵阳市', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:43.0) Gecko/20100101 Firefox/43.0', '2016-01-13 17:53:00');
INSERT INTO `tb_visitor` VALUES ('45dd5ff22c6e4a11989e4f0390a878e9', '222.85.139.103', '192.168.1.17', '贵州省贵阳市', 'Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.1; WOW64; Trident/6.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; .NET4.0C; .NET4.0E)', '2016-01-13 16:09:01');
INSERT INTO `tb_visitor` VALUES ('45ed21f10ab44e48bf458805e869707a', '222.85.139.103', '192.168.1.95', '贵州省贵阳市', 'Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 10.0; WOW64; Trident/8.0; .NET4.0C; .NET4.0E; .NET CLR 2.0.50727; .NET CLR 3.0.30729; .NET CLR 3.5.30729)', '2016-01-13 17:55:22');
INSERT INTO `tb_visitor` VALUES ('475241a497634f44b4b1e107e0f07206', '222.85.139.103', '0:0:0:0:0:0:0:1', '贵州省贵阳市', 'Mozilla/5.0 (Windows NT 10.0; WOW64; rv:43.0) Gecko/20100101 Firefox/43.0', '2016-01-13 17:40:14');
INSERT INTO `tb_visitor` VALUES ('47dd45ac86e64e99914e294acae8c7f2', '222.85.139.103', '192.168.1.17', '贵州省贵阳市', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:43.0) Gecko/20100101 Firefox/43.0', '2016-01-13 17:24:26');
INSERT INTO `tb_visitor` VALUES ('58f4b2a5a76840a1a1b2491dc65ae5df', '222.85.139.103', '192.168.1.26', '贵州省贵阳市', 'Mozilla/5.0 (Windows NT 10.0; WOW64; rv:43.0) Gecko/20100101 Firefox/43.0', '2016-01-13 16:18:14');
INSERT INTO `tb_visitor` VALUES ('5cdf8323d02349ba93ad44e230e5115d', '222.85.139.103', '0:0:0:0:0:0:0:1', '贵州省贵阳市', 'Mozilla/5.0 (Windows NT 10.0; WOW64; rv:43.0) Gecko/20100101 Firefox/43.0', '2016-01-13 17:15:06');
INSERT INTO `tb_visitor` VALUES ('62116f1158a049c186d2276d183467df', '222.85.139.103', '192.168.1.17', '贵州省贵阳市', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:43.0) Gecko/20100101 Firefox/43.0', '2016-01-13 17:24:44');
INSERT INTO `tb_visitor` VALUES ('67da7e98a5ea4b7297fc165a56136a4e', '222.85.139.103', '0:0:0:0:0:0:0:1', '贵州省贵阳市', 'Mozilla/5.0 (Windows NT 10.0; WOW64; rv:43.0) Gecko/20100101 Firefox/43.0', '2016-01-13 17:16:14');
INSERT INTO `tb_visitor` VALUES ('7329746d91f244f498b2989a92fa9f15', '222.85.139.103', '192.168.1.17', '贵州省贵阳市', 'Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.1; WOW64; Trident/6.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; .NET4.0C; .NET4.0E)', '2016-01-13 15:48:04');
INSERT INTO `tb_visitor` VALUES ('780f768f84684362b1d8cd61504f9a0f', '222.85.139.103', '192.168.1.95', '贵州省贵阳市', 'Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 10.0; WOW64; Trident/8.0; .NET4.0C; .NET4.0E; .NET CLR 2.0.50727; .NET CLR 3.0.30729; .NET CLR 3.5.30729)', '2016-01-13 17:55:34');
INSERT INTO `tb_visitor` VALUES ('881abbcc4f4a45e4976acd39be541b49', '222.85.139.103', '0:0:0:0:0:0:0:1', '贵州省贵阳市', 'Mozilla/5.0 (Windows NT 10.0; WOW64; rv:43.0) Gecko/20100101 Firefox/43.0', '2016-01-13 17:16:20');
INSERT INTO `tb_visitor` VALUES ('8a883d05d1d140e1b9421f5fcf9ceb8f', '222.85.139.103', '0:0:0:0:0:0:0:1', '贵州省贵阳市', 'Mozilla/5.0 (Windows NT 10.0; WOW64; rv:43.0) Gecko/20100101 Firefox/43.0', '2016-01-13 17:40:50');
INSERT INTO `tb_visitor` VALUES ('8a9b22a9214f4a38aa6a8a345a360de9', '222.85.139.103', '192.168.1.88', '贵州省贵阳市', 'Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 10.0; WOW64; Trident/8.0; .NET4.0C; .NET4.0E; InfoPath.3; .NET CLR 2.0.50727; .NET CLR 3.0.30729; .NET CLR 3.5.30729)', '2016-01-14 09:51:20');
INSERT INTO `tb_visitor` VALUES ('8b10782ebc07485aaf6d0c1fc6888607', '222.85.139.103', '192.168.1.97', '贵州省贵阳市', 'Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 10.0; WOW64; Trident/8.0; .NET4.0C; .NET4.0E; .NET CLR 2.0.50727; .NET CLR 3.0.30729; .NET CLR 3.5.30729; InfoPath.3; MALCJS)', '2016-01-13 19:04:23');
INSERT INTO `tb_visitor` VALUES ('8b6e0d09f0354d0b874fd882473ea966', '222.85.139.103', '192.168.1.47', '贵州省贵阳市', 'Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.3; WOW64; Trident/7.0; .NET4.0E; .NET4.0C; .NET CLR 3.5.30729; .NET CLR 2.0.50727; .NET CLR 3.0.30729; Tablet PC 2.0; InfoPath.3)', '2016-01-13 17:54:23');
INSERT INTO `tb_visitor` VALUES ('92fa15a1eb624bd5949abfb1c6852479', '222.85.139.103', '0:0:0:0:0:0:0:1', '贵州省贵阳市', 'Mozilla/5.0 (Windows NT 10.0; WOW64; rv:43.0) Gecko/20100101 Firefox/43.0', '2016-01-13 15:57:33');
INSERT INTO `tb_visitor` VALUES ('92ffd756b8cd46e9a9b2d7ca1e8ac1ae', '222.85.139.103', '192.168.1.95', '贵州省贵阳市', 'Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 10.0; WOW64; Trident/8.0; .NET4.0C; .NET4.0E; .NET CLR 2.0.50727; .NET CLR 3.0.30729; .NET CLR 3.5.30729)', '2016-01-14 09:46:07');
INSERT INTO `tb_visitor` VALUES ('946f16c6e02942f9a3195a4903861bb4', '222.85.139.103', '192.168.1.47', '贵州省贵阳市', 'Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.3; WOW64; Trident/7.0; .NET4.0E; .NET4.0C; .NET CLR 3.5.30729; .NET CLR 2.0.50727; .NET CLR 3.0.30729; Tablet PC 2.0; InfoPath.3)', '2016-01-13 17:59:56');
INSERT INTO `tb_visitor` VALUES ('961c54341de8457095a641325d299086', '222.85.139.103', '0:0:0:0:0:0:0:1', '贵州省贵阳市', 'Mozilla/5.0 (Windows NT 10.0; WOW64; rv:43.0) Gecko/20100101 Firefox/43.0', '2016-01-13 15:50:22');
INSERT INTO `tb_visitor` VALUES ('9c1a7ae508e24baab274d5dc9b9a3017', '222.85.139.103', '192.168.1.26', '贵州省贵阳市', 'Mozilla/5.0 (Windows NT 10.0; WOW64; rv:43.0) Gecko/20100101 Firefox/43.0', '2016-01-13 18:07:10');
INSERT INTO `tb_visitor` VALUES ('9fc2cabb1dfe40ca94b32b787ae2bc60', '222.85.139.103', '192.168.1.17', '贵州省贵阳市', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:43.0) Gecko/20100101 Firefox/43.0', '2016-01-13 16:19:28');
INSERT INTO `tb_visitor` VALUES ('a10ba746a1b44d6f8f958d79809963c8', '222.85.139.103', '192.168.1.97', '贵州省贵阳市', 'Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 10.0; WOW64; Trident/8.0; .NET4.0C; .NET4.0E; .NET CLR 2.0.50727; .NET CLR 3.0.30729; .NET CLR 3.5.30729; InfoPath.3; MALCJS)', '2016-01-13 17:16:05');
INSERT INTO `tb_visitor` VALUES ('b14919fd308146e18f05b4d206684ec7', '222.85.139.103', '192.168.1.88', '贵州省贵阳市', 'Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 10.0; WOW64; Trident/8.0; .NET4.0C; .NET4.0E; InfoPath.3; .NET CLR 2.0.50727; .NET CLR 3.0.30729; .NET CLR 3.5.30729)', '2016-01-14 09:50:22');
INSERT INTO `tb_visitor` VALUES ('b4a67421d5e24ab6b35a36f486b8e4fc', '222.85.139.103', '0:0:0:0:0:0:0:1', '贵州省贵阳市', 'Mozilla/5.0 (Windows NT 10.0; WOW64; rv:43.0) Gecko/20100101 Firefox/43.0', '2016-01-13 15:50:11');
INSERT INTO `tb_visitor` VALUES ('c88f4ee171f44f4288742acaeb9a6614', '222.85.139.103', '192.168.1.26', '贵州省贵阳市', 'Mozilla/5.0 (Windows NT 10.0; WOW64; rv:43.0) Gecko/20100101 Firefox/43.0', '2016-01-13 16:19:34');
INSERT INTO `tb_visitor` VALUES ('cc40ddb6d60d4613878207a23c245e34', '222.85.139.103', '192.168.1.17', '贵州省贵阳市', 'Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.1; WOW64; Trident/6.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; .NET4.0C; .NET4.0E)', '2016-01-13 16:08:59');
INSERT INTO `tb_visitor` VALUES ('ccabbb747ef34cf598acaf3d938cb3ec', '222.85.139.103', '192.168.1.17', '贵州省贵阳市', 'Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.1; Win64; x64; Trident/6.0; .NET CLR 2.0.50727; SLCC2; .NET CLR 3.5.30729; .NET CLR 3.0.30729; .NET4.0C; .NET4.0E)', '2016-01-13 15:52:33');
INSERT INTO `tb_visitor` VALUES ('ceb22e52de36408b83bae73eee0cfd77', '222.85.139.103', '192.168.1.47', '贵州省贵阳市', 'Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.3; WOW64; Trident/7.0; .NET4.0E; .NET4.0C; .NET CLR 3.5.30729; .NET CLR 2.0.50727; .NET CLR 3.0.30729; Tablet PC 2.0; InfoPath.3)', '2016-01-13 17:59:46');
INSERT INTO `tb_visitor` VALUES ('e44857cf3d4d4ae9a50b50496057f286', '222.85.139.103', '0:0:0:0:0:0:0:1', '贵州省贵阳市', 'Mozilla/5.0 (Windows NT 10.0; WOW64; rv:43.0) Gecko/20100101 Firefox/43.0', '2016-01-13 15:51:13');
INSERT INTO `tb_visitor` VALUES ('e69ca369bd724486a009cbfaeee26b5c', '222.85.139.103', '127.0.0.1', '贵州省贵阳市', 'Mozilla/5.0 (Windows NT 10.0; WOW64; rv:43.0) Gecko/20100101 Firefox/43.0', '2016-01-14 09:39:28');
INSERT INTO `tb_visitor` VALUES ('e744998f179c4718bfdbe4a02be0bc06', '222.85.139.103', '192.168.1.17', '贵州省贵阳市', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:43.0) Gecko/20100101 Firefox/43.0', '2016-01-13 17:23:43');
INSERT INTO `tb_visitor` VALUES ('efe6be723887493c9f222a9ec09028a5', '222.85.139.103', '192.168.1.97', '贵州省贵阳市', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36 Edge/12.10240', '2016-01-13 16:02:29');
INSERT INTO `tb_visitor` VALUES ('f4622342479c43c0bf482ddad8a47f23', '222.85.139.103', '0:0:0:0:0:0:0:1', '贵州省贵阳市', 'Mozilla/5.0 (Windows NT 10.0; WOW64; rv:43.0) Gecko/20100101 Firefox/43.0', '2016-01-13 16:20:14');
INSERT INTO `tb_visitor` VALUES ('fd24ab8de6114e7aab638911ea0162f3', '222.85.139.103', '192.168.1.17', '贵州省贵阳市', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:43.0) Gecko/20100101 Firefox/43.0', '2016-01-13 16:20:03');
DROP TRIGGER IF EXISTS `addcommentsNun`;
DELIMITER ;;
CREATE TRIGGER `addcommentsNun` BEFORE INSERT ON `tb_comments` FOR EACH ROW begin
SET @id=NEW.articleId;
update tb_article set tb_article.commentsNun=tb_article.commentsNun+1 where tb_article.id=@id;
end
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `delcommentsNun`;
DELIMITER ;;
CREATE TRIGGER `delcommentsNun` BEFORE DELETE ON `tb_comments` FOR EACH ROW begin
SET @id=old.articleId;
update tb_article set tb_article.commentsNun=tb_article.commentsNun-1 where tb_article.id=@id;
end
;;
DELIMITER ;
