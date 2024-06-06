package main.game.snapshot;

import java.util.HashMap;
import java.util.Map;

public interface Memento {
    Map save(Map map);
    Map load(Map map);
}
