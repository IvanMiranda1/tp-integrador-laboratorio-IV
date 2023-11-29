<template>
    <div class="container">
      <form @submit.prevent="registrarPrestamo">
        <h1>Registrar Prestamo</h1>
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
const generos = ref([])
const getParams = ()=>{
    id.value = route.params.id || "";
}

const libros = ref([]);
const usuarios = ref([]);
const prestamo = ref({
  libro_id:'',
  usuario_id:'',
  fecha_prestamo:"",
  estado: false,
})

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

const registrarPrestamo = async ()=> {
  try {
    const res = await axios.post("http://localhost:8080/api/prestamo",prestamo.value)
    console.log(res)
  } catch (error) {
    console.log(error)
  }
}

const getPrestamoById = async (id) => {
    try {
        const res = await axios.get(`http://localhost:8080/api/libro/${id.value}`)
        console.log(res.data)
        libro.value = res.data;
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
    width: 550px;
    padding-left: 100px;
    padding-right: 100px;
    padding-top: 25px;
    padding-bottom: 25px;
    margin-top: 150px;
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
    border: 1px solid black;
    box-shadow: 3px 4px 5px -5px black;
    padding: 10px;
    border-radius: 6px;
    background-color: #e5e5cc;
    overflow: hidden;
}
select:checked {
  background-color: black;
}
</style>