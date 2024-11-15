package repository;

import model.Account;
import model.Transaction;

import java.util.List;
import java.util.Map;

public class AccountRepo {
    Map<Integer, List<Account>> mapAccounts; //  Integer - это userId
    Map<Integer, List<Transaction>> mapTransaction; // Integer это AccountId
}
