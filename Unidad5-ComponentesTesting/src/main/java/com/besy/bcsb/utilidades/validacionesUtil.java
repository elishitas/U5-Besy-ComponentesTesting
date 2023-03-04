package com.besy.bcsb.utilidades;

public class validacionesUtil {

    public static void validarId(Long id) {

        String mensaje = (id == null) ? "El ID no puede ser nulo." : (id < 1) ? "El ID no puede ser menor a 1." : null;
        if (mensaje != null) {
            throw new IllegalArgumentException(mensaje);
        }
    }

    public static void validarNombreDeGenero(String nombre) {

        if (nombre == null) {
            throw new IllegalArgumentException("El nombre del género no puede ser nulo.");
        }
        if (nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del género no puede ser vacío.");
        }
        if (nombre.length() > 50) {
            throw new IllegalArgumentException("El nombre del género no puede tener más de 50 caracteres.");
        }
    }
}
