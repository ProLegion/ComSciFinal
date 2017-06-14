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
    private JLabel menuLabel, generationCounter;
    private JComboBox pres;
    private int generationNum = 0;
    private Grid grid;
    private DummyGrid DGrid = new DummyGrid(0, 0);
    private WriteGrid write = new WriteGrid();
    private Timer timer = new Timer(250, new TimerListener());
    private static final Color LIME = new Color(153, 255, 51);
    private static final Color STONE = new Color(128, 128, 128);
    private static final Color DARKGREY = new Color(32, 32, 32);
    ImageIcon img = new ImageIcon("ico_test.png");

    public Control(final Grid grid) {
        this.grid = grid;

        setLayout(new GridLayout(0, 1, 10, 10));

        menuLabel = new JLabel("          Menu");
        menuLabel.setFont(new Font("Century Gothic", Font.PLAIN, 12));

        menuLabel.setForeground(LIME);

        add(menuLabel);

        save = new JButton("Save");
        save.setFont(new Font("Century Gothic", Font.PLAIN, 12));
        save.setBackground(STONE);
        save.setForeground(LIME);
        save.setBorder(null);
        save.setFocusPainted(false);
        save.addActionListener(new BtnListener());
        save.setActionCommand("save");
        add(save);

        open = new JButton("Open");
        open.setFont(new Font("Century Gothic", Font.PLAIN, 12));
        open.setBackground(STONE);
        open.setForeground(LIME);
        open.setBorder(null);
        open.setFocusPainted(false);
        open.addActionListener(new BtnListener());
        open.setActionCommand("open");
        add(open);

        compare = new JButton("Compare");
        compare.setFont(new Font("Century Gothic", Font.PLAIN, 12));
        compare.setBackground(STONE);
        compare.setForeground(LIME);
        compare.setBorderPainted(false);
        compare.setFocusPainted(false);
        compare.addActionListener(new BtnListener());
        compare.setActionCommand("compare");
        add(compare);

        step = new JButton("Take Step");
        step.setFont(new Font("Century Gothic", Font.PLAIN, 12));
        step.setBackground(STONE);
        step.setForeground(LIME);
        step.setBorder(null);
        step.setFocusPainted(false);
        step.addActionListener(new BtnListener());
        step.setActionCommand("step");
        add(step);


        clear = new JButton("Clear Grid");
        clear.setFont(new Font("Century Gothic", Font.PLAIN, 12));
        clear.setBackground(STONE);
        clear.setForeground(LIME);
        clear.setBorderPainted(false);
        clear.setFocusPainted(false);
        clear.addActionListener(new BtnListener());
        clear.setActionCommand("clear");
        add(clear);

        run = new JButton("Run");
        run.setFont(new Font("Century Gothic", Font.PLAIN, 12));
        run.setBackground(STONE);
        run.setForeground(LIME);
        run.setBorderPainted(false);
        run.setFocusPainted(false);
        run.addActionListener(new BtnListener());
        run.setActionCommand("run");
        add(run);

        generationCounter = new JLabel("");
        generationCounter.setFont(new Font("Century Gothic", Font.PLAIN, 12));
        generationCounter.setForeground(LIME);
        add(generationCounter);

        recall = new JButton("Recall");
        recall.setFont(new Font("Century Gothic", Font.PLAIN, 12));
        recall.setBackground(STONE);
        recall.setForeground(LIME);
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
                    grid.scoreGrid(generationNum);
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
                    DGrid.setVals(grid.getHighRound(), grid.getHighCount());
                    write.writeScore(DGrid);
                    run.setText("Run");
                    run.setFont(new Font("Century Gothic", Font.PLAIN, 12));
                    run.setBackground(STONE);
                    run.setForeground(LIME);
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
                    CompareLoad window = new CompareLoad();
                    JPanel load = new JPanel();
                    JFrame compare = new JFrame("Compare");
                    CompareScore score = new CompareScore();
                    compare.setIconImage(img.getImage());
                    load.setBackground(DARKGREY);
                    window.setBackground(DARKGREY);
                    score.setBackground(DARKGREY);
                    load.add(window);
                    load.add(score);
                    compare.setResizable(true);
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
                grid.scoreGrid(generationNum);
                generationCounter.setText("Generation: " + generationNum);
            } else {
                DGrid.setVals(grid.getHighRound(), grid.getHighCount());
                write.writeScore(DGrid);
                timer.stop();
                run.setText("Run");
                run.setFont(new Font("Century Gothic", Font.PLAIN, 12));
                run.setBackground(STONE);
                run.setForeground(LIME);
                run.setActionCommand("run");
                System.out.println("Timer Running: " + timer.isRunning());
            }
        }
    }
}
