/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50703
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50703
File Encoding         : 65001

Date: 2014-11-12 22:22:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for circle
-- ----------------------------
DROP TABLE IF EXISTS `circle`;
CREATE TABLE `circle` (
  `circle_id` int(11) NOT NULL AUTO_INCREMENT,
  `creator_id` int(11) DEFAULT NULL,
  `group_id` varchar(18) DEFAULT NULL,
  `circle_name` varchar(255) DEFAULT NULL,
  `circle_description` varchar(255) DEFAULT NULL,
  `circle_avatar` varchar(255) DEFAULT NULL,
  `category` int(11) DEFAULT NULL,
  `longitude` double(20,7) DEFAULT NULL,
  `latitude` double(20,7) DEFAULT NULL,
  `circle_create_time` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`circle_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of circle
-- ----------------------------
INSERT INTO `circle` VALUES ('1', '-1', '1412996732230555', '个人show', '官方圈子官方圈子官方圈子官方圈子官方圈子官方圈子官方圈子官方圈子官方圈子官方圈子官方圈子官方圈子官方圈子官方圈子', 'http://10.6.7.158:8080/InterestFriend/images/2014-09-23-15-37-27.jpg', '1', null, null, '2014-09-16');

-- ----------------------------
-- Table structure for circlemembers
-- ----------------------------
DROP TABLE IF EXISTS `circlemembers`;
CREATE TABLE `circlemembers` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `circle_id` int(11) DEFAULT NULL,
  `last_update_time` bigint(255) DEFAULT NULL,
  `user_state` varchar(255) DEFAULT NULL,
  `circle_last_request_time` bigint(20) DEFAULT NULL,
  `circle_state` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=82 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of circlemembers
-- ----------------------------
INSERT INTO `circlemembers` VALUES ('80', '7', '1', '1415586508341', 'ADD', '1415586508341', 'ADD');
INSERT INTO `circlemembers` VALUES ('81', '8', '1', '1415694565546', 'UPDATE', '1415586523304', 'ADD');

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `comment_id` int(11) NOT NULL AUTO_INCREMENT,
  `publisher_id` int(11) DEFAULT NULL,
  `growth_id` int(11) DEFAULT NULL,
  `comment_content` varchar(255) DEFAULT NULL,
  `comment_time` varchar(255) NOT NULL,
  `reply_someone_id` int(11) DEFAULT NULL,
  `reply_someone_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`comment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comment
-- ----------------------------

-- ----------------------------
-- Table structure for growth
-- ----------------------------
DROP TABLE IF EXISTS `growth`;
CREATE TABLE `growth` (
  `growth_id` int(11) NOT NULL AUTO_INCREMENT,
  `cid` int(11) DEFAULT NULL,
  `publisher_id` int(11) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `time` varchar(255) DEFAULT NULL,
  `praise_count` int(11) DEFAULT NULL,
  PRIMARY KEY (`growth_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of growth
-- ----------------------------
INSERT INTO `growth` VALUES ('1', '1', '8', '', '2014-11-12 15:05', '14');
INSERT INTO `growth` VALUES ('2', '1', '8', '咯用陌陌', '2014-11-12 15:05', '6');

-- ----------------------------
-- Table structure for growth_img
-- ----------------------------
DROP TABLE IF EXISTS `growth_img`;
CREATE TABLE `growth_img` (
  `image_id` int(11) NOT NULL AUTO_INCREMENT,
  `cid` int(11) DEFAULT NULL,
  `growth_id` int(11) DEFAULT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`image_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of growth_img
-- ----------------------------
INSERT INTO `growth_img` VALUES ('1', '1', '1', 'http://10.6.7.158:8080/InterestFriend/growth-image/2014-11-12-15-05-37-1.jpg');
INSERT INTO `growth_img` VALUES ('2', '1', '1', 'http://10.6.7.158:8080/InterestFriend/growth-image/2014-11-12-15-05-37-2.jpg');
INSERT INTO `growth_img` VALUES ('3', '1', '1', 'http://10.6.7.158:8080/InterestFriend/growth-image/2014-11-12-15-05-37-3.jpg');
INSERT INTO `growth_img` VALUES ('4', '1', '1', 'http://10.6.7.158:8080/InterestFriend/growth-image/2014-11-12-15-05-37-4.jpg');
INSERT INTO `growth_img` VALUES ('5', '1', '1', 'http://10.6.7.158:8080/InterestFriend/growth-image/2014-11-12-15-05-37-5.jpg');
INSERT INTO `growth_img` VALUES ('6', '1', '1', 'http://10.6.7.158:8080/InterestFriend/growth-image/2014-11-12-15-05-37-6.jpg');
INSERT INTO `growth_img` VALUES ('7', '1', '1', 'http://10.6.7.158:8080/InterestFriend/growth-image/2014-11-12-15-05-37-7.jpg');
INSERT INTO `growth_img` VALUES ('8', '1', '1', 'http://10.6.7.158:8080/InterestFriend/growth-image/2014-11-12-15-05-37-8.jpg');
INSERT INTO `growth_img` VALUES ('9', '1', '1', 'http://10.6.7.158:8080/InterestFriend/growth-image/2014-11-12-15-05-37-9.jpg');
INSERT INTO `growth_img` VALUES ('10', '1', '2', 'http://10.6.7.158:8080/InterestFriend/growth-image/2014-11-12-15-05-51-1.jpg');
INSERT INTO `growth_img` VALUES ('11', '1', '2', 'http://10.6.7.158:8080/InterestFriend/growth-image/2014-11-12-15-05-51-2.jpg');

-- ----------------------------
-- Table structure for growth_praise
-- ----------------------------
DROP TABLE IF EXISTS `growth_praise`;
CREATE TABLE `growth_praise` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `growth_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of growth_praise
-- ----------------------------
INSERT INTO `growth_praise` VALUES ('9', '8', '2');
INSERT INTO `growth_praise` VALUES ('10', '8', '2');
INSERT INTO `growth_praise` VALUES ('11', '8', '2');

-- ----------------------------
-- Table structure for member_circles
-- ----------------------------
DROP TABLE IF EXISTS `member_circles`;
CREATE TABLE `member_circles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `circle_id` int(11) DEFAULT NULL,
  `circle_last_request_time` bigint(20) DEFAULT NULL,
  `circle_state` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of member_circles
-- ----------------------------

-- ----------------------------
-- Table structure for qequest_join_circle
-- ----------------------------
DROP TABLE IF EXISTS `qequest_join_circle`;
CREATE TABLE `qequest_join_circle` (
  `join_circle_id` int(11) DEFAULT NULL,
  `join_circle_creator_id` int(11) DEFAULT NULL,
  `request_join_circle_user_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qequest_join_circle
-- ----------------------------
INSERT INTO `qequest_join_circle` VALUES ('20', '8', '7');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) NOT NULL,
  `user_chat_id` varchar(255) DEFAULT NULL,
  `user_cellphone` varchar(255) NOT NULL,
  `user_password` varchar(255) NOT NULL,
  `user_gender` varchar(255) DEFAULT NULL,
  `user_birthday` varchar(255) DEFAULT NULL,
  `user_avatar` varchar(255) DEFAULT NULL,
  `user_pinyin_str` varchar(255) DEFAULT NULL,
  `user_sort_key` varchar(255) DEFAULT NULL,
  `user_register_time` varchar(255) DEFAULT NULL,
  `user_declaration` varchar(255) DEFAULT NULL,
  `user_description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('-1', '趣友', null, '', '', '', '', '', '', '', '', '', '');
INSERT INTO `user` VALUES ('7', '短短的', '13165146101', '13165146101', '123456', '男', '2014-9-11', 'http://10.6.7.158:8080/InterestFriend/user-avatar/2014-10-11-11-12-17.jpg', 'duanduande', 'ddd', '2014-09-11 ', null, null);
INSERT INTO `user` VALUES ('8', '宋斌彬', '18560133195', '18560133195', '123456', '女', '2014-9-11', 'http://10.6.7.158:8080/InterestFriend/user-avatar/2014-10-28-16-53-16.jpg', 'songbinbin', 'sbb ', '2014-10-07 ', 'g法国嘎嘎嘎是鼎飞丹砂是对方公司的鬼地方是对方公司的分公司答复是对方公司的分公司答复公司的法规到分公司答复嘎股份公司啦啦啦g法国嘎嘎嘎嘎股份公司啦啦啦g法国嘎嘎嘎嘎股份公司啦啦啦g法国嘎嘎嘎嘎股份公司啦啦啦g法国嘎嘎嘎嘎股份公司g法国嘎嘎嘎嘎股份公司啦啦啦g法国嘎嘎嘎嘎股份公司啦啦啦g法国嘎嘎嘎嘎股份公司啦啦啦g法国嘎嘎嘎嘎股份公司啦啦啦g法国嘎嘎嘎嘎股份公司啦啦啦g法国嘎嘎嘎嘎股份公司啦啦啦啦啦', '24若非等人www五我');

-- ----------------------------
-- Table structure for video
-- ----------------------------
DROP TABLE IF EXISTS `video`;
CREATE TABLE `video` (
  `video_id` int(11) NOT NULL AUTO_INCREMENT,
  `publisher_id` int(11) DEFAULT NULL,
  `cid` int(11) DEFAULT NULL,
  `video_img` varchar(255) DEFAULT NULL,
  `video_path` varchar(255) DEFAULT NULL,
  `video_size` int(11) DEFAULT NULL,
  `time` varchar(255) DEFAULT NULL,
  `video_duration` varchar(255) DEFAULT NULL,
  `video_content` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`video_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of video
-- ----------------------------

-- ----------------------------
-- Table structure for video_comment
-- ----------------------------
DROP TABLE IF EXISTS `video_comment`;
CREATE TABLE `video_comment` (
  `comment_id` int(11) NOT NULL AUTO_INCREMENT,
  `video_id` int(11) DEFAULT NULL,
  `publisher_id` int(11) DEFAULT NULL,
  `comment_content` varchar(255) DEFAULT NULL,
  `comment_time` varchar(255) DEFAULT NULL,
  `reply_someone_name` varchar(255) DEFAULT NULL,
  `reply_someone_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`comment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of video_comment
-- ----------------------------
