module com.fit3077.fierydragongame.fierydragongame {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.fit3077.fierydragongame.fierydragongame to javafx.fxml;
    exports com.fit3077.fierydragongame.fierydragongame;
}