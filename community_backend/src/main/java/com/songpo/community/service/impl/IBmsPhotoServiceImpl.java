package com.songpo.community.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.songpo.community.mapper.BmsPhotoMapper;
import com.songpo.community.model.entity.BmsPhoto;
import com.songpo.community.service.IBmsPhotoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IBmsPhotoServiceImpl extends ServiceImpl<BmsPhotoMapper, BmsPhoto> implements IBmsPhotoService {

    @Override
    public List<BmsPhoto> getRandomPhotoList(Integer nums) {
        List<BmsPhoto> res = this.baseMapper.getRandomPhotoList(nums);
        return res;
    }
}
