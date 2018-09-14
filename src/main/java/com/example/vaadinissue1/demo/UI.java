package com.example.vaadinissue1.demo;

import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import org.springframework.beans.factory.annotation.Autowired;

@SpringUI
public class UI extends com.vaadin.ui.UI {

	private View view;

	@Autowired
	public UI(View view) {
		this.view = view;
	}

	@Override
	protected void init(VaadinRequest vaadinRequest) {
		setContent(view);
	}
}
