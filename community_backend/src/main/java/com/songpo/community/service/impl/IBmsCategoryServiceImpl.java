package com.songpo.community.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.songpo.community.mapper.BmsCategoryMapper;
import com.songpo.community.mapper.BmsTopicMapper;
import com.songpo.community.model.entity.BmsCategory;
import com.songpo.community.model.entity.BmsPost;
import com.songpo.community.model.vo.PostVO;
import com.songpo.community.service.IBmsCategoryService;
import com.songpo.community.service.IBmsPostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Slf4j
@Service
public class IBmsCategoryServiceImpl extends ServiceImpl<BmsCategoryMapper, BmsCategory> implements IBmsCategoryService {
    @Resource
    private BmsCategoryMapper bmsCategoryMapper;
    @Resource
    private BmsTopicMapper bmsTopicMapper;
    @Autowired
    private IBmsPostService IBmsPostService;

    @Override
    public Set<Map<String, Object>> getSubCateInfo(String category_id, Boolean tree) {
        Set<Map<String, Object>> subCateset = new HashSet<>();
        List<BmsCategory> subCateRes = bmsCategoryMapper.selectList(new LambdaQueryWrapper<BmsCategory>().eq(BmsCategory::getCategoryID, category_id));
        for (BmsCategory subcate : subCateRes) {
            Map<String, Object> subCateMap = new HashMap<>();
            subCateMap.put("id", subcate.getSubcategoryID());
            int subcount = bmsTopicMapper.selectCount(new LambdaQueryWrapper<BmsPost>().eq(BmsPost::getSubcategoryId, subcate.getSubcategoryID()));
            if (tree) {
                subCateMap.put("name", subcate.getSubcategoryName() + "（" + Integer.toString(subcount) + "）");
            } else {
                subCateMap.put("name", subcate.getSubcategoryName());
            }
            subCateMap.put("counts", subcount);
            subCateset.add(subCateMap);
        }
        return subCateset;
    }

    @Override
    public Page<PostVO> selectTopicsByCateName(Page<PostVO> page, String name, String flag) {
        Page<PostVO> iPage = this.baseMapper.selectTopicIdsByCateName(page, name, flag);
        IBmsPostService.setTopicTags(iPage);
        return iPage;
    }
}
