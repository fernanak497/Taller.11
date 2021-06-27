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

public class Vista extends JFrame implements ActionListener {
    private JMenuBar barraMenu;
    private JMenu menu;
    private JMenuItem menuRegistro, mConsulta, mBuscar;
    private JPanel panelImg;
    private JLabel lImg;

    public Vista() {
        this("Registro de Publicaciones - V 1.0");
    }

    public Vista(String string) {
        super(string);
    }

    public void iniciar() {
        this.iniciarComponentes();
    }

    public void iniciarComponentes(){
        this.iniciarBarraMenu();
        this.crearPanelImg();
        this.setLocationRelativeTo(null);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

    public void iniciarBarraMenu(){

        this.barraMenu = new JMenuBar();

        this.menu = new JMenu("Operaciones");

        this.menuRegistro = new JMenuItem("Registro");
        this.menuRegistro.addActionListener(this);

        this.mConsulta = new JMenuItem("Consulta");
        this.mConsulta.addActionListener(this);

        this.mBuscar = new JMenuItem("Buscar");
        this.mBuscar.addActionListener(this);

        this.menu.add(this.menuRegistro);
        this.menu.add(this.mConsulta);
        this.menu.add(this.mBuscar);

        this.barraMenu.add(this.menu);

        this.setJMenuBar(this.barraMenu);
    }

    public void crearPanelImg(){
        this.panelImg = new JPanel();
        this.panelImg.setLayout(new FlowLayout(FlowLayout.RIGHT));
        this.lImg = new JLabel();
        this.lImg.setIcon(new ImageIcon("src/Imagenes/logoUpc.jfif"));
        this.panelImg.add(this.lImg);

        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(this.panelImg, BorderLayout.SOUTH);
    }

    public void buscar() {
        GestionPublicacion gestor = new GestionPublicacion();
        String isbn = JOptionPane.showInputDialog(this, "Ingrese el isbn de la publicacion.");

        if (isbn != null) {
            try {
                Publicacion publicacion = gestor.buscar(isbn);
                String tipo = publicacion instanceof Libro ? "1" : "2";
                VentanaRegistro registro = new VentanaRegistro(this, true, tipo, publicacion);
            } catch (ExcepcionAcessoDatos excepcionAcessoDatos) {
                JOptionPane.showMessageDialog(this, "Publicación no encontrada", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()==this.menuRegistro){
            String tipo;
            do {
                tipo = JOptionPane.showInputDialog(this, "¿Qué tipo de publicación quiere registrar?\n\n1. Libro - 2. Audio libro.");
                if (tipo == null) {
                    break;
                }

                if (!tipo.equals("1") && !tipo.equals("2")) {
                    JOptionPane.showMessageDialog(this, "Opción inválida", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } while (!tipo.equals("1") && !tipo.equals("2"));

            if (tipo != null) {
                VentanaRegistro registro = new VentanaRegistro(this, true, tipo);
            }
        }

        if (ae.getSource()==this.mBuscar) {
            this.buscar();
        }

        if(ae.getSource()==this.mConsulta){
            VentanaConsulta consulta = new VentanaConsulta(this, true);
        }

    }
}