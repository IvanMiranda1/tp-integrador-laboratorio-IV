<template>
    <div class="container">
      <div class="prestamos-container" v-if="prestamos && prestamos.length > 0">
        <div class="prestamo" v-for="prestamo in prestamos" :key="prestamo.prestamo_id">
          <p>ID Prestamo: {{ prestamo.prestamo_id }}</p>
          <p>Fecha de inicio de prestamo: {{ prestamo.fecha_prestamo }}</p>
          <div>
            <p v-if="prestamo.estado">Estado: El préstamo no está activo. Ya no se permite su modificacion.</p>
            <p v-else>Estado: El préstamo está activo.</p>
          </div>
          <p><button @click="()=>{devuelto(prestamo.prestamo_id)}">Marcar como devuelto</button></p>
          <p><button @click="()=>{eliminarPrestamo(prestamo.prestamo_id)}">Eliminar</button></p>
          <p><button @click="()=>{modificarPrestamo(prestamo.prestamo_id,prestamo.estado)}">Modificar</button></p>
          <table>
            <tr>
              <th>ID Libro</th>
              <th>Titulo</th>
              <th>Autor</th>
              <th>Cant.Pag</th>
              <th>Sinopsis</th>
            </tr>
            <tr>
              <td>{{ prestamo.libro.libro_id }}</td>
              <td>{{ prestamo.libro.titulo }}</td>
              <td>{{ prestamo.libro.autor }}</td>
              <td>{{ prestamo.libro.cantpag }}</td>
              <td>{{ prestamo.libro.sinopsis }}</td>
            </tr>
          </table>
          <table>
            <tr>
              <th>ID Usuario</th>
              <th>Nombre y apellido</th>
              <th>Usuario</th>
            </tr>
            <tr>
              <td>{{ prestamo.usuario.usuario_id }}</td>
              <td>{{ prestamo.usuario.nomyape }}</td>
              <td>{{ prestamo.usuario.usuario }}</td>
            </tr>
          </table>
        </div>
      </div>
    </div>
</template>

<script setup>
import axios from 'axios';
import {ref, onMounted} from 'vue';
import {useRouter} from 'vue-router';
const router = useRouter();
const prestamos = ref([])

const modificarPrestamo = (id, estado) => {
  if(estado == false){
    try{
    router.push({name: 'modificarPrestamo', params: {id:id}})
    } catch(error){
      console.error("Error en modificarPrestamo: "+id, error);
    }
  } else {
    console.error("El prestamo ya ha sido devuelto. No se permite su modificacion.")
  }
} 

const eliminarPrestamo = async (id) =>{
  try {
    const res = await axios.delete(`http://localhost:8080/api/prestamo/${id}`)
    fetchPrestamo();
  } catch(error) {
    console.log(error)
  }
}

const fetchPrestamo = async () => {
  try {
    const res = await axios.get("http://localhost:8080/api/prestamo");
    prestamos.value = res.data
    console.log(res)
  } catch (error) {
    console.log(error)
  }
}

const devuelto = async (id)=> {
  try {
    const res = await axios.put(`http://localhost:8080/api/prestamo/${id}`)
    fetchPrestamo();
  } catch (error) {
    console.log(error)
  }
}

onMounted(()=>{
  fetchPrestamo();
})
</script>

<style scoped>
.container {
    display: flex;
    flex-direction: column;
    align-items: center;
    padding-top: 30px;
}

.prestamo-container {
    display: flex;
    flex-wrap: wrap;
}

.prestamo {
    padding: 10px;
    box-shadow: 3px 3px 15px black;
    margin-bottom: 10px;
    width: 800px;
    height: 250px;
    overflow: auto;
}

table, tr, th, td{
  border: 1px solid gray;
}
table {
  margin-bottom: 20px;
}
</style>
