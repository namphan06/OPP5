/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test1;

import javax.swing.*;
import java.awt.*;

public class LineChartWithDatabase extends JPanel {
    private String[] simTypes;
    private int[] contactCounts;

    public LineChartWithDatabase(String[] simTypes, int[] contactCounts) {
        this.simTypes = simTypes;
        this.contactCounts = contactCounts;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        int width = getWidth();
        int height = getHeight();

        // Vẽ trục x và y
        g2d.drawLine(50, height - 50, width - 50, height - 50); // Trục x
        g2d.drawLine(50, height - 50, 50, 50); // Trục y

        // Vẽ các điểm dữ liệu
        int dataSize = simTypes.length;
        int xSpacing = (width - 100) / (dataSize - 1);
        int maxDataValue = getMax(contactCounts);
        for (int i = 0; i < dataSize; i++) {
            int x = 50 + i * xSpacing;
            int y = height - 50 - (contactCounts[i] * (height - 100) / maxDataValue);
            g2d.fillOval(x - 3, y - 3, 6, 6); // Vẽ điểm

            // Hiển thị số lượng của từng thành phần
            g2d.drawString(String.valueOf(contactCounts[i]), x - 5, y - 10);

            if (i > 0) {
                int prevX = 50 + (i - 1) * xSpacing;
                int prevY = height - 50 - (contactCounts[i - 1] * (height - 100) / maxDataValue);
                g2d.drawLine(prevX, prevY, x, y); // Vẽ đường nối
            }
        }

        // Vẽ nhãn cho các dòng
        for (int i = 0; i < dataSize; i++) {
            int x = 50 + i * xSpacing;
            g2d.drawString(simTypes[i], x, height - 30);
        }


    }

    private int getMax(int[] array) {
        int max = array[0];
        for (int value : array) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

   
}
