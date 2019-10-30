package com.openclassrooms.entrevoisins.service;

import android.annotation.SuppressLint;
import android.util.Log;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

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
    public void addFavorite(int position ) {
        neighbours.get (position).setFavorite (true);

    }

    @Override
   public void deleteFavorite(Neighbour favorite) {
        favorites.remove(favorite); }

}
