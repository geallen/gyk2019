package gykizmir.com.geziuygulamasi;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MyNotesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MyNotesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyNotesFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ListView myNotesListView;
    private Button gotoMyNotesBtn;

    private ProgressDialog progressDialog;
    private List<String> myNotesList = new ArrayList<>();
    private OnFragmentInteractionListener mListener;
    private ArrayAdapter<String> arrayAdapter;

    String myPlace;
    public MyNotesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyNotesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MyNotesFragment newInstance(String param1, String param2) {
        MyNotesFragment fragment = new MyNotesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        myNotesList = getMyNotes();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_my_notes, container, false);

        gotoMyNotesBtn = (Button) view.findViewById(R.id.go_to_my_notes_btn);

        gotoMyNotesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getContext(), AddNotesActivity.class);
                startActivity(intent);
            }
        });

        arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1,
                android.R.id.text1, myNotesList);


        myNotesListView = (ListView) view.findViewById(R.id.my_notes_lv);
        myNotesListView.setAdapter(arrayAdapter);
        return view;
    }

    private List<String> getMyNotes(){
        showProgressDialog();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference reference = firebaseDatabase.getReference().child("GezdigimYerler");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                progressDialog.dismiss();
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    if (!myNotesList.contains(ds.child("sehirAdi").getValue().toString())) {
                        myPlace = ds.child("sehirAdi").getValue().toString();
                        myNotesList.add(myPlace);
                    }

                }
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                progressDialog.dismiss();
            }
        });

        return myNotesList;

    }


    private void showProgressDialog(){
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Yukleniyor ...");
        progressDialog.setCancelable(false);
        progressDialog.show();
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
