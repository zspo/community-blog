package com.songpo.community.controller;

import com.songpo.community.common.api.ApiResult;
import com.songpo.community.model.dto.CommentDTO;
import com.songpo.community.model.entity.BmsComment;
import com.songpo.community.model.entity.UmsUser;
import com.songpo.community.model.vo.CommentVO;
import com.songpo.community.service.IBmsCommentService;
import com.songpo.community.service.IUmsUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

import static com.songpo.community.jwt.JwtUtil.USER_NAME;


@RestController
@RequestMapping("/comment")
public class BmsCommentController extends BaseController {

    @Resource
    private IBmsCommentService bmsCommentService;
    @Resource
    private IUmsUserService umsUserService;

    @GetMapping("/get_comments")
    public ApiResult<List<Map<String, Object>>> getCommentsByTopicID(@RequestParam(value = "topic_id", defaultValue = "1") String topic_id) {
        List<Map<String, Object>> lstBmsComment = bmsCommentService.getCommentsByTopicID(topic_id);
        return ApiResult.success(lstBmsComment);
    }
    @PostMapping("/add_comment")
    public ApiResult<BmsComment> add_comment(@RequestHeader(value = USER_NAME) String userName,
                                             @RequestBody CommentDTO dto) {
        UmsUser user = umsUserService.getUserByUsername(userName);
        BmsComment comment = bmsCommentService.create(dto, user);
        return ApiResult.success(comment);
    }
}
