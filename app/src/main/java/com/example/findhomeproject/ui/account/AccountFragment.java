package com.example.findhomeproject.ui.account;

import android.app.MediaRouteButton;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.findhomeproject.R;
import com.example.findhomeproject.intents.Login;
import com.example.findhomeproject.intents.MotelPosted;
import com.example.findhomeproject.intents.MotelPosting;
import com.example.findhomeproject.modelForMotel.MotelNews;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AccountFragment extends Fragment {
    Spinner spYouLive;
    List<String> tinh;
    ArrayAdapter<String> dataAdapter;
    Button btnLogOut;
    TextView txtNameAccount, txtEmailAccount, txtPassword;
    CircleImageView imgProfileImage;
    FirebaseUser user;

    Button btnUpdateProfile, btnUpdateImage, btnPhongDaDang;

    ImageView btnEditNameAccount, btnEditPassword;
    public final int Image_Request_Code = 123;

    private Uri filePathUri;
    private String Storage_Path = "MotelImageUsers/";
    StorageReference storageReference;


    private ProgressBar progressBar;

    String imageURL = "";

    ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View account = inflater.inflate(R.layout.fragment_account, container, false);

        addControls(account);
        addEvents();

        return account;
    }

    private void addControls(View account) {
        spYouLive = (Spinner) account.findViewById(R.id.spYouLive);
        btnLogOut = account.findViewById(R.id.btnLogOut);
        tinh = new ArrayList<String>();
        tinh = Arrays.asList(getResources().getStringArray(R.array.tinh_thanh));
        dataAdapter = new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_spinner_item,tinh);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spYouLive.setAdapter(dataAdapter);
        progressBar = account.findViewById(R.id.progressBar);
        txtNameAccount = account.findViewById(R.id.txtNameAccount);
        txtEmailAccount = account.findViewById(R.id.txtEmailAccount);
        imgProfileImage = account.findViewById(R.id.imgProfileImage);
        btnUpdateProfile = account.findViewById(R.id.btnUpdateProfile);
        btnEditPassword = account.findViewById(R.id.btnEditPassword);
        txtPassword = account.findViewById(R.id.txtPassWord);
        progressDialog = new ProgressDialog(getActivity());
        btnUpdateImage = account.findViewById(R.id.btnUpdateImage);
        btnPhongDaDang = account.findViewById(R.id.btnPhongDaDang);

        storageReference = FirebaseStorage.getInstance().getReference();

        btnEditNameAccount = account.findViewById(R.id.btnEditNameAccount);

        user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null)
        {
            String email = user.getEmail();
            String name = user.getDisplayName();
            Uri image = user.getPhotoUrl();

            txtNameAccount.setText(name);
            txtEmailAccount.setText(email);

            if(image == null)
            {
                imgProfileImage.setImageResource(R.drawable.home);
            }
            else
            {
                Picasso.get().load(image).into(imgProfileImage);
                Log.i("userImage:", String.valueOf(image));
            }

        }

    }

    private void addEvents() {
        spYouLive.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getActivity(), Login.class);
                startActivity(intent);
            }
        });

        btnEditNameAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edit(txtNameAccount);
            }
        });

        btnEditPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePassword();
            }
        });

        imgProfileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editImage();
            }
        });

        btnUpdateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateProfile();
            }
        });

        btnUpdateImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadImageFileToFirebaseStorage();
            }
        });

        btnPhongDaDang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToMotelPosted();
            }
        });

    }

    private void goToMotelPosted() {
        Intent intent = new Intent(getActivity(), MotelPosted.class);
        startActivity(intent);
    }

    private void updatePassword() {
        LayoutInflater li = LayoutInflater.from(getActivity());
        View row = li.inflate(R.layout.show_dialog, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());

        alertDialogBuilder.setView(row);

        final EditText userInput = row.findViewById(R.id.txtEdit);

        alertDialogBuilder.setCancelable(false).setPositiveButton("Update",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        update(userInput);
                    }
                })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        });

        AlertDialog alertDialog = alertDialogBuilder.create();

        alertDialog.show();
    }

    private void update(EditText text) {
        String newPassword = text.getText().toString();
        progressBar.setVisibility(View.VISIBLE);
        user.updatePassword(newPassword).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getActivity(), "Cập nhật password thành công", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }
                else
                {
                    Toast.makeText(getActivity(), "Kiểm tra kết nối", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void updateProfile() {
        progressBar.setVisibility(View.VISIBLE);
        btnUpdateProfile.setVisibility(View.INVISIBLE);
        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(txtNameAccount.getText().toString())
                .setPhotoUri(Uri.parse(imageURL))
                .build();
        user.updateProfile(profileUpdates).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getActivity(), "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
    }

    private void editImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Please Select Image"), Image_Request_Code);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Image_Request_Code && resultCode == getActivity().RESULT_OK && data != null && data.getData() != null) {

            filePathUri  = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), filePathUri);
                imgProfileImage.setImageBitmap(bitmap);
                btnUpdateImage.setVisibility(View.VISIBLE);

            }
            catch (IOException e) {

                e.printStackTrace();
            }
        }
    }

    public String GetFileExtension(Uri uri) {

        ContentResolver contentResolver = getActivity().getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        // Returning the file Extension.
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri)) ;

    }

    public void uploadImageFileToFirebaseStorage() {
        if (filePathUri != null) {

            progressDialog.setTitle("Image is Uploading...");
            progressDialog.show();

            final StorageReference storageReference2nd = storageReference.child(
                    Storage_Path + System.currentTimeMillis() + "." + GetFileExtension(filePathUri)
            );

            storageReference2nd.putFile(filePathUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            progressDialog.dismiss();
                            btnUpdateImage.setVisibility(View.INVISIBLE);

                            Toast.makeText(getActivity(), "Successfully ", Toast.LENGTH_SHORT).show();

                            Task<Uri> task = taskSnapshot.getMetadata().getReference().getDownloadUrl();
                            task.addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    imageURL = uri.toString();
                                }
                            });
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            Toast.makeText(getActivity(), "Thử lại", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.setTitle("Uploading");
                        }
                    });
        }
        else
        {
            Toast.makeText(getActivity(), "Thử lại", Toast.LENGTH_SHORT).show();
        }
    }

    private void edit(final TextView text) {
        LayoutInflater li = LayoutInflater.from(getActivity());
        View row = li.inflate(R.layout.show_dialog, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());

        alertDialogBuilder.setView(row);

        final EditText userInput = (EditText) row.findViewById(R.id.txtEdit);

        alertDialogBuilder.setCancelable(false).setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                text.setText(userInput.getText());
                                btnUpdateProfile.setVisibility(View.VISIBLE);
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        });

        AlertDialog alertDialog = alertDialogBuilder.create();

        alertDialog.show();
    }
}
