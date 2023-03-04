package com.besy.bcsb.repositorios.memory.implementaciones;

import com.besy.bcsb.dominio.Personaje;
import com.besy.bcsb.repositorios.memory.interfaces.IPersonajeRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class PersonajeRepositoryImpl implements IPersonajeRepository {

    private List<Personaje> personajes;

    public PersonajeRepositoryImpl() {

        this.personajes = new ArrayList<>(
                Arrays.asList(
                        new Personaje
                                (1L,"Larry", 31, 51.1f, "Las elevadas aspiraciones de Larry (Ben Stiller) caen en saco roto cuando debe aceptar un humilde trabajo de vigilante nocturno en el Museo de Historia Natural de Nueva York."),
                        new Personaje
                                (2L,"Walter Mitty", 42, 52.2f, "Walter Mitty (Ben Stiller) es un hombre soñador que trabaja como editor gráfico en la revista LIFE y que escapa de su aburrida vida gracias a su imaginación, inventando mundos de fantasía en los que se convierte en un verdadero héroe."),
                        new Personaje
                                (3L,"Derek Zoolander", 33, 53.3f, "Derek Zoolander (Ben Stiller) ha sido por tres temporadas consecutivas el Top Model número uno. Pero la cresta de la fama es inestable."),
                        new Personaje
                                (4L,"Ted Stehman", 20, 54.4f, "Trece años después sigue suspirando por la misma chica."),
                        new Personaje
                                (5L,"Jennifer Lopez", 53, 55.5f, "La superestrella mundial reflexiona sobre su polifacética carrera y la presión que supone vivir bajo los focos."),
                        new Personaje
                                (6L,"Britney Spears", 53, 56.6f, "La lucha de Britney Spears para recuperar su propia tutela en los tribunales se juega en paralelo a la carrera por tratar de contar su calvario personal."),
                        new Personaje
                                (7L,"Emma Stone", 33, 57.7f, "La historia de La La Land:Un estado mental ensoñador y eufórico alejado de las más duras realidades de la vida."),
                        new Personaje
                                (8L,"Miles Teller", 28, 58.8f, "El protagonista aprende de memoria durante una larga noche de práctica compulsiva y lacerante."),
                        new Personaje
                                (9L,"Mei Lee", 16, 59.9f, "Una niña se debate entre seguir siendo la hija obediente que su madre quiere que sea y el caos de la adolescencia."),
                        new Personaje
                                (10L,"Luca", 7, 50.10f, "Dos niños emprenden una aventura entre humanos que odian a los monstruos.")
                )
        );

    }

    @Override
    public List<Personaje> buscarPorNombreYEdad(String nombre, Integer edad) {
        if (nombre == null && edad <= 0) {
            return this.personajes;
        }

        return this.personajes.stream()
                .filter(p -> (nombre == null || p.getNombre().equalsIgnoreCase(nombre))
                        && (edad <= 0 || p.getEdad() == edad))
                .collect(Collectors.toList());
    }

    @Override
    public List<Personaje> buscarPorEdades(Integer desde, Integer hasta) {
        return personajes.stream()
                .filter(p -> p.getEdad() >= desde && p.getEdad() <= hasta)
                .collect(Collectors.toList());
    }

    @Override
    public Personaje crear(Personaje personaje) {
        personaje.setId(this.personajes.size()+1L);
        this.personajes.add(personaje);
        return personaje;
    }

    @Override
    public Personaje actualizar(Long id, Personaje personaje) {
        if (existePersonajePorId(id)) {
            personajes.stream()
                    .filter(p -> p.getId().equals(id))
                    .findFirst()
                    .ifPresent(p -> {
                        p.setNombre(personaje.getNombre());
                        p.setEdad(personaje.getEdad() != null ? personaje.getEdad() : p.getEdad());
                        p.setPeso(personaje.getPeso() != null ? personaje.getPeso() : p.getPeso());
                        p.setHistoria(personaje.getHistoria() != null ? personaje.getHistoria() : p.getHistoria());
                    });
            return personaje;
        } else {
            throw new IllegalArgumentException("No existe personaje con ese ID.");
        }
    }

    @Override
    public boolean existePersonajePorId(Long id) {
        return this.personajes.stream().anyMatch(g -> g.getId().equals(id));
    }

    @Override
    public List<Personaje> buscarTodos() {
        return this.personajes;
    }
}

