package com.example.tarefas.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tarefas.R;
import com.example.tarefas.model.Tarefa;
import com.example.tarefas.repositorio.TarefaDAO;
import com.example.tarefas.view.TarefaActivity;
import com.example.tarefas.viewholder.ViewHolderTarefa;

import java.util.ArrayList;

public class AdapterTarefas extends RecyclerView.Adapter<ViewHolderTarefa> {

    private Activity activity;
    private LayoutInflater layoutInflater;
    private ArrayList<Tarefa> listaDeTarefas;

    public AdapterTarefas(Activity activity, ArrayList<Tarefa> tarefas){
        this.activity = activity;
        this.layoutInflater = LayoutInflater.from(this.activity.getBaseContext());
        this.listaDeTarefas = tarefas;
    }

    @NonNull
    @Override
    public ViewHolderTarefa onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = this.layoutInflater.inflate(R.layout.item_tarefa, parent, false);


        return new ViewHolderTarefa(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderTarefa holder, int position) {

        Tarefa t = this.listaDeTarefas.get(position);

        if(t.getFeito()==1){
            holder.appCompatCheckBoxFeito.setChecked(true);
        }

        holder.tvTarefa.setText(t.getNome());


        holder.tvTarefa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity.getBaseContext(), TarefaActivity.class);

                intent.putExtra("tarefa", t);

                activity.startActivity(intent);
            }
        });

        holder.imageViewEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity.getBaseContext(), TarefaActivity.class);
                intent.putExtra("tarefa", t);
                intent.putExtra("editar", "editar");

                activity.startActivity(intent);
                activity.finish();

            }
        });

        holder.appCompatCheckBoxFeito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TarefaDAO tarefaDAO = new TarefaDAO(activity.getBaseContext());

                if(holder.appCompatCheckBoxFeito.isChecked()){
                    tarefaDAO.realizarTarefa(t);
                }

                else{
                    tarefaDAO.desfazerTarefa(t);
                }
            }
        });

        holder.imageViewExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TarefaDAO dao = new TarefaDAO(activity.getBaseContext());
                dao.apagarTarefa(t);

                listaDeTarefas.remove(t);

                notifyDataSetChanged();

                Toast.makeText(activity.getBaseContext(), "Tarefa excluída com sucesso!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.listaDeTarefas.size();
    }

    public void atualizarAdapter(ArrayList<Tarefa> tarefas){
        this.listaDeTarefas.clear();

        this.listaDeTarefas.addAll(tarefas);

        notifyDataSetChanged();
    }



}
