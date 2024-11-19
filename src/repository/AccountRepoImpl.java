package repository;

import model.Account;

import java.util.*;
import java.util.stream.Collectors;

public class AccountRepoImpl implements AccountRepo {

    private final Map<Integer, List<Account>> mapAccounts = new HashMap<>();

    @Override
    public void addAccount(Account account) {
        mapAccounts.computeIfAbsent(account.getUserId(), k -> new ArrayList<>()).add(account);
    }

    @Override
    public List<Account> getAccountsByUserId(int userId) {
//        return mapAccounts.getOrDefault(userId, Collections.emptyList());
        List<Account> accounts = mapAccounts.get(userId);
        if (accounts == null) {
            return Collections.emptyList();
        }
        return new ArrayList<>(accounts);
    }

    @Override
    public Account getAccountById(int accountId) {
        return mapAccounts.values().stream()
                .flatMap(List::stream)
                .filter(account -> account.getId() == accountId)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void deleteAccount(int userId, int accountId) {
        List<Account> accounts = mapAccounts.get(userId);
        if (accounts == null || accounts.isEmpty()) {
            throw new IllegalArgumentException("Аккаунты для пользователя с ID " + userId + " не найдены.");
        }

        accounts.removeIf(account -> account.getId() == accountId);

        if (accounts.isEmpty()) {
            mapAccounts.remove(userId);
        }
    }

    @Override
    public boolean existsById(int accountId) {
        return mapAccounts.values().stream()
                .flatMap(List::stream)
                .anyMatch(account -> account.getId() == accountId);
    }

    @Override
    public Integer getAccountId(int accountId) {
        Account account = getAccountById(accountId);
        return account != null ? account.getId() : null;
    }

    @Override
    public void clear() {
        mapAccounts.clear();
        System.out.println("Accounts cleared");
    }
}
