--
-- alter table for `customer`
--

ALTER TABLE `customer`
ADD INDEX `idx_customer_user_id` (`userId` ASC);