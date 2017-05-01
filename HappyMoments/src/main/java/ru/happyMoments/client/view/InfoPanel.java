package ru.happyMoments.client.view;

import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

import java.awt.*;

public class InfoPanel extends PopupPanel {

    private VerticalPanel contentPanel;
    private FlowPanel imagePanel;
    private HorizontalPanel editPanel;
    private FlowPanel namePanel;
    private Image image;
    private Image editImage;
    private Image acceptImage;
    private Image deleteImage;
    private TextBox name;
    private Label textName;
    private VerticalPanel nameContentPanel;
    private VerticalPanel mainInfoPanel;
    private VerticalPanel descriptionPanel;
    private Label textDescription;
    private TextBox description;
    private VerticalPanel timePanel;
    private Label textTime;
    private TextBox time;
    private VerticalPanel datePanel;
    private Label textDate;
    private TextBox date;
    private VerticalPanel locationPanel;
    private Label textLocation;
    private Label textLatitude;
    private Label textLongitude;
    private TextBox latitude;
    private TextBox longitude;
    private HorizontalPanel latitudePanel;
    private HorizontalPanel longitudePanel;


    public InfoPanel() {
        createMarkUp();
    }

    private void createSeparator() {
        FlowPanel separator = new FlowPanel();
        separator.getElement().getStyle().setHeight(1, Style.Unit.PX);
        separator.getElement().getStyle().setWidth(Window.getClientWidth() / 4.5 - 10, Style.Unit.PX);
        separator.getElement().getStyle().setBackgroundColor("#757575");
        separator.getElement().getStyle().setOpacity(0.4);
        mainInfoPanel.add(separator);
    }

    private void createMarkUp() {
        this.getElement().getStyle().setBackgroundColor("White");
        this.getElement().getStyle().setOpacity(0.9);
        this.getElement().getStyle().setHeight(Window.getClientHeight() - 20, Style.Unit.PX);
        this.getElement().getStyle().setWidth(Window.getClientWidth() / 4.5 - 10, Style.Unit.PX);
        this.getElement().getStyle().setMarginTop(10, Style.Unit.PX);
        this.getElement().getStyle().setMarginLeft(7.0 / 9.0 * Window.getClientWidth(), Style.Unit.PX);

        contentPanel = new VerticalPanel();
        contentPanel.getElement().getStyle().setOpacity(0.9);
        contentPanel.getElement().getStyle().setHeight(Window.getClientHeight() - 20, Style.Unit.PX);
        contentPanel.getElement().getStyle().setWidth(Window.getClientWidth() / 4.5 - 10, Style.Unit.PX);

        imagePanel = new FlowPanel();
        imagePanel.getElement().getStyle().setHeight(Window.getClientHeight() / 3, Style.Unit.PX);
        imagePanel.getElement().getStyle().setWidth(Window.getClientWidth() / 4.5 - 10, Style.Unit.PX);

        image = new Image();
        image.getElement().getStyle().setHeight(Window.getClientHeight() / 3, Style.Unit.PX);
        image.getElement().getStyle().setWidth(Window.getClientWidth() / 4.5 - 10, Style.Unit.PX);

        namePanel = new FlowPanel();
        namePanel.getElement().getStyle().setHeight(Window.getClientHeight() / 9, Style.Unit.PX);
        namePanel.getElement().getStyle().setWidth(Window.getClientWidth() / 4.5 - 10, Style.Unit.PX);
        namePanel.getElement().getStyle().setBackgroundColor("4285f4");

        nameContentPanel = new VerticalPanel();
        nameContentPanel.getElement().getStyle().setHeight(Window.getClientHeight() / 9, Style.Unit.PX);
        nameContentPanel.getElement().getStyle().setWidth(Window.getClientWidth() / 4.5 - 10, Style.Unit.PX);
        namePanel.add(nameContentPanel);

        textName = new Label();
        textName.getElement().getStyle().setFontSize(15, Style.Unit.PX);
        textName.getElement().getStyle().setColor("White");
        textName.getElement().getStyle().setMarginLeft(25, Style.Unit.PX);
        textName.getElement().getStyle().setMarginTop(Window.getClientHeight() / 50, Style.Unit.PX);
        textName.setText("Название события");
        nameContentPanel.add(textName);

        name = new TextBox();
        name.setEnabled(false);
        name.getElement().getStyle().setColor("White");
        name.getElement().getStyle().setFontSize(25, Style.Unit.PX);
        name.getElement().getStyle().setWidth(Window.getClientWidth() / 5.5, Style.Unit.PX);
        name.getElement().getStyle().setMarginLeft(25, Style.Unit.PX);
        name.getElement().getStyle().setMarginTop(-Window.getClientHeight() / 50, Style.Unit.PX);
        name.getElement().getStyle().setBackgroundColor("4285f4");
        name.getElement().getStyle().setBorderStyle(Style.BorderStyle.NONE);
        nameContentPanel.add(name);

        mainInfoPanel = new VerticalPanel();
        mainInfoPanel.getElement().getStyle().setWidth(Window.getClientWidth() / 4.5 - 10, Style.Unit.PX);
        mainInfoPanel.getElement().getStyle().setHeight(Window.getClientHeight() * 5 / 9 - 20, Style.Unit.PX);

        editImage = new Image();
        editImage.getElement().getStyle().setHeight(Window.getClientWidth() / 30, Style.Unit.PX);
        editImage.getElement().getStyle().setWidth(Window.getClientWidth() / 30, Style.Unit.PX);
        editImage.getElement().getStyle().setMarginLeft(Window.getClientWidth() / 45, Style.Unit.PX);
        editImage.getElement().getStyle().setMarginTop(Window.getClientWidth() / 140, Style.Unit.PX);

        deleteImage = new Image();
        deleteImage.getElement().getStyle().setHeight(Window.getClientWidth() / 30, Style.Unit.PX);
        deleteImage.getElement().getStyle().setWidth(Window.getClientWidth() / 30, Style.Unit.PX);
        deleteImage.getElement().getStyle().setMarginRight(Window.getClientWidth() / 125, Style.Unit.PX);
        deleteImage.getElement().getStyle().setMarginTop(Window.getClientWidth() / 140, Style.Unit.PX);

        acceptImage = new Image();
        acceptImage.getElement().getStyle().setHeight(Window.getClientWidth() / 30, Style.Unit.PX);
        acceptImage.getElement().getStyle().setWidth(Window.getClientWidth() / 30, Style.Unit.PX);
        acceptImage.getElement().getStyle().setMarginTop(Window.getClientWidth() / 140, Style.Unit.PX);

        editPanel = new HorizontalPanel();
        editPanel.getElement().getStyle().setHeight(Window.getClientHeight() / 11, Style.Unit.PX);
        editPanel.getElement().getStyle().setWidth(Window.getClientWidth() / 4.5 - 10, Style.Unit.PX);
        editPanel.add(editImage);
        editPanel.add(deleteImage);
        editPanel.add(acceptImage);
        mainInfoPanel.add(editPanel);

        createSeparator();

        descriptionPanel = new VerticalPanel();
        descriptionPanel.getElement().getStyle().setWidth(Window.getClientWidth() / 4.5 - 10, Style.Unit.PX);
        descriptionPanel.getElement().getStyle().setHeight(Window.getClientHeight() / 9 - 4, Style.Unit.PX);
        mainInfoPanel.add(descriptionPanel);

        createSeparator();

        textDescription = new Label();
        textDescription.getElement().getStyle().setColor("#757575");
        textDescription.getElement().getStyle().setFontSize(15, Style.Unit.PX);
        textDescription.getElement().getStyle().setMarginLeft(25, Style.Unit.PX);
        textDescription.getElement().getStyle().setMarginTop(Window.getClientHeight() /50 , Style.Unit.PX);
        textDescription.setText("Описание события");
        descriptionPanel.add(textDescription);

        description = new TextBox();
        description.setEnabled(false);
        description.getElement().getStyle().setBorderStyle(Style.BorderStyle.NONE);
        description.getElement().getStyle().setWidth(Window.getClientWidth() / 5.5, Style.Unit.PX);
        description.getElement().getStyle().setMarginTop(-Window.getClientHeight() /70 , Style.Unit.PX);
        description.getElement().getStyle().setColor("#757575");
        description.getElement().getStyle().setBackgroundColor("White");
        description.getElement().getStyle().setFontSize(25, Style.Unit.PX);
        description.getElement().getStyle().setMarginLeft(25, Style.Unit.PX);
        descriptionPanel.add(description);

        timePanel = new VerticalPanel();
        timePanel.getElement().getStyle().setWidth(Window.getClientWidth() / 4.5 - 10, Style.Unit.PX);
        timePanel.getElement().getStyle().setHeight(Window.getClientHeight() / 9 - 4, Style.Unit.PX);
        mainInfoPanel.add(timePanel);

        createSeparator();

        textTime = new Label();
        textTime.getElement().getStyle().setColor("#757575");
        textTime.getElement().getStyle().setFontSize(15, Style.Unit.PX);
        textTime.getElement().getStyle().setMarginLeft(25, Style.Unit.PX);
        textTime.getElement().getStyle().setMarginTop(Window.getClientHeight() / 50, Style.Unit.PX);
        textTime.setText("Время события");
        timePanel.add(textTime);

        time = new TextBox();
        time.setEnabled(false);
        time.getElement().getStyle().setBorderStyle(Style.BorderStyle.NONE);
        time.getElement().getStyle().setWidth(Window.getClientWidth() / 5.5, Style.Unit.PX);
        time.getElement().getStyle().setColor("#757575");
        time.getElement().getStyle().setBackgroundColor("White");
        time.getElement().getStyle().setFontSize(25, Style.Unit.PX);
        time.getElement().getStyle().setMarginLeft(25, Style.Unit.PX);
        time.getElement().getStyle().setMarginTop(-Window.getClientHeight() /70 , Style.Unit.PX);
        timePanel.add(time);

        datePanel = new VerticalPanel();
        datePanel.getElement().getStyle().setWidth(Window.getClientWidth() / 4.5 - 10, Style.Unit.PX);
        datePanel.getElement().getStyle().setHeight(Window.getClientHeight() / 9 - 4, Style.Unit.PX);
        mainInfoPanel.add(datePanel);

        createSeparator();

        textDate = new Label();
        textDate.getElement().getStyle().setColor("#757575");
        textDate.getElement().getStyle().setFontSize(15, Style.Unit.PX);
        textDate.getElement().getStyle().setMarginLeft(25, Style.Unit.PX);
        textDate.getElement().getStyle().setMarginTop(Window.getClientHeight() / 50, Style.Unit.PX);
        textDate.setText("Дата события");
        datePanel.add(textDate);

        date = new TextBox();
        date.setEnabled(false);
        date.getElement().getStyle().setBorderStyle(Style.BorderStyle.NONE);
        date.getElement().getStyle().setWidth(Window.getClientWidth() / 5.5, Style.Unit.PX);
        date.getElement().getStyle().setColor("#757575");
        date.getElement().getStyle().setBackgroundColor("White");
        date.getElement().getStyle().setFontSize(25, Style.Unit.PX);
        date.getElement().getStyle().setMarginLeft(25, Style.Unit.PX);
        date.getElement().getStyle().setMarginTop(-Window.getClientHeight() /70 , Style.Unit.PX);
        datePanel.add(date);

        locationPanel = new VerticalPanel();
        locationPanel.getElement().getStyle().setWidth(Window.getClientWidth() / 4.5 - 10, Style.Unit.PX);
        locationPanel.getElement().getStyle().setHeight(Window.getClientHeight() / 9 - 4, Style.Unit.PX);
        mainInfoPanel.add(locationPanel);

        textLocation = new Label();
        textLocation.getElement().getStyle().setColor("#757575");
        textLocation.getElement().getStyle().setFontSize(15, Style.Unit.PX);
        textLocation.getElement().getStyle().setMarginLeft(25, Style.Unit.PX);
        textLocation.getElement().getStyle().setMarginTop(Window.getClientHeight() / 120, Style.Unit.PX);
        textLocation.setText("Координаты события");
        locationPanel.add(textLocation);

        latitudePanel = new HorizontalPanel();
        latitudePanel.getElement().getStyle().setWidth(Window.getClientWidth() / 4.5 - 10, Style.Unit.PX);
        latitudePanel.getElement().getStyle().setHeight(Window.getClientHeight() / 24 - 8, Style.Unit.PX);
        latitudePanel.getElement().getStyle().setMarginTop(Window.getClientHeight() / 120, Style.Unit.PX);

        longitudePanel = new HorizontalPanel();
        longitudePanel.getElement().getStyle().setWidth(Window.getClientWidth() / 4.5 - 10, Style.Unit.PX);
        longitudePanel.getElement().getStyle().setHeight(Window.getClientHeight() / 24 - 8, Style.Unit.PX);

        textLatitude = new Label();
        textLatitude.getElement().getStyle().setColor("#757575");
        textLatitude.getElement().getStyle().setFontSize(15, Style.Unit.PX);
        textLatitude.getElement().getStyle().setMarginLeft(25, Style.Unit.PX);
        textLatitude.getElement().getStyle().setMarginTop(Window.getClientHeight() / 500, Style.Unit.PX);
        textLatitude.setText("Широта: ");

        textLongitude = new Label();
        textLongitude.getElement().getStyle().setColor("#757575");
        textLongitude.getElement().getStyle().setFontSize(15, Style.Unit.PX);
        textLongitude.getElement().getStyle().setMarginLeft(25, Style.Unit.PX);
        textLongitude.getElement().getStyle().setMarginTop(Window.getClientHeight() / 500, Style.Unit.PX);
        textLongitude.setText("Долгота: ");

        latitude = new TextBox();
        latitude.setEnabled(false);
        latitude.getElement().getStyle().setBorderStyle(Style.BorderStyle.NONE);
        latitude.getElement().getStyle().setWidth(Window.getClientWidth() / 8, Style.Unit.PX);
        latitude.getElement().getStyle().setColor("#757575");
        latitude.getElement().getStyle().setBackgroundColor("White");
        latitude.getElement().getStyle().setFontSize(17, Style.Unit.PX);

        latitudePanel.add(textLatitude);
        latitudePanel.add(latitude);

        locationPanel.add(latitudePanel);

        longitude = new TextBox();
        longitude.setEnabled(false);
        longitude.getElement().getStyle().setBorderStyle(Style.BorderStyle.NONE);
        longitude.getElement().getStyle().setWidth(Window.getClientWidth() / 8, Style.Unit.PX);
        longitude.getElement().getStyle().setColor("#757575");
        longitude.getElement().getStyle().setBackgroundColor("White");
        longitude.getElement().getStyle().setFontSize(17, Style.Unit.PX);
        locationPanel.add(longitude);

        longitudePanel.add(textLongitude);
        longitudePanel.add(longitude);

        locationPanel.add(longitudePanel);

        imagePanel.add(image);

        contentPanel.add(imagePanel);
        contentPanel.add(namePanel);
        contentPanel.add(mainInfoPanel);

        this.add(contentPanel);
    }

    public Image getImage() {
        return image;
    }

    public TextBox getName() {
        return name;
    }

    public TextBox getDescription() {
        return description;
    }

    public TextBox getTime() {
        return time;
    }

    public TextBox getDate() {
        return date;
    }

    public TextBox getLatitude() {
        return latitude;
    }

    public TextBox getLongitude() {
        return longitude;
    }

    public Image getEditImage() {
        return editImage;
    }

    public Image getDeleteImage() {
        return deleteImage;
    }

    public Image getAcceptImage() {
        return acceptImage;
    }

    public void setActive(boolean value) {
        if (value == true) {
            this.name.setEnabled(true);
            this.description.setEnabled(true);
            this.time.setEnabled(true);
            this.date.setEnabled(true);
        }
        else {
            this.name.setEnabled(false);
            this.description.setEnabled(false);
            this.time.setEnabled(false);
            this.date.setEnabled(false);
        }

    }

    public void setDefaultView() {
        time.getElement().getStyle().setBorderStyle(Style.BorderStyle.NONE);
        date.getElement().getStyle().setBorderStyle(Style.BorderStyle.NONE);
        name.getElement().getStyle().setBorderStyle(Style.BorderStyle.NONE);
        description.getElement().getStyle().setBorderStyle(Style.BorderStyle.NONE);
    }
}
