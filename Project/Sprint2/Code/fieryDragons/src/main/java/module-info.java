module com.fit3077.fierydragons {
    requires javafx.controls;
    requires javafx.fxml;
    requires json;
    requires json.simple;


    opens com.fit3077.fierydragons to javafx.fxml;
    exports com.fit3077.fierydragons;
}