package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.DummyNeighbourGenerator;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.graphics.Color.RED;
import static android.graphics.Color.YELLOW;

public class DetailsNeigbourActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView (R.id.mName_neigbbour)//Name of Neighbour
    TextView mNeighbourName;
    @BindView (R.id.myAvatar)//Neighbour Avatar
    ImageView mAvatar;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.fab)
    FloatingActionButton fab;


    private NeighbourApiService mApiService;
    Neighbour neighbour;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_scrolling);
        ButterKnife.bind(this);

        //Récupération des détails d'un voisin
        Intent intent = getIntent();
        neighbour = intent.getParcelableExtra ("data");
        position = intent.getExtras().getInt ("position");

        //Log.e ("FavoriteInit", "Fav: " + neighbour.isFavorite () );

        //la flêche de retour
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("MyTitle");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // perform whatever you want on back arrow click
                toolbar.setNavigationOnClickListener(view -> onBackPressed());
            }
        });

        //pour ajouter a la liste des favoris
        fab.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {

                neighbour.setFavorite (!neighbour.isFavorite ());

               // Log.e ("onActivityResult", "id: " + neighbour.isFavorite () );

                Intent resultIntent = new Intent();
                // TODO Add extras or a data URI to this intent as appropriate.
               // resultIntent.putExtra("neighbour", neighbour);
                resultIntent.putExtra("position", position);
                setResult(Activity.RESULT_OK, resultIntent);
                finish();

            }
        });


        //afficher le nom de la personne dans le collapsingToolbarLayout
        collapsingToolbarLayout.setTitle(neighbour.getName ());

        //afficher les details envoyes
        mNeighbourName.setText(neighbour.getName ());
        Glide.with(this)
                .load(neighbour.getAvatarUrl ())
                //.apply(RequestOptions.circleCropTransform())
                .into(mAvatar);

    }

   /** @Override
    public void onBackPressed() {
        super.onBackPressed ();


    }**/
}
