/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
/**
 *
 * @author IVXX_LeGioN
 */
public class Control extends JPanel {
    private JButton step, clear, run, open, save, recall, compare;
    private JLabel menuLabel,generationCounter;
    private JComboBox pres;
    private int generationNum = 0;
    private Grid grid;
    private Timer timer = new Timer(250, new TimerListener());
    private static final Color SEABLUE = new Color(153, 255, 51);
    private static final Color SEAGRAY = new Color(128, 128, 128);
    private static final Color DARKGREY = new Color(32, 32, 32);
    private String[] presets = {"Blank", "Blinker", "Exploder"};

    public Control(final Grid grid) {
        this.grid = grid;

        setLayout(new GridLayout(0, 1, 10, 10));

        menuLabel = new JLabel("          Menu");
        menuLabel.setFont(new Font("Century Gothic", Font.PLAIN, 12));
        
        menuLabel.setForeground(SEABLUE);
        
        add(menuLabel);
        
        save = new JButton("Save");
        save.setFont(new Font("Century Gothic", Font.PLAIN, 12));
        save.setBackground(SEAGRAY);
        save.setForeground(SEABLUE);
        save.setBorder(null);
        save.setFocusPainted(false);
        save.addActionListener(new BtnListener());
        save.setActionCommand("save");
        add(save);

        open = new JButton("Open");
        open.setFont(new Font("Century Gothic", Font.PLAIN, 12));
        open.setBackground(SEAGRAY);
        open.setForeground(SEABLUE);
        open.setBorder(null);
        open.setFocusPainted(false);
        open.addActionListener(new BtnListener());
        open.setActionCommand("open");
        add(open);
        
        compare = new JButton("Compare");
        compare.setFont(new Font("Century Gothic", Font.PLAIN, 12));
        compare.setBackground(SEAGRAY);
        compare.setForeground(SEABLUE);
        compare.setBorderPainted(false);
        compare.setFocusPainted(false);
        compare.addActionListener(new BtnListener());
        compare.setActionCommand("compare");
        add(compare);
        
        step = new JButton("Take Step");
        step.setFont(new Font("Century Gothic", Font.PLAIN, 12));
        step.setBackground(SEAGRAY);
        step.setForeground(SEABLUE);
        step.setBorder(null);
        step.setFocusPainted(false);
        step.addActionListener(new BtnListener());
        step.setActionCommand("step");
        add(step);

        
        clear = new JButton("Clear Grid");
        clear.setFont(new Font("Century Gothic", Font.PLAIN, 12));
        clear.setBackground(SEAGRAY);
        clear.setForeground(SEABLUE);
        clear.setBorderPainted(false);
        clear.setFocusPainted(false);
        clear.addActionListener(new BtnListener());
        clear.setActionCommand("clear");
        add(clear);

        run = new JButton("Run");
        run.setFont(new Font("Century Gothic", Font.PLAIN, 12));
        run.setBackground(SEAGRAY);
        run.setForeground(SEABLUE);
        run.setBorderPainted(false);
        run.setFocusPainted(false);
        run.addActionListener(new BtnListener());
        run.setActionCommand("run");
        add(run);

        generationCounter = new JLabel("");
        generationCounter.setFont(new Font("Century Gothic", Font.PLAIN, 12));
        generationCounter.setForeground(SEABLUE);
        add(generationCounter);

        recall = new JButton("Recall");
        recall.setFont(new Font("Century Gothic", Font.PLAIN, 12));
        recall.setBackground(SEAGRAY);
        recall.setForeground(SEABLUE);
        recall.setBorderPainted(false);
        recall.setFocusPainted(false);
        recall.addActionListener(new BtnListener());
        recall.setActionCommand("recall");
        recall.setVisible(false);
        add(recall);
        
        

        setOpaque(false);
    }

    class BtnListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String whichBtn = e.getActionCommand();
            switch (whichBtn) {
                case "save":
                    grid.saveGrid();
                    break;
                case "open":
                    grid.loadPattern();
                    generationNum = 0;
                    generationCounter.setText("");
                    break;
                case "step":
                    grid.step();
                    generationNum++;
                    generationCounter.setText("Generation: " + generationNum);
                    System.out.println("Single Step Taken");
                    break;
                case "clear":
                    grid.clear();
                    System.out.println("Grid Cleared");
                    generationNum = 0;
                    generationCounter.setText("");
                    break;
                case "run":
                    grid.holdGrid();
                    timer.start();
                    run.setText("STOP");
                    run.setFont(new Font("Century Gothic", Font.BOLD, 12));
                    run.setBackground(Color.RED);
                    run.setForeground(Color.BLACK);
                    run.setActionCommand("stop");
                    System.out.println("Timer Running: " + timer.isRunning());
                    recall.setVisible(true);
                    break;
                case "stop":
                    timer.stop();
                    run.setText("Run");
                    run.setFont(new Font("Century Gothic", Font.PLAIN, 12));
                    run.setBackground(SEAGRAY);
                    run.setForeground(SEABLUE);
                    run.setActionCommand("run");
                    System.out.println("Timer Running: " + timer.isRunning());
                    break;
                case "recall":
                    grid.recallGrid();
                    generationNum = 0;
                    generationCounter.setText("");
                    recall.setVisible(false);
                    break;
                case "compare":
                    CompareLoader window = new CompareLoader();
                    JPanel load = new JPanel();
                    JFrame compare = new JFrame("Compare");
                    load.setBackground(DARKGREY);
                    window.setBackground(DARKGREY);
                    load.add(window);
                    compare.setResizable(false);
                    compare.setLocationRelativeTo(null);
                    compare.add(load);
                    compare.pack();
                    compare.setVisible(true);
                    break;
            }
 
        }
    }

    class TimerListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (!grid.isEmpty()) {
                grid.step();
                generationNum++;
                generationCounter.setText("Generation: " + generationNum);
            } else {
                timer.stop();
                run.setText("Run");
                run.setFont(new Font("Century Gothic", Font.PLAIN, 12));
                run.setBackground(SEAGRAY);
                run.setForeground(SEABLUE);
                run.setActionCommand("run");
                System.out.println("Timer Running: " + timer.isRunning());
            }
        }
    }    
}
