package unex.es.extravelapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.widget.TextView;

import unex.es.extravelapp.BD_Viajes.DataBaseHelper;
import unex.es.extravelapp.dummy.DummyContent;

public class ViajeDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viaje_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton favorito = (FloatingActionButton) findViewById(R.id.fab);
        favorito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "¿Añadir a favoritos?", Snackbar.LENGTH_LONG)
                        .setAction("Añadir", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent A2 = new Intent(getApplicationContext(), ViajeListActivity.class);

                                Intent intent = getIntent();
                                Bundle extras = intent.getExtras();
                                String id = (String) extras.get("viaje_id");
                                String idViaje = (String) extras.get("viaje_idViaje");
                                DataBaseHelper dbhelper = new DataBaseHelper(getApplication());
                                dbhelper.importarViaje(id, idViaje);

                                startActivity(A2);

                            }
                        }).show();
            }
        });

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // savedInstanceState is non-null when there is fragment state saved from previous configurations of this activity
        // In this case, the fragment will automatically be re-added to its container so we don't need to manually add it.
        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity using a fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putString(ViajeDetailFragment.ARG_ITEM_ID,
                    getIntent().getStringExtra(ViajeDetailFragment.ARG_ITEM_ID));
            ViajeDetailFragment fragment = new ViajeDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.viaje_detail_container, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of thisactivity, the Up button is shown.
            navigateUpTo(new Intent(this, ViajeListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
