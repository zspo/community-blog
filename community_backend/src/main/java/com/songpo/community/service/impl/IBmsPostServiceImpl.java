package com.songpo.community.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.songpo.community.mapper.*;
import com.songpo.community.model.dto.CreateTopicDTO;
import com.songpo.community.model.entity.*;
import com.songpo.community.model.vo.PostVO;
import com.songpo.community.model.vo.ProfileVO;
import com.songpo.community.service.IBmsPostService;
import com.songpo.community.service.IBmsTagService;
import com.songpo.community.service.IUmsUserService;
import com.songpo.community.utils.Commons;
import com.songpo.community.utils.DateKit;
import com.vdurmont.emoji.EmojiParser;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class IBmsPostServiceImpl extends ServiceImpl<BmsTopicMapper, BmsPost> implements IBmsPostService {
    @Resource
    private BmsTagMapper bmsTagMapper;
    @Resource
    private UmsUserMapper umsUserMapper;
    @Resource
    private BmsCategoryMapper bmsCategoryMapper;
    @Resource
    private BmsPhotoMapper bmsPhotoMapper;


    @Autowired
    @Lazy
    private IBmsTagService iBmsTagService;

    @Autowired
    private IUmsUserService iUmsUserService;
    @Autowired
    private com.songpo.community.service.IBmsTopicTagService IBmsTopicTagService;


    @Override
    public Page<PostVO> getList(Page<PostVO> page, String tab) {
        // 查询话题
        Page<PostVO> iPage = this.baseMapper.selectListAndPage(page, tab);
        // 查询话题的标签
        setTopicTags(iPage);
        return iPage;
    }

    @Override
    public List<PostVO> getLifeList() {
        List<PostVO> res = this.baseMapper.selectLifePost();
        return res;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BmsPost create(CreateTopicDTO dto, UmsUser user) {
        BmsPost topic1 = this.baseMapper.selectOne(new LambdaQueryWrapper<BmsPost>().eq(BmsPost::getTitle, dto.getTitle()));
        Assert.isNull(topic1, "话题已存在，请修改");

        // 封装
        BmsPost topic = BmsPost.builder()
                .userId(user.getId())
                .title(dto.getTitle())
                .cover(Commons.show_thumb(dto.getContent()))
                .content(EmojiParser.parseToAliases(dto.getContent()))
                .categoryId(dto.getCategoryId())
                .subcategoryId(dto.getSubcategoryId())
                .createTime(new Date())
                .build();
        this.baseMapper.insert(topic);

        // 用户积分增加
        int newScore = user.getScore() + 1;
        umsUserMapper.updateById(user.setScore(newScore));

        // 标签
        if (!ObjectUtils.isEmpty(dto.getTags())) {
            // 保存标签
            List<BmsTag> tags = iBmsTagService.insertTags(dto.getTags());
            // 处理标签与话题的关联
            IBmsTopicTagService.createTopicTag(topic.getId(), tags);
        }

        // 如果是life，获取图片插入photo
        if (dto.getCategoryId().equals("103")) {
            List<String> img_list = Commons.show_all_thumb(topic.getContent());
            for (String img_url : img_list) {
                BmsPhoto photo = BmsPhoto.builder()
                        .imgUrl(img_url)
                        .topicId(topic.getId())
                        .createTime(new Date())
                        .build();
                bmsPhotoMapper.insert(photo);
            }
        }
        return topic;
    }

    @Override
    public Map<String, Object> viewTopic(String id) {
        Map<String, Object> map = new HashMap<>(16);
        BmsPost topic = this.baseMapper.selectById(id);
        Assert.notNull(topic, "当前话题不存在,或已被作者删除");
        // 查询话题详情
        topic.setView(topic.getView() + 1);
        this.baseMapper.updateById(topic);
        // emoji转码
        topic.setContent(EmojiParser.parseToUnicode(topic.getContent()));
        map.put("topic", topic);
        // 分类
        BmsCategory category = bmsCategoryMapper.selectNameById(topic.getCategoryId(), topic.getSubcategoryId());
        map.put("category_name", category.getCategoryName());
        map.put("subcategory_name", category.getSubcategoryName());
        // 标签
        QueryWrapper<BmsTopicTag> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(BmsTopicTag::getTopicId, topic.getId());
        Set<String> set = new HashSet<>();
        for (BmsTopicTag articleTag : IBmsTopicTagService.list(wrapper)) {
            set.add(articleTag.getTagId());
        }
        List<BmsTag> tags = iBmsTagService.listByIds(set);
        map.put("tags", tags);

        // 作者

        ProfileVO user = iUmsUserService.getUserProfile(topic.getUserId());
        map.put("user", user);

        return map;
    }

    @Override
    public Map<String, Object> viewLifeDetail(String id) {
        Map<String, Object> map = new HashMap<>(16);
        BmsPost topic = this.baseMapper.selectById(id);
        Assert.notNull(topic, "当前话题不存在,或已被作者删除");
        // 查询话题详情
        topic.setView(topic.getView() + 1);
        this.baseMapper.updateById(topic);
        // emoji转码

        map.put("title", topic.getTitle());

        String c_time = Commons.getLifeCreateTime(DateKit.dateFormat(topic.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
        map.put("topic_c_time", c_time);

        List<String> p_list = Commons.show_all_p(topic.getContent());
        map.put("topic_p_list", p_list);

        List<String> img_list = Commons.show_all_thumb(topic.getContent());
        map.put("topic_img_list", img_list);

        // 分类
        BmsCategory category = bmsCategoryMapper.selectNameById(topic.getCategoryId(), topic.getSubcategoryId());
        map.put("category_name", category.getCategoryName());
        map.put("subcategory_name", category.getSubcategoryName());
        // 标签
        QueryWrapper<BmsTopicTag> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(BmsTopicTag::getTopicId, topic.getId());
        Set<String> set = new HashSet<>();
        for (BmsTopicTag articleTag : IBmsTopicTagService.list(wrapper)) {
            set.add(articleTag.getTagId());
        }
        List<BmsTag> tags = iBmsTagService.listByIds(set);
        map.put("tags", tags);

        // 作者

        ProfileVO user = iUmsUserService.getUserProfile(topic.getUserId());
        map.put("user", user);

        return map;
    }

    @Override
    public List<BmsPost> getHotPost(String mode) {
        return this.baseMapper.getHotPost(mode);
    }

    @Override
    public List<BmsPost> getRecommend(String id) {
        return this.baseMapper.selectRecommend(id);
    }
    @Override
    public Page<PostVO> searchByKey(String keyword, Page<PostVO> page) {
        // 查询话题
        Page<PostVO> iPage = this.baseMapper.searchByKey(page, keyword);
        // 查询话题的标签
        setTopicTags(iPage);
        return iPage;
    }

    public void setTopicTags(Page<PostVO> iPage) {
        iPage.getRecords().forEach(topic -> {
            List<BmsTopicTag> topicTags = IBmsTopicTagService.selectByTopicId(topic.getId());
            if (!topicTags.isEmpty()) {
                List<String> tagIds = topicTags.stream().map(BmsTopicTag::getTagId).collect(Collectors.toList());
                List<BmsTag> tags = bmsTagMapper.selectBatchIds(tagIds);
                topic.setTags(tags);
            }
        });
    }
}
