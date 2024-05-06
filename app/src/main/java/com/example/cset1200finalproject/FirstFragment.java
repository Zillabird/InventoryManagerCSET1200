package com.example.cset1200finalproject;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.cset1200finalproject.databinding.FragmentFirstBinding;

import java.util.Objects;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnModInv.setOnClickListener(v ->
               NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment));

        binding.btnViewInv.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                binding.textView3.setText(((Inventory)getActivity()).printInventory());
            }
        });

        binding.btnViewLogs.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                binding.textView3.setText(((Inventory)getActivity()).printLog());
            }
        });

        binding.textView3.setMovementMethod(new ScrollingMovementMethod());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}