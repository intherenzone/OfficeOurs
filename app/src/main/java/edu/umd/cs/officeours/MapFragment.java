package edu.umd.cs.officeours;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.support.v4.app.Fragment;
import android.widget.LinearLayout;

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
        final View view = inflater.inflate(R.layout.fragment_map, container, false);

        Button home = (Button) view.findViewById(R.id.home_button);
        Button first_floor_button = (Button) view.findViewById(R.id.first_floor_button);
        Button second_floor_button = (Button) view.findViewById(R.id.second_floor_button);
        Button third_floor_button = (Button) view.findViewById(R.id.third_floor_button);
        Button fourth_floor_button = (Button) view.findViewById(R.id.fourth_floor_button);
//        map = (ImageView) view.findViewById(R.id.map);
//        map.setTag(R.drawable.first_floor);
//
//
        home.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                getActivity().finish();
            }
        });

        first_floor_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                LinearLayout mapLayout = (LinearLayout) view.findViewById(R.id.map_layout);
                ImageView mapImageView = new ImageView(getActivity().getApplicationContext());
                int height =  view.findViewById(R.id.MAP_ID).getHeight();
                int width =  view.findViewById(R.id.MAP_ID).getWidth();
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.first_floor);
                mapImageView.setImageBitmap(Bitmap.createScaledBitmap(bitmap,width,height,true));
                mapImageView.setId(R.id.MAP_ID);
                mapLayout.removeView(view.findViewById(R.id.MAP_ID));
                mapLayout.addView(mapImageView);

            }
        });
        second_floor_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                LinearLayout mapLayout = (LinearLayout) view.findViewById(R.id.map_layout);
                ImageView mapImageView = new ImageView(getActivity().getApplicationContext());
                ImageView mapView = (ImageView) view.findViewById(R.id.MAP_ID);
                int height =  mapView.getHeight();
                int width =  mapView.getWidth();
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.second_floor);
                mapImageView.setImageBitmap(Bitmap.createScaledBitmap(bitmap,width,height,true));
                mapImageView.setId(R.id.MAP_ID);
                mapLayout.removeView(view.findViewById(R.id.MAP_ID));
                mapLayout.addView(mapImageView);

            }
        });
        third_floor_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                LinearLayout mapLayout = (LinearLayout) view.findViewById(R.id.map_layout);
                ImageView mapImageView = new ImageView(getActivity().getApplicationContext());
                int height =  view.findViewById(R.id.MAP_ID).getHeight();
                int width =  view.findViewById(R.id.MAP_ID).getWidth();
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.third_floor);
                mapImageView.setImageBitmap(Bitmap.createScaledBitmap(bitmap,width,height,true));
                mapImageView.setId(R.id.MAP_ID);
                mapLayout.removeView(view.findViewById(R.id.MAP_ID));
                mapLayout.addView(mapImageView);

            }
        });
        fourth_floor_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                LinearLayout mapLayout = (LinearLayout) view.findViewById(R.id.map_layout);
                ImageView mapImageView = new ImageView(getActivity().getApplicationContext());
                int height =  view.findViewById(R.id.MAP_ID).getHeight();
                int width =  view.findViewById(R.id.MAP_ID).getWidth();
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.fourth_floor);
                mapImageView.setImageBitmap(Bitmap.createScaledBitmap(bitmap,width,height,true));
                mapImageView.setId(R.id.MAP_ID);
                mapLayout.removeView(view.findViewById(R.id.MAP_ID));
                mapLayout.addView(mapImageView);

            }
        });

        return view;
    }
}
