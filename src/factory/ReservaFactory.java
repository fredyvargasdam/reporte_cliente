/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;

import implementation.ReservaManagerImplementation;
import manager.ReservaManager;

/**
 * Factoria de la reserva
 * @author Fredy Vargas Flores
 */
public class ReservaFactory {
    public ReservaManager getReservaManagerImplementation (){
        return new ReservaManagerImplementation();
    }
    
}
