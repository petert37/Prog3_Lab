package swingmvclab;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

/*
 * A hallgatók adatait tároló osztály.
 */
public class StudentData extends AbstractTableModel {

    /*
     * Ez a tagváltozó tárolja a hallgatói adatokat.
     * NE MÓDOSÍTSD!
     */
    List<Student> students = new ArrayList<>();

    public void addStudent(String name, String neptun){
        students.add(new Student(name, neptun, false, 0));
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return students.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Student student = students.get(rowIndex);
        switch (columnIndex){
            case 0: return student.getName();
            case 1: return student.getNeptun();
            case 2: return student.hasSignature();
            case 3: return student.getGrade();
            default: return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column){
            case 0: return "Név";
            case 1: return "Neptun";
            case 2: return "Aláírás";
            case 3: return "Jegy";
            default: return super.getColumnName(column);
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex){
            case 0: return String.class;
            case 1: return String.class;
            case 2: return Boolean.class;
            case 3: return Integer.class;
            default: return super.getColumnClass(columnIndex);
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 2 || columnIndex == 3 || super.isCellEditable(rowIndex, columnIndex);
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        super.setValueAt(aValue, rowIndex, columnIndex);
        Student student = students.get(rowIndex);
        if(columnIndex == 2) student.setSignature((Boolean) aValue);
        if(columnIndex == 3) student.setGrade((Integer) aValue);
        fireTableDataChanged();
    }
}
