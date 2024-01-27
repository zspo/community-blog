package com.songpo.community.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.songpo.community.model.entity.BmsTip;

public interface IBmsTipService extends IService<BmsTip> {
    BmsTip getRandomTip();
}
