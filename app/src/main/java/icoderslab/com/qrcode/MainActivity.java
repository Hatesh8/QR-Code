package icoderslab.com.qrcode;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    static Button scanQRcode;
    static TextView displayQRcode;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scanQRcode=(Button)findViewById(R.id.scanQRcode);
        displayQRcode=(TextView)findViewById(R.id.displayQRcode);
        scanQRcode.setOnClickListener(this);

    }

    @Override
    public void onClick(View v)
    {
        IntentIntegrator integrator=new IntentIntegrator(this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
        integrator.setPrompt("Scan");
        integrator.setCameraId(0);
        integrator.setBeepEnabled(false);
        integrator.setBarcodeImageEnabled(false);
        integrator.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result=IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if(result!=null)
        {
            if(result.getContents()==null)
            {

                Toast.makeText(getApplicationContext(),"Scanning cancelled",Toast.LENGTH_SHORT).show();
            }
            else
            {
                displayQRcode.setText(result.getContents());
            }
        }
        else
        {
            super.onActivityResult(requestCode,resultCode,data);
        }
    }
}
