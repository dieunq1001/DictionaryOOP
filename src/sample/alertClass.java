package sample;

import javafx.scene.control.Alert;

public class alertClass {

    public static void showAlertSuccessful() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thành công!");
        alert.setHeaderText(null);
        alert.setContentText("Thêm từ vào bộ từ điển thành công!");
        alert.showAndWait();
    }

    public static void showAlertFail() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thất bại!");
        alert.setHeaderText(null);
        alert.setContentText("Từ này đã có trong từ điển!");
        alert.showAndWait();
    }

    public static void showAlertNull() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thất bại!");
        alert.setHeaderText(null);
        alert.setContentText("Từ này chưa có trong từ điển!");
        alert.showAndWait();
    }

    public static void showAlertRemove() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thành công!");
        alert.setHeaderText(null);
        alert.setContentText("Đã xóa từ này khỏi từ điển!");
        alert.showAndWait();
    }

    public static void showAlertChange() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thành công!");
        alert.setHeaderText(null);
        alert.setContentText("Đã thay đổi nghĩa của từ này");
        alert.showAndWait();
    }

}
