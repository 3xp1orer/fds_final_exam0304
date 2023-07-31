import Vue from 'vue'
import Vuex from 'vuex'
import network from '@/network/network';

const parse = require('parse-link-header');

Vue.use(Vuex)


export const store = new Vuex.Store({
    state: {
        events: [],
        selectedEvent: {},
        editMode: false,
        createUrl: undefined,
        editUrl: undefined,
        deleteUrl: undefined,
        nextUrl: undefined,
        prevUrl: undefined,
        clearSearchField: false,
        editEventErrorMessage: ""
    },
    mutations: {
        SET_EVENTS(state, {events, createUrl, nextUrl, prevUrl}) {
            state.events = events;
            state.selectedEvent = {};
            state.clearSearchField = false;
            state.createUrl = createUrl;
            state.prevUrl = prevUrl;
            state.nextUrl = nextUrl;
        },
        SET_EVENT(state, {event, editUrl, deleteUrl}) {
            state.selectedEvent = event;
            state.editUrl = editUrl;
            state.deleteUrl = deleteUrl;
            state.clearSearchField = true;
            state.editMode = false;
        },
        SET_EDIT_MODE(state, editMode) {
            state.editMode = editMode;
            if (editMode == false && state.selectedEvent.id == 0) {
                state.selectedEvent = {};
            }
        },
        /* eslint-disable no-unused-vars */
        CREATE_NEW_EVENT(state) {
            state.editMode = true;
            state.selectedEvent = {
                id: 0,
                name: "",
                description: "",
                semester: "",
                eventType: ""
            }
        },
        SET_ERROR_MESSAGE(state, errorMessage) {
            state.editEventErrorMessage = errorMessage;
        }
    },
    actions: {
        // get filter methode über all events
        async getAllEventsBySearchValue(context, search){
            const dispatcherResponse = await network.getDispatcherState();
            const allLinks = parse(dispatcherResponse.headers.link);
            const url = allLinks['getAllEventsBySearchValue'].url.replace("{search}", search);
            alert(url);
            await context.dispatch('loadPage', url);
        },
        async getAllEvents(context, search) {
            const dispatcherResponse = await network.getDispatcherState();
            const allLinks = parse(dispatcherResponse.headers.link);
            const url = allLinks['getAllEvents'].url.replace("{QUERY}", search);
            await context.dispatch('loadPage', url);
        },
        async loadPage(context, url) {
            const getCollectionResponse = await network.getAllEventsState(url);
            const nextRelations = parse(getCollectionResponse.headers.link);
            context.commit("SET_EVENTS", {
                events: getCollectionResponse.data,
                createUrl: nextRelations['createEvent'],
                nextUrl: nextRelations['next'],
                prevUrl: nextRelations['prev']
            });
        },
        async loadNextPage(context) {
            await context.dispatch('loadPage', this.state.nextUrl.url);
        },
        async loadPrevPage(context) {
            await context.dispatch('loadPage', this.state.prevUrl.url);
        },
        async getSingleEvent(context, url) {
            const response = await network.getSingleEventState(url);
            const nextRelations = parse(response.headers.link);
            context.commit("SET_EVENT", {
                event: response.data,
                editUrl: nextRelations['updateEvent'],
                deleteUrl: nextRelations['deleteEvent'],
            });
        },
        async switchToEditMode(context) {
            context.commit('SET_EDIT_MODE', true);
        },
        async switchToDetailMode(context) {
            context.commit('SET_EDIT_MODE', false);
        },
        async createNewEvent(context) {
            context.commit('CREATE_NEW_EVENT');
        },
        async saveOrUpdateEvent(context) {
            if (this.state.selectedEvent.topicShort.trim().length === 0) {
                context.commit('SET_ERROR_MESSAGE', 'Event topicShort must not be empty');
            } else {
                context.commit('SET_ERROR_MESSAGE', '');
                if (this.state.selectedEvent.id === 0) {
                    await context.dispatch('postSingleEvent');
                } else {
                    await context.dispatch('putSingleEvent');
                }
            }
        },
        async postSingleEvent(context) {
            const postResponse = await network.postSingleEvent(this.state.createUrl.url, this.state.selectedEvent);
            const nextRelations = parse(postResponse.headers.link);
            const allEventsUrl = nextRelations['getAllEvents'];
            const eventUrl = postResponse.headers.location;
            await context.dispatch('loadPage', allEventsUrl.url);
            await context.dispatch('getSingleEvent', eventUrl);
        },
        async putSingleEvent(context) {
            const response = await network.updateSingleEvent(this.state.editUrl.url, this.state.selectedEvent);
            const nextRelations = parse(response.headers.link);
            const allEventsUrl = nextRelations['getAllEvents'];
            const eventUrl = nextRelations['getEvent'];
            await context.dispatch('loadPage', allEventsUrl.url);
            await context.dispatch('getSingleEvent', eventUrl.url);
        },
        /* eslint-disable no-unused-vars */
        async deleteSingleEvent(context) {
            const response = await network.deleteSingleEvent(this.state.deleteUrl.url);
            const nextRelations = parse(response.headers.link);
            const allEventsUrl = nextRelations['getAllEvents'];
            await context.dispatch('loadPage', allEventsUrl.url);
        },
        async cancelEditAndReloadEvent(context) {
            context.commit('SET_EDIT_MODE', false);
            context.commit('SET_ERROR_MESSAGE', '');
            await context.dispatch('getSingleEvent', this.state.selectedEvent.self.href);
        }
    },
    getters: {
        isCreateAllowed(state) {
            return state.createUrl != undefined;
        },
        isEventEditable(state) {
            return state.editUrl != undefined;
        },
        isEventDeletable(state) {
            return state.deleteUrl != undefined;
        },
        isNextPageAvailable(state) {
            return state.nextUrl != undefined;
        },
        isPrevPageAvailable(state) {
            return state.prevUrl != undefined;
        }
    }
})

