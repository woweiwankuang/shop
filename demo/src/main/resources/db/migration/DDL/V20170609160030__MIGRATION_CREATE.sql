--
-- Table structure for table `migration`
--

CREATE TABLE `migration` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(50) NOT NULL COMMENT '名称',
  `success` tinyint(1) NOT NULL COMMENT '脚本状态，0：失败，1：成功',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_migration_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='脚本信息表';