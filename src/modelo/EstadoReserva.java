/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Fredy
 */
public enum EstadoReserva {
    /**
     * La reserva ha sido cancelada
     */
    CANCELADA,
    /**
     * La reserva ha sido confirmada
     */
    CONFIRMADA,
    /**
     * La reserva ha sido finalizada
     */
    REALIZADA,
    /**
     * La reserva ha expirado
     */
    EXPIRADA;

}
