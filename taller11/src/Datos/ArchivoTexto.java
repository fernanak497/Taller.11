package Datos;

import excepciones.ExcepcionAcessoDatos;
import Dominio.Publicacion;

import java.util.List;

public class ArchivoTexto implements IAccessoDatos<Publicacion, String> {

    @Override
    public void agregar(Publicacion publicacion) throws ExcepcionAcessoDatos {

    }

    @Override
    public List<Publicacion> leerPublicaciones() throws ExcepcionAcessoDatos {
        return null;
    }

    @Override
    public Publicacion mostrarPublicacion(String ISBN) throws ExcepcionAcessoDatos {
        return null;
    }

    @Override
    public void borrar(String ISBN) throws ExcepcionAcessoDatos {

    }

    @Override
    public List<Publicacion> filtrar(String texto) {
        return null;
    }
}
