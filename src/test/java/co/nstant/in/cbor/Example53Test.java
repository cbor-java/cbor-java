package co.nstant.in.cbor;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import co.nstant.in.cbor.model.DataItem;

/**
 * 32("http://www.example.com") ->
 * 0xd82076687474703a2f2f7777772e6578616d706c652e636f6d
 */
public class Example53Test {

	private static final List<DataItem> VALUE = new CborBuilder().addTag(32)
			.add("http://www.example.com").build();

	private static final byte[] ENCODED_VALUE = new byte[] { (byte) 0xd8, 0x20,
			0x76, 0x68, 0x74, 0x74, 0x70, 0x3a, 0x2f, 0x2f, 0x77, 0x77, 0x77,
			0x2e, 0x65, 0x78, 0x61, 0x6d, 0x70, 0x6c, 0x65, 0x2e, 0x63, 0x6f,
			0x6d };

	@Test
	public void shouldEncode() throws CborException {
		ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();
		CborEncoder encoder = new CborEncoder(byteOutputStream);
		encoder.encode(VALUE);
		Assert.assertArrayEquals(ENCODED_VALUE, byteOutputStream.toByteArray());
	}

	@Test
	public void shouldDecode() throws CborException {
		InputStream inputStream = new ByteArrayInputStream(ENCODED_VALUE);
		CborDecoder decoder = new CborDecoder(inputStream);
		List<DataItem> dataItems = decoder.decode();
		Assert.assertArrayEquals(VALUE.toArray(), dataItems.toArray());
	}

}
