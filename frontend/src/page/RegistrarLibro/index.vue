<template>
    <div class="container">
        <form @submit.prevent="registrarLibro">
            <h1>Registre su libro</h1>
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
            <input type="submit" value="Registrar libro">
        </form>
    </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';
const selectGeneros = ref({
    genero:[]
});
const libro = ref({
    titulo:"",
    autor:"",
    generos_id: [],
    cantpag:0,
    sinopsis:""
});
const generos = ref([]);


const registrarLibro = async () =>{
    try{
        console.log(libro.value)
        const res = await axios.post("http://localhost:8080/api/libro", libro.value)
}   catch(error){
        console.log(error)
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

onMounted(()=>{
    fetchGeneros()
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
    width: 500px;
    padding-left: 100px;
    padding-right: 100px;
    padding-bottom: 25px;
    padding-top: 10px;
    margin-top: 15px;
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