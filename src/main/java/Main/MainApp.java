package Main;

import DataLayer.DataAccessObjects.TransactionDAO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {
    private String dataStoreXMLPath = "dataStore.xml";

    @Override
    public void start(Stage stage) throws Exception {

        String xmlFolder = "C:\\Users\\Auror\\Desktop\\Számlatörténet\\";
        TransactionDAO transactionDAO = TransactionDAO.GetTransactionDAO();
        transactionDAO.GetTransactionsFromDataStore(dataStoreXMLPath);
        transactionDAO.GetTransactionsFromXML(xmlFolder + "export (4).xml");

        Parent root = FXMLLoader.load(getClass()
                .getResource("/fxml/MainScene.fxml"));
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        
        stage.setTitle("Finance Manager");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
