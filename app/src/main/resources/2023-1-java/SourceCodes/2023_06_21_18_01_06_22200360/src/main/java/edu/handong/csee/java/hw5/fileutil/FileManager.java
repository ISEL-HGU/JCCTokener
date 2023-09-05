-Service-

@Transactional
public String saveImage(MultipartFile file, Long userId) {
final String bucketName = "checkmate-bucket1";

		String objectName = userId + "_" + System.currentTimeMillis() + "_" + file.getOriginalFilename(); // unique name

		try {
		ObjectMetadata objectMetadata = new ObjectMetadata();
		objectMetadata.setContentLength(file.getSize());
		objectMetadata.setContentType(file.getContentType());
		s3.putObject(bucketName, objectName, file.getInputStream(), objectMetadata);

		String uploadedImageUrl = String.format("%s/%s/%s", endPoint, bucketName, URLEncoder.encode(objectName, "UTF-8").replace("+", "%20"));
		AccessControlList accessControlList = s3.getObjectAcl(bucketName, objectName);
		accessControlList.grantPermission(GroupGrantee.AllUsers, Permission.Read);

		s3.setObjectAcl(bucketName, objectName, accessControlList);
		User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));

		user.setImage(uploadedImageUrl);
		userRepository.save(user);

		return uploadedImageUrl;

		}catch(IOException | AmazonS3Exception e) {
		throw new RuntimeException("Error uploading the image!", e);
		}

		}