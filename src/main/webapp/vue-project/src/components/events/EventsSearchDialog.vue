<template>
  <div class="eventssearchdialog">
    <div class="input-group">
      <input type="text" class="form-control" placeholder="Search events ..."
             v-model="search" @keyup.enter="startSearch">
      <div class="input-group-append">
        <button class="btn btn-secondary" type="button" @click="startSearch">
          <i class="fa fa-search"></i>
        </button>
      </div>
    </div>
  </div>
</template>

<script>
import {mapState} from "vuex";

export default {
  name: "EventsSearchDialog",
  data: function () {
    return {
      search: ""
    }
  },
  methods: {
    startSearch: function () {
      this.$store.dispatch("getAllEventsBySearchValue", this.search);
    }
  },
  computed: {
    ...mapState(['clearSearchField'])
  },
  watch: {
    clearSearchField(newValue, oldValue) {
      if (newValue == true && oldValue == false) {
        this.search = "";
      }
    }
  }
}
</script>

<style scoped>
.eventssearchdialog {
  margin-bottom: 24px;
}
</style>
