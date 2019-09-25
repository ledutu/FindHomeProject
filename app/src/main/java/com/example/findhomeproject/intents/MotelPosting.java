package com.example.findhomeproject.intents;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.findhomeproject.R;
import com.example.findhomeproject.adapter.AdapterImage;
import com.example.findhomeproject.modelForMotel.MotelNews;
import com.example.findhomeproject.ui.home.HomeFragment;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MotelPosting extends AppCompatActivity {

    ImageButton btnCancel, btnPost;
    EditText txtMotelPostingtitle, txtMotelPostingAddress,
            txtMotelPostingCost, txtMotelPostingArea, txtMotelYourNamePosting,
            txtMotelYourPhonePosting, txtMotelPostingDetail;

    TextView btnChooseImage, txtImageLimited;
    ImageView gvImage;

    DatabaseReference myRef;
    FirebaseDatabase firebaseDatabase;
    StorageReference storageReference;
    ProgressDialog progressDialog;

    public final static int Image_Request_Code = 10;
    private Uri filePathUri;
    private String Storage_Path = "MotelImage/";

    public int id = 3;

    int PICK_IMAGE_MULTIPLE = 1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motel_posting);
        getSupportActionBar().hide();
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnChooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseImage();
            }
        });
        
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showADialog();
            }
        });

        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UploadImageFileToFirebaseStorage();

            }
        });
        
    }

    public String GetFileExtension(Uri uri) {

        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        // Returning the file Extension.
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri)) ;

    }

    public void UploadImageFileToFirebaseStorage() {

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

                            Toast.makeText(MotelPosting.this, "Successfully ", Toast.LENGTH_LONG).show();

                            Task<Uri> task = taskSnapshot.getMetadata().getReference().getDownloadUrl();
                            task.addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    id++;
                                    String motelPostingTitle = txtMotelPostingtitle.getText().toString().trim();
                                    String motelPostingAddress = txtMotelPostingAddress.getText().toString().trim();
                                    String motelPostingCost = txtMotelPostingCost.getText().toString().trim();
                                    String motelPostingArea = txtMotelPostingArea.getText().toString().trim();
                                    String motelYourNamePosting= txtMotelYourNamePosting.getText().toString().trim();
                                    String motelYourPhonePosting = txtMotelYourPhonePosting.getText().toString().trim();
                                    String motelPostingDetail = txtMotelPostingDetail.getText().toString().trim();
                                    String phone = motelYourPhonePosting + " - " + motelYourNamePosting;


                                    @SuppressWarnings("VisibleForTests")
                                    MotelNews imageUploadInfo = new MotelNews(
                                            id,
                                            motelPostingCost,
                                            uri.toString(),
                                            motelPostingTitle,
                                            motelPostingAddress,
                                            phone,
                                            motelPostingArea,
                                            "1 ngày trước",
                                            motelPostingDetail
                                    );

                                    String imageUploadId = myRef.push().getKey();

                                    myRef.child(imageUploadId).setValue(imageUploadInfo);
                                    finish();
                                }
                            });
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            progressDialog.dismiss();
                            Toast.makeText(MotelPosting.this, exception.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.setTitle("Uploading...");

                        }
                    });
        } else {

            Toast.makeText(MotelPosting.this, "Please Select Image or Add Image Name", Toast.LENGTH_LONG).show();

        }
    }

    private void showADialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MotelPosting.this);
        builder.setMessage("Bạn hủy rồi sinh viên sống sao đây?");
        builder.setCancelable(true);

        builder.setPositiveButton(
                "Vẫn hủy",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                });

        builder.setNegativeButton(
                "Suy nghỉ lại",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert = builder.create();
        alert.show();
    }


    /*protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            if (requestCode == PICK_IMAGE_MULTIPLE && resultCode == RESULT_OK
                    && null != data) {

                String[] filePathColumn = { MediaStore.Images.Media.DATA };
                arrImage = new ArrayList<String>();
                if(data.getData()!=null){

                    mImageUri=data.getData();

                    Cursor cursor = getContentResolver().query(mImageUri,
                            filePathColumn, null, null, null);

                    cursor.moveToFirst();

                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    image  = cursor.getString(columnIndex);
                    cursor.close();

                    mArrayUri = new ArrayList<Uri>();
                    mArrayUri.add(mImageUri);
                    adapterImage = new AdapterImage(getApplicationContext(),mArrayUri);
                    gvImage.setAdapter(adapterImage);
                    gvImage.setVerticalSpacing(gvImage.getHorizontalSpacing());
                    ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) gvImage
                            .getLayoutParams();
                    mlp.setMargins(0, gvImage.getHorizontalSpacing(), 0, 0);

                } else {
                    if (data.getClipData() != null) {
                        ClipData mClipData = data.getClipData();
                        ArrayList<Uri> mArrayUri = new ArrayList<Uri>();
                        for (int i = 0; i < mClipData.getItemCount(); i++) {

                            ClipData.Item item = mClipData.getItemAt(i);
                            Uri uri = item.getUri();
                            mArrayUri.add(uri);

                            Cursor cursor = getContentResolver().query(uri, filePathColumn, null, null, null);
                            cursor.moveToFirst();

                            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                            image = cursor.getString(columnIndex);
                            arrImage.add(image);
                            cursor.close();

                            adapterImage = new AdapterImage(getApplicationContext(), mArrayUri);
                            gvImage.setAdapter(adapterImage);
                            gvImage.setVerticalSpacing(gvImage.getHorizontalSpacing());
                            ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) gvImage
                                    .getLayoutParams();
                            mlp.setMargins(0, gvImage.getHorizontalSpacing(), 0, 0);

                        }
                    }
                }
            } else {
                Toast.makeText(this, "You haven't picked Image",
                        Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG)
                    .show();
        }

        super.onActivityResult(requestCode, resultCode, data);
    }*/

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Image_Request_Code && resultCode == RESULT_OK && data != null && data.getData() != null) {

            filePathUri  = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePathUri);
                gvImage.setImageBitmap(bitmap);
                Toast.makeText(MotelPosting.this, "Selected one picture!", Toast.LENGTH_LONG).show();

            }
            catch (IOException e) {

                e.printStackTrace();
            }
        }
    }


    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Please Select Image"), Image_Request_Code);
    }

    private void addControls() {
        btnCancel = findViewById(R.id.btnCancel);
        btnPost = findViewById(R.id.btnPost);
        txtMotelPostingtitle = findViewById(R.id.txtMotelPostingtitle);
        txtMotelPostingAddress = findViewById(R.id.txtMotelPostingAddress);
        txtMotelPostingCost = findViewById(R.id.txtMotelPostingCost);
        txtMotelPostingArea = findViewById(R.id.txtMotelPostingArea);
        txtMotelYourNamePosting = findViewById(R.id.txtMotelYourNamePosting);
        txtMotelYourPhonePosting = findViewById(R.id.txtMotelYourPhonePosting);
        txtMotelPostingDetail = findViewById(R.id.txtMotelPostingDetail);
        btnChooseImage = findViewById(R.id.btnChooseImage);
        txtImageLimited = findViewById(R.id.txtImageLimited);
        gvImage = findViewById(R.id.gvImage);
        firebaseDatabase = FirebaseDatabase.getInstance();
        myRef = firebaseDatabase.getReference("motels");
        storageReference = FirebaseStorage.getInstance().getReference();
        progressDialog = new ProgressDialog(MotelPosting.this);
    }
}
