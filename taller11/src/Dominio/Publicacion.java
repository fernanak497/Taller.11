package Dominio;

import java.io.Serializable;

public abstract class Publicacion implements Serializable {

    private String isbn;
    private String title;
    private String autor;
    private int anio;
    private double precio;

    public Publicacion(String isbn) {
        this.isbn = isbn;
    }

    public Publicacion(String isbn, String title, String autor, int anio, double precio) {
        this.isbn = isbn;
        this.title = title;
        this.autor = autor;
        this.anio = anio;
        this.precio = precio;
    }

    public Publicacion() {
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public abstract String getInfo();

    @Override
    public String toString() {
        return (
                isbn +
                        ", " +
                        title +
                        ", " +
                        autor +
                        ", " +
                        anio +
                        ", " +
                        precio +
                        ", " +
                        getInfo()
        );
    }
}
