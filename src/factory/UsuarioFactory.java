/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;

import manager.UsuarioManager;
import client.UsuarioRESTClient;
import implementation.UsuarioManagerImplementation;

/**
 *
 * @author 2dam
 */
public class UsuarioFactory {
    public UsuarioManager  getUsuarioManagerImplementation (){
        return new UsuarioManagerImplementation();
    }
}
