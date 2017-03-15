package ru.scrumdev.sample.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.maps.client.LoadApi;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import ru.scrumdev.sample.client.maps.BasicMapWidget;
import ru.scrumdev.sample.client.model.Event;
import ru.scrumdev.sample.client.presenter.Presenter;

import javax.inject.Inject;
import java.util.ArrayList;

import com.google.gwt.maps.client.LoadApi;
import com.google.gwt.maps.client.LoadApi.LoadLibrary;

public class View extends Composite {
    interface MainPanelUiBinder extends UiBinder<Widget, View> {
    }

    private static MainPanelUiBinder ourUiBinder = GWT.create(MainPanelUiBinder.class);

    @UiField
    FlowPanel leftPanel;
    @UiField
    FlowPanel mapPanel;
    @UiField
    FlowPanel rightPanel;
    @UiField
    FlowPanel imagePanel;
    @UiField
    FlowPanel infoPanel;
    @UiField
    Label name;
    @UiField
    Label eventName;
    @UiField
    Label description;
    @UiField
    Label eventDescription;
    @UiField
    Label time;
    @UiField
    Label eventTime;
    @UiField
    Label date;
    @UiField
    Label eventDate;
    @UiField
    Button updateButton;
    private EventBus eventBus;
    private Presenter presenter;

    @Inject
    public View(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    public void createUI() {
        initWidget(ourUiBinder.createAndBindUi(this));
        setUI();
        loadMapApi();
        RootPanel.get("panelId").add(this);
    }

    public void bind() {
        updateButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                presenter.loadData();
            }
        });
    }

    private void loadMapApi() {
        boolean sensor = true;

        // load all the libs for use in the maps
        ArrayList<LoadLibrary> loadLibraries = new ArrayList<LoadApi.LoadLibrary>();
        loadLibraries.add(LoadLibrary.ADSENSE);
        loadLibraries.add(LoadLibrary.DRAWING);
        loadLibraries.add(LoadLibrary.GEOMETRY);
        loadLibraries.add(LoadLibrary.PANORAMIO);
        loadLibraries.add(LoadLibrary.PLACES);
        loadLibraries.add(LoadLibrary.WEATHER);
        loadLibraries.add(LoadLibrary.VISUALIZATION);

        Runnable onLoad = new Runnable() {
            @Override
            public void run() {
                draw();
            }
        };

        LoadApi.go(onLoad, loadLibraries, sensor);
    }

    public void draw() {
        BasicMapWidget wMap = new BasicMapWidget();
        addMapWidget(wMap);
    }

    private void addMapWidget(Widget widget) {
        mapPanel.add(widget);
    }

    public void setUI() {
        leftPanel.getElement().getStyle().setBorderColor("Gray");
        leftPanel.getElement().getStyle().setBorderStyle(Style.BorderStyle.SOLID);
        leftPanel.getElement().getStyle().setWidth(Window.getClientWidth() / 1.5, Style.Unit.PX);
        leftPanel.getElement().getStyle().setHeight(Window.getClientHeight() - 7, Style.Unit.PX);

        mapPanel.getElement().getStyle().setBorderColor("Gray");
        mapPanel.getElement().getStyle().setBorderStyle(Style.BorderStyle.SOLID);
        mapPanel.getElement().getStyle().setWidth(Window.getClientWidth() / 1.5, Style.Unit.PX);
        mapPanel.getElement().getStyle().setHeight(Window.getClientHeight() - 7, Style.Unit.PX);

        rightPanel.getElement().getStyle().setBorderColor("Gray");
        rightPanel.getElement().getStyle().setBorderStyle(Style.BorderStyle.SOLID);
        rightPanel.getElement().getStyle().setWidth(0.33 * Window.getClientWidth() - 14, Style.Unit.PX);
        rightPanel.getElement().getStyle().setHeight(Window.getClientHeight() - 7, Style.Unit.PX);

        imagePanel.getElement().getStyle().setBorderColor("Gray");
        imagePanel.getElement().getStyle().setBorderStyle(Style.BorderStyle.SOLID);
        imagePanel.getElement().getStyle().setWidth(0.33 * Window.getClientWidth() - 15, Style.Unit.PX);
        imagePanel.getElement().getStyle().setHeight(Window.getClientHeight() / 2.5, Style.Unit.PX);

        infoPanel.getElement().getStyle().setBorderColor("Gray");
        infoPanel.getElement().getStyle().setBorderStyle(Style.BorderStyle.SOLID);
        infoPanel.getElement().getStyle().setWidth(0.33 * Window.getClientWidth() - 14, Style.Unit.PX);
        infoPanel.getElement().getStyle().setHeight(3.0 / 5.0 * Window.getClientHeight() - 15, Style.Unit.PX);

        eventName.getElement().getStyle().setPaddingTop(30, Style.Unit.PX);
        eventName.getElement().getStyle().setPaddingLeft(15, Style.Unit.PX);
        name.getElement().getStyle().setPaddingTop(15, Style.Unit.PX);
        name.getElement().getStyle().setPaddingLeft(15, Style.Unit.PX);

        eventDescription.getElement().getStyle().setPaddingTop(30, Style.Unit.PX);
        eventDescription.getElement().getStyle().setPaddingLeft(15, Style.Unit.PX);
        description.getElement().getStyle().setPaddingTop(15, Style.Unit.PX);
        description.getElement().getStyle().setPaddingLeft(15, Style.Unit.PX);

        eventTime.getElement().getStyle().setPaddingTop(30, Style.Unit.PX);
        eventTime.getElement().getStyle().setPaddingLeft(15, Style.Unit.PX);
        time.getElement().getStyle().setPaddingTop(15, Style.Unit.PX);
        time.getElement().getStyle().setPaddingLeft(15, Style.Unit.PX);

        eventDate.getElement().getStyle().setPaddingTop(30, Style.Unit.PX);
        eventDate.getElement().getStyle().setPaddingLeft(15, Style.Unit.PX);
        date.getElement().getStyle().setPaddingTop(15, Style.Unit.PX);
        date.getElement().getStyle().setPaddingLeft(15, Style.Unit.PX);
    }

    public void updateFields(Event event) {
        this.name.setText(event.getName());
        this.description.setText(event.getDescription());
        this.date.setText(event.getDate().toString());
        this.time.setText(event.getTime());
    }
}