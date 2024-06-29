module com.example.pharaohgame_try2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.pharaohgame_try2 to javafx.fxml;
    exports com.example.pharaohgame_try2;
    exports com.example.pharaohgame_try2.object;
    opens com.example.pharaohgame_try2.object to javafx.fxml;
    exports com.example.pharaohgame_try2.monster;
    opens com.example.pharaohgame_try2.monster to javafx.fxml;
}