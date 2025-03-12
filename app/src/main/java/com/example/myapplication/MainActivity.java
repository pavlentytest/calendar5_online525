package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.ak.ColoredDate;
import com.ak.EventObjects;
import com.ak.KalendarView;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

class MyEventObjects extends  EventObjects {

    public MyEventObjects(String message, Date date) {
        super(message, date);
    }

    public String getMessage(Date date) {
        return this.getMessage();
    }

}

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        KalendarView mKalendarView = findViewById(R.id.kalendar);
        List<ColoredDate> datesColors = new ArrayList<>();

        Calendar calendar = new GregorianCalendar(2025,2,15);
        datesColors.add(new ColoredDate(calendar.getTime(), getResources().getColor(R.color.green,null)));
        mKalendarView.setColoredDates(datesColors);


        List<MyEventObjects> events = new ArrayList<>();
        Calendar calendar2 = new GregorianCalendar(2025,2,23);
        events.add(new MyEventObjects("start holiday!",calendar2.getTime()));
        mKalendarView.setEvents(events);

        mKalendarView.setDateSelector(new KalendarView.DateSelector() {
            @Override
            public void onDateClicked(Date selectedDate) {
                showDialog(Long.toString(selectedDate.getTime()));
            }
        });
    }

    public void showDialog(String str) {
        BottomSheetDialog dialog = new BottomSheetDialog(this);
        View bottomSheet = getLayoutInflater().inflate(R.layout.dialog,null);
        TextView textView = bottomSheet.findViewById(R.id.textView);
        textView.setText(str);
        dialog.setContentView(bottomSheet);
        dialog.show();

    }
}