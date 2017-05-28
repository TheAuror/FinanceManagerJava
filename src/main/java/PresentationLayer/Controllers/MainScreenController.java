package PresentationLayer.Controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import DataLayer.DataAccessObjects.TransactionDAO;
import DataLayer.Models.TransactionModel;
import com.sun.javafx.binding.SelectBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class MainScreenController implements Initializable {
    public static ObservableList<TransactionModel> transactions = FXCollections.observableArrayList();

    private TransactionDAO transactionDAO;

    public ToggleButton showCreditButton;
    public ToggleButton showDebitButton;
    public TextField transactionMessageField;
    public Label transactionMessageLabel;
    public Label transactionValueLabel;
    public TextField transactionValueField;
    public Label transactionOwnerLabel;
    public TextField transactionOwnerField;
    public Label otherMemberOfTransactionLabel;
    public TextField otherMemberOfTransactionField;
    public Label transactionTimeLabel;
    public TextField transactionTimeField;
    public Button refreshButton;
    public TableView mainTableView;


    List<TransactionModel> transactionModels = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        transactionDAO = TransactionDAO.GetTransactionDAO();
        transactionModels = transactionDAO.GetTransactions();

        TableColumn<TransactionModel, String> messageColumn = new TableColumn<>("Message");
        messageColumn.setMinWidth(200);
        messageColumn.setCellValueFactory(new PropertyValueFactory<>("message"));

        TableColumn<TransactionModel, String> dateColumn = new TableColumn<>("Date");
        dateColumn.setMinWidth(200);
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("transactionTime"));

        mainTableView.getColumns().addAll(messageColumn, dateColumn);

        mainTableView.getSelectionModel().selectedItemProperty().addListener((observable1, oldValue1, newValue1) -> {
            if(newValue1 != null)
            {
                TransactionModel transactionModel = (TransactionModel)newValue1;
                transactionMessageField.setText(transactionModel.getMessage());
                otherMemberOfTransactionField.setText(transactionModel.getOtherAccount());
                transactionOwnerField.setText(String.valueOf(transactionModel.getUserId()));
                transactionTimeField.setText(transactionModel.getTransactionTime().toString());
                transactionValueField.setText(String.valueOf(transactionModel.getValue()));
            }
        });

        refreshButton.setOnAction(event -> RefreshTableView());

        showCreditButton.fire();
        showDebitButton.fire();

        showCreditButton.selectedProperty().addListener((observable, oldValue, newValue) -> {
            showCredit = newValue;
            RefreshTableView();
        });
        showDebitButton.selectedProperty().addListener((observable, oldValue, newValue) -> {
            showDebit = newValue;
            RefreshTableView();
        });

        RefreshTableView();
    }

    public boolean showCredit = true;
    public boolean showDebit = true;

    private void RefreshTableView()
    {
        transactions = FXCollections.observableArrayList();

        transactionDAO.GetTransactions().forEach((e) -> {
            if(e.isCredit() && showCredit)
                transactions.add(e);
            if(!e.isCredit() && showDebit)
                transactions.add(e);
        });
        mainTableView.setItems(transactions);
    }
}
