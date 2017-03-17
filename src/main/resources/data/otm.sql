/*
Navicat MySQL Data Transfer

Source Server         : mysql - localhost
Source Server Version : 50716
Source Host           : localhost:3306
Source Database       : online_test_maker

Target Server Type    : MYSQL
Target Server Version : 50716
File Encoding         : 65001

Date: 2017-03-17 11:50:04
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `answer`
-- ----------------------------
DROP TABLE IF EXISTS `answer`;
CREATE TABLE `answer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `value` varchar(255) NOT NULL,
  `question_id` bigint(20) NOT NULL,
  `order` int(11) NOT NULL,
  `correct` bit(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8frr4bcabmmeyyu60qt7iiblo` (`question_id`),
  CONSTRAINT `FK8frr4bcabmmeyyu60qt7iiblo` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`),
  CONSTRAINT `FK_QUESTION` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of answer
-- ----------------------------
INSERT INTO `answer` VALUES ('1', 'It\'s something related to GUI', '1', '1', '');
INSERT INTO `answer` VALUES ('2', 'Abstract type', '1', '3', '');
INSERT INTO `answer` VALUES ('3', 'It\'s a something between two faces', '1', '2', '');
INSERT INTO `answer` VALUES ('4', 'It\'s a blue book about java', '1', '4', '');
INSERT INTO `answer` VALUES ('5', 'Final is used when you want to finish the program', '2', '2', '');
INSERT INTO `answer` VALUES ('6', 'Final is used with a variable to say that its value cannot change', '2', '1', '');
INSERT INTO `answer` VALUES ('7', 'Final is used on a class that can\'t be extended', '2', '3', '');
INSERT INTO `answer` VALUES ('8', 'Final is used after a try catch block', '2', '4', '');
INSERT INTO `answer` VALUES ('9', 'It is used to prevent two threads calling the same method simultaneously', '3', '2', '');
INSERT INTO `answer` VALUES ('10', 'It is used to synchronize data on different relational databases', '3', '1', '');
INSERT INTO `answer` VALUES ('11', 'It is used by the garbage collector to synchronize not used variables', '3', '3', '');
INSERT INTO `answer` VALUES ('12', 'It is not a keyword', '3', '4', '');
INSERT INTO `answer` VALUES ('13', 'Spring', '4', '1', '');
INSERT INTO `answer` VALUES ('14', 'Symfony', '4', '2', '');
INSERT INTO `answer` VALUES ('15', 'Hibernate', '4', '3', '');
INSERT INTO `answer` VALUES ('16', 'Javascript', '4', '4', '');
INSERT INTO `answer` VALUES ('17', 'Adobe CQ5', '4', '5', '');
INSERT INTO `answer` VALUES ('18', 'Vakinankaratra', '6', '1', '');
INSERT INTO `answer` VALUES ('19', 'Ambatolampy', '6', '2', '');
INSERT INTO `answer` VALUES ('20', 'Analamanga', '6', '3', '');
INSERT INTO `answer` VALUES ('21', 'Alaotra Mangoro', '6', '4', '');
INSERT INTO `answer` VALUES ('22', 'Amoron\'i Mania', '6', '5', '');

-- ----------------------------
-- Table structure for `exam`
-- ----------------------------
DROP TABLE IF EXISTS `exam`;
CREATE TABLE `exam` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `duration` bigint(20) DEFAULT NULL,
  `pass_score` int(20) NOT NULL DEFAULT '60',
  `maximum_score` int(20) NOT NULL DEFAULT '100',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of exam
-- ----------------------------
INSERT INTO `exam` VALUES ('1', 'Java test skills', 'This test is designed to assess your java skills. It will test your ability to code with Java language, your ability to understand how collections work, how maps are used. You will also see some algorithms solving.', '1', '60', '100');
INSERT INTO `exam` VALUES ('2', 'Madagascar General knowledges', 'The objective of this test is to assess your knowledges about Madagascar, its economy, history, geography...', '10', '60', '100');

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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of question
-- ----------------------------
INSERT INTO `question` VALUES ('1', '1', 'What is an interface?', null, 'SINGLE_CHOICE', '1');
INSERT INTO `question` VALUES ('2', '1', 'What is the purpose of the keyword \'final\'?', 'Choose all that apply', 'MULTIPLE_CHOICE', '2');
INSERT INTO `question` VALUES ('3', '1', 'Which best describes the use of synchronized keyword?', null, 'SINGLE_CHOICE', '3');
INSERT INTO `question` VALUES ('4', '1', 'Choose all Java technologies ', 'Choose all that apply', 'MULTIPLE_CHOICE', '4');
INSERT INTO `question` VALUES ('6', '2', 'What is the region of Antsirabe', null, 'SINGLE_CHOICE', '1');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
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

-- ----------------------------
-- Table structure for `user_answer`
-- ----------------------------
DROP TABLE IF EXISTS `user_answer`;
CREATE TABLE `user_answer` (
  `user_id` bigint(20) NOT NULL,
  `answer_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`answer_id`),
  KEY `FKm321eamc0drwpxfkvyl9giypt` (`answer_id`),
  CONSTRAINT `FK7kw17ur9w2egkc51xua3yh0ux` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKm321eamc0drwpxfkvyl9giypt` FOREIGN KEY (`answer_id`) REFERENCES `answer` (`id`),
  CONSTRAINT `FKtydvp1fxqvpsxsuumj5vk9ky` FOREIGN KEY (`answer_id`) REFERENCES `answer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_answer
-- ----------------------------
INSERT INTO `user_answer` VALUES ('1', '1');
INSERT INTO `user_answer` VALUES ('1', '5');
INSERT INTO `user_answer` VALUES ('1', '11');
INSERT INTO `user_answer` VALUES ('1', '18');

-- ----------------------------
-- Table structure for `user_exam`
-- ----------------------------
DROP TABLE IF EXISTS `user_exam`;
CREATE TABLE `user_exam` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `exam_id` bigint(20) NOT NULL,
  `start_date` datetime DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  `score` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKl44lsl55re2s7jhjlh527y85l` (`exam_id`),
  KEY `FK30xxyjqs5y3dnq0qtb2o159ds` (`user_id`),
  CONSTRAINT `FK30xxyjqs5y3dnq0qtb2o159ds` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKl44lsl55re2s7jhjlh527y85l` FOREIGN KEY (`exam_id`) REFERENCES `exam` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_exam
-- ----------------------------
INSERT INTO `user_exam` VALUES ('1', '1', '1', '2017-03-17 10:27:12', '2017-03-17 10:28:12', '0');
INSERT INTO `user_exam` VALUES ('2', '1', '2', '2017-03-17 03:11:07', null, null);
