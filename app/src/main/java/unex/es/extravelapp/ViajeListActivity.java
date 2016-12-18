package unex.es.extravelapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import unex.es.extravelapp.BD_Viajes.DataBaseHelper;
import unex.es.extravelapp.dummy.DummyContent;

import java.util.ArrayList;
import java.util.List;

public class ViajeListActivity extends AppCompatActivity {

    private boolean mTwoPane;
    String latitudOrigen = "";
    String longitudOrigen = "";
    String latitudDestino = "";
    String longitudDestino = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viaje_list);

        FloatingActionButton volver = (FloatingActionButton) findViewById(R.id.fab);
        volver.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MenuLateral.class);
                intent.putExtra("id", "Buscar");
                startActivity(intent);
            }
        });

        View recyclerView = findViewById(R.id.viaje_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);

        /*Ver la ruta entre el origen y destino*/
        FloatingActionButton botonMapa = (FloatingActionButton)findViewById(R.id.botonMap);
        botonMapa.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){

                if(!latitudOrigen.equals("") && !longitudOrigen.equals("") && !latitudDestino.equals("") && !longitudDestino.equals("")) {
                    Intent IntentMapa = new Intent(getApplicationContext(), MapaActivity.class);
                    IntentMapa.putExtra("viaje_origen_Lat", latitudOrigen);
                    IntentMapa.putExtra("viaje_origen_Lon", longitudOrigen);
                    IntentMapa.putExtra("viaje_destino_Lat", latitudDestino);
                    IntentMapa.putExtra("viaje_destino_Lon", longitudDestino);
                    startActivity(IntentMapa);
                }
            }
        });

        if (findViewById(R.id.viaje_detail_container) != null) {
            // The detail container view will be present only in the large-screen layouts (res/values-w900dp).
            // If this view is present, then the activity should be in two-pane mode.
            mTwoPane = true;
        }
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {

        //Obtenemos el nombre de origen que el usuario indicó
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String origen = (String) extras.get("viaje_origen");
        String destino = (String) extras.get("viaje_destino");
        int i = 0;

        //Creamos una lista auxiliar para aquellos viajes que cumplan la condición
        List<DummyContent.DummyItem> VIAJES = new ArrayList<DummyContent.DummyItem>();

        //Relleno la lista de los viajes que cumplan la condicion
        while (i < DummyContent.ITEMS.size()) {
            if (DummyContent.ITEMS.get(i).getOrigen().equals(origen) && DummyContent.ITEMS.get(i).getDestino().equals(destino)) {
                VIAJES.add(DummyContent.ITEMS.get(i));
            }
            i++;
        }

        if(VIAJES.size() == 0){
            CharSequence text = "No hay viajes";
            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(this, text, duration);
            toast.show();

        //Si al menos hay un viaje, obtenemos sus coordenadas para el mapa
        }else{
            latitudOrigen = VIAJES.get(0).getLatitudOrigen();
            longitudOrigen = VIAJES.get(0).getLongitudOrigen();
            latitudDestino = VIAJES.get(0).getLatitudDestino();
            longitudDestino = VIAJES.get(0).getLongitudDestino();
        }
            recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(VIAJES));
    }

    public class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final List<DummyContent.DummyItem> mValues;

        public SimpleItemRecyclerViewAdapter(List<DummyContent.DummyItem> items) {
            mValues = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.viaje_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.mItem = mValues.get(position);
            holder.mContentView.setText(mValues.get(position).getTipoTransporte() + " - " + mValues.get(position).getFecha());
            holder.mContentView2.setText(mValues.get(position).getOrigen() + " - " + mValues.get(position).getDestino());
            holder.mContentView3.setText(mValues.get(position).getHoraSalida() + " - " + mValues.get(position).getHoraLlegada());
            holder.mView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (mTwoPane) {
                        Bundle arguments = new Bundle();
                        arguments.putString(ViajeDetailFragment.ARG_ITEM_ID, holder.mItem.getId().toString());
                        ViajeDetailFragment fragment = new ViajeDetailFragment();
                        fragment.setArguments(arguments);
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.viaje_detail_container, fragment)
                                .commit();
                    } else {
                        Context context = v.getContext();
                        Intent intent = new Intent(context, ViajeDetailActivity.class);
                        intent.putExtra(ViajeDetailFragment.ARG_ITEM_ID, holder.mItem.id);
                        intent.putExtra("viaje_id",holder.mItem.getId());
                        intent.putExtra("viaje_idViaje",holder.mItem.getIdViaje());
                        intent.putExtra("viaje_tipoTransporte",holder.mItem.getTipoTransporte());
                        intent.putExtra("viaje_horaSalida",holder.mItem.getHoraSalida());
                        intent.putExtra("viaje_horaLlegada",holder.mItem.getHoraLlegada());
                        intent.putExtra("viaje_precio",holder.mItem.getPrecio());
                        intent.putExtra("viaje_fecha",holder.mItem.getFecha());
                        intent.putExtra("viaje_origen",holder.mItem.getOrigen());
                        intent.putExtra("viaje_destino",holder.mItem.getDestino());
                        intent.putExtra("viaje_latO",holder.mItem.getLatitudOrigen());
                        intent.putExtra("viaje_longO",holder.mItem.getLongitudOrigen());
                        intent.putExtra("viaje_latD",holder.mItem.getLatitudDestino());
                        intent.putExtra("viaje_longD",holder.mItem.getLongitudDestino());
                        context.startActivity(intent);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final TextView mContentView;
            public final TextView mContentView2;
            public final TextView mContentView3;
            public DummyContent.DummyItem mItem;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                mContentView = (TextView) view.findViewById(R.id.content);
                mContentView2 = (TextView) view.findViewById(R.id.content2);
                mContentView3 = (TextView) view.findViewById(R.id.content3);
            }

            @Override
            public String toString() {
                return mContentView.getText() + "'";
            }
        }
    }
}
