package com.test.trabajofinalunidad3;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

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

        setupUI();

        setupRecyclerView();

        setupProductData();

        setupListeners();

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("Principal"));
        tabLayout.addTab(tabLayout.newTab().setText("Próximamente"));

        // Configurar listener para las pestañas
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 1) {
                    Snackbar.make(findViewById(R.id.rootLayout), "Próximamente", Snackbar.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                // No se requiere acción
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // No se requiere acción
            }
        });

        // Encuentra el TextView en el que se hará clic para mostrar el menú emergente
        TextView textView = findViewById(R.id.textView);
        textView.setOnClickListener(this::showPopUpMenu);
    }

    // Método para mostrar el menú emergente al pulsar el TextView
    public void showPopUpMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        MenuInflater menuInflater = popupMenu.getMenuInflater();
        menuInflater.inflate(R.menu.context_menu, popupMenu.getMenu());

        // Manejar la acción de los ítems del menú
        popupMenu.setOnMenuItemClickListener(item -> {
            TextView tv = (TextView) view;

            // Cambiar el color según la opción seleccionada
            if (item.getItemId() == R.id.amarillo) {
                tv.setTextColor(Color.YELLOW);
                Toast.makeText(this, "Color Amarillo seleccionado", Toast.LENGTH_SHORT).show();
            } if (item.getItemId() == R.id.violeta) {
                tv.setTextColor(Color.MAGENTA);
                Toast.makeText(this, "Color Violeta seleccionado", Toast.LENGTH_SHORT).show();
            }
            else if(item.getItemId()== R.id.blanco){
                tv.setTextColor(Color.WHITE);
                Toast.makeText(this, "Color Blanco seleccionado", Toast.LENGTH_SHORT).show();
            }

            return true;
        });

        // Mostrar el menú emergente
        popupMenu.show();
    }

    private void setupUI() {
        selectedToggleButton = null;
        imageView = findViewById(R.id.imageView);

        findViewById(R.id.buttonBuscar).setOnClickListener(v -> {
            showProducts();
            showPopUpMenu(v);
        });
        findViewById(R.id.buttonBorrar).setOnClickListener(v -> clearSelections());
    }

    private void setupRecyclerView() {
        RecyclerView recyclerViewProducts = findViewById(R.id.recyclerViewProducts);
        productList = new ArrayList<>();
        adapter = new ProductAdapter(productList);
        recyclerViewProducts.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewProducts.setAdapter(adapter);
    }

    @SuppressLint("FindViewByIdCast")
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

    private void showProducts() {
        productList.clear();
        imageView.setImageResource(0);


        if (selectedToggleButton == null) {
            Snackbar.make(findViewById(R.id.rootLayout), R.string.por_favor_selecciona_una_categor_a, Snackbar.LENGTH_SHORT).show();
            return;
        }

        ProductData data = productDataMap.get(selectedToggleButton);
        if (data != null) {
            imageView.setImageResource(data.imageResId);
            productList.addAll(data.products);
        }

        adapter.notifyDataSetChanged();
    }

    @SuppressLint("NotifyDataSetChanged")
    private void clearSelections() {
        if (selectedToggleButton != null) {
            selectedToggleButton.setChecked(false);
            selectedToggleButton = null;
        }

        productList.clear();
        imageView.setImageResource(0);
        adapter.notifyDataSetChanged();

        Snackbar.make(findViewById(R.id.rootLayout), R.string.las_selecciones_han_sido_borradas, Snackbar.LENGTH_SHORT).show();
    }

    private List<Product> getProducts(Product... products) {
        return List.of(products);
    }

    private static class ProductData {
        final int imageResId;
        final List<Product> products;

        ProductData(int imageResId, List<Product> products) {
            this.imageResId = imageResId;
            this.products = products;
        }
    }
}








