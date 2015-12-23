package sadcup.android.jnaonas;

import com.sun.jna.Library;
import com.sun.jna.Pointer;

/**
 * Created by netiger on 12/23/15.
 */
public interface JNIDemoMapping extends Library{
    public Pointer initialize(int a);
    public void run(Pointer demo);
    public int add(Pointer demo, int b);
    public void finalize(Pointer demo);


}
