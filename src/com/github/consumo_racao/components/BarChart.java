package com.github.consumo_racao.components;

import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class BarChart extends JPanel {

    private final JFreeChart chart;

    public BarChart() {
        super(new CardLayout());
        DefaultCategoryDataset data = new DefaultCategoryDataset();

        chart = ChartFactory.createBarChart("", "", "", data);

        this.add(new ChartPanel(chart));
    }

    public void setData(CategoryDataset dataSet) {
        chart.getCategoryPlot().setDataset(dataSet);
        chart.fireChartChanged();
    }

    public void setChartTitle(String title) {
        chart.getTitle().setText(title);
        chart.fireChartChanged();
    }

    public void setChartTitleColor(Color color) {
        chart.getTitle().setPaint(color);
        chart.fireChartChanged();

    }

    public void setChartColor(Color color) {
        chart.setBackgroundPaint(color);
        chart.getCategoryPlot().setBackgroundPaint(color);
        chart.fireChartChanged();
    }

    public void setChartLabelsColor(Color color) {
        CategoryAxis domainAxis = chart.getCategoryPlot().getDomainAxis();
        ValueAxis rangeAxis = chart.getCategoryPlot().getRangeAxis();
        domainAxis.setLabelPaint(color);
        domainAxis.setTickLabelPaint(color);
        domainAxis.setTickMarkPaint(color);
        rangeAxis.setLabelPaint(color);
        rangeAxis.setTickLabelPaint(color);
        rangeAxis.setTickMarkPaint(color);
        domainAxis.configure();
        rangeAxis.configure();
        chart.fireChartChanged();
    }

    public void setChartLabelsVisible(boolean visible) {
        CategoryAxis domain = chart.getCategoryPlot().getDomainAxis();
        ValueAxis range = chart.getCategoryPlot().getRangeAxis();
        domain.setTickLabelsVisible(visible);
        domain.setTickMarksVisible(visible);
        range.setTickLabelsVisible(visible);
        range.setTickMarksVisible(visible);
        domain.configure();
        range.configure();
        chart.fireChartChanged();
    }

    public void setChartLabelsAngleDown_45() {
        chart.getCategoryPlot().getDomainAxis().setCategoryLabelPositions(CategoryLabelPositions.DOWN_45);
        chart.getCategoryPlot().getDomainAxis().configure();
        chart.fireChartChanged();
    }

    public void setChartMarksVisible(boolean visible) {
        CategoryItemRenderer rend = chart.getCategoryPlot().getRenderer();
        rend.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        rend.setBaseItemLabelsVisible(visible);
        chart.fireChartChanged();
    }

    public void setChartMarksColor(Color color) {
        CategoryItemRenderer rend = chart.getCategoryPlot().getRenderer();
        rend.setBaseItemLabelPaint(color);
        chart.fireChartChanged();
    }

    public void setChartSeriesColor(Color color) {
        CategoryItemRenderer rend = chart.getCategoryPlot().getRenderer();
        rend.setSeriesPaint(0, color);
        chart.fireChartChanged();
    }

    public void setChartSeriesVisibleOnLegend(boolean visible) {
        CategoryItemRenderer rend = chart.getCategoryPlot().getRenderer();
        rend.setSeriesVisibleInLegend(0, visible);
        chart.fireChartChanged();

    }
}
