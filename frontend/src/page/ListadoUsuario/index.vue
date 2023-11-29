<template>
    <div class="container">
      <div class="usuarios-container" v-if="usuarios.length > 0">
        <div class="usuario" v-for="usuario in usuarios" :key="usuario.usuario_id">
          <p>Nombre: {{ usuario.nomyape }}</p>
          <p>Usuario: {{ usuario.usuario }}</p>
          <p><button @click="()=>{eliminarUsuario(usuario.usuario_id)}">Eliminar</button></p>
          <p><button @click="()=>{modificarUsuario(usuario.usuario_id)}">Modificar</button></p>
        </div>
      </div>
    </div>
</template>

<script setup>
import axios from 'axios';
import {ref, onMounted} from 'vue';
import {useRouter} from 'vue-router';
const router = useRouter();
const usuarios = ref([])

const modificarUsuario = (id) => {
    try{
        router.push({name: 'modificarUsuario', params: {id:id}})
    }catch(error){
        console.error("Error en modificarUsuario:" + id, error);
    }
}

const eliminarUsuario = async (id) =>{
    try{
        const res = await axios.delete(`http://localhost:8080/api/usuario/${id}`)
        console.log(`Usuario con ID ${id} eliminado`);
        fetchUsuarios();
    } catch (error) {
        console.log(error)
        console.log(id)
    }
}


const fetchUsuarios = async () => {
    try {
        const res = await axios.get("http://localhost:8080/api/usuario")
        usuarios.value = res.data
    } catch (error) {
        console.log(error)
    }
}

onMounted(()=>{
  fetchUsuarios();
})

</script>

<style scoped>
.container {
    display: flex;
    flex-direction: column;
    align-items: center;
    padding-top: 30px;
}

.usuario-container {
    display: flex;
    flex-wrap: wrap;
    gap: 40px;
}

.usuario {
    padding: 10px;
    box-shadow: 3px 3px 15px black;
    margin-bottom: 10px;
    width: 300px;
    height: 100px;
    overflow: auto;
}
</style>
