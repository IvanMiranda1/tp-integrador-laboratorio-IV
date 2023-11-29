import { createRouter, createWebHistory } from "vue-router";
import Home from "../page/Home/index.vue"
import RegistrarLibro from "../page/RegistrarLibro/index.vue"
import ListadoLibro from "../page/ListadoLibro/index.vue"
import ModificarLibro from "../page/ModificarLibro/index.vue"


import RegistrarUsuario from "../page/RegistrarUsuario/index.vue"
import ListadoUsuario from "../page/ListadoUsuario/index.vue"
import ModificarUsuario from "../page/ModificarUsuario/index.vue"

import RegistrarPrestamo from "../page/RegistrarPrestamo/index.vue"
import ListadoPrestamo from "../page/ListadoPrestamo/index.vue"
import ModificarPrestamo from "../page/ModificarPrestamo/index.vue"

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes : [
        {
            path: '/',
            name: 'home',
            component: Home
        },
        {
            path: '/registrarlibro',
            name: 'registrarlibro',
            component: RegistrarLibro
        },
        {
            path: '/listadoLibro',
            name: 'listadoLibro',
            component: ListadoLibro
        },
        {
            path: '/modificarlibro/:id',
            name : 'modificarLibro',
            component: ModificarLibro,
            props: true,
        },
        {
            path: '/registrarUsuario',
            name: 'registrarUsuario',
            component: RegistrarUsuario
        },
        {
            path: '/listadoUsuario',
            name: 'listadoUsuario',
            component: ListadoUsuario
        },
        {
            path: '/modificarUsuario/:id',
            name : 'modificarUsuario',
            component: ModificarUsuario,
            props: true,
        },
        {
            path: '/registrarPrestamo',
            name: 'registrarPrestamo',
            component: RegistrarPrestamo
        },
        {
            path: '/listadoPrestamo',
            name: 'listadoPrestamo',
            component: ListadoPrestamo
        },
        {
            path: '/modificarPrestamo/:id',
            name : 'modificarPrestamo',
            component: ModificarPrestamo,
            props: true,
        }
    ]
})

export default router;