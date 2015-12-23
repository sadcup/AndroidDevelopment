package sadcup.android.jnaonas;

import com.sun.jna.Library;
import com.sun.jna.Pointer;

/**
 * Created by netiger on 12/23/15.
 */
public interface JNIStringDemoMapping extends Library {

    public Pointer initializeString(String str);
    public String substrString(Pointer demoString, int begin, int end);
    public void finalizeString(Pointer demoString);
}
