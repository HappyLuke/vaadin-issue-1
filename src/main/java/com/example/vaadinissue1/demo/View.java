package com.example.vaadinissue1.demo;

import com.vaadin.server.Page;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PopupView;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.Observable;
import java.util.Observer;

@SpringComponent
@UIScope
public class View extends VerticalLayout implements Observer {

	private final PopupContentView popupContentView;

	private PopupView popup;

	private Button button;

	@Autowired
	public View(PopupContentView popupContentView) {
		this.popupContentView = popupContentView;
	}

	@PostConstruct
	void init() {
		button = new Button("Open Popup");
		button.addClickListener(clickEvent -> openPopup());
		this.addComponent(button);
	}

	private void openPopup() {
		if (popup == null) {
			setSizeOfPopUp();
			// popup will adjust automatically to size of content
			popup = new PopupView(null, popupContentView);
			popup.addPopupVisibilityListener(event -> {
				if (event.isPopupVisible()) {
					popupContentView.build(this::submitted);
				}
			});
			popup.setHideOnMouseOut(false);
			this.addComponent(popup);
		}
		popup.setPopupVisible(true);
	}

	private void setSizeOfPopUp() {
		if (popupContentView != null) {
			popupContentView.setWidth(Page.getCurrent().getBrowserWindowWidth(), Unit.PIXELS);
			popupContentView.setHeight(((float) Page.getCurrent().getBrowserWindowHeight()) / 3, Unit.PIXELS);
		}
	}

	private void submitted(String s) {
		popup.setVisible(false);
		Notification.show(s);
	}

	@Override
	public void update(Observable observable, Object o) {
		if (observable instanceof BrowserWindowResizeListenerObservable) {
			setSizeOfPopUp();
		}
	}
}

