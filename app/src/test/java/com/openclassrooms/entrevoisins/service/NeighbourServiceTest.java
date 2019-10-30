package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.hamcrest.CoreMatchers;
import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Unit test on Neighbour service
 */
@RunWith(JUnit4.class)
public class NeighbourServiceTest {

    private NeighbourApiService service;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }

    @Test
    public void getNeighboursWithSuccess() {
        List<Neighbour> neighbours = service.getNeighbours();
        List<Neighbour> expectedNeighbours = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
        assertThat(neighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedNeighbours.toArray()));
    }

    @Test
    public void deleteNeighbourWithSuccess() {
        Neighbour neighbourToDelete = service.getNeighbours().get(0);
        service.deleteNeighbour(neighbourToDelete);
        assertFalse(service.getNeighbours().contains(neighbourToDelete));
    }
//mes tests
    @Test
    public void getFavoritesWithSuccess() {
       List<Neighbour> favorites = service.getFavorites ();
       List<Neighbour> ExpectedFavorites = favorites;
       assertThat(favorites, IsIterableContainingInAnyOrder.containsInAnyOrder(ExpectedFavorites.toArray()));
    }

    @Test
    public void AddFavoriteWithSuccess() {
       service.getFavorites().clear ();
       Neighbour neighbourFav = service.getNeighbours ().get (0);
       service.addFavorite (0);
       assertTrue (service.getFavorites ().contains(neighbourFav));

      }

   @Test
    public void deleteFavoriteWithSuccess() {
       Neighbour favoriteToDelete = service.getNeighbours ().get (0);
       service.addFavorite (favoriteToDelete.getId ());
       service.deleteFavorite(favoriteToDelete);
       assertFalse (service.getFavorites ().contains(favoriteToDelete));
    }

}
