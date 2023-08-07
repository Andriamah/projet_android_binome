package com.example.projetm1.view;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.projetm1.R;
import com.example.projetm1.controller.ContenuController;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddContenuActivity extends AppCompatActivity {
    private int editTextIdClient;
    private int editTextIdZone;
    private EditText editTextCommentaire;
    private String editTextPhoto;
    private String editTextVideo;
    private static final int REQUEST_CODE_GALLERY = 100;
    private static final int REQUEST_CODE_CAMERA = 101;
    private static final int REQUEST_CAMERA_PERMISSION = 100;
    private static final int REQUEST_CODE_PICK_VIDEO = 200;
    private ImageView imagePaysage;
    private Uri imageUri;
    private ImageButton imgbtnCamera;
    private ImageButton imgbtnVideo;
    private ImageButton imgbtnGalerie;
    private VideoView videopaysage;
    private String photoBase64 = "_";
    private  String videoBase64 = "_";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contenu);
        initActivity();

        Button retour = findViewById(R.id.btnRetour); // Remplacez "button" par l'ID de votre bouton
        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Créez un Intent pour démarrer la nouvelle activité
                Intent intent = new Intent(AddContenuActivity.this, UpdateProfilActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initActivity(){
        // c'est la session et l'id_zone
        editTextIdClient = 1;
        editTextIdZone = 1;
        editTextCommentaire = findViewById(R.id.textEdithCommentaire);
        editTextPhoto = "photo";
        editTextVideo = "video";

        imgbtnCamera = findViewById(R.id.IMGBtnCamera);
        imgbtnGalerie = findViewById(R.id.IMGBtnGalerie);
        imagePaysage = findViewById(R.id.imgPaysage);
        imgbtnVideo = findViewById(R.id.IMGBtnVideo);
        videopaysage = findViewById(R.id.videopaysage);

        createOnclickBtnOuvrirGalerie();
        createOnClickBtnOuvrirCamera();
        checkCameraPermissionAndOpenCamera();
        createOnClickBtnOuvrirVideo();
        createOnclickBtnAjouterContenu();
    }

    private void createOnclickBtnOuvrirGalerie()
    {
        imgbtnGalerie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AddContenuActivity.this, "Ouvrir Galerie", Toast.LENGTH_SHORT).show();
                openGallery();
            }
        });
    }

    private void createOnclickBtnAjouterContenu(){
        Button buttonAjouter = findViewById(R.id.btnPublier);
        buttonAjouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AddContenuActivity.this, "commnecer", Toast.LENGTH_SHORT).show();
                ajouterContenu();
            }
        });
    }
    private void openGallery() {
        // Intent pour ouvrir la galerie
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, REQUEST_CODE_GALLERY);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (imageUri != null) {
            outState.putString("imageUri", imageUri.toString());
        }
    }
    private void ajouterContenu() {
        // c'est la session et l'id_zone
        int id_client = 1;
        int id_zone = 1;
        String commentaire = editTextCommentaire.getText().toString();
        String photo = photoBase64;
        String video = videoBase64;

        ContenuController contenuController = new ContenuController();
        Date currentDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = sdf.format(currentDate);
        contenuController.addContenu(id_client, id_zone, commentaire, photo, video, formattedDate, new ContenuController.AddContenuCallback() {
            @Override
            public void onAddContenuSuccess(String successMessage) {
                // Traitement en cas de succès de l'ajout du contenu
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(AddContenuActivity.this, "Contenu ajouté", Toast.LENGTH_SHORT).show();
                    }
                });
            }
            @Override
            public void onAddContenuFailure(String errorMessage) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(AddContenuActivity.this, "Désolé, il y a une erreur", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null) {
            Log.d("TAG", "*-*-*---*-*-*-*-*-*-*-*-*activiy Galery--*-*-**-*-*-*-*-*-*");
            Uri selectedImageUri = data.getData();
            Bitmap imageBitmap = uriToBitmap(selectedImageUri);
            int desiredWidth = 600;
            int desiredHeight = 400;
            Bitmap compressedBitmap = Bitmap.createScaledBitmap(imageBitmap, desiredWidth, desiredHeight, true);
            String base64Image = bitmapToBase64(imageBitmap);
            photoBase64 = base64Image;
            Log.d("TAG_IMG", "base64************* : "+base64Image);
            // Décodez la chaîne Base64 en Bitmap (facultatif, vous pouvez sauter cette étape)
            Bitmap decodedBitmap = decodeBase64ToBitmap(base64Image);
            // Afficher l'image dans votre application (par exemple, dans un ImageView)
            imagePaysage = findViewById(R.id.imgPaysage);
            try {
               // imagePaysage.setImageURI(selectedImageUri);
                imagePaysage = findViewById(R.id.imgPaysage);
                imagePaysage.setImageBitmap(decodedBitmap);

            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, "Erreur lors du chargement de l'image", Toast.LENGTH_SHORT).show();
            }
        }else if (requestCode == REQUEST_CODE_CAMERA && resultCode == RESULT_OK) {
            Log.d("TAG", "*-*-*-*-*-*--*-*Activity camere -*-*-**-*-*--*-**-*-*-*- ");
            // L'image a été prise avec succès, vous pouvez maintenant la convertir en Base64
            if (data != null && data.getExtras() != null) {
                Bitmap imageBitmap = (Bitmap) data.getExtras().get("data");
                Log.d("TAG", "data not nullll ");
                if (imageBitmap != null) {
                    Toast.makeText(this, "Bitmap not null", Toast.LENGTH_SHORT).show();
                    String base64Image = bitmapToBase64(imageBitmap);
                    photoBase64 = base64Image;
                    Toast.makeText(this, base64Image, Toast.LENGTH_SHORT).show();
                    Log.d("TAG", "base64************* : "+base64Image);

                    // Décodez la chaîne Base64 en Bitmap
                    Bitmap imageBitmapp = decodeBase64ToBitmap(base64Image);

                    // Affichez l'image dans votre ImageView
                    imagePaysage = findViewById(R.id.imgPaysage);
                    imagePaysage.setImageBitmap(imageBitmapp);

                }
            }
        }else if (requestCode == REQUEST_CODE_PICK_VIDEO && resultCode == RESULT_OK && data != null) {
            // Récupérer l'URI de la vidéo sélectionnée depuis les données de l'intention
            Uri selectedVideoUri = data.getData();
            Log.d("TAG_VIDEO", "LETS TAKE VIDEOOOO");
            String base64Video = videoToBase64(selectedVideoUri);
            videoBase64 = base64Video;
            if(base64Video!=null){
                playVideoFromBase64(base64Video);
            }
        }
    }

    // Méthode pour obtenir le chemin absolu de la vidéo à partir de l'URI
    private String getVideoPathFromUri(Uri contentUri) {
        String[] projection = { MediaStore.Video.Media.DATA };
        Cursor cursor = getContentResolver().query(contentUri, projection, null, null, null);
        if (cursor == null) return null;
        int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA);
        cursor.moveToFirst();
        String videoPath = cursor.getString(columnIndex);
        cursor.close();
        return videoPath;
    }
    private Bitmap uriToBitmap(Uri uri) {
        try {
            ContentResolver resolver = getContentResolver();
            InputStream inputStream = resolver.openInputStream(uri);
            return BitmapFactory.decodeStream(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void checkCameraPermissionAndOpenCamera() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            // La permission n'est pas accordée, on la demande à l'utilisateur
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA},
                    REQUEST_CAMERA_PERMISSION);
        } else {
            // La permission est déjà accordée, on peut ouvrir la caméra
            openCamera();
        }
    }
    private void openCamera(){
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (cameraIntent.resolveActivity(getPackageManager()) != null) {
            //Toast.makeText(this, "La caméra est en activite.", Toast.LENGTH_SHORT).show();
            Log.d("Camera", "Launching camera activity...");
            startActivityForResult(cameraIntent, REQUEST_CODE_CAMERA);
        } else {
            Log.d("Camera", "Camera activity not available.");
            Toast.makeText(this, "La caméra n'est pas disponible sur cet appareil.", Toast.LENGTH_SHORT).show();
        }
    }

    private void createOnClickBtnOuvrirCamera() {
        imgbtnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AddContenuActivity.this, "Ouvrir Caméra", Toast.LENGTH_SHORT).show();
                openCamera();
            }
        });
    }

    private void createOnClickBtnOuvrirVideo() {
        imgbtnVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AddContenuActivity.this, "Ouvrir galerie pour video", Toast.LENGTH_SHORT).show();
                pickVideoFromGallery();
            }
        });
    }
    // Méthode pour convertir un Bitmap en Base64
    private String bitmapToBase64(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(byteArray, Base64.DEFAULT);
    }

    public static Bitmap decodeBase64ToBitmap(String base64Image) {
        byte[] decodedBytes = Base64.decode(base64Image, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
    }

    private void pickVideoFromGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_CODE_PICK_VIDEO);
    }

    // Méthode pour convertir une vidéo en base64
    private String videoToBase64(Uri videoUri) {
        try {
            // Ouvrir un flux d'entrée à partir de l'URI de la vidéo
            InputStream inputStream = getContentResolver().openInputStream(videoUri);

            // Lire les données de la vidéo en tant que tableau d'octets
            byte[] videoBytes = new byte[inputStream.available()];
            inputStream.read(videoBytes);

            // Convertir le tableau d'octets en une chaîne Base64
            String base64Video = Base64.encodeToString(videoBytes, Base64.DEFAULT);

            return base64Video;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private byte[] base64ToByteArray(String base64String) {
        return Base64.decode(base64String, Base64.DEFAULT);
    }
    private File saveByteArrayToFile(byte[] byteArray) {
        try {
            File outputDir = getCacheDir();
            File outputFile = File.createTempFile("video_temp", ".mp4", outputDir);
            FileOutputStream fos = new FileOutputStream(outputFile);
            fos.write(byteArray);
            fos.close();
            return outputFile;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void playVideoFromBase64(String base64Video) {
        byte[] videoBytes = base64ToByteArray(base64Video);
        if (videoBytes != null) {
            File videoFile = saveByteArrayToFile(videoBytes);
            if (videoFile != null) {
                // Définir l'URI du fichier temporaire dans le VideoView
                videopaysage.start();
                videopaysage= findViewById(R.id.videopaysage);
                videopaysage.setVideoURI(Uri.fromFile(videoFile));
                videopaysage.start();
            }
        }
    }
}
