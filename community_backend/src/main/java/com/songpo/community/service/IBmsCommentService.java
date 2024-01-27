package com.songpo.community.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.songpo.community.model.dto.CommentDTO;
import com.songpo.community.model.entity.BmsComment;
import com.songpo.community.model.entity.UmsUser;
import com.songpo.community.model.vo.CommentVO;

import java.util.List;
import java.util.Map;


public interface IBmsCommentService extends IService<BmsComment> {
    /**
     *
     *
     * @param topic_id
     * @return {@link BmsComment}
     */
    List<Map<String, Object>> getCommentsByTopicID(String topic_id);

    BmsComment create(CommentDTO dto, UmsUser principal);
}
