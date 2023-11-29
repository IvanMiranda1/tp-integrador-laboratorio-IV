<template>
    <div class="container">
        <div v-if="id != ''">
            <h1>Modificar su libro</h1>
            <form @submit.prevent="modificarLibro">
                <div>
                    <label for="libro_id">ID del Libro:</label>
                    <input type="text" id="libro_id" v-model="libro.libro_id" required onlyread>
                </div>
                <div>
                    <label for="titulo">Titulo del libro:</label>
                    <input type="text" id="titulo" v-model="libro.titulo" required>
                </div>
                <div>
                    <label for="autor">Autor:</label>
                    <input type="text" id="autor" v-model="libro.autor" required>
                </div>
                <div>
                    <label for="genero">Genero:</label>
                    <select id="genero" v-model="libro.generos_id" multiple>
                        <option v-for="genero in generos" :key="genero.genero_id" :value="genero.genero_id">
                            {{ genero.nombre }}
                        </option>
                    </select>
                </div>
                <div>
                    <label for="cantpag">Páginas:</label>
                    <input type="number" id="cantpag" v-model="libro.cantpag" required>
                </div>
                <div>
                    <label for="sinopsis">Sinopsis:</label>
                    <input type="text" id="sinopsis" v-model="libro.sinopsis" required>
                </div>
                <input type="submit" value="Modificar libro">
            </form>
        </div>
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

const libro = ref({})

const getLibroById = async (id) => {
    try {
        const res = await axios.get(`http://localhost:8080/api/libro/${id.value}`)
        console.log(res.data)
        libro.value = res.data;
    } catch(error){
        console.log(error);
    }
}

const fetchGeneros = async () =>{
    try{
        const res = await axios.get("http://localhost:8080/api/genero");
        generos.value = res.data;
    } catch(error){
        console.log(error)
    }
}   

const modificarLibro = async () => {
    try{
        const res = await axios.put(`http://localhost:8080/api/libro`, libro.value)
        router.push({name: 'listadoLibro'})
    } catch (error) {
        console.log("error en modificarLibro",error)
    }   
}

onMounted(()=>{
    getParams();
    fetchGeneros();
    getLibroById(id);
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