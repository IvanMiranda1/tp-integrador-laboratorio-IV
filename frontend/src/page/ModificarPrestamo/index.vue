<template>
    <div class="container">
      <h1>Modificar Prestamo</h1>
      <form @submit.prevent="modificarPrestamo">
        <div>
          <label for="libro">Seleccione un libro: </label>
          <select id="libro" v-model="prestamo.libro_id" >
            <option v-for="libro in libros" :key="libro.libro_id" :value="libro.libro_id">
            ID:{{ libro.libro_id }} || {{ libro.titulo }} de {{ libro.autor }} </option>
          </select>
        </div>
        <div>
          <label for="usuario">Seleccione un usuario: </label>
          <select id="usuario" v-model="prestamo.usuario_id" >
            <option v-for="usuario in usuarios" :key="usuario.usuario_id" :value="usuario.usuario_id">
            ID:{{ usuario.usuario_id }} || {{ usuario.usuario }} </option>
          </select>
        </div>
        <div>
          <label for="fecha_prestamo">Fecha del prestamo: </label>
          <input type="date" id="fecha_prestamo" v-model="prestamo.fecha_prestamo" required>
        </div>
        <input type="submit" value="Registrar prestamo">
      </form>
    </div>
</template>

<script setup>
import {ref, onMounted} from 'vue';
import { useRoute,useRouter } from 'vue-router';
import axios from 'axios';
const route = useRoute();
const router = useRouter();
const id = ref('')
const getParams = ()=>{
    id.value = route.params.id || "";
}

const libros = ref([]);
const usuarios = ref([]);
const prestamo = ref([]);


const fetchLibros = async ()=>{
  try{
    const res = await axios.get("http://localhost:8080/api/libro")
    libros.value = res.data
  } catch(error){
    console.log(error)
  }
}

const fetchUsuarios = async ()=>{
  try{
    const res = await axios.get("http://localhost:8080/api/usuario")
    usuarios.value = res.data
  } catch(error){
    console.log(error)
  }
}

const modificarPrestamo = async () => {
    try{
        const res = await axios.put(`http://localhost:8080/api/prestamo`, prestamo.value)
        router.push({name: 'listadoPrestamo'})
    } catch (error) {
        console.log("error en modificarPrestamo",error)
    }   
}

const getPrestamoById = async (id) => {
    try {
        const res = await axios.get(`http://localhost:8080/api/prestamo/${id.value}`)
        console.log(res.data)
        prestamo.value = res.data;
    } catch(error){
        console.log(error);
    }
}


onMounted(()=>{
  getParams()
  fetchLibros()
  fetchUsuarios()
  getPrestamoById(id)
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
