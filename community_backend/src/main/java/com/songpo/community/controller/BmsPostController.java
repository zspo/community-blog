package com.songpo.community.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.songpo.community.api.QiniuCloudService;
import com.songpo.community.common.api.ApiResult;
import com.songpo.community.common.api.IErrorCode;
import com.songpo.community.common.exception.ApiException;
import com.songpo.community.model.dto.CreateTopicDTO;
import com.songpo.community.model.entity.BmsPost;
import com.songpo.community.model.entity.UmsUser;
import com.songpo.community.model.vo.PostVO;
import com.songpo.community.service.IBmsPhotoService;
import com.songpo.community.service.IBmsPostService;
import com.songpo.community.service.IBmsTopicTagService;
import com.songpo.community.service.IUmsUserService;
import com.songpo.community.jwt.JwtUtil;
import com.songpo.community.utils.UUID;
import com.vdurmont.emoji.EmojiParser;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.lang3.StringUtils;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.xml.crypto.Data;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/post")
public class BmsPostController extends BaseController {

    @Resource
    private IBmsPostService iBmsPostService;
    @Resource
    private IBmsPhotoService iBmsPhotoService;
    @Resource
    private IUmsUserService umsUserService;
    @Resource
    private IBmsTopicTagService iBmsTopicTagService;
    @Resource
    private QiniuCloudService qiniuCloudService;

    @GetMapping("/list")
    public ApiResult<Page<PostVO>> list(@RequestParam(value = "tab", defaultValue = "latest") String tab,
                                        @RequestParam(value = "pageNo", defaultValue = "1")  Integer pageNo,
                                        @RequestParam(value = "size", defaultValue = "10") Integer pageSize) {
        Page<PostVO> list = iBmsPostService.getList(new Page<>(pageNo, pageSize), tab);
        return ApiResult.success(list);
    }

    @GetMapping("/listLife")
    public ApiResult<List<PostVO>> listLife() {
        List<PostVO> list = iBmsPostService.getLifeList();
        return ApiResult.success(list);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ApiResult<BmsPost> create(@RequestHeader(value = JwtUtil.USER_NAME) String userName
            , @RequestBody CreateTopicDTO dto) {
        UmsUser user = umsUserService.getUserByUsername(userName);
        BmsPost topic = iBmsPostService.create(dto, user);
        return ApiResult.success(topic);
    }
    @GetMapping()
    public ApiResult<Map<String, Object>> view(@RequestParam("id") String id) {
        Map<String, Object> map = iBmsPostService.viewTopic(id);
        return ApiResult.success(map);
    }

    @GetMapping("/life")
    public ApiResult<Map<String, Object>> viewLife(@RequestParam("id") String id) {
        Map<String, Object> map = iBmsPostService.viewLifeDetail(id);
        return ApiResult.success(map);
    }

    @GetMapping("/hot")
    public ApiResult<List<BmsPost>> getHotPost(@RequestParam("mode") String mode) {
        List<BmsPost> list = iBmsPostService.getHotPost(mode);
        return ApiResult.success(list);
    }

    @GetMapping("/recommend")
    public ApiResult<List<BmsPost>> getRecommend(@RequestParam("topicId") String id) {
        List<BmsPost> topics = iBmsPostService.getRecommend(id);
        return ApiResult.success(topics);
    }

    @PostMapping("/update")
    public ApiResult<BmsPost> update(@RequestHeader(value = JwtUtil.USER_NAME) String userName, @Valid @RequestBody BmsPost post) {
        UmsUser umsUser = umsUserService.getUserByUsername(userName);
        Assert.isTrue(umsUser.getId().equals(post.getUserId()), "非本人无权修改");
        post.setModifyTime(new Date());
        post.setContent(EmojiParser.parseToAliases(post.getContent()));
        iBmsPostService.updateById(post);
        return ApiResult.success(post);
    }

    @DeleteMapping("/delete/{id}")
    public ApiResult<String> delete(@RequestHeader(value = JwtUtil.USER_NAME) String userName, @PathVariable("id") String id) {
        UmsUser umsUser = umsUserService.getUserByUsername(userName);
        BmsPost byId = iBmsPostService.getById(id);
        Assert.notNull(byId, "来晚一步，话题已不存在");
        Assert.isTrue(byId.getUserId().equals(umsUser.getId()), "你为什么可以删除别人的话题？？？");
        iBmsPostService.removeById(id);
        Map<String, Object> columnMap = new HashMap<>();
        columnMap.put("topic_id",id);
        iBmsTopicTagService.removeByMap(columnMap);
        iBmsPhotoService.removeByMap(columnMap);
        return ApiResult.success(null,"删除成功");
    }

    @PostMapping("/api/upload_file")
    public void fileUpLoadToQiNiuCloud(HttpServletRequest request,
                                       HttpServletResponse response,
                                       @RequestParam(name = "image-file", required = true)
                                       MultipartFile file){
        //文件上传
        try {
            request.setCharacterEncoding( "utf-8" );
            response.setHeader( "Content-Type" , "text/html" );

            String fileName = getFileKey(file.getOriginalFilename()).replaceFirst("/","");

            String return_url = qiniuCloudService.upload(file, fileName);

            response.getWriter().write( "{\"success\": 1, \"message\":\"上传成功\",\"url\":\"" + return_url + "\"}" );
        } catch (IOException e) {
            e.printStackTrace();
            try {
                response.getWriter().write( "{\"success\":0}" );
            } catch (IOException e1) {
                throw ApiException.withErrorCode((IErrorCode) e1);
            }
            throw null;
        }
    }

    private static String getFileKey(String name) {
        String prefix = "/upload_blog/" + DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDate.now());

        name = StringUtils.trimToNull(name);
        if (name == null) {
            return prefix + "/" + UUID.UU32() + "." + null;
        } else {
            name = name.replace('\\', '/');
            name = name.substring(name.lastIndexOf("/") + 1);
            int index = name.lastIndexOf(".");
            String ext = null;
            if (index >= 0) {
                ext = StringUtils.trimToNull(name.substring(index + 1));
            }
            return prefix + "/" + UUID.UU32() + "." + (ext == null ? null : (ext));
        }
    }

    public static void main(String[] args) {
        String a = "fsdafsf/fasdfsf.png";
        System.out.println(getFileKey(a));
    }

}
