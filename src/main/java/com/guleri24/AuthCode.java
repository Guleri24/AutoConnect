package com.guleri24;

import javax.crypto.Mac;
        import javax.crypto.spec.SecretKeySpec;
        import java.security.InvalidKeyException;
        import java.security.NoSuchAlgorithmException;
        import java.time.Instant;
        import java.time.temporal.ChronoUnit;
        import java.util.Base64;

public class AuthCode {
    public static void main(String[] args) {
        String sharedSecret = "secret"; // Replace with your shared secret

        // Generate the current TOTP code
        String totpCode = generateTOTPCode(sharedSecret);

        System.out.println("Current TOTP code: " + totpCode);
    }

    private static String generateTOTPCode(String sharedSecret) {
        try {
            // Convert the shared secret to bytes
            byte[] sharedSecretBytes = Base64.getDecoder().decode(sharedSecret);

            // Get the current time in 30-second intervals
            Instant currentTime = Instant.now().truncatedTo(ChronoUnit.SECONDS);
            long timeWindow = currentTime.getEpochSecond() / 30;

            // Convert the time window to bytes (8 bytes, big-endian)
            byte[] timeWindowBytes = new byte[8];
            for (int i = 7; i >= 0; i--) {
                timeWindowBytes[i] = (byte) timeWindow;
                timeWindow >>= 8;
            }

            // Generate the HMAC-SHA1 hash
            Mac hmacSha1 = Mac.getInstance("HmacSHA1");
            SecretKeySpec keySpec = new SecretKeySpec(sharedSecretBytes, "HmacSHA1");
            hmacSha1.init(keySpec);
            byte[] hmacSha1Bytes = hmacSha1.doFinal(timeWindowBytes);

            // Calculate the offset and length of the dynamic code
            int offset = hmacSha1Bytes[hmacSha1Bytes.length - 1] & 0x0F;
            int length = 4;

            // Extract the dynamic code from the hash
            int dynamicCode = (
                    (hmacSha1Bytes[offset] & 0x7F) << 24 |
                            (hmacSha1Bytes[offset + 1] & 0xFF) << 16 |
                            (hmacSha1Bytes[offset + 2] & 0xFF) << 8 |
                            (hmacSha1Bytes[offset + 3] & 0xFF)
            );

            // Generate the TOTP code (digits = length)
            return String.format("%0" + 6 + "d", dynamicCode % (int) Math.pow(10, 6));
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
        }

        return null;
    }
}
