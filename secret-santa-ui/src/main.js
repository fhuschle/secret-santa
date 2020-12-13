import Vue from 'vue'
import App from './App.vue'
import router from './router'
import vuetify from './plugins/vuetify';
import axios from "axios";

Vue.config.productionTip = false

axios.defaults.headers.common['Access-Control-Allow-Origin'] = '*';
Vue.prototype.$axios = axios;

/*TODO: Remove slow cors workaround*/
export const baseUrl = process.env.NODE_ENV === "production" ? 'https://' + location.host + '/api/' : 'https://cors-anywhere.herokuapp.com/http://localhost:8084/';

new Vue({
  router,
  vuetify,
  render: h => h(App)
}).$mount('#app')
