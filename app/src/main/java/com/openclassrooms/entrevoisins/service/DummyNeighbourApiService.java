package com.openclassrooms.entrevoisins.service;

import android.annotation.SuppressLint;
import android.util.Log;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

import static android.support.constraint.Constraints.TAG;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements  NeighbourApiService {





    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();
    private List<Neighbour> favorites = new ArrayList<> ();


    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    // Get the favorites list

    public List<Neighbour> getFavorites() {

        for (int  i=0 ; i< neighbours.size(); i++ ){

            Neighbour neighbour = getNeighbours().get(i);

            if (neighbour.isFavorite() && !favorites.contains (neighbour)){

                favorites.add(neighbour);
            }

        }


        return favorites;
    }





    @Override
    // add a favorite

    public void addFavorite(Neighbour neighbour) {

        //Log.e ("Appel", "id: " + neighbour.getId () );


        for (int  i=0 ; i< neighbours.size(); i++ ){

            if (neighbour.getId () == neighbours.get(i).getId ()){

               //neighbours.get (i).setFavorite (true);
                neighbours.get (i).setFavorite (!neighbours.get (i).isFavorite ());

            }

        }
    }




}
