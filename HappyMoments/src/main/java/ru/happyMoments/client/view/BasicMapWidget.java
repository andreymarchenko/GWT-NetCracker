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
import com.google.gwt.maps.client.overlays.Marker;
import com.google.gwt.maps.client.overlays.MarkerOptions;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import ru.happyMoments.client.presenter.Presenter;
import ru.happyMoments.shared.dto.LightEventDto;
import ru.happyMoments.shared.factories.Factory;

import java.util.ArrayList;
import java.util.List;

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
                marker.setAnimation(Animation.BOUNCE);

                presenter.loadEvent(Factory.createLightEventDto(marker.getPosition().getLatitude(),
                        marker.getPosition().getLongitude()));

                InfoPanel infoPanel = new InfoPanel();
                infoPanel.getElement().getStyle().setBackgroundColor("White");
                infoPanel.getElement().getStyle().setHeight(100, Style.Unit.PX);
                infoPanel.getElement().getStyle().setWidth(100, Style.Unit.PX);
                infoPanel.setPopupPosition(Window.getClientWidth() / 2, Window.getClientHeight() / 2);
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
        clearMap();
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

    public List<Marker> getMarkers() {
        return markers;
    }
}
