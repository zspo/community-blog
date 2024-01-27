import request from '@/utils/request'

// 关注
export function follow(id) {
  return request(({
    url: `/api/relationship/subscribe/${id}`,
    method: 'get'
  }))
}

// 关注
export function unFollow(id) {
  return request(({
    url: `/api/relationship/unsubscribe/${id}`,
    method: 'get'
  }))
}

// 验证是否关注
export function hasFollow(topicUserId) {
  return request(({
    url: `/api/relationship/validate/${topicUserId}`,
    method: 'get'
  }))
}
