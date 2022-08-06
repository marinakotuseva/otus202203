package ru.atm;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

import ru.atm.api.AtmService;

/**
 * @author Marina Kotuseva
 */
public class AtmServiceImpl implements AtmService {


    @Override
    public int getAtmBalance(Atm atm) {

        return atm.getAtmBanknotes().entrySet().stream().reduce(0,
                (prevEntry, entry) -> prevEntry + (entry.getKey().getNom() * entry.getValue()),
                Integer::sum);
    }

    @Override
    public void addBanknote(Atm atm, Banknote banknote) {

        Map<Banknote, Integer> atmBanknotes = atm.getAtmBanknotes();
        atmBanknotes.put(banknote, Optional.ofNullable(atmBanknotes.get(banknote)).orElse(0) + 1);

        System.out.println("Taken 1 banknote by " + banknote.getNom());
    }

    @Override
    public boolean getCash(Atm atm, Integer amount) {
        // In reverse order
        Map<Banknote, Integer> sortedAtmBanknotes = new TreeMap<>(Collections.reverseOrder());
        sortedAtmBanknotes.putAll(atm.getAtmBanknotes());

        System.out.println("Want to withdraw " + amount);
        if (getAtmBalance(atm) < amount){
            throw new UnsupportedOperationException("Not enough cash in the ATM!");
        }

        for (var entry: sortedAtmBanknotes.entrySet()) {
            var currentBn = entry.getKey();
            var currentNom = currentBn.getNom();
            var haveBanknotes = entry.getValue();
            var needBanknotes = amount / currentNom;
            if (haveBanknotes < needBanknotes) {
                System.out.println("No available banknotes");
            } else {
                atm.getAtmBanknotes().put(currentBn, atm.getAtmBanknotes().get(currentBn)-needBanknotes);
                amount -= needBanknotes*currentNom;
                System.out.println("Withdrawn " + needBanknotes + " banknote by " + currentNom);
            }

        }
        return true;
    }
}
