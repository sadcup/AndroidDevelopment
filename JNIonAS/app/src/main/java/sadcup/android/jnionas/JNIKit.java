package sadcup.android.jnionas;

/**
 * Created by netiger on 12/22/15.
 */
public class JNIKit {
    //计算平方
    public static native int square(int num);
    //获取欢迎信息
    public static native String welcome();
    static {
        System.loadLibrary("JNIDemo");
    }
}

