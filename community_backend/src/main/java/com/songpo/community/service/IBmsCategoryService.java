package com.songpo.community.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.songpo.community.model.entity.BmsCategory;
import com.songpo.community.model.vo.PostVO;

import java.util.Map;
import java.util.Set;

public interface IBmsCategoryService extends IService<BmsCategory> {

    Set<Map<String, Object>> getSubCateInfo(String category_id, Boolean tree);

    Page<PostVO> selectTopicsByCateName(Page<PostVO> page, String name, String flag);

}
