package com.dw.zxing;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * 解析二维码
 * Created by daiwei on 2017/1/2.
 */
public class ReadQRCode {
    public static void main(String[] args) {
        MultiFormatReader multiFormatReader=new MultiFormatReader();
        //创建一个新 File 实例
        File file=new File("D:/img.png");
        try {
            BufferedImage bufferedImage=ImageIO.read(file);
            BinaryBitmap binaryBitmap=new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(bufferedImage)));
            HashMap hints=new HashMap();
            hints.put(EncodeHintType.CHARACTER_SET,"utf-8");		//设置支持中文编码
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);		//纠错等级。
            hints.put(EncodeHintType.MARGIN, 2);
            try {
                //解析二维码
                Result result = multiFormatReader.decode(binaryBitmap, hints);
                System.out.println("解析结果："+result.toString());
                System.out.println("二维码格式："+result.getBarcodeFormat());
                System.out.println("二维码文本类容："+result.getText());
            } catch (NotFoundException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
