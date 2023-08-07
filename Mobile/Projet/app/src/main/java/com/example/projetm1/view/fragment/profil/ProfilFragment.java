package com.example.projetm1.view.fragment.profil;

import static android.app.Activity.RESULT_OK;

import android.content.ContentResolver;
import android.content.Intent;
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
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.projetm1.R;
import com.example.projetm1.controller.ClientController;
import com.example.projetm1.model.Client;
import com.example.projetm1.outils.Decodage;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ProfilFragment extends Fragment {

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

    private String pdpCode;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profil, container, false);
        initViews(rootView);
        return rootView;
    }

    private void initViews(View rootView) {
        clientId = 1;
        nomText = rootView.findViewById(R.id.textEdithNom);
        prenomText = rootView.findViewById(R.id.textEdithPrenom);
        pseudoText = rootView.findViewById(R.id.textEdithPseudo);
        mailText = rootView.findViewById(R.id.textEdithMail);
        mdpText = rootView.findViewById(R.id.edithTextMdp);
        btnValider = rootView.findViewById(R.id.BtnValider);
        btnModifPdp = rootView.findViewById(R.id.ImgBtnModifPdp);

        clientController = new ClientController();
        displayClientWithId();
        createOnclickBtnOuvrirGalerie();
        createOnclickBtnValider();
    }

    private void displayClientWithId() {
        clientController.getClientById(clientId, new ClientController.GetClientByIdCallback() {
            @Override
            public void onGetClientByIdSuccess(Client client) {
                // Afficher les détails du client récupéré dans l'interface utilisateur
                String imagecode = client.getPdp();
                Bitmap imageBitmapp = Decodage.decodeBase64ToBitmap(imagecode);
                pdpCode = imagecode;
                requireActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
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
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show();
                    }
                });
            }

        });
    }

    private void createOnclickBtnValider() {
        btnValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateProfilMethod();
            }
        });
    }

    private void createOnclickBtnOuvrirGalerie() {
        btnModifPdp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });
    }

    private void openGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, REQUEST_CODE_GALLERY);
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
            btnModifPdp.setImageBitmap(imageBitmap);
        }
    }

    private Bitmap uriToBitmap(Uri uri) {
        try {
            ContentResolver resolver = requireActivity().getContentResolver();
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

    private void updateProfilMethod() {
        // Logique pour mettre à jour le profil
        // Récupérer les informations saisies par l'utilisateur à partir des vues
        String nom = nomText.getText().toString();
        String prenom = prenomText.getText().toString();
        String pseudo = pseudoText.getText().toString();
        String mail = mailText.getText().toString();
        String mdp = mdpText.getText().toString();

        // Appeler la méthode updateProfil du ClientController pour mettre à jour le profil du client
        // Vous devez implémenter cette méthode dans le ClientController
        clientController.updateProfil(clientId, nom, prenom, pseudo, mail, mdp,pdpCode, new ClientController.UpdateProfilCallback() {
            @Override
            public void onUpdateProfilSuccess() {
                // Affichage du toast sur le thread principal
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), "Profil mis à jour avec succès", Toast.LENGTH_SHORT).show();
                    }
                });
            }
            @Override
            public void onUpdateProfilFailure(final String errorMessage) {
                // Affichage du toast sur le thread principal
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_SHORT).show();
                    }
                });
            }

        });
    }
}
