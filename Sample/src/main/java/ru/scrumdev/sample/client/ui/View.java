package ru.scrumdev.sample.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.Window;
import ru.scrumdev.sample.client.*;

public class View extends Composite {
    interface MainPanelUiBinder extends UiBinder<Widget, View> {
    }

    private static MainPanelUiBinder ourUiBinder = GWT.create(MainPanelUiBinder.class);

    @UiField
    FlowPanel leftPanel;
    @UiField
    FlowPanel leftInnerPanel1;
    @UiField
    FlowPanel leftInnerPanel2;
    @UiField
    FlowPanel rightPanel;
    @UiField
    FlowPanel rightInnerPanel1;
    @UiField
    FlowPanel rightInnerPanel2;
    @UiField
    Label name;
    @UiField
    Label description;
    @UiField
    Label time;
    @UiField
    Label date;
    @UiField
    Button updateButton;

    private Presenter presenter;

    public View() {
        initWidget(ourUiBinder.createAndBindUi(this));
        presenter = new Presenter();
        presenter.setView(this);
        setUI();
    }

    public void setUI() {
        leftPanel.getElement().getStyle().setBorderColor("Gray");
        leftPanel.getElement().getStyle().setBorderStyle(Style.BorderStyle.SOLID);
        leftPanel.getElement().getStyle().setWidth(Window.getClientWidth() / 1.5, Style.Unit.PX);
        leftPanel.getElement().getStyle().setHeight(Window.getClientHeight() - 7, Style.Unit.PX);

        leftInnerPanel1.getElement().getStyle().setBorderColor("Gray");
        leftInnerPanel1.getElement().getStyle().setBorderStyle(Style.BorderStyle.SOLID);
        leftInnerPanel1.getElement().getStyle().setWidth(Window.getClientWidth() / 1.5, Style.Unit.PX);
        leftInnerPanel1.getElement().getStyle().setHeight(Window.getClientHeight() / 13.0, Style.Unit.PX);

        leftInnerPanel2.getElement().getStyle().setBorderColor("Gray");
        leftInnerPanel2.getElement().getStyle().setBorderStyle(Style.BorderStyle.SOLID);
        leftInnerPanel2.getElement().getStyle().setWidth(Window.getClientWidth() / 1.5, Style.Unit.PX);
        leftInnerPanel2.getElement().getStyle().setHeight((1.0 - 1.0 / 13.0) * Window.getClientHeight() - 16, Style.Unit.PX);

        rightPanel.getElement().getStyle().setBorderColor("Gray");
        rightPanel.getElement().getStyle().setBorderStyle(Style.BorderStyle.SOLID);
        rightPanel.getElement().getStyle().setWidth(0.33 * Window.getClientWidth() - 14, Style.Unit.PX);
        rightPanel.getElement().getStyle().setHeight(Window.getClientHeight() - 7, Style.Unit.PX);

        rightInnerPanel1.getElement().getStyle().setBorderColor("Gray");
        rightInnerPanel1.getElement().getStyle().setBorderStyle(Style.BorderStyle.SOLID);
        rightInnerPanel1.getElement().getStyle().setWidth(0.33 * Window.getClientWidth() - 15, Style.Unit.PX);
        rightInnerPanel1.getElement().getStyle().setHeight(Window.getClientHeight() / 3, Style.Unit.PX);

        rightInnerPanel2.getElement().getStyle().setBorderColor("Gray");
        rightInnerPanel2.getElement().getStyle().setBorderStyle(Style.BorderStyle.SOLID);
        rightInnerPanel2.getElement().getStyle().setWidth(0.33 * Window.getClientWidth() - 14, Style.Unit.PX);
        rightInnerPanel2.getElement().getStyle().setHeight(2.0 / 3.0 * Window.getClientHeight() - 15, Style.Unit.PX);

        name.getElement().getStyle().setPaddingTop(15, Style.Unit.PX);
        name.getElement().getStyle().setPaddingLeft(15, Style.Unit.PX);
        description.getElement().getStyle().setPaddingTop(40, Style.Unit.PX);
        description.getElement().getStyle().setPaddingLeft(15, Style.Unit.PX);
        time.getElement().getStyle().setPaddingTop(40, Style.Unit.PX);
        time.getElement().getStyle().setPaddingLeft(15, Style.Unit.PX);
        date.getElement().getStyle().setPaddingTop(40, Style.Unit.PX);
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