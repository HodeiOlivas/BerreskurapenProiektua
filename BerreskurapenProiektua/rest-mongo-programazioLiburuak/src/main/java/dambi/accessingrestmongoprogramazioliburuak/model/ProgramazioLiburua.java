package dambi.accessingrestmongoprogramazioliburuak.model;

import java.util.Date;
import java.util.List;

public class ProgramazioLiburua {

    private String title;
    private String isbn;
    private int pageCount;
    private Date publishedDate;
    private String thumbnailUrl;
    private String shortDescription;
    private String status;
    private List<String> authors;
    private List<String> categories;
    private List<Puntuazioa> puntuazioa;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public Date getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;

    }

    public List<Puntuazioa> getPuntuazioa() {
        return puntuazioa;
    }

    public void setPuntuazioa(List<Puntuazioa> puntuazioa) {
        this.puntuazioa = puntuazioa;
    }

    @Override
    public String toString() {
        return "ProgramazioLiburua [title=" + title + ", isbn=" + isbn + ", pageCount=" + pageCount + ", publishedDate="
                + publishedDate + ", thumbnailUrl=" + thumbnailUrl + ", shortDescription=" + shortDescription
                + ", status=" + status + ", authors=" + authors + ", categories=" + categories + ", puntuazioa="
                + puntuazioa + "]";
    }

}
