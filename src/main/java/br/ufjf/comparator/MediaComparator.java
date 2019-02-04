/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.comparator;

import br.ufjf.model.Repositorio;
import java.util.Comparator;

/**
 *
 * @author jpdia
 */
public class MediaComparator implements Comparator<Repositorio> {

    @Override
    public int compare(Repositorio t, Repositorio t1) {
        return t.getMedia().compareTo(t1.getMedia());
    }
}
