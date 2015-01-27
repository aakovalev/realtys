package ru.camoroh13.realtys.service.impl;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.camoroh13.realtys.dao.ImageDAO;
import ru.camoroh13.realtys.domain.Image;
import ru.camoroh13.realtys.service.ImageService;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: Konstantin
 */
@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    ImageDAO imageDAO;

    @Override
    public void save(Image image) {
        imageDAO.save(image);
    }

    @Override
    public void delete(Image image) {
        imageDAO.delete(image);
    }

    @Override
    public Image get(Integer id) {
        return imageDAO.get(id);
    }

/**
   * A logo picture to picture and watermark, effective this way
   * one instance : http://www.mvgod.com/images/poster/NaNiYaChuanQi2XKaiSiBinWangZi-10285-12811-19978-13383/3.jpg
   * @param srcImageFileName --
   *          source image
   * @param waterMarkImageFileName --
   *          watermark logo image
   * @param alpha --
   *          alpha composite  0 - 1,　0 Full transparency, 1 Opaque
   * @param mark_position --
   *          Watermark position, the four corners and central respectively,
   *       and so constant that ImageWatermark.MARK_LEFT_TOP
   */
  @Override
  public void markImage(String srcImageFileName, String waterMarkImageFileName, float alpha, int mark_position) {
      System.out.println("Image - watemark " + srcImageFileName + " - " + waterMarkImageFileName);

      try {
          File srcImageFile = new File(srcImageFileName);
          if (!srcImageFile.exists()) return;

          File markFile = new File(waterMarkImageFileName);
          if (!markFile.exists()) return;

          BufferedImage srcImage = ImageIO.read(srcImageFile);
          BufferedImage markImage = ImageIO.read(markFile);

          BufferedImage waterMarkedImage = drawWaterMark(srcImage, markImage, alpha);
          convertImageToJpeg(waterMarkedImage, srcImageFileName);
      } catch (IOException e) {
          e.printStackTrace();
      }

  }

    private BufferedImage drawWaterMark(BufferedImage srcImage, BufferedImage markImage, float alpha) {
        Graphics2D g = srcImage.createGraphics();
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));

        int width = srcImage.getWidth();
        int height = srcImage.getHeight();
        int mark_img_width = markImage.getWidth();
        int mark_img_height = markImage.getHeight();

        g.drawImage(markImage, (width - mark_img_width) / 2, (height - mark_img_height) / 2,
                mark_img_width, mark_img_height, null);

        System.out.println("W Size: " + (width - mark_img_width) / 2 + " - " + (height - mark_img_height) / 2 + " - " +
                mark_img_width + " - " + mark_img_height);

        g.dispose();

        return srcImage;
    }

    private void convertImageToJpeg(BufferedImage image, String fileNameToSave) throws IOException {
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(fileNameToSave);
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            encoder.encode(image);
        }
        finally {
            if (out != null)  {
                out.close();
            }
        }
    }

}
