package com.besy.bcsb.servicios.implementacionesDataBase;

import com.besy.bcsb.dominio.Genero;
import com.besy.bcsb.dto.mapper.IGeneroMapper;
import com.besy.bcsb.dto.request.GeneroRequestDto;
import com.besy.bcsb.dto.response.GeneroResponseDto;
import com.besy.bcsb.repositorios.memory.interfaces.IGeneroRepository;
import com.besy.bcsb.servicios.interfaces.IGeneroService;
import com.besy.bcsb.utilidades.validacionesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@ConditionalOnProperty(prefix = "app", name = "type-data", havingValue = "database")
public class GeneroServiceImpl implements IGeneroService {

    private final IGeneroRepository repository;
    private final IGeneroMapper generoMapper;

    public GeneroServiceImpl(IGeneroRepository repository, IGeneroMapper generoMapper) {
        this.repository = repository;
        this.generoMapper = generoMapper;
    }

    @Transactional(readOnly = true)
    @Override
    public List<GeneroResponseDto> obtenerTodos() {
        List<Genero> generos = repository.obtenerTodos();
        List<GeneroResponseDto> generoDto = generos.stream()
                .map(generoMapper::mapToDto)
                .collect(Collectors.toList());

        return generoDto;
    }

    @Transactional(readOnly = true)
    public GeneroResponseDto buscarPorId(Long id) {
        Optional<Genero> oGenero = repository.buscarPorId(id);

        if (oGenero.isEmpty()) {
            throw new NoSuchElementException(String.format("El género con id %d no existe", id));
        }

        return generoMapper.mapToDto(oGenero.get());
    }

    @Transactional(readOnly = false)
    @Override
    public GeneroResponseDto crearGeneros(GeneroRequestDto dto) {
        if (repository.existePorNombre(dto.getNombre())) {
            throw new IllegalArgumentException(String.format("Ya existe un género con el nombre %s", dto.getNombre()));
        }

        Genero genero = generoMapper.mapToEntity(dto);
        Genero nuevoGenero = repository.crearGenero(genero);
        return generoMapper.mapToDto(nuevoGenero);
    }

    @Transactional(readOnly = false)
    @Override
    public GeneroResponseDto actualizarGenero(Long id, GeneroRequestDto dto) {
        Optional<Genero> oGenero = repository.buscarPorId(id);

        if (oGenero.isEmpty()) {
            throw new NoSuchElementException(String.format("El género con id %d no existe", id));
        }

        Genero genero = generoMapper.mapToEntity(dto);
        genero.setId(id);
        Genero generoActualizado = repository.actualizarGenero(id, genero);
        return generoMapper.mapToDto(generoActualizado);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Genero> buscarPorNombre(String nombre) {
        return repository.buscarPorNombre(nombre);
    }
}