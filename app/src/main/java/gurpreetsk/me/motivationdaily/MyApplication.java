package gurpreetsk.me.motivationdaily;

import android.app.Application;
import android.widget.Toast;


/**
 * Created by Gurpreet on 26/12/16.
 */

public class MyApplication extends Application {

    public void onCreate() {
        super.onCreate();
//        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
//            return;
//        }
//        LeakCanary.install(this);
//        Stetho.initializeWithDefaults(this);
        Toast.makeText(this, "MyApplication.java", Toast.LENGTH_SHORT).show();
    }

}
