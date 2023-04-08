package com.example.application.views.apex;

import com.github.appreciated.apexcharts.ApexChartsBuilder;
import com.github.appreciated.apexcharts.config.builder.ChartBuilder;
import com.github.appreciated.apexcharts.config.builder.LegendBuilder;
import com.github.appreciated.apexcharts.config.chart.Type;
import com.github.appreciated.apexcharts.config.legend.Position;

import java.util.List;

public class PieChartExample extends ApexChartsBuilder {

    public PieChartExample(List<String> labels, List<Double> values) {
        withChart(ChartBuilder.get().withType(Type.PIE).build())
                .withLabels(labels.toArray(String[]::new))
                .withLegend(LegendBuilder.get()
                        .withPosition(Position.BOTTOM)
                        .build())
                .withSeries(values.toArray(Double[]::new));
    }
}