<!-- Todo Anpassen -->
<template>
  <div class="events">
    <div v-for="e in events" v-bind:key="e.id" @click="selectRow(e)">
      <div class="event">
        <span>{{ e.topicShort }}</span>&nbsp;<span>({{ e.startDateAndTime }})</span>
      </div>
    </div>
  </div>
</template>

<script>
import {mapState} from "vuex";
export default {
  name: "Events",
  mounted() {
    this.$store.dispatch("getAllEvents", "");
  },
  computed: {
    ...mapState(['events'])
  },
  methods: {
    selectRow: function (event) {
      this.$store.dispatch("getSingleEvent", event.self.href);
    }
  }
}
</script>

<style scoped>
.events {
  float: left;
  clear: both;
}

.event {
  font-size: 24pt;
  margin-bottom: 10px;
}
</style>
