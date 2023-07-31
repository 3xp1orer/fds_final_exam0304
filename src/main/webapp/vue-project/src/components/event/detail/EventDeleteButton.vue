<template>
  <div class="deleteeventbutton">
    <button
        v-if="isDeletable"
        class="btn btn-secondary"
        @click="openModalDialog()">Delete Event
    </button>
    <EventDeleteModalDialog
        v-bind:showDeleteDialog="showDeleteDialog"
        @yesDelete="deleteThisEvent()" @cancelDelete="closeModelDialog()"/>
  </div>
</template>

<script>

import EventDeleteModalDialog from "@/components/event/detail/EventDeleteModalDialog.vue";

export default {
  name: "EventDeleteButton",
  components: {EventDeleteModalDialog},
  data: function () {
    return {
      showDeleteDialog: false
    }
  },
  methods: {
    openModalDialog: function () {
      this.showDeleteDialog = true;
    },
    deleteThisEvent: function () {
      this.showDeleteDialog = false;
      this.$store.dispatch('deleteSingleEvent');
    },
    closeModelDialog: function () {
      this.showDeleteDialog = false;
    }
  },
  computed: {
    isDeletable() {
      return this.$store.getters.isEventDeletable
    }
  }
}
</script>

<style scoped>
.deleteeventbutton {
  margin-right: 20px;
}
</style>
