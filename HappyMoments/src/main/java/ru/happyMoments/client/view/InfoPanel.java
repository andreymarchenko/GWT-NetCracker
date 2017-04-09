package ru.happyMoments.client.view;

import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;

public class InfoPanel extends PopupPanel{

    private VerticalPanel contentPanel;
    private FlowPanel imagePanel;
    private FlowPanel namePanel;
    private Image image;
    private Label name;

    public InfoPanel() {
        createMarkUp();
        this.show();
    }

    private void createMarkUp() {
        this.getElement().getStyle().setBackgroundColor("White");
        this.getElement().getStyle().setHeight(Window.getClientHeight() - 20, Style.Unit.PX);
        this.getElement().getStyle().setWidth(Window.getClientWidth() / 4.5 - 10, Style.Unit.PX);
        this.getElement().getStyle().setMarginTop(10, Style.Unit.PX);
        this.getElement().getStyle().setMarginLeft(7.0 / 9.0 * Window.getClientWidth(), Style.Unit.PX);

        contentPanel = new VerticalPanel();
        contentPanel.getElement().getStyle().setHeight(Window.getClientHeight() - 20, Style.Unit.PX);
        contentPanel.getElement().getStyle().setWidth(Window.getClientWidth() / 4.5 - 10, Style.Unit.PX);

        imagePanel = new FlowPanel();
        imagePanel.getElement().getStyle().setHeight(Window.getClientWidth() / 5.5 - 10, Style.Unit.PX);
        imagePanel.getElement().getStyle().setWidth(Window.getClientWidth() / 4.5 - 10, Style.Unit.PX);

        image = new Image();
        image.getElement().getStyle().setHeight(Window.getClientWidth() / 5.5 - 10, Style.Unit.PX);
        image.getElement().getStyle().setWidth(Window.getClientWidth() / 4.5 - 10, Style.Unit.PX);

        namePanel = new FlowPanel();
        namePanel.getElement().getStyle().setHeight(Window.getClientHeight() / 7, Style.Unit.PX);
        namePanel.getElement().getStyle().setWidth(Window.getClientWidth() / 4.5 - 10, Style.Unit.PX);
        namePanel.getElement().getStyle().setBackgroundColor("4285f4");
        namePanel.getElement().getStyle().setMarginTop(-Window.getClientHeight() / 3.175, Style.Unit.PX);

        name = new Label();
        name.getElement().getStyle().setColor("White");
        name.getElement().getStyle().setFontSize(25, Style.Unit.PX);

        namePanel.add(name);
        imagePanel.add(image);
        contentPanel.add(imagePanel);
        contentPanel.add(namePanel);

        this.add(contentPanel);
    }

    public Image getImage() {
        return image;
    }

    public Label getName() {
        return name;
    }
}
