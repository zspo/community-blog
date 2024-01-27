import request from '@/utils/request'

export function getAllCateInfoList() {
    return request(({
      url: '/api/category',
      method: 'get'
    }))
}

export function getSubCateInfoByCateId(category_id) {
    return request(({
        url: `/api/category/get_subCateInfo/${category_id}`,
        method: 'get',
    }))
}

export function getTopicsByCateName(paramMap) {
    return request({
        url: '/api/category/' + paramMap.name,
        method: 'get',
        params: {
          page: paramMap.page,
          size: paramMap.size
        }
    })
}

export function getTopicsBySubCateName(paramMap) {
    return request({
        url: '/api/category/subcategory/' + paramMap.name,
        method: 'get',
        params: {
          page: paramMap.page,
          size: paramMap.size
        }
    })
}