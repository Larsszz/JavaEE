package DAOPack.AccessClasses;

import DAOPack.EnityClasses.ExchangeRate;
import utils.ConnectionsUtils;

import javax.persistence.EntityManager;

public class ExchangeRateDao extends BaseDao {

    public static void changeRate(String currency, int value) {
        EntityManager manager = ConnectionsUtils.factory.createEntityManager();
        manager.getTransaction().begin();
        try {
            ExchangeRate exchangeRate = manager.find(ExchangeRate.class,currency.toUpperCase());
            exchangeRate.setCourse(value);
            manager.persist(exchangeRate);
            manager.getTransaction().commit();
        } catch (Exception ex) {
            manager.getTransaction().rollback();
            throw new RuntimeException(ex);
        }
    }

    public static int getExchangeRate (String currency) {
        if (currency.toUpperCase().equals("UAH")) return 1;
       EntityManager manager = ConnectionsUtils.factory.createEntityManager();
       try {
           ExchangeRate exchangeRate = manager.find(ExchangeRate.class,currency.toUpperCase());
           return exchangeRate.getCourse();
       } catch (Exception ex) {
           throw new RuntimeException(ex);
       }
    }

    public static double getCoff (String fromCurrency, String toCurrency) {
        double res1 = getExchangeRate(fromCurrency);
        double res2 = getExchangeRate(toCurrency);
        return res1/res2;
    }
}
