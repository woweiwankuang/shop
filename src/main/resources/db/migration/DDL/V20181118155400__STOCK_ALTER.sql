--
-- alter table for `stock`
--

ALTER TABLE `stock`
CHANGE COLUMN `supplier` `supplierId` INT(11) NOT NULL COMMENT '供应商id';