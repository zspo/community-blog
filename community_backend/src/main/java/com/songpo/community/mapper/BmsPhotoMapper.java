package com.songpo.community.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.songpo.community.model.entity.BmsPhoto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BmsPhotoMapper extends BaseMapper<BmsPhoto> {

    List<BmsPhoto> getRandomPhotoList(@Param("nums") Integer nums);
}
