package com.songpo.community.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.songpo.community.common.api.ApiResult;
import com.songpo.community.mapper.BmsCategoryMapper;
import com.songpo.community.mapper.BmsTopicMapper;
import com.songpo.community.model.entity.BmsCategory;
import com.songpo.community.model.entity.BmsPost;
import com.songpo.community.model.vo.PostVO;
import com.songpo.community.service.IBmsCategoryService;
import com.songpo.community.service.IBmsPostService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

@RestController
@RequestMapping("/category")
public class BmsCategoryController extends BaseController {
    @Resource
    private BmsCategoryMapper bmsCategoryMapper;
    @Resource
    private BmsTopicMapper bmsTopicMapper;
    @Resource
    private IBmsCategoryService iBmsCategoryService;
    @Resource
    private IBmsPostService iBmsPostService;

    @GetMapping()
    public ApiResult<Set<Map<String, Object>>> getAllCateInfo() {
        Set<Map<String, Object>> set = new HashSet<>();
        List<BmsCategory> bmsCate = iBmsCategoryService.list();
        for (BmsCategory cate : bmsCate) {
            Map<String, Object> cateMap = new HashMap<>();
            cateMap.put("id", cate.getCategoryID());
//            cateMap.put("name", cate.getCategoryName());
            int count = bmsTopicMapper.selectCount(new LambdaQueryWrapper<BmsPost>().eq(BmsPost::getCategoryId, cate.getCategoryID()));
            cateMap.put("counts", count);

            cateMap.put("name", cate.getCategoryName() + "（" + Integer.toString(count) + "）");

            Set<Map<String, Object>> subCateset = iBmsCategoryService.getSubCateInfo(cate.getCategoryID(), true);

            cateMap.put("subCateList", subCateset);

            set.add(cateMap);
        }
        return ApiResult.success(set);
    }

    @GetMapping("/{name}")
    public ApiResult<Page<PostVO>> getTopicsByCateName(
            @PathVariable("name") String name,
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size) {
        Page<PostVO> topics = iBmsCategoryService.selectTopicsByCateName(new Page<>(page, size), name, "cate");
        return ApiResult.success(topics);
    }

    @GetMapping("/subcategory/{name}")
    public ApiResult<Page<PostVO>> getTopicsBySubCateName(
            @PathVariable("name") String name,
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size) {
        Page<PostVO> topics = iBmsCategoryService.selectTopicsByCateName(new Page<>(page, size), name, "subcate");
        return ApiResult.success(topics);
    }

    @GetMapping("/get_subCateInfo/{category_id}")
    public ApiResult<Set<Map<String, Object>>> getSubCateInfo(@PathVariable("category_id") String category_id) {
        Set<Map<String, Object>> subCateset = new HashSet<>();
        if (category_id.equals("")) {
            return ApiResult.success(subCateset);
        } else {
            subCateset = iBmsCategoryService.getSubCateInfo(category_id, true);
        }
        return ApiResult.success(subCateset);
    }
}
