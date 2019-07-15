package DAOPack.AccessClasses;

import DAOPack.EnityClasses.Wallet;

public class WalletDao extends BaseDao {
    public WalletDao() {
    }

    public static void addMoney(Wallet wallet, int summ, String currency) {
        switch (currency.toUpperCase()) {
            case "UAH":
                wallet.setSummuah(wallet.getSummuah() + summ);
                break;
            case "EUR":
                wallet.setSummeur(wallet.getSummeur() + summ);
                break;
            case "USD":
                wallet.setSummusd(wallet.getSummusd() + summ);
                break;
            default:
                throw new IllegalArgumentException("Illegal currency");
        }
    }

    public static void takeMoney(Wallet wallet, int summ, String currency) {
        switch (currency.toUpperCase()) {
            case "UAH":
                wallet.setSummuah(wallet.getSummuah() - summ);
                if (wallet.getSummuah()<0) {
                    throw new IllegalArgumentException("Not enough UAH");
                }
                break;
            case "EUR":
                wallet.setSummeur(wallet.getSummeur() - summ);
                if (wallet.getSummeur()<0) {
                    throw new IllegalArgumentException("Not enough EUR");
                }
                break;
            case "USD":
                wallet.setSummusd(wallet.getSummusd() - summ);
                if (wallet.getSummusd()<0) {
                    throw new IllegalArgumentException("Not enough USD");
                }
                break;
            default:
                throw new IllegalArgumentException("Illegal currency");
        }
    }

}

