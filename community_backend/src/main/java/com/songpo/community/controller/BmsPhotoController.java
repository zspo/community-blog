package com.songpo.community.controller;

import com.songpo.community.common.api.ApiResult;
import com.songpo.community.mapper.BmsPhotoMapper;
import com.songpo.community.model.entity.BmsPhoto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/photo")
public class BmsPhotoController extends BaseController{
    @Resource
    private BmsPhotoMapper bmsPhotoMapper;

    @GetMapping("/getRandomPhoto")
    public ApiResult<List<BmsPhoto>> getRandomPhoto(@RequestParam(value="nums", defaultValue="10") Integer nums) {
        List<BmsPhoto> res = bmsPhotoMapper.getRandomPhotoList(nums);
        return ApiResult.success(res);
    }
}
