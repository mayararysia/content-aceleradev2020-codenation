package challenge;

import java.util.List;

public class Validador {

    public static void validarParametro(Object object) {
        if (object == null || object.equals("")) throw new NullPointerException();
        if (object instanceof Number)
            if (((Number) object).intValue() < 0) throw new IllegalArgumentException();
    }

    public static boolean verificarValidadeLista(List<?> lista){
        return (lista != null || lista.size() > 0);
    }

    public static boolean verificarListaInvalida(List<?> lista){
        return lista == null;
    }
}
