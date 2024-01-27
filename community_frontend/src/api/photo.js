import request from '@/utils/request'

export function getRandomPhoto(nums) {
    return request({
      url: `/api/photo/getRandomPhoto`,
      method: 'get',
      params: {
        nums: nums
      }
    })
  }