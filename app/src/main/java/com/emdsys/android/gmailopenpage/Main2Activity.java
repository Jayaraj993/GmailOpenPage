package com.emdsys.android.gmailopenpage;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {
    private static int RESULT_LOAD_IMAGE = 1;
    TextView textView,chatTexts;
    ImageView watsapp_profile_image,back_button,attach_button,ok_button;
    EditText chatEditText;
    static String result=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initializer();
        Intent i=getIntent();
        String name = i.getStringExtra("name");
        Bundle extras = getIntent().getExtras();
        Bitmap bmp = (Bitmap) extras.getParcelable("imagebitmap");
        watsapp_profile_image.setImageBitmap(bmp );
        textView.setText(name);
    }
    public void initializer(){
        textView = (TextView) findViewById(R.id.intent_name);
        watsapp_profile_image= (ImageView) findViewById(R.id.watsapp_image);
        back_button= (ImageView) findViewById(R.id.back_button);
        back_button.setOnClickListener(this);
        attach_button= (ImageView) findViewById(R.id.attach_button);
        attach_button.setOnClickListener(this);
        chatEditText= (EditText) findViewById(R.id.chat_edit_text);
        ok_button= (ImageView) findViewById(R.id.ok_button);
        ok_button.setOnClickListener(this);
        chatTexts= (TextView) findViewById(R.id.chat_texts);
        watsapp_profile_image.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            watsapp_profile_image.setImageBitmap(BitmapFactory.decodeFile(picturePath));

        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_button:
                finish();
                break;
            case R.id.attach_button:
                gallery();
                break;
            case  R.id.ok_button:
                appendText();
                break;
            case R.id.watsapp_image:
                Intent intent=new Intent(this,collapsing_image.class);
                watsapp_profile_image.buildDrawingCache();
                Bitmap image= watsapp_profile_image.getDrawingCache();

                Bundle extras = new Bundle();
                extras.putParcelable("collapse_image", image);
                intent.putExtras(extras);
                startActivity(intent);
        }
    }
    public void gallery(){
        Intent i = new Intent(
                Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(i, RESULT_LOAD_IMAGE);
    }
    public void appendText(){
        String msg=chatEditText.getText().toString();
        if (msg!=null){
            StringBuilder builder=new StringBuilder();
            builder.append(msg);
            result=result+builder.toString();
            chatTexts.setText(result+" \n");
            chatEditText.setText("");
            InputMethodManager inputManager = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);

            inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);

        }
    }
}
