/*
Navicat MySQL Data Transfer

Source Server         : mysql - localhost
Source Server Version : 50716
Source Host           : localhost:3306
Source Database       : online_test_maker

Target Server Type    : MYSQL
Target Server Version : 50716
File Encoding         : 65001

Date: 2017-03-15 00:05:21
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `exam`
-- ----------------------------
DROP TABLE IF EXISTS `exam`;
CREATE TABLE `exam` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `duration` bigint(20) DEFAULT NULL,
  `pass_score` bigint(20) NOT NULL DEFAULT '60',
  `maximum_score` bigint(20) NOT NULL DEFAULT '100',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of exam
-- ----------------------------
INSERT INTO `exam` VALUES ('1', 'Java test skills', 'This test is designed to assess your java skills. It will test your ability to code with Java language, your ability to understand how collections work, how maps are used. You will also see some algorithms solving.', '60', '60', '100');
INSERT INTO `exam` VALUES ('2', 'Histoire de Mada', 'Test vos connaissances generales sur l\'histoire de Madagascar', '10', '60', '100');

-- ----------------------------
-- Table structure for `exam_questions`
-- ----------------------------
DROP TABLE IF EXISTS `exam_questions`;
CREATE TABLE `exam_questions` (
  `exam_id` bigint(20) NOT NULL,
  `questions_id` bigint(20) NOT NULL,
  PRIMARY KEY (`exam_id`,`questions_id`),
  UNIQUE KEY `UK_cxrwctay9v81ai2mrjf9dk6hh` (`questions_id`),
  CONSTRAINT `FK4choqefx183qopsef3ajgg9fm` FOREIGN KEY (`exam_id`) REFERENCES `exam` (`id`),
  CONSTRAINT `FKebmtdfdhau51c4najr9i96pve` FOREIGN KEY (`questions_id`) REFERENCES `question` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of exam_questions
-- ----------------------------

-- ----------------------------
-- Table structure for `question`
-- ----------------------------
DROP TABLE IF EXISTS `question`;
CREATE TABLE `question` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `exam_id` bigint(20) NOT NULL,
  `title` varchar(255) NOT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `type` varchar(50) NOT NULL,
  `order` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKhupso6ldavcx993tfnrjsdl1p` (`exam_id`),
  CONSTRAINT `FK_EXAM` FOREIGN KEY (`exam_id`) REFERENCES `exam` (`id`),
  CONSTRAINT `FKhupso6ldavcx993tfnrjsdl1p` FOREIGN KEY (`exam_id`) REFERENCES `exam` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of question
-- ----------------------------
INSERT INTO `question` VALUES ('1', '1', 'What is an interface?', null, 'SINGLE_CHOICE', '1');
INSERT INTO `question` VALUES ('2', '1', 'What is the purpose of the keyword \'final\'?', null, 'SINGLE_CHOICE', '2');
INSERT INTO `question` VALUES ('3', '1', 'Which best describes the use of synchronized keyword?', null, 'SINGLE_CHOICE', '3');
INSERT INTO `question` VALUES ('4', '1', 'Choose all Java technologies ', 'Choose all that apply', 'MULTIPLE_CHOICE', '4');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(100) DEFAULT NULL,
  `user_name` varchar(100) NOT NULL,
  `user_password` varchar(255) DEFAULT NULL,
  `last_name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'Marc', 'kobs', '$2a$04$SVRptDd/VbOqbLq36VnrqeKCtCVUUQeSsSBp7odmahKAFbwmrNDXq', 'Ratombotsoa');
