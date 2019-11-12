-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` char(32) NOT NULL COMMENT '主键ID',
  `tenant_id` char(32) NOT NULL COMMENT '租户ID',
  `account` varchar(45) NOT NULL COMMENT '账号',
  `password` varchar(45) NOT NULL COMMENT '密码',
  `name` varchar(20) NULL DEFAULT NULL COMMENT '昵称',
  `real_name` varchar(10) NULL DEFAULT NULL COMMENT '真名',
  `email` varchar(45) NULL DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(45) NULL DEFAULT NULL COMMENT '手机',
  `birthday` datetime(0) NULL DEFAULT NULL COMMENT '生日',
  `sex` smallint(6) NULL DEFAULT NULL COMMENT '性别',
  `role_id` varchar(32) NULL DEFAULT NULL COMMENT '角色id',
  `dept_id` varchar(32) NULL DEFAULT NULL COMMENT '部门id',
  `create_user` char(32) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` char(32) NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `status` int(2) NULL DEFAULT NULL COMMENT '状态',
  `note` varchar(100) NULL DEFAULT NULL COMMENT '备注',
  `is_deleted` int(2) NULL DEFAULT 0 COMMENT '是否已删除,0未删除，1已删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `tenant_account` (`tenant_id`,`account`) USING BTREE
) ENGINE = InnoDB DEFAULT CHARSET=utf8 COMMENT = '用户表';

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` char(32) NOT NULL COMMENT '主键',
  `parent_id` char(32) NULL DEFAULT 0 COMMENT '父级菜单id',
  `code` varchar(255) NOT NULL COMMENT '菜单编号(唯一)',
  `name` varchar(255) NOT NULL COMMENT '菜单名称',
  `alias` varchar(255) NULL DEFAULT NULL COMMENT '菜单别名',
  `path` varchar(255) NULL DEFAULT NULL COMMENT '请求地址',
  `source` varchar(255) NULL DEFAULT NULL COMMENT '菜单图标资源',
  `sort` int(2) NULL DEFAULT 1 COMMENT '排序',
  `category` int(2) NULL DEFAULT NULL COMMENT '菜单类型',
  `action` int(2) NULL DEFAULT 0 COMMENT '操作按钮类型',
  `is_open` int(2) NULL DEFAULT 1 COMMENT '是否打开新页面',
  `create_user` char(32) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` char(32) NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `status` int(2) NULL DEFAULT NULL COMMENT '状态',
  `note` varchar(100) NULL DEFAULT NULL COMMENT '备注',
  `is_deleted` int(2) NULL DEFAULT 0 COMMENT '是否已删除,0未删除，1已删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `code` (`code`) USING BTREE
) ENGINE = InnoDB DEFAULT CHARSET=utf8 COMMENT = '菜单表';

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` char(32) NOT NULL COMMENT '主键',
  `tenant_id` char(32) NOT NULL COMMENT '租户ID',
  `parent_id` char(32) NULL DEFAULT 0 COMMENT '父主键',
  `role_name` varchar(255) NULL DEFAULT NULL COMMENT '角色名',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  `role_alias` varchar(255) NULL DEFAULT NULL COMMENT '角色别名',
  `create_user` char(32) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` char(32) NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `status` int(2) NULL DEFAULT NULL COMMENT '状态',
  `note` varchar(100) NULL DEFAULT NULL COMMENT '备注',
  `is_deleted` int(2) NULL DEFAULT 0 COMMENT '是否已删除,0未删除，1已删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `tenant_role` (`tenant_id`,`role_name`) USING BTREE,
  UNIQUE KEY `tenant_alias` (`tenant_id`,`role_alias`) USING BTREE
) ENGINE = InnoDB DEFAULT CHARSET=utf8 COMMENT = '角色表';

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `id` char(32) NOT NULL COMMENT '主键',
  `role_id` char(32) NOT NULL COMMENT '角色id',
  `menu_id` char(32) NOT NULL COMMENT '菜单id',
  `create_user` char(32) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` char(32) NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `status` int(2) NULL DEFAULT NULL COMMENT '状态',
  `note` varchar(100) NULL DEFAULT NULL COMMENT '备注',
  `is_deleted` int(2) NULL DEFAULT 0 COMMENT '是否已删除,0未删除，1已删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `role_menu` (`role_id`,`menu_id`) USING BTREE
) ENGINE = InnoDB DEFAULT CHARSET=utf8 COMMENT = '角色菜单表';

-- ----------------------------
-- Table structure for sys_tenant
-- ----------------------------
DROP TABLE IF EXISTS `sys_tenant`;
CREATE TABLE `sys_tenant`  (
  `id` char(32) NOT NULL COMMENT '主键',
  `tenant_id` char(32) NOT NULL COMMENT '租户ID',
  `tenant_name` varchar(50) NOT NULL COMMENT '租户名称',
  `linkman` varchar(20) NULL DEFAULT NULL COMMENT '联系人',
  `contact_number` varchar(20) NULL DEFAULT NULL COMMENT '联系电话',
  `address` varchar(255) NULL DEFAULT NULL COMMENT '联系地址',
  `create_user` char(32) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` char(32) NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `status` int(2) NULL DEFAULT NULL COMMENT '状态',
  `note` varchar(100) NULL DEFAULT NULL COMMENT '备注',
  `is_deleted` int(2) NULL DEFAULT 0 COMMENT '是否已删除,0未删除，1已删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `tenant_id` (`tenant_id`) USING BTREE
) ENGINE = InnoDB DEFAULT CHARSET=utf8 COMMENT = '租户表';

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
  `id` char(32) NOT NULL COMMENT '主键',
  `tenant_id` char(32) NOT NULL COMMENT '租户ID',
  `parent_id` char(32) NULL DEFAULT 0 COMMENT '父主键',
  `dept_name` varchar(45) NULL DEFAULT NULL COMMENT '部门名',
  `full_name` varchar(45) NULL DEFAULT NULL COMMENT '部门全称',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  `create_user` char(32) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` char(32) NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `status` int(2) NULL DEFAULT NULL COMMENT '状态',
  `note` varchar(100) NULL DEFAULT NULL COMMENT '备注',
  `is_deleted` int(2) NULL DEFAULT 0 COMMENT '是否已删除,0未删除，1已删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `tenant_dept` (`tenant_id`,`dept_name`) USING BTREE
) ENGINE = InnoDB DEFAULT CHARSET=utf8 COMMENT = '部门表';


########################################################################################################################

-- ----------------------------
-- Table structure for sys_client
-- ----------------------------
DROP TABLE IF EXISTS `sys_client`;
CREATE TABLE `sys_client`  (
  `id` char(32) NOT NULL COMMENT '主键',
  `client_id` char(32) NOT NULL COMMENT '客户端id',
  `client_secret` varchar(256) NOT NULL COMMENT '客户端密钥',
  `resource_ids` varchar(256) NULL DEFAULT NULL COMMENT '资源集合',
  `scope` varchar(256) NOT NULL COMMENT '授权范围',
  `authorized_grant_types` varchar(256) NOT NULL COMMENT '授权类型',
  `web_server_redirect_uri` varchar(256) NULL DEFAULT NULL COMMENT '回调地址',
  `authorities` varchar(256) NULL DEFAULT NULL COMMENT '权限',
  `access_token_validity` int(11) NOT NULL COMMENT '令牌过期秒数',
  `refresh_token_validity` int(11) NOT NULL COMMENT '刷新令牌过期秒数',
  `additional_information` varchar(4096) NULL DEFAULT NULL COMMENT '附加说明',
  `autoapprove` varchar(256) NULL DEFAULT NULL COMMENT '自动授权',
  `create_user` char(32) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` char(32) NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `status` int(2) NULL DEFAULT NULL COMMENT '状态',
  `is_deleted` int(2) NULL DEFAULT 0 COMMENT '是否已删除,0未删除，1已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB DEFAULT CHARSET=utf8 COMMENT = '客户端表';

-- ----------------------------
-- Table structure for sys_code
-- ----------------------------
DROP TABLE IF EXISTS `sys_code`;
CREATE TABLE `sys_code`  (
  `id` char(32) NOT NULL COMMENT '主键',
  `datasource_id` char(32) NULL DEFAULT NULL COMMENT '数据源主键',
  `service_name` varchar(64) NULL DEFAULT NULL COMMENT '服务名称',
  `code_name` varchar(64) NULL DEFAULT NULL COMMENT '模块名称',
  `table_name` varchar(64) NULL DEFAULT NULL COMMENT '表名',
  `table_prefix` varchar(64) NULL DEFAULT NULL COMMENT '表前缀',
  `pk_name` varchar(32) NULL DEFAULT NULL COMMENT '主键名',
  `package_name` varchar(500) NULL DEFAULT NULL COMMENT '后端包名',
  `base_mode` int(2) NULL DEFAULT NULL COMMENT '基础业务模式',
  `wrap_mode` int(2) NULL DEFAULT NULL COMMENT '包装器模式',
  `api_path` varchar(2000) NULL DEFAULT NULL COMMENT '后端路径',
  `web_path` varchar(2000) NULL DEFAULT NULL COMMENT '前端路径',
  `create_user` char(32) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` char(32) NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `status` int(2) NULL DEFAULT NULL COMMENT '状态',
  `is_deleted` int(2) NULL DEFAULT 0 COMMENT '是否已删除,0未删除，1已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB DEFAULT CHARSET=utf8 COMMENT = '代码生成表';

-- ----------------------------
-- Table structure for sys_datasource
-- ----------------------------
DROP TABLE IF EXISTS `sys_datasource`;
CREATE TABLE `sys_datasource`  (
  `id` char(32) NOT NULL COMMENT '主键',
  `name` varchar(100) NULL DEFAULT NULL COMMENT '名称',
  `driver_class` varchar(100) NULL DEFAULT NULL COMMENT '驱动类',
  `url` varchar(500) NULL DEFAULT NULL COMMENT '连接地址',
  `username` varchar(50) NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(50) NULL DEFAULT NULL COMMENT '密码',
  `remark` varchar(255) NULL DEFAULT NULL COMMENT '备注',
  `create_dept` bigint(64) NULL DEFAULT NULL COMMENT '创建部门',
  `create_user` char(32) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` char(32) NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `status` int(2) NULL DEFAULT NULL COMMENT '状态',
  `is_deleted` int(2) NULL DEFAULT 0 COMMENT '是否已删除,0未删除，1已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB DEFAULT CHARSET=utf8 COMMENT = '数据源配置表';

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict`  (
  `id` char(32) NOT NULL COMMENT '主键',
  `parent_id` char(32) NULL DEFAULT 0 COMMENT '父主键',
  `code` varchar(255) NULL DEFAULT NULL COMMENT '字典码',
  `dict_key` int(2) NULL DEFAULT NULL COMMENT '字典值',
  `dict_value` varchar(255) NULL DEFAULT NULL COMMENT '字典名称',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  `remark` varchar(255) NULL DEFAULT NULL COMMENT '字典备注',
  `create_user` char(32) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` char(32) NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `status` int(2) NULL DEFAULT NULL COMMENT '状态',
  `is_deleted` int(2) NULL DEFAULT 0 COMMENT '是否已删除,0未删除，1已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB DEFAULT CHARSET=utf8 COMMENT = '字典表';

-- ----------------------------
-- Table structure for sys_log_api
-- ----------------------------
DROP TABLE IF EXISTS `sys_log_api`;
CREATE TABLE `sys_log_api`  (
  `id` char(32) NOT NULL COMMENT '主键',
  `tenant_id` char(32) NOT NULL COMMENT '租户ID',
  `service_id` varchar(32) NULL DEFAULT NULL COMMENT '服务ID',
  `server_host` varchar(255) NULL DEFAULT NULL COMMENT '服务器名',
  `server_ip` varchar(255) NULL DEFAULT NULL COMMENT '服务器IP地址',
  `env` varchar(255) NULL DEFAULT NULL COMMENT '服务器环境',
  `type` char(1) NULL DEFAULT '1' COMMENT '日志类型',
  `title` varchar(255) NULL DEFAULT '' COMMENT '日志标题',
  `method` varchar(10) NULL DEFAULT NULL COMMENT '操作方式',
  `request_uri` varchar(255) NULL DEFAULT NULL COMMENT '请求URI',
  `user_agent` varchar(1000) NULL DEFAULT NULL COMMENT '用户代理',
  `remote_ip` varchar(255) NULL DEFAULT NULL COMMENT '操作IP地址',
  `method_class` varchar(255) NULL DEFAULT NULL COMMENT '方法类',
  `method_name` varchar(255) NULL DEFAULT NULL COMMENT '方法名',
  `params` text NULL COMMENT '操作提交的数据',
  `time` varchar(64) NULL DEFAULT NULL COMMENT '执行时间',
  `create_user` char(32) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` char(32) NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `status` int(2) NULL DEFAULT NULL COMMENT '状态',
  `is_deleted` int(2) NULL DEFAULT 0 COMMENT '是否已删除,0未删除，1已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB DEFAULT CHARSET=utf8 COMMENT = '接口日志表';

-- ----------------------------
-- Table structure for sys_log_error
-- ----------------------------
DROP TABLE IF EXISTS `sys_log_error`;
CREATE TABLE `sys_log_error`  (
  `id` char(32) NOT NULL COMMENT '主键',
  `tenant_id` char(32) NOT NULL COMMENT '租户ID',
  `service_id` char(32) NULL DEFAULT NULL COMMENT '服务ID',
  `server_host` varchar(255) NULL DEFAULT NULL COMMENT '服务器名',
  `server_ip` varchar(255) NULL DEFAULT NULL COMMENT '服务器IP地址',
  `env` varchar(255) NULL DEFAULT NULL COMMENT '系统环境',
  `method` varchar(10) NULL DEFAULT NULL COMMENT '操作方式',
  `request_uri` varchar(255) NULL DEFAULT NULL COMMENT '请求URI',
  `user_agent` varchar(1000) NULL DEFAULT NULL COMMENT '用户代理',
  `stack_trace` text NULL COMMENT '堆栈',
  `exception_name` varchar(255) NULL DEFAULT NULL COMMENT '异常名',
  `message` text NULL COMMENT '异常信息',
  `line_number` int(11) NULL DEFAULT NULL COMMENT '错误行数',
  `remote_ip` varchar(255) NULL DEFAULT NULL COMMENT '操作IP地址',
  `method_class` varchar(255) NULL DEFAULT NULL COMMENT '方法类',
  `file_name` varchar(1000) NULL DEFAULT NULL COMMENT '文件名',
  `method_name` varchar(255) NULL DEFAULT NULL COMMENT '方法名',
  `params` text NULL COMMENT '操作提交的数据',
  `time` varchar(64) NULL DEFAULT NULL COMMENT '执行时间',
  `create_user` char(32) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` char(32) NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `status` int(2) NULL DEFAULT NULL COMMENT '状态',
  `is_deleted` int(2) NULL DEFAULT 0 COMMENT '是否已删除,0未删除，1已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB DEFAULT CHARSET=utf8 COMMENT = '错误日志表';

-- ----------------------------
-- Table structure for sys_log_usual
-- ----------------------------
DROP TABLE IF EXISTS `sys_log_usual`;
CREATE TABLE `sys_log_usual`  (
  `id` char(32) NOT NULL COMMENT '主键',
  `tenant_id` char(32) NOT NULL COMMENT '租户ID',
  `service_id` char(32) NULL DEFAULT NULL COMMENT '服务ID',
  `server_host` varchar(255) NULL DEFAULT NULL COMMENT '服务器名',
  `server_ip` varchar(255) NULL DEFAULT NULL COMMENT '服务器IP地址',
  `env` varchar(255) NULL DEFAULT NULL COMMENT '系统环境',
  `log_level` varchar(10) NULL DEFAULT NULL COMMENT '日志级别',
  `log_id` varchar(100) NULL DEFAULT NULL COMMENT '日志业务id',
  `log_data` text NULL COMMENT '日志数据',
  `method` varchar(10) NULL DEFAULT NULL COMMENT '操作方式',
  `request_uri` varchar(255) NULL DEFAULT NULL COMMENT '请求URI',
  `remote_ip` varchar(255) NULL DEFAULT NULL COMMENT '操作IP地址',
  `method_class` varchar(255) NULL DEFAULT NULL COMMENT '方法类',
  `method_name` varchar(255) NULL DEFAULT NULL COMMENT '方法名',
  `user_agent` varchar(1000) NULL DEFAULT NULL COMMENT '用户代理',
  `params` text NULL COMMENT '操作提交的数据',
  `time` datetime(0) NULL DEFAULT NULL COMMENT '执行时间',
  `create_user` char(32) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` char(32) NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `status` int(2) NULL DEFAULT NULL COMMENT '状态',
  `is_deleted` int(2) NULL DEFAULT 0 COMMENT '是否已删除,0未删除，1已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB DEFAULT CHARSET=utf8 COMMENT = '通用日志表';

-- ----------------------------
-- Table structure for sys_notice
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice`;
CREATE TABLE `sys_notice`  (
  `id` char(32) NOT NULL COMMENT '主键',
  `tenant_id` char(32) NOT NULL COMMENT '租户ID',
  `title` varchar(255) NULL DEFAULT NULL COMMENT '标题',
  `category` int(11) NULL DEFAULT NULL COMMENT '类型',
  `release_time` datetime(0) NULL DEFAULT NULL COMMENT '发布时间',
  `content` varchar(255) NULL DEFAULT NULL COMMENT '内容',
  `create_user` char(32) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` char(32) NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `status` int(2) NULL DEFAULT NULL COMMENT '状态',
  `is_deleted` int(2) NULL DEFAULT 0 COMMENT '是否已删除,0未删除，1已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB DEFAULT CHARSET=utf8 COMMENT = '通知公告表';

-- ----------------------------
-- Table structure for sys_param
-- ----------------------------
DROP TABLE IF EXISTS `sys_param`;
CREATE TABLE `sys_param`  (
  `id` char(32) NOT NULL COMMENT '主键',
  `param_name` varchar(255) NULL DEFAULT NULL COMMENT '参数名',
  `param_key` varchar(255) NULL DEFAULT NULL COMMENT '参数键',
  `param_value` varchar(255) NULL DEFAULT NULL COMMENT '参数值',
  `remark` varchar(255) NULL DEFAULT NULL COMMENT '备注',
  `create_user` char(32) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` char(32) NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `status` int(2) NULL DEFAULT NULL COMMENT '状态',
  `is_deleted` int(2) NULL DEFAULT 0 COMMENT '是否已删除,0未删除，1已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB DEFAULT CHARSET=utf8 COMMENT = '参数表';








