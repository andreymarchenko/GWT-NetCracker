package ru.happyMoments.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.maps.client.LoadApi;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import javax.inject.Inject;
import java.util.ArrayList;
import com.google.gwt.maps.client.LoadApi.LoadLibrary;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import ru.happyMoments.client.entity.Event;
import ru.happyMoments.client.maps.BasicMapWidget;
import ru.happyMoments.client.presenter.Presenter;

public class View extends Composite {
    interface MainPanelUiBinder extends UiBinder<Widget, View> {
    }

    private static MainPanelUiBinder ourUiBinder = GWT.create(MainPanelUiBinder.class);

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
    @UiField
    Image image;

    private EventBus eventBus;
    private Presenter presenter;

    private static final double MAP_PANEL_WIDTH_REDUCTION = 1.36;
    private static final double MAP_PANEL_HEIGHT_REDUCTION = 20;
    private static final double RIGHT_PANEL_WIDTH_REDUCTION = 0.25;
    private static final double IMAGE_PANEL_HEIGHT_REDUCTION = 2.5;
    private static final double INFO_PANEL_HEIGHT_REDUCTION = 0.55;
    private static final double TOP_PADDING_UPPER_ELEMENT = 30;
    private static final double TOP_PADDING_LOWER_ELEMENT = 30;
    private static final double TOP_PADDING_LEFT = 30;

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
        RootPanel.get("root").add(this);
    }

    public void bind() {
        updateButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                presenter.loadData();
                image.setUrl("http://www.google.com/images/logo.gif");
                image.setVisibleRect(-35, -80,
                        (int) (0.25 * Window.getClientWidth()),
                        (int) (Window.getClientHeight() / 2.5));
                imagePanel.add(image);
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

        mapPanel.getElement().getStyle().setWidth(Window.getClientWidth() / MAP_PANEL_WIDTH_REDUCTION, Style.Unit.PX);
        mapPanel.getElement().getStyle().setHeight(Window.getClientHeight() - MAP_PANEL_HEIGHT_REDUCTION, Style.Unit.PX);

        rightPanel.getElement().getStyle().setWidth(RIGHT_PANEL_WIDTH_REDUCTION * Window.getClientWidth(), Style.Unit.PX);
        rightPanel.getElement().getStyle().setHeight(Window.getClientHeight() - MAP_PANEL_HEIGHT_REDUCTION, Style.Unit.PX);

        imagePanel.getElement().getStyle().setBorderColor("Gray");
        imagePanel.getElement().getStyle().setBorderStyle(Style.BorderStyle.SOLID);
        imagePanel.getElement().getStyle().setWidth(RIGHT_PANEL_WIDTH_REDUCTION * Window.getClientWidth(), Style.Unit.PX);
        imagePanel.getElement().getStyle().setHeight(Window.getClientHeight() / IMAGE_PANEL_HEIGHT_REDUCTION, Style.Unit.PX);

        infoPanel.getElement().getStyle().setBorderColor("Gray");
        infoPanel.getElement().getStyle().setBorderStyle(Style.BorderStyle.SOLID);
        infoPanel.getElement().getStyle().setWidth(RIGHT_PANEL_WIDTH_REDUCTION * Window.getClientWidth(), Style.Unit.PX);
        infoPanel.getElement().getStyle().setHeight(INFO_PANEL_HEIGHT_REDUCTION * Window.getClientHeight(), Style.Unit.PX);

        eventName.getElement().getStyle().setPaddingTop(TOP_PADDING_UPPER_ELEMENT, Style.Unit.PX);
        eventName.getElement().getStyle().setPaddingLeft(TOP_PADDING_LEFT, Style.Unit.PX);
        name.getElement().getStyle().setPaddingTop(TOP_PADDING_LOWER_ELEMENT, Style.Unit.PX);
        name.getElement().getStyle().setPaddingLeft(TOP_PADDING_LEFT, Style.Unit.PX);

        eventDescription.getElement().getStyle().setPaddingTop(TOP_PADDING_UPPER_ELEMENT, Style.Unit.PX);
        eventDescription.getElement().getStyle().setPaddingLeft(TOP_PADDING_LEFT, Style.Unit.PX);
        description.getElement().getStyle().setPaddingTop(TOP_PADDING_LOWER_ELEMENT, Style.Unit.PX);
        description.getElement().getStyle().setPaddingLeft(TOP_PADDING_LEFT, Style.Unit.PX);

        eventTime.getElement().getStyle().setPaddingTop(TOP_PADDING_UPPER_ELEMENT, Style.Unit.PX);
        eventTime.getElement().getStyle().setPaddingLeft(TOP_PADDING_LEFT, Style.Unit.PX);
        time.getElement().getStyle().setPaddingTop(TOP_PADDING_LOWER_ELEMENT, Style.Unit.PX);
        time.getElement().getStyle().setPaddingLeft(TOP_PADDING_LEFT, Style.Unit.PX);

        eventDate.getElement().getStyle().setPaddingTop(TOP_PADDING_UPPER_ELEMENT, Style.Unit.PX);
        eventDate.getElement().getStyle().setPaddingLeft(TOP_PADDING_LEFT, Style.Unit.PX);
        date.getElement().getStyle().setPaddingTop(TOP_PADDING_LOWER_ELEMENT, Style.Unit.PX);
        date.getElement().getStyle().setPaddingLeft(TOP_PADDING_LEFT, Style.Unit.PX);
    }

    public void updateFields(Event event) {
        this.name.setText(event.getName());
        this.description.setText(event.getDescription());
        this.date.setText(event.getDate().toString());
        this.time.setText(event.getTime());
    }
}