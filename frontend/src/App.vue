<script setup>
import { RouterLink, RouterView } from 'vue-router'
</script>

<template>
    <div class="container">
      <nav>
        <ul>
          <li><RouterLink to="/">Home</RouterLink></li>
          <div>
            <p>Libro</p>
            <li><RouterLink to="/RegistrarLibro">Registrar</RouterLink></li>
            <li><RouterLink to="/ListadoLibro">Listado</RouterLink></li>
          </div>
          <div>
            <p>Usuario</p>
            <li><RouterLink to="/RegistrarUsuario">Registrar</RouterLink></li>
            <li><RouterLink to="/ListadoUsuario">Listado</RouterLink></li>
          </div>
          <div>
            <p>Prestamo</p>
            <li><RouterLink to="/RegistrarPrestamo">Registrar</RouterLink></li>
            <li><RouterLink to="/ListadoPrestamo">Listado</RouterLink></li>
          </div>
        </ul>
      </nav>
      <div class="view-container">
        <RouterView :style="{ width: '80%' }"/>
      </div>
    </div>
</template>

<style scoped>
.container { 
  display: flex;
}
nav {
  border-right: 1px solid gray;
  box-shadow: -10px 50px 50px 0 black;
  width: 10%;
  height: 100vh;
  display: flex;
  flex-direction: column;
  justify-content: center;
}
ul {
  margin-left: 10px;
}
ul > div {
  width: 80%;
}
ul > div > li {
  margin-left: 10px;
}
ul > div > p {
  font-style: italic;
}
li {
  transition: all 1s;

}
li {
  transition: border-bottom 0.3s ease;
}

li > *:hover {
  border-bottom: 1px solid #535bf2;
  transition-duration: 0.6s; 
}
.view-container{
  display: flex;
  justify-content: center;
  overflow: auto;
  max-height: 100vh;
  width: 100vw;
}
</style>
