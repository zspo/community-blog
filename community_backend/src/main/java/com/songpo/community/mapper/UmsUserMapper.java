package com.songpo.community.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.songpo.community.model.entity.UmsUser;
import com.songpo.community.model.vo.PostVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 用户
 *
 * @author Knox 2020/11/7
 */
@Repository
public interface UmsUserMapper extends BaseMapper<UmsUser> {
    Page<PostVO> selectTopicIdsByUserId(@Param("page") Page<PostVO> page, @Param("id") String id);
}
