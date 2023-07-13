package sg.edu.rp.c346.id22014114.songdatabasev2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class DisplaySong extends AppCompatActivity {
    ArrayList<String> songs;
    ListView lv;
    Button btn5,back;
    ArrayList<Song> al;
    ArrayAdapter<Song> aa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        lv = findViewById(R.id.lvDisplay);
        btn5 = findViewById(R.id.btn5);
        back = findViewById(R.id.btnback);
        al = new ArrayList<Song>();
        aa = new ArrayAdapter<Song>(this,
                android.R.layout.simple_list_item_1, al);

        lv.setAdapter(aa);

        Intent i = getIntent();
        DBHelper db = new DBHelper(DisplaySong.this);
        al.clear();
        al.addAll(db.getSongs());
        aa.notifyDataSetChanged();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int
                    position, long identity) {
                Song data = al.get(position);
                Intent i = new Intent(DisplaySong.this,
                        EditSong.class);
                i.putExtra("ID", data);
                i.putExtra("Title", data);
                i.putExtra("Singers", data);
                i.putExtra("Year", data);
                i.putExtra("Stars", data);
                startActivity(i);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btn5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Create the DBHelper object, passing in the
                // activity's Context
//                Intent i = new Intent(DisplaySong.this, EditSong.class);
//                startActivity(i);
                DBHelper dbh = new DBHelper(DisplaySong.this);
                al.clear();
                String filterText = "*****";
                al.addAll(dbh.getAllSongs(String.valueOf(filterText.length())));
                aa.notifyDataSetChanged();

            }

        });



    }
}