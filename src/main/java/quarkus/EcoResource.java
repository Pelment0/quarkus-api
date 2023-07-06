package quarkus;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/saludar")
public class EcoResource {
    @GET
    public String saludar(){
        return "Hola muy buenas";
    }
}
