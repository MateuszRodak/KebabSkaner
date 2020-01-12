package pl.mr.kebab.model.enums;

import java.util.NoSuchElementException;

public enum JednostkaPorcja {
    G("g"),
    DKG("dkg"),
    KG("kg"),
    SZT("szt."),
    L("l");

    private String value;

    JednostkaPorcja(String s) {
        value = s;
    }

    public String getValue() {
        return value;
    }

    public static JednostkaPorcja getEnum(String s) {
        for (JednostkaPorcja jednostkaPorcja : JednostkaPorcja.values()) {
            if (jednostkaPorcja.value.equals(s)) {
                return jednostkaPorcja;
            }
        }
        throw new NoSuchElementException("Brak takiej wartości! - " + s);
    }
}
