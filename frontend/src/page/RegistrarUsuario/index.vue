<template>
    <div class="container">
      <form @submit.prevent="registrarUsuario">
        <h1>Registrar Usuario</h1>
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
</template>

<script setup>
import {ref} from 'vue';
import axios from 'axios';
const usuario = ref({
  usuario: "",
  nomyape: ""
})
const registrarUsuario = async () =>{
    try{
        console.log(usuario.value)
        const res = await axios.post("http://localhost:8080/api/usuario", usuario.value)
}   catch(error){
        console.log(error)
    }
}
</script>


<style scoped>
.container {
    display: flex;
    flex-direction: column;
    align-items: center;
    margin-top: 200px;
}
h1{
    margin-bottom: 20px;
    
}
form {
    display: flex;
    flex-direction: column;
    width: 500px;
    padding-left: 100px;
    padding-right: 100px;
    padding-top: 25px;
    padding-bottom: 25px;
    box-shadow: 0px 0px 10px  black;
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
    border: 1px solid black;
    box-shadow: 3px 4px 5px -5px black;
    border-radius: 4px;
}
input[type="submit"]{
    background-color: #646cff;
    color: black;
    cursor: pointer;
}
input[type="submit"]:hover{
    background-color: #535bf2;
}
select{
    height: 200px;
    border: 1px solid black;
    box-shadow: 3px 4px 5px -5px black;
    padding: 10px;
    border-radius: 6px;
    background-color: #e5e5cc;
    overflow: hidden;
}
</style>