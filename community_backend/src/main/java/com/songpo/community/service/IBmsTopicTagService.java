package com.songpo.community.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.songpo.community.model.entity.BmsTag;
import com.songpo.community.model.entity.BmsTopicTag;
import com.songpo.community.model.vo.PostVO;

import java.util.List;
import java.util.Set;

public interface IBmsTopicTagService extends IService<BmsTopicTag> {

    /**
     * 获取Topic Tag 关联记录
     *
     * @param topicId TopicId
     * @return
     */
    List<BmsTopicTag> selectByTopicId(String topicId);
    /**
     * 创建中间关系
     *
     * @param id
     * @param tags
     * @return
     */
    void createTopicTag(String id, List<BmsTag> tags);
//    /**
//     * 获取标签换脸话题ID集合
//     *
//     * @param id
//     * @return
//     */
//    Set<String> selectTopicIdsByTagId(String id);

}
