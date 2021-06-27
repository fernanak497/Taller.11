package Datos;

import excepciones.ExcepcionAcessoDatos;
import Dominio.Publicacion;

import java.io.*;
import java.util.*;

public class ArchivoObjeto implements IAccessoDatos<Publicacion, String> {

    private File archivo;
    private FileInputStream aLectura;
    private FileOutputStream aEscritura;

    private static class Publicaciones implements Serializable {
        List<Publicacion> lista = new ArrayList<>();
    }

    public ArchivoObjeto(String name) {
        this.archivo = new File(name);
    }

    public ArchivoObjeto() {
        this("publicaciones.obj");
    }

    private Publicaciones leer() throws ExcepcionAcessoDatos {
        Publicaciones publicaciones;
        ObjectInputStream ois = null;

        if (this.archivo.exists()) {
            try {
                this.aLectura = new FileInputStream(this.archivo);
                ois = new ObjectInputStream(this.aLectura);
                publicaciones = (Publicaciones) ois.readObject();
                return publicaciones;
            } catch (IOException ioe) {
                throw new ExcepcionAcessoDatos("No se pudo leer el archivo.");
            } catch (ClassNotFoundException ex) {
                throw new ExcepcionAcessoDatos("La clase Publicaciones No existe");
            } finally {
                if (ois != null) {
                    try {
                        ois.close();
                    } catch (IOException ioException) {
                    }
                }
                if (this.aLectura != null) {
                    try {
                        this.aLectura.close();
                    } catch (IOException ioException) {
                    }
                }
            }
        }
        else{
            publicaciones = new Publicaciones();
            return publicaciones;
        }

    }

    private void guardar(Publicaciones publicaciones) throws ExcepcionAcessoDatos {
        ObjectOutputStream oos = null;
        try {
            this.aEscritura = new FileOutputStream(this.archivo, false);
            oos = new ObjectOutputStream(this.aEscritura);
            oos.writeObject(publicaciones);
        } catch (IOException ioe) {
            throw new ExcepcionAcessoDatos("No se pudo escribir en el archivo.");
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                }
            }
            if (this.aEscritura != null) {
                try {
                    this.aEscritura.close();
                } catch (IOException ioe) {
                }
            }
        }
    }

    @Override
    public void agregar(Publicacion publicacion) throws ExcepcionAcessoDatos {
        Publicaciones publicaciones = this.leer();
        publicaciones.lista.add(publicacion);
        this.guardar(publicaciones);
    }

    @Override
    public List<Publicacion> leerPublicaciones() throws ExcepcionAcessoDatos {
        List<Publicacion> publicaciones = this.leer().lista;
        if (publicaciones.isEmpty()) {
            throw new ExcepcionAcessoDatos("No hay publicaciones registradas");
        }

        return publicaciones;
    }

    private Publicacion getPublicacion(List<Publicacion> publicaciones, String ISBN) {
        for (Publicacion p: publicaciones) {
            if (p.getIsbn().equals(ISBN)) {
                return p;
            }
        }

        return null;
    }

    @Override
    public Publicacion mostrarPublicacion(String ISBN) throws ExcepcionAcessoDatos {
        Publicacion publicacion = getPublicacion(this.leerPublicaciones(), ISBN);
        if (publicacion == null) {
            throw new ExcepcionAcessoDatos("No existe publicacion");
        }

        return publicacion;
    }

    @Override
    public void borrar(String ISBN) throws ExcepcionAcessoDatos {
        Publicaciones publicaciones = this.leer();
        Publicacion publicacion = getPublicacion(publicaciones.lista, ISBN);
        if (publicaciones.lista.remove(publicacion)) {
            this.archivo.delete();
            this.guardar(publicaciones);
        } else {
            throw new ExcepcionAcessoDatos("No se pudo borrar la publicación");
        }
    }

    @Override
    public List<Publicacion> filtrar(String texto)  throws ExcepcionAcessoDatos{
        List<Publicacion> publicaciones = this.leer().lista;
        List<Publicacion> filtradas = new ArrayList<>();
        for (Publicacion p: publicaciones) {
            if (p.getIsbn().contains(texto) || p.getAutor().contains(texto) || p.getTitle().contains(texto)) {

                filtradas.add(p);
            }
        }
        return filtradas;
    }
}
