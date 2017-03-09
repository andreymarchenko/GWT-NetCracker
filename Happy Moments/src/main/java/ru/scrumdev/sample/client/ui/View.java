package ru.scrumdev.sample.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import ru.scrumdev.sample.client.*;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Date;

public class View extends Composite {
    interface MainPanelUiBinder extends UiBinder<Widget, View> {
    }

    private static MainPanelUiBinder ourUiBinder = GWT.create(MainPanelUiBinder.class);

    @UiField
    FlowPanel leftPanel;
    @UiField
    FlowPanel searchPanel;
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

    private Presenter presenter;

    @Inject
    public View() {
        initWidget(ourUiBinder.createAndBindUi(this));
        presenter = new Presenter(this);
        setUI();
    }

    public void updateUI() {
        ArrayList<ru.scrumdev.sample.client.Image> list = new ArrayList<>();
        ru.scrumdev.sample.client.Image img = new ru.scrumdev.sample.client.Image(1, "C:\\GWT-NetCracker\\Happy Moments\\src\\main\\resources\\ru\\scrumdev\\sample\\1.png");
        list.add(img);
        final Event event = new Event(1, "Some Discription", new Date(2017, 02, 28), "First event", list, "21.00");

        updateButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                getPresenter().loadData(event);
            }
        });
    }

    public void setUI() {
        leftPanel.getElement().getStyle().setBorderColor("Gray");
        leftPanel.getElement().getStyle().setBorderStyle(Style.BorderStyle.SOLID);
        leftPanel.getElement().getStyle().setWidth(Window.getClientWidth() / 1.5, Style.Unit.PX);
        leftPanel.getElement().getStyle().setHeight(Window.getClientHeight() - 7, Style.Unit.PX);

        searchPanel.getElement().getStyle().setBorderColor("Gray");
        searchPanel.getElement().getStyle().setBorderStyle(Style.BorderStyle.SOLID);
        searchPanel.getElement().getStyle().setWidth(Window.getClientWidth() / 1.5, Style.Unit.PX);
        searchPanel.getElement().getStyle().setHeight(Window.getClientHeight() / 13.0, Style.Unit.PX);

        mapPanel.getElement().getStyle().setBorderColor("Gray");
        mapPanel.getElement().getStyle().setBorderStyle(Style.BorderStyle.SOLID);
        mapPanel.getElement().getStyle().setWidth(Window.getClientWidth() / 1.5, Style.Unit.PX);
        mapPanel.getElement().getStyle().setHeight((1.0 - 1.0 / 13.0) * Window.getClientHeight() - 16, Style.Unit.PX);

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

    public Button getUpdateButton() {
        return updateButton;
    }

    public void setUpdateButton(Button updateButton) {
        this.updateButton = updateButton;
    }

    public Presenter getPresenter() {
        return presenter;
    }

    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    public Label getName() {
        return name;
    }

    public void setName(Label name) {
        this.name = name;
    }

    public Label getDescription() {
        return description;
    }

    public void setDescription(Label description) {
        this.description = description;
    }

    public Label getTime() {
        return time;
    }

    public void setTime(Label time) {
        this.time = time;
    }

    public Label getDate() {
        return date;
    }

    public void setDate(Label date) {
        this.date = date;
    }
}