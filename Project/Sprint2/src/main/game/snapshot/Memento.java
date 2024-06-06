package main.game.snapshot;

public interface Memento {
    void save(String filePath);
    Object load(String filePath);
}
