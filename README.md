#ZqxChart
![折线图,柱状图](http://git.oschina.net/uploads/images/2016/0630/141247_00df7a2e_372342.gif "折线图,柱状图")
![饼状图](http://git.oschina.net/uploads/images/2016/0630/141029_c9b91675_372342.gif "饼状图")
maven：
------
    <dependency>
        <groupId>com.zqx.chart</groupId>
        <artifactId>chart</artifactId>
        <version>0.2</version>
        <type>pom</type>
    </dependency>


gradle：
-------
    compile 'com.zqx.chart:chart:0.2'

使用方法：
==========
  1.折线图
  --------
      //通过代码设置：
      LineChart lineChart = (LineChart) findViewById(R.id.linechart);
      LineChartData lineChartData = LineChartData.builder()
              .setXdata(xdata)//x轴数据
              .setYdata(ydata)//y轴数据
              .setYpCount(7)//y轴刻度数量
              .setCoordinatesColor(getResources().getColor(android.R.color.holo_orange_dark))
              //.setXXX()
              .setAnimType(Anim.ANIM_ALPHA)//动画效果，目前仅支持两种
              .build();
      lineChart.setChartData(lineChartData);
      //通过xml设置：
      <?xml version="1.0" encoding="utf-8"?>
      <LinearLayout
          xmlns:android="http://schemas.android.com/apk/res/android"
          xmlns:chart="http://schemas.android.com/apk/res/com.zqx.zqxcharts" <!-- 此处指定自己的包名 -->
          android:layout_width="match_parent"
          android:layout_height="match_parent"
          android:orientation="vertical">
      
              <com.zqx.chart.view.LineChart
                  android:layout_marginTop="5dp"
                  android:id="@+id/linechart"
                  android:layout_width="match_parent"
                  android:layout_height="200dp"
                  android:layout_marginRight="5dp"
                  chart:lCoordinatesColor="@color/colorAccent" 
                  chart:lxTextColor="@color/colorAccent"
                  chart:lyTextColor="@color/colorAccent"
                  chart:pointColor="@color/colorAccent"
                  />
            
      </LinearLayout>
      // res/下新建 attrs.xml
      <!-- 折线图样式属性 -->
      <declare-styleable name="zqxchart_line">
          <!-- 坐标系颜色 -->
          <attr name="lCoordinatesColor" format="color"/>
          <!-- 折点颜色 -->
          <attr name="pointColor" format="color"/>
          <!-- 折点文字颜色 -->
          <attr name="pointTextColor" format="color"/>
          <!-- 折点文字尺寸 -->
          <attr name="pointTextSize" format="integer"/>
          <!-- 折线颜色 -->
          <attr name="lineColor" format="color"/>
          <!-- x轴文字颜色 -->
          <attr name="lxTextColor" format="color"/>
          <!-- y轴文字颜色 -->
          <attr name="lyTextColor" format="color"/>
          <!-- x轴文字尺寸 -->
          <attr name="lxTextSize" format="integer"/>
          <!-- y轴文字尺寸 -->
          <attr name="lyTextSize" format="integer"/>
          <!-- x轴刻度点数量 -->
          <attr name="lxPointCount" format="integer"/>
          <!-- y轴刻度点数量 -->
          <attr name="lyPointCount" format="integer"/>
          <!-- 折线宽度 -->
          <attr name="line_width" format="float"/>
          <!-- 折点尺寸 -->
          <attr name="point_size" format="float"/>
          <!-- 折线样式是否闭合 -->
          <attr name="lPathStyle">
              <enum name="STROKE" value="0"></enum>
              <enum name="FILL" value="1"></enum>
          </attr>
      </declare-styleable>
  2.柱状图
  --------
      //通过代码设置
      Histogram histogramChart = (Histogram) findViewById(R.id.histogramchart);
      HistogramData histogramData = HistogramData.builder()
              .setXdata(xdata)
              .setYdata(ydata)
              .setYpCount(7)
              .setAnimType(Anim.ANIM_ALPHA)
              .build();
      histogramChart.setChartData(histogramData);
      #通过xml设置
      <com.zqx.chart.view.LineChart
            android:layout_marginTop="5dp"
            android:id="@+id/linechart"
            android:layout_width="match_parent"
            android:layout_height="200dp"
            android:layout_marginRight="5dp"
            chart:lCoordinatesColor="@color/colorAccent"
            chart:lxTextColor="@color/colorAccent"
            chart:lyTextColor="@color/colorAccent"
            chart:pointColor="@color/colorAccent"
            />
      <!-- 柱状图样式属性 -->
      <declare-styleable name="zqxchart_histogram">
          <!-- 坐标系颜色 -->
          <attr name="hCoordinatesColor" format="color"/>
          <attr name="rectTextColor" format="color"/>
          <attr name="rectTextSize" format="integer"/>
          <!-- x轴文字颜色 -->
          <attr name="hxTextColor" format="color"/>
          <!-- y轴文字颜色 -->
          <attr name="hyTextColor" format="color"/>
          <!-- x轴文字尺寸 -->
          <attr name="hxTextSize" format="integer"/>
          <!-- y轴文字尺寸 -->
          <attr name="hyTextSize" format="integer"/>
          <!-- x轴刻度点数量 -->
          <attr name="hxPointCount" format="integer"/>
          <!-- y轴刻度点数量 -->
          <attr name="hyPointCount" format="integer"/>
      </declare-styleable>
      //也可以通过代码和 arrays.xml 设置每个x坐标对应柱状图的颜色,代码可以通过新建一个color 数组并调用
          setColors(xxx);
      //arrays.xml color 个数与数据个数对应
        <!-- 柱状图颜色属性 -->
        <integer-array name="histogram_color" >
            <item>@android:color/darker_gray</item>
            <item>@android:color/holo_red_dark</item>
            <item>@android:color/holo_green_dark</item>
            <item>@android:color/holo_orange_dark</item>
            <item>@color/histogram_test</item>
            <item>@android:color/holo_blue_dark</item>
            <item>@color/colorAccent</item>
        </integer-array>
  3.饼状图
  --------
        饼状图动画效果目前只支持alpha
        code:
            PieChart pieChart = (PieChart) findViewById(R.id.piechart);
            int[] colors = new int[]{Color.RED,Color.BLACK,Color.BLUE,Color.GREEN,Color.GRAY,
                    Color.YELLOW,Color.LTGRAY,Color.CYAN,Color.MAGENTA};
            PieChartData pieChartData = PieChartData.builder()
                    .setDatas(datas)
                    //.setColors(colors)
                    .setTextColor(Color.RED)
                    //.setTextSize(36)
                    //.setSeparationDegree(3)
                    .setPieItemClickListener(new OnPieItemClickListener() {
                        @Override
                        public void onPieItemClick(int position) {
                            Toast.makeText(MainActivity.this,"click->"+position,Toast.LENGTH_SHORT).show();
                        }
                    }).build();
            pieChart.setChartData(pieChartData);
        xml:
            <com.zqx.chart.view.PieChart
                  android:id="@+id/piechart"
                  android:layout_width="match_parent"
                  android:padding="5dp"
                  android:layout_height="400dp"
                  chart:textColor="@color/colorAccent"
                  chart:textSize="40"
                  />
        <!-- 饼状图颜色属性 -->
        <integer-array name="pie_colors" >
          <item>@color/colorPrimary</item>
          <item>@android:color/darker_gray</item>
          <item>@android:color/holo_red_dark</item>
          <item>@android:color/holo_green_dark</item>
          <item>@android:color/holo_orange_dark</item>
          <item>@android:color/white</item>
          <item>@android:color/holo_blue_dark</item>
          <item>@color/colorAccent</item>
          <item>@android:color/black</item>
        </integer-array>
        饼状图自定义属性
        <declare-styleable name="zqxchart_pie">
            <!-- 间隔角度 -->
            <attr name="separationDegree" format="integer"/>
            <!-- 文字颜色 -->
            <attr name="textSize" format="integer"/>
            <!-- 文字颜色 -->
            <attr name="textColor" format="color"/>
            <!-- 动画效果 -->
            <attr name="panimType">
                <enum name="NONE" value="-1"></enum>
                <!--enum name="TRANSLATE" value="0"></enum-->
                <enum name="ALPHA" value="1"></enum>
            </attr>
        </declare-styleable>
  
待完成
======
折线图和柱状图的多组数据显示。