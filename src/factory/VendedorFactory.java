/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;

import implementation.VendedorManagerImplementation;

/**
 * Factoria del vendedor
 * @author Moroni
 */
public class VendedorFactory {
    public VendedorManagerImplementation getVendedorManagerImplementation (){
        return new VendedorManagerImplementation();
    }
}
