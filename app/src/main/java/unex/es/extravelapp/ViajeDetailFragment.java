package unex.es.extravelapp;

import android.app.Activity;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import unex.es.extravelapp.dummy.DummyContent;

public class ViajeDetailFragment extends Fragment {


    /*
    * Esta es la clase que se encarga de mostrar en pantalla los datos
    * detallados del viaje que previamente haya seleccionado el usuario en la lista.
    */

    public static final String ARG_ITEM_ID = "item_id";
    private DummyContent.DummyItem mItem;
    public ViajeDetailFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            mItem = DummyContent.ITEMS.get(Integer.valueOf(getArguments().getString(ARG_ITEM_ID))-1);

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle("ExTravel");
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.viaje_detail, container, false);
        if (mItem != null) {
            ((TextView) rootView.findViewById(R.id.tipoTransporte)).setText(mItem.getTipoTransporte());
            ((TextView) rootView.findViewById(R.id.horaSalida)).setText(mItem.getHoraSalida());
            ((TextView) rootView.findViewById(R.id.horaLlegada)).setText(mItem.getHoraLlegada());
            ((TextView) rootView.findViewById(R.id.precio)).setText(mItem.getPrecio());
            ((TextView) rootView.findViewById(R.id.fecha)).setText(mItem.getFecha());
            ((TextView) rootView.findViewById(R.id.origen)).setText(mItem.getOrigen());
            ((TextView) rootView.findViewById(R.id.destino)).setText(mItem.getDestino());
        }
        return rootView;
    }
}
