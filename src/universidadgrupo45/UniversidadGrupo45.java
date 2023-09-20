/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universidadgrupo45;

import java.sql.Connection;
import java.time.LocalDate;
import java.time.Month;
import universidadgrupo45.accesoADatos.AlumnoData;
import universidadgrupo45.accesoADatos.Conexion;
import universidadgrupo45.entidades.Alumno;

/**
 *
 * @author Sorondo
 */
public class UniversidadGrupo45 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
             Connection con = Conexion.getConexion();
             
        //Alumno juan = new Alumno(40,12321122, "luna", "Juan pedro",LocalDate.of(1980,4,25), true);
        
       /* AlumnoData alu = new AlumnoData();
       // alu.guardarAlumno(juan);
       
       //  alu.modificarAlumno(juan);
       //  alu.eliminarAlumno(40);
       
           Alumno alumnoEncontrado = alu.buscarAlumnoPorDni(10123456);
           if (alumnoEncontrado!= null){ 
           
           System.out.println(" Dni: " + alumnoEncontrado.getDni());
           System.out.println(" Apellido: " + alumnoEncontrado.getApellido());
             }
           
           } 
           */
       AlumnoData alu = new AlumnoData();
           for (Alumno alumno: alu.listarAlumnos()) {
               System.out.println(alumno.getDni());
               System.out.println(alumno.getApellido());
               System.out.println( alumno.getNombre());
               System.out.println(alumno.getFechaNac());
        }
}
}
