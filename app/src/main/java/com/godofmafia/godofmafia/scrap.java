package com.godofmafia.godofmafia;
/*

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.Gravity;

import androidx.appcompat.widget.AppCompatTextView;

public class FontAwesomeTextView extends AppCompatTextView {

    private Context context;

    public FontAwesomeTextView(Context context) {
        super(context);
        this.context = context;
        createView();
    }

    public FontAwesomeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        createView();
    }
// Typefaces switch between Light and Regular in onClickListener()
    private void createView(){
        setGravity(Gravity.CENTER);

        // Light Typeface
        Typeface ltf = Typeface.createFromAsset
                (getContext().getAssets(), "font/font_awesome_5_pro_light_300.otf");
        setTypeface (ltf);


        // Regular Typeface
        Typeface rtf = Typeface.createFromAsset
                (getContext().getAssets(), "font/font_awesome_5_pro_regular_400.otf");
        setTypeface (rtf);
    }
}

                        private void dispatchTakePictureIntent () {
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
                                    Uri photoURI = (Uri) FileProvider.getUriForFile(this,
                                            "com.example.android.fileprovider",
                                            photoFile);
                                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                                    startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
                                }
                            }
                        }
                    }
*/



