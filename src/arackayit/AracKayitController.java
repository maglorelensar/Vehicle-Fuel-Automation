
package arackayit;

import Daolar.AracDao;
import arackayit.Tablolar.Arac;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author g3250
 */
public class AracKayitController implements Initializable {

    @FXML
    private TextField Plaka;
    @FXML
    private TextField YakitTipi;
    @FXML
    private TextField AracTipi;
    @FXML
    private TextField Kullanici;
    @FXML
    private DatePicker KayitTarihi;

    @FXML
    private TableView<Arac> tableView;

    AracDao aracDao;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        aracDao = new AracDao();

        EditButtonAdd();
        DeleteButtonAdd();

        aracListesi();

    }

    @FXML
    private void aracKaydet(ActionEvent event) throws IOException {

        Arac arac = new Arac(Plaka.getText(), YakitTipi.getText(), AracTipi.getText(), Kullanici.getText(), KayitTarihi.getValue().toString());

        aracDao.Kaydet(arac);
        aracListesi();

        Plaka.setText(null);
        YakitTipi.setText(null);
        AracTipi.setText(null);
        Kullanici.setText(null);
        KayitTarihi.setValue(null);
    }

    private void aracListesi() {
        ArrayList<Arac> aracListesi = aracDao.ListeGetir();

        ObservableList<Arac> data = tableView.getItems();
        data.clear();

        for (Arac arac : aracListesi) {
            data.add(arac);
        }
        System.out.println(data);

    }

    void AracSil(Arac arac) {
        aracDao.Sil(arac);
        aracListesi();
    }

    private void EditButtonAdd() {
        TableColumn actionCol = new TableColumn("Düzenle");
        actionCol.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));

        Callback<TableColumn<Arac, String>, TableCell<Arac, String>> cellFactory
                = //
                new Callback<TableColumn<Arac, String>, TableCell<Arac, String>>() {
                    @Override
                    public TableCell call(final TableColumn<Arac, String> param) {
                        final TableCell<Arac, String> cell = new TableCell<Arac, String>() {

                            final Button btnedit = new Button("Düzenle");

                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {

                                    btnedit.setOnAction(event -> {
                                        Arac arac = getTableView().getItems().get(getIndex());
                                        AracSil(arac);

                                        Plaka.setText(arac.getPlaka());
                                        YakitTipi.setText(arac.getYakitTipi());
                                        AracTipi.setText(arac.getAracTipi());
                                        Kullanici.setText(arac.getKullanici());
                                        String date = "";
                                        LocalDate localDate = null;
                                        DateTimeFormatter formatter = null;

                                        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                                        localDate = LocalDate.parse(arac.getKayitTarihi(), formatter);

                                        KayitTarihi.setValue(localDate);
                                    });
                                    setGraphic(btnedit);
                                    setText(null);
                                }
                            }
                        };
                        return cell;
                    }
                };

        actionCol.setCellFactory(cellFactory);
        tableView.getColumns().add(actionCol);
    }

    private void DeleteButtonAdd() {
        TableColumn actionCol = new TableColumn("Sil");
        actionCol.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));

        Callback<TableColumn<Arac, String>, TableCell<Arac, String>> cellFactory
                = //
                new Callback<TableColumn<Arac, String>, TableCell<Arac, String>>() {
                    @Override
                    public TableCell call(final TableColumn<Arac, String> param) {
                        final TableCell<Arac, String> cell = new TableCell<Arac, String>() {

                            final Button btn = new Button("Sil");

                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    btn.setOnAction(event -> {
                                        Arac arac = getTableView().getItems().get(getIndex());
                                        Alert alert = new Alert(AlertType.INFORMATION, "Silmek İstediginize Emin misiniz? Arac Plaka : " + arac.getPlaka() + " ?", 
                                                ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
                                        alert.showAndWait();

                                        if (alert.getResult() == ButtonType.YES) {
                                            AracSil(arac);
                                        }

                                    });
                                    setGraphic(btn);

                                    setText(null);
                                }
                            }
                        };
                        return cell;
                    }
                };

        actionCol.setCellFactory(cellFactory);
        tableView.getColumns().add(actionCol);
    }

}
