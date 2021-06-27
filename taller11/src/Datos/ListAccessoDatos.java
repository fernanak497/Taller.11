package Datos;

import excepciones.ExcepcionAcessoDatos;
import Dominio.Publicacion;

import java.util.*;

public class ListAccessoDatos implements IAccessoDatos<Publicacion, String> {

    private final List<Publicacion> publicaciones = new ArrayList<>();

    @Override
    public void agregar(Publicacion publicacion) throws ExcepcionAcessoDatos {
        if (publicacion == null) {
            throw new ExcepcionAcessoDatos("El objeto a insertar es de tipo null");
        }
        if (publicacion.getIsbn() == null || publicacion.getIsbn().isEmpty()) {
            throw new ExcepcionAcessoDatos("El objeto a insertar no tiene ISBN");
        }

        publicaciones.add(publicacion);
    }

    @Override
    public List<Publicacion> leerPublicaciones() throws ExcepcionAcessoDatos {
        if (publicaciones.size() == 0) {
            throw new ExcepcionAcessoDatos("No hay publicaciones registradas");
        }

        return publicaciones;
    }

    @Override
    public Publicacion mostrarPublicacion(String ISBN) throws ExcepcionAcessoDatos {
         if (ISBN == null || ISBN.isEmpty()) {
            throw new ExcepcionAcessoDatos("El ISBN a buscar es inválido");
        }

         Publicacion publicacion = null;
        for (Publicacion p: publicaciones) {
            if (p.getIsbn().equals(ISBN)) {
                publicacion = p;
            }
        }

        if (publicacion == null) {
            throw new ExcepcionAcessoDatos("No existe publicacion");
        }

        return publicacion;
    }

    @Override
    public void borrar(String ISBN) throws ExcepcionAcessoDatos {
        Publicacion publicacion = mostrarPublicacion(ISBN);
        publicaciones.remove(publicacion);
    }

    @Override
    public List<Publicacion> filtrar(String texto) {
        return null;
    }
}
