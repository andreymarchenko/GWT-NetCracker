package ru.happyMoments.client.view;

import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.maps.client.MapOptions;
import com.google.gwt.maps.client.MapTypeId;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.adsense.AdFormat;
import com.google.gwt.maps.client.adsense.AdUnitOptions;
import com.google.gwt.maps.client.adsense.AdUnitWidget;
import com.google.gwt.maps.client.base.LatLng;
import com.google.gwt.maps.client.controls.ControlPosition;
import com.google.gwt.maps.client.events.channelnumber.ChannelNumberChangeMapEvent;
import com.google.gwt.maps.client.events.channelnumber.ChannelNumberChangeMapHandler;
import com.google.gwt.maps.client.events.click.ClickMapEvent;
import com.google.gwt.maps.client.events.click.ClickMapHandler;
import com.google.gwt.maps.client.events.dblclick.DblClickMapEvent;
import com.google.gwt.maps.client.events.dblclick.DblClickMapHandler;
import com.google.gwt.maps.client.events.format.FormatChangeMapEvent;
import com.google.gwt.maps.client.events.format.FormatChangeMapHandler;
import com.google.gwt.maps.client.events.mapchange.MapChangeMapEvent;
import com.google.gwt.maps.client.events.mapchange.MapChangeMapHandler;
import com.google.gwt.maps.client.events.position.PositionChangeMapEvent;
import com.google.gwt.maps.client.events.position.PositionChangeMapHandler;
import com.google.gwt.maps.client.overlays.Animation;
import com.google.gwt.maps.client.overlays.Marker;
import com.google.gwt.maps.client.overlays.MarkerOptions;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import org.vectomatic.file.File;
import org.vectomatic.file.FileList;
import ru.happyMoments.client.presenter.Presenter;
import ru.happyMoments.shared.dto.LightEventDto;
import ru.happyMoments.shared.factories.Factory;
import ru.happyMoments.shared.staff.Checker;

import java.util.ArrayList;
import java.util.List;

public class BasicMapWidget extends Composite {

    private final VerticalPanel pWidget;
    private MapWidget mapWidget;
    private List<Marker> markers;
    private AddDialogBox addDialogBox;
    private Presenter presenter;
    private double longitude;
    private double latitude;
    private InfoPanel infoPanel;

    public BasicMapWidget() {
        pWidget = new VerticalPanel();
        markers = new ArrayList<>();
        initWidget(pWidget);
        drawMap();
        drawMapAds();
        addDialogBox = new AddDialogBox();
        bind();
        infoPanel = new InfoPanel();
    }

    private void bind() {

        mapWidget.addDblClickHandler(new DblClickMapHandler() {
            @Override
            public void onEvent(final DblClickMapEvent dblClickMapEvent) {
                addDialogBox.show();
                addDialogBox.restoreHints();

                latitude = dblClickMapEvent.getMouseEvent().getLatLng().getLatitude();
                longitude = dblClickMapEvent.getMouseEvent().getLatLng().getLongitude();
            }
        });

        addDialogBox.getAddButton().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                int indicator = 0;
                int fileIndex = 0;
                if (Checker.checkDate(addDialogBox.getDateInput().getText()).equals("")) {
                    addDialogBox.getDateInput().getElement().getStyle().setBorderStyle(Style.BorderStyle.SOLID);
                    addDialogBox.getDateInput().getElement().getStyle().setBorderColor("#de3e50");
                    indicator++;
                }
                if (!Checker.checkString(addDialogBox.getNameInput().getText())) {
                    addDialogBox.getNameInput().getElement().getStyle().setBorderStyle(Style.BorderStyle.SOLID);
                    addDialogBox.getNameInput().getElement().getStyle().setBorderColor("#de3e50");
                    indicator++;
                }
                if (!Checker.checkString(addDialogBox.getDescriptionInput().getText())) {
                    addDialogBox.getDescriptionInput().getElement().getStyle().setBorderStyle(Style.BorderStyle.SOLID);
                    addDialogBox.getDescriptionInput().getElement().getStyle().setBorderColor("#de3e50");
                    indicator++;
                }
                if (addDialogBox.getFileUpload().getFilename().equals("")) {
                    Window.alert("Пожалуйста, выберите файл.");
                    indicator++;
                    fileIndex++;
                }
                if ((!addDialogBox.getFileUpload().getFilename().endsWith(".jpg"))
                        && !addDialogBox.getFileUpload().getFilename().endsWith(".png")) {
                    if(fileIndex>0) {
                        Window.alert("Неверный формат файла");
                        indicator++;
                    }
                }
                if (addDialogBox.getTimeInput().getText().contains("h")) {
                    if (Checker.checkTime(addDialogBox.getTimeInput().getText()).equals("")) {
                        addDialogBox.getTimeInput().getElement().getStyle().setBorderStyle(Style.BorderStyle.SOLID);
                        addDialogBox.getTimeInput().getElement().getStyle().setBorderColor("#de3e50");
                        indicator++;
                    }
                } else if (Checker.checkHourMinute(addDialogBox.getTimeInput().getText()).equals("")) {
                    addDialogBox.getTimeInput().getElement().getStyle().setBorderStyle(Style.BorderStyle.SOLID);
                    addDialogBox.getTimeInput().getElement().getStyle().setBorderColor("#de3e50");
                    indicator++;
                }
                if (indicator == 0) {
                    FileList files = addDialogBox.getFileUpload().getFiles();
                    File file = files.getItem(0);

                    presenter.uploadImage(file);

                    presenter.createEvent(Factory.createEventDto(
                            0,
                            addDialogBox.getDescriptionInput().getText().trim(),
                            Checker.checkDate(addDialogBox.getDateInput().getText()),
                            addDialogBox.getNameInput().getText().trim(),
                            Factory.createImageDto(0, " "),
                            Checker.convertTime(addDialogBox.getTimeInput().getText()),
                            latitude,
                            longitude
                    ));
                    addDialogBox.hide();
                }
            }
        });
    }

    public void stopAnimation() {
        for (Marker m : markers) {
            m.setAnimation(Animation.STOPANIMATION);
        }
    }

    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    private void clearMap() {
        for (int i = 0; i < markers.size(); i++) {
            markers.get(i).clear();
        }
    }

    private void drawMarkerWithDropAnimation(double latitude, double longitude) {
        MarkerOptions options = MarkerOptions.newInstance();
        options.setPosition(LatLng.newInstance(latitude, longitude));

        final Marker marker = Marker.newInstance(options);
        marker.setMap(mapWidget);
        markers.add(marker);

        marker.addClickHandler(new ClickMapHandler() {
            @Override
            public void onEvent(ClickMapEvent event) {
                stopAnimation();
                infoPanel.setActive(false);
                marker.setAnimation(Animation.BOUNCE);

                presenter.loadEvent(Factory.createLightEventDto(marker.getPosition().getLatitude(),
                        marker.getPosition().getLongitude()));

                infoPanel.setVisible(true);
            }
        });
    }

    private void drawMap() {
        LatLng center = LatLng.newInstance(56.1937, 44.0027);
        MapOptions opts = MapOptions.newInstance();
        opts.setZoom(11);
        opts.setDisableDoubleClickZoom(true);
        opts.setCenter(center);
        opts.setMapTypeId(MapTypeId.ROADMAP);

        mapWidget = new MapWidget(opts);
        pWidget.add(mapWidget);

        mapWidget.setSize(Double.toString(Window.getClientWidth()),
                Double.toString(Window.getClientHeight()));
    }

    public void launchApp(List<LightEventDto> lightEventDtos) {
        if (lightEventDtos.size() != 0) {
            clearMap();
            markers.clear();
            for (int i = 0; i < lightEventDtos.size(); i++) {
                drawMarkerWithDropAnimation(lightEventDtos.get(i).getLatitude(), lightEventDtos.get(i).getLongitude());
            }
        }
    }

    private void drawMapAds() {
        AdUnitOptions options = AdUnitOptions.newInstance();
        options.setFormat(AdFormat.HALF_BANNER);
        options.setPosition(ControlPosition.RIGHT_CENTER);
        options.setMap(mapWidget);
        options.setPublisherId("pub-0032065764310410");
        options.setChannelNumber("4000893900");

        AdUnitWidget adUnit = new AdUnitWidget(options);

        adUnit.addChannelNumberChangeHandler(new ChannelNumberChangeMapHandler() {
            @Override
            public void onEvent(ChannelNumberChangeMapEvent event) {
            }
        });

        adUnit.addFormatChangeHandler(new FormatChangeMapHandler() {
            @Override
            public void onEvent(FormatChangeMapEvent event) {
            }
        });

        adUnit.addMapChangeHandler(new MapChangeMapHandler() {
            @Override
            public void onEvent(MapChangeMapEvent event) {
            }
        });

        adUnit.addPositionChangeHandler(new PositionChangeMapHandler() {
            @Override
            public void onEvent(PositionChangeMapEvent event) {
            }
        });
    }

    public MapWidget getMapWidget() {
        return mapWidget;
    }

    public List<Marker> getMarkers() {
        return markers;
    }

    public InfoPanel getInfoPanel() {
        return infoPanel;
    }
}
