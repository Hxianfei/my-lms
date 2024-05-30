/*
SQLyog Community v13.1.6 (64 bit)
MySQL - 8.1.0 : Database - my_lms_db
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`my_lms_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `my_lms_db`;

/*Table structure for table `book` */

DROP TABLE IF EXISTS `book`;

CREATE TABLE `book` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(50) NOT NULL COMMENT '书名',
  `author` varchar(20) NOT NULL COMMENT '作者',
  `isbn` varchar(20) NOT NULL COMMENT 'ISBN',
  `publisher` varchar(20) NOT NULL COMMENT '出版社',
  `publish_year` int NOT NULL COMMENT '出版年份',
  `quantity` int NOT NULL COMMENT '数量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `book` */

insert  into `book`(`id`,`title`,`author`,`isbn`,`publisher`,`publish_year`,`quantity`) values 
(1,'解忧杂货店','东野圭吾','978-7-5442-8837-6','南海出版公司',2014,38),
(2,'百年孤独','加西亚·马尔克斯','978-7-5327-5397-3','上海译文出版社',1984,31),
(3,'追风筝的人','卡勒德·胡赛尼','978-7-5339-2836-6','浙江文艺出版社',2003,46),
(5,'时间简史','史蒂芬·霍金','978-7-5633-4723-9','广西师范大学出版社',1988,26),
(6,'人类简史','尤瓦尔·赫拉利','978-7-5086-5910-7','中信出版集团',2014,40),
(7,'活着','余华','978-7-108-00724-3','生活·读书·新知三联书店',1993,35),
(8,'白夜行','东野圭吾','978-7-5399-5994-7','江苏文艺出版社',1999,55),
(9,'哈利·波特与魔法石','J.K.罗琳','978-7-5399-3999-2','人民文学出版社',1997,71),
(10,'1984','乔治·奥威尔','978-7-5447-3503-3','译林出版社',1949,66),
(12,'水虎转','黄先非','昆明理工大学','9999888877123',2021,40),
(13,'哈姆雷特','莎士比亚','978-7-101-00196-2','人民文学出版社',1603,250),
(14,'月亮与六便士','毛姆','978-7-5302-1262-2','北京十月文艺出版社',1919,269),
(15,'三体','刘慈欣','978-7-5502-7001-6','重庆出版社',2006,300),
(16,'人类群星闪耀时','斯蒂芬·茨威格','978-7-5447-1112-0','译林出版社',1927,298),
(17,'一个人的好天气','青山七惠','978-7-5442-5205-1','南海出版公司',2007,141),
(18,'麦田里的守望者','J.D. 塞林格','978-7-5327-3808-5','上海译文出版社',1951,224),
(19,'了不起的盖茨比','F.S. 菲茨杰拉德','978-7-5447-1378-5','译林出版社',1925,213),
(20,'简爱','夏洛蒂·勃朗特','978-7-0200-1028-5','人民文学出版社',1847,448),
(21,'百年法','罗伯特·查尔斯·威尔森','978-7-5502-3621-8','重庆出版社',2003,767),
(22,'解忧杂货店2','东野圭吾','978-7-5442-9876-5','南海出版公司',2018,336),
(23,'西游记','吴承恩','978-7-0200-4051-7','人民文学出版社',1592,860),
(24,'傲慢与偏见','简·奥斯汀','978-7-108-02030-5','生活·读书·新知三联书店',1813,384),
(25,'飘','玛格丽特·米切尔','978-7-5327-3300-9','上海译文出版社',1936,1032),
(26,'看不见的城市','伊塔洛·卡尔维诺','978-7-5447-2345-6','译林出版社',1972,208),
(27,'追忆似水年华','马塞尔·普鲁斯特','978-7-5327-4210-1','上海译文出版社',1913,700),
(28,'活着为了讲述','加西亚·马尔克斯','978-7-5302-1897-8','北京十月文艺出版社',2003,352),
(29,'美丽新世界','阿道司·赫胥黎','978-7-5502-5678-9','重庆出版社',1932,272),
(30,'罪与罚','陀思妥耶夫斯基','978-7-0200-2890-1','人民文学出版社',1866,480),
(31,'堂吉诃德','米格尔·德·塞万提斯','978-7-5302-0012-3','北京十月文艺出版社',1605,800),
(33,'book3','huagnxiang','昆工出版社','6666655555123',2012,5);

/*Table structure for table `book_category` */

DROP TABLE IF EXISTS `book_category`;

CREATE TABLE `book_category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `book_id` int NOT NULL COMMENT '图书id',
  `category_id` int NOT NULL COMMENT '类别id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `book_category` */

insert  into `book_category`(`id`,`book_id`,`category_id`) values 
(1,1,2),
(2,2,3),
(3,3,2),
(4,5,2),
(5,6,2),
(6,7,2),
(7,8,4),
(8,17,4);

/*Table structure for table `borrow` */

DROP TABLE IF EXISTS `borrow`;

CREATE TABLE `borrow` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL COMMENT '用户id',
  `book_id` int NOT NULL COMMENT '图书id',
  `borrow_time` datetime NOT NULL COMMENT '借书时间',
  `return_time` datetime DEFAULT NULL COMMENT '还书时间',
  `status` int NOT NULL DEFAULT '0' COMMENT '状态 0:未还 1:已还',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Table structure for table `category` */

DROP TABLE IF EXISTS `category`;

CREATE TABLE `category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL COMMENT '类别名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `category` */

insert  into `category`(`id`,`name`) values 
(2,'艺术'),
(3,'历史'),
(4,'数学');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL COMMENT '用户名',
  `password` varchar(128) NOT NULL COMMENT '密码',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(11) DEFAULT NULL COMMENT '手机号',
  `role` int NOT NULL DEFAULT '0' COMMENT '角色 0:普通用户 1:管理员',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `user` */

insert  into `user`(`id`,`username`,`password`,`email`,`phone`,`role`,`create_time`,`update_time`) values 
(1,'黄先非','e10adc3949ba59abbe56e057f20f883e','738659501@qq.com','15987212674',0,'2024-05-13 22:46:21','2024-05-29 15:30:21'),
(6,'Admin','e10adc3949ba59abbe56e057f20f883e','888899999@qq.com','18866669999',1,'2024-05-17 13:09:14','2024-05-30 16:02:21');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
