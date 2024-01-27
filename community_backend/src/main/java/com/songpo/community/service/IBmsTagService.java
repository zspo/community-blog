package com.songpo.community.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.songpo.community.model.entity.BmsPost;
import com.songpo.community.model.entity.BmsTag;
import com.songpo.community.model.vo.PostVO;

import java.util.List;


public interface IBmsTagService extends IService<BmsTag> {
    /**
     * 插入标签
     *
     * @param tags
     * @return
     */
    List<BmsTag> insertTags(List<String> tags);
    /**
     * 获取标签关联话题
     *
     * @param page
     * @param id
     * @return
     */
    Page<PostVO> selectTopicsByTagId(Page<PostVO> page, String id);
}
