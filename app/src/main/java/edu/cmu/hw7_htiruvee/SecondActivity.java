package edu.cmu.hw7_htiruvee;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

public class SecondActivity extends AppCompatActivity {
    TextView txtView;
    Button btn;
    ImageView myImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        txtView = (TextView) findViewById(R.id.txtContent);
        btn = (Button) findViewById(R.id.button);
        myImageView = (ImageView) findViewById(R.id.imgview);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap myBitmap = BitmapFactory.decodeResource(
                        getApplicationContext().getResources(),
                        R.raw.qr_code);
                myImageView.setImageBitmap(myBitmap);
                BarcodeDetector detector = new BarcodeDetector.Builder(getApplicationContext())
                                .setBarcodeFormats(Barcode.DATA_MATRIX | Barcode.QR_CODE)
                                .build();

                if(!detector.isOperational()){
                    txtView.setText("Could not set up the detector!");
                    return;
                }

                Frame frame = new Frame.Builder().setBitmap(myBitmap).build();
                SparseArray<Barcode> barcodes = detector.detect(frame);

                Barcode thisCode = barcodes.valueAt(0);
                txtView.setText(thisCode.displayValue);
                Log.d("detectedCode: ", thisCode.rawValue);
                Log.d("displayValue: ", thisCode.displayValue);
            }
        });

    }


}
