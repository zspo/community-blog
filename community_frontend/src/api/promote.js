import request from '@/utils/request'

// 获取推广
export function getList() {
  return request(({
    url: '/api/promotion/all',
    method: 'get'
  }))
}
