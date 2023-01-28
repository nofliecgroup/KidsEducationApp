package com.nofliegroup.learningappforkids;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FruitsFragment
 * #newInstance} factory method to
 * create an instance of this fragment.
 */
public class FruitsFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      View v =  inflater.inflate(R.layout.fragment_fruits, container, false);

      String fruits[] = new String[10];
        fruits[0] = "Apple";
        fruits[1] = "Banana";
        fruits[2] = "Cherry";
        fruits[3] = "Grapes";
        fruits[4] = "Kiwi";
        fruits[5] = "Lemon";
        fruits[6] = "Mango";
        fruits[7] = "Orange";
        fruits[8] = "Pineapple";
        fruits[9] = "Strawberry";

        ListView lv = v.findViewById(R.id.listeviewfruit);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, fruits);
        lv.setAdapter(adapter);


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), FruitActivity.class);
                intent.putExtra("name", fruits[position]);
                startActivity(intent);

            }
        });



    return v;
    }
}