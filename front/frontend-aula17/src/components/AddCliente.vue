<template>
  <div class="submitform">
    <div v-if="!submitted">
      <div class="form-group">
        <label for="login">Login</label>
        <input
          type="text"
          class="form-control"
          id="login"
          required
          v-model="cliente.login"
          name="login"
        />
      </div>
      <div class="form-group">
        <label for="senha">Senha</label>
        <input
          type="number"
          class="form-control"
          id="senha"
          required
          v-model="cliente.senha"
          name="senha"
        />
      </div>
      <button v-on:click="salvarCliente" class="btn btn-success">Salvar</button>
    </div>
    <div v-else>
      <h4>Salvo com sucesso.</h4>
      <button class="btn btn-success" v-on:click="novoCliente">
        Adicionar
      </button>
    </div>
  </div>
</template>
<script>
import http from "../http-common";
export default {
  name: "add-cliente",
  data() {
    return {
      cliente: {
        id: 0,
        login: "",
        senha: 0,
        recebeprom: false,
      },
      submitted: false,
    };
  },
  methods: {
    salvarCliente() {
      var data = {
        login: this.cliente.login,
        senha: this.cliente.senha,
      };
      http
        .post("/cliente", data)
        .then((response) => {
          this.cliente.id = response.data.id;
          console.log(response.data);
        })
        .catch((e) => {
          console.log(e);
        });
      this.submitted = true;
    },
    novoCliente() {
      this.submitted = false;
      this.cliente = {};
    },
  },
};
</script>
<style>
.submitform {
  max-width: 300px;
  margin: auto;
}
</style>