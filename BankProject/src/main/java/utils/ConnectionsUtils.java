package utils;

import DAOPack.EnityClasses.ExchangeRate;
import DAOPack.EnityClasses.User;
import DAOPack.EnityClasses.Wallet;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;

public class ConnectionsUtils {


    public static EntityManagerFactory factory = Persistence.createEntityManagerFactory("BankProject");
    private static ArrayList<User> users = new ArrayList<>();
    private static ArrayList<Wallet> wallets = new ArrayList<>();

    public static void initBankDataBase() {
        initUsers();
        initWallets();
        setUsersWallets();
        initExchange();
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        try {
            for (int i = 0; i < 4; i++) {
                manager.persist(users.get(i));
                manager.persist(wallets.get(i));
            }
            manager.getTransaction().commit();
        } catch (Exception ex) {
            if (manager.getTransaction().isActive()) {
                manager.getTransaction().rollback();
            }
            throw new RuntimeException(ex);
        }
    }

    private static void initUsers() {
        User user1 = new User("Vasya",25,null);
        User user2 = new User("Petya",23,null);
        User user3 = new User("Ivan",30,null);
        User user4 = new User("Mitya",28,null);
        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);

    }

    private static void initWallets() {
        Wallet wallet1 = new Wallet(users.get(0), 51687556);
        Wallet wallet2 = new Wallet(users.get(1), 51687332);
        Wallet wallet3 = new Wallet(users.get(2), 51689321);
        Wallet wallet4 = new Wallet(users.get(3), 51686632);
        wallets.add(wallet1);
        wallets.add(wallet2);
        wallets.add(wallet3);
        wallets.add(wallet4);

    }

    private static void setUsersWallets() {
        for (int i = 0; i < 4; i++) {
            users.get(i).setWallet(wallets.get(i));
        }
    }

    private static void initExchange() {
        ExchangeRate er1 = new ExchangeRate("USD",27);
        ExchangeRate er2 = new ExchangeRate("EUR", 31);
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        try {
            manager.persist(er1);
            manager.persist(er2);
            manager.getTransaction().commit();
            System.out.println("Exchange commit!");
        } catch (Exception ex) {
            if(manager.getTransaction().isActive()) {
                manager.getTransaction().rollback();
            }
            throw new RuntimeException(ex);
        }
    }
}
