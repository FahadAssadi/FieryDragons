package com.fit3077.fierydragons.models.event;

import com.fit3077.fierydragons.models.dragonCards.DragonCard;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

public class EventManager {
    private static EventManager instance;
    private final Map<EventType, List<Subscriber>> subscribers;

    private EventManager() {
        subscribers = new HashMap<>();
    }

    public static synchronized EventManager getInstance() {
        if (instance == null) {
            instance = new EventManager();
        }
        return instance;
    }

    public void subscribe(EventType eventType, Subscriber listener) {
        subscribers.computeIfAbsent(eventType, k -> new ArrayList<>()).add(listener);
    }

    public void unsubscribe(EventType eventType, Subscriber listener) {
        List<Subscriber> eventSubscribers = subscribers.get(eventType);
        if (eventSubscribers != null) {
            eventSubscribers.remove(listener);
        }
    }

    public void notify(EventType eventType) {
        List<Subscriber> eventSubscribers = subscribers.get(eventType);
        if (eventSubscribers != null) {
            for (Subscriber subscriber : eventSubscribers) {
                subscriber.update();
            }
        }
    }
}
