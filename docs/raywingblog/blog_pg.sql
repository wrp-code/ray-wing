drop database if exists raywing_blog ;
create database raywing_blog;

-- 文章
CREATE TABLE IF NOT EXISTS public.article
(
    id bigint,
    create_time timestamp without time zone NOT NULL default now(),
    update_time timestamp without time zone NOT NULL default now(),
    deleted smallint NOT NULL default 0,

    title varchar NOT NULL,
    content text NOT NULL,
    content_table json,
    CONSTRAINT article_pkey PRIMARY KEY (id)
    );
COMMENT ON TABLE public.article
    IS '文章';
COMMENT ON COLUMN  public.article.title IS '文章';
COMMENT ON COLUMN  public.article.content IS '文章内容';
COMMENT ON COLUMN  public.article.content_table IS '带锚点链接的目录';


-- 目录分类
CREATE TABLE IF NOT EXISTS public.catalog
(
    id bigint,
    create_time timestamp without time zone NOT NULL default now(),
    update_time timestamp without time zone NOT NULL default now(),
    deleted smallint NOT NULL default 0,

    name varchar NOT NULL,
    parent_id bigint,
    article_count int NOT NULL default 0,
    description varchar,
    CONSTRAINT catalog_pkey PRIMARY KEY (id)
    );
COMMENT ON TABLE public.catalog
    IS '目录分类';
COMMENT ON COLUMN  public.catalog.name IS '目录名称';
COMMENT ON COLUMN  public.catalog.parent_id IS '父级目录ID';
COMMENT ON COLUMN  public.catalog.article_count IS '文章数量';
COMMENT ON COLUMN  public.catalog.description IS '描述';


-- 目录分类关联文章
CREATE TABLE IF NOT EXISTS public.catalog_article
(
    id bigint,
    create_time timestamp without time zone NOT NULL default now(),
    update_time timestamp without time zone NOT NULL default now(),
    deleted smallint NOT NULL default 0,

    catalog_id bigint,
    article_id bigint,

    CONSTRAINT catalog_article_pkey PRIMARY KEY (id)
    );
COMMENT ON TABLE public.catalog_article
    IS '目录分类关联文章';
