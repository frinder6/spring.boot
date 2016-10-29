
INSERT INTO `user` ( `age`, `name`, `remark`, `sex` )
  SELECT `age`, `name`, `remark`, `sex` FROM `user`;


SELECT
  (crc32(upper('name1')) % 3);