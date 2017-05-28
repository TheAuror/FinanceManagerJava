/*
 * Copyright (C) 2017 Auror
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package DataLayer.Models;

import java.time.LocalDateTime;

/**
 * Represents an exchange of money
 * @author Auror
 */
public class TransactionModel {
    private int id;
    private int userId;
    private boolean isCredit;
    private double value;
    private LocalDateTime transactionTime;
    private String currency;
    private String message;
    private String otherAccount;

    public TransactionModel()
    {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public boolean isCredit() {
        return isCredit;
    }

    public void setIsCredit(boolean isCredit) {
        this.isCredit = isCredit;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public LocalDateTime getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(String transactionTime) {
        LocalDateTime dateTime = LocalDateTime.of(Integer.parseInt(transactionTime.split("\\-")[0]),
                                                  Integer.parseInt(transactionTime.split("\\-")[1]),
                                                  Integer.parseInt(transactionTime.split("\\-")[2]), 0, 0);
        this.transactionTime = dateTime;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getMessage() {
        if(message == null || message.replaceAll(" ", "").isEmpty())
            return "No message";
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public TransactionModel withId(int id) {
        setId(id);
        return this;
    }

    public TransactionModel withUserId(int userId) {
        setUserId(userId);
        return this;
    }

    public TransactionModel withIsCredit(boolean isCredit) {
        setIsCredit(isCredit);
        return this;
    }

    public TransactionModel withValue(double value) {
        setValue(value);
        return this;
    }

    public TransactionModel withTransactionTime(String transactionTime) {
        setTransactionTime(transactionTime);
        return this;
    }

    public TransactionModel withCurrency(String currency) {
        setCurrency(currency);
        return this;
    }

    public TransactionModel withMessage(String message) {
        setMessage(message);
        return this;
    }

    public String getOtherAccount()
    {
        return otherAccount;
    }

    public void setOtherAccount(String otherAccount)
    {
        this.otherAccount = otherAccount;
    }

    public TransactionModel withOtherAccount(String otherAccount) {
        setOtherAccount(otherAccount);
        return this;
    }
}
