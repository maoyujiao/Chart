package com.zqx.zqxcharts;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.zqx.chart.anim.Anim;
import com.zqx.chart.data.HistogramData;
import com.zqx.chart.data.LineChartData;
import com.zqx.chart.view.Histogram;
import com.zqx.chart.view.LineChart;

public class MainActivity extends AppCompatActivity {
    float[] ydata = new float[7];
    float[] ydata2 = new float[7];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final String[] xdata = new String[]{"06-11","06-12","06-13","06-14","06-15","06-16","06-17"};
        update();
        final LineChart lineChart = (LineChart) findViewById(R.id.linechart);
        final LineChartData lineChartData = LineChartData.builder()
                .setXdata(xdata)
                .setYdata(ydata)
                .setCoordinatesColor(getResources().getColor(android.R.color.holo_orange_dark))
                .setYpCount(7)
                .setAnimType(Anim.ANIM_ALPHA)
                .build();
        lineChart.setChartData(lineChartData);
        final Histogram histogramChart = (Histogram) findViewById(R.id.histogramchart);
        final HistogramData histogramData = HistogramData.builder()
                .setXdata(xdata)
                .setYdata(ydata)
                .setYpCount(7)
                .setAnimType(Anim.ANIM_ALPHA)
                .build();
        histogramChart.setChartData(histogramData);
        findViewById(R.id.update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update();
                lineChartData.setYdata(ydata);
                histogramData.setYdata(ydata2);
                lineChart.update(lineChartData);
                histogramChart.update(histogramData);
            }
        });
        /*ListView listView = (ListView) findViewById(R.id.listView);
        View view = getLayoutInflater().inflate(R.layout.piechart,null,false);
        final PieChart pieChart = (PieChart) view.findViewById(R.id.piechart);
        listView.addHeaderView(view);
        float[] datas = new float[]{1f,2f,3f,4f,5f,6f,7f,8f,9f};
        final int[] colors = new int[]{Color.RED,Color.BLACK,Color.BLUE,Color.GREEN,Color.GRAY,
                Color.YELLOW,Color.LTGRAY,Color.CYAN,Color.MAGENTA};
        pieChart.setDatas(datas);
        //pieChart.setColors(colors);
        final List list = new ArrayList();
        for (float data : datas){
            list.add(data);
        }
        listView.setAdapter(new BaseListViewAdapter<Object>(list,R.layout.piechart_item) {
            @Override
            protected void convert(MyListViewHolder holder, Object item, int postion) {
                holder.getView(R.id.color).setBackgroundColor(pieChart.getColors()[postion]);
                holder.<TextView>getView(R.id.tv).setText(item.toString());
            }
        });*/
    }

    private void update(){
        for (int i=0;i<7;i++){
            ydata[i] = (float) (Math.random() * 50.0f);
            ydata2[i] = (float) (Math.random() * 100.0f);
        }
    }
}
