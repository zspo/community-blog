package com.songpo.community.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.songpo.community.mapper.BmsBillboardMapper;
import com.songpo.community.model.entity.BmsBillboard;
import com.songpo.community.service.IBmsBillboardService;
import org.springframework.stereotype.Service;

@Service
public class IBmsBillboardServiceImpl extends ServiceImpl<BmsBillboardMapper
        , BmsBillboard> implements IBmsBillboardService {

}
