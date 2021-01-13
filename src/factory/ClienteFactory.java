/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;

import manager.ClienteManager;
import client.ClienteRESTClient;

/**
 *
 * @author 2dam
 */
public class ClienteFactory {
    public ClienteManager getClienteRESTClient (){
        return new ClienteRESTClient();
    }
}
