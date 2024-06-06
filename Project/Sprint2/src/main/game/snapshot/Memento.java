package main.game.snapshot;

import java.util.Map;

public interface Memento {
    Map<String, Object> save(Map<String , Object> map);
    Map<String, Object> load(Map<String , Object> map);
}
