package test;

import datos.Conexion;
import datos.PersonaDAO;
import domain.*;
import java.sql.Connection;
import java.sql.SQLException;

public class TestManejoPersonas {
    public static void main(String[] args) {
        Connection conexion=null;
        PersonaDAO personaDao;
        try {
            conexion = Conexion.getConnection();
            if(conexion.getAutoCommit()){
                conexion.setAutoCommit(false);
                personaDao = new PersonaDAO(conexion);
                Persona cambioPersona = new  Persona();
                cambioPersona.setIdPersona(2);
                cambioPersona.setNombre("Karla Ivonne");
                cambioPersona.setApellido("Gomez");
                cambioPersona.setEmail("Kicobe@mail.com");
                cambioPersona.setTelefono("556556555");
                personaDao.update(cambioPersona);
               //Persona newPersona = new  Persona();
               //newPersona.setNombre("carlos");
               //newPersona.setApellido("Ramirez");
               //personaDao.insertar(newPersona);
                conexion.commit();
            }
        } catch (SQLException ex) {
            try {
                ex.printStackTrace(System.out);
                System.out.println("Rollback");
                conexion.rollback();
            } catch (SQLException ex1) {
                ex.printStackTrace(System.out);
            }
        }
        
        
    }
}
