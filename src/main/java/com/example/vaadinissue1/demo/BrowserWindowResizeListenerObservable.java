package com.example.vaadinissue1.demo;

import com.vaadin.server.Page;
import org.springframework.stereotype.Service;

import java.util.Observable;

@Service
public class BrowserWindowResizeListenerObservable extends Observable implements Page.BrowserWindowResizeListener {

	@Override
	public void browserWindowResized(Page.BrowserWindowResizeEvent browserWindowResizeEvent) {
		this.setChanged();
		this.notifyObservers();
	}
}
