package com.songpo.community.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.songpo.community.model.entity.BmsTag;
import com.songpo.community.model.vo.PostVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface BmsTagMapper extends BaseMapper<BmsTag> {

    Page<PostVO> selectTopicIdsByTagId(@Param("page") Page<PostVO> page, @Param("id") String id);

}
