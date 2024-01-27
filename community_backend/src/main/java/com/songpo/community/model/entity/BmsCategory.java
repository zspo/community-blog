package com.songpo.community.model.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@TableName("bms_category")
@NoArgsConstructor
@AllArgsConstructor
public class BmsCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 一级分类ID
     */
    @TableField("category_id")
    private String categoryID;

    /**
     * 一级分类名称
     */
    @TableField("category_name")
    private String categoryName;

    /**
     * 一级分类ID
     */
    @TableField("subcategory_id")
    private String subcategoryID;

    /**
     * 一级分类名称
     */
    @TableField("subcategory_name")
    private String subcategoryName;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 修改时间
     */
    @TableField(value = "modify_time", fill = FieldFill.UPDATE)
    private Date modifyTime;


}
