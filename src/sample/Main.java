package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main extends Application {

    public static final String E_V_FILE_PATH = "C:\\Users\\taotk\\IdeaProjects\\Dict11\\comps\\E_V.txt";
    protected static final String V_E_FILE_PATH = "C:\\Users\\taotk\\IdeaProjects\\Dict11\\comps\\V_E.txt";
    protected static final String FXML_FILE_PATH = "C:\\Users\\taotk\\IdeaProjects\\Dict11\\src\\sample\\View.fxml";
    private static final String SPLITTING_CHARACTERS = "<html>";

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("View.fxml"));
        primaryStage.setTitle("Dictionary");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void readDataEV() throws IOException {
        FileReader fis = new FileReader(E_V_FILE_PATH);
        BufferedReader br = new BufferedReader(fis);
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(SPLITTING_CHARACTERS);
            String word = parts[0];
            String definition = parts[1];
            Word wordObj = new Word(word, definition);
            Controller.wordE.add(wordObj);
        }
    }

    public static void readDataVE() throws IOException {
        FileReader fis = new FileReader(V_E_FILE_PATH);
        BufferedReader br = new BufferedReader(fis);
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(SPLITTING_CHARACTERS);
            String word = parts[0];
            String definition = parts[1];
            Word wordObj = new Word(word, definition);
            Controller.wordV.add(wordObj);
        }

    }

    public static void main(String[] args) {
        launch(args);
    }
}
