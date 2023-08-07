package com.example.projetm1.view;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projetm1.R;
import com.example.projetm1.controller.ContenuController;
import com.example.projetm1.controller.FavoriController;
import com.example.projetm1.controller.NotificationController;
import com.example.projetm1.model.Contenu_client_zone;
import com.example.projetm1.outils.Decodage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DetailContenuActivity extends AppCompatActivity {

    private TextView textZone;
    private ImageView imgPaysage;
    private VideoView videoPayasge;
    private TextView textCommentaire;
    private TextView textAuteur;
    private TextView textDate;
    private ImageButton btnJaime;
    private int contenuId;
    private int clientId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_contenu);
        initActivity();
    }

    public void initActivity(){
        contenuId = 7;
        clientId = 1;
        textZone = findViewById(R.id.textViewDetailZone);
        imgPaysage = findViewById(R.id.imgdetailPaysage);
        videoPayasge = findViewById(R.id.videodetailpaysage);
        textCommentaire = findViewById(R.id.textViewDetailCommentaire);
        textAuteur = findViewById(R.id.textViewAuteur);
        textDate = findViewById(R.id.textViewDetailDate);
        btnJaime = findViewById(R.id.IMGBTNJaime);
        displayClientWithId();
        createOnclickBtnJaime();
        createOnclickBtnJaime();
    }
    private void displayClientWithId() {
        //ici je dois encore changer l'id

        // Créer une instance du ClientController
        ContenuController contenuController = new ContenuController();

        // Appeler la méthode getClientById avec l'ID du client et le callback
        contenuController.getContenuById(contenuId, new ContenuController.GetContenuByIdCallback() {
            @Override
            public void onGetContenuByIdSuccess(Contenu_client_zone contenu) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("TAG----ESSAI---TYYYY","TY LE IZY FA NANDEH "+contenu.getIntitule_zone());
                        textZone.setText(contenu.getIntitule_zone());
                        //imgPaysage.findViewById(R.id.imgdetailPaysage);
                        String imagecode = contenu.getPhoto();
                        Bitmap imageBitmapp = Decodage.decodeBase64ToBitmap(imagecode);

                        // Affichez l'image dans votre ImageView
                        //imagePaysage = findViewById(R.id.imgPaysage);
                        imgPaysage.setImageBitmap(imageBitmapp);
                        //videoPayasge.findViewById(R.id.videodetailpaysage);
                        String videocode= contenu.getVideo();
                        if(videocode!=null){
                            playVideoFromBase64(videocode);
                        }
                        textCommentaire.setText(contenu.getCommentaire());
                        textAuteur.setText(contenu.getPseudo());
                        textDate.setText(contenu.getDate_contenu());

                    }
                });
            }

            @Override
            public void onGetContenuByIdFailure(String errorMessage) {
                Log.d("TAG_AFFICHER", "base64************* : "+errorMessage);
            }

        });
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
    private byte[] base64ToByteArray(String base64String) {
        return Base64.decode(base64String, Base64.DEFAULT);
    }
    private void playVideoFromBase64(String base64Video) {
        byte[] videoBytes = base64ToByteArray(base64Video);
        if (videoBytes != null) {
            File videoFile = saveByteArrayToFile(videoBytes);
            if (videoFile != null) {
                // Définir l'URI du fichier temporaire dans le VideoView
                videoPayasge.start();
                //videoPayasge= findViewById(R.id.videopaysage);
                videoPayasge.setVideoURI(Uri.fromFile(videoFile));
                videoPayasge.start();
            }
        }
    }
    private void createOnclickBtnJaime(){

        btnJaime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DetailContenuActivity.this, "J'aime", Toast.LENGTH_SHORT).show();
                temp_reajir_contenu();
            }
        });
    }

    private void temp_reajir_contenu(){
        ajouterNotification( clientId, contenuId);
        ajouterFavori(clientId, contenuId);
        // Trouver l'ID de la nouvelle image (par exemple, R.drawable.nouvelle_image)
        int nouvelleImageID = R.drawable.coeurr;
        // Changer l'image de btnJaime en utilisant setImageResource()
        btnJaime.setImageResource(nouvelleImageID);
    }

    private void ajouterNotification(int id_client,int id_contenu){
        NotificationController notificationController = new NotificationController();
        Date currentDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = sdf.format(currentDate);
        notificationController.addNotification(id_client, id_contenu,formattedDate, new NotificationController.AddNotificationCallBack() {

            @Override
            public void onAddNotificationSuccess() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(DetailContenuActivity.this, "Connexion réussie", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onAddNotificationFailure(String errorMessage) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(DetailContenuActivity.this, "Erreur de connexion : " + errorMessage, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void ajouterFavori(int id_client, int id_contenu){
        FavoriController favoriController = new FavoriController();
        favoriController.addFavori(id_client, id_contenu, new FavoriController.AddFavoriCallBack() {
            @Override
            public void onAddFavoriSuccess() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(DetailContenuActivity.this, "Connexion réussie", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onAddFavoriFailure(String errorMessage) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(DetailContenuActivity.this, "Erreur de connexion : " + errorMessage, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

}