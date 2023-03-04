package com.besy.bcsb.repositorios.memory.implementaciones;

import com.besy.bcsb.dominio.Genero;
import com.besy.bcsb.repositorios.memory.interfaces.IGeneroRepository;
import com.besy.bcsb.utilidades.validacionesUtil;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public class GeneroRepositoryImpl implements IGeneroRepository {

    private List<Genero> generos;

    public GeneroRepositoryImpl(){
        this.generos = new ArrayList<>(
                Arrays.asList(
                        new Genero(1L, "Aventura"),
                        new Genero(2L, "Comedia"),
                        new Genero(3L, "Documental"),
                        new Genero(4L, "Musical"),
                        new Genero(5L, "Infantil")
                )
        );
    }

    @Override
    public List<Genero> obtenerTodos() {
        return this.generos;
    }

    @Override
    public Genero crearGenero(Genero genero) {
        genero.setId(this.generos.size()+1L);

        this.generos.add(genero);

        return genero;
    }

    @Override
    public Genero actualizarGenero(Long id, Genero genero) {
        validacionesUtil.validarId(id);

        Optional<Genero> generoExistente = this.generos.stream()
                .filter(g -> g.getId().equals(id)).findFirst();

        if (generoExistente.isPresent()) {
            generoExistente.get().setNombre(genero.getNombre());
            return genero;
        } else {
            return null;
        }
    }
    @Override
    public Optional<Genero> buscarPorId(Long id) {
        validacionesUtil.validarId(id);

        return this.generos.stream()
                .filter(g -> g.getId().equals(id))
                .findFirst();
    }

    @Override
    public boolean existePorNombre(String nombre) {
        return this.generos.stream().anyMatch(g -> g.getNombre().equalsIgnoreCase(nombre));
    }

    @Override
    public Optional<Genero> buscarPorNombre(String nombre) {
        return this.generos.stream()
                .filter(g -> g.getNombre().equalsIgnoreCase(nombre)).findFirst();
    }
}
