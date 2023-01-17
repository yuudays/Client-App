package eltech.client;

import eltech.api.data.Movie;

import javax.swing.*;
import java.awt.*;

public class MovieRenderer extends DefaultListCellRenderer {
    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        setText(((Movie)value).getName());
        return this;
    }
}
