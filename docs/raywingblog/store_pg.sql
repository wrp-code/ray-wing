-- 存储表
CREATE TABLE system.files
(
    id          bigint PRIMARY KEY,
    create_time TIMESTAMP WITHOUT TIME ZONE DEFAULT now(),
    update_time TIMESTAMP WITHOUT TIME ZONE DEFAULT now(),
    deleted     smallint           NOT NULL DEFAULT 0,

    file_name    VARCHAR NOT NULL,
    type    VARCHAR       NOT NULL,
    size   bigint NOT NULL,
    url       VARCHAR
);
COMMENT ON TABLE system.files
    IS '存储表';
COMMENT ON COLUMN  system.files.file_name IS '文件名';
COMMENT ON COLUMN  system.files.type IS '文件类型';
COMMENT ON COLUMN  system.files.size IS '大小';
COMMENT ON COLUMN  system.files.url IS '相对路径';
