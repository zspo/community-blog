package com.songpo.community.model.dto;

import lombok.Data;

import java.io.Serializable;


@Data
public class CommentDTO implements Serializable {
    private static final long serialVersionUID = -5957433707110390852L;

    /**
     * 内容
     */
    private String content;

    private String topic_id;

    private Integer like;

    private String reply_id;

    private String parent_id;
}
