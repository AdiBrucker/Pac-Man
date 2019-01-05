package View;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class AboutView {
    Label l_about;
    Button B_back;


    public AboutView(Stage stage)

    {
        try {
            initAboutView( stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void   initAboutView(Stage stage) throws Exception {

        GridPane pane = new GridPane();
        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.setVgap(10);
        pane.setHgap(10);


        BackgroundImage myB = new BackgroundImage(new Image("res/EmptyBackground.jpeg", 550, 600, false, true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        pane.setBackground(new Background(myB));


//        l_about = new Label("הוראות משחק: בתפריט הראשני תוכלו לבחור אם תרצו להתחיל לשחק , לצפות בהיסטוריית משחקים או ");
//        l_about.setMaxSize(300,300);
//        l_about.setMinSize(300,300);
//        l_about.setStyle("-fx-background-color: black ; -fx-text-fill:green ");
//        GridPane.setConstraints( l_about,3,15);



        Image image2 = new Image("res/INS.PNG");
        ImageView imgView = new ImageView(image2);
        GridPane.setConstraints( imgView,3,15);
        //  pane.getChildren().add(imgView );

        Button B_back = new Button("Back");
        B_back.setMaxSize(90,90);
        B_back.setOnAction(e->new StartGame(stage) );
        B_back.setStyle("-fx-background-color: white");
        GridPane.setConstraints( B_back,0,0);


        // GridPane.setConstraints( image2,3,15);



        pane.getChildren().addAll( B_back,imgView);

        Scene scene = new Scene(pane, 550,600);
        stage.setScene(scene);

        stage.show();



    }
}
