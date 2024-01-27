package com.songpo.community.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.songpo.community.mapper.BmsCommentMapper;
import com.songpo.community.mapper.BmsTopicMapper;
import com.songpo.community.mapper.UmsUserMapper;
import com.songpo.community.model.dto.CommentDTO;
import com.songpo.community.model.entity.BmsComment;
import com.songpo.community.model.entity.BmsPost;
import com.songpo.community.model.entity.UmsUser;
import com.songpo.community.model.vo.CommentVO;
import com.songpo.community.service.IBmsCommentService;
import com.songpo.community.service.IBmsPostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;


@Slf4j
@Service
public class IBmsCommentServiceImpl extends ServiceImpl<BmsCommentMapper, BmsComment> implements IBmsCommentService {

    @Resource
    private UmsUserMapper umsUserMapper;
    @Resource
    private BmsTopicMapper bmsTopicMapper;

    @Override
    public List<Map<String, Object>> getCommentsByTopicID(String topic_id) {
        List<Map<String, Object>> list = new ArrayList<>();
        List<BmsComment> lstBmsComment = this.baseMapper.getCommentsByTopicID(topic_id);
        for (BmsComment comment: lstBmsComment) {
            Map<String, Object> com_map = getInfoMapByCommentVO(comment);

            List<Map<String, Object>> replylist = new ArrayList<>();
            List<BmsComment> replyCom = this.baseMapper.getReplyByCommentID(comment.getId());
            for (BmsComment reply: replyCom) {
                Map<String, Object> reply_map = getInfoMapByCommentVO(reply);
                reply_map.put("commentId", reply.getParentId());
                reply_map.put("toId", reply.getReplyId());
                UmsUser user_info = umsUserMapper.selectById(reply.getReplyId());
                reply_map.put("to_user_name", user_info.getUsername());
                reply_map.put("toName", user_info.getAlias());
                reply_map.put("toAvatar", user_info.getAvatar());
                replylist.add(reply_map);
            }

            com_map.put("reply", replylist);
            list.add(com_map);
        }
        return list;
    }

    private Map<String, Object> getInfoMapByCommentVO(BmsComment comment) {
        Map<String, Object> com_map = new HashMap<>();
        com_map.put("id", comment.getId());
        com_map.put("date", comment.getCreateTime());
        com_map.put("topic_id", comment.getTopicId());
        com_map.put("fromId", comment.getUserId());
        UmsUser user_info = umsUserMapper.selectById(comment.getUserId());
        com_map.put("user_name", user_info.getUsername());
        com_map.put("fromName", user_info.getAlias());
        com_map.put("fromAvatar", user_info.getAvatar());
        com_map.put("likeNum", comment.getLike());
        com_map.put("content", comment.getContent());
        return com_map;
    }

    @Override
    public BmsComment create(CommentDTO dto, UmsUser user) {
        BmsComment comment = BmsComment.builder()
                .content(process_content(dto.getContent()))
                .userId(user.getId())
                .topicId(dto.getTopic_id())
                .like(dto.getLike())
                .replyId(dto.getReply_id())
                .parentId(dto.getParent_id())
                .createTime(new Date())
                .build();
        this.baseMapper.insert(comment);

        // update post comment counts
        BmsPost topic = bmsTopicMapper.selectById(dto.getTopic_id());
        topic.setComments(topic.getComments() + 1);
        bmsTopicMapper.updateById(topic);

        return comment;
    }

    private String process_content(String content) {
        if (content.contains("@")) {
            return content.substring(content.indexOf(" ")+1,content.length());
        } else {
            return content;
        }
    }
}
