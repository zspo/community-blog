package com.songpo.community.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.songpo.community.mapper.BmsFollowMapper;
import com.songpo.community.model.entity.BmsFollow;
import com.songpo.community.service.IBmsFollowService;
import org.springframework.stereotype.Service;


@Service
public class IBmsFollowServiceImpl extends ServiceImpl<BmsFollowMapper, BmsFollow> implements IBmsFollowService {
}
