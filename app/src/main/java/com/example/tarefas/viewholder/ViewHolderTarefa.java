package com.example.tarefas.viewholder;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tarefas.R;

public class ViewHolderTarefa extends RecyclerView.ViewHolder {

    public LinearLayout linearLayoutTarefa;
    public TextView tvTarefa;
    public ImageView imageViewEditar;
    public ImageView imageViewExcluir;
    public CheckBox appCompatCheckBoxFeito;


    public ViewHolderTarefa(@NonNull View itemView) {
        super(itemView);

        this.linearLayoutTarefa = itemView.findViewById(R.id.linear_layout_prato);
        this.tvTarefa = itemView.findViewById(R.id.tv_tarefa);
        this.imageViewEditar = itemView.findViewById(R.id.img_view_editar_tarefa);
        this.imageViewExcluir = itemView.findViewById(R.id.img_view_excluir_tarefa);
        this.appCompatCheckBoxFeito = itemView.findViewById(R.id.checkbox_feito);
    }
}
