package com.wrp.raywing.user.util;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.util.Random;

/**
 * @author wrp
 * @since 2025年09月05日 16:38
 **/
public class CaptchaUtils {

    public static final String chars = "ABCDEFGHJKLMNPQRSTUVWXYZabcdefghjkmnpqrstuvwxyz23456789";

    public static RenderedImage createCaptcha(String captchaText, int width, int height) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();

        // 设置背景色
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, width, height);

        // 创建随机数生成器
        Random random = new Random();

        // 设置字体
        Font font = new Font("Arial", Font.BOLD, 30);
        g2d.setFont(font);

        // 绘制验证码文本
        for (int i = 0; i < captchaText.length(); i++) {
            // 为每个字符设置随机颜色
            Color color = new Color(
                    random.nextInt(200),
                    random.nextInt(200),
                    random.nextInt(200)
            );
            g2d.setColor(color);

            // 为每个字符添加随机旋转
            double rotation = random.nextDouble() * 0.3 - 0.15;
            g2d.rotate(rotation, 25 + i * 30, 40);

            // 绘制字符
            g2d.drawString(String.valueOf(captchaText.charAt(i)), 15 + i * 30, 40);

            // 重置旋转
            g2d.rotate(-rotation, 25 + i * 30, 40);
        }

        // 添加干扰线
        for (int i = 0; i < 10; i++) {
            int x1 = random.nextInt(width);
            int y1 = random.nextInt(height);
            int x2 = random.nextInt(width);
            int y2 = random.nextInt(height);
            g2d.setColor(new Color(
                    random.nextInt(255),
                    random.nextInt(255),
                    random.nextInt(255),
                    random.nextInt(100) + 50
            ));
            g2d.drawLine(x1, y1, x2, y2);
        }

        // 添加噪点
        for (int i = 0; i < 200; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            g2d.setColor(new Color(
                    random.nextInt(255),
                    random.nextInt(255),
                    random.nextInt(255)
            ));
            g2d.fillRect(x, y, 1, 1);
        }

        g2d.dispose();
        return image;
    }

    public static String generateRandomText(int length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(chars.length());
            sb.append(chars.charAt(index));
        }

        return sb.toString();
    }
}
