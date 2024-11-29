package com.test.trabajofinalunidad3;

import android.os.Bundle;
import android.util.Log;
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

    private String TAG = "MainActivity";
    private ToggleButton selectedToggleButton;
    private ImageView imageView;
    private ProductAdapter adapter;
    private List<Product> productList;
    private Map<ToggleButton, ProductData> productDataMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "Actividad principal iniciada.");

        // Inicializar vistas y configurar funcionalidades
        setupUI();
        setupRecyclerView();
        setupProductData();
        setupListeners();
    }

    // Inicializa las vistas principales y configura los botones de acción
    private void setupUI() {
        selectedToggleButton = null;
        imageView = findViewById(R.id.imageView);

        // Configurar acciones para los botones "Buscar" y "Borrar"
        findViewById(R.id.buttonBuscar).setOnClickListener(v -> {
            Log.d(TAG, "Botón 'Buscar' presionado."); // Log cuando se presiona "Buscar"
            showProducts();
        });

        findViewById(R.id.buttonBorrar).setOnClickListener(v -> {
            Log.d(TAG, "Botón 'Borrar' presionado."); // Log cuando se presiona "Borrar"
            clearSelections();
        });
    }

    // Configura el RecyclerView para mostrar la lista de productos
    private void setupRecyclerView() {
        RecyclerView recyclerViewProducts = findViewById(R.id.recyclerViewProducts);
        productList = new ArrayList<>();
        adapter = new ProductAdapter(productList);
        recyclerViewProducts.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewProducts.setAdapter(adapter);
        Log.d(TAG, "RecyclerView configurado correctamente.");
    }

    // Asocia cada ToggleButton con una imagen y lista de productos
    private void setupProductData() {
        productDataMap = new HashMap<>();

        // Categoría Otoño/Invierno
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

        // Categoría Primavera/Verano
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

        // Categoría Perenne
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
        Log.d(TAG, "Datos de productos configurados correctamente.");
    }

    // Configura los listeners para manejar las selecciones de los ToggleButtons
    private void setupListeners() {
        for (ToggleButton toggleButton : productDataMap.keySet()) {
            toggleButton.setOnClickListener(v -> {
                Log.d(TAG, "ToggleButton presionado: " + toggleButton.getText()); // Log del botón seleccionado
                handleToggleSelection(toggleButton);
            });
        }
    }

    // Maneja la selección de un ToggleButton
    private void handleToggleSelection(ToggleButton selectedButton) {
        // Si ya hay un botón seleccionado, lo desmarca
        if (selectedToggleButton != null) {
            selectedToggleButton.setChecked(false);
        }

        // Marca el nuevo botón seleccionado o desmarca si ya estaba seleccionado
        selectedToggleButton = selectedButton.isChecked() ? selectedButton : null;
        Log.d(TAG, "Botón seleccionado: " +
                (selectedToggleButton != null ? selectedToggleButton.getText() : "Ninguno"));
    }

    // Muestra los productos e imagen asociados al ToggleButton seleccionado
    private void showProducts() {
        productList.clear();
        imageView.setImageResource(0);

        if (selectedToggleButton == null) {
            // Muestra un mensaje si no hay ninguna categoría seleccionada
            Toast.makeText(this, R.string.por_favor_selecciona_una_categor_a, Toast.LENGTH_SHORT).show();
            Log.d(TAG, "Ningún botón seleccionado para mostrar productos.");
            return;
        }

        // Obtiene los datos asociados al ToggleButton seleccionado
        ProductData data = productDataMap.get(selectedToggleButton);
        if (data != null) {
            imageView.setImageResource(data.imageResId);
            productList.addAll(data.products);
            Log.d(TAG, "Productos mostrados para categoría: " + selectedToggleButton.getText());
        }

        adapter.notifyDataSetChanged(); // Notifica al adaptador sobre los cambios
    }

    // Limpia las selecciones y la lista de productos
    private void clearSelections() {
        if (selectedToggleButton != null) {
            selectedToggleButton.setChecked(false);
            selectedToggleButton = null;
        }

        productList.clear();
        imageView.setImageResource(0);
        adapter.notifyDataSetChanged(); // Notifica al adaptador sobre los cambios

        Log.d(TAG, "Selecciones y productos limpiados.");
        Toast.makeText(this, R.string.las_selecciones_han_sido_borradas, Toast.LENGTH_SHORT).show();
    }

    // Convierte un arreglo de productos en una lista
    private List<Product> getProducts(Product... products) {
        return List.of(products);
    }

    // Clase interna para manejar los datos asociados a un botón
    private static class ProductData {
        final int imageResId;
        final List<Product> products;

        ProductData(int imageResId, List<Product> products) {
            this.imageResId = imageResId;
            this.products = products;
        }
    }
}








