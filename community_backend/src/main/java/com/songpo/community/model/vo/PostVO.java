package com.songpo.community.model.vo;

import com.songpo.community.model.entity.BmsTag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class PostVO implements Serializable {
    private static final long serialVersionUID = -261082150965211545L;

    /**
     * 文章ID
     */
    private String id;

    private String content;
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 用户昵称
     */
    private String alias;
    /**
     * 账号
     */
    private String username;
    /**
     * 标题
     */
    private String title;
    /**
     * 图片封面
     */
    private String cover;
    /**
     * 一级分类
     */
    private String categoryName;
    /**
     * 二级分类
     */
    private String subcategoryName;
    /**
     * 点赞统计
     */
    private Integer like;
    /**
     * 分享统计
     */
    private Integer share;
    /**
     * 评论统计
     */
    private Integer comments;
    /**
     * 置顶
     */
    private Boolean top;
    /**
     * 加精
     */
    private Boolean essence;
    /**
     * 收藏次數
     */
    private Integer collects;
    /**
     * 话题关联标签
     */
    private List<BmsTag> tags;
    /**
     * 浏览量
     */
    private Integer view;
    /**
     * 专栏ID
     */
    private String sectionId;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date modifyTime;
}
