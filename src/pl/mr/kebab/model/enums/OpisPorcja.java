package pl.mr.kebab.model.enums;

import java.util.NoSuchElementException;

public enum OpisPorcja {
    MALY("mały"),
    SREDNI("średni"),
    DUZY("duży"),
    B_DUZY("b.duży"),
    WIELKI("wielki");

    private String value;

    OpisPorcja(String s) {
        value = s;
    }

    public String getValue() {
        return value;
    }

    public static OpisPorcja getEnum(String s) {
        for (OpisPorcja opisPorcja : OpisPorcja.values()) {
            if (opisPorcja.value.equals(s)) {
                return opisPorcja;
            }
        }
        throw new NoSuchElementException("Brak takiej wartości! - " + s);
    }
}
