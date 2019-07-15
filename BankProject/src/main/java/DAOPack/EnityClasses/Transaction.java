package DAOPack.EnityClasses;

import javax.persistence.*;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToOne
    @JoinColumn(name = "fromwallet")
    private Wallet fromWallet;
    @OneToOne
    @JoinColumn(name = "towallet")
    private Wallet toWallet;
    private String typeOfCurrency;
    private int summ;

    public Transaction(Wallet fromWallet, Wallet toWallet, String typeOfCurrency, int summ) {
        this.fromWallet = fromWallet;
        this.toWallet = toWallet;
        this.typeOfCurrency = typeOfCurrency;
        this.summ = summ;
    }

    public Transaction() {
    }

    public String getTypeOfCurrency() {
        return typeOfCurrency;
    }

    public void setTypeOfCurrency(String typeOfCurrency) {
        this.typeOfCurrency = typeOfCurrency;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Wallet getFromWallet() {
        return fromWallet;
    }

    public void setFromWallet(Wallet fromWallet) {
        this.fromWallet = fromWallet;
    }

    public Wallet getToWallet() {
        return toWallet;
    }

    public void setToWallet(Wallet toWallet) {
        this.toWallet = toWallet;
    }

    public int getSumm() {
        return summ;
    }

    public void setSumm(int summUAH) {
        this.summ = summUAH;
    }
}
