<template>
    <div class="container">
        <div>
            <form class="buscar" @submit.prevent="busquedaStr">
                <div>
                    <label for="busqueda">Busqueda por titulo: </label>
                    <input type="text" id="busqueda" v-model="busqueda.busqueda" required>
                </div>
                <button type="submit">Buscar</button>
            </form>
        </div>
        <div class="libro-container" v-if="libros.length > 0">
            <div class="libro" v-for="libro in libros" :key="libro.libro_id">
                <h3>{{ libro.titulo }}</h3>
                <p>Autor: {{ libro.autor }}</p>
                <p>Generos: 
                    <div v-for="id in libro.generos_id">
                        <span v-if="generos.find(genero => genero.genero_id === id)">
                            {{ generos.find(genero => genero.genero_id === id).nombre }}
                        </span>
                    </div>
                </p>
                <p>Cantidad de paginas: {{ libro.cantpag }}</p>
                <p>Sinopsis: {{ libro.sinopsis }}</p>
                <p><button @click="()=>{eliminarLibro(libro.libro_id)}">Eliminar</button></p>
                <p><button @click="()=>{modificarLibro(libro.libro_id)}">Modificar</button></p>
            </div>
        </div>
    </div>
</template>

<script setup>
import axios from 'axios';
import {ref, onMounted} from 'vue';
import {useRouter} from 'vue-router';
const router = useRouter();

const libros = ref([])
const generos = ref([])

const busqueda = ref({
    busqueda: "",
})
/*
const busquedaStr = async ()=>{
    try {
        router.push({name: 'busquedaLibro', params: {busq:busqueda.busqueda}})
    } catch (error) {
        console.error("Error en busquedaLibro" + id, error);
    }
}
*/

const busquedaStr = async () => {
    try {
        const str = busqueda.value.busqueda;
        const res = await axios.get(`http://localhost:8080/api/libro/busq/${str}`)
        libros.value = res.data
    } catch (error) {
        console.log(error)
    }
}

const modificarLibro = (id) => {
    try{
        router.push({name: 'modificarLibro', params: {id:id}})
    }catch(error){
        console.error("Error en modificarLibro:" + id, error);
    }
}

const fetchLibros = async () => {
    try {
        const res = await axios.get("http://localhost:8080/api/libro/conGenero")
        libros.value = res.data
    } catch (error) {
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

const eliminarLibro = async (id) =>{
    try{
        const res = await axios.delete(`http://localhost:8080/api/libro/${id}`)
        console.log(`Libro con ID ${id} eliminado`);
        fetchLibros();
    } catch (error) {
        console.log(error)
    }
}

onMounted(()=>{
    fetchLibros()
    fetchGeneros()
})
</script>


<style scoped>

.container {
    display: flex;
    flex-direction: column;
    align-items: center;
    padding-top: 30px;
}

.libro-container {
    display: flex;
    flex-wrap: wrap;
    gap: 40px;
}

.libro {
    padding: 10px;
    box-shadow: 3px 3px 15px black;
    margin-bottom: 10px;
    width: 300px;
    height: 300px;
    overflow: auto;
}
.buscar {
    display: flex;
    padding: 10px;
    gap: 10px;
}


</style>