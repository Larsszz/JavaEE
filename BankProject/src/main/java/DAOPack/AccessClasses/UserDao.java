package DAOPack.AccessClasses;

import DAOPack.EnityClasses.Transaction;
import DAOPack.EnityClasses.User;
import DAOPack.EnityClasses.Wallet;
import utils.ConnectionsUtils;

import javax.persistence.EntityManager;
import java.io.Serializable;

public class UserDao extends BaseDao {

    private static EntityManager manager = ConnectionsUtils.factory.createEntityManager();

    public static void addCash(Serializable userId, int summ, String currency) {
        manager.getTransaction().begin();
        try {
            User user = manager.find(User.class, userId);
            Wallet wallet = user.getWallet();
            WalletDao.addMoney(wallet, summ, currency);
            Transaction transaction = new Transaction(null, wallet, currency, summ);
            manager.persist(wallet);
            manager.persist(transaction);
            manager.getTransaction().commit();
        } catch (Exception ex) {
            manager.getTransaction().rollback();
            throw new RuntimeException(ex);
        }
    }

    public static void sendMoney(Serializable userIdFrom, Serializable userIdTo, int summ, String currency) {
        manager.getTransaction().begin();
        try {
            User userFrom = manager.find(User.class, userIdFrom);
            User userTo = manager.find(User.class, userIdTo);
            Wallet walletFrom = userFrom.getWallet();
            Wallet walletTo = userTo.getWallet();
            WalletDao.takeMoney(walletFrom, summ, currency);
            WalletDao.addMoney(walletTo, summ, currency);
            Transaction transaction = new Transaction(walletFrom, walletTo, currency, summ);
            manager.persist(walletFrom);
            manager.persist(walletTo);
            manager.persist(transaction);
            manager.getTransaction().commit();
        } catch (Exception ex) {
            manager.getTransaction().rollback();
            throw new RuntimeException(ex);
        }
    }

    public static void convertMoney(Serializable idUser, int summ, String typeFrom, String typeTo) {
        manager.getTransaction().begin();
        try {
            User user = manager.find(User.class, idUser);
            Wallet wallet = user.getWallet();
            WalletDao.takeMoney(wallet, summ, typeFrom);
            double convertCoff = ExchangeRateDao.getCoff(typeFrom, typeTo);
            double convertSum = summ * convertCoff;
            WalletDao.addMoney(wallet, (int) convertSum, typeTo);
            manager.persist(wallet);
            manager.getTransaction().commit();
        } catch (Exception ex) {
            manager.getTransaction().rollback();
            throw new RuntimeException(ex);
        }
    }

    public static void getSumInUah(Serializable userId) {
        User user = manager.find(User.class, userId);
        Wallet wallet = user.getWallet();
        int summUah = wallet.getSummuah();
        summUah += (wallet.getSummusd() * ExchangeRateDao.getExchangeRate("usd")) +
                (wallet.getSummeur() * ExchangeRateDao.getExchangeRate("eur"));
        System.out.println("All sum in UAH = " + summUah);
    }

}

