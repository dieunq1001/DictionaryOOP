package sample;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.spec.RSAOtherPrimeInfo;
import java.util.*;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import static sample.Main.E_V_FILE_PATH;
import static sample.Main.V_E_FILE_PATH;
import static sample.alertClass.*;

public class Controller implements Initializable {

    public static ArrayList<Word> wordE = new ArrayList<Word>();
    public static ArrayList<Word> wordV = new ArrayList<Word>();
    private SortedSet<String> keywordE;
    private SortedSet<String> keywordV;
    public static Map<String, String> dataE = new HashMap<>();
    public static Map<String, String> dataV = new HashMap<>();

    private autoSuggest text;

    @FXML
    private TabPane tabPane;

    /* tab 1 - english vietnamese */
    @FXML
    private Tab engVie;
    @FXML
    private TextField TextE;
    @FXML
    private Button S1;
    @FXML
    private WebView webViewE;
    @FXML
    private Button Voice1;
    @FXML
    private Button apiTestE;
    /* tab 2 - vietnamese english */
    @FXML
    private Tab vieEng;
    @FXML
    private TextField TextV;
    @FXML
    private Button S2;
    @FXML
    private WebView webViewV;
    @FXML
    private Button Voice2;
    @FXML
    private Button apiTestV;
    /* tab 3 - adding new word to already exist file */
    @FXML
    private Tab add;
    @FXML
    private TextField TextAdd;
    @FXML
    private TextField wordMean;
    @FXML
    private Button addVieEng;
    @FXML
    private Button addEngVie;
    /* tab 4 - remove and change word in dictionary */
    @FXML
    private Tab removeChange;
    @FXML
    private TextField TextRCW;
    @FXML
    private TextArea TextDef;
    @FXML
    private Button checkAV;
    @FXML
    private Button checkVA;
    @FXML
    private Button changeDefE;
    @FXML
    private Button changeDefV;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Main.readDataEV();
            Main.readDataVE();
        } catch (IOException e) {
            e.printStackTrace();
        }
        keywordE = new TreeSet<>();
        for(int i=0;i<wordE.size();i++) {
            keywordE.add(wordE.get(i).getWord());
        }
        keywordV = new TreeSet<>();
        for(int i=0;i<wordV.size();i++) {
            keywordV.add(wordV.get(i).getWord());
        }
        text = new autoSuggest(TextE);
        text.keyW().addAll(getKeywordE());
        text = new autoSuggest(TextV);
        text.keyW().addAll(getKeywordV());
    }

    // TexttoSpeech
    @FXML
    private void TextSpeechE(ActionEvent event) {
        String s = TextE.getText();
        TextToSpeech tts = new TextToSpeech();
        tts.speech(s);
    }

    @FXML
    private void TextSpeechV(ActionEvent event) {
        String s = TextV.getText();
        TextToSpeech tts = new TextToSpeech();
        tts.speech(s);
    }

    // finding words from an already-read file
    @FXML
    private void findEng(ActionEvent event) {
        String s = TextE.getText();
        String mean = null;
        Map<String, String> tem_data = new HashMap<>();
        for (int i = 0; i < wordE.size(); i++) {
            if (wordE.get(i).getWord().contentEquals(s)) {
                mean = wordE.get(i).getDef();
                dataE.put(wordE.get(i).getWord(),wordE.get(i).getDef());
                tem_data.put(wordE.get(i).getWord(),wordE.get(i).getDef());
                break;
            }
        }
        webViewE.getEngine().loadContent(mean, "text/html");
        Main.listViewH.getItems().addAll(tem_data.keySet());
        tem_data.clear();
    }

    @FXML
    private void findVie(ActionEvent event) {
        String s = TextV.getText();
        String mean = null;
        Map<String, String> tem_data = new HashMap<>();
        for (int i = 0; i < wordV.size(); i++) {
            if (wordV.get(i).getWord().contentEquals(s)) {
                mean = wordV.get(i).getDef();
                dataV.put(wordV.get(i).getWord(),wordV.get(i).getDef());
                tem_data.put(wordV.get(i).getWord(),wordV.get(i).getDef());
                break;
            }
        }
        webViewV.getEngine().loadContent(mean, "text/html");
        Main.listViewH.getItems().addAll(tem_data.keySet());
        tem_data.clear();
    }

    // adding word to dictionary permanently
    @FXML
    private void setAddVieEng(ActionEvent event) {
        String addw = TextAdd.getText();
        String meanw = wordMean.getText();
        boolean isFound = false;
        for (int i = 0; i < wordV.size(); i++) {
            if (wordV.get(i).getWord().contentEquals(addw)) {
                isFound = true;
                break;
            }
        }
        if(isFound) {
            showAlertFail();
            return;
        }
        try {
            writeToFileVE writeVE = new writeToFileVE(addw, meanw);
            Word w = new Word(addw,"<i>"+addw+"</i><br/><ul><li><font color='#cc0000'><b>"+meanw+"</b></font></li></ul></html>"+"\n");
            wordV.add(w);
            keywordV.add(addw);
            text = new autoSuggest(TextV);
            text.keyW().addAll(getKeywordV());
            showAlertSuccessful();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void setAddEngVie(ActionEvent event) {
        String addw = TextAdd.getText();
        String meanw = wordMean.getText();
        boolean isFound = false;
        for (int i = 0; i < wordE.size(); i++) {
            if (wordE.get(i).getWord().contentEquals(addw)) {
                isFound = true;
                break;
            }
        }
        if(isFound) {
            showAlertFail();
            return;
        }
        try {
            writeToFileEV writeEV = new writeToFileEV(addw, meanw);
            Word w = new Word(addw,"<i>"+addw+"</i><br/><ul><li><font color='#cc0000'><b>"+meanw+"</b></font></li></ul></html>"+"\n");
            wordE.add(w);
            keywordE.add(addw);
            text = new autoSuggest(TextE);
            text.keyW().addAll(getKeywordE());
            showAlertSuccessful();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // API google script
    @FXML
    public void setApiTestE(ActionEvent event) throws IOException {
        String s = TextE.getText();
        WebEngine webEngine = webViewE.getEngine();
        webViewE.getEngine().loadContent(translate("en","vi",s), "text/html");
    }

    @FXML
    public void setApiTestV(ActionEvent event) throws IOException {
        String s = TextV.getText();
        WebEngine webEngine = webViewV.getEngine();
        webViewV.getEngine().loadContent(translate("vi","en",s), "text/html");
    }

    // check if the word is in dictionary
    @FXML
    public void setCheckAV(ActionEvent event) {
        String wordCheck = TextRCW.getText();
        boolean isFound = false;
        for (int i = 0; i < wordE.size(); i++) {
            if (wordE.get(i).getWord().contentEquals(wordCheck)) {
                isFound = true;
                break;
            }
        }
        if(!isFound) {
            showAlertNull();
            return;
        }
        String def = null;
        for (int i = 0; i < wordE.size(); i++) {
            if (wordE.get(i).getWord().contentEquals(wordCheck)) {
                def = wordE.get(i).getDef();
                break;
            }
        }
        TextDef.appendText(def);
    }

    @FXML
    public void setCheckVA(ActionEvent event) {
        String wordCheck = TextRCW.getText();
        boolean isFound = false;
        for (int i = 0; i < wordV.size(); i++) {
            if (wordV.get(i).getWord().contentEquals(wordCheck)) {
                isFound = true;
                break;
            }
        }
        if(!isFound) {
            showAlertNull();
            return;
        }
        String def = null;
        for (int i = 0; i < wordV.size(); i++) {
            if (wordV.get(i).getWord().contentEquals(wordCheck)) {
                def = wordV.get(i).getDef();
                break;
            }
        }
        TextDef.appendText(def);
    }

    // delete the word but not permanent
    @FXML
    public void deleteAV(ActionEvent event) {
        String wordCheck = TextRCW.getText();
        boolean isFound = false;
        int index = 0;
        for (int i = 0; i < wordE.size(); i++) {
            if (wordE.get(i).getWord().contentEquals(wordCheck)) {
                isFound = true;
                index = i;
                break;
            }
        }
        if(!isFound) {
            showAlertNull();
            return;
        }
        else {
            wordE.remove(index);
            keywordE.remove(wordCheck);
            text = new autoSuggest(TextE);
            text.keyW().addAll(getKeywordE());
            showAlertRemove();
        }
    }

    @FXML
    public void deleteVA(ActionEvent event) {
        String wordCheck = TextRCW.getText();
        boolean isFound = false;
        int index = 0;
        for (int i = 0; i < wordV.size(); i++) {
            if (wordV.get(i).getWord().contentEquals(wordCheck)) {
                isFound = true;
                index = i;
                break;
            }
        }
        if(!isFound) {
            showAlertNull();
            return;
        }
        else {
            wordV.remove(index);
            keywordV.remove(wordCheck);
            text = new autoSuggest(TextV);
            text.keyW().addAll(getKeywordV());
            showAlertRemove();
        }
    }

    // change the definition of the word included html
    @FXML
    public void setChangeDefE(ActionEvent event) {
        String newdef = TextDef.getText();
        String word = TextRCW.getText();
        for (int i = 0; i < wordE.size(); i++) {
            if (wordE.get(i).getWord().contentEquals(word)) {
                wordE.get(i).setDef(newdef);
                showAlertChange();
                break;
            }
        }
    }

    @FXML
    public void setChangeDefV(ActionEvent event) {
        String newdef = TextDef.getText();
        String word = TextRCW.getText();
        for (int i = 0; i < wordV.size(); i++) {
            if (wordV.get(i).getWord().contentEquals(word)) {
                wordV.get(i).setDef(newdef);
                showAlertChange();
                break;
            }
        }
    }

    private static String translate(String langFrom, String langTo, String text) throws IOException {
        String urlStr = "https://script.google.com/macros/s/AKfycbzfliNj0JzzdtunuazOUm8yJ3guSX1begZi7-WST3mREVxjRmg/exec" +
                "?q=" + URLEncoder.encode(text, "UTF-8") +
                "&target=" + langTo +
                "&source=" + langFrom;
        URL url = new URL(urlStr);
        StringBuilder response = new StringBuilder();
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }

    public SortedSet<String> getKeywordE(){
        return keywordE;
    }

    public SortedSet<String> getKeywordV(){
        return keywordV;
    }

}
