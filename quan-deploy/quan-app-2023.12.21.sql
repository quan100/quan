ALTER TABLE dictionary ADD COLUMN `type` varchar(8) DEFAULT NULL COMMENT '类型';
ALTER TABLE dictionary ADD COLUMN `del_flag` bit(1) DEFAULT b'0' COMMENT '删除状态，0：正常，1：删除';