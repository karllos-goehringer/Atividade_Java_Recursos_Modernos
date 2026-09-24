package num5;

import java.util.List;
import java.util.Optional;

public class BuscarPorID {
    public Optional<String> buscarPorIdc(List<String> lista, int indice){
        return lista.get(indice).describeConstable();
    }
}
