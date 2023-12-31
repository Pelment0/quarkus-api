package quarkus;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ApplicationScoped //Necesario para la inyeccion de dependecias. Le estamos diciendo a Quarkus que queremos que nos instancie esta clase
                   //Se relaciona con el @Inject del constructor de TemperaturasResource
public class TemperaturasService implements ITemperaturasService{
    private List<Temperatura> valores = new ArrayList<>();

    @Override
    public void addTemperatura(Temperatura t){
        valores.add(t);
    }

    @Override
    public List<Temperatura> obtenerTemperaturas() {
        return Collections.unmodifiableList(valores);
    }

    @Override
    public int maxima(){
        return valores.stream().mapToInt(Temperatura::getMaxima).max().getAsInt();
    }

    @Override
    public boolean isEmpty() {
        return valores.isEmpty();
    }

    @Override
    public Optional<Temperatura> sacarTemperatura(String ciudad) { //.stream() nos permite hacer un filtrado (entre otras cosas) de los elementos de la lista
        return valores.stream().filter(t -> t.getCiudad().equals(ciudad)).findAny();
        //Esto me devuelve la primera temperatura que se encuentre (findAny())
        //cuya ciudad sea la proporcionada por parametro
    }

}
