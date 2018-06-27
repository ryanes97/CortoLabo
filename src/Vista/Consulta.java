/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Dao.Moviedao;
import Modelo.Movie;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author LN710Q
 */
public class Consulta extends JFrame{
    public JLabel lblnombre, lbldirector, lblpais, lblcalificacion,lblanio,lblproyeccion;
    
    public JTextField nombre, director, pais, anio;
    public JComboBox clasificacion;
    
    ButtonGroup proyeccion = new ButtonGroup();
    public JRadioButton no;
    public JRadioButton si;
    public JTable resultados;
    
    public JPanel table;
    
    public JButton insertar, eliminar, actualizar, buscar;
    
    private static final int ANCHOC = 130, ALTOC = 40;
    
    DefaultTableModel tm;
    
    public Consulta(){
        super("Inventario");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        agregarLabels();
        formulario();
        llenarTabla();
        Container container = getContentPane();
        container.add(lblnombre);
        container.add(lbldirector);
        container.add(lblpais);
        container.add(lblcalificacion);
        container.add(lblanio);
        container.add(lblproyeccion);
        container.add(nombre);
        container.add(director);
        container.add(pais);
        container.add(anio);
        container.add(si);
        container.add(no);
        container.add(buscar);
        container.add(insertar);
        container.add(actualizar);
        container.add(eliminar);
        container.add(table);
        setSize(1000,500);
        eventos();
    }
    
    public final void agregarLabels(){
        lblnombre = new JLabel("Nombre");
        lbldirector= new JLabel("Director");
        lblpais = new JLabel("Pais");
        lblcalificacion = new JLabel("Calificacion");
        lblanio = new JLabel("Año");
        lblproyeccion = new JLabel("En proyeccion");
        lblnombre.setBounds(200, 50, 50, 30);
        lbldirector.setBounds(200, 90, 60, 30);
        lblpais.setBounds(220, 130, 50, 30);
        lblcalificacion.setBounds(540, 50, 80, 30);
        lblanio.setBounds(590, 90, 50, 30);
        lblproyeccion.setBounds(532, 130, 90, 30);
    }
    
    public final void formulario(){
        nombre = new JTextField();
        director = new JTextField();
        pais = new JTextField();
        clasificacion = new JComboBox();
        anio = new JTextField();
        
        si = new JRadioButton("si",true);
        no = new JRadioButton("no");
        resultados = new JTable();
        buscar = new JButton("Buscar");
        insertar = new JButton("Insertar");
        eliminar = new JButton("Eliminar");
        actualizar = new JButton("Actualizar");
        
        table = new JPanel();
       
        clasificacion.addItem("G");
        clasificacion.addItem("PG");
        clasificacion.addItem("14A");
        clasificacion.addItem("18A");
        clasificacion.addItem("R");
        clasificacion.addItem("A");
        
        proyeccion = new ButtonGroup();
        proyeccion.add(si);
        proyeccion.add(no);
        
        nombre.setBounds(270, 50, ANCHOC, ALTOC);
        director.setBounds(270, 90, ANCHOC, ALTOC);
        pais.setBounds(270, 130, ANCHOC, ALTOC);
        anio.setBounds(640, 90, ANCHOC, ALTOC);
        si.setBounds(640, 130, 50, ALTOC);
        no.setBounds(690, 130, 50, ALTOC);
        
        buscar.setBounds(740, 210, ANCHOC,ALTOC);
        insertar.setBounds(90, 210, ANCHOC,ALTOC);
        actualizar.setBounds(300, 210, ANCHOC,ALTOC);
        eliminar.setBounds(530, 210, ANCHOC,ALTOC);
        clasificacion.setBounds(640, 50, ANCHOC,ALTOC);
        resultados=new JTable();
        resultados.setBounds(0,250,1000,200);
        table.setBounds(resultados.getBounds());
        table.add(new JScrollPane(resultados));
        
    }
    
    public void llenarTabla(){
        tm = new DefaultTableModel(){
            public Class<?> getColumnClass(int column){
                switch (column){
                    case 0:
                        return String.class;
                    case 1:
                        return String.class;
                    case 2:
                        return String.class;
                    case 3:
                        return String.class;
                    case 4:
                        return Integer.class;
                    default:
                        return Boolean.class;
                }
            }
          
            
        };
        
        tm.addColumn("Nombre");
        tm.addColumn("Director");
        tm.addColumn("Pais");
        tm.addColumn("Clasifiacion");
        tm.addColumn("Anio");
        tm.addColumn("Proyeccion");
        
        Moviedao fd = new Moviedao();
        ArrayList<Movie> movies = fd.readAll();
        
        for (Movie fi : movies){
            tm.addRow(new Object[]{fi.getNombre(),fi.getDirector(),fi.getPais(),fi.getCalisificaion(),fi.getAnio(),fi.isProyeccion()});
        }
        
        resultados.setModel(tm);
    }
    
    public void eventos(){
        insertar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                Moviedao fd = new Moviedao();
                Movie f = new Movie(nombre.getText(),director.getText(),pais.getText(),clasificacion.getSelectedItem().toString(),
                Integer.parseInt(anio.getText()),true);
                
                if(no.isSelected()){
                    f.setProyeccion(false);
                }
                
                if(fd.create(f)){
                    JOptionPane.showMessageDialog(null,"Exito");
                    limpiarCampos();
                    llenarTabla();
                }else{
                    JOptionPane.showMessageDialog(null,"Ocurrio un problema");
                }
            }
        
        });
        
        actualizar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                Moviedao fd = new Moviedao();
                Movie f = new Movie(nombre.getText(),director.getText(),pais.getText(),clasificacion.getSelectedItem().toString(),
                Integer.parseInt(anio.getText()),true);
                
                if(no.isSelected()){
                    f.setProyeccion(false);
                }
                
                if(fd.update(f)){
                    JOptionPane.showMessageDialog(null,"Exito");
                    limpiarCampos();
                    llenarTabla();
                }else{
                    JOptionPane.showMessageDialog(null,"Ocurrio un problema");
                }
            }
        
        });
        
    eliminar.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){
            Moviedao fd = new Moviedao();
            if(fd.delete(nombre.getText())){
                JOptionPane.showMessageDialog(null,"Eliminado");
                limpiarCampos();
                llenarTabla();
            }else{
                JOptionPane.showMessageDialog(null,"Ocurrio un problema");
            }
        }
    });
    
    buscar.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){
            Moviedao fd = new Moviedao();
            Movie f = fd.read(nombre.getText());
            if(f == null){
                JOptionPane.showMessageDialog(null,"No se ha encontrado");
            }else{
                nombre.setText(f.getNombre());
                director.setText(f.getDirector());
                pais.setText(f.getPais());
                clasificacion.setSelectedItem(f.getCalisificaion());
                anio.setText(Integer.toString(f.getAnio()));
                
                if(f.isProyeccion()){
                    si.setSelected(true);
                }else{
                    no.setSelected(true);
                }
            }
        }
    });
    }
    
    public void limpiarCampos(){
        nombre.setText("");
        director.setText("");
        pais.setText("");
        clasificacion.setSelectedItem("G");
        anio.setText("");
        //proyeccion.setSelected(no, true);
        
    }
}
