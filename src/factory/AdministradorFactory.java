/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;

import manager.AdministradorManager;
import client.AdministradorRESTClient;

/**
 *
 * @author 2dam
 */
public class AdministradorFactory {
    public AdministradorManager getAdministradorRESTClient (){
        return new AdministradorRESTClient();
    }
}
