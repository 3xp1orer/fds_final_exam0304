<template>
  <div>
    <form>
      <div v-if="editEventErrorMessage !== ''" class="errorMessage">{{ editEventErrorMessage }}</div>
      <div class="form-group">
        <label for="name">Event topic </label>
        <input type="text" class="form-control"
               v-model="selectedEvent.topicShort"
               id="name"
               placeholder="Enter event topic">
      </div>
      <div class="form-group">
        <label for="description">Event description </label>
        <textarea class="form-control"
                  v-model="selectedEvent.topicLong"
                  id="description" rows="4"
                  placeholder="Enter event description"></textarea>
      </div>
      <div class="form-group">
        <label for="address">Event address </label>
        <input type="text" class="form-control"
                  v-model="selectedEvent.address"
                  id="address"
                  placeholder="Enter event address">
      </div>
      <div class="form-group">
        <label for="startDateAndTime">Event start date and time </label>
        <input type="text" class="form-control"
                  v-model="selectedEvent.startDateAndTime"
                  id="startDateAndTime"
                  placeholder="Enter event start date and time">
      </div>
      <div class="form-group">
        <label for="endDateAndTime">Event end date and time </label>
        <input type="text" class="form-control"
                  v-model="selectedEvent.endDateAndTime"
                  id="endDateAndTime"
                  placeholder="Enter event end time and date">
      </div>
      <div class="form-group">
        <label for="institution">Event institution </label>
        <input type="text" class="form-control"
                  v-model="selectedEvent.institution"
                  id="institution"
                  placeholder="Enter event description">
      </div>
      <div class="form-group">
        <label for="lecturer">Event Lecturer </label>
        <input type="text" class="form-control"
                  v-model="selectedEvent.lecturer"
                  id="lecturer"
                  placeholder="Enter event lecturer">
      </div>
      <div class="form-group">
        <label for="location">Event location </label>
        <input type="text" class="form-control"
                  v-model="selectedEvent.location"
                  id="location"
                  placeholder="Enter event description">
      </div>
    </form>
  </div>
</template>

<script>
import {mapState} from "vuex";
import {TYPE_MAP} from '../eventTypes';

export default {
  name: "EventEdit",
  data: function () {
    return {
      allowedTypes: Array.from(TYPE_MAP.values())
    }
  },
  computed: {
    ...mapState(['selectedEvent', 'editEventErrorMessage']),
    etype: {
      get() {
        return TYPE_MAP.get(this.selectedEvent.eventType);
      },
      set(value) {
        const type = [...TYPE_MAP].find(([, val]) => val === value)[0];
        this.selectedEvent.eventType = type;
      }
    }
  }
}
</script>

<style scoped>
.errorMessage {
  color: #0ede6d;
  font-size: larger;
  font-weight: bold;
}
</style>
