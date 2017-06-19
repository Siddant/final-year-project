package fintessapplication.com.siddant.fintessapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.achartengine.ChartFactory;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.BasicStroke;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class showData extends AppCompatActivity implements View.OnClickListener{
    public static double distance;
    public static String duration;
    public static double maxspeed;
    public static double minspeed;
    public static double maxpace;
    public static double minpace;

    private View mChart;

    private Button saveData, discardData,btnspeed,btnpace;


    ArrayList<String> time = new ArrayList<String>();
    ArrayList<String> pace = new ArrayList<String>();
    ArrayList<String> speed = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_show_data);
        //TableLayout tl = (TableLayout) findViewById(R.id.mainTable);
        Intent intent = getIntent();
        time = intent.getStringArrayListExtra("Timearray");

        pace = intent.getStringArrayListExtra("pacearray");
        speed = intent.getStringArrayListExtra("speedarray");


        btnspeed = (Button) this.findViewById(R.id.button7);
        btnspeed.setOnClickListener(this);

        saveData = (Button)this.findViewById(R.id.button5);
        discardData= (Button)this.findViewById(R.id.button6);
        btnpace = (Button)this.findViewById(R.id.button12);

        btnpace.setOnClickListener(this);

        saveData.setOnClickListener(this);
        discardData.setOnClickListener(this);
    }
    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        Toast.makeText(getBaseContext(), "onDestroy", Toast.LENGTH_SHORT).show();

        View_Activity.total_step = 0;
        View_Activity.steps30secs = 0;
        View_Activity.steps1mins = 0;
        View_Activity. steps1secs =0;

        super.onDestroy();


    }
    @Override
    public void onBackPressed() {
    }
    @Override
    public void onClick(View view) {
        Intent intent = new Intent();

        switch (view.getId()) {
            case R.id.button5:

                try {
                    String timeStamp = new SimpleDateFormat("yyyy.MM.dd_HH.mm.ss ").format(Calendar.getInstance().getTime());
                    timeStamp = timeStamp + duration;

                    FileOutputStream fOut = openFileOutput(timeStamp, Context.MODE_PRIVATE);
                    //FileWriter fOut = new FileWriter(file);

                    for (int i = 0; i < time.size(); i++) {
                        fOut.write((time.get(i) + ",").getBytes());
                        Log.d("wwwwwwwww", "deep arr: " + time.get(i));
                    }
                    for (int i = 0; i < pace.size(); i++) {
                        fOut.write((pace.get(i) + ",").getBytes());
                        Log.d("eeeeeee", "deep arr: " + pace.get(i));
                    }
                    for (int i = 0; i < speed.size(); i++) {
                        fOut.write((speed.get(i) + ",").getBytes());
                        Log.d("aaaaaa", "deep arr: " + speed.get(i) );
                    }
                    fOut.write((distance + ",").getBytes());
                    fOut.write((duration + ",").getBytes());
                    fOut.write((maxpace + ",").getBytes());
                    fOut.write((minpace + ",").getBytes());
                    fOut.write((maxspeed + ",").getBytes());
                    fOut.write((minspeed + ",").getBytes());

                    fOut.write((timeStamp + ",").getBytes());
                    fOut.close();

                }

                catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                intent.setClass(showData.this, menu.class);
                startActivity(intent);
                Toast.makeText(getBaseContext(), "data saved", Toast.LENGTH_SHORT).show();
                break;


                case R.id.button6:
                time.clear();
                pace.clear();
                speed.clear();
                    intent.setClass(showData.this, menu.class);
                    startActivity(intent);
                Toast.makeText(getBaseContext(), "data cleared", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button7:
                createSpeedChart();
                break;
            case R.id.button12:
                createPaceChart();
                break;

        }
    }


    private void createSpeedChart(){
        int xmax = time.size();

        int numProvinces = speed.size();
        //int max = Integer.parseInt(pace.get(pace.size()));
// Creating an XYSeries for Income
        XYSeries Speed = new XYSeries("Speed");
        XYSeries Maxspeed = new XYSeries("Maximum speed");
        XYSeries Minspeed = new XYSeries("Miminum speed");

        double largest = 0.0;
        for(int i =0;i<speed.size();i++) {

            if(Double.parseDouble(speed.get(i)) > largest) {

                largest = Double.parseDouble(speed.get(i));

            }
        }
        if(maxspeed >largest){
            largest = maxspeed;
        }

// Adding data to Income and Expense Series
        for(int i=0;i<numProvinces;i++){
            Speed.add(i, Double.parseDouble(speed.get(i)));

        }
        for(int i=0;i<numProvinces;i++){
            Maxspeed.add(i, maxspeed);

        }
        for(int i=0;i<numProvinces;i++){
            Minspeed.add(i, minspeed);

        }
// Creating a dataset to hold each series
        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
// Adding Income Series to the dataset
        dataset.addSeries(Speed);
        dataset.addSeries(Maxspeed);
        dataset.addSeries(Minspeed);



// Creating XYSeriesRenderer to customize expenseSeries
        XYSeriesRenderer SpeedRenderer = new XYSeriesRenderer();
        SpeedRenderer.setColor(Color.BLUE);
        SpeedRenderer.setFillPoints(true);
        SpeedRenderer.setLineWidth(2f);
        SpeedRenderer.setChartValuesTextSize(30);

        SpeedRenderer.setDisplayChartValues(true);
//setting line graph point style to circle
        SpeedRenderer.setPointStyle(PointStyle.SQUARE);
//setting stroke of the line chart to solid
        SpeedRenderer.setStroke(BasicStroke.SOLID);



        // Creating XYSeriesRenderer to customize expenseSeries
        XYSeriesRenderer MaxSpeedRenderer = new XYSeriesRenderer();
        MaxSpeedRenderer.setColor(Color.RED);
        MaxSpeedRenderer.setFillPoints(true);
        MaxSpeedRenderer.setLineWidth(2f);
        MaxSpeedRenderer.setDisplayChartValues(true);
        MaxSpeedRenderer.setChartValuesTextSize(30);
//setting line graph point style to circle
        MaxSpeedRenderer.setPointStyle(PointStyle.SQUARE);
//setting stroke of the line chart to solid
        MaxSpeedRenderer.setStroke(BasicStroke.SOLID);


        // Creating XYSeriesRenderer to customize expenseSeries
        XYSeriesRenderer MinSpeedRenderer = new XYSeriesRenderer();
        MinSpeedRenderer.setColor(Color.BLACK);
        MinSpeedRenderer.setFillPoints(true);
        MinSpeedRenderer.setLineWidth(2f);
        MinSpeedRenderer.setDisplayChartValues(true);
        MinSpeedRenderer.setChartValuesTextSize(30);
//setting line graph point style to circle
        MinSpeedRenderer.setPointStyle(PointStyle.SQUARE);
//setting stroke of the line chart to solid
        MinSpeedRenderer.setStroke(BasicStroke.SOLID);





// Creating a XYMultipleSeriesRenderer to customize the whole chart
        XYMultipleSeriesRenderer multiRenderer = new XYMultipleSeriesRenderer();
        multiRenderer.setXLabels(0);
        multiRenderer.setYLabelsColor(0, Color.BLACK);
        multiRenderer.setXLabelsColor(Color.BLACK);
/***
 * Customizing graphs
 */
//setting text size of the title
        multiRenderer.setChartTitleTextSize(40);
//setting text size of the axis title
        multiRenderer.setAxisTitleTextSize(40);
//setting text size of the graph lable
        multiRenderer.setLabelsTextSize(40);
//setting zoom buttons visiblity
        multiRenderer.setZoomButtonsVisible(true);
//setting pan enablity which uses graph to move on both axis//setting click false on graph
//setting zoom to false on both axis
        multiRenderer.setZoomEnabled(true, true);
//setting lines to display on y axis
        multiRenderer.setShowGridY(true);
//setting lines to display on x axis
        multiRenderer.setShowGridX(true);
//setting legend to fit the screen size
        multiRenderer.setFitLegend(true);
//setting displaying line on grid
        multiRenderer.setShowGrid(true);
//setting zoom to false
        multiRenderer.setZoomEnabled(true);
//setting external zoom functions to false
        multiRenderer.setExternalZoomEnabled(true);
//setting displaying lines on graph to be formatted(like using graphics)
        multiRenderer.setAntialiasing(true);
//setting to in scroll to false
        multiRenderer.setInScroll(false);
        multiRenderer.setPanEnabled(true, true);
        multiRenderer.setClickEnabled(false);
//setting to set legend height of the graph
        multiRenderer.setLegendHeight(30);
//setting x axis label align
        multiRenderer.setXLabelsAlign(Paint.Align.CENTER);
//setting y axis label to align
        multiRenderer.setYLabelsAlign(Paint.Align.LEFT);
//setting text style
        multiRenderer.setTextTypeface("sans_serif", Typeface.NORMAL);
//setting no of values to display in y axis
        multiRenderer.setYLabels(10);
// setting y axis max value, Since i'm using static values inside the graph so i'm setting y max value to 4000.
// if you use dynamic values then get the max y value and set here
        multiRenderer.setYAxisMax(largest);
//setting used to move the graph on xaxiz to .5 to the right
        multiRenderer.setXAxisMin(-0.5);
//setting used to move the graph on xaxiz to .5 to the right
        multiRenderer.setXAxisMax(xmax);
        multiRenderer.setLegendTextSize(20f);

//setting bar size or space between two bars
//multiRenderer.setBarSpacing(0.5);
//Setting background color of the graph to transparent
        multiRenderer.setBackgroundColor(Color.TRANSPARENT);
//Setting margin color of the graph to transparent
        multiRenderer.setMarginsColor(getResources().getColor(R.color.transparent_background));
        multiRenderer.setApplyBackgroundColor(true);
        multiRenderer.setScale(2f);
//setting x axis point size
        multiRenderer.setPointSize(4f);
//setting the margin size for the graph in the order top, left, bottom, right
        multiRenderer.setMargins(new int[]{30, 30, 30, 30});

        for(int i=0; i< time.size();i++){
            multiRenderer.addXTextLabel(i, time.get(i));
        }

// Adding incomeRenderer and expenseRenderer to multipleRenderer
// Note: The order of adding dataseries to dataset and renderers to multipleRenderer
// should be same
        multiRenderer.addSeriesRenderer(SpeedRenderer);
        multiRenderer.addSeriesRenderer(MaxSpeedRenderer);
        multiRenderer.addSeriesRenderer(MinSpeedRenderer);


//this part is used to display graph on the xml
        LinearLayout chartContainer = (LinearLayout) findViewById(R.id.chart);
//remove any views before u paint the chart
        chartContainer.removeAllViews();
//drawing bar chart
        mChart = ChartFactory.getCubeLineChartView(showData.this, dataset, multiRenderer, 0.02f);
//adding the view to the linearlayout
        chartContainer.addView(mChart);
    }





    private void createPaceChart(){
        int xmax = time.size();

        int numProvinces = speed.size();
        //int max = Integer.parseInt(pace.get(pace.size()));
// Creating an XYSeries for Income
        XYSeries Speed = new XYSeries("Pace");
        XYSeries Maxspeed = new XYSeries("Maximum Pace");
        XYSeries Minspeed = new XYSeries("Miminum Pace");

        double largest = 0.0;
        for(int i =0;i<pace.size();i++) {

            if(Double.parseDouble(String.valueOf(pace.get(i))) > largest) {

                largest = Double.parseDouble(String.valueOf(pace.get(i)));

            }
        }
        if(minpace >largest){
            largest = minpace;
        }

// Adding data to Income and Expense Series
        for(int i=0;i<numProvinces;i++){
            Speed.add(i, Double.parseDouble(String.valueOf(pace.get(i))));

        }
        for(int i=0;i<numProvinces;i++){
            Maxspeed.add(i, maxpace);

        }
        for(int i=0;i<numProvinces;i++){
            Minspeed.add(i, minpace);

        }
// Creating a dataset to hold each series
        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
// Adding Income Series to the dataset
        dataset.addSeries(Speed);
        dataset.addSeries(Maxspeed);
        dataset.addSeries(Minspeed);



// Creating XYSeriesRenderer to customize expenseSeries
        XYSeriesRenderer SpeedRenderer = new XYSeriesRenderer();
        SpeedRenderer.setColor(Color.BLUE);
        SpeedRenderer.setFillPoints(true);
        SpeedRenderer.setLineWidth(2f);
        SpeedRenderer.setChartValuesTextSize(30);

        SpeedRenderer.setDisplayChartValues(true);
//setting line graph point style to circle
        SpeedRenderer.setPointStyle(PointStyle.SQUARE);
//setting stroke of the line chart to solid
        SpeedRenderer.setStroke(BasicStroke.SOLID);



        // Creating XYSeriesRenderer to customize expenseSeries
        XYSeriesRenderer MaxSpeedRenderer = new XYSeriesRenderer();
        MaxSpeedRenderer.setColor(Color.RED);
        MaxSpeedRenderer.setFillPoints(true);
        MaxSpeedRenderer.setLineWidth(2f);
        MaxSpeedRenderer.setDisplayChartValues(true);
        MaxSpeedRenderer.setChartValuesTextSize(30);
//setting line graph point style to circle
        MaxSpeedRenderer.setPointStyle(PointStyle.SQUARE);
//setting stroke of the line chart to solid
        MaxSpeedRenderer.setStroke(BasicStroke.SOLID);


        // Creating XYSeriesRenderer to customize expenseSeries
        XYSeriesRenderer MinSpeedRenderer = new XYSeriesRenderer();
        MinSpeedRenderer.setColor(Color.BLACK);
        MinSpeedRenderer.setFillPoints(true);
        MinSpeedRenderer.setLineWidth(2f);
        MinSpeedRenderer.setDisplayChartValues(true);
        MinSpeedRenderer.setChartValuesTextSize(30);
//setting line graph point style to circle
        MinSpeedRenderer.setPointStyle(PointStyle.SQUARE);
//setting stroke of the line chart to solid
        MinSpeedRenderer.setStroke(BasicStroke.SOLID);





// Creating a XYMultipleSeriesRenderer to customize the whole chart
        XYMultipleSeriesRenderer multiRenderer = new XYMultipleSeriesRenderer();
        multiRenderer.setXLabels(0);
        multiRenderer.setYLabelsColor(0, Color.BLACK);
        multiRenderer.setXLabelsColor(Color.BLACK);
/***
 * Customizing graphs
 */
//setting text size of the title
        multiRenderer.setChartTitleTextSize(40);
//setting text size of the axis title
        multiRenderer.setAxisTitleTextSize(40);
//setting text size of the graph lable
        multiRenderer.setLabelsTextSize(40);
//setting zoom buttons visiblity
        multiRenderer.setZoomButtonsVisible(true);
//setting pan enablity which uses graph to move on both axis//setting click false on graph
//setting zoom to false on both axis
        multiRenderer.setZoomEnabled(true, true);
//setting lines to display on y axis
        multiRenderer.setShowGridY(true);
//setting lines to display on x axis
        multiRenderer.setShowGridX(true);
//setting legend to fit the screen size
        multiRenderer.setFitLegend(true);
//setting displaying line on grid
        multiRenderer.setShowGrid(true);
//setting zoom to false
        multiRenderer.setZoomEnabled(true);
//setting external zoom functions to false
        multiRenderer.setExternalZoomEnabled(true);
//setting displaying lines on graph to be formatted(like using graphics)
        multiRenderer.setAntialiasing(true);
//setting to in scroll to false
        multiRenderer.setInScroll(false);
        multiRenderer.setPanEnabled(true, true);
        multiRenderer.setClickEnabled(false);
//setting to set legend height of the graph
        multiRenderer.setLegendHeight(30);
//setting x axis label align
        multiRenderer.setXLabelsAlign(Paint.Align.CENTER);
//setting y axis label to align
        multiRenderer.setYLabelsAlign(Paint.Align.LEFT);
//setting text style
        multiRenderer.setTextTypeface("sans_serif", Typeface.NORMAL);
//setting no of values to display in y axis
        multiRenderer.setYLabels(10);
// setting y axis max value, Since i'm using static values inside the graph so i'm setting y max value to 4000.
// if you use dynamic values then get the max y value and set here
        multiRenderer.setYAxisMax(largest);
//setting used to move the graph on xaxiz to .5 to the right
        multiRenderer.setXAxisMin(-0.5);
//setting used to move the graph on xaxiz to .5 to the right
        multiRenderer.setXAxisMax(xmax);
        multiRenderer.setLegendTextSize(20f);

//setting bar size or space between two bars
//multiRenderer.setBarSpacing(0.5);
//Setting background color of the graph to transparent
        multiRenderer.setBackgroundColor(Color.TRANSPARENT);
//Setting margin color of the graph to transparent
        multiRenderer.setMarginsColor(getResources().getColor(R.color.transparent_background));
        multiRenderer.setApplyBackgroundColor(true);
        multiRenderer.setScale(2f);
//setting x axis point size
        multiRenderer.setPointSize(4f);
//setting the margin size for the graph in the order top, left, bottom, right
        multiRenderer.setMargins(new int[]{30, 30, 30, 30});

        for(int i=0; i< time.size();i++){
            multiRenderer.addXTextLabel(i, time.get(i));
        }

// Adding incomeRenderer and expenseRenderer to multipleRenderer
// Note: The order of adding dataseries to dataset and renderers to multipleRenderer
// should be same
        multiRenderer.addSeriesRenderer(SpeedRenderer);
        multiRenderer.addSeriesRenderer(MaxSpeedRenderer);
        multiRenderer.addSeriesRenderer(MinSpeedRenderer);


//this part is used to display graph on the xml
        LinearLayout chartContainer = (LinearLayout) findViewById(R.id.chart);
//remove any views before u paint the chart
        chartContainer.removeAllViews();
//drawing bar chart
        mChart = ChartFactory.getCubeLineChartView(showData.this, dataset, multiRenderer, 0.02f);
//adding the view to the linearlayout
        chartContainer.addView(mChart);
    }


}




