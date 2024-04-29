package game.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventManager {
    public static EventManager instance;
    Map<EventType, List<EventListener>> listeners ;

    private EventManager() {
        this.listeners = new HashMap<>();
    }

    public void subscribe(EventType eventType, EventListener eventListener){
        // Checks if the array exists, if not create the array of subscribers
        List<EventListener> subscribers = listeners.computeIfAbsent(eventType, k -> new ArrayList<>());

        subscribers.add(eventListener);
    }

    public void unsubscribe(EventType eventType, EventListener eventListener){
        List<EventListener> subscribers = listeners.get(eventType);

        if (subscribers != null) {
            subscribers.remove(eventListener);
        }
    }

    public void notify(EventType eventType){
        List<EventListener> subscribers = listeners.get(eventType);

        for (EventListener eventListener: subscribers){
            eventListener.update(eventType);
        }
    }

    public static EventManager getInstance(){
        if (instance == null) {
            instance = new EventManager();
        }

        return instance;
    }
}
