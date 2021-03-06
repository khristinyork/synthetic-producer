package net.redborder.utils.types;

import junit.framework.TestCase;
import net.redborder.utils.types.impl.MacType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(MockitoJUnitRunner.class)
public class MacTypeTest extends TestCase {
    @Test
    public void get() {
        String MIN = "00:00:00:00:00:00";
        String MAX = "00:00:00:00:00:FF";


        Map<String, Object> opts = new HashMap<>();
        opts.put("max", MAX);
        opts.put("min", MIN);
        MacType type = new MacType(opts);

        long minLong = type.longFromMac(MIN);
        long maxLong = type.longFromMac(MAX);

        for (int i = 0; i < 999; i++) {
            String result = (String) type.get();
            long resultLong = type.longFromMac(result);

            if (resultLong >= maxLong || resultLong < minLong) {
                fail("Mac range violated");
            }
        }
    }
}
