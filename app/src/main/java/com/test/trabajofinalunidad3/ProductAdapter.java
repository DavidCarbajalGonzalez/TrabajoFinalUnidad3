package com.test.trabajofinalunidad3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

// Adaptador para el RecyclerView que muestra los productos
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
        // Obtiene el producto en la posición actual y lo enlaza al ViewHolder
        holder.bind(productList.get(position));
    }

    @Override
    public int getItemCount() {
        // Retorna la cantidad de elementos en la lista
        return productList.size();
    }

    // Clase interna que representa un ViewHolder para un producto
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




