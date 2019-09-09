import request from '@/utils/request'

export function getAllRole() {
  return request({
    url: '/role',
    method: 'get'
  })
}

export function getRoleCodeMenu(roleCode) {
  return request({
    url: '/role/' + roleCode + '/menu',
    method: 'get'
  })
}
