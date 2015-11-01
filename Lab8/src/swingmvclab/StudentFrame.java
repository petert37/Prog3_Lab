package swingmvclab;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;

/*
 * A megjelenítendõ ablakunk osztálya.
 */
public class StudentFrame extends JFrame {

    /*
     * Ebben az objektumban vannak a hallgatói adatok.
     * A program indulás után betölti az adatokat fájlból, bezáráskor pedig kimenti oda.
     * 
     * NE MÓDOSÍTSD!
     */
    private StudentData data;
    private JTextField nameField, neptunField;

    /*
     * Itt hozzuk létre és adjuk hozzá az ablakunkhoz a különbözõ komponenseket
     * (táblázat, beviteli mezõ, gomb).
     */
    private void initComponents() {
        this.setLayout(new BorderLayout());

        JTable table = new JTable(data);
        table.setFillsViewportHeight(true);

        table.setDefaultRenderer(String.class, new StudentTableCellRenderer(table.getDefaultRenderer(String.class)));
        table.setDefaultRenderer(Boolean.class, new StudentTableCellRenderer(table.getDefaultRenderer(Boolean.class)));
        table.setDefaultRenderer(Integer.class, new StudentTableCellRenderer(table.getDefaultRenderer(Integer.class)));

        table.setRowSorter(new TableRowSorter<>(data));

        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane, BorderLayout.CENTER);

        JPanel southPanel = new JPanel();
        //southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.X_AXIS));

        nameField = new JTextField();
        nameField.setColumns(20);
        neptunField = new JTextField();
        neptunField.setColumns(6);

        JButton addButton = new JButton("Felvesz");
        addButton.addActionListener(e ->
                        data.addStudent(nameField.getText(), neptunField.getText())
        );

        southPanel.add(new JLabel("Nev:"));
        southPanel.add(nameField);
        southPanel.add(new JLabel("Neptun:"));
        southPanel.add(neptunField);
        southPanel.add(addButton);

        add(southPanel, BorderLayout.SOUTH);

        // ...
    }

    /*
     * Az ablak konstruktora.
     *
     * NE MÓDOSÍTSD!
     */
    @SuppressWarnings("unchecked")
    public StudentFrame() {
        super("Hallgatoi nyilvantartas");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Induláskor betöltjük az adatokat
        try {
            data = new StudentData();
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("students.dat"));
            data.students = (List<Student>)ois.readObject();
            ois.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }

        // Bezáráskor mentjük az adatokat
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("students.dat"));
                    oos.writeObject(data.students);
                    oos.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        // Felépítjük az ablakot
        setMinimumSize(new Dimension(500, 200));
        initComponents();
        setLocationRelativeTo(null);
    }

    /*
     * A program belépési pontja.
     * 
     * NE MÓDOSÍTSD!
     */
    public static void main(String[] args) {
        // Megjelenítjük az ablakot
        StudentFrame sf = new StudentFrame();
        sf.setVisible(true);
    }

    private class StudentTableCellRenderer implements TableCellRenderer{

        private TableCellRenderer renderer;

        public StudentTableCellRenderer(TableCellRenderer defaultRenderer){
            renderer = defaultRenderer;
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

            Component component = renderer.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            Student student = data.students.get(table.getRowSorter().convertRowIndexToModel(row));

            if(student.hasSignature() && student.getGrade() >= 2)
                component.setBackground(new Color(0x00ff00));
            else
                component.setBackground(new Color(0xff0000));

            return component;
        }
    }

}
