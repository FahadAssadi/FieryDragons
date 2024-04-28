package game.event;

import game.event.type.EventType;

public interface EventListener {
    void update(EventType eventType);
}
