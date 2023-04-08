package com.example.application.views.dashboard;

import com.example.application.data.service.CrmService;
import com.example.application.views.MainLayout;
import com.example.application.views.apex.PieChartExample;
import com.github.appreciated.apexcharts.ApexCharts;
import com.github.appreciated.apexcharts.ApexChartsBuilder;
import com.github.appreciated.apexcharts.config.builder.TitleSubtitleBuilder;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.charts.model.DataSeries;
import com.vaadin.flow.component.charts.model.DataSeriesItem;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;
import jakarta.annotation.security.PermitAll;

import java.util.List;
import java.util.stream.Collectors;

@PermitAll
@Route(value = "dashboard", layout = MainLayout.class)
@PageTitle("Dashboard | Vaadin CRM")
public class DashboardView extends VerticalLayout {
    private final CrmService service;

    public DashboardView(CrmService service) {
        this.service = service;
        addClassName("dashboard-view");
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        add(getContactStats(), getCompaniesChart());
    }

    private Component getContactStats() {
        Span stats = new Span(service.countContacts() + " contacts");
        stats.addClassNames(
                LumoUtility.FontSize.XLARGE,
                LumoUtility.Margin.Top.MEDIUM);
        return stats;
    }

//    private Chart getCompaniesChart() {
//        Chart chart = new Chart(ChartType.PIE);
//
//        DataSeries dataSeries = new DataSeries();
//        service.findAllCompanies().forEach(company ->
//                dataSeries.add(new DataSeriesItem(company.getName(), company.getEmployeeCount())));
//        chart.getConfiguration().setSeries(dataSeries);
//        return chart;
//    }

    private Component getCompaniesChart() {

        DataSeries dataSeries = new DataSeries();
        service.findAllCompanies().forEach(company ->
        dataSeries.add(new DataSeriesItem(company.getName(), company.getEmployeeCount())));

        List<String> labels = dataSeries.getData().stream().
                map(dataSeriesItem -> dataSeriesItem.getName()).collect(Collectors.toList());

        List<Double> values = dataSeries.getData().stream().
                map(dataSeriesItem -> dataSeriesItem.getY().doubleValue()).collect(Collectors.toList());

        ApexChartsBuilder builder =
                new PieChartExample(labels, values).withTitle(TitleSubtitleBuilder.get().withText("Prueba APEX").build());
        ApexCharts chart = builder.build();
        var chartPie = new Div(chart);
//        chartPie.setWidthFull();
//        chartPie.setMaxWidth("1100px");
        chartPie.addClassName("company-chart");
        return chartPie;
    }
}