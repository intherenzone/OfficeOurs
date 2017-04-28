package edu.umd.cs.officeours;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.support.v4.app.Fragment;

public class MapFragment extends Fragment {

    private ImageView map;

    public static MapFragment newInstance() {

        return new MapFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);

        Button home = (Button) view.findViewById(R.id.home_button);
        Button previous_button = (Button) view.findViewById(R.id.preious_floor_button);
        Button next_button = (Button) view.findViewById(R.id.next_floor_button);
        map = (ImageView) view.findViewById(R.id.map);
        map.setTag(R.drawable.first_floor);


        previous_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if((Integer)map.getTag() == R.drawable.first_floor){
                    map.setImageResource(R.drawable.ground_floor);
                    map.setTag(R.drawable.ground_floor);
                } else if((Integer)map.getTag()==R.drawable.second_floor){
                    map.setImageResource(R.drawable.first_floor);
                    map.setTag(R.drawable.first_floor);
                }

            }
        });

        next_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if((Integer)map.getTag()==R.drawable.first_floor){
                    map.setImageResource(R.drawable.second_floor);
                    map.setTag(R.drawable.second_floor);
                } else if((Integer)map.getTag()==R.drawable.ground_floor){
                    map.setImageResource(R.drawable.first_floor);
                    map.setTag(R.drawable.first_floor);
                }
            }
        });


        return view;
    }
}
