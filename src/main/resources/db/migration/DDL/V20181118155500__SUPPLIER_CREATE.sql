--
-- table create `supplier`
--

CREATE TABLE `supplier` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID，自增',
  `createTime` BIGINT(20) NOT NULL COMMENT '创建时间',
  `lastModified` BIGINT(20) NOT NULL COMMENT '最近一次修改时间',
  `name` VARCHAR(40) NOT NULL COMMENT '用户名称',
  `phoneNum` VARCHAR(11) NOT NULL COMMENT '手机号码',
  `remark` VARCHAR(100) COMMENT '备注',
  `userId` int(11) NOT NULL COMMENT '所属用户id',
  PRIMARY KEY (`id`),
  INDEX `idx_stock_user_id` (`userId` ASC)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='供应商表';