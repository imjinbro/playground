package com.jinbro.lib;

import static org.testcontainers.containers.localstack.LocalStackContainer.Service.S3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import java.io.File;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.testcontainers.containers.localstack.LocalStackContainer;
import org.testcontainers.utility.DockerImageName;

/*
	AWS S3에 직접 업로드 하지 않고, docker localstack 컨테이너로 올린 s3에 업로드
 */
class S3ImageUpload {

	@Rule
	private LocalStackContainer localstack = new LocalStackContainer(DockerImageName.parse("localstack/localstack:0.11.3")).withServices(S3);

	@BeforeEach
	void setUp() {
		localstack.start();
	}

	@Test
	@DisplayName("S3 이미지 업로드")
	void fqxphstyp() throws Exception {
		AmazonS3 s3Client = AmazonS3ClientBuilder
			.standard()
			.withEndpointConfiguration(localstack.getEndpointConfiguration(S3))
			.withCredentials(localstack.getDefaultCredentialsProvider())
			.build();

		File imageFile = new ClassPathResource("/image/test-jpg.jpg").getFile();
		String bucketName = "test-image";
		String dirName = "upload";
		String fileName = dirName + "/" + imageFile.getName();

		ObjectMetadata metadata = new ObjectMetadata();
		Date expireTime = Date.from(LocalDateTime.now().plusDays(1L).atZone(ZoneId.systemDefault()).toInstant());
		metadata.setExpirationTime(expireTime);

		s3Client.createBucket(bucketName);
		s3Client.putObject(new PutObjectRequest(bucketName, fileName, imageFile).withMetadata(metadata));

		String imageUri = s3Client.getUrl(bucketName, fileName).toURI().toString();
		System.out.println(imageUri);

		metadata = s3Client.getObjectMetadata(bucketName, fileName);
		System.out.println(metadata);
	}
}