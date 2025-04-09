<script setup lang="ts">
import AddBookForm from "./components/AddBookForm.vue";
</script>

<template>
  <main class="grid-layout">
    <AddBookForm class="book" />
  </main>
</template>

<style scoped>
.grid-layout {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  grid-template-rows: repeat(2, auto);
  grid-gap: 20px;
  padding: 20px;
}

.book {
  grid-column: 2 / 3;
  grid-row: 1 / 2;
}
</style>
