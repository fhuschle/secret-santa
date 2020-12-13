import Vue from 'vue'
import App from './App.vue'
import router from './router'
import vuetify from './plugins/vuetify';
import axios from "axios";

Vue.config.productionTip = false
/*TODO: Remove setting the access control header globally*/
/*axios.defaults.headers.common['Access-Control-Allow-Origin'] = '*';*/
Vue.prototype.$axios = axios;

/*TODO: Remove slow cors workaround*/
export const baseUrl = process.env.NODE_ENV === "production" ? 'http://' + location.host + '/api/' : 'http://localhost:8084/';

new Vue({
  router,
  vuetify,
  render: h => h(App)
}).$mount('#app')
