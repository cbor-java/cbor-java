package co.nstant.in.cbor;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import co.nstant.in.cbor.model.Array;
import co.nstant.in.cbor.model.DataItem;
import co.nstant.in.cbor.model.SimpleValue;

/**
 * [_ 1, [2, 3], [_ 4, 5]] -> 0x9f018202039f0405ffff
 */
public class Example75Test {

    private static final List<DataItem> VALUE = new CborBuilder()
                    .add(new Array().setChunked(true))
                    .add(1)
                    .addArray().add(2).add(3).end()
                    .add(new Array().setChunked(true))
                    .add(4)
                    .add(5)
                    .add(SimpleValue.BREAK)
                    .add(SimpleValue.BREAK)
                    .build();

    private static final byte[] ENCODED_VALUE = new byte[] {
                    (byte) 0x9f, 0x01, (byte) 0x82, 0x02, 0x03, (byte) 0x9f,
                    0x04, 0x05, (byte) 0xff, (byte) 0xff };

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
        decoder.setAutoDecodeInfinitiveArrays(false);
        List<DataItem> dataItems = decoder.decode();
        Assert.assertArrayEquals(VALUE.toArray(), dataItems.toArray());
    }

}
