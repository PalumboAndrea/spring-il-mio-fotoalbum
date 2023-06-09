<script>
import axios from 'axios'

const url = "http://localhost:8080/api/";

export default {

  data(){
    return{
        foto: [],
        userSearch: "",
        messaggio: {
          email: "",
          messaggio: ""
        }
    }
  },
  methods: {
    fotoIndex() {
        axios.get(url + "foto")
          .then((response) => {

              this.foto = response.data;
          })
          .catch(function (error) {
              console.log(error);
          })
          .finally(() => {

          });
    },
    pizzeSearch() {
        axios.get(url + "foto/by/name", {
            params: {
                titolo: this.userSearch
            }
        })
          .then((response) => {

              this.foto = response.data;
          })
          .catch(function (error) {
              console.log(error);
          })
          .finally(() => {

          });
    },
    sendForm() {
      axios.post(url + "messaggio", this.messaggio)
          .then(res => {
              
            this.messaggio.email = "";
            this.messaggio.messaggio = "";
            this.$router.push('/home');
          })
          .catch(err => console.log(err));
    }
  },
  mounted() {
    this.fotoIndex();
  },
}

</script>

<template>

  <div class="container mt-2">

    <div class="d-flex">
      <input type="text" name="name" placeholder="Nome" v-model="userSearch" @keyup="pizzeSearch()" class="w-50 form-control">
      <button @click="pizzeSearch()" class="btn btn-secondary ms-2">
        Cerca
      </button>
    </div>

    <h1>
      Ecco le mie foto:
    </h1>
    
    <ul>
      <template v-for="foto in foto">
        <li class="mb-2" v-if="foto.visibile">
        <span>
          {{ foto.titolo }}
        </span>
        <br>
        <img :src="foto.url" alt="">
      </li>
      </template>
      
    </ul>

    <div class="border border-2 border-dark-subtle rounded-2 p-3">

      <h5>
        Se hai qualche curiosità inviami un messaggio!
      </h5>
      
      <form class="row g-3" method="POST" @submit.prevent="sendForm">
      
        <div class="col-12">
          <label class="form-label">Email</label>
          <input type="email" class="form-control" name="email" v-model="messaggio.email">
        </div>

        <div class="col-12">
          <label class="form-label">Messaggio</label>
          <textarea class="form-control" placeholder="Scrivi un messaggio" name="messaggio" v-model="messaggio.messaggio"></textarea>
        </div>
        
        <div class="col-12 text-center">
          <input type="submit" value="Invia!" class="btn btn-success">
        </div>
    
      </form>

    </div>

  </div>

</template>

<style lang="scss">
@use '../style.scss';

img{
  width: 300px;
}

</style>
