/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bai7;

    

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Vector;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;



 class bai7 extends JFrame implements ActionListener,ItemListener{
     private JTable table;
    private JButton b1, b2, b3;
    private JTextField t1;
    private JComboBox c1;
    private JPanel p1, p2;
    private JLabel l1, l2;
    private JFileChooser f;
    private JScrollPane scroll;

    public bai7() {
        setTitle("Demo JFileChooser");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(100, 100);
        setSize(500, 500);
        setLayout(new BorderLayout());
        p1 = new JPanel();
        p2 = new JPanel();
        add(p1, BorderLayout.NORTH);
        add(p2, BorderLayout.SOUTH);
        p1.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 5));
        b1 = new JButton("Open");
        l1 = new JLabel("Source:");
        t1 = new JTextField(20);
        t1.setPreferredSize(new Dimension(100, 30));
        t1.setFont(new Font("Times new roman", Font.BOLD, 12));
        t1.setEditable(false);
        p1.add(b1);
        p1.add(l1);
        p1.add(t1);
        p2.setLayout(new FlowLayout());
        l2 = new JLabel("Sort by:");
        String[] tmp = new String[]{"ID", "Name", "Class", "Score"};
        c1 = new JComboBox(tmp);
        c1.addItemListener(this);
        p2.add(l2);
        p2.add(c1);
        setVisible(true);
        b1.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ("Open".equals(e.getActionCommand())) {
            f = new JFileChooser();
            int returnVal = f.showOpenDialog(null);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = f.getSelectedFile();
                t1.setText(file.getAbsolutePath());
                
                    // doan nay e them vao ne
                    ArrayList<SinhVienPTIT> list =new ArrayList<>();
                    try (Scanner s=new Scanner(file)){
                        while(s.hasNextLine()){
                            int maSv=0;
                            try{// doc vao ma sv
                                maSv=Integer.parseInt(s.nextLine());
                            }
                            catch(NumberFormatException en)
                            {
                                System.out.println(en);
                            }
                            String ten=s.nextLine();
                            String lop=s.nextLine();


                            double dtb=0;
                            try{// doc vao diem tb
                            dtb=Double.parseDouble(s.nextLine());
                            }
                            catch(NumberFormatException en){
                                System.out.println(en);
                            }

                            // try catch de add sv vao array list
                            try{
                                SinhVienPTIT sv=new SinhVienPTIT();
                                sv.setMaSV(maSv);
                                sv.setTen(ten);
                                sv.setLop(lop);
                                sv.setDtb(dtb);
                                list.add(sv);
                            } catch (maSVException ex) {
                                System.out.println(ex);
                            } catch (tenException ex) {
                                System.out.println(ex);
                            } catch (lopException ex) {
                                System.out.println(ex);
                            } catch (dtbException ex) {
                                System.out.println(ex);
                            }


                        }

                    } catch (FileNotFoundException ex) {
                        System.out.println(ex);
                    }
                    
        
                    Vector<Vector<String>> v=new Vector<>();
                    for(int i=0;i<list.size();i++){
                        Vector<String> temp=new Vector<>();
                        temp.add(list.get(i).getMaSV()+"");
                        temp.add(list.get(i).getTen());
                        temp.add(list.get(i).getLop());
                        temp.add(list.get(i).getDtb()+"");
                        v.add(temp);
                    }
                    
                    Vector<String> column = new Vector<>();
                    column.add("ID");
                    column.add("Name");
                    column.add("Class");
                    column.add("Score");
                    table = new JTable(v, column);
                    scroll = new JScrollPane(table);
                    table.setAutoCreateRowSorter(true); // auto sort

                    add(scroll, BorderLayout.CENTER);

                    setVisible(true);
                
            }
        }

    }

    public static void main(String[] args) {
        new bai7();

    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == c1) {
            String c = (String) c1.getSelectedItem();
            TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
            table.setRowSorter(sorter);
            ArrayList<RowSorter.SortKey> sortKeys = new ArrayList<>();

            switch (c) {
                case "ID":
                    sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
                    break;
                case "Name":

                    sortKeys.add(new RowSorter.SortKey(1, SortOrder.ASCENDING));

                    break;
                case "Class":
                    sortKeys.add(new RowSorter.SortKey(2, SortOrder.ASCENDING));
                    break;
                case "Score":
                    sortKeys.add(new RowSorter.SortKey(3, SortOrder.ASCENDING));
                    break;
                default:
                    break;
            }

            sorter.setSortKeys(sortKeys);
        }

    }
}   
