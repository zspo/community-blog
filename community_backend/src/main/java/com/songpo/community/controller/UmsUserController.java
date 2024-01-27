package com.songpo.community.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.songpo.community.common.api.ApiResult;
import com.songpo.community.mapper.UmsUserMapper;
import com.songpo.community.model.dto.LoginDTO;
import com.songpo.community.model.dto.RegisterDTO;
import com.songpo.community.model.entity.BmsPost;
import com.songpo.community.model.entity.UmsUser;
import com.songpo.community.model.vo.PostVO;
import com.songpo.community.model.vo.ProfileVO;
import com.songpo.community.service.IBmsPostService;
import com.songpo.community.service.IUmsUserService;
import com.songpo.community.jwt.JwtUtil;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.util.Assert;
import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/ums/user")
public class UmsUserController extends BaseController {

    @Resource
    private UmsUserMapper umsUserMapper;
    @Resource
    private IUmsUserService iUmsUserService;
    @Resource
    private IBmsPostService iBmsPostService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ApiResult<Map<String, Object>> register(@Valid @RequestBody RegisterDTO dto) {
        UmsUser user = iUmsUserService.executeRegister(dto);
        if (ObjectUtils.isEmpty(user)) {
            return ApiResult.failed("账号注册失败");
        }
        Map<String, Object> map = new HashMap<>(16);
        map.put("user", user);
        return ApiResult.success(map);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ApiResult<Map<String, String>> login(@Valid @RequestBody LoginDTO dto) {
        String token = iUmsUserService.executeLogin(dto);
        if (ObjectUtils.isEmpty(token)) {
            return ApiResult.failed("账号密码错误");
        }
        Map<String, String> map = new HashMap<>(16);
        map.put("token", token);
        return ApiResult.success(map, "登录成功");
    }

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public ApiResult<UmsUser> getUser(@RequestHeader(value = JwtUtil.USER_NAME) String userName) {
        UmsUser user = iUmsUserService.getUserByUsername(userName);
        return ApiResult.success(user);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ApiResult<Object> logOut() {
        return ApiResult.success(null, "注销成功");
    }

    @GetMapping("/{username}")
    public ApiResult<Map<String, Object>> getUserByName(@PathVariable("username") String username,
                                                        @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
                                                        @RequestParam(value = "size", defaultValue = "10") Integer size) {
        Map<String, Object> map = new HashMap<>(16);
        ProfileVO user = iUmsUserService.getUserProfileByUsername(username);
        Assert.notNull(user, "用户不存在");
//        Page<BmsPost> page = iBmsPostService.page(new Page<>(pageNo, size),
//                new LambdaQueryWrapper<BmsPost>().eq(BmsPost::getUserId, user.getId()));
        Page<PostVO> page = umsUserMapper.selectTopicIdsByUserId(new Page<>(pageNo, size), user.getId());
        map.put("user", user);
        map.put("topics", page);
        return ApiResult.success(map);
    }
    @PostMapping("/update")
    public ApiResult<UmsUser> updateUser(@RequestBody UmsUser umsUser) {
        iUmsUserService.updateById(umsUser);
        return ApiResult.success(umsUser);
    }
}
