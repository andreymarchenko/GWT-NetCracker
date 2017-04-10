package ru.happyMoments.client.view;

import com.google.gwt.dom.client.Style;
import com.google.gwt.maps.client.MapOptions;
import com.google.gwt.maps.client.MapTypeId;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.adsense.AdFormat;
import com.google.gwt.maps.client.adsense.AdUnitOptions;
import com.google.gwt.maps.client.adsense.AdUnitWidget;
import com.google.gwt.maps.client.base.LatLng;
import com.google.gwt.maps.client.controls.ControlPosition;
import com.google.gwt.maps.client.events.MouseEvent;
import com.google.gwt.maps.client.events.channelnumber.ChannelNumberChangeMapEvent;
import com.google.gwt.maps.client.events.channelnumber.ChannelNumberChangeMapHandler;
import com.google.gwt.maps.client.events.click.ClickMapEvent;
import com.google.gwt.maps.client.events.click.ClickMapHandler;
import com.google.gwt.maps.client.events.format.FormatChangeMapEvent;
import com.google.gwt.maps.client.events.format.FormatChangeMapHandler;
import com.google.gwt.maps.client.events.mapchange.MapChangeMapEvent;
import com.google.gwt.maps.client.events.mapchange.MapChangeMapHandler;
import com.google.gwt.maps.client.events.position.PositionChangeMapEvent;
import com.google.gwt.maps.client.events.position.PositionChangeMapHandler;
import com.google.gwt.maps.client.overlays.Animation;
import com.google.gwt.maps.client.overlays.InfoWindow;
import com.google.gwt.maps.client.overlays.InfoWindowOptions;
import com.google.gwt.maps.client.overlays.Marker;
import com.google.gwt.maps.client.overlays.MarkerOptions;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import ru.happyMoments.client.presenter.Presenter;
import ru.happyMoments.shared.dto.ImageDto;
import ru.happyMoments.shared.dto.LightEventDto;
import ru.happyMoments.shared.factories.LightEventDtoFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class BasicMapWidget extends Composite {

    private final VerticalPanel pWidget;
    private MapWidget mapWidget;
    private List<Marker> markers;
    private Presenter presenter;

    public BasicMapWidget() {
        pWidget = new VerticalPanel();
        markers = new ArrayList<>();
        initWidget(pWidget);
        drawMap();
        drawMapAds();
    }

    private void stopAnimation() {
        for (Marker m : markers) {
            m.setAnimation(Animation.STOPANIMATION);
        }
    }

    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    private void drawMarkerWithDropAnimation(double latitude, double longitude) {
        MarkerOptions options = MarkerOptions.newInstance();
        options.setPosition(LatLng.newInstance(latitude, longitude));
        options.setTitle("Thanks for clicking on me.");

        final Marker marker = Marker.newInstance(options);
        marker.setMap(mapWidget);
        markers.add(marker);

        marker.addClickHandler(new ClickMapHandler() {
            @Override
            public void onEvent(ClickMapEvent event) {
                stopAnimation();
                marker.setAnimation(Animation.BOUNCE);

                presenter.loadEvent(LightEventDtoFactory.create(marker.getPosition().getLatitude(),
                        marker.getPosition().getLongitude()));

                PopupPanel infoPanel = new PopupPanel();
                infoPanel.getElement().getStyle().setBackgroundColor("White");
                infoPanel.getElement().getStyle().setHeight(100, Style.Unit.PX);
                infoPanel.getElement().getStyle().setWidth(100, Style.Unit.PX);
                infoPanel.setPopupPosition(Window.getClientWidth()/2, Window.getClientHeight()/2);
                infoPanel.setVisible(true);
                //drawInfoWindow(marker, event.getMouseEvent());
            }
        });
    }

    protected void drawInfoWindow(Marker marker, MouseEvent mouseEvent) {

        if (marker == null || mouseEvent == null) {
            return;
        }

        HTML html = new HTML("You clicked on: " + mouseEvent.getLatLng().getToString());
        InfoWindowOptions options = InfoWindowOptions.newInstance();
        options.setContent(html);
        InfoWindow iw = InfoWindow.newInstance(options);
        iw.open(mapWidget, marker);
    }

    private void drawMap() {
        LatLng center = LatLng.newInstance(56.1937, 44.0027);
        MapOptions opts = MapOptions.newInstance();
        opts.setZoom(11);
        opts.setCenter(center);
        opts.setMapTypeId(MapTypeId.ROADMAP);

        mapWidget = new MapWidget(opts);
        pWidget.add(mapWidget);

        mapWidget.setSize(Double.toString(Window.getClientWidth()),
                Double.toString(Window.getClientHeight()));


        /*mapWidget.addClickHandler(new ClickMapHandler() {
            @Override
            public void onEvent(ClickMapEvent event) {
                drawMarkerWithDropAnimation(event.getMouseEvent().getLatLng().getLatitude(),
                        event.getMouseEvent().getLatLng().getLongitude());
            }
        });*/
    }

    public void launchApp(List<LightEventDto> lightEventDtos) {
        markers.clear();
        for (int i = 0; i < lightEventDtos.size(); i++) {
            drawMarkerWithDropAnimation(lightEventDtos.get(i).getLatitude(), lightEventDtos.get(i).getLongitude());
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
}
