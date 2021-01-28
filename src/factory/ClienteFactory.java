/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;

import manager.ClienteManager;
import implementation.ClienteManagerImplementation;

/**
 * Factoria del cliente
 * @author Fredy Vargas Flores
 */
public class ClienteFactory {
    public ClienteManager getClienteManagerImplementation(){
        return new ClienteManagerImplementation();
    }
}
