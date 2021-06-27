package Dominio;

import java.io.Serializable;

public class Libro extends Publicacion implements Serializable {

    private int paginas;
    private int edicion;

    public Libro(String ISBN) {
        super(ISBN);
    }

    public Libro(String isbn, String titulo, String autor, int anio, double precio, int paginas, int edicion) {
        super(isbn, titulo, autor, anio, precio);
        this.paginas = paginas;
        this.edicion = edicion;
    }

    public Libro() {
    }

    public int getPaginas() {
        return paginas;
    }

    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }

    public int getEdicion() {
        return edicion;
    }

    public void setEdicion(int edicion) {
        this.edicion = edicion;
    }

    @Override
    public String getInfo() {
        return ", " + paginas + ", " + edicion;
    }
}
