package com.deznorth.fridgekeeper;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import com.deznorth.fridgekeeper.FridgeFragment;

public class AddItem extends AppCompatActivity {

    private static final String TAG = "AddItem";
    private int mItemType;
    private int mItemDateType = 1;

    private String mItemName;
    private String mItemDate;

    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private Button addButton;
    private TextView mNameEdit;
    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private ImageView imageView;
    private Spinner spinner;
    private ArrayAdapter<CharSequence> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        imageView = findViewById(R.id.addItem_img);
        spinner = findViewById(R.id.spinner);
        adapter = ArrayAdapter.createFromResource(this,R.array.Item_Types,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        imageView.setImageResource(R.drawable.no_image_yet);
                        break;
                    case 1:
                        mItemType = 1;
                        imageView.setImageResource(R.drawable.ic_groceries);
                        break;
                    case 2:
                        mItemType = 2;
                        imageView.setImageResource(R.drawable.ic_leftovers);
                        break;
                    case 3:
                        mItemType = 3;
                        imageView.setImageResource(R.drawable.ic_takeout);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mDisplayDate = findViewById(R.id.DatePicker);
        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(AddItem.this
                        ,R.style.FKTheme,mDateSetListener
                        ,year,month,day);
                //dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month++;
                String date = month +"/"+dayOfMonth+"/"+year;
                mItemDate = date;
                mDisplayDate.setText(date);
            }
        };

        mNameEdit = findViewById(R.id.nameEditText);
        mNameEdit.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)){
                    mItemName = mNameEdit.getText().toString();
                }
                return false;
            }
        });

        radioGroup = findViewById(R.id.radioGroup3);
        addButton = findViewById(R.id.addButton);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = findViewById(checkedId);
                String selection = rb.getTag().toString();

                switch (selection){
                    case "op1":
                        mItemDateType = 1;
                        break;
                    case "op2":
                        mItemDateType = 2;
                        break;
                }
                //Toast.makeText(AddItem.this,selection,Toast.LENGTH_LONG).show();
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((mItemType != 0) && (mItemDate != null) && (mItemName != null)){
                    createItem();
                } else{
                    Toast.makeText(AddItem.this,"Please complete all fields.",Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    private String getAdder(){
        SharedPreferences sharedPrefs = getSharedPreferences(
                getString(R.string.Shared_Prefs_Key), Context.MODE_PRIVATE);

        return sharedPrefs.getString(getString(R.string.profile_name_Key)
                ,getString(R.string.profile_default_name));
    }

    private void createItem(){
        FridgeFragment ff = new FridgeFragment();
        ff.addItem(mItemType,mItemName,mItemDate,getAdder(),mItemDateType);
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        Toast.makeText(AddItem.this,mItemType+" "+
                mItemName+" "+mItemDate+" "+mItemDateType,Toast.LENGTH_LONG).show();
    }
}
