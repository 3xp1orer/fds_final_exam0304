<template>
  <div>
    <EventEdit/>
    <hr>
    <EventEditButtons/>
  </div>
</template>

<script>
import EventEdit from "@/components/event/edit/EventEdit.vue";
import EventEditButtons from "@/components/event/edit/EventEditButtons.vue";

export default {
  name: "EventEditPanel",
  components: {EventEditButtons, EventEdit}
}
</script>

<style scoped>

</style>
