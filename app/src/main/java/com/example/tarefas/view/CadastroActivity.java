package com.example.tarefas.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tarefas.R;
import com.example.tarefas.model.Tarefa;
import com.example.tarefas.repositorio.TarefaDAO;

import java.util.ArrayList;

public class CadastroActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText etNome;
    private EditText etDescricao;
    private Button btnAdicionar;

    private ArrayList<Tarefa> tarefas;

    private TarefaDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        this.etNome = findViewById(R.id.et_nome);
        this.etDescricao = findViewById(R.id.et_descricao);
        this.btnAdicionar = findViewById(R.id.btn_adicionar);

        this.btnAdicionar.setOnClickListener(this);

        if (getIntent().getExtras() != null){
            this.tarefas = (ArrayList<Tarefa>) getIntent().getSerializableExtra("lista_de_tarefas");
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_adicionar:

                this.adicioanrTarefa();
                break;
        }
    }


    private void adicioanrTarefa(){
        String nome = this.etNome.getText().toString();
        String descricao = this.etDescricao.getText().toString();

        if (!nome.equals("") && !descricao.equals("")){
            Tarefa tarefa = new Tarefa(nome, descricao);

            this.tarefas.add(tarefa);

            this.dao = new TarefaDAO(this);
            this.dao.salvarTarefa(tarefa);

            Toast.makeText(this, "Tarefa Cadastrada com Sucesso!", Toast.LENGTH_SHORT).show();


            Intent returnIntent = new Intent(this, MainActivity.class);


            setResult(RESULT_OK, returnIntent);
            finish();
        }
        else {
            Toast.makeText(this, "Preencha corretamente os campos para cadastrar!", Toast.LENGTH_SHORT).show();
        }
    }
}