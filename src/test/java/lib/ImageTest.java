package lib;

import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.imageio.ImageIO;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.Base64Utils;

class ImageTest {

	private static final String TEST_IMAGE_PATH = "image/test-image.jpg";

	@Test
	void 이미지정보_읽기() throws Exception {
		File testImage = new ClassPathResource(TEST_IMAGE_PATH).getFile();

		// Base64 인코딩/디코딩
		byte[] imageBytes = Files.readAllBytes(testImage.toPath());
		byte[] imageBase64 = Base64Utils.encode(imageBytes); // 하나의 공통으로 인코딩 해두었다가 다시 디코딩 : 시스템마다 다를 수 있으므로 동일하게 맞추기 위해서 base64로 인,디코딩을 거침
		byte[] reBytes = Base64Utils.decode(imageBase64);

		BufferedImage bufferedImage = ImageIO.read(testImage);
		System.out.println(bufferedImage);
	}


	@Test
	void 이미지타입_변환() throws Exception {
		File image = new ClassPathResource(TEST_IMAGE_PATH).getFile();
		BufferedImage jpgImage = ImageIO.read(image);

		BufferedImage pngImage = new BufferedImage(jpgImage.getWidth(), jpgImage.getHeight(), BufferedImage.TYPE_INT_RGB);
		pngImage.setData(jpgImage.getData());

		Path path = Paths.get(this.getClass().getResource("/image").getPath());
		File pngImageFile = new File(path.toString(), "convert.png");
		pngImageFile.createNewFile();
		ImageIO.write(pngImage, "png", pngImageFile);
	}
}