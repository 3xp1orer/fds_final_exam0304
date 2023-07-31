<template>
  <div class="eventcancelbutton">
    <button
        class="btn btn-secondary"
        @click="cancelEdit()">Cancel
    </button>
  </div>
</template>

<script>
export default {
  name: "EventCancelButton",
  methods: {
    cancelEdit: function () {
      this.$store.dispatch('cancelEditAndReloadEvent');
    }
  }
}
</script>

<style scoped>
.eventcancelbutton {
  margin-right: 20px;
}
</style>
