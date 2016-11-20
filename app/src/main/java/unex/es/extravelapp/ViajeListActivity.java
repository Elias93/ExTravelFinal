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


import unex.es.extravelapp.BD_Viajes.DataBaseHelper;
import unex.es.extravelapp.dummy.DummyContent;

import java.util.ArrayList;
import java.util.List;

public class ViajeListActivity extends AppCompatActivity {

    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viaje_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Listado de vehículos");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Ordenar por...", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                Intent intent = new Intent(getApplicationContext(), MenuLateral.class);
                intent.putExtra("id", "Buscar");
                startActivity(intent);
            }
        });

        View recyclerView = findViewById(R.id.viaje_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);

        if (findViewById(R.id.viaje_detail_container) != null) {
            // The detail container view will be present only in the large-screen layouts (res/values-w900dp).
            // If this view is present, then the activity should be in two-pane mode.
            mTwoPane = true;
        }
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        //restringir por origen
        //recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(DummyContent.ITEMS));
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String origen = (String) extras.get("viaje_origen");
        if(!origen.equals("")) {
            int i = 0;
            List<DummyContent.DummyItem> ITEMS2 = new ArrayList<DummyContent.DummyItem>();
            while (i < DummyContent.ITEMS.size()) {
                if (DummyContent.ITEMS.get(i).getOrigen().equals(origen)) {
                    ITEMS2.add(DummyContent.ITEMS.get(i));
                }
                i++;
            }
            recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(ITEMS2));
        }else
            recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(DummyContent.ITEMS));
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
            //holder.mIdView.setText(mValues.get(position).id);
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
            //public final TextView mIdView;
            public final TextView mContentView;
            public final TextView mContentView2;
            public final TextView mContentView3;
            public DummyContent.DummyItem mItem;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                //mIdView = (TextView) view.findViewById(R.id.id);
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
