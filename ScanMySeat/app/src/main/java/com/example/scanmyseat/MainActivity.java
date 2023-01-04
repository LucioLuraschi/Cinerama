package com.example.scanmyseat;


import static android.graphics.ImageFormat.YUV_420_888;
import static android.graphics.ImageFormat.YUV_422_888;
import static android.graphics.ImageFormat.YUV_444_888;
import static android.view.Surface.ROTATION_270;
import static android.view.Surface.ROTATION_90;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraCharacteristics;
import android.os.Bundle;
import android.util.Size;
import android.view.Surface;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.ImageProxy;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.PlanarYUVLuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.multi.qrcode.QRCodeMultiReader;

import java.nio.ByteBuffer;
import java.util.concurrent.ExecutionException;
import android.view.View.OnClickListener;

public class MainActivity extends AppCompatActivity {

    private PreviewView previewView;
    private ListenableFuture<ProcessCameraProvider> cameraProviderListenableFuture;
    private TextView textView;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        previewView = findViewById(R.id.cameraPreview);
        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button);
        button.setVisibility(View.INVISIBLE);

        //A pop-up is displayed and asks for the permissions of using camera
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){
            initCamera();
        }else{
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CAMERA},101);
        }
        cameraProviderListenableFuture = ProcessCameraProvider.getInstance(this);
    }

    //Initialize the camera
    private void initCamera(){
        cameraProviderListenableFuture = ProcessCameraProvider.getInstance(MainActivity.this);
        cameraProviderListenableFuture.addListener(new Runnable() {
            @Override
            public void run() {
                try {
                    ProcessCameraProvider cameraProvider = cameraProviderListenableFuture.get();
                    bindImageAnalysis(cameraProvider);
                } catch (ExecutionException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, ContextCompat.getMainExecutor(MainActivity.this));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            initCamera();
        }else {
            Toast.makeText(MainActivity.this,"Permissions denied",Toast.LENGTH_SHORT).show();
        }
    }

    /*
    We initialize the camera process (launch camera, analyze the image viewed in the surface, end of the instance)
    We initialize the settings of the camera (horizontal or vertical view)
    We analyze then the QRCode
     */

    private void bindImageAnalysis(ProcessCameraProvider processCameraProvider){
        Preview preview = new Preview.Builder().build();
        CameraSelector cameraSelector = new CameraSelector.Builder().requireLensFacing(CameraSelector.LENS_FACING_BACK).build();
        preview.setSurfaceProvider(previewView.getSurfaceProvider());

        ImageAnalysis imageAnalysis =new ImageAnalysis.Builder().setTargetResolution(new Size(1280,720))
                .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST).build();

        imageAnalysis.setAnalyzer(ContextCompat.getMainExecutor(MainActivity.this), new ImageAnalysis.Analyzer() {
            @Override
            public void analyze(@NonNull ImageProxy image) {
                if (image.getFormat() == YUV_420_888 || image.getFormat() == YUV_422_888 || image.getFormat() == YUV_444_888) {
                    ByteBuffer byteBuffer = image.getPlanes()[0].getBuffer();
                    byte[] imageData = new byte[byteBuffer.capacity()];
                    byteBuffer.get(imageData);

                    PlanarYUVLuminanceSource source = new PlanarYUVLuminanceSource(
                            imageData,
                            image.getWidth(), image.getHeight(),
                            0, 0,
                            image.getWidth(), image.getHeight(),
                            false
                    );

                    BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(source));

                    try {
                        Result result = new QRCodeMultiReader().decode(binaryBitmap);
                        String id = result.getText();
                        //listener.onQRCodeFound(result.getText());
                        button.setVisibility(View.VISIBLE);
                        Intent intent = new Intent(getApplicationContext(), ResumeQrCode.class);
                        intent.putExtra("ticket", id);
                        button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                startActivity(intent);
                            }
                        });
                    } catch (FormatException | ChecksumException | NotFoundException e) {
                        //listener.qrCodeNotFound();
                        textView.setText("Scan non réalisé");
                    }
                }
                image.close();

            }

            /*            @Override
            public void analyze(@NonNull ImageProxy image) {
                Image mediaImage = image.getImage();
                if (mediaImage==null){
                    InputImage image2 = InputImage.fromMediaImage(mediaImage,image.getImageInfo().getRotationDegrees());
                    BarcodeScanner scanner = BarcodeScanning.getClient();

                    Task<List<Barcode>> results = scanner.process(image2);

                    results.addOnSuccessListener(new OnSuccessListener<List<Barcode>>() {
                        @Override
                        public void onSuccess(List<Barcode> barcodes) {
                            for (Barcode barcode:barcodes){
                                final String getValue = barcode.getRawValue();
                                textView.setText("Scan réalisé");
                            }

                            image.close();
                            mediaImage.close();
                        }
                    });
                }
            }
*/

        });
        processCameraProvider.bindToLifecycle(this,cameraSelector,imageAnalysis,preview);

        /*
        Preview preview = new Preview.Builder().build();
        CameraSelector cameraSelector = new CameraSelector.Builder().requireLensFacing(CameraSelector.LENS_FACING_BACK).build();
        preview.setSurfaceProvider(previewView.getSurfaceProvider());
        processCameraProvider.bindToLifecycle(this,cameraSelector,imageAnalysis,preview);
*/

    }
}

