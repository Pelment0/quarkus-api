package quarkus;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

/** Podemos dejar la clase vacía porque todos los metodos que necesitamos vienen implementados en la interfaz PanacheRepository
* La utilidad de la clase está en que nosotros podemos implementar métodos de query concretos que necesitemos ya que
* la interfaz solo nos proporciona unos métodos por defecto
 */

@ApplicationScoped //Para la clase sea inyectable y la podamos meter dentro de nuestro recurso
public class BookRepository implements PanacheRepository<Book> {
}