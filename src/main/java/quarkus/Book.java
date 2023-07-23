package quarkus;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Book extends PanacheEntity { //Extender de PanacheEntity nos brinda un monton de metodos estaticos para interactuar con la bd
    private String title;

    private int numPages;

    private LocalDate publicationDate;

    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNumPages() {
        return numPages;
    }

    public void setNumPages(int numPages) {
        this.numPages = numPages;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book book)) return false;
        return numPages == book.numPages && Objects.equals(title, book.title) && Objects.equals(publicationDate, book.publicationDate) && Objects.equals(description, book.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, numPages, publicationDate, description);
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", numPages=" + numPages +
                ", publicationDate=" + publicationDate +
                ", description='" + description + '\'' +
                '}';
    }
}
