package com.example.vaadinissue1.demo;

import com.vaadin.data.TreeData;
import com.vaadin.data.provider.TreeDataProvider;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotNull;

@SpringComponent
@UIScope
public class PopupContentView extends VerticalLayout {

	private SubmitCallback submitCallback;
	private Button submitBtn;

	private HorizontalSplitPanel horizontalSplitPanel;

	@PostConstruct
	void init() {
		TreeData<String> treeData = new TreeData<>();
		treeData.addItems(null, "TestRoot1", "TestRoot2");
		treeData.addItems("TestRoot1", "Children1", "Children2");
		TreeDataProvider<String> treeDataProvider = new TreeDataProvider<>(treeData);
		Tree<String> tree = new Tree<>(treeDataProvider);
		horizontalSplitPanel = new HorizontalSplitPanel();
		horizontalSplitPanel.addComponent(new Panel(tree));
		horizontalSplitPanel.addComponent(new Panel(new DateField()));

		submitBtn = new Button("Submit Popup");
		submitBtn.addClickListener(clickEvent -> submit());
	}

	void build(@NotNull SubmitCallback callback) {
		removeAllComponents();
		this.addComponent(horizontalSplitPanel);
		this.addComponent(submitBtn);
		this.setExpandRatio(horizontalSplitPanel, 1.0f);
		this.submitCallback = callback;
	}

	private void submit() {
		submitCallback.submit("Submitted");
	}

	@FunctionalInterface
	public interface SubmitCallback {

		void submit(String someContent);
	}
}
