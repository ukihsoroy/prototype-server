-- --------------------------------------------------------
-- 主机:                           39.105.164.165
-- 服务器版本:                        5.7.26 - MySQL Community Server (GPL)
-- 服务器操作系统:                      Linux
-- HeidiSQL 版本:                  10.3.0.5780
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping database structure for sigma_server
CREATE DATABASE IF NOT EXISTS `sigma_server` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `sigma_server`;

-- 导出  表 sigma_server.t_department 结构
CREATE TABLE IF NOT EXISTS `t_department` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `code` varchar(50) NOT NULL COMMENT '部门编码，由系统统一生成，不可修改',
  `name` varchar(50) NOT NULL COMMENT '部门名称',
  `parent_code` varchar(50) NOT NULL COMMENT '父级部门编码',
  `disable` smallint(1) NOT NULL DEFAULT '0' COMMENT '数据状态：0-有效，1-删除',
  `version` bigint(20) NOT NULL DEFAULT '1' COMMENT '更新版本',
  `create_user` bigint(20) DEFAULT NULL COMMENT '创建用户',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `modified_user` bigint(20) DEFAULT NULL COMMENT '更新用户',
  `gmt_modified` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`),
  KEY `parent_code` (`parent_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='部门表';

-- 正在导出表  sigma_server.t_department 的数据：~0 rows (大约)
DELETE FROM `t_department`;
/*!40000 ALTER TABLE `t_department` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_department` ENABLE KEYS */;

-- 导出  表 sigma_server.t_department_user 结构
CREATE TABLE IF NOT EXISTS `t_department_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `department_code` varchar(50) NOT NULL COMMENT '部门编码，由系统统一生成，不可修改',
  `user_id` varchar(50) NOT NULL COMMENT '用户编码（UUID），由系统统一生成，不可修改',
  `enable` smallint(1) NOT NULL DEFAULT '0' COMMENT '数据状态：0-有效，1-删除',
  `disable` bigint(20) NOT NULL DEFAULT '1' COMMENT '更新版本',
  `create_user` bigint(20) DEFAULT NULL COMMENT '创建用户',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `modified_user` bigint(20) DEFAULT NULL COMMENT '更新用户',
  `gmt_modified` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `department_code` (`department_code`),
  KEY `user_code` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='部门用户关联表，事实表';

-- 正在导出表  sigma_server.t_department_user 的数据：~0 rows (大约)
DELETE FROM `t_department_user`;
/*!40000 ALTER TABLE `t_department_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_department_user` ENABLE KEYS */;

-- 导出  表 sigma_server.t_dict 结构
CREATE TABLE IF NOT EXISTS `t_dict` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `code` varchar(50) NOT NULL COMMENT '字典编码',
  `value` varchar(50) NOT NULL COMMENT '字典名称',
  `type` varchar(50) NOT NULL COMMENT '字典类型',
  `description` varchar(200) DEFAULT NULL COMMENT '描述',
  `disable` smallint(1) NOT NULL DEFAULT '0' COMMENT '数据状态：0-有效，1-删除',
  `version` bigint(20) NOT NULL DEFAULT '1' COMMENT '更新版本',
  `create_user` bigint(20) DEFAULT NULL COMMENT '创建用户',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `modified_user` bigint(20) DEFAULT NULL COMMENT '更新用户',
  `gmt_modified` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `dict_code` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='字典表';

-- 正在导出表  sigma_server.t_dict 的数据：~2 rows (大约)
DELETE FROM `t_dict`;
/*!40000 ALTER TABLE `t_dict` DISABLE KEYS */;
INSERT INTO `t_dict` (`id`, `code`, `value`, `type`, `description`, `disable`, `version`, `create_user`, `gmt_create`, `modified_user`, `gmt_modified`) VALUES
	(1, 'sms_template', 'SMS_172007235', 'login', '阿里云短信模板登录', 0, 1, NULL, NULL, NULL, NULL),
	(2, 'sms_template', 'SMS_172007235', 'register', '阿里云短信注册模板', 0, 1, NULL, NULL, NULL, NULL);
/*!40000 ALTER TABLE `t_dict` ENABLE KEYS */;

-- 导出  表 sigma_server.t_menu 结构
CREATE TABLE IF NOT EXISTS `t_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(50) NOT NULL COMMENT '菜单名称',
  `path` varchar(50) NOT NULL COMMENT '菜单路由规则索引',
  `component` varchar(50) NOT NULL COMMENT '命名视图组件',
  `redirect` varchar(50) DEFAULT NULL COMMENT '路由重定向',
  `meta_json` json NOT NULL COMMENT '菜单元数据。 name: 路由名称，icon: 路由图标',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '上级菜单编码，由系统统一生成，不可修改',
  `disable` smallint(1) NOT NULL DEFAULT '0' COMMENT '数据状态：0-有效，1-删除',
  `version` bigint(20) NOT NULL DEFAULT '1' COMMENT '更新版本',
  `create_user` bigint(20) DEFAULT NULL COMMENT '创建用户',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `modified_user` bigint(20) DEFAULT NULL COMMENT '更新用户',
  `gmt_modified` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='菜单表，维度表';

-- 正在导出表  sigma_server.t_menu 的数据：~4 rows (大约)
DELETE FROM `t_menu`;
/*!40000 ALTER TABLE `t_menu` DISABLE KEYS */;
INSERT INTO `t_menu` (`id`, `name`, `path`, `component`, `redirect`, `meta_json`, `parent_id`, `disable`, `version`, `create_user`, `gmt_create`, `modified_user`, `gmt_modified`) VALUES
	(1, '系统操作', '/system', '/system', NULL, '{"icon": "#", "title": "系统操作"}', NULL, 0, 1, NULL, NULL, NULL, '2019-07-08 21:52:33'),
	(2, '用户管理', '/system/user', '/system/user', NULL, '{"icon": "#", "title": "用户管理"}', 1, 0, 1, NULL, NULL, NULL, '2019-07-08 22:27:45'),
	(3, '权限管理', '/system/role', '/system/role', NULL, '{"icon": "#", "title": "权限管理"}', 1, 0, 1, NULL, NULL, NULL, NULL),
	(4, '菜单管理', '/system/menu', '/system/menu', NULL, '{"icon": "#", "title": "菜单管理"}', 1, 0, 1, NULL, NULL, NULL, NULL);
/*!40000 ALTER TABLE `t_menu` ENABLE KEYS */;

-- 导出  表 sigma_server.t_request_log 结构
CREATE TABLE IF NOT EXISTS `t_request_log` (
  `id` varchar(32) NOT NULL COMMENT '主键ID，使用UUID',
  `user_id` varchar(32) DEFAULT NULL COMMENT '请求用户ID',
  `ip_addr` varchar(32) NOT NULL COMMENT '用户ip信息',
  `request_time` datetime NOT NULL COMMENT '请求时间',
  `request_link` varchar(50) NOT NULL COMMENT '请求url',
  `disable` smallint(1) NOT NULL DEFAULT '0' COMMENT '数据状态：0-有效，1-删除',
  `create_user` bigint(20) DEFAULT NULL COMMENT '创建用户ID',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `modified_user` bigint(20) DEFAULT NULL COMMENT '更新用户ID',
  `gmt_modified` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='请求日志表';

-- 正在导出表  sigma_server.t_request_log 的数据：~0 rows (大约)
DELETE FROM `t_request_log`;
/*!40000 ALTER TABLE `t_request_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_request_log` ENABLE KEYS */;

-- 导出  表 sigma_server.t_role 结构
CREATE TABLE IF NOT EXISTS `t_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `code` varchar(50) NOT NULL COMMENT '角色编码，由系统统一生成，不可修改',
  `name` varchar(50) NOT NULL COMMENT '角色名称',
  `disable` smallint(1) NOT NULL DEFAULT '0' COMMENT '数据状态：0-有效，1-删除',
  `version` bigint(20) NOT NULL DEFAULT '1' COMMENT '更新版本',
  `create_user` bigint(20) DEFAULT NULL COMMENT '创建用户',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `modified_user` bigint(20) DEFAULT NULL COMMENT '更新用户',
  `gmt_modified` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='系统角色表，维度表';

-- 正在导出表  sigma_server.t_role 的数据：~4 rows (大约)
DELETE FROM `t_role`;
/*!40000 ALTER TABLE `t_role` DISABLE KEYS */;
INSERT INTO `t_role` (`id`, `code`, `name`, `disable`, `version`, `create_user`, `gmt_create`, `modified_user`, `gmt_modified`) VALUES
	(1, 'ROLE_ADMIN', '系统管理员', 0, 1, NULL, NULL, NULL, NULL),
	(2, 'ROLE_USER', '普通用户', 0, 1, 1, '2019-07-08 22:50:56', NULL, NULL),
	(3, 'ROLE_VIP', '超级会员', 0, 1, NULL, NULL, NULL, NULL),
	(4, 'ROLE_STUDENT', '汪精卫', 1, 1, 1, '2019-09-20 02:33:42', 1, '2019-09-20 02:34:25');
/*!40000 ALTER TABLE `t_role` ENABLE KEYS */;

-- 导出  表 sigma_server.t_role_menu 结构
CREATE TABLE IF NOT EXISTS `t_role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_code` varchar(50) NOT NULL COMMENT '角色编码，由系统统一生成，不可修改',
  `menu_id` bigint(20) DEFAULT NULL COMMENT '菜单编码，由系统统一生成，不可修改',
  `create` smallint(1) NOT NULL DEFAULT '1' COMMENT '创建数据权限：0-否，1-是',
  `read` smallint(1) NOT NULL DEFAULT '1' COMMENT '读取详情权限：0-否，1-是',
  `update` smallint(1) NOT NULL DEFAULT '1' COMMENT '更新数据权限：0-否，1-是',
  `delete` smallint(1) NOT NULL DEFAULT '1' COMMENT '删除数据权限：0-否，1-是',
  `disable` smallint(1) NOT NULL DEFAULT '0' COMMENT '数据状态：0-有效，1-删除',
  `version` bigint(20) NOT NULL DEFAULT '1' COMMENT '更新版本',
  `create_user` bigint(20) DEFAULT NULL COMMENT '创建用户',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `modified_user` bigint(20) DEFAULT NULL COMMENT '更新用户',
  `gmt_modified` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `role_code` (`role_code`),
  KEY `menu_code` (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='权限菜单关联表，事实表';

-- 正在导出表  sigma_server.t_role_menu 的数据：~11 rows (大约)
DELETE FROM `t_role_menu`;
/*!40000 ALTER TABLE `t_role_menu` DISABLE KEYS */;
INSERT INTO `t_role_menu` (`id`, `role_code`, `menu_id`, `create`, `read`, `update`, `delete`, `disable`, `version`, `create_user`, `gmt_create`, `modified_user`, `gmt_modified`) VALUES
	(1, 'ROLE_ADMIN', 1, 1, 1, 1, 1, 0, 1, NULL, NULL, NULL, NULL),
	(2, 'ROLE_STUDENT', 1, 0, 0, 0, 0, 1, 1, NULL, NULL, NULL, NULL),
	(3, 'ROLE_STUDENT', 2, 0, 0, 0, 0, 1, 1, NULL, NULL, NULL, NULL),
	(4, 'ROLE_STUDENT', 3, 0, 0, 0, 0, 1, 1, NULL, NULL, NULL, NULL),
	(5, 'ROLE_STUDENT', 1, 0, 0, 0, 0, 1, 1, NULL, NULL, NULL, NULL),
	(6, 'ROLE_STUDENT', 2, 0, 0, 0, 0, 1, 1, NULL, NULL, NULL, NULL),
	(7, 'ROLE_STUDENT', 3, 0, 0, 0, 0, 1, 1, NULL, NULL, NULL, NULL),
	(8, 'ROLE_STUDENT', 4, 0, 0, 0, 0, 1, 1, NULL, NULL, NULL, NULL),
	(9, 'ROLE_ADMIN', 2, 1, 1, 1, 1, 0, 1, NULL, NULL, NULL, NULL),
	(10, 'ROLE_ADMIN', 3, 1, 1, 1, 1, 0, 1, NULL, NULL, NULL, NULL),
	(11, 'ROLE_ADMIN', 4, 1, 1, 1, 1, 0, 1, NULL, NULL, NULL, NULL);
/*!40000 ALTER TABLE `t_role_menu` ENABLE KEYS */;

-- 导出  表 sigma_server.t_send_code_log 结构
CREATE TABLE IF NOT EXISTS `t_send_code_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `receive_address` varchar(50) NOT NULL COMMENT '接收方',
  `send_type` varchar(50) NOT NULL COMMENT '发送消息类型',
  `message_type` varchar(50) NOT NULL COMMENT '消息类型',
  `message_code` varchar(50) NOT NULL COMMENT '消息代码',
  `expire_in` bigint(20) NOT NULL DEFAULT '0' COMMENT '失效时间，单位秒',
  `disable` smallint(6) NOT NULL DEFAULT '0' COMMENT '逻辑删除状态',
  `version` bigint(20) NOT NULL DEFAULT '1' COMMENT '版本',
  `create_user` bigint(20) DEFAULT NULL COMMENT '创建人',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `modified_user` bigint(20) DEFAULT NULL COMMENT '更新人',
  `gmt_modified` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='验证码日志';

-- 正在导出表  sigma_server.t_send_code_log 的数据：~0 rows (大约)
DELETE FROM `t_send_code_log`;
/*!40000 ALTER TABLE `t_send_code_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_send_code_log` ENABLE KEYS */;

-- 导出  表 sigma_server.t_user 结构
CREATE TABLE IF NOT EXISTS `t_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(200) NOT NULL COMMENT '密码',
  `avatar` varchar(50) DEFAULT NULL COMMENT '头像相对地址',
  `nickname` varchar(50) DEFAULT NULL COMMENT '昵称',
  `birthday` datetime DEFAULT NULL COMMENT '生日',
  `gender` smallint(1) DEFAULT NULL COMMENT '性别',
  `mobile` varchar(11) NOT NULL COMMENT '手机',
  `email` varchar(50) NOT NULL COMMENT '邮箱',
  `wechat` varchar(200) DEFAULT NULL COMMENT '微信三方登陆id',
  `QQ` varchar(200) DEFAULT NULL COMMENT 'qq三方登陆id',
  `weibo` varchar(200) DEFAULT NULL COMMENT '微博三方登陆id',
  `signature` varchar(200) DEFAULT NULL COMMENT '用户签名',
  `balance` decimal(10,2) DEFAULT '0.00' COMMENT '余额',
  `disable` smallint(1) NOT NULL DEFAULT '0' COMMENT '数据状态：0-有效，1-删除',
  `version` bigint(20) NOT NULL DEFAULT '1' COMMENT '更新版本',
  `create_user` bigint(20) DEFAULT NULL COMMENT '创建用户',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `modified_user` bigint(20) DEFAULT NULL COMMENT '更新用户',
  `gmt_modified` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='用户表，维度表';

-- 正在导出表  sigma_server.t_user 的数据：~2 rows (大约)
DELETE FROM `t_user`;
/*!40000 ALTER TABLE `t_user` DISABLE KEYS */;
INSERT INTO `t_user` (`id`, `username`, `password`, `avatar`, `nickname`, `birthday`, `gender`, `mobile`, `email`, `wechat`, `QQ`, `weibo`, `signature`, `balance`, `disable`, `version`, `create_user`, `gmt_create`, `modified_user`, `gmt_modified`) VALUES
	(1, 'sigma', '$2a$10$b3VrgiJ6Ai6gI/kGzKAem.pUl0ca4JE3219qyPeW4EYFM/fy3bl5K', 'https://github.com/account', 'K.O', '2020-06-09 20:54:21', 1, '13604261401', 'ko.shen@hotmail.comnm', NULL, NULL, NULL, NULL, 0.00, 0, 0, NULL, NULL, 1, '2019-12-15 15:33:31'),
	(4, 'you_leet', '$2a$10$21gu9X9Qw2GqYVp1zCe40e.gDrVrOT.W82GFtaa/VeV/ZxbJ42MFO', NULL, 'Sigma', NULL, 0, '13604261403', 'you_leet@foxmail.com', NULL, NULL, NULL, NULL, 0.00, 0, 1, NULL, '2019-07-09 21:48:48', NULL, NULL);
/*!40000 ALTER TABLE `t_user` ENABLE KEYS */;

-- 导出  表 sigma_server.t_user_role 结构
CREATE TABLE IF NOT EXISTS `t_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户编码（UUID），由系统统一生成，不可修改',
  `role_code` varchar(50) NOT NULL COMMENT '角色编码，由系统统一生成，不可修改',
  `disable` smallint(1) NOT NULL DEFAULT '0' COMMENT '数据状态：0-有效，1-删除',
  `version` bigint(20) NOT NULL DEFAULT '1' COMMENT '更新版本',
  `create_user` bigint(20) DEFAULT NULL COMMENT '创建用户',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `modified_user` bigint(20) DEFAULT NULL COMMENT '更新用户',
  `gmt_modified` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `user_code` (`user_id`),
  KEY `role_code` (`role_code`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='用户权限关联表，事实表';

-- 正在导出表  sigma_server.t_user_role 的数据：~2 rows (大约)
DELETE FROM `t_user_role`;
/*!40000 ALTER TABLE `t_user_role` DISABLE KEYS */;
INSERT INTO `t_user_role` (`id`, `user_id`, `role_code`, `disable`, `version`, `create_user`, `gmt_create`, `modified_user`, `gmt_modified`) VALUES
	(1, 1, 'ROLE_ADMIN', 0, 1, NULL, NULL, NULL, NULL),
	(2, 2, 'ROLE_USER', 0, 1, NULL, NULL, NULL, NULL);
/*!40000 ALTER TABLE `t_user_role` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
