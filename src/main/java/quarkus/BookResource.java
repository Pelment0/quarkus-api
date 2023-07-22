package quarkus;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/books")
@Transactional //se pone en aquellos endpoints que vayan a modificar la base de datos (tema Transacciones BBDD)
public class BookResource {

    @Inject
    private BookRepository repo;

    @GET
    public List<Book> index(){
        return repo.listAll(); //select * from Book
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Book insert(Book insertedBook){
        assert insertedBook.getId() == null; //compruebo que el id es nulo antes de crearlo
        repo.persist(insertedBook); //creo un nuevo libro en la tabla Book
        assert insertedBook.getId() != null; //Compruebo que .persist() le ha asignado correctamente un id
        return insertedBook;
    }

    @DELETE
    public void delete(Book book){
        repo.delete(book);
    }
}
