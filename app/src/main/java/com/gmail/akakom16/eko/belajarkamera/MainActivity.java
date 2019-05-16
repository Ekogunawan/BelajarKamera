package com.gmail.akakom16.eko.belajarkamera;

//TODO 1 java : yaitu program di bawah adalah package dari class java sendiri yang bisas di panggil kapanpun
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;

//TODO 2 java: Yaitu program di bawah adalah class MainActivity dari class java yang di guanakan dengan extend ke class turunan dengan nama appCompatActivity
public class MainActivity extends AppCompatActivity
{
    // TODO 3 java : Yaitu program di bawah adalah mendeklarasikan Variabel yang sifatnya pubic yang bisa di akases di bebagai class di dalam java
    ImageView imageView;

    @Override
    //TODO 4 java : Yaitu program di bawah adalah memdeklarasikan method onCreate yang isisnya akan menyimpan state
    protected void onCreate(Bundle savedInstanceState)
    {
        //TODO 5 java : Yaitu program di bawah adalah untuk memanggil variabel dari class xml
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnCamera = (Button) findViewById(R.id.btnCamera);
        imageView = (ImageView) findViewById(R.id.imageView);
        //TODO 6 java : Yaitu program di bawah adalah ketika button di klikk akan menghasilkan intent atau berpindah halaman ke CAMERA .
        btnCamera.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(takePictureIntent,0);
            }
        });


    }
    @Override
    //TODO 7 java : Yaitu program di bawah adalah method untuk menampilkan data ke  dalam activity
    protected void onActivityResult(int requestCode,int resultCode, Intent data)
    {
        //TODO 8 java : Yaitu program di bawah adalah memanggil variabel dari imageView yang di dalam Bitmap dan akan di tampilkan di main layar activity
        super.onActivityResult(requestCode,resultCode,data);
        Bitmap imageBitmap = (Bitmap) data.getExtras().get("data");
        imageView.setImageBitmap(imageBitmap);

    }
    //TODO 9 java : Yaitu program di bawah adalah mendeklarasikan method untuk save Image ke dalam External hp
    private void SavaImage(Bitmap finalBitmap){
        //TODO 10 java : Yaitu program di bawah adalah mendeklarasikan variabel dengan nilainya yaitu dari get External storage directory dan di simpan di File dengan nama save image dan untuk formatnya yaitu JPG
        String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(root+"/save_images");
        myDir.mkdir();
        Random generator = new Random();
        int n = 10000;
        n = generator.nextInt();
        String fname = "Image-"+ n +".jpg";
        File file = new File(myDir, fname);
        //TODO 11 java : Yaitu program di bawah adalah membuat kondisi di mana yaitu ada exists dan menhapus gambar.
        if(file.exists()) file.delete();
        //TODO 12 java : Yaitu program di bawah adalah untuk kondisi try jika di save makan akan menhasilkan output dan formatnya di compres dengan format JPEG
        try{
            FileOutputStream out = new FileOutputStream(file);
            finalBitmap.compress(Bitmap.CompressFormat.JPEG,90,out);
            out.flush();
            out.close();

        }
        //TODO 13 java : Yaitu program di bawah jika kondisi catch atau salah makan akan menampilkan exception e dan di tampilan
        catch (Exception e){
            e.printStackTrace();
        }
    }

}
