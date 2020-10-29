<template>
  <div v-if="this.cliente">
    <h4>Cliente</h4>
    <div><label>Login: </label> {{ this.cliente.login }}</div>
    <div><label>Senha: </label> {{ this.cliente.senha }}</div>
    <div><label>RecebeProm: </label> {{ this.cliente.recebeprom }}</div>
    <button
      v-if="this.cliente.recebeprom"
      v-on:click="updateRecebeProm(false)"
      class="button is-small btn-primary"
    >
      SemPromo
    </button>
    <button
      v-else
      v-on:click="updateRecebeProm(true)"
      class="button is-small btn-primary"
    >
      TemPromo
    </button>
    <button class="button is-small btn-danger" v-on:click="deleteCliente()">
      Delete
    </button>
  </div>
  <div v-else>
    <br />
    <p>Clique em um cliente...</p>
  </div>
</template>
<script>
import http from "../http-common";
export default {
  name: "cliente",
  props: ["cliente"],
  methods: {
    updateRecebeProm(status) {
      var data = {
        id: this.cliente.id,
        login: this.cliente.login,
        senha: this.cliente.senha,
        recebeprom: status,
      };
      http
        .put("/cliente/" + this.cliente.id, data)
        .then((response) => {
          this.cliente.recebeprom = response.data.recebeprom;
          console.log(response.data);
        })
        .catch((e) => {
          console.log(e);
        });
    },
    deleteCliente() {
      http
        .delete("/cliente/" + this.cliente.id)
        .then((response) => {
          console.log(response.data);
          this.$emit("refreshData");
          this.$router.push("/");
        })
        .catch((e) => {
          console.log(e);
        });
    },
  },
};
</script>