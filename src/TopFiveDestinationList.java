import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class TopFiveDestinationList {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	TopDestinationListFrame topDestinationListFrame = new TopDestinationListFrame();
                topDestinationListFrame.setTitle("Top 5 Destination List");
                topDestinationListFrame.setVisible(true);
            }
        });
    }
}


class TopDestinationListFrame extends JFrame {
    private DefaultListModel listModel;

    public TopDestinationListFrame() {
        super("Top Five Destination List");

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(900, 750);
        
        // North panel for logo and searchbar
        JPanel topPanel = new JPanel(); 
        topPanel.setBackground(new Color(16, 71, 222));
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS)); // Set layout manager, chose BoxLayout to put multiple components horizontally
        topPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        
        // Make and Add logo txt
        JLabel snhuTravelLabel = new JLabel("SNHU Travel");
        snhuTravelLabel.setFont(new Font("Dialog-BoldItalic", Font.BOLD, 20));
        snhuTravelLabel.setForeground(new Color(255, 201, 25));
        topPanel.add(snhuTravelLabel);
        Border SoftBevelLower = BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.black, Color.blue); // Create styled border
        snhuTravelLabel.setBorder(SoftBevelLower); // Set the border to new styled border
        
        // Create search bar
        JTextField searchBar = new JTextField();
        searchBar.setPreferredSize(new Dimension(250, 40));
        topPanel.add(searchBar); // Add search bar to topPanel
        
        // Create search button
        JButton button = new JButton("Search");
        topPanel.add(button);
        
        getContentPane().add(topPanel, BorderLayout.NORTH);
        
        

        listModel = new DefaultListModel();


        //Make updates to your top 5 list below. Import the new image files to resources directory.
        addDestinationNameAndPicture("1. Top Destination (short sentence description)", new ImageIcon(getClass().getResource("/resources/fuji.jpg")));
        addDestinationNameAndPicture("2. 2nd Top Destination", new ImageIcon(getClass().getResource("/resources/valencia.jpg")));
        addDestinationNameAndPicture("3. 3rd Top Destination", new ImageIcon(getClass().getResource("/resources/conchal.jpg")));
        addDestinationNameAndPicture("4. 4th Top Destination", new ImageIcon(getClass().getResource("/resources/italy.jpg")));
        addDestinationNameAndPicture("5. 5th Top Destination", new ImageIcon(getClass().getResource("/resources/yellowstone.jpeg")));
        
        JList list = new JList(listModel);
        JScrollPane scrollPane = new JScrollPane(list);

        TextAndIconListCellRenderer renderer = new TextAndIconListCellRenderer(2);

        list.setCellRenderer(renderer);


        getContentPane().add(scrollPane, BorderLayout.CENTER);
    }

    private void addDestinationNameAndPicture(String text, Icon icon) {
        TextAndIcon tai = new TextAndIcon(text, icon);
        listModel.addElement(tai);
    }
}


class TextAndIcon {
    private String text;
    private Icon icon;

    public TextAndIcon(String text, Icon icon) {
        this.text = text;
        this.icon = icon;
    }

    public String getText() {
        return text;
    }

    public Icon getIcon() {
        return icon;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }
}


class TextAndIconListCellRenderer extends JLabel implements ListCellRenderer {
    private static final Border NO_FOCUS_BORDER = new EmptyBorder(1, 1, 1, 1);

    private Border insideBorder;

    public TextAndIconListCellRenderer() {
        this(0, 0, 0, 0);
    }

    public TextAndIconListCellRenderer(int padding) {
        this(padding, padding, padding, padding);
    }

    public TextAndIconListCellRenderer(int topPadding, int rightPadding, int bottomPadding, int leftPadding) {
        insideBorder = BorderFactory.createEmptyBorder(topPadding, leftPadding, bottomPadding, rightPadding);
        setOpaque(true);
    }

    public Component getListCellRendererComponent(JList list, Object value,
    int index, boolean isSelected, boolean hasFocus) {
        // The object from the combo box model MUST be a TextAndIcon.
        TextAndIcon tai = (TextAndIcon) value;

        // Sets text and icon on 'this' JLabel.
        setText(tai.getText());
        setIcon(tai.getIcon());

        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }

        Border outsideBorder;

        if (hasFocus) {
            outsideBorder = UIManager.getBorder("List.focusCellHighlightBorder");
        } else {
            outsideBorder = NO_FOCUS_BORDER;
        }

        setBorder(BorderFactory.createCompoundBorder(outsideBorder, insideBorder));
        setComponentOrientation(list.getComponentOrientation());
        setEnabled(list.isEnabled());
        setFont(list.getFont());

        return this;
    }

    // The following methods are overridden to be empty for performance
    // reasons. If you want to understand better why, please read:
    //
    // http://java.sun.com/javase/6/docs/api/javax/swing/DefaultListCellRenderer.html#override

    public void validate() {}
    public void invalidate() {}
    public void repaint() {}
    public void revalidate() {}
    public void repaint(long tm, int x, int y, int width, int height) {}
    public void repaint(Rectangle r) {}
}