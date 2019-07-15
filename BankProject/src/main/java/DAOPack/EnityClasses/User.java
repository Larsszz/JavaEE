package DAOPack.EnityClasses;

import javax.persistence.*;

@Entity
@Table(name = "users")

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String uname;
    private int age;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "wallet")
    private Wallet wallet;

    public User(String uname, int age, Wallet wallet) {
        this.uname = uname;
        this.age = age;
        this.wallet = wallet;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }
}
