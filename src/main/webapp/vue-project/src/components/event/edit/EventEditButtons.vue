<template>
  <div class="eventbuttons">
    <EventSaveButton/>
    <EventCancelButton/>
  </div>
</template>

<script>
import EventSaveButton from "@/components/event/edit/EventSaveButton.vue";
import EventCancelButton from "@/components/event/edit/EventCancelButton.vue";

export default {
  name: "EventEditButtons",
  components: {EventCancelButton, EventSaveButton}
}
</script>

<style scoped>
.eventbuttons {
  display: flex;
  flex-direction: row;
  justify-content: flex-end;
}

</style>
