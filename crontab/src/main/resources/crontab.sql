CREATE TABLE `sys_task` (
  `id` char(32) NOT NULL COMMENT '主键ID',
  `job_name` varchar(255) DEFAULT NULL COMMENT '任务名',
  `description` varchar(255) DEFAULT NULL COMMENT '任务描述',
  `cron_expression` varchar(255) DEFAULT NULL COMMENT 'cron表达式',
  `bean_class` varchar(255) DEFAULT NULL COMMENT '包名+类名',
  `job_status` varchar(32) NOT NULL DEFAULT '0' COMMENT '任务状态,0开启，1停用',
  `job_group` varchar(255) DEFAULT NULL COMMENT '任务分组',
  `create_user` char(32) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` char(32) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '定时任务表';