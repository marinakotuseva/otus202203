package ru.atm.api;

import ru.atm.Atm;
import ru.atm.Banknote;

/**
 * @author Marina Kotuseva
 */
public interface AtmService {

    int getAtmBalance(Atm atm);

    /**
     * Добавить в банкомат банкноту
     * @param atm куда добавить
     * @param banknote что добавить
     */
    void addBanknote(Atm atm, Banknote banknote);

    /**
     * Снять наличные.
     * @param atm откуда снять
     * @param amount сколько снять
     * @return успешно ли снято нужное кол-во
     */
    boolean getCash(Atm atm, Integer amount);
}
