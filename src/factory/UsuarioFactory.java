/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;

import implementation.UsuarioManagerImplementation;

/**
 * Factoria del usuario
 * @author Lorena Cáceres Manuel
 */
public class UsuarioFactory {
    public UsuarioManagerImplementation getUsuarioManagerImplementation() {
        return new UsuarioManagerImplementation();
    }
}
