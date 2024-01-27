package com.songpo.community.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.songpo.community.model.entity.BmsCategory;
import com.songpo.community.model.vo.PostVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BmsCategoryMapper extends BaseMapper<BmsCategory> {
    BmsCategory selectNameById(@Param("category_id") String category_id, @Param("subcategory_id") String subcategory_id);

    Page<PostVO> selectTopicIdsByCateName(@Param("page") Page<PostVO> page, @Param("name") String name, @Param("flag") String flag);

}
