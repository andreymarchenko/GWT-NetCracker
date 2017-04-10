package ru.happyMoments.client.view;

import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;

public class InfoPanel extends PopupPanel {

    private VerticalPanel contentPanel;
    private FlowPanel imagePanel;
    private FlowPanel namePanel;
    private Image image;
    private Label name;
    private Label textName;
    private VerticalPanel nameContentPanel;
    private VerticalPanel mainInfoPanel;
    private VerticalPanel descriptionPanel;
    private Label textDescription;
    private Label description;
    private VerticalPanel timePanel;
    private Label textTime;
    private Label time;
    private VerticalPanel datePanel;
    private Label textDate;
    private Label date;
    private VerticalPanel locationPanel;
    private Label textLocation;
    private Label latitude;
    private Label longitude;


    public InfoPanel() {
        createMarkUp();
    }

    private void createSeparator() {
        VerticalPanel separator = new VerticalPanel();
        separator.getElement().getStyle().setHeight(2, Style.Unit.PX);
        separator.getElement().getStyle().setWidth(Window.getClientWidth() /4.5 - 10, Style.Unit.PX);
        separator.getElement().getStyle().setBorderColor("#757575");
        separator.getElement().getStyle().setOpacity(0.4);
        //separator.getElement().getStyle().setMarginLeft(Window.getClientWidth() / 24.5, Style.Unit.PX);
        separator.getElement().getStyle().setBorderWidth(0.5,Style.Unit.PX );
        separator.getElement().getStyle().setBorderStyle(Style.BorderStyle.SOLID);
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
        textName.getElement().getStyle().setMarginTop(30, Style.Unit.PX);
        textName.setText("Название события");
        nameContentPanel.add(textName);

        name = new Label();
        name.getElement().getStyle().setColor("White");
        name.getElement().getStyle().setFontSize(25, Style.Unit.PX);
        name.getElement().getStyle().setMarginLeft(25, Style.Unit.PX);
        name.getElement().getStyle().setMarginTop(-Window.getClientHeight() / 32, Style.Unit.PX);
        nameContentPanel.add(name);

        mainInfoPanel = new VerticalPanel();
        mainInfoPanel.getElement().getStyle().setWidth(Window.getClientWidth() / 4.5 - 10, Style.Unit.PX);
        mainInfoPanel.getElement().getStyle().setHeight(Window.getClientHeight() * 5 / 9 - 20, Style.Unit.PX);

        descriptionPanel = new VerticalPanel();
        descriptionPanel.getElement().getStyle().setWidth(Window.getClientWidth() / 4.5 - 10, Style.Unit.PX);
        descriptionPanel.getElement().getStyle().setHeight(Window.getClientHeight() / 9 - 4, Style.Unit.PX);
        mainInfoPanel.add(descriptionPanel);

        createSeparator();

        textDescription = new Label();
        textDescription.getElement().getStyle().setColor("#757575");
        textDescription.getElement().getStyle().setFontSize(15, Style.Unit.PX);
        textDescription.getElement().getStyle().setMarginLeft(25, Style.Unit.PX);
        textDescription.getElement().getStyle().setMarginTop(Window.getClientHeight() / 35, Style.Unit.PX);
        textDescription.setText("Описание события");
        descriptionPanel.add(textDescription);

        description = new Label();
        description.getElement().getStyle().setColor("#757575");
        description.getElement().getStyle().setFontSize(25, Style.Unit.PX);
        description.getElement().getStyle().setMarginLeft(25, Style.Unit.PX);
        description.getElement().getStyle().setMarginTop(-Window.getClientHeight() / 45, Style.Unit.PX);
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
        textTime.getElement().getStyle().setMarginTop(Window.getClientHeight() / 35, Style.Unit.PX);
        textTime.setText("Время события");
        timePanel.add(textTime);

        time = new Label();
        time.getElement().getStyle().setColor("#757575");
        time.getElement().getStyle().setFontSize(25, Style.Unit.PX);
        time.getElement().getStyle().setMarginLeft(25, Style.Unit.PX);
        time.getElement().getStyle().setMarginTop(-Window.getClientHeight() / 45, Style.Unit.PX);
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
        textDate.getElement().getStyle().setMarginTop(Window.getClientHeight() / 35, Style.Unit.PX);
        textDate.setText("Дата события");
        datePanel.add(textDate);

        date = new Label();
        date.getElement().getStyle().setColor("#757575");
        date.getElement().getStyle().setFontSize(25, Style.Unit.PX);
        date.getElement().getStyle().setMarginLeft(25, Style.Unit.PX);
        date.getElement().getStyle().setMarginTop(-Window.getClientHeight() / 45, Style.Unit.PX);
        datePanel.add(date);

        locationPanel = new VerticalPanel();
        locationPanel.getElement().getStyle().setWidth(Window.getClientWidth() / 4.5 - 10, Style.Unit.PX);
        locationPanel.getElement().getStyle().setHeight(Window.getClientHeight() / 9 - 4, Style.Unit.PX);
        mainInfoPanel.add(locationPanel);

        textLocation = new Label();
        textLocation.getElement().getStyle().setColor("#757575");
        textLocation.getElement().getStyle().setFontSize(15, Style.Unit.PX);
        textLocation.getElement().getStyle().setMarginLeft(25, Style.Unit.PX);
        textLocation.getElement().getStyle().setMarginTop(Window.getClientHeight() / 45, Style.Unit.PX);
        textLocation.setText("Координаты события");
        locationPanel.add(textLocation);

        latitude = new Label();
        latitude.getElement().getStyle().setColor("#757575");
        latitude.getElement().getStyle().setFontSize(20, Style.Unit.PX);
        latitude.getElement().getStyle().setMarginLeft(25, Style.Unit.PX);
        //latitude.getElement().getStyle().setMarginTop(Window.getClientHeight() / 65, Style.Unit.PX);
        locationPanel.add(latitude);

        longitude = new Label();
        longitude.getElement().getStyle().setColor("#757575");
        longitude.getElement().getStyle().setFontSize(20, Style.Unit.PX);
        longitude.getElement().getStyle().setMarginLeft(25, Style.Unit.PX);
        //longitude.getElement().getStyle().setMarginTop(Window.getClientHeight() / 65, Style.Unit.PX);
        locationPanel.add(longitude);

        imagePanel.add(image);

        contentPanel.add(imagePanel);
        contentPanel.add(namePanel);
        contentPanel.add(mainInfoPanel);

        this.add(contentPanel);
    }

    public Image getImage() {
        return image;
    }

    public Label getName() {
        return name;
    }

    public Label getDescription() {
        return description;
    }

    public Label getTime() {
        return time;
    }

    public Label getDate() {
        return date;
    }

    public Label getLatitude() {
        return latitude;
    }

    public Label getLongitude() {
        return longitude;
    }
}
