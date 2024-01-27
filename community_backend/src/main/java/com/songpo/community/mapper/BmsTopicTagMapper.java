package com.songpo.community.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.songpo.community.model.entity.BmsTopicTag;
import com.songpo.community.model.vo.TagVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;


@Repository
public interface BmsTopicTagMapper extends BaseMapper<BmsTopicTag> {
    /**
     * 根据标签获取话题ID集合
     *
     * @param id
     * @return
     */
    Set<String> getTopicIdsByTagId(@Param("id") String id);

    List<TagVO> getAllTags();

}
