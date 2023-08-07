package com.example.projetm1.view;


import android.content.ContentResolver;
import android.content.Intent;
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
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projetm1.R;
import com.example.projetm1.controller.ClientController;
import com.example.projetm1.model.Client;
import com.example.projetm1.outils.NotificationHelper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;


public class RegisterActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_GALLERY = 100;
    private EditText nom;
    private EditText prenom;
    private EditText pseudo;
    private EditText mail;
    private EditText mdp;
    private ImageButton btnAddPdp;
    private String pdpCode;
    private ClientController clientController;

    // Déclarez d'autres EditText pour les autres champs (nom, prénom, etc.)
    private Button btnRegister;

    private void initViews(View rootView) {

        btnAddPdp = rootView.findViewById(R.id.ImgBtnAddPdp);
        clientController = new ClientController();

        createOnclickBtnOuvrirGalerie();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

//        Enlever le header
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        clientController = new ClientController();
        nom = findViewById(R.id.textEdithNom);
        prenom = findViewById(R.id.textEdithPrenom);
        pseudo = findViewById(R.id.textEdithPseudo);
        mail = findViewById(R.id.textEdithMail);
        mdp = findViewById(R.id.edithTextMdp);
        btnAddPdp = findViewById(R.id.ImgBtnAddPdp);
        clientController = new ClientController();

        createOnclickBtnOuvrirGalerie();
//        pdp = findViewById(R.id.pdp);

        // Initialisez les autres EditText

        btnRegister = findViewById(R.id.BtnValider);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Récupérez les valeurs des champs EditText
                String _nom = nom.getText().toString();
                String _prenom = prenom.getText().toString();
                String _pseudo = pseudo.getText().toString();
                String _mail = mail.getText().toString();
                String _mdp = mdp.getText().toString();
                // Récupérez les autres valeurs

                // Créez un nouvel objet Client
                Client client = new Client(_nom,_prenom,_pseudo,_mail,_mdp,"haha");
                clientController.register(client,
                        new ClientController.RegisterCallback() {
                            @Override
                            public void onRegisterSuccess() {
                                // L'authentification a réussi
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        String title = "BIENVENU SUR DAGOTOUR";
                                        String message = "Decouvrez les merveilles de Madagascar";

                                        // Appeler la méthode de la classe NotificationHelper pour afficher la notification
                                        NotificationHelper.displayNotification(RegisterActivity.this, title, message);
                                        Toast.makeText(RegisterActivity.this, "Inscription réussie", Toast.LENGTH_SHORT).show();
//                                        startActivity(new Intent(AuthActivity.this, Accueil.class));
                                    }
                                });
                            }
                            @Override
                            public void onRegisterFailure(final String errorMessage) {
                                // L'authentification a échoué
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(RegisterActivity.this, "Erreur  : " + errorMessage, Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        });

                // Enregistrez le client dans la base de données ou effectuez d'autres actions

            }
        });
        TextView authLinkTextView = findViewById(R.id.authLinkTextView);
        authLinkTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lorsque le texte est cliqué, démarrez l'activité RegisterActivity
                Intent intent = new Intent(RegisterActivity.this, AuthActivity.class);
                startActivity(intent);
            }
        });
    }
    private void openGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, REQUEST_CODE_GALLERY);
    }
    private void createOnclickBtnOuvrirGalerie() {
        btnAddPdp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null) {
            Uri selectedImageUri = data.getData();
            Bitmap imageBitmap = uriToBitmap(selectedImageUri);
            // Mettre à jour l'image affichée dans l'ImageView

            int desiredWidth = 600;
            int desiredHeight = 400;
            Bitmap compressedBitmap = Bitmap.createScaledBitmap(imageBitmap, desiredWidth, desiredHeight, true);
            String base64Image = bitmapToBase64(imageBitmap);
            pdpCode=base64Image;
            Log.d("TAG_IMG", "base64************* : "+base64Image);
            // Décodez la chaîne Base64 en Bitmap (facultatif, vous pouvez sauter cette étape)
            Bitmap decodedBitmap = decodeBase64ToBitmap(base64Image);
            btnAddPdp.setImageBitmap(imageBitmap);
        }
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

    private String bitmapToBase64(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(byteArray, Base64.DEFAULT);
    }

    private Bitmap decodeBase64ToBitmap(String base64Image) {
        byte[] decodedBytes = Base64.decode(base64Image, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
    }
}
