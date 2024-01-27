import request from '@/utils/request'

// 列表
export function getList(pageNo, size, tab) {
  return request(({
    url: '/api/post/list',
    method: 'get',
    params: { pageNo: pageNo, size: size, tab: tab }
  }))
}

// 发布
export function post(topic) {
  return request({
    url: '/api/post/create',
    method: 'post',
    data: topic
  })
}

// 浏览
export function getTopic(id) {
  return request({
    url: `/api/post`,
    method: 'get',
    params: {
      id: id
    }
  })
}

// 浏览
export function getLifeDetail(id) {
  return request({
    url: `/api/post/life`,
    method: 'get',
    params: {
      id: id
    }
  })
}

export function getHotPost(mode) {
  return request({
    url: '/api/post/hot',
    method: 'get',
    params: {mode: mode}
  })
}

// 获取详情页推荐
export function getRecommendTopics(id) {
  return request({
    url: '/api/post/recommend',
    method: 'get',
    params: {
      topicId: id
    }
  })
}

export function update(topic) {
  return request({
    url: '/api/post/update',
    method: 'post',
    data: topic
  })
}

export function deleteTopic(id) {
  return request({
    url: `/api/post/delete/${id}`,
    method: 'delete'
  })
}

