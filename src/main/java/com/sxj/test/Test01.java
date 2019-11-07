package com.sxj.test;

import com.sxj.test.model.BlockQueue;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.sql.Time;
import java.util.*;
import java.util.List;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * @program: test02
 * @description:
 * @author: sxj
 * @create: 2019-11-01 18:54
 **/
public class Test01 {
    public static void main(String[] args) {
        EventQueue.invokeLater(()->{
            JFrame frame = new BounceFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}

class BounceFrame extends JFrame{
    private BallComponet cmp;
    private static final int STEPS = 1000;
    private static final int DELAY = 3;

    public BounceFrame() throws HeadlessException {
        setTitle("Bonce");
        cmp = new BallComponet();
        add(cmp,BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
    }
}

class BallComponet extends JPanel{
    private static final int DEFAULT_WIDTH = 450;
    private static final int DEFAULT_HEIGHT = 350;
    List<Ball> balls = new ArrayList<>();

    public void addBall(Ball ball) {
        balls.add(ball);
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;
        for (Ball ball : balls){
            graphics2D.fill(ball.getShape());
        }
    }

    public Dimension getPreferieSize() {
        return new Dimension(DEFAULT_WIDTH,DEFAULT_HEIGHT);
    }
}

class Ball{
    private static final int XSIZE= 15;
    private static final int YSIZE= 15;
    private double x = 0;
    private double y = 0;
    private double dx = 0;
    private double dy = 0;

    public void move(Rectangle2D bounds) {
        x += dx;
        y += dy;
        if (x < bounds.getMinX()){
            x = bounds.getMinX();
            x -= dx;
        }
        if (x + XSIZE >= bounds.getMaxX()){
            x = bounds.getMaxX() - XSIZE;
            dx = -dx;
        }
        if (y <bounds.getMinY()){
            y = bounds.getMinY();
            y -= y;
        }
        if (y + YSIZE >= bounds.getMaxY()){
            y = bounds.getMaxY() - YSIZE;
            dy = -dy;
        }
    }
    public Ellipse2D getShape(){
        return new Ellipse2D.Double(x,y,XSIZE,YSIZE);
    }
}


