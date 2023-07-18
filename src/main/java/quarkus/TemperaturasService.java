package quarkus;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@ApplicationScoped //Necesario para la inyeccion de dependecias. Le estamos diciendo a Quarkus que queremos que nos instancie esta clase
                   //Se relaciona con el @Inject del constructor de TemperaturasResource
public class TemperaturasService {
    private List<Temperatura> valores = new ArrayList<>();

    public void addTemperatura(Temperatura t){
        valores.add(t);
    }

    public List<Temperatura> obtenerTemperaturas() {
        return Collections.unmodifiableList(valores);
    }

    public int maxima(){
        return valores.stream().mapToInt(Temperatura::getMaxima).max().getAsInt();
    }
}
