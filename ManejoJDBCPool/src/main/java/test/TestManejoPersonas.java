package test;

import datos.Conexion;
import datos.PersonaDAO;
import datos.PersonaDaoJDBC;
import domain.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

public class TestManejoPersonas {

    public static void main(String[] args) {
        Connection conexion = null;
        PersonaDAO personaDAO;
        try {
            conexion = Conexion.getConnection();
            if (conexion.getAutoCommit()) {
                conexion.setAutoCommit(false);
                personaDAO = new PersonaDaoJDBC(conexion);
                List<PersonaDTO> personas = personaDAO.select();
                for (PersonaDTO persona : personas) {
                    System.out.println("persona = " + persona);
                }
                conexion.commit();
                System.out.println("Se ha realizado los cambios");
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
