package Datos;

import excepciones.ExcepcionAcessoDatos;
import java.util.List;

public interface IAccessoDatos<T, K> {
    void agregar(T publicacion) throws ExcepcionAcessoDatos;

    List<T> leerPublicaciones() throws ExcepcionAcessoDatos;

    T mostrarPublicacion(K ISBN) throws ExcepcionAcessoDatos;

    void borrar(K ISBN) throws ExcepcionAcessoDatos;

    List<T> filtrar(K texto) throws ExcepcionAcessoDatos;
}
