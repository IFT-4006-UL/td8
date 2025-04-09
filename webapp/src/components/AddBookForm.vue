<template>
  <div class="p-4 max-w-md mx-auto">
    <h1 class="text-2xl font-semibold mb-2">Bibliothèque {{ LIBRARY_NAME }}</h1>
    <p class="mb-2 text-gray-600" data-cy="book-count">Nombre total de livres : <strong>{{ bookCount }}</strong></p>
    <h2 class="text-xl font-bold mb-4">Ajouter un livre</h2>

    <form @submit.prevent="submitBook" class="space-y-4">
      <div>
        <label class="block mb-1">Titre:</label>
        <input v-model="title" class="w-full border p-2 rounded" type="text" required id="title-input" data-cy="title-input"/>
      </div>

      <div>
        <label class="block mb-1">Auteur:</label>
        <input v-model="author" class="w-full border p-2 rounded" type="text" required id="author-input" data-cy="author-input"/>
      </div>

      <button type="submit" class="bg-blue-500 hover:bg-blue-600 text-white py-2 px-4 rounded" data-cy="submit-add-book">
        Ajouter le livre
      </button>
    </form>

    <p v-if="successMessage" id="success-message" class="text-green-600 mt-4">{{ successMessage }}</p>
  </div>
</template>

<script lang="ts" setup>
import { ref, onMounted } from 'vue'
import axios from 'axios'

const title = ref('')
const author = ref('')
const successMessage = ref('')
const bookCount = ref(0)

const LIBRARY_NAME = 'Ulaval'

const fetchBookCount = async () => {
  try {
    const response = await axios.get(`/libraries/${LIBRARY_NAME}/books`)
    if (Array.isArray(response.data.books)) {
      bookCount.value = response.data.books.length
    }
  } catch (error) {
    console.error('Erreur lors de la récupération des livres:', error)
  }
}

const submitBook = async () => {
  try {
    await axios.post(`/libraries/${LIBRARY_NAME}/books`, {
      title: title.value,
      author: author.value,
    })
    successMessage.value = `Livre ${title.value} correctement ajouté!`
    title.value = ''
    author.value = ''
  } catch (error) {
    console.error('Failed to add book:', error)
    successMessage.value = 'Erreur lors de l\'ajout du livre.'
  }
  await fetchBookCount()
}

onMounted(fetchBookCount)
</script>

<style scoped>
input:focus {
  outline: none;
  border-color: #2563eb;
  box-shadow: 0 0 0 1px #2563eb;
}
</style>
