package maze.controller;

import javax.swing.*;
import java.awt.event.ActionEvent;

public interface BaseController {
    void keyPressed(String bindingKey);
    void keyReleased(String bindingKey);

    default void registerKeyBindings(JComponent comp, String[] bindingKeys) {
        for (String bindingKey : bindingKeys) {
            comp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(bindingKey), bindingKey + "_pre");
            comp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released " + bindingKey), bindingKey + "_rel");
            System.out.println(bindingKey + ", released " + bindingKey );
            comp.getActionMap().put(bindingKey + "_pre", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    BaseController.this.keyPressed(bindingKey);
                }
            });
            comp.getActionMap().put(bindingKey + "_rel", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    BaseController.this.keyReleased(bindingKey);
                }
            });
        }
    }
}
