package com.test.trabajofinalunidad3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private final List<Product> productList;

    // Constructor que inicializa la lista de productos
    public ProductAdapter(List<Product> productList) {
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        // Obtiene el producto en la posición actual
        Product product = productList.get(position);

        // Establece los datos del producto en las vistas correspondientes
        holder.bind(product);
    }

    @Override
    public int getItemCount() {
        // Retorna la cantidad de elementos en la lista
        return productList.size();
    }

    // Clase interna para el ViewHolder que mantiene referencias a las vistas del diseño del producto
    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        private final TextView productName;
        private final ImageView productImage;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            // Inicializa las vistas del diseño del producto
            productName = itemView.findViewById(R.id.productName);
            productImage = itemView.findViewById(R.id.productImage);
        }

        // Método para enlazar los datos del producto con las vistas
        public void bind(Product product) {
            productName.setText(product.getName());
            productImage.setImageResource(product.getImageResId());
        }
    }
}



