package DataLayer.DataAccessObjects;

import DataLayer.Models.TransactionModel;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Auror on 2017.05.24..
 */
public class TransactionDAO {
    private List<TransactionModel> transactions;
    private static TransactionDAO self;
    private static String defaultDataStoreXML = "dataStore.xml";
    private int maxId = 0;

    public static TransactionDAO GetTransactionDAO()
    {
        if(self == null)
        {
            self = new TransactionDAO();
            self.transactions = new ArrayList<>();
        }
        return self;
    }

    public List<TransactionModel> GetTransactionsFromXML(String filePath)
    {
        SAXBuilder builder = new SAXBuilder();
        File xmlFile = new File(filePath);

        try {

            Document document = builder.build(xmlFile);
            Element rootNode = document.getRootElement();
            List list = rootNode.getChild("Rpt").getChildren("Ntry");

            for (int i = 0; i < list.size(); i++) {
                Element node = (Element) list.get(i);
                this.transactions.add(new TransactionModel()
                    .withId(i)
                    .withUserId(0)
                    .withCurrency(node.getChild("Amt").getAttribute("Ccy").getValue())
                    .withIsCredit(Objects.equals(node.getChild("CdtDbtInd").getValue(), "CRDT"))
                    .withValue(Double.parseDouble(node.getChild("Amt").getValue()))
                    .withMessage(node.getChild("NtryDtls").getChild("TxDtls").getChild("RmtInf").getChild("Ustrd").getValue().replaceAll("\\d\\d\\d\\d.\\d\\d.\\d\\d", ""))
                    .withTransactionTime(node.getChild("BookgDt").getChild("Dt").getValue())
                    .withOtherAccount(Objects.equals(node.getChild("CdtDbtInd").getValue(), "CRDT") ?
                        node.getChild("NtryDtls").getChild("TxDtls").getChild("RltdPties").getChild("Cdtr").getChild("Nm").getValue() :
                        node.getChild("NtryDtls").getChild("TxDtls").getChild("RltdPties").getChild("Dbtr").getChild("Nm").getValue()));
            }


        } catch (IOException | JDOMException io) {
            System.out.println(io.getMessage());
        }

        SaveTransactionsToXML();

        for (TransactionModel transaction : transactions)
        {
            System.out.println(transaction.getValue());
        }

        return transactions;
    }

    public boolean GetTransactionsFromDataStore(String path)
    {
        if(path != null && !path.isEmpty())
            defaultDataStoreXML = path;

        SAXBuilder builder = new SAXBuilder();
        File xmlFile = new File(path);

        try {

            Document document = builder.build(xmlFile);
            Element rootNode = document.getRootElement();
            List list = rootNode.getChild("transactions").getChildren("transaction");

            for (int i = 0; i < list.size(); i++) {
                Element node = (Element) list.get(i);
                TransactionModel newTransactionModel = new TransactionModel()
                        .withId(Integer.parseInt(node.getChild("id").getValue()))
                        .withUserId(Integer.parseInt(node.getChild("userId").getValue()))
                        .withCurrency(node.getChild("currency").getValue())
                        .withIsCredit(Boolean.parseBoolean(node.getChild("isCredit").getValue()))
                        .withValue(Double.parseDouble(node.getChild("value").getValue()))
                        .withMessage(node.getChild("message").getValue())
                        .withTransactionTime(node.getChild("transactionTime").getValue())
                        .withOtherAccount(node.getChild("otherAccount").getValue());

                if(newTransactionModel.getId() >= maxId)
                {
                    maxId = newTransactionModel.getId();
                }
                transactions.add(newTransactionModel);
            }


        } catch (Exception io) {
            System.out.println(io.getMessage());
            return false;
        }

        return true;
    }

    public boolean SaveTransactionsToXML()
    {
        Element dataStoreRoot = new Element("dataStoreRoot");
        Document document = new Document(dataStoreRoot);

        Element transactionsElement = new Element("transactions");
        dataStoreRoot.addContent(transactionsElement);

        for(TransactionModel t : transactions)
        {
            Element transaction = new Element("transaction");
            transactionsElement.addContent(transaction);
            transaction.addContent(new Element("id").setText(String.valueOf(t.getId())));
            transaction.addContent(new Element("userId").setText(String.valueOf(t.getUserId())));
            transaction.addContent(new Element("isCredit").setText(String.valueOf(t.isCredit())));
            transaction.addContent(new Element("value").setText(String.valueOf(t.getValue())));
            transaction.addContent(new Element("transactionTime").setText(String.valueOf(t.getTransactionTime()).replaceAll("T00:00", "")));
            transaction.addContent(new Element("currency").setText(String.valueOf(t.getCurrency())));
            transaction.addContent(new Element("message").setText(String.valueOf(t.getMessage())));
            transaction.addContent(new Element("otherAccount").setText(String.valueOf(t.getOtherAccount())));
        }
        XMLOutputter xmlOutputter = new XMLOutputter();

        xmlOutputter.setFormat(Format.getPrettyFormat());
        try
        {
            xmlOutputter.output(document, new FileOutputStream(defaultDataStoreXML));
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public List<TransactionModel> GetTransactions()
    {
        return transactions;
    }

    public TransactionModel GetTransactionById(int id)
    {
        return null;
    }

    public List<TransactionModel> GetTransactionsOfUser(int userId)
    {
        return null;
    }

    public List<TransactionModel> GetTransactionsBetween(LocalDateTime begin, LocalDateTime end)
    {
        return null;
    }
}
