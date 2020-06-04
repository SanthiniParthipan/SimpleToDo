package sg.edu.rp.c346.id19028654.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etElement;
    Button btnAdd,btnClear,btnD;
    ListView lvitem;
    ArrayList<String>alItem;
    ArrayAdapter<String>aaItem;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etElement=findViewById(R.id.editText);
        btnAdd=findViewById(R.id.buttonAdd);
        btnClear=findViewById(R.id.buttonclear);
        btnD=findViewById(R.id.buttondelete);
        lvitem=findViewById(R.id.listViewItems);
        spinner=findViewById(R.id.spinner);

        alItem=new ArrayList<>();

        aaItem=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,alItem);

        lvitem.setAdapter(aaItem);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message=etElement.getText().toString();
                alItem.add(message);
                aaItem.notifyDataSetChanged();
                etElement.setText(null);
            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alItem.clear();
                aaItem.notifyDataSetChanged();
            }
        });

        btnD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (alItem.size()==0){
                    Toast.makeText(getApplicationContext(), "you dont have any task to remove",
                            Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    int pos=Integer.parseInt(etElement.getText().toString());
                    if(alItem.size() <=pos ){
                        Toast.makeText(MainActivity.this, "wrong index" ,
                                Toast.LENGTH_SHORT).show();
                        return;
                    }else {
                        alItem.remove(pos);
                        aaItem.notifyDataSetChanged();
                        etElement.setText(null);
                    }
                }
            }
        });
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        etElement.setText(getString(R.string.task));
                        btnAdd.setEnabled(true);
                        btnD.setEnabled(false);
                        break;
                    case 1:
                        etElement.setText(getString(R.string.index));
                        btnAdd.setEnabled(false);
                        btnD.setEnabled(true);
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}
