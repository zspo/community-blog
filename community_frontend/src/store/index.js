import Vue from 'vue'
import Vuex from 'vuex'
import getters from './getters'
import user from './modules/user'

Vue.use(Vuex)

const store = new Vuex.Store({
    modules: {
        user
    },
    getters
})  

export default store

export const state = () => ({
    isTopbarBlock: true, // 顶部栏是否显示
})
  
export const mutations = {
    UPDATE_TOPBAR_BLOCK(state, payload){
        if (state.isTopbarBlock !== payload) {
        state.isTopbarBlock = payload
        }
    }
}