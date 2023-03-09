package game.tictactoe;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;


public class MainAppTTT extends Application {
    private final int height = 600;
    private final int width = height;
    private final int rect = height / 3;
//    private int[][] board = new int[2][2];
    private char[][] board = {{'x','o','x'},{'x','x','x'},{'o','x','o'}};
    Canvas canvas = new Canvas(width, height);
    GraphicsContext gc = canvas.getGraphicsContext2D();
    private boolean playerTurn = true;
    @Override
    public void start(Stage stage) throws IOException {
        drawBoard();
        canvas.setOnMouseClicked(e -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                int x = (int)e.getX()/rect;
                int y = (int)e.getY()/rect;
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
        stage.setTitle("TicTacToe");
        stage.setScene(new Scene(new StackPane(canvas)));
        stage.show();
    }
    private void drawBoard() {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, width, height);
        gc.setStroke(Color.WHITESMOKE);
        for (int i = 0; i < width; i += rect) {
            for (int j = 0; j < height; j += rect) {
                gc.strokeRect(i, j, rect, rect);
                if (board[j/rect][i/rect] == 'x') {
                    gc.strokeLine(i, j, i+rect, j+rect);
                    gc.strokeLine(i, j+rect, i+rect, j);
                } else if (board[j/rect][i/rect] == 'o') {
                    gc.strokeRoundRect(i, j, rect, rect, rect, rect);
                }
            }
        }
    }
    private boolean validTurn(int y, int x) {
        return board[y][x] == ' ';
    }
    private void resetBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[j][i] = ' ';
            }
        }
    }

    public static void main(String[] args) {
        launch();
    }
}