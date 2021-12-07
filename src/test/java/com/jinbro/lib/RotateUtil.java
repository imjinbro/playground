package com.jinbro.lib;

import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifIFD0Directory;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.imageio.ImageIO;
import org.imgscalr.Scalr;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

public class RotateUtil {

	@Test
	void test() throws Exception {
		File image = new ClassPathResource("image/metadata-rotated-image.jpg").getFile();
		int orientation = getOrientation(image);
		System.out.println(orientation);

		BufferedImage bufferedImage = ImageIO.read(image);
		BufferedImage rotateImage = rotateImage(bufferedImage, orientation);

		Path path = Paths.get(this.getClass().getResource("/image").getPath());
		File outputfile = new File(path.toString(), "re-rotate.jpg");
		ImageIO.write(rotateImage, "jpg", outputfile);
	}

	private int getOrientation(File image) {
		try {
			final Metadata metadata = ImageMetadataReader.readMetadata(image);
			final Directory directory = metadata.getFirstDirectoryOfType(ExifIFD0Directory.class);

			if (directory != null && directory.containsTag(ExifIFD0Directory.TAG_ORIENTATION)) {
				return directory.getInt(ExifIFD0Directory.TAG_ORIENTATION);// 회전정보
			}
			return 1;
		} catch (Exception e) {
			return -999;
		}
	}

	private BufferedImage rotateImage(BufferedImage bufferedImage, int orientation) {
		switch (orientation) {
			case 6:
				bufferedImage = Scalr.rotate(bufferedImage, Scalr.Rotation.CW_90);
				break;

			case 1:
				break;

			case 3:
				bufferedImage = Scalr.rotate(bufferedImage, Scalr.Rotation.CW_180);
				break;

			case 8:
				bufferedImage = Scalr.rotate(bufferedImage, Scalr.Rotation.CW_270);
				break;

			default:
				break;
		}

		return bufferedImage;
	}
}
