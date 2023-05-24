module Game {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires json.simple;
    exports view;
    opens view to javafx.fxml;
}