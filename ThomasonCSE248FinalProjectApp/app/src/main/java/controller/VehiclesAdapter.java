package controller;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.conorthomason.garageapp.R;
import com.conorthomason.garageapp.Vehicle;

import java.util.List;

public class VehiclesAdapter extends RecyclerView.Adapter<VehiclesAdapter.ViewHolder> {

    private List<Vehicle> mVehicles;

    public VehiclesAdapter(List<Vehicle> vehicles){
        mVehicles = vehicles;
    }

    @Override
    public VehiclesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View vehicleView = inflater.inflate(R.layout.recycler_view_layout, parent, false);

        ViewHolder viewHolder = new ViewHolder(vehicleView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(VehiclesAdapter.ViewHolder viewHolder, int position){
        Vehicle vehicle = mVehicles.get(position);

        TextView textView = viewHolder.mainTextView;
        textView.setText(vehicle.getMainTextFormat());

        TextView subView = viewHolder.detailsTextView;
        subView.setText(vehicle.getDetails());
    }

    @Override
    public int getItemCount(){
        return mVehicles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView mainTextView;
        public TextView detailsTextView;

        public ViewHolder(View itemView){
            super(itemView);
            mainTextView = (TextView) itemView.findViewById(R.id.mainText);
            detailsTextView = (TextView) itemView.findViewById(R.id.detailsText);
        }
    }
}
