/*
   Copyright 2009-2022 PrimeTek.

   Licensed under PrimeFaces Commercial License, Version 1.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

   Licensed under PrimeFaces Commercial License, Version 1.0 (the "License");

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */
package org.primefaces.siberia.view;

import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.axes.cartesian.CartesianScales;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearAxes;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearTicks;
import org.primefaces.model.charts.axes.radial.RadialScales;
import org.primefaces.model.charts.axes.radial.linear.RadialLinearAngleLines;
import org.primefaces.model.charts.axes.radial.linear.RadialLinearPointLabels;
import org.primefaces.model.charts.axes.radial.linear.RadialLinearTicks;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.bar.BarChartOptions;
import org.primefaces.model.charts.bubble.BubbleChartDataSet;
import org.primefaces.model.charts.bubble.BubbleChartModel;
import org.primefaces.model.charts.data.BubblePoint;
import org.primefaces.model.charts.data.NumericPoint;
import org.primefaces.model.charts.donut.DonutChartDataSet;
import org.primefaces.model.charts.donut.DonutChartModel;
import org.primefaces.model.charts.hbar.HorizontalBarChartDataSet;
import org.primefaces.model.charts.hbar.HorizontalBarChartModel;
import org.primefaces.model.charts.line.LineChartDataSet;
import org.primefaces.model.charts.line.LineChartModel;
import org.primefaces.model.charts.line.LineChartOptions;
import org.primefaces.model.charts.optionconfig.elements.Elements;
import org.primefaces.model.charts.optionconfig.elements.ElementsLine;
import org.primefaces.model.charts.optionconfig.legend.Legend;
import org.primefaces.model.charts.optionconfig.legend.LegendLabel;
import org.primefaces.model.charts.optionconfig.title.Title;
import org.primefaces.model.charts.optionconfig.tooltip.Tooltip;
import org.primefaces.model.charts.pie.PieChartDataSet;
import org.primefaces.model.charts.pie.PieChartModel;
import org.primefaces.model.charts.polar.PolarAreaChartDataSet;
import org.primefaces.model.charts.polar.PolarAreaChartModel;
import org.primefaces.model.charts.radar.RadarChartDataSet;
import org.primefaces.model.charts.radar.RadarChartModel;
import org.primefaces.model.charts.radar.RadarChartOptions;
import org.primefaces.model.charts.scatter.ScatterChartModel;

import tec.inf.javaEE.lab2023.business.GraficasBusinessLocal;
import tec.inf.javaEE.lab2023.dto.GraficasDTO;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.primefaces.model.charts.optionconfig.animation.Animation;

@Named
@RequestScoped
public class ChartDemoView implements Serializable {

	@EJB
	GraficasBusinessLocal graficasService;
	
    private PieChartModel pieModel;
    
    private PieChartModel pieViajesRubros;
    private PieChartModel pieViajesTiposdeCarga;
    private PieChartModel pieVolumenesdeCargaRubros;

    private PolarAreaChartModel polarAreaModel;

    private LineChartModel lineModel;

    private LineChartModel cartesianLinerModel;

    private BarChartModel barModel;
    
    private BarChartModel barEmpresasViajes;
    private BarChartModel barEmpresasVehiculos;
    private BarChartModel barUsuariosTipos;
    private BarChartModel barVehiculosEmpresas;

    private BarChartModel barModel2;

    private HorizontalBarChartModel hbarModel;

    private BarChartModel stackedBarModel;

    private BarChartModel stackedGroupBarModel;

    private RadarChartModel radarModel;

    private RadarChartModel radarModel2;

    private BubbleChartModel bubbleModel;

    private BarChartModel mixedModel;

    private DonutChartModel donutModel;

    private ScatterChartModel scatterModel;

    @PostConstruct
    public void init() {
        createPieViajesRubros();
        createPieViajesTiposdeCarga();
        createPieVolumenesdeCargaRubros();
    	createBarEmpresasVehiculos();
    	createBarEmpresasViajes();
    	createBarUsuariosTipos();
    }
    
    public void createPieViajesRubros() {
    	pieViajesRubros = new PieChartModel();
        ChartData data = new ChartData();

        PieChartDataSet dataSet = new PieChartDataSet();
        
        List<GraficasDTO> listData = graficasService.ViajeRubro();
        List<Number> values = new ArrayList<>();
        List<String> labels = new ArrayList<>();
        for(GraficasDTO g: listData) {
        	values.add(g.getCantidad());
        	labels.add(g.getData());
        }
        
        dataSet.setData(values);

        List<String> bgColors = new ArrayList<>();
        bgColors.add("rgba(128, 0, 0, 0.2)");       // Rojo oscuro con transparencia
        bgColors.add("rgba(0, 128, 0, 0.2)");       // Verde oscuro con transparencia
        bgColors.add("rgba(0, 0, 128, 0.2)");       // Azul oscuro con transparencia
        bgColors.add("rgba(128, 128, 0, 0.2)");     // Amarillo oscuro con transparencia
        bgColors.add("rgba(128, 0, 128, 0.2)");     // Magenta oscuro con transparencia
        bgColors.add("rgba(0, 128, 128, 0.2)");     // Cian oscuro con transparencia
        bgColors.add("rgba(64, 0, 0, 0.2)");        // Rojo muy oscuro con transparencia
        bgColors.add("rgba(0, 64, 0, 0.2)");        // Verde muy oscuro con transparencia
        bgColors.add("rgba(0, 0, 64, 0.2)");        // Azul muy oscuro con transparencia
        bgColors.add("rgba(64, 64, 0, 0.2)");       // Amarillo muy oscuro con transparencia
        
        List<String> borderColor = new ArrayList<>();
        borderColor.add("rgba(128, 0, 0)");       // Rojo oscuro con transparencia
        borderColor.add("rgba(0, 128, 0)");       // Verde oscuro con transparencia
        borderColor.add("rgba(0, 0, 128)");       // Azul oscuro con transparencia
        borderColor.add("rgba(128, 128, 0)");     // Amarillo oscuro con transparencia
        borderColor.add("rgba(128, 0, 128)");     // Magenta oscuro con transparencia
        borderColor.add("rgba(0, 128, 128)");     // Cian oscuro con transparencia
        borderColor.add("rgba(64, 0, 0)");        // Rojo muy oscuro con transparencia
        borderColor.add("rgba(0, 64, 0)");        // Verde muy oscuro con transparencia
        borderColor.add("rgba(0, 0, 64)");        // Azul muy oscuro con transparencia
        borderColor.add("rgba(64, 64, 0)");
        dataSet.setBorderColor(borderColor);



        dataSet.setBackgroundColor(bgColors);

        data.addChartDataSet(dataSet);
        data.setLabels(labels);

        pieViajesRubros.setData(data);
    }
    
	public void createPieViajesTiposdeCarga() {
		pieViajesTiposdeCarga = new PieChartModel();
        ChartData data = new ChartData();

        PieChartDataSet dataSet = new PieChartDataSet();
        
        List<GraficasDTO> listData = graficasService.ViajeTiposDeCarga();
        List<Number> values = new ArrayList<>();
        List<String> labels = new ArrayList<>();
        for(GraficasDTO g: listData) {
        	values.add(g.getCantidad());
        	labels.add(g.getData());
        }
        
        dataSet.setData(values);

        List<String> bgColors = new ArrayList<>();
        bgColors.add("rgba(128, 0, 0, 0.2)");       // Rojo oscuro con transparencia
        bgColors.add("rgba(0, 128, 0, 0.2)");       // Verde oscuro con transparencia
        bgColors.add("rgba(0, 0, 128, 0.2)");       // Azul oscuro con transparencia
        bgColors.add("rgba(128, 128, 0, 0.2)");     // Amarillo oscuro con transparencia
        bgColors.add("rgba(128, 0, 128, 0.2)");     // Magenta oscuro con transparencia
        bgColors.add("rgba(0, 128, 128, 0.2)");     // Cian oscuro con transparencia
        bgColors.add("rgba(64, 0, 0, 0.2)");        // Rojo muy oscuro con transparencia
        bgColors.add("rgba(0, 64, 0, 0.2)");        // Verde muy oscuro con transparencia
        bgColors.add("rgba(0, 0, 64, 0.2)");        // Azul muy oscuro con transparencia
        bgColors.add("rgba(64, 64, 0, 0.2)");       // Amarillo muy oscuro con transparencia
        
        List<String> borderColor = new ArrayList<>();
        borderColor.add("rgba(128, 0, 0)");       // Rojo oscuro con transparencia
        borderColor.add("rgba(0, 128, 0)");       // Verde oscuro con transparencia
        borderColor.add("rgba(0, 0, 128)");       // Azul oscuro con transparencia
        borderColor.add("rgba(128, 128, 0)");     // Amarillo oscuro con transparencia
        borderColor.add("rgba(128, 0, 128)");     // Magenta oscuro con transparencia
        borderColor.add("rgba(0, 128, 128)");     // Cian oscuro con transparencia
        borderColor.add("rgba(64, 0, 0)");        // Rojo muy oscuro con transparencia
        borderColor.add("rgba(0, 64, 0)");        // Verde muy oscuro con transparencia
        borderColor.add("rgba(0, 0, 64)");        // Azul muy oscuro con transparencia
        borderColor.add("rgba(64, 64, 0)");
        dataSet.setBorderColor(borderColor);




        dataSet.setBackgroundColor(bgColors);

        data.addChartDataSet(dataSet);
        data.setLabels(labels);

        pieViajesTiposdeCarga.setData(data);
	}
	
	public void createPieVolumenesdeCargaRubros() {
		pieVolumenesdeCargaRubros = new PieChartModel();
        ChartData data = new ChartData();

        PieChartDataSet dataSet = new PieChartDataSet();
        
        List<GraficasDTO> listData = graficasService.RubroVolumen();
        List<Number> values = new ArrayList<>();
        List<String> labels = new ArrayList<>();
        for(GraficasDTO g: listData) {
        	values.add(g.getCantidad());
        	labels.add(g.getData());
        }
        
        dataSet.setData(values);

        List<String> bgColors = new ArrayList<>();
        bgColors.add("rgba(128, 0, 0, 0.2)");       // Rojo oscuro con transparencia
        bgColors.add("rgba(0, 128, 0, 0.2)");       // Verde oscuro con transparencia
        bgColors.add("rgba(0, 0, 128, 0.2)");       // Azul oscuro con transparencia
        bgColors.add("rgba(128, 128, 0, 0.2)");     // Amarillo oscuro con transparencia
        bgColors.add("rgba(128, 0, 128, 0.2)");     // Magenta oscuro con transparencia
        bgColors.add("rgba(0, 128, 128, 0.2)");     // Cian oscuro con transparencia
        bgColors.add("rgba(64, 0, 0, 0.2)");        // Rojo muy oscuro con transparencia
        bgColors.add("rgba(0, 64, 0, 0.2)");        // Verde muy oscuro con transparencia
        bgColors.add("rgba(0, 0, 64, 0.2)");        // Azul muy oscuro con transparencia
        bgColors.add("rgba(64, 64, 0, 0.2)");       // Amarillo muy oscuro con transparencia
        
        List<String> borderColor = new ArrayList<>();
        borderColor.add("rgba(128, 0, 0)");       // Rojo oscuro con transparencia
        borderColor.add("rgba(0, 128, 0)");       // Verde oscuro con transparencia
        borderColor.add("rgba(0, 0, 128)");       // Azul oscuro con transparencia
        borderColor.add("rgba(128, 128, 0)");     // Amarillo oscuro con transparencia
        borderColor.add("rgba(128, 0, 128)");     // Magenta oscuro con transparencia
        borderColor.add("rgba(0, 128, 128)");     // Cian oscuro con transparencia
        borderColor.add("rgba(64, 0, 0)");        // Rojo muy oscuro con transparencia
        borderColor.add("rgba(0, 64, 0)");        // Verde muy oscuro con transparencia
        borderColor.add("rgba(0, 0, 64)");        // Azul muy oscuro con transparencia
        borderColor.add("rgba(64, 64, 0)");
        dataSet.setBorderColor(borderColor);


        dataSet.setBackgroundColor(bgColors);

        data.addChartDataSet(dataSet);
        data.setLabels(labels);

        pieVolumenesdeCargaRubros.setData(data);
	}
	
	public void createBarEmpresasViajes() {
    	barEmpresasViajes = new BarChartModel();
        ChartData data = new ChartData();

        BarChartDataSet barDataSet = new BarChartDataSet();
        barDataSet.setLabel("Datos");
        
        List<GraficasDTO> listData = graficasService.EmpresaViaje();
        List<Number> values = new ArrayList<>();
        List<String> labels = new ArrayList<>();
        for(GraficasDTO g: listData) {
        	values.add(g.getCantidad());
        	labels.add(g.getData());
        }

        barDataSet.setData(values);
        data.setLabels(labels);

        List<String> bgColor = new ArrayList<>();
        bgColor.add("rgba(255, 99, 132, 0.2)");
        bgColor.add("rgba(255, 159, 64, 0.2)");
        bgColor.add("rgba(255, 205, 86, 0.2)");
        bgColor.add("rgba(75, 192, 192, 0.2)");
        bgColor.add("rgba(54, 162, 235, 0.2)");
        barDataSet.setBackgroundColor(bgColor);

        List<String> borderColor = new ArrayList<>();
        borderColor.add("rgb(255, 99, 132)");
        borderColor.add("rgb(255, 159, 64)");
        borderColor.add("rgb(255, 205, 86)");
        borderColor.add("rgb(75, 192, 192)");
        borderColor.add("rgb(54, 162, 235)");
        barDataSet.setBorderColor(borderColor);
        barDataSet.setBorderWidth(1);

        data.addChartDataSet(barDataSet);
        barEmpresasViajes.setData(data);

        //Options
        BarChartOptions options = new BarChartOptions();
        CartesianScales cScales = new CartesianScales();
        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
        linearAxes.setOffset(true);
        CartesianLinearTicks ticks = new CartesianLinearTicks();
        ticks.setBeginAtZero(true);
        linearAxes.setTicks(ticks);
        cScales.addYAxesData(linearAxes);
        options.setScales(cScales);

        Title title = new Title();
        title.setDisplay(true);
        title.setText("Grafica de Barras");
        options.setTitle(title);

        Legend legend = new Legend();
        legend.setDisplay(true);
        legend.setPosition("top");
        LegendLabel legendLabels = new LegendLabel();
        legendLabels.setFontStyle("bold");
        legendLabels.setFontColor("#2980B9");
        legendLabels.setFontSize(24);
        legend.setLabels(legendLabels);
        options.setLegend(legend);

        // disable animation
        Animation animation = new Animation();
        animation.setDuration(0);
        options.setAnimation(animation);

        barEmpresasViajes.setOptions(options);
    }
    
    public void createBarEmpresasVehiculos() {
    	barEmpresasVehiculos = new BarChartModel();
        ChartData data = new ChartData();

        BarChartDataSet barDataSet = new BarChartDataSet();
        barDataSet.setLabel("Datos");
        
        List<GraficasDTO> listData = graficasService.EmpresaVehiculo();
        List<Number> values = new ArrayList<>();
        List<String> labels = new ArrayList<>();
        for(GraficasDTO g: listData) {
        	values.add(g.getCantidad());
        	labels.add(g.getData());
        }

        barDataSet.setData(values);
        data.setLabels(labels);

        List<String> bgColor = new ArrayList<>();
        bgColor.add("rgba(255, 99, 132, 0.2)");
        bgColor.add("rgba(255, 159, 64, 0.2)");
        bgColor.add("rgba(255, 205, 86, 0.2)");
        bgColor.add("rgba(75, 192, 192, 0.2)");
        bgColor.add("rgba(54, 162, 235, 0.2)");
        barDataSet.setBackgroundColor(bgColor);

        List<String> borderColor = new ArrayList<>();
        borderColor.add("rgb(255, 99, 132)");
        borderColor.add("rgb(255, 159, 64)");
        borderColor.add("rgb(255, 205, 86)");
        borderColor.add("rgb(75, 192, 192)");
        borderColor.add("rgb(54, 162, 235)");
        barDataSet.setBorderColor(borderColor);
        barDataSet.setBorderWidth(1);

        data.addChartDataSet(barDataSet);
        barEmpresasVehiculos.setData(data);

        //Options
        BarChartOptions options = new BarChartOptions();
        CartesianScales cScales = new CartesianScales();
        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
        linearAxes.setOffset(true);
        CartesianLinearTicks ticks = new CartesianLinearTicks();
        ticks.setBeginAtZero(true);
        linearAxes.setTicks(ticks);
        cScales.addYAxesData(linearAxes);
        options.setScales(cScales);

        Title title = new Title();
        title.setDisplay(true);
        title.setText("Grafica de Barras");
        options.setTitle(title);

        Legend legend = new Legend();
        legend.setDisplay(true);
        legend.setPosition("top");
        LegendLabel legendLabels = new LegendLabel();
        legendLabels.setFontStyle("bold");
        legendLabels.setFontColor("#2980B9");
        legendLabels.setFontSize(24);
        legend.setLabels(legendLabels);
        options.setLegend(legend);

        // disable animation
        Animation animation = new Animation();
        animation.setDuration(0);
        options.setAnimation(animation);

        barEmpresasVehiculos.setOptions(options);
    }
    
    public void createBarUsuariosTipos() {
    	barUsuariosTipos = new BarChartModel();
        ChartData data = new ChartData();

        BarChartDataSet barDataSet = new BarChartDataSet();
        barDataSet.setLabel("Datos");
        
        
        List<GraficasDTO> listData = graficasService.UsuarioTipo();
        List<Number> values = new ArrayList<>();
        List<String> labels = new ArrayList<>();
        for(GraficasDTO g: listData) {
        	values.add(g.getCantidad());
        	labels.add(g.getData());
        }
        barDataSet.setData(values);

        List<String> bgColor = new ArrayList<>();
        bgColor.add("rgba(255, 99, 132, 0.2)");
        bgColor.add("rgba(255, 159, 64, 0.2)");
        bgColor.add("rgba(255, 205, 86, 0.2)");
        bgColor.add("rgba(75, 192, 192, 0.2)");
        bgColor.add("rgba(54, 162, 235, 0.2)");
        barDataSet.setBackgroundColor(bgColor);

        List<String> borderColor = new ArrayList<>();
        borderColor.add("rgb(255, 99, 132)");
        borderColor.add("rgb(255, 159, 64)");
        borderColor.add("rgb(255, 205, 86)");
        borderColor.add("rgb(75, 192, 192)");
        borderColor.add("rgb(54, 162, 235)");
        barDataSet.setBorderColor(borderColor);
        barDataSet.setBorderWidth(1);

        data.addChartDataSet(barDataSet);
        data.setLabels(labels);
        barUsuariosTipos.setData(data);

        //Options
        BarChartOptions options = new BarChartOptions();
        CartesianScales cScales = new CartesianScales();
        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
        linearAxes.setOffset(true);
        CartesianLinearTicks ticks = new CartesianLinearTicks();
        ticks.setBeginAtZero(true);
        linearAxes.setTicks(ticks);
        cScales.addYAxesData(linearAxes);
        options.setScales(cScales);

        Title title = new Title();
        title.setDisplay(true);
        title.setText("Grafica de Barras");
        options.setTitle(title);

        Legend legend = new Legend();
        legend.setDisplay(true);
        legend.setPosition("top");
        LegendLabel legendLabels = new LegendLabel();
        legendLabels.setFontStyle("bold");
        legendLabels.setFontColor("#2980B9");
        legendLabels.setFontSize(24);
        legend.setLabels(legendLabels);
        options.setLegend(legend);

        // disable animation
        Animation animation = new Animation();
        animation.setDuration(0);
        options.setAnimation(animation);

        barUsuariosTipos.setOptions(options);
    }

    public void createRadarModel2() {
        radarModel2 = new RadarChartModel();
        ChartData data = new ChartData();

        RadarChartDataSet radarDataSet = new RadarChartDataSet();
        radarDataSet.setLabel("P.Practitioner");
        radarDataSet.setTension(0.1);
        radarDataSet.setBackgroundColor("rgba(102, 153, 204, 0.2)");
        radarDataSet.setBorderColor("rgba(102, 153, 204, 1)");
        radarDataSet.setPointBackgroundColor("rgba(102, 153, 204, 1)");
        radarDataSet.setPointBorderColor("#fff");
        radarDataSet.setPointHoverRadius(5);
        radarDataSet.setPointHoverBackgroundColor("#fff");
        radarDataSet.setPointHoverBorderColor("rgba(102, 153, 204, 1)");
        List<Number> dataVal = new ArrayList<>();
        dataVal.add(2);
        dataVal.add(3);
        dataVal.add(2);
        dataVal.add(1);
        dataVal.add(3);
        radarDataSet.setData(dataVal);

        RadarChartDataSet radarDataSet2 = new RadarChartDataSet();
        radarDataSet2.setLabel("P.Manager");
        radarDataSet2.setTension(0.1);
        radarDataSet2.setBackgroundColor("rgba(255, 204, 102, 0.2)");
        radarDataSet2.setBorderColor("rgba(255, 204, 102, 1)");
        radarDataSet2.setPointBackgroundColor("rgba(255, 204, 102, 1)");
        radarDataSet2.setPointBorderColor("#fff");
        radarDataSet2.setPointHoverRadius(5);
        radarDataSet2.setPointHoverBackgroundColor("#fff");
        radarDataSet2.setPointHoverBorderColor("rgba(255, 204, 102, 1)");
        List<Number> dataVal2 = new ArrayList<>();
        dataVal2.add(2);
        dataVal2.add(3);
        dataVal2.add(3);
        dataVal2.add(2);
        dataVal2.add(3);
        radarDataSet2.setData(dataVal2);

        data.addChartDataSet(radarDataSet);
        data.addChartDataSet(radarDataSet2);

        List<List<String>> labels = new ArrayList<>();
        labels.add(new ArrayList(Arrays.asList("Process", "Excellence")));
        labels.add(new ArrayList(Arrays.asList("Problem", "Solving")));
        labels.add(new ArrayList(Arrays.asList("Facilitation")));
        labels.add(new ArrayList(Arrays.asList("Project", "Mgmt")));
        labels.add(new ArrayList(Arrays.asList("Change", "Mgmt")));
        data.setLabels(labels);

        /* Options */
        RadarChartOptions options = new RadarChartOptions();
        RadialScales rScales = new RadialScales();

        RadialLinearAngleLines angleLines = new RadialLinearAngleLines();
        angleLines.setDisplay(true);
        angleLines.setLineWidth(0.5);
        angleLines.setColor("rgba(128, 128, 128, 0.2)");
        rScales.setAngleLines(angleLines);

        RadialLinearPointLabels pointLabels = new RadialLinearPointLabels();
        pointLabels.setFontSize(14);
        pointLabels.setFontStyle("300");
        pointLabels.setFontColor("rgba(204, 204, 204, 1)");
        pointLabels.setFontFamily("Lato, sans-serif");

        RadialLinearTicks ticks = new RadialLinearTicks();
        ticks.setBeginAtZero(true);
        ticks.setMaxTicksLimit(3);
        ticks.setMin(0);
        ticks.setMax(3);
        ticks.setDisplay(false);

        options.setScales(rScales);

        radarModel2.setOptions(options);
        radarModel2.setData(data);
        radarModel2.setExtender("skinRadarChart");
    }

    public void createBubbleModel() {
        bubbleModel = new BubbleChartModel();
        ChartData data = new ChartData();

        BubbleChartDataSet dataSet = new BubbleChartDataSet();
        List<BubblePoint> values = new ArrayList<>();
        values.add(new BubblePoint(20, 30, 15));
        values.add(new BubblePoint(40, 10, 10));
        dataSet.setData(values);
        dataSet.setBackgroundColor("rgb(255, 99, 132)");
        dataSet.setLabel("First Dataset");
        data.addChartDataSet(dataSet);
        bubbleModel.setData(data);
    }

    public void createMixedModel() {
        mixedModel = new BarChartModel();
        ChartData data = new ChartData();

        BarChartDataSet dataSet = new BarChartDataSet();
        List<Number> values = new ArrayList<>();
        values.add(10);
        values.add(20);
        values.add(30);
        values.add(40);
        dataSet.setData(values);
        dataSet.setLabel("Bar Dataset");
        dataSet.setBorderColor("rgb(255, 99, 132)");
        dataSet.setBackgroundColor("rgba(255, 99, 132, 0.2)");

        LineChartDataSet dataSet2 = new LineChartDataSet();
        List<Object> values2 = new ArrayList<>();
        values2.add(50);
        values2.add(50);
        values2.add(50);
        values2.add(50);
        dataSet2.setData(values2);
        dataSet2.setLabel("Line Dataset");
        dataSet2.setFill(false);
        dataSet2.setBorderColor("rgb(54, 162, 235)");

        data.addChartDataSet(dataSet);
        data.addChartDataSet(dataSet2);

        List<String> labels = new ArrayList<>();
        labels.add("January");
        labels.add("February");
        labels.add("March");
        labels.add("April");
        data.setLabels(labels);

        mixedModel.setData(data);

        //Options
        BarChartOptions options = new BarChartOptions();
        CartesianScales cScales = new CartesianScales();
        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
        linearAxes.setOffset(true);
        CartesianLinearTicks ticks = new CartesianLinearTicks();
        ticks.setBeginAtZero(true);
        linearAxes.setTicks(ticks);

        cScales.addYAxesData(linearAxes);
        options.setScales(cScales);
        mixedModel.setOptions(options);
    }

    public void createDonutModel() {
        donutModel = new DonutChartModel();
        ChartData data = new ChartData();

        DonutChartDataSet dataSet = new DonutChartDataSet();
        List<Number> values = new ArrayList<>();
        values.add(300);
        values.add(50);
        values.add(100);
        dataSet.setData(values);

        List<String> bgColors = new ArrayList<>();
        bgColors.add("rgb(255, 99, 132)");
        bgColors.add("rgb(54, 162, 235)");
        bgColors.add("rgb(255, 205, 86)");
        dataSet.setBackgroundColor(bgColors);

        data.addChartDataSet(dataSet);
        List<String> labels = new ArrayList<>();
        labels.add("Red");
        labels.add("Blue");
        labels.add("Yellow");
        data.setLabels(labels);

        donutModel.setData(data);
    }

    public void itemSelect(ItemSelectEvent event) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Item selected",
                "Item Index: " + event.getItemIndex() + ", DataSet Index:" + event.getDataSetIndex());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public PieChartModel getPieModel() {
        return pieModel;
    }

    public void setPieModel(PieChartModel pieModel) {
        this.pieModel = pieModel;
    }

    public PolarAreaChartModel getPolarAreaModel() {
        return polarAreaModel;
    }

    public void setPolarAreaModel(PolarAreaChartModel polarAreaModel) {
        this.polarAreaModel = polarAreaModel;
    }

    public LineChartModel getLineModel() {
        return lineModel;
    }

    public void setLineModel(LineChartModel lineModel) {
        this.lineModel = lineModel;
    }

    public LineChartModel getCartesianLinerModel() {
        return cartesianLinerModel;
    }

    public void setCartesianLinerModel(LineChartModel cartesianLinerModel) {
        this.cartesianLinerModel = cartesianLinerModel;
    }

    public BarChartModel getBarModel() {
        return barModel;
    }

    public void setBarModel(BarChartModel barModel) {
        this.barModel = barModel;
    }

    public BarChartModel getBarModel2() {
        return barModel2;
    }

    public void setBarModel2(BarChartModel barModel2) {
        this.barModel2 = barModel2;
    }

    public HorizontalBarChartModel getHbarModel() {
        return hbarModel;
    }

    public void setHbarModel(HorizontalBarChartModel hbarModel) {
        this.hbarModel = hbarModel;
    }

    public BarChartModel getStackedBarModel() {
        return stackedBarModel;
    }

    public void setStackedBarModel(BarChartModel stackedBarModel) {
        this.stackedBarModel = stackedBarModel;
    }

    public BarChartModel getStackedGroupBarModel() {
        return stackedGroupBarModel;
    }

    public void setStackedGroupBarModel(BarChartModel stackedGroupBarModel) {
        this.stackedGroupBarModel = stackedGroupBarModel;
    }

    public RadarChartModel getRadarModel() {
        return radarModel;
    }

    public void setRadarModel(RadarChartModel radarModel) {
        this.radarModel = radarModel;
    }

    public RadarChartModel getRadarModel2() {
        return radarModel2;
    }

    public void setRadarModel2(RadarChartModel radarModel2) {
        this.radarModel2 = radarModel2;
    }

    public BubbleChartModel getBubbleModel() {
        return bubbleModel;
    }

    public void setBubbleModel(BubbleChartModel bubbleModel) {
        this.bubbleModel = bubbleModel;
    }

    public BarChartModel getMixedModel() {
        return mixedModel;
    }

    public void setMixedModel(BarChartModel mixedModel) {
        this.mixedModel = mixedModel;
    }

    public DonutChartModel getDonutModel() {
        return donutModel;
    }

    public void setDonutModel(DonutChartModel donutModel) {
        this.donutModel = donutModel;
    }

    public ScatterChartModel getScatterModel() {
        return scatterModel;
    }

    public void setScatterModel(ScatterChartModel scatterModel) {
        this.scatterModel = scatterModel;
    }

	public BarChartModel getBarEmpresasViajes() {
		return barEmpresasViajes;
	}

	public void setBarEmpresasViajes(BarChartModel barEmpresasViajes) {
		this.barEmpresasViajes = barEmpresasViajes;
	}

	public BarChartModel getBarUsuariosTipos() {
		return barUsuariosTipos;
	}

	public void setBarUsuariosTipos(BarChartModel barUsuariosTipos) {
		this.barUsuariosTipos = barUsuariosTipos;
	}

	public BarChartModel getBarVehiculosEmpresas() {
		return barVehiculosEmpresas;
	}

	public void setBarVehiculosEmpresas(BarChartModel barVehiculosEmpresas) {
		this.barVehiculosEmpresas = barVehiculosEmpresas;
	}

	public PieChartModel getPieViajesRubros() {
		return pieViajesRubros;
	}

	public void setPieViajesRubros(PieChartModel pieViajesRubros) {
		this.pieViajesRubros = pieViajesRubros;
	}

	public PieChartModel getPieViajesTiposdeCarga() {
		return pieViajesTiposdeCarga;
	}

	public void setPieViajesTiposdeCarga(PieChartModel pieViajesTiposdeCarga) {
		this.pieViajesTiposdeCarga = pieViajesTiposdeCarga;
	}

	public PieChartModel getPieVolumenesdeCargaRubros() {
		return pieVolumenesdeCargaRubros;
	}

	public void setPieVolumenesdeCargaRubros(PieChartModel pieVolumenesdeCargaRubros) {
		this.pieVolumenesdeCargaRubros = pieVolumenesdeCargaRubros;
	}

	public BarChartModel getBarEmpresasVehiculos() {
		return barEmpresasVehiculos;
	}

	public void setBarEmpresasVehiculos(BarChartModel barEmpresasVehiculos) {
		this.barEmpresasVehiculos = barEmpresasVehiculos;
	}
}
