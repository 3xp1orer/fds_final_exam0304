<template>
  <div class="editeventbutton">
    <button
        class="btn btn-secondary"
        @click="editThisEvent()">Edit Event
    </button>
  </div>
</template>

<script>

export default {
  name: "EventEditButton",
  methods: {
    editThisEvent: function () {
      this.$store.dispatch('switchToEditMode');
    }
  },
  computed: {
    isEditable() {
      return this.$store.getters.isEventEditable
    }
  }
}
</script>

<style scoped>
.editeventbutton {
  margin-right: 20px;
}
</style>
