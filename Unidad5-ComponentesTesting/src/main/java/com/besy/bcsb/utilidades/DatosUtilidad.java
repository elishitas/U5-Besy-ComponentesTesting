package com.besy.bcsb.utilidades;

import com.besy.bcsb.dominio.Genero;
import com.besy.bcsb.dominio.PeliculaSerie;
import com.besy.bcsb.dominio.Personaje;
import org.ietf.jgss.GSSName;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;

public class DatosUtilidad {

    public static List<Genero> creacionGeneros() {

        List<Genero> generos = new ArrayList<>();

        Genero genero1 = new Genero(1L, "Aventura");
        Genero genero2 = new Genero(2L, "Comedia");
        Genero genero3 = new Genero(3L, "Documental");
        Genero genero4 = new Genero(4L, "Musical");
        Genero genero5 = new Genero(5L, "Infantil");

        generos.add(genero1);
        generos.add(genero2);
        generos.add(genero3);
        generos.add(genero4);
        generos.add(genero5);

        return generos;
    }

    public static List<PeliculaSerie> creacionPeliculasSerie(List<Genero> generos) {

        List<PeliculaSerie> peliculasSeries = new ArrayList<>();

        PeliculaSerie peliculaSerie1 = new PeliculaSerie
                (1L,"La Noche de los Museos", LocalDate.of(2006, 03, 31), (byte) 5, generos.get(0));
        PeliculaSerie peliculaSerie2 = new PeliculaSerie
                (2L,"La vida secreta de Walter Mitty", LocalDate.of(2013, 03, 31), (byte) 5, generos.get(0));
        PeliculaSerie peliculaSerie3 = new PeliculaSerie
                (3L,"Zoolander", LocalDate.of(2001, 03, 31), (byte) 1, generos.get(1));
        PeliculaSerie peliculaSerie4 = new PeliculaSerie
                (4L,"Loco por Mary", LocalDate.of(1998, 03, 31), (byte) 1, generos.get(1));
        PeliculaSerie peliculaSerie5 = new PeliculaSerie
                (5L,"Halftime", LocalDate.of(2022, 03, 31), (byte) 2, generos.get(2));
        PeliculaSerie peliculaSerie6 = new PeliculaSerie
                (6L,"Britney vs Spears", LocalDate.of(2021, 03, 31), (byte) 2, generos.get(2));
        PeliculaSerie peliculaSerie7 = new PeliculaSerie
                (7L,"La La Land", LocalDate.of(2016, 03, 31), (byte) 3, generos.get(3));
        PeliculaSerie peliculaSerie8 = new PeliculaSerie
                (8L,"Whiplash", LocalDate.of(2014, 03, 31), (byte) 3, generos.get(3));
        PeliculaSerie peliculaSerie9 = new PeliculaSerie
                (9L,"Red", LocalDate.of(2022, 03, 31), (byte) 4, generos.get(4));
        PeliculaSerie peliculaSerie10 = new PeliculaSerie
                (10L,"Luca", LocalDate.of(2021, 03, 31), (byte) 4, generos.get(4));

        peliculasSeries.add(peliculaSerie1);
        peliculasSeries.add(peliculaSerie2);
        peliculasSeries.add(peliculaSerie3);
        peliculasSeries.add(peliculaSerie4);
        peliculasSeries.add(peliculaSerie5);
        peliculasSeries.add(peliculaSerie6);
        peliculasSeries.add(peliculaSerie7);
        peliculasSeries.add(peliculaSerie8);
        peliculasSeries.add(peliculaSerie9);
        peliculasSeries.add(peliculaSerie10);

        return peliculasSeries;
    }

    public static List<Personaje> creacionPersonajes() {

        List<Personaje> personajes = new ArrayList<>();

        Personaje personaje1 = new Personaje
                (1L,"Larry", 31, 51.1f, "Las elevadas aspiraciones de Larry (Ben Stiller) caen en saco roto cuando debe aceptar un humilde trabajo de vigilante nocturno en el Museo de Historia Natural de Nueva York.");
        Personaje personaje2 = new Personaje
                (2L,"Walter Mitty", 42, 52.2f, "Walter Mitty (Ben Stiller) es un hombre soñador que trabaja como editor gráfico en la revista LIFE y que escapa de su aburrida vida gracias a su imaginación, inventando mundos de fantasía en los que se convierte en un verdadero héroe.");
        Personaje personaje3 = new Personaje
                (3L,"Derek Zoolander", 33, 53.3f, "Derek Zoolander (Ben Stiller) ha sido por tres temporadas consecutivas el Top Model número uno. Pero la cresta de la fama es inestable");
        Personaje personaje4 = new Personaje
                (4L,"Ted Stehman", 20, 54.4f, "Trece años después sigue suspirando por la misma chica");
        Personaje personaje5 = new Personaje
                (5L,"Jennifer Lopez", 53, 55.5f, "La superestrella mundial reflexiona sobre su polifacética carrera y la presión que supone vivir bajo los focos");
        Personaje personaje6 = new Personaje
                (6L,"Britney Spears", 53, 56.6f, " La lucha de Britney Spears para recuperar su propia tutela en los tribunales se juega en paralelo a la carrera por tratar de contar su calvario personal");
        Personaje personaje7 = new Personaje
                (7L,"Emma Stone", 33, 57.7f, "La historia de La La Land:Un estado mental ensoñador y eufórico alejado de las más duras realidades de la vida");
        Personaje personaje8 = new Personaje
                (8L,"Miles Teller", 28, 58.8f, "El protagonista aprende de memoria durante una larga noche de práctica compulsiva y lacerante");
        Personaje personaje9 = new Personaje
                (9L,"Mei Lee", 16, 59.9f, "Una niña se debate entre seguir siendo la hija obediente que su madre quiere que sea y el caos de la adolescencia");
        Personaje personaje10 = new Personaje
                (10L,"Luca", 7, 50.10f, "Dos niños emprenden una aventura entre humanos que odian a los monstruos.");

        personajes.add(personaje1);
        personajes.add(personaje2);
        personajes.add(personaje3);
        personajes.add(personaje4);
        personajes.add(personaje5);
        personajes.add(personaje6);
        personajes.add(personaje7);
        personajes.add(personaje8);
        personajes.add(personaje9);
        personajes.add(personaje10);

        return personajes;
    }

    public static List<Personaje> filtrarPersonajesPorNombre(String nombre) {

        List<Personaje> personajes = creacionPersonajes();

            return personajes.stream()
                    .filter(personaje -> personaje.getNombre().equalsIgnoreCase(nombre))
                    .collect(Collectors.toList());
        }

    public static List<Personaje> filtrarPersonajesPorEdad(int edad){

        List<Personaje> personajes = creacionPersonajes();

        return personajes.stream()
                .filter(p->p.getEdad() == edad)
                .collect(Collectors.toList());
    }

    public static List<Personaje> filtrarPorRangoDeEdad(int desde, int hasta){
        List<Personaje> personajes = creacionPersonajes();
        return personajes.stream()
                .filter(personaje -> personaje.getEdad() >= desde && personaje.getEdad()<=hasta).collect(Collectors.toList());
    }

    public static List<PeliculaSerie> filtrarPeliculasSeriesPorTitulo(String titulo){

        List<Genero> generos = creacionGeneros();
        List<PeliculaSerie> peliculasSeries = creacionPeliculasSerie(generos);

        return peliculasSeries.stream()
                .filter(peliculaSerie -> peliculaSerie.getTitulo().equalsIgnoreCase(titulo))
                .collect(Collectors.toList());
    }

    public static List<PeliculaSerie> filtrarPeliculasSeriesPorRangoDeCalificacion(byte desde, byte hasta){
        List<Genero> generos = creacionGeneros();
        List<PeliculaSerie> peliculaSeries = creacionPeliculasSerie(generos);

        return peliculaSeries.stream()
                .filter(peliculaSerie -> peliculaSerie.getCalificacion() >= desde && peliculaSerie.getCalificacion()<=hasta).collect(Collectors.toList());
    }

    public static List<PeliculaSerie> filtrarPeliculasSeriesPorNombreDeGenero(String nombreGenero){

        List<Genero> generos = creacionGeneros();
        List<PeliculaSerie> peliculasSeries = creacionPeliculasSerie(generos);

        return peliculasSeries.stream()
                .filter(peliculaSerie -> peliculaSerie.getGenero().getNombre().equalsIgnoreCase(nombreGenero))
                .collect(Collectors.toList());
    }

    public static List<PeliculaSerie> filtrarPeliculaSeriesPorFecha(String desde,String hasta) {
        List<Genero>generos= creacionGeneros();
        List<PeliculaSerie> peliculasSeries = creacionPeliculasSerie(generos);

        var fechaDesde=LocalDate.parse(desde, DateTimeFormatter.ofPattern("ddMMyyyy"));
        var fechaHasta=LocalDate.parse(hasta,DateTimeFormatter.ofPattern("ddMMyyyy"));

        return peliculasSeries.stream()
                .filter(peliculaSerie ->peliculaSerie.getFechaDeCreacion()
                        .isAfter(fechaDesde) || peliculaSerie.getFechaDeCreacion().isEqual(fechaDesde))
                .filter(peliculaSerie -> peliculaSerie.getFechaDeCreacion()
                        .isBefore(fechaHasta) || peliculaSerie.getFechaDeCreacion().isEqual(fechaHasta))
                .collect(Collectors.toList());
    }

    public static List<PeliculaSerie> agregarPeliculaSerie(PeliculaSerie nuevaPeliculaSerie){
        List<Genero> generos = creacionGeneros();
        List<PeliculaSerie> peliculaSeries = creacionPeliculasSerie(generos);
        nuevaPeliculaSerie.setId((long) (peliculaSeries.size() + 1));
        peliculaSeries.add(nuevaPeliculaSerie);
        return peliculaSeries;
    }

    public static List<PeliculaSerie> modificarPeliculaSerie(long id, PeliculaSerie pelicula) {
        List<Genero> generos = creacionGeneros();
        List<PeliculaSerie> peliculas= creacionPeliculasSerie(generos);


        Optional<PeliculaSerie> oPelicula = peliculas.stream().
                filter(p -> p.getId() == id)
                .findAny();

        if(!oPelicula.isPresent()){
            throw new RuntimeException("El Id ingresado no existe");
        }

        peliculas.forEach(p -> {
            if(p.getId() == id){
                p.setTitulo(pelicula.getTitulo());
                p.setCalificacion((byte) pelicula.getCalificacion());
                p.setFechaDeCreacion(pelicula.getFechaDeCreacion());
                p.setGenero(pelicula.getGenero());
            }
        } );
        return peliculas;
    }

    public static List<Personaje> agregarPersonaje(Personaje nuevoPersonaje){
        List<Personaje> personajes = creacionPersonajes();
        nuevoPersonaje.setId((long) (personajes.size() + 1));
        personajes.add(nuevoPersonaje);
        return personajes;
    }

    public static List<Personaje> modificarPersonaje(Long id, Personaje personaje){
        List<Personaje> personajes = creacionPersonajes();

        Optional<Personaje> oPersonaje = personajes.stream().
                filter(p -> p.getId() == id)
                .findAny();

        if(!oPersonaje.isPresent()){
            throw new RuntimeException("El Id ingresado no existe");
        }

        personajes.forEach(p ->{
            if(p.getId() == id){
                p.setNombre(personaje.getNombre());
                p.setEdad(personaje.getEdad());
                p.setPeso(personaje.getPeso());
                p.setHistoria(personaje.getHistoria());
            }
        });
        return personajes;
    }

    public static List<Genero> agregarGenero(Genero nuevoGenero) {
        List<Genero> generos = creacionGeneros();
        nuevoGenero.setId((long) (generos.size() + 1));
        generos.add(nuevoGenero);
        return generos;
    }

    public static List<Genero> modificarGenero(Long id, Genero genero){
        List<Genero> generos = creacionGeneros();

        Optional<Genero> oGenero = generos.stream().
                filter(p -> p.getId() == id)
                .findAny();

        if(!oGenero.isPresent()){
            throw new RuntimeException("El Id ingresado no existe");
        }

        generos.forEach(g ->{
            if(g.getId()== id){
                g.setNombre(genero.getNombre());
            }
        });
        return generos;
    }


}
