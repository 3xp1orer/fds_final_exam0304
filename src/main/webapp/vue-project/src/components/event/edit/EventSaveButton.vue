<template>
  <div class="eventsavebutton">
    <button
        class="btn btn-secondary"
        @click="saveEvent()">Save
    </button>
  </div>
</template>

<script>
export default {
  name: "EventSaveButton",
  methods: {
    saveEvent: function () {
      this.$store.dispatch('saveOrUpdateEvent');
    }
  }
}
</script>

<style scoped>
.eventsavebutton {
  margin-right: 20px;
}
</style>
