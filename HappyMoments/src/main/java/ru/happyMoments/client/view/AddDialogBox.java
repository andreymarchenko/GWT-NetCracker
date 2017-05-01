package ru.happyMoments.client.view;


import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import org.vectomatic.file.FileUploadExt;


import javax.ws.rs.POST;
import javax.ws.rs.Path;

public class AddDialogBox extends DialogBox {

    private VerticalPanel mainPanel;
    private HorizontalPanel horizontalPanel;
    private VerticalPanel leftPanel;
    private VerticalPanel rightPanel;
    private HorizontalPanel buttonPanel;
    private TextBox nameInput;
    private TextBox descriptionInput;
    private TextBox dateInput;
    private TextBox timeInput;
    private Label nameInfo;
    private Label descriptionInfo;
    private Label dateInfo;
    private Label timeInfo;
    private Button addButton;
    private Button closeButton;
    private HorizontalPanel fileUploadPanel;
    private FileUploadExt fileUpload;
    private Label addingEvent;

    public AddDialogBox() {
        createMarkup();
        bind(this);
        bind();
        super.setAnimationEnabled(true);
        super.setGlassEnabled(true);
    }

    private void createMarkup() {
        addingEvent = new Label("Добавление события");
        fileUpload = new FileUploadExt();
        fileUploadPanel = new HorizontalPanel();
        fileUploadPanel.add(fileUpload);
        mainPanel = new VerticalPanel();
        horizontalPanel = new HorizontalPanel();
        buttonPanel = new HorizontalPanel();
        leftPanel = new VerticalPanel();
        rightPanel = new VerticalPanel();
        nameInput = new TextBox();
        descriptionInput = new TextBox();
        dateInput = new TextBox();
        timeInput = new TextBox();
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
        createLine();
        rightPanel.add(descriptionInput);
        createLine();
        rightPanel.add(dateInput);
        createLine();
        rightPanel.add(timeInput);
        createLine();
        horizontalPanel.add(leftPanel);
        horizontalPanel.add(rightPanel);
        mainPanel.add(addingEvent);
        mainPanel.add(horizontalPanel);
        mainPanel.add(fileUploadPanel);
        mainPanel.add(buttonPanel);
        this.add(mainPanel);
        setStyle();
    }

    private void createLine() {
        FlowPanel line = new FlowPanel();
        line.getElement().getStyle().setHeight(1, Style.Unit.PX);
        line.getElement().getStyle().setBackgroundColor("Gray");
        rightPanel.add(line);
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
        this.getElement().getStyle().setHeight(420, Style.Unit.PX);
        this.getElement().getStyle().setBackgroundColor("White");
        this.getElement().getStyle().setOpacity(0.9);

        addingEvent.getElement().getStyle().setMarginTop(30, Style.Unit.PX);
        addingEvent.getElement().getStyle().setMarginLeft(115, Style.Unit.PX);
        addingEvent.getElement().getStyle().setFontSize(20, Style.Unit.PX);

        rightPanel.getElement().getStyle().setPaddingLeft(35, Style.Unit.PX);

        nameInput.getElement().getStyle().setBorderStyle(Style.BorderStyle.NONE);
        nameInput.getElement().getStyle().setHeight(25, Style.Unit.PX);
        descriptionInput.getElement().getStyle().setBorderStyle(Style.BorderStyle.NONE);
        descriptionInput.getElement().getStyle().setHeight(25, Style.Unit.PX);
        dateInput.getElement().getStyle().setBorderStyle(Style.BorderStyle.NONE);
        dateInput.getElement().getStyle().setHeight(25, Style.Unit.PX);
        timeInput.getElement().getStyle().setBorderStyle(Style.BorderStyle.NONE);
        timeInput.getElement().getStyle().setHeight(25, Style.Unit.PX);

        nameInput.getElement().getStyle().setMarginTop(25, Style.Unit.PX);
        descriptionInput.getElement().getStyle().setMarginTop(33, Style.Unit.PX);
        dateInput.getElement().getStyle().setMarginTop(33, Style.Unit.PX);
        timeInput.getElement().getStyle().setMarginTop(33, Style.Unit.PX);

        nameInfo.getElement().getStyle().setMarginTop(30, Style.Unit.PX);
        nameInfo.getElement().getStyle().setPaddingLeft(45, Style.Unit.PX);

        descriptionInfo.getElement().getStyle().setMarginTop(42, Style.Unit.PX);
        descriptionInfo.getElement().getStyle().setPaddingLeft(45, Style.Unit.PX);

        dateInfo.getElement().getStyle().setMarginTop(42, Style.Unit.PX);
        dateInfo.getElement().getStyle().setPaddingLeft(45, Style.Unit.PX);

        timeInfo.getElement().getStyle().setMarginTop(42, Style.Unit.PX);
        timeInfo.getElement().getStyle().setPaddingLeft(45, Style.Unit.PX);

        buttonPanel.getElement().getStyle().setMarginTop(25, Style.Unit.PX);
        buttonPanel.getElement().getStyle().setPaddingLeft(140, Style.Unit.PX);

        fileUploadPanel.getElement().getStyle().setMarginTop(35, Style.Unit.PX);
        fileUploadPanel.getElement().getStyle().setMarginLeft(100, Style.Unit.PX);

        addButton.getElement().getStyle().setWidth(60, Style.Unit.PX);
        addButton.getElement().getStyle().setHeight(30, Style.Unit.PX);

        closeButton.getElement().getStyle().setWidth(60, Style.Unit.PX);
        closeButton.getElement().getStyle().setHeight(30, Style.Unit.PX);

        closeButton.getElement().getStyle().setMarginLeft(20, Style.Unit.PX);

        leftPanel.getElement().getStyle().setMarginTop(10, Style.Unit.PX);
        rightPanel.getElement().getStyle().setMarginTop(10, Style.Unit.PX);

        restoreHints();

    }

    public void restoreHints() {
        nameInput.setText("Прогулка");
        nameInput.getElement().getStyle().setColor("adadad");

        descriptionInput.setText("Гулял в парке");
        descriptionInput.getElement().getStyle().setColor("adadad");

        dateInput.setText("21 05 2016");
        dateInput.getElement().getStyle().setColor("adadad");

        timeInput.setText("16:55");
        timeInput.getElement().getStyle().setColor("adadad");
    }

    private void bind() {
        nameInput.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                nameInput.setText("");
                nameInput.getElement().getStyle().setColor("000000");
            }
        });
        descriptionInput.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                descriptionInput.setText("");
                descriptionInput.getElement().getStyle().setColor("000000");
            }
        });

        dateInput.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                dateInput.setText("");
                dateInput.getElement().getStyle().setColor("000000");
            }
        });

        timeInput.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                timeInput.setText("");
                timeInput.getElement().getStyle().setColor("000000");
            }
        });

    }

    public Button getAddButton() {
        return addButton;
    }

    public TextBox getNameInput() {
        return nameInput;
    }

    public TextBox getDescriptionInput() {
        return descriptionInput;
    }

    public TextBox getDateInput() {
        return dateInput;
    }

    public TextBox getTimeInput() {
        return timeInput;
    }

    public FileUploadExt getFileUpload() {
        return fileUpload;
    }

    public HorizontalPanel getForm() {
        return fileUploadPanel;
    }

    public void setMainPanel(VerticalPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    public void setNameInput(TextBox nameInput) {
        this.nameInput = nameInput;
    }

    public void setDescriptionInput(TextBox descriptionInput) {
        this.descriptionInput = descriptionInput;
    }

    public void setDateInput(TextBox dateInput) {
        this.dateInput = dateInput;
    }

    public void setTimeInput(TextBox timeInput) {
        this.timeInput = timeInput;
    }
}

