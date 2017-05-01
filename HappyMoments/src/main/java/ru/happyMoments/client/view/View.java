package ru.happyMoments.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.maps.client.LoadApi;
import com.google.gwt.maps.client.LoadApi.LoadLibrary;
import com.google.gwt.maps.client.events.click.ClickMapEvent;
import com.google.gwt.maps.client.events.click.ClickMapHandler;
import com.google.gwt.maps.client.events.dblclick.DblClickMapEvent;
import com.google.gwt.maps.client.events.dblclick.DblClickMapHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import com.google.web.bindery.event.shared.EventBus;
import org.vectomatic.file.File;
import org.vectomatic.file.FileList;
import ru.happyMoments.client.presenter.Presenter;
import ru.happyMoments.shared.constants.Paths;
import ru.happyMoments.shared.staff.Checker;
import ru.happyMoments.shared.dto.EventDto;
import ru.happyMoments.shared.dto.LightEventDto;
import ru.happyMoments.shared.factories.Factory;
import ru.happyMoments.shared.staff.Creator;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class View extends Composite {

    interface MainPanelUiBinder extends UiBinder<Widget, View> {
    }

    private static MainPanelUiBinder ourUiBinder = GWT.create(MainPanelUiBinder.class);

    @UiField
    RootLayoutPanel mapPanel;

    private EventBus eventBus;
    private Presenter presenter;
    private BasicMapWidget wMap;
    private List<LightEventDto> lightEvents;
    private InfoPanel infoPanel;

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
                prepareMap();
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
        RootPanel.get("root").add(this);
    }

    public void setUI() {
        mapPanel.getElement().getStyle().setWidth(Window.getClientWidth(), Style.Unit.PX);
        mapPanel.getElement().getStyle().setHeight(Window.getClientHeight(), Style.Unit.PX);
    }

    public void updateFields(EventDto event) {
        infoPanel.show();
        infoPanel.getImage().setUrl(event.getImage().getUrl());
        infoPanel.getEditImage().setUrl(Paths.EDIT_IMAGE_PATH);
        infoPanel.getDeleteImage().setUrl(Paths.DELETE_IMAGE_PATH);
        infoPanel.getAcceptImage().setUrl(Paths.ACCEPT_IMAGE_PATH);
        infoPanel.getName().setText(event.getName());
        infoPanel.getDescription().setText(event.getDescription());
        infoPanel.getTime().setText(event.getTime());
        infoPanel.getDate().setText(event.getDate());
        infoPanel.getLatitude().setText(Double.toString(Creator.round(event.getLatitude(), 5)));
        infoPanel.getLongitude().setText(Double.toString(Creator.round(event.getLongitude(), 5)));
        bind();
    }

    public void setLightData(final List<LightEventDto> lightEventDtos) {
        lightEvents = lightEventDtos;
        prepareMap();
    }

    private void bind() {
        infoPanel.getEditImage().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                infoPanel.setActive(true);
            }
        });

        infoPanel.getAcceptImage().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                infoPanel.setActive(false);
                presenter.editEvent(Factory.createEventDto(
                        presenter.getCurrentEventDto().getId(),
                        infoPanel.getDescription().getText(),
                        infoPanel.getDate().getText(),
                        infoPanel.getName().getText(),
                        presenter.getCurrentEventDto().getImage(),
                        infoPanel.getTime().getText(),
                        Double.parseDouble(infoPanel.getLatitude().getText()),
                        Double.parseDouble(infoPanel.getLongitude().getText())));
            }
        });

        infoPanel.getDeleteImage().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                infoPanel.setActive(false);
                infoPanel.setVisible(false);
                presenter.deleteEvent();
            }
        });
    }

    private void prepareMap() {
        if (wMap != null) {
            if (lightEvents != null && !lightEvents.isEmpty()) {
                wMap.launchApp(lightEvents);
            }
            infoPanel = wMap.getInfoPanel();
            wMap.getMapWidget().addClickHandler(new ClickMapHandler() {
                @Override
                public void onEvent(ClickMapEvent clickMapEvent) {
                    infoPanel.hide();
                    infoPanel.setActive(false);
                    wMap.stopAnimation();
                }
            });

        }
    }
}