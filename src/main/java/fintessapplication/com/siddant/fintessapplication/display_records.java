package fintessapplication.com.siddant.fintessapplication;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.achartengine.ChartFactory;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.BasicStroke;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import java.io.FileInputStream;
import java.util.ArrayList;

public class display_records extends AppCompatActivity implements View.OnClickListener {
    public static double distance;
    public static String duration;
    public static  String file;
    private TextView Duration,Distance;// ����
    private View mChart;
    private Button btnspeed, btnpace;
    public static double maxspeed;
    public static double minspeed;
    public static double maxpace;
    public static double minpace;
    ArrayList<String> time = new ArrayList<String>();
    ArrayList<Double> pace = new ArrayList<Double>();
    ArrayList<Double> speed = new ArrayList<Double>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_records);

        btnspeed = (Button) this.findViewById(R.id.button10);
        btnspeed.setOnClickListener(this);

        btnpace = (Button) this.findViewById(R.id.button11);
        btnpace.setOnClickListener(this);
        try{

            String line;

            FileInputStream fin = openFileInput(file);
            int c;
            String temp="";
            ArrayList<String> results = new ArrayList<String>(); //declaring my arrya list

            while( (c = fin.read()) != -1){
                temp = temp + Character.toString((char)c);
            }
           // Log.d("mmmmmmmmmmm", "deep arr: " + temp);

            String[] items = temp.split(",");
            for (String item : items)
            {
                results.add(item);
            }

            int equal = ((results.size()-6)/3);



            while(true){
                if(results.size() == 0){
                    break;
                }else{
                    if(time.size() < equal){
                        time.add(results.get(0));
                        results.remove(0);
                    }else if(pace.size() < equal){
                        pace.add(Double.parseDouble(results.get(0)));
                        results.remove(0);
                    }else if(speed.size() < equal){
                        speed.add(Double.parseDouble(results.get(0)));
                        results.remove(0);
                    }else {
                        distance = Double.parseDouble(results.get(0));
                        results.remove(0);
                        duration = results.get(0);
                        results.remove(0);
                        maxpace = Double.parseDouble(results.get(0));
                        results.remove(0);
                        minpace = Double.parseDouble(results.get(0));
                        results.remove(0);
                        maxspeed = Double.parseDouble(results.get(0));
                        results.remove(0);
                        minspeed = Double.parseDouble(results.get(0));
                        results.remove(0);
                    }
                }
            }


        }
        catch(Exception e){
        }

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.button10:
                createSpeedChart();
                break;
            case R.id.button11:
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

            if(Double.parseDouble(String.valueOf(speed.get(i))) > largest) {

                largest = Double.parseDouble(String.valueOf(speed.get(i)));

            }
        }
        if(maxspeed >largest){
            largest = maxspeed;
        }

// Adding data to Income and Expense Series
        for(int i=0;i<numProvinces;i++){
            Speed.add(i, Double.parseDouble(String.valueOf(speed.get(i))));

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
        mChart = ChartFactory.getCubeLineChartView(display_records.this, dataset, multiRenderer, 0.02f);
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
        mChart = ChartFactory.getCubeLineChartView(display_records.this, dataset, multiRenderer, 0.02f);
//adding the view to the linearlayout
        chartContainer.addView(mChart);
    }










}
