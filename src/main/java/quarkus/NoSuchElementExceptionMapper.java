package quarkus;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.util.NoSuchElementException;

@Provider
public class NoSuchElementExceptionMapper implements ExceptionMapper<NoSuchElementException> {

    //"record" se utiliza para crear clases cuya funcion principal es almacenar datos y no tiene lógica adicional
    // mas allá de los metodos de acceso (getters) y algunos metodos de utilidad como equals(), hashCode() o toString()
    public record NoSuchElementMessage(String message, String detail){

    }

    //"var" hace que el compilador pueda deducir el tipo de la variable de manera implicita en funcion del valor
    //asignado a la variable. Solo puede ser utilizada para declarar variables locales dentro de un metodo o
    //bloque de codigo. No puede ser utilizada como variables atributos de clase o parametros de metodos
    @Override
    public Response toResponse(NoSuchElementException e) {
        var error = new NoSuchElementMessage(e.getMessage(), null);
        return Response.status(404).entity(error).build();
    }
}
