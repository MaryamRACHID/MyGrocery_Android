package com.ensah.mygroceryapp.ui.custCategories;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.ensah.mygroceryapp.databinding.FragmentCustcategoriesBinding;

public class CustCategoriesFragment extends Fragment {

    private FragmentCustcategoriesBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        CustCategoriesViewModel custCategoriesViewModel = new CustCategoriesViewModel();

        binding = FragmentCustcategoriesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;
        custCategoriesViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}