package com.songpo.community.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@TableName("bms_photo")
@AllArgsConstructor
@NoArgsConstructor
public class BmsPhoto implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 图片链接
     */
    @NotBlank(message = "图片不可为空")
    @TableField(value = "img_url")
    private String imgUrl;

    @TableField(value = "topic_id")
    private String topicId;

    @TableField(value = "`like`")
    @Builder.Default
    private Integer like = 0;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;
}
