<template>
  <div style="display: flex; flex-direction: column; height: 100%">
    <v-container style="width: 90%" class="pr-12 pl-12 mx-a">
      <div class="text-h1 center">Welcome to Sorbonne Gutenberg</div>

      <div class="text-h6 font-weight-bold">
        The platform for find all the books :)
      </div>

      <v-tabs class="mt-4">
        <v-tab> Search by book name </v-tab>
        <v-tab> Search by keywords in books </v-tab>
        <v-tab> Search by regex </v-tab>
      </v-tabs>
      <v-autocomplete
        v-model="model"
        class="mt-8 mb-4"
        :items="items"
        :loading="isLoading"
        :search-input.sync="search"
        hide-no-data
        hide-selected
        dense
        color="#0c2d6e"
        filled
        item-text="Description"
        item-value="API"
        label="Gutenberg books"
        placeholder="Start typing to Search"
        return-object
      ></v-autocomplete>
      <v-expand-transition>
        <v-list v-if="model" class="red lighten-3">
          <v-list-item v-for="(field, i) in fields" :key="i">
            <v-list-item-content>
              <v-list-item-title v-text="field.value"></v-list-item-title>
              <v-list-item-subtitle v-text="field.key"></v-list-item-subtitle>
            </v-list-item-content>
          </v-list-item>
        </v-list>
      </v-expand-transition>
    </v-container>
    <div style="max-width: 100%; background-color: #f5f6f7; flex-grow: 2">
      <v-container class="mt-4 pr-3 pl-3">
        <div class="text-h5 font-weight-bold mb-4">French books</div>

        <v-row dense>
          <v-col
            v-for="(item, i) in this.$store.state.books"
            :key="i"
            xs="12"
            sm="6"
            md="6"
            lg="4"
            xl="3"
          >
            <v-card
              :color="item.color"
              outlined
              elevation="2"
              target="_blank"
              rel="noopener noreferrer"
              :href="'https://www.gutenberg.org/ebooks/' + item.link"
              class="mt-2 mb-2 ml-2 mr-2"
              style="height: 100%"
            >
              <div class="d-flex flex-no-wrap justify-space-between">
                <div>
                  <v-card-title
                    class="text-h5"
                    v-text="item.Title"
                  ></v-card-title>

                  <v-card-subtitle v-text="item.Author"></v-card-subtitle>
                </div>

                <v-avatar class="ma-3" size="200" tile>
                  <v-img
                    contain
                    :src="`https://www.gutenberg.org/cache/epub/${item.link}/pg${item.link}.cover.medium.jpg`"
                  ></v-img>
                </v-avatar>
              </div>
            </v-card>
          </v-col>
        </v-row>
      </v-container>
    </div>
  </div>
</template>

<script>
export default {
  data: () => ({
    descriptionLimit: 60,
    entries: [],
    isLoading: false,
    model: null,
    search: null,
  }),

  computed: {
    fields() {
      if (!this.model) return []

      return Object.keys(this.model).map((key) => {
        return {
          key,
          value: this.model[key] || 'n/a',
        }
      })
    },
    items() {
      return this.entries.map((entry) => {
        const Description =
          entry.Description.length > this.descriptionLimit
            ? entry.Description.slice(0, this.descriptionLimit) + '...'
            : entry.Description

        return Object.assign({}, entry, { Description })
      })
    },
  },

  watch: {
    search(val) {
      // Items have already been loaded
      if (this.items.length > 0) return

      // Items have already been requested
      if (this.isLoading) return

      this.isLoading = true

      console.log(val)

      // Lazily load input items
      fetch('https://api.publicapis.org/entries')
        .then((res) => res.json())
        .then((res) => {
          const { count, entries } = res
          this.count = count
          this.entries = entries
        })
        .catch((err) => {
          console.log(err)
        })
        .finally(() => (this.isLoading = false))
    },
  },
  mounted() {
    this.$store.dispatch('fetchBooks', 'French')
  },
}
</script>