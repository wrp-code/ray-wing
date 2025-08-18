

-- 用户表
CREATE TABLE system.sys_user
(
    id          bigint PRIMARY KEY,
    create_time TIMESTAMP WITHOUT TIME ZONE DEFAULT now(),
    update_time TIMESTAMP WITHOUT TIME ZONE DEFAULT now(),
    deleted     smallint           NOT NULL DEFAULT 0,

    username    VARCHAR(64) UNIQUE NOT NULL,
    password    VARCHAR(255)       NOT NULL,
    full_name   VARCHAR(32),
    phone       VARCHAR(11),
    email       VARCHAR(32) UNIQUE,
    avatar      varchar
);
COMMENT ON TABLE system.sys_user
    IS '用户';
COMMENT ON COLUMN  system.sys_user.username IS '用户名';
COMMENT ON COLUMN  system.sys_user.password IS '密码';
COMMENT ON COLUMN  system.sys_user.full_name IS '真实姓名';
COMMENT ON COLUMN  system.sys_user.phone IS '手机号';
COMMENT ON COLUMN  system.sys_user.email IS '邮箱';
COMMENT ON COLUMN  system.sys_user.avatar IS '头像';
-- 索引
CREATE INDEX idx_user_username ON system.sys_user (username);

-- 角色表
CREATE TABLE system.role
(
    id          bigint PRIMARY KEY,
    create_time TIMESTAMP WITHOUT TIME ZONE DEFAULT now(),
    update_time TIMESTAMP WITHOUT TIME ZONE DEFAULT now(),
    deleted     smallint           NOT NULL DEFAULT 0,

    role_name   VARCHAR(32) UNIQUE NOT NULL,
    description TEXT
);
COMMENT ON TABLE system.role
    IS '角色';
COMMENT ON COLUMN  system.role.role_name IS '角色';
COMMENT ON COLUMN  system.role.description IS '描述';

-- 功能表
CREATE TABLE system.function
(
    id          bigint PRIMARY KEY,
    create_time TIMESTAMP WITHOUT TIME ZONE DEFAULT now(),
    update_time TIMESTAMP WITHOUT TIME ZONE DEFAULT now(),
    deleted     smallint           NOT NULL DEFAULT 0,

    name   VARCHAR(32) UNIQUE NOT NULL,
    parent_id bigint,
    description TEXT
);
COMMENT ON TABLE system.role
    IS '功能';
COMMENT ON COLUMN  system.function.name IS '功能';
COMMENT ON COLUMN  system.function.parent_id IS '父级id';
COMMENT ON COLUMN  system.function.description IS '描述';

-- 权限
CREATE TABLE system.permission
(
    id              bigint PRIMARY KEY,
    create_time     TIMESTAMP WITHOUT TIME ZONE DEFAULT now(),
    update_time     TIMESTAMP WITHOUT TIME ZONE DEFAULT now(),
    deleted         smallint            NOT NULL DEFAULT 0,

    permission_name VARCHAR(100) NOT NULL,
    permission_identify VARCHAR(100) UNIQUE NOT NULL,
    description     TEXT
);
COMMENT ON TABLE system.permission
    IS '角色';
COMMENT ON COLUMN  system.permission.permission_name IS '权限名称';
COMMENT ON COLUMN  system.permission.permission_identify IS '权限描述符';
COMMENT ON COLUMN  system.permission.description IS '描述';

--  用户-角色关联表 (user_role)
CREATE TABLE system.user_role
(
    id          bigint PRIMARY KEY,
    create_time TIMESTAMP WITHOUT TIME ZONE DEFAULT now(),
    update_time TIMESTAMP WITHOUT TIME ZONE DEFAULT now(),
    deleted     smallint NOT NULL DEFAULT 0,
    user_id     bigint   NOT NULL,
    role_id     bigint   NOT NULL
);


--  角色-权限关联表 (role_permission)
CREATE TABLE system.role_permission
(
    id            bigint PRIMARY KEY,
    create_time   TIMESTAMP WITHOUT TIME ZONE DEFAULT now(),
    update_time   TIMESTAMP WITHOUT TIME ZONE DEFAULT now(),
    deleted       smallint NOT NULL DEFAULT 0,
    role_id       bigint   NOT NULL,
    permission_id bigint   NOT NULL
);

--  功能-权限关联表 (function_permission)
CREATE TABLE system.function_permission
(
    id            bigint PRIMARY KEY,
    create_time   TIMESTAMP WITHOUT TIME ZONE DEFAULT now(),
    update_time   TIMESTAMP WITHOUT TIME ZONE DEFAULT now(),
    deleted       smallint NOT NULL DEFAULT 0,
    function_id       bigint   NOT NULL,
    permission_id bigint   NOT NULL
);