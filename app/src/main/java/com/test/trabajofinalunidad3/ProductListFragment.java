package com.test.trabajofinalunidad3;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class ProductListFragment extends Fragment {
    private static final String ARG_PRODUCTS = "products";

    // Constructor estático para crear una instancia del fragmento con los argumentos
    public static ProductListFragment newInstance(ArrayList<Product> products) {
        ProductListFragment fragment = new ProductListFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(ARG_PRODUCTS, products);
        fragment.setArguments(args);
        return fragment;
    }

    // Recupera los productos enviados como argumento
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            ArrayList<Product> products = getArguments().getParcelableArrayList(ARG_PRODUCTS);
            // Aquí puedes trabajar con la lista de productos
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Infla el diseño del fragmento
        return inflater.inflate(R.layout.fragment_product_list, container, false);
    }
}

