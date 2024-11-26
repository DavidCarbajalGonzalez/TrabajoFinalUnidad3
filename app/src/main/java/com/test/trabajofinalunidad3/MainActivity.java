package com.test.trabajofinalunidad3;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private ToggleButton selectedToggleButton;
    private ImageView imageView;
    private ProductAdapter adapter;
    private List<Product> productList;
    private Map<ToggleButton, ProductData> productDataMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar vistas
        setupUI();

        // Configurar RecyclerView
        setupRecyclerView();

        // Asociar ToggleButtons con datos e imágenes
        setupProductData();

        // Configurar listeners
        setupListeners();
    }

    // Configura las vistas principales y los botones de acción
    private void setupUI() {
        selectedToggleButton = null;
        imageView = findViewById(R.id.imageView);

        findViewById(R.id.buttonBuscar).setOnClickListener(v -> showProducts());
        findViewById(R.id.buttonBorrar).setOnClickListener(v -> clearSelections());
    }

    // Configura el RecyclerView para mostrar productos en una lista
    private void setupRecyclerView() {
        RecyclerView recyclerViewProducts = findViewById(R.id.recyclerViewProducts);
        productList = new ArrayList<>();
        adapter = new ProductAdapter(productList);
        recyclerViewProducts.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewProducts.setAdapter(adapter);
    }

    //Asocia cada ToggleButton con su imagen correspondiente y lista de productos
    private void setupProductData() {
        productDataMap = new HashMap<>();

        productDataMap.put(findViewById(R.id.toggleButtonOtonoInvierno),
                new ProductData(R.drawable.otonoinvierno, getProducts(
                        new Product("Calabaza", R.drawable.calabaza),
                        new Product("Rábanos", R.drawable.rabanos),
                        new Product("Brócoli", R.drawable.brocoli),
                        new Product("Ajo", R.drawable.ajo),
                        new Product("Cebolla", R.drawable.cebolla),
                        new Product("Acelga", R.drawable.acelga),
                        new Product("Espinaca", R.drawable.espinaca),
                        new Product("Habas", R.drawable.habas),
                        new Product("Guisantes", R.drawable.guisantes),
                        new Product("Kiwi", R.drawable.kiwi),
                        new Product("Naranja", R.drawable.naranjas),
                        new Product("Limón", R.drawable.limones)
                )));

        productDataMap.put(findViewById(R.id.toggleButtonPrimaveraVerano),
                new ProductData(R.drawable.primaveraverano, getProducts(
                        new Product("Tomate", R.drawable.tomate),
                        new Product("Pepino", R.drawable.pepino),
                        new Product("Fresas", R.drawable.fresas),
                        new Product("Melón", R.drawable.melon),
                        new Product("Sandia", R.drawable.sandia),
                        new Product("Lechuga", R.drawable.lechuga),
                        new Product("Zanahoria", R.drawable.zanahoria)

                )));

        productDataMap.put(findViewById(R.id.toggleButtonPerenne),
                new ProductData(R.drawable.perenne, getProducts(
                        new Product("Romero", R.drawable.romero),
                        new Product("Tomillo", R.drawable.tomillo),
                        new Product("Espárragos", R.drawable.esparragos),
                        new Product("Alcachofa", R.drawable.alcachofa),
                        new Product("Apio", R.drawable.apio),
                        new Product("Manzana", R.drawable.manzana),
                        new Product("Pera", R.drawable.pera),
                        new Product("Mora", R.drawable.mora),
                        new Product("Menta", R.drawable.menta)
                )));
    }

    //  Configura los listeners para manejar la selección de ToggleButtons
    private void setupListeners() {
        for (ToggleButton toggleButton : productDataMap.keySet()) {
            toggleButton.setOnClickListener(v -> handleToggleSelection(toggleButton));
        }
    }

    private void handleToggleSelection(ToggleButton selectedButton) {
        if (selectedToggleButton != null) {
            selectedToggleButton.setChecked(false);
        }

        selectedToggleButton = selectedButton.isChecked() ? selectedButton : null;
    }

    // Muestra los productos e imagen asociados al ToggleButton seleccionado
    private void showProducts() {
        productList.clear();
        imageView.setImageResource(0);

        if (selectedToggleButton == null) {
            Toast.makeText(this, R.string.por_favor_selecciona_una_categor_a, Toast.LENGTH_SHORT).show();
            return;
        }

        ProductData data = productDataMap.get(selectedToggleButton);
        if (data != null) {
            imageView.setImageResource(data.imageResId);
            productList.addAll(data.products);
        }

        adapter.notifyDataSetChanged();
    }

    // Limpia las selecciones
    private void clearSelections() {
        if (selectedToggleButton != null) {
            selectedToggleButton.setChecked(false);
            selectedToggleButton = null;
        }

        productList.clear();
        imageView.setImageResource(0);
        adapter.notifyDataSetChanged();

        Toast.makeText(this, R.string.las_selecciones_han_sido_borradas, Toast.LENGTH_SHORT).show();
    }

    private List<Product> getProducts(Product... products) {
        return List.of(products);
    }

    // Clase interna para manejar los datos de productos
    private static class ProductData {
        final int imageResId;
        final List<Product> products;

        ProductData(int imageResId, List<Product> products) {
            this.imageResId = imageResId;
            this.products = products;
        }
    }
}






