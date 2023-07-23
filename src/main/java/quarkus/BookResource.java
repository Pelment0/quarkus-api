package quarkus;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/books")
@Transactional //se pone en aquellos endpoints que vayan a modificar la base de datos (tema Transacciones BBDD)
public class BookResource {

    @GET
    public List<Book> index(){
        return Book.listAll(); //select * from Book
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Book insert(Book insertedBook){
        insertedBook.persist();
        return insertedBook;
    }

    @DELETE
    public void delete(Book book){
        book.delete();
    }
}
