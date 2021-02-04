package com.example.tarefas.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tarefas.R;
import com.example.tarefas.model.Tarefa;
import com.example.tarefas.repositorio.TarefaDAO;

public class TarefaActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView tvNomeTarefa;
    private TextView tvDescricaoTarefa;

    private EditText etNomeTarefa;
    private EditText etDescricaoTarefa;

    private Button btnAlterar;

    Tarefa t;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarefa);


        if(getIntent().getExtras() != null){
            this.t =  (Tarefa) getIntent().getSerializableExtra("tarefa");

            if(getIntent().getExtras().getString("editar") != null)
            {
                this.iniciarTelaDeTarefaEditavel();
            }
            else
            {
                this.iniciarTelaDeTarefaSelecionada();
            }







        }
    }


    private void iniciarTelaDeTarefaEditavel(){
        this.etNomeTarefa = findViewById(R.id.et_nome_tarefa);
        this.etDescricaoTarefa = findViewById(R.id.et_descricao_tarefa);
        this.btnAlterar = findViewById(R.id.btn_alterar);

        this.etNomeTarefa.setVisibility(View.VISIBLE);
        this.etDescricaoTarefa.setVisibility(View.VISIBLE);
        this.btnAlterar.setVisibility(View.VISIBLE);

        this.btnAlterar.setOnClickListener(this);

        this.etNomeTarefa.setText(this.t.getNome());
        this.etDescricaoTarefa.setText(this.t.getDescricao());


    }

    private void iniciarTelaDeTarefaSelecionada(){
        this.tvNomeTarefa = findViewById(R.id.tv_nome_tarefa);
        this.tvDescricaoTarefa = findViewById(R.id.tv_descricao_tarefa);

        this.tvNomeTarefa.setVisibility(View.VISIBLE);
        this.tvDescricaoTarefa.setVisibility(View.VISIBLE);

        this.tvNomeTarefa.setText(t.getNome());
        this.tvDescricaoTarefa.setText(t.getDescricao());

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_alterar:
                if(this.etNomeTarefa.getText() != null && this.etDescricaoTarefa.getText() != null){
                    this.t.setNome(this.etNomeTarefa.getText().toString());
                    this.t.setDescricao(this.etDescricaoTarefa.getText().toString());

                    TarefaDAO dao = new TarefaDAO(this);

                    dao.salvarTarefa(t);

                    Toast.makeText(this, "Tarefa Alterada com Sucesso!", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);

                    finish();
                }
                else {
                    Toast.makeText(this, "Preencha os campos corretamente para alterar!", Toast.LENGTH_SHORT).show();

                }

                break;
        }
    }
}