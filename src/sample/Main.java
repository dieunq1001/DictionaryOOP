package sample;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

public class Main extends Application {

    public static final String E_V_FILE_PATH = "C:\\Users\\taotk\\IdeaProjects\\Dict11\\comps\\E_V.txt";
    protected static final String V_E_FILE_PATH = "C:\\Users\\taotk\\IdeaProjects\\Dict11\\comps\\V_E.txt";
    protected static final String FXML_FILE_PATH = "C:\\Users\\taotk\\IdeaProjects\\Dict11\\src\\sample\\View.fxml";
    private static final String SPLITTING_CHARACTERS = "<html>";

    /* tab 4 - History */
    @FXML
    private Tab History;
    @FXML
    public static ListView<String> listViewH;
    @FXML
    private WebView webViewH;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("View.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Dictionary");
        primaryStage.show();

        initComponents(scene);
    }

    public void initComponents(Scene scene) {
        webViewH = (WebView) scene.lookup("#webViewH");
        listViewH = (ListView<String>) scene.lookup("#listViewH");
        listViewH.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (Controller.dataE.get(newValue.trim()) != null) {
                        String definitionE = Controller.dataE.get(newValue.trim());
                        webViewH.getEngine().loadContent(definitionE, "text/html");
                    } else if (Controller.dataV.get(newValue.trim()) != null) {
                        String definitionV = Controller.dataV.get(newValue.trim());
                        webViewH.getEngine().loadContent(definitionV, "text/html");
                    }
                }
        );
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
