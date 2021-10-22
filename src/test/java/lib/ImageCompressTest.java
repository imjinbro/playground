package lib;

import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.FileImageOutputStream;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

class ImageCompressTest {

	@Test
	void dyddpccet() throws Exception {
		File file = new ClassPathResource("/image/test-jpg.jpg").getFile();
		BufferedImage image = ImageIO.read(file);

		ImageWriter writer = ImageIO.getImageWritersByFormatName("jpeg").next();
		ImageWriteParam param = writer.getDefaultWriteParam();
		param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
		param.setCompressionQuality(0.5f);

		Path path = Paths.get(this.getClass().getResource("/image").getPath());
		File resizedImageFile = new File(path.toString(), "resized.jpg");
		FileImageOutputStream output = new FileImageOutputStream(resizedImageFile);
		writer.setOutput(output);
		IIOImage iioImage = new IIOImage(image, null, null);
		writer.write(null, iioImage, param);
	}
}
