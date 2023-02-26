/*
 Navicat Premium Data Transfer

 Source Server         : db
 Source Server Type    : MySQL
 Source Server Version : 80016 (8.0.16)
 Source Host           : localhost:3306
 Source Schema         : project

 Target Server Type    : MySQL
 Target Server Version : 80016 (8.0.16)
 File Encoding         : 65001

 Date: 26/02/2023 20:07:17
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_activity
-- ----------------------------
DROP TABLE IF EXISTS `t_activity`;
CREATE TABLE `t_activity`  (
  `acid` int(11) NOT NULL AUTO_INCREMENT,
  `activity_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `activity_club` int(11) NULL DEFAULT NULL,
  `activity_host` int(255) NULL DEFAULT NULL,
  `activity_start` datetime NULL DEFAULT NULL,
  `activity_time` datetime NULL DEFAULT NULL,
  `activity_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `activity_tel` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `activity_introduction` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `activity_state` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`acid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_activity
-- ----------------------------
INSERT INTO `t_activity` VALUES (4, '活动1', 1, 1, '2023-02-07 14:36:10', '2023-02-28 14:36:12', '福建师范大学', '18250172726', '这是第一个活动', 1);
INSERT INTO `t_activity` VALUES (5, '活动2', 1, 1, '2023-01-31 22:50:36', '2023-03-04 21:58:06', '福建师范大学', '13314958841', 'second', 2);
INSERT INTO `t_activity` VALUES (6, '活动3', 1, 1, '2023-02-22 22:50:40', '2023-02-23 22:50:43', '福建师范大学', '13245665465', '阿萨德', 1);
INSERT INTO `t_activity` VALUES (7, '活动4', 1, 1, '2023-02-23 23:00:14', '2023-02-25 23:00:18', '地球', '12312415151', '按时发送阿斯', 1);

-- ----------------------------
-- Table structure for t_apply
-- ----------------------------
DROP TABLE IF EXISTS `t_apply`;
CREATE TABLE `t_apply`  (
  `aid` int(11) NOT NULL AUTO_INCREMENT,
  `apply_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `apply_type` int(11) NULL DEFAULT NULL,
  `applicant` int(11) NULL DEFAULT NULL,
  `apply_time` datetime NULL DEFAULT NULL,
  `apply_found` datetime NULL DEFAULT NULL,
  `apply_reason` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `apply_state` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`aid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_apply
-- ----------------------------
INSERT INTO `t_apply` VALUES (1, '观影社1', 1, 1, '2023-02-18 10:20:18', '2023-02-22 11:31:03', NULL, 1);
INSERT INTO `t_apply` VALUES (2, '观影社2', 2, 3, '2023-02-18 10:26:52', '2023-03-01 11:31:16', NULL, 2);
INSERT INTO `t_apply` VALUES (3, '网球社', 3, 5, NULL, NULL, NULL, 1);
INSERT INTO `t_apply` VALUES (4, '羽毛球社', 4, NULL, NULL, NULL, NULL, 2);

-- ----------------------------
-- Table structure for t_attend
-- ----------------------------
DROP TABLE IF EXISTS `t_attend`;
CREATE TABLE `t_attend`  (
  `acid` int(11) NOT NULL,
  `uid` int(11) NOT NULL,
  PRIMARY KEY (`acid`, `uid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_attend
-- ----------------------------

-- ----------------------------
-- Table structure for t_club
-- ----------------------------
DROP TABLE IF EXISTS `t_club`;
CREATE TABLE `t_club`  (
  `cid` int(11) NOT NULL AUTO_INCREMENT,
  `club_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `club_type` int(255) NULL DEFAULT NULL,
  `club_head` int(11) NULL DEFAULT NULL,
  `club_img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `club_introduction` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `club_number` int(11) NULL DEFAULT NULL,
  `club_grade` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `club_create` datetime NULL DEFAULT NULL,
  `club_state` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`cid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_club
-- ----------------------------
INSERT INTO `t_club` VALUES (1, '轮滑社', 3, 3, 'http://localhost:8888/file/f23400aae009403c9886439278638cfc.png', '辩论与口才协会成立于2003年11月，现有会员65人。协会宗旨是发扬我中华民族优良传统文化，提高逻辑推理能力，促进同学们的思考研究，学习表达技巧，以推动本校的辩论风气，训练演说及辩论人才，以达到高尚之涵养与情操。', 50, '9.6', '2023-02-17 23:11:00', 1);
INSERT INTO `t_club` VALUES (2, '1768轮滑协会', 3, 3, 'http://localhost:8888/file/11d37f7b023446e3bf760b9a66659524.png', '辩论与口才协会成立于2003年11月，现有会员65人。协会宗旨是发扬我中华民族优良传统文化，提高逻辑推理能力，促进同学们的思考研究，学习表达技巧，以推动本校的辩论风气，训练演说及辩论人才，以达到高尚之涵养与情操。', NULL, NULL, '2023-02-16 23:26:49', 1);
INSERT INTO `t_club` VALUES (3, '随便什么社', 2, 4, 'http://localhost:8888/file/c5584016602b4368947115c4cef67fd2.png', '辩论与口才协会成立于2003年11月，现有会员65人。协会宗旨是发扬我中华民族优良传统文化，提高逻辑推理能力，促进同学们的思考研究，学习表达技巧，以推动本校的辩论风气，训练演说及辩论人才，以达到高尚之涵养与情操。', NULL, NULL, '2023-02-21 16:47:59', 2);
INSERT INTO `t_club` VALUES (5, '阿萨德', 2, 5, 'http://localhost:8888/file/f23400aae009403c9886439278638cfc.png', '辩论与口才协会成立于2003年11月，现有会员65人。协会宗旨是发扬我中华民族优良传统文化，提高逻辑推理能力，促进同学们的思考研究，学习表达技巧，以推动本校的辩论风气，训练演说及辩论人才，以达到高尚之涵养与情操。', NULL, NULL, '2023-02-15 00:06:23', 1);
INSERT INTO `t_club` VALUES (6, '社团', 1, 8, 'http://localhost:8888/file/c5584016602b4368947115c4cef67fd2.png', '辩论与口才协会成立于2003年11月，现有会员65人。协会宗旨是发扬我中华民族优良传统文化，提高逻辑推理能力，促进同学们的思考研究，学习表达技巧，以推动本校的辩论风气，训练演说及辩论人才，以达到高尚之涵养与情操。', NULL, NULL, '2023-02-23 00:40:38', 1);

-- ----------------------------
-- Table structure for t_file
-- ----------------------------
DROP TABLE IF EXISTS `t_file`;
CREATE TABLE `t_file`  (
  `fid` int(11) NOT NULL AUTO_INCREMENT,
  `file_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `file_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `file_size` bigint(20) NULL DEFAULT NULL,
  `file_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `file_md5` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`fid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 44 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_file
-- ----------------------------
INSERT INTO `t_file` VALUES (7, 'avatar.png', 'png', 62, 'http://localhost:8888/file/5b8c4d014065410d974f412fef8dac5a.png', 'c08dd1b4a37f93fdebc6a4c596d71d8c');
INSERT INTO `t_file` VALUES (13, '2.png', 'png', 10, 'http://localhost:8888/file/b185b474cc024bdf9148d62cc8b0103b.png', 'a7dd7975dc25ab19a231842e7e3bb49f');
INSERT INTO `t_file` VALUES (27, '1.png', 'png', 10, 'http://localhost:8888/file/b185b474cc024bdf9148d62cc8b0103b.png', 'a7dd7975dc25ab19a231842e7e3bb49f');
INSERT INTO `t_file` VALUES (28, '1.png', 'png', 10, 'http://localhost:8888/file/b185b474cc024bdf9148d62cc8b0103b.png', 'a7dd7975dc25ab19a231842e7e3bb49f');
INSERT INTO `t_file` VALUES (29, 'avatar.png', 'png', 62, 'http://localhost:8888/file/5b8c4d014065410d974f412fef8dac5a.png', 'c08dd1b4a37f93fdebc6a4c596d71d8c');
INSERT INTO `t_file` VALUES (30, 'avatar.png', 'png', 62, 'http://localhost:8888/file/5b8c4d014065410d974f412fef8dac5a.png', 'c08dd1b4a37f93fdebc6a4c596d71d8c');
INSERT INTO `t_file` VALUES (31, '1.png', 'png', 10, 'http://localhost:8888/file/b185b474cc024bdf9148d62cc8b0103b.png', 'a7dd7975dc25ab19a231842e7e3bb49f');
INSERT INTO `t_file` VALUES (32, 'avatar.png', 'png', 62, 'http://localhost:8888/file/5b8c4d014065410d974f412fef8dac5a.png', 'c08dd1b4a37f93fdebc6a4c596d71d8c');
INSERT INTO `t_file` VALUES (33, '1.png', 'png', 10, 'http://localhost:8888/file/b185b474cc024bdf9148d62cc8b0103b.png', 'a7dd7975dc25ab19a231842e7e3bb49f');
INSERT INTO `t_file` VALUES (34, '1.png', 'png', 10, 'http://localhost:8888/file/b185b474cc024bdf9148d62cc8b0103b.png', 'a7dd7975dc25ab19a231842e7e3bb49f');
INSERT INTO `t_file` VALUES (35, '300.jpg', 'jpg', 19, 'http://localhost:8888/file/70cb27739c724102a53bfb3e8c839c9a.jpg', 'be6cf27b74443617c2847fee3b3c1ee7');
INSERT INTO `t_file` VALUES (36, 'avatar.png', 'png', 62, 'http://localhost:8888/file/5b8c4d014065410d974f412fef8dac5a.png', 'c08dd1b4a37f93fdebc6a4c596d71d8c');
INSERT INTO `t_file` VALUES (37, '动漫社.png', 'png', 619, 'http://localhost:8888/file/f23400aae009403c9886439278638cfc.png', 'a748d23ca20db7182e2ff18fb0615ff2');
INSERT INTO `t_file` VALUES (38, '配音社.png', 'png', 9, 'http://localhost:8888/file/c5584016602b4368947115c4cef67fd2.png', '2d086017f5ef4edb28dd7e474f4de2f6');
INSERT INTO `t_file` VALUES (39, '棋社.png', 'png', 22, 'http://localhost:8888/file/11d37f7b023446e3bf760b9a66659524.png', '48fe2d2655734f1514a881b844436ced');
INSERT INTO `t_file` VALUES (40, '300.jpg', 'jpg', 19, 'http://localhost:8888/file/70cb27739c724102a53bfb3e8c839c9a.jpg', 'be6cf27b74443617c2847fee3b3c1ee7');
INSERT INTO `t_file` VALUES (41, '2.png', 'png', 10, 'http://localhost:8888/file/b185b474cc024bdf9148d62cc8b0103b.png', 'a7dd7975dc25ab19a231842e7e3bb49f');
INSERT INTO `t_file` VALUES (42, '300.jpg', 'jpg', 19, 'http://localhost:8888/file/70cb27739c724102a53bfb3e8c839c9a.jpg', 'be6cf27b74443617c2847fee3b3c1ee7');
INSERT INTO `t_file` VALUES (43, '1.png', 'png', 10, 'http://localhost:8888/file/b185b474cc024bdf9148d62cc8b0103b.png', 'a7dd7975dc25ab19a231842e7e3bb49f');

-- ----------------------------
-- Table structure for t_member
-- ----------------------------
DROP TABLE IF EXISTS `t_member`;
CREATE TABLE `t_member`  (
  `uid` int(11) NOT NULL,
  `cid` int(11) NOT NULL,
  `member_role` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`uid`, `cid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_member
-- ----------------------------

-- ----------------------------
-- Table structure for t_type
-- ----------------------------
DROP TABLE IF EXISTS `t_type`;
CREATE TABLE `t_type`  (
  `tid` int(11) NOT NULL AUTO_INCREMENT,
  `type_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type_remark` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  PRIMARY KEY (`tid`) USING BTREE,
  UNIQUE INDEX `type_name`(`type_name` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_type
-- ----------------------------
INSERT INTO `t_type` VALUES (1, '专业学术类', '专业学术类的社团是高校巩固和扩充本专业学术领域知识的另一个阵地。顾名思义就是和学校的专业有所挂钩。此类社团紧紧围绕着专业课开展学术探讨、学术交流和学术咨询活动，如高校的英语协会、大学生教育协会、大学生心理协会、方言文化研究交流协会等，但社团的活动形式较为单一，活动内容过于专业，无法吸引更多的学生参加，因此，一般规模不大。');
INSERT INTO `t_type` VALUES (2, '艺术兴趣类', '艺术兴趣类社团创办的基础是社员有着共同的兴趣爱好和艺术追求，这类社团的目的在于发展自己的兴趣爱好，在专业学习之余，一起组织并体验参加活动的乐趣，丰富校园文化生活。艺术兴趣类的社团是高校学生社团的主力军，数量大，种类多，活动形式多样。一方面学生有着不同的兴趣，他们可以依据自己的兴趣创办不不同的兴趣类社团；艺术兴趣类社团满足了学生的精神需求，也是大部分学生所选。');
INSERT INTO `t_type` VALUES (3, '体育健身类', '体育健身类社团是具有相同体育活动兴趣的同学、为提高自己的该项技能自发组织的体育活动类社团，其活动比较注重趣味性，一般以举办全校性比赛及定期集中训练为主。它是高校引导着学生加强健身意识、增强学生体质的有效载体，此类社团深受体育爱好者的喜爱。');
INSERT INTO `t_type` VALUES (4, '公益服务类', '公益服务类社团主要是以进行一些社会服务、实践公益类活动为内容而结成的学生社团。公益服务类社团是校园文化建设的重要组成部分，开辟了高校思想政治教育工作的新平台，有利于大学生道德思想水平的提高。社团活动的举行主要是利用大学生的课余和闲暇时间进行环境保护、义务劳动、献血、扶贫、支教、社会调查等社会公益服务活动。');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `uid` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `name` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '真实姓名',
  `sex` int(20) NULL DEFAULT NULL COMMENT '性别',
  `tel` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话',
  `email` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `type` int(11) NULL DEFAULT NULL COMMENT '账号类型',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '头像',
  PRIMARY KEY (`uid`) USING BTREE,
  UNIQUE INDEX `username`(`username` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, 'admin', '123456', '肖海冰', 1, '18250172726', '1434361044@qq.com', 1, 'http://localhost:8888/file/70cb27739c724102a53bfb3e8c839c9a.jpg');
INSERT INTO `t_user` VALUES (3, 'sun', '123456', '孙悟空', 1, '18250172726', '1434361044@qq.com', 3, 'http://localhost:8888/file/70cb27739c724102a53bfb3e8c839c9a.jpg');
INSERT INTO `t_user` VALUES (4, 'zhu', '123456', '猪八戒', 1, '13314958841', 'zhubajie@163.com', 2, 'http://localhost:8888/file/b185b474cc024bdf9148d62cc8b0103b.png');
INSERT INTO `t_user` VALUES (5, 'zhangsan', '123456', '张三', 2, '123', 'zhangsan_2001@qq.com', 2, 'http://localhost:8888/file/70cb27739c724102a53bfb3e8c839c9a.jpg');
INSERT INTO `t_user` VALUES (8, 'asdaisodjao', '123456', '笑死我了', 1, '12541236', '21415', 3, 'http://localhost:8888/file/70cb27739c724102a53bfb3e8c839c9a.jpg');
INSERT INTO `t_user` VALUES (9, '512341', '123456', '可莉', 2, '18250172726', '156as465s@163.com', 1, 'http://localhost:8888/file/70cb27739c724102a53bfb3e8c839c9a.jpg');
INSERT INTO `t_user` VALUES (10, 'student', '123456', '肖海冰', 1, '18816597842', '1265165162@163.com', 3, 'http://localhost:8888/file/70cb27739c724102a53bfb3e8c839c9a.jpg');
INSERT INTO `t_user` VALUES (11, 'studnet1', '123456', '肖海冰', 1, '18250172726', '1434361044@qq.com', 3, 'http://localhost:8888/file/b185b474cc024bdf9148d62cc8b0103b.png');

SET FOREIGN_KEY_CHECKS = 1;
