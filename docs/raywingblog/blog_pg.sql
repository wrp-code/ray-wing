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
    CONSTRAINT article_pkey PRIMARY KEY (id)
    );
COMMENT ON TABLE public.article
    IS '文章';
COMMENT ON COLUMN  public.article.title IS '文章';
COMMENT ON COLUMN  public.article.content IS '文章内容';
