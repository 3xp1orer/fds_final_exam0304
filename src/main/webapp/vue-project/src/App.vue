<template>
  <div id="app">
    <EventsApp/>
  </div>
</template>

<script>
import EventsApp from "@/components/main/EventsApp.vue";

export default {
  name: 'App',
  components: {
    EventsApp,
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  min-height: 100vh;
  display: flex;
  flex-direction: column;
}
</style>
