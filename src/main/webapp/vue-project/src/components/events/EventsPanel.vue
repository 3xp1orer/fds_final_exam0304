<template>
  <div class="eventspanel">
    <div>
      <EventsSearchDialog/>
      <Events/>
    </div>
    <EventsButton/>
  </div>
</template>

<script>
import Events from "@/components/events/Events.vue";
import EventsSearchDialog from "@/components/events/EventsSearchDialog.vue";
import EventsButton from "@/components/events/EventsButton.vue";

export default {
  name: "EventsPanel",
  components: {EventsButton, EventsSearchDialog, Events},
}
</script>

<style scoped>
.eventspanel {
  flex-basis: 30%;
  background: #2c3e50;
  color: white;
  padding: 10px;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}
</style>
