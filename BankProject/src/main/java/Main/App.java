package Main;

import DAOPack.AccessClasses.UserDao;
import utils.ConnectionsUtils;

public class App
{
    public static void main( String[] args )
    {
        ConnectionsUtils.initBankDataBase();
        UserDao.addCash(1,1000,"uah");
        UserDao.sendMoney(1,2,100,"uah");
        UserDao.convertMoney(1,300,"uah","usd");
        UserDao.addCash(1,10,"eur");
        UserDao.getSumInUah(1);
    }
}
