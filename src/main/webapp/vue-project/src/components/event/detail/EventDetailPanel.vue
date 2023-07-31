<template>
  <div class="eventdetail">
    <EventDetail/>
    <hr>
    <EventDetailButtons/>
  </div>
</template>

<script>
import EventDetail from "@/components/event/detail/EventDetail.vue";
import EventDetailButtons from "@/components/event/detail/EventDetailButtons.vue";

export default {
  name: "EventDetailPanel",
  components: { EventDetail, EventDetailButtons }}
</script>

<style scoped>
.eventdetail {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}
</style>
