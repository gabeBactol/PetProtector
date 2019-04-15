package edu.miracostacollege.cs134.petprotector;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final int RESULT_LOAD_IMAGE = 200;
    private ImageView petImageView;
    private EditText nameEditText;
    private EditText descriptionEditText;
    private EditText numberEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //connect pet image view to layout
        //set image url to petImageView

        petImageView = findViewById(R.id.petImageView);
        petImageView.setImageURI(getUritoResources(this, R.drawable.none));

        nameEditText = findViewById(R.id.nameEditText);
        descriptionEditText = findViewById(R.id.descriptionEditText);
        numberEditText = findViewById(R.id.numberEditText);
    }

    private static Uri getUritoResources(Context context, int id)
    {
        Resources res = context.getResources();
        String uri = ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + res.getResourcePackageName(id) + "/" + res.getResourceTypeName(id) + "/" + res.getResourceEntryName(id);
        //android: resource: //edu.miracostacollege.cs134.petprotector/drawable/none

        return Uri.parse(uri);
    }
    public void selectPetImage(View v)
    {
        //1) Make list(empty) of permissions
        //2) As user grants them, add each permissions to the list
        List<String> permsList = new ArrayList<>();
        int perReqCode = 100;
        int hasCameraPerm = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        int hasReadExternalPerm = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        int hasWriteExternalPerm = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        //Check to see if camera permission is denied
        //If denied, add it to the list of permissions requests
        if(hasCameraPerm == PackageManager.PERMISSION_DENIED)
        {
            permsList.add(Manifest.permission.CAMERA);
        }
        if(hasReadExternalPerm == PackageManager.PERMISSION_DENIED)
        {
            permsList.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }

        if(hasWriteExternalPerm == PackageManager.PERMISSION_DENIED)
        {
            permsList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }

        if(permsList.size() > 0)
        {
            //Convert the List into an array
            String[] perms = new String[permsList.size()];
            permsList.toArray(perms);
            //Make request to the user
            ActivityCompat.requestPermissions(this, perms, perReqCode);
        }

        //After requesting permissions, find out which ones user granted
        //check to see if ALL permissions were granted
        if(hasCameraPerm == PackageManager.PERMISSION_GRANTED && hasReadExternalPerm == PackageManager.PERMISSION_GRANTED && hasWriteExternalPerm == PackageManager.PERMISSION_GRANTED)
        {
            //Open the Gallery
            Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);
        }
        else
        {
            //Toast saying permissions required
        }
    }
    //Override onActivityResult to find out what the user picked

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_LOAD_IMAGE)
        {
            Uri uri = data.getData();
            petImageView.setImageURI(uri);
        }
    }

    public void addPetToList(View v)
    {
        String name = nameEditText.getText().toString();
        String description = descriptionEditText.getText().toString();
        String number = numberEditText.getText().toString();

    }
}
