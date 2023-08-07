package com.example.projetm1.view.fragment.addcontenu;

import static android.app.Activity.RESULT_OK;

import static com.example.projetm1.view.AddContenuActivity.decodeBase64ToBitmap;

import android.Manifest;
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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.projetm1.R;
import com.example.projetm1.controller.ContenuController;
import com.example.projetm1.controller.FavoriController;
import com.example.projetm1.controller.NotificationController;
import com.example.projetm1.view.AddContenuActivity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddContenuFragment extends Fragment {
    private static final int REQUEST_CODE_GALLERY = 1;
    private static final int REQUEST_CODE_CAMERA = 2;
    private static final int REQUEST_CODE_PICK_VIDEO = 3;
    private static final int REQUEST_CAMERA_PERMISSION = 4;

    private ImageView imagePaysage;
    private VideoView videopaysage;
    private ImageButton imgbtnCamera;
    private ImageButton imgbtnGalerie;
    private ImageButton imgbtnVideo;
    private String photoBase64;
    private String videoBase64;
    private Uri imageUri;
    private EditText editTextCommentaire;

    private Button buttonAjouter;
    // Define other variables as needed

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_contenu, container, false);
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        editTextCommentaire = view.findViewById(R.id.textEdithCommentaire);
        imagePaysage = view.findViewById(R.id.imgPaysage);
        videopaysage = view.findViewById(R.id.videopaysage);
        imgbtnCamera = view.findViewById(R.id.IMGBtnCamera);
        imgbtnGalerie = view.findViewById(R.id.IMGBtnGalerie);
        imgbtnVideo = view.findViewById(R.id.IMGBtnVideo);
        buttonAjouter = view.findViewById(R.id.btnPublier);


        imgbtnGalerie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Ouvrir Galerie", Toast.LENGTH_SHORT).show();
                openGallery();
            }
        });

        imgbtnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Ouvrir Caméra", Toast.LENGTH_SHORT).show();
                checkCameraPermissionAndOpenCamera();
            }
        });

        imgbtnVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Ouvrir galerie pour vidéo", Toast.LENGTH_SHORT).show();
                pickVideoFromGallery();
            }
        });

        // Other view initialization and listeners
    }

    private void createOnclickBtnOuvrirGalerie()
    {
        imgbtnGalerie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getActivity(), "Ouvrir Galerie", Toast.LENGTH_SHORT).show();
                openGallery();
            }
        });
    }
    private void createOnclickBtnAjouterContenu(){

        buttonAjouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "commnecer", Toast.LENGTH_SHORT).show();
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
    public void onSaveInstanceState(Bundle outState) {
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
                requireActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), "Contenu ajouté", Toast.LENGTH_SHORT).show();
                    }
                });
            }
            @Override
            public void onAddContenuFailure(String errorMessage) {
                requireActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), "Désolé, il y a une erreur", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
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
            try {
                // imagePaysage.setImageURI(selectedImageUri);
                imagePaysage.setImageBitmap(decodedBitmap);

            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(getActivity(), "Erreur lors du chargement de l'image", Toast.LENGTH_SHORT).show();
            }
        }else if (requestCode == REQUEST_CODE_CAMERA && resultCode == RESULT_OK) {
            Log.d("TAG", "*-*-*-*-*-*--*-*Activity camere -*-*-**-*-*--*-**-*-*-*- ");
            // L'image a été prise avec succès, vous pouvez maintenant la convertir en Base64
            if (data != null && data.getExtras() != null) {
                Bitmap imageBitmap = (Bitmap) data.getExtras().get("data");
                Log.d("TAG", "data not nullll ");
                if (imageBitmap != null) {
                    Toast.makeText(getActivity(), "Bitmap not null", Toast.LENGTH_SHORT).show();
                    String base64Image = bitmapToBase64(imageBitmap);
                    photoBase64 = base64Image;
                    Toast.makeText(getActivity(), base64Image, Toast.LENGTH_SHORT).show();
                    Log.d("TAG", "base64************* : "+base64Image);

                    // Décodez la chaîne Base64 en Bitmap
                    Bitmap imageBitmapp = decodeBase64ToBitmap(base64Image);

                    // Affichez l'image dans votre ImageView
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

    private String getVideoPathFromUri(Uri contentUri) {
        String[] projection = { MediaStore.Video.Media.DATA };
        Cursor cursor = getActivity().getContentResolver().query(contentUri, projection, null, null, null);
        if (cursor == null) return null;
        int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA);
        cursor.moveToFirst();
        String videoPath = cursor.getString(columnIndex);
        cursor.close();
        return videoPath;
    }

    private Bitmap uriToBitmap(Uri uri) {
        try {
            ContentResolver resolver = getActivity().getContentResolver();
            InputStream inputStream = resolver.openInputStream(uri);
            return BitmapFactory.decodeStream(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void checkCameraPermissionAndOpenCamera() {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            // La permission n'est pas accordée, on la demande à l'utilisateur
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.CAMERA},
                    REQUEST_CAMERA_PERMISSION);
        } else {
            // La permission est déjà accordée, on peut ouvrir la caméra
            openCamera();
        }
    }

    private void openCamera(){
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (cameraIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            //Toast.makeText(this, "La caméra est en activite.", Toast.LENGTH_SHORT).show();
            Log.d("Camera", "Launching camera activity...");
            startActivityForResult(cameraIntent, REQUEST_CODE_CAMERA);
        } else {
            Log.d("Camera", "Camera activity not available.");
            Toast.makeText(getActivity(), "La caméra n'est pas disponible sur cet appareil.", Toast.LENGTH_SHORT).show();
        }
    }

    private void createOnClickBtnOuvrirCamera() {
        imgbtnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Ouvrir Caméra", Toast.LENGTH_SHORT).show();
                openCamera();
            }
        });
    }

    private void createOnClickBtnOuvrirVideo() {
        imgbtnVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Ouvrir galerie pour video", Toast.LENGTH_SHORT).show();
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
            InputStream inputStream = getActivity().getContentResolver().openInputStream(videoUri);

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
            File outputDir = getActivity().getCacheDir();
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
//                videopaysage= findViewById(R.id.videopaysage);
                videopaysage.setVideoURI(Uri.fromFile(videoFile));
                videopaysage.start();
            }
        }
    }


    private void temp_reajir_contenu(int id,int id_client,int id_contenu){
        ajouterNotification( id, id_client, id_contenu);
        ajouterFavori(id, id_client,  id_contenu);
    }

    private void ajouterNotification(int id,int id_client,int id_contenu){
        NotificationController notificationController = new NotificationController();
        Date currentDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = sdf.format(currentDate);
        notificationController.addNotification(id,id_client, id_contenu,formattedDate, new NotificationController.AddNotificationCallBack() {

            @Override
            public void onAddNotificationSuccess() {
                requireActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), "Connexion réussie", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onAddNotificationFailure(String errorMessage) {
                requireActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), "Erreur de connexion : " + errorMessage, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void ajouterFavori(int id, int id_client, int id_contenu){
        FavoriController favoriController = new FavoriController();
        favoriController.addFavori(id, id_client, id_contenu, new FavoriController.AddFavoriCallBack() {
            @Override
            public void onAddFavoriSuccess() {
                requireActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), "Connexion réussie", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onAddFavoriFailure(String errorMessage) {
                requireActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), "Erreur de connexion : " + errorMessage, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
    // Other methods from the original code

    // Remember to handle permission requests, onActivityResult, and other methods accordingly
}
