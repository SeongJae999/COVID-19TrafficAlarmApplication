package com.example.coronagpsalarm;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import com.example.coronagpsalarm.models.PatientInfo;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

public class HomeActivity extends Fragment {
    private View view;
    private TextView korPatient, otherPatient, korDayAgo, patient, deaths, recovered, date, patientDay, korDay, otherDay, patientStack, recoveredStack, IsolationStack, deathsStack;
    private HorizontalBarChart horizontalBarChart;

    private ArrayList<PatientInfo> arrayList;

    public HomeActivity(ArrayList<PatientInfo> arrayList) {
        this.arrayList = arrayList;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // Fragment 를 안고 있는 Activity 에 붙었을 때
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    // Fragment 와 Layout 을 연결시켜주는 부분
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_home, container, false);
        setHorizontalBarChart();

        Date currentTime = Calendar.getInstance().getTime();
        String date_text = new SimpleDateFormat("yyyy.MM.dd", Locale.getDefault()).format(currentTime);

        date = view.findViewById(R.id.date);
        date.setText(date_text + " 0시 기준");

        korPatient = view.findViewById(R.id.KorPatient);
        korPatient.setText(arrayList.get(2).getPatient() + "명");
        korDayAgo = view.findViewById(R.id.KorDayAgo);
        korDayAgo.setText("(전일대비 +" + arrayList.get(1).getPatient() +")");
        otherPatient = view.findViewById(R.id.OtherPatient);
        otherPatient.setText(arrayList.get(2).getOther()+"명");

        patientDay = view.findViewById(R.id.patientDay);
        patientDay.setText("" + arrayList.get(0).getPatient());
        korDay = view.findViewById(R.id.korDay);
        korDay.setText("" + arrayList.get(0).getIsolation());
        otherDay = view.findViewById(R.id.otherDay);
        otherDay.setText("" + arrayList.get(0).getOther());
        patientStack = view.findViewById(R.id.patientStack);
        patientStack.setText("" + arrayList.get(2).getPatient());
        recoveredStack = view.findViewById(R.id.recoveredStack);
        recoveredStack.setText("" + arrayList.get(2).getRecovered());
        IsolationStack = view.findViewById(R.id.IsolationStack);
        IsolationStack.setText("" + arrayList.get(2).getIsolation());
        deathsStack = view.findViewById(R.id.deathsStack);
        deathsStack.setText("" + arrayList.get(2).getDeaths());

        patient = view.findViewById(R.id.patient);
        patient.setText(arrayList.get(10).getPatient() + "명");
        deaths = view.findViewById(R.id.deaths);
        deaths.setText(arrayList.get(10).getDeaths() + "명");
        recovered = view.findViewById(R.id.recovered);
        recovered.setText(arrayList.get(10).getRecovered() + "명");

        return view;
    }

    private void setHorizontalBarChart() {
        horizontalBarChart = (HorizontalBarChart) view.findViewById(R.id.horizontalBarChar);
        horizontalBarChart.setNoDataText("Description that you want");
        horizontalBarChart.invalidate();
        horizontalBarChart.setDrawBarShadow(false);
        horizontalBarChart.setFitBars(true);
        horizontalBarChart.getLegend().setEnabled(false);
        horizontalBarChart.setPinchZoom(false);
        horizontalBarChart.setDrawValueAboveBar(false);

        XAxis xAxis = horizontalBarChart.getXAxis();
        xAxis.setDrawGridLines(false);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setEnabled(true);
        xAxis.setDrawAxisLine(false);

        YAxis yLeft = horizontalBarChart.getAxisLeft();
        yLeft.setAxisMaximum(8000);
        yLeft.setAxisMinimum(0);
        yLeft.setEnabled(false);

        xAxis.setLabelCount(5);

        String[] values = {"경북", "검역", "경기", "서울", "대구"};
        xAxis.setValueFormatter(new IndexAxisValueFormatter(values));

        YAxis yRight = horizontalBarChart.getAxisRight();
        yRight.setDrawAxisLine(true);
        yRight.setDrawGridLines(false);
        yRight.setEnabled(false);

        setGraphData();
        horizontalBarChart.animateY(2000);
    }

    private void setGraphData() {
        ArrayList<BarEntry> entris = new ArrayList<>();
        entris.add(new BarEntry(0, arrayList.get(7).getPatient()));
        entris.add(new BarEntry(1, arrayList.get(4).getPatient()));
        entris.add(new BarEntry(2, arrayList.get(5).getPatient()));
        entris.add(new BarEntry(3, arrayList.get(12).getPatient()));
        entris.add(new BarEntry(4, arrayList.get(9).getPatient()));

        BarDataSet barDataSet = new BarDataSet(entris, "Bar Data Set");

        barDataSet.setColors(
                ContextCompat.getColor(horizontalBarChart.getContext(), R.color.md_blue),
                ContextCompat.getColor(horizontalBarChart.getContext(), R.color.md_blue),
                ContextCompat.getColor(horizontalBarChart.getContext(), R.color.md_blue),
                ContextCompat.getColor(horizontalBarChart.getContext(), R.color.md_blue),
                ContextCompat.getColor(horizontalBarChart.getContext(), R.color.md_blue)
        );

        horizontalBarChart.setDrawBarShadow(true);
        barDataSet.setBarShadowColor(Color.argb(40,150,150,150));
        BarData data = new BarData(barDataSet);

        data.setBarWidth(0.9f);

        horizontalBarChart.setData(data);
        horizontalBarChart.invalidate();
    }
}
