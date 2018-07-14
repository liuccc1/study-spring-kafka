/*
 Navicat MySQL Data Transfer

 Source Server         : mysql
 Source Server Version : 50625
 Source Host           : localhost
 Source Database       : test

 Target Server Version : 50625
 File Encoding         : utf-8

 Date: 07/14/2018 14:47:52 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `t_kafka_event_process`
-- ----------------------------
DROP TABLE IF EXISTS `t_kafka_event_process`;
CREATE TABLE `t_kafka_event_process` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `payload` varchar(2000) NOT NULL,
  `eventType` varchar(30) NOT NULL,
  `status` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_kafka_event_publish`
-- ----------------------------
DROP TABLE IF EXISTS `t_kafka_event_publish`;
CREATE TABLE `t_kafka_event_publish` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `payload` varchar(2000) NOT NULL,
  `eventType` varchar(30) NOT NULL,
  `status` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_kafka_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_kafka_user`;
CREATE TABLE `t_kafka_user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `createTime` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
