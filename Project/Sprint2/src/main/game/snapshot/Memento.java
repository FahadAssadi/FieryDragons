package main.game.snapshot;

import java.util.Map;

/**
 * Saves the current state of the object into the provided map.
 *
 * @param  map  the map to store the state in
 * @return      a map containing the saved state of the object
 */
public interface Memento {
    Map<String, Object> save(Map<String , Object> map);
}
