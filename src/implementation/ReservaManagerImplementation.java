/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package implementation;

import client.ReservaRESTClient;
import exceptions.ErrorServerException;
import java.net.ConnectException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.GenericType;
import manager.ReservaManager;
import modelo.Reserva;

/**
 *
 * @author Fredy Vargas Flores
 */
public class ReservaManagerImplementation implements ReservaManager {

    private final ReservaRESTClient webClient;
    private static final Logger LOGGER = Logger.getLogger("ReservaManagerImplementation");

    public ReservaManagerImplementation() {
        webClient = new ReservaRESTClient();
    }

    @Override
    public List<Reserva> findReservasCanceladas() throws ClientErrorException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void edit(Reserva reserva) throws ClientErrorException{
        webClient.edit(reserva);
    }

    @Override
    public List<Reserva> findReservasConfirmadas() throws ClientErrorException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Reserva find(Reserva reserva, String id) throws ErrorServerException {
       reserva = null;
        try {
            reserva = webClient.find(Reserva.class, id);
        } catch (Exception e) {
            if(e.getCause() instanceof ConnectException){
            LOGGER.severe("ReservaManagerImplementation: find " + e.getMessage());
            throw new ErrorServerException();
            }
        }
        return reserva;
    }

    public void create(Reserva reserva) throws ClientErrorException {
        webClient.create(reserva);
    }

    @Override
    public List<Reserva> findReservasRealizadas() throws ErrorServerException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Reserva> findReservas() throws ClientErrorException {
        List<Reserva> reserva = null;
        reserva = webClient.findReservas(new GenericType<List<Reserva>>() {
        });
        
        return reserva;
    }

    @Override
    public void remove(String id) throws ErrorServerException {
        try {
            webClient.remove(id);
        } catch (Exception e) {
            if(e.getCause() instanceof ConnectException){
            LOGGER.severe("ReservaManagerImplementation: remove " + e.getMessage());
            throw new ErrorServerException();
            }
        }
    }

    @Override
    public void close() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

 
}