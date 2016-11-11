package eas.com.entity;

/**
 * Created by eduardo on 11/6/2016.
 */
public class Book {
    private long id;
    private String title;
    private Author author;
    private String publishing;
    private String edition;
    private String editor;
    private int pages;
    private String code;


    /**
     * calc the code of the book
     */
    private void bookCode() {
        this.code = new StringBuilder(this.author.getFirstSurname().substring(0, 3).toUpperCase())
                .append(",")
                .append(this.title.charAt(0)).toString();
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author=" + author +
                ", publishing='" + publishing + '\'' +
                ", edition='" + edition + '\'' +
                ", editor='" + editor + '\'' +
                ", pages=" + pages +
                ", code='" + code + '\'' +
                '}';
    }


    /*Setter And Getter*/

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getPublishing() {
        return publishing;
    }

    public void setPublishing(String publishing) {
        this.publishing = publishing;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
