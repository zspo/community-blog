package com.songpo.community.model.vo;

import lombok.Data;

import java.util.Date;


@Data
public class CommentVO {

    private String id;

    private String content;

    private String topicId;

    private String userId;

    private Integer like;

    private String replyId;

    private String parentId;

    private Date createTime;

}
