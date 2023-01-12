package it.corso.esercizio0301.business.interfaces;

import it.corso.esercizio0301.model.Role;

public interface RuoloServiceInterface {

    public void deleteById(Integer id);


    public Role findById(Integer id);

}
