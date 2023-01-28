package com.nofliegroup.learningappforkids;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AlphabetFragment
 * #newInstance} factory method to
 * create an instance of this fragment.
 */
public class AlphabetFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_alphabet, container, false);

        //Generating the alphabet
        //String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String alpha[] = new String[26];
        for (int i = 0, j= 65; i < 26; i++, j++) {
               alpha[i] = Character.toString((char) j);
               // j++;
       }
        GridView gv = v.findViewById(R.id.gridView1);
        //calling the model
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, alpha);
        gv.setAdapter(adapter);

    gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(getActivity(), AlphabetActivities.class);
            intent.putExtra("name", alpha[position]);
            startActivity(intent);

        }
    });

        return v;

    }
}