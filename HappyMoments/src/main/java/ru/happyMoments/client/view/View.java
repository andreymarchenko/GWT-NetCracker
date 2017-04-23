package ru.happyMoments.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.maps.client.events.click.ClickMapEvent;
import com.google.gwt.maps.client.events.click.ClickMapHandler;
import com.google.gwt.maps.client.events.dblclick.DblClickMapEvent;
import com.google.gwt.maps.client.events.dblclick.DblClickMapHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.dom.client.Style;
import com.google.web.bindery.event.shared.EventBus;
import com.google.gwt.maps.client.LoadApi;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

import com.google.gwt.maps.client.LoadApi.LoadLibrary;
import com.google.gwt.user.client.ui.Composite;
import ru.happyMoments.client.presenter.Presenter;
import ru.happyMoments.shared.dto.EventDto;
import ru.happyMoments.shared.dto.LightEventDto;
import ru.happyMoments.shared.factories.Factory;

public class View extends Composite {

    interface MainPanelUiBinder extends UiBinder<Widget, View> {
    }

    private static MainPanelUiBinder ourUiBinder = GWT.create(MainPanelUiBinder.class);

    @UiField
    RootLayoutPanel mapPanel;

    private EventBus eventBus;
    private Presenter presenter;
    private BasicMapWidget wMap;
    //Дописать флаг проверки на заполнение маркеров
    private List<LightEventDto> lightEvents;

    private InfoPanel infoPanel;
    private AddDialogBox addDialogBox;

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

    private void loadMapApi() {
        boolean sensor = true;

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
                wMap = new BasicMapWidget();
                wMap.setPresenter(presenter);
                addMapWidget(wMap);
                setMarkers();
            }
        };

        LoadApi.go(onLoad, loadLibraries, sensor);
    }

    private void addMapWidget(Widget widget) {
        mapPanel.add(widget);
    }

    public void createUI() {
        initWidget(ourUiBinder.createAndBindUi(this));
        setUI();
        loadMapApi();
        infoPanel = new InfoPanel();
        addDialogBox = new AddDialogBox();
        RootPanel.get("root").add(this);
    }

    public void setUI() {
        mapPanel.getElement().getStyle().setWidth(Window.getClientWidth(), Style.Unit.PX);
        mapPanel.getElement().getStyle().setHeight(Window.getClientHeight(), Style.Unit.PX);
    }

    public void updateFields(EventDto event) {
        infoPanel.show();
        infoPanel.getImage().setUrl(event.getImage().getUrl());
        infoPanel.getEditImage().setUrl("http://127.0.0.1:8888/images/edit.png");
        infoPanel.getDeleteImage().setUrl("http://127.0.0.1:8888/images/delete.png");
        infoPanel.getAcceptImage().setUrl("http://127.0.0.1:8888/images/accept.png");
        infoPanel.getName().setText(event.getName());
        infoPanel.getDescription().setText(event.getDescription());
        infoPanel.getTime().setText(event.getTime());
        infoPanel.getDate().setText(event.getDate());
        infoPanel.getLatitude().setText("Широта:  " + Double.toString(event.getLatitude()));
        infoPanel.getLongitude().setText("Долгота:  " + Double.toString(event.getLongitude()));
    }

    public void setLightData(final List<LightEventDto> lightEventDtos) {
        lightEvents = lightEventDtos;
        setMarkers();
        bind();
    }

    private void bind() {
        wMap.getMapWidget().addDblClickHandler(new DblClickMapHandler() {
            @Override
            public void onEvent(final DblClickMapEvent dblClickMapEvent) {
                addDialogBox.show();
                addDialogBox.getAddButton().addClickHandler(new ClickHandler() {
                    @Override
                    public void onClick(ClickEvent event) {
                        presenter.createEvent(Factory.createEventDto(
                                wMap.getMarkers().size() + 1,
                                addDialogBox.getDescriptionInput().getText(),
                                addDialogBox.getDateInput().getText(),
                                addDialogBox.getNameInput().getText(),
                                Factory.createImageDto(wMap.getMarkers().size(), ""),
                                addDialogBox.getTimeInput().getText(),
                                dblClickMapEvent.getMouseEvent().getLatLng().getLatitude(),
                                dblClickMapEvent.getMouseEvent().getLatLng().getLongitude()
                        ));
                        addDialogBox.hide();
                    }
                });
            }
        });
    }

    private void setMarkers() {
        if (wMap != null && lightEvents!= null && !lightEvents.isEmpty()) {
            wMap.launchApp(lightEvents);
            wMap.getMapWidget().addClickHandler(new ClickMapHandler() {
                @Override
                public void onEvent(ClickMapEvent clickMapEvent) {
                    infoPanel.hide();
                    wMap.stopAnimation();
                }
            });
        }
    }
}