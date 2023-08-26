package com.defen.fenapi.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.Random;

public class CaptchaService {
    private static final String CAPTCHA_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    private static final int WIDTH = 150;
    private static final int HEIGHT = 50;

    public String generateCaptcha(OutputStream outputStream) {
        BufferedImage bufferedImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bufferedImage.createGraphics();
        
        // Background
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        
        // Text
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 30));
        Random random = new Random();
        StringBuilder captchaString = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            int index = random.nextInt(CAPTCHA_CHARS.length());
            char ch = CAPTCHA_CHARS.charAt(index);
            captchaString.append(ch);
            g.drawString(String.valueOf(ch), i * 30, 30);
        }
        
        // Lines
        g.setColor(Color.GRAY);
        for (int i = 0; i < 3; i++) {
            int x1 = random.nextInt(WIDTH);
            int y1 = random.nextInt(HEIGHT);
            int x2 = random.nextInt(WIDTH);
            int y2 = random.nextInt(HEIGHT);
            g.drawLine(x1, y1, x2, y2);
        }

        g.dispose();
        try {
            ImageIO.write(bufferedImage, "png", outputStream);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return captchaString.toString();
    }

    public boolean verifyCaptcha(String userInput, String actualCaptcha) {
        return userInput != null && userInput.equalsIgnoreCase(actualCaptcha);
    }
}
