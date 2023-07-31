<template>
  <div class="event">
    <div class="container">
      <div class="row">
        <h1><b>{{ selectedEvent.topicShort }}</b></h1>
      </div>
      <dl class="row">
        <dt class="col-sm-3"><b>Description: </b></dt>
        <dd class="col-sm-9">{{ selectedEvent.topicLong }}
          <EventType v-bind:event-type="selectedEvent.topicLong"/>
        </dd>
      </dl>
      <dl class="row">
        <dt class="col-sm-3"><b>Where: </b></dt>
        <dd class="col-sm-9">{{ selectedEvent.address }}</dd>
      </dl>
      <dl class="row">
        <dt class="col-sm-3"><b>When: </b></dt>
        <dd class="col-sm-9">{{ selectedEvent.startDateAndTime }}</dd>
      </dl>
      <dl class="row">
        <dt class="col-sm-3"><b>Till: </b></dt>
        <dd class="col-sm-9">{{ selectedEvent.endDateAndTime }}</dd>
      </dl>
      <dl class="row">
        <dt class="col-sm-3"><b>Institution: </b></dt>
        <dd class="col-sm-9">{{ selectedEvent.institution }}</dd>
      </dl>
      <dl class="row">
        <dt class="col-sm-3"><b>Lecturer: </b></dt>
        <dd class="col-sm-9">{{ selectedEvent.lecturer }}</dd>
      </dl>
      <dl class="row">
        <dt class="col-sm-3"><b>Location: </b></dt>
        <dd class="col-sm-9">{{ selectedEvent.location }}</dd>
      </dl>
      <hr>
      <div class="row">
        <div class="col-sm-9">
          {{ selectedEvent.description }}
        </div>
      </div>
      <hr>
    </div>
  </div>
</template>

<script>
import {mapState} from "vuex";
import EventType from "@/components/event/detail/EventType.vue";

export default {
  name: "EventDetail",
  components: {EventType},
  computed: {
    ...mapState(['selectedEvent'])
  }
}
</script>

<style scoped>
.event {
  display: flex;
}
</style>
