package game.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The EventManager class manages event subscriptions and notifications.
 */
public class EventManager {
    // Singleton instance of EventManager
    public static EventManager instance;
    // Map to store event types and their corresponding listeners
    Map<EventType, List<EventListener>> listeners ;

    /**
     * Private constructor to prevent external instantiation.
     */
    private EventManager() {
        this.listeners = new HashMap<>();
    }

    /**
     * Subscribes an event listener to an event type.
     *
     * @param eventType     The type of event to subscribe to.
     * @param eventListener The event listener to subscribe.
     */
    public void subscribe(EventType eventType, EventListener eventListener){
        // Checks if the array exists, if not create the array of subscribers
        List<EventListener> subscribers = listeners.computeIfAbsent(eventType, k -> new ArrayList<>());

        subscribers.add(eventListener);
    }

    /**
     * Unsubscribes an event listener from an event type.
     *
     * @param eventType     The type of event to unsubscribe from.
     * @param eventListener The event listener to unsubscribe.
     */
    public void unsubscribe(EventType eventType, EventListener eventListener){
        List<EventListener> subscribers = listeners.get(eventType);

        if (subscribers == null) {
            return;
        }

        subscribers.remove(eventListener);
    }

    /**
     * Notifies all subscribers of a particular event type.
     *
     * @param eventType The type of event to notify subscribers.
     */
    public void notify(EventType eventType){
        List<EventListener> subscribers = listeners.get(eventType);

        if (subscribers == null){
            return;
        }

        for (EventListener eventListener: subscribers){
            eventListener.update(eventType);
        }
    }

    /**
     * Gets the singleton instance of EventManager.
     *
     * @return The singleton instance of EventManager.
     */
    public static EventManager getInstance(){
        if (instance == null) {
            instance = new EventManager();
        }

        return instance;
    }
}
