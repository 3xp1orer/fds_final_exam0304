<template>
  <div class="eventsapp">
    <EventsPanel/>
    <EventPanel/>
  </div>
</template>

<script>
import EventsPanel from "@/components/events/EventsPanel.vue";
import EventPanel from "@/components/event/EventPanel.vue";

export default {
  name: "EventsApp",
  components: {EventPanel, EventsPanel}
}
</script>

<style scoped>
.eventsapp {
  flex: 1;
  display: flex;
  flex-direction: row;
  align-items: stretch;
  min-width: 100%;
}
</style>
