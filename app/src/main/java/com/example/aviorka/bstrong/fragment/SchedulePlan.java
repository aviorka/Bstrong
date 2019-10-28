package com.example.aviorka.bstrong.fragment;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.aviorka.bstrong.R;
import com.example.aviorka.bstrong.persistence.Storage;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SchedulePlan.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SchedulePlan#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SchedulePlan extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    int [][] idMapping = {{R.id.tvLegs0, R.id.tvLegs1, R.id.tvLegs2, R.id.tvLegs3, R.id.tvLegs4, R.id.tvLegs5, R.id.tvLegs6},
            {R.id.tvBack0, R.id.tvBack1, R.id.tvBack2, R.id.tvBack3, R.id.tvBack4, R.id.tvBack5, R.id.tvBack6},
            {R.id.tvChest0, R.id.tvChest1, R.id.tvChest2, R.id.tvChest3, R.id.tvChest4, R.id.tvChest5, R.id.tvChest6},
            {R.id.tvBiceps0, R.id.tvBiceps1, R.id.tvBiceps2, R.id.tvBiceps3, R.id.tvBiceps4, R.id.tvBiceps5, R.id.tvBiceps6},
            {R.id.tvTriceps0, R.id.tvTriceps1, R.id.tvTriceps2, R.id.tvTriceps3, R.id.tvTriceps4, R.id.tvTriceps5, R.id.tvTriceps6},
            {R.id.tvShoulders0, R.id.tvShoulders1, R.id.tvShoulders2, R.id.tvShoulders3, R.id.tvShoulders4, R.id.tvShoulders5, R.id.tvShoulders6}};


    private static final int RECU_1 =1;
    private static final int RECU_2 =2;
    private static final int RECU_3 =3;

    private OnFragmentInteractionListener mListener;
    private Storage db ;
    public SchedulePlan() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SchedulePlan.
     */
    // TODO: Rename and change types and number of parameters
    public static SchedulePlan newInstance(String param1, String param2) {
        SchedulePlan fragment = new SchedulePlan();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = Storage.geInstance(this.getContext());
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    /**
     * Retrieve data depicting a single exercise for a week
     * according to the user selected frequency.
     */
    private void showData(){

        List<ContentValues> resultSet = db.getMultiple("select * from exercise", new String[]{});
        for(ContentValues muscle : resultSet){
            setExercise(muscle.getAsInteger("muscleId"), muscle.getAsInteger("recurrenceId"));
        }
    }

    /**
     * set exercises to schedule plan
     * @param muscle id from DB
     * @param recurrence id from DB
     */
    private void setExercise(int muscle, int recurrence){
        TextView tv = null;

        switch(recurrence){
            case RECU_1:
                tv = (TextView)getView().findViewById(idMapping[muscle-1][1]);
                tv.setText("X");
                break;
            case RECU_2:
                tv = (TextView)getView().findViewById(idMapping[muscle-1][1]);
                tv.setText("X");
                tv = (TextView)getView().findViewById(idMapping[muscle-1][3]);
                tv.setText("X");
                break;
            case RECU_3:
                tv = (TextView)getView().findViewById(idMapping[muscle-1][0]);
                tv.setText("X");
                tv = (TextView)getView().findViewById(idMapping[muscle-1][2]);
                tv.setText("X");
                tv = (TextView)getView().findViewById(idMapping[muscle-1][4]);
                tv.setText("X");
                break;
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_schedule_plan, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
