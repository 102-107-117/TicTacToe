module game.tictactoe {
    requires javafx.controls;
    requires javafx.fxml;


    opens game.tictactoe to javafx.fxml;
    exports game.tictactoe;
}