package com.songpo.community.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.songpo.community.model.entity.BmsPhoto;

import java.util.List;

public interface IBmsPhotoService extends IService<BmsPhoto> {
    /**
     * 获取图片
     * @param nums
     * @return
     */
    List<BmsPhoto> getRandomPhotoList(Integer nums);
}
