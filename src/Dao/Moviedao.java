/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Conexion.Conexion;
import Interfaces.Metodos;
import Modelo.Movie;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LN710Q
 */
public class Moviedao implements Metodos<Movie>{
    private static final String SQL_INSERT = "INSERT INTO movie (nombre,director,pais,clasificacion,anio,proyeccion) VALUES (?,?,?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE movie SET nombre = ?, director = ? , pais = ?, clasificacion =?, WHERE nombre=?";
    private static final String SQL_DELETE = "DELETE FROM movie WHERE nombre=?";
    private static final String SQL_READ = "SELECT * FROM movie WHERE nombre=?";
    private static final String SQL_READALL = "SELECT * FROM movie";
    private static final Conexion con = Conexion.conectar();
    
    @Override
    public boolean create(Movie g) {
        PreparedStatement ps;
        try{
            ps = con.getCnx().prepareStatement(SQL_INSERT);
            ps.setString(1,g.getNombre());
            ps.setString(2,g.getDirector());
            ps.setString(3,g.getPais());
            ps.setString(4,g.getCalisificaion());
            ps.setInt(5,g.getAnio());
            ps.setBoolean(6, true);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(Moviedao.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            con.cerrarConexion();
        }
        return false;
    }

    @Override
    public boolean delete(Object key) {
        PreparedStatement ps;
        try{
            ps = con.getCnx().prepareStatement(SQL_DELETE);
            ps.setString(1, key.toString());
            
            if (ps.executeUpdate()>0){
                return true;
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(Moviedao.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            con.cerrarConexion();
        }
        return false;
    }

    @Override
    public boolean update(Movie c) {
        PreparedStatement ps;
        try{
            ps = con.getCnx().prepareStatement(SQL_UPDATE);
            ps.setString(1,c.getNombre());
            ps.setString(2,c.getDirector());
            ps.setString(3,c.getPais());
            ps.setString(4,c.getCalisificaion());
            ps.setInt(5,c.getAnio());
            ps.setBoolean(6, true);
            if (ps.executeUpdate() > 0 )
                return true;
        }catch (SQLException ex) {
            Logger.getLogger(Moviedao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    @Override
    public Movie read(Object key) {
        Movie m = null;
        PreparedStatement ps;
        ResultSet rs;
        try{
            ps = con.getCnx().prepareStatement(SQL_READ);
            ps.setString(1, key.toString());
            
            rs = ps.executeQuery();
            while (rs.next()){
                m = new Movie(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getBoolean(6));
            }
            rs.close();
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(Moviedao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            con.cerrarConexion();
        }
        return m;
    }

    @Override
    public ArrayList<Movie> readAll() {
        ArrayList<Movie> all = new ArrayList();
        Statement s;
        ResultSet rs;
        try{
            s = con.getCnx().prepareStatement(SQL_READALL);
            rs = s.executeQuery(SQL_READALL);
            while(rs.next()){
                all.add(new Movie(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getBoolean(6)));
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Movie.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            con.cerrarConexion();
        }
        return all;
    }
    
}
