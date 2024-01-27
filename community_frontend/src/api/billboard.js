import request from '@/utils/request'

export function getBillboard() {
  return request({
    url: '/api/billboard/show',
    method: 'get'
  })
}