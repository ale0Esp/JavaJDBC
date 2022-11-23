package datos;

import static datos.Conexion.*;
import domain.Persona;
import java.sql.*;
import java.util.*;

public class PersonaDAO {

    private Connection conexionTransaccional;

    private static final String SQL_SELECT = "SELECT id_persona,nombre,apellido,email,telefono FROM persona";
    private static final String SQL_INSERT = "INSERT INTO persona (nombre, apellido, email, telefono) VALUES (?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE persona SET nombre=?, apellido=?, email=?, telefono=? WHERE id_persona = ?";
    private static final String SQL_DELETE = "DELETE FROM persona WHERE id_persona=?";

    public PersonaDAO(Connection conexionTransaccional) {
        this.conexionTransaccional = conexionTransaccional;
    }

    public List<Persona> seleccionar() throws SQLException {
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement stmt = null;
        Persona persona = null;
        List<Persona> personas = new ArrayList<>();
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int idPersona = rs.getInt("id_persona");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String email = rs.getString("email");
                String telefono = rs.getString("telefono");
                persona = new Persona(idPersona, nombre, apellido, email, telefono);
                personas.add(persona);
            }
        
        } finally {
            try {
                close(stmt);
                close(rs);
                if (this.conexionTransaccional == null) {
                    close(conn);
                }
            } catch (SQLException ex) {
            }
        }
        return personas;
    }

    public int insertar(Persona persona) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;

        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, persona.getNombre());
            stmt.setString(2, persona.getApellido());
            stmt.setString(3, persona.getEmail());
            stmt.setString(4, persona.getTelefono());
            registros = stmt.executeUpdate();
       
        } finally {
            try {
                close(stmt);
                if (this.conexionTransaccional == null) {
                    close(conn);
                }
            } catch (SQLException ex) {
            }
        }
        return registros;
    }

    public int update(Persona persona) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            System.out.println("ejecutando query: " + SQL_UPDATE);
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, persona.getNombre());
            stmt.setString(2, persona.getApellido());
            stmt.setString(3, persona.getEmail());
            stmt.setString(4, persona.getTelefono());
            stmt.setInt(5, persona.getIdPersona());

            rows = stmt.executeUpdate();
            System.out.println("Registros actualizado:" + rows);
        } finally {
            try {
                close(stmt);
                if (this.conexionTransaccional == null) {
                    close(conn);
                }
            } catch (SQLException ex) {
            }
        }
        return rows;
    }

    public int delete(Persona persona) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            System.out.println("Ejecutando query:" + SQL_DELETE);
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, persona.getIdPersona());
            rows = stmt.executeUpdate();
            System.out.println("Registros eliminados:" + rows);
        
        } finally {
            try {
                close(stmt);
                if (this.conexionTransaccional == null) {
                    close(conn);
                }
            } catch (SQLException ex) {
            }
        }

        return rows;
    }
}
