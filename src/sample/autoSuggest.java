package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Side;
import javafx.scene.control.*;

import java.util.*;

public class autoSuggest {
    private TextField textField;
    private ContextMenu entries;
    private SortedSet<String> keyw;

    public autoSuggest(TextField textField) {
        this.textField = textField;
        entries = new ContextMenu();
        keyw = new TreeSet<>();
        this.textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldvalue, String newvalue) {
                if (newvalue.length() == 0) {
                    entries.hide();
                } else {
                    LinkedList<String> sResult = new LinkedList<>();
                    sResult.addAll(keyw.subSet(newvalue, newvalue + Character.MAX_VALUE));
                    if (keyw.size() > 0) {
                        bornItems(sResult);
                        if (!entries.isShowing()) {
                            entries.show(textField, Side.BOTTOM, 0, 0);
                        }
                    } else {
                        entries.hide();
                    }
                }
            }
        });
    }

    public SortedSet<String> keyW() {
        return keyw;
    }

    public void bornItems(List<String> search) {
        List<MenuItem> menuItems = new LinkedList<>();
        int maxNumItems = 10;
        int count = Math.min(maxNumItems, search.size());

        for (int i = 0; i < count; i++) {
            String result = search.get(i);
            Label itemslabel = new Label(result);
            CustomMenuItem item = new CustomMenuItem(itemslabel, true);

            item.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    textField.setText(result);
                    entries.hide();
                }
            });
            menuItems.add(item);
        }
        entries.getItems().clear();
        entries.getItems().addAll(menuItems);
    }
}

