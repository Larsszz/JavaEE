package DAOPack.EnityClasses;

import javax.persistence.*;

@Entity
@Table(name = "wallets")

public class Wallet {

    @Id
    @Column(name = "wnumber")
    private int id;

    private int summeur;
    private int summusd;
    private int summuah;

    @OneToOne(mappedBy = "wallet")
    private User wuser;

    public Wallet(User wuser, int id) {
        this.wuser = wuser;
        this.id = id;
    }

    public Wallet() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSummeur() {
        return summeur;
    }

    public void setSummeur(int summeur) {
        this.summeur = summeur;
    }

    public int getSummusd() {
        return summusd;
    }

    public void setSummusd(int summusd) {
        this.summusd = summusd;
    }

    public int getSummuah() {
        return summuah;
    }

    public void setSummuah(int summuah) {
        this.summuah = summuah;
    }

    public User getWuser() {
        return wuser;
    }

    public void setWuser(User wuser) {
        this.wuser = wuser;
    }
}
