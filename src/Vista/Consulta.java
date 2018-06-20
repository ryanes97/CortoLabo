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
public class Consulta {
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
        setSize(600,600);
        eventos();
    }
    
    public final void agregarLabels(){
        lblnombre = new JLabel("Nombre");
        lbldirector= new JLabel("Director");
        lblpais = new JLabel("Pais");
        lblcalificacion = new JLabel("Calificacion");
        lblanio = new JLabel("Año");
        lblproyeccion = new JLabel("En proyeccion");
        lblnombre.setBounds(10, 10, ANCHOC, ALTOC);
        lbldirector.setBounds(10,60,ANCHOC,ALTOC);
        lblpais.setBounds(10,100,ANCHOC,ALTOC);
        lblcalificacion.setBounds(10,140,ANCHOC,ALTOC);
        lblanio.setBounds(40, 40, ALTOC, ANCHOC);
        lblproyeccion.setBounds(40, 90, ALTOC, ANCHOC);
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
        
        nombre.setBounds(140, 10, ANCHOC, ALTOC);
        director.setBounds(140, 60, ANCHOC, ALTOC);
        pais.setBounds(140, 140, ANCHOC, ALTOC);
        clasificacion.setBounds(140, 220, ALTOC, ANCHOC);
        anio.setBounds(140, 300, ALTOC, ANCHOC);
        si.setBounds(140,140,50,ALTOC);
        no.setBounds(210,140,50,ALTOC);
        
        buscar.setBounds(300, 10, ANCHOC,ALTOC);
        insertar.setBounds(10, 210, ANCHOC,ALTOC);
        actualizar.setBounds(150, 210, ANCHOC,ALTOC);
        eliminar.setBounds(300, 210, ANCHOC,ALTOC);
        resultados=new JTable();
        table.setBounds(10, 250, 500,200);
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
