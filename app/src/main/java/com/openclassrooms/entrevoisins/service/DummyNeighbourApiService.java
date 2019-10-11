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
    public List<Neighbour> getFavorites() {

        for (int  i=0 ; i< neighbours.size(); i++ ){

            Neighbour neighbour = getNeighbours().get(i);

            if (neighbour.isFavorite()){

                favorites.add(neighbour);
            }

        }


        return favorites;
    }




    @SuppressLint("WrongConstant")
    @Override
    public void addFavorite(Neighbour neighbour) {

        //Log.e ("Appel", "id: " + neighbour.getId () );


        for (int  i=0 ; i< neighbours.size(); i++ ){

            if (neighbour.getId () == neighbours.get(i).getId ()){

                neighbours.get (i).setFavorite (true);
                //neighbours.get (i).setFavorite (!neighbours.get (i).isFavorite ());

            }

        }
    }



 /**   @Override
    public List<Neighbour> getFavoritesNeighbour() {

        for (int  i=0 ; i< neighbours.size(); i++ ){

            Neighbour neighbour = getNeighbours().get(i);
            if ( neighbour.isFavorite()==true)
            {
                favoritesNeighbours.add(neighbour);
            }

        }
        return favoritesNeighbours;
    }

    @Override
    public void addFavoritesNeighbour(Neighbour neighbour) {

        if (!favoritesNeighbours.contains(neighbour)) {
            favoritesNeighbours.add(neighbour);
        }

    }
**/


}
