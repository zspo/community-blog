import request from '@/utils/request'

export function getRandomImg() {
  return request({
    url: '/api/image/random',
    method: 'get'
  })
}