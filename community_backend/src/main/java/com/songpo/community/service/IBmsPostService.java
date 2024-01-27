package com.songpo.community.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.songpo.community.model.dto.CreateTopicDTO;
import com.songpo.community.model.entity.BmsPost;
import com.songpo.community.model.entity.UmsUser;
import com.songpo.community.model.vo.PostVO;

import java.util.List;
import java.util.Map;


public interface IBmsPostService extends IService<BmsPost> {

    /**
     * 获取首页话题列表
     *
     * @param page
     * @param tab
     * @return
     */
    Page<PostVO> getList(Page<PostVO> page, String tab);

    /**
     * 获取
     * @return
     */
    List<PostVO> getLifeList();

    /**
     * 发布
     *
     * @param dto
     * @param principal
     * @return
     */
    BmsPost create(CreateTopicDTO dto, UmsUser principal);

    /**
     * 查看话题详情
     *
     * @param id
     * @return
     */
    Map<String, Object> viewTopic(String id);

    /**
     * 查看life详情
     * @param id
     * @return
     */
    Map<String, Object> viewLifeDetail(String id);

    /**
     * 获取热门文章
     * @return
     */
    List<BmsPost> getHotPost(String mode);
    /**
     * 获取随机推荐10篇
     *
     * @param id
     * @return
     */
    List<BmsPost> getRecommend(String id);
    /**
     * 关键字检索
     *
     * @param keyword
     * @param page
     * @return
     */
    Page<PostVO> searchByKey(String keyword, Page<PostVO> page);

    void setTopicTags(Page<PostVO> iPage);
}
