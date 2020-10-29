<template>
  <div class="searchform">
    <h4>Busca por senha</h4>
    <div class="form-group">
      <input
        type="number"
        class="form-control"
        id="senha"
        required
        v-model="senha"
        name="senha"
      />
    </div>
    <div class="btn-group">
      <button v-on:click="pesquisarClientes" class="btn btn-success">
        Pesquisar
      </button>
    </div>
    <ul class="search-result">
      <li v-for="(cliente, index) in clientes" :key="index">
        <h6>{{ cliente.login }} ({{ cliente.senha }})</h6>
      </li>
    </ul>
  </div>
</template>
<script>
import http from "../http-common";
export default {
  name: "pesquisar-cliente",
  data() {
    return {
      senha: 0,
      clientes: [],
    };
  },
  methods: {
    pesquisarClientes() {
      http
        .get("/clientes/senha/" + this.senha)
        .then((response) => {
          this.clientes = response.data; // JSON are parsed automatically.
          console.log(response.data);
        })
        .catch((e) => {
          console.log(e);
        });
    },
  },
};
</script>
<style>
.searchform {
  max-width: 300px;
  margin: auto;
}
.search-result {
  margin-top: 20px;
  text-align: left;
}
</style>