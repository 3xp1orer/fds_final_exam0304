<template>
  <div class="eventpanel" v-if="isEventSelected()">
    <div v-if="editMode == false">
      <EventDetailPanel/>
    </div>
    <div v-else>
      <EventEditPanel/>
    </div>
  </div>
</template>

<script>
import {mapState} from "vuex";
import EventDetailPanel from "@/components/event/detail/EventDetailPanel.vue";
import EventEditPanel from "@/components/event/edit/EventEditPanel.vue";

export default {
  name: "EventPanel",
  components: {
    EventEditPanel,
    EventDetailPanel,
  },
  methods: {
    isEventSelected() {
      return Object.keys(this.selectedEvent).length != 0
    }
  },
  computed: {
    ...mapState(['editMode', 'selectedEvent'])
  }
}
</script>

<style scoped>
.eventpanel {
  flex-basis: 70%;
  background: hsla(1610, 100%, 42%, 20);
  padding: 10px;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}

</style>
