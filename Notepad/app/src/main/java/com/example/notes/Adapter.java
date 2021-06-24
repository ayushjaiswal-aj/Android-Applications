package com.example.notes;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    Context context;
    List<Products> products;
    LayoutInflater inflater;

    Adapter (Context context, List<Products> products ){
        this.context = context;
        this.products = products;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.note_card, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Products product = products.get(position);
        holder.title.setText(product.getTitle());
        holder.note.setText(product.getNote());
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView title, note;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            title = itemView.findViewById(R.id.title);
            note = itemView.findViewById(R.id.note);
        }

        @Override
        public void onClick(View v) {
            int position = this.getAdapterPosition();
            Products product = products.get(position);
            String id = Integer.toString(product.getId());
            String title = product.getTitle();
            String note = product.getNote();

            Intent intent = new Intent(context, EditNote.class);
            intent.putExtra("Rid", id);
            intent.putExtra("Rtitle", title);
            intent.putExtra("Rnote", note);
            context.startActivity(intent);

        }
    }
}
