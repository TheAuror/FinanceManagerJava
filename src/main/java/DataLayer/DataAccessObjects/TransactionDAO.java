package DataLayer.DataAccessObjects;

import DataLayer.Models.TransactionModel;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Auror on 2017.05.24..
 */
public class TransactionDAO {
    private List<TransactionModel> transactions;
    private static TransactionDAO self;

    public static TransactionDAO GetTransactionDAO()
    {
        if(self == null)
        {
            self = new TransactionDAO();
            self.transactions = self.GetTransactionsFromXML();
        }
        return self;
    }

    public List<TransactionModel> GetTransactionsFromXML()
    {


        return transactions;
    }

    public boolean SaveTransactionsToXML()
    {
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
