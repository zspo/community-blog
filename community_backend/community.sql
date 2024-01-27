-- MySQL dump 10.13  Distrib 8.0.23, for macos10.15 (x86_64)
--
-- Host: localhost    Database: community
-- ------------------------------------------------------
-- Server version	8.0.23

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `bms_billboard`
--

DROP TABLE IF EXISTS `bms_billboard`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bms_billboard` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '公告',
  `create_time` datetime DEFAULT NULL COMMENT '公告时间',
  `show` tinyint(1) DEFAULT NULL COMMENT '1：展示中，0：过期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='全站公告';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bms_billboard`
--

LOCK TABLES `bms_billboard` WRITE;
/*!40000 ALTER TABLE `bms_billboard` DISABLE KEYS */;
INSERT INTO `bms_billboard` VALUES (2,'R1.0 开始已实现护眼模式 ,妈妈再也不用担心我的眼睛了。','2020-11-19 17:16:19',0),(4,'系统已更新至最新版1.0.1',NULL,1);
/*!40000 ALTER TABLE `bms_billboard` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bms_category`
--

DROP TABLE IF EXISTS `bms_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bms_category` (
  `category_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '一级分类ID',
  `category_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '//一级分类名称',
  `subcategory_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '二级分类ID',
  `subcategory_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '//二级分类名称',
  `create_time` datetime DEFAULT NULL COMMENT '//创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '//更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bms_category`
--

LOCK TABLES `bms_category` WRITE;
/*!40000 ALTER TABLE `bms_category` DISABLE KEYS */;
INSERT INTO `bms_category` VALUES ('101','机器学习','2101002','深度学习','2021-02-28 16:06:41','2021-02-28 16:06:43'),('101','机器学习','2101001','机器学习','2021-02-28 16:06:41','2021-02-28 16:06:43'),('102','编程语言','2102001','Java','2021-02-28 16:07:00','2021-02-28 16:07:02'),('102','编程语言','2102002','C++','2021-02-28 16:07:00','2021-02-28 16:07:02'),('102','编程语言','2102003','Python','2021-02-28 16:07:00','2021-02-28 16:07:02'),('103','生活掠影','2103001','摄影','2021-02-28 16:07:19','2021-02-28 16:07:21'),('103','生活掠影','2103002','旅行','2021-02-28 16:07:19','2021-02-28 16:07:21'),('103','生活掠影','2103003','美食','2021-02-28 16:07:19','2021-02-28 16:07:21'),('104','其他','2104001','看一看','2021-02-28 16:07:25','2021-02-28 16:07:28'),('101','机器学习','2101003','自然语言处理','2021-02-28 16:09:17','2021-02-28 16:09:20'),('101','机器学习','2101004','广告推荐','2021-02-28 16:09:54','2021-02-28 16:09:56');
/*!40000 ALTER TABLE `bms_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bms_comment`
--

DROP TABLE IF EXISTS `bms_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bms_comment` (
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `content` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '内容',
  `user_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '作者ID',
  `topic_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'topic_id',
  `like` int NOT NULL DEFAULT '0' COMMENT '点赞统计',
  `reply_id` varchar(20) DEFAULT NULL COMMENT '//回复用户id',
  `parent_id` varchar(20) DEFAULT NULL COMMENT '//父评论id',
  `create_time` datetime NOT NULL COMMENT '发布时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='评论表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bms_comment`
--

LOCK TABLES `bms_comment` WRITE;
/*!40000 ALTER TABLE `bms_comment` DISABLE KEYS */;
INSERT INTO `bms_comment` VALUES ('1','测试回复功能','1349618748226658305','1365686365121921025',2,NULL,NULL,'2021-03-01 08:44:26',NULL),('1370611790042329090','ces','1370307364484218881','1370309856068567042',0,NULL,NULL,'2021-03-13 13:44:50',NULL),('1370611921546342401','@冰河映寒星 回复','1370307364484218881','1370309856068567042',0,'1370307364484218881','1370611790042329090','2021-03-13 13:45:21',NULL),('1370612418252599297','@冰河映寒星 123','1370307364484218881','1370309856068567042',0,'1370307364484218881','1370611790042329090','2021-03-13 13:47:20',NULL),('1370613126318223361','@冰河映寒星 123','1370307364484218881','1370309856068567042',0,'1370307364484218881','1370611790042329090','2021-03-13 13:50:09',NULL),('1370616093415010305','231','1370307364484218881','1370309856068567042',0,NULL,NULL,'2021-03-13 14:01:56',NULL),('1370617884090343426',' 124','1370307364484218881','1370309856068567042',0,'1370307364484218881','1370611790042329090','2021-03-13 14:09:03',NULL),('1370617922443059201','ceewegag','1370307364484218881','1370309856068567042',0,'1370307364484218881','1370611790042329090','2021-03-13 14:09:12',NULL),('1370618012448628737',' ceewegag afwef ','1370307364484218881','1370309856068567042',0,'1370307364484218881','1370611790042329090','2021-03-13 14:09:34',NULL),('1370618175158296577','测试','1370307364484218881','1370309856068567042',0,'1370307364484218881','1370611790042329090','2021-03-13 14:10:12',NULL),('1370618227113140226','发  发生发啊发 ','1370307364484218881','1370309856068567042',0,'1370307364484218881','1370611790042329090','2021-03-13 14:10:25',NULL),('1370618267466539010','发算法发发生','1370307364484218881','1370309856068567042',0,NULL,NULL,'2021-03-13 14:10:34',NULL),('1370618963733516289','测试空格 ','1370307364484218881','1370309856068567042',0,NULL,NULL,'2021-03-13 14:13:20',NULL),('1370619117068881922','测试 测试空格 ','1370307364484218881','1370309856068567042',0,NULL,NULL,'2021-03-13 14:13:57',NULL),('2','测试回复评论','1365678250561294338','1365686365121921025',4,'1349618748226658305','1','2021-03-01 08:46:46',NULL),('3','测试回复功能123113','1349618748226658305','1365686365121921025',4,NULL,NULL,'2021-03-01 08:46:26',NULL),('4','fasfsfsffwef','1349618748226658305','1365686365121921025',2,NULL,NULL,'2021-03-01 08:48:26',NULL),('5','asfsafsfdsf','1365678250561294338','1365686365121921025',10,'1349618748226658305','4','2021-03-01 08:48:26',NULL);
/*!40000 ALTER TABLE `bms_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bms_follow`
--

DROP TABLE IF EXISTS `bms_follow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bms_follow` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `parent_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '被关注人ID',
  `follower_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '关注人ID',
  `create_time` datetime DEFAULT NULL COMMENT '关注时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=131 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='用户关注';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bms_follow`
--

LOCK TABLES `bms_follow` WRITE;
/*!40000 ALTER TABLE `bms_follow` DISABLE KEYS */;
INSERT INTO `bms_follow` VALUES (65,'1329723594994229250','1317498859501797378','2020-11-19 17:16:19'),(85,'1332912847614083073','1332636310897664002','2020-11-19 17:16:19'),(129,'1349290158897311745','1349618748226658305','2020-11-19 17:16:19');
/*!40000 ALTER TABLE `bms_follow` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bms_photo`
--

DROP TABLE IF EXISTS `bms_photo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bms_photo` (
  `id` varchar(20) NOT NULL COMMENT 'id',
  `img_url` varchar(255) NOT NULL COMMENT '图片链接',
  `topic_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '文章id',
  `like` int NOT NULL COMMENT '点赞统计',
  `create_time` datetime NOT NULL COMMENT '发布时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bms_photo`
--

LOCK TABLES `bms_photo` WRITE;
/*!40000 ALTER TABLE `bms_photo` DISABLE KEYS */;
INSERT INTO `bms_photo` VALUES ('1376904990910562305','https://img0.baidu.com/it/u=2074248317,741471452&fm=253&fmt=auto&app=138&f=JPEG?w=231&h=500','1376904990637932546',0,'2021-03-30 22:31:46'),('1376904990939922433','https://img1.baidu.com/it/u=1890390320,3399874998&fm=253&fmt=auto&app=120&f=JPEG?w=1422&h=800','1376904990637932546',0,'2021-03-30 22:31:46'),('1376934826525777921','https://img1.baidu.com/it/u=1586503404,2024787974&fm=253&fmt=auto&app=120&f=JPEG?w=1280&h=800','1376934826404143105',0,'2021-03-31 00:30:20'),('1376934826546749441','https://img1.baidu.com/it/u=716638254,3920932970&fm=253&fmt=auto&app=138&f=JPEG?w=889&h=500','1376934826404143105',0,'2021-03-31 00:30:20'),('1376935741303513090','https://img2.baidu.com/it/u=1093557123,1839329425&fm=253&fmt=auto&app=120&f=JPEG?w=1140&h=760','1376935741194461186',0,'2021-03-31 00:33:58'),('1376935741328678914','https://img0.baidu.com/it/u=3182669744,3015526205&fm=253&fmt=auto&app=138&f=JPEG?w=889&h=500','1376935741194461186',0,'2021-03-31 00:33:58'),('1446861926996824066','https://img2.baidu.com/it/u=3359649605,3626874592&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=752','1446861926879383553',0,'2021-10-09 23:35:39');
/*!40000 ALTER TABLE `bms_photo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bms_post`
--

DROP TABLE IF EXISTS `bms_post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bms_post` (
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '标题',
  `cover` varchar(255) DEFAULT NULL COMMENT '文章封面图',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT 'markdown内容',
  `user_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '作者ID',
  `category_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '一级分类ID',
  `subcategory_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '二级分类ID',
  `comments` int NOT NULL DEFAULT '0' COMMENT '评论统计',
  `like` int NOT NULL DEFAULT '0' COMMENT '点赞统计',
  `share` int NOT NULL DEFAULT '0' COMMENT '分享统计',
  `collects` int NOT NULL DEFAULT '0' COMMENT '收藏统计',
  `view` int NOT NULL DEFAULT '0' COMMENT '浏览统计',
  `top` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否置顶，1-是，0-否',
  `essence` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否加精，1-是，0-否',
  `section_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '专栏ID',
  `create_time` datetime NOT NULL COMMENT '发布时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  UNIQUE KEY `title` (`title`) USING BTREE,
  KEY `user_id` (`user_id`) USING BTREE,
  KEY `create_time` (`create_time`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='话题表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bms_post`
--

LOCK TABLES `bms_post` WRITE;
/*!40000 ALTER TABLE `bms_post` DISABLE KEYS */;
INSERT INTO `bms_post` VALUES ('1333447953558765569','1',NULL,'12\n2\n\n','1349290158897311745','101','2101001',5,10,0,0,78,_binary '\0',_binary '\0','11','2020-12-01 00:29:01','2020-12-03 23:56:51'),('1349362401438392322','2021 健康，快乐',NULL,'2021的`FLAG`\n\n1. 技能进步\n2. 没有烦恼\n3. 发财 :smile:\n\n','1349290158897311745','101','2101001',0,3,2,3,31,_binary '\0',_binary '\0','11','2021-01-13 22:27:21','2021-01-14 17:30:13'),('1366021783747002369','C++学习 测试一篇文章',NULL,'这是一篇测试文章\n\n','1365678250561294338','102','2102002',0,0,0,0,22,_binary '\0',_binary '\0','','2021-02-28 21:45:47',NULL),('1334481725322297346','hello，spring-security',NULL,':hibiscus: spring-security\n\n','1349290158897311745','101','2101003',10,3,11,3,57,_binary '\0',_binary '\0','11','2020-12-03 20:56:51',NULL),('1376203990499291138','南京记录',NULL,'南京之行\n123，456，789\n112233\n![](https://img-blog.csdnimg.cn/20210208150659161.png)\n![](https://img-blog.csdnimg.cn/20210208151656979.jpeg)\n![](https://img-blog.csdnimg.cn/20210208173436952.jpg)\n\n','1370307364484218881','103','2103001',0,0,0,0,17,_binary '\0',_binary '\0','','2021-03-29 00:06:15',NULL),('1332650453142827009','哈哈哈，helloworld',NULL,'这是第一篇哦\n\n> hi :handshake: 你好\n\n`hello world`\n\n:+1: 很好\n','1349290158897311745','101','2101004',0,20,0,3,32,_binary '\0',_binary '\0','11','2020-11-28 19:40:02','2020-11-28 19:46:39'),('1333432347031646209','哈哈哈，换了个dark主题',NULL,'主题更换为Dark\n\n','1349290158897311745','102','2102001',20,0,0,0,6,_binary '\0',_binary '\0','11','2020-11-30 23:27:00',NULL),('1333668258587750401','嘿嘿，测试一下啊',NULL,'大家好\n`Hello everyone!`\n\n\n\n','1349290158897311745','102','2102001',0,2,3,8,10,_binary '\0',_binary '\0','11','2020-12-01 15:04:26','2020-12-01 16:49:14'),('1376899125008613377','图片测试入库',NULL,'测试入库\n\n![](http://img.aisharespace.com/20210208173436952.jpg)\n![](http://img.aisharespace.com/IMG_0655-20210329233118997.jpg)\n\n','1370307364484218881','103','2103001',0,0,0,0,7,_binary '\0',_binary '\0','','2021-03-30 22:08:28',NULL),('1376900162880749570','图片测试入库2','http://img.aisharespace.com/IMG_0655-20210329233118997.jpg','图片测试入库\n\n![](http://img.aisharespace.com/20210208173436952.jpg)\n![](http://img.aisharespace.com/IMG_0655-20210329233118997.jpg)\n\n','1370307364484218881','103','2103003',0,0,0,0,21,_binary '\0',_binary '\0','','2021-03-30 22:12:35',NULL),('1332682473151635458','我要发财',NULL,'2021 冲冲冲！！！\n\n','1349290158897311745','102','2102001',6,7,3,8,94,_binary '\0',_binary '\0','21','2020-11-28 21:47:16','2020-11-30 19:40:22'),('1333695976536748034','最新版本介绍，同步更新！',NULL,'<p align=center>一款基于SpringBoot构建的智慧社区系统</p>\n\n<p align=center>\n<a href=\"https://github.com/1020317774/rhapsody-admin/stargazers\"><img alt=\"GitHub release\" src=\"https://img.shields.io/github/release/1020317774/rhapsody-admin?style=flat-square\"></a>\n<a href=\"https://github.com/1020317774/rhapsody-admin/blob/main/LICENSE\"><img alt=\"GitHub license\" src=\"https://img.shields.io/github/license/1020317774/rhapsody-admin\"></a>\n</p>\n\n### Hi there :wave:\n\n<!--\n**1020317774/1020317774** is a :sparkles: _special_ :sparkles: repository because its `README.md` (this file) appears on your GitHub profile.\n\nHere are some ideas to get you started:\n\n- :telescope: I’m currently working on ...\n- :seedling: I’m currently learning ...\n- :dancers: I’m looking to collaborate on ...\n- :thinking: I’m looking for help with ...\n- :speech_balloon: Ask me about ...\n- :mailbox: How to reach me: ...\n- :smile: Pronouns: ...\n- :zap: Fun fact: ...\n-->\n\n[![1020317774\'s github stats](https://github-readme-stats.vercel.app/api?username=1020317774&show_icons=true&count_private=true)](https://github.com/1020317774)\n\n[![Top Langs](https://github-readme-stats.vercel.app/api/top-langs/?username=1020317774&layout=compact)](https://github.com/1020317774)\n---------\n\n> 作者：王一晨\n> github：[https://github.com/1020317774](https://github.com/1020317774)\n\n## 技术栈\n\n- [x] SpringBoot 2.X\n- [x] Mysql 8.X\n- [x] Mybatis\n- [x] MybatisPlus\n- [x] Redis\n- [x] Jwt\n- [x] FastJson\n- [x] Hutool\n- [x] Lombok\n- [ ] ElasticSearch\n\n……\n\n## 安装指导\n\n- 克隆\n\n```java\ngit clone https://github.com/1020317774/rhapsody-admin.git\n```\n\n- 修改`application.properties`选择环境\n- 修改多环境配置中的redis参数和数据库\n- 启动`BootApplication`\n- 访问[`http://127.0.0.1:10000`](http://127.0.0.1:10000)\n\n','1349290158897311745','102','2102003',6,7,3,8,55,_binary '',_binary '','21','2020-12-01 16:54:34','2020-12-01 17:05:00'),('1333676096156528641','测试',NULL,'测试\n\n','1349290158897311745','102','2102003',6,7,3,20,38,_binary '\0',_binary '\0','21','2020-12-01 15:35:34',NULL),('1446861926879383553','测试cover','http://img.aisharespace.com/IMG_8802.jpg','![](http://img.aisharespace.com/IMG_8802.jpg)\n\n','1370307364484218881','103','2103002',0,0,0,0,59,_binary '\0',_binary '\0','','2021-10-09 23:35:39',NULL),('1376555839605665793','测试detail',NULL,'春在白塔湖公园，放眼望去，满园的春色比来来往往的人流还要繁华，偶然看到一个穿着和服的小姐姐在摆拍，小姐姐依靠在樱花树下，摄影师半趴在地上，在 姑娘如银月般的笑容中，轻按着快门。\n\n\n\n- ![img](http://img.aisharespace.com/IMG_0655-20210329232757810.jpg)\n\n- ![img](http://img.aisharespace.com/IMG_0658.jpg)\n\n春在白塔湖公园，放眼望去，满园的春色比来来往往的人流还要繁华，偶然看到一个穿着和服的小姐姐在摆拍，小姐姐依靠在樱花树下，摄影师半趴在地上，在 姑娘如银月般的笑容中，轻按着快门。\n\n\n\n- ![img](http://img.aisharespace.com/IMG_0655-20210329232757810.jpg)\n\n- ![img](http://img.aisharespace.com/IMG_0658.jpg)\n\n春在白塔湖公园，放眼望去，满园的春色比来来往往的人流还要繁华，偶然看到一个穿着和服的小姐姐在摆拍，小姐姐依靠在樱花树下，摄影师半趴在地上，在 姑娘如银月般的笑容中，轻按着快门。\n\n\n\n- ![img](http://img.aisharespace.com/IMG_0655-20210329232757810.jpg)\n\n- ![img](http://img.aisharespace.com/IMG_0658.jpg)\n\n春在白塔湖公园，放眼望去，满园的春色比来来往往的人流还要繁华，偶然看到一个穿着和服的小姐姐在摆拍，小姐姐依靠在樱花树下，摄影师半趴在地上，在 姑娘如银月般的笑容中，轻按着快门。\n\n\n\n- ![img](http://img.aisharespace.com/IMG_0655-20210329232757810.jpg)\n\n- ![img](http://img.aisharespace.com/IMG_0658.jpg)\n\n','1370307364484218881','103','2103002',0,0,0,0,12,_binary '\0',_binary '\0','','2021-03-29 23:24:22',NULL),('1376206355252080642','测试life detail',NULL,'测试侧视侧视文贼发顺丰爱上\n\n','1370307364484218881','103','2103003',0,0,0,0,11,_binary '\0',_binary '\0','','2021-03-29 00:15:38',NULL),('1370309856068567042','测试一篇博客',NULL,'测试一下\n\n[toc]\n\n# 一级\n\n# erji\n\n### ji\n\n##### fasfa\n\n段落自动锁进段落自动锁进段落自动锁进段落自动锁进段落自动锁进段落自动锁进段落自动锁进段落自动锁进段落自动锁进段落自动锁进段落自动锁进\n\n','1370307364484218881','101','2101003',8,0,0,0,88,_binary '\0',_binary '\0','','2021-03-12 17:45:03','2021-03-13 16:04:52'),('1376901382718570498','测试图片入库3','http://img.aisharespace.com/20210208173436952.jpg','测试图片入库\n\n![](http://img.aisharespace.com/20210208173436952.jpg)\n\n![](http://img.aisharespace.com/IMG_0655-20210329233118997.jpg)\n\n','1370307364484218881','103','2103002',0,0,0,0,61,_binary '\0',_binary '\0','','2021-03-30 22:17:26',NULL),('1365686365121921025','测试文章分类标签',NULL,'settsetststs\n\n','1365678250561294338','103','2103002',0,0,0,0,91,_binary '\0',_binary '\0','','2021-02-27 23:32:57',NULL),('1374023202521382913','测试略影',NULL,'fasfsfs\n\n','1370307364484218881','103','2103003',0,0,0,0,11,_binary '\0',_binary '\0','','2021-03-22 23:40:34',NULL),('1332681213400817665','聚合查询并统计',NULL,'* [x] SQL：\n\n```sql\nSELECT s.*,\nCOUNT(t.id) AS topics\nFROM section s\nLEFT JOIN topic t\nON s.id = t.section_id\nGROUP BY s.title\n```\n\n','1349290158897311745','103','2103002',6,7,3,20,57,_binary '\0',_binary '\0','21','2020-11-28 21:42:16','2020-11-29 15:00:42'),('1335149981733449729','视频嵌入',NULL,':+1:\n\n[https://www.bilibili.com/video/BV1w64y1f7w3](https://www.bilibili.com/video/BV1w64y1f7w3)\n\n[1](https://www.bilibili.com/video/BV1tp4y1x72w)\n\n```\n.vditor-reset pre > code\n```\n\n```\npublic class HelloWorld {\n\npublic static void main(String[] args) {\n    System.out.println(\"Hello World!\");\n}\n}\n```\n\n','1349290158897311745','103','2103002',6,7,3,20,44,_binary '\0',_binary '\0','21','2020-12-05 17:12:16','2021-01-14 13:06:16');
/*!40000 ALTER TABLE `bms_post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bms_post_tag`
--

DROP TABLE IF EXISTS `bms_post_tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bms_post_tag` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `tag_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标签ID',
  `topic_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '话题ID',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `tag_id` (`tag_id`) USING BTREE,
  KEY `topic_id` (`topic_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=87 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='话题-标签 中间表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bms_post_tag`
--

LOCK TABLES `bms_post_tag` WRITE;
/*!40000 ALTER TABLE `bms_post_tag` DISABLE KEYS */;
INSERT INTO `bms_post_tag` VALUES (36,'1332650453377708034','1332650453142827009'),(37,'1332681213568589825','1332681213400817665'),(38,'1332681213631504385','1332681213400817665'),(39,'1332682473218744321','1332682473151635458'),(40,'1332913064463794178','1332913064396685314'),(41,'1332913064530903041','1332913064396685314'),(42,'1333432347107143681','1333432347031646209'),(43,'1333432347107143682','1333432347031646209'),(44,'1333447953697177602','1333447953558765569'),(45,'1332913064463794178','1333668258587750401'),(46,'1333676096320106498','1333676096156528641'),(47,'1333695976742268930','1333695976536748034'),(48,'1334481725519429634','1334481725322297346'),(49,'1333447953697177602','1335149981733449729'),(50,'1349362401597775874','1349362401438392322'),(51,'1349631541306732545','1349631541260595202'),(55,'1332681213568589825','1365685903777841154'),(56,'1332682473218744321','1365685903777841154'),(57,'1333432347107143681','1365685903777841154'),(58,'1332681213568589825','1365686365121921025'),(59,'1332682473218744321','1365686365121921025'),(60,'1333432347107143681','1365686365121921025'),(64,'1333447953697177602','1366015595676389378'),(65,'1332681213568589825','1366015595676389378'),(66,'1366021783788945410','1366021783747002369'),(67,'1332681213568589825','1370309856068567042'),(68,'1332682473218744321','1370309856068567042'),(69,'1332913064530903041','1370309856068567042'),(70,'1333432347107143681','1374023202521382913'),(71,'1332913064463794178','1374023202521382913'),(72,'1332650453377708034','1376203990499291138'),(73,'1332681213631504385','1376203990499291138'),(74,'1332682473218744321','1376206355252080642'),(75,'1332913064530903041','1376206355252080642'),(76,'1332681213631504385','1376555839605665793'),(77,'1332913064463794178','1376899125008613377'),(78,'1333432347107143681','1376900162880749570'),(79,'1332913064463794178','1376901382718570498'),(86,'1332681213631504385','1446861926879383553');
/*!40000 ALTER TABLE `bms_post_tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bms_promotion`
--

DROP TABLE IF EXISTS `bms_promotion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bms_promotion` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '广告标题',
  `link` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '广告链接',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '说明',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='广告推广表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bms_promotion`
--

LOCK TABLES `bms_promotion` WRITE;
/*!40000 ALTER TABLE `bms_promotion` DISABLE KEYS */;
INSERT INTO `bms_promotion` VALUES (1,'百度','https://baidu.com','百度');
/*!40000 ALTER TABLE `bms_promotion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bms_tag`
--

DROP TABLE IF EXISTS `bms_tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bms_tag` (
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标签ID',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '标签',
  `topic_count` int NOT NULL DEFAULT '0' COMMENT '关联话题',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `name` (`name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='标签表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bms_tag`
--

LOCK TABLES `bms_tag` WRITE;
/*!40000 ALTER TABLE `bms_tag` DISABLE KEYS */;
INSERT INTO `bms_tag` VALUES ('1332650453377708034','java',3),('1332681213568589825','css',7),('1332681213631504385','mongodb',4),('1332682473218744321','python',8),('1332913064463794178','vue',5),('1332913064530903041','react',4),('1333432347107143681','node',5),('1333432347107143682','mysql',1),('1333447953697177602','flask',3),('1333676096320106498','spring',1),('1333695976742268930','django',1),('1334481725519429634','security',1),('1349362401597775874','tensorflow',1),('1349631541306732545','pytorch',1),('1365875312649244673','新标签测试',1),('1366021783788945410','c++',1);
/*!40000 ALTER TABLE `bms_tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bms_tip`
--

DROP TABLE IF EXISTS `bms_tip`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bms_tip` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `content` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '内容',
  `author` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '作者',
  `type` tinyint NOT NULL COMMENT '1：使用，0：过期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=24864 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='每日赠言';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bms_tip`
--

LOCK TABLES `bms_tip` WRITE;
/*!40000 ALTER TABLE `bms_tip` DISABLE KEYS */;
INSERT INTO `bms_tip` VALUES (1,'多锉出快锯，多做长知识。','佚名',1),(2,'未来总留着什么给对它抱有信心的人。','佚名',1),(3,'一个人的智慧不够用，两个人的智慧用不完。','谚语',1),(4,'十个指头按不住十个跳蚤','傣族',1),(5,'言不信者，行不果。','墨子',1),(6,'攀援而登，箕踞而遨，则几数州之土壤，皆在衽席之下。','柳宗元',1),(7,'美德大都包含在良好的习惯之内。','帕利克',1),(8,'人有不及，可以情恕。','《晋书》',1),(9,'明·吴惟顺','法不传六耳',1),(10,'真正的朋友应该说真话，不管那话多么尖锐。','奥斯特洛夫斯基',1),(11,'时间是一切财富中最宝贵的财富。','德奥弗拉斯多',1),(12,'看人下菜碟','民谚',1),(13,'如果不是怕别人反感，女人决不会保持完整的严肃。','拉罗什福科',1),(14,'爱是春暖花开时对你满满的笑意','佚名',1),(15,'希望是坚韧的拐杖，忍耐是旅行袋，携带它们，人可以登上永恒之旅。','罗素',1),(18,'天国般的幸福，存在于对真爱的希望。','佚名',1),(19,'我们现在必须完全保持党的纪律，否则一切都会陷入污泥中。','马克思',1),(20,'在科学上没有平坦的大道，只有不畏劳苦沿着陡峭山路攀登的人，才有希望达到光辉的顶点。','马克思',1),(21,'懒惰的马嫌路远','蒙古',1),(22,'别忘记热水是由冷水烧成的','非洲',1);
/*!40000 ALTER TABLE `bms_tip` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ums_user`
--

DROP TABLE IF EXISTS `ums_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ums_user` (
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户ID',
  `username` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '用户名',
  `alias` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户昵称',
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '密码',
  `avatar` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '头像',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '手机',
  `score` int NOT NULL DEFAULT '0' COMMENT '积分',
  `token` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT 'token',
  `bio` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '个人简介',
  `active` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否激活，1：是，0：否',
  `status` bit(1) DEFAULT b'1' COMMENT '状态，1：使用，0：停用',
  `role_id` int DEFAULT NULL COMMENT '用户角色',
  `create_time` datetime NOT NULL COMMENT '加入时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `user_name` (`username`) USING BTREE,
  KEY `user_email` (`email`) USING BTREE,
  KEY `user_create_time` (`create_time`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ums_user`
--

LOCK TABLES `ums_user` WRITE;
/*!40000 ALTER TABLE `ums_user` DISABLE KEYS */;
INSERT INTO `ums_user` VALUES ('1349290158897311745','admin_rm','admin','$2a$10$8qx711TBg/2hxfL7N.sxf.0ROMhR/iuPhQx33IFqGd7PLgt5nGJTO','https://s3.ax1x.com/2020/12/01/DfHNo4.jpg','23456@qq.com',NULL,2,'','自由职业者',_binary '',_binary '',NULL,'2021-01-13 17:40:17',NULL),('1349618748226658305','zhangsan','zhangsan','$2a$10$7K3yYv8sMV5Xsc2facXTcuyDo8JQ4FJHvjZ7qtWYcJdei3Q6Fvqdm','https://s3.ax1x.com/2020/12/01/DfHNo4.jpg','23456@qq.com',NULL,0,'','自由职业者',_binary '',_binary '',NULL,'2021-01-14 15:25:59',NULL),('1365678250561294338','admin_1','admin_1','f6fdffe48c908deb0f4c3bd36c032e72','https://s3.ax1x.com/2020/12/01/DfHNo4.jpg','afsf@qq.com',NULL,7,'','自由职业者',_binary '',_binary '',NULL,'2021-02-27 23:00:43',NULL),('1370307364484218881','admin','冰河映寒星','7d1f91e51b967826175722fa48d4b1ca','https://s3.ax1x.com/2020/12/01/DfHNo4.jpg','782575191@qq.com',NULL,15,'','自由职业者',_binary '',_binary '',NULL,'2021-03-12 17:35:09',NULL);
/*!40000 ALTER TABLE `ums_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-11-26 18:20:43
