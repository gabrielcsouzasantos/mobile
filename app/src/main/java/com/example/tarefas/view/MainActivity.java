package com.example.tarefas.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.tarefas.R;
import com.example.tarefas.adapter.AdapterTarefas;
import com.example.tarefas.model.Tarefa;
import com.example.tarefas.repositorio.TarefaDAO;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private RecyclerView rvTarefas;
    private Button btnCadastrar;

    private ArrayList<Tarefa> tarefas;

    private AdapterTarefas adapterTarefas;

    private TarefaDAO dao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.btnCadastrar = findViewById(R.id.btn_cadastrar);
        this.btnCadastrar.setOnClickListener(this);

        this.rvTarefas = findViewById(R.id.rv_tarefas);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        this.rvTarefas.setLayoutManager(linearLayoutManager);

        this.dao = new TarefaDAO(this);


        this.tarefas = new ArrayList<>();
        this.tarefas = dao.buscarTodasTarefas();

        this.adapterTarefas = new AdapterTarefas(MainActivity.this, this.tarefas);

        this.rvTarefas.setAdapter(this.adapterTarefas);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btn_cadastrar:
                Intent intent = new Intent(this, CadastroActivity.class);

                intent.putExtra("lista_de_tarefas", this.tarefas);

                startActivityForResult(intent, 1);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case 1:

                if (resultCode == RESULT_OK){
                    this.tarefas = dao.buscarTodasTarefas();

                    this.adapterTarefas.atualizarAdapter(this.tarefas);
                }

                break;

        }
    }
}