import Vue from 'vue'
import App from './App.vue'
import {store} from './store/store.js'
import '@fortawesome/fontawesome-free/css/all.css'
import '@fortawesome/fontawesome-free/js/all.js'

Vue.config.productionTip = false

new Vue({
    store,
    render: h => h(App),
}).$mount('#app')
