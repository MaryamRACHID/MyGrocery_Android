package com.ensah.mygroceryapp.ui.custProduct;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.ensah.mygroceryapp.databinding.FragmentCustproductsBinding;

public class CustProductsFragment extends Fragment {

    private FragmentCustproductsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        CustProductsViewModel custProductsViewModel =
                new ViewModelProvider(this).get(CustProductsViewModel.class);

        binding = FragmentCustproductsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;
        custProductsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}