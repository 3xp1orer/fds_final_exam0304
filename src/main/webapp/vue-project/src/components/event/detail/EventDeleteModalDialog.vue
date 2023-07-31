<template>
  <div v-if="showDeleteDialog">
    <div class="modal-mask" @click="clickOutside">
      <div class="modal-wrapper">
        <div class="modal-container">
          <div class="modal-header">
            <h3>Delete this event?</h3>
          </div>
          <div class="modal-body">
            Do you really want to delete this event?
          </div>
          <div class="modal-footer">
            <button class="btn btn-danger" @click="deleteEvent()">
              Yes
            </button>
            <button class="btn btn-secondary" @click="cancel()">
              No
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "EventDeleteModalDialog",
  props: {
    showDeleteDialog: Boolean
  },
  methods: {
    deleteEvent: function () {
      this.$emit("yesDelete");
    },
    cancel: function () {
      this.$emit("cancelDelete");
    },
    clickOutside: function () {
      console.log("Click outside");
    }
  }
}
</script>

<style scoped>
.modal-mask {
  position: fixed;
  z-index: 1;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  display: table;
  transition: opacity 0.3s ease;
}

.modal-wrapper {
  display: table-cell;
  vertical-align: middle;
}

.modal-container {
  width: 400px;
  margin: 0px auto;
  padding: 20px 30px;
  background-color: #fff;
  border-radius: 2px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.33);
  transition: all 0.3s ease;
}

.modal-header h3 {
  margin-top: 0;
  font-weight: bolder;
  font-size: large;
  color: #42b983;
}

.modal-body {
  margin: 20px 0;
}
</style>
