package test;

import datos.PersonaDAO;
import datos.usuarioDAO;
import domain.*;
import java.util.*;

public class TestManejoPersonas {
    public static void main(String[] args) {
        PersonaDAO personDao = new PersonaDAO();
        usuarioDAO user = new usuarioDAO();
//        List <Persona> personas = personDao.seleccionar();
//        personas.forEach(persona -> {
//            System.out.println("persona = " + persona);
//        });
        //insertanmdo
//        Persona personaNew = new Persona("Carlos","Esparza","Cespazrza@mail.com","556654654");
//        personDao.insertar(personaNew);
        user.deleteUsuario(100);
        
        
    }
}
