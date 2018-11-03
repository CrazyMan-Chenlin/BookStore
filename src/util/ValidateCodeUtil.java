package util;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

public class ValidateCodeUtil {
    static Random random =new Random();
    //生成验证码
    public static String getNewCode(OutputStream outputStream) throws IOException {
        int width=120;
        int height=50;
        //生成一张图片
        BufferedImage bi=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        //绘画图片
        //拿到画笔
        Graphics graphics=bi.getGraphics();
        //将底色设置为灰色
        graphics.setColor(Color.gray);
        //进行矩形填充
        graphics.fillRect(0,0,width,height);
        //生成4个随机数
        String number="";
        for (int i = 0; i <4 ; i++) {
            number+=random.nextInt(10);
        }
        //把颜色设置为黑色
        graphics.setColor(Color.BLACK);
        //设置字体
        graphics.setFont(new Font("黑体",Font.ITALIC,35));
        //将随机数画在图片上
        graphics.drawString(number,20,40);
        //画30条干扰线
        for (int i = 0; i <30 ; i++) {
            int x1=random.nextInt(width);
            int x2=random.nextInt(width);
            int y1=random.nextInt(height);
            int y2=random.nextInt(height);
            //设置干扰线五颜六色
            graphics.setColor(getRamColor());
            graphics.drawLine(x1,y1,x2,y2);
        }
        //输出文件
        ImageIO.write(bi,"png",outputStream);
         return number;
    }
    //生成随机颜色，rgb不同数值能调出不同颜色
    private static Color getRamColor(){
        int r=random.nextInt(255);
        int g=random.nextInt(255);
        int b=random.nextInt(255);
        return new Color(r,g,b);
    }
}
