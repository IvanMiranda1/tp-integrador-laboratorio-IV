<template>
    <div class="container">
      <div v-if="id != ''">
        <h1>Modificar usuario</h1>
        <form @submit.prevent="modificarUsuario">
          <div>
            <label for="usuario">Usuario: </label>
            <input type="text" id="usuario" v-model="usuario.usuario" required>
          </div>
          <div>
            <label for="nomyape">Nombre y Apellido: </label>
            <input type="text" id="nomyape" v-model="usuario.nomyape" required>
          </div>
          <input type="submit" value="Registrar usuario">
        </form>
      </div>
    </div>
</template>

<script setup>
import {ref, onMounted} from 'vue';
import {useRouter, useRoute } from 'vue-router';
import axios from 'axios';
const route = useRoute();
const router = useRouter();
const id = ref('')
const getParams = ()=>{
    id.value = route.params.id || "";
}
const usuario = ref({})
const getUsuarioById = async () => {
    try {
        const res = await axios.get(`http://localhost:8080/api/usuario/${id.value}`)
        console.log(res.data)
        usuario.value = res.data;
    } catch(error){
        console.log(error);
    }
}

const modificarUsuario = async () => {
    try{
        const res = await axios.put(`http://localhost:8080/api/usuario`, usuario.value)
        router.push({name: 'listadoUsuario'})
    } catch (error) {
        console.log("error en modificarUsuario",error)
    }   
}


onMounted(()=>{
  getParams();
  getUsuarioById();
})
</script>

<style scoped>
.container {
    display: flex;
    flex-direction: column;
    align-items: center;
}
h1{
    margin-bottom: 20px;
}
form {
    display: flex;
    flex-direction: column;
    width: 250px;
}
form > div {
    display: flex;
    flex-direction: column;
}
label {
    margin-bottom: 5px;
}
input {
    padding: 8px;
    margin-bottom: 20px;
    border: 1pz solid #ddd;
    border-radius: 4px;
    color: #ddd;
}
input[type="submit"]{
    background-color: #646cff;
    color: black;
    cursor: pointer;
}
input[type="submit"]:hover{
    background-color: #535bf2;
}
</style>