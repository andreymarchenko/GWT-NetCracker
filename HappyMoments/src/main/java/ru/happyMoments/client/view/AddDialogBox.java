package ru.happyMoments.client.view;


import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;

public class AddDialogBox extends DialogBox {

    private VerticalPanel mainPanel;
    private HorizontalPanel horizontalPanel;
    private VerticalPanel leftPanel;
    private VerticalPanel rightPanel;
    private HorizontalPanel buttonPanel;
    private TextArea nameInput;
    private TextArea descriptionInput;
    private TextArea dateInput;
    private TextArea timeInput;
    private Label nameInfo;
    private Label descriptionInfo;
    private Label dateInfo;
    private Label timeInfo;
    private Button addButton;
    private Button closeButton;

    public AddDialogBox() {
        createMarkup();
        bind(this);
        super.setAnimationEnabled(true);
        super.setGlassEnabled(true);
    }

    private void createMarkup() {
        mainPanel = new VerticalPanel();
        horizontalPanel = new HorizontalPanel();
        buttonPanel = new HorizontalPanel();
        leftPanel = new VerticalPanel();
        rightPanel = new VerticalPanel();
        nameInput = new TextArea();
        descriptionInput = new TextArea();
        dateInput = new TextArea();
        timeInput = new TextArea();
        nameInfo = new Label("Название события");
        descriptionInfo = new Label("Описание события");
        dateInfo = new Label("Дата события");
        timeInfo = new Label("Время события");
        addButton = new Button("Add");
        closeButton = new Button("Close");
        buttonPanel.add(addButton);
        buttonPanel.add(closeButton);
        leftPanel.add(nameInfo);
        leftPanel.add(descriptionInfo);
        leftPanel.add(dateInfo);
        leftPanel.add(timeInfo);
        rightPanel.add(nameInput);
        rightPanel.add(descriptionInput);
        rightPanel.add(dateInput);
        rightPanel.add(timeInput);
        horizontalPanel.add(leftPanel);
        horizontalPanel.add(rightPanel);
        mainPanel.add(horizontalPanel);
        mainPanel.add(buttonPanel);
        this.add(mainPanel);
        setStyle();
    }

    private void bind(final AddDialogBox addDialogBox) {
        closeButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                addDialogBox.hide();
            }
        });
    }

    private void setStyle() {
        this.setPopupPosition((Window.getClientWidth() - 420) / 2, (Window.getClientHeight() - 420) / 2);
        this.getElement().getStyle().setWidth(420, Style.Unit.PX);
        this.getElement().getStyle().setHeight(320, Style.Unit.PX);
        this.getElement().getStyle().setBackgroundColor("White");
        this.getElement().getStyle().setOpacity(0.9);

        rightPanel.getElement().getStyle().setPaddingLeft(50, Style.Unit.PX);

        nameInput.getElement().getStyle().setMarginTop(20, Style.Unit.PX);
        descriptionInput.getElement().getStyle().setMarginTop(30, Style.Unit.PX);
        dateInput.getElement().getStyle().setMarginTop(30, Style.Unit.PX);
        timeInput.getElement().getStyle().setMarginTop(30, Style.Unit.PX);

        nameInfo.getElement().getStyle().setMarginTop(25, Style.Unit.PX);
        nameInfo.getElement().getStyle().setPaddingLeft(45, Style.Unit.PX);

        descriptionInfo.getElement().getStyle().setMarginTop(47, Style.Unit.PX);
        descriptionInfo.getElement().getStyle().setPaddingLeft(45, Style.Unit.PX);

        dateInfo.getElement().getStyle().setMarginTop(47, Style.Unit.PX);
        dateInfo.getElement().getStyle().setPaddingLeft(45, Style.Unit.PX);

        timeInfo.getElement().getStyle().setMarginTop(47, Style.Unit.PX);
        timeInfo.getElement().getStyle().setPaddingLeft(45, Style.Unit.PX);

        buttonPanel.getElement().getStyle().setMarginTop(25, Style.Unit.PX);
        buttonPanel.getElement().getStyle().setPaddingLeft(140, Style.Unit.PX);

        addButton.getElement().getStyle().setWidth(60, Style.Unit.PX);
        addButton.getElement().getStyle().setHeight(30, Style.Unit.PX);

        closeButton.getElement().getStyle().setWidth(60, Style.Unit.PX);
        closeButton.getElement().getStyle().setHeight(30, Style.Unit.PX);

        closeButton.getElement().getStyle().setMarginLeft(20, Style.Unit.PX);
    }

    public Button getAddButton() {
        return addButton;
    }

    public TextArea getNameInput() {
        return nameInput;
    }

    public TextArea getDescriptionInput() {
        return descriptionInput;
    }

    public TextArea getDateInput() {
        return dateInput;
    }

    public TextArea getTimeInput() {
        return timeInput;
    }
}

