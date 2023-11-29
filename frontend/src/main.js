import { createApp } from 'vue'
import axios from 'axios'
import VueAxios from 'vue-axios'
import router from './router'
import './style.css'
import App from './App.vue'

const app = createApp(App)
app.use(VueAxios, axios)
app.use(router)
app.mount('#app')
