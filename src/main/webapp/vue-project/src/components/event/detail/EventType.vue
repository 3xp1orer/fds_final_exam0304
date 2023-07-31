<template>
  <div class="eventtype">
    {{ type }}
  </div>
</template>

<script>
import {TYPE_MAP} from '../eventTypes.js';

export default {
  name: "EventType",
  props: {
    eventType: String
  },
  computed: {
    type: function () {
      return TYPE_MAP.get(this.eventType);
    }
  }
}
</script>

<style scoped>

</style>
