package com.example.cadastroproduto;


import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import android.app.AlertDialog;

public class MainActivity extends AppCompatActivity {

    private EditText editNome;
    private EditText editPreco;
    private Button btnCadastrar;
    private ListView listaProdutos;

    private ProdutoDbHelper dbHelper;

    private ArrayList<String> produtos;
    private ArrayAdapter<String> adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Referências dos componentes da tela
        editNome = findViewById(R.id.editNome);
        editPreco = findViewById(R.id.editPreco);
        btnCadastrar = findViewById(R.id.btnCadastrar);
        listaProdutos = findViewById(R.id.listaProdutos);

        // Inicializa o banco de dados
        dbHelper = new ProdutoDbHelper(this);

        // Inicializa a lista
        produtos = new ArrayList<>();

        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                produtos
        );

        listaProdutos.setAdapter(adapter);

        // Carrega os produtos já cadastrados
        carregarProdutos();

        // Ação do botão Cadastrar
        btnCadastrar.setOnClickListener(v -> {

            String nome = editNome.getText().toString().trim();
            String precoTexto = editPreco.getText().toString().trim();

            // Validação do nome
            if (nome.length() < 3) {
               mostrarErro("Nome inválido, minimo 3 caracteres.");

                return;
            }

            if (precoTexto.isEmpty()) {
                mostrarErro("Informe o preço do produto.");

                return;
            }

            double preco;

            try {
                // Permite utilizar vírgula ou ponto
                precoTexto = precoTexto.replace(",", ".");

                preco = Double.parseDouble(precoTexto);

            } catch (NumberFormatException e) {

               mostrarErro("Preço invalido, digite um numero valido.");

                return;
            }

            // Validação do preço
            if (preco <= 2) {
                mostrarErro("Preço invalido, informe um valor valido.");

                return;
            }

            // Cria o produto
            Produto produto = new Produto(nome, preco);

            // Insere o produto no banco de dados
            dbHelper.inserirProduto(produto);

            // Atualiza a lista exibida na tela
            carregarProdutos();

            editNome.setText("");
            editPreco.setText("");

            Toast.makeText(
                    MainActivity.this,
                    "Produto cadastrado com sucesso!",
                    Toast.LENGTH_SHORT
            ).show();
        });
    }

    private void carregarProdutos() {

        produtos.clear();

        produtos.addAll(dbHelper.listarProdutos());

        adapter.notifyDataSetChanged();
    }

    private void mostrarErro(String mensagem) {
       new AlertDialog.Builder(this)
               .setTitle("Erro")
               .setMessage(mensagem)
               .setPositiveButton("OK", null)
               .show();

    }


}