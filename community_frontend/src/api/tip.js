import request from '@/utils/request'

export function getTodayTip() {
  return request({
    url: '/api/tip/today',
    method: 'get'
  })
}