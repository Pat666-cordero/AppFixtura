package com.example.fixtura;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.fixtura.models.Usuario;
import com.example.fixtura.models.daoUsuario;
import com.example.fixtura.ui.desafio.ConsultarDesafioFragment;
import com.example.fixtura.ui.home.HomeFragment;
import com.example.fixtura.ui.jugador.ConsultarJugadorFragment;

import java.util.concurrent.TimeUnit;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    EditText user, pass;
    Button btnEntrar, btnRegistrar;
    daoUsuario dao;
    @Override
    protected void onCreate(@Nullable Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.login_main);

        user = (EditText)findViewById(R.id.user);
        pass = (EditText)findViewById(R.id.pass);
        btnEntrar = (Button)findViewById(R.id.btnEntrar);
        btnRegistrar = (Button)findViewById(R.id.btnRegistrar);
        btnEntrar.setOnClickListener(this);
        btnRegistrar.setOnClickListener(this);
        dao = new daoUsuario(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnEntrar:
                String u = user.getText().toString();
                String p = pass.getText().toString();
                if(u.equals("")&&p.equals("")) {
                    Toast.makeText(this, "ERROR: Campos vacios", Toast.LENGTH_LONG).show();
                }else if(dao.login(u,p)==1){
                    Usuario ux = dao.getUsUario(u,p);
                    Toast.makeText(this,"Datos correctos", Toast.LENGTH_LONG).show();
                    Intent i2 = new Intent(LoginActivity.this, MainActivity.class);
                    //i2.putExtra("id", ux.getId());
                    startActivity(i2);
                }
                break;

            case R.id.btnRegistrar:
                Intent i = new Intent(LoginActivity.this,RegistroActivity.class);
                startActivity(i);
                break;
        }
    }
}
