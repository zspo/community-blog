package com.songpo.community.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.songpo.community.model.entity.BmsComment;
import com.songpo.community.model.vo.CommentVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BmsCommentMapper extends BaseMapper<BmsComment> {

    /**
     * getCommentsByTopicID
     *
     * @param topic_id
     * @return
     */
    List<BmsComment> getCommentsByTopicID(@Param("topic_id") String topic_id);

    /**
     *
     * @param parent_id
     * @return
     */
    List<BmsComment> getReplyByCommentID(@Param("parent_id") String parent_id);
}
