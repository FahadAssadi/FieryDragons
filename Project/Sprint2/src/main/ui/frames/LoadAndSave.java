package main.ui.frames;

import main.game.GameBoard;
import main.misc.Utility;

import java.util.LinkedHashMap;
import java.util.Map;

public class LoadAndSave {
    private static LoadAndSave instance;

    private int saveFileNumber;
    private int loadFileNumber;

    public LoadAndSave() {
        this.saveFileNumber = 0;
        this.loadFileNumber = 0;
    }

    public void setSaveFileNumber(int fileNumber) {
        this.saveFileNumber = fileNumber;
    }

    public void setLoadFileNumber(int fileNumber) {
        this.loadFileNumber = fileNumber;
    }

    public void save(GameBoard gameBoard) {
        Map<String, Object> map = gameBoard.save(new LinkedHashMap<>());

        String path = "save_" + this.saveFileNumber + ".yml";
        Utility.writeYamlFile(map, path);
    }

    public GameBoard load() {
        String path = "save_" + this.loadFileNumber + ".yml";

        Map<String, Object> map = Utility.readYamlFile(path);
        System.out.println(map );

        return new GameBoard(map);
    }

    public static LoadAndSave getInstance() {
        if (instance == null) {
            instance = new LoadAndSave();
        }
        return instance;
    }

}
