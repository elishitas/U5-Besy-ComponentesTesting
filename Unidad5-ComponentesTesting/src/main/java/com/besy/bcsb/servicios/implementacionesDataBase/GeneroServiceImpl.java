package com.besy.bcsb.servicios.implementacionesDataBase;

import com.besy.bcsb.dominio.Genero;
import com.besy.bcsb.repositorios.memory.interfaces.IGeneroRepository;
import com.besy.bcsb.servicios.interfaces.IGeneroService;
import com.besy.bcsb.utilidades.validacionesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@ConditionalOnProperty(prefix = "app", name = "type-data", havingValue = "database")
public class GeneroServiceImpl implements IGeneroService {

    @Autowired
    IGeneroRepository generoRepository;

    //public GeneroServiceImpl(IGeneroRepository generoRepository) {
      //  this.generoRepository = generoRepository;
    //}

    @Override
    @Transactional(readOnly = true)
    public List<Genero> obtenerTodos() {
        return this.generoRepository.obtenerTodos();
    }

    @Override
    @Transactional(readOnly = false)
    public Genero crearGeneros(Genero genero) {

        validacionesUtil.validarNombreDeGenero(genero.getNombre());

        if (this.generoRepository.existePorNombre(genero.getNombre())) {
            //se deja una excepcion
            throw new IllegalArgumentException("El nombre del genero ya existe");
        }
        return this.generoRepository.crearGenero(genero);
    }

    @Override
    @Transactional(readOnly = false)
    public Genero actualizarGenero(Long id, Genero genero) {

        validacionesUtil.validarNombreDeGenero(genero.getNombre());
        genero.setId(id);

        Optional<Genero> generoExistente = this.generoRepository.buscarPorId(id);

        if (generoExistente.isPresent()) {
            if (this.generoRepository.existePorNombre(genero.getNombre())) {
                throw new IllegalArgumentException("Ya existe un genero con ese nombre.");
            }
            return this.generoRepository.actualizarGenero(id, genero);
        } else {
            throw new IllegalArgumentException("No existe genero con ese ID.");
        }
    }

    @Override
    public Optional<Genero> buscarPorNombre(String nombre) {
        return this.generoRepository.buscarPorNombre(nombre);
    }
}
