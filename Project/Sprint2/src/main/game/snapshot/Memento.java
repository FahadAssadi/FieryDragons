package main.game.snapshot;

import java.util.Map;

public interface Memento {
    Map<String, Object> save(Map<String , Object> map);
}
