package co.nstant.in.cbor;

/**
 * -1000 -> 0x3903e7
 */
public class Example18Test extends AbstractNumberTest {

    public Example18Test() {
        super(-1000, new byte[] { 0x39, 0x03, (byte) 0xe7 });
    }

}
