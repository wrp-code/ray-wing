package com.wrp.raywing.store.entity;

import com.baomidou.mybatisplus.annotation.TableName;



import com.wrp.raywing.common.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 存储表
 * 
 * @author wrp
 * @since 2025-08-25 12:33:54
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "system.files", autoResultMap = true)
public class FilesEntity extends BaseEntity {

	/**
	 * 文件名
	 */
	private String fileName;
	/**
	 * 文件类型
	 */
	private String type;
	/**
	 * 大小
	 */
	private Long size;
	/**
	 * 相对路径
	 */
	private String url;

}
