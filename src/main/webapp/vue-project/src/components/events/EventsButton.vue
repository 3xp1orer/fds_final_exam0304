<!-- Todo Anpassen -->
<template>
  <div class="eventbutton">
    <button
        class="btn btn-secondary prevbutton"
        v-if="isPrevPageAvailable"
        @click="prevPage()">Prev page
    </button>
    <button
        class="btn btn-secondary nextbutton"
        v-if="isNextPageAvailable"
        @click="nextPage()">Next page
    </button>
    <button
        class="btn btn-secondary createbutton"
        v-if="isCreateAllowed"
        @click="createEvent()">Create Event
    </button>
  </div>
</template>

<script>
export default {
  name: "EventsButton",
  methods: {
    prevPage: function () {
      this.$store.dispatch('loadPrevPage');
    },
    nextPage: function () {
      this.$store.dispatch('loadNextPage');
    },
    createEvent: function () {
      this.$store.dispatch("createNewEvent");
    }
  },
  computed: {
    isCreateAllowed() {
      return this.$store.getters.isCreateAllowed
    },
    isNextPageAvailable() {
      return this.$store.getters.isNextPageAvailable
    },
    isPrevPageAvailable() {
      return this.$store.getters.isPrevPageAvailable
    }
  }


}
</script>

<style scoped>
.eventbutton {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}

.prevbutton {
  margin-left: 20px;
}

.nextbutton {
  margin-right: 20px;
}

.createbutton {
  margin-right: 20px;
}

</style>
