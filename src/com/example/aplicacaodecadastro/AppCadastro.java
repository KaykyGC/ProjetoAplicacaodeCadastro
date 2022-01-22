package com.example.aplicacaodecadastro;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.*;
import android.view.*;
import android.app.*;

public class AppCadastro extends Activity {
	
	Registro pri,reg,ult,aux;
	
	EditText ednome,edprof,edidade;
	
	int numreg,pos;
	
	void CarregaTelaPrincipal() {
		setContentView(R.layout.activity_app_cadastro);
		Button btcadpess = (Button) findViewById(R.id.btcadastrarpessoasprincipal);
		Button btlistapess = (Button) findViewById(R.id.btlistarpessoasprincipal);
		btcadpess.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				CarregaTelaCadastro();
			}
		});
		btlistapess.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				CarregaListaPessoas();
			}
		});
	}

	void CarregaTelaCadastro() {
		setContentView(R.layout.cadastro);
		Button btcadastrar = (Button) findViewById(R.cadastro.btcadastrar);
		Button btcancelar = (Button) findViewById(R.cadastro.btcancelar);
		
		btcadastrar.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				try {
					reg = new Registro();
					ednome = (EditText) findViewById(R.cadastro.idnome);
					edprof = (EditText) findViewById(R.cadastro.idprofissao);
					edidade = (EditText) findViewById(R.cadastro.ididade);
					reg.nome = ednome.getText().toString();
					reg.profissao = edprof.getText().toString();
					reg.idade = edidade.getText().toString();
					if (pri == null)
						pri = reg;
					reg.Ant = ult;
					if (ult == null)
						ult = reg;
					else {
						ult.Prox = reg;
						ult = reg;
					}
					numreg++;
					showMessage("Cadastro efetuado com sucesso", "Aviso");
					CarregaTelaPrincipal();
					
				} catch (Exception e) {
					showMessage("Erro ao cadastrar", "Erro");
				}
			}
		});
		btcancelar.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				CarregaTelaPrincipal();
			}
		});
	}

	void CarregaListaPessoas() {
		if (numreg == 0) {
			showMessage("Nenhum registro cadastrado", "Aviso");
			CarregaTelaPrincipal();
			return;
		}
		setContentView(R.layout.listacadastrados);
		pos = 1;
		aux = pri;
		TextView txtnome = (TextView) findViewById(R.id.txtnomelista);
		TextView txtidade = (TextView) findViewById(R.id.txtidadelista);
		TextView txtprofissao = (TextView) findViewById(R.id.txtprofissaolista);
		Button btmenu = (Button) findViewById(R.lista.btmenu);
		Button btavancar = (Button) findViewById(R.lista.btavancar);
		Button btvoltar = (Button) findViewById(R.lista.btvoltar);
		txtnome.setText(aux.nome);
		txtidade.setText(aux.idade);
		txtprofissao.setText(aux.profissao);
		btmenu.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				CarregaTelaPrincipal();
			}
		});
		btvoltar.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				if (pos == 1)
					return;
				pos--;
				aux = aux.Ant;
				TextView txtnome = (TextView) findViewById(R.id.txtnomelista);
				TextView txtidade = (TextView) findViewById(R.id.txtidadelista);
				TextView txtprofissao = (TextView) findViewById(R.id.txtprofissaolista);
				txtnome.setText(aux.nome);
				txtidade.setText(aux.idade);
				txtprofissao.setText(aux.profissao);
			}
		});
		btavancar.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				if (pos == numreg)
					return;
				pos++;
				aux = aux.Prox;
				TextView txtnome = (TextView) findViewById(R.id.txtnomelista);
				TextView txtidade = (TextView) findViewById(R.id.txtidadelista);
				TextView txtprofissao = (TextView) findViewById(R.id.txtprofissaolista);
				txtnome.setText(aux.nome);
				txtidade.setText(aux.idade);
				txtprofissao.setText(aux.profissao);
			}
		});
	}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_cadastro);
        numreg=0;
        pri=ult=null;
        CarregaTelaPrincipal();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.app_cadastro, menu);
        return true;
    }

	public void showMessage(String Caption, String Title) {
		AlertDialog.Builder dialogo = new AlertDialog.Builder(AppCadastro.this);
		dialogo.setTitle(Title);
		dialogo.setMessage(Caption);
		dialogo.setNeutralButton("OK", null);
		dialogo.show();
	}
    			
    }
    

