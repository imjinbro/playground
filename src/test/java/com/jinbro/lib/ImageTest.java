package com.jinbro.lib;

import java.awt.Color;
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

	private static final String TEST_JPG_PATH = "image/test-jpg.jpg";
	private static final String TEST_PNG_PATH = "image/test-png.png";

	@Test
	void 이미지정보_읽기() throws Exception {
		File testImage = new ClassPathResource(TEST_JPG_PATH).getFile();
		BufferedImage bufferedImage = ImageIO.read(testImage);

		System.out.println(bufferedImage.getWidth());
		System.out.println(bufferedImage.getHeight());

		long bytes = testImage.length();
		long kilobyte = bytes / 1024;
		long megabyte = kilobyte / 1024;

		System.out.println(bytes + "byte");
		System.out.println(kilobyte + "KB");
		System.out.println(megabyte + "MB");
	}

	@Test
	void 이미지_Base64_인코딩_디코딩() throws Exception {
		File testImage = new ClassPathResource(TEST_JPG_PATH).getFile();

		// Base64 인코딩/디코딩
		byte[] imageBytes = Files.readAllBytes(testImage.toPath());
		byte[] imageBase64 = Base64Utils.encode(imageBytes); // 하나의 공통으로 인코딩 해두었다가 다시 디코딩 : 시스템마다 다를 수 있으므로 동일하게 맞추기 위해서 base64로 인,디코딩을 거침
		byte[] reBytes = Base64Utils.decode(imageBase64);

		BufferedImage bufferedImage = ImageIO.read(testImage);
		System.out.println(bufferedImage);
	}


	@Test
	void jpg_to_png_이미지타입_변환() throws Exception {
		File image = new ClassPathResource(TEST_JPG_PATH).getFile();
		BufferedImage jpgImage = ImageIO.read(image);

		BufferedImage pngImage = new BufferedImage(jpgImage.getWidth(), jpgImage.getHeight(), BufferedImage.TYPE_INT_RGB);
		pngImage.setData(jpgImage.getData());

		Path path = Paths.get(this.getClass().getResource("/image").getPath());
		File pngImageFile = new File(path.toString(), "convert-png.png");
		pngImageFile.createNewFile();
		ImageIO.write(pngImage, "png", pngImageFile);
	}

	@Test
	void png_to_jpg_이미지타입_변환() throws Exception {
		File image = new ClassPathResource(TEST_PNG_PATH).getFile();
		BufferedImage pngImage = ImageIO.read(image);

		BufferedImage jpgImage = new BufferedImage(pngImage.getWidth(), pngImage.getHeight(), BufferedImage.TYPE_INT_RGB);
		jpgImage.createGraphics().drawImage(pngImage, 0, 0, Color.WHITE, null);

		Path path = Paths.get(this.getClass().getResource("/image").getPath());
		File pngImageFile = new File(path.toString(), "convert-jpg.jpg");
		ImageIO.write(jpgImage, "jpg", pngImageFile);
	}
}