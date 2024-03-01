// --------------------------------------------------------------------------
//* Basado en: */
// https://www.youtube.com/watch?v=LjML3JTmGps&t=184s&ab_channel=SantiagoJmnz
// --------------------------------------------------------------------------

package com.estudiante.spring.app.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.estudiante.spring.app.Models.Estudiante;
import com.estudiante.spring.app.Services.IdUnicoService;
import java.time.LocalDate;

//  Decorador 
@RestController  
@RequestMapping("/estudiantes") 
public class EstudianteController { 

    private List<Estudiante> listaEstudiantes = new ArrayList<>();
    private int nextId = 1;
    private String idUnico = "";
    private int semestre = 1;
    private int anio = LocalDate.now().getYear();
    private int mes = LocalDate.now().getMonthValue();
    private String anioS = String.valueOf(anio);
    private String idEstudiante = "";
    private String nextIdString = "";
    private IdUnicoService idUnicoService;


    // Operación Create - Agregar un nuevo estudiante
    @PostMapping 
    public ResponseEntity<Estudiante> agregarEstudiante(@RequestBody Estudiante estudiante) {
        
        nextIdString = Integer.toString(nextId++);
        
        if (mes > 6 && semestre == 1) {
            semestre = 2 ; 
        } 
        
        do {
            nextIdString =  "0" + nextIdString;
        } while (nextIdString.length() < 3) ;   
        
        idEstudiante = anioS + String.valueOf(semestre) + nextIdString ;
        estudiante.setId(Integer.parseInt(idEstudiante));
        idUnico = idUnicoService.generarIdUnico();
        estudiante.setidUnico(idUnico);
        
        listaEstudiantes.add(estudiante);
        return new ResponseEntity<>(estudiante, HttpStatus.CREATED);
    }

    // Operación Read - Obtener todos los estudiantes
    @GetMapping 
    public ResponseEntity<List<Estudiante>> obtenerEstudiantes() {
        return new ResponseEntity<>(listaEstudiantes, HttpStatus.OK);
    }

    // Operación Read - Obtener un estudiante por ID
    @GetMapping("/{id}") 
    public ResponseEntity<?> obtenerEstudiantePorId(@PathVariable int id) { 
        Estudiante encontrado = null;
        for (Estudiante estudiante : listaEstudiantes) {
            if (estudiante.getId() == id) {
                encontrado = estudiante;
                break;
            }
        }
        if (encontrado != null) {
            return new ResponseEntity<>(encontrado, HttpStatus.OK); 
        } else {
            return new ResponseEntity<>("Estudiante no encontrado", HttpStatus.NOT_FOUND); 
        }
    }

    
    //public boolean obtenerEstudiantePorIdUnico( String idUnico) { 
     //   boolean encontrado = false;
     //   for (Estudiante estudiante : listaEstudiantes) {
     //       if (estudiante.getidUnico() == idUnico) {
     //           encontrado = true;
     //           break;
     //       }
     //   }
     //   return encontrado ;
    //}

   /*  public String generarIdUnico() {
  
    //    do {
//
  //          UUID uniqueId = UUID.randomUUID() ;
  //          autoUniqueID = uniqueId.toString();
            EncontroIdUnico = obtenerEstudiantePorIdUnico(autoUniqueID);

        } while (EncontroIdUnico == true);

        return autoUniqueID ;
    }*/
    

    // Operación Update - Actualizar un estudiante
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarEstudiante(@PathVariable int id, @RequestBody Estudiante estudianteActualizado) {
        for (Estudiante estudiante : listaEstudiantes) {
            if (estudiante.getId() == id) {
                estudiante.setNombre(estudianteActualizado.getNombre());
                estudiante.setEdad(estudianteActualizado.getEdad());

                // Actualizar otros campos según sea necesario
                return new ResponseEntity<>(estudiante, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("Estudiante no encontrado", HttpStatus.NOT_FOUND);
    }

    // Operación Delete - Eliminar un estudiante
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarEstudiante(@PathVariable int id) {
        Estudiante encontrado = null;
        for (Estudiante estudiante : listaEstudiantes) {
            if (estudiante.getId() == id) {
                encontrado = estudiante;
                break;
            }
        }
        if (encontrado != null) {
            listaEstudiantes.remove(encontrado);
            return new ResponseEntity<>("Estudiante eliminado", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Estudiante no encontrado", HttpStatus.NOT_FOUND);
        }
    }
}
