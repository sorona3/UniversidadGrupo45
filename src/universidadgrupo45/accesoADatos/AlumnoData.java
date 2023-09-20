
package universidadgrupo45.accesoADatos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import universidadgrupo45.entidades.Alumno;

public class AlumnoData {
      private Connection con = null;
      
      public AlumnoData (){
      
          con = Conexion.getConexion();
          
      }
      
      public void guardarAlumno (Alumno alumno){
          
          String sql = "INSERT INTO alumno (dni,apellido, nombre, fechaNacimiento, estado)"
                  + "values (? , ? ,? ,? ,? )";
          
          try {
              PreparedStatement ps = con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
              ps.setInt(1,alumno.getDni());
              ps.setString(2,alumno.getApellido());
              ps.setString(3,alumno.getNombre());
              ps.setDate(4,Date.valueOf(alumno.getFechaNac()));
              ps.setBoolean(5,alumno.isActivo());
              ps.executeUpdate();
              
              ResultSet rs = ps.getGeneratedKeys();
              
              if (rs.next() ) {
                  
                  alumno.setIdAlumno(rs.getInt(1));
                  JOptionPane.showMessageDialog(null," Alumno guardado");
                  
              }
              ps.close();
         
              
          } catch (SQLException ex) {
              JOptionPane.showMessageDialog(null," error al acceder a la tabla alumno");
          }
          
      }
      
      public void modificarAlumno(Alumno alumno){
      
           String sql = "UPDATE alumno SET dni= ?,apellido= ?, nombre= ?, fechaNacimiento= ? "
                   + " WHERE id_Alumno= ?";
      
          try {
              
              PreparedStatement ps = con.prepareStatement(sql);
              ps.setInt(1,alumno.getDni());
              ps.setString(2,alumno.getApellido());
              ps.setString(3,alumno.getNombre());
              ps.setDate(4,Date.valueOf(alumno.getFechaNac()));
              ps.setInt(5,alumno.getIdAlumno());
              int exito = ps.executeUpdate();
              
              if (exito==1){
              JOptionPane.showMessageDialog(null, " alumno modificado");
              
              }
              
          } catch (SQLException ex) {
              JOptionPane.showMessageDialog(null,"error al acceder a la tabla alumno");
          }
          
   
        }  
      
      public void eliminarAlumno(int id){
          
          String sql = "UPDATE alumno SET estado = 0 where id_Alumno = ?";
          try {
              PreparedStatement ps = con.prepareStatement(sql);
              ps.setInt(1,id);
              int exito = ps.executeUpdate();
              
              if (exito == 1){
              JOptionPane.showMessageDialog(null," Alumno eliminado ");
              
              }
              
              
          } catch (SQLException ex) {
              JOptionPane.showMessageDialog(null,"error al acceder a la tabla alumno");
          }
          
      }
      
      public Alumno buscarAlumno (int id){
          
          
          
          String sql = " SELECT dni, apellido, nombre, fechaNacimiento FROM alumno  WHERE id_Alumno = ? and estado = 1";
          Alumno alumno = null;
          
          try {
              PreparedStatement ps = con .prepareStatement(sql);
              ps.setInt(1, id);
              ResultSet rs = ps.executeQuery();
              if (rs.next()){
              alumno = new Alumno();
              alumno.setIdAlumno(id);
              alumno.setDni(rs.getInt("DNI"));
              alumno.setApellido(rs.getString("Apellido"));
              alumno.setNombre(rs.getString("Nombre"));
              alumno.setFechaNac(rs.getDate("fechaNacimiento").toLocalDate());
              alumno.setActivo(true);
              
              } else {
                  JOptionPane.showMessageDialog(null,"no existe ese  alumno");
              }
              ps.close();
              
          } catch (SQLException ex) {
              JOptionPane.showMessageDialog(null,"error al acceder a la tabla alumno");
          }
          
          
          
          
          return alumno;
      }
      
      public Alumno buscarAlumnoPorDni (int dni){
          
          
          
          String sql = " SELECT id_Alumno, dni, apellido, nombre, fechaNacimiento FROM alumno  WHERE dni = ? and estado = 1";
          Alumno alumno = null;
          
          try {
              PreparedStatement ps = con .prepareStatement(sql);
              ps.setInt(1, dni);
              ResultSet rs = ps.executeQuery();
              if (rs.next()){
              alumno = new Alumno();
              alumno.setIdAlumno(rs.getInt("id_Alumno"));
              alumno.setDni(rs.getInt("DNI"));
              alumno.setApellido(rs.getString("Apellido"));
              alumno.setNombre(rs.getString("Nombre"));
              alumno.setFechaNac(rs.getDate("fechaNacimiento").toLocalDate());
              alumno.setActivo(true);
              
              } else {
                  JOptionPane.showMessageDialog(null,"no existe ese  alumno");
              }
              ps.close();
              
          } catch (SQLException ex) {
              JOptionPane.showMessageDialog(null,"error al acceder a la tabla alumno");
          }
          
          
          
          
          return alumno;
      }
      
       public List <Alumno> listarAlumnos(){
          
          
          
          String sql = " SELECT id_Alumno, dni, apellido, nombre, fechaNacimiento FROM alumno  WHERE  estado = 1";
          ArrayList<Alumno> alumnos = new ArrayList<>();
          
          try {
              PreparedStatement ps = con .prepareStatement(sql);
              
              ResultSet rs = ps.executeQuery();
              while  (rs.next()){
              Alumno alumno = new Alumno();
              alumno.setIdAlumno(rs.getInt("id_Alumno"));
              alumno.setDni(rs.getInt("DNI"));
              alumno.setApellido(rs.getString("Apellido"));
              alumno.setNombre(rs.getString("Nombre"));
              alumno.setFechaNac(rs.getDate("fechaNacimiento").toLocalDate());
              alumno.setActivo(true);
              
              alumnos.add(alumno);
              
              } 
                  
              ps.close();
              
          } catch (SQLException ex) {
              JOptionPane.showMessageDialog(null,"error al acceder a la tabla alumno");
          }
         
          
          return alumnos;
      }
      
      
}
