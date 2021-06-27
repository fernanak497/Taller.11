package Dominio;

import java.io.Serializable;

public class AudioLibro extends Publicacion implements Serializable {

    private double duracion;
    private String formato;
    private double peso;

    public AudioLibro(String isbn) {
        super(isbn);
    }

    public AudioLibro(String isbn, String titulo, String autor, int anio, double precio, double duracion, String formato, double peso) {
        super(isbn, titulo, autor, anio, precio);
        this.duracion = duracion;
        this.formato = formato;
        this.peso = peso;
    }

    public AudioLibro() {
    }

    public double getDuracion() {
        return duracion;
    }

    public void setDuracion(double duracion) {
        this.duracion = duracion;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    @Override
    public String getInfo() {
        return ", " + duracion + ", " + formato + ", " + peso;
    }
}
