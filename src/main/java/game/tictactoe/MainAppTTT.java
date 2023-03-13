package game.tictactoe;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Arrays;


public class MainAppTTT extends Application {
    private final int boardSize = 300;
    private final int rect = boardSize / 3;
    //    private int[][] board = new int[2][2];
    private char[][] board = {{'x', 'o', 'x'}, {'x', 'x', 'x'}, {'o', 'x', 'o'}};
    Button resetButton = new Button("Reset");
    int playerX = 0;
    int playerO = 0;
    Label score = new Label("Score : X=" + playerX + " O=" + playerO);
    Canvas canvas = new Canvas(boardSize, boardSize);
    HBox topMenu = new HBox(resetButton, score);


    GraphicsContext gc = canvas.getGraphicsContext2D();
    private boolean playerTurn = true;

    @Override
    public void start(Stage stage) throws IOException {
        drawBoard();
        canvas.setOnMouseClicked(e -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                int x = (int) e.getX() / rect;
                int y = (int) e.getY() / rect;
                if (validTurn(y, x)) {
                    if (playerTurn) {
                        board[y][x] = 'x';
                    } else {
                        board[y][x] = 'o';
                    }
                    playerTurn = !playerTurn;
                }
            } else if (e.getButton() == MouseButton.SECONDARY) {
                resetBoard();
            }
            drawBoard();
        });
        resetButton.setOnAction(e -> resetBoard());
        stage.setTitle("TicTacToe");
        stage.setScene(new Scene(new VBox(topMenu, canvas)));
        stage.show();
    }

    private void drawBoard() {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, boardSize, boardSize);
        gc.setStroke(Color.WHITESMOKE);
        //Todo boardSize check goes out of bound when can't devided by 3 without cast
        for (int i = 0; i < boardSize; i += rect) {
            for (int j = 0; j < boardSize; j += rect) {
                gc.strokeRect(i, j, rect, rect);
                if (board[j / rect][i / rect] == 'x') {
                    gc.strokeLine(i, j, i + rect, j + rect);
                    gc.strokeLine(i, j + rect, i + rect, j);
                } else if (board[j / rect][i / rect] == 'o') {
                    gc.strokeRoundRect(i, j, rect, rect, rect, rect);
                }
            }
        }
    }

    private boolean validTurn(int y, int x) {
        return board[y][x] == ' ';
    }

    private void resetBoard() {
        Arrays.stream(board).forEach(e -> Arrays.fill(e, ' '));
        drawBoard();
    }

    public static void main(String[] args) {
        launch();
    }
}