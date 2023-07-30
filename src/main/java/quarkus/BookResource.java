package quarkus;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;
import java.util.NoSuchElementException;

@Path("/books")
@Transactional //se pone en aquellos endpoints que vayan a modificar la base de datos (tema Transacciones BBDD) o arriba de la clase para que aplique a toda ella
public class BookResource {

    @Inject
    BookRepository repository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Book> index(){
        return repository.listAll(); //select * from Book
    }

    @POST
    public Book insert(Book insertedBook){
        repository.persist(insertedBook); //creo un nuevo libro en la tabla Book
        return insertedBook;
    }

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") Long id){
        repository.deleteById(id);
    }

    @GET
    @Path("{id}")
    public Book getBook(@PathParam("id") Long id){
        var book = repository.findById(id);
        if(book != null){
            return book;
        }
        throw new NoSuchElementException("No existe el libro con el ID " + id + ".");
    }

    @PUT
    @Path("{id}")
    public Book update(@PathParam("id") Long id, Book book){
        var updatedBook = repository.findById(id);
        if(updatedBook != null){
            updatedBook.setTitle(book.getTitle());
            updatedBook.setPublicationDate(book.getPublicationDate());
            updatedBook.setNumPages(book.getNumPages());
            updatedBook.setDescription(book.getDescription());
            repository.persist(updatedBook); //Aquí persist no crea un nuevo libro sino que detecta que updatedBook
                                             //es un libro que ya existe en el repositorio y simplemente lo modifica
            return updatedBook;
        }
        throw new NoSuchElementException("No hay libro con el id " + id + ".");
    }
}
