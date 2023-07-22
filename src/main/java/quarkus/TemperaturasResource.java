package quarkus;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.NoSuchElementException;

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
    public Response maxima(){ //Response es para mandar respuestas personalizadas a peticiones http en caso de haya algun error
       if(temperaturas.isEmpty()){
           return Response.status(404).entity("No hay temperaturas en la lista").build();
       } else {
           int temperauraMaxima = temperaturas.maxima();
           return Response.ok(Integer.toString(temperauraMaxima)).build();
       }
    }

    @GET
    @Path("{ciudad}")
    @Produces(MediaType.APPLICATION_JSON)
    public Temperatura sacar(@PathParam("ciudad") String ciudad){
        return temperaturas.sacarTemperatura(ciudad)
                .orElseThrow(() -> new NoSuchElementException("No hay registro para la ciudad de " + ciudad));
    }
}
