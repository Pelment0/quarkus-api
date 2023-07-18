package quarkus;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Path("/temperaturas")
public class TemperaturasResource {

    private TemperaturasService temperaturas;


    @Inject //Inyeccion de dependencias: Esto hace que Quarkus instancie automaticamente un TemperaturasService
    public TemperaturasResource(TemperaturasService temperaturas){
        this.temperaturas = temperaturas;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Temperatura nueva(Temperatura temp){
        temperaturas.addTemperatura(temp);
        return temp;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Temperatura> list(){
        return temperaturas.obtenerTemperaturas();
    }

    @GET
    @Path("/maxima")
    @Produces(MediaType.TEXT_PLAIN)
    public String maxima(){
        return Integer.toString(temperaturas.maxima());
    }

    /*@GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/una")
    public Temperatura medicion(){
        return new Temperatura("Malaga", 18, 28);
    }*/
}
