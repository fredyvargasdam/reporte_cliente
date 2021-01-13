/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;

import manager.VendedorManager;
import client.VendedorRESTClient;

/**
 *
 * @author 2dam
 */
public class VendedorFactory {
    public VendedorManager getVendedorRESTClient (){
        return new VendedorRESTClient();
    }
}
