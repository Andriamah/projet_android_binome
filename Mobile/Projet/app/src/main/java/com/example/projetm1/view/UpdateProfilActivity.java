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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projetm1.R;
import com.example.projetm1.controller.ClientController;
import com.example.projetm1.model.Client;
import com.example.projetm1.outils.Decodage;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class UpdateProfilActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_GALLERY = 100;
    private ImageButton btnModifPdp;
    private EditText nomText;
    private EditText prenomText;
    private EditText pseudoText;
    private EditText mailText;
    private EditText mdpText;
    private ClientController clientController;
    private Button btnValider;
    private int clientId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profil);
        initActivity();
    }

    public void initActivity(){
        clientId = 1;
        nomText = findViewById(R.id.textEdithNom);
        prenomText = findViewById(R.id.textEdithPrenom);
        pseudoText = findViewById(R.id.textEdithPseudo);
        mailText = findViewById(R.id.textEdithMail);
        mdpText = findViewById(R.id.editTextMdp);
        btnValider = findViewById(R.id.BtnValider);
        btnModifPdp = findViewById(R.id.ImgBtnModifPdp);
        displayClientWithId();
        createOnclickBtnOuvrirGalerie();
        createOnclickBtnValider();
    }
    private void displayClientWithId() {
            //ici je dois encore changer l'id
            int clientId = 1;

            // Créer une instance du ClientController
            ClientController clientController = new ClientController();

            // Appeler la méthode getClientById avec l'ID du client et le callback
            clientController.getClientById(clientId, new ClientController.GetClientByIdCallback() {
                @Override
                public void onGetClientByIdSuccess(Client client) {
                    // Afficher les détails du client récupéré dans l'interface utilisateur
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            String imagecode = client.getPdp();
                            Bitmap imageBitmapp = Decodage.decodeBase64ToBitmap(imagecode);
                            btnModifPdp.setImageBitmap(imageBitmapp);
                           nomText.setText(client.getNom());
                           prenomText.setText(client.getPrenom());
                           pseudoText.setText(client.getPseudo());
                           mailText.setText(client.getMail());
                           mdpText.setText(client.getMdp());
                        }
                    });
                }

                @Override
                public void onGetClientByIdFailure(String errorMessage) {
                    // Afficher un message d'erreur en cas d'échec
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(UpdateProfilActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
        }
    private void updateProfilMethod() {
            String nom = nomText.getText().toString();
            String prenom = prenomText.getText().toString();
            String pseudo = pseudoText.getText().toString();
            String mail = mailText.getText().toString();
            String mdp = mdpText.getText().toString();
            String pdp = "pdp";
            ClientController clientController = new ClientController();
            // Appeler la méthode updateProfil en passant l'objet UpdateProfilCallback
            clientController.updateProfil(clientId, nom, prenom, pseudo, mail, mdp, pdp, new ClientController.UpdateProfilCallback() {
                @Override
                public void onUpdateProfilSuccess() {
                    // Traitement en cas de succès de la mise à jour du profil
                    // Par exemple, afficher un message de succès
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(UpdateProfilActivity.this, "Profil mis à jour avec succès", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                @Override
                public void onUpdateProfilFailure(String errorMessage) {
                    // Traitement en cas d'échec de la mise à jour du profil
                    // Par exemple, afficher un message d'erreur
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(UpdateProfilActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
        }
    private void createOnclickBtnValider()
    {
        btnValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UpdateProfilActivity.this, "Ouvrir Galerie", Toast.LENGTH_SHORT).show();
                updateProfilMethod();
            }
        });
    }
    private void createOnclickBtnOuvrirGalerie()
    {
        btnModifPdp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UpdateProfilActivity.this, "Ouvrir Galerie", Toast.LENGTH_SHORT).show();
                openGallery();
            }
        });
    }
    private void openGallery() {
        // Intent pour ouvrir la galerie
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, REQUEST_CODE_GALLERY);
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
            Log.d("TAG_IMG", "base64************* : "+base64Image);
            // Décodez la chaîne Base64 en Bitmap (facultatif, vous pouvez sauter cette étape)
            Bitmap decodedBitmap = decodeBase64ToBitmap(base64Image);
            // Afficher l'image dans votre application (par exemple, dans un ImageView)
            try {
                btnModifPdp.setImageBitmap(decodedBitmap);

            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, "Erreur lors du chargement de l'image", Toast.LENGTH_SHORT).show();
            }
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

    public static Bitmap decodeBase64ToBitmap(String base64Image) {
        byte[] decodedBytes = Base64.decode(base64Image, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
    }
}