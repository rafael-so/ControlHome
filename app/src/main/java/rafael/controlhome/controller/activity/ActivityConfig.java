package rafael.controlhome.controller.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import rafael.controlhome.R;
import rafael.controlhome.modell.dao.DaoAddressIP;
import rafael.controlhome.connection.mqtt.ConnectionMQTT;
import rafael.controlhome.modell.ModelAddressIP;

public class ActivityConfig  extends AppCompatActivity {

    ModelAddressIP modelAddressIP = new ModelAddressIP();
    DaoAddressIP daoAddressIP = null;
    ConnectionMQTT connectionMQTT;

    public EditText editText = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        daoAddressIP = new DaoAddressIP(this.getApplicationContext(), modelAddressIP);
        clickListenerBotaoSalvar();
        editText = findViewById(R.id.campoIP);
        print();
    }

    public void setPojoEnderecoIP(){
        modelAddressIP.setIP(editText.getText().toString());
        daoAddressIP.alterar(modelAddressIP);

    }

    public void clickListenerBotaoSalvar(){
        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setPojoEnderecoIP();
                ConnectionMQTT.connection(view.getContext().getApplicationContext());
                onBackPressed();
                finish();
            }
        };
        findViewById(R.id.botaoSalvar).setOnClickListener(clickListener);
    }

    public void print(){
        editText.setText(daoAddressIP.consultarUltimoIP());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                onBackPressed();
                finish();
                break;

            default:
                break;
        }
        return true;
    }
}
