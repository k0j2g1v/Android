package com.example.miniproject;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;

    static final int REQUEST_TAKE_PHOTO = 1;
    String currentPhotoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView)findViewById(R.id.imageView);
        Button cameraBtn = (Button)findViewById(R.id.cameraBtn);

        cameraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dispatchTakePictureIntent();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        galleryAddPic();

        setPic();
    }


    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    private void galleryAddPic() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(currentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File

            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.miniproject.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }
    private void setPic() {
        // Get the dimensions of the View
        int targetW = imageView.getWidth();
        int targetH = imageView.getHeight();

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;

        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.min(photoW/targetW, photoH/targetH);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(currentPhotoPath, bmOptions);

        try {


        ExifInterface ei = new ExifInterface(currentPhotoPath);
        int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                ExifInterface.ORIENTATION_UNDEFINED);

        Bitmap rotatedBitmap = null;
        switch(orientation) {

            case ExifInterface.ORIENTATION_ROTATE_90:
                rotatedBitmap = rotateImage(bitmap, 90);
                break;

            case ExifInterface.ORIENTATION_ROTATE_180:
                rotatedBitmap = rotateImage(bitmap, 180);
                break;

            case ExifInterface.ORIENTATION_ROTATE_270:
                rotatedBitmap = rotateImage(bitmap, 270);
                break;

            case ExifInterface.ORIENTATION_NORMAL:
            default:
                rotatedBitmap = bitmap;
        }
            //imageView.setImageBitmap(rotatedBitmap);
            saveBitmaptoJpeg(rotatedBitmap,currentPhotoPath);

        } catch (Exception e){
            Log.e("Rotate","e");
        }


//        try
//        {
//            // 이미지를 상황에 맞게 회전시킨다
//            ExifInterface exif = new ExifInterface(currentPhotoPath);
//            int exifOrientation = exif.getAttributeInt(
//                    ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
//            int exifDegree = exifOrientationToDegrees(exifOrientation);
//            bitmap = rotate(bitmap, exifDegree);
//
//            // 변환된 이미지 사용
//            imageView.setImageBitmap(bitmap);
//            saveBitmaptoJpeg(bitmap,currentPhotoPath);
//        }
//        catch(Exception e)
//        {
//            Toast.makeText(this, "오류발생: " + e.getLocalizedMessage(),
//                Toast.LENGTH_LONG).show();
//        }
//    }
//    public int exifOrientationToDegrees(int exifOrientation)
//    {
//        if(exifOrientation == ExifInterface.ORIENTATION_ROTATE_90)
//        {
//            return 90;
//        }
//        else if(exifOrientation == ExifInterface.ORIENTATION_ROTATE_180)
//        {
//            return 180;
//        }
//        else if(exifOrientation == ExifInterface.ORIENTATION_ROTATE_270)
//        {
//            return 270;
//        }
//        return 0;
//    }
//    public Bitmap rotate(Bitmap bitmap, int degrees)
//    {
//        if(degrees != 0 && bitmap != null)
//        {
//            Matrix m = new Matrix();
//            m.setRotate(degrees, (float) bitmap.getWidth() / 2,
//                    (float) bitmap.getHeight() / 2);
//
//            try
//            {
//                Bitmap converted = Bitmap.createBitmap(bitmap, 0, 0,
//                        bitmap.getWidth(), bitmap.getHeight(), m, true);
//                if(bitmap != converted)
//                {
//                    bitmap.recycle();
//                    bitmap = converted;
//                }
//            }
//            catch(OutOfMemoryError ex)
//            {
//                // 메모리가 부족하여 회전을 시키지 못할 경우 그냥 원본을 반환합니다.
//            }
//        }
//        return bitmap;
    }

    public static Bitmap rotateImage(Bitmap source, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(),
                matrix, true);
    }

    public static void saveBitmaptoJpeg(Bitmap bitmap,String path){
        File file = new File(path);
        OutputStream os = null;
        try{
           os = new BufferedOutputStream(new FileOutputStream(file));
           bitmap.compress(Bitmap.CompressFormat.JPEG,100,os);
           os.close();

        }catch(FileNotFoundException exception){
            Log.e("FileNotFoundException", exception.getMessage());
        }catch(IOException exception){
            Log.e("IOException", exception.getMessage());
        }
    }
}

