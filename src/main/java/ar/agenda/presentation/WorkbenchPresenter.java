package ar.agenda.presentation;

import ar.agenda.presentation.views.WorkbenchView;

import javax.inject.Inject;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WorkbenchPresenter implements ActionListener {

    WorkbenchView view;

    @Inject
    public WorkbenchPresenter(WorkbenchView view) {
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Trace
    public void displayWorkbench() {
        this.view.display();
    }
}
