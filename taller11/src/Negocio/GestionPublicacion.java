package Negocio;

import excepciones.ExcepcionAcessoDatos;
import Dominio.Publicacion;
import Datos.ArchivoObjeto;
import Datos.IAccessoDatos;

import java.util.List;

public class GestionPublicacion {

    IAccessoDatos<Publicacion, String> datos;

    public GestionPublicacion() {
        this.datos = new ArchivoObjeto();
    }

    public void agregarPublicacion(Publicacion publicacion) throws ExcepcionAcessoDatos {
        if (publicacion == null) {
            throw new ExcepcionAcessoDatos("La publicacion a insertar es de tipo null");
        } else if (publicacion.getIsbn() == null || publicacion.getIsbn().isEmpty()) {
            throw new ExcepcionAcessoDatos("La publicacion a insertar no tiene ISBN");
        } else if (publicacion.getAutor() == null || publicacion.getAutor().isEmpty()) {
            throw new ExcepcionAcessoDatos("La publicacion a insertar no tiene autor");
        } else if (publicacion.getTitle() == null || publicacion.getTitle().isEmpty()) {
            throw new ExcepcionAcessoDatos("La publicacion a insertar no tiene título");
        }
        Publicacion repeated = null;
        try {
            repeated = this.buscar(publicacion.getIsbn());
        } catch (ExcepcionAcessoDatos e) {

        }
        if (repeated != null) {
            throw new ExcepcionAcessoDatos("Isbn ya existente");
        }

        datos.agregar(publicacion);
    }

    public List<Publicacion> leerPublicaciones() throws ExcepcionAcessoDatos {
        return datos.leerPublicaciones();
    }

    public Publicacion buscar(String ISBN) throws ExcepcionAcessoDatos {
        if (ISBN == null || ISBN.isEmpty()) {
            throw new ExcepcionAcessoDatos("El ISBN a buscar es inválido");
        }
        return datos.mostrarPublicacion(ISBN);
    }

    public void borrarPublication(String ISBN) throws ExcepcionAcessoDatos {
        if (ISBN == null || ISBN.isEmpty()) {
            throw new ExcepcionAcessoDatos("Isbn inválido");
        }
        datos.borrar(ISBN);
    }

    public List<Publicacion> filtrarPublicacionPorText(String texto) throws ExcepcionAcessoDatos {
        return datos.filtrar(texto);
    }
}
