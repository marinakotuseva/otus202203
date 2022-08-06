package ru.atm;


import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.assertj.core.api.Java6Assertions.assertThatThrownBy;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import ru.atm.api.AtmService;

/**
 * @author Marina Kotuseva
 */
class AtmServiceImplTest {
    private final AtmService atmService = new AtmServiceImpl();


    @Test
    void testBalance() {
        Atm atm = new Atm(new HashMap<>());
        assertThat(atmService.getAtmBalance(atm)).isZero();

        Atm secondAtm = new Atm(Map.of(Banknote.BANKNOTE100, 1, Banknote.BANKNOTE10, 2));
        assertThat(atmService.getAtmBalance(secondAtm)).isEqualTo(120);

    }

    @Test
    void addBanknote() {
        Atm atm = new Atm(new HashMap<>());
        assertThat(atmService.getAtmBalance(atm)).isZero();

        atmService.addBanknote(atm, Banknote.BANKNOTE50);
        assertThat(atmService.getAtmBalance(atm)).isEqualTo(50);
    }

    @Test
    void getCashSuccessNoAvail() {

        HashMap<Banknote, Integer> money = new HashMap<>();
        money.put(Banknote.BANKNOTE100, 1);
        money.put(Banknote.BANKNOTE10, 2);

        Atm atm = new Atm(money);

        atmService.getCash(atm, 50);
    }

    @Test
    void getCashSuccessSuccess() {

        HashMap<Banknote, Integer> money = new HashMap<>();
        money.put(Banknote.BANKNOTE100, 1);
        money.put(Banknote.BANKNOTE10, 2);

        Atm atm = new Atm(money);

        atmService.getCash(atm, 20);

        assertThat(atmService.getAtmBalance(atm)).isEqualTo(100);
    }

    @Test
    void getCashFail() {

        HashMap<Banknote, Integer> money = new HashMap<>();
        money.put(Banknote.BANKNOTE100, 1);
        money.put(Banknote.BANKNOTE10, 2);

        Atm atm = new Atm(money);
        assertThat(atmService.getAtmBalance(atm)).isEqualTo(120);

        assertThatThrownBy(() -> atmService.getCash(atm, 320))
                .isInstanceOf(UnsupportedOperationException.class);

        assertThat(atmService.getAtmBalance(atm)).isEqualTo(120);
    }
}
