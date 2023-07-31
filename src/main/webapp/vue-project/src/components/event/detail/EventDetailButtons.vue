<template>
  <div class="eventbuttons">
    <EditEventButton/>
    <DeleteEventButton/>
  </div>

</template>

<script>
import EditEventButton from "@/components/event/detail/EventEditButton.vue";
import DeleteEventButton from "@/components/event/detail/EventDeleteButton.vue";

export default {
  name: "EventDetailButtons",
  components: { DeleteEventButton, EditEventButton }
}
</script>

<style scoped>
.eventbuttons {
  display: flex;
  flex-direction: row;
  justify-content: flex-end;
}
</style>
