package com.example.saanpsidi;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class SnakeLadder extends Application {

    public final int tileSize=40;
    int height=10;
    int width=10;
    int yLine=430;
    int diceValue;
    Label randResult;

    Group tileGroup = new Group();
    Player playerOne, playerTwo;

    boolean gameStart=true, turnOnePlayer=true, turnTwoPlayer =false;


    public Pane createContent(){
        Pane root = new Pane();
        root.setPrefSize(width * tileSize, height * tileSize+80);
        root.getChildren().addAll(tileGroup);


        for (int i = 0; i <height ; i++) {
            for (int j = 0; j <width ; j++) {

                Tile tile= new Tile(tileSize, tileSize);
                tile.setTranslateX(j * tileSize);
                tile.setTranslateY(i * tileSize);
                tileGroup.getChildren().addAll(tile);

            }
        }

            //add label
        randResult =  new Label("Start the Game");
        randResult.setTranslateX(160);
        randResult.setTranslateY(yLine-20);
            //add button
        Button playerOneButton = new Button("Player 1");
        playerOneButton.setTranslateX(20);
        playerOneButton.setTranslateY(yLine);
        playerOneButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(gameStart)
                {
                    if(turnOnePlayer)
                    {
                        getDiceValue();
                        playerOne.movePlayer(diceValue);
                        playerOne.playerAtSnakeOrLadder();
                        turnOnePlayer=false;
                        turnTwoPlayer =true;
                    }
                }

            }
        });

        Button gameButton = new Button("Start Game");
        gameButton.setTranslateX(160);
        gameButton.setTranslateY(yLine);

        Button playerTwoButton = new Button("Player 2");
        playerTwoButton.setTranslateX(320);
        playerTwoButton.setTranslateY(yLine);
        playerTwoButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(gameStart)
                {
                    if(turnTwoPlayer)
                    {
                        getDiceValue();
                        playerTwo.movePlayer(diceValue);
                        playerTwo.playerAtSnakeOrLadder();
                        turnTwoPlayer=false;
                        turnOnePlayer =true;
                    }
                }

            }
        });


        playerOne =  new Player(tileSize, Color.BLACK);
        playerTwo = new Player(tileSize-10, Color.WHITE);


        Image img= new Image("C:\\Users\\NPS\\IdeaProjects\\SaanpSidi\\src\\main\\resources\\snakeLadderBckImg.jpg");
        ImageView boardImage= new ImageView();
        boardImage.setImage(img);
        boardImage.setFitWidth(tileSize*width);
        boardImage.setFitHeight(tileSize*height);

        tileGroup.getChildren().addAll(boardImage,
                                      randResult, playerOneButton,
                                      gameButton, playerTwoButton,
                                       playerOne.getGamePiece(),
                                        playerTwo.getGamePiece());



        return root;
    }

    private void getDiceValue(){
        diceValue=(int)(Math.random()*6+1);
        randResult.setText(Integer.toString(diceValue));
    }

    @Override
    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(createContent());
        stage.setTitle("Saanp Sidi");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}