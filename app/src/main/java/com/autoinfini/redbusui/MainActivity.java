package com.autoinfini.redbusui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnSeatSelected {

    private static final int COLUMNS = 15;
    private static final int ROWS = 7;
//    private static final int GAP = 2;
    private static final int TOP_FULL_COL = 3;
    private static final int BOT_FULL_COL = 1;
    private static final int[] GAPS = {4,5, 9,10};
    private static final int[] BOOKED = {0, 1, 2, 12, 14, 15, 26, 44, 45, 46, 54, 49, 66, 94, 99, 96};
    private TextView txtSeatSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtSeatSelected = (TextView) findViewById(R.id.txt_seat_selected);

        List<AbstractItem> items = new ArrayList<>();
        int t_counter = 0;
        for (int i = 0; i < COLUMNS * TOP_FULL_COL; i++) {
            if (contains(BOOKED, t_counter))
                items.add(new BookedItem(String.valueOf("T-" + ++t_counter)));
            else
                items.add(new CenterItem(String.valueOf("T-" + ++t_counter)));

        }

        boolean empty_flag = false;
        for (int i = 0; i < ROWS * COLUMNS; i++) {
            empty_flag = false;
            for (int Gap : GAPS) {

                if (i % COLUMNS == Gap) {
                    empty_flag = true;

                }


            }
            if (empty_flag) {

                items.add(new EmptyItem(String.valueOf(i)));
            } else {

                if (contains(BOOKED, t_counter))
                    items.add(new BookedItem(String.valueOf("T-" + ++t_counter)));
                else
                    items.add(new CenterItem(String.valueOf("T-" + ++t_counter)));
            }

//            if (i % COLUMNS == GAPS[0] || i % COLUMNS == GAPS[1] )
//                items.add(new EmptyItem(String.valueOf(i)));
//            else{
//                items.add(new CenterItem(String.valueOf(i)));
//
//            }
//            items.add(new CenterItem(String.valueOf(i)));

        }
        for (int i = 0; i < COLUMNS * BOT_FULL_COL; i++) {
            if (contains(BOOKED, t_counter))
                items.add(new BookedItem(String.valueOf("T-" + ++t_counter)));
            else
                items.add(new CenterItem(String.valueOf("T-" + ++t_counter)));

        }


//        for (int i=0; i<ROWS*COLUMNS; i++) {
//
//            if (i%COLUMNS==0 || i%COLUMNS==4) {
//                items.add(new EdgeItem(String.valueOf(i)));
//            } else if (i%COLUMNS==1 || i%COLUMNS==3) {
//                items.add(new CenterItem(String.valueOf(i)));
//            } else {
//                items.add(new EmptyItem(String.valueOf(i)));
//            }
//        }
//
//        for (int i=0; i<=5; i++) {
//            if (i%COLUMNS==0 || i%COLUMNS==4) {
//                items.add(new EdgeItem(String.valueOf(i)));
//            } else if (i%COLUMNS==1 || i%COLUMNS==3) {
//                items.add(new CenterItem(String.valueOf(i)));
//            }
//        }


        GridLayoutManager manager = new GridLayoutManager(this, COLUMNS);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.lst_items);
        recyclerView.setLayoutManager(manager);

        AirplaneAdapter adapter = new AirplaneAdapter(this, items);
        recyclerView.setAdapter(adapter);
    }

    public boolean contains(final int[] array, final int key) {
        Arrays.sort(array);
        return Arrays.binarySearch(array, key) >= 0;
    }

    @Override
    public void onSeatSelected(int count, String lbl) {

        Toast.makeText(this, lbl, Toast.LENGTH_SHORT).show();
        txtSeatSelected.setText("Book " + count + " seats");
    }
}
