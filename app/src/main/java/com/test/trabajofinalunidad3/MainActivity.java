package com.test.trabajofinalunidad3;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private ToggleButton toggleButtonOtonoInvierno, toggleButtonPrimaveraVerano, toggleButtonPerenne;
    private ImageView imageView;
    private ProductAdapter adapter;
    private List<String> productList;
    private Map<ToggleButton, ProductData> productDataMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar vistas
        toggleButtonOtonoInvierno = findViewById(R.id.toggleButtonOtonoInvierno);
        toggleButtonPrimaveraVerano = findViewById(R.id.toggleButtonPrimaveraVerano);
        toggleButtonPerenne = findViewById(R.id.toggleButtonPerenne);
        Button buttonBuscar = findViewById(R.id.buttonBuscar);
        Button buttonBorrar = findViewById(R.id.buttonBorrar);
        imageView = findViewById(R.id.imageView);
        RecyclerView recyclerViewProducts = findViewById(R.id.recyclerViewProducts);

        // Configurar RecyclerView
        productList = new ArrayList<>();
        adapter = new ProductAdapter(productList);
        recyclerViewProducts.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewProducts.setAdapter(adapter);

        // Asociar ToggleButtons con datos e imágenes
        productDataMap = new HashMap<>();
        productDataMap.put(toggleButtonOtonoInvierno, new ProductData(R.drawable.otonoinvierno, getOtonoInviernoProducts()));
        productDataMap.put(toggleButtonPrimaveraVerano, new ProductData(R.drawable.primaveraverano, getPrimaveraVeranoProducts()));
        productDataMap.put(toggleButtonPerenne, new ProductData(R.drawable.perenne, getPerenneProducts()));

        // Configurar listeners
        toggleButtonOtonoInvierno.setOnClickListener(v -> handleToggleSelection(toggleButtonOtonoInvierno));
        toggleButtonPrimaveraVerano.setOnClickListener(v -> handleToggleSelection(toggleButtonPrimaveraVerano));
        toggleButtonPerenne.setOnClickListener(v -> handleToggleSelection(toggleButtonPerenne));

        buttonBuscar.setOnClickListener(v -> showProducts());
        buttonBorrar.setOnClickListener(v -> clearSelections());
    }

    private void handleToggleSelection(ToggleButton selectedButton) {
        for (ToggleButton button : productDataMap.keySet()) {
            button.setChecked(button == selectedButton);
        }
    }

    private void showProducts() {
        productList.clear();
        imageView.setImageResource(0);

        for (Map.Entry<ToggleButton, ProductData> entry : productDataMap.entrySet()) {
            if (entry.getKey().isChecked()) {
                ProductData data = entry.getValue();
                imageView.setImageResource(data.imageResId);
                productList.addAll(data.products);
                break;
            }
        }

        adapter.notifyDataSetChanged();
    }

    private void clearSelections() {
        for (ToggleButton button : productDataMap.keySet()) {
            button.setChecked(false);
        }
        productList.clear();
        imageView.setImageResource(0);
        adapter.notifyDataSetChanged();
    }

    private List<String> getOtonoInviernoProducts() {
        return List.of("Calabaza", "Rábanos", "Brócoli", "Escarola", "Guisantes", "Habas", "Nabos", "Coles de Bruselas", "Eneldo", "Alcachofas", "Lentejas");
    }

    private List<String> getPrimaveraVeranoProducts() {
        return List.of("Tomate", "Pepino", "Fresas", "Berenjena", "Zanahoria", "Cebolla", "Melón", "Sandía", "Calabacín", "Espinaca", "Cerezas", "Higos");
    }

    private List<String> getPerenneProducts() {
        return List.of("Romero", "Tomillo", "Espárragos", "Ruibarbo", "Arándanos", "Uvas", "Menta", "Salvia", "Orégano");
    }

    // Clase interna para manejar los datos de productos
    private static class ProductData {
        int imageResId;
        List<String> products;

        ProductData(int imageResId, List<String> products) {
            this.imageResId = imageResId;
            this.products = products;
        }
    }
}





