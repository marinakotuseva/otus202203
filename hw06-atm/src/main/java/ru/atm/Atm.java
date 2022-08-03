package ru.atm;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import lombok.Data;
import lombok.Getter;

/**
 * @author Marina Kotuseva
 */
@Getter
public class Atm {
    private Map<Banknote, Integer> atmBanknotes;

    public Atm(Map<Banknote, Integer> atmBanknotes){
        this.atmBanknotes = atmBanknotes;
    }
}
