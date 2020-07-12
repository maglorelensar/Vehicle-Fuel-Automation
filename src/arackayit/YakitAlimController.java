/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arackayit;

import Daolar.YakitAlimDao;
import arackayit.Tablolar.YakitAlim;
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
public class YakitAlimController implements Initializable {

    @FXML
    private TextField plaka;
    @FXML
    private TextField litre;
    @FXML
    private TextField fiyat;
    @FXML
    private TextField adres;
    @FXML
    private DatePicker tarih;

    @FXML
    private TableView<YakitAlim> tableView;

    YakitAlimDao yakitAlimDao;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        yakitAlimDao = new YakitAlimDao();

        EditButtonAdd();
        DeleteButtonAdd();

        yakitAlimListesi();

    }

    @FXML
    private void yakitAlimKaydet(ActionEvent event) throws IOException {

        YakitAlim model = new YakitAlim(plaka.getText(), Double.parseDouble(litre.getText()), Double.parseDouble(fiyat.getText()), tarih.getValue().toString(), adres.getText());

        yakitAlimDao.Kaydet(model);
        yakitAlimListesi();

        plaka.setText(null);
        litre.setText(null);
        fiyat.setText(null);
        adres.setText(null);
        tarih.setValue(null);
    }

    private void yakitAlimListesi() {
        ArrayList<YakitAlim> list = yakitAlimDao.ListeGetir();

        ObservableList<YakitAlim> data = tableView.getItems();
        data.clear();

        for (YakitAlim model : list) {
            data.add(model);
        }

    }

    void YakitAlimSil(YakitAlim model) {
        yakitAlimDao.Sil(model);
        yakitAlimListesi();
    }

    private void EditButtonAdd() {
        TableColumn actionCol = new TableColumn("Düzenle");
        actionCol.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));

        Callback<TableColumn<YakitAlim, String>, TableCell<YakitAlim, String>> cellFactory;
        cellFactory = //
                new Callback<TableColumn<YakitAlim, String>, TableCell<YakitAlim, String>>() {
                    @Override
                    public TableCell call(final TableColumn<YakitAlim, String> param) {
                        final TableCell<YakitAlim, String> cell;
                        cell = new TableCell<YakitAlim, String>() {
                            
                            final Button btnedit = new Button("Düzenle");

                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {

                                    btnedit.setOnAction((ActionEvent event) -> {
                                        YakitAlim model = getTableView().getItems().get(getIndex());
                                        YakitAlimSil(model);

                                        plaka.setText(model.getPlaka());
                                        litre.setText(Double.toString(model.getLitre()));
                                        fiyat.setText(Double.toString(model.getFiyat()));
                                        adres.setText(model.getAdres());
                                        String date = "";
                                        LocalDate localDate = null;
                                        DateTimeFormatter formatter = null;

                                        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                                        localDate = LocalDate.parse(model.getTarih(), formatter);

                                        tarih.setValue(localDate);
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

        Callback<TableColumn<YakitAlim, String>, TableCell<YakitAlim, String>> cellFactory
                = //
                new Callback<TableColumn<YakitAlim, String>, TableCell<YakitAlim, String>>() {
                    @Override
                    public TableCell call(final TableColumn<YakitAlim, String> param) {
                        final TableCell<YakitAlim, String> cell = new TableCell<YakitAlim, String>() {

                            final Button btn = new Button("Sil");

                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    btn.setOnAction(event -> {
                                        YakitAlim model = getTableView().getItems().get(getIndex());
                                        Alert alert = new Alert(AlertType.INFORMATION, "Silmek İstediginize Emin misiniz? YakitAlim Plaka : " + model.getPlaka() + " ?",
                                                ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
                                        alert.showAndWait();

                                        if (alert.getResult() == ButtonType.YES) {
                                            YakitAlimSil(model);
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
