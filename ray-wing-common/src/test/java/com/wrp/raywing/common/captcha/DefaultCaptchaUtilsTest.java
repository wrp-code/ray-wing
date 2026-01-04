package com.wrp.raywing.common.captcha;

import com.wrp.raywing.common.util.CaptchaUtils;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

/**
 * 
 * @author wrp
 * @since 2025年09月05日 16:45
 **/
class DefaultCaptchaUtilsTest {
    @Test
    public void create() {
        // 生成随机验证码文本
        String captchaText = CaptchaUtils.generateRandomText(6);
        RenderedImage captcha = CaptchaUtils.createCaptcha(captchaText, 200, 50);

        try {
            ImageIO.write(captcha, "PNG", new File("E:\\temp\\captcha.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}