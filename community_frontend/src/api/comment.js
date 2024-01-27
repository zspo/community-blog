import request from '@/utils/request'

export function fetchCommentsByTopicId(topic_Id) {
  return request({
    url: '/api/comment/get_comments',
    method: 'get',
    params: {
      topic_id: topic_Id
    }
  })
}

export function pushComment(data) {
  return request({
    url: '/api/comment/add_comment',
    method: 'post',
    data: data
  })
}

