package Vista;

import Dominio.AudioLibro;
import Dominio.Libro;
import Dominio.Publicacion;
import Negocio.GestionPublicacion;
import excepciones.ExcepcionAcessoDatos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.time.Year;

public class VentanaRegistro extends JDialog {

    private JLabel lIsbn, lTitulo, lAutor, lAnio, lPrecio, lEdicion, lPaginas, lPeso, lDuracion, lFormato;

    private JTextField tIsbn, tTitulo, tAutor;
    private JFormattedTextField ftPrecio, ftEdicion, ftPaginas, ftPeso, ftDuracion;
    private JComboBox cAnio, cFormato;

    private JButton bGuardar, bCancelar, bNuevo, bEliminar;
    private JPanel panelDatos, panelBotones;

    private GestionPublicacion gestor;
    private Container contenedor;
    private String tipo;

    public VentanaRegistro(JFrame frame, boolean bln, String tipo) {
        this(frame, bln, tipo, null);
    }

    public VentanaRegistro(JFrame frame, boolean bln, String tipo, Publicacion publicacion) {
        super(frame, bln);
        this.tipo = tipo;
        this.gestor = new GestionPublicacion();
        this.setTitle("Formulario Registro de Publicaciones - V1");
        this.iniciarComponentes();
        if (publicacion != null) {
            this.activarComponentes();
            this.actualizarValoresComponentes(publicacion);
        }
        this.pack();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

    public void iniciarComponentes() {
        this.contenedor = this.getContentPane();
        this.contenedor.setLayout(new BorderLayout());
        this.iniciarPanelDatos();
        this.iniciarPanelBotones();
    }

    public void iniciarPanelDatos() {
        this.panelDatos = new JPanel();
        this.panelDatos.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.panelDatos.setLayout(new GridLayout(9, 2, 5, 5));

        this.lIsbn = new JLabel("ISBN: ");
        this.lTitulo = new JLabel("Titulo: ");
        this.lAutor = new JLabel("Autor: ");
        this.lAnio = new JLabel("Año: ");
        this.lPrecio = new JLabel("Precio: ");

        this.lPaginas = new JLabel("Páginas: ");
        this.lEdicion = new JLabel("Edición: ");

        this.lPeso = new JLabel("Peso: ");
        this.lFormato = new JLabel("Formato: ");
        this.lDuracion = new JLabel("Duración: ");

        this.tIsbn = new JTextField();
        this.tIsbn.setEnabled(false);

        this.tTitulo = new JTextField(null);
        this.tTitulo.setEnabled(false);
        this.tAutor = new JTextField(null);
        this.tAutor.setEnabled(false);

        NumberFormat formato = NumberFormat.getInstance();

        this.ftPrecio = new JFormattedTextField(formato);
        this.ftPrecio.setEnabled(false);

        this.ftEdicion = new JFormattedTextField(formato);
        this.ftEdicion.setEnabled(false);

        this.ftPaginas = new JFormattedTextField(formato);
        this.ftPaginas.setEnabled(false);

        this.ftPeso = new JFormattedTextField(formato);
        this.ftPeso.setEnabled(false);

        this.ftDuracion = new JFormattedTextField(formato);
        this.ftDuracion.setEnabled(false);

        this.cAnio = new JComboBox();
        for (int i = Year.now().getValue(); i >= 0; i--) {
            this.cAnio.addItem(i);
        }
        this.cAnio.setEnabled(false);

        this.cFormato = new JComboBox();
        this.cFormato.addItem("mp3");
        this.cFormato.addItem("wav");
        this.cFormato.addItem("aiff");
        this.cFormato.addItem("ogg");
        this.cFormato.addItem("avi");
        this.cFormato.setEnabled(false);

        this.bGuardar = new JButton("Guardar");
        this.bGuardar.addActionListener(new ClickBotonGuardar());
        this.bGuardar.setEnabled(false);
        this.bCancelar = new JButton("Cancelar");
        this.bCancelar.addActionListener(new ClickBotonCancelar());
        this.bCancelar.setEnabled(false);

        this.panelDatos.add(this.lIsbn);
        this.panelDatos.add(this.tIsbn);

        this.panelDatos.add(this.lTitulo);
        this.panelDatos.add(this.tTitulo);

        this.panelDatos.add(this.lAutor);
        this.panelDatos.add(this.tAutor);

        this.panelDatos.add(this.lAnio);
        this.panelDatos.add(this.cAnio);

        this.panelDatos.add(this.lPrecio);
        this.panelDatos.add(this.ftPrecio);

        if (this.tipo.equals("1")) {
            this.panelDatos.add(this.lPaginas);
            this.panelDatos.add(this.ftPaginas);

            this.panelDatos.add(this.lEdicion);
            this.panelDatos.add(this.ftEdicion);

        } else {
            this.panelDatos.add(this.lFormato);
            this.panelDatos.add(this.cFormato);

            this.panelDatos.add(this.lPeso);
            this.panelDatos.add(this.ftPeso);

            this.panelDatos.add(this.lDuracion);
            this.panelDatos.add(this.ftDuracion);
        }

        this.panelDatos.add(this.bGuardar);
        this.panelDatos.add(this.bCancelar);

        this.contenedor.add(this.panelDatos, BorderLayout.CENTER);

    }

    public void iniciarPanelBotones() {

        this.panelBotones = new JPanel();
        this.panelBotones.setLayout(new GridLayout(3, 1, 5, 5));

        this.bNuevo = new JButton("Nuevo");
        this.bNuevo.addActionListener(new clickBotonNuevo());

        this.bEliminar = new JButton("Eliminar");
        this.bEliminar.addActionListener(new ClickBotonEliminar());
        this.bEliminar.setEnabled(false);

        this.panelBotones.add(this.bNuevo);
        this.panelBotones.add(this.bEliminar);

        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(this.panelBotones);

        this.contenedor.add(panel, BorderLayout.EAST);

    }

    public void activarComponentes() {
        for (Component component: this.panelDatos.getComponents()) {
            component.setEnabled(true);
        }
        this.bCancelar.setEnabled(true);
        this.bGuardar.setEnabled(true);
        this.bEliminar.setEnabled(true);
        this.tIsbn.grabFocus();
    }

    public void desactivarComponentes() {
        for (Component component: this.panelDatos.getComponents()) {
            component.setEnabled(false);
        }
        this.bCancelar.setEnabled(false);
        this.bGuardar.setEnabled(false);
        this.bEliminar.setEnabled(false);
    }

    public void limpiarComponentes() {
        this.tIsbn.setText(null);
        this.tTitulo.setText(null);
        this.tAutor.setText(null);
        this.cAnio.setSelectedIndex(0);
        this.cFormato.setSelectedIndex(0);
        this.ftPrecio.setValue(null);
        this.ftEdicion.setValue(null);
        this.ftPaginas.setValue(null);
        this.ftPeso.setValue(null);
        this.ftDuracion.setValue(null);
    }

    public Publicacion leerComponentes() {


        String isbn = this.tIsbn.getText();
        String titulo = this.tTitulo.getText();
        String autor = this.tAutor.getText();
        String formato = this.cFormato.getSelectedItem().toString();
        int anio = 0;
        double precio = 0;
        try {
            precio = Integer.parseInt(this.ftPrecio.getValue().toString());
            anio = Integer.parseInt(this.cAnio.getSelectedItem().toString());
        } catch (NullPointerException e) {

        }
        ;

        if (this.tipo.equals("1")) {
            int paginas = 0;
            int edicion = 0;
            try {
                paginas = Integer.parseInt(this.ftPaginas.getValue().toString());
                edicion = Integer.parseInt(this.ftEdicion.getValue().toString());
            } catch (NullPointerException e) {
            }
           return new Libro(isbn, titulo, autor, anio, precio, paginas, edicion);
        } else {
            double duracion = 0;
            double peso = 0;
            try {
                duracion = Double.parseDouble(this.ftPaginas.getValue().toString());
                peso = Double.parseDouble(this.ftEdicion.getValue().toString());
            } catch (NullPointerException e) {
            }
            return new AudioLibro(isbn, titulo, autor, anio, precio, duracion, formato, peso);
        }
    }

    public void guardarPublicacion() {
        try {
            Publicacion publicacion = this.leerComponentes();
            this.gestor.agregarPublicacion(publicacion);
            this.ventanaMsg("Datos guardados cone exito", "Confirmacion", JOptionPane.INFORMATION_MESSAGE);
            this.limpiarComponentes();
            this.tIsbn.grabFocus();
        } catch (ExcepcionAcessoDatos | NumberFormatException ex) {
            this.ventanaMsg(ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

        }
    }

    public void actualizarValoresComponentes(Publicacion publicacion) {
        this.tIsbn.setText(publicacion.getIsbn());
        this.tAutor.setText(publicacion.getAutor());
        this.tTitulo.setText(publicacion.getTitle());
        this.cAnio.setSelectedItem(publicacion.getAnio());
        this.ftPrecio.setValue(publicacion.getPrecio());

        if (publicacion instanceof Libro) {
            this.ftPaginas.setValue(((Libro) publicacion).getPaginas());
            this.ftEdicion.setValue(((Libro) publicacion).getEdicion());
        } else if (publicacion instanceof AudioLibro) {
            this.cFormato.setSelectedItem(((AudioLibro) publicacion).getFormato());
            this.ftDuracion.setValue(((AudioLibro) publicacion).getDuracion());
            this.ftPeso.setValue(((AudioLibro) publicacion).getPeso());
        }
    }

    public void eliminarPublicacion() {
        try {
            String isbn = this.tIsbn.getText();
            int confirma = JOptionPane.showConfirmDialog(this, "Desea confirmar la eliminacion del elemento", "Mensaje", JOptionPane.OK_CANCEL_OPTION);
            if(confirma==0){
                this.gestor.borrarPublication(isbn);
                this.ventanaMsg("Datos eliminador con exito", "Confirmacion", JOptionPane.INFORMATION_MESSAGE);
                this.limpiarComponentes();
            }
        } catch (ExcepcionAcessoDatos ex) {
            this.limpiarComponentes();
            this.ventanaMsg(ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void ventanaMsg(String msg, String titulo, int tipo) {
        JOptionPane.showMessageDialog(this, msg, titulo, tipo);
    }

    class clickBotonNuevo implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            activarComponentes();
        }
    }

    class ClickBotonGuardar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            guardarPublicacion();
        }

    }

    class ClickBotonCancelar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            limpiarComponentes();
            desactivarComponentes();
            tIsbn.grabFocus();
        }

    }

    class ClickBotonEliminar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            eliminarPublicacion();
        }

    }

}

