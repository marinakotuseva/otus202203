package ru.atm;

/**
 * @author Marina Kotuseva
 */
public enum Banknote {
    BANKNOTE10(10),
    BANKNOTE50(50),
    BANKNOTE100(100);

    private final Integer nom;

    Banknote(Integer i) {
        this.nom = i;
    }

    public Integer getNom() {
        return nom;
    }
}
