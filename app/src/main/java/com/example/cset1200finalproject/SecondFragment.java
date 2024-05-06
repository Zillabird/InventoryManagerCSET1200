package com.example.cset1200finalproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.cset1200finalproject.databinding.FragmentSecondBinding;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnAddInv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = String.valueOf(binding.txtboxName.getText());
                String description = String.valueOf(binding.txtboxDesc.getText());
                String seller = String.valueOf(binding.txtboxSeller.getText());
                double price = Double.parseDouble(String.valueOf(binding.txtboxPrice.getText()));
                int quantity = Integer.parseInt(String.valueOf(binding.txtboxQuan.getText()));
                ((Inventory)getActivity()).addInventory(name,price,description,seller,quantity);

            }
        });

        binding.btnDelInv.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                int id = Integer.parseInt(String.valueOf(binding.txtboxID.getText()));
                ((Inventory)getActivity()).delInventory(id);
            }
        });

        binding.btnMakePurch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = Integer.parseInt(String.valueOf(binding.txtboxID.getText()));
                String buyer = String.valueOf(binding.txtboxBuyer.getText());
                ((Inventory)getActivity()).makePurchase(id,buyer);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}