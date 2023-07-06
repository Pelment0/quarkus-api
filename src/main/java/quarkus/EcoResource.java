package quarkus;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;

import java.util.Optional;

@Path("/saludar")
public class EcoResource {
    @GET
    public String saludar(@QueryParam("mensaje") String mensaje){
        return Optional.ofNullable(mensaje).map(n -> "> " + n).orElse("No se muy bien que decirte");
    }

    @GET
    @Path("/{nombre}") //Los parametros entre llaves son parametros de la URL, pero no de query (query -> {URL}?param=valor)
    public String saludo(@PathParam("nombre") String nombre){
        return "Hola " + nombre;
    }

    @GET
    @Path("/{nombre}/mayusculas")
    public String gritar(@PathParam("nombre") String nombre){
        return "HOLA " + nombre.toUpperCase();
    }
}
