package ru.happyMoments.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.LoadEvent;
import com.google.gwt.event.dom.client.LoadHandler;
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
import org.vectomatic.file.FileUtils;
import ru.happyMoments.client.presenter.Presenter;
import ru.happyMoments.client.staff.Checker;
import ru.happyMoments.shared.dto.EventDto;
import ru.happyMoments.shared.dto.LightEventDto;
import ru.happyMoments.shared.factories.Factory;

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
    private AddDialogBox addDialogBox;

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
        infoPanel.getLatitude().setText(Double.toString(event.getLatitude()));
        infoPanel.getLongitude().setText(Double.toString(event.getLongitude()));
    }

    public void setLightData(final List<LightEventDto> lightEventDtos) {
        lightEvents = lightEventDtos;
        setMarkers();
        bind();
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
                        //getCurrentEventId/getCurrentEvent
                        presenter.getCurrentEventDto().getId(),
                        infoPanel.getDescription().getText(),
                        infoPanel.getDate().getText(),
                        infoPanel.getName().getText(),
                        //getCurrentEventImage/getCurrentEvent
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

    private Image createPngImage(final File file) {
        final Image image = new Image();
        final String url = FileUtils.createObjectURL(file);
        image.addLoadHandler(new LoadHandler() {
            @Override
            public void onLoad(LoadEvent event) {
                sizeBitmap(image);
                FileUtils.revokeObjectURL(url);
            }
        });
        image.setUrl(url);
        return image;
    }

    private void sizeBitmap(Image image) {
        int width = image.getWidth();
        if (width == 0) {
            width = ieWidth(image.getElement());
        }
        int height = image.getHeight();
        if (height == 0) {
            height = ieHeight(image.getElement());
        }
        GWT.log("size=" + width + "x" + height);
        float f = 150.0f / Math.max(width, height);
        int w = (int) (f * width);
        int h = (int) (f * height);
        image.setPixelSize(w, h);
        image.getElement().getStyle().setWidth(w, Style.Unit.PX);
        image.getElement().getStyle().setHeight(h, Style.Unit.PX);
        image.setVisible(true);
    }

    private void setMarkers() {
        if (wMap != null && lightEvents != null && !lightEvents.isEmpty()) {
            wMap.launchApp(lightEvents);

            wMap.getMapWidget().addClickHandler(new ClickMapHandler() {
                @Override
                public void onEvent(ClickMapEvent clickMapEvent) {
                    infoPanel.hide();
                    wMap.stopAnimation();
                }
            });

            wMap.getMapWidget().addDblClickHandler(new DblClickMapHandler() {
                @Override
                public void onEvent(final DblClickMapEvent dblClickMapEvent) {
                    addDialogBox.show();
                    addDialogBox.getAddButton().addClickHandler(new ClickHandler() {
                        @Override
                        public void onClick(ClickEvent event) {
                            if (!Checker.checkTime(addDialogBox.getTimeInput().getText())) {
                                Window.alert("Некорректный формат времени");
                            } else {
                                FileList files = addDialogBox.getFileUpload().getFiles();
                                final File file = files.getItem(0);
                                Image image = createPngImage(file);
                                presenter.uploadImage(file);

                            /*reader.addLoadEndHandler(new LoadEndHandler() {
                                @Override
                                public void onLoadEnd(LoadEndEvent loadEndEvent) {
                                    if (reader.getError() == null) {
                                        FileList files = addDialogBox.getFileUpload().getFiles();
                                        File file = files.getItem(0);
                                        presenter.uploadImage(file);
                                    }
                                }
                            });*/

                            /*presenter.createEvent(Factory.createEventDto(
                                    wMap.getMarkers().size() + 1,
                                    addDialogBox.getDescriptionInput().getText(),
                                    addDialogBox.getDateInput().getText(),
                                    addDialogBox.getNameInput().getText(),
                                    Factory.createImageDto(wMap.getMarkers().size(), addDialogBox.getFileUpload().getFilename()),
                                    addDialogBox.getTimeInput().getText(),
                                    dblClickMapEvent.getMouseEvent().getLatLng().getLatitude(),
                                    dblClickMapEvent.getMouseEvent().getLatLng().getLongitude()
                            ));*/
                                addDialogBox.hide();
                                infoPanel.setActive(false);
                            }
                        }
                    });
                }
            });
        }
    }

    // For that piece of crap called IE
    private static native int ieWidth(Element elt) /*-{
        return elt.naturalWidth;
    }-*/;

    private static native int ieHeight(Element elt) /*-{
        return elt.naturalHeight;
    }-*/;
}