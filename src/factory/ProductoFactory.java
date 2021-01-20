/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;

import manager.ProductoManager;
import implementation.ProductoManagerImplementation;

/**
 *
 * @author 2dam
 */
public class ProductoFactory {

    public ProductoManager getProductoManagerImplementation() {
        return new ProductoManagerImplementation();
    }
}
