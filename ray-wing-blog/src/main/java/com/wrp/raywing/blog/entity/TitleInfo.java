package com.wrp.raywing.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 标题信息，主要用于前端渲染文章目录
 * @author wrp
 * @since 2025年08月21日 11:03
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TitleInfo {
    /**
     * 标题级别
     */
    private int level;
    /**
     * 标题名称
     */
    private String title;
}
